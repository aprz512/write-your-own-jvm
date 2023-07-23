package write.your.own.jvm.instruction.constant;

import write.your.own.jvm.instruction.Instruction;
import write.your.own.jvm.runtimedata.StackFrame;

/**
 * Push the float constant (0.0) onto the operand stack.
 */
public class FConst0 implements Instruction {
    @Override
    public int getOpCode() {
        return 0x0B;
    }

    @Override
    public void execute(StackFrame frame) {
        frame.getOperandStack().pushFloat(0.0f);
    }

    @Override
    public String getReadableName() {
        return "fconst_0";
    }
}
