package practice;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class TestCase_02_Practicejava {
	
	public static void main(String[] args) {
		//step1: launch the browser
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("http://localhost:8888");
		
		//step2: login to the application
		driver.findElement(By.name("user_name")).sendKeys("admin");
		driver.findElement(By.name("user_password")).sendKeys("admin");
		driver.findElement(By.id("submitButton")).click();
		
		//step3:navigate to organization link
		
		driver.findElement(By.linkText("Organizations")).click();
		
		//step4: create organization
		driver.findElement(By.xpath("//img[@alt='Create Organization...']")).click();
		driver.findElement(By.name("accountname")).sendKeys("qsp");
		WebElement radiobutton=driver.findElement(By.xpath("//input[@value='T']"));
		radiobutton.click();
		if(radiobutton.isSelected())
		{
			System.out.println(" group is selected");
		WebElement group=driver.findElement(By.name("assigned_group_id"));
			Select s3=new Select(group);
		s3.selectByValue("2");
		}
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		//step5: validation for organization creation
		String organizationHeader= driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if(organizationHeader.contains("qsp"))
			System.out.println(organizationHeader+"\n---Pass----");
		else
			System.out.println("fail");
		
		//step6: logout from the application
		WebElement act=driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		Actions a=new Actions(driver);
		a.moveToElement(act).perform();
		driver.findElement(By.linkText("Sign Out")).click();
		System.out.println("logout successfully");
		
	}
		
	}


