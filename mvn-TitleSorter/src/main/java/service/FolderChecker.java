package service;

import entity.Photo;
import lombok.Getter;
import util.Helper;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
@Getter
public class FolderChecker {
    private VariantChecker variantChecker;
    private TasksChecker tasksChecker;

    public FolderChecker() {
        variantChecker=new VariantChecker();
        tasksChecker=new TasksChecker();

    }

    public List<Photo> scanPhotos(Path inputPath){
        File[]files=inputPath.toFile().listFiles();
        List<Photo>photos=new ArrayList<>();
        for(File oneFile:files) {
            if (Files.isDirectory(oneFile.toPath())) {
                continue;
            }
            String extension = Helper.getFileExtension(oneFile);
            if (extension.equals("jpg") || extension.equals("jpeg")) {
                String fullName = oneFile.getName();
                int variant = variantChecker.determineVariant(fullName);
                List<Integer> numbers =tasksChecker.determineTasks(fullName);
                photos.add(Photo.of(fullName, variant, numbers));
            }
        }
        return photos;
    }
}
