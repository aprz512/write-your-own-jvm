package write.your.own.jvm.classfile.attribute.annotation;

import write.your.own.jvm.classfile.ClassReader;

/**
 * annotation {
 * u2 type_index;
 * u2 num_element_value_pairs;
 * {   u2            element_name_index;
 * element_value value;
 * } element_value_pairs[num_element_value_pairs];
 * }
 */
public class Annotation extends ElementValue {
    public final int typeIndex;
    public final int numElementValuePairs;

    public final ElementValuePair[] elementValuePairs;

    public Annotation(int tag, ClassReader reader) {
        super(tag);
        typeIndex = reader.nextU2ToInt();
        numElementValuePairs = reader.nextU2ToInt();
        elementValuePairs = ElementValuePair.readElementValuePairs(reader, numElementValuePairs);
    }

    public Annotation(ClassReader reader) {
        super(-1);
        typeIndex = reader.nextU2ToInt();
        numElementValuePairs = reader.nextU2ToInt();
        elementValuePairs = ElementValuePair.readElementValuePairs(reader, numElementValuePairs);
    }

    public static Annotation[] readAnnotations(ClassReader reader, int numAnnotations) {
        Annotation[] annotations = new Annotation[numAnnotations];
        for (int i = 0; i < numAnnotations; i++) {
            annotations[i] = new Annotation(reader);
        }
        return annotations;
    }
}
