package write.your.own.jvm.vnative.java.io;

import write.your.own.jvm.vnative.EmptyNativeMethod;
import write.your.own.jvm.vnative.NativeRegistry;

public class NFileInputStream {

    public static void init() {
        NativeRegistry.getInstance().registerNativeMethod(
                "java/io/FileInputStream",
                "initIDs",
                "()V",
                new EmptyNativeMethod());
    }


}
