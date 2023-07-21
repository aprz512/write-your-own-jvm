package write.your.own.jvm.classfile.attribute.annotation;

import write.your.own.jvm.classfile.ClassReader;

/**
 * {   u2            element_name_index;
 * element_value value;
 * } element_value_pairs[num_element_value_pairs];
 */
public class ElementValuePair {
    public final int elementNameIndex;

    public ElementValuePair(ClassReader reader) {
        elementNameIndex = reader.nextU2ToInt();
    }

    public static ElementValuePair[] readElementValuePairs(ClassReader reader, int numElementValuePairs) {
        ElementValuePair[] elementValuePairs = new ElementValuePair[numElementValuePairs];
        for (int i = 0; i < numElementValuePairs; i++) {
            elementValuePairs[i] = new ElementValuePair(reader);
        }
        return elementValuePairs;
    }
}
