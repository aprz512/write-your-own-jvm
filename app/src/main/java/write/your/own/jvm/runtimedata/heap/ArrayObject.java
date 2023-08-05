package write.your.own.jvm.runtimedata.heap;

public class ArrayObject extends MyObject {

    // 用 Object[] 有点浪费，因为 int[] 也可以用 Object 接受
    // 懒得改了
    private final Object[] array;

    public ArrayObject(MyClass myClass, int count) {
        super(myClass);
        this.array = new Object[count];
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
}
