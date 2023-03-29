package vtiger.GenericUtilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/** 
 * This class consists of generic methods to read data from property file.
 * @author Swetha G C
 */
public class PropertyFileUtility {

	/**
	 * This method will read data from property file
	 * @param key
	 * @return
	 * @throws IOException 
	 */
	
		public String readDataFromPropertyFile(String key) throws IOException {
			FileInputStream fis=new FileInputStream(IConstantsUtility.propertyFilePath);
			Properties pObj=new Properties();
			pObj.load(fis);
			String value=pObj.getProperty(key);
			return value;
	}
		
	
	
}
