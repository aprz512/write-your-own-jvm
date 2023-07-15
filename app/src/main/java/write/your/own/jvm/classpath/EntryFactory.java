package write.your.own.jvm.classpath;

import java.io.File;

public class EntryFactory {

    public static Entry create(String classpath) {

        // /a/b/c ; /x/y/z.zip
        if (classpath.contains(File.pathSeparator)) {
            return new CompositeEntry(classpath);
        }

        // /a/b/*
        if (classpath.endsWith("*")) {
            return new WildcardEntry(classpath);
        }

        // /x/y/z.jar
        if (classpath.endsWith(".jar")
                || classpath.endsWith(".JAR")
                || classpath.endsWith(".zip")
                || classpath.endsWith(".ZIP")) {
            return new ZipEntry(classpath);
        }

        return new DirEntry(classpath);
    }

}
