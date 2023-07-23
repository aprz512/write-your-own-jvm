package write.your.own.jvm.instruction.math;

import write.your.own.jvm.instruction.Instruction;
import write.your.own.jvm.runtimedata.StackFrame;

/**
 * <a href="https://docs.oracle.com/javase/specs/jvms/se7/html/jvms-6.html#jvms-6.5.lshr">...</a>
 */
public class LShr implements Instruction {
    @Override
    public int getOpCode() {
        return 0x7B;
    }

    @Override
    public void execute(StackFrame frame) {
        int v1 = frame.getOperandStack().popInt();
        long v2 = frame.getOperandStack().popLong();
        int s = v1 & 0x3f;
        long ret = v2 >> s;
        frame.getOperandStack().pushLong(ret);
    }

    @Override
    public String getReadableName() {
        return "lshr";
    }
}
