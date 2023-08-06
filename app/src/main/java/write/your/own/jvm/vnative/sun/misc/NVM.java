package write.your.own.jvm.vnative.sun.misc;

import write.your.own.jvm.instruction.base.InvokeMethod;
import write.your.own.jvm.runtimedata.StackFrame;
import write.your.own.jvm.runtimedata.heap.MyClass;
import write.your.own.jvm.runtimedata.heap.MyClassLoader;
import write.your.own.jvm.runtimedata.heap.MyMethod;
import write.your.own.jvm.vnative.NativeMethod;
import write.your.own.jvm.vnative.NativeRegistry;

public class NVM {
    public static void init() {
        NativeRegistry.getInstance().registerNativeMethod(
                "sun/misc/VM",
                "initialize",
                "()V",
                new Initialize());

    }

    public static class Initialize implements NativeMethod {

        @Override
        public void invoke(StackFrame frame) {
            MyClassLoader classLoader = frame.getMyMethod().getMyClass().getClassLoader();
            MyClass systemClass = classLoader.loadClass("java/lang/System");
            MyMethod initializeSystemClassMethod = systemClass.getStaticMethod("initializeSystemClass", "()V");
            InvokeMethod.invokeMethod(frame, initializeSystemClassMethod);
        }
    }

}
