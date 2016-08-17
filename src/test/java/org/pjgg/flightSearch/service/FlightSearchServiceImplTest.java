package org.pjgg.flightSearch.service;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.runners.MockitoJUnitRunner;
import org.pjgg.flightSearch.connector.flight.FlightConnectorImpl;
import org.pjgg.flightSearch.converters.ConverterServiceLocator;
import org.pjgg.flightSearch.converters.FlightToFlightSearchResponseConverter;
import org.pjgg.flightSearch.dto.FlightSearchRequest;
import org.pjgg.flightSearch.dto.FlightSearchResponse;
import org.pjgg.flightSearch.model.Flight;
import org.pjgg.flightSearch.service.pricing.PassengerTypeImpl;
import org.pjgg.flightSearch.service.pricing.PricingRuleImpl;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(MockitoJUnitRunner.class)
public class FlightSearchServiceImplTest {

    @Test
    public void findFlightsTest() {

        Flight flight = new Flight("origin","Dest","airline",100.0);
        Stream<Flight> flights = Arrays.asList(flight).stream();

        FlightSearchResponse flightSearchResponse = new FlightSearchResponse("code", 100.0);
        List<FlightSearchResponse> FlightSearchResponseList = Arrays.asList(flightSearchResponse);

        //Mock
        FlightSearchRequest flightSearchRequest = mock(FlightSearchRequest.class);

        PricingRuleImpl pricingRuleImpl = mock(PricingRuleImpl.class);
        PassengerTypeImpl passengerTypeImpl= mock(PassengerTypeImpl.class);
        FlightConnectorImpl flightConnectorImpl = mock(FlightConnectorImpl.class);
        FlightToFlightSearchResponseConverter flightToFlightSearchResponseConverter = mock(FlightToFlightSearchResponseConverter.class);

        //Stubbing
        when(flightConnectorImpl.filterEntities(Matchers.any())).thenReturn(flights);
        when(pricingRuleImpl.applyPricingRules(flightSearchRequest, flights)).thenReturn(flights);
        when(passengerTypeImpl.applyPricingRules(flightSearchRequest, flights)).thenReturn(flights);
        when(flightToFlightSearchResponseConverter.convertToList(flights)).thenReturn(FlightSearchResponseList);

        //Invoke
        FlightSearchServiceImpl flightSearchServiceImpl = new FlightSearchServiceImpl(flightConnectorImpl, pricingRuleImpl, passengerTypeImpl, flightToFlightSearchResponseConverter);
        List<FlightSearchResponse> result = flightSearchServiceImpl.findFlights(flightSearchRequest);

        //Assert
        assertTrue(result.get(0).getFlightCode().equalsIgnoreCase("code"));
        assertTrue(result.get(0).getPrice() == 100.0);

        //Verify
        verify(flightConnectorImpl, times(1)).filterEntities(Matchers.any());
        verify(pricingRuleImpl, times(1)).applyPricingRules(flightSearchRequest, flights);
        verify(passengerTypeImpl, times(1)).applyPricingRules(flightSearchRequest, flights);
        verify(flightToFlightSearchResponseConverter, times(1)).convertToList(flights);

    }

}
