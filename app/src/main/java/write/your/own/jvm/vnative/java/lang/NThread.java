package write.your.own.jvm.vnative.java.lang;

import write.your.own.jvm.runtimedata.StackFrame;
import write.your.own.jvm.runtimedata.heap.MyClass;
import write.your.own.jvm.runtimedata.heap.MyClassLoader;
import write.your.own.jvm.runtimedata.heap.MyObject;
import write.your.own.jvm.vnative.EmptyNativeMethod;
import write.your.own.jvm.vnative.NativeMethod;
import write.your.own.jvm.vnative.NativeRegistry;

public class NThread {
    public static void init() {
        NativeRegistry.getInstance().registerNativeMethod(
                "java/lang/Thread",
                "registerNatives",
                "()V",
                new EmptyNativeMethod());

        NativeRegistry.getInstance().registerNativeMethod(
                "java/lang/Thread", "currentThread", "()Ljava/lang/Thread;",
                new CurrentThread());

        NativeRegistry.getInstance().registerNativeMethod(
                "java/lang/Thread", "setPriority0", "(I)V",
                new SetPriority0());

        NativeRegistry.getInstance().registerNativeMethod(
                "java/lang/Thread", "isAlive", "()Z",
                new IsAlive());

        NativeRegistry.getInstance().registerNativeMethod(
                "java/lang/Thread", "start0", "()V",
                new Start0());
    }

    private static class CurrentThread implements NativeMethod {

        @Override
        public void invoke(StackFrame frame) {
            // set thread info
            MyClassLoader classLoader = frame.getMyMethod().getMyClass().getClassLoader();
            MyClass threadClass = classLoader.loadClass("java/lang/Thread");
            MyObject threadObj = threadClass.newObject();
            MyClass threadGroupClass = classLoader.loadClass("java/lang/ThreadGroup");
            MyObject threadGroupObj = threadGroupClass.newObject();
            threadObj.setRefFieldValue("group", "Ljava/lang/ThreadGroup;", threadGroupObj);
            threadObj.setIntFieldValue("priority", "I", 1);
            frame.getOperandStack().pushRef(threadObj);
        }
    }

    private static class SetPriority0 implements NativeMethod {

        @Override
        public void invoke(StackFrame frame) {

        }
    }

    private static class IsAlive implements NativeMethod {

        @Override
        public void invoke(StackFrame frame) {
            // fake logic
            frame.getOperandStack().pushInt(0);
        }
    }

    private static class Start0 implements NativeMethod {

        @Override
        public void invoke(StackFrame frame) {

        }
    }
}
