package service;

import entity.MakingDirectoryData;
import entity.Photo;
import foldersManipulation.examTitle.ExamTitle;
import foldersManipulation.folderInformation.FolderInformationImp;
import util.Helper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DirectoryMaker implements DirectoryMakerImp {
//    private void makeDirectories(MakingDirectoryData data,int i){
//        Path path = tryToCreateExamDirectory(data.getFolderInformation());
//        for (int i = 0; i < Helper.findAmountOfVariants(data.getPhotoList()); i++) {
//            try {
//                String dirFullName= path +"\\Вариант "+(i+1);
//                if(!new File(dirFullName).exists()) {
//                    Files.createDirectory(Paths.get(dirFullName));
//                }
//                for (int j = 0; j < Helper.findOutAmountOfNumbers(data.getPhotoList()); j++) {
//                    if(!new File(dirFullName+"\\Номер "+(j+1)).exists()) {
//                        Files.createDirectory(Paths.get(dirFullName + "\\Номер " + (j + 1)));
//                    }
//                }
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        }
//    }
    public void makeDirectories(MakingDirectoryData data){
        makeVariantDirectories(data);
//        makeTaskDirectories();
//        Path path = tryToCreateExamDirectory(data.getFolderInformation());
//        for (int i = 0; i < Helper.findAmountOfVariants(data.getPhotoList()); i++) {
//            try {
//                String dirFullName= path +"\\Вариант "+(i+1);
//                if(!new File(dirFullName).exists()) {
//                    Files.createDirectory(Paths.get(dirFullName));
//                }
//                for (int j = 0; j < Helper.findOutAmountOfNumbers(data.getPhotoList()); j++) {
//                    if(!new File(dirFullName+"\\Номер "+(j+1)).exists()) {
//                        Files.createDirectory(Paths.get(dirFullName + "\\Номер " + (j + 1)));
//                    }
//                }
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        }
    }
    //original
    private void makeVariantDirectories(MakingDirectoryData data) {
        for (int i = 0; i < Helper.findAmountOfVariants(data.getPhotoList()); i++) {
            try {
                Path pathWithVariant = Path.of( data.getPath() +"\\Вариант "+(i+1));
                if(!new File(pathWithVariant.toString()).exists()) {
                    Files.createDirectory(pathWithVariant);
                }
                makeTaskDirectories(pathWithVariant,
                        Helper.findOutAmountOfNumbers(data.getPhotoList()));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }


    private void makeTaskDirectories(Path path,int taskAmount) throws IOException {
        for (int j = 0; j < taskAmount; j++) {
            if(!new File(path+"\\Номер "+(j+1)).exists()) {
                Files.createDirectory(Paths.get(path + "\\Номер " + (j + 1)));
            }
        }
    }


//    private Path tryToCreateExamDirectory(FolderInformationImp folderInformation) {
//        Path path=makeDirWithExamName(folderInformation);
//        try {
//            Files.createDirectory(path);
//        } catch (IOException e) {
//            System.out.println("directory "+path +"  creation error");
//            throw new RuntimeException(e);
//        }
//        return path;
//    }





}
