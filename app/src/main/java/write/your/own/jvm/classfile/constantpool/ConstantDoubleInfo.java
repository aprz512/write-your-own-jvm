package write.your.own.jvm.classfile.constantpool;

import write.your.own.jvm.classfile.ClassReader;

public class ConstantDoubleInfo implements ConstantInfo {

    private final double value;

    public ConstantDoubleInfo(ClassReader reader) {
        value = reader.nextU8ToDouble();
    }
//
//    @Override
//    public String getValue() {
//        return String.valueOf(value);
//    }

    @Override
    public String toString() {
        return "Double: " + value;
    }

    public double getDoubleValue() {
        return value;
    }

    public int getTag() {
        return ConstantInfo.CONST_TAG_DOUBLE;
    }
}
