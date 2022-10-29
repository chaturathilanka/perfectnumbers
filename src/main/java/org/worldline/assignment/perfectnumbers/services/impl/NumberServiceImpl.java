package org.worldline.assignment.perfectnumbers.services.impl;

import org.worldline.assignment.perfectnumbers.services.NumberService;

import java.util.ArrayList;
import java.util.List;

public class NumberServiceImpl implements NumberService {
    @Override
    public boolean isPerfectNumber(int number) {
        int total = 0;
        for (int i = 1; i <= number / 2; i++) {
            if (number % i == 0) {
                total += i;
            }
        }
        if (total == number) {
            return true;
        } else
            return false;
    }

    @Override
    public List<Integer> getPerfectNumberSeries(Integer from, Integer to) {
        List<Integer> perfectNumberSeries = new ArrayList<>();

        for (int i = from; i < to; i++) {
            if (isPerfectNumber(i)) {
                perfectNumberSeries.add(i);
            }
        }
        return perfectNumberSeries;
    }
}
