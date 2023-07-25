package write.your.own.jvm.classfile.constantpool;

import write.your.own.jvm.classfile.ClassReader;

public class ConstantStringInfo implements ConstantInfo {

    private final ConstantPool constPool;

    /**
     * The value of the string_index item must be a valid index into the constant_pool table.
     * The constant_pool entry at that index must be a CONSTANT_Utf8_info (§4.4.7) structure representing the sequence of Unicode code points to which the String object is to be initialized.
     */
    private final int nameIndex;

    public ConstantStringInfo(ConstantPool aConstPool, ClassReader reader) {
        this.nameIndex = reader.nextU2ToInt();
        this.constPool = aConstPool;
    }

    @Override
    public String toString() {
        return "ConstStringInfo{" +
                "name=" + constPool.getUtf8(nameIndex) +
                '}';
    }

    public String getString() {
        return constPool.getUtf8(nameIndex);
    }

    public int getTag() {
        return ConstantInfo.CONST_TAG_STRING;
    }

}
