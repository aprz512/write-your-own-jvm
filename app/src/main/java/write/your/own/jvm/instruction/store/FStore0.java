package write.your.own.jvm.instruction.store;

import write.your.own.jvm.instruction.Instruction;
import write.your.own.jvm.runtimedata.StackFrame;

/**
 * The <n> must be an index into the local variable array of the current frame (§2.6).
 * The value on the top of the operand stack must be of type float.
 * It is popped from the operand stack and undergoes value set conversion (§2.8.3), resulting in value'.
 * The value of the local variable at <n> is set to value'.
 */
public class FStore0 implements Instruction {

    @Override
    public int getOpCode() {
        return 0x43;
    }

    @Override
    public void execute(StackFrame frame) {
        float value = frame.getOperandStack().popFloat();
        frame.getLocalVars().setFloat(0, value);
    }

    @Override
    public String getReadableName() {
        return "fstore_0";
    }
}
