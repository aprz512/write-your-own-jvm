package write.your.own.jvm.instruction.stack;

import write.your.own.jvm.instruction.Instruction;
import write.your.own.jvm.runtimedata.OperandStack;
import write.your.own.jvm.runtimedata.Slot;
import write.your.own.jvm.runtimedata.StackFrame;

/**
 * Duplicate the top value on the operand stack and push the duplicated value onto the operand stack.
 * The dup instruction must not be used unless value is a value of a category 1 computational type (§2.11.1).
 */
public class Dup implements Instruction {
    @Override
    public int getOpCode() {
        return 0x59;
    }

    @Override
    public void execute(StackFrame frame) {
        OperandStack operandStack = frame.getOperandStack();
        Slot slot = operandStack.popSlot();
        operandStack.pushSlot(slot);
        operandStack.pushSlot(slot);
    }

    @Override
    public String getReadableName() {
        return "dup";
    }
}
