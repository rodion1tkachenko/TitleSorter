package foldersManipulation;

import java.nio.file.Files;
import java.nio.file.Path;

public abstract class ParentFolder {
    public Path path;

    public Path getPath() {
        return path;
    }

    public ParentFolder() {
        enterPath();
    }
    public abstract Path enterPath();
    public boolean isDirectory(String inputStringPath) {
        return Files.isDirectory(Path.of(inputStringPath));
    }


}
