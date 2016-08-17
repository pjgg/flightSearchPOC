Feature: Search flight by route

  As a user I would life to retrieve a flight reference through a given route

  @loadAirports
  @loadFlights
  @loadAirlines
  @loadPricingRules
  @CleanData
  Scenario Outline: Search flight by route
    Given an "<airportOriginCode>" "<airportDestinationCode>" <departureFromNowInDays> <adultAmount> <childAmount> <infantAmount>
    When when you perform a search
    Then you get a list of flights where the following code is included "<expectedFlightCode>" and price is "<expectedPrice>" and the amount of flights retrieved is <expentedAmountOfRecords>

    Examples:
      | airportOriginCode | airportDestinationCode|departureFromNowInDays|adultAmount|childAmount|infantAmount|expectedFlightCode|expectedPrice   |expentedAmountOfRecords |
      |       BCN         |           MAD         |       17             |      1    |    0      |    0       |       IB2171     |    259         |           2            |
      |       BCN         |           MAD         |       19             |      1    |    0      |    0       |       LH5496     |    293         |           2            |
      |       AMS         |           FRA         |       31             |      1    |    0      |    0       |       TK2372     |    157.6       |           3            |
      |       LHR         |           IST         |       15             |      2    |    1      |    1       |       TK8891     |    806         |           2            |
      |       BCN         |           MAD         |       2              |      1    |    2      |    0       |       IB2171     |    909.09      |           2            |
      |       CDG         |           FRA         |       40             |      1    |    2      |    0       |       IB2171     |    -1          |          0             |
