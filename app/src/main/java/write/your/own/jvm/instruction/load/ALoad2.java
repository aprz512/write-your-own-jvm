package write.your.own.jvm.instruction.load;

import write.your.own.jvm.instruction.base.NoOperandInstruction;
import write.your.own.jvm.runtimedata.ObjRef;
import write.your.own.jvm.runtimedata.StackFrame;

/**
 * The <n> must be an index into the local variable array of the current frame (§2.6).
 * The local variable at <n> must contain a reference.
 * The objectref in the local variable at <n> is pushed onto the operand stack.
 */
public class ALoad2 extends NoOperandInstruction {

    @Override
    public int getOpCode() {
        return 0x2C;
    }

    @Override
    public void execute(StackFrame frame) {
        ObjRef ref = frame.getLocalVars().getRef(2);
        frame.getOperandStack().pushRef(ref);
    }

    @Override
    public String getReadableName() {
        return "aload_2";
    }
}
