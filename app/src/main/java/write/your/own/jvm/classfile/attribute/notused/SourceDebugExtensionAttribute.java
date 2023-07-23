package write.your.own.jvm.classfile.attribute.notused;

import write.your.own.jvm.classfile.ClassReader;
import write.your.own.jvm.classfile.attribute.AttributeInfo;

/**
 * SourceDebugExtension_attribute {
 * u2 attribute_name_index;
 * u4 attribute_length;
 * u1 debug_extension[attribute_length];
 * }
 */
public class SourceDebugExtensionAttribute extends AttributeInfo {
    private final byte[] debugExtension;

    public SourceDebugExtensionAttribute(int attrNameIndex, int attrLength, ClassReader reader) {
        super(attrNameIndex, attrLength);
        debugExtension = reader.nextBytes(attrLength);
    }
}
