package execute;

import org.junit.runner.RunWith;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "src/test/java/stepdefinitions",
				glue= {"stepdefinitions"}
		)
public class TestRunner {

}
