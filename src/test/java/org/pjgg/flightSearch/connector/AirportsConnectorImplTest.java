package org.pjgg.flightSearch.connector;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.pjgg.flightSearch.connector.airport.AirportsConnectorImpl;
import org.pjgg.flightSearch.model.Airport;

import static org.junit.Assert.assertTrue;
import static org.pjgg.flightSearch.connector.airport.AirportPredicates.isAlwaysTrue;

@RunWith(MockitoJUnitRunner.class)
public class AirportsConnectorImplTest {

    @Test
    public void addEntityTest(){

        Airport airport = new Airport("code", "Madrid");

        //invoke
        AirportsConnectorImpl airportsConnectorImpl = new AirportsConnectorImpl();
        airportsConnectorImpl.addEntity(airport);

        //Assert
        assertTrue(airportsConnectorImpl.filterEntities(isAlwaysTrue()).count() == 1);

    }

    @Test
    public void removeEntityTest(){

        Airport airport = new Airport("code", "Madrid");

        //invoke
        AirportsConnectorImpl airportsConnectorImpl = new AirportsConnectorImpl();
        airportsConnectorImpl.addEntity(airport);
        airportsConnectorImpl.removeEntities(isAlwaysTrue());

        //Assert
        assertTrue(airportsConnectorImpl.filterEntities(isAlwaysTrue()).count() == 0);

    }

    @Test
    public void findEntityTest(){

        Airport airport = new Airport("code", "Madrid");

        //invoke
        AirportsConnectorImpl airportsConnectorImpl = new AirportsConnectorImpl();
        airportsConnectorImpl.addEntity(airport);
        airportsConnectorImpl.addEntity(airport);
        airportsConnectorImpl.addEntity(airport);

        //Assert
        assertTrue(airportsConnectorImpl.filterEntities(isAlwaysTrue()).count() == 3);

    }
}
