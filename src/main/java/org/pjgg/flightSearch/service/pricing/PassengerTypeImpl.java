package org.pjgg.flightSearch.service.pricing;


import org.pjgg.flightSearch.connector.airline.AirlinesConnector;
import org.pjgg.flightSearch.connector.airline.AirlinesPredicates;
import org.pjgg.flightSearch.model.Airline;
import org.pjgg.flightSearch.model.Flight;
import org.pjgg.flightSearch.dto.FlightSearchRequest;
import org.pjgg.flightSearch.utils.CustomMaths;

import java.util.stream.Stream;

public class PassengerTypeImpl implements PriceCalculator {

    private AirlinesConnector airlinesConnector;

    private final static int PERCENTAGE = 67; //33% discount

    public PassengerTypeImpl(AirlinesConnector airlinesConnector){
        this.airlinesConnector = airlinesConnector;
    }

    @Override
    public Flight apply(final FlightSearchRequest flightSearchRequest, final Flight flight) {

       final int adultAmount = flightSearchRequest.getAdultAmount();
       final int childAmount = flightSearchRequest.getChildAmount();
       final int infantAmount = flightSearchRequest.getInfantAmount();

       final Flight flightAdultApplied = (adultAmount > 0)?applyAdultPassangerTypeRules(adultAmount,flight):new Flight("None","None","None",0.0);
       final Flight flightChildApplied = (childAmount > 0)?applyChildPassangerTypeRules(childAmount,flight):new Flight("None","None","None",0.0);
       final Flight flightInfantApplied = (infantAmount > 0)?applyInfantPassangerTypeRules(infantAmount,flight):new Flight("None","None","None",0.0);

       final double totalPrice = flightAdultApplied.getPrice() + flightChildApplied.getPrice() + flightInfantApplied.getPrice();
       final Flight resultFlight = new Flight(flight.getOrigin(),flight.getDestination(),flight.getAirline(), totalPrice);

       return resultFlight;
    }

    private Flight applyAdultPassangerTypeRules(final int adultAmount, final Flight flight){

       final double adultTotalPrice = adultAmount * flight.getPrice();
       final Flight adultResultFlight = new Flight(flight.getOrigin(), flight.getDestination(), flight.getAirline(), adultTotalPrice);

       return adultResultFlight;
    }

    private Flight applyChildPassangerTypeRules(final int childAmount, final Flight flight){
       final double childTotalPrice = childAmount * (CustomMaths.applyPercentage(PERCENTAGE, flight.getPrice()));
       final Flight childResultFlight = new Flight(flight.getOrigin(), flight.getDestination(), flight.getAirline(), childTotalPrice);

       return childResultFlight;
    }

    private Flight applyInfantPassangerTypeRules(final int infantAmount, final Flight flight){
        final Stream<Airline> airlineStream =  airlinesConnector.filterEntities(AirlinesPredicates.hasAirlineChildFixePrice(flight.getAirline().substring(0,2)));
        final Airline airlineFixPrice = airlineStream.findFirst().get();
        final double infantTotalPrice = infantAmount * airlineFixPrice.getInfantPrice();
        final Flight infantResultFlight = new Flight(flight.getOrigin(), flight.getDestination(), flight.getAirline(), infantTotalPrice);

        return infantResultFlight;
    }
}
