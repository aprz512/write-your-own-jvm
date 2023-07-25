package write.your.own.jvm.instruction.extended;

import write.your.own.jvm.instruction.CodeReader;
import write.your.own.jvm.instruction.base.Operand1Instruction;
import write.your.own.jvm.runtimedata.StackFrame;
import write.your.own.jvm.runtimedata.heap.MyObject;

/**
 * <a href="https://docs.oracle.com/javase/specs/jvms/se7/html/jvms-6.html#jvms-6.5.ifnull">...</a>
 */
public class IfNull extends Operand1Instruction {
    public IfNull(CodeReader reader) {
        super(reader);
    }

    @Override
    public int getOpCode() {
        return 0xc6;
    }

    @Override
    public void execute(StackFrame frame) {
        MyObject ref = frame.getOperandStack().popRef();
        if (ref == null) {
            frame.setNextPc(frame.getThread().getPc() + operand);
        }
    }

    @Override
    public String getReadableName() {
        return "ifnull";
    }

    @Override
    protected int readOperand(CodeReader reader) {
        return reader.readShort();
    }
}
