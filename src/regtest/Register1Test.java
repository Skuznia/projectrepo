package regtest;

import static org.junit.Assert.*;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Register1Test {

	@Test
	public void test() {
		String firstName = "John";
		String lastName = "Doe";
		Register1 test = new Register1();
		WebDriver driver = new ChromeDriver();
		WebDriverWait wait = new WebDriverWait(driver, 10);
		test.regSurfing(driver);
		String email = test.inputInfo(driver, firstName, lastName);
		
		//check if the sign in (aka not logged in) button is there.
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@data-qa-id='global-nav-home']")));
		if(driver.findElements(By.xpath("//a[@href='https://qa5.caringbridge.org/signin?returl=%2F']")).size() != 0){
			fail("Failed to successfully sign in.");
		}
		
		//heading to profile
		driver.findElement(By.xpath("//a[@data-qa-id='global-nav-home']")).click();
		//verifying that the registration process completed successful
		driver.get("https://qa5.caringbridge.org/settings");
				
		//checking firstName
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='firstName']")));
		WebElement element = driver.findElement(By.xpath("//input[@id='firstName']"));
		
		assertEquals("First Name differnet from the one entered.",firstName,element.getAttribute("value"));
		//checking last name
		element = driver.findElement(By.xpath("//input[@id='lastName']"));
		assertEquals("Last Name differnet from the one entered.",lastName,element.getAttribute("value"));
		
		//checking email
		element = driver.findElement(By.xpath("//input[@id='email']"));
		assertEquals("Email differs from the one entered.",email,element.getAttribute("value"));
		
		driver.quit();
		
		//fail("Not yet implemented");
	}

}
