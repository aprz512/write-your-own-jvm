package write.your.own.jvm.classfile.constantpool;

public class InvalidConstantInfo implements ConstantInfo {
    @Override
    public String getValue() {
        return "";
    }

    @Override
    public String toString() {
        return "Invalid";
    }
}
