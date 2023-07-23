package write.your.own.jvm.instruction.load;

import write.your.own.jvm.instruction.CodeReader;
import write.your.own.jvm.instruction.base.IndexByte1OperandInstruction;
import write.your.own.jvm.runtimedata.ObjRef;
import write.your.own.jvm.runtimedata.StackFrame;
import write.your.own.jvm.util.NumUtil;

/**
 * The index is an unsigned byte that must be an index into the local variable array of the current frame (§2.6).
 * The local variable at index must contain a reference.
 * The objectref in the local variable at index is pushed onto the operand stack.
 */
public class ALoad extends IndexByte1OperandInstruction {
    public ALoad(CodeReader reader) {
        super(reader);
    }

    @Override
    public int getOpCode() {
        return 0x19;
    }

    @Override
    public void execute(StackFrame frame) {
        ObjRef ref = frame.getLocalVars().getRef(NumUtil.byteToUnsignedInt(indexByte));
        frame.getOperandStack().pushRef(ref);
    }

    @Override
    public String getReadableName() {
        return null;
    }
}
