package vtiger.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationInfoPage {//rule1: create pom class for every page
	
	//rule2: find web elements using @findby, @findall, @findbys
	
	@FindBy(xpath = "//span[@class='dvHeaderText']")
	private WebElement headerOrgNameTxt;
	
	//rule3: create constructor to intialize these elements
	public OrganizationInfoPage(WebDriver driver)
	{
		PageFactory.initElements(driver,this);
	}

	//rule4: provide getters to access elements
	
	public WebElement getHeaderOrgNameTxt() {
		return headerOrgNameTxt;
	}
	
	//business library
	/**
	 * this method will capture the text from organization header
	 */
	public String getOrgHeader()
	{
		return headerOrgNameTxt.getText();	
	}
	
	

}
