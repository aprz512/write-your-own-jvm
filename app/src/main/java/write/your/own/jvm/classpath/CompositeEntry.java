package write.your.own.jvm.classpath;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 组合路径，路径之间使用 ; 分割（windows）
 */
public class CompositeEntry implements Entry {

    private final List<Entry> entries = new ArrayList<>();

    public CompositeEntry(String compositeClasspathList) {
        for (String classpath : compositeClasspathList.split(File.pathSeparator)) {
            entries.add(EntryFactory.create(classpath));
        }
    }

    @Override
    public byte[] readClass(String fullyQualifiedClassName) throws Exception {
        // search every entry to find class file
        for (Entry entry : entries) {
            try {
                return entry.readClass(fullyQualifiedClassName);
            } catch (Exception ignored) {
            }
        }
        // not found
        throw new Exception("class not found: " + fullyQualifiedClassName);
    }

}
