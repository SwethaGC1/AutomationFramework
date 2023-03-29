package vtiger.Organization;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import vtiger.GenericUtilities.BaseClass;
import vtiger.ObjectRepository.CreateOrganizationPage;
import vtiger.ObjectRepository.HomePage;
import vtiger.ObjectRepository.OrganizationInfoPage;
import vtiger.ObjectRepository.OrganizationsPage;

public class CreateMultipleOrganizationWithIndustry extends BaseClass {

	@Test(dataProvider = "getData")
	public void CreateMultipleOrganization(String Org, String INDUSTRY )
	{
		String ORGNAME=Org+jUtil.getRandomNumber();
		HomePage hp=new HomePage(driver);
		hp.clickOrgnizationLink();
		
		OrganizationsPage op=new OrganizationsPage(driver);
		op.clickOrgImage();
		
		CreateOrganizationPage cop=new CreateOrganizationPage(driver);
		cop.clickSaveOrg(ORGNAME, INDUSTRY);
		
		OrganizationInfoPage oip=new OrganizationInfoPage(driver);
		String OrgHead=oip.getOrgHeader();
		Assert.assertTrue(OrgHead.contains(ORGNAME));
	}
	
	@DataProvider
	public Object[][] getData() throws EncryptedDocumentException, IOException
	{
	Object[][] data = eUtil.readMultipleData("Multiple");
	return data;
	}
}
