package write.your.own.jvm.instruction.stack;

import write.your.own.jvm.exception.NotImplementedException;
import write.your.own.jvm.instruction.Instruction;
import write.your.own.jvm.runtimedata.StackFrame;

/**
 * Duplicate the top one or two values on the operand stack and insert the duplicated values, in the original order, into the operand stack.
 */
public class Dup2X2 implements Instruction {
    @Override
    public int getOpCode() {
        return 0x5E;
    }

    @Override
    public void execute(StackFrame frame) {
        throw new NotImplementedException();
    }

    @Override
    public String getReadableName() {
        return "dup2_x2";
    }
}
