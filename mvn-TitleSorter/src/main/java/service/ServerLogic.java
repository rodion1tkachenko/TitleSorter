package service;

import entity.MakingDirectoryData;
import entity.Photo;
import foldersManipulation.folderInformation.FolderInformation;
import foldersManipulation.folderInformation.FolderInformationImp;

import java.util.ArrayList;
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
//        scanPhotos();(1 step)
        allPhotos=folderChecker.scanPhotos(folderInformation.getInputPath());
//        savePhotoAtDatabase();
        makeDirectories();
        movePhotos();

//        TableActSTANCE.savePhotosAtDatabase(allPhotos, folderInformation.getTitle());ion.IN

//        HashMap<Integer, HashSet<Integer>> statistic = Statistic.getStatistic(allPhotos, outputDirectory);
//        Statistic.getLuckyVariants(statistic,outputDirectory);
//        Statistic.getUnluckyVariants(statistic,outputDirectory);
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

    public  List<Photo> getAllPhotos() {
        return allPhotos;
    }
}
