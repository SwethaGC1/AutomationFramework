package vtiger.Contacts;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.FirefoxDriverManager;
import vtiger.GenericUtilities.ExcelFileUtility;
import vtiger.GenericUtilities.PropertyFileUtility;
import vtiger.GenericUtilities.WebDriverUtility;
import vtiger.GenericUtilities.javaUtility;
import vtiger.ObjectRepository.ContactInfoPage;
import vtiger.ObjectRepository.ContactsPage;
import vtiger.ObjectRepository.CreateContactPage;
import vtiger.ObjectRepository.CreateOrganizationPage;
import vtiger.ObjectRepository.HomePage;
import vtiger.ObjectRepository.LoginPage;
import vtiger.ObjectRepository.OrganizationInfoPage;
import vtiger.ObjectRepository.OrganizationsPage;

public class vtigerContactWithOrganizationUsingPOM {

	public static void main(String[] args) throws IOException {
		PropertyFileUtility pUtil=new PropertyFileUtility();
		ExcelFileUtility eUtil=new ExcelFileUtility();
		WebDriverUtility wUtil=new WebDriverUtility();
		javaUtility jUtil=new javaUtility();
		//WebDriver driver=new chrome
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
		
		//click on organization link to create organization
		HomePage hp=new HomePage(driver);
		hp.clickOrgnizationLink();
		
		// click on orgnization look up image
		OrganizationsPage op=new OrganizationsPage(driver);
		op.clickOrgImage();
		
		//create new organization with industry type and save		
		CreateOrganizationPage cop=new CreateOrganizationPage(driver);
		cop.clickSaveOrg(ORGNAME, "Apparel");
		
		//validate for organization header
		OrganizationInfoPage oip=new OrganizationInfoPage(driver);
		String OrgHead = oip.getOrgHeader();
		if(OrgHead.contains(ORGNAME))
			System.out.println("organization is created");
		else
			System.out.println("organization is not created");
		
		//click on contact link in home page
		hp.clickContactLink();
		
		//click on contact lppk up image
		ContactsPage cp=new ContactsPage(driver);
		cp.clickContactImg();
		
		//create new contact with organization
		CreateContactPage ccp=new CreateContactPage(driver);
		ccp.createNewContactwithOrg(driver, LASTNAME, ORGNAME);
		
		//validate for contact header
		ContactInfoPage cip=new ContactInfoPage(driver);
		String ContactHeader=cip.getContactHeader();
		if(ContactHeader.contains(LASTNAME))
			System.out.println("contact created");
		else
			System.out.println("contact not created");
		
		//Logout from the application
		hp.clickSignOutLink(driver);
		System.out.println("logout successfully");
		
			}
}
