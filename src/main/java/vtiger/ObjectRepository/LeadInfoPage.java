package vtiger.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LeadInfoPage {//rule1: create pom call for every page
	//rule2: Find elements using @FindBy, @findAll, @findBys
	@FindBy(xpath = "//span[@class='dvHeaderText']")
	private WebElement leadHeaderTxt;
	
	//rule3: create constructor to intialize these elements
	public LeadInfoPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	//rule4: provide getters to access these elements
	public WebElement getLeadHeaderTxt() {
		return leadHeaderTxt;
	}
	
	//business library
	
	/**
	 * this method returns lead header text value to validate
	 * @return
	 */
	public String getLeadHeader()
	{
		return leadHeaderTxt.getText();
	}
	

}
