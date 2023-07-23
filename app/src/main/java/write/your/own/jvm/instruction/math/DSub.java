package write.your.own.jvm.instruction.math;

import write.your.own.jvm.instruction.Instruction;
import write.your.own.jvm.runtimedata.StackFrame;

/**
 * <a href="https://docs.oracle.com/javase/specs/jvms/se7/html/jvms-6.html#jvms-6.5.dsub">...</a>
 */
public class DSub implements Instruction {
    @Override
    public int getOpCode() {
        return 0x67;
    }

    @Override
    public void execute(StackFrame frame) {
        double v1 = frame.getOperandStack().popDouble();
        double v2 = frame.getOperandStack().popDouble();
        frame.getOperandStack().pushDouble(v2 - v1);
    }

    @Override
    public String getReadableName() {
        return "dsub";
    }
}
