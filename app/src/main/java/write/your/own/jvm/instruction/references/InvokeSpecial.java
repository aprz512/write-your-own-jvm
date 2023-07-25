package write.your.own.jvm.instruction.references;

import write.your.own.jvm.instruction.CodeReader;
import write.your.own.jvm.instruction.base.Operand1Instruction;
import write.your.own.jvm.runtimedata.StackFrame;

/**
 * <a href="https://docs.oracle.com/javase/specs/jvms/se7/html/jvms-6.html#jvms-6.5.invokespecial">...</a>
 */
public class InvokeSpecial extends Operand1Instruction {
    public InvokeSpecial(CodeReader reader) {
        super(reader);
    }

    @Override
    public int getOpCode() {
        return 0xb7;
    }

    @Override
    public void execute(StackFrame frame) {
        frame.getOperandStack().popRef();
    }

    @Override
    public String getReadableName() {
        return "invokespecial";
    }

    @Override
    protected int readOperand(CodeReader reader) {
        return reader.readUnsignedShort();
    }
}
