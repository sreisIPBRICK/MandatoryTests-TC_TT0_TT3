package Pages;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.xml.sax.SAXException;

import Data.xmlData;

public class GroupwarePage {
	WebDriver driver;
	LoginPage objLogin;
	MainPage objMainPage;
	CafePage objCafePage;
	xmlData objxml= new xmlData();
	String ConfigXmlFile="FilesXML/AddUsers.xml";
	
	@FindBy(id="composelink")
	WebElement composelink;
	
	@FindBy(xpath="html/body/p")
	WebElement SuccessMessage;
	
	@FindBy(xpath=".//*[@id='composelink']")
	WebElement NewMessage;

	@FindBy(xpath=".//*[@id='sendto']/td[2]/div/ul/li/input")
	WebElement to;
	
	@FindBy(xpath=".//*[@id='subject']")
	WebElement subject;
	
	@FindBy(xpath="html/body")
	WebElement body;
	
	@FindBy(xpath=".//*[@id='send_button']")
	WebElement send;
	
	@FindBy(xpath=".//*[@id='VProw_1']")
	WebElement mailtest;
	
	@FindBy(xpath=".//*[@id='horde-logout']/a")
	WebElement logout;
	
	@FindBy(xpath=".//*[@id='checkmaillink']")
	WebElement refresh;
	
	@FindBy(id="horiz_opts")
	WebElement horiz_opts;
	
	@FindBy(id="button_delete")
	WebElement button_delete;

	@FindBy(xpath="html/body/div[1]/ul/li/div")
	WebElement warning;
			
	public GroupwarePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	public void deleteMail(){
		horiz_opts.click();
		button_delete.click();
	}
	public void sendMail(String mail) throws InterruptedException{		
		objMainPage= new MainPage(driver);		
		// Store the current window handle
		String winHandleBefore = driver.getWindowHandle();
		// Perform the click operation that opens new window
		NewMessage.click();
		// Switch to new window opened
		for(String winHandle : driver.getWindowHandles()){
		    driver.switchTo().window(winHandle);
		}
		// Perform the actions on new window
		to.sendKeys(mail);
		subject.sendKeys("teste");		
		Thread.sleep(1000);		
		objMainPage.switchToFrame("composeMessage");		
		body.click();
		body.sendKeys("SuccessfullyTest");	
		objMainPage.switchToFrame("Default");
		send.click();
		// Close the new window, if that window no more required
		driver.close();
		// Switch back to original browser (first window)
		driver.switchTo().window(winHandleBefore);
		logout.click();
	}
	
	public void RefreshClickDeleteMail() throws InterruptedException{
		Thread.sleep(2000);
		refresh.click();
		mailtest.click();
		Thread.sleep(3000);
		Assert.assertTrue(this.getSuccessMailMessage().contains("SuccessfullyTest"));
		horiz_opts.click();
		button_delete.click();
		Thread.sleep(3000);	
		logout.click();
	}
	
	public void ReadXmlAndSendMail() throws NumberFormatException, ParserConfigurationException, SAXException, IOException, InterruptedException{
		objLogin= new LoginPage(driver);
		//System.out.print("PUTA QUE PARIU ESTA MERDA");
		String ConfigXmlFile="FilesXML/AddUsers.xml";
		int num = Integer.parseInt(objxml.getxml(ConfigXmlFile,"nUsers"));
		for(int i=0;i<num;i++){
			if(objxml.getxml(ConfigXmlFile,"log"+i).equals(objxml.getxml(ConfigXmlFile,"log2"))){
				
				objLogin.loginGroupware(objxml.getxml(ConfigXmlFile,"logM"),objxml.getxml(ConfigXmlFile,"pwM"));
				String merdas=Integer.toString(i);
				String mail=objxml.getxml(ConfigXmlFile,"log"+i);
				mail.concat(merdas).concat(objxml.getxml(ConfigXmlFile,"domain"));
				//System.out.print("ReadXmlSendMailFunction:"+mail);
				this.sendMail(mail);
				i++;
			}
			/*if(objxml.getxml(ConfigXmlFile,"log"+i).equals(objxml.getxml(ConfigXmlFile,"log0"))){
				objLogin.loginGroupware(objxml.getxml(ConfigXmlFile,"log0"),objxml.getxml(ConfigXmlFile,"pw0"));
				Assert.assertTrue(warning.getText().contains("Login failed because your username or password was entered incorrectly."));
			}*/
			else{
			objLogin.loginGroupware(objxml.getxml(ConfigXmlFile,"log"+i),objxml.getxml(ConfigXmlFile,"pw"+i));
			String merdas=Integer.toString(i);
			String mail=objxml.getxml(ConfigXmlFile,"log"+i);
			mail.concat(merdas).concat(objxml.getxml(ConfigXmlFile,"domain"));
			//System.out.print("ReadXmlSendMailFunction2:"+mail);
			this.sendMail(mail);
			}
		}
	}

	public String getSuccessMailMessage(){		
		WebElement iframeMsg = driver.findElement(By.xpath("//*[contains(@class, 'htmlMsgData')]"));        
		driver.switchTo().frame(iframeMsg);
		WebElement body = driver.findElement(By.cssSelector("body"));
		String str = body.getText();
		System.out.println(str);
		driver.switchTo().defaultContent();
		return str;	
	}
	
	public void ReadcvsFileAndSendMail() throws InterruptedException, ParserConfigurationException, SAXException, IOException{
		objLogin= new LoginPage(driver);
		String ConfigXmlFile="FilesXML/AddUsers.xml";
		
		String csvFile = objxml.getxml(ConfigXmlFile,"userslist");/*"/home/sreis/workspace/userslist.csv";*/
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
                // use comma as separator
                String[] name = line.split(cvsSplitBy);             
                //System.out.println("ReadcvsFileAndSendMail():"+ name[2] + "," + name[4] + "," + name[9]);       
                objLogin.loginGroupware(name[2],name[9]);
                //System.out.println("ReadcvsFileAndSendMail2()"+ driver.getTitle());
                Assert.assertTrue(driver.getTitle().contains("Mail ::"));
                this.sendMail(name[4]);
                //delete mail
                
               }           
        	System.out.println(i);
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
	
	
}
