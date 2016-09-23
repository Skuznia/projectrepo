package regtest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Register1 {
	private static final Logger log4j = LogManager.getLogger(Register1.class.getName());
	
	public static void main(String[] args){
		log4j.trace("Initializing Program."); 
		//log4j.debug("Debug message test."); 
		String firstName = "John";
		String lastName = "Doe";
		Register1 test = new Register1();
		log4j.trace("Running runReg(" + firstName + "," + lastName + ")");
		test.runReg(firstName,lastName);
		//log4j.info("Info message test."); 
		//log4j.error("Error message test.");
		
	}
	public void runReg(String first, String last){
		String firstName = first;
		String lastName = last;
		WebDriver driver = new ChromeDriver();
		this.regSurfing(driver);
		this.inputInfo(driver, firstName, lastName);
		driver.quit();
	}
	
	public void regSurfing(WebDriver d){
		WebDriverWait wait = new WebDriverWait(d, 10);
		log4j.trace("Directing to qa5.caringbridge.org");
		//direct to qa5
		d.get("https://qa5.caringbridge.org/");
		try{
			WebElement element = d.findElement(By.xpath("//a[@data-qa-id='global-nav-login']"));
			//wait until we can find the element
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@data-qa-id='global-nav-login']")));
			log4j.trace("Directing to signin/register page.");
			//click redirects us to next register page
			element.click();
			
			log4j.trace("Directing to register page.");
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@data-qa-id='profile-signin-signup']")));
			//click redirects us to register page
			d.findElement(By.xpath("//a[@data-qa-id='profile-signin-signup']")).click();
		}catch(Exception e){
			log4j.error("Exception in refSurfing: NoSuchElementException");
		}
	}
	
	public String inputInfo(WebDriver d, String first, String last){
		WebDriverWait wait = new WebDriverWait(d, 10);
		log4j.trace("Initializing Emailgeneratorv2 class.");
		Emailgeneratorv2 gen = new Emailgeneratorv2();
		log4j.trace("Calling emailgen() from Emailgeneratorv2 object.");
		//generate an email string
		String email = gen.emailgen();
		try{
			log4j.trace("Inputting info.");
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@data-qa-id='profile-signup-first-name']")));
			//input first name
			d.findElement(By.xpath("//input[@data-qa-id='profile-signup-first-name']")).sendKeys(first);
			//input last name
			d.findElement(By.xpath("//input[@data-qa-id='profile-signup-last-name']")).sendKeys(last);		
			
			//register with email
			d.findElement(By.xpath("//input[@data-qa-id='profile-signup-email']")).sendKeys(email);
			//input password
			d.findElement(By.xpath("//input[@data-qa-id='profile-signup-password']")).sendKeys("abc123");
			//click on terms of agreement
			d.findElement(By.xpath("//input[@data-qa-id='profile-signup-terms']")).click();
			//click on register
			d.findElement(By.xpath("//input[@data-qa-id='profile-signup-submit']")).click();
			log4j.trace("Submitting account registration.");
		}catch(Exception e){
			log4j.error("Exception in inputInfo: " + e);
		}
		return email;
		
	}
	public boolean verifyLogin(WebDriver d){
		WebDriverWait wait = new WebDriverWait(d,10);
		try{
			//check if the sign in (aka not logged in) button is there.
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@data-qa-id='global-nav-home']")));
			if(d.findElements(By.xpath("//a[@href='https://qa5.caringbridge.org/signin?returl=%2F']")).size() != 0){
				return false;
			}
		}catch(Exception e){
			log4j.error("Exception in verifyLogin: " + e);
		}
		return true;
	}
	
}
