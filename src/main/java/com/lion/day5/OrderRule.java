package com.lion.day5;

public record OrderRule(int before, int after) {
    @Override
    public String toString() {
        return before + "|" + after;
    }
}
