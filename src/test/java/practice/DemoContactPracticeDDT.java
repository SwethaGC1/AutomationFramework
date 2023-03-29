package practice;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DemoContactPracticeDDT {

	public static void main(String[] args) throws EncryptedDocumentException, IOException {
		//Step1: Read the data from property and excel files
				/* read common data from property file*/
				FileInputStream fisp=new FileInputStream(".\\src\\test\\resources\\CommonData.properties");
				Properties pObj=new Properties();
				pObj.load(fisp);
				String URL=pObj.getProperty("url");
				String BROWSER=pObj.getProperty("browser");
				String USERNAME=pObj.getProperty("username");
				String PASSWORD=pObj.getProperty("password");
				
				/*read test data from excel file */
				FileInputStream fise=new FileInputStream(".\\src\\test\\resources\\testdata.xlsx");
				Workbook wb= WorkbookFactory.create(fise);
				String LastName=wb.getSheet("Contact").getRow(4).getCell(2).getStringCellValue();
				       //qspider123
				
				WebDriver driver=null;
				
				//Step2: Launch browser
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
				
				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
				driver.get(URL);
				
				//STep3: login to the vtiger application
				
				driver.findElement(By.name("user_name")).sendKeys(USERNAME);
				driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
				driver.findElement(By.id("submitButton")).click();
				
				//Step4: naviagte to contacts module
				driver.findElement(By.linkText("Contacts")).click();
				
				//step5: click on create contact image
				
				driver.findElement(By.xpath("//img[@alt='Create Contact...']")).click();
				driver.findElement(By.name("lastname")).sendKeys(LastName);
				driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
				
				//Step6: validate contact creation
				String headerValue=driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
				if(headerValue.contains(LastName))
					System.out.println(LastName+"\n Pass");
				else
					System.out.println("fail");
				
				//Step7:Logout from the application
				
				WebElement logout = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
				Actions a=new Actions(driver);
				a.moveToElement(logout).perform();
				driver.findElement(By.linkText("Sign Out")).click();
				System.out.println("logout success");
				
	}

}
