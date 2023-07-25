package write.your.own.jvm.instruction.store;

import write.your.own.jvm.instruction.Instruction;
import write.your.own.jvm.runtimedata.StackFrame;

/**
 * Both <n> and <n>+1 must be indices into the local variable array of the current frame (§2.6).
 * The value on the top of the operand stack must be of type double.
 * It is popped from the operand stack and undergoes value set conversion (§2.8.3), resulting in value'.
 * The local variables at <n> and <n>+1 are set to value'.
 */
public class DStore0 implements Instruction {

    @Override
    public int getOpCode() {
        return 0x47;
    }

    @Override
    public void execute(StackFrame frame) {
        double value = frame.getOperandStack().popDouble();
        frame.getLocalVariableTable().setDouble(0, value);
    }

    @Override
    public String getReadableName() {
        return "dstore_0";
    }
}
