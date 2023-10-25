package foldersManipulation.inputFolder;

import foldersManipulation.ParentFolder;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class InputFolder extends ParentFolder implements InputFolderImp {
    @Override
    public Path enterPath() {
        System.out.print("Enter input directory's path -> ");
        Scanner scanner = new Scanner(System.in);
        String inputStringPath = null;
        boolean pathIsValid = false;
        while (!pathIsValid) {
            if (scanner.hasNextLine()) {
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
