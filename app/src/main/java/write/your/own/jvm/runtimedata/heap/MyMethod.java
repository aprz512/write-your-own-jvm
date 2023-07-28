package write.your.own.jvm.runtimedata.heap;

import write.your.own.jvm.classfile.MemberInfo;
import write.your.own.jvm.classfile.attribute.CodeAttribute;

import java.util.List;

public class MyMethod extends ClassMember {

    private int maxStack;
    private int maxLocals;
    private byte[] code;

    private int argsSlotCount;

    public MyMethod(MyClass myClass, MemberInfo info) {
        super(myClass, info);
    }

    public static MyMethod[] createMethods(MyClass myClass, MemberInfo[] infos) {
        MyMethod[] myMethods = new MyMethod[infos.length];
        for (int i = 0; i < infos.length; i++) {
            myMethods[i] = new MyMethod(myClass, infos[i]);
            myMethods[i].copyAttributes(infos[i]);
            myMethods[i].calArgsSlotCount();
        }
        return myMethods;
    }

    private void calArgsSlotCount() {
        MethodDescriptorParser methodDescriptorParser = new MethodDescriptorParser();
        MethodDescriptor methodDescriptor = methodDescriptorParser.parse(getDescriptor());
        List<String> parameterTypes = methodDescriptor.getParameterTypes();
        for (String pType : parameterTypes) {
            argsSlotCount++;
            if ("J".equals(pType) || "D".equals(pType)) {
                argsSlotCount++;
            }
        }
        // 传递 this
        if (!isStatic()) {
            argsSlotCount++;
        }
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

    public int getArgsSlotCount() {
        return argsSlotCount;
    }

}
