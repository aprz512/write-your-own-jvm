package write.your.own.jvm.instruction.constant;

import write.your.own.jvm.instruction.base.NoOperandInstruction;
import write.your.own.jvm.runtimedata.StackFrame;

/**
 * Push the long  constant (0) onto the operand stack.
 */
public class LConst0 extends NoOperandInstruction {
    @Override
    public int getOpCode() {
        return 0x09;
    }

    @Override
    public void execute(StackFrame frame) {
        frame.getOperandStack().pushLong(0L);
    }

    @Override
    public String getReadableName() {
        return "lconst_0";
    }
}
