package write.your.own.jvm.instruction.references;

import write.your.own.jvm.exception.MyJvmException;
import write.your.own.jvm.instruction.CodeReader;
import write.your.own.jvm.instruction.Instruction;
import write.your.own.jvm.instruction.base.InvokeMethod;
import write.your.own.jvm.runtimedata.OperandStack;
import write.your.own.jvm.runtimedata.StackFrame;
import write.your.own.jvm.runtimedata.heap.MyClass;
import write.your.own.jvm.runtimedata.heap.MyMethod;
import write.your.own.jvm.runtimedata.heap.MyObject;
import write.your.own.jvm.runtimedata.heap.constants.ConstantPool;
import write.your.own.jvm.runtimedata.heap.constants.InterfaceMethodRef;
import write.your.own.jvm.runtimedata.heap.constants.MethodRef;

/**
 * <a href="https://docs.oracle.com/javase/specs/jvms/se7/html/jvms-6.html#jvms-6.5.invokeinterface">...</a>
 */
public class InvokeInterface implements Instruction {

    private final int index;
    private final int count;
    private final int zero;

    public InvokeInterface(CodeReader codeReader) {
        index = codeReader.readUnsignedShort();
        // must not be zero
        count = codeReader.readUnsignedByte();
        // must always be zero
        zero = codeReader.readByte();
    }

    @Override
    public int getOpCode() {
        return 0xb9;
    }

    @Override
    public void execute(StackFrame frame) {
        ConstantPool constantPool = frame.getMyMethod().getMyClass().getConstantPool();
        ConstantPool.Constant constant = constantPool.getConstant(index);
        InterfaceMethodRef methodRef = (InterfaceMethodRef) constant.value;
        MyMethod resolvedMethod = methodRef.getResolvedInterfaceMethod();
        MyClass resolvedClass = methodRef.getResolvedClass();

        if (resolvedMethod.isStatic() || resolvedMethod.isPrivate()) {
            throw new MyJvmException("java.lang.IncompatibleClassChangeError");
        }

        OperandStack operandStack = frame.getOperandStack();
        MyObject ref = operandStack.getRefFromTop(resolvedMethod.getArgsSlotCount() - 1);
        if (ref == null) {
            throw new MyJvmException("java.lang.NullPointerException");
        }

        if (!ref.getMyClass().isImplement(resolvedClass)) {
            throw new MyJvmException("java.lang.IncompatibleClassChangeError");
        }

        MyMethod lookupMethod = MethodRef.lookupMethodInClass(ref.getMyClass(), methodRef.getName(), methodRef.getDescriptor());
        if (lookupMethod == null || lookupMethod.isAbstract()) {
            throw new MyJvmException("java.lang.AbstractMethodError");
        }

        if (!lookupMethod.isPublic()) {
            throw new MyJvmException("java.lang.IllegalAccessError");
        }
        InvokeMethod.invokeMethod(frame, lookupMethod);
    }

    @Override
    public String getReadableName() {
        return "invokeinterface";
    }
}
