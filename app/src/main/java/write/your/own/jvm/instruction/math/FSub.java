package write.your.own.jvm.instruction.math;

import write.your.own.jvm.instruction.Instruction;
import write.your.own.jvm.runtimedata.StackFrame;

/**
 * <a href="https://docs.oracle.com/javase/specs/jvms/se7/html/jvms-6.html#jvms-6.5.fsub">...</a>
 */
public class FSub implements Instruction {
    @Override
    public int getOpCode() {
        return 0x66;
    }

    @Override
    public void execute(StackFrame frame) {
        float v1 = frame.getOperandStack().popFloat();
        float v2 = frame.getOperandStack().popFloat();
        frame.getOperandStack().pushFloat(v2 - v1);
    }

    @Override
    public String getReadableName() {
        return "fsub";
    }
}