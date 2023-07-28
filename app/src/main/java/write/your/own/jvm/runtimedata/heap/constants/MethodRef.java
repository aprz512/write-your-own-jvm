package write.your.own.jvm.runtimedata.heap.constants;

import write.your.own.jvm.classfile.constantpool.ConstantMemberRefInfo;
import write.your.own.jvm.exception.MyJvmException;
import write.your.own.jvm.runtimedata.heap.MyClass;
import write.your.own.jvm.runtimedata.heap.MyMethod;

public class MethodRef extends MemberRef {

    private MyMethod myMethod;


    public MethodRef(ConstantPool constantPool, ConstantMemberRefInfo refInfo) {
        super(constantPool, refInfo);
    }

    public static MyMethod lookupMethodInClass(MyClass currentClass, String name, String descriptor) {
        while (currentClass != null) {
            MyMethod[] methods = currentClass.getMethods();
            for (MyMethod method : methods) {
                if (method.getName().equals(name) && method.getDescriptor().equals(descriptor)) {
                    return method;
                }
            }
            currentClass = currentClass.getSuperClass();
        }

        return null;
    }

    public MyMethod getResolvedMethod() {
        if (myMethod == null) {
            myMethod = resolveMethodRef();
        }

        return myMethod;
    }

    private MyMethod resolveMethodRef() {
        MyClass currentClass = constantPool.getMyClass();
        MyClass resolvedClass = getResolvedClass();
        // 暂不支持接口的静态方法和默认方法
        if (resolvedClass.isInterface()) {
            throw new MyJvmException("java.lang.IncompatibleClassChangeError");
        }
        MyMethod currentMethod = lookupMethod(resolvedClass, getName(), getDescriptor());
        if (currentMethod == null) {
            throw new MyJvmException("java.lang.NoSuchMethodError");
        }
        if (!currentMethod.isAccessibleTo(currentClass)) {
            throw new MyJvmException("java.lang.IllegalAccessError");
        }
        return currentMethod;
    }

    private MyMethod lookupMethod(MyClass currentClass, String name, String descriptor) {
        MyMethod lookupMethod = lookupMethodInClass(currentClass, name, descriptor);
        if (lookupMethod == null) {
            lookupMethod = lookupMethodInInterfaces(currentClass.getInterfaces(), name, descriptor);
        }
        return lookupMethod;
    }

    private MyMethod lookupMethodInInterfaces(MyClass[] interfaces, String name, String descriptor) {
        for (MyClass inter : interfaces) {
            for (MyMethod method : inter.getMethods()) {
                if (method.getName().equals(name) && method.getDescriptor().equals(descriptor)) {
                    return method;
                }
            }
            MyMethod method = lookupMethodInInterfaces(inter.getInterfaces(), name, descriptor);
            if (method != null) {
                return method;
            }
        }
        return null;
    }
}
