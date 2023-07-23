package write.your.own.jvm.instruction.math;

import write.your.own.jvm.instruction.Instruction;
import write.your.own.jvm.runtimedata.StackFrame;

/**
 * <a href="https://docs.oracle.com/javase/specs/jvms/se7/html/jvms-6.html#jvms-6.5.iadd">...</a>
 */
public class IAdd implements Instruction {

    @Override
    public int getOpCode() {
        return 0x60;
    }

    @Override
    public void execute(StackFrame frame) {
        int v1 = frame.getOperandStack().popInt();
        int v2 = frame.getOperandStack().popInt();
        frame.getOperandStack().pushInt(v2 + v1);
    }

    @Override
    public String getReadableName() {
        return "iadd";
    }
}
