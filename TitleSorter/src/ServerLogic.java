import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ServerLogic {
    private  List<File> allFiles;
    private List<Photo>allPhotos;
    InputFolder inputFolder=new InputFolder();
    OutputFolder outputFolder=new OutputFolder();
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
        allPhotos.forEach(System.out::println);
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
    public void getOutputDirectory(){

    }
}
