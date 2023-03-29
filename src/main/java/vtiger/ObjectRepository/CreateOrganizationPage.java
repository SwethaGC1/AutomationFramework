package vtiger.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import vtiger.GenericUtilities.WebDriverUtility;

public class CreateOrganizationPage extends WebDriverUtility{//rule1: create pom class for every page
	
	//rule2: findelements using @findby, @findall, @findbys
	
	@FindBy(name = "accountname")
	private WebElement orgnametxt;
	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement saveOrgBtn;
	@FindBy(name = "industry")
	private WebElement IndustryDropDown;
	
	//rule3: create constructor to intialize web elements
	public CreateOrganizationPage(WebDriver driver)
	{
		PageFactory.initElements(driver,this);
	}

	
	//rule4: provide getters to access these elements
	public WebElement getOrgnametxt() {
		return orgnametxt;
	}

	public WebElement getSaveOrgBtn() {
		return saveOrgBtn;
	}
	
	public WebElement getIndustryDropDown() {
		return IndustryDropDown;
	}
	
	//business library
	
	/**
	 * This method will create organization with org name and industry type
	 * @param orgname
	 * @param industrydropdown
	 */
	public void clickSaveOrg(String orgname, String industrydropdown) {
		
			orgnametxt.sendKeys(orgname);
			HandleDropDown(IndustryDropDown, industrydropdown);
			saveOrgBtn.click();
	}
}
		
		



	
	
	


