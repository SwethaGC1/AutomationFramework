package vtiger.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import vtiger.GenericUtilities.WebDriverUtility;

public class ContactInfoPage extends WebDriverUtility{//rule1: create pom class for every page
	//rule2: find elements using @findBy, @findAll, @findBys
	@FindBy(xpath = "//span[@class='dvHeaderText']")
	private WebElement contactHeaderTxt;
	
	//rule3:create constructor to intialize web elements
	public ContactInfoPage(WebDriver driver)
	{
		PageFactory.initElements(driver,this);
	}

	//rule4: provide getters to access these elements
	public WebElement getContactHeaderTxt() {
		return contactHeaderTxt;
	}
	
	//business library
	
	/**
	 * This method returns contact  header text
	 * @return
	 */
	public String getContactHeader()
	{
		return contactHeaderTxt.getText();
	}
	
	
	

}
