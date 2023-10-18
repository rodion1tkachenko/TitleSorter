package util;

import java.io.File;
import java.util.List;

import entity.Photo;
import service.ServerLogic;

public class Helper {
    public static int findOutAmountOfNumbers(List<Photo>photos){
        int lastNumber=1;
        for (int i = 0; i < photos.size(); i++) {
            for (int j = 0; j < photos.get(i).getTaskNumbers().size(); j++) {
                if(photos.get(i).getTaskNumbers().get(j)>lastNumber){
                    lastNumber= photos.get(i).getTaskNumbers().get(j);
                }
            }
        }
        return lastNumber;
    }
    public static int findOutAmountOfVariants(List<Photo>photos) {
        int maxVariant=1;
        for (int i = 0; i < photos.size(); i++) {
            int currentVariant= photos.get(i).getVariant();
            if(currentVariant>maxVariant){
                maxVariant=currentVariant;
            }
        }
        return maxVariant;
    }
    public static String getFileExtension(File file) {
        String fileName = file.getName();
        if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)

            return fileName.substring(fileName.lastIndexOf(".")+1);

        else return "";
    }
    public static void printAllPhotos(List list){
        for(Object object:list){
            System.out.println(object);
        }
    }
}
