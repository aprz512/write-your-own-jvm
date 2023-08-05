package write.your.own.jvm.util;

public class Log {

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
