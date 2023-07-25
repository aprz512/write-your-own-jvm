package write.your.own.jvm.instruction.load;

import write.your.own.jvm.instruction.Instruction;
import write.your.own.jvm.runtimedata.StackFrame;

/**
 * Both <n> and <n>+1 must be indices into the local variable array of the current frame (§2.6).
 * The local variable at <n> must contain a double.
 * The value of the local variable at <n> is pushed onto the operand stack.
 */
public class DLoad2 implements Instruction {

    @Override
    public int getOpCode() {
        return 0x28;
    }

    @Override
    public void execute(StackFrame frame) {
        double value = frame.getLocalVariableTable().getDouble(2);
        frame.getOperandStack().pushDouble(value);
    }

    @Override
    public String getReadableName() {
        return "dload_2";
    }
}
