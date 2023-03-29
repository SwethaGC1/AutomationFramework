package practice;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class demo {

	public static void main(String[] args) {
		WebDriver driver=null;
		WebDriverManager.edgedriver().setup();
		 driver = new EdgeDriver();

	}

}
