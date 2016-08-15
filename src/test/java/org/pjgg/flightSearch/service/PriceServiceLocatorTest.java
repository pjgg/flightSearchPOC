package org.pjgg.flightSearch.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest({PriceServiceLocator.class})
public class PriceServiceLocatorTest {

    @Test
    public void getPassagerTypeCalculator() {
        //Mock
        PassengerTypeImpl passengerTypeImpl = mock(PassengerTypeImpl.class);
        PriceServiceLocator priceServiceLocator = mock(PriceServiceLocator.class);
        Whitebox.setInternalState(PriceServiceLocator.class, "INSTANCE", priceServiceLocator);

        //Stubbing
        when(priceServiceLocator.getPassagerTypeCalculator()).thenReturn(passengerTypeImpl);

        //Invoke
        PriceServiceLocator.INSTANCE.getPassagerTypeCalculator();

        //Verify
        verify(priceServiceLocator, times(1)).getPassagerTypeCalculator();

    }

    @Test
    public void getPricingRulesCalculator() {
        //Mock
        PricingRuleImpl pricingRuleImpl = mock(PricingRuleImpl.class);
        PriceServiceLocator priceServiceLocator = mock(PriceServiceLocator.class);
        Whitebox.setInternalState(PriceServiceLocator.class, "INSTANCE", priceServiceLocator);

        //Stubbing
        when(priceServiceLocator.getPricingRulesCalculator()).thenReturn(pricingRuleImpl);

        //Invoke
        PriceServiceLocator.INSTANCE.getPricingRulesCalculator();

        //Verify
        verify(priceServiceLocator, times(1)).getPricingRulesCalculator();

    }

}
