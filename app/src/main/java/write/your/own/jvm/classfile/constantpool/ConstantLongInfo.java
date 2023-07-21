package write.your.own.jvm.classfile.constantpool;

import write.your.own.jvm.classfile.ClassReader;

public class ConstantLongInfo implements ConstantInfo {

    private final Long value;

    public ConstantLongInfo(ClassReader reader) {
        value = reader.nextU8ToLong();
    }

    @Override
    public String getValue() {
        return String.valueOf(value);
    }

    @Override
    public String toString() {
        return "Long: " + value;
    }
}
