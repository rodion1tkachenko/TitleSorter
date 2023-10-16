package util;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class StatWriter {
    private static final String ADD_LUCKY ="""
The luckiest variants is 
""";

    // заменить на are/is в разных случаях
    BufferedWriter bufferedWriter;


    public StatWriter() {
    }

    public StatWriter(Path path) {
        try {
            bufferedWriter=new BufferedWriter(new FileWriter(path.toString()+"\\statictic.txt"));
        }catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    void writeGoodInfo(ArrayList<Integer> variants,Path path){
        try (BufferedWriter bufferedWriter=new BufferedWriter(new FileWriter(path.toString()+"\\statictic.txt"))){
            bufferedWriter.write(ADD_LUCKY);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    void writeBadInfo(Path path){
        try (BufferedWriter bufferedWriter=new BufferedWriter(new FileWriter(path.toString()+"\\statictic.txt"))){
            bufferedWriter.write(ADD_LUCKY+"123312313");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
