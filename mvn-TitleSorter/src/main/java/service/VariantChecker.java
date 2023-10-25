package service;

public class VariantChecker {
    public int determineVariant(String inputString){
        String result=inputString.substring(inputString.indexOf("в")+1,inputString.indexOf(' '));
        return Integer.parseInt(result);
    }
}
