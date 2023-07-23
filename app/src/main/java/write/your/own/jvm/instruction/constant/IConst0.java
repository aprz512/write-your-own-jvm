package write.your.own.jvm.instruction.constant;

import write.your.own.jvm.instruction.base.NoOperandInstruction;
import write.your.own.jvm.runtimedata.StackFrame;

/**
 * Push the int constant (0) onto the operand stack.
 */
public class IConst0 extends NoOperandInstruction {
    @Override
    public int getOpCode() {
        return 0x3;
    }

    @Override
    public void execute(StackFrame frame) {
        frame.getOperandStack().pushInt(0);
    }

    @Override
    public String getReadableName() {
        return "iconst_0";
    }
}
