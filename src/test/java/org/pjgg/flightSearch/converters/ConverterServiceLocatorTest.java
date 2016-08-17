package org.pjgg.flightSearch.converters;

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
@PrepareForTest({ConverterServiceLocator.class})
public class ConverterServiceLocatorTest {

    @Test
    public void ConverterServiceLocatorTest() {
        //Mock
        FlightToFlightSearchResponseConverter flightToFlightSearchResponseConverter = mock(FlightToFlightSearchResponseConverter.class);
        ConverterServiceLocator converterServiceLocator = mock(ConverterServiceLocator.class);
        Whitebox.setInternalState(ConverterServiceLocator.class, "INSTANCE", converterServiceLocator);

        //Stubbing
        when(converterServiceLocator.getFlightToFlightSearchResponseConverter()).thenReturn(flightToFlightSearchResponseConverter);

        //Invoke
        ConverterServiceLocator.INSTANCE.getFlightToFlightSearchResponseConverter();

        //Verify
        verify(converterServiceLocator, times(1)).getFlightToFlightSearchResponseConverter();
    }
}
