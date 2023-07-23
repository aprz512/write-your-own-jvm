package write.your.own.jvm.instruction.math;

import write.your.own.jvm.instruction.Instruction;
import write.your.own.jvm.runtimedata.StackFrame;

/**
 * Both value1 and value2 must be of type int.
 * The values are popped from the operand stack.
 * An int result is calculated by shifting value1 left by s bit positions, where s is the value of the low 5 bits of value2.
 * The result is pushed onto the operand stack.
 */
public class IShl implements Instruction {
    @Override
    public int getOpCode() {
        return 0x78;
    }

    @Override
    public void execute(StackFrame frame) {
        int v1 = frame.getOperandStack().popInt();
        int v2 = frame.getOperandStack().popInt();
        int s = v1 & 0x1f;
        int ret = v2 << s;
        frame.getOperandStack().pushInt(ret);
    }

    @Override
    public String getReadableName() {
        return "ishl";
    }
}
