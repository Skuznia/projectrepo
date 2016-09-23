package regtest;

import static org.junit.Assert.*;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Register1Test {
	private static final Logger log4j = LogManager.getLogger(Register1.class.getName());

	@Test
	public void test() {
		log4j.trace("Initializing Program.");
		String firstName = "John";
		String lastName = "Doe";
		Register1 test = new Register1();
		WebDriver driver = new ChromeDriver();
		WebDriverWait wait = new WebDriverWait(driver, 10);
		log4j.trace("Running regSurfing.");
		test.regSurfing(driver);
		log4j.trace("Running inputInfo.");
		String email = test.inputInfo(driver, firstName, lastName);
		log4j.trace("Verifying Login Successful, initializing verifyLogin().");
		if(test.verifyLogin(driver) == false){
			fail("Failed to successfully sign in.");
			driver.quit();
		}
		try{
			//heading to profile
			driver.findElement(By.xpath("//a[@data-qa-id='global-nav-home']")).click();
			//verifying that the registration process completed successful
			log4j.trace("Directing to qa5.caringbridge.org/settings");
			driver.get("https://qa5.caringbridge.org/settings");	
			//checking firstName
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='firstName']")));
			log4j.trace("Verifying all the expected information matches the real information.");
			WebElement element = driver.findElement(By.xpath("//input[@id='firstName']"));
			assertEquals("First Name differnet from the one entered.",firstName,element.getAttribute("value"));
			//checking last name
			element = driver.findElement(By.xpath("//input[@id='lastName']"));
			assertEquals("Last Name differnet from the one entered.",lastName,element.getAttribute("value"));
			
			//checking email
			element = driver.findElement(By.xpath("//input[@id='email']"));
			assertEquals("Email differs from the one entered.",email,element.getAttribute("value"));
		}catch(Exception e){
			log4j.error("Exception found in test(): " + e);
			driver.quit();
			fail("Something went wrong..");
		}
		driver.quit();
		
		//fail("Not yet implemented");
	}

}
