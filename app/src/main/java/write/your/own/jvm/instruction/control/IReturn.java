package write.your.own.jvm.instruction.control;

import write.your.own.jvm.instruction.Instruction;
import write.your.own.jvm.runtimedata.MyThread;
import write.your.own.jvm.runtimedata.StackFrame;

/**
 * Return int from method
 * <a href="https://docs.oracle.com/javase/specs/jvms/se7/html/jvms-6.html#jvms-6.5.ireturn">...</a>
 */
public class IReturn implements Instruction {
    @Override
    public int getOpCode() {
        return 0xac;
    }

    @Override
    public void execute(StackFrame frame) {
        MyThread thread = frame.getThread();
        StackFrame currentFrame = thread.popStackFrame();
        StackFrame invokerFrame = frame.getThread().currentStackFrame();
        int returnValue = currentFrame.getOperandStack().popInt();
        invokerFrame.getOperandStack().pushInt(returnValue);
    }

    @Override
    public String getReadableName() {
        return "ireturn";
    }
}
