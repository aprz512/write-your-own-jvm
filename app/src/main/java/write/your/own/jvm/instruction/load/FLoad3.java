package write.your.own.jvm.instruction.load;

import write.your.own.jvm.instruction.base.NoOperandInstruction;
import write.your.own.jvm.runtimedata.StackFrame;

/**
 * The <n> must be an index into the local variable array of the current frame (§2.6).
 * The local variable at <n> must contain a float.
 * The value of the local variable at <n> is pushed onto the operand stack.
 */
public class FLoad3 extends NoOperandInstruction {

    @Override
    public int getOpCode() {
        return 0x25;
    }

    @Override
    public void execute(StackFrame frame) {
        float value = frame.getLocalVars().getFloat(3);
        frame.getOperandStack().pushFloat(value);
    }

    @Override
    public String getReadableName() {
        return "fload_3";
    }
}