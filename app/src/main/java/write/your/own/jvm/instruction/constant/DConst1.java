package write.your.own.jvm.instruction.constant;

import write.your.own.jvm.instruction.Instruction;
import write.your.own.jvm.runtimedata.StackFrame;

/**
 * Push the double constant (1.0) onto the operand stack.
 */
public class DConst1 implements Instruction {
    @Override
    public int getOpCode() {
        return 0x0F;
    }

    @Override
    public void execute(StackFrame frame) {
        frame.getOperandStack().pushDouble(1.0d);
    }

    @Override
    public String getReadableName() {
        return "dconst_1";
    }
}
