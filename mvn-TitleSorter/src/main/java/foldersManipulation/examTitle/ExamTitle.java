package foldersManipulation.examTitle;

import foldersManipulation.examTitle.ExamTitleImp;

import java.util.Scanner;
import java.util.regex.Pattern;

public class ExamTitle implements ExamTitleImp {
    private String title;

    public String getTitle() {
        return title;
    }

    public ExamTitle() {
        enterTitle();
    }

    private void enterTitle() {
        Scanner scanner=new Scanner(System.in);
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
                    title=inputString;
                    isNameFound=true;
                }

            }
        }
    }
}
