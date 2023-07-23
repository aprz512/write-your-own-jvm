package write.your.own.jvm.instruction.constant;

import write.your.own.jvm.instruction.base.NoOperandInstruction;
import write.your.own.jvm.runtimedata.StackFrame;

public class Nop extends NoOperandInstruction {
    @Override
    public int getOpCode() {
        return 0x0;
    }

    @Override
    public void execute(StackFrame frame) {

    }

    @Override
    public String getReadableName() {
        return "nop";
    }
}
