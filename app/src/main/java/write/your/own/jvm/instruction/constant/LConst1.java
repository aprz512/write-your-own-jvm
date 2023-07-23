package write.your.own.jvm.instruction.constant;

import write.your.own.jvm.instruction.Instruction;
import write.your.own.jvm.runtimedata.StackFrame;

/**
 * Push the long  constant (1) onto the operand stack.
 */
public class LConst1 implements Instruction {
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
