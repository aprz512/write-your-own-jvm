package write.your.own.jvm.instruction.load;

import write.your.own.jvm.instruction.base.NoOperandInstruction;
import write.your.own.jvm.runtimedata.StackFrame;

/**
 * The index is an unsigned byte.
 * Both index and index+1 must be indices into the local variable array of the current frame (§2.6).
 * The local variable at index must contain a long.
 * The value of the local variable at index is pushed onto the operand stack.
 */
public class LLoad2 extends NoOperandInstruction {

    @Override
    public int getOpCode() {
        return 0x20;
    }

    @Override
    public void execute(StackFrame frame) {
        long value = frame.getLocalVars().getLong(2);
        frame.getOperandStack().pushLong(value);
    }

    @Override
    public String getReadableName() {
        return "lload_2";
    }
}
