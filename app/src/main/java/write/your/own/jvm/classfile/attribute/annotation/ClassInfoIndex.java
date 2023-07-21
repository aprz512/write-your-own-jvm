package write.your.own.jvm.classfile.attribute.annotation;

import write.your.own.jvm.classfile.ClassReader;

public class ClassInfoIndex extends ElementValue {
    public final int classInfoIndex;

    public ClassInfoIndex(int tag, ClassReader reader) {
        super(tag);
        classInfoIndex = reader.nextU2ToInt();
    }
}
