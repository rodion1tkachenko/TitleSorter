import java.nio.file.Path;
import java.util.List;

public class Photo {
    private Path absolutePath;
    private String fullPath;
    private int variant;
    private List<Integer>taskNumbers;

    @Override
    public String toString() {
        return "Photo{" +
                "fullPath='" + fullPath + '\'' +
                ", variant=" + variant +
                ", taskNumbers=" + taskNumbers +
                '}';
    }

    public Photo(Path absolutePath, String fullPath, int variant, List<Integer> taskNumbers) {
        this.absolutePath = absolutePath;
        this.fullPath = fullPath;
        this.variant = variant;
        this.taskNumbers = taskNumbers;
    }

    public Photo(String fullPath, int variant, List<Integer> taskNumbers) {
        this.fullPath = fullPath;
        this.variant = variant;
        this.taskNumbers = taskNumbers;
    }

    public String getFullPath() {
        return fullPath;
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
