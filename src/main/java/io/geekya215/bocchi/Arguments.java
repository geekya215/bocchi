package io.geekya215.bocchi;

import java.io.IOException;
import java.io.PrintStream;
import java.util.jar.JarFile;
import java.util.zip.ZipException;

import static io.geekya215.bocchi.BuildInfo.*;

public final class Arguments {
    private boolean help;
    private boolean version;
    private String classpath;
    private String mainClass;
    private String[] mainClassArgs;

    // reference from `java --help` outputs
    public static void printUsage(PrintStream ps) {
        ps.println("Usage: bocchi [options] <mainclass> [args...]");
        ps.println("           (to execute a class)");
        ps.println("   or  bocchi [options] -jar <jarfile> [args...]");
        ps.println("           (to execute a jar file)");
        ps.println();
        ps.println(" Arguments following the main class, source file, -jar <jarfile>");
        ps.println(" are passed as the arguments to main class.");
        ps.println();
        ps.println(" where options include:");
        ps.println("    -cp --classpath <class search path of directories and zip/jar files>");
        ps.println("    -v --version    print virtual machine version");
        ps.println("    -? -h --help    print this help message");
    }

    public static void printVersion(PrintStream ps) {
        ps.printf("Bocchi VM (%d.%d.%d-%s)\n", MAJOR, MINOR, BUILD, LABEL);
    }

    public Arguments(String[] args) {
        if (args.length == 0) {
            help = true;
            return;
        }

        if (args[0].equals("-?")
            || args[0].equals("-h")
            || args[0].equals("--help")) {
            help = true;
            return;
        }

        if (args[0].equals("-v") || args[0].equals("--version")) {
            version = true;
            return;
        }

        int index = 0;

        if (args[index].equals("-cp") || args[index].equals("--classpath")) {
            if (index + 2 < args.length && !args[index + 1].startsWith("-")) {
                classpath = args[index + 1];
                index += 2;
            } else {
                help = true;
                return;
            }
        }

        if (args[index].equals("-jar")) {
            if (index + 1 < args.length && !args[index + 1].startsWith("-")) {
                mainClass = processJar(args[index + 1]);
                index += 2;
                if (index < args.length) {
                    mainClassArgs = new String[args.length - index];
                    System.arraycopy(args, index, mainClassArgs, 0, args.length - index);
                    return;
                }
            } else {
                help = true;
                return;
            }
        }

        if (index < args.length && !args[index].startsWith("-")) {
            mainClass = args[index];
            index += 1;
            if (index < args.length) {
                mainClassArgs = new String[args.length - index];
                System.arraycopy(args, index, mainClassArgs, 0, args.length - index);
            }
            return;
        }

        help = true;
    }

    private static String processJar(String path) {
        try (JarFile jar = new JarFile(path)) {
            String mainClass = jar.getManifest().getMainAttributes().getValue("Main-Class");
            if (mainClass == null) {
                throw new IllegalArgumentException("no main manifest attribute, in " + path);
            }
            return mainClass;
        } catch (IOException e) {
            String errorMessage = e instanceof ZipException
                ? "Error: Invalid or corrupt jarfile "
                : "Error: Unable to access jarfile ";
            throw new IllegalArgumentException(errorMessage + path);
        }
    }

    public boolean isHelp() {
        return help;
    }

    public boolean isVersion() {
        return version;
    }

    public String getClasspath() {
        return classpath;
    }

    public String getMainClass() {
        return mainClass;
    }

    public String[] getMainClassArgs() {
        return mainClassArgs;
    }
}
