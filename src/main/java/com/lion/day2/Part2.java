package com.lion.day2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Part2 {
    private static final String FILE_PATH = "src/main/java/com/lion/day2/input.txt";

    public static void main(String[] args) {
        File file = new File(FILE_PATH);
        List<List<Integer>> rawReports = getReportsFromFile(file);

        List<Report> reports = new ArrayList<>();

        for (List<Integer> l : rawReports){
            Report report = new Report(l);
            report.safetyCheck();

            if (!report.isSafe()){
                report.permissiveSafetyCheck();
                System.out.println("Unsafe report after tolerance: " + report);
            }

            reports.add(report);
        }

        List<Report> safeReports = reports.stream().filter(Report::isSafe).toList();

        System.out.printf("There are %d safe reports if we tolerate one bad level.%n", safeReports.size());
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
