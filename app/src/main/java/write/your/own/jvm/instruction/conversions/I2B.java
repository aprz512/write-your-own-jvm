package write.your.own.jvm.instruction.conversions;


import write.your.own.jvm.instruction.Instruction;
import write.your.own.jvm.runtimedata.StackFrame;

/**
 * <a href="https://docs.oracle.com/javase/specs/jvms/se7/html/jvms-6.html#jvms-6.5.i2b">...</a>
 */
public class I2B implements Instruction {
    @Override
    public int getOpCode() {
        return 0x91;
    }

    @Override
    public void execute(StackFrame frame) {
        byte value = (byte) frame.getOperandStack().popInt();
        frame.getOperandStack().pushInt(value);
    }

    @Override
    public String getReadableName() {
        return "i2b";
    }
}
