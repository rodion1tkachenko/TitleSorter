import java.io.IOException;

public class UserInterface {
    static ServerLogic serverLogic=new ServerLogic();
    public static void main(String[] args) throws IOException {//remove io
     serverLogic.scanPhotos(serverLogic.inputFolder.getDirectory());
        serverLogic.sendToTheDesiredFolders();
    }


}
