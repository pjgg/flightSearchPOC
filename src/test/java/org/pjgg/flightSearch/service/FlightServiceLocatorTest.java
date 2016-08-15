package org.pjgg.flightSearch.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest({FlightServiceLocator.class})
public class FlightServiceLocatorTest {

    @Test
    public void getFlightServiceLocator() {
        //Mock
        FlightSearchServiceImpl flightSearchServiceImpl = mock(FlightSearchServiceImpl.class);
        FlightServiceLocator flightServiceLocator = mock(FlightServiceLocator.class);
        Whitebox.setInternalState(FlightServiceLocator.class, "INSTANCE", flightServiceLocator);

        //Stubbing
        when(flightServiceLocator.getFlightSearchService()).thenReturn(flightSearchServiceImpl);

        //Invoke
        FlightServiceLocator.INSTANCE.getFlightSearchService();

        //Verify
        verify(flightServiceLocator, times(1)).getFlightSearchService();
    }

}
