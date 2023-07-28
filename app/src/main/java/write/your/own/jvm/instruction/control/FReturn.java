package write.your.own.jvm.instruction.control;

import write.your.own.jvm.instruction.Instruction;
import write.your.own.jvm.runtimedata.MyThread;
import write.your.own.jvm.runtimedata.StackFrame;

/**
 * Return int from method
 * <a href="https://docs.oracle.com/javase/specs/jvms/se7/html/jvms-6.html#jvms-6.5.freturn">...</a>
 */
public class FReturn implements Instruction {
    @Override
    public int getOpCode() {
        return 0xae;
    }

    @Override
    public void execute(StackFrame frame) {
        MyThread thread = frame.getThread();
        StackFrame currentFrame = thread.popStackFrame();
        StackFrame invokerFrame = frame.getThread().currentStackFrame();
        float returnValue = currentFrame.getOperandStack().popFloat();
        invokerFrame.getOperandStack().pushFloat(returnValue);
    }

    @Override
    public String getReadableName() {
        return "freturn";
    }
}
