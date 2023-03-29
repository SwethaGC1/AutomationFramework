package vtiger.Lead;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.FirefoxDriverManager;
import vtiger.GenericUtilities.ExcelFileUtility;
import vtiger.GenericUtilities.PropertyFileUtility;
import vtiger.GenericUtilities.WebDriverUtility;
import vtiger.ObjectRepository.CreateLeadFromQuickSearchPage;
import vtiger.ObjectRepository.HomePage;
import vtiger.ObjectRepository.LeadInfoPage;
import vtiger.ObjectRepository.LoginPage;

public class CreateLeadByQuichSearchUsingPOM {
	

	public static void main(String[] args) throws IOException {
		PropertyFileUtility pUtil=new PropertyFileUtility();
		ExcelFileUtility eUtil=new ExcelFileUtility();
		WebDriverUtility wUtil=new WebDriverUtility();
		
		//WebDriver driver=new chrome
		String BROWSER=pUtil.readDataFromPropertyFile("browser");
		String URL=pUtil.readDataFromPropertyFile("url");
		String USERNAME=pUtil.readDataFromPropertyFile("username");
		String PASSWORD=pUtil.readDataFromPropertyFile("password");
		
		/*Read data from Excel file for test data */
		String COMPANY=eUtil.excelFileUtility("Lead", 1, 3);
		String LASTNAME=eUtil.excelFileUtility("Lead", 1, 2);
		
		WebDriver driver=null;
		//Step2: launch the browser-run time polymorphism
		if(BROWSER.equalsIgnoreCase("chrome"))
		{
			ChromeDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
		}
		else if(BROWSER.equalsIgnoreCase("firefox"))
		{
			FirefoxDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
		}
		else
			System.out.println("invalid browser");
		//web utility for maximize and implicitly wait
		wUtil.maximizeWindow(driver);
		wUtil.waitForPage(driver);
		driver.get(URL);
		
		
		//login page call pom class
		LoginPage lg=new LoginPage(driver);
		lg.loginToApp(USERNAME, PASSWORD);
		
		//call createleadfromquicksearch pom class to crete and save new lead
		CreateLeadFromQuickSearchPage clfqs=new CreateLeadFromQuickSearchPage(driver);
		clfqs.CreateLeadQuickSearch(driver, "New Lead", COMPANY, LASTNAME);
		
		//validate for lead cretion call pom class
		LeadInfoPage lip=new LeadInfoPage(driver);
		String LEADHEADER=lip.getLeadHeader();
		if(LEADHEADER.contains(LASTNAME))
			System.out.println("lead created");
		else
			System.out.println("lead not created");
		
		//call homepage to logout application
		HomePage hp=new HomePage(driver);
		hp.clickSignOutLink(driver);
		System.out.println("logout successfully");
	}
}
		
