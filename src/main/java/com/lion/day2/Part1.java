package com.lion.day2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Part1 {
    public static void main(String[] args) {
        /*
        6 reports (rows) 5 levels (columns
        7 6 4 2 1
        1 2 7 8 9
        9 7 6 2 1
        1 3 2 4 5
        8 6 4 4 1
        1 3 6 7 9

        Which reports are safe?
        Safe means
        -The levels are either all increasing or all decreasing.
        -Any two adjacent levels differ by at least one and at most three.

         */
        File file = new File("src/main/java/com/lion/day2/input.txt");
        List<List<Integer>> rawReports = getReportsFromFile(file);

        List<Report> reports = new ArrayList<>();

        for (List<Integer> l : rawReports){
            Report report = new Report(l);
            report.safetyCheck();
            reports.add(report);
        }

        List<Report> safeReports = reports.stream().filter(Report::isSafe).toList();

        System.out.printf("There are %d safe reports.\n", safeReports.size());
    }

    private static List<List<Integer>> getReportsFromFile(File file){
        List<List<Integer>> reports = new ArrayList<>();

        try(
                BufferedReader br = new BufferedReader(new FileReader(file))
        ) {

            String line;

            while ((line = br.readLine()) != null) {
                String[] levels = line.split(" ");

                List<Integer> report = new ArrayList<>();

                for(String s : levels){
                    report.add(Integer.parseInt(s));
                }

                reports.add(report);

            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }

        return reports;
    }
}
