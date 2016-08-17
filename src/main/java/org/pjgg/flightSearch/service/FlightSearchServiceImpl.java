package org.pjgg.flightSearch.service;


import org.pjgg.flightSearch.connector.flight.FlightConnector;
import org.pjgg.flightSearch.connector.flight.FlightPredicates;
import org.pjgg.flightSearch.converters.Converter;
import org.pjgg.flightSearch.converters.ConverterServiceLocator;
import org.pjgg.flightSearch.dto.FlightSearchRequest;
import org.pjgg.flightSearch.dto.FlightSearchResponse;
import org.pjgg.flightSearch.model.Flight;
import org.pjgg.flightSearch.service.pricing.PriceCalculator;

import java.util.List;
import java.util.stream.Stream;

public class FlightSearchServiceImpl implements FlightSearchService {

    private FlightConnector flightConnector;

    private PriceCalculator pricingRulesCalcularorService;

    private PriceCalculator passagerTypeCalcularorService;

    private Converter<Flight,FlightSearchResponse> flightToFlightSearchResponseConverter;


    public FlightSearchServiceImpl(FlightConnector flightConnector,PriceCalculator pricingRulesCalcularorService,PriceCalculator passagerTypeCalcularorService, Converter<Flight,FlightSearchResponse> flightToFlightSearchResponseConverter ){
        this.flightConnector = flightConnector;
        this.pricingRulesCalcularorService = pricingRulesCalcularorService;
        this.passagerTypeCalcularorService = passagerTypeCalcularorService;
        this.flightToFlightSearchResponseConverter = flightToFlightSearchResponseConverter;
    }

    public List<FlightSearchResponse> findFlights(final FlightSearchRequest flightRequest){

        final Stream<Flight> flights =  flightConnector.filterEntities(FlightPredicates.match(flightRequest.getAirportOriginCode(), flightRequest.getAirportDestinationCode()));
        final Stream<Flight> flightsWithBasePrice = pricingRulesCalcularorService.applyPricingRules(flightRequest, flights);
        final Stream<Flight> flightsWithBasePriceAndPassagerType = passagerTypeCalcularorService.applyPricingRules(flightRequest, flightsWithBasePrice);

        return flightToFlightSearchResponseConverter.convertToList(flightsWithBasePriceAndPassagerType);
    }

}
