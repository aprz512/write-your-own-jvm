package write.your.own.jvm.instruction.math;

import write.your.own.jvm.instruction.Instruction;
import write.your.own.jvm.runtimedata.StackFrame;

/**
 * <a href="https://docs.oracle.com/javase/specs/jvms/se7/html/jvms-6.html#jvms-6.5.dneg">...</a>
 */
public class DNeg implements Instruction {
    @Override
    public int getOpCode() {
        return 0x77;
    }

    @Override
    public void execute(StackFrame frame) {
        double v1 = frame.getOperandStack().popDouble();
        frame.getOperandStack().pushDouble(-v1);
    }

    @Override
    public String getReadableName() {
        return "dneg";
    }
}
