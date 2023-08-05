package write.your.own.jvm.instruction.reserved;

import write.your.own.jvm.exception.MyJvmException;
import write.your.own.jvm.instruction.Instruction;
import write.your.own.jvm.runtimedata.StackFrame;
import write.your.own.jvm.runtimedata.heap.MyMethod;
import write.your.own.jvm.vnative.NativeMethod;
import write.your.own.jvm.vnative.NativeRegistry;

public class InvokeNative implements Instruction {
    @Override
    public int getOpCode() {
        return 0xfe;
    }

    @Override
    public void execute(StackFrame frame) {
        MyMethod myMethod = frame.getMyMethod();
        String thisClassName = myMethod.getMyClass().getThisClassName();
        String methodName = myMethod.getName();
        String methodDescriptor = myMethod.getDescriptor();
        NativeMethod nativeMethod = NativeRegistry.getInstance().findNativeMethod(thisClassName, methodName, methodDescriptor);
        if (nativeMethod == null) {
            String methodInfo = thisClassName + "." + methodName + methodDescriptor;
            throw new MyJvmException("java.lang.UnsatisfiedLinkError: " + methodInfo);
        }
        nativeMethod.invoke(frame);
    }

    @Override
    public String getReadableName() {
        return "invokeNative";
    }

}
