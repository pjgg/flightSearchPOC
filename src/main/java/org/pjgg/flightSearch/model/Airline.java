package org.pjgg.flightSearch.model;


import java.util.Optional;

public class Airline {

    private String code;

    private String name;

    private double infantPrice;

    public final static double NONE_INFANT_PRICE = -1.0;

    public Airline(String iataCode, String name, Optional<Double> infantPrice) {
        this.code = iataCode;
        this.name = name;
        this.infantPrice = infantPrice.orElse(NONE_INFANT_PRICE);
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public double getInfantPrice() {
        return infantPrice;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setInfantPrice(double infantPrice) {
        this.infantPrice = infantPrice;
    }
}
