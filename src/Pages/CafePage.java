package Pages;

import java.awt.AWTException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;

import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.xml.sax.SAXException;
import Data.xmlData;
import Data.UploadFile;

public class CafePage {
	WebDriver driver;
	LoginPage objLogin;
	GroupwarePage objGroupware;
	MainPage objMainPage;
	UploadFile objUploadFile;
	xmlData objxml= new xmlData();
	String ConfigXmlFile="FilesXML/AddUsers.xml";
	String baseUrl,baseUrl2,ip,baseUrlcafe;
	
	@FindBy(id="LoginForm_username_2")
	WebElement LoginFormusername;
	
	@FindBy(id="LoginForm_password_2")
	WebElement LoginFormpw;
	
	@FindBy(css="#app_2")
	WebElement groupware;
	
	@FindBy(css="#id_my_files")
	WebElement MyFiles;
	
	@FindBy(linkText="Vương Kim Bội")
	WebElement user;

	@FindBy(xpath=".//*[@id='content']/div/div/div[3]/form/button")
	WebElement Login;
	
	@FindBy(xpath="/html/body/div[2]/section/div/div/div/div[1]/form/div[3]/button")
	WebElement Login1;
	
	@FindBy(xpath=".//*[@id='logout_icon']/a")
	WebElement logout;
	
	@FindBy(xpath=".//*[@id='VProw_1']/div[3]")
	WebElement mailtest;
	
	@FindBy(xpath="html/body/p")
	WebElement SuccessMessage;
	
	@FindBy(linkText="Refresh")
	WebElement refresh;
	
	@FindBy(id="horiz_opts")
	WebElement horiz_opts;
	
	@FindBy(id="button_delete")
	WebElement button_delete;
	
	@FindBy(xpath=".//*[@id='composelink']")
	WebElement NewMessage;
	
	@FindBy(id="upload-files")
	WebElement upload;
	
	@FindBy(id="submit-files")
	WebElement submit;
	
	@FindBy(id="SetupEnvironmentAutoTests.odt")
	WebElement file;
	
	@FindBy(id="LoginForm_password_em_")
	WebElement failLogin;
	//
	
	public CafePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void loginCafe(String strUserName,String strPasword){
		LoginFormusername.clear();
		LoginFormusername.sendKeys(strUserName);
		LoginFormpw.clear();
		LoginFormpw.sendKeys(strPasword);
		Login.click();
	}
	
	public void gotogroupware(){		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", groupware);
		//System.out.print(groupware.isDisplayed());
	}
	
	public void gotoMyFiles(){		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", MyFiles);
		//System.out.print(MyFiles.isDisplayed());
	}
	public void uploadfile() throws InterruptedException, AWTException{
		objUploadFile= new UploadFile();
		upload.click();
		objUploadFile.FileUploadRobot(driver,"/home/sreis/sreis.IPBRICK.COM/workspace/SetupEnvironmentAutoTests.odt");
		submit.click();
		Assert.assertTrue(file.getText().contains("AutoTests.odt"));
		//logout.click();
	}

	public void SendMailbyCafe(String mail) throws InterruptedException{
		objGroupware= new GroupwarePage(driver);
		/////////////////////////////////////////////////////////////////////////////////////////////////
		// Store the current window handle
		String winHandleBefore = driver.getWindowHandle();
		// Perform the click operation that opens new window
		this.gotogroupware();
		// Switch to new window opened
		for(String winHandle : driver.getWindowHandles()){
		driver.switchTo().window(winHandle);
		}
		// Perform the actions on new window
		objGroupware.sendMail(mail);
		// Close the new window, if that window no more required
		driver.close();
		// Switch back to original browser (first window)
		driver.switchTo().window(winHandleBefore);
		// Continue with original browser (first window)				
		/////////////////////////////////////////////////////////////////////////////////////////////////
	}
	
	public void CheckMailbyCafe() throws InterruptedException{
		objLogin= new LoginPage(driver);
		objMainPage= new MainPage(driver);
		objGroupware= new GroupwarePage(driver);		
		/////////////////////////////////////////////////////////////////////////////////////////////////
		// Store the current window handle
		String winHandleBefore = driver.getWindowHandle();
		// Perform the click operation that opens new window
		this.gotogroupware();
		// Switch to new window opened
		for(String winHandle : driver.getWindowHandles()){
		driver.switchTo().window(winHandle);
		}
		// Perform the actions on new window
		objGroupware.RefreshClickDeleteMail();
		// Close the new window, if that window no more required
		driver.close();
		// Switch back to original browser (first window)
		driver.switchTo().window(winHandleBefore);
		// Continue with original browser (first window)
		Thread.sleep(1000);
		logout.click();
	
		/////////////////////////////////////////////////////////////////////////////////////////////////		
	}
	
	
	public String getSuccessMailMessage(){
		String str = SuccessMessage.getText();
		System.out.println(str);
		return str;
	}
	
	public String getFailLoginMessage(){
		String str = failLogin.getText();
		System.out.println(str);
		return str;
	}
	
	public String AllUsersMailList() throws NumberFormatException, ParserConfigurationException, SAXException, IOException{
		int i;
		int num=Integer.parseInt(objxml.getxml(ConfigXmlFile,"nUsers"));
		String list=new String();
		for(i=0;i<num;i++){
			//list=objxml.getxml(ConfigXmlFile,"log"+i);
			list=list.concat(objxml.getxml(ConfigXmlFile,"log"+i)).concat("@").concat(objxml.getxml(ConfigXmlFile,"domain")).concat(" ");	
		}
		System.out.println(list);
		return list;
	}
	
	public String GetMassList() throws ParserConfigurationException, SAXException, IOException, NumberFormatException, InterruptedException, AWTException{
		
		String csvFile = objxml.getxml(ConfigXmlFile,"userslist");/*"/home/sreis/workspace/userslist.csv";*/
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ";";
        String list=new String();
        int i = 0;
        try {
            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {
            	if(i == 0 || i==1) {
                    i++;  
                    continue;
            	} 
                String[] name = line.split(cvsSplitBy);             
                list=list.concat(" ").concat(name[4]);            
               }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
		return list;	
	}
	
public void ReadcvsAndVerifyMassUsers(WebDriver driver) throws ParserConfigurationException, SAXException, IOException, NumberFormatException, InterruptedException, AWTException{
		
	    this.driver=driver;
	    PageFactory.initElements(driver, this);
	    
		String csvFile = objxml.getxml(ConfigXmlFile,"userslist");
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ";";
        int i = 0;
        try {
            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {
            	if(i == 0 || i==1) {
                    i++;  
                    continue;
            	} 
                String[] name = line.split(cvsSplitBy);             
                objGroupware= new GroupwarePage(driver);			
        		driver.get(baseUrlcafe);
        		driver.manage().window().maximize();
        		this.loginCafe("Administrator", "123");
        		this.SendMailbyCafe(this.GetMassList());
        		this.logout();
        		this.loginCafe(name[4],name[9]);
        		this.gotoMyFiles();
        		this.uploadfile();		
        		this.CheckMailbyCafe();
               }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }	
	}
	
	public void logout() throws InterruptedException{
		Thread.sleep(1000);
		logout.click();
	}
	
}
