package write.your.own.jvm.runtimedata.heap;

import write.your.own.jvm.classfile.ClassFile;
import write.your.own.jvm.exception.MyJvmException;
import write.your.own.jvm.runtimedata.LocalVariableTable;
import write.your.own.jvm.runtimedata.heap.constants.ConstantPool;

public class MyClass {

    private int accessFlag;
    private String thisClassName;
    private String superClassName;
    private String[] interfaceNames;
    private ConstantPool constantPool;
    private MyField[] fields;
    private MyMethod[] methods;
    private MyClassLoader classLoader;
    private MyClass superClass;
    private MyClass[] interfaces;

    private int instanceSlotCount;
    private int staticSlotCount;
    /**
     * 刚好可以用来储存静态变量（static final 的）
     */
    private LocalVariableTable staticVars;

    /**
     * 类的初始化逻辑控制
     * 由于我们的类加载器还不够完善，所以先使用一个简单的布尔状态就足够了
     */
    private boolean initStarted;

    public MyClass(ClassFile classFile) {
        accessFlag = classFile.getAccessFlag();
        thisClassName = classFile.getThisClassName();
        superClassName = classFile.getSuperClassName();
        interfaceNames = classFile.getInterfaceNames();
        constantPool = newConstantPool(classFile);
        fields = newFields(classFile);
        methods = newMethods(classFile);
    }

    public boolean isInitStarted() {
        return initStarted;
    }

    public void setInitStarted(boolean initStarted) {
        this.initStarted = initStarted;
    }

    private MyMethod[] newMethods(ClassFile classFile) {
        return MyMethod.createMethods(this, classFile.getMethods());
    }

    private MyField[] newFields(ClassFile classFile) {
        return MyField.createFields(this, classFile.getFields());
    }

    private ConstantPool newConstantPool(ClassFile classFile) {
        return new ConstantPool(classFile, this);
    }

    public boolean isPublic() {
        return (accessFlag & AccessFlag.ACC_PUBLIC) != 0;
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

    public String getThisClassName() {
        return thisClassName;
    }

    public MyClass getSuperClass() {
        return superClass;
    }

    public void setSuperClass(MyClass superClass) {
        this.superClass = superClass;
    }

    public String getSuperClassName() {
        return superClassName;
    }

    public int getAccessFlag() {
        return accessFlag;
    }

    public String[] getInterfaceNames() {
        return interfaceNames;
    }

    public ConstantPool getConstantPool() {
        return constantPool;
    }

    public MyField[] getFields() {
        return fields;
    }

    public MyMethod[] getMethods() {
        return methods;
    }

    public MyClassLoader getClassLoader() {
        return classLoader;
    }

    public void setClassLoader(MyClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    public MyClass[] getInterfaces() {
        return interfaces;
    }

    public void setInterfaces(MyClass[] interfaces) {
        this.interfaces = interfaces;
    }

    public int getInstanceSlotCount() {
        return instanceSlotCount;
    }

    public void setInstanceSlotCount(int instanceSlotCount) {
        this.instanceSlotCount = instanceSlotCount;
    }

    public int getStaticSlotCount() {
        return staticSlotCount;
    }

    public void setStaticSlotCount(int staticSlotCount) {
        this.staticSlotCount = staticSlotCount;
    }

    public LocalVariableTable getStaticVars() {
        return staticVars;
    }

    public void setStaticVars(LocalVariableTable staticVars) {
        this.staticVars = staticVars;
    }

    public String getPackageName() {
        int i = thisClassName.lastIndexOf("/");
        if (i > 0) {
            return thisClassName.substring(0, i);
        }
        return "";
    }

    public boolean isAccessibleTo(MyClass mc) {
        return this.isPublic() || this.getPackageName().equals(mc.getPackageName());
    }

    public boolean isSubClassOf(MyClass myClass) {
        MyClass superClass = this;
        do {
            superClass = superClass.getSuperClass();
            if (superClass == myClass) {
                return true;
            }
        } while (superClass != null);

        return false;
    }

    public boolean isSuperClassOf(MyClass myClass) {
        return myClass.isSubClassOf(this);
    }

    public MyObject newObject() {
        return new MyObject(this);
    }

    public boolean instanceOf(MyClass refClass) {
        if (this == refClass) {
            return true;
        }
        MyClass[] interfaces = this.getInterfaces();
        for (MyClass inter : interfaces) {
            if (inter == refClass) {
                return true;
            }
        }
        MyClass superClass = this.getSuperClass();
        if (superClass == null) {
            return false;
        }
        return superClass.instanceOf(refClass);
    }

    public MyMethod geMainMethod() {
        return getStaticMethod("main", "([Ljava/lang/String;)V");
    }

    public MyMethod getStaticMethod(String name, String descriptor) {
        for (MyMethod method : methods) {
            if (method.isStatic() && method.getName().equals(name)
                    && method.getDescriptor().equals(descriptor)) {
                return method;
            }
        }
        throw new MyJvmException("no such method: " + name + descriptor);
    }

    public boolean isImplement(MyClass interfaceClass) {
        MyClass currentClass = this;
        while (currentClass != null) {
            MyClass[] inters = currentClass.getInterfaces();
            for (MyClass inter : inters) {
                if (inter == interfaceClass || inter.isSubInterfaceOf(interfaceClass)) {
                    return true;
                }
            }
            currentClass = currentClass.getSuperClass();
        }

        return false;
    }

    public boolean isSubInterfaceOf(MyClass interfaceClass) {
        MyClass[] inters = this.getInterfaces();
        for (MyClass inter : inters) {
            if (inter == interfaceClass || inter.isSubInterfaceOf(interfaceClass)) {
                return true;
            }
        }

        return false;
    }

    public MyField getField(String name, String descriptor, boolean isStatic) {
        MyClass currentClass = this;
        while (currentClass != null) {
            MyField[] fields = currentClass.getFields();
            for (MyField field : fields) {
                if (field.isSame(name, descriptor) && field.isStatic() == isStatic) {
                    return field;
                }
            }
            currentClass = currentClass.getSuperClass();
        }

        return null;
    }

    public MyMethod getMethod(String name, String descriptor, boolean isStatic) {
        MyClass currentClass = this;
        while (currentClass != null) {
            MyMethod[] myMethods = currentClass.getMethods();
            for (MyMethod method : myMethods) {
                if (method.isSame(name, descriptor) && method.isStatic() == isStatic) {
                    return method;
                }
            }
            currentClass = currentClass.getSuperClass();
        }

        return null;
    }


}
