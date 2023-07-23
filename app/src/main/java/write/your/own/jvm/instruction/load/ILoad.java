package write.your.own.jvm.instruction.load;

import write.your.own.jvm.instruction.CodeReader;
import write.your.own.jvm.instruction.base.Operand1Instruction;
import write.your.own.jvm.runtimedata.StackFrame;

/**
 * iload
 * index
 * <p>
 * The index is an unsigned byte that must be an index into the local variable array of the current frame (§2.6).
 * The local variable at index must contain an int. The value of the local variable at index is pushed onto the operand stack.
 */
public class ILoad extends Operand1Instruction {
    public ILoad(CodeReader reader) {
        super(reader);
    }

    @Override
    protected int readOperand(CodeReader reader) {
        return reader.readUnsignedByte();
    }

    @Override
    public int getOpCode() {
        return 0x15;
    }

    @Override
    public void execute(StackFrame frame) {
        int local = frame.getLocalVars().getInt(operand);
        frame.getOperandStack().pushInt(local);
    }

    @Override
    public String getReadableName() {
        return "iload";
    }
}
