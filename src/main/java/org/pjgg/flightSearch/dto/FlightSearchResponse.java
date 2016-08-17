package org.pjgg.flightSearch.dto;


public class FlightSearchResponse {

    private String flightCode;

    private double price;

    public FlightSearchResponse(String flightCode, Double price) {
        this.flightCode = flightCode;
        this.price = price;
    }

    public String getFlightCode() {
        return flightCode;
    }

    public void setFlightCode(String flightCode) {
        this.flightCode = flightCode;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
