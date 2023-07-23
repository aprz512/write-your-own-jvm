package write.your.own.jvm.instruction.comparisons;

import write.your.own.jvm.instruction.Instruction;
import write.your.own.jvm.runtimedata.StackFrame;

/**
 * <a href="https://docs.oracle.com/javase/specs/jvms/se7/html/jvms-6.html#jvms-6.5.lcmp">...</a>
 */
public class LCmp implements Instruction {

    @Override
    public int getOpCode() {
        return 0x94;
    }

    @Override
    public void execute(StackFrame frame) {
        long v1 = frame.getOperandStack().popLong();
        long v2 = frame.getOperandStack().popLong();
        int result = Long.compare(v2, v1);
        frame.getOperandStack().pushInt(result);
    }

    @Override
    public String getReadableName() {
        return "lcmp";
    }

}
