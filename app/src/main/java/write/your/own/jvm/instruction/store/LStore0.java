package write.your.own.jvm.instruction.store;

import write.your.own.jvm.instruction.Instruction;
import write.your.own.jvm.runtimedata.StackFrame;

/**
 * Both <n> and <n>+1 must be indices into the local variable array of the current frame (§2.6).
 * The value on the top of the operand stack must be of type long.
 * It is popped from the operand stack, and the local variables at <n> and <n>+1 are set to value.
 */
public class LStore0 implements Instruction {

    @Override
    public int getOpCode() {
        return 0x3F;
    }

    @Override
    public void execute(StackFrame frame) {
        long value = frame.getOperandStack().popLong();
        frame.getLocalVariableTable().setLong(0, value);
    }

    @Override
    public String getReadableName() {
        return "lstore_0";
    }
}
