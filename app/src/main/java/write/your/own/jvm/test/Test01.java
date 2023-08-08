package write.your.own.jvm.test;

public class Test01 {
    public static void main(String[] args) {
        test(args);
    }

    public static void test(String[] args) {
        test2(args);
    }

    public static void test2(String[] args) {
        System.out.println(args.length);
    }

}
