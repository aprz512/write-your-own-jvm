package write.your.own.jvm.classfile.constantpool;

import write.your.own.jvm.classfile.ClassReader;

/**
 * CONSTANT_Fieldref_info {
 * u1 tag;
 * u2 class_index;
 * u2 name_and_type_index;
 * }
 * <p>
 * CONSTANT_Methodref_info {
 * u1 tag;
 * u2 class_index;
 * u2 name_and_type_index;
 * }
 * <p>
 * CONSTANT_InterfaceMethodref_info {
 * u1 tag;
 * u2 class_index;
 * u2 name_and_type_index;
 * }
 */
public class ConstantMemberRefInfo implements ConstantInfo {

    // The constant_pool entry at that index must be a CONSTANT_Class_info (§4.4.1) structure representing a class or interface type that has the field or method as a member.
    private final int classIndex;
    // The constant_pool entry at that index must be a CONSTANT_NameAndType_info (§4.4.6) structure. This constant_pool entry indicates the name and descriptor of the field or method.
    private final int nameAndTypeIndex;
    private final ConstantPool constPool;

    private final int tag;

    public ConstantMemberRefInfo(ConstantPool constPool, ClassReader reader, int tag) {
        this.classIndex = reader.nextU2ToInt();
        this.nameAndTypeIndex = reader.nextU2ToInt();
        this.constPool = constPool;
        this.tag = tag;
    }

//    @Override
//    public String getValue() {
//        return constPool.getUTF8(classIndex) + " " + constPool.getUTF8(nameAndTypeIndex);
//    }

    @Override
    public String toString() {
        ConstantInfo[] constantInfos = constPool.getConstantInfos();
        ConstantClassInfo constClassInfo = (ConstantClassInfo) constantInfos[classIndex];
        ConstantNameAndTypeInfo nameAndTypeInfo = (ConstantNameAndTypeInfo) constantInfos[nameAndTypeIndex];
        return "ConstantMemberRefInfo{" +
                constClassInfo + "  " +
                nameAndTypeInfo +
                '}';
    }

    public int getTag() {
        return tag;
    }

    public String getClassName() {
        return constPool.getClassName(classIndex);
    }

    public String[] getNameAndDescriptor() {
        return constPool.getNameAndType(nameAndTypeIndex);
    }

}
