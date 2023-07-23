package write.your.own.jvm.instruction.constant;

import write.your.own.jvm.instruction.base.NoOperandInstruction;
import write.your.own.jvm.runtimedata.StackFrame;

/**
 * Push the long  constant (1) onto the operand stack.
 */
public class LConst1 extends NoOperandInstruction {
    @Override
    public int getOpCode() {
        return 0xA;
    }

    @Override
    public void execute(StackFrame frame) {
        frame.getOperandStack().pushLong(1L);
    }

    @Override
    public String getReadableName() {
        return "lconst_1";
    }
}
