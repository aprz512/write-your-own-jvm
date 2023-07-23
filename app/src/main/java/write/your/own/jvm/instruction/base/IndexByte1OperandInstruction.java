package write.your.own.jvm.instruction.base;

import write.your.own.jvm.instruction.CodeReader;
import write.your.own.jvm.instruction.Instruction;

public abstract class IndexByte1OperandInstruction implements Instruction {

    // The index is an unsigned byte that must be a valid index into the run-time constant pool of the current class
    //  The run-time constant pool entry at index either must be a run-time constant of type int or float,
    //  or a reference to a string literal, or a symbolic reference to a class, method type, or method handle
    protected final byte indexByte;

    public IndexByte1OperandInstruction(CodeReader reader) {
        indexByte = reader.readByte();
    }
}
