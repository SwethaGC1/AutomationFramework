package vtiger.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactsPage { //rule1: create pom class for every page
	
	//rule2: find webElements using @findby, @findall, @findbys
	
	@FindBy(xpath="//img[@title='Create Contact...']")
	private WebElement clickcontactBtn;
	
	//rule3: create constructor to intialize web element
		public ContactsPage(WebDriver driver)
		{
			PageFactory.initElements(driver,this);
		}

		//rule4: generate getters to access these elements
		public WebElement getCreatecontactBtn() {
			return clickcontactBtn;
		}
		
		//business library
		public void clickContactImg() {
			clickcontactBtn.click();
		}


}
