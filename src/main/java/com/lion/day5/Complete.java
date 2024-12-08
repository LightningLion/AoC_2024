package com.lion.day5;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static com.lion.utils.Utils.getStringsFromFile;

public class Complete {
    private static final String SAMPLE_RULES_PATH = "src/main/java/com/lion/day5/sample_rules.txt";
    private static final String SAMPLE_UPDATES_PATH = "src/main/java/com/lion/day5/sample_updates.txt";
    private static final String INPUT_RULES_PATH = "src/main/java/com/lion/day5/input_rules.txt";
    private static final String INPUT_UPDATES_PATH = "src/main/java/com/lion/day5/input_updates.txt";

    public static void main(String[] args) {
        //File fileRules = new File(SAMPLE_RULES_PATH);
        //File fileUpdates = new File(SAMPLE_UPDATES_PATH);
        File fileRules = new File(INPUT_RULES_PATH);
        File fileUpdates = new File(INPUT_UPDATES_PATH);

        System.out.println("====================PART 1====================");
        List<String> inputsRules = getStringsFromFile(fileRules);
        List<OrderRule> rules = processInputRules(inputsRules);

        List<String> inputsUpdates = getStringsFromFile(fileUpdates);
        List<SafetyUpdate> updates = processInputUpdates(inputsUpdates, rules);

        long sumValidUpdates = sumMiddleValues(updates);

        System.out.printf("Accumulated middle value of correct updates is: %d.%n", sumValidUpdates);

        System.out.println();

        /*
        ###################################################
        #                    PART 2                       #
        ###################################################
         */

        System.out.println("====================PART 2====================");
        long sumInvalidUpdates = 0;
        List<SafetyUpdate> incorrectUpdates = updates.stream().filter(u -> !u.isValid()).toList();

        for (SafetyUpdate su : incorrectUpdates){
            su.rearrange();
            sumInvalidUpdates += su.getMiddleValue();
        }

        System.out.printf("Accumulated middle value of incorrect updates is: %d.%n", sumInvalidUpdates);

    }

    private static List<OrderRule> processInputRules(List<String> inputsRules) {
        List<OrderRule> rules = new ArrayList<>();

        for (String s : inputsRules){
            String[] numbers = s.split("\\|");
            int number1 = Integer.parseInt(numbers[0]);
            int number2 = Integer.parseInt(numbers[1]);

            rules.add(new OrderRule(number1, number2));
        }

        return rules;
    }

    private static List<SafetyUpdate> processInputUpdates(List<String> inputsUpdates, List<OrderRule> rules) {
        List<SafetyUpdate> updates = new ArrayList<>();

        for (String s : inputsUpdates){
            String[] strPages =s.split(",");
            List<Integer> intPages = new ArrayList<>();
            for (String page : strPages){
                intPages.add(Integer.parseInt(page));
            }

            SafetyUpdate update = new SafetyUpdate(intPages);
            update.processRules(rules);
            updates.add(update);
        }

        return updates;
    }

    private static long sumMiddleValues(List<SafetyUpdate> updates) {
        long sum = 0;
        for (SafetyUpdate su : updates){
            if (su.isValid()) {
                sum += su.getMiddleValue();
            }
        }

        return  sum;
    }

}
