package write.your.own.jvm.instruction.math;

import write.your.own.jvm.instruction.CodeReader;
import write.your.own.jvm.instruction.base.Operand2Instruction;
import write.your.own.jvm.runtimedata.StackFrame;

/**
 * <a href="https://docs.oracle.com/javase/specs/jvms/se7/html/jvms-6.html#jvms-6.5.iinc">...</a>
 */
public class IInc extends Operand2Instruction {

    public IInc(CodeReader reader) {
        super(reader);
    }

    @Override
    public int readOperand1(CodeReader reader) {
        return reader.readUnsignedByte();
    }

    @Override
    public int readOperand2(CodeReader reader) {
        return reader.readByte();
    }

    @Override
    public int getOpCode() {
        return 0x84;
    }

    @Override
    public void execute(StackFrame frame) {
        int value = frame.getLocalVariableTable().getInt(op1);
        value += op2;
        frame.getLocalVariableTable().setInt(op1, value);
    }

    @Override
    public String getReadableName() {
        return "iinc";
    }


}
