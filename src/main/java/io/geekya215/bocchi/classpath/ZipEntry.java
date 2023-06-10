package io.geekya215.bocchi.classpath;

import java.io.IOException;
import java.nio.file.*;

public final class ZipEntry implements Entry {
    private final Path path;

    public ZipEntry(String path) {
        this.path = Paths.get(path).toAbsolutePath();
    }

    @Override
    public byte[] readClass(String className) throws IOException {
        try (FileSystem zipFs = FileSystems.newFileSystem(path)) {
            return Files.readAllBytes(zipFs.getPath(className));
        }
    }
}
