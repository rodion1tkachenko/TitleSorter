import foldersManipulation.InputFolder;
import foldersManipulation.OutputFolder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ServerLogic {
    private static List<Photo>allPhotos;
    private InputFolder inputFolder=new InputFolder();
    private OutputFolder outputFolder=new OutputFolder();

    public void run(){
        scanPhotos(getInputFolder().getDirectory());
        sendToTheDesiredFolders();
    }

    public  void scanPhotos(Path path){
        File[]files=path.toFile().listFiles();
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
        makeDirectories(outputFolder.getDirectory());
        moveFiles();
    }
    private void makeDirectories(Path path){
        for (int i = 0; i < Helper.findOutAmountOfVariants(); i++) {
            try {
                String dirFullName=path.toString()+"\\Вариант "+(i+1);
                if(!new File(dirFullName).exists()) {
                    Files.createDirectory(Paths.get(dirFullName));
                }
                for (int j = 0; j < Helper.findOutAmountOfNumbers(); j++) {
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
        //123
    }

    private Path makeNewPath(Photo photo,int number){
        StringBuilder builder=new StringBuilder(outputFolder.getPath().toString());
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
    private ArrayList<Integer> getNumbers(String[] splitedString){
        String substringWithBrackets= null;
            String neccesaryString=splitedString[1];
            if(neccesaryString.contains(".")){
                substringWithBrackets = neccesaryString.substring(0,neccesaryString.indexOf('.'));
            }
            else{
                substringWithBrackets=splitedString[1];
            }
        return getNumbersFromStringWithBrackets(substringWithBrackets);
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
 
    public static List<Photo> getAllPhotos() {
        return allPhotos;
    }

    public InputFolder getInputFolder() {
        return inputFolder;
    }

    public OutputFolder getOutputFolder() {
        return outputFolder;
    }
}
