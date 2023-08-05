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
            myMethods[i] = createMethod(myClass, infos[i]);
        }
        return myMethods;
    }

    private static MyMethod createMethod(MyClass myClass, MemberInfo info) {
        MyMethod method = new MyMethod(myClass, info);
        method.copyAttributes(info);
        MethodDescriptorParser methodDescriptorParser = new MethodDescriptorParser();
        MethodDescriptor methodDescriptor = methodDescriptorParser.parse(method.getDescriptor());
        method.argsSlotCount = method.calArgsSlotCount(methodDescriptor);
        if (method.isNative()) {
            method.injectCodeAttribute(methodDescriptor.getReturnType());
        }
        return method;
    }

    private void injectCodeAttribute(String returnType) {
        this.maxStack = 4;
        this.maxLocals = argsSlotCount;
        switch (returnType.charAt(0)) {
            case 'V':
                this.code = new byte[]{(byte) 0xfe, (byte) 0xb1}; // return
                return;
            case 'D':
                this.code = new byte[]{(byte) 0xfe, (byte) 0xaf}; // dreturn
                return;
            case 'F':
                this.code = new byte[]{(byte) 0xfe, (byte) 0xae}; // freturn
                return;
            case 'J':
                this.code = new byte[]{(byte) 0xfe, (byte) 0xad}; // lreturn
                return;
            case 'L':
            case '[':
                this.code = new byte[]{(byte) 0xfe, (byte) 0xb0}; // areturn
                return;
            default:
                this.code = new byte[]{(byte) 0xfe, (byte) 0xac}; // ireturn
        }
    }

    private int calArgsSlotCount(MethodDescriptor methodDescriptor) {
        List<String> parameterTypes = methodDescriptor.getParameterTypes();
        int count = 0;
        for (String pType : parameterTypes) {
            count++;
            if ("J".equals(pType) || "D".equals(pType)) {
                count++;
            }
        }
        // 传递 this
        if (!isStatic()) {
            count++;
        }
        return count;
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
