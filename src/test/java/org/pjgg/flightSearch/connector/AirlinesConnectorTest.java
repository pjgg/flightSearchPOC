package org.pjgg.flightSearch.connector;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.pjgg.flightSearch.connector.airline.AirlinesConnectorImpl;
import org.pjgg.flightSearch.connector.airline.AirlinesPredicates;
import org.pjgg.flightSearch.model.Airline;

import java.util.Optional;

import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class AirlinesConnectorTest {

    @Test
    public void addEntityTest(){

        Airline airline = new Airline("CODE", "NAME", Optional.of(10.0));

        //invoke
        AirlinesConnectorImpl airlineConnectorImpl = new AirlinesConnectorImpl();
        airlineConnectorImpl.addEntity(airline);

        //Assert
        assertTrue(airlineConnectorImpl.filterEntities(AirlinesPredicates.isAlwaysTrue()).count() == 1);

    }

    @Test
    public void removeEntityTest(){

        Airline airline = new Airline("CODE", "NAME", Optional.of(10.0));

        //invoke
        AirlinesConnectorImpl airlineConnectorImpl = new AirlinesConnectorImpl();
        airlineConnectorImpl.addEntity(airline);
        airlineConnectorImpl.removeEntities(AirlinesPredicates.isAlwaysTrue());

        //Assert
        assertTrue(airlineConnectorImpl.filterEntities(AirlinesPredicates.isAlwaysTrue()).count() == 0);

    }

    @Test
    public void findEntityTest(){

        Airline airline = new Airline("CODE", "NAME", Optional.of(10.0));

        //invoke
        AirlinesConnectorImpl airlineConnectorImpl = new AirlinesConnectorImpl();
        airlineConnectorImpl.addEntity(airline);
        airlineConnectorImpl.addEntity(airline);
        airlineConnectorImpl.addEntity(airline);

        //Assert
        assertTrue(airlineConnectorImpl.filterEntities(AirlinesPredicates.isAlwaysTrue()).count() == 3);

    }

}
