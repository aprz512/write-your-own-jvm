package write.your.own.jvm.instruction.extended;

import write.your.own.jvm.instruction.CodeReader;
import write.your.own.jvm.instruction.base.Operand1Instruction;
import write.your.own.jvm.runtimedata.StackFrame;
import write.your.own.jvm.runtimedata.heap.MyObject;

/**
 * <a href="https://docs.oracle.com/javase/specs/jvms/se7/html/jvms-6.html#jvms-6.5.ifnonnull">...</a>
 */
public class IfNonNull extends Operand1Instruction {
    public IfNonNull(CodeReader reader) {
        super(reader);
    }

    @Override
    public int getOpCode() {
        return 0xc7;
    }

    @Override
    public void execute(StackFrame frame) {
        MyObject ref = frame.getOperandStack().popRef();
        if (ref != null) {
            frame.setNextPc(frame.getThread().getPc() + operand);
        }
    }

    @Override
    public String getReadableName() {
        return "ifnonnull";
    }

    @Override
    protected int readOperand(CodeReader reader) {
        return reader.readShort();
    }
}
