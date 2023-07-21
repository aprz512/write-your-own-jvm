package write.your.own.jvm.classfile.attribute;

/**
 * Deprecated_attribute {
 *     u2 attribute_name_index;
 *     u4 attribute_length;
 * }
 */
public class DeprecatedAttribute extends AttributeInfo {
    public DeprecatedAttribute(int attrNameIndex, int attrLength) {
        super(attrNameIndex, attrLength);
    }
}
