package write.your.own.jvm.runtimedata.heap;

import write.your.own.jvm.Cmd;
import write.your.own.jvm.classfile.ClassFile;
import write.your.own.jvm.classpath.Classpath;
import write.your.own.jvm.exception.NotImplementedException;
import write.your.own.jvm.runtimedata.LocalVariableTable;
import write.your.own.jvm.runtimedata.MyString;
import write.your.own.jvm.runtimedata.PrimitiveType;
import write.your.own.jvm.runtimedata.heap.constants.ConstantPool;
import write.your.own.jvm.util.Log;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MyClassLoader {
    private final Classpath classpath;
    private final HashMap<String, MyClass> loadedClasses = new HashMap<>();

    public MyClassLoader(Classpath classpath) {
        this.classpath = classpath;

        loadBasicClasses();
        loadPrimitiveClasses();
    }

    private void loadPrimitiveClasses() {
        PrimitiveType.primitiveTypes.forEach((key, value) -> {
            loadPrimitiveClass(key);
        });
    }

    public MyObject createClassObject() {
        MyClass classValue = loadedClasses.get("java/lang/Class");
        if (classValue != null) {
            return classValue.newObject();
        }
        return null;
    }

    /**
     * 每个基本类型都有一个包装类，包装类中有一个静态常量，叫作TYPE，其中存放的就是基本类型的类。
     */
    private void loadPrimitiveClass(String primitiveClassName) {
        MyClass myClass = MyClass.createPrimitiveClass(primitiveClassName, this);
        myClass.setJClass(createClassObject());
        myClass.getJClass().setExtra(myClass);
        loadedClasses.put(primitiveClassName, myClass);
    }

    private void loadBasicClasses() {
        // 先 load object，会跳过 jclass 的设置
        // 再 load class，遍历集合再设置
        MyClass objectClass = loadClass("java/lang/Object");
        MyClass classClass = loadClass("java/lang/Class");
        Set<Map.Entry<String, MyClass>> entries = loadedClasses.entrySet();
        entries.forEach(entry -> {
                    MyClass value = entry.getValue();
                    if (value.getJClass() == null) {
                        MyObject classObject = classClass.newObject();
                        classObject.setExtra(value);
                        value.setJClass(classObject);
                    }
                }
        );
    }

    public MyClass loadClass(String name) {
        MyClass loaded = loadedClasses.get(name);
        if (loaded != null) {
            return loaded;
        }

        MyClass loadedClass;

        if (name.charAt(0) == '[') {
            loadedClass = loadArrayClass(name);
        } else {
            loadedClass = loadNonArrayClass(name);
        }

        MyClass classValue = loadedClasses.get("java/lang/Class");
        if (classValue != null) {
            MyObject classObject = classValue.newObject();
            classObject.setExtra(loadedClass);
            loadedClass.setJClass(classObject);
        }
        return loadedClass;
    }

    private MyClass loadArrayClass(String name) {
        MyClass myClass = MyClass.createArrayClass(name, this);
        loadedClasses.put(name, myClass);
        return myClass;
    }

    /**
     * 数组类和普通类有很大的不同，它的数据并不是来自class文件，而是由Java虚拟机在运行期间生成。
     */
    private MyClass loadNonArrayClass(String name) {
        byte[] data = readClass(name);
        MyClass myClass = defineClass(data, name);
        link(myClass);
        if (Cmd.Config.verboseClassFlag) {
            Log.d("Load class: " + name);
        }
        loadedClasses.put(name, myClass);
        return myClass;
    }

    private void link(MyClass myClass) {
        verify(myClass);
        prepare(myClass);
    }

    private void verify(MyClass myClass) {

    }

    private void prepare(MyClass myClass) {
        calcInstanceFieldSlotIds(myClass);
        calcStaticFieldSlotIds(myClass);
        allocAndInitStaticVars(myClass);
    }

    private void allocAndInitStaticVars(MyClass myClass) {
        myClass.setStaticVars(new StaticFields(myClass.getStaticSlotCount()));
        MyField[] fields = myClass.getFields();
        for (MyField field : fields) {
            if (field.isStatic() && field.isFinal()) {
                initStaticFinalVar(myClass, field);
            }
        }
    }

    private void initStaticFinalVar(MyClass myClass, MyField field) {
        LocalVariableTable staticVars = myClass.getStaticVars();
        ConstantPool constantPool = myClass.getConstantPool();
        int constValueIndex = field.getConstValueIndex();
        ConstantPool.Constant constant = constantPool.getConstant(constValueIndex);
        int slotId = field.getSlotId();
        if (constValueIndex > 0) {
            switch (field.getDescriptor()) {
                case "Z":
                case "B":
                case "C":
                case "S":
                case "I":
                    staticVars.setInt(slotId, (Integer) constant.value);
                    break;
                case "J":
                    staticVars.setLong(slotId, (Long) constant.value);
                    break;
                case "F":
                    staticVars.setFloat(slotId, (Float) constant.value);
                    break;
                case "D":
                    staticVars.setDouble(slotId, (Double) constant.value);
                    break;
                case "Ljava/lang/String;":
                    MyObject stringObject = MyString.create((String) constant.value, this);
                    staticVars.setRef(slotId, stringObject);
                    break;
                default:
                    throw new NotImplementedException();
            }
        }
    }

    private void calcStaticFieldSlotIds(MyClass myClass) {
        MyField[] fields = myClass.getFields();
        int slotId = 0;
        for (MyField field : fields) {
            if (field.isStatic()) {
                field.setSlotId(slotId);
                slotId++;
                if (field.isLongOrDouble()) {
                    slotId++;
                }
            }
        }
        myClass.setStaticSlotCount(slotId);
    }

    private void calcInstanceFieldSlotIds(MyClass myClass) {
        MyClass superClass = myClass.getSuperClass();
        int superSlotCount = superClass == null ? 0 : superClass.getInstanceSlotCount();
        MyField[] fields = myClass.getFields();
        int slotId = superSlotCount;
        for (MyField field : fields) {
            if (!field.isStatic()) {
                field.setSlotId(slotId);
                slotId++;
                if (field.isLongOrDouble()) {
                    slotId++;
                }
            }
        }
        myClass.setInstanceSlotCount(slotId);
    }


    private MyClass defineClass(byte[] data, String name) {
//        Log.e("defineClass:" + name);
        MyClass myClass = parseClass(data);
        myClass.setClassLoader(this);
        resolveSuperClassLoader(myClass);
        resolveInterfaces(myClass);
        return myClass;
    }

    private void resolveInterfaces(MyClass myClass) {
        String[] interfaceNames = myClass.getInterfaceNames();
        MyClass[] interfaces = new MyClass[interfaceNames.length];
        for (int i = 0; i < interfaceNames.length; i++) {
            interfaces[i] = myClass.getClassLoader().loadClass(interfaceNames[i]);
        }
        myClass.setInterfaces(interfaces);
    }

    /**
     * load super class
     */
    private void resolveSuperClassLoader(MyClass myClass) {
        if (!"java/lang/Object".equals(myClass.getThisClassName())) {
            myClass.setSuperClass(myClass.getClassLoader().loadClass(myClass.getSuperClassName()));
        }
    }

    private MyClass parseClass(byte[] data) {
        return new MyClass(new ClassFile(data));
    }

    public byte[] readClass(String name) {
        try {
            return classpath.readClass(name);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
