package vtiger.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import vtiger.GenericUtilities.WebDriverUtility;

public class HomePage extends WebDriverUtility {//rule1: create pom class for every page
	
	//rule2: find elements using @findby, @findall, @findbys
	
	/*web Element to click contact */
	@FindBy(partialLinkText="Contacts")
	private WebElement contactslink;
	
	/*web Element to click organization */
	@FindBy(partialLinkText = "Organizations")
	private WebElement organizationlink;
	
	/*web element to find administrator image to sign out */
	@FindBy(xpath = "//img[@src='themes/softed/images/user.PNG']")
	private WebElement administratorImg;
	
	/*web Element to click signout */
	@FindBy(partialLinkText = "Sign Out")
	private WebElement signOutlink;
	
		
	//rule3: create constructor to intialize webelements-intialization
	public HomePage(WebDriver driver)
	{
		PageFactory.initElements(driver,this);
	}

	//rule4: provide getters to access these elements-utilization
	public WebElement getContactslink() {
		return contactslink;
	}

	public WebElement getOrganizationlink() {
		return organizationlink;
	}

	public WebElement getSignOutlink() {
		return signOutlink;
	}
	
	
	public WebElement getAdministratorImg() {
		return administratorImg;
	}

	

	//business library
	/**
	 * This method is used to click on contact link in home page
	 */
	public void clickContactLink()
	{
		contactslink.click();
	}
	
	/**
	 * this method is used to click on organization link in home page
	 */
	public void clickOrgnizationLink()
	{
		organizationlink.click();
	}
	
	/**
	 * this method will do mouse over action on administrator image and click sign out link
	 * @param driver
	 */
	public void clickSignOutLink(WebDriver driver)
	{
		mouseOverAction(driver, administratorImg);
		signOutlink.click();
	}
	
	
	
	
}
