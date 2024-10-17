package calculator.stringSplitter;

import camp.nextstep.edu.missionutils.Console;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class StringSplitter {
    public String[] splitsString(String input){
        List<String>  separator = new ArrayList<>();
        separator.add(",");
        separator.add(":");
        String number = input;
        if(input.startsWith("//")){
            int endPoint = input.indexOf("\\n");
            if(input.indexOf("\\n") == -1){
                throw new IllegalArgumentException(); // 예외발생
            }

            String customSeparators = input.substring(2,endPoint);

            if(customSeparators.length() > 1){
                String[] splited = customSeparators.split("");
                for(String str : splited){
                    separator.add(Pattern.quote(str));
                }
            }else{
                separator.add(Pattern.quote(customSeparators));
            }
            number = input.substring(endPoint+1);
        }
        String calculateSeparator = separator.stream().collect(Collectors.joining("|"));
        return number.split(calculateSeparator);
    }

    public static void main(String[] args) {
        StringSplitter stp = new StringSplitter();
        String tmp = Console.readLine();
        String[] test = stp.splitsString(tmp);
        System.out.println("test = " + Arrays.toString(test));
    }
}
