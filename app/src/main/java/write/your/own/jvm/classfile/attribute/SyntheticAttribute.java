package write.your.own.jvm.classfile.attribute;

/**
 * Synthetic_attribute {
 * u2 attribute_name_index;
 * u4 attribute_length;
 * }
 */
public class SyntheticAttribute extends AttributeInfo {
    public SyntheticAttribute(int attributeNameIndex, int attributeLength) {
        super(attributeNameIndex, attributeLength);
    }
}
