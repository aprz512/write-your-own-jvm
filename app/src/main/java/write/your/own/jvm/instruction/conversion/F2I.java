package write.your.own.jvm.instruction.conversion;

import write.your.own.jvm.instruction.Instruction;
import write.your.own.jvm.runtimedata.StackFrame;

/**
 * <a href="https://docs.oracle.com/javase/specs/jvms/se7/html/jvms-6.html#jvms-6.5.f2i">...</a>
 */
public class F2I implements Instruction {

    @Override
    public int getOpCode() {
        return 0x8B;
    }

    @Override
    public void execute(StackFrame frame) {
        int value = (int) frame.getOperandStack().popFloat();
        frame.getOperandStack().pushInt(value);
    }

    @Override
    public String getReadableName() {
        return "f2i";
    }
}
