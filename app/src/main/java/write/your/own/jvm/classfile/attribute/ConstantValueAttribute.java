package write.your.own.jvm.classfile.attribute;

import write.your.own.jvm.classfile.ClassReader;
import write.your.own.jvm.classfile.constantpool.ConstantPool;

/**
 * ConstantValue_attribute {
 * u2 attribute_name_index;
 * u4 attribute_length;
 * u2 constantvalue_index;
 * }
 */
public class ConstantValueAttribute extends AttributeInfo {

    /**
     * The value of the constantvalue_index item must be a valid index into the constant_pool table.
     * The constant_pool entry at that index gives the constant value represented by this attribute.
     * The constant_pool entry must be of a type appropriate to the field, as shown by Table 4.7.
     */
    private final int constantValueIndex;
    private final ConstantPool constantPool;

    public ConstantValueAttribute(int attributeNameIndex, int attributeLength, ClassReader reader, ConstantPool constantPool) {
        super(attributeNameIndex, attributeLength);
        this.constantPool = constantPool;
        this.constantValueIndex = reader.nextU2ToInt();
    }

    public int getConstantValueIndex() {
        return constantValueIndex;
    }
}
