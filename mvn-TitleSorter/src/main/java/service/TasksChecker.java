package service;

import java.util.ArrayList;

public class TasksChecker {
    public ArrayList<Integer> determineTasks(String string) {
        String[]splitedString=string.split(" ");
        String substringWithBrackets;
        String neccesaryString=splitedString[1];
        if(neccesaryString.contains(".")){
            substringWithBrackets = neccesaryString.substring(0,neccesaryString.indexOf('.'));
        }
        else{
            substringWithBrackets=splitedString[1];
        }
        return getNumbersFromStringWithBrackets(substringWithBrackets);

    }


    private ArrayList<Integer> getNumbersFromStringWithBrackets(String bracketsString){
        ArrayList<Integer>result=new ArrayList<>();
        try {
            for (char num : bracketsString.toCharArray()) {
                result.add(Integer.parseInt(num + ""));
            }
        }
        catch (NumberFormatException exception){
            return result;
        }
        return result;
    }
}
