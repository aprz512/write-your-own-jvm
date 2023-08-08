package write.your.own.jvm.runtimedata;

import write.your.own.jvm.runtimedata.heap.MyMethod;

/**
 * Shim是一种(小型的)库，负责透明地拦截API调用，并更改其参数，或将其转发至其他组件，或者自行处理。
 */
public class Shim {

    public static StackFrame createShimReturnFrame(MyThread thread, int maxStack) {
        MyMethod shimMethod = MyMethod.createShimMethod(new byte[]{(byte) 0xb1}, "<return>", maxStack);
        return new StackFrame(thread, shimMethod);
    }

//    public static MyMethod createShimMethod() {
//        MyMethod returnMethod = MyMethod.createShimMethod(new byte[] {(byte) 0xb1}, "<return>");
//        MyMethod aThrowMethod = MyMethod.createShimMethod(new byte[] {(byte) 0xbf}, "<athrow>");
//    }

}
