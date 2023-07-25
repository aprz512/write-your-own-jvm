package write.your.own.jvm.instruction.store;

import write.your.own.jvm.instruction.CodeReader;
import write.your.own.jvm.instruction.base.Operand1Instruction;
import write.your.own.jvm.runtimedata.StackFrame;

/**
 * The index is an unsigned byte.
 * Both index and index+1 must be indices into the local variable array of the current frame (§2.6).
 * The value on the top of the operand stack must be of type double.
 * It is popped from the operand stack and undergoes value set conversion (§2.8.3), resulting in value'.
 * The local variables at index and index+1 are set to value'.
 */
public class DStore extends Operand1Instruction {

    public DStore(CodeReader reader) {
        super(reader);
    }

    @Override
    protected int readOperand(CodeReader reader) {
        return reader.readUnsignedByte();
    }

    @Override
    public int getOpCode() {
        return 0x39;
    }

    @Override
    public void execute(StackFrame frame) {
        double value = frame.getOperandStack().popDouble();
        frame.getLocalVariableTable().setDouble(operand, value);
    }

    @Override
    public String getReadableName() {
        return "dstore";
    }
}
