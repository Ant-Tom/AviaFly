package com.gridnine.testing.filters;

import com.gridnine.testing.Flight;
import com.gridnine.testing.FlightFilter;
import com.gridnine.testing.Segment;

import java.time.Duration;
import java.util.List;

/**
 * Исключает перелёты, где общее время на земле (между сегментами) > 2 часов.
 * test(flight) = true, если суммарное время на земле <= 2 часов.
 */
public class LongGroundTimeFilter implements FlightFilter {

    @Override
    public boolean test(Flight flight) {
        List<Segment> segments = flight.getSegments();
        // Если всего один сегмент, "земли" нет, значит всё ок
        if (segments.size() < 2) {
            return true;
        }

        Duration totalGroundTime = Duration.ZERO;
        for (int i = 0; i < segments.size() - 1; i++) {
            Duration groundTime = Duration.between(
                    segments.get(i).getArrivalDate(),
                    segments.get(i + 1).getDepartureDate()
            );
            totalGroundTime = totalGroundTime.plus(groundTime);
        }
        // Возвращаем true, если общее время на земле <= 2 часов
        return totalGroundTime.compareTo(Duration.ofHours(2)) <= 0;
    }
}
