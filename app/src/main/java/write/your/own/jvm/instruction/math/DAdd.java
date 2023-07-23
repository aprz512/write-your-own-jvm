package write.your.own.jvm.instruction.math;

import write.your.own.jvm.instruction.Instruction;
import write.your.own.jvm.runtimedata.StackFrame;

/**
 * <a href="https://docs.oracle.com/javase/specs/jvms/se7/html/jvms-6.html#jvms-6.5.dadd">...</a>
 */
public class DAdd implements Instruction {

    @Override
    public int getOpCode() {
        return 0x63;
    }

    @Override
    public void execute(StackFrame frame) {
        double d1 = frame.getOperandStack().popDouble();
        double d2 = frame.getOperandStack().popDouble();
        frame.getOperandStack().pushDouble(d2 + d1);
    }

    @Override
    public String getReadableName() {
        return "dadd";
    }
}
