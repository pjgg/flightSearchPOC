package org.pjgg.flightSearch.service;


import org.pjgg.flightSearch.dto.FlightSearchRequest;
import org.pjgg.flightSearch.dto.FlightSearchResponse;

import java.util.List;

public interface FlightSearchService {

    public List<FlightSearchResponse> findFlights(FlightSearchRequest flightRequest);

}
