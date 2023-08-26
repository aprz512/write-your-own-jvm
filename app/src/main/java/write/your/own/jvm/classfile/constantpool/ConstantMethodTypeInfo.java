package write.your.own.jvm.classfile.constantpool;

import write.your.own.jvm.classfile.ClassReader;

/**
 * CONSTANT_MethodType_info {
 * u1 tag;
 * u2 descriptor_index;
 * }
 */
public class ConstantMethodTypeInfo implements ConstantInfo {

    private final int descriptorIndex;
    private final ConstantPool constantPool;

    public ConstantMethodTypeInfo(ConstantPool constantPool, ClassReader reader, int tag) {
        this.constantPool = constantPool;
        descriptorIndex = reader.nextU2ToInt();
    }

    @Override
    public int getTag() {
        return 16;
    }

    public String getDescriptor() {
        return constantPool.getUtf8(descriptorIndex);
    }
}
