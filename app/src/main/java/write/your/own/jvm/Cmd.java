package write.your.own.jvm;


import org.apache.commons.cli.*;

import java.util.Arrays;
import java.util.List;


public class Cmd {
    private final String classpath;
    private final String mainClass;
    private final List<String> args;

    public Cmd(String classpath, String mainClass, List<String> args) {
        this.classpath = classpath;
        this.mainClass = mainClass;
        this.args = args;
    }

    public static Cmd parseArgs(String[] args) {
        // create Options object
        Options options = new Options();
        // add option
        options.addOption("h", false, "print help message");
        options.addOption("v", false, "print version");
        // -cp equals -classpath
        options.addOption("cp", true, "class path where to find");

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
            String cp = commandLine.getOptionValue("cp");
            String[] leftArgs = commandLine.getArgs();
            return new Cmd(cp, leftArgs[0], Arrays.asList(leftArgs).subList(1, leftArgs.length));
        } catch (ParseException e) {
            e.printStackTrace();
            printHelp();
        }
        return null;
    }

    private static void printClasspath(String classpath) {
        Log.d("classpath: " + classpath);
    }

    private static void printVersion() {
        Log.d("version: 0.1.0");
    }

    private static void printHelp() {
        Log.d("usage: <mc> [args] [-options]");
        Log.d("mc: main class name");
        Log.d("args: main class args");
        Log.d(" -h      usage help");
        Log.d(" -v      current version");
        Log.d(" -cp     class path which need to load");
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
}
