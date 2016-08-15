package org.pjgg.flightSearch.service;


import org.pjgg.flightSearch.connector.ConnectorServiceLocator;
import org.pjgg.flightSearch.connector.flight.FlightConnector;
import org.pjgg.flightSearch.connector.flight.FlightPredicates;
import org.pjgg.flightSearch.converters.Converter;
import org.pjgg.flightSearch.converters.ConverterServiceLocator;
import org.pjgg.flightSearch.dto.FlightSearchRequest;
import org.pjgg.flightSearch.dto.FlightSearchResponse;
import org.pjgg.flightSearch.model.Flight;

import java.util.List;
import java.util.stream.Stream;

public class FlightSearchServiceImpl implements FlightSearchService {

    private FlightConnector flightConnector = ConnectorServiceLocator.INSTANCE.getFlightConnector();

    private PriceCalculator pricingRulesCalcularorService = PriceServiceLocator.INSTANCE.getPricingRulesCalculator();

    private PriceCalculator passagerTypeCalcularorService = PriceServiceLocator.INSTANCE.getPassagerTypeCalculator();

    private Converter<Flight,FlightSearchResponse> flightToFlightSearchResponseConverter = ConverterServiceLocator.INSTANCE.getFlightToFlightSearchResponseConverter();


    public List<FlightSearchResponse> findFlights(final FlightSearchRequest flightRequest){

        final Stream<Flight> flights =  flightConnector.filterEntities(FlightPredicates.match(flightRequest.getAirportOriginCode(), flightRequest.getAirportDestinationCode()));
        final Stream<Flight> flightsWithBasePrice = pricingRulesCalcularorService.applyPricingRules(flightRequest, flights);
        final Stream<Flight> flightsWithBasePriceAndPassagerType = passagerTypeCalcularorService.applyPricingRules(flightRequest, flightsWithBasePrice);

        return flightToFlightSearchResponseConverter.convertToList(flightsWithBasePriceAndPassagerType);
    }

}
