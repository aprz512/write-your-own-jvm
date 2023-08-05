package write.your.own.jvm.vnative.java.lang;

import write.your.own.jvm.exception.MyJvmException;
import write.your.own.jvm.runtimedata.MyString;
import write.your.own.jvm.runtimedata.StackFrame;
import write.your.own.jvm.runtimedata.heap.MyObject;
import write.your.own.jvm.runtimedata.heap.StringPool;
import write.your.own.jvm.vnative.NativeMethod;
import write.your.own.jvm.vnative.NativeRegistry;

public class NString {

    public static void init() {
        NativeRegistry.getInstance().registerNativeMethod(
                "java/lang/String",
                "intern",
                "()Ljava/lang/String;",
                new Intern());
    }

    /**
     * 它的作用是如果字符串常量池中已经包含一个等于此 String 对象的字符串，
     * 则返回代表池中这个字符串的 String 对象的引用；
     * 否则，会将此 String 对象包含的字符串添加到常量池中，并且返回此 String 对象的引用。
     */
    public static class Intern implements NativeMethod {

        @Override
        public void invoke(StackFrame frame) {
            MyObject stringObj = frame.getLocalVariableTable().getRef(0);
            if (!stringObj.getMyClass().getThisClassName().equals("java/lang/String")) {
                throw new MyJvmException("not a string obj!!!");
            }
            String string = MyString.toString(stringObj);
            MyObject poolString = StringPool.getInstance().putString(string, stringObj);
            frame.getOperandStack().pushRef(poolString);
        }
    }
}
