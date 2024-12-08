package com.lion.day1;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class Part1 {
    private static final String FILE_PATH = "src/main/java/com/lion/day1/input.txt.txt";

    public static void main(String[] args) {

        File file = new File(FILE_PATH);

        ArrayList<ArrayList<Integer>> lists = getSetsFromInputFile(file);

        ArrayList<Integer> leftList = lists.get(0);
        ArrayList<Integer> rightList = lists.get(1);

        int accumulatedDistance = calculateAccumulatedDistance(leftList, rightList);

        System.out.printf("Accumulated distance is: %d\n", accumulatedDistance);

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

    private static int calculateDistance(int number1, int number2){
        int distance = Math.abs(number1 - number2);
        System.out.printf("Distance between %d and %d is %d.\n", number1, number2, distance);
        return distance;
    }

    private static int calculateAccumulatedDistance(List<Integer> set1, List<Integer> set2){
        int accumulatedDistance = 0;

        for (int i = 0; i < set1.size(); i++){
            int number1 = set1.get(i);
            int number2 = set2.get(i);

            accumulatedDistance += calculateDistance(number1, number2);
        }

        return accumulatedDistance;
    }
}