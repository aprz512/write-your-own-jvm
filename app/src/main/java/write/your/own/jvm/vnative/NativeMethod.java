package write.your.own.jvm.vnative;

import write.your.own.jvm.runtimedata.StackFrame;

public interface NativeMethod {

    void invoke(StackFrame frame);

}
