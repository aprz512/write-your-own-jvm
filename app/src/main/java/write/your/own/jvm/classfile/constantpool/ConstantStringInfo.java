package write.your.own.jvm.classfile.constantpool;

import write.your.own.jvm.classfile.ClassReader;

public class ConstantStringInfo implements ConstantInfo {

    private final ConstantPool constPool;
    private final int nameIndex;

    public ConstantStringInfo(ConstantPool aConstPool, ClassReader reader) {
        this.nameIndex = reader.nextU2ToInt();
        this.constPool = aConstPool;
    }

    @Override
    public String getValue() {
        return constPool.getUTF8(nameIndex);
    }

    @Override
    public String toString() {
        return "ConstStringInfo{" +
                "name=" + constPool.getUTF8(nameIndex) +
                '}';
    }
}
