package write.your.own.jvm.test;

public class Test094 implements Cloneable {

    private double pi = 3.14;

    public static void main(String[] args) {
        Test094 obj1 = new Test094();
        Test094 obj2 = obj1.clone();
        obj1.pi = 3.1415926;
        System.out.println(obj1.pi);
        System.out.println(obj2.pi);
    }

    @Override
    public Test094 clone() {
        try {
            return (Test094) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }


}
