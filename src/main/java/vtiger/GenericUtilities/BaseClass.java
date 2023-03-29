package vtiger.GenericUtilities;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;
import vtiger.ObjectRepository.HomePage;
import vtiger.ObjectRepository.LoginPage;

/**
 * This calss consists of basic configuration annotations of TestNG
 * @author The Drawing Corner
 *
 */
public class BaseClass   {

	public PropertyFileUtility pUtil=new PropertyFileUtility();
	public ExcelFileUtility eUtil=new ExcelFileUtility();
	public javaUtility jUtil=new javaUtility();
	public WebDriverUtility wUtil=new WebDriverUtility();
	public static WebDriver sdriver;//only for listneres , instead of creating object or making base class static
	public WebDriver driver;
	
	@BeforeSuite(groups = {"regressionSuite","smokeSuite"})
	public void bsConfig()
	{
		System.out.println("---database connection successfull-----");
	}
	//@Parameters("browser")
	//@BeforeTest
	@BeforeClass(groups = {"regressionSuite","smokeSuite"})
	public void bcConfig(/*String BROWSER*/) throws IOException
	{
		String BROWSER=pUtil.readDataFromPropertyFile("browser");
		String URL=pUtil.readDataFromPropertyFile("url");
		
		//step2: launch the browser--runtime polymorphism
		if(BROWSER.equalsIgnoreCase("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
			System.out.println("-----"+BROWSER+" launched successfully---");
		}
		else if(BROWSER.equalsIgnoreCase("edge"))
		{
			WebDriverManager.edgedriver().setup();
			driver=new EdgeDriver();
			System.out.println("-----"+BROWSER+" launched successfully---");
		}
		else
			System.out.println("invalid browser");
		sdriver=driver;//listners
		wUtil.maximizeWindow(driver);
		wUtil.waitForPage(driver);
		driver.get(URL);
		
	}
	
	@BeforeMethod(groups = {"regressionSuite","smokeSuite"})
	public void bmConfig() throws IOException
	{
		
		String USERNAME=pUtil.readDataFromPropertyFile("username");
		String PASSWORD=pUtil.readDataFromPropertyFile("password");
		//Login to application
		LoginPage lp=new LoginPage(driver);
		lp.loginToApp(USERNAME, PASSWORD);
		System.out.println("login successfull");		
	}
	
	@AfterMethod(groups = {"regressionSuite","smokeSuite"})
	public void amConfig() {
		HomePage hp=new HomePage(driver);
		hp.clickSignOutLink(driver);
		System.out.println("logout successfull");
	}
	
	@AfterClass(groups = {"regressionSuite","smokeSuite"})
	public void acConfig()
	{
		driver.quit();
		System.out.println("Browser closed succesfully");
	}
	
	
	@AfterSuite(groups = {"regressionSuite","smokeSuite"})
	public void asConfig()
	{
		
		System.out.println("---database connection successfull-----");	
	}
	
	
}
