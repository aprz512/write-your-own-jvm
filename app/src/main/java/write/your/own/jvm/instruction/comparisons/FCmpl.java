package write.your.own.jvm.instruction.comparisons;

import write.your.own.jvm.instruction.Instruction;
import write.your.own.jvm.runtimedata.StackFrame;

/**
 * <a href="https://docs.oracle.com/javase/specs/jvms/se7/html/jvms-6.html#jvms-6.5.fcmp_op">...</a>
 */
public class FCmpl implements Instruction {
    @Override
    public int getOpCode() {
        return 0x95;
    }

    @Override
    public void execute(StackFrame frame) {
        float v1 = frame.getOperandStack().popFloat();
        float v2 = frame.getOperandStack().popFloat();
        if (v1 != v1 || v2 != v2) {
            frame.getOperandStack().pushInt(-1);
            return;
        }
        int result = Float.compare(v2, v1);
        frame.getOperandStack().pushInt(result);
    }

    @Override
    public String getReadableName() {
        return "fcmpl";
    }
}
