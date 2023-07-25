package write.your.own.jvm.runtimedata.heap.constants;

import write.your.own.jvm.classfile.constantpool.ConstantMemberRefInfo;

public class MemberRef extends SymRef {

    protected final String name;
    protected final String descriptor;

    public MemberRef(ConstantPool constantPool, ConstantMemberRefInfo refInfo) {
        super(constantPool, refInfo.getClassName());
        String[] nameAndDescriptor = refInfo.getNameAndDescriptor();
        name = nameAndDescriptor[0];
        descriptor = nameAndDescriptor[1];
    }

    public String getName() {
        return name;
    }

    public String getDescriptor() {
        return descriptor;
    }


}
