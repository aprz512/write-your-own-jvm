package write.your.own.jvm.runtimedata.heap;

import com.google.common.annotations.VisibleForTesting;

import java.util.Arrays;

public class MyObject {

    private MyClass myClass;
    private InstanceFields instanceFields;

    private Object[] array;

    /**
     * extra字段用来记录Object结构体实例的额外信息
     */
    private Object extra;

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

    public MyObject getRefFieldValue(String name, String descriptor) {
        MyField field = myClass.getField(name, descriptor, false);
        return instanceFields.getRef(field.getSlotId());
    }

    public MyObject cloneMyObject() {
        MyObject cloneObject = new MyObject();
        cloneObject.myClass = this.myClass;
        // clone local
        cloneObject.instanceFields = cloneObject.instanceFields.cloneInstanceFields();
        // clone array
        cloneObject.array = Arrays.copyOf(this.array, this.array.length);
        cloneObject.extra = this.extra;

        return cloneObject;
    }

}
