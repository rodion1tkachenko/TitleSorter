package foldersManipulation.folderInformation;

import foldersManipulation.examTitle.ExamTitle;
import foldersManipulation.inputFolder.InputFolder;
import foldersManipulation.outputFolder.OutputFolder;

import java.nio.file.Path;

public interface FolderInformationImp {
    public Path getInputPath();
    public Path getOutputPath();
    public String getTitle();
    public void setInputFolder(InputFolder inputFolder);
    public void setOutputFolder(OutputFolder outputFolder);
    public void setExamTitle(ExamTitle examTitle);
}
