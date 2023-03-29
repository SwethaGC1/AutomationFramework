package practice;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TestNgPractice {

	@Test(enabled=false)
	//@Test(priority=1)
	//@Test(invocationCount = 0)
	//@Test
	public void createuser()
	{
		System.out.println("create");
		Assert.fail();
	}
	
	//@Test(priority=2,invocationCount = 2)
	//@Test(dependsOnMethods = "createuser")
	@Test
	public void updateuser()
	{
		System.out.println("update");
	}
	
	//@Test(dependsOnMethods = {"createuser","updateuser"} )
	@Test(enabled=true)
	public void deleteuser()
	{
		System.out.println("delete");
	}
	
}
