package write.your.own.jvm.instruction.constant;

import write.your.own.jvm.instruction.base.NoOperandInstruction;
import write.your.own.jvm.runtimedata.StackFrame;

/**
 * Push the int constant (-1) onto the operand stack.
 */
public class IConstM1 extends NoOperandInstruction {
    @Override
    public int getOpCode() {
        return 0x2;
    }

    @Override
    public void execute(StackFrame frame) {
        frame.getOperandStack().pushInt(-1);
    }

    @Override
    public String getReadableName() {
        return "iconst_m1";
    }
}
