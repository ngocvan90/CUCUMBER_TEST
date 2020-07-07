package commons;

import org.openqa.selenium.WebDriver;


public class BaseClass {
	public static WebDriver driver;

	public static void main(String args[]) throws Throwable {
		try {
			cucumber.api.cli.Main.main(new String[] { "classpath:features", "-t", "@scenario_AccountPage", "-g",
					"com.sadakar.cucumber.stepdefinitions/", "-g", "com.sadakar.cucumber.common", "-p", "pretty", "-p",
					"json:target/cucumber-reports/Cucumber.json", "-p", "html:target/cucumber-reports", "-m" });
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
