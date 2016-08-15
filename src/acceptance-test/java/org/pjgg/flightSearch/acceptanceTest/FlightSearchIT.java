package org.pjgg.flightSearch.acceptanceTest;


import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.pjgg.flightSearch.dto.FlightSearchRequest;
import org.pjgg.flightSearch.dto.FlightSearchResponse;
import org.pjgg.flightSearch.service.FlightSearchService;
import org.pjgg.flightSearch.service.FlightServiceLocator;

import java.util.Collections;
import java.util.List;
import static org.junit.Assert.*;

public class FlightSearchIT {

    private FlightSearchService flightSearchService = FlightServiceLocator.INSTANCE.getFlightSearchService();

    private FlightSearchRequest flightSearchRequest;

    private List<FlightSearchResponse> flightSearchResponseList = Collections.emptyList();

    @Given("^an \"([^\"]*)\" \"([^\"]*)\" (\\d+) (\\d+) (\\d+) (\\d+)$")
    public void an(String airportOriginCode, String airportDestinationCode, int daysUntilDeparture, int adultAmount, int childAmount, int infantAmount) throws Throwable {
        flightSearchRequest = new FlightSearchRequest(airportOriginCode, airportDestinationCode, daysUntilDeparture, adultAmount, childAmount, infantAmount);
    }

    @When("^when you perform a search$")
    public void when_you_perform_a_search() throws Throwable {
        flightSearchResponseList = flightSearchService.findFlights(flightSearchRequest);
    }


    @Then("^you get a list of flights where the following code is included \"([^\"]*)\" and price is \"([^\"]*)\" and the amount of flights retrieved is (\\d+)$")
    public void you_get_a_list_of_flights_where_the_following_code_is_included_and_price_is_and_the_amount_of_flights_retrieved_is(String expectedFlightCode, String ePrice, int expectedAmountRetrived) throws Throwable {

        final double expectedPrice = Double.valueOf(ePrice);

        assertTrue("UnExpected amount of flights", flightSearchResponseList.size() == expectedAmountRetrived);
        FlightSearchResponse flightSearchResponse = flightSearchResponseList.stream().filter(FlightSearchResponsePredicate.isAirlineFlightCode(expectedFlightCode)).findFirst().orElse(new FlightSearchResponse("None", -1.0));
        assertFalse(flightSearchResponse.getFlightCode().equalsIgnoreCase("None"));

        assertTrue("invalid epected price", flightSearchResponse.getPrice() == expectedPrice);
    }

}
