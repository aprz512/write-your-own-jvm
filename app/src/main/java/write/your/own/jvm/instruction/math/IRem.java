package write.your.own.jvm.instruction.math;

import write.your.own.jvm.instruction.Instruction;
import write.your.own.jvm.runtimedata.StackFrame;

/**
 * <a href="https://docs.oracle.com/javase/specs/jvms/se7/html/jvms-6.html#jvms-6.5.irem">...</a>
 */
public class IRem implements Instruction {
    @Override
    public int getOpCode() {
        return 0x70;
    }

    @Override
    public void execute(StackFrame frame) {
        int v1 = frame.getOperandStack().popInt();
        int v2 = frame.getOperandStack().popInt();
        frame.getOperandStack().pushInt(v2 % v1);
    }

    @Override
    public String getReadableName() {
        return "irem";
    }
}
