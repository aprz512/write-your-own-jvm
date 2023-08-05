package write.your.own.jvm.instruction.comparison;

import write.your.own.jvm.instruction.Instruction;
import write.your.own.jvm.runtimedata.StackFrame;

/**
 * <a href="https://docs.oracle.com/javase/specs/jvms/se7/html/jvms-6.html#jvms-6.5.dcmp_op">...</a>
 */
public class DCmpl implements Instruction {
    @Override
    public int getOpCode() {
        return 0x97;
    }

    @Override
    public void execute(StackFrame frame) {
        double v1 = frame.getOperandStack().popDouble();
        double v2 = frame.getOperandStack().popDouble();
        if (v1 != v1 || v2 != v2) {
            frame.getOperandStack().pushInt(-1);
            return;
        }
        int result = Double.compare(v2, v1);
        frame.getOperandStack().pushInt(result);
    }

    @Override
    public String getReadableName() {
        return "dcmpl";
    }
}
