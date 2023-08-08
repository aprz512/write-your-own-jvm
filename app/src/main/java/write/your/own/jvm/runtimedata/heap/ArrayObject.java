package write.your.own.jvm.runtimedata.heap;

import java.util.Arrays;

public class ArrayObject extends MyObject {

    // 用 Object[] 有点浪费，因为 int[] 也可以用 Object 接受
    // 懒得改了
    // this type should be MyObject[]
    private Object[] array;

    public ArrayObject(MyClass myClass, int count) {
        super(myClass);
        this.array = new Object[count];
    }

    private ArrayObject() {

    }

    public int getArrayLength() {
        return array.length;
    }

    public Object getArrayElement(int index) {
        return array[index];
    }

    public void setArrayElement(int index, Object value) {
        array[index] = value;
    }

    public ArrayObject cloneArrayObject() {
        ArrayObject cloneObject = new ArrayObject();
        cloneObject.myClass = this.myClass;
        // clone array
        if (this.array != null) {
            cloneObject.array = Arrays.copyOf(this.array, this.array.length);
        }
        cloneObject.extra = this.extra;

        return cloneObject;
    }
}
