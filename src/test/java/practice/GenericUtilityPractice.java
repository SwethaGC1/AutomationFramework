package practice;

import java.io.IOException;

import vtiger.GenericUtilities.ExcelFileUtility;
import vtiger.GenericUtilities.PropertyFileUtility;
import vtiger.GenericUtilities.javaUtility;

public class GenericUtilityPractice {

	public static void main(String[] args) throws IOException {
		PropertyFileUtility pUtil=new PropertyFileUtility();
		String URL=pUtil.readDataFromPropertyFile("url");
		System.out.println(URL);
		
		ExcelFileUtility eUtil=new ExcelFileUtility();
		String name=eUtil.excelFileUtility("Contact", 7, 2);
		System.out.println(name);
		int rowvalue=eUtil.getRowCount("contact");
		System.out.println(rowvalue);
		
		eUtil.writeDataIntoExcel("Contact", 13, 5, "batman");
		
		javaUtility jUtil=new javaUtility();
		System.out.println(jUtil.getSystemDate());
		System.out.println(jUtil.getSystemDateInFormat());
		System.out.println(jUtil.getRandomNumber());

	}

}
