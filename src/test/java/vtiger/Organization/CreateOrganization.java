package vtiger.Organization;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

public class CreateOrganization {

	public static void main(String[] args) throws IOException {
		Random ran=new Random(1000);//orgnization not allow duplicate,so it generates random value for every run we can append that number to name to avoid duplicate
		int random=ran.nextInt();
		
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
		String OrgName=wb.getSheet("Organization").getRow(1).getCell(2).getStringCellValue()+random;
		       //qspider123
		WebDriver driver=null;
		
		//step2: launch browser-run time polymorphism
		if(BROWSER.equalsIgnoreCase("chrome"))
		
			driver=new ChromeDriver();
		
		else if (BROWSER.equalsIgnoreCase("firefox"))
			driver=new FirefoxDriver();
		else 
			System.out.println("invalid browser");
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(URL);
		
		//Step3: Login to the Vtiger application
		
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
		
		//step4: Navigate to the organization module
		driver.findElement(By.linkText("Organizations")).click();
	
		//step5: create contact in organization
		driver.findElement(By.xpath("//img[@alt='Create Organization...']")).click();
		driver.findElement(By.name("accountname")).sendKeys(OrgName);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		//Step6:validattion for orgnization cretion
		
		String orgHeader=driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if(orgHeader.contains(OrgName))
			System.out.println(orgHeader+"\n Pass");
		else
			System.out.println("fail");
		
		//Step7: logout from the application
		WebElement logout = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		Actions a=new Actions(driver);
		a.moveToElement(logout).perform();
		driver.findElement(By.linkText("Sign Out")).click();
		System.out.println("logout success");
		
		

	}

	

}
