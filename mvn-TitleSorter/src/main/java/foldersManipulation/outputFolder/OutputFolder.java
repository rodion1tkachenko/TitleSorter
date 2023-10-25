package foldersManipulation.outputFolder;

import foldersManipulation.ParentFolder;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class OutputFolder extends ParentFolder implements OutputFolderImp {

    public OutputFolder() {
        path=enterPath();
    }

    public Path enterPath() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the directory where the photos will be sorted -> ");
        String inputStringPath;
        boolean pathIsValid = false;
        while (!pathIsValid) {
            inputStringPath = scanner.nextLine();

            if (isDirectory(inputStringPath)) {
                path = Paths.get(inputStringPath);
                return path;
            } else {
                System.out.print("You entered not a output directory. Please try again -> ");
            }
            scanner.close();

        }

        return null;
    }


}
