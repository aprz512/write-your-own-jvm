package write.your.own.jvm.vnative.sun.misc;

import write.your.own.jvm.instruction.base.InvokeMethod;
import write.your.own.jvm.runtimedata.MyString;
import write.your.own.jvm.runtimedata.OperandStack;
import write.your.own.jvm.runtimedata.StackFrame;
import write.your.own.jvm.runtimedata.heap.MyClass;
import write.your.own.jvm.runtimedata.heap.MyField;
import write.your.own.jvm.runtimedata.heap.MyMethod;
import write.your.own.jvm.runtimedata.heap.MyObject;
import write.your.own.jvm.vnative.NativeMethod;
import write.your.own.jvm.vnative.NativeRegistry;

public class NVM {
    public static void init() {
        NativeRegistry.getInstance().registerNativeMethod(
                "sun/misc/VM",
                "initialize",
                "()V",
                new Initialize());

    }

    public static class Initialize implements NativeMethod {

        @Override
        public void invoke(StackFrame frame) {
            MyClass myClass = frame.getMyMethod().getMyClass();
            MyField field = myClass.getField("savedProps", "Ljava/util/Properties;", true);
            MyObject fieldValue = myClass.getStaticVars().getRef(field.getSlotId());
            MyObject key = MyString.create("foo", myClass.getClassLoader());
            MyObject value = MyString.create("bar", myClass.getClassLoader());

            OperandStack operandStack = frame.getOperandStack();
            operandStack.pushRef(fieldValue);
            operandStack.pushRef(key);
            operandStack.pushRef(value);

            MyClass propertiesClass = myClass.getClassLoader().loadClass("java/util/Properties");
            MyMethod setProperty = propertiesClass.getMethod(
                    "setProperty",
                    "(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/Object;",
                    false);
            InvokeMethod.invokeMethod(frame, setProperty);
        }
    }

}
