package write.your.own.jvm.runtimedata.heap.constants;

import write.your.own.jvm.classfile.constantpool.ConstantClassInfo;

public class ClassRef extends SymRef {

    public ClassRef(ConstantPool constantPool, ConstantClassInfo classInfo) {
        super(constantPool, classInfo.getName());
    }


}
