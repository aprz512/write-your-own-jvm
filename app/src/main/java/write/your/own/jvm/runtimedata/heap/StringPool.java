package write.your.own.jvm.runtimedata.heap;

import java.util.HashMap;

public class StringPool {

    private static final StringPool instance = new StringPool();
    private final HashMap<String, MyObject> inernalStrings = new HashMap<>();

    private StringPool() {
    }

    public static StringPool getInstance() {
        return instance;
    }

    public MyObject putString(String string, MyObject stringObj) {
        if (inernalStrings.containsKey(string)) {
            return inernalStrings.get(string);
        }
        inernalStrings.put(string, stringObj);
        return stringObj;
    }

    public MyObject get(String string) {
        return inernalStrings.get(string);
    }
}
