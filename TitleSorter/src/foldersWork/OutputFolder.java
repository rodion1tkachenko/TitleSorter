package foldersWork;

import java.nio.file.Path;
import java.nio.file.Paths;

public class OutputFolder extends AbstractFolder {

    @Override
    public Path getDirectory() {
            System.out.print("Enter the directory where the photos will be sorted -> ");
            String inputStringPath;
            boolean pathIsValid=false;
            while(!pathIsValid) {
                    inputStringPath = scanner.nextLine();

                if(isDirectory(inputStringPath)){
                    path=Paths.get(inputStringPath);
                    return path;
                }
                else {
                    System.out.print("You entered not a output directory. Please try again -> ");
                }
                scanner.close();

        }
        return null;
    }

}
