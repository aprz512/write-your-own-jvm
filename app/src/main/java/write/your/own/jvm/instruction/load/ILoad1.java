package write.your.own.jvm.instruction.load;

import write.your.own.jvm.instruction.Instruction;
import write.your.own.jvm.runtimedata.StackFrame;

/**
 * The <n> must be an index into the local variable array of the current frame (§2.6).
 * The local variable at <n> must contain an int. The value of the local variable at <n> is pushed onto the operand stack.
 */
public class ILoad1 implements Instruction {

    @Override
    public int getOpCode() {
        return 0x1B;
    }

    @Override
    public void execute(StackFrame frame) {
        int local = frame.getLocalVariableTable().getInt(1);
        frame.getOperandStack().pushInt(local);
    }

    @Override
    public String getReadableName() {
        return "iload_1";
    }
}
