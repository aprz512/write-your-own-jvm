package write.your.own.jvm.test;

public class Test07 implements Runnable {
    public static void main(String[] args) {
        new Test07().test();
    }

    public static void staticMethod() {
//        System.out.println("staticMethod");
    }

    public void test() {
        Test07.staticMethod(); // invokestatic
        Test07 demo = new Test07(); // invokespecial
        demo.instanceMethod(); // invokespecial
        super.equals(null); // invokespecial
        this.run(); // invokevirtual
        ((Runnable) demo).run(); // invokeinterface
    }

    private void instanceMethod() {
//        System.out.println("instanceMethod");
    }

    @Override
    public void run() {
//        System.out.println("run");
    }

}
