package org.pjgg.flightSearch.converters;


import org.pjgg.flightSearch.dto.FlightSearchResponse;
import org.pjgg.flightSearch.model.Flight;

public class FlightToFlightSearchResponseConverter implements Converter<Flight, FlightSearchResponse> {

    @Override
    public FlightSearchResponse apply(Flight flight) {
        return new FlightSearchResponse(flight.getAirline(), flight.getPrice());
    }

}
