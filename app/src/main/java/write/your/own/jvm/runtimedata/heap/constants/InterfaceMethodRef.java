package write.your.own.jvm.runtimedata.heap.constants;

import write.your.own.jvm.classfile.constantpool.ConstantMemberRefInfo;
import write.your.own.jvm.exception.MyJvmException;
import write.your.own.jvm.runtimedata.heap.MyClass;
import write.your.own.jvm.runtimedata.heap.MyMethod;

public class InterfaceMethodRef extends MemberRef {

    private MyMethod myMethod;

    public InterfaceMethodRef(ConstantPool constantPool, ConstantMemberRefInfo refInfo) {
        super(constantPool, refInfo);
    }

    public MyMethod getResolvedInterfaceMethod() {
        if (myMethod == null) {
            myMethod = resolveInterfaceMethodRef();
        }

        return myMethod;
    }

    private MyMethod resolveInterfaceMethodRef() {
        MyClass currentClass = constantPool.getMyClass();
        MyClass resolvedClass = getResolvedClass();
        if (!resolvedClass.isInterface()) {
            throw new MyJvmException("java.lang.IncompatibleClassChangeError");
        }
        MyMethod currentMethod = lookupMethodInInterfaces(new MyClass[]{resolvedClass}, getName(), getDescriptor());
        if (currentMethod == null) {
            throw new MyJvmException("java.lang.NoSuchMethodError");
        }
        if (!currentMethod.isAccessibleTo(currentClass)) {
            throw new MyJvmException("java.lang.IllegalAccessError");
        }
        return currentMethod;
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
