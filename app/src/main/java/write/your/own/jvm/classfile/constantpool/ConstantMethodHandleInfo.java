package write.your.own.jvm.classfile.constantpool;

import write.your.own.jvm.classfile.ClassReader;

/**
 * CONSTANT_MethodHandle_info {
 * u1 tag;
 * u1 reference_kind;
 * u2 reference_index;
 * }
 */
public class ConstantMethodHandleInfo implements ConstantInfo {

    private final int referenceKind;
    private final int referenceIndex;

    private final ConstantPool constantPool;

    public ConstantMethodHandleInfo(ConstantPool constPool, ClassReader reader, int tag) {
        referenceKind = reader.nextU1toInt();
        referenceIndex = reader.nextU2ToInt();
        this.constantPool = constPool;
    }

    public int getReferenceIndex() {
        return referenceIndex;
    }

    public int getReferenceKind() {
        return referenceKind;
    }

    @Override
    public int getTag() {
        return 15;
    }

    public ConstantMemberRefInfo getMethodRef() {
        return (ConstantMemberRefInfo) constantPool.getConstantInfos()[referenceIndex];
    }

}
