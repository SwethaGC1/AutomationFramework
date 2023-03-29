package vtiger.Contacts;

import static org.testng.Assert.fail;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


import vtiger.GenericUtilities.BaseClass;
import vtiger.ObjectRepository.ContactInfoPage;
import vtiger.ObjectRepository.ContactsPage;
import vtiger.ObjectRepository.CreateContactPage;
import vtiger.ObjectRepository.CreateOrganizationPage;
import vtiger.ObjectRepository.HomePage;
import vtiger.ObjectRepository.OrganizationInfoPage;
import vtiger.ObjectRepository.OrganizationsPage;

@Listeners(vtiger.GenericUtilities.ListenersImplementation.class)
public class CreateContactWithOrganizationTest extends BaseClass {
	
	@Test
	public void CreateContactWithOrgTest() throws EncryptedDocumentException, IOException
	{
		
	/*Read data from Excel file for test data */
	String ORGNAME=eUtil.excelFileUtility("Contact", 7, 2)+jUtil.getRandomNumber();
	String LASTNAME=eUtil.excelFileUtility("Contact", 7, 3);
	
	//WebDriver driver=null;
	
	
	//click on organization link to create organization
	HomePage hp=new HomePage(driver);
	hp.clickOrgnizationLink();
	Reporter.log("click on org link successful");
	// click on orgnization look up image
	OrganizationsPage op=new OrganizationsPage(driver);
	op.clickOrgImage();
	Reporter.log("click on org look up image successful");
	
	//create new organization with industry type and save		
	CreateOrganizationPage cop=new CreateOrganizationPage(driver);
	cop.clickSaveOrg(ORGNAME, "Apparel");
	Reporter.log("org is created successfully");
	
	//validate for organization header
	OrganizationInfoPage oip=new OrganizationInfoPage(driver);
	String OrgHead = oip.getOrgHeader();
	Assert.assertTrue(OrgHead.contains(ORGNAME));
	System.out.println(OrgHead+"---Orgnization is created");
	
	/*if(OrgHead.contains(ORGNAME))
		System.out.println("organization is created");
	else
		System.out.println("organization is not created");*/
	
	//click on contact link in home page
	hp.clickContactLink();
	Reporter.log("click on contact link successful");
	//Assert.fail();
	
	//click on contact look up image
	ContactsPage cp=new ContactsPage(driver);
	cp.clickContactImg();
	Reporter.log("click on contact look up image successful");
	
	//create new contact with organization
	CreateContactPage ccp=new CreateContactPage(driver);
	ccp.createNewContactwithOrg(driver, LASTNAME, ORGNAME);
	Reporter.log("new contact is created",true);//like print statement
	
	//validate for contact header
	ContactInfoPage cip=new ContactInfoPage(driver);
	String ContactHeader=cip.getContactHeader();
	Assert.assertTrue(ContactHeader.contains(LASTNAME));
	System.out.println(ContactHeader+"-----contact created");
	
/*	if(ContactHeader.contains(LASTNAME))
		System.out.println("contact created");
	else
		System.out.println("contact not created");*/
	
	
		}
}
