package write.your.own.jvm.instruction.conversions;


import write.your.own.jvm.instruction.Instruction;
import write.your.own.jvm.runtimedata.StackFrame;

/**
 * <a href="https://docs.oracle.com/javase/specs/jvms/se7/html/jvms-6.html#jvms-6.5.i2c">...</a>
 */
public class I2C implements Instruction {
    @Override
    public int getOpCode() {
        return 0x92;
    }

    @Override
    public void execute(StackFrame frame) {
        char value = (char) frame.getOperandStack().popInt();
        frame.getOperandStack().pushInt(value);
    }

    @Override
    public String getReadableName() {
        return "i2c";
    }
}
