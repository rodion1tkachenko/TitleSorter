public class Helper {
    public static int findOutAmountOfNumbers(){
        int lastNumber=1;
        for (int i = 0; i < ServerLogic.getAllPhotos().size(); i++) {
            for (int j = 0; j < ServerLogic.getAllPhotos().get(i).getTaskNumbers().size(); j++) {
                if(ServerLogic.getAllPhotos().get(i).getTaskNumbers().get(j)>lastNumber){
                    lastNumber=ServerLogic.getAllPhotos().get(i).getTaskNumbers().get(j);
                }
            }
        }
        return lastNumber;
    }
}
