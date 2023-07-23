package write.your.own.jvm.instruction.constant;

import write.your.own.jvm.instruction.Instruction;
import write.your.own.jvm.runtimedata.StackFrame;

public class Nop implements Instruction {
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
