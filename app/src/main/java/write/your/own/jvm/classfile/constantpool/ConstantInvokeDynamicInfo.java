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
    public ConstantInvokeDynamicInfo(ConstantPool constPool, ClassReader reader, int tag) {
        int bootstrap_method_attr_index = reader.nextU2ToInt();
        int name_and_type_index = reader.nextU2ToInt();
    }

    @Override
    public int getTag() {
        return 18;
    }
}
