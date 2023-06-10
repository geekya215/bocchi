import io.geekya215.bocchi.Arguments;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ArgumentsTest {
    @Test
    void testShowHelp() {
        String[] args1 = new String[]{};
        Arguments arguments1 = new Arguments(args1);
        assertTrue(arguments1.isHelp());

        String[] args2 = new String[]{"-?"};
        Arguments arguments2 = new Arguments(args2);
        assertTrue(arguments2.isHelp());

        String[] args3 = new String[]{"-h"};
        Arguments arguments3 = new Arguments(args3);
        assertTrue(arguments3.isHelp());

        String[] args4 = new String[]{"--help"};
        Arguments arguments4 = new Arguments(args4);
        assertTrue(arguments4.isHelp());

        String[] args5 = new String[]{"-abc"};
        Arguments arguments5 = new Arguments(args5);
        assertTrue(arguments5.isHelp());
    }

    @Test
    void testShowVersion() {
        String[] args1 = new String[]{"-v"};
        Arguments arguments1 = new Arguments(args1);
        assertTrue(arguments1.isVersion());

        String[] args2 = new String[]{"--version"};
        Arguments arguments2 = new Arguments(args2);
        assertTrue(arguments2.isVersion());
    }

    @Test
    void testMainClass() {
        String[] args = new String[]{"Bocchi"};
        Arguments arguments = new Arguments(args);
        assertEquals("Bocchi", arguments.getMainClass());
    }

    @Test
    void testMainClassWithArguments() {
        String[] args = new String[]{"Bocchi", "Ryou", "Nijika", "Ikuyo"};
        Arguments arguments = new Arguments(args);
        assertEquals("Bocchi", arguments.getMainClass());
        assertArrayEquals(new String[]{"Ryou", "Nijika", "Ikuyo"}, arguments.getMainClassArgs());
    }

    @Test
    void testClassPath() {
        String[] args = new String[]{"-cp", "/bocchi/lib/"};
        Arguments arguments = new Arguments(args);
        assertTrue(arguments.isHelp());
    }

    @Test
    void testClassPathWithMainClass() {
        String[] args = new String[]{"-cp", "/bocchi/lib/", "Bocchi"};
        Arguments arguments = new Arguments(args);
        assertEquals("/bocchi/lib/", arguments.getClasspath());
        assertEquals("Bocchi", arguments.getMainClass());
    }

    @Test
    void testClassPathWithMainClassAndArguments() {
        String[] args = new String[]{"-cp", "/bocchi/lib/", "Bocchi", "Ryou", "Nijika", "Ikuyo"};
        Arguments arguments = new Arguments(args);
        assertEquals("/bocchi/lib/", arguments.getClasspath());
        assertEquals("Bocchi", arguments.getMainClass());
        assertArrayEquals(new String[]{"Ryou", "Nijika", "Ikuyo"}, arguments.getMainClassArgs());
    }

    @Test
    void testNonExistJar() {
        String[] args = new String[]{"-jar", "src/test/resources/band.jar"};
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> new Arguments(args));
        assertEquals("Error: Unable to access jarfile src/test/resources/band.jar", e.getMessage());
    }

    @Test
    void testInvalidJar() {
        String[] args = new String[]{"-jar", "src/test/resources/rock.jar"};
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> new Arguments(args));
        assertEquals("no main manifest attribute, in src/test/resources/rock.jar", e.getMessage());
    }

    @Test
    void testNotJar() {
        String[] args = new String[]{"-jar", "src/test/resources/the.txt"};
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> new Arguments(args));
        assertEquals("Error: Invalid or corrupt jarfile src/test/resources/the.txt", e.getMessage());
    }

    @Test
    void testJar() {
        String[] args = new String[]{"-jar", "src/test/resources/bocchi.jar"};
        Arguments arguments = new Arguments(args);
        assertEquals("bocchi.the.rock.Bocchi", arguments.getMainClass());
    }

    @Test
    void testJarWithArguments() {
        String[] args = new String[]{"-jar", "src/test/resources/bocchi.jar", "Ryou", "Nijika", "Ikuyo"};
        Arguments arguments = new Arguments(args);
        assertEquals("bocchi.the.rock.Bocchi", arguments.getMainClass());
        assertArrayEquals(new String[]{"Ryou", "Nijika", "Ikuyo"}, arguments.getMainClassArgs());
    }
}
