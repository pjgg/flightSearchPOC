package org.pjgg.flightSearch.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.pjgg.flightSearch.connector.ConnectorServiceLocator;
import org.pjgg.flightSearch.connector.pricingrules.PricingRulesConnectorImpl;
import org.pjgg.flightSearch.dto.FlightSearchRequest;
import org.pjgg.flightSearch.model.Flight;
import org.pjgg.flightSearch.model.PricingRules;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ConnectorServiceLocator.class})
public class PricingRuleImplTest {

    @Test
    public void applyTest() {

      PricingRules p = new PricingRules(16, 30, 100);
      Stream<PricingRules> streamPricingRulesMock = Arrays.asList(p).stream();

      //Mock
     FlightSearchRequest flightSearchRequest = mock(FlightSearchRequest.class);
     Flight flightMock = mock(Flight.class);
     ConnectorServiceLocator connectorServiceLocatorMock = mock(ConnectorServiceLocator.class);
     PricingRulesConnectorImpl pricingRulesConnectorImpl = mock(PricingRulesConnectorImpl.class);
     Whitebox.setInternalState(ConnectorServiceLocator.class, "INSTANCE", connectorServiceLocatorMock);


     //Stubbing
     when(connectorServiceLocatorMock.getPricingRulesConnector()).thenReturn(pricingRulesConnectorImpl);
     when(flightSearchRequest.getDepartureFrom()).thenReturn(19);
     when(pricingRulesConnectorImpl.filterEntities(Matchers.any())).thenReturn(streamPricingRulesMock);
     when(flightMock.getPrice()).thenReturn(100.0);
     when(flightMock.getOrigin()).thenReturn("origin");
     when(flightMock.getDestination()).thenReturn("destination");
     when(flightMock.getAirline()).thenReturn("airline");

     //Invoke
     PricingRuleImpl pricingRuleImpl = new PricingRuleImpl();
     Flight result = pricingRuleImpl.apply(flightSearchRequest, flightMock);

     //Asserts
     assertTrue(result.getAirline().equalsIgnoreCase("airline"));
     assertTrue(result.getDestination().equalsIgnoreCase("destination"));
     assertTrue(result.getOrigin().equalsIgnoreCase("origin"));
     assertTrue(result.getPrice() == 100.0);

     //Verify
      verify(pricingRulesConnectorImpl, times(1)).filterEntities(Matchers.any());

    }

}
