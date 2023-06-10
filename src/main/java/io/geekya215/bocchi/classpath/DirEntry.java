package io.geekya215.bocchi.classpath;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public final class DirEntry implements Entry {
    private final Path path;

    public DirEntry(String classpath) {
        this.path = Paths.get(classpath).toAbsolutePath();
    }

    @Override
    public byte[] readClass(String className) throws IOException {
        return Files.readAllBytes(path.resolve(className));
    }
}
