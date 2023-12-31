package write.your.own.jvm.instruction.load;

import write.your.own.jvm.instruction.CodeReader;
import write.your.own.jvm.instruction.base.Operand1Instruction;
import write.your.own.jvm.runtimedata.StackFrame;
import write.your.own.jvm.runtimedata.heap.MyObject;

/**
 * The index is an unsigned byte that must be an index into the local variable array of the current frame (§2.6).
 * The local variable at index must contain a reference.
 * The objectref in the local variable at index is pushed onto the operand stack.
 */
public class ALoad extends Operand1Instruction {

    public ALoad(CodeReader reader) {
        super(reader);
    }

    @Override
    protected int readOperand(CodeReader reader) {
        return reader.readUnsignedByte();
    }

    @Override
    public int getOpCode() {
        return 0x19;
    }

    @Override
    public void execute(StackFrame frame) {
        MyObject ref = frame.getLocalVariableTable().getRef(operand);
        frame.getOperandStack().pushRef(ref);
    }

    @Override
    public String getReadableName() {
        return "aload";
    }
}
