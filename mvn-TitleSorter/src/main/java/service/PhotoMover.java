package service;

import entity.MakingDirectoryData;
import entity.Photo;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class PhotoMover implements PhotoMoverImp{
    public void moveFiles(MakingDirectoryData data){
            for(Photo photo: data.getPhotoList()){
                for(int number: photo.getSolvedNumbers()){
                    tryToCopyPhotoInNewFolder(photo, number,
                            data.getFolderInformation().getOutputPath(),
                            data.getFolderInformation().getInputPath());
                }
            }

    }

//TODO:possible that no title directory and error then
    private  void tryToCopyPhotoInNewFolder(Photo photo, int number, Path outputDirectory,Path inputDirectory) {
        try {
            Files.copy(getAbsolutePath(inputDirectory,photo),
                makeNewPath(photo, number,outputDirectory));
        } catch (IOException e) {
            System.out.println("Exception when program tries to copy photo");
            throw new RuntimeException(e);
        }
    }
    private Path makeNewPath(Photo photo, int number,Path absolutePath){
        StringBuilder builder=new StringBuilder(absolutePath.toString());
        builder.append("\\Вариант ");
        builder.append(photo.getVariant());
        builder.append("\\Номер ");
        builder.append(number);
        builder.append(("\\"+photo.getFileName()));
        return Path.of( builder.toString());
    }
    private Path getAbsolutePath(Path path, Photo photo){
        return Path.of(path +"\\"+ photo.getFileName());

    }

}
