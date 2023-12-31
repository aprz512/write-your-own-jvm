package write.your.own.jvm.vnative;

import write.your.own.jvm.vnative.java.io.NFileDescriptor;
import write.your.own.jvm.vnative.java.io.NFileInputStream;
import write.your.own.jvm.vnative.java.io.NFileOutputStream;
import write.your.own.jvm.vnative.java.lang.*;
import write.your.own.jvm.vnative.java.security.NAccessController;
import write.your.own.jvm.vnative.sun.misc.NUnsafe;
import write.your.own.jvm.vnative.sun.misc.NVM;
import write.your.own.jvm.vnative.sun.reflect.NReflection;

import java.util.HashMap;

/**
 * 注册 native 方法
 */
public class NativeRegistry {

    private static final NativeRegistry registry = new NativeRegistry();
    private final HashMap<String, NativeMethod> registryMap = new HashMap<>();

    private NativeRegistry() {
    }

    public static NativeRegistry getInstance() {
        return registry;
    }

    public static void init() {
        NClass.init();
        NObject.init();
        NFloat.init();
        NString.init();
        NSystem.init();
        NDouble.init();
        NVM.init();
        NThrowable.init();
        NFileInputStream.init();
        NFileOutputStream.init();
        NFileDescriptor.init();
        NUnsafe.init();
        NReflection.init();
        NAccessController.init();
        NThread.init();
    }

    public void registerNativeMethod(String className, String methodName, String methodDescriptor, NativeMethod method) {
        String key = buildKey(className, methodName, methodDescriptor);
        registryMap.put(key, method);
    }

    private String buildKey(String className, String methodName, String methodDescriptor) {
        return className + "#" + methodName + "#" + methodDescriptor;
    }

    public NativeMethod findNativeMethod(String className, String methodName, String methodDescriptor) {
//        if (methodDescriptor.equals("()V") && methodName.equals("registerNatives")) {
//            // single instance should be better
//            return new EmptyNativeMethod();
//        }

        String key = buildKey(className, methodName, methodDescriptor);
        return registryMap.get(key);
    }

}
