package write.your.own.jvm.classfile.constantpool;

public class InvalidConstantInfo implements ConstantInfo {

//    @Override
//    public String getValue() {
//        return "";
//    }

    public int getTag() {
        return 0;
    }

    @Override
    public String toString() {
        return "Invalid";
    }

}
