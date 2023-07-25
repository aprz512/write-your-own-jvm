package write.your.own.jvm.classfile.constantpool;

import write.your.own.jvm.classfile.ClassReader;

public class ConstantNameAndTypeInfo implements ConstantInfo {

    private final ConstantPool constPool;
    /**
     * The value of the name_index item must be a valid index into the constant_pool table.
     * The constant_pool entry at that index must be a CONSTANT_Utf8_info (§4.4.7) structure representing either the special method name <init> (§2.9) or a valid unqualified name (§4.2.2) denoting a field or method.
     */
    private final int nameIndex;
    /**
     * The value of the descriptor_index item must be a valid index into the constant_pool table.
     * The constant_pool entry at that index must be a CONSTANT_Utf8_info (§4.4.7) structure representing a valid field descriptor (§4.3.2) or method descriptor (§4.3.3).
     */
    private final int descriptorIndex;

    public ConstantNameAndTypeInfo(ConstantPool constPool, ClassReader reader) {
        this.nameIndex = reader.nextU2ToInt();
        this.descriptorIndex = reader.nextU2ToInt();
        this.constPool = constPool;
    }

//    @Override
//    public String getValue() {
//        return this.constPool.getUtf8(this.nameIndex) + "&"
//                + this.constPool.getUtf8(this.descriptorIndex);
//    }

    @Override
    public String toString() {
        return "ConstantNameAndTypeInfo{"
                + this.constPool.getUtf8(this.nameIndex)
                + "&"
                + this.constPool.getUtf8(this.descriptorIndex)
                + "}";
    }

    /**
     * The tag item of the CONSTANT_NameAndType_info structure has the value CONSTANT_NameAndType (12).
     */
    public int getTag() {
        return ConstantInfo.CONST_TAG_NAME_AND_TYPE;
    }

    public String getName() {
        return constPool.getUtf8(nameIndex);
    }

    public String getDescriptor() {
        return constPool.getUtf8(descriptorIndex);
    }

}
