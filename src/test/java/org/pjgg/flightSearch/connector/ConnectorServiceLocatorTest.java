package org.pjgg.flightSearch.connector;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.pjgg.flightSearch.connector.airline.AirlinesConnectorImpl;
import org.pjgg.flightSearch.connector.airport.AirportsConnectorImpl;
import org.pjgg.flightSearch.connector.flight.FlightConnectorImpl;
import org.pjgg.flightSearch.connector.pricingrules.PricingRulesConnectorImpl;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ConnectorServiceLocator.class})
public class ConnectorServiceLocatorTest {

    @Test
    public void airlinesConnectorTest() {
        //Mock
        AirlinesConnectorImpl airlinesConnectorImpl = mock(AirlinesConnectorImpl.class);
        ConnectorServiceLocator connectorServiceLocator = mock(ConnectorServiceLocator.class);
        Whitebox.setInternalState(ConnectorServiceLocator.class, "INSTANCE", connectorServiceLocator);

        //Stubbing
        when(connectorServiceLocator.getAirlinesConnector()).thenReturn(airlinesConnectorImpl);

        //Invoke
        ConnectorServiceLocator.INSTANCE.getAirlinesConnector();

        //Verify
        verify(connectorServiceLocator, times(1)).getAirlinesConnector();
    }

    @Test
    public void airportConnectorTest() {
        //Mock
        AirportsConnectorImpl airportsConnectorImpl = mock(AirportsConnectorImpl.class);
        ConnectorServiceLocator connectorServiceLocator = mock(ConnectorServiceLocator.class);
        Whitebox.setInternalState(ConnectorServiceLocator.class, "INSTANCE", connectorServiceLocator);

        //Stubbing
        when(connectorServiceLocator.getAirportsConnector()).thenReturn(airportsConnectorImpl);

        //Invoke
        ConnectorServiceLocator.INSTANCE.getAirportsConnector();

        //Verify
        verify(connectorServiceLocator, times(1)).getAirportsConnector();
    }

    @Test
    public void flightConnectorImplTest() {
        //Mock
        FlightConnectorImpl flightConnectorImpl = mock(FlightConnectorImpl.class);
        ConnectorServiceLocator connectorServiceLocator = mock(ConnectorServiceLocator.class);
        Whitebox.setInternalState(ConnectorServiceLocator.class, "INSTANCE", connectorServiceLocator);

        //Stubbing
        when(connectorServiceLocator.getFlightConnector()).thenReturn(flightConnectorImpl);

        //Invoke
        ConnectorServiceLocator.INSTANCE.getFlightConnector();

        //Verify
        verify(connectorServiceLocator, times(1)).getFlightConnector();
    }

    @Test
    public void pricingRulesConnectorTest() {
        //Mock
        PricingRulesConnectorImpl pricingRulesConnectorImpl = mock(PricingRulesConnectorImpl.class);
        ConnectorServiceLocator connectorServiceLocator = mock(ConnectorServiceLocator.class);
        Whitebox.setInternalState(ConnectorServiceLocator.class, "INSTANCE", connectorServiceLocator);

        //Stubbing
        when(connectorServiceLocator.getPricingRulesConnector()).thenReturn(pricingRulesConnectorImpl);

        //Invoke
        ConnectorServiceLocator.INSTANCE.getPricingRulesConnector();

        //Verify
        verify(connectorServiceLocator, times(1)).getPricingRulesConnector();
    }
}
