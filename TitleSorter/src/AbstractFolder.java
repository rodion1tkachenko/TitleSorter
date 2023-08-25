import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public abstract class AbstractFolder {
    private Path path;

    public abstract Path getDirectory();

    public boolean isDirectory(String userString){
        Path inputpath= Paths.get(userString);
        return Files.isDirectory(inputpath);
    }

    public Path getPath() {
        return path;
    }
}
