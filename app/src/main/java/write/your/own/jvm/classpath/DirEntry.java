package write.your.own.jvm.classpath;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * load a class file bytes from directory
 */
public class DirEntry implements Entry {

    // directory absolute path which your target class file in
    // 在 Solaris OS 中，Path 使用 Solaris 语法（/home/joe/foo）
    // 在 Microsoft Windows 中，Path 使用 Windows 语法（C:\home\joe\foo）。
    private final Path absDirPath;

    public DirEntry(String classpath) {
        absDirPath = Paths.get(classpath).toAbsolutePath();
    }

    @Override
    public byte[] readClass(String fullyQualifiedClassName) throws Exception {
        return Files.readAllBytes(absDirPath.resolve(fullyQualifiedClassName));
    }
}
