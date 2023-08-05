package write.your.own.jvm.test;

public class Test093 {

    public static void main(String[] args) {
        Object obj1 = new Test093();
        Object obj2 = new Test093();
        System.out.println(obj1.hashCode());
        System.out.println(obj1);
        System.out.println(obj1.equals(obj2));
        System.out.println(obj1.equals(obj1));
    }

}
