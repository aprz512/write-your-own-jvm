package write.your.own.jvm.instruction.stack;

import write.your.own.jvm.instruction.Instruction;
import write.your.own.jvm.runtimedata.OperandStack;
import write.your.own.jvm.runtimedata.StackFrame;

/**
 * Pop the top one or two values from the operand stack.
 */
public class Pop2 implements Instruction {
    @Override
    public int getOpCode() {
        return 0x58;
    }

    @Override
    public void execute(StackFrame frame) {
        OperandStack operandStack = frame.getOperandStack();
        operandStack.popSlot();
        operandStack.popSlot();
    }

    @Override
    public String getReadableName() {
        return "pop2";
    }
}
