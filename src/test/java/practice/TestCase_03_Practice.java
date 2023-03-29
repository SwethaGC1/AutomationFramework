package practice;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class TestCase_03_Practice {
	
	public static void main(String[] args) {
		//step1:launch browser
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("http://localhost:8888");
		
		//step2: login to the application vtiger
		
		driver.findElement(By.name("user_name")).sendKeys("admin");
		driver.findElement(By.name("user_password")).sendKeys("admin");
		driver.findElement(By.id("submitButton")).click();
		
		//step3: navigate to organization module
		
		driver.findElement(By.linkText("Organizations")).click();
		
		//step4: create organization 
		
		driver.findElement(By.xpath("//img[@alt='Create Organization...']")).click();
		driver.findElement(By.name("accountname")).sendKeys("jspiders");
		WebElement dropdown=driver.findElement(By.name("industry"));
		Select s=new Select(dropdown);
		s.selectByValue("Chemicals");
		WebElement radiobutton=driver.findElement(By.xpath("//input[@value='T']"));
		radiobutton.click();
		if(radiobutton.isSelected())
		{
			System.out.println(" group is selected");
		WebElement group=driver.findElement(By.name("assigned_group_id"));
			Select s3=new Select(group);
		s3.selectByValue("3");
		}
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		//step5: validate for the organization creation
		String headervalue=driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if(headervalue.contains("jspiders"))
			System.out.println(headervalue+"\n----Pass-----");
		else
			System.out.println("fail");
		
		//step6: logout form the application and browser
		WebElement logout=driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		Actions a=new Actions(driver);
		a.moveToElement(logout).perform();
		driver.findElement(By.linkText("Sign Out")).click();
		System.out.println("logut successfully");
		driver.quit();
		
	}
}
