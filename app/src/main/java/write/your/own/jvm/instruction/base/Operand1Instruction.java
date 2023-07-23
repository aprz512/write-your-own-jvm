package write.your.own.jvm.instruction.base;

import write.your.own.jvm.instruction.CodeReader;
import write.your.own.jvm.instruction.Instruction;

public abstract class Operand1Instruction implements Instruction {

    protected final int operand;

    public Operand1Instruction(CodeReader reader) {
        operand = readOperand(reader);
    }

    protected abstract int readOperand(CodeReader reader);

}
