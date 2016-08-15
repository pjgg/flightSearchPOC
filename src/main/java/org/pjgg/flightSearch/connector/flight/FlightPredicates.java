package org.pjgg.flightSearch.connector.flight;


import org.pjgg.flightSearch.model.Flight;

import java.util.function.Predicate;

public class FlightPredicates {

    public static Predicate<Flight> isAlwaysTrue() {return f -> true;}

    public static Predicate<Flight> match(String from, String to){
        return f -> f.getOrigin().equalsIgnoreCase(from) && f.getDestination().equalsIgnoreCase(to);
    }

    public static Predicate<Flight> isAirline(String airlineCode){
        return f -> f.getAirline().equalsIgnoreCase(airlineCode);
    }

}
