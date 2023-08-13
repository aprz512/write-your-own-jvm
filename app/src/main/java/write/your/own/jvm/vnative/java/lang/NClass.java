package write.your.own.jvm.vnative.java.lang;

import write.your.own.jvm.exception.MyJvmException;
import write.your.own.jvm.instruction.base.InvokeMethod;
import write.your.own.jvm.runtimedata.*;
import write.your.own.jvm.runtimedata.heap.*;
import write.your.own.jvm.vnative.EmptyNativeMethod;
import write.your.own.jvm.vnative.NativeMethod;
import write.your.own.jvm.vnative.NativeRegistry;

import java.util.List;

public class NClass {

    public static void init() {
        NativeRegistry.getInstance().registerNativeMethod(
                "java/lang/Class",
                "registerNatives",
                "()V",
                new EmptyNativeMethod());
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
        NativeRegistry.getInstance().registerNativeMethod(
                "java/lang/Class",
                "isInterface",
                "()Z",
                new IsInterface());
        NativeRegistry.getInstance().registerNativeMethod(
                "java/lang/Class",
                "isPrimitive",
                "()Z",
                new IsPrimitive());
        NativeRegistry.getInstance().registerNativeMethod("java/lang/Class", "getDeclaredFields0", "(Z)[Ljava/lang/reflect/Field;", new GetDeclaredFields0());
        NativeRegistry.getInstance().registerNativeMethod("java/lang/Class", "forName0", "(Ljava/lang/String;ZLjava/lang/ClassLoader;Ljava/lang/Class;)Ljava/lang/Class;", new ForName0());
//        NativeRegistry.getInstance().registerNativeMethod("java/lang/Class", "getDeclaredConstructors0", "(Z)[Ljava/lang/reflect/Constructor;", new GetDeclaredConstructors0());
//        NativeRegistry.getInstance().registerNativeMethod("java/lang/Class", "getModifiers", "()I", new GetModifiers());
//        NativeRegistry.getInstance().registerNativeMethod("java/lang/Class", "getSuperclass", "()Ljava/lang/Class;", new GetSuperclass());
//        NativeRegistry.getInstance().registerNativeMethod("java/lang/Class", "getInterfaces0", "()[Ljava/lang/Class;", new GetInterfaces0());
//        NativeRegistry.getInstance().registerNativeMethod("java/lang/Class", "isArray", "()Z", new IsArray());
//        NativeRegistry.getInstance().registerNativeMethod("java/lang/Class", "getDeclaredMethods0", "(Z)[Ljava/lang/reflect/Method;", new GetDeclaredMethods0());
//        NativeRegistry.getInstance().registerNativeMethod("java/lang/Class", "getComponentType", "()Ljava/lang/Class;", new GetComponentType());
//        NativeRegistry.getInstance().registerNativeMethod("java/lang/Class", "isAssignableFrom", "(Ljava/lang/Class;)Z", new IsAssignableFrom());
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
            LocalVariableTable localVariableTable = frame.getLocalVariableTable();
            MyObject thisObj = localVariableTable.getRef(0);
            MyClass jClass = (MyClass) thisObj.getExtra();
            frame.getOperandStack().pushBoolean(jClass.isInterface());
        }
    }

    public static class IsPrimitive implements NativeMethod {

        @Override
        public void invoke(StackFrame frame) {
            LocalVariableTable localVariableTable = frame.getLocalVariableTable();
            MyObject thisObj = localVariableTable.getRef(0);
            MyClass jClass = (MyClass) thisObj.getExtra();
            frame.getOperandStack().pushBoolean(jClass.isPrimitive());
        }
    }

    /**
     * // private native Field[] getDeclaredFields0(boolean publicOnly);
     * // (Z)[Ljava/lang/reflect/Field;
     */
    public static class GetDeclaredFields0 implements NativeMethod {

        private static final String fieldConstructorDescriptor =
                "(Ljava/lang/Class;" +
                        "Ljava/lang/String;" +
                        "Ljava/lang/Class;" +
                        "II" +
                        "Ljava/lang/String;" +
                        "[B)V";

        @Override
        public void invoke(StackFrame frame) {
            LocalVariableTable localVariableTable = frame.getLocalVariableTable();
            MyObject thisObj = localVariableTable.getRef(0);
            int publicOnly = localVariableTable.getInt(1);

            MyClass jClass = (MyClass) thisObj.getExtra();
            List<MyField> publicFields = jClass.getFields(publicOnly == 1);
            int fieldsCount = publicFields.size();

            MyClassLoader classLoader = frame.getMyMethod().getMyClass().getClassLoader();
            MyClass fieldClass = classLoader.loadClass("java/lang/reflect/Field");
            ArrayObject fieldArrayObj = fieldClass.newArrayObject(fieldsCount);

            // 使用 Field 的构造函数来创建 Field 对象
            if (fieldsCount > 0) {
                MyMethod constructor = fieldClass.getConstructor(fieldConstructorDescriptor);
                for (int i = 0; i < publicFields.size(); i++) {
                    MyField field = publicFields.get(i);
                    MyObject fieldObj = fieldClass.newObject();
                    fieldObj.setExtra(field);
                    fieldArrayObj.setArrayElement(i, fieldObj);

                    OperandStack stack = new OperandStack(8);
                    stack.pushRef(fieldObj);
                    stack.pushRef(thisObj);
                    stack.pushRef(MyString.create(field.getName(), classLoader));
                    stack.pushRef(field.getType().getJClass());
                    stack.pushInt(field.getAccessFlag());
                    stack.pushInt(field.getSlotId());
                    // todo: 这里的 signature 是空的，直接使用类型加名字算了，反正 native 逻辑都是自己写的
                    stack.pushRef(MyString.create(field.getSignature(), classLoader));
                    stack.pushRef(ArrayObject.createByteArrayObject(classLoader, field.getAnnotationData()));

                    StackFrame shimReturnFrame = Shim.createShimReturnFrame(frame.getThread(), 8);
                    frame.getThread().pushStackFrame(shimReturnFrame);

                    InvokeMethod.invokeMethod(shimReturnFrame, constructor);
                }
            }

            OperandStack operandStack = frame.getOperandStack();
            operandStack.pushRef(fieldArrayObj);
        }
    }

    /**
     * // private static native Class<?> forName0(String name, boolean initialize,
     * //                                         ClassLoader loader,
     * //                                         Class<?> caller)
     * //     throws ClassNotFoundException;
     * // (Ljava/lang/String;ZLjava/lang/ClassLoader;Ljava/lang/Class;)Ljava/lang/Class;
     */
    private static class ForName0 implements NativeMethod {

        @Override
        public void invoke(StackFrame frame) {
            LocalVariableTable localVariableTable = frame.getLocalVariableTable();
        }
    }

}
