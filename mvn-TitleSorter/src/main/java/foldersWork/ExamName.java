package foldersWork;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLOutput;
import java.util.Scanner;
import java.util.regex.Pattern;

public class ExamName extends AbstractFolder {

    @Override
    public Path getDirectory() {
        System.out.print("Enter exam's name -> ");

        boolean isNameFound=false;
        while(!isNameFound){
            if (scanner.hasNextLine()) {
                String inputString = scanner.nextLine();
                if(Character.isDigit(inputString.toCharArray()[0])){
                    System.out.print("Your exam name starts with a digit. Please try again ->");
                }

                else if(Pattern.matches(".*\\p{InCyrillic}.*", inputString)){
                    System.out.print("You entered name with Cyrillic symbols. Please try again -> ");
                }
                else {
                    path = Paths.get(inputString);
                    isNameFound=true;
                }

            }
        }
        return path;


    }
}
