package stepDefinitions;

import Pages.VirtualGame;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import util.DriverFactory;

public class VirtualGameStepDefinitions {

    WebDriver driver = DriverFactory.getDriver();
    VirtualGame virtualGame = new VirtualGame(driver);

    @Given("User navigates to the Sanal Oyunlar page")
    public void userNavigatesToVirtualGamesPage() {
        virtualGame.navigateToVirtualGamePage();
    }
    @When("User clicks on the Inspired 3 game button")
    public void userClicksOnTheGameButton() {
       virtualGame.clickOnTheGameButton();
    }
    @Then("The user receives an {string} message in a new window")
    public void checkUnsuccessfulVirtualGames(String message) {
       virtualGame.checkUnsuccessfulPlay(message);
    }
}
