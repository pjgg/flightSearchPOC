package org.pjgg.flightSearch.connector;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.pjgg.flightSearch.connector.airline.AirlinesConnectorImpl;
import org.pjgg.flightSearch.connector.airline.AirlinesPredicates;
import org.pjgg.flightSearch.connector.flight.FlightConnectorImpl;
import org.pjgg.flightSearch.connector.flight.FlightPredicates;
import org.pjgg.flightSearch.model.Airline;
import org.pjgg.flightSearch.model.Flight;

import java.util.Optional;

import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class FlightConnectorImplTest {

    @Test
    public void addEntityTest() {

        Flight flight = new Flight("origin", "destination", "airline", 100.0);

        //invoke
        FlightConnectorImpl flightConnectorImpl = new FlightConnectorImpl();
        flightConnectorImpl.addEntity(flight);

        //Assert
        assertTrue(flightConnectorImpl.filterEntities(FlightPredicates.isAlwaysTrue()).count() == 1);

    }

    @Test
    public void removeEntityTest() {

        Flight flight = new Flight("origin", "destination", "airline", 100.0);

        //invoke
        FlightConnectorImpl flightConnectorImpl = new FlightConnectorImpl();
        flightConnectorImpl.addEntity(flight);
        flightConnectorImpl.removeEntities(FlightPredicates.isAlwaysTrue());

        //Assert
        assertTrue(flightConnectorImpl.filterEntities(FlightPredicates.isAlwaysTrue()).count() == 0);

    }

    @Test
    public void findEntityTest() {

        Flight flight = new Flight("origin", "destination", "airline", 100.0);

        //invoke
        FlightConnectorImpl flightConnectorImpl = new FlightConnectorImpl();
        flightConnectorImpl.addEntity(flight);
        flightConnectorImpl.addEntity(flight);
        flightConnectorImpl.addEntity(flight);

        //Assert
        assertTrue(flightConnectorImpl.filterEntities(FlightPredicates.isAlwaysTrue()).count() == 3);
    }


}
