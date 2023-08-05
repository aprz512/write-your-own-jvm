package write.your.own.jvm.instruction.conversion;


import write.your.own.jvm.instruction.Instruction;
import write.your.own.jvm.runtimedata.StackFrame;

/**
 * <a href="https://docs.oracle.com/javase/specs/jvms/se7/html/jvms-6.html#jvms-6.5.l2i">...</a>
 */
public class L2I implements Instruction {
    @Override
    public int getOpCode() {
        return 0x88;
    }

    @Override
    public void execute(StackFrame frame) {
        int value = (int) frame.getOperandStack().popLong();
        frame.getOperandStack().pushInt(value);
    }

    @Override
    public String getReadableName() {
        return "l2i";
    }
}
