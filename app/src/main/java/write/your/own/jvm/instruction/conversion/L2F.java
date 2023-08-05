package write.your.own.jvm.instruction.conversion;


import write.your.own.jvm.instruction.Instruction;
import write.your.own.jvm.runtimedata.StackFrame;

/**
 * <a href="https://docs.oracle.com/javase/specs/jvms/se7/html/jvms-6.html#jvms-6.5.l2f">...</a>
 */
public class L2F implements Instruction {
    @Override
    public int getOpCode() {
        return 0x89;
    }

    @Override
    public void execute(StackFrame frame) {
        float value = (float) frame.getOperandStack().popLong();
        frame.getOperandStack().pushFloat(value);
    }

    @Override
    public String getReadableName() {
        return "l2f";
    }
}
