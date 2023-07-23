package write.your.own.jvm.instruction.store;

import write.your.own.jvm.instruction.Instruction;
import write.your.own.jvm.runtimedata.ObjRef;
import write.your.own.jvm.runtimedata.StackFrame;

/**
 * The <n> must be an index into the local variable array of the current frame (§2.6).
 * The objectref on the top of the operand stack must be of type returnAddress or of type reference.
 * It is popped from the operand stack, and the value of the local variable at <n> is set to objectref.
 */
public class AStore1 implements Instruction {

    @Override
    public int getOpCode() {
        return 0x4C;
    }

    @Override
    public void execute(StackFrame frame) {
        ObjRef ref = frame.getOperandStack().popRef();
        frame.getLocalVars().setRef(1, ref);
    }

    @Override
    public String getReadableName() {
        return "astore_1";
    }
}
