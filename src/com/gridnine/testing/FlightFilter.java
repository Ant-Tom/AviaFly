package com.gridnine.testing;

import java.util.ArrayList;
import java.util.List;

public interface FlightFilter {
    /**
     * Проверяет, удовлетворяет ли перелёт условию фильтра.
     */
    boolean test(Flight flight);

    /**
     * Утилитный метод для фильтрации списка перелётов.
     */
    static List<Flight> filter(List<Flight> flights, FlightFilter filter) {
        List<Flight> result = new ArrayList<>();
        for (Flight flight : flights) {
            if (filter.test(flight)) {
                result.add(flight);
            }
        }
        return result;
    }
}
