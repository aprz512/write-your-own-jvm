package write.your.own.jvm.vnative.java.io;

import write.your.own.jvm.runtimedata.StackFrame;
import write.your.own.jvm.vnative.EmptyNativeMethod;
import write.your.own.jvm.vnative.NativeMethod;
import write.your.own.jvm.vnative.NativeRegistry;

public class NFileDescriptor {

    public static void init() {
        NativeRegistry.getInstance().registerNativeMethod(
                "java/io/FileDescriptor",
                "initIDs",
                "()V",
                new EmptyNativeMethod());

        NativeRegistry.getInstance().registerNativeMethod(
                "java/io/FileDescriptor",
                "set",
                "(I)J",
                new Set());
    }

    public static class Set implements NativeMethod {

        @Override
        public void invoke(StackFrame frame) {
            // fake logic
            frame.getOperandStack().pushLong(0);
        }
    }


}
