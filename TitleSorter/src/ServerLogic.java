import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ServerLogic {
    private  List<File> allPhotos =new ArrayList<>();
    public  void iterateInDirectory(Path path){
        File[]files=path.toFile().listFiles();
        allPhotos.addAll(Arrays.asList(files));
        for (int i = 0; i < allPhotos.size(); i++) {
            String fullName=allPhotos.get(i).getName();

        }
    }
    public int getVariant(String fullString){
        String firstWord= fullString.split(" ")[0];
        System.out.println(firstWord);
        return 0;
    }
}
