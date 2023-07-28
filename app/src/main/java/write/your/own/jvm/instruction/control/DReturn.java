package write.your.own.jvm.instruction.control;

import write.your.own.jvm.instruction.Instruction;
import write.your.own.jvm.runtimedata.MyThread;
import write.your.own.jvm.runtimedata.StackFrame;

/**
 * Return int from method
 * <a href="https://docs.oracle.com/javase/specs/jvms/se7/html/jvms-6.html#jvms-6.5.dreturn">...</a>
 */
public class DReturn implements Instruction {
    @Override
    public int getOpCode() {
        return 0xaf;
    }

    @Override
    public void execute(StackFrame frame) {
        MyThread thread = frame.getThread();
        StackFrame currentFrame = thread.popStackFrame();
        StackFrame invokerFrame = frame.getThread().currentStackFrame();
        double returnValue = currentFrame.getOperandStack().popDouble();
        invokerFrame.getOperandStack().pushDouble(returnValue);
    }

    @Override
    public String getReadableName() {
        return "dreturn";
    }
}
