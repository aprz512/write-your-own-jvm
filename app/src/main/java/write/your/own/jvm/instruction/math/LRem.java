package write.your.own.jvm.instruction.math;

import write.your.own.jvm.instruction.Instruction;
import write.your.own.jvm.runtimedata.StackFrame;

/**
 * <a href="https://docs.oracle.com/javase/specs/jvms/se7/html/jvms-6.html#jvms-6.5.lrem">...</a>
 */
public class LRem implements Instruction {
    @Override
    public int getOpCode() {
        return 0x71;
    }

    @Override
    public void execute(StackFrame frame) {
        long v1 = frame.getOperandStack().popLong();
        long v2 = frame.getOperandStack().popLong();
        frame.getOperandStack().pushLong(v2 % v1);
    }

    @Override
    public String getReadableName() {
        return "lrem";
    }
}
