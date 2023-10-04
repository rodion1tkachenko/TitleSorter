package util;

import java.io.File;
import java.util.List;

import service.ServerLogic;

public class Helper {
    public static int findOutAmountOfNumbers(){
        int lastNumber=1;
        for (int i = 0; i < ServerLogic.getAllPhotos().size(); i++) {
            for (int j = 0; j < ServerLogic.getAllPhotos().get(i).getTaskNumbers().size(); j++) {
                if(ServerLogic.getAllPhotos().get(i).getTaskNumbers().get(j)>lastNumber){
                    lastNumber= ServerLogic.getAllPhotos().get(i).getTaskNumbers().get(j);
                }
            }
        }
        return lastNumber;
    }
    public static int findOutAmountOfVariants() {
        int maxVariant=1;
        for (int i = 0; i < ServerLogic.getAllPhotos().size(); i++) {
            int currentVariant= ServerLogic.getAllPhotos().get(i).getVariant();
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
