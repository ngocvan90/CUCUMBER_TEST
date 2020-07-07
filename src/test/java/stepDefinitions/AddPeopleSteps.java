package stepDefinitions;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import commons.PageFactoryManager;
import cucumber.api.DataTable;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pageObject.AddPeoplePage;
import runners.Hooks;

public class AddPeopleSteps {
	WebDriver driver;
	private AddPeoplePage addPeoplePage;

	public AddPeopleSteps() {
		driver = Hooks.openBrowser();
		addPeoplePage = PageFactoryManager.getAddPeoplePage(driver);
	}
	

	@When("^User clicks on Add People menu$")
	public void userClicksOnAddPeople() {
		addPeoplePage.clickOnAddPeopleMenu();
	}

	@When("^User adds some users into list$")
	public void userAddsSomeUsersIntoList(DataTable table){
		List<Map<String, String>> people = table.asMaps(String.class, String.class);
		List<String> keys = table.topCells();
		for (int i = 0; i < people.size(); i++) {
			String firstName = people.get(i).get(keys.get(0));
			String lastName = people.get(i).get(keys.get(1));
			String email = addPeoplePage.randomMailNumber() + people.get(i).get(keys.get(2));
			addPeoplePage.inputFirstName(firstName);
			addPeoplePage.inputLastNameForAPerson(firstName, lastName);
			addPeoplePage.inputEmailForAPerson(firstName, email);
		}
	}
	
	@When("^User clicks on Add People button$")
	public void userClicksOnAddPeopleButton() {
		addPeoplePage.clickOnAddPeopleButton();
	}

	@Then("^Verify that system displays successful message \"([^\"]*)\"$")
    public void verifyThatSystemDisplaysSuccessfulMessageSomething(String message){
		Assert.assertTrue(addPeoplePage.getSuccessMessage().contains(message));
    }

}
