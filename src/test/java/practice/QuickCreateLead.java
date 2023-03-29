package practice;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class QuickCreateLead {

	public static void main(String[] args) {
		//step1: launch the browser
				WebDriver driver=new ChromeDriver();
				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
				driver.get("http://localhost:8888");
				
				//step2: login to the application
				driver.findElement(By.name("user_name")).sendKeys("admin");
				driver.findElement(By.name("user_password")).sendKeys("admin");
				driver.findElement(By.id("submitButton")).click();
				
				//navigate to quick create dropdown
				WebElement dropdown = driver.findElement(By.id("qccombo"));
				dropdown.click();
				Select s=new Select(dropdown);
				s.selectByVisibleText("New Lead");
				// handle child window
				//step1: capture all the window id's
					Set<String> winIds = driver.getWindowHandles();
					//step2: navigate to each window
					for(String win:winIds)
					{
						//step3: switch to window and capture title
						String wintitle=driver.switchTo().window(win).getTitle();
						
						//step4: compare the title with required partial title
						if(wintitle.contains("Create Lead"))
						{
							break;
						}
					}
					driver.findElement(By.name("company")).sendKeys("wipro");
					driver.findElement(By.name("lastname")).sendKeys("swetha");
					driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
					//logout
					WebElement image=driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
					Actions a=new Actions(driver);
					a.moveToElement(image).perform();
					driver.findElement(By.linkText("Sign Out")).click();
					System.out.println("logged out successfully");
					//driver.quit();
					

					
				}
	}

