
import org.junit.runner.RunWith;

import cucumber.api.junit.Cucumber;
import cucumber.api.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(format = { "pretty", "html:target/cucumber-html-report" }
 ,features="src/acceptance-test/resources/features/FlightSearch.feature"
)
public class RunCukesIT {
	
}

