package write.your.own.jvm.instruction.stack;

import write.your.own.jvm.instruction.Instruction;
import write.your.own.jvm.runtimedata.OperandStack;
import write.your.own.jvm.runtimedata.Slot;
import write.your.own.jvm.runtimedata.StackFrame;

/**
 * Duplicate the top value on the operand stack and insert the duplicated value two or three values down in the operand stack.
 */
public class DupX2 implements Instruction {
    @Override
    public int getOpCode() {
        return 0x5B;
    }

    @Override
    public void execute(StackFrame frame) {
        OperandStack stack = frame.getOperandStack();
        Slot slot1 = stack.popSlot();
        Slot slot2 = stack.popSlot();
        Slot slot3 = stack.popSlot();
        stack.pushSlot(slot1);
        stack.pushSlot(slot3);
        stack.pushSlot(slot2);
        stack.pushSlot(slot1);
    }

    @Override
    public String getReadableName() {
        return "dup_x2";
    }
}
