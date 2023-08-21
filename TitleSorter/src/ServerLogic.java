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
    private  List<File> allFiles;
    private static List<Photo>allPhotos;
     InputFolder inputFolder=new InputFolder();
     OutputFolder outputFolder=new OutputFolder();
     static Scanner scanner=new Scanner(System.in);
    public  void scanPhotos(Path path){
        File[]files=path.toFile().listFiles();
        allFiles =new ArrayList<>();
        allPhotos=new ArrayList<>();
        allFiles.addAll(Arrays.asList(files));
        for (int i = 0; i < allFiles.size(); i++) {
            String fullName= allFiles.get(i).getName();
            String[]splitedString=fullName.split(" ");
            int variant=getVariant(splitedString);
            allPhotos.add(new Photo(fullName,variant, getNumbers(splitedString)));
        }

    }
    public void sendToTheDesiredFolders(){
        makeDirectories(outputFolder.getDirectory());
        moveFiles();
        
    }
    private void moveFiles(){
        for(Photo photo: allPhotos){
            for(int number: photo.getTaskNumbers()){
                System.out.println(makeNewPath(photo, number));
            }
        }
        //123
    }
    private String makeNewPath(Photo photo,int number){
        StringBuilder builder=new StringBuilder(outputFolder.getPath().toString());
        builder.append("\\Вариант ");
        builder.append(photo.getVariant());
        builder.append("\\Номер ");
        builder.append(number);
        builder.append(("\\"+photo.getFullPath()));
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
                    Files.createDirectory(Paths.get(dirFullName+"\\Номер "+(j+1)));
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
}
