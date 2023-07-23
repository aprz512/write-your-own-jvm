package write.your.own.jvm.instruction.comparisons;

import write.your.own.jvm.instruction.CodeReader;
import write.your.own.jvm.instruction.base.Operand1Instruction;
import write.your.own.jvm.runtimedata.StackFrame;

/**
 * <a href="https://docs.oracle.com/javase/specs/jvms/se7/html/jvms-6.html#jvms-6.5.if_cond">...</a>
 */
public class IfLe extends Operand1Instruction {

    public IfLe(CodeReader reader) {
        super(reader);
    }

    @Override
    protected int readOperand(CodeReader reader) {
        return reader.readShort();
    }

    @Override
    public int getOpCode() {
        return 0x9E;
    }

    @Override
    public void execute(StackFrame frame) {
        int value = frame.getOperandStack().popInt();
        if (value <= 0) {
            frame.setNextPc(operand + frame.getThread().getPc());
        }
    }

    @Override
    public String getReadableName() {
        return "ifle";
    }

}
