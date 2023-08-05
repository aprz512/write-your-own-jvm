package write.your.own.jvm.vnative;

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

    public void registerNativeMethod(String className, String methodName, String methodDescriptor, NativeMethod method) {
        String key = buildKey(className, methodName, methodDescriptor);
        registryMap.put(key, method);
    }

    private String buildKey(String className, String methodName, String methodDescriptor) {
        return className + "#" + methodName + "#" + methodDescriptor;
    }

    public NativeMethod findNativeMethod(String className, String methodName, String methodDescriptor) {
        if (methodDescriptor.equals("()V") && methodName.equals("registerNatives")) {
            // single instance should be better
            return new EmptyNativeMethod();
        }

        String key = buildKey(className, methodName, methodDescriptor);
        return registryMap.get(key);
    }

}
