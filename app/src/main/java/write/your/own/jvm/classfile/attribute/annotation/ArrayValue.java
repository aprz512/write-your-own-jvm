package write.your.own.jvm.classfile.attribute.annotation;

import write.your.own.jvm.classfile.ClassReader;

public class ArrayValue extends ElementValue {
    public final int numValues;
    public final ElementValue[] values;

    public ArrayValue(int tag, ClassReader reader) {
        super(tag);
        numValues = reader.nextU2ToInt();
        values = ElementValue.readElementValues(reader, numValues);
    }
}
