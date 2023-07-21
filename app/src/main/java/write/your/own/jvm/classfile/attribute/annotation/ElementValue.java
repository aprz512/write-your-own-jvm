package write.your.own.jvm.classfile.attribute.annotation;

import write.your.own.jvm.classfile.ClassReader;

/**
 * element_value {
 * u1 tag;
 * union {
 * u2 const_value_index;
 * <p>
 * {   u2 type_name_index;
 * u2 const_name_index;
 * } enum_const_value;
 * <p>
 * u2 class_info_index;
 * <p>
 * annotation annotation_value;
 * <p>
 * {   u2            num_values;
 * element_value values[num_values];
 * } array_value;
 * } value;
 * }
 */
public abstract class ElementValue {

    public final int tag;

    public ElementValue(int tag) {
        this.tag = tag;
    }

    public static ElementValue[] readElementValues(ClassReader reader, int elementValueTableLength) {
        ElementValue[] elementValues = new ElementValue[elementValueTableLength];
        int tag = reader.nextU1toInt();
        for (int i = 0; i < elementValueTableLength; i++) {
            elementValues[i] = ElementValueFactory.newInstance(reader, tag);
        }
        return elementValues;
    }

}
