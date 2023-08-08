import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class OutputFolder implements DirectoryActions {
    private Path path;

    public Path getPath() {
        return path;
    }

    @Override
    public Path getDirectory() {
        System.out.print("Enter the directory where the photos will be sorted -> ");
        String inputStringPath;
        boolean pathIsValid=false;
            while(!pathIsValid) {
                inputStringPath = ServerLogic.scanner.nextLine();
                if(isDirectory(inputStringPath)){
                    path=Paths.get(inputStringPath);
                    return path;
                }
                else {
                    System.out.print("You entered not a output directory. Please try again -> ");
                }
            }

        return null;
    }

    public  boolean isDirectory(String userString){
        Path inputpath= Paths.get(userString);
        return Files.isDirectory(inputpath);

    }
}
