package write.your.own.jvm.instruction.constant;

import write.your.own.jvm.instruction.Instruction;
import write.your.own.jvm.runtimedata.StackFrame;

/**
 * Push the int constant (4) onto the operand stack.
 */
public class IConst4 implements Instruction {
    @Override
    public int getOpCode() {
        return 0x7;
    }

    @Override
    public void execute(StackFrame frame) {
        frame.getOperandStack().pushInt(4);
    }

    @Override
    public String getReadableName() {
        return "iconst_4";
    }
}
