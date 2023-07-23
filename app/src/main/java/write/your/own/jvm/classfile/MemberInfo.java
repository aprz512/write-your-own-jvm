package write.your.own.jvm.classfile;

import write.your.own.jvm.classfile.attribute.AttributeInfo;
import write.your.own.jvm.classfile.constantpool.ConstantPool;

/**
 * method_info {
 * u2             access_flags;
 * u2             name_index;
 * u2             descriptor_index;
 * u2             attributes_count;
 * attribute_info attributes[attributes_count];
 * }
 * <p>
 * field_info {
 * u2             access_flags;
 * u2             name_index;
 * u2             descriptor_index;
 * u2             attributes_count;
 * attribute_info attributes[attributes_count];
 * }
 */
public class MemberInfo {

    private final int accessFlags;
    private final int nameIndex;
    private final int descriptorIndex;
    private final int attributesCount;
    private final AttributeInfo[] attributes;
    private ConstantPool constantPool;

    public MemberInfo(ConstantPool constantPool, ClassReader reader) {
        this.constantPool = constantPool;
        accessFlags = reader.nextU2ToInt();
        nameIndex = reader.nextU2ToInt();
        descriptorIndex = reader.nextU2ToInt();
        attributesCount = reader.nextU2ToInt();
        attributes = AttributeInfo.readAttributes(reader, constantPool, attributesCount);
    }

    public static MemberInfo[] readMembers(ConstantPool constantPool, ClassReader reader) {
        int memberCount = reader.nextU2ToInt();
        MemberInfo[] memberInfo = new MemberInfo[memberCount];

        for (int i = 0; i < memberCount; i++) {
            memberInfo[i] = new MemberInfo(constantPool, reader);
//            Log.d("memberInfo = " + constantPool.getUTF8(memberInfo[i].getNameIndex()));
        }

        return memberInfo;
    }

    public int getNameIndex() {
        return nameIndex;
    }
}
