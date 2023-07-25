package write.your.own.jvm.classfile.constantpool;

import write.your.own.jvm.classfile.ClassReader;

public class ConstantUft8Info implements ConstantInfo {

    private final String value;

    public ConstantUft8Info(ClassReader reader) {
        int length = reader.nextU2ToInt();
        byte[] bytes = reader.nextBytes(length);
        this.value = new String(bytes);
    }

//    @Override
//    public String getValue() {
//        return value;
//    }

    @Override
    public String toString() {
        return value;
    }

    public int getTag() {
        return ConstantInfo.CONST_TAG_UTF8;
    }

    public String getUtf8() {
        return value;
    }
}
