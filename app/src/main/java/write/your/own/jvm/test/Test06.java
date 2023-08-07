package write.your.own.jvm.test;

public class Test06 {
    public static final int f = 100;
    public static int staticVar;
    public int instanceVar;

    public static void main(String[] args) {
        int x = 32768; // ldc
        Test06 myObj = new Test06(); // new
        Test06.staticVar = x; // putstatic
        x = Test06.staticVar; // getstatic
        myObj.instanceVar = x; // putfield
        x = myObj.instanceVar; // getfield
        Object obj = myObj;
        if (obj instanceof Test06) { // instanceof
            myObj = (Test06) obj; // checkcast
            System.out.println(myObj.instanceVar);
        }
    }

}
