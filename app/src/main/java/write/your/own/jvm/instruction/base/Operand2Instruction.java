package write.your.own.jvm.instruction.base;

import write.your.own.jvm.instruction.CodeReader;
import write.your.own.jvm.instruction.Instruction;

public abstract class Operand2Instruction implements Instruction {
    protected final int op1;
    protected final int op2;

    public Operand2Instruction(CodeReader reader) {
        op1 = readOperand1(reader);
        op2 = readOperand2(reader);
    }

    public abstract int readOperand1(CodeReader reader);

    public abstract int readOperand2(CodeReader reader);
}
