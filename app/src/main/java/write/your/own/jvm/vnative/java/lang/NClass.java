package write.your.own.jvm.vnative.java.lang;

import write.your.own.jvm.exception.MyJvmException;
import write.your.own.jvm.runtimedata.MyString;
import write.your.own.jvm.runtimedata.StackFrame;
import write.your.own.jvm.runtimedata.heap.MyClass;
import write.your.own.jvm.runtimedata.heap.MyClassLoader;
import write.your.own.jvm.runtimedata.heap.MyObject;
import write.your.own.jvm.vnative.EmptyNativeMethod;
import write.your.own.jvm.vnative.NativeMethod;
import write.your.own.jvm.vnative.NativeRegistry;

public class NClass {

    public static void init() {
        NativeRegistry.getInstance().registerNativeMethod(
                "java/lang/Class",
                "getPrimitiveClass",
                "(Ljava/lang/String;)Ljava/lang/Class;",
                new GetPrimitiveClass());
        NativeRegistry.getInstance().registerNativeMethod(
                "java/lang/Class",
                "getName0",
                "()Ljava/lang/String;",
                new GetName0());
        NativeRegistry.getInstance().registerNativeMethod(
                "java/lang/Class",
                "desiredAssertionStatus0",
                "(Ljava/lang/Class;)Z",
                new DesiredAssertionStatus0());
//        NativeRegistry.getInstance().registerNativeMethod(
//                "java/lang/Class",
//                "isInterface",
//                "()Z",
//                new Object.GetClass());
//        NativeRegistry.getInstance().registerNativeMethod(
//                "java/lang/Class",
//                "isPrimitive",
//                "()Z",
//                new Object.GetClass());

        NativeRegistry.getInstance().registerNativeMethod(
                "java/lang/Class",
                "registerNatives",
                "()V",
                new EmptyNativeMethod());
    }

    public static class GetPrimitiveClass implements NativeMethod {

        @Override
        public void invoke(StackFrame frame) {
            MyObject ref = frame.getLocalVariableTable().getRef(0);
            if (!ref.getMyClass().getThisClassName().equals("java/lang/String")) {
                throw new MyJvmException("not a string obj!!!");
            }
            String string = MyString.toString(ref);
            MyClassLoader classLoader = frame.getMyMethod().getMyClass().getClassLoader();
            MyObject jClass = classLoader.loadClass(string).getJClass();
            frame.getOperandStack().pushRef(jClass);
        }

    }

    public static class GetName0 implements NativeMethod {

        @Override
        public void invoke(StackFrame frame) {
            MyObject ref = frame.getLocalVariableTable().getRef(0);
            MyClass myClass = (MyClass) ref.getExtra();
            String javaName = myClass.getJavaName();
            MyObject nameObject = MyString.create(javaName, myClass.getClassLoader());
            frame.getOperandStack().pushRef(nameObject);
        }

    }

    public static class DesiredAssertionStatus0 implements NativeMethod {

        @Override
        public void invoke(StackFrame frame) {
            frame.getOperandStack().pushInt(0);
        }
    }

    public static class IsInterface implements NativeMethod {

        @Override
        public void invoke(StackFrame frame) {

        }
    }

    public static class IsPrimitive implements NativeMethod {

        @Override
        public void invoke(StackFrame frame) {

        }
    }

}
