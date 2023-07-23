package write.your.own.jvm.instruction.constant;

import write.your.own.jvm.instruction.Instruction;
import write.your.own.jvm.runtimedata.StackFrame;

/**
 * Push the int constant (-1) onto the operand stack.
 */
public class IConstM1 implements Instruction {
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
