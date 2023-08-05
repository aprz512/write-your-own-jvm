package write.your.own.jvm.instruction.reference;

import write.your.own.jvm.exception.MyJvmException;
import write.your.own.jvm.instruction.CodeReader;
import write.your.own.jvm.instruction.base.InvokeMethod;
import write.your.own.jvm.instruction.base.Operand1Instruction;
import write.your.own.jvm.runtimedata.StackFrame;
import write.your.own.jvm.runtimedata.heap.ClassInit;
import write.your.own.jvm.runtimedata.heap.MyClass;
import write.your.own.jvm.runtimedata.heap.MyMethod;
import write.your.own.jvm.runtimedata.heap.constants.ConstantPool;
import write.your.own.jvm.runtimedata.heap.constants.MethodRef;

/**
 * <a href="https://docs.oracle.com/javase/specs/jvms/se7/html/jvms-6.html#jvms-6.5.invokestatic">...</a>
 */
public class InvokeStatic extends Operand1Instruction {

    public InvokeStatic(CodeReader reader) {
        super(reader);
    }

    @Override
    public int getOpCode() {
        return 0xb8;
    }

    @Override
    public void execute(StackFrame frame) {
        ConstantPool constantPool = frame.getMyMethod().getMyClass().getConstantPool();
        ConstantPool.Constant constant = constantPool.getConstant(operand);
        MethodRef methodRef = (MethodRef) constant.value;
        MyMethod resolvedMethod = methodRef.getResolvedMethod();
        if (!resolvedMethod.isStatic()) {
            throw new MyJvmException("java.lang.IncompatibleClassChangeError");
        }
        // 这一规则由class文件验证器保证，这里不做检查。
//        if(resolvedMethod.isInterface()
//        || resolvedMethod.isAbstract()
//        || resolvedMethod.getName().equals("<init>")
//        || resolvedMethod.getName().equals("<clinit>")) {
//            throw new MyJvmException("");
//        }
        MyClass resolvedClass = methodRef.getResolvedClass();
        if (!resolvedClass.isInitStarted()) {
            frame.revertPc();
            ClassInit.initMyClass(resolvedClass, frame.getThread());
            return;
        }

        InvokeMethod.invokeMethod(frame, resolvedMethod);
    }

    @Override
    public String getReadableName() {
        return "invokestatic";
    }

    @Override
    protected int readOperand(CodeReader reader) {
        return reader.readUnsignedShort();
    }
}
