package write.your.own.jvm.instruction.base;

import write.your.own.jvm.instruction.CodeReader;
import write.your.own.jvm.instruction.Instruction;

/**
 * for example (if condition):
 * <p>
 * 3 if_icmple 7 (+4)
 * <p>
 * branchPc = 4
 */
public abstract class BranchInstruction implements Instruction {

    private final int branchOffset;

    public BranchInstruction(CodeReader reader) {
        branchOffset = reader.readByte();
    }

    public int getBranchOffset() {
        return branchOffset;
    }

}
