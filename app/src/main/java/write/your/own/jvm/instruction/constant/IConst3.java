package write.your.own.jvm.instruction.constant;

import write.your.own.jvm.instruction.Instruction;
import write.your.own.jvm.runtimedata.StackFrame;

/**
 * Push the int constant (3) onto the operand stack.
 */
public class IConst3 implements Instruction {
    @Override
    public int getOpCode() {
        return 0x6;
    }

    @Override
    public void execute(StackFrame frame) {
        frame.getOperandStack().pushInt(3);
    }

    @Override
    public String getReadableName() {
        return "iconst_3";
    }
}
