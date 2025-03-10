package com.gridnine.testing;

import com.gridnine.testing.filters.ArrivalBeforeDepartureFilter;
import com.gridnine.testing.filters.DepartureBeforeNowFilter;
import com.gridnine.testing.filters.LongGroundTimeFilter;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Получаем тестовый набор перелётов
        List<Flight> flights = FlightBuilder.createFlights();

        // Исходный набор
        System.out.println("Исходный набор перелётов:");
        flights.forEach(System.out::println);

        // 1) Исключаем перелёты с вылетом до текущего момента
        System.out.println("\n1) Исключаем перелёты с вылетом до текущего момента:");
        List<Flight> futureFlights = FlightFilter.filter(flights, new DepartureBeforeNowFilter());
        futureFlights.forEach(System.out::println);

        // 2) Исключаем перелёты, в которых есть сегменты с прилётом раньше вылета
        System.out.println("\n2) Исключаем перелёты, где сегмент прилетает раньше, чем вылетает:");
        List<Flight> validFlights = FlightFilter.filter(flights, new ArrivalBeforeDepartureFilter());
        validFlights.forEach(System.out::println);

        // 3) Исключаем перелёты, где общее время на земле > 2 часов
        System.out.println("\n3) Исключаем перелёты, где общее время на земле превышает 2 часа:");
        List<Flight> shortGroundFlights = FlightFilter.filter(flights, new LongGroundTimeFilter());
        shortGroundFlights.forEach(System.out::println);
    }
}
