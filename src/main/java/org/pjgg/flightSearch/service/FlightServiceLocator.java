package org.pjgg.flightSearch.service;


public enum FlightServiceLocator {

    INSTANCE;

    private final FlightSearchService flightSearchService;

    private FlightServiceLocator(){
        flightSearchService = new FlightSearchServiceImpl();
    }

    public FlightSearchService getFlightSearchService(){return this.flightSearchService;}

}
