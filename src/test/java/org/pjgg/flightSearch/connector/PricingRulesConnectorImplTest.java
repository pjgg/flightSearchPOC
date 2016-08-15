package org.pjgg.flightSearch.connector;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.pjgg.flightSearch.connector.airport.AirportsConnectorImpl;
import org.pjgg.flightSearch.connector.pricingrules.PricingRulesConnectorImpl;
import org.pjgg.flightSearch.connector.pricingrules.PricingRulesPredicates;
import org.pjgg.flightSearch.model.Airport;
import org.pjgg.flightSearch.model.PricingRules;

import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class PricingRulesConnectorImplTest {

    @Test
    public void addEntityTest(){

        PricingRules pricingRules = new PricingRules(0,15,100);

        //invoke
        PricingRulesConnectorImpl pricingRulesConnectorImpl = new PricingRulesConnectorImpl();
        pricingRulesConnectorImpl.addEntity(pricingRules);

        //Assert
        assertTrue(pricingRulesConnectorImpl.filterEntities(PricingRulesPredicates.isBetween(5)).count() == 1);

    }


    @Test
    public void findEntityTest(){

        PricingRules pricingRules = new PricingRules(0,15,100);

        //invoke
        PricingRulesConnectorImpl pricingRulesConnectorImpl = new PricingRulesConnectorImpl();
        pricingRulesConnectorImpl.addEntity(pricingRules);
        pricingRulesConnectorImpl.addEntity(pricingRules);
        pricingRulesConnectorImpl.addEntity(pricingRules);

        //Assert
        assertTrue(pricingRulesConnectorImpl.filterEntities(PricingRulesPredicates.isBetween(5)).count() == 3);

    }

}
