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

        for (int i = 1; i < levels.size(); i++){
            int previousLevel = levels.get(i-1);
            int currentLevel = levels.get(i);

            if (currentLevel > previousLevel){
                increasing = true;
            } else {
                decreasing = true;
            }

            int difference = Math.abs(previousLevel - currentLevel);

            if (difference < 1 || difference > 3){
                return;
            }

            if (increasing && decreasing){
                return;
            }
        }

        this.safe = true;
    }

    public void permissiveSafetyCheck(){
        for (int i = 0; i < levels.size(); i++){
            List<Integer> reducedLevels = new ArrayList<>();
            boolean alreadyRemoved = false;
            int levelToExclude = levels.get(i);

            for (Integer n : levels){


                if (!n.equals(levelToExclude)) {
                    reducedLevels.add(n);
                } else {
                    if (!alreadyRemoved) {
                        alreadyRemoved = true;
                    } else {
                        reducedLevels.add(n);
                    }
                }
            }

            Report copyReport = new Report(reducedLevels);
            copyReport.safetyCheck();
            //System.out.println("Trying reduced report: " + copyReport + ". Is safe: " + copyReport.isSafe());

            if(copyReport.isSafe()){
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
