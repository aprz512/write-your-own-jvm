package write.your.own.jvm.classfile.constantpool;

import write.your.own.jvm.classfile.ClassReader;

public class ConstantNameAndTypeInfo implements ConstantInfo {

    private final ConstantPool constPool;
    private final int nameIndex;
    private final int descriptorIndex;

    public ConstantNameAndTypeInfo(ConstantPool constPool, ClassReader reader) {
        this.nameIndex = reader.nextU2ToInt();
        this.descriptorIndex = reader.nextU2ToInt();
        this.constPool = constPool;
    }

    @Override
    public String getValue() {
        return this.constPool.getUTF8(this.nameIndex) + "&"
                + this.constPool.getUTF8(this.descriptorIndex);
    }

    @Override
    public String toString() {
        return "ConstantNameAndTypeInfo{"
                + this.constPool.getUTF8(this.nameIndex)
                + "&"
                + this.constPool.getUTF8(this.descriptorIndex)
                + "}";
    }

}
