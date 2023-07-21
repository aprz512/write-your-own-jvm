package write.your.own.jvm.classfile.attribute.notused;

import write.your.own.jvm.classfile.ClassReader;
import write.your.own.jvm.classfile.attribute.AttributeInfo;

/**
 * EnclosingMethod_attribute {
 *     u2 attribute_name_index;
 *     u4 attribute_length;
 *     u2 class_index;
 *     u2 method_index;
 * }
 */
public class EnclosingMethodAttribute  extends AttributeInfo {

    private final int classIndex;
    private final int methodIndex;

    public EnclosingMethodAttribute(int attributeNameIndex, int attributeLength, ClassReader reader) {
        super(attributeNameIndex, attributeLength);
        classIndex = reader.nextU2ToInt();
        methodIndex = reader.nextU2ToInt();
    }
}
