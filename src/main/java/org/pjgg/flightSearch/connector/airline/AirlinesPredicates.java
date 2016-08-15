package org.pjgg.flightSearch.connector.airline;


import org.pjgg.flightSearch.model.Airline;

import java.util.function.Predicate;

public class AirlinesPredicates {

    public static Predicate<Airline> isAlwaysTrue() {
        return p -> true;
    }

    public static Predicate<Airline> hasAirlineChildFixePrice(String code) {
        return airline -> airline.getCode().equalsIgnoreCase(code) && airline.getInfantPrice() != Airline.NONE_INFANT_PRICE;
    }

}
