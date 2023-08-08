package write.your.own.jvm.vnative.sun.misc;

import write.your.own.jvm.runtimedata.LocalVariableTable;
import write.your.own.jvm.runtimedata.StackFrame;
import write.your.own.jvm.runtimedata.heap.ArrayObject;
import write.your.own.jvm.runtimedata.heap.MyObject;
import write.your.own.jvm.vnative.EmptyNativeMethod;
import write.your.own.jvm.vnative.NativeMethod;
import write.your.own.jvm.vnative.NativeRegistry;

public class NUnsafe {

    public static void init() {
        NativeRegistry.getInstance().registerNativeMethod(
                "sun/misc/Unsafe",
                "registerNatives",
                "()V",
                new EmptyNativeMethod());

        NativeRegistry.getInstance().registerNativeMethod(
                "sun/misc/Unsafe",
                "arrayBaseOffset",
                "(Ljava/lang/Class;)I",
                new ArrayBaseOffset());

        NativeRegistry.getInstance().registerNativeMethod(
                "sun/misc/Unsafe",
                "arrayIndexScale",
                "(Ljava/lang/Class;)I",
                new ArrayIndexScale());
        NativeRegistry.getInstance().registerNativeMethod(
                "sun/misc/Unsafe",
                "addressSize",
                "()I",
                new AddressSize());
        NativeRegistry.getInstance().registerNativeMethod(
                "sun/misc/Unsafe",
                "objectFieldOffset",
                "(Ljava/lang/reflect/Field;)J",
                new ObjectFieldOffset());
        NativeRegistry.getInstance().registerNativeMethod(
                "sun/misc/Unsafe",
                "compareAndSwapObject",
                "(Ljava/lang/Object;JLjava/lang/Object;Ljava/lang/Object;)Z",
                new CompareAndSwapObject());
        NativeRegistry.getInstance().registerNativeMethod(
                "sun/misc/Unsafe",
                "getIntVolatile",
                "(Ljava/lang/Object;J)I",
                new GetInt());
        NativeRegistry.getInstance().registerNativeMethod(
                "sun/misc/Unsafe",
                "compareAndSwapInt",
                "(Ljava/lang/Object;JII)Z",
                new CompareAndSwapInt());
        NativeRegistry.getInstance().registerNativeMethod(
                "sun/misc/Unsafe",
                "getObjectVolatile",
                "(Ljava/lang/Object;J)Ljava/lang/Object;",
                new GetObject());
        NativeRegistry.getInstance().registerNativeMethod(
                "sun/misc/Unsafe",
                "compareAndSwapLong",
                "(Ljava/lang/Object;JJJ)Z",
                new CompareAndSwapLong());
    }

    /**
     * // public native int arrayBaseOffset(Class<?> type);
     * // (Ljava/lang/Class;)I
     */
    public static class ArrayBaseOffset implements NativeMethod {

        @Override
        public void invoke(StackFrame frame) {
            // fake logic
            // set return value 0
            frame.getOperandStack().pushInt(0);
        }
    }

    /**
     * // public native int arrayIndexScale(Class<?> type);
     * // (Ljava/lang/Class;)I
     */
    public static class ArrayIndexScale implements NativeMethod {
        @Override
        public void invoke(StackFrame frame) {
            // fake logic
            // set return value 1
            frame.getOperandStack().pushInt(1);
        }
    }

    /**
     * // public native int addressSize();
     * // ()I
     */
    public static class AddressSize implements NativeMethod {
        @Override
        public void invoke(StackFrame frame) {
            // fake logic
            // set return value 8
            frame.getOperandStack().pushInt(8);
        }
    }

    /**
     * // public native long objectFieldOffset(Field field);
     * // (Ljava/lang/reflect/Field;)J
     */
    public static class ObjectFieldOffset implements NativeMethod {
        @Override
        public void invoke(StackFrame frame) {
            // cal field offset
            LocalVariableTable localVariableTable = frame.getLocalVariableTable();
            // 0 is this, 1 is field
            MyObject field = localVariableTable.getRef(1);
            // java.lang.reflect.Field.slot
            int offset = field.getIntFieldValue("slot", "I");
            frame.getOperandStack().pushLong(offset);
        }
    }

