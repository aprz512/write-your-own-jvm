package write.your.own.jvm;

import org.apache.commons.cli.*;
import write.your.own.jvm.util.Log;

import java.util.Arrays;
import java.util.List;

public class Cmd {

    private final boolean verboseClassFlag;
    private final boolean verboseInstFlag;
    private final boolean verboseFrameFlag;
    private final String classpath;
    private final String mainClass;
    private final List<String> args;

    public Cmd(String classpath, String mainClass, boolean verboseClassFlag, boolean verboseInstFlag, boolean verboseFrameFlag, List<String> args) {
        this.classpath = classpath;
        this.mainClass = mainClass;
        this.args = args;
        this.verboseClassFlag = verboseClassFlag;
        this.verboseInstFlag = verboseInstFlag;
        this.verboseFrameFlag = verboseFrameFlag;
        Config.init(this);
    }

    public static Cmd parseArgs(String[] args) {
        // create Options object
        Options options = new Options();
        // add option
        options.addOption("h", false, "print help message");
        options.addOption("v", false, "print version");
        // -cp equals -classpath
        options.addOption("cp", true, "class path where to find");
        options.addOption("vclass", false, "print loaded classed info");
        options.addOption("vinst", false, "print executed instruction info");
        options.addOption("vframe", false, "print operand stack and local variable table info");

        try {
            // Create a parser
            CommandLineParser parser = new DefaultParser();
            // parse the options passed as command line arguments
            CommandLine commandLine = parser.parse(options, args);
            if (commandLine.hasOption("h")) {
                printHelp();
                System.exit(0);
            }

            if (commandLine.hasOption("v")) {
                printVersion();
            }

            if (commandLine.hasOption("cp")) {
                printClasspath(commandLine.getOptionValue("cp"));
            }

            boolean verboseClass = commandLine.hasOption("vclass");

            boolean verboseInstruction = commandLine.hasOption("vinst");

            boolean verboseOperandStack = commandLine.hasOption("vframe");

            String cp = commandLine.getOptionValue("cp");
            String[] leftArgs = commandLine.getArgs();
            if (leftArgs.length == 0) {
                printHelp();
                System.exit(0);
            }
            return new Cmd(cp, leftArgs[0], verboseClass, verboseInstruction, verboseOperandStack, Arrays.asList(leftArgs).subList(1, leftArgs.length));
        } catch (ParseException e) {
            e.printStackTrace();
            printHelp();
        }
        return null;
    }

    private static void printClasspath(String classpath) {
        Log.o("classpath: " + classpath);
    }

    private static void printVersion() {
        Log.o("version: 0.1.0");
    }

    private static void printHelp() {
        Log.o("usage: <mc> [args] [-options]");
        Log.o("mc: main class name");
        Log.o("args: main class args");
        Log.o(" -h      usage help");
        Log.o(" -v      current version");
        Log.o(" -cp     class path which need to load");
    }

    public String getClasspath() {
        return classpath;
    }

    public String getMainClass() {
        return mainClass;
    }

    public List<String> getArgs() {
        return args;
    }

    public static class Config {

        public static boolean verboseClassFlag;
        public static boolean verboseInstFlag;
        public static boolean verboseFrameFlag;

        public static void init(Cmd cmd) {
            verboseClassFlag = cmd.verboseClassFlag;
            verboseInstFlag = cmd.verboseInstFlag;
            verboseFrameFlag = cmd.verboseFrameFlag;
        }

    }
}
