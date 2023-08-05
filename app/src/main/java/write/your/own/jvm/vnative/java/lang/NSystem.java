package write.your.own.jvm.vnative.java.lang;

import write.your.own.jvm.exception.MyJvmException;
import write.your.own.jvm.runtimedata.LocalVariableTable;
import write.your.own.jvm.runtimedata.StackFrame;
import write.your.own.jvm.runtimedata.heap.ArrayObject;
import write.your.own.jvm.runtimedata.heap.MyClass;
import write.your.own.jvm.vnative.NativeMethod;
import write.your.own.jvm.vnative.NativeRegistry;

public class NSystem {
    public static void init() {
        NativeRegistry.getInstance().registerNativeMethod(
                "java/lang/System",
                "arraycopy",
                "(Ljava/lang/Object;ILjava/lang/Object;II)V",
                new ArrayCopy());
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
}
