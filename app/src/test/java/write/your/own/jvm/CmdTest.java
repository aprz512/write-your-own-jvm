package write.your.own.jvm;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CmdTest {
    @Test
    void test() {
        assertArrayEquals(Cmd.parseArgs(new String[]{"App", "a1", "a2"}).getArgs().toArray(), new String[]{"a1", "a2"});
        assertEquals(Cmd.parseArgs(new String[]{"App", "a1", "-cp", "xxxxxxx"}).getClasspath(), "xxxxxxx");
        assertEquals(Cmd.parseArgs(new String[]{"App", "a1", "-cp", "xxxxxxx"}).getMainClass(), "App");
    }
}
