import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ServerLogic {

    private static List<Photo>allPhotos;
     private InputFolder inputFolder=new InputFolder();
     private OutputFolder outputFolder=new OutputFolder();
     static Scanner scanner=new Scanner(System.in);
    public  void scanPhotos(Path path){
        //плохо работает с файлами с неправильным форматом( т.е. папки выдают ошибки)
        File[]files=path.toFile().listFiles();
        allPhotos=new ArrayList<>();
        for(File oneFile:files){
            if(Files.isDirectory(oneFile.toPath())){
                continue;
            }
            String fullName= oneFile.getName();
            String[]splitedString=fullName.split(" ");
            int variant=getVariant(splitedString);
            allPhotos.add(new Photo(Paths.get(oneFile.getAbsolutePath()),
                    fullName,variant, getNumbers(splitedString)));
        }


    }
    public void sendToTheDesiredFolders(){
        makeDirectories(outputFolder.getDirectory());
        moveFiles();
        
    }
    private void moveFiles(){
        try {
            for(Photo photo: allPhotos){
                for(int number: photo.getTaskNumbers()){
                    Files.copy(photo.getAbsolutePath(),Paths.get(makeNewPath(photo, number)));
                }
            }
        } catch (IOException e) {
            e.getStackTrace();
        }
        //123
    }
    private String makeNewPath(Photo photo,int number){
        StringBuilder builder=new StringBuilder(outputFolder.getPath().toString());
        builder.append("\\Вариант ");
        builder.append(photo.getVariant());
        builder.append("\\Номер ");
        builder.append(number);
        builder.append(("\\"+photo.getFileName()));
        return builder.toString();
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
    private int getVariant(String[] splitedString){
        String stringVariant=splitedString[0].substring(splitedString[0].indexOf("в")+1);
        return Integer.parseInt(stringVariant);
    }
    private ArrayList<Integer> getNumbers(String[] splitedString){
        String neccesaryString=splitedString[1];
        String substringWithBrackets=neccesaryString.substring(0,neccesaryString.indexOf('.'));
        /*выдает баг при строке где больше 1 пробела!!*/
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
