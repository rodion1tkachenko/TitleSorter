package util;

import entity.Photo;
import service.ServerLogic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.*;

public class Statistic {
    HashMap<Integer, HashSet<Integer>>map=new HashMap<>();
    public void getStatistic(List<Photo> photoList, Path path) {
        for (int i=0;i<Helper.findOutAmountOfVariants();i++){
            map.put(i+1,new HashSet<>());
        }
        for(Photo photo:photoList){
            map.get(photo.getVariant()).addAll(photo.getTaskNumbers());
        }
        System.out.println(map);
        getLuckyVariants(path);
        getUnluckyVariants(path);
    }

    private void getUnluckyVariants(Path path) {
        ArrayList<Integer> hardestVariants=new ArrayList();
        int minTaskNumbers=Helper.findOutAmountOfNumbers();
        for (Map.Entry<Integer,HashSet<Integer>> entry : map.entrySet()) {
            if(entry.getValue().size()<minTaskNumbers){
                minTaskNumbers=entry.getValue().size();
                hardestVariants.clear();
                hardestVariants.add(entry.getKey());
            }

            else if(entry.getValue().size()==minTaskNumbers){
                hardestVariants.add(entry.getKey());
            }
        }
        //add method to create txt file and write info there
        System.out.print("The unluckiest variant is ");
        for (int variant:hardestVariants){
            System.out.print(variant+" ");
        }
        try (BufferedWriter bufferedWriter = new BufferedWriter
                (new FileWriter(path.toString() + "\\statictic.txt",true))) {
                bufferedWriter.write("The unluckiest variant is");
                bufferedWriter.write(hardestVariants.toString()+"\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private void getLuckyVariants(Path path) {
        ArrayList<Integer> easiestVariants=new ArrayList();
        int maxNumbers=0;
        for (Map.Entry<Integer,HashSet<Integer>> entry : map.entrySet()) {
            if(entry.getValue().size()>maxNumbers){
                maxNumbers=entry.getValue().size();
                easiestVariants.clear();
                easiestVariants.add(entry.getKey());
            }
            else if(entry.getValue().size()==maxNumbers){
                easiestVariants.add(entry.getKey());
            }
        }
        //add method to create txt file and write info there
        System.out.print("The luckiest variant is ");
        for (int variant:easiestVariants){
            System.out.print(variant+" ");
        }
        try (BufferedWriter bufferedWriter = new BufferedWriter
                (new FileWriter(path.toString() + "\\statictic.txt",true))) {
            bufferedWriter.write("The unluckiest variant is");
            bufferedWriter.write(easiestVariants.toString()+"\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
