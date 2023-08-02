import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class InputFolder implements DirectoryActions {
    @Override
    public Path getDirectory(){
        System.out.print("Enter input directory's path -> ");
        String inputStringPath;
        boolean pathIsValid=false;
        try(Scanner scanner=new Scanner(System.in)){
            while(!pathIsValid) {
                inputStringPath = scanner.nextLine();
                if(isDirectory( inputStringPath)){
                    return Paths.get(inputStringPath);
                }
                else {
                    System.out.print("You entered not a directory. Please try again -> ");
                }
            }
        }
        return null;
    }
    @Override
    public boolean isDirectory(String userString){
        Path inputpath= Paths.get(userString);
        return Files.isDirectory(inputpath);
    }
}
