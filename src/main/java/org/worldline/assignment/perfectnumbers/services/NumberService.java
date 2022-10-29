package org.worldline.assignment.perfectnumbers.services;

import java.util.List;

public interface NumberService {

    boolean isPerfectNumber(int number);
    List<Integer> getPerfectNumberSeries(Integer from, Integer to);
}
