package write.your.own.jvm.classpath;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

public class EntryTest {

    @Test
    public void test() {
//        int testValue = Integer.valueOf("7FFFFFFF", 16);
//        String javaHome = System.getProperty("java.home");
//        String javaHome2 = System.getenv("JAVA_HOME");
    }

    @Test
    public void create() {
        assertEquals(DirEntry.class, EntryFactory.create(".").getClass());
        assertEquals(CompositeEntry.class, EntryFactory.create("foo.jar" + File.pathSeparator + "bar.jar").getClass());
        assertEquals(WildcardEntry.class, EntryFactory.create("foo/bar/*").getClass());
    }

    @Test
    public void dirEntry() throws Exception {
        // just for windows
        String className = EntryTest.class.getName().replace('.', '/') + ".class";
        String classesDir = EntryTest.class.getResource("EntryTest.class").getPath()
                .replaceFirst("/", "")
                .replace(className, "");

        Entry entry = EntryFactory.create(classesDir);
        assertEquals(DirEntry.class, entry.getClass());

        byte[] data = entry.readClass(className);
        assertNotNull(data);
    }

    @Test
    public void zipEntry() throws Exception {
        Path lib = Paths.get(System.getProperty("java.home"), "lib", "charsets.jar");
        String targetFilePath = lib.toAbsolutePath().toString();

        assertTrue(new File(targetFilePath).exists());

        Entry entry = EntryFactory.create(targetFilePath);
        assertEquals(ZipEntry.class, entry.getClass());

        byte[] data = entry.readClass("sun/nio/cs/ext/Big5.class");
        assertNotNull(data);
    }

    @Test
    public void compositeEntry() throws Exception {
        String cp = System.getProperty("java.class.path");

        Entry entry = EntryFactory.create(cp);
        assertEquals(CompositeEntry.class, entry.getClass());

        byte[] data = entry.readClass("org/junit/jupiter/api/Test.class");
        assertNotNull(data);
    }

    @Test
    public void wildcardEntry() throws Exception {
        Path lib = Paths.get(System.getProperty("java.home"), "lib");
        String targetFilePath = lib.toAbsolutePath().toString();
        Entry entry = EntryFactory.create(targetFilePath + "*");
        assertEquals(WildcardEntry.class, entry.getClass());

        byte[] data = entry.readClass("sun/nio/cs/ext/Big5.class");
        assertNotNull(data);
    }

}
