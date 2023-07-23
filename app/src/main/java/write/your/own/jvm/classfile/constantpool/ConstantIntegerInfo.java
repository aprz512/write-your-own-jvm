package write.your.own.jvm.classfile.constantpool;

import write.your.own.jvm.classfile.ClassReader;

public class ConstantIntegerInfo implements ConstantInfo {

    private final int value;

    public ConstantIntegerInfo(ClassReader reader) {
        value = reader.nextU4ToInt();
    }

    @Override
    public String getValue() {
        return String.valueOf(value);
    }

    @Override
    public String toString() {
        return "Integer: " + value;
    }

    public int getIntValue() {
        return value;
    }
}
