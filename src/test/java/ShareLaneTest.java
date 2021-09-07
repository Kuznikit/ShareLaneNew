import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class ShareLaneTest extends BaseTest {
    @Test
    public void LinkSignUpShouldWork() {
        driver.get("https://www.sharelane.com/cgi-bin/main.py");
        driver.findElement(By.linkText("Sign up")).click();
        boolean isSignUpOpened = driver.findElement(By.cssSelector("[value=Continue]")).isDisplayed();
        assertTrue(isSignUpOpened, "Sign Up was not opened");
    }

    @Test
    public void validZipCodeShouldBeAccepted() {
        driver.get("https://www.sharelane.com/cgi-bin/register.py");
        driver.findElement(By.name("zip_code")).sendKeys("11111");
        driver.findElement(By.cssSelector("[value=Continue]")).click();
        boolean isPageOpened = driver.findElement(By.cssSelector("[value=Register]")).isDisplayed();
        assertTrue(isPageOpened, "Sign Up was not opened");
    }

    @Test
    public void ZipCodeShouldBeRequired() {
        driver.get("https://www.sharelane.com/cgi-bin/register.py");
        driver.findElement(By.cssSelector("[value=Continue]")).click();
        String error = driver.findElement(By.cssSelector("[class=error_message]")).getText();
        assertEquals(error, "Oops, error on page. ZIP code should have 5 digits", "text is not correct");
    }

    @Test
    public void ZipCodeWithMoreThan6CharShouldRejected() {
        driver.get("http://www.sharelane.com/cgi-bin/register.py");
        driver.findElement(By.name("zip_code")).sendKeys("111111");
        driver.findElement(By.cssSelector("[value=Continue]")).click();
        String error = driver.findElement(By.cssSelector("[class=error_message]")).getText();
        assertEquals(error, "Oops, error on page. ZIP code should have 5 digits", "text is not correct");
    }

    @Test
    public void ZipCodeWithTextShouldRejected() {
        driver.get("http://www.sharelane.com/cgi-bin/register.py");
        driver.findElement(By.name("zip_code")).sendKeys("aaa");
        driver.findElement(By.cssSelector("[value=Continue]")).click();
        String error = driver.findElement(By.cssSelector("[class=error_message]")).getText();
        assertEquals(error, "Oops, error on page. ZIP code should have 5 digits", "text is not correct");
    }
}
