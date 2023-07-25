package write.your.own.jvm.classfile.constantpool;

import write.your.own.jvm.classfile.ClassReader;

public class ConstantFloatInfo implements ConstantInfo {

    private final float value;

    public ConstantFloatInfo(ClassReader reader) {
        value = reader.nextU4ToFloat();
    }

//    @Override
//    public String getValue() {
//        return String.valueOf(value);
//    }

    @Override
    public String toString() {
        return "Float: " + value;
    }

    public float getFloatValue() {
        return value;
    }

    public int getTag() {
        return ConstantInfo.CONST_TAG_FLOAT;
    }

}
