package write.your.own.jvm.classfile.attribute.annotation;

import write.your.own.jvm.classfile.ClassReader;
import write.your.own.jvm.exception.NotImplementedException;

public class ElementValueFactory {

    public static ElementValue newInstance(ClassReader reader, int tag) {
        Integer tagInteger = tag;
        if (Integer.valueOf('B').equals(tagInteger)
                || Integer.valueOf('C').equals(tagInteger)
                || Integer.valueOf('D').equals(tagInteger)
                || Integer.valueOf('F').equals(tagInteger)
                || Integer.valueOf('I').equals(tagInteger)
                || Integer.valueOf('J').equals(tagInteger)
                || Integer.valueOf('S').equals(tagInteger)
                || Integer.valueOf('Z').equals(tagInteger)
                || Integer.valueOf('s').equals(tagInteger)) {
            return new ConstValueIndex(tag, reader);
        } else if (Integer.valueOf('e').equals(tagInteger)) {
            return new EnumConstValue(tag, reader);
        } else if (Integer.valueOf('c').equals(tagInteger)) {
            return new ClassInfoIndex(tag, reader);
        } else if (Integer.valueOf('@').equals(tagInteger)) {
            return new Annotation(tag, reader);
        } else if (Integer.valueOf('[').equals(tagInteger)) {
            return new ArrayValue(tag, reader);
        } else {
            throw new NotImplementedException();
        }
    }

}
