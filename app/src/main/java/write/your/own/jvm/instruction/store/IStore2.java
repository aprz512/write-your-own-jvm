package write.your.own.jvm.instruction.store;

import write.your.own.jvm.instruction.Instruction;
import write.your.own.jvm.runtimedata.StackFrame;

/**
 * The <n> must be an index into the local variable array of the current frame (§2.6).
 * The value on the top of the operand stack must be of type int.
 * It is popped from the operand stack, and the value of the local variable at <n> is set to value.
 */
public class IStore2 implements Instruction {

    @Override
    public int getOpCode() {
        return 0x3D;
    }

    @Override
    public void execute(StackFrame frame) {
        int value = frame.getOperandStack().popInt();
        frame.getLocalVars().setInt(2, value);
    }

    @Override
    public String getReadableName() {
        return "istore_2";
    }
}
