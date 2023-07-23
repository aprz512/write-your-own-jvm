package write.your.own.jvm.instruction.constant;

import write.your.own.jvm.instruction.CodeReader;
import write.your.own.jvm.instruction.base.IndexByte1OperandInstruction;
import write.your.own.jvm.runtimedata.StackFrame;
import write.your.own.jvm.util.NumUtil;

/**
 * The immediate byte is sign-extended to an int value.
 * That value is pushed onto the operand stack.
 */
public class BiPush extends IndexByte1OperandInstruction {
    public BiPush(CodeReader reader) {
        super(reader);
    }

    @Override
    public int getOpCode() {
        return 0x10;
    }

    @Override
    public void execute(StackFrame frame) {
        frame.getOperandStack().pushInt(NumUtil.byteToSignedInt(indexByte));
    }

    @Override
    public String getReadableName() {
        return "bipush";
    }
}
