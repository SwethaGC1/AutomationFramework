package practice;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import vtiger.ObjectRepository.LoginPage;

public class POMClassPractice {

	public static void main(String[] args) {
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("http://localhost:8888");
		
		LoginPage lg=new LoginPage(driver);
			//lg.getUsernameEdt().sendKeys("admin");
			//lg.getPasswordEdt().sendKeys("admin");
			//lg.getSubmitBtn().click();
		lg.loginToApp("admin","admin");
	}
}
