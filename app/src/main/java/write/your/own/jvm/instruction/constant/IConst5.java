package write.your.own.jvm.instruction.constant;

import write.your.own.jvm.instruction.Instruction;
import write.your.own.jvm.runtimedata.StackFrame;

/**
 * Push the int constant (5) onto the operand stack.
 */
public class IConst5 implements Instruction {
    @Override
    public int getOpCode() {
        return 0x8;
    }

    @Override
    public void execute(StackFrame frame) {
        frame.getOperandStack().pushInt(5);
    }

    @Override
    public String getReadableName() {
        return "iconst_5";
    }
}
