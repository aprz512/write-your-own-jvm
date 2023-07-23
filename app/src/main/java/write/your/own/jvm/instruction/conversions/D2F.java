package write.your.own.jvm.instruction.conversions;

import write.your.own.jvm.instruction.Instruction;
import write.your.own.jvm.runtimedata.StackFrame;

/**
 * <a href="https://docs.oracle.com/javase/specs/jvms/se7/html/jvms-6.html#jvms-6.5.d2f">...</a>
 */
public class D2F implements Instruction {

    @Override
    public int getOpCode() {
        return 0x90;
    }

    @Override
    public void execute(StackFrame frame) {
        double d1 = frame.getOperandStack().popDouble();
        frame.getOperandStack().pushFloat((float) d1);
    }

    @Override
    public String getReadableName() {
        return "d2f";
    }
}
