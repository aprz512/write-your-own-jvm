package write.your.own.jvm.vnative.java.lang;

import write.your.own.jvm.runtimedata.MyThread;
import write.your.own.jvm.runtimedata.StackFrame;
import write.your.own.jvm.runtimedata.heap.MyClass;
import write.your.own.jvm.runtimedata.heap.MyMethod;
import write.your.own.jvm.runtimedata.heap.MyObject;
import write.your.own.jvm.vnative.NativeMethod;
import write.your.own.jvm.vnative.NativeRegistry;

import java.util.List;

public class NThrowable {

    public static void init() {
        NativeRegistry.getInstance().registerNativeMethod(
                "java/lang/Throwable",
                "fillInStackTrace",
                "(I)Ljava/lang/Throwable;",
                new FillInStackTrace());
    }

    public static class FillInStackTrace implements NativeMethod {

        @Override
        public void invoke(StackFrame frame) {
            MyObject thisObj = frame.getLocalVariableTable().getRef(0);
            frame.getOperandStack().pushRef(thisObj);
            StackTraceElement[] stackTraceElements = createStackTraceElements(thisObj, frame.getThread());
            thisObj.setExtra(stackTraceElements);
        }

        private StackTraceElement[] createStackTraceElements(MyObject thisObj, MyThread thread) {
            // 栈顶两帧正在执行fillInStackTrace（int）和fillInStackTrace（）方法，所以需要跳过这两帧。
            int fillFrame = 2;
            // 这两帧下面的几帧正在执行异常类的构造函数，所以也要跳过，具体要跳过多少帧数则要看异常类的继承层次。
            int skipFrame = distanceToObject(thisObj.getMyClass()) + fillFrame;
            List<StackFrame> stackTraceFrames = thread.getStackTraceFrames(skipFrame);
            StackTraceElement[] stackTraceElements = new StackTraceElement[stackTraceFrames.size()];
            for (int i = 0; i < stackTraceElements.length; i++) {
                stackTraceElements[i] = createStackTraceElement(stackTraceFrames.get(i));
            }
            return stackTraceElements;
        }

        private StackTraceElement createStackTraceElement(StackFrame stackFrame) {
            MyMethod method = stackFrame.getMyMethod();
            MyClass myClass = method.getMyClass();
            int nextPc = stackFrame.getNextPc();

            StackTraceElement stackTraceElement = new StackTraceElement();
            stackTraceElement.className = myClass.getJavaName();
            stackTraceElement.fileName = myClass.getSourceFile();
            stackTraceElement.lineNumber = method.getLineNumber(nextPc - 1);
            stackTraceElement.methodName = method.getName();

            return stackTraceElement;
        }

        private int distanceToObject(MyClass myClass) {
            int distance = 0;
            MyClass superClass = myClass.getSuperClass();
            while (superClass != null) {
                distance++;
                superClass = superClass.getSuperClass();
            }
            return distance;
        }
    }

    public static class StackTraceElement {
        public String fileName;
        public String className;
        public String methodName;
        // 帧正在执行哪行代码
        public int lineNumber;

        @Override
        public String toString() {
            return "StackTraceElement{" +
                    "fileName='" + fileName + '\'' +
                    ", className='" + className + '\'' +
                    ", methodName='" + methodName + '\'' +
                    ", lineNumber=" + lineNumber +
                    '}';
        }
    }

}
