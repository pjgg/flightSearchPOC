package org.pjgg.flightSearch.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.pjgg.flightSearch.connector.ConnectorServiceLocator;
import org.pjgg.flightSearch.connector.airline.AirlinesConnectorImpl;
import org.pjgg.flightSearch.dto.FlightSearchRequest;
import org.pjgg.flightSearch.model.Airline;
import org.pjgg.flightSearch.model.Flight;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.Assert.assertTrue;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ConnectorServiceLocator.class})
public class PassengerTypeImplTest {

    @Test
    public void applyAdultTest() {
        //Mock
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
        PassengerTypeImpl passengerTypeImpl = new PassengerTypeImpl();
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

        //stubbing
        when(flightSearchRequest.getAdultAmount()).thenReturn(0);
        when(flightSearchRequest.getChildAmount()).thenReturn(1);
        when(flightSearchRequest.getInfantAmount()).thenReturn(0);
        when(flightMock.getPrice()).thenReturn(100.0);
        when(flightMock.getOrigin()).thenReturn("origin");
        when(flightMock.getDestination()).thenReturn("Destination");
        when(flightMock.getAirline()).thenReturn("airline");

        //invoke
        PassengerTypeImpl passengerTypeImpl = new PassengerTypeImpl();
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
        ConnectorServiceLocator connectorServiceLocatorMock = mock(ConnectorServiceLocator.class);
        AirlinesConnectorImpl airlinesConnectorImpl = mock(AirlinesConnectorImpl.class);
        Whitebox.setInternalState(ConnectorServiceLocator.class, "INSTANCE", connectorServiceLocatorMock);
        FlightSearchRequest flightSearchRequest = mock(FlightSearchRequest.class);
        Flight flightMock = mock(Flight.class);

        //stubbing
        when(connectorServiceLocatorMock.getAirlinesConnector()).thenReturn(airlinesConnectorImpl);
        when(airlinesConnectorImpl.filterEntities(Matchers.any())).thenReturn(streamAirlinesMock);
        when(flightSearchRequest.getAdultAmount()).thenReturn(0);
        when(flightSearchRequest.getChildAmount()).thenReturn(0);
        when(flightSearchRequest.getInfantAmount()).thenReturn(1);
        when(flightMock.getPrice()).thenReturn(100.0);
        when(flightMock.getOrigin()).thenReturn("origin");
        when(flightMock.getDestination()).thenReturn("Destination");
        when(flightMock.getAirline()).thenReturn("airline");

        //invoke
        PassengerTypeImpl passengerTypeImpl = new PassengerTypeImpl();
        Flight result = passengerTypeImpl.apply(flightSearchRequest, flightMock);

        //Asserts
        assertTrue(result.getAirline().equalsIgnoreCase("airline"));
        assertTrue(result.getDestination().equalsIgnoreCase("destination"));
        assertTrue(result.getOrigin().equalsIgnoreCase("origin"));
        assertTrue(result.getPrice() == 10.0);

    }
}
