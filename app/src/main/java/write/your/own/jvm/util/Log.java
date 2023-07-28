package write.your.own.jvm.util;

public class Log {

    private static final int x = 10;
    private int y = 1090890;

    public static void d(String message) {
        System.out.println(message);
    }

    public static void e(String message) {
        System.out.println("error: " + message);
    }

    public static void o(String message) {
        System.out.println(message);
    }

}
