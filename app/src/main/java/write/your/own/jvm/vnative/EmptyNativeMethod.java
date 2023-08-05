package write.your.own.jvm.vnative;

import write.your.own.jvm.runtimedata.StackFrame;

public class EmptyNativeMethod implements NativeMethod {

    @Override
    public void invoke(StackFrame frame) {

    }

}
