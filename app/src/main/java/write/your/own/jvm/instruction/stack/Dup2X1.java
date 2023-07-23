package write.your.own.jvm.instruction.stack;

import write.your.own.jvm.exception.NotImplementedException;
import write.your.own.jvm.instruction.Instruction;
import write.your.own.jvm.runtimedata.StackFrame;

/**
 * Duplicate the top one or two values on the operand stack and insert the duplicated values,
 * in the original order, one value beneath the original value or values in the operand stack.
 */
public class Dup2X1 implements Instruction {
    @Override
    public int getOpCode() {
        return 0x5D;
    }

    @Override
    public void execute(StackFrame frame) {
        throw new NotImplementedException();
    }

    @Override
    public String getReadableName() {
        return "dup2_x1";
    }
}
