package vtiger.Lead;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import vtiger.GenericUtilities.BaseClass;
import vtiger.ObjectRepository.CreateLeadFromQuickSearchPage;
import vtiger.ObjectRepository.LeadInfoPage;
@Listeners(vtiger.GenericUtilities.ListenersImplementation.class)
public class CreateLeadFromQuickSearchTest extends BaseClass {
	
	@Test
	public void CreateLeadTest() throws EncryptedDocumentException, IOException
	{
		//read data from excel file specific to test script
		String COMPANYNAME=eUtil.excelFileUtility("Lead", 1, 3);
		String LASTNAME=eUtil.excelFileUtility("Lead", 1, 2);
		Reporter.log("/read data from excel file specific to test script successfully");
		
		//call createLeadfromquicksearch pom class
		CreateLeadFromQuickSearchPage clfqsp=new CreateLeadFromQuickSearchPage(driver);
		clfqsp.CreateLeadQuickSearch(driver, "New Lead", COMPANYNAME, LASTNAME);
		Reporter.log("call createLeadfromquicksearch pom class successful");
		
		//call leadinfo class for header validation
		LeadInfoPage lip=new LeadInfoPage(driver);
		String LEADHEADER=lip.getLeadHeader();
		Assert.assertTrue(LEADHEADER.contains(LASTNAME));
		System.out.println(LEADHEADER+"---Lead created");
		/*if(LEADHEADER.contains(LASTNAME))
			System.out.println("lead created");
		else
			System.out.println("lead not created");*/
		//Assert.fail();
		}

}
