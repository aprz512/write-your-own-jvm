package write.your.own.jvm.vnative.java.lang;

import write.your.own.jvm.exception.MyJvmException;
import write.your.own.jvm.runtimedata.StackFrame;
import write.your.own.jvm.runtimedata.heap.MyClass;
import write.your.own.jvm.runtimedata.heap.MyObject;
import write.your.own.jvm.vnative.NativeMethod;
import write.your.own.jvm.vnative.NativeRegistry;

public class Object {

    public static void init() {
        NativeRegistry.getInstance().registerNativeMethod(
                "java/lang/Object",
                "getClass",
                "()Ljava/lang/Class;",
                new GetClass());
        NativeRegistry.getInstance().registerNativeMethod(
                "java/lang/Object",
                "hashCode",
                "()I",
                new HashCode());
        NativeRegistry.getInstance().registerNativeMethod(
                "java/lang/Object",
                "clone",
                "()Ljava/lang/Object;",
                new Clone());
    }

    public static class HashCode implements NativeMethod {
        @Override
        public void invoke(StackFrame frame) {
            MyObject ref = frame.getLocalVariableTable().getRef(0);
            int hashcode = ref.hashCode();
            frame.getOperandStack().pushInt(hashcode);
        }
    }

    public static class Clone implements NativeMethod {
        @Override
        public void invoke(StackFrame frame) {
            MyObject ref = frame.getLocalVariableTable().getRef(0);
            MyClass cloneable = ref.getMyClass().getClassLoader().loadClass("java/lang/Cloneable");
            if (!ref.getMyClass().isImplement(cloneable)) {
                throw new MyJvmException("java.lang.CloneNotSupportedException");
            }
            frame.getOperandStack().pushRef(ref.cloneMyObject());
        }
    }

    public static class GetClass implements NativeMethod {
        @Override
        public void invoke(StackFrame frame) {
            MyObject ref = frame.getLocalVariableTable().getRef(0);
            MyObject jClass = ref.getMyClass().getJClass();
            frame.getOperandStack().pushRef(jClass);
        }
    }


}
