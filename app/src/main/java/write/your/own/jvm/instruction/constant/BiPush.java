package write.your.own.jvm.instruction.constant;

import write.your.own.jvm.instruction.CodeReader;
import write.your.own.jvm.instruction.base.Operand1Instruction;
import write.your.own.jvm.runtimedata.StackFrame;

/**
 * The immediate byte is sign-extended to an int value.
 * That value is pushed onto the operand stack.
 */
public class BiPush extends Operand1Instruction {

    public BiPush(CodeReader reader) {
        super(reader);
    }

    @Override
    public int getOpCode() {
        return 0x10;
    }

    @Override
    public void execute(StackFrame frame) {
        frame.getOperandStack().pushInt(operand);
    }

    @Override
    public String getReadableName() {
        return "bipush";
    }

    @Override
    protected int readOperand(CodeReader reader) {
        return reader.readByte();
    }
}
