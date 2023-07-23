package write.your.own.jvm.instruction.load;

import write.your.own.jvm.instruction.CodeReader;
import write.your.own.jvm.instruction.base.IndexByte1OperandInstruction;
import write.your.own.jvm.runtimedata.StackFrame;
import write.your.own.jvm.util.NumUtil;

/**
 * The index is an unsigned byte that must be an index into the local variable array of the current frame (§2.6).
 * The local variable at index must contain a float.
 * The value of the local variable at index is pushed onto the operand stack.
 */
public class FLoad extends IndexByte1OperandInstruction {

    public FLoad(CodeReader reader) {
        super(reader);
    }

    @Override
    public int getOpCode() {
        return 0x17;
    }

    @Override
    public void execute(StackFrame frame) {
        int index = NumUtil.byteToUnsignedInt(indexByte);
        float value = frame.getLocalVars().getFloat(index);
        frame.getOperandStack().pushFloat(value);
    }

    @Override
    public String getReadableName() {
        return "fload";
    }
}
