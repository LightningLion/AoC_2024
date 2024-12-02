package com.lion.day2;

import java.util.ArrayList;
import java.util.List;

public class Report {
    private final List<Integer> levels;
    private boolean safe = false;

    public Report(List<Integer> levels) {
        this.levels = levels;
    }

    public void safetyCheck(){
        boolean decreasing = false;
        boolean increasing = false;

        for (int i = 0; i < levels.size() - 1; i++){
            int currentLevel = levels.get(i);
            int nextLevel = levels.get(i+1);

            int difference = Math.abs(nextLevel - currentLevel);

            if (difference < 1 || difference > 3) return;

            if (currentLevel > nextLevel) decreasing = true;
            if (currentLevel < nextLevel) increasing = true;

            if (increasing && decreasing) return;
        }

        this.safe = true;
    }

    private  List<Integer> createReducedList(int indexToExclude){
        List<Integer> reducedList = new ArrayList<>();

        for (int i = 0; i < levels.size(); i++){
            if (i != indexToExclude){
                reducedList.add(levels.get(i));
            }
        }

        return reducedList;
    }

    public void permissiveSafetyCheck(){
        for (int i = 0; i < levels.size(); i++){
            List<Integer> reducedList = createReducedList(i);

            Report copyReport = new Report(reducedList);
            copyReport.safetyCheck();

            if (copyReport.isSafe()){
                this.safe = true;
                return;
            }
        }
    }

    public boolean isSafe() {
        return safe;
    }

    @Override
    public String toString() {
        return "Report{" +
                "levels=" + levels +
                ", safe=" + safe +
                '}';
    }
}
