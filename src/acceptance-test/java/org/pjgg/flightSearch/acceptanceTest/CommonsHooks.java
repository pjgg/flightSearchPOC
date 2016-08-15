package org.pjgg.flightSearch.acceptanceTest;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.pjgg.flightSearch.acceptanceTest.utils.CsvFileReader;
import org.pjgg.flightSearch.connector.ConnectorServiceLocator;
import org.pjgg.flightSearch.connector.airline.AirlinesConnector;
import org.pjgg.flightSearch.connector.airline.AirlinesPredicates;
import org.pjgg.flightSearch.connector.airport.AirportPredicates;
import org.pjgg.flightSearch.connector.airport.AirportsConnector;
import org.pjgg.flightSearch.connector.flight.FlightConnector;
import org.pjgg.flightSearch.connector.flight.FlightPredicates;
import org.pjgg.flightSearch.connector.pricingrules.PricingRulesConnector;
import org.pjgg.flightSearch.model.Airline;
import org.pjgg.flightSearch.model.Airport;
import org.pjgg.flightSearch.model.Flight;
import org.pjgg.flightSearch.model.PricingRules;

import java.io.File;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

public class CommonsHooks {

    private final static Logger LOGGER = Logger.getLogger(CommonsHooks.class.getName());
    private final ClassLoader classLoader = getClass().getClassLoader();

    private final static String AIRPORT_FILE_NAME = "airports.csv";
    private final static String FLIGHTS_FILE_NAME = "flights.csv";
    private final static String AIRLINES_FILE_NAME = "airlines.csv";
    private final static String PRICING_RULES_FILE_NAME = "pricingRules.csv";

    private AirlinesConnector airlinesConnector = ConnectorServiceLocator.INSTANCE.getAirlinesConnector();
    private AirportsConnector airPortsConnector = ConnectorServiceLocator.INSTANCE.getAirportsConnector();
    private FlightConnector flightConnector = ConnectorServiceLocator.INSTANCE.getFlightConnector();
    private PricingRulesConnector pricingRulesConnector = ConnectorServiceLocator.INSTANCE.getPricingRulesConnector();


    @Before("@loadAirports")
    public void loadAirports(){
        LOGGER.info("loading airports");
        CsvFileReader<Airport> csvReader = new CsvFileReader();
        File airportCsv = new File(classLoader.getResource(AIRPORT_FILE_NAME).getFile());
        List<Airport> airports = csvReader.read(airportCsv, new Airport("FakeCode","FakeCity"),Airport.class);
        airports.stream().forEach(airport -> airPortsConnector.addEntity(airport));
    }

    @Before("@loadFlights")
    public void loadFlights(){
        LOGGER.info("loading loadFlights");
        CsvFileReader<Flight> csvReader = new CsvFileReader();
        File flightCsv = new File(classLoader.getResource(FLIGHTS_FILE_NAME).getFile());
        List<Flight> flights = csvReader.read(flightCsv, new Flight("FakeOrignin","FakeDestination", "FakeAirline", 10),Flight.class);
        flights.stream().forEach(flight -> flightConnector.addEntity(flight));
    }

    @Before("@loadAirlines")
    public void loadAirlines(){
        LOGGER.info("loading airlines");
        CsvFileReader<Airline> csvReader = new CsvFileReader();
        File airlineCsv = new File(classLoader.getResource(AIRLINES_FILE_NAME).getFile());
        List<Airline> airlines = csvReader.read(airlineCsv, new Airline("FakeIataCode","FakeName", Optional.of(10.0)),Airline.class);
        airlines.stream().forEach(airline -> airlinesConnector.addEntity(airline));
    }

    @Before("@loadPricingRules")
    public void loadPricingRules(){
        LOGGER.info("loading pricing rules");
        CsvFileReader<PricingRules> csvReader = new CsvFileReader();
        File pricingRulesCsv = new File(classLoader.getResource(PRICING_RULES_FILE_NAME).getFile());
        List<PricingRules> pricingRulesEntities = csvReader.read(pricingRulesCsv, new PricingRules(0,0,0),PricingRules.class);
        pricingRulesEntities.stream().forEach(p -> pricingRulesConnector.addEntity(p));
    }

    @After("@CleanData")
    public void cleanData(){
        LOGGER.info("Clean all Data");
        airlinesConnector.removeEntities(AirlinesPredicates.isAlwaysTrue());
        airPortsConnector.removeEntities(AirportPredicates.isAlwaysTrue());
        flightConnector.removeEntities(FlightPredicates.isAlwaysTrue());
    }


}
