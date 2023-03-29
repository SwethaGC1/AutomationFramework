package vtiger.Organization;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import vtiger.GenericUtilities.BaseClass;
import vtiger.ObjectRepository.CreateOrganizationPage;
import vtiger.ObjectRepository.HomePage;
import vtiger.ObjectRepository.OrganizationInfoPage;
import vtiger.ObjectRepository.OrganizationsPage;

@Listeners(vtiger.GenericUtilities.ListenersImplementation.class)
public class CreateNewOrganizationTest extends BaseClass {

	@Test(groups = "smokeSuite")
	public void CreateOrgTest() throws EncryptedDocumentException, IOException
	{
	// read data from excel file-- test script specific so not in base class
		String ORGNAME=eUtil.excelFileUtility("Organization", 1, 2)	+jUtil.getRandomNumber();
		
		//click on org link call home page method
		HomePage hp=new HomePage(driver);
		hp.clickOrgnizationLink();
		
		//click on org look up image call org page method
		OrganizationsPage op=new OrganizationsPage(driver);
		op.clickOrgImage();
		
		//enter org,industry dropdown details and save call createorg method 
		CreateOrganizationPage cop=new CreateOrganizationPage(driver);
		cop.clickSaveOrg(ORGNAME, "Apparel");
		
		//vlaidate for org header call orgheaderingo method
		OrganizationInfoPage oip=new OrganizationInfoPage(driver);
		String OrgHeader=oip.getOrgHeader();
		Assert.assertTrue(OrgHeader.contains(ORGNAME));
		System.out.println(OrgHeader+"----Organization is created");
		
		/*if(OrgHeader.contains(ORGNAME))
		{
			System.out.println("new organization is created");
		}
		else
			System.out.println("org not created");*/
		
		
	}
	/*@Test(groups = "regressionSuite")
	public void demo()
	{
	System.out.println("demo");
	//Assert.fail();
	
	}
	
	@Test(groups="RegionalRegressionSuite")
	public void regional()
	{
		System.out.println("regional regression");
	}*/
}
