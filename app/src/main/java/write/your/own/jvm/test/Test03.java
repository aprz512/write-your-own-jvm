package write.your.own.jvm.test;

public class Test03 {
    public static void main(String[] args) {
        Test03 test03 = new Test03();
//        System.out.println(test03);
    }

    public static int test(int a) {
        if (a > 0) {
            a = 1;
        } else if (a < 0) {
            a = -1;
        } else {
            a = 0;
        }

        return a;
    }

    int chooseNear(int i) {
        switch (i) {
            case 10:
                return 0;
            case 11:
                return 1;
            case 13:
                return 2;
            default:
                return -1;
        }
    }

    int chooseNear2(String i) {
        switch (i) {
            case "10":
                return 0;
            case "11":
                return 1;
            case "13":
                return 2;
            default:
                return -1;
        }
    }
}
