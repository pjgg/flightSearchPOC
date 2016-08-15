package org.pjgg.flightSearch.connector.airport;


import org.pjgg.flightSearch.model.Airport;

import java.util.function.Predicate;

public class AirportPredicates {

    public static Predicate<Airport> isAlwaysTrue() {
        return p -> true;
    }

}
