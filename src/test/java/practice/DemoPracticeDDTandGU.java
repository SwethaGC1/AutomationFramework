package practice;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
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

public class DemoPracticeDDTandGU {
	public static void main(String[] args) throws EncryptedDocumentException, IOException {
		//Step1: Create object for all utilities
			    PropertyFileUtility pUtil=new PropertyFileUtility();
			    ExcelFileUtility eUtil=new ExcelFileUtility();
			    WebDriverUtility wUtil=new WebDriverUtility();
			    javaUtility jUtil=new javaUtility();
			    
			    //Step2: read all the necessary data
			    /*Read data from property file- common data*/
				String URL=pUtil.readDataFromPropertyFile("url");
				String BROWSER=pUtil.readDataFromPropertyFile("browser");
				String USERNAME=pUtil.readDataFromPropertyFile("username");
				String PASSWORD=pUtil.readDataFromPropertyFile("password");
				
				/*read test data from excel sheet-test data */
				
				String ORGNAME=eUtil.excelFileUtility("Contact", 1, 2)+jUtil.getRandomNumber();
								
				WebDriver driver=null;
				
				//Step3: Launch browser-runtime polymorphism
				if(BROWSER.equalsIgnoreCase("chrome"))
				{
					WebDriverManager.chromedriver().setup();
					driver=new ChromeDriver();
				}
				else if (BROWSER.equalsIgnoreCase("edge"))
				{
					//WebDriverManager.firefoxdriver().setup();
					//driver=new FirefoxDriver();
					WebDriverManager.edgedriver().setup();
					driver=new EdgeDriver();
				}
				else 
					System.out.println("invalid browser");
				
				wUtil.maximizeWindow(driver);
				wUtil.waitForPage(driver);
				driver.get(URL);
				
				//STep3: login to the vtiger application
				
				driver.findElement(By.name("user_name")).sendKeys(USERNAME);
				driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
				driver.findElement(By.id("submitButton")).click();
				
				//Step4: navigate to contacts module
				driver.findElement(By.linkText("Organizations")).click();
				
				//step5: create contact in organization
				driver.findElement(By.xpath("//img[@alt='Create Organization...']")).click();
				driver.findElement(By.name("accountname")).sendKeys(ORGNAME);
				driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
				
				//Step6:validattion for orgnization cretion
				
				String orgHeader=driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
				if(orgHeader.contains(ORGNAME))
					System.out.println(orgHeader+"\n Pass");
				else
					System.out.println("fail");
				
				//Step7: logout from the application
				WebElement logout = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
				wUtil.mouseOverAction(driver, logout);
				driver.findElement(By.linkText("Sign Out")).click();
				System.out.println("logout success");
				
	}

}

