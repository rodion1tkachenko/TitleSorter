package entity;

import lombok.AllArgsConstructor;

import java.nio.file.Path;
import java.util.List;
@AllArgsConstructor(staticName = "of")
public class Photo {
//    private Path absolutePath;
    private String fileName;
    private int variant;
    private List<Integer> solvedNumbers;

    public static Photo of(Path absolutePath, String fullPath, int variant, List<Integer> taskNumbers) {
        return new Photo(/*absolutePath,*/fullPath,variant,taskNumbers);
    }


    @Override
    public String toString() {
        return "entity.Photo{" +
//                "absolutePath=" + absolutePath +
                ", fileName='" + fileName + '\'' +
                ", variant=" + variant +
                ", solvedNumbers=" + solvedNumbers +
                '}';
    }

//    public Photo(/*Path absolutePath,*/String fullPath, int variant, List<Integer> solvedNumbers) {
//        this.absolutePath = absolutePath;
//        this.fileName = fullPath;
//        this.variant = variant;
//        this.solvedNumbers = solvedNumbers;
//    }

//    public Photo(String fileName, int variant, List<Integer> solvedNumbers) {
//        this.fileName = fileName;
//        this.variant = variant;
//        this.solvedNumbers = solvedNumbers;
//    }

    public String getFileName() {
        return fileName;
    }

    public int getVariant() {
        return variant;
    }

//    public Path getAbsolutePath() {
//        return absolutePath;
//    }

    public List<Integer> getSolvedNumbers() {
        return solvedNumbers;
    }

}
