package write.your.own.jvm.runtimedata.heap;

import write.your.own.jvm.classfile.MemberInfo;
import write.your.own.jvm.classfile.attribute.CodeAttribute;
import write.your.own.jvm.classfile.attribute.LineNumberTableAttribute;

import java.util.List;

public class MyMethod extends ClassMember {

    private int maxStack;
    private int maxLocals;
    private byte[] code;

    private int argsSlotCount;

    private ExceptionTable exceptionTable;
    private LineNumberTableAttribute.LineNumberTable[] lineNumberTable;

    private MyMethod() {
    }

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

    public static MyMethod createShimMethod(byte[] methodCode, String methodName, int maxStack) {
        MyMethod method = new MyMethod();
        method.code = methodCode;
        method.name = methodName;
        method.myClass = MyClass.createShimClass();
        method.accessFlag = AccessFlag.ACC_PUBLIC;
        method.maxStack = maxStack;
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
            this.exceptionTable = ExceptionTable.newExceptionTable(codeAttribute.getExceptionTable(), getMyClass().getConstantPool());
            LineNumberTableAttribute lineNumberTableAttribute = codeAttribute.getLineNumberTableAttribute();
            if (lineNumberTableAttribute != null) {
                this.lineNumberTable = lineNumberTableAttribute.getLineNumberTable();
            }
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

    public int findExceptionHandler(MyClass exceptionClass, int pc) {
        ExceptionTable.ExceptionHandler exceptionHandler = this.exceptionTable.findExceptionHandler(exceptionClass, pc);
        if (exceptionHandler != null) {
            return exceptionHandler.handlerPc;
        }
        return -1;
    }

    public int getLineNumber(int pc) {
        for (int i = lineNumberTable.length - 1; i >= 0; i--) {
            LineNumberTableAttribute.LineNumberTable table = lineNumberTable[i];
            if (pc > table.startPc) {
                return table.lineNumber;
            }
        }

        return -1;
    }
}
