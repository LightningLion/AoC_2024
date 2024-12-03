package com.lion.day3;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part1 {
    private static final String SAMPLE_PATH = "src/main/java/com/lion/day3/sample.txt";
    private static final String INPUT_PATH = "src/main/java/com/lion/day3/input.txt";
    private static final String EXPRESSION = "mul{1}\\([0-9]*,[0-9]*\\)";

    public static void main(String[] args) {
        //File file = new File(SAMPLE_PATH);
        File file = new File(INPUT_PATH);
        List<String> inputs = getStringsFromFile(file);

        List<String> matchedPatterns = matchPatterns(inputs);
        long result = processMatchedPatterns(matchedPatterns);

        System.out.println("Accumulated multiplicaton is: " + result + ".");

    }

    private static List<String> getStringsFromFile(File file){
        List<String> inputs = new ArrayList<>();
        String line;

        try(
                BufferedReader br = new BufferedReader(new FileReader(file))
        ) {
            while ((line = br.readLine()) != null) {
                inputs.add(line);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }

        return inputs;
    }

    private static List<String> matchPatterns(List<String> inputs){
        List<String> result = new ArrayList<>();
        Pattern pattern = Pattern.compile(EXPRESSION);

        for (String s : inputs){
            Matcher matcher = pattern.matcher(s);

            if(matcher.find()) {
                do {
                    result.add(matcher.group());
                } while(matcher.find(matcher.start()+1));
            }
        }

        System.out.println(result);

        return result;
    }

    private static long processMatchedPatterns(List<String> matches){
        long accumulatedTotal = 0;

        for (String s : matches){
            String[] strings = s.split(",");
            Integer number1 = Integer.parseInt(strings[0].substring(4));
            Integer number2 = Integer.parseInt(strings[1].substring(0, strings[1].length()-1));

            int multiplication = number1 * number2;
            accumulatedTotal += multiplication;
        }

        return accumulatedTotal;
    }
}