package service;
import dao.PhotoDao;
import foldersWork.ExamName;
import foldersWork.InputFolder;
import foldersWork.OutputFolder;
import util.Helper;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import entity.Photo;
import util.Statistic;
import util.TableAction;

public class ServerLogic {
    private List<Photo>allPhotos;
    private Path inputDirectory;
    private Path outputDirectory;
    private String examTitle ;
    public void run(){
        getDirectories();
        scanPhotos();
        savePhotoAtDatabase();
        sendToTheDesiredFolders();
        HashMap<Integer, HashSet<Integer>> statistic = Statistic.getStatistic(allPhotos, outputDirectory);
        Statistic.getLuckyVariants(statistic,outputDirectory);
        Statistic.getUnluckyVariants(statistic,outputDirectory);
    }
    //private*
    public void getDirectories() {
        examTitle= new ExamName().getDirectory().toString();
        inputDirectory= new InputFolder().getDirectory();
        outputDirectory=new OutputFolder().getDirectory();
    }
    private void savePhotoAtDatabase() {
        TableAction.INSTANCE.create(createTableName());
        PhotoDao photoDao = PhotoDao.getInstance();
        for(Photo photo: allPhotos){
            photoDao.save(photo,createTableName());
        }
    }
    String createTableName(){
        return examTitle.replaceAll("[ -]","_");
    }

    public void scanPhotos(){
        File[]files=inputDirectory.toFile().listFiles();
        allPhotos=new ArrayList<>();
        for(File oneFile:files) {
            if (Files.isDirectory(oneFile.toPath())) {
                continue;
            }
            String extension = Helper.getFileExtension(oneFile);
            if (extension.equals("jpg") || extension.equals("jpeg")) {
                String fullName = oneFile.getName();
                int variant = getVariant(fullName);
                List<Integer> numbers = getNumbers(fullName);
                allPhotos.add(new Photo(Paths.get(oneFile.getAbsolutePath()),
                        fullName, variant, numbers));
            }
        }
    }
    public void sendToTheDesiredFolders(){
        makeDirectories();
        moveFiles();
    }
    private Path makeDirWithExamName(){
        return Paths.get( outputDirectory.toString()+"\\" +examTitle);
    }

    private void makeDirectories(){
        Path path=makeDirWithExamName();
        try {
            Files.createDirectory(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (int i = 0; i < Helper.findOutAmountOfVariants(allPhotos); i++) {
            try {
                String dirFullName=path.toString()+"\\Вариант "+(i+1);
                if(!new File(dirFullName).exists()) {
                    Files.createDirectory(Paths.get(dirFullName));
                }
                for (int j = 0; j < Helper.findOutAmountOfNumbers(allPhotos); j++) {
                    if(!new File(dirFullName+"\\Номер "+(j+1)).exists()) {
                        Files.createDirectory(Paths.get(dirFullName + "\\Номер " + (j + 1)));
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    private void moveFiles(){
        try {
            for(Photo photo: getAllPhotos()){
                for(int number: photo.getTaskNumbers()){
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


    private int getVariant(String inputString){
        String result=inputString.substring(inputString.indexOf("в")+1,inputString.indexOf(' '));
        return Integer.parseInt(result);
    }
    private ArrayList<Integer> getNumbers(String string) {
        String[]splitedString=string.split(" ");
        String substringWithBrackets;
        String neccesaryString=splitedString[1];
        if(neccesaryString.contains(".")){
            substringWithBrackets = neccesaryString.substring(0,neccesaryString.indexOf('.'));
        }
        else{
            substringWithBrackets=splitedString[1];
        }
        return getNumbersFromStringWithBrackets(substringWithBrackets);

    }

    private ArrayList<Integer> getNumbersFromStringWithBrackets(String bracketsString){
        ArrayList<Integer>result=new ArrayList<>();
        try {
            for (char num : bracketsString.toCharArray()) {
                result.add(Integer.parseInt(num + ""));
            }
        }
        catch (NumberFormatException exception){
            return result;
        }
        return result;
    }
    public  List<Photo> getAllPhotos() {
        return allPhotos;
    }

    public String getExamTitle() {
        return examTitle;
    }
}
