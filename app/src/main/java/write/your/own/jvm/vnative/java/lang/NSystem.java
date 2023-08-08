package write.your.own.jvm.vnative.java.lang;

import write.your.own.jvm.exception.MyJvmException;
import write.your.own.jvm.instruction.base.InvokeMethod;
import write.your.own.jvm.runtimedata.*;
import write.your.own.jvm.runtimedata.heap.*;
import write.your.own.jvm.vnative.EmptyNativeMethod;
import write.your.own.jvm.vnative.NativeMethod;
import write.your.own.jvm.vnative.NativeRegistry;

import java.util.HashMap;
import java.util.function.BiConsumer;

public class NSystem {
    public static void init() {
        NativeRegistry.getInstance().registerNativeMethod(
                "java/lang/System",
                "arraycopy",
                "(Ljava/lang/Object;ILjava/lang/Object;II)V",
                new ArrayCopy());
        NativeRegistry.getInstance().registerNativeMethod(
                "java/lang/System",
                "initProperties",
                "(Ljava/util/Properties;)Ljava/util/Properties;",
                new InitProperties());
        NativeRegistry.getInstance().registerNativeMethod(
                "java/lang/System",
                "registerNatives",
                "()V",
                new EmptyNativeMethod());
    }

    public static class ArrayCopy implements NativeMethod {

        @Override
        public void invoke(StackFrame frame) {
            LocalVariableTable localVariableTable = frame.getLocalVariableTable();
            ArrayObject src = (ArrayObject) localVariableTable.getRef(0);
            int srcPos = localVariableTable.getInt(1);
            ArrayObject dst = (ArrayObject) localVariableTable.getRef(2);
            int dstPos = localVariableTable.getInt(3);
            int length = localVariableTable.getInt(4);

            if (src == null || dst == null) {
                throw new MyJvmException("java.lang.NullPointerException");
            }

            if (!checkArrayCopy(src, dst)) {
                throw new MyJvmException("java.lang.ArrayStoreException");
            }

            if (srcPos < 0 || dstPos < 0 || srcPos + length > src.getArrayLength()
                    || dstPos + length > dst.getArrayLength()) {
                throw new MyJvmException("java.lang.IndexOutOfBoundsException");
            }

            for (int i = 0; i < length; i++) {
                dst.setArrayElement(dstPos + i, src.getArrayElement(srcPos + i));
            }
        }

        private boolean checkArrayCopy(ArrayObject src, ArrayObject dst) {
            MyClass srcClass = src.getMyClass();
            MyClass dstClass = dst.getMyClass();
            if (!srcClass.isArray() || !dstClass.isArray()) {
                return false;
            }

            if (srcClass.getComponentClass().isPrimitive()
                    || dstClass.getComponentClass().isPrimitive()) {
                return srcClass == dstClass;
            }
            return true;
        }
    }

    /**
     * // private static native Properties initProperties(Properties props);
     * // (Ljava/util/Properties;)Ljava/util/Properties;
     */
    public static class InitProperties implements NativeMethod {

        @Override
        public void invoke(StackFrame frame) {
            LocalVariableTable localVariableTable = frame.getLocalVariableTable();
            MyObject props = localVariableTable.getRef(0);
            OperandStack operandStack = frame.getOperandStack();
            operandStack.pushRef(props);
            MyClassLoader classLoader = props.getMyClass().getClassLoader();

            MyMethod setProperty = props.getMyClass().getMethod(
                    "setProperty",
                    "(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/Object;",
                    false);
            HashMap<String, String> systemProperties = systemProperties();
            systemProperties.forEach(new BiConsumer<String, String>() {
                @Override
                public void accept(String key, String value) {
                    MyObject keyObj = MyString.create(key, classLoader);
                    MyObject valueObj = MyString.create(value, classLoader);

                    // 虚拟一个方法调用，便于传递参数
                    StackFrame vsf = Shim.createShimReturnFrame(frame.getThread(), 3);
                    OperandStack vos = vsf.getOperandStack();
                    vos.pushRef(props);
                    vos.pushRef(keyObj);
                    vos.pushRef(valueObj);
                    frame.getThread().pushStackFrame(vsf);

                    // 在虚拟的方法里面调用 setProperty 方法
                    InvokeMethod.invokeMethod(vsf, setProperty);
                }
            });
        }

        private HashMap<String, String> systemProperties() {
            HashMap<String, String> map = new HashMap<>();
            map.put("java.version", "1.8.0");
            map.put("java.vendor", "jvm.go");
            map.put("java.vendor.rl", "https://github.com/aprz512/write-your-own-jvm");
            map.put("java.home", System.getProperty("java.home"));
            map.put("java.class.version", "52.0");
            map.put("java.class.path", System.getProperty("java.class.path"));
            map.put("java.awt.graphicsenv", "sun.awt.CGraphicsEnvironment");
            map.put("os.name", "my_jvm");
            map.put("os.arch", System.getProperty("os.arch"));
            map.put("os.version", "");
            map.put("file.separator", "/");
            map.put("path.separator", ":");
            map.put("line.separator", "\n");
            map.put("user.name", "");
            map.put("user.home", "");
            map.put("user.dir", ".");
            map.put("user.country", "CN");
            map.put("file.encoding", "UTF-8");
            return map;
        }
    }
}
