package write.your.own.jvm.instruction.constant;

import write.your.own.jvm.classfile.constantpool.ConstantInfo;
import write.your.own.jvm.exception.NotImplementedException;
import write.your.own.jvm.instruction.CodeReader;
import write.your.own.jvm.instruction.base.Operand1Instruction;
import write.your.own.jvm.runtimedata.OperandStack;
import write.your.own.jvm.runtimedata.StackFrame;
import write.your.own.jvm.runtimedata.heap.constants.ConstantPool;

/**
 * <a href="https://docs.oracle.com/javase/specs/jvms/se7/html/jvms-6.html#jvms-6.5.ldc2_w">...</a>
 */
public class Ldc2W extends Operand1Instruction {

    public Ldc2W(CodeReader reader) {
        super(reader);
    }

    @Override
    public int getOpCode() {
        return 0x14;
    }

    @Override
    public void execute(StackFrame frame) {
        OperandStack operandStack = frame.getOperandStack();
        ConstantPool constantPool = frame.getMyMethod().getMyClass().getConstantPool();
        ConstantPool.Constant constant = constantPool.getConstant(operand);
        switch (constant.tag) {
            case ConstantInfo.CONST_TAG_LONG:
                operandStack.pushLong((Long) constant.value);
                break;
            case ConstantInfo.CONST_TAG_DOUBLE:
                operandStack.pushDouble((Double) constant.value);
                break;
            default:
                throw new NotImplementedException();
        }
    }

    @Override
    public String getReadableName() {
        return "ldc2_w";
    }

    @Override
    protected int readOperand(CodeReader reader) {
        return reader.readUnsignedShort();
    }
}
