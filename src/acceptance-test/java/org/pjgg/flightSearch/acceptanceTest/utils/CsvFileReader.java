package org.pjgg.flightSearch.acceptanceTest.utils;

import org.pjgg.flightSearch.model.Airline;
import org.pjgg.flightSearch.model.Airport;
import org.pjgg.flightSearch.model.Flight;
import org.pjgg.flightSearch.model.PricingRules;

import java.io.BufferedReader;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.io.FileReader;
import java.io.IOException;
import java.util.Optional;
import java.util.logging.Logger;

import static org.pjgg.flightSearch.acceptanceTest.utils.ClassPattern.inCaseOf;

// CODE INSPIRED FROM THE FOLLOWING WEB SITE
//https://examples.javacodegeeks.com/core-java/writeread-csv-files-in-java-example/
public class CsvFileReader<E> {

    private final static Logger LOGGER = Logger.getLogger(CsvFileReader.class.getName());

    private static final String COMMA_DELIMITER = ",";

    //Airport attributes index
    private static final int AIRPORT_CODE = 0;
    private static final int AIRPORT_CITY = 1;

    //flight attributes index
    private static final int FLIGHT_ORIGIN = 0;
    private static final int FLIGHT_DESTIONATION = 1;
    private static final int FLIGHT_AIRLINE = 2;
    private static final int FLIGHT_PRICE = 3;

    //airline attributes index
    private static final int AIRLINE_CODE = 0;
    private static final int AIRLINE_NAME = 1;
    private static final int AIRLINE_PRICE = 2;

    //pricinrules attributes index
    private static final int PRICING_RULE_FROM = 0;
    private static final int PRICING_RULE_TO = 1;
    private static final int PRICING_RULE_PERCENTAGE = 2;

    public List<E> read(File fileName, E fakeInstanceClass, Class<E> type) {

        BufferedReader fileReader = null;
        List<E> entities = new ArrayList<E>();

        try {

            String line = "";

            //Create the file reader
            fileReader = new BufferedReader(new FileReader(fileName));

            //Read the CSV file header to skip it
            fileReader.readLine();

            //Read the file line by line starting from the second line
            while ((line = fileReader.readLine()) != null) {
                //Get all tokens available in line
                String[] tokens = line.split(COMMA_DELIMITER);
                if (tokens.length > 0) {

                    PatternMatching patternMatching = new PatternMatching(
                            inCaseOf(Airport.class, airportCsvRecord -> new Airport(tokens[AIRPORT_CODE], tokens[AIRPORT_CITY])),
                            inCaseOf(Flight.class, flightCsvRecord -> new Flight(tokens[FLIGHT_ORIGIN], tokens[FLIGHT_DESTIONATION], tokens[FLIGHT_AIRLINE], Integer.parseInt(tokens[FLIGHT_PRICE]))),
                            inCaseOf(Airline.class, airlineCsvRecord -> new Airline(tokens[AIRLINE_CODE], tokens[AIRLINE_NAME], Optional.of(Double.valueOf(tokens[AIRLINE_PRICE])))),
                            inCaseOf(PricingRules.class, pricingRulesCsvRecord -> new PricingRules(Integer.parseInt(tokens[PRICING_RULE_FROM]), Integer.parseInt(tokens[PRICING_RULE_TO]), Integer.parseInt(tokens[PRICING_RULE_PERCENTAGE])))
                    );

                    entities.add(type.cast(patternMatching.matchFor(fakeInstanceClass)));
                }
            }


        } catch (Exception e) {
            LOGGER.info("Error in CsvFileReader !!!");
            e.printStackTrace();
        } finally {
            try {
                fileReader.close();
            } catch (IOException e) {
                LOGGER.info("Error while closing fileReader !!!");
                e.printStackTrace();
            }
        }
        return entities;
    }
}
