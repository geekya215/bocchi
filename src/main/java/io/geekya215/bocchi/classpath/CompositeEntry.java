package io.geekya215.bocchi.classpath;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CompositeEntry implements Entry {
    private final List<Entry> entries = new ArrayList<>();

    public CompositeEntry(String paths) {
        for (String path : paths.split(File.pathSeparator)) {
            entries.add(Entry.create(path));
        }
    }

    @Override
    public byte[] readClass(String className) throws IOException {
        for (Entry entry : entries) {
            try {
                return entry.readClass(className);
            } catch (IOException e) {
                // ignore
            }
        }
        throw new IOException("class not found " + className);
    }
}
