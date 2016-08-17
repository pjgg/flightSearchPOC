package org.pjgg.flightSearch.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.runners.MockitoJUnitRunner;
import org.pjgg.flightSearch.connector.airline.AirlinesConnectorImpl;
import org.pjgg.flightSearch.dto.FlightSearchRequest;
import org.pjgg.flightSearch.model.Airline;
import org.pjgg.flightSearch.model.Flight;
import org.pjgg.flightSearch.service.pricing.PassengerTypeImpl;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.Assert.assertTrue;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PassengerTypeImplTest {

    @Test
    public void applyAdultTest() {
        //Mock
        AirlinesConnectorImpl airlinesConnectorImpl = mock(AirlinesConnectorImpl.class);
        FlightSearchRequest flightSearchRequest = mock(FlightSearchRequest.class);
        Flight flightMock = mock(Flight.class);

        //stubbing
        when(flightSearchRequest.getAdultAmount()).thenReturn(2);
        when(flightSearchRequest.getChildAmount()).thenReturn(0);
        when(flightSearchRequest.getInfantAmount()).thenReturn(0);
        when(flightMock.getPrice()).thenReturn(100.0);
        when(flightMock.getOrigin()).thenReturn("origin");
        when(flightMock.getDestination()).thenReturn("Destination");
        when(flightMock.getAirline()).thenReturn("airline");

        //invoke
        PassengerTypeImpl passengerTypeImpl = new PassengerTypeImpl(airlinesConnectorImpl);
        Flight result = passengerTypeImpl.apply(flightSearchRequest, flightMock);

        //Asserts
        assertTrue(result.getAirline().equalsIgnoreCase("airline"));
        assertTrue(result.getDestination().equalsIgnoreCase("destination"));
        assertTrue(result.getOrigin().equalsIgnoreCase("origin"));
        assertTrue(result.getPrice() == 200.0);

    }

    @Test
    public void applyChildTest() {
        //Mock
        FlightSearchRequest flightSearchRequest = mock(FlightSearchRequest.class);
        Flight flightMock = mock(Flight.class);
        AirlinesConnectorImpl airlinesConnectorImpl = mock(AirlinesConnectorImpl.class);

        //stubbing
        when(flightSearchRequest.getAdultAmount()).thenReturn(0);
        when(flightSearchRequest.getChildAmount()).thenReturn(1);
        when(flightSearchRequest.getInfantAmount()).thenReturn(0);
        when(flightMock.getPrice()).thenReturn(100.0);
        when(flightMock.getOrigin()).thenReturn("origin");
        when(flightMock.getDestination()).thenReturn("Destination");
        when(flightMock.getAirline()).thenReturn("airline");

        //invoke
        PassengerTypeImpl passengerTypeImpl = new PassengerTypeImpl(airlinesConnectorImpl);
        Flight result = passengerTypeImpl.apply(flightSearchRequest, flightMock);

        //Asserts
        assertTrue(result.getAirline().equalsIgnoreCase("airline"));
        assertTrue(result.getDestination().equalsIgnoreCase("destination"));
        assertTrue(result.getOrigin().equalsIgnoreCase("origin"));
        assertTrue(result.getPrice() == 67.0);

    }

    @Test
    public void applyInfantTest() {

        Airline a = new Airline("IB", "Iberia", Optional.of(10.0));
        Stream<Airline> streamAirlinesMock = Arrays.asList(a).stream();

        //Mock
        AirlinesConnectorImpl airlinesConnectorImpl = mock(AirlinesConnectorImpl.class);
        FlightSearchRequest flightSearchRequest = mock(FlightSearchRequest.class);
        Flight flightMock = mock(Flight.class);

        //stubbing
        when(airlinesConnectorImpl.filterEntities(Matchers.any())).thenReturn(streamAirlinesMock);
        when(flightSearchRequest.getAdultAmount()).thenReturn(0);
        when(flightSearchRequest.getChildAmount()).thenReturn(0);
        when(flightSearchRequest.getInfantAmount()).thenReturn(1);
        when(flightMock.getPrice()).thenReturn(100.0);
        when(flightMock.getOrigin()).thenReturn("origin");
        when(flightMock.getDestination()).thenReturn("Destination");
        when(flightMock.getAirline()).thenReturn("airline");

        //invoke
        PassengerTypeImpl passengerTypeImpl = new PassengerTypeImpl(airlinesConnectorImpl);
        Flight result = passengerTypeImpl.apply(flightSearchRequest, flightMock);

        //Asserts
        assertTrue(result.getAirline().equalsIgnoreCase("airline"));
        assertTrue(result.getDestination().equalsIgnoreCase("destination"));
        assertTrue(result.getOrigin().equalsIgnoreCase("origin"));
        assertTrue(result.getPrice() == 10.0);

    }
}
