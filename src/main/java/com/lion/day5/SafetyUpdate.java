package com.lion.day5;

import java.util.ArrayList;
import java.util.List;

public class SafetyUpdate {
    private final List<Integer> pages;
    private final List<OrderRule> orderRules = new ArrayList<>();
    private boolean valid = false;
    private int middleValue = 0;


    public SafetyUpdate(List<Integer> pages) {
        this.pages = new ArrayList<>(pages);
    }

    public boolean isValid() {
        return valid;
    }

    public void processRules(List<OrderRule> rules) {
        //Add only rules that refer to two pages in the update
        for (OrderRule rule : rules){
            if (pages.contains(rule.before()) && pages.contains(rule.after())){
                orderRules.add(rule);
            }
        }

        /*
            For each page, make a list of the pages that preceded it.
            Then, for each rule that has that page in the "before" field check if the "after" page appears
            in the previous pages, and if so, the update doesn't comply with the rules.

        */
        for (int i = 0; i < pages.size(); i++){
            List<Integer> previousPages = new ArrayList<>();

            for (int j = 0; j < i; j++){
                previousPages.add(pages.get(j));
            }

            for (OrderRule rule : rules){
                if (rule.before() == pages.get(i)){
                    if (previousPages.contains(rule.after())){
                        this.valid = false;
                        System.out.println("Update: " + this + " doesn't comply.");
                        return;
                    }
                }
            }
        }
        this.valid = true;
        this.middleValue = pages.get(pages.size()/2);
        System.out.println("Update: " + this + " does comply.");
    }

    public int getMiddleValue() {
        return middleValue;
    }

    @Override
    public String toString() {
        return "SafetyUpdate{" +
                "pages=" + pages +
                ", orderRules=" + orderRules +
                ", valid=" + valid +
                ", middleValue=" + middleValue +
                '}';
    }
}
