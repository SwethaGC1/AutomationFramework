package vtiger.Contacts;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import vtiger.GenericUtilities.ExcelFileUtility;
import vtiger.GenericUtilities.PropertyFileUtility;
import vtiger.GenericUtilities.WebDriverUtility;
import vtiger.GenericUtilities.javaUtility;

public class vtigerContactWithOrganization {
	
	public static void main(String[] args) throws IOException {
	//Create object for all utilities
	PropertyFileUtility pUtil=new PropertyFileUtility();
	ExcelFileUtility eUtil=new ExcelFileUtility();
	WebDriverUtility wUtil=new WebDriverUtility();
	javaUtility jUtil=new javaUtility();
	
	//read all the necessary data from property and excel file
	/* read common data from property file */
	String BROWSER=pUtil.readDataFromPropertyFile("browser");
	String URL=pUtil.readDataFromPropertyFile("url");
	String USERNAME=pUtil.readDataFromPropertyFile("username");
	String PASSWORD=pUtil.readDataFromPropertyFile("password");
	
	/*Read data from Excel file for test data */
	String ORGNAME=eUtil.excelFileUtility("Contact", 7, 2)+jUtil.getRandomNumber();
	String LASTNAME=eUtil.excelFileUtility("Contact", 7, 3);
	
	WebDriver driver=null;
	
	//Step2: launch the browser-run time polymorphism
	if(BROWSER.equalsIgnoreCase("chrome"))
	{
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
	}
	else if (BROWSER.equalsIgnoreCase("edge"))
	{
		WebDriverManager.edgedriver().setup();
		driver=new EdgeDriver();
	}
	else
		System.out.println("invalid browser");
	wUtil.maximizeWindow(driver);
	wUtil.waitForPage(driver);
	driver.get(URL);
	
	//Step3: login to the application
	driver.findElement(By.name("user_name")).sendKeys(USERNAME);
	driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
	driver.findElement(By.id("submitButton")).click();
	
	//step4:click on organization module
	driver.findElement(By.partialLinkText("Organizations")).click();
	
	//step5: click on create organization look up image
	driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
	
	//step6: create organization with mandatory fields
	driver.findElement(By.name("accountname")).sendKeys(ORGNAME);
	//save
	driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
	//Step 7: validate for organization creation
	String headerValue=driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
	if(headerValue.contains(ORGNAME))
		System.out.println("organization is created");
	else
		System.out.println("organization is not created");
	
	//step8: click on contacts link
	
	driver.findElement(By.linkText("Contacts")).click();
	
	//step9: click on create contact look up image
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
	
	// Step 10: Create contact with mandatory fields and save
			driver.findElement(By.name("lastname")).sendKeys(LASTNAME);
			
		// Step 11: Click on Organizatiion look up image
			driver.findElement(By.xpath("//input[@name='account_name']/following-sibling::img[@title='Select']")).click();
		//Step12 Switch window to child
			wUtil.switchToWindow(driver, "Accounts");
			
	//step13: search for org name
	driver.findElement(By.id("search_txt")).sendKeys(ORGNAME);
	driver.findElement(By.name("search")).click();
	
	/*driver.findElement(By.linkText(ORGNAME)).click();*/
	driver.findElement(By.xpath("//a[text()='"+ORGNAME+"']")).click();
	
	//Step14: switch control to parent window and save
	wUtil.switchToWindow(driver,"Contacts");
	driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
	
	//step15:validate for contact creation
	String contactheader=driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
	if(contactheader.contains(LASTNAME))
		System.out.println("contact created");
	else
		System.out.println("contact not created");
	
	//step16:logout from the application
	WebElement logout = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
	wUtil.mouseOverAction(driver, logout);
	driver.findElement(By.partialLinkText("Sign Out")).click();
	System.out.println("logout successfully");
	
	
	}
}
