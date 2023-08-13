package write.your.own.jvm.test;

public class Test04 {

    private static final int a = 10;
    private final String b = "20";

    private void func(Runnable runnable) {
        runnable.run();
    }

}
