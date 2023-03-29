package vtiger.ObjectRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import vtiger.GenericUtilities.WebDriverUtility;

/**
 * 
 * @author Swetha GC
 *
 */
public class CreateContactPage extends WebDriverUtility {//rule1: create pom class for every page
	
	//rule2: find webElements using @findby, @findall, @findbys
	
	@FindBy(name="lastname")
	private WebElement lastnameEdt;
	
	@FindBy(xpath = "//input[@name='account_name']/following-sibling::img[@title='Select']")
	private WebElement OrgLookUpImg;
	
	@FindBy(id = "search_txt")
	private WebElement searchEdt;
	
	@FindBy(name = "search")
	private WebElement searchBtn;
	
	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement savebtn;
	
	//rule3: create constructor to intialize web element
	public CreateContactPage(WebDriver driver)
	{
		PageFactory.initElements(driver,this);
	}

	//rule4: generate getters to access these elements
	public WebElement getLastnameEdt() {
		return lastnameEdt;
	}

	public WebElement getOrgLookUpImg() {
		return OrgLookUpImg;
	}
	
	public WebElement getSearchEdt() {
		return searchEdt;
	}

	public WebElement getSearchBtn() {
		return searchBtn;
	}

	public WebElement getSavebtn() {
		return savebtn;
	}
	
	//business library
	
	/**
	 * This method create new contact with last name
	 * @param lastname
	 */
	public void createNewContact(String lastname)
	{
		lastnameEdt.sendKeys(lastname);
		savebtn.click();
	}
	
	/**
	 * This method creates new contact with orgnization name
	 * @param driver
	 * @param lastname
	 * @param orgname
	 */
	public void createNewContactwithOrg(WebDriver driver, String lastname, String orgname)
	{
		lastnameEdt.sendKeys(lastname);
		OrgLookUpImg.click();
		switchToWindow(driver, "Accounts");
		searchEdt.sendKeys(orgname);
		searchBtn.click();
		driver.findElement(By.xpath("//a[text()='"+orgname+"']")).click();
		switchToWindow(driver, "Contacts");
		savebtn.click();
		
	}
	
	

}
