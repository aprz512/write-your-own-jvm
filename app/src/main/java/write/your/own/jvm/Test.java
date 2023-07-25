package write.your.own.jvm;

public class Test {

    public static int staticVar;
    public int instanceVar;
    public static void main(String[] args) {
        int x = 32768; // ldc
        Test myObj = new Test(); // new
        Test.staticVar = x; // putstatic
        x = Test.staticVar; // getstatic
        myObj.instanceVar = x; // putfield
        x = myObj.instanceVar; // getfield
        Object obj = myObj;
        if (obj instanceof Test) { // instanceof
            myObj = (Test) obj; // checkcast
            System.out.println(myObj.instanceVar);
//            System.setOut();
//            System.console().printf("instanceVar = %d\n", myObj.instanceVar);

        }
    }

}
