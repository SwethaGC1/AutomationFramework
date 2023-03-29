package vtiger.Lead;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Set;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;



public class CreateLeadByQuicksearch {
	
	public static void main(String[] args) throws IOException {
		//step1: Read data from property file 
		FileInputStream fis=new FileInputStream(".\\src\\test\\resources\\CommonData.properties");
		Properties property=new Properties();
		property.load(fis);
		String URL = property.getProperty("url");
		String BROWSER=property.getProperty("browser");
		String USERNAME=property.getProperty("username");
		String PASSWORD=property.getProperty("password");
		
		//Read data from excel file
		
		FileInputStream fise=new FileInputStream(".\\src\\test\\resources\\testdata.xlsx");
		Workbook wb= WorkbookFactory.create(fise);
		Sheet sh=wb.getSheet("Lead");
		//Row rw=sh.getRow(1);
		//Cell ce=rw.getCell(1);
		//String data=ce.getStringCellValue();
		String COMPANY=sh.getRow(1).getCell(3).getStringCellValue();
		String LASTNAME=sh.getRow(1).getCell(2).getStringCellValue();
		//launch browser
		WebDriver driver=null;
		
		if(BROWSER.equalsIgnoreCase("chrome"))
		{
			driver=new ChromeDriver();
		}
		else if(BROWSER.equalsIgnoreCase("edge"))
			driver=new EdgeDriver();
		else
			System.out.println("invalid browser");
		//load url with maximizing and synchronizing the driver
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(URL);
		
		//login to the application
		
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
		
		//go to quick search drop down in home page for new lead
		WebElement dropdown = driver.findElement(By.xpath("//td[@style='padding-left:10px']/select"));
		Select s=new Select(dropdown);
		s.selectByVisibleText("New Lead");
		
		//switch control to new window create lead and save
		String parent = driver.getWindowHandle();
		Set<String> winIds = driver.getWindowHandles();
		
		//navigate to each window
		for(String win:winIds)
		{
			//switch to window and get title
			String winTitle=driver.switchTo().window(win).getTitle();
			if(winTitle.contains("create Lead"))
				break;
		}
		driver.findElement(By.name("company")).sendKeys(COMPANY);
		driver.findElement(By.name("lastname")).sendKeys(LASTNAME);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		driver.switchTo().window(parent);
		//validate for lead creation
		String leadheader=driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if(leadheader.contains(LASTNAME))
			System.out.println("lead created");
		else
			System.out.println("lead not created");
		
		//logout appication
		//logout
		WebElement image=driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		Actions a=new Actions(driver);
		a.moveToElement(image).perform();
		driver.findElement(By.linkText("Sign Out")).click();
		System.out.println("logged out successfully");
		
		
		
	
}

}
