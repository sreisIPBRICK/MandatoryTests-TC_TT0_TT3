package Data;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class UploadFile {
	WebDriver driver;
	
	@FindBy(name = "file_patch")
	WebElement file_patch;
	@FindBy(name="f_subbugfixes")
	WebElement f_subbugfixes;
	@FindBy(name = "usercsvfile")
	WebElement usercsvfile;
	@FindBy(name = "computercsvfile")
	WebElement computercsvfile;
	@FindBy(name="accao")
	WebElement accao;
	
	public void FileUpload(WebDriver driver,String path) throws Exception {
		this.driver = driver;
		PageFactory.initElements(driver, this);       
        file_patch.sendKeys(path);
        f_subbugfixes.click();
        }
	public void CVSUpload(WebDriver driver,String path) throws Exception {
		this.driver = driver;
		PageFactory.initElements(driver, this);       
		usercsvfile.sendKeys(path);
		accao.click();
        }
	public void CVSUploadd(WebDriver driver,String path) throws Exception {
		this.driver = driver;
		PageFactory.initElements(driver, this);       
		computercsvfile.sendKeys(path);
		accao.click();
        }
	public void clicksendfile(){
		usercsvfile.click();
	}
	public void FileUploadRobot(WebDriver driver, String path) throws InterruptedException, AWTException{
		
		this.driver=driver;
		PageFactory.initElements(driver, this);
		StringSelection sel = new StringSelection(path);	 
		 // Copy to clipboard
		 Toolkit.getDefaultToolkit().getSystemClipboard().setContents(sel,null);
		 Thread.sleep(2000);
		 // Create object of Robot class
		 Robot robot = new Robot();
		 Thread.sleep(1000);	      
		 // Press Enter
		 robot.keyPress(KeyEvent.VK_ENTER); 
		 // Release Enter
		 robot.keyRelease(KeyEvent.VK_ENTER);
		  // Press CTRL+V
		 robot.keyPress(KeyEvent.VK_CONTROL);
		 robot.keyPress(KeyEvent.VK_V); 
		 // Release CTRL+V
		 robot.keyRelease(KeyEvent.VK_CONTROL);
		 robot.keyRelease(KeyEvent.VK_V);
		 Thread.sleep(1000);	        
		 // Press Enter 
		 robot.keyPress(KeyEvent.VK_ENTER);
		 robot.keyRelease(KeyEvent.VK_ENTER);
	}

}


