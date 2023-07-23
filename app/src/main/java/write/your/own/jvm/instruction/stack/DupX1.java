package write.your.own.jvm.instruction.stack;

import write.your.own.jvm.instruction.Instruction;
import write.your.own.jvm.runtimedata.OperandStack;
import write.your.own.jvm.runtimedata.Slot;
import write.your.own.jvm.runtimedata.StackFrame;

/**
 * Duplicate the top value on the operand stack and insert the duplicated value two values down in the operand stack.
 * The dup_x1 instruction must not be used unless both value1 and value2 are values of a category 1 computational type (§2.11.1).
 */
public class DupX1 implements Instruction {
    @Override
    public int getOpCode() {
        return 0x5A;
    }

    @Override
    public void execute(StackFrame frame) {
        OperandStack operandStack = frame.getOperandStack();
        Slot slot1 = operandStack.popSlot();
        Slot slot2 = operandStack.popSlot();
        operandStack.pushSlot(slot1);
        operandStack.pushSlot(slot2);
        operandStack.pushSlot(slot1);
    }

    @Override
    public String getReadableName() {
        return "dup_x1";
    }
}
