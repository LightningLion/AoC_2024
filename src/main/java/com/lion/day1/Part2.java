package com.lion.day1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class Part2 {
    public static void main(String[] args) {

        File file = new File("src/main/java/com/lion/day1/input.txt");

        ArrayList<ArrayList<Integer>> lists = getSetsFromInputFile(file);

        ArrayList<Integer> leftList = lists.get(0);
        ArrayList<Integer> rightList = lists.get(1);

        long accumulatedProximity = calculateAccumulatedProximity(leftList, rightList);

        System.out.printf("Accumulated proximity is: %d\n", accumulatedProximity);

    }

    private static ArrayList<ArrayList<Integer>> getSetsFromInputFile(File file){
        ArrayList<ArrayList<Integer>> returnList = new ArrayList<>();

        try(
                BufferedReader br = new BufferedReader(new FileReader(file))
        ) {
            ArrayList<Integer> leftList = new ArrayList<>();
            ArrayList<Integer> rightList = new ArrayList<>();

            String line;

            while ((line = br.readLine()) != null) {
                String[] lines = line.split(" {3}");

                leftList.add(Integer.parseInt(lines[0]));
                rightList.add(Integer.parseInt(lines[1]));
            }

            leftList.sort(Comparator.naturalOrder());
            rightList.sort(Comparator.naturalOrder());

            returnList.add(leftList);
            returnList.add(rightList);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return returnList;
    }

    private static long calculateProximity(int number1, List<Integer> list2){
        long count = list2.stream().filter(n -> n.equals(number1)).count();

        long proximity = number1 * count;
        System.out.printf("Proximity for number %d is %d.\n", number1, proximity);
        return proximity;
    }

    private static long calculateAccumulatedProximity(List<Integer> list1, List<Integer> list2){
        long accumulatedProximity = 0;

        for (int number1 : list1) {
            accumulatedProximity += calculateProximity(number1, list2);
        }

        return accumulatedProximity;
    }
}