package write.your.own.jvm.instruction.conversions;

import write.your.own.jvm.instruction.Instruction;
import write.your.own.jvm.runtimedata.StackFrame;

/**
 * <a href="https://docs.oracle.com/javase/specs/jvms/se7/html/jvms-6.html#jvms-6.5.f2d">...</a>
 */
public class F2D implements Instruction {

    @Override
    public int getOpCode() {
        return 0x8D;
    }

    @Override
    public void execute(StackFrame frame) {
        float value = frame.getOperandStack().popFloat();
        frame.getOperandStack().pushDouble(value);
    }

    @Override
    public String getReadableName() {
        return "f2d";
    }
}
