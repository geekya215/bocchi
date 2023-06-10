package io.geekya215.bocchi.classpath;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class WildcardEntry extends CompositeEntry {
    public WildcardEntry(String path) {
        super(toPathList(path));
    }

    private static String toPathList(String wildcardPath) {
        try (Stream<Path> walk = Files.walk(Paths.get(wildcardPath.replace("*", "")))) {
            return walk.filter(Files::isRegularFile)
                .map(Path::toString)
                .filter(p -> p.endsWith(".jar") || p.endsWith(".JAR"))
                .collect(Collectors.joining(File.pathSeparator));
        } catch (IOException e) {
            return "";
        }
    }
}
