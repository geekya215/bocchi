package io.geekya215.bocchi.classpath;

import java.io.File;
import java.io.IOException;

public interface Entry {
    byte[] readClass(String className) throws IOException;

    static Entry create(String path) {
        if (path.contains(File.pathSeparator)) {
            return new CompositeEntry(path);
        }

        if (path.endsWith("*")) {
            return new WildcardEntry(path);
        }

        if (path.endsWith(".jar") || path.endsWith(".JAR") ||
            path.endsWith(".zip") || path.endsWith(".ZIP")) {
            return new ZipEntry(path);
        }

        return new DirEntry(path);
    }
}
