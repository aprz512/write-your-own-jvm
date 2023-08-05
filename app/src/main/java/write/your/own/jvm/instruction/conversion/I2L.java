package write.your.own.jvm.instruction.conversion;


import write.your.own.jvm.instruction.Instruction;
import write.your.own.jvm.runtimedata.StackFrame;

/**
 * <a href="https://docs.oracle.com/javase/specs/jvms/se7/html/jvms-6.html#jvms-6.5.i2l">...</a>
 */
public class I2L implements Instruction {
    @Override
    public int getOpCode() {
        return 0x85;
    }

    @Override
    public void execute(StackFrame frame) {
        long value = frame.getOperandStack().popInt();
        frame.getOperandStack().pushLong(value);
    }

    @Override
    public String getReadableName() {
        return "i2l";
    }
}
