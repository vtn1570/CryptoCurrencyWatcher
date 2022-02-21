package com.example.currencyWatcher.math;

public class MathFormulas {
    public static boolean priceChangeOnePercent(double originalValue, double newValue) {
        double percentOfChanging = (newValue - originalValue) / originalValue * 100;
        return Math.abs(percentOfChanging) > 1;
    }

}

