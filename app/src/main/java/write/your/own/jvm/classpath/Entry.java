package write.your.own.jvm.classpath;

public interface Entry {

    // className: fully/qualified/ClassName.class
    // for example: write/your/own/jvm/classpath/Entry.class
    byte[] readClass(String fullyQualifiedClassName) throws Exception;

}
