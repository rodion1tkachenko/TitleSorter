public class UserInterface {
    static ServerLogic serverLogic=new ServerLogic();
    public static void main(String[] args) {
     serverLogic.scanPhotos(serverLogic.inputFolder.getDirectory());
        System.out.println(Helper.findOutAmountOfNumbers());
    }


}
