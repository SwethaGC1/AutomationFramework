package practice;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class TestCase_05_Practice {

	public static void main(String[] args) {
		//step1: launch browser
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("http://localhost:8888");
		
		//step2: login to vtiger application
		driver.findElement(By.name("user_name")).sendKeys("admin");
		driver.findElement(By.name("user_password")).sendKeys("admin");
		driver.findElement(By.id("submitButton")).click();
		
		//step3: navigate to contact module
		driver.findElement(By.linkText("Contacts")).click();
		
		//step4: create contact
		driver.findElement(By.xpath("//img[@alt='Create Contact...']")).click();
		driver.findElement(By.name("lastname")).sendKeys("shruthi");
		driver.findElement(By.xpath("//img[contains(@onclick,'return')]")).click();
		//driver.findElement(By.xpath("//tr[@class='lvtColDataHover']")).click();
		//driver.findElement(By.id("3")).click();
		String parent=driver.getWindowHandle();
		 Set<String> s = driver.getWindowHandles();
		 Iterator<String> I1= s.iterator();
		 while(I1.hasNext())
		 {

		 String child_window=I1.next();


		 if(!parent.equals(child_window))
		 {
		 driver.switchTo().window(child_window);

		 driver.findElement(By.xpath("//table[@cellspacing='1']/tbody/tr[2]/td/a")).click();


		 }
		 }
		
		driver.switchTo().window(parent);
		
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		//step5: validate for contact creation
		String headervalue=driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if(headervalue.contains("shruthi"))
			System.out.println(headervalue+"\n----pass----");
		else
			System.out.println("fail");
		
		//step6: logout from the application and close the browser
		WebElement image=driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		Actions a=new Actions(driver);
		a.moveToElement(image).perform();
		driver.findElement(By.linkText("Sign Out")).click();
		System.out.println("logged out successfully");
		driver.quit();
		

	}

}
