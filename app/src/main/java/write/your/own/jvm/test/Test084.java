package write.your.own.jvm.test;

public class Test084 {

    public static void main(String[] args) {
        String hello = "Hello, world!";
        System.out.println(hello.substring(1, 3));
        test();
    }

    public static native void test();

}
