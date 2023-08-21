import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Test {
    public static void main(String[] args) throws IOException {
        Files.copy(Path.of("C:\\testInformation\\photos\\в2 222228.jpg"),Path.of("C:\\testInformation\\photosMoveHere\\2.jpg"));
    }
}
