package write.your.own.jvm.test;

public class InvokeDynamicTest {

//    static {
//        System.setProperty("jdk.internal.lambda.dumpProxyClasses", "./DUMP_CLASS_FILEs");
//    }

    public static void main(String[] args) throws Throwable {
        PTest t = (a, b1) -> {
            System.out.println(b1);
            return "null";
        };
        t.test(1, "te");
    }

    interface PTest {
        String test(int a, String b);
    }
//
//    public static void testMethod(String s) {
//        System.out.println("hello String:" + s);
//    }
//
//    public static CallSite BootstrapMethod(MethodHandles.Lookup lookup, String name, MethodType mt) throws Throwable {
//        return new ConstantCallSite(lookup.findStatic(InvokeDynamicTest.class, name, mt));
//    }
//
//    private static MethodType MT_BootstrapMethod() {
//        return MethodType
//                .fromMethodDescriptorString(
//                        "(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String; Ljava/lang/invoke/MethodType;)Ljava/lang/invoke/CallSite;", null);
//    }
//
//    private static MethodHandle MH_BootstrapMethod() throws Throwable {
//        return lookup().findStatic(InvokeDynamicTest.class, "BootstrapMethod", MT_BootstrapMethod());
//    }
//
//    private static MethodHandle INDY_BootstrapMethod() throws Throwable {
//        CallSite cs = (CallSite) MH_BootstrapMethod().invokeWithArguments(lookup(), "testMethod",
//                MethodType.fromMethodDescriptorString("(Ljava/lang/String;)V", null));
//        return cs.dynamicInvoker();
//    }

}
