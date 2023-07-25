package write.your.own.jvm.runtimedata.heap;

import write.your.own.jvm.classfile.MemberInfo;
import write.your.own.jvm.classfile.attribute.CodeAttribute;

public class MyMethod extends ClassMember {

    private int maxStack;
    private int maxLocals;
    private byte[] code;

    public MyMethod(MyClass myClass, MemberInfo info) {
        super(myClass, info);
    }

    public static MyMethod[] createMethods(MyClass myClass, MemberInfo[] infos) {
        MyMethod[] myMethods = new MyMethod[infos.length];
        for (int i = 0; i < infos.length; i++) {
            myMethods[i] = new MyMethod(myClass, infos[i]);
            myMethods[i].copyAttributes(infos[i]);
        }
        return myMethods;
    }

    private void copyAttributes(MemberInfo info) {
        CodeAttribute codeAttribute = info.getCodeAttribute();
        // native method
        if (codeAttribute != null) {
            this.code = codeAttribute.getCode();
            this.maxLocals = codeAttribute.getMaxLocals();
            this.maxStack = codeAttribute.getMaxStack();
        }
    }

    public int getMaxStack() {
        return maxStack;
    }

    public int getMaxLocals() {
        return maxLocals;
    }

    public byte[] getCode() {
        return code;
    }
}
