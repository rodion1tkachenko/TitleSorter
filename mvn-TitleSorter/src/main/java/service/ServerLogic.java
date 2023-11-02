package service;

import entity.MakingDirectoryData;
import entity.Photo;
import foldersManipulation.folderInformation.FolderInformation;
import foldersManipulation.folderInformation.FolderInformationImp;
import util.Statistic;
import util.TableAction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class ServerLogic {
    private List<Photo>allPhotos;
    private FolderInformationImp folderInformation;
    private FolderChecker folderChecker;
    private DirectoryMakerImp directoryMaker;
    private PhotoMoverImp photoMover;

    public ServerLogic() {
        allPhotos=new ArrayList<>();
        folderChecker=new FolderChecker();
        folderInformation=new FolderInformation();
        photoMover=new PhotoMover();
        directoryMaker=new DirectoryMaker();
    }

    public void run(){
        getPhotosFromInputFolder();
        savePhotosAtDatabase();
        makeDirectories();
        movePhotos();
        writeStatisticInFile();
    }

    private void savePhotosAtDatabase() {
        TableAction.INSTANCE.savePhotosAtDatabase(allPhotos,folderInformation.getTitle());
    }

    private void getPhotosFromInputFolder() {
        allPhotos=folderChecker.scanPhotos(folderInformation.getInputPath());
    }

    private void writeStatisticInFile() {
        HashMap<Integer, HashSet<Integer>> statistic = Statistic.getStatistic(allPhotos, folderInformation.getOutputPath());
        Statistic.getLuckyVariants(statistic,folderInformation.getOutputPath());
        Statistic.getUnluckyVariants(statistic,folderInformation.getOutputPath());
    }

    private void movePhotos() {
        photoMover.moveFiles(MakingDirectoryData.of(allPhotos,folderInformation,folderInformation.getOutputPath()));
    }

    //TODO: change method parameters
    private void makeDirectories() {
        directoryMaker.makeDirectories(
                MakingDirectoryData.of(allPhotos,folderInformation,folderInformation.getOutputPath())
        );
    }

}
