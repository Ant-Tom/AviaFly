package com.gridnine.testing.filters;

import com.gridnine.testing.Flight;
import com.gridnine.testing.FlightFilter;
import com.gridnine.testing.Segment;

/**
 * Исключает перелёты, в которых есть сегмент с прилётом раньше вылета.
 * test(flight) = true, если перелёт НЕ нарушает правило (нет сегментов, где arrival < departure).
 */
public class ArrivalBeforeDepartureFilter implements FlightFilter {
    @Override
    public boolean test(Flight flight) {
        return flight.getSegments().stream()
                .noneMatch(segment -> segment.getArrivalDate().isBefore(segment.getDepartureDate()));
    }
}
