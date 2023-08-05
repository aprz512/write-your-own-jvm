package write.your.own.jvm.vnative.java.lang;

import write.your.own.jvm.runtimedata.StackFrame;
import write.your.own.jvm.vnative.NativeMethod;
import write.your.own.jvm.vnative.NativeRegistry;

public class NFloat {

    public static void init() {
        NativeRegistry.getInstance().registerNativeMethod(
                "java/lang/Float",
                "floatToRawIntBits",
                "(F)I",
                new FloatToRawIntBits());
    }

    public static class FloatToRawIntBits implements NativeMethod {

        @Override
        public void invoke(StackFrame frame) {
            float value = frame.getLocalVariableTable().getFloat(0);
            int bits = java.lang.Float.floatToRawIntBits(value);
            frame.getOperandStack().pushInt(bits);
        }
    }

}
