package runner;

import dao.PhotoDao;
import entity.Photo;
import service.ServerLogic;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Test {
    public static void main(String[] args) throws IOException {
        ServerLogic serverLogic=new ServerLogic();
        serverLogic.run();
        System.out.println(serverLogic.makeDirWithExamName(Paths.get("C:\\Users\\Rodion\\Desktop\\мусорка")));
    }
}
