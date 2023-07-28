package write.your.own.jvm.runtimedata.heap.constants;

import write.your.own.jvm.classfile.constantpool.ConstantMemberRefInfo;
import write.your.own.jvm.runtimedata.heap.MyClass;
import write.your.own.jvm.runtimedata.heap.MyField;

public class FieldRef extends MemberRef {

    private MyField myField;

    public FieldRef(ConstantPool constantPool, ConstantMemberRefInfo refInfo) {
        super(constantPool, refInfo);
    }

    public MyField getResolvedField() {
        if (myField == null) {
            myField = resolveFieldRef();
        }
        return myField;
    }

    private MyField resolveFieldRef() {
        MyClass current = constantPool.getMyClass();
        MyClass refClass = getResolvedClass();
        MyField field = lookupField(refClass, name, descriptor);
        if (field == null) {
            throw new RuntimeException("java.lang.NoSuchFieldError");
        }
        if (!field.isAccessibleTo(current)) {
            throw new RuntimeException("java.lang.IllegalAccessError");
        }
        return field;
    }

    /**
     * 首先在C的字段中查找
     * 如果找不到，在C的直接接口递归应用这个查找过程
     * 如果还找不到的话，在C的超类中递归应用这个查找过程
     * 如果仍然找不到，则查找失败
     */
    private MyField lookupField(MyClass refClass, String name, String descriptor) {
        MyField[] fields = refClass.getFields();
        for (MyField field : fields) {
            if (field.getDescriptor().equals(descriptor) && field.getName().equals(name)) {
                return field;
            }
        }

        MyClass[] interfaces = refClass.getInterfaces();
        for (MyClass inter : interfaces) {
            MyField interField = lookupField(inter, name, descriptor);
            if (interField != null) {
                return interField;
            }
        }

        MyClass superClass = refClass.getSuperClass();
        if (superClass != null) {
            return lookupField(superClass, name, descriptor);
        }

        return null;
    }


}
