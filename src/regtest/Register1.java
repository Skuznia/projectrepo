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
		log4j.debug("Debug message."); 
		
		Register1 test = new Register1();
		test.runReg("John","Doe");
		
		log4j.info("Info message."); 
		log4j.error("Error message");
		
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
		
		//direct to qa5
		d.get("https://qa5.caringbridge.org/");
				
		WebElement element = d.findElement(By.xpath("//a[@data-qa-id='global-nav-login']"));
		//wait until we can find the element
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@data-qa-id='global-nav-login']")));
		
		//click redirects us to next register page
		element.click();
				
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@data-qa-id='profile-signin-signup']")));
		//click redirects us to register page
		d.findElement(By.xpath("//a[@data-qa-id='profile-signin-signup']")).click();
	}
	
	public String inputInfo(WebDriver d,String first, String last){
		WebDriverWait wait = new WebDriverWait(d, 10);
		Emailgeneratorv2 gen = new Emailgeneratorv2();
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@data-qa-id='profile-signup-first-name']")));
		//input first name
		d.findElement(By.xpath("//input[@data-qa-id='profile-signup-first-name']")).sendKeys(first);
		//input last name
		d.findElement(By.xpath("//input[@data-qa-id='profile-signup-last-name']")).sendKeys(last);		
		//generate an email string
		String email = gen.emailgen();
		//print out the email string
		System.out.println(email);
		//register with email
		d.findElement(By.xpath("//input[@data-qa-id='profile-signup-email']")).sendKeys(email);
		//input password
		d.findElement(By.xpath("//input[@data-qa-id='profile-signup-password']")).sendKeys("abc123");
		//click on terms of agreement
		d.findElement(By.xpath("//input[@data-qa-id='profile-signup-terms']")).click();
		//click on register
		d.findElement(By.xpath("//input[@data-qa-id='profile-signup-submit']")).click();
		return email;
	}
}
