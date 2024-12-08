package com.lion.day5;

import java.util.ArrayList;
import java.util.List;

public class SafetyUpdate {
    private List<Integer> pages;
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
        this.setMiddleValue();
        System.out.println("Update: " + this + " does comply.");
    }

    private void setMiddleValue() {
        this.middleValue = pages.get(pages.size()/2);
    }

    public void rearrange() {
        List<Integer> correctOrder = new ArrayList<>();

        /*
            For each rule to follow we make two lists: one, previous, with the numbers in the "before" field
            and another, following, with the numbers in the "after" field.
            There will always be a number appearing only in previous, which is found by substracting following to previous.
            In the same way (although it's not used in this code) if we were to substract previous from following
            there would be a single number which is the last one.

            This process is repeated n times, where n is the number of pages, each time the rules with the "before"
            number position already are discarded.

         */
        for (int i = 0; i < this.pages.size(); i++) {
            List<Integer> previous = new ArrayList<>();
            List<Integer> following = new ArrayList<>();

            List<OrderRule> rulesToCheck = this.orderRules.stream().filter(r -> !correctOrder.contains(r.before())).toList();

            for (OrderRule rule : rulesToCheck) {
                if (!previous.contains(rule.before())) {
                    previous.add(rule.before());
                }

                if (!following.contains(rule.after())) {
                    following.add(rule.after());
                }
            }

            previous.removeAll(following);

            if (previous.size() == 1) {
                correctOrder.add(previous.get(0));
            }

            if (following.size() == 1) {
                correctOrder.add(following.get(0));
                break;
            }

        }

        System.out.println("Update after rearrangement: " + correctOrder);
        this.pages = correctOrder;
        this.setMiddleValue();

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
