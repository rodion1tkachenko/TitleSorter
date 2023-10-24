package foldersWork;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public abstract class AbstractFolder {

    Path path;
    Scanner scanner=new Scanner(System.in);

    public abstract Path getDirectory();

    public boolean isDirectory(String userString){
        Path inputpath= Paths.get(userString);
        return Files.isDirectory(inputpath);
    }

    public Path getPath() {
        return path;
    }
}
