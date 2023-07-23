package write.your.own.jvm.instruction.store;

import write.your.own.jvm.instruction.CodeReader;
import write.your.own.jvm.instruction.base.Operand1Instruction;
import write.your.own.jvm.runtimedata.StackFrame;

/**
 * The index is an unsigned byte.
 * Both index and index+1 must be indices into the local variable array of the current frame (§2.6).
 * The value on the top of the operand stack must be of type long.
 * It is popped from the operand stack, and the local variables at index and index+1 are set to value.
 */
public class LStore extends Operand1Instruction {

    public LStore(CodeReader reader) {
        super(reader);
    }

    @Override
    protected int readOperand(CodeReader reader) {
        return reader.readUnsignedByte();
    }

    @Override
    public int getOpCode() {
        return 0x37;
    }

    @Override
    public void execute(StackFrame frame) {
        long value = frame.getOperandStack().popLong();
        frame.getLocalVars().setLong(operand, value);
    }

    @Override
    public String getReadableName() {
        return "lstore";
    }
}
