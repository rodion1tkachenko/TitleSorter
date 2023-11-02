package util;

import entity.Photo;
import enums.InformationType;
import exception.NotWrittenToFileException;
import exception.ListOfPhotosIsNullException;
import exception.PathIsNullException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.*;

public class Statistic {

    public static HashMap<Integer, HashSet<Integer>> getStatistic(List<Photo> photoList, Path path) {
        if (photoList == null) {
            throw new ListOfPhotosIsNullException();
        } else if (path == null) {
            throw new PathIsNullException();
        }
        HashMap<Integer, HashSet<Integer>> map = new HashMap<>();
        for (int i = 0; i < Helper.findAmountOfVariants(photoList); i++) {
            map.put(i + 1, new HashSet<>());
        }
        for (Photo photo : photoList) {
            map.get(photo.getVariant()).addAll(photo.getSolvedNumbers());
        }
        System.out.println(map);
        return map;
    }

    public static List<Integer> getLuckyVariants(HashMap<Integer, HashSet<Integer>> map, Path path) {
        List<Integer> easiestVariants = new ArrayList();
        int maxNumbers = 0;
        for (Map.Entry<Integer, HashSet<Integer>> entry : map.entrySet()) {
            if (entry.getValue().size() > maxNumbers) {
                maxNumbers = entry.getValue().size();
                easiestVariants.clear();
                easiestVariants.add(entry.getKey());
            } else if (entry.getValue().size() == maxNumbers) {
                easiestVariants.add(entry.getKey());
            }
        }
//        add method to create txt file and write info there
        if (!InfoWriter.writeToFile(InformationToWrite
                .of(path, easiestVariants, InformationType.LUCKY))) {
            throw new NotWrittenToFileException();
        }
        return easiestVariants;
    }

    class InfoWriter {
        private static final String LUCKIEST_VARIANT = "the luckiest variant is ";
        private static final String UNLUCKIEST_VARIANT = "the unluckiest variant is ";

        private static boolean writeToFile(InformationToWrite information) {
            try (BufferedWriter bufferedWriter = new BufferedWriter
                    (new FileWriter(information.getPath().toString() + "\\statictic.txt", true))) {
                if (information.getInformationType() == InformationType.LUCKY) {
                    bufferedWriter.write(LUCKIEST_VARIANT);
                } else {
                    bufferedWriter.write(UNLUCKIEST_VARIANT);
                }
                bufferedWriter.write(information.getVariants() + "\n");
                return true;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Getter
    @AllArgsConstructor(staticName = "of")
    static class InformationToWrite {
        private Path path;
        private List<Integer> variants;
        private InformationType informationType;
    }

    public static List<Integer> getUnluckyVariants(HashMap<Integer, HashSet<Integer>> map, Path path) {
        if (map.isEmpty()) {
            throw new NullPointerException();
        }
        List<Integer> hardestVariants = new ArrayList<>();
        int minTaskNumbers = Integer.MAX_VALUE;
        for (Map.Entry<Integer, HashSet<Integer>> entry : map.entrySet()) {
            if (entry.getValue().size() < minTaskNumbers) {
                minTaskNumbers = entry.getValue().size();
                hardestVariants.clear();
                hardestVariants.add(entry.getKey());
            } else if (entry.getValue().size() == minTaskNumbers) {
                hardestVariants.add(entry.getKey());
            }
        }
        if (!InfoWriter.writeToFile(InformationToWrite.of(path, hardestVariants, InformationType.UNLUCKY))) {
            throw new NotWrittenToFileException();
        }
        return hardestVariants;
    }
}
