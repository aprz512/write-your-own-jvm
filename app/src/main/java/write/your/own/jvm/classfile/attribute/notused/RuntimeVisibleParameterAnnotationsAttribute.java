package write.your.own.jvm.classfile.attribute.notused;

import write.your.own.jvm.classfile.ClassReader;
import write.your.own.jvm.classfile.attribute.AttributeInfo;
import write.your.own.jvm.classfile.attribute.annotation.Annotation;

/**
 * RuntimeVisibleParameterAnnotations_attribute {
 * u2 attribute_name_index;
 * u4 attribute_length;
 * u1 num_parameters;
 * {   u2         num_annotations;
 * annotation annotations[num_annotations];
 * } parameter_annotations[num_parameters];
 * }
 */
public class RuntimeVisibleParameterAnnotationsAttribute extends AttributeInfo {

    public final int numParameters;
    public final ParameterAnnotation[] parameterAnnotations;

    public RuntimeVisibleParameterAnnotationsAttribute(int attributeNameIndex, int attributeLength, ClassReader reader) {
        super(attributeNameIndex, attributeLength);
        numParameters = reader.nextU1toInt();
        parameterAnnotations = ParameterAnnotation.readParameterAnnotations(reader, numParameters);
    }

    public static class ParameterAnnotation {
        public final int numAnnotations;
        public final Annotation[] annotations;

        public ParameterAnnotation(ClassReader reader) {
            numAnnotations = reader.nextU2ToInt();
            annotations = Annotation.readAnnotations(reader, numAnnotations);
        }

        static ParameterAnnotation[] readParameterAnnotations(ClassReader reader, int length) {
            ParameterAnnotation[] parameterAnnotations = new ParameterAnnotation[length];
            for (int i = 0; i < length; i++) {
                parameterAnnotations[i] = new ParameterAnnotation(reader);
            }
            return parameterAnnotations;
        }
    }

}
