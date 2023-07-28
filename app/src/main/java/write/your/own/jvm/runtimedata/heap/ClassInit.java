package write.your.own.jvm.runtimedata.heap;

import write.your.own.jvm.runtimedata.MyThread;
import write.your.own.jvm.runtimedata.StackFrame;

public class ClassInit {

    public static void initMyClass(MyClass myClass, MyThread thread) {
        myClass.setInitStarted(true);
        scheduleClinit(myClass, thread);
        initSuperClass(myClass, thread);
    }

    private static void initSuperClass(MyClass myClass, MyThread thread) {
        if (!myClass.isInterface()) {
            MyClass superClass = myClass.getSuperClass();
            if (superClass != null && !superClass.isInitStarted()) {
                initMyClass(superClass, thread);
            }
        }
    }

    private static void scheduleClinit(MyClass myClass, MyThread thread) {
        MyMethod staticMethod = myClass.getStaticMethod("<clinit>", "()V");
        if (staticMethod != null) {
            StackFrame stackFrame = thread.newStackFrame(staticMethod);
            thread.pushStackFrame(stackFrame);
        }
    }

}
