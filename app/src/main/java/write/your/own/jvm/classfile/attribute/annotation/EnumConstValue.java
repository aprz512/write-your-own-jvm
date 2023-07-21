package write.your.own.jvm.classfile.attribute.annotation;

import write.your.own.jvm.classfile.ClassReader;

public class EnumConstValue extends ElementValue {
    public final int typeNameIndex;
    public final int constNameIndex;

    public EnumConstValue(int tag, ClassReader reader) {
        super(tag);
        typeNameIndex = reader.nextU2ToInt();
        constNameIndex = reader.nextU2ToInt();
    }
}
