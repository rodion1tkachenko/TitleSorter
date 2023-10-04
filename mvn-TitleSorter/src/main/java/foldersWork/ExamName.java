package foldersWork;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class ExamName extends AbstractFolder {

    @Override
    public Path getDirectory() {
        System.out.print("Enter exam's name -> ");
        String inputStringPath = null;

        if (scanner.hasNextLine()) {
            inputStringPath = scanner.nextLine();
            path = Paths.get(inputStringPath);

        }
        return path;


    }
}
