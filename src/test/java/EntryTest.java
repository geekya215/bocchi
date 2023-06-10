import io.geekya215.bocchi.classpath.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EntryTest {
    Path workdir;

    @BeforeEach
    void setup() {
        workdir = Path.of("src/test/resources");
    }

    @Test
    void testDirEntry() throws IOException {
        Entry entry = Entry.create(workdir.toString());
        assertEquals(DirEntry.class, entry.getClass());
        byte[] bytecode = entry.readClass("Bocchi.class");
        Assertions.assertNotNull(bytecode);
    }

    @Test
    void testZipEntry() throws IOException {
        Entry entry = Entry.create(workdir.resolve("bocchi.jar").toString());
        assertEquals(ZipEntry.class, entry.getClass());
        byte[] bytecode = entry.readClass("bocchi/the/rock/Bocchi.class");
        Assertions.assertNotNull(bytecode);
    }

    @Test
    void testCompositeEntry() throws IOException {
        String path1 = "src/test/java";
        String path2 = "src/test/resources";
        Entry entry = Entry.create(String.join(File.pathSeparator, path1, path2));
        assertEquals(CompositeEntry.class, entry.getClass());
        byte[] bytecode = entry.readClass("Bocchi.class");
        Assertions.assertNotNull(bytecode);
    }

    @Test
    void testWildcardEntry() throws IOException {
        Entry entry = Entry.create("src/test/resources/*");
        assertEquals(WildcardEntry.class, entry.getClass());
        byte[] bytecode = entry.readClass("bocchi/the/rock/Bocchi.class");
        Assertions.assertNotNull(bytecode);
    }

    @Test
    void testCompositeEntryPrior1() throws IOException {
        String path1 = "src/test/resources/*";
        String path2 = "src/test/resources";
        Entry entry = Entry.create(String.join(File.pathSeparator, path1, path2));
        assertEquals(CompositeEntry.class, entry.getClass());
        byte[] bytecode = entry.readClass("Bocchi.class");
        Assertions.assertNotNull(bytecode);
    }

    @Test
    void testCompositeEntryPrior2() throws IOException {
        String path1 = "src/test/resources/*";
        String path2 = "src/test/resources";
        Entry entry = Entry.create(String.join(File.pathSeparator, path1, path2));
        assertEquals(CompositeEntry.class, entry.getClass());
        byte[] bytecode = entry.readClass("bocchi/the/rock/Bocchi.class");
        Assertions.assertNotNull(bytecode);
    }
}
