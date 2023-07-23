package write.your.own.jvm.instruction.store;

import write.your.own.jvm.instruction.CodeReader;
import write.your.own.jvm.instruction.base.Operand1Instruction;
import write.your.own.jvm.runtimedata.ObjRef;
import write.your.own.jvm.runtimedata.StackFrame;

/**
 * The index is an unsigned byte that must be an index into the local variable array of the current frame (§2.6).
 * The objectref on the top of the operand stack must be of type returnAddress or of type reference.
 * It is popped from the operand stack, and the value of the local variable at index is set to objectref.
 */
public class AStore extends Operand1Instruction {

    public AStore(CodeReader reader) {
        super(reader);
    }

    @Override
    protected int readOperand(CodeReader reader) {
        return reader.readUnsignedByte();
    }

    @Override
    public int getOpCode() {
        return 0x3A;
    }

    @Override
    public void execute(StackFrame frame) {
        ObjRef ref = frame.getOperandStack().popRef();
        frame.getLocalVars().setRef(operand, ref);
    }

    @Override
    public String getReadableName() {
        return "astore";
    }
}
