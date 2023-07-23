package write.your.own.jvm.instruction.load;

import write.your.own.jvm.instruction.CodeReader;
import write.your.own.jvm.instruction.base.IndexByte1OperandInstruction;
import write.your.own.jvm.runtimedata.StackFrame;
import write.your.own.jvm.util.NumUtil;

/**
 * The index is an unsigned byte.
 * Both index and index+1 must be indices into the local variable array of the current frame (§2.6).
 * The local variable at index must contain a long.
 * The value of the local variable at index is pushed onto the operand stack.
 */
public class LLoad extends IndexByte1OperandInstruction {

    public LLoad(CodeReader reader) {
        super(reader);
    }

    @Override
    public int getOpCode() {
        return 0x16;
    }

    @Override
    public void execute(StackFrame frame) {
        int index = NumUtil.byteToUnsignedInt(indexByte);
        long value = frame.getLocalVars().getLong(index);
        frame.getOperandStack().pushLong(value);
    }

    @Override
    public String getReadableName() {
        return "lload";
    }
}
