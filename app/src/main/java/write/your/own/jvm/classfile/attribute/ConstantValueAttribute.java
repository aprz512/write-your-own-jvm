package write.your.own.jvm.classfile.attribute;

import write.your.own.jvm.classfile.ClassReader;

/**
 * ConstantValue_attribute {
 * u2 attribute_name_index;
 * u4 attribute_length;
 * u2 constantvalue_index;
 * }
 */
public class ConstantValueAttribute extends AttributeInfo {

    private final int constantValueIndex;

    public ConstantValueAttribute(int attributeNameIndex, int attributeLength, ClassReader reader) {
        super(attributeNameIndex, attributeLength);
        this.constantValueIndex = reader.nextU2ToInt();
    }


}