    /**
     * // public final native boolean compareAndSwapObject(Object o, long offset, Object expected, Object x)
     * // (Ljava/lang/Object;JLjava/lang/Object;Ljava/lang/Object;)Z
     */
    public static class CompareAndSwapObject implements NativeMethod {
        @Override
        public void invoke(StackFrame frame) {
            LocalVariableTable localVariableTable = frame.getLocalVariableTable();
            MyObject o = localVariableTable.getRef(1);
            long offset = localVariableTable.getLong(2);
            MyObject expected = localVariableTable.getRef(4);
            MyObject x = localVariableTable.getRef(5);

            // just swap, no race for single thread mode
            if (o instanceof ArrayObject) {
                ArrayObject arrayObject = (ArrayObject) o;
                arrayObject.setArrayElement((int) offset, x);
                frame.getOperandStack().pushInt(1);
            } else {
                o.getInstanceFields().setRef((int) offset, x);
                frame.getOperandStack().pushInt(1);
            }
        }
    }

    /**
     * // public native boolean getInt(Object o, long offset);
     * // (Ljava/lang/Object;J)I
     */
    public static class GetInt implements NativeMethod {
        @Override
        public void invoke(StackFrame frame) {
            LocalVariableTable localVariableTable = frame.getLocalVariableTable();
            MyObject o = localVariableTable.getRef(1);
            long offset = localVariableTable.getLong(2);

            // just swap, no race for single thread mode
            if (o instanceof ArrayObject) {
                ArrayObject arrayObject = (ArrayObject) o;
                Object value = arrayObject.getArrayElement((int) offset);
                frame.getOperandStack().pushInt((Integer) value);
            } else {
                int value = o.getInstanceFields().getInt((int) offset);
                frame.getOperandStack().pushInt(value);
            }
        }
    }

    /**
     * // public final native boolean compareAndSwapInt(Object o, long offset, int expected, int x);
     * // (Ljava/lang/Object;JII)Z
     */
    public static class CompareAndSwapInt implements NativeMethod {
        @Override
        public void invoke(StackFrame frame) {
            LocalVariableTable localVariableTable = frame.getLocalVariableTable();
            MyObject o = localVariableTable.getRef(1);
            long offset = localVariableTable.getLong(2);
            int expected = localVariableTable.getInt(4);
            int x = localVariableTable.getInt(5);

            // just swap, no race for single thread mode
            if (o instanceof ArrayObject) {
                ArrayObject arrayObject = (ArrayObject) o;
                arrayObject.setArrayElement((int) offset, x);
                frame.getOperandStack().pushInt(1);
            } else {
                o.getInstanceFields().setInt((int) offset, x);
                frame.getOperandStack().pushInt(1);
            }
        }
    }

    /**
     * // public native Object getObject(Object o, long offset);
     * // (Ljava/lang/Object;J)Ljava/lang/Object;
     */
    public static class GetObject implements NativeMethod {
        @Override
        public void invoke(StackFrame frame) {
            LocalVariableTable localVariableTable = frame.getLocalVariableTable();
            MyObject o = localVariableTable.getRef(1);
            long offset = localVariableTable.getLong(2);

            // just swap, no race for single thread mode
            if (o instanceof ArrayObject) {
                ArrayObject arrayObject = (ArrayObject) o;
                Object value = arrayObject.getArrayElement((int) offset);
                frame.getOperandStack().pushRef((MyObject) value);
            } else {
                MyObject value = o.getInstanceFields().getRef((int) offset);
                frame.getOperandStack().pushRef(value);
            }
        }
    }

    /**
     * // public final native boolean compareAndSwapLong(Object o, long offset, long expected, long x);
     * // (Ljava/lang/Object;JJJ)Z
     */
    public static class CompareAndSwapLong implements NativeMethod {
        @Override
        public void invoke(StackFrame frame) {
            LocalVariableTable localVariableTable = frame.getLocalVariableTable();
            MyObject o = localVariableTable.getRef(1);
            long offset = localVariableTable.getLong(2);
            long expected = localVariableTable.getLong(4);
            long x = localVariableTable.getLong(6);

            // just swap, no race for single thread mode
            if (o instanceof ArrayObject) {
                ArrayObject arrayObject = (ArrayObject) o;
                arrayObject.setArrayElement((int) offset, x);
                frame.getOperandStack().pushInt(1);
            } else {
                o.getInstanceFields().setLong((int) offset, x);
                frame.getOperandStack().pushInt(1);
            }
        }
    }


}
