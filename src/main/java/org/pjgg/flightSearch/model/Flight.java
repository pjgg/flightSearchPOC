package org.pjgg.flightSearch.model;


public class Flight {

    private String origin;

    private String destination;

    private String airline;

    private double price;

    public Flight(String origin, String destination, String airline, double price) {
        this.origin = origin;
        this.destination = destination;
        this.airline = airline;
        this.price = price;
    }

    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }

    public String getAirline() {
        return airline;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

}
