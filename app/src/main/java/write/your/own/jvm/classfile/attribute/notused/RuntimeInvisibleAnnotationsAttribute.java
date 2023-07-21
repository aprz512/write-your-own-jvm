package write.your.own.jvm.classfile.attribute.notused;

import write.your.own.jvm.classfile.ClassReader;
import write.your.own.jvm.classfile.attribute.AttributeInfo;
import write.your.own.jvm.classfile.attribute.annotation.Annotation;

/**
 * RuntimeInvisibleAnnotations_attribute {
 * u2         attribute_name_index;
 * u4         attribute_length;
 * u2         num_annotations;
 * annotation annotations[num_annotations];
 * }
 */
public class RuntimeInvisibleAnnotationsAttribute extends AttributeInfo {
    public final int numAnnotations;
    public final Annotation[] annotations;

    public RuntimeInvisibleAnnotationsAttribute(int attributeNameIndex, int attributeLength, ClassReader reader) {
        super(attributeNameIndex, attributeLength);
        numAnnotations = reader.nextU2ToInt();
        annotations = Annotation.readAnnotations(reader, numAnnotations);
    }
}
