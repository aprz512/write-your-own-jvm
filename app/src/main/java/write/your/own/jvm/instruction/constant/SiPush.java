package write.your.own.jvm.instruction.constant;

import write.your.own.jvm.instruction.CodeReader;
import write.your.own.jvm.instruction.base.IndexByte2OperandInstruction;
import write.your.own.jvm.runtimedata.StackFrame;

/**
 * The immediate unsigned byte1 and byte2 values are assembled into an intermediate short where the value of the short is (byte1 << 8) | byte2.
 * The intermediate value is then sign-extended to an int value. That value is pushed onto the operand stack.
 */
public class SiPush extends IndexByte2OperandInstruction {
    public SiPush(CodeReader reader) {
        super(reader);
    }

    @Override
    public int getOpCode() {
        return 0x11;
    }

    @Override
    public void execute(StackFrame frame) {
        int merge = (indexByte1 << 8) | indexByte2;
        frame.getOperandStack().pushInt(merge);
    }

    @Override
    public String getReadableName() {
        return "sipush";
    }
}
