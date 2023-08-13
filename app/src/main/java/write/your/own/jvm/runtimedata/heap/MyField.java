package write.your.own.jvm.runtimedata.heap;

import write.your.own.jvm.classfile.MemberInfo;
import write.your.own.jvm.classfile.attribute.ConstantValueAttribute;
import write.your.own.jvm.instruction.reference.ClassNameHelper;

public class MyField extends ClassMember {
    /**
     * 只有 static final 修饰的 field 才会有值
     */
    private int constValueIndex;
    private int slotId;

    public MyField(MyClass myClass, MemberInfo info) {
        super(myClass, info);
        ConstantValueAttribute constantValueAttribute = info.getConstantValueAttribute();
        if (constantValueAttribute != null) {
            constValueIndex = constantValueAttribute.getConstantValueIndex();
        }
    }

    public static MyField[] createFields(MyClass myClass, MemberInfo[] infos) {
        MyField[] myFields = new MyField[infos.length];
        for (int i = 0; i < infos.length; i++) {
            myFields[i] = new MyField(myClass, infos[i]);
        }
        return myFields;
    }

    public int getConstValueIndex() {
        return constValueIndex;
    }

    public int getSlotId() {
        return slotId;
    }

    public void setSlotId(int slotId) {
        this.slotId = slotId;
    }

    public boolean isLongOrDouble() {
        return "J".equals(descriptor) || "D".equals(descriptor);
    }

    public MyClass getType() {
        String className = ClassNameHelper.toClassName(this.descriptor);
        return this.getMyClass().getClassLoader().loadClass(className);
    }
}
