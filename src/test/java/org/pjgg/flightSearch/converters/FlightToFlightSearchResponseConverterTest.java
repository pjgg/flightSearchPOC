package org.pjgg.flightSearch.converters;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.pjgg.flightSearch.dto.FlightSearchResponse;
import org.pjgg.flightSearch.model.Flight;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class FlightToFlightSearchResponseConverterTest {

    @Test
    public void applyTest() {

        //Mock
        Flight flight = mock(Flight.class);

        //Stubbing
        when(flight.getPrice()).thenReturn(100.0);
        when(flight.getAirline()).thenReturn("IB1234");

        //Invoke
        FlightToFlightSearchResponseConverter converter = new FlightToFlightSearchResponseConverter();
        FlightSearchResponse result = converter.apply(flight);

        //Assert
        assertTrue(result.getFlightCode().equalsIgnoreCase("IB1234"));
        assertTrue(result.getPrice() == 100.0);
    }

}
