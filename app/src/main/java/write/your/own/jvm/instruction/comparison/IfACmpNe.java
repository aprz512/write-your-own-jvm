package write.your.own.jvm.instruction.comparison;

import write.your.own.jvm.instruction.CodeReader;
import write.your.own.jvm.instruction.base.Operand1Instruction;
import write.your.own.jvm.runtimedata.StackFrame;
import write.your.own.jvm.runtimedata.heap.MyObject;

/**
 * <a href="https://docs.oracle.com/javase/specs/jvms/se7/html/jvms-6.html#jvms-6.5.if_acmp_cond">...</a>
 */
public class IfACmpNe extends Operand1Instruction {

    public IfACmpNe(CodeReader reader) {
        super(reader);
    }

    @Override
    protected int readOperand(CodeReader reader) {
        return reader.readShort();
    }

    @Override
    public int getOpCode() {
        return 0xA6;
    }

    @Override
    public void execute(StackFrame frame) {
        MyObject v2 = frame.getOperandStack().popRef();
        MyObject v1 = frame.getOperandStack().popRef();
        if (v1 != v2) {
            frame.setNextPc(frame.getThread().getPc() + operand);
        }
    }

    @Override
    public String getReadableName() {
        return "if_acmpne";
    }


}
