package write.your.own.jvm.instruction.stack;

import write.your.own.jvm.instruction.Instruction;
import write.your.own.jvm.runtimedata.OperandStack;
import write.your.own.jvm.runtimedata.Slot;
import write.your.own.jvm.runtimedata.StackFrame;

/**
 * Swap the top two values on the operand stack.
 * <p>
 * The swap instruction must not be used unless value1 and value2 are both values of a category 1 computational type (§2.11.1).
 */
public class Swap implements Instruction {
    @Override
    public int getOpCode() {
        return 0x5F;
    }

    @Override
    public void execute(StackFrame frame) {
        OperandStack operandStack = frame.getOperandStack();
        Slot slot1 = operandStack.popSlot();
        Slot slot2 = operandStack.popSlot();
        operandStack.pushSlot(slot1);
        operandStack.pushSlot(slot2);
    }

    @Override
    public String getReadableName() {
        return "swap";
    }
}
