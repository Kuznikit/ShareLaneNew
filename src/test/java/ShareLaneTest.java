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

    @Test
    public void FirstNameWithOutTextShouldRejected() {
        driver.get("https://www.sharelane.com/cgi-bin/register.py?page=1&zip_code=11111");
        driver.findElement(By.name("first_name")).sendKeys("");
        driver.findElement(By.cssSelector("[value=Register]")).click();
        String error = driver.findElement(By.cssSelector("[class=error_message]")).getText();
        assertEquals(error, "Oops, error on page. Some of your fields have invalid data or email was previously used", "First Name should have any symbols");
    }

    @Test
    public void FirstNameWithNumberShouldRejected() {
        driver.get("https://www.sharelane.com/cgi-bin/register.py?page=1&zip_code=11111");
        driver.findElement(By.name("first_name")).sendKeys("123");
        driver.findElement(By.cssSelector("[value=Register]")).click();
        String error = driver.findElement(By.cssSelector("[class=error_message]")).getText();
        assertEquals(error, "Oops, error on page. Some of your fields have invalid data or email was previously used", "First Name should have any symbols");
    }

    @Test
    public void FirstNameWithCyrillicShouldRejected() {
        driver.get("https://www.sharelane.com/cgi-bin/register.py?page=1&zip_code=11111");
        driver.findElement(By.name("first_name")).sendKeys("ЕЁиы");
        driver.findElement(By.cssSelector("[value=Register]")).click();
        String errorSingUp = driver.findElement(By.cssSelector("[class=error_message]")).getText();
        assertEquals(errorSingUp, "Oops, error on page. Some of your fields have invalid data or email was previously used", "First Name should have any symbols");
    }

    @Test
    public void EmailWithOutTextShouldRejected() {
        driver.get("https://www.sharelane.com/cgi-bin/register.py?page=1&zip_code=11111");
        driver.findElement(By.name("email")).sendKeys("");
        driver.findElement(By.cssSelector("[value=Register]")).click();
        String error = driver.findElement(By.cssSelector("[class=error_message]")).getText();
        assertEquals(error, "Oops, error on page. Some of your fields have invalid data or email was previously used", "First Name should have any symbols");
    }
    @Test
    public void EmailWithWrongTypeShouldRejected() {
        driver.get("https://www.sharelane.com/cgi-bin/register.py?page=1&zip_code=11111");
        driver.findElement(By.name("email")).sendKeys("abc");
        driver.findElement(By.cssSelector("[value=Register]")).click();
        String error = driver.findElement(By.cssSelector("[class=error_message]")).getText();
        assertEquals(error, "Oops, error on page. Some of your fields have invalid data or email was previously used", "First Name should have any symbols");
    }
    @Test
    public void PassWithOutTextShouldRejected() {
        driver.get("https://www.sharelane.com/cgi-bin/register.py?page=1&zip_code=11111");
        driver.findElement(By.name("password1")).sendKeys("");
        driver.findElement(By.cssSelector("[value=Register]")).click();
        String error = driver.findElement(By.cssSelector("[class=error_message]")).getText();
        assertEquals(error, "Oops, error on page. Some of your fields have invalid data or email was previously used", "First Name should have any symbols");
    }
    @Test
    public void PassLessThan4SymbolsShouldRejected() {
        driver.get("https://www.sharelane.com/cgi-bin/register.py?page=1&zip_code=11111");
        driver.findElement(By.name("password1")).sendKeys("111");
        driver.findElement(By.cssSelector("[value=Register]")).click();
        String error = driver.findElement(By.cssSelector("[class=error_message]")).getText();
        assertEquals(error, "Oops, error on page. Some of your fields have invalid data or email was previously used", "First Name should have any symbols");
    }
    @Test
    public void Pass2DoesntMatchPass1ShouldRejected() {
        driver.get("https://www.sharelane.com/cgi-bin/register.py?page=1&zip_code=11111");
        driver.findElement(By.name("password1")).sendKeys("1111");
        driver.findElement(By.name("password1")).sendKeys("1112");
        driver.findElement(By.cssSelector("[value=Register]")).click();
        String error = driver.findElement(By.cssSelector("[class=error_message]")).getText();
        assertEquals(error, "Oops, error on page. Some of your fields have invalid data or email was previously used", "First Name should have any symbols");
    }
    @Test
    public void RegisterIsWorking() {
        driver.get("https://www.sharelane.com/cgi-bin/register.py?page=1&zip_code=11111");
        driver.findElement(By.name("first_name")).sendKeys("abc");
        driver.findElement(By.name("email")).sendKeys("abc@gmail.com");
        driver.findElement(By.name("password1")).sendKeys("1111");
        driver.findElement(By.name("password2")).sendKeys("1111");
        driver.findElement(By.cssSelector("[value=Register]")).click();
        String isSignUpOpened = driver.findElement(By.cssSelector("[class='confirmation_message']")).getText();
        assertEquals(isSignUpOpened, "Account is created!");

    }
}
