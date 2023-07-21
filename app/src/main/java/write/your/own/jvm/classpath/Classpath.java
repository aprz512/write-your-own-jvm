package write.your.own.jvm.classpath;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Classpath {

    // for example: java.lang.*
    private final Entry bootStrapClasspath;
    // for example: sun.security.util.CurveDB
    private final Entry extensionClasspath;
    // for example: your project java class
    private final Entry appClasspath;

    public Classpath(String jreOption, String cpOption) {
        bootStrapClasspath = parseBootStrapClasspath(jreOption);
        extensionClasspath = parseExtensionClasspath(jreOption);
        appClasspath = parseAppClasspath(cpOption);
    }

    /**
     * search jre directory priority:
     * 1. jreOption
     * 2. current dir
     * 3. java_home
     *
     * @param jreOption passed on jre directory
     * @return jre directory
     */
    private static String getJreDir(String jreOption) {
        if (jreOption != null && !("".equals(jreOption)) && Files.exists(Paths.get(jreOption))) {
            return jreOption;
        }
        if (Files.exists(Paths.get("./jre"))) {
            return "./jre";
        }
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
    private Entry parseExtensionClasspath(String jreOption) {
        String jreDir = getJreDir(jreOption);
        // jre/lib/ext/*
        String jreExtPath = Paths.get(jreDir, "lib", "ext") + File.separator + "*";
        return new WildcardEntry(jreExtPath);
    }

    /**
     * bootstrap search path : jre/lib/*
     */
    private Entry parseBootStrapClasspath(String jreOption) {
        String jreDir = getJreDir(jreOption);
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
