package write.your.own.jvm.instruction.control;

import write.your.own.jvm.instruction.Instruction;
import write.your.own.jvm.runtimedata.MyThread;
import write.your.own.jvm.runtimedata.StackFrame;
import write.your.own.jvm.runtimedata.heap.MyObject;

/**
 * Return int from method
 * <a href="https://docs.oracle.com/javase/specs/jvms/se7/html/jvms-6.html#jvms-6.5.areturn">...</a>
 */
public class AReturn implements Instruction {
    @Override
    public int getOpCode() {
        return 0xb0;
    }

    @Override
    public void execute(StackFrame frame) {
        MyThread thread = frame.getThread();
        StackFrame currentFrame = thread.popStackFrame();
        StackFrame invokerFrame = frame.getThread().currentStackFrame();
        MyObject returnValue = currentFrame.getOperandStack().popRef();
        invokerFrame.getOperandStack().pushRef(returnValue);
    }

    @Override
    public String getReadableName() {
        return "areturn";
    }
}
