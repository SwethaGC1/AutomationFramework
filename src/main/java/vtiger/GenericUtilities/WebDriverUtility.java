package vtiger.GenericUtilities;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverUtility {

	/**
	 * This method used to maximize the window
	 * @param driver
	 */
	public void maximizeWindow(WebDriver driver)
	{
		driver.manage().window().maximize();
	}
	
	/**
	 * This method is used to minimize the window
	 * @param driver
	 */
	public void minimizeWindow(WebDriver driver)
	{
		driver.manage().window().minimize();
	}
	
	/**
	 * this method waits for the page to load
	 * @param driver
	 */
	public void waitForPage(WebDriver driver)
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	}
	
	
	/**
	 * this method will wait until element is visible
	 * @param driver
	 * @param element
	 */
	public void waitForElementTobeVisible(WebDriver driver, WebElement element)
	{
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	/**
	 * This method will wait until element is clickable
	 * @param driver
	 * @param element
	 */
	public void waitForElementTobeClickable(WebDriver driver,WebElement element)
	{
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	/**
	 * This method will handle dropdown based on visible text
	 * @param element
	 * @param key
	 */
	public void HandleDropDown(WebElement element,String key)
	{
		Select s=new Select(element);
		s.selectByVisibleText(key);
		
	}
	
	/**
	 * this method will handle dropdown based on index value
	 * @param element
	 * @param value
	 */
	public void HandleDropDown(WebElement element, int value)
	{
		Select s=new Select(element);
		s.selectByIndex(value);
	}
	
	/**
	 * this method will handle drop down based on value
	 * @param value
	 * @param element
	 */
	public void HandleDropDown(String value,WebElement element)
	{
		Select s=new Select(element);
		s.selectByValue(value);
	}
	
	/**
	 * tdhis method will do mouse over action using move to element
	 * @param driver
	 * @param element
	 */
	public void mouseOverAction(WebDriver driver,WebElement element)
	{
		Actions a=new Actions(driver);
		a.moveToElement(element).perform();
	}
	
	/**
	 * This method will perform right click anywhere in a webpage
	 * @param driver
	 */
	public void rightClickAction(WebDriver driver)
	{
		Actions a=new Actions(driver);
		a.contextClick().perform();
	}
	
	/**
	 * This method will perform right click action on web element
	 * @param driver
	 * @param element
	 */
	public void rightClickAction(WebDriver driver,WebElement element)
	{
		Actions a=new Actions(driver);
		a.contextClick(element).perform();
	}
	
	/**
	 * This method will perform double click anywhere on web page
	 * @param driver
	 */
	public void doubleClickAction(WebDriver driver)
	{
		Actions a=new Actions(driver);
		a.doubleClick().perform();
	}
	
	/**
	 * This method will perform double click on web element
	 * @param driver
	 * @param element
	 */
	public void doubleClickAction(WebDriver driver, WebElement element)
	{
		Actions a=new Actions(driver);
		a.doubleClick(element);
	}
	
	/**
	 * This method will perofm drag and drop actions on element
	 * @param driver
	 * @param srcelement
	 * @param trgelement
	 */
	public void dragAndDropAction(WebDriver driver, WebElement srcelement, WebElement trgelement)
	{
		Actions a=new Actions(driver);
		a.dragAndDrop(srcelement, trgelement);
	}
	
	/**
	 * this method will accept the alert
	 * @param driver
	 */
	public void acceptAlert(WebDriver driver)
	{
		driver.switchTo().alert().accept();
	}
	
	/**
	 * This method will dismiss the alert
	 * @param driver
	 */
	public void dismissAlert(WebDriver driver)
	{
		driver.switchTo().alert().dismiss();
	}
	
	/**
	 * this method will capture the text from  alert popup
	 * @param driver
	 * @return
	 */
	public String getAlertText(WebDriver driver)
	{
		return driver.switchTo().alert().getText();
	}
	
	/**
	 * This method will handle frames using index value
	 * @param driver
	 * @param value
	 */
	public void handleFrames(WebDriver driver,int value)
	{
		driver.switchTo().frame(value);
	}
	
	/**
	 * this method will handle frames based on name or id
	 * @param driver
	 * @param nameorid
	 */
	public void handleFrames(WebDriver driver,String nameorid)
	{
		driver.switchTo().frame(nameorid);
	}
	
	/** 
	 * this method will handle frames based on web element
	 * @param driver
	 * @param element
	 */
	public void handleFrames(WebDriver driver,WebElement element)
	{
		driver.switchTo().frame(element);
	}
	
	/**
	 * this method will switch to immediate parent frame
	 * @param driver
	 */
	public void switchToParentFrame(WebDriver driver)
	{
		driver.switchTo().parentFrame();
	}
	
	/**
	 * This method will switch to default frame
	 * @param driver
	 */
	public void switchToDefaultFrame(WebDriver driver)
	{
		driver.switchTo().defaultContent();
	}
	
	/**
	 * This method will do scroll action downwards vertically
	 * @param driver
	 */
	public void handleScrollBar(WebDriver driver) {
		
		JavascriptExecutor js=(JavascriptExecutor) driver;
		js.executeScript("Window.scrollby(0,500", "");
	}
	
	/**
	 * This method will scroll the page until the element vertically in DOM(document object module)
	 * @param element
	 * @param driver
	 */
	public void handleScrollBar(WebElement element, WebDriver driver)
	{		
		JavascriptExecutor js=(JavascriptExecutor) driver;
		int y=element.getLocation().getY();
		js.executeScript("window.scrollby(0,"+y+")",element);
	}
	
	/**
	 * this method will switch the  window based on partial window title
	 * @param driver
	 * @param partialwintitle
	 */
	public void switchToWindow(WebDriver driver,String partialwintitle)
	{
		//step1: capture all the window id's
		Set<String> winIds = driver.getWindowHandles();
		//step2: navigate to each window
		for(String win:winIds)
		{
			//step3: switch to window and capture title
			String wintitle=driver.switchTo().window(win).getTitle();
			
			//step4: compare the title with required partial title
			if(wintitle.contains(partialwintitle))
			{
				break;
			}
		}
		
	}
	
	/**
	 * This method will take screenshot and saves in foder
	 * @param driver
	 * @param screenshotname
	 * @return
	 * @throws IOException
	 */
	public String takeScreenShot(WebDriver driver, String screenshotname) throws IOException
	{
		TakesScreenshot ts=(TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File dst=new File(".\\screenshots\\"+screenshotname+".png"); //".\\screenshots\\" identifies as folder only one foewrd slash dont identify as folder
		FileUtils.copyFile(src, dst);
		//this will return only folder path but for report we need complete path
		return dst.getAbsolutePath();// for extent report
	}
}
