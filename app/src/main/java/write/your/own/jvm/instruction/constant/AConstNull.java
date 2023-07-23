package write.your.own.jvm.instruction.constant;

import write.your.own.jvm.instruction.base.NoOperandInstruction;
import write.your.own.jvm.runtimedata.StackFrame;

/**
 * Push null
 */
public class AConstNull extends NoOperandInstruction {
    @Override
    public int getOpCode() {
        return 0x01;
    }

    @Override
    public void execute(StackFrame frame) {
        frame.getOperandStack().pushRef(null);
    }

    @Override
    public String getReadableName() {
        return "aconst_null";
    }
}
