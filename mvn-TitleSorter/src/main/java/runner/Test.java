package runner;

import dao.PhotoDao;
import entity.Photo;
import service.ServerLogic;
import util.TableAction;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Test {
    public static void main(String[] args) throws IOException {
//        System.out.println(makeSqlTableName("Kont-rol 228"));
        // new ServerLogic().getDirectories();
        try (BufferedWriter bufferedWriter = new BufferedWriter(
                new FileWriter("C:\\testInformation\\photos\\229.txt"))) {
            bufferedWriter.write("asd");
            bufferedWriter.write("123");
        }

    }
    static String makeSqlTableName(String string){
        return string.replaceAll("[ -.]","_");
    }
}
