package vtiger.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationsPage {//rule1: create pom class for every page
			
			//rule2: findelements using @findby, @findall, @findbys
			
			@FindBy(xpath = "//img[@title='Create Organization...']")
			private WebElement clickorglookUpbtn;
			
			//rule3: create constructor to intialize web elements
				public OrganizationsPage(WebDriver driver)
				{
					PageFactory.initElements(driver,this);
				}

				//rule4: provide getters to access these elements
				public WebElement getCreateorglookUpbtn() {
					return clickorglookUpbtn;
				}
				
				//business library
				public void clickOrgImage()
				{
					clickorglookUpbtn.click();
				}

		

}
