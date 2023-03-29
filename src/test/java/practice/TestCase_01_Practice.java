package practice;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class TestCase_01_Practice {

	public static void main(String[] args) {
		//step1: Launch the browser
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("http://localhost:8888");
		
		//step2:login to the application
		driver.findElement(By.name("user_name")).sendKeys("admin");
		driver.findElement(By.name("user_password")).sendKeys("admin");
		driver.findElement(By.id("submitButton")).click();
		
		//step3: navigate to contacts link
		driver.findElement(By.linkText("Contacts")).click();
		
		//step4: click on create contact image
		
		driver.findElement(By.xpath("//img[@alt='Create Contact...']")).click();
		driver.findElement(By.name("lastname")).sendKeys("swetha");
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		//step5: verification for contact
		String contactHeader=driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if(contactHeader.contains("swetha"))
		
			System.out.println(contactHeader+"---Pass--");
	
		else
			System.out.println("fail");
		//step6: logout the application
		WebElement element=driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		Actions a=new Actions(driver);
		a.moveToElement(element).perform();
		driver.findElement(By.linkText("Sign Out")).click();
		System.out.println("logout successfully");
	}
}
