package write.your.own.jvm.instruction.math;

import write.your.own.jvm.instruction.Instruction;
import write.your.own.jvm.runtimedata.StackFrame;

/**
 * <a href="https://docs.oracle.com/javase/specs/jvms/se7/html/jvms-6.html#jvms-6.5.fneg">...</a>
 */
public class FNeg implements Instruction {
    @Override
    public int getOpCode() {
        return 0x76;
    }

    @Override
    public void execute(StackFrame frame) {
        float v1 = frame.getOperandStack().popFloat();
        frame.getOperandStack().pushFloat(-v1);
    }

    @Override
    public String getReadableName() {
        return "fneg";
    }
}
