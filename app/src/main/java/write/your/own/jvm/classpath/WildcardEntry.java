package write.your.own.jvm.classpath;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 从Java 6开始，还可以使用通配符（*）指定某个目录下的所有 JAR文件，格式如下：
 * java -cp classes;lib\* ...
 */
public class WildcardEntry extends CompositeEntry {

    public WildcardEntry(String wildcardPath) {
        super(compositePath(wildcardPath));
    }

    private static String compositePath(String wildcardPath) {
        // remove *
        String baseDir = wildcardPath.replace("*", "");
        try {
            // The try-with-resources Statement
            try (Stream<Path> pathStream = Files.walk(Paths.get(baseDir))) {
                // filter .jar and .JAR
                // collect
                return pathStream.filter(Files::isRegularFile)
                        .map(Path::toString)
                        .filter(p -> p.endsWith(".jar") || p.endsWith(".JAR"))
                        .collect(Collectors.joining(File.pathSeparator));
            }
        } catch (IOException e) {
            //e.printStackTrace(System.err);
            return "";
        }
    }

}
