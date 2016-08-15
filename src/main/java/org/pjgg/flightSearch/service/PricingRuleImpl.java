package org.pjgg.flightSearch.service;


import org.pjgg.flightSearch.connector.ConnectorServiceLocator;
import org.pjgg.flightSearch.connector.pricingrules.PricingRulesConnector;
import org.pjgg.flightSearch.connector.pricingrules.PricingRulesPredicates;
import org.pjgg.flightSearch.dto.FlightSearchRequest;
import org.pjgg.flightSearch.model.Flight;
import org.pjgg.flightSearch.model.PricingRules;
import org.pjgg.flightSearch.utils.CustomMaths;

public class PricingRuleImpl implements PriceCalculator {

    private PricingRulesConnector pricingRulesConnector = ConnectorServiceLocator.INSTANCE.getPricingRulesConnector();

    @Override
    public Flight apply(final FlightSearchRequest flightSearchRequest, final Flight flight) {

        final int dayToDeparture = flightSearchRequest.getDepartureFrom();
        final PricingRules pricingRules = pricingRulesConnector.filterEntities(PricingRulesPredicates.isBetween(dayToDeparture)).findFirst().get();
        final double basePrice = CustomMaths.applyPercentage(pricingRules.getPercentage(), flight.getPrice());
        return new Flight(flight.getOrigin(), flight.getDestination(), flight.getAirline(), basePrice);
    }



}
