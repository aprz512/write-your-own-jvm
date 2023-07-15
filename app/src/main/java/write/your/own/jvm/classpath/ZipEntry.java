package write.your.own.jvm.classpath;

import java.nio.file.*;

/**
 * load a class file bytes from zip file
 */
public class ZipEntry implements Entry {

    // zip file's absolute path
    // 在 Solaris OS 中，Path 使用 Solaris 语法（/home/joe/foo）
    // 在 Microsoft Windows 中，Path 使用 Windows 语法（C:\home\joe\foo）。
    private final Path absZipFilePath;

    public ZipEntry(String classPath) {
        absZipFilePath = Paths.get(classPath).toAbsolutePath();
    }

    @Override
    public byte[] readClass(String fullyQualifiedClassName) throws Exception {
        // unzip jar/zip file
        // read class file bytes from zip file
        // This method makes use of specialized providers that create pseudo file systems
        // where the contents of one or more files is treated as a file system.
        // pass null to loader means only attempt to locate an installed provider
        try (FileSystem zipFs = FileSystems.newFileSystem(absZipFilePath, null)) {
            return Files.readAllBytes(zipFs.getPath(fullyQualifiedClassName));
        }
    }
}
