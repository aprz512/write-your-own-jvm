package write.your.own.jvm.instruction.stack;

import write.your.own.jvm.instruction.Instruction;
import write.your.own.jvm.runtimedata.OperandStack;
import write.your.own.jvm.runtimedata.Slot;
import write.your.own.jvm.runtimedata.StackFrame;

/**
 * Duplicate the top one or two values on the operand stack and
 * push the duplicated value or values back onto the operand stack in the original order.
 */
public class Dup2 implements Instruction {
    @Override
    public int getOpCode() {
        return 0x5C;
    }

    @Override
    public void execute(StackFrame frame) {
        OperandStack operandStack = frame.getOperandStack();
        Slot slot1 = operandStack.popSlot();
        Slot slot2 = operandStack.popSlot();
        operandStack.pushSlot(slot1);
        operandStack.pushSlot(slot2);
        operandStack.pushSlot(slot1);
        operandStack.pushSlot(slot2);
    }

    @Override
    public String getReadableName() {
        return "dup2";
    }
}
