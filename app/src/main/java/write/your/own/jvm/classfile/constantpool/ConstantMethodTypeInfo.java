package write.your.own.jvm.classfile.constantpool;

import write.your.own.jvm.classfile.ClassReader;

/**
 * CONSTANT_MethodType_info {
 * u1 tag;
 * u2 descriptor_index;
 * }
 */
public class ConstantMethodTypeInfo implements ConstantInfo {
    public ConstantMethodTypeInfo(ConstantPool constPool, ClassReader reader, int tag) {
        int descriptor_index = reader.nextU2ToInt();
    }

    @Override
    public int getTag() {
        return 16;
    }
}
