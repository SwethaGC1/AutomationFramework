package practice;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertFilePractice {

	public static void main(String[] args) throws IOException {
		//step1: Open the file in java readable format
		FileInputStream fis=new FileInputStream(".\\src\\test\\resources\\CommonData.properties");

		//step2: create object of properties from java.util
		Properties pObj=new Properties();
		
		//step3: load the file input stream into properties
		pObj.load(fis);
		
	// step4 	access the value with keys
		 String URL=pObj.getProperty("url");
		 String USERNAME=pObj.getProperty("username");
		 String PASSWORD=pObj.getProperty("password");
		 
		 System.out.println(URL+" " +USERNAME+" "+PASSWORD);
	}

}
