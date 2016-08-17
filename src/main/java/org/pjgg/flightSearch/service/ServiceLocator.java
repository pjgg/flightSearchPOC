package org.pjgg.flightSearch.service;


import org.pjgg.flightSearch.connector.ConnectorServiceLocator;
import org.pjgg.flightSearch.converters.ConverterServiceLocator;
import org.pjgg.flightSearch.service.pricing.PassengerTypeImpl;
import org.pjgg.flightSearch.service.pricing.PriceCalculator;
import org.pjgg.flightSearch.service.pricing.PricingRuleImpl;

public enum ServiceLocator {

    INSTANCE;

    private final PriceCalculator pricingRulesCalculator;
    private final PriceCalculator passagerTypeCalculator;
    private final FlightSearchService flightSearchService;

    private ServiceLocator() {
        pricingRulesCalculator = new PricingRuleImpl(ConnectorServiceLocator.INSTANCE.getPricingRulesConnector());
        passagerTypeCalculator = new PassengerTypeImpl(ConnectorServiceLocator.INSTANCE.getAirlinesConnector());
        flightSearchService = new FlightSearchServiceImpl(ConnectorServiceLocator.INSTANCE.getFlightConnector(), pricingRulesCalculator, passagerTypeCalculator, ConverterServiceLocator.INSTANCE.getFlightToFlightSearchResponseConverter());
    }

    public PriceCalculator getPricingRulesCalculator() {
        return this.pricingRulesCalculator;
    }

    public PriceCalculator getPassagerTypeCalculator() {
        return this.passagerTypeCalculator;
    }

    public FlightSearchService getFlightSearchService() {
        return this.flightSearchService;
    }

}
