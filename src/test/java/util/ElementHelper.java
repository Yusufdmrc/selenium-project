    package util;
    import org.openqa.selenium.By;
    import org.openqa.selenium.WebDriver;
    import org.openqa.selenium.WebElement;
    import org.openqa.selenium.interactions.Actions;
    import org.openqa.selenium.support.ui.ExpectedConditions;
    import org.openqa.selenium.support.ui.WebDriverWait;

    import java.time.Duration;

    public class ElementHelper {
        WebDriver driver;
        WebDriverWait wait;
        Actions action;

        public ElementHelper(WebDriver driver){
            this.driver = driver;
            this.wait = new WebDriverWait(driver, Duration.ofSeconds(Constants.PAGE_LOAD_TIMEOUT));
            this.action = new Actions(driver);
        }

        public void checkVisible(WebElement element){
            wait.until(ExpectedConditions.visibilityOf(element));
        }

        public void checkClickable(WebElement element){
            wait.until(ExpectedConditions.elementToBeClickable(element));
        }

        public void click(WebElement element) {
           checkClickable(element);
           element.click();
        }
        public void checkNotVisible(WebElement element) {
            try {
                wait.until(ExpectedConditions.invisibilityOf(element));
                System.out.println("Element " + element.toString() + " is not visible as expected.");
            } catch (Exception e) {
                throw new AssertionError("Element " + element.toString() + " is visible");
            }
        }

        public void pause(int seconds) {
            try {
                Thread.sleep(seconds * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }








