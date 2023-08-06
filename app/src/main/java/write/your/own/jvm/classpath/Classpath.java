package write.your.own.jvm.classpath;

import java.io.File;
import java.nio.file.Paths;

public class Classpath {

    // for example: java.lang.*
    private final Entry bootStrapClasspath;
    // for example: sun.security.util.CurveDB
    private final Entry extensionClasspath;
    // for example: your project java class
    private final Entry appClasspath;

    public Classpath(String cpOption) {
        bootStrapClasspath = parseBootStrapClasspath();
        extensionClasspath = parseExtensionClasspath();
        appClasspath = parseAppClasspath(cpOption);
    }

    /**
     * @return jre directory
     */
    private static String getJreDir() {
        String jh = System.getenv("JAVA_HOME");
        if (jh != null) {
            return Paths.get(jh, "jre").toString();
        }
        throw new RuntimeException("Can not find JRE folder!");
    }

    /**
     * app classpath:
     * 1. cpOption
     * 2. current dir
     */
    private Entry parseAppClasspath(String cpOption) {
        if (cpOption == null) {
            cpOption = ".";
        }
        return EntryFactory.create(cpOption);
    }

    /**
     * extension search path : jre/lib/ext/*
     */
    private Entry parseExtensionClasspath() {
        String jreDir = getJreDir();
        // jre/lib/ext/*
        String jreExtPath = Paths.get(jreDir, "lib", "ext") + File.separator + "*";
        return new WildcardEntry(jreExtPath);
    }

    /**
     * bootstrap search path : jre/lib/*
     */
    private Entry parseBootStrapClasspath() {
        String jreDir = getJreDir();
        // jre/lib/*
        String jreLibPath = Paths.get(jreDir, "lib") + File.separator + "*";
        return new WildcardEntry(jreLibPath);
    }


    // className: fully/qualified/ClassName
    public byte[] readClass(String className) throws Exception {
        className = className + ".class";

        try {
            return bootStrapClasspath.readClass(className);
        } catch (Exception ignored) {

        }

        try {
            return extensionClasspath.readClass(className);
        } catch (Exception ignored) {

        }

        return appClasspath.readClass(className);
    }


}
