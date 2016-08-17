package org.pjgg.flightSearch.converters;


public enum ConverterServiceLocator {

    INSTANCE;

    private final Converter flightToFlightSearchResponseConverter;

    private ConverterServiceLocator() {
        flightToFlightSearchResponseConverter = new FlightToFlightSearchResponseConverter();
    }

    public Converter getFlightToFlightSearchResponseConverter() {
        return this.flightToFlightSearchResponseConverter;
    }

}
