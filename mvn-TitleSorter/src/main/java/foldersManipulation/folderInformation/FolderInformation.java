package foldersManipulation.folderInformation;

import foldersManipulation.inputFolder.InputFolder;
import foldersManipulation.outputFolder.OutputFolder;
import foldersManipulation.examTitle.ExamTitle;
import lombok.Getter;
import lombok.Setter;

import java.nio.file.Path;


public class FolderInformation implements FolderInformationImp {
    private InputFolder inputFolder;
    private OutputFolder outputFolder;
    private ExamTitle examTitle;

    public void setInputFolder(InputFolder inputFolder) {
        this.inputFolder = inputFolder;
    }

    public void setOutputFolder(OutputFolder outputFolder) {
        this.outputFolder = outputFolder;
    }

    public void setExamTitle(ExamTitle examTitle) {
        this.examTitle = examTitle;
    }

    public Path getInputPath(){
        return inputFolder.getPath();
    }
    public Path getOutputPath(){
        return outputFolder.getPath();
    }
    public String getTitle(){
        return examTitle.getTitle();
    }
    public FolderInformation() {
        inputFolder=new InputFolder();
        outputFolder=new OutputFolder();
        examTitle=new ExamTitle();
    }

//    private Path enterOuputPath() {
//        Scanner scanner = new Scanner(System.in);
//        System.out.print("Enter the directory where the photos will be sorted -> ");
//        String inputStringPath;
//        boolean pathIsValid = false;
//        while (!pathIsValid) {
//            inputStringPath = scanner.nextLine();
//
//            if (Files.isDirectory(Path.of(inputStringPath))) {
//                outputPath = Paths.get(inputStringPath);
//                return outputPath;
//            } else {
//                System.out.print("You entered not a output directory. Please try again -> ");
//            }
//        }
//        return null;
//    }
//    private Path enterInputPath() {
//        System.out.print("Enter input directory's path -> ");
//        Scanner scanner = new Scanner(System.in);
//        String inputStringPath = null;
//        boolean pathIsValid = false;
//        while (!pathIsValid) {
//            if (scanner.hasNextLine()) {
//                inputStringPath = scanner.nextLine();
//            }
//            Path maybePath = Paths.get(inputStringPath);
//            if (Files.isDirectory(maybePath)) {
//                inputPath = maybePath;
//                return inputPath;
//            } else {
//                System.out.print("You entered not a directory. Please try again -> ");
//            }
//        }
//        return null;
//    }
}
