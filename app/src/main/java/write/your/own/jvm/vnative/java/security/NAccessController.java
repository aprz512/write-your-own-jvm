package write.your.own.jvm.vnative.java.security;

import write.your.own.jvm.instruction.base.InvokeMethod;
import write.your.own.jvm.runtimedata.StackFrame;
import write.your.own.jvm.runtimedata.heap.MyMethod;
import write.your.own.jvm.runtimedata.heap.MyObject;
import write.your.own.jvm.vnative.NativeMethod;
import write.your.own.jvm.vnative.NativeRegistry;

public class NAccessController {
    public static void init() {
        NativeRegistry.getInstance().registerNativeMethod(
                "java/security/AccessController",
                "doPrivileged",
                "(Ljava/security/PrivilegedAction;)Ljava/lang/Object;",
                new DoPrivileged());
        NativeRegistry.getInstance().registerNativeMethod(
                "java/security/AccessController",
                "doPrivileged",
                "(Ljava/security/PrivilegedExceptionAction;)Ljava/lang/Object;",
                new DoPrivileged2());
        NativeRegistry.getInstance().registerNativeMethod(
                "java/security/AccessController",
                "getStackAccessControlContext",
                "()Ljava/security/AccessControlContext;",
                new GetStackAccessControlContext());
    }

    /**
     * // @CallerSensitive
     * // public static native <T> T doPrivileged(PrivilegedAction<T> action);
     * // (Ljava/security/PrivilegedAction;)Ljava/lang/Object;
     */
    public static class DoPrivileged implements NativeMethod {

        @Override
        public void invoke(StackFrame frame) {
            MyObject action = frame.getLocalVariableTable().getRef(0);
            frame.getOperandStack().pushRef(action);
            MyMethod runMethod = action.getMyClass().getMethod("run", "()Ljava/lang/Object;", false);
            InvokeMethod.invokeMethod(frame, runMethod);
        }
    }

    /**
     * // @CallerSensitive
     * // public static native <T> T
     * //     doPrivileged(PrivilegedExceptionAction<T> action)
     * //     throws PrivilegedActionException;
     * // (Ljava/security/PrivilegedExceptionAction;)Ljava/lang/Object;
     */
    public static class DoPrivileged2 implements NativeMethod {

        @Override
        public void invoke(StackFrame frame) {
            MyObject action = frame.getLocalVariableTable().getRef(0);
            frame.getOperandStack().pushRef(action);
            MyMethod runMethod = action.getMyClass().getMethod("run", "()Ljava/lang/Object;", false);
            InvokeMethod.invokeMethod(frame, runMethod);
        }
    }

    public static class GetStackAccessControlContext implements NativeMethod {

        @Override
        public void invoke(StackFrame frame) {
            // fake logic
            frame.getOperandStack().pushRef(null);
        }
    }
}
