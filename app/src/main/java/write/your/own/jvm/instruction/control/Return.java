package write.your.own.jvm.instruction.control;

import write.your.own.jvm.instruction.Instruction;
import write.your.own.jvm.runtimedata.StackFrame;

/**
 * <a href="https://docs.oracle.com/javase/specs/jvms/se7/html/jvms-6.html#jvms-6.5.return">...</a>
 */
public class Return implements Instruction {
    @Override
    public int getOpCode() {
        return 0xb1;
    }

    @Override
    public void execute(StackFrame frame) {
        frame.getThread().popStackFrame();
    }

    @Override
    public String getReadableName() {
        return "return";
    }
}
