package com.gridnine.testing.filters;

import com.gridnine.testing.Flight;
import com.gridnine.testing.FlightFilter;
import com.gridnine.testing.Segment;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Исключает перелёты, у которых первый сегмент отправляется до текущего момента времени.
 * test(flight) = true, если перелёт НЕ нарушает правило (т.е. вылет после "сейчас").
 */
public class DepartureBeforeNowFilter implements FlightFilter {
    @Override
    public boolean test(Flight flight) {
        List<Segment> segments = flight.getSegments();
        if (segments.isEmpty()) return false; // если почему-то нет сегментов, исключаем

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime firstDeparture = segments.get(0).getDepartureDate();

        // Возвращаем true, если вылет >= now (то есть перелёт валиден и не нарушает правило)
        return !firstDeparture.isBefore(now);
    }
}
