package write.your.own.jvm.runtimedata;

import write.your.own.jvm.runtimedata.heap.ArrayObject;
import write.your.own.jvm.runtimedata.heap.MyClass;
import write.your.own.jvm.runtimedata.heap.MyClassLoader;
import write.your.own.jvm.runtimedata.heap.MyObject;

public class MyString {

    public static MyObject create(String real, MyClassLoader classLoader) {
        // create String object
        MyClass stringClass = classLoader.loadClass("java/lang/String");
        MyObject stringObject = stringClass.newObject();

        // create char array object for value field
        MyClass charArrayClass = classLoader.loadClass("[C");
        ArrayObject arrayObject = charArrayClass.newArrayObject(real.getBytes().length);
        // copy value bytes
        for (int i = 0; i < real.getBytes().length; i++) {
            arrayObject.setArrayElement(i, real.getBytes()[i]);
        }
        // set value field
        stringObject.setRefFieldValue("value", "[C", arrayObject);
        return stringObject;
    }

    public static String toString(MyObject stringObject) {
        ArrayObject arrayObject = (ArrayObject) stringObject.getRefFieldValue("value", "[C");
        byte[] valueBytes = new byte[arrayObject.getArrayLength()];
        for (int i = 0; i < arrayObject.getArrayLength(); i++) {
            valueBytes[i] = (byte) arrayObject.getArrayElement(i);
        }
        return new String(valueBytes);
    }

}
