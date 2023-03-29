package vtiger.ObjectRepository;
/**
 * 
 * @author Swetha G C
 *
 */

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {//rule1: create pom calss for every page
	
	//rule2:identify the web element with @findby,@findall and @findbys
	
	@FindBy(name="user_name") private WebElement usernameEdt;
	@FindAll({@FindBy(name="user_password"),@FindBy(xpath="//input[@type='password']")})
	private WebElement passwordEdt;
	@FindBy(id="submitButton")
	private WebElement submitBtn;
	
	//rule 3: create constructor to intialize web Element
	public LoginPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	//rule 4:provide getters to access these elements	
	public WebElement getUsernameEdt() {
		return usernameEdt;
	}

	public WebElement getPasswordEdt() {
		return passwordEdt;
	}

	public WebElement getSubmitBtn() {
		return submitBtn;
	}
	
	 //Business Libraries:Generic method specific to current project
	
	public void loginToApp(String username,String password)
	{
		usernameEdt.sendKeys(username);
		passwordEdt.sendKeys(password);
		submitBtn.click();
		
	}
	

}
