package runners;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "src/test/resources/features", 
		glue = { "stepDefinitions" },
		// dryRun = true,
		monochrome = true, 
				 plugin = { "pretty","html:target/site/cucumber-reports", "json:target/site/cucumber.json"}, 
		snippets = SnippetType.CAMELCASE)
public class TestRunner {
//	private static String[] defaultOptions = { "--glue", "stepdefinitions", "--plugin", "pretty", "--plugin",
//			"html:target/site/cucumber-reports", "--plugin", "json:target/site/cucumber.json" };
//
//	public static void main(String[] args) {
//		try {
//			Stream<String> cucumberOptions = Stream.concat(Stream.of(defaultOptions), Stream.of(args));
//			Main.main(cucumberOptions.toArray(String[]::new));
//		} catch (Throwable e) {
//			e.printStackTrace();
//		}
//	}
}
