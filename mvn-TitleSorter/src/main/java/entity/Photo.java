package entity;

import lombok.AllArgsConstructor;

import java.nio.file.Path;
import java.util.List;

public class Photo {
    private Path absolutePath;
    private String fileName;
    private int variant;
    private List<Integer>taskNumbers;
    public static Photo of(Path absolutePath, String fullPath, int variant, List<Integer> taskNumbers) {
        return new Photo(absolutePath,fullPath,variant,taskNumbers);
    }

    @Override
    public String toString() {
        return "entity.Photo{" +
                "absolutePath=" + absolutePath +
                ", fileName='" + fileName + '\'' +
                ", variant=" + variant +
                ", taskNumbers=" + taskNumbers +
                '}';
    }

    public Photo(Path absolutePath, String fullPath, int variant, List<Integer> taskNumbers) {
        this.absolutePath = absolutePath;
        this.fileName = fullPath;
        this.variant = variant;
        this.taskNumbers = taskNumbers;
    }

    public Photo(String fullPath, int variant, List<Integer> taskNumbers) {
        this.fileName = fullPath;
        this.variant = variant;
        this.taskNumbers = taskNumbers;
    }

    public String getFileName() {
        return fileName;
    }

    public int getVariant() {
        return variant;
    }

    public Path getAbsolutePath() {
        return absolutePath;
    }

    public List<Integer> getTaskNumbers() {
        return taskNumbers;
    }

}
