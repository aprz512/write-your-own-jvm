package write.your.own.jvm.vnative.sun.reflect;

import write.your.own.jvm.runtimedata.StackFrame;
import write.your.own.jvm.runtimedata.heap.MyClass;
import write.your.own.jvm.runtimedata.heap.MyObject;
import write.your.own.jvm.vnative.NativeMethod;
import write.your.own.jvm.vnative.NativeRegistry;

public class NReflection {

    public static void init() {
        NativeRegistry.getInstance().registerNativeMethod(
                "sun/reflect/Reflection",
                "getCallerClass",
                "()Ljava/lang/Class;",
                new GetCallerClass());

        NativeRegistry.getInstance().registerNativeMethod(
                "sun/reflect/Reflection",
                "getClassAccessFlags",
                "(Ljava/lang/Class;)I",
                new GetClassAccessFlags());
    }

    /**
     * // public static native Class<?> getCallerClass();
     * // ()Ljava/lang/Class;
     */
    public static class GetCallerClass implements NativeMethod {

        @Override
        public void invoke(StackFrame frame) {
            // top0 is sun/reflect/Reflection
            // top1 is the caller of getCallerClass()
            // top2 is the caller of method
            StackFrame callerFrame = frame.getThread().peekFrame(2);
            MyObject jClass = callerFrame.getMyMethod().getMyClass().getJClass();
            frame.getOperandStack().pushRef(jClass);
        }
    }

    /**
     * // public static native int getClassAccessFlags(Class<?> type);
     * // (Ljava/lang/Class;)I
     */
    public static class GetClassAccessFlags implements NativeMethod {

        @Override
        public void invoke(StackFrame frame) {
            MyObject thisObj = frame.getLocalVariableTable().getRef(0);
            Object extra = thisObj.getExtra();
            MyClass jClass = (MyClass) extra;
            int accessFlag = jClass.getAccessFlag();
            frame.getOperandStack().pushInt(accessFlag);
        }
    }

}
