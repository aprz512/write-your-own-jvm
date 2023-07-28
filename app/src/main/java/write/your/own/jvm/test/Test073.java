package write.your.own.jvm.test;

public class Test073 {

    public static int index = 30;

    static {
        index = 10;
    }

    public static void main(String[] args) {
        long x = fibonacci(index);
        System.out.println(x);
    }

    private static long fibonacci(long n) {
        if (n <= 1) {
            return n;
        }
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    // 0 1 1 2 3 5 8 13

}
