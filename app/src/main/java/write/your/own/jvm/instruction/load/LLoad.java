package write.your.own.jvm.instruction.load;

import write.your.own.jvm.instruction.CodeReader;
import write.your.own.jvm.instruction.base.Operand1Instruction;
import write.your.own.jvm.runtimedata.StackFrame;

/**
 * The index is an unsigned byte.
 * Both index and index+1 must be indices into the local variable array of the current frame (§2.6).
 * The local variable at index must contain a long.
 * The value of the local variable at index is pushed onto the operand stack.
 */
public class LLoad extends Operand1Instruction {

    public LLoad(CodeReader reader) {
        super(reader);
    }

    @Override
    protected int readOperand(CodeReader reader) {
        return reader.readUnsignedByte();
    }

    @Override
    public int getOpCode() {
        return 0x16;
    }

    @Override
    public void execute(StackFrame frame) {
        long value = frame.getLocalVariableTable().getLong(operand);
        frame.getOperandStack().pushLong(value);
    }

    @Override
    public String getReadableName() {
        return "lload";
    }
}
