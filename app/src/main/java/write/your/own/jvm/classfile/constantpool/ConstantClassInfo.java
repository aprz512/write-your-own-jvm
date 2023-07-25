package write.your.own.jvm.classfile.constantpool;

import write.your.own.jvm.classfile.ClassReader;

/**
 * CONSTANT_Class_info {
 * u1 tag;
 * u2 name_index;
 * }
 */
public class ConstantClassInfo implements ConstantInfo {

    private final ConstantPool constPool;
    // The constant_pool entry at that index must be a CONSTANT_Utf8_info (§4.4.7) structure
    // representing a valid binary class or interface name encoded in internal form
    private final int nameIndex;

    public ConstantClassInfo(ConstantPool constPool, ClassReader reader) {
        nameIndex = reader.nextU2ToInt();
        this.constPool = constPool;
    }

//    @Override
//    public String getValue() {
//        return constPool.getUTF8(nameIndex);
//    }

    @Override
    public int getTag() {
        return ConstantInfo.CONST_TAG_CLASS;
    }

    public String getName() {
        return constPool.getUtf8(nameIndex);
    }

    // just for test
    @Override
    public String toString() {
        return "ConstantClassInfo{" + getName() + "}";
    }

}
