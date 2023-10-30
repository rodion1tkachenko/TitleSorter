package service;

import foldersManipulation.folderInformation.FolderInformation;
import foldersManipulation.folderInformation.FolderInformationImp;
import lombok.AllArgsConstructor;
import util.Helper;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import entity.Photo;
import util.TableAction;

public class ServerLogic {
    private List<Photo>allPhotos;
    private FolderInformationImp folderInformation;
    private FolderChecker folderChecker;
    private DirectoryMakerImp directoryMaker;

    public ServerLogic() {
        allPhotos=new ArrayList<>();
        folderInformation=new FolderInformation();
        directoryMaker=new DirectoryMaker();
    }

    public void run(){
//        scanPhotos();(1 step)
        allPhotos=folderChecker.scanPhotos();
//        savePhotoAtDatabase();
        TableAction.INSTANCE.savePhotosAtDatabase(allPhotos, folderInformation.getTitle());

        //        sendToTheDesiredFolders();
//        HashMap<Integer, HashSet<Integer>> statistic = Statistic.getStatistic(allPhotos, outputDirectory);
//        Statistic.getLuckyVariants(statistic,outputDirectory);
//        Statistic.getUnluckyVariants(statistic,outputDirectory);
    }

    public  List<Photo> getAllPhotos() {
        return allPhotos;
    }
}
