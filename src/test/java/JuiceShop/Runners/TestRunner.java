package JuiceShop.Runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"json:target/cucumber.json","html:target/default-cucumber-reports","rerun:@target/rerun.txt"},
        features = "src/test/resources/features",
        glue = "JuiceShop/Step_Defenitions",
        dryRun=false,
        tags="@SQLInjection"
)
public class TestRunner {

}
