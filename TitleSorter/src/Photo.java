import java.util.List;

public class Photo {
    private String fullPath;
    private int variant;
    private List<Integer>taskNumbers;

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

    public List<Integer> getTaskNumbers() {
        return taskNumbers;
    }

}
