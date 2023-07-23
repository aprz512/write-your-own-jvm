package write.your.own.jvm.instruction.stack;

import write.your.own.jvm.instruction.Instruction;
import write.your.own.jvm.runtimedata.StackFrame;

/**
 * Pop the top value from the operand stack.
 * The pop instruction must not be used unless value is a value of a category 1 computational type (§2.11.1).
 */
public class Pop implements Instruction {
    @Override
    public int getOpCode() {
        return 0x57;
    }

    @Override
    public void execute(StackFrame frame) {
        frame.getOperandStack().popSlot();
    }

    @Override
    public String getReadableName() {
        return "pop";
    }
}
