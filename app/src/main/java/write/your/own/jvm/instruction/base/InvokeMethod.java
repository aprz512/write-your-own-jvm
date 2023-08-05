package write.your.own.jvm.instruction.base;

import write.your.own.jvm.runtimedata.MyThread;
import write.your.own.jvm.runtimedata.Slot;
import write.your.own.jvm.runtimedata.StackFrame;
import write.your.own.jvm.runtimedata.heap.MyMethod;

public class InvokeMethod {

    public static void invokeMethod(StackFrame invokerFrame, MyMethod method) {
        MyThread thread = invokerFrame.getThread();
        StackFrame invokedFrame = thread.newStackFrame(method);
        thread.pushStackFrame(invokedFrame);
        int argSlotCount = method.getArgsSlotCount();
        if (argSlotCount > 0) {
            // 操作数栈上的参数：
            // arg3
            // arg2
            // arg1
            // 传递给调用的方法需要按照 index 对应，第一个参数设置到 index 为 0 的位置
            // 实例方法，通常 index 为 0 的位置是 this
            for (int i = argSlotCount - 1; i >= 0; i--) {
                Slot slot = invokerFrame.getOperandStack().popSlot();
                invokedFrame.getLocalVariableTable().setSlot(i, slot);
            }
        }
    }

}
