package write.your.own.jvm.classfile.constantpool;

import write.your.own.jvm.classfile.ClassReader;

/**
 * CONSTANT_InvokeDynamic_info {
 * u1 tag;
 * u2 bootstrap_method_attr_index;
 * u2 name_and_type_index;
 * }
 */
public class ConstantInvokeDynamicInfo implements ConstantInfo {

    private final int bootstrapMethodAttrIndex;
    private final int nameAndTypeIndex;
    private final ConstantPool constantPool;

    public ConstantInvokeDynamicInfo(ConstantPool constPool, ClassReader reader, int tag) {
        this.constantPool = constPool;
        bootstrapMethodAttrIndex = reader.nextU2ToInt();
        nameAndTypeIndex = reader.nextU2ToInt();
    }

    @Override
    public int getTag() {
        return 18;
    }

    public int getBootstrapMethodAttrIndex() {
        return bootstrapMethodAttrIndex;
    }

    public String[] getNameAndType() {
        return constantPool.getNameAndType(nameAndTypeIndex);
    }


}
