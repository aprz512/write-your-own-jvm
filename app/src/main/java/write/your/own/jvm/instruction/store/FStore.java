package write.your.own.jvm.instruction.store;

import write.your.own.jvm.instruction.CodeReader;
import write.your.own.jvm.instruction.base.Operand1Instruction;
import write.your.own.jvm.runtimedata.StackFrame;

/**
 * The index is an unsigned byte that must be an index into the local variable array of the current frame (§2.6).
 * The value on the top of the operand stack must be of type float.
 * It is popped from the operand stack and undergoes value set conversion (§2.8.3), resulting in value'.
 * The value of the local variable at index is set to value'.
 */
public class FStore extends Operand1Instruction {

    public FStore(CodeReader reader) {
        super(reader);
    }

    @Override
    protected int readOperand(CodeReader reader) {
        return reader.readUnsignedByte();
    }

    @Override
    public int getOpCode() {
        return 0x38;
    }

    @Override
    public void execute(StackFrame frame) {
        float value = frame.getOperandStack().popFloat();
        frame.getLocalVariableTable().setFloat(operand, value);
    }

    @Override
    public String getReadableName() {
        return "fstore";
    }
}
