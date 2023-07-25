package write.your.own.jvm.runtimedata.heap.constants;

import write.your.own.jvm.classfile.constantpool.ConstantMemberRefInfo;
import write.your.own.jvm.runtimedata.heap.MyMethod;

public class MethodRef extends MemberRef {

    private MyMethod myMethod;


    public MethodRef(ConstantPool constantPool, ConstantMemberRefInfo refInfo) {
        super(constantPool, refInfo);
    }

}
