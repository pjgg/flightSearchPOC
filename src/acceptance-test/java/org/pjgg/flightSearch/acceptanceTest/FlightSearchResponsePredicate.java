package org.pjgg.flightSearch.acceptanceTest;


import org.pjgg.flightSearch.dto.FlightSearchResponse;
import org.pjgg.flightSearch.model.Airline;

import java.util.function.Predicate;

public class FlightSearchResponsePredicate {

    public static Predicate<FlightSearchResponse> isAirlineFlightCode(String airlineFlightCode) {
        return flightSearchResponse -> flightSearchResponse.getFlightCode().equalsIgnoreCase(airlineFlightCode);
    }

}
