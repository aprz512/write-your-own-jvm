package write.your.own.jvm.instruction.comparison;

import write.your.own.jvm.instruction.CodeReader;
import write.your.own.jvm.instruction.base.Operand1Instruction;
import write.your.own.jvm.runtimedata.StackFrame;

/**
 * <a href="https://docs.oracle.com/javase/specs/jvms/se7/html/jvms-6.html#jvms-6.5.if_icmp_cond">...</a>
 */
public class IfICmpLe extends Operand1Instruction {

    public IfICmpLe(CodeReader reader) {
        super(reader);
    }

    @Override
    protected int readOperand(CodeReader reader) {
        return reader.readShort();
    }

    @Override
    public int getOpCode() {
        return 0xA4;
    }

    @Override
    public void execute(StackFrame frame) {
        int v2 = frame.getOperandStack().popInt();
        int v1 = frame.getOperandStack().popInt();
        if (v1 <= v2) {
            frame.setNextPc(frame.getThread().getPc() + operand);
        }
    }

    @Override
    public String getReadableName() {
        return "if_icmple";
    }


}
