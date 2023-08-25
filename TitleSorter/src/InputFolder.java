import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class InputFolder implements DirectoryActions {
    private Path path;
    @Override
    public Path getDirectory(){
            System.out.print("Enter input directory's path -> ");
            String inputStringPath=null;
            boolean pathIsValid = false;
            while (!pathIsValid) {
                if(ServerLogic.scanner.hasNextLine()) {
                    inputStringPath = ServerLogic.scanner.nextLine();
                }
                if (isDirectory(inputStringPath)) {
                    path = Paths.get(inputStringPath);
                    return path;
                } else {
                    System.out.print("You entered not a directory. Please try again -> ");
                }

        }
        return null;
    }
    @Override
    public boolean isDirectory(String userString){
        Path inputpath= Paths.get(userString);
        return Files.isDirectory(inputpath);
    }

    public Path getPath() {
        return path;
    }
}
