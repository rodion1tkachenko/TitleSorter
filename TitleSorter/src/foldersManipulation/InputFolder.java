package foldersManipulation;

import java.nio.file.Path;
import java.nio.file.Paths;

public class InputFolder extends AbstractFolder {

    @Override
    public Path getDirectory(){
            System.out.print("Enter input directory's path -> ");
            String inputStringPath=null;
            boolean pathIsValid = false;
            while (!pathIsValid) {
                if(scanner.hasNextLine()) {
                    inputStringPath = scanner.nextLine();
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

}
