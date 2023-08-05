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
    public ConstantMethodHandleInfo(ConstantPool constPool, ClassReader reader, int tag) {
        int reference_kind = reader.nextU1toInt();
        int reference_index = reader.nextU2ToInt();
    }

    @Override
    public int getTag() {
        return 15;
    }
}
