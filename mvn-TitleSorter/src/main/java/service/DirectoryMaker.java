package service;

import entity.MakingDirectoryData;
import entity.Photo;
import foldersManipulation.folderInformation.FolderInformationImp;
import util.Helper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DirectoryMaker {
    public void sendToTheDesiredFolders(MakingDirectoryData data){
        makeDirectories(data);
        moveFiles();
    }
    private void makeDirectories(MakingDirectoryData data){
        Path path = tryToCreateExamDirectory(data.getFolderInformation());
        for (int i = 0; i < Helper.findAmountOfVariants(data.getPhotoList()); i++) {
            try {
                String dirFullName= path +"\\Вариант "+(i+1);
                if(!new File(dirFullName).exists()) {
                    Files.createDirectory(Paths.get(dirFullName));
                }
                for (int j = 0; j < Helper.findOutAmountOfNumbers(data.getPhotoList()); j++) {
                    if(!new File(dirFullName+"\\Номер "+(j+1)).exists()) {
                        Files.createDirectory(Paths.get(dirFullName + "\\Номер " + (j + 1)));
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    private Path tryToCreateExamDirectory(FolderInformationImp folderInformation) {
        Path path=makeDirWithExamName(folderInformation);
        try {
            Files.createDirectory(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return path;
    }
    private Path makeDirWithExamName(FolderInformationImp folderInformation){
        return Paths.get(folderInformation.getOutputPath().toString()
                +"\\" +folderInformation.getTitle());
    }

    private void moveFiles(){
        try {
            for(Photo photo: getAllPhotos()){
                for(int number: photo.getSolvedNumbers()){
                    Files.copy(photo.getAbsolutePath(),makeNewPath(photo, number));
                }
            }
        } catch (IOException e) {
            e.getStackTrace();
        }
    }

    private Path makeNewPath(Photo photo, int number){
        StringBuilder builder=new StringBuilder(makeDirWithExamName().toString());
        builder.append("\\Вариант ");
        builder.append(photo.getVariant());
        builder.append("\\Номер ");
        builder.append(number);
        builder.append(("\\"+photo.getFileName()));
        return Paths.get( builder.toString());
    }
}
