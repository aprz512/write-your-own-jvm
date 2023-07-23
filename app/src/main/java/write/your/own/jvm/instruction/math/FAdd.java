package write.your.own.jvm.instruction.math;

import write.your.own.jvm.instruction.Instruction;
import write.your.own.jvm.runtimedata.StackFrame;

/**
 * <a href="https://docs.oracle.com/javase/specs/jvms/se7/html/jvms-6.html#jvms-6.5.fadd">...</a>
 */
public class FAdd implements Instruction {

    @Override
    public int getOpCode() {
        return 0x62;
    }

    @Override
    public void execute(StackFrame frame) {
        float f1 = frame.getOperandStack().popFloat();
        float f2 = frame.getOperandStack().popFloat();
        frame.getOperandStack().pushDouble(f2 + f1);
    }

    @Override
    public String getReadableName() {
        return "fadd";
    }
}
