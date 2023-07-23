package write.your.own.jvm.instruction.base;

import write.your.own.jvm.instruction.CodeReader;
import write.your.own.jvm.instruction.Instruction;

public abstract class IndexByte2OperandInstruction implements Instruction {
    protected final int indexByte1;
    protected final int indexByte2;

    public IndexByte2OperandInstruction(CodeReader reader) {
        indexByte1 = reader.readByte();
        indexByte2 = reader.readByte();
    }
}
