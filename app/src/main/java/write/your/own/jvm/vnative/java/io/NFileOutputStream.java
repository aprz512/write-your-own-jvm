package write.your.own.jvm.vnative.java.io;

import write.your.own.jvm.runtimedata.LocalVariableTable;
import write.your.own.jvm.runtimedata.StackFrame;
import write.your.own.jvm.runtimedata.heap.ArrayObject;
import write.your.own.jvm.vnative.EmptyNativeMethod;
import write.your.own.jvm.vnative.NativeMethod;
import write.your.own.jvm.vnative.NativeRegistry;

public class NFileOutputStream {

    public static void init() {
        NativeRegistry.getInstance().registerNativeMethod(
                "java/io/FileOutputStream",
                "initIDs",
                "()V",
                new EmptyNativeMethod());

        NativeRegistry.getInstance().registerNativeMethod(
                "java/io/FileOutputStream",
                "writeBytes",
                "([BIIZ)V",
                new WriteBytes());
    }

    /**
     * // private native void writeBytes(byte b[], int off, int len, boolean append) throws IOException;
     * // ([BIIZ)V
     */
    public static class WriteBytes implements NativeMethod {

        @Override
        public void invoke(StackFrame frame) {
            LocalVariableTable localVariableTable = frame.getLocalVariableTable();
            ArrayObject b = (ArrayObject) localVariableTable.getRef(1);
            int off = localVariableTable.getInt(2);
            int len = localVariableTable.getInt(3);
            int append = localVariableTable.getInt(4);

            byte[] bytes = new byte[b.getArrayLength()];
            for (int i = 0; i < bytes.length; i++) {
                bytes[i] = (byte) b.getArrayElement(i);
            }
            // relocate all output stream to std
            System.out.write(bytes, off, len);
        }
    }


}
