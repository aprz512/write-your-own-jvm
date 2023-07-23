package write.your.own.jvm.instruction.math;

import write.your.own.jvm.instruction.Instruction;
import write.your.own.jvm.runtimedata.StackFrame;

/**
 * <a href="https://docs.oracle.com/javase/specs/jvms/se7/html/jvms-6.html#jvms-6.5.iushr">...</a>
 */
public class IUShr implements Instruction {
    @Override
    public int getOpCode() {
        return 0x7C;
    }

    @Override
    public void execute(StackFrame frame) {
        int v1 = frame.getOperandStack().popInt();
        int v2 = frame.getOperandStack().popInt();
        int s = v1 & 0x1f;
        int ret = v2 >>> s;
        frame.getOperandStack().pushInt(ret);
    }

    @Override
    public String getReadableName() {
        return "iushr";
    }
}
