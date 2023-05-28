package HRM;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;
import orangeHRM.pageobjects.LoginPage;
import orangeHRM.pageobjects.AddingNewUser;
import orangeHRM.pageobjects.LogOut;
public class OHRM {
	
	WebDriver wd;
	
	@BeforeSuite
	public void Launching() {
	
		WebDriverManager.chromedriver().setup();
		wd = new ChromeDriver();		  
		wd.manage().window().maximize();
		wd.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
	}
	
	@BeforeTest
	public void Login() {
		
		LoginPage loginpage = new LoginPage(wd);
		loginpage.goTo();
		loginpage.loginApplication("Admin","cU@L6YXm3o");
	}
	
	@Test(enabled=true, priority=0) 
	public void AddingNewUser() throws InterruptedException {
			
		AddingNewUser HrAdministration = new AddingNewUser(wd);
		HrAdministration.HRAdmin();
		
		HrAdministration.UserDetails("p", "kavyas", "Kavya@1524", "Kavya@1524");
		HrAdministration.SaveUser();
		
		LogOut logout = new LogOut(wd);
		logout.logoutApplication();
		
		LoginPage loginpage = new LoginPage(wd);
		loginpage.loginByNewUser("kavyas", "Kavya@1524");
		
		// Verifying UserName
		HrAdministration.Verifying();
	    
		// Deleting Added User
		logout.logoutApplication();
		loginpage.loginApplication("Admin","cU@L6YXm3o");
		HrAdministration.DeleteUser();
		logout.logoutApplication();
		
	}
	
	@AfterSuite
	public void Quit() {
		wd.quit();
	}
}
