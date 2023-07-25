package write.your.own.jvm.runtimedata.heap.constants;

import write.your.own.jvm.runtimedata.heap.MyClass;

public class SymRef {

    /**
     * 字段存放符号引用所在的运行时常量池指针，
     * 这样就可以通过符号引用访问到运行时常量池，
     * 进一步又可以访问到类数据。
     */
    protected ConstantPool constantPool;

    /**
     * 存放类的完全限定名
     */
    protected String className;

    /**
     * class字段缓存解析后的类结构体指针，这样类符号引用只需要解析一次就可以了，
     * 后续可以直接使用缓存值。
     */
    protected MyClass myClass;

    public SymRef(ConstantPool constantPool, String className) {
        this.constantPool = constantPool;
        this.className = className;
    }

    public MyClass resolvedClass() {
        if (this.myClass == null) {
            myClass = resolveClassRef();
        }
        return myClass;
    }

    /**
     * 解析常量池里面的类引用
     */
    private MyClass resolveClassRef() {
        MyClass mc = constantPool.getMyClass();
        MyClass myClassRef = mc.getClassLoader().loadClass(className);
        if (!myClassRef.isAccessibleTo(mc)) {
            throw new RuntimeException("类[" + mc + "]无法访问到类[" + myClassRef + "]");
        }
        return myClassRef;
    }
}
