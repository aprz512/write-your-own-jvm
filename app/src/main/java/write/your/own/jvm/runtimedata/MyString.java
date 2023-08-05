package write.your.own.jvm.runtimedata;

import write.your.own.jvm.runtimedata.heap.*;

public class MyString {

    public static MyObject create(String real, MyClassLoader classLoader) {

        MyObject cache = StringPool.getInstance().get(real);
        if (cache != null) {
            return cache;
        }

        // create String object
        MyClass stringClass = classLoader.loadClass("java/lang/String");
        MyObject stringObject = stringClass.newObject();

        // create char array object for value field
        MyClass charArrayClass = classLoader.loadClass("[C");
        ArrayObject arrayObject = charArrayClass.newArrayObject(real.getBytes().length);
        // copy value bytes
        for (int i = 0; i < real.getBytes().length; i++) {
            arrayObject.setArrayElement(i, (char) real.getBytes()[i]);
        }
        // set value field
        stringObject.setRefFieldValue("value", "[C", arrayObject);

        StringPool.getInstance().putString(real, stringObject);

        return stringObject;
    }

    public static String toString(MyObject stringObject) {
        ArrayObject arrayObject = (ArrayObject) stringObject.getRefFieldValue("value", "[C");
        char[] valueBytes = new char[arrayObject.getArrayLength()];
        for (int i = 0; i < arrayObject.getArrayLength(); i++) {
            valueBytes[i] = (char) arrayObject.getArrayElement(i);
        }
        return new String(valueBytes);
    }

}
