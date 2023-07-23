package write.your.own.jvm.instruction.control;

import write.your.own.jvm.instruction.CodeReader;
import write.your.own.jvm.instruction.base.Operand1Instruction;
import write.your.own.jvm.runtimedata.StackFrame;

/**
 * <a href="https://docs.oracle.com/javase/specs/jvms/se7/html/jvms-6.html#jvms-6.5.goto">...</a>
 */
public class Goto extends Operand1Instruction {
    public Goto(CodeReader reader) {
        super(reader);
    }

    @Override
    public int getOpCode() {
        return 0xa7;
    }

    @Override
    public void execute(StackFrame frame) {
        frame.setNextPc(frame.getThread().getPc() + operand);
    }

    @Override
    public String getReadableName() {
        return "goto";
    }

    @Override
    protected int readOperand(CodeReader reader) {
        return reader.readShort();
    }
}
