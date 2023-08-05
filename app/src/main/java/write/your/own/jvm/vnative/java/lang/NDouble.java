package write.your.own.jvm.vnative.java.lang;

import write.your.own.jvm.runtimedata.StackFrame;
import write.your.own.jvm.vnative.NativeMethod;
import write.your.own.jvm.vnative.NativeRegistry;

public class NDouble {

    public static void init() {
        NativeRegistry.getInstance().registerNativeMethod(
                "java/lang/Double",
                "doubleToRawLongBits",
                "(D)J",
                new DoubleToRawLongBits());

        NativeRegistry.getInstance().registerNativeMethod(
                "java/lang/Double",
                "longBitsToDouble",
                "(J)D",
                new LongBitsToDouble());
    }

    public static class DoubleToRawLongBits implements NativeMethod {

        @Override
        public void invoke(StackFrame frame) {
            double value = frame.getLocalVariableTable().getDouble(0);
            long bits = Double.doubleToRawLongBits(value);
            frame.getOperandStack().pushLong(bits);
        }
    }

    public static class LongBitsToDouble implements NativeMethod {

        @Override
        public void invoke(StackFrame frame) {
            long value = frame.getLocalVariableTable().getLong(0);
            double bits = Double.longBitsToDouble(value);
            frame.getOperandStack().pushDouble(bits);
        }
    }

}
