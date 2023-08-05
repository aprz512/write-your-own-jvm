package write.your.own.jvm.instruction.reference;

import write.your.own.jvm.exception.MyJvmException;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ClassNameHelper {

    private static final HashMap<String, String> primitiveTypes = new HashMap<>();

    static {
        primitiveTypes.put("void", "V");
        primitiveTypes.put("boolean", "Z");
        primitiveTypes.put("byte", "B");
        primitiveTypes.put("short", "S");
        primitiveTypes.put("int", "I");
        primitiveTypes.put("long", "J");
        primitiveTypes.put("char", "C");
        primitiveTypes.put("float", "F");
        primitiveTypes.put("double", "D");
    }

    // [XXX -> [[XXX
    // int -> [I
    // XXX -> [LXXX;
    public static String getArrayClassName(String className) {
        return "[" + toDescriptor(className);
    }

    // [[XXX -> [XXX
    // [LXXX; -> XXX
    // [I -> int
    public static String getComponentClassName(String className) {
        if (className.charAt(0) == '[') {
            return toClassName(className.substring(1));
        }
        throw new MyJvmException("className error: " + className);
    }

    // [XXX => [XXX
    // int  => I
    // XXX  => LXXX;
    public static String toDescriptor(String className) {
        // array
        if (className.charAt(0) == '[') {
            return className;
        }
        // primitive
        String type = primitiveTypes.get(className);
        if (type != null) {
            return type;
        }
        // ref
        return "L" + className + ";";
    }

    // [XXX  => [XXX
    // LXXX; => XXX
    // I     => int
    public static String toClassName(String descriptor) {
        // array
        if (descriptor.charAt(0) == '[') {
            return descriptor;
        }
        // primitive
        Set<Map.Entry<String, String>> entries = primitiveTypes.entrySet();
        for (Map.Entry<String, String> entry : entries) {
            String key = entry.getKey();
            String value = entry.getValue();
            if (value.equals(descriptor)) {
                return key;
            }
        }
        // ref
        return descriptor.substring(1, descriptor.length() - 1);
    }

}
