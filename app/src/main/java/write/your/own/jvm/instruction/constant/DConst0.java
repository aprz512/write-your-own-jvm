package write.your.own.jvm.instruction.constant;

import write.your.own.jvm.instruction.base.NoOperandInstruction;
import write.your.own.jvm.runtimedata.StackFrame;

/**
 * Push the double constant (0.0) onto the operand stack.
 */
public class DConst0 extends NoOperandInstruction {
    @Override
    public int getOpCode() {
        return 0x0E;
    }

    @Override
    public void execute(StackFrame frame) {
        frame.getOperandStack().pushDouble(0.0d);
    }

    @Override
    public String getReadableName() {
        return "dconst_0";
    }
}
