package org.pjgg.flightSearch.connector;


import org.pjgg.flightSearch.connector.airline.AirlinesConnector;
import org.pjgg.flightSearch.connector.airline.AirlinesConnectorImpl;
import org.pjgg.flightSearch.connector.airport.AirportsConnector;
import org.pjgg.flightSearch.connector.airport.AirportsConnectorImpl;
import org.pjgg.flightSearch.connector.flight.FlightConnector;
import org.pjgg.flightSearch.connector.flight.FlightConnectorImpl;
import org.pjgg.flightSearch.connector.pricingrules.PricingRulesConnector;
import org.pjgg.flightSearch.connector.pricingrules.PricingRulesConnectorImpl;

public enum ConnectorServiceLocator {

    INSTANCE;

    private final AirlinesConnector airlinesConnector;

    private final AirportsConnector airportsConnector;

    private final FlightConnector flightConnector;

    private final PricingRulesConnector pricingRulesConnector;

    private ConnectorServiceLocator(){
        airlinesConnector = new AirlinesConnectorImpl();
        airportsConnector = new AirportsConnectorImpl();
        flightConnector = new FlightConnectorImpl();
        pricingRulesConnector = new PricingRulesConnectorImpl();
    }

    public AirlinesConnector getAirlinesConnector(){return this.airlinesConnector;}

    public AirportsConnector getAirportsConnector(){return this.airportsConnector;}

    public FlightConnector getFlightConnector(){return this.flightConnector;}

    public PricingRulesConnector getPricingRulesConnector(){return this.pricingRulesConnector;}
}
