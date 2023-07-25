package write.your.own.jvm.runtimedata.heap;

import write.your.own.jvm.classfile.MemberInfo;

public class ClassMember {
    protected int accessFlag;
    protected String name;
    protected String descriptor;
    protected MyClass myClass;

    public ClassMember(MyClass myClass, MemberInfo info) {
        this.myClass = myClass;
        this.accessFlag = info.getAccessFlags();
        this.name = info.getName();
        this.descriptor = info.getDescriptor();
    }

    public boolean isPublic() {
        return (accessFlag & AccessFlag.ACC_PUBLIC) != 0;
    }

    public boolean isProtected() {
        return (accessFlag & AccessFlag.ACC_PROTECTED) != 0;
    }

    public boolean isPrivate() {
        return (accessFlag & AccessFlag.ACC_PRIVATE) != 0;
    }

    public boolean isFinal() {
        return (accessFlag & AccessFlag.ACC_FINAL) != 0;
    }

    public boolean isSuper() {
        return (accessFlag & AccessFlag.ACC_SUPER) != 0;
    }

    public boolean isInterface() {
        return (accessFlag & AccessFlag.ACC_INTERFACE) != 0;
    }

    public boolean isAbstract() {
        return (accessFlag & AccessFlag.ACC_ABSTRACT) != 0;
    }

    public boolean isSynthetic() {
        return (accessFlag & AccessFlag.ACC_SYNTHETIC) != 0;
    }

    public boolean isAnnotation() {
        return (accessFlag & AccessFlag.ACC_ANNOTATION) != 0;
    }

    public boolean isEnum() {
        return (accessFlag & AccessFlag.ACC_ENUM) != 0;
    }

    public boolean isStatic() {
        return (accessFlag & AccessFlag.ACC_STATIC) != 0;
    }

    public String getDescriptor() {
        return descriptor;
    }

    public String getName() {
        return name;
    }

    /**
     * 如果字段是public，则任何类都可以访问。
     * 如果字段是protected，则只有子类和同一个包下的类可以访问。
     * 如果字段有默认访问权限（非public，非protected，也非privated），则只有同一个包下的类可以访问。
     * 否则，字段是private，只有声明这个字段的类才能访问。
     */
    public boolean isAccessibleTo(MyClass ref) {
        if (isPublic()) {
            return true;
        }

        if (isProtected()) {
            return ref == this.myClass
                    || ref.isSubClassOf(myClass)
                    || ref.getPackageName().equals(myClass.getPackageName());
        }

        if (!isPrivate()) {
            return ref.getPackageName().equals(myClass.getPackageName());
        }

        return ref == myClass;
    }

    public MyClass getMyClass() {
        return myClass;
    }
}
