package write.your.own.jvm.instruction.conversions;

import write.your.own.jvm.instruction.Instruction;
import write.your.own.jvm.runtimedata.StackFrame;

/**
 * <a href="https://docs.oracle.com/javase/specs/jvms/se7/html/jvms-6.html#jvms-6.5.f2l">...</a>
 */
public class F2L implements Instruction {

    @Override
    public int getOpCode() {
        return 0x8C;
    }

    @Override
    public void execute(StackFrame frame) {
        long value = (long) frame.getOperandStack().popFloat();
        frame.getOperandStack().pushLong(value);
    }

    @Override
    public String getReadableName() {
        return "f2l";
    }
}
