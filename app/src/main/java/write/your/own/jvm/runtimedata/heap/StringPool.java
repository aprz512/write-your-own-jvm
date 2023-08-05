package write.your.own.jvm.runtimedata.heap;

import java.util.HashMap;

public class StringPool {

    private static final StringPool instance = new StringPool();
    private final HashMap<Integer, String> inernalStrings = new HashMap<>();

    private StringPool() {
    }

    public static StringPool getInstance() {
        return instance;
    }

    public void putString(String value) {
        inernalStrings.put(value.hashCode(), value);
    }

//    public String get

}
