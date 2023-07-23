package write.your.own.jvm.instruction.constant;

import write.your.own.jvm.instruction.CodeReader;
import write.your.own.jvm.instruction.base.Operand1Instruction;
import write.your.own.jvm.runtimedata.StackFrame;

/**
 * The immediate unsigned byte1 and byte2 values are assembled into an intermediate short where the value of the short is (byte1 << 8) | byte2.
 * The intermediate value is then sign-extended to an int value. That value is pushed onto the operand stack.
 */
public class SiPush extends Operand1Instruction {

    public SiPush(CodeReader reader) {
        super(reader);
    }

    @Override
    protected int readOperand(CodeReader reader) {
        return reader.readShort();
    }

    @Override
    public int getOpCode() {
        return 0x11;
    }

    @Override
    public void execute(StackFrame frame) {
        frame.getOperandStack().pushInt(operand);
    }

    @Override
    public String getReadableName() {
        return "sipush";
    }
}
