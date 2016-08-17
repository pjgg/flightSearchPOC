package org.pjgg.flightSearch.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.pjgg.flightSearch.service.pricing.PassengerTypeImpl;
import org.pjgg.flightSearch.service.pricing.PricingRuleImpl;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ServiceLocator.class})
public class ServiceLocatorTest {

    @Test
    public void getPassagerTypeCalculator() {
        //Mock
        PassengerTypeImpl passengerTypeImpl = mock(PassengerTypeImpl.class);
        ServiceLocator serviceLocator = mock(ServiceLocator.class);
        Whitebox.setInternalState(ServiceLocator.class, "INSTANCE", serviceLocator);

        //Stubbing
        when(serviceLocator.getPassagerTypeCalculator()).thenReturn(passengerTypeImpl);

        //Invoke
        ServiceLocator.INSTANCE.getPassagerTypeCalculator();

        //Verify
        verify(serviceLocator, times(1)).getPassagerTypeCalculator();

    }

    @Test
    public void getPricingRulesCalculator() {
        //Mock
        PricingRuleImpl pricingRuleImpl = mock(PricingRuleImpl.class);
        ServiceLocator serviceLocator = mock(ServiceLocator.class);
        Whitebox.setInternalState(ServiceLocator.class, "INSTANCE", serviceLocator);

        //Stubbing
        when(serviceLocator.getPricingRulesCalculator()).thenReturn(pricingRuleImpl);

        //Invoke
        ServiceLocator.INSTANCE.getPricingRulesCalculator();

        //Verify
        verify(serviceLocator, times(1)).getPricingRulesCalculator();

    }

}
