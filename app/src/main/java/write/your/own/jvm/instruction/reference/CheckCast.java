package write.your.own.jvm.instruction.reference;

import write.your.own.jvm.exception.MyJvmException;
import write.your.own.jvm.instruction.CodeReader;
import write.your.own.jvm.instruction.base.Operand1Instruction;
import write.your.own.jvm.runtimedata.OperandStack;
import write.your.own.jvm.runtimedata.StackFrame;
import write.your.own.jvm.runtimedata.heap.MyClass;
import write.your.own.jvm.runtimedata.heap.MyObject;
import write.your.own.jvm.runtimedata.heap.constants.ClassRef;
import write.your.own.jvm.runtimedata.heap.constants.ConstantPool;

/**
 * <a href="https://docs.oracle.com/javase/specs/jvms/se7/html/jvms-6.html#jvms-6.5.checkcast">...</a>
 */
public class CheckCast extends Operand1Instruction {

    public CheckCast(CodeReader reader) {
        super(reader);
    }

    @Override
    public int getOpCode() {
        return 0xc0;
    }

    @Override
    public void execute(StackFrame frame) {
        OperandStack operandStack = frame.getOperandStack();
        MyObject ref = operandStack.popRef();
        operandStack.pushRef(ref);
        if (ref == null) {
            return;
        }
        ConstantPool constantPool = frame.getMyMethod().getMyClass().getConstantPool();
        ConstantPool.Constant constant = constantPool.getConstant(operand);
        ClassRef classRef = (ClassRef) constant.value;
        MyClass myClass = classRef.getResolvedClass();
        if (!ref.isInstanceOf(myClass)) {
            throw new MyJvmException("java.lang.ClassCastException");
        }
    }

    @Override
    public String getReadableName() {
        return "checkcast";
    }

    @Override
    protected int readOperand(CodeReader reader) {
        return reader.readUnsignedShort();
    }
}
