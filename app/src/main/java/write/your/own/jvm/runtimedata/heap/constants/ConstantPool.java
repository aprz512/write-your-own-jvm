package write.your.own.jvm.runtimedata.heap.constants;

import write.your.own.jvm.classfile.ClassFile;
import write.your.own.jvm.classfile.constantpool.*;
import write.your.own.jvm.exception.NotImplementedException;
import write.your.own.jvm.runtimedata.heap.MyClass;

/**
 * 运行时常量池
 */
public class ConstantPool {
    private final MyClass myClass;
    private final Constant[] constants;

    public ConstantPool(ClassFile file, MyClass myClass) {
        this.myClass = myClass;
        constants = createConstantPool(file);
    }

    private Constant[] createConstantPool(ClassFile file) {
        ConstantInfo[] constantInfos = file.getConstantPool().getConstantInfos();
        Constant[] constants = new Constant[constantInfos.length];
        for (int i = 1; i < constants.length; i++) {
            ConstantInfo info = constantInfos[i];
            constants[i] = createConstant(info.getTag(), info);
            if (info.getTag() == ConstantInfo.CONST_TAG_DOUBLE ||
                    info.getTag() == ConstantInfo.CONST_TAG_LONG) {
                i++;
            }
        }
        return constants;
    }

    private Constant createConstant(int tag, ConstantInfo info) {
        switch (tag) {
            case ConstantInfo.CONST_TAG_DOUBLE:
                ConstantDoubleInfo doubleInfo = (ConstantDoubleInfo) info;
                return new Constant(doubleInfo.getDoubleValue(), tag);
            case ConstantInfo.CONST_TAG_INTEGER:
                ConstantIntegerInfo integerInfo = (ConstantIntegerInfo) info;
                return new Constant(integerInfo.getIntValue(), tag);
            case ConstantInfo.CONST_TAG_FLOAT:
                ConstantFloatInfo floatInfo = (ConstantFloatInfo) info;
                return new Constant(floatInfo.getFloatValue(), tag);
            case ConstantInfo.CONST_TAG_LONG:
                ConstantLongInfo longInfo = (ConstantLongInfo) info;
                return new Constant(longInfo.getLongValue(), tag);
            case ConstantInfo.CONST_TAG_STRING:
                ConstantStringInfo stringInfo = (ConstantStringInfo) info;
                return new Constant(stringInfo.getString(), tag);
            case ConstantInfo.CONST_TAG_CLASS:
                ConstantClassInfo classInfo = (ConstantClassInfo) info;
                return new Constant(new ClassRef(this, classInfo), tag);
            case ConstantInfo.CONST_TAG_FIELD_REF:
                ConstantMemberRefInfo fieldRefInfo = (ConstantMemberRefInfo) info;
                return new Constant(new FieldRef(this, fieldRefInfo), tag);
            case ConstantInfo.CONST_TAG_METHOD_REF:
                ConstantMemberRefInfo methodRefInfo = (ConstantMemberRefInfo) info;
                return new Constant(new MethodRef(this, methodRefInfo), tag);
            case ConstantInfo.CONST_TAG_INTERFACE_METHOD_REF:
                ConstantMemberRefInfo interfaceMethodRefInfo = (ConstantMemberRefInfo) info;
                return new Constant(new InterfaceMethodRef(this, interfaceMethodRefInfo), tag);
            default:
                // todo
                return new Constant(new Object(), tag);
        }
    }

    public Constant getConstant(int index) {
        return constants[index];
    }

    public MyClass getMyClass() {
        return myClass;
    }

    public static class Constant {
        public Object value;
        public int tag;

        public Constant(Object value, int tag) {
            this.value = value;
            this.tag = tag;
        }
    }
}
