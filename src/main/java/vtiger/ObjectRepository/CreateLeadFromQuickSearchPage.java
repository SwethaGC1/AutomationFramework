package vtiger.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import vtiger.GenericUtilities.WebDriverUtility;

public class CreateLeadFromQuickSearchPage extends WebDriverUtility {//rule1: create pom class for every page
	//rule2: find web element using @findby, @findAll, @FindBys
	
	/*web element to click on lead dropdown in quick search */
	@FindBy(xpath = "//td[@style='padding-left:10px']/select")
	private WebElement leadDropDown;
	
	/*web element to find company name in lead child window */
	@FindBy(name = "company")
	private WebElement companynameTxt;
	
	/*Web element to find last name in lead child window */
	@FindBy(name = "lastname")
	private WebElement lastnameTxt;
	
	/*web element to click save button in lead child window */
	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement saveBtn;

	//rule3: create consturctor to intialize the elements
	public CreateLeadFromQuickSearchPage(WebDriver driver)
	{
		PageFactory.initElements(driver,this);
	}
	
	//Rule4: provide getters to access these elements
	public WebElement getLeadDropDown() {
		return leadDropDown;
	}
	public WebElement getCompanynameTxt() {
			return companynameTxt;
		}

	public WebElement getLastnameTxt() {
			return lastnameTxt;
		}

	public WebElement getSaveBtn() {
			return saveBtn;
		}
		
	//business library
	
	
	/**
	 * this method creates new lead from create lead child window from quick search drop down 
	 * @param driver
	 * @param dropdownlead
	 * @param companyname
	 * @param lastname
	 */
	public void CreateLeadQuickSearch(WebDriver driver, String dropdownlead,String companyname,String lastname)
	{
		Select s=new Select(leadDropDown);
		s.selectByVisibleText(dropdownlead);
		switchToWindow(driver, "Create Lead");
		companynameTxt.sendKeys(companyname);
		lastnameTxt.sendKeys(lastname);
		saveBtn.click();
		switchToWindow(driver, "Leads");
	}
	
	
	
}
