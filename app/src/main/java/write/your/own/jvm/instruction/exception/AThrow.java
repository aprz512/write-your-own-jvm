package write.your.own.jvm.instruction.exception;

import write.your.own.jvm.exception.MyJvmException;
import write.your.own.jvm.instruction.Instruction;
import write.your.own.jvm.runtimedata.MyString;
import write.your.own.jvm.runtimedata.MyThread;
import write.your.own.jvm.runtimedata.OperandStack;
import write.your.own.jvm.runtimedata.StackFrame;
import write.your.own.jvm.runtimedata.heap.ExceptionTable;
import write.your.own.jvm.runtimedata.heap.MyMethod;
import write.your.own.jvm.runtimedata.heap.MyObject;
import write.your.own.jvm.util.Log;
import write.your.own.jvm.vnative.java.lang.NThrowable;

/**
 * <a href="https://docs.oracle.com/javase/specs/jvms/se7/html/jvms-6.html#jvms-6.5.athrow">...</a>
 */
public class AThrow implements Instruction {
    @Override
    public int getOpCode() {
        return 0xbf;
    }

    @Override
    public void execute(StackFrame frame) {
        MyObject exceptionObj = frame.getOperandStack().popRef();
        if (exceptionObj == null) {
            throw new MyJvmException("java.lang.NullPointerException");
        }
        MyThread thread = frame.getThread();
        if (!findAndGotoExceptionHandler(thread, exceptionObj)) {
            handleUncaughtException(thread, exceptionObj);
        }
    }

    private void handleUncaughtException(MyThread thread, MyObject exceptionObj) {
        thread.clearStack();
        MyObject value = exceptionObj.getRefFieldValue("detailMessage", "Ljava/lang/String;");
        String string = MyString.toString(value);
        Log.e(string);
        NThrowable.StackTraceElement[] elements = (NThrowable.StackTraceElement[]) exceptionObj.getExtra();
        for (NThrowable.StackTraceElement element : elements) {
            Log.e(element.toString());
        }
    }

    private boolean findAndGotoExceptionHandler(MyThread thread, MyObject exceptionObj) {
        do {
            StackFrame stackFrame = thread.currentStackFrame();
            int pc = stackFrame.getNextPc() - 1;
            MyMethod myMethod = stackFrame.getMyMethod();
            int handlePc = myMethod.findExceptionHandler(exceptionObj.getMyClass(), pc);
            if (handlePc > 0) {
                OperandStack operandStack = stackFrame.getOperandStack();
                operandStack.clear();
                operandStack.pushRef(exceptionObj);
                stackFrame.setNextPc(handlePc);
                return true;
            }
            thread.popStackFrame();
        } while (!thread.isStackFrameEmpty());
        return false;
    }

    @Override
    public String getReadableName() {
        return null;
    }
}
