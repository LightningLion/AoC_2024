package com.lion.day5;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Part1 {
    private static final String SAMPLE_RULES_PATH = "src/main/java/com/lion/day5/sample_rules.txt";
    private static final String SAMPLE_UPDATES_PATH = "src/main/java/com/lion/day5/sample_updates.txt";
    private static final String INPUT_RULES_PATH = "src/main/java/com/lion/day5/input_rules.txt";
    private static final String INPUT_UPDATES_PATH = "src/main/java/com/lion/day5/input_updates.txt";

    public static void main(String[] args) {
        File fileRules = new File(SAMPLE_RULES_PATH);
        File fileUpdates = new File(INPUT_UPDATES_PATH);
        //File fileRules = new File(SAMPLE_RULES_PATH);
        //File fileUpdates = new File(INPUT_UPDATES_PATH);

        List<String> inputsRules = getStringsFromFile(fileRules);
        List<String> inputsUPdates = getStringsFromFile(fileUpdates);
    }

    private static List<String> getStringsFromFile(File file) {
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

        return  inputs;

    }

}
