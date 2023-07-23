package write.your.own.jvm.instruction.constant;

import write.your.own.jvm.instruction.base.NoOperandInstruction;
import write.your.own.jvm.runtimedata.StackFrame;

/**
 * Push the float constant (2.0) onto the operand stack.
 */
public class FConst2 extends NoOperandInstruction {
    @Override
    public int getOpCode() {
        return 0x0D;
    }

    @Override
    public void execute(StackFrame frame) {
        frame.getOperandStack().pushFloat(2.0f);
    }

    @Override
    public String getReadableName() {
        return "fconst_2";
    }
}
