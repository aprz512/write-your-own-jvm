package write.your.own.jvm.instruction.conversion;


import write.your.own.jvm.instruction.Instruction;
import write.your.own.jvm.runtimedata.StackFrame;

/**
 * <a href="https://docs.oracle.com/javase/specs/jvms/se7/html/jvms-6.html#jvms-6.5.l2d">...</a>
 */
public class L2D implements Instruction {
    @Override
    public int getOpCode() {
        return 0x8A;
    }

    @Override
    public void execute(StackFrame frame) {
        double value = (double) frame.getOperandStack().popLong();
        frame.getOperandStack().pushDouble(value);
    }

    @Override
    public String getReadableName() {
        return "l2d";
    }
}
