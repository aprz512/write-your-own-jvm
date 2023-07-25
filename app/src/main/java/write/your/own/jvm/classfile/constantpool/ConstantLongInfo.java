package write.your.own.jvm.classfile.constantpool;

import write.your.own.jvm.classfile.ClassReader;

public class ConstantLongInfo implements ConstantInfo {

    private final long value;

    public ConstantLongInfo(ClassReader reader) {
        value = reader.nextU8ToLong();
    }

//    @Override
//    public String getValue() {
//        return String.valueOf(value);
//    }

    public long getLongValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Long: " + value;
    }

    public int getTag() {
        return ConstantInfo.CONST_TAG_LONG;
    }
}
