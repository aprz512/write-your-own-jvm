package write.your.own.jvm.classfile.attribute.annotation;

import write.your.own.jvm.classfile.ClassReader;

public class ConstValueIndex extends ElementValue {
    public final int constValueIndex;

    public ConstValueIndex(int tag, ClassReader reader) {
        super(tag);
        constValueIndex = reader.nextU2ToInt();
    }
}
