package write.your.own.jvm.runtimedata.heap;

import com.google.common.annotations.VisibleForTesting;

public class MyObject {

    protected MyClass myClass;
    protected InstanceFields instanceFields;

    /**
     * extra字段用来记录Object结构体实例的额外信息
     * 对于一个 Class 的对象来说，比如 String.class，
     * myClass 字段就是 java.lang.Class
     * extra 字段应该代表的是它真是的类型 String.class，它是 java.lang.Class 的一个实例
     */
    protected Object extra;

    public MyObject(MyClass myClass) {
        this.myClass = myClass;
        this.instanceFields = new InstanceFields(myClass.getInstanceSlotCount());
    }

    @VisibleForTesting
    public MyObject() {
    }

    public MyClass getMyClass() {
        return myClass;
    }

    public InstanceFields getInstanceFields() {
        return instanceFields;
    }

    public Object getExtra() {
        return extra;
    }

    public void setExtra(Object extra) {
        this.extra = extra;
    }

    public boolean isInstanceOf(MyClass otherClass) {
        return otherClass.isAssignableFrom(myClass);
    }

    public void setRefFieldValue(String name, String descriptor, MyObject refValue) {
        MyField field = myClass.getField(name, descriptor, false);
        instanceFields.setRef(field.getSlotId(), refValue);
    }

    public void setIntFieldValue(String name, String descriptor, int value) {
        MyField field = myClass.getField(name, descriptor, false);
        instanceFields.setInt(field.getSlotId(), value);
    }

    public MyObject getRefFieldValue(String name, String descriptor) {
        MyField field = myClass.getField(name, descriptor, false);
        return instanceFields.getRef(field.getSlotId());
    }

    public int getIntFieldValue(String name, String descriptor) {
        MyField field = myClass.getField(name, descriptor, false);
        return instanceFields.getInt(field.getSlotId());
    }

    public MyObject cloneMyObject() {
        MyObject cloneObject = new MyObject();
        cloneObject.myClass = this.myClass;
        // clone local
        if (this.instanceFields != null) {
            cloneObject.instanceFields = this.instanceFields.cloneInstanceFields();
        }
        cloneObject.extra = this.extra;
        return cloneObject;
    }

}
