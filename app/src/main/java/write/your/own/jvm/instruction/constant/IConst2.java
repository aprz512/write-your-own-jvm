package write.your.own.jvm.instruction.constant;

import write.your.own.jvm.instruction.Instruction;
import write.your.own.jvm.runtimedata.StackFrame;

/**
 * Push the int constant (2) onto the operand stack.
 */
public class IConst2 implements Instruction {
    @Override
    public int getOpCode() {
        return 0x5;
    }

    @Override
    public void execute(StackFrame frame) {
        frame.getOperandStack().pushInt(2);
    }

    @Override
    public String getReadableName() {
        return "iconst_2";
    }
}
