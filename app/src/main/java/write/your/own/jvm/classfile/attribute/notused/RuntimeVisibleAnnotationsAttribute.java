package write.your.own.jvm.classfile.attribute.notused;

import write.your.own.jvm.classfile.ClassReader;
import write.your.own.jvm.classfile.attribute.AttributeInfo;
import write.your.own.jvm.classfile.attribute.annotation.Annotation;

/**
 * RuntimeVisibleAnnotations_attribute {
 * u2         attribute_name_index;
 * u4         attribute_length;
 * u2         num_annotations;
 * annotation annotations[num_annotations];
 * }
 */
public class RuntimeVisibleAnnotationsAttribute extends AttributeInfo {
    public final int numAnnotations;
    public final Annotation[] annotations;

    public RuntimeVisibleAnnotationsAttribute(int attrNameIndex, int attrLength, ClassReader reader) {
        super(attrNameIndex, attrLength);
        numAnnotations = reader.nextU2ToInt();
        annotations = Annotation.readAnnotations(reader, numAnnotations);
    }
}
