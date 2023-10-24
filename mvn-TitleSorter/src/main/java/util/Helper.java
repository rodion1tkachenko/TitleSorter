package util;

import java.io.File;
import java.util.List;

import entity.Photo;
import exception.FileHasNoExtensionException;
import exception.FileIsNullException;
import exception.ListOfPhotosIsNullException;
import exception.ListOfPhotoIsEmptyException;

public class Helper {
    public static int findOutAmountOfNumbers(List<Photo> photos) {
        if (photos == null) {
            throw new ListOfPhotosIsNullException();
        }
        if (photos.isEmpty()) {
            throw new ListOfPhotoIsEmptyException();
        }
        int lastNumber = 0;
        for (int i = 0; i < photos.size(); i++) {
            for (int j = 0; j < photos.get(i).getTaskNumbers().size(); j++) {
                if (photos.get(i).getTaskNumbers().get(j) > lastNumber) {
                    lastNumber = photos.get(i).getTaskNumbers().get(j);
                }
            }
        }
        return lastNumber;
    }
    public static int findOutAmountOfVariants(List<Photo> photos) {
        if (photos == null) {
            throw new ListOfPhotosIsNullException();
        }
        if (photos.isEmpty()) {
            throw new ListOfPhotoIsEmptyException();
        }
        int maxVariant = 0;
        for (int i = 0; i < photos.size(); i++) {
            int currentVariant = photos.get(i).getVariant();
            if (currentVariant > maxVariant) {
                maxVariant = currentVariant;
            }
        }
        return maxVariant;
    }
    public static String getFileExtension(File file) {
        if (file == null) {
            throw new FileIsNullException();
        }
        String fileName = file.getName();
        if (fileName.lastIndexOf('.') == -1 || fileName.lastIndexOf(".") == 0) {
            throw new FileHasNoExtensionException();
        }
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }
}
