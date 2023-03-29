package practice;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataProviderPractice {

	@Test(dataProvider = "phone")
	public void dataproviderPractice(String phone, int amount)
	{
		System.out.println(phone+"------"+amount);
	}
	
	@DataProvider(name="phone")
	public Object[][] getData1()
	{
	Object[][] data=new Object[3][2];//3sets of data with 2 info
	data[0][0]="Samsung";
	data[0][1]=1200;
	data[1][0]="iphone";
	data[1][1]=2000;
	data[2][0]="nokia";
	data[2][1]=200;
	return data;
	}
	
	@Test(dataProvider = "laptop")
	public void dataprovider2(String laptop)
	{
		System.out.println(laptop);
	}
	
	@DataProvider(name="laptop")
	public Object[][] getData()
	{
		Object[][] datalap=new Object[2][1];
	
		datalap[0][0]="Dell";
		datalap[1][0]="mac";
		return datalap;
	}
}
