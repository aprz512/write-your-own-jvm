package write.your.own.jvm.instruction.load;

import write.your.own.jvm.instruction.base.NoOperandInstruction;
import write.your.own.jvm.runtimedata.StackFrame;

/**
 * Both <n> and <n>+1 must be indices into the local variable array of the current frame (§2.6).
 * The local variable at <n> must contain a double.
 * The value of the local variable at <n> is pushed onto the operand stack.
 */
public class DLoad0 extends NoOperandInstruction {

    @Override
    public int getOpCode() {
        return 0x26;
    }

    @Override
    public void execute(StackFrame frame) {
        double value = frame.getLocalVars().getDouble(0);
        frame.getOperandStack().pushDouble(value);
    }

    @Override
    public String getReadableName() {
        return "dload_0";
    }
}
