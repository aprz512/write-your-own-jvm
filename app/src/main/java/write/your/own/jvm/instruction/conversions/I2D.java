package write.your.own.jvm.instruction.conversions;


import write.your.own.jvm.instruction.Instruction;
import write.your.own.jvm.runtimedata.StackFrame;

/**
 * <a href="https://docs.oracle.com/javase/specs/jvms/se7/html/jvms-6.html#jvms-6.5.i2d">...</a>
 */
public class I2D implements Instruction {
    @Override
    public int getOpCode() {
        return 0x87;
    }

    @Override
    public void execute(StackFrame frame) {
        double value = frame.getOperandStack().popInt();
        frame.getOperandStack().pushDouble(value);
    }

    @Override
    public String getReadableName() {
        return "i2d";
    }
}
