package Tests;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import javax.xml.parsers.ParserConfigurationException;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;
import Data.SSHUploader;
import Data.UploadFile;
import Data.xmlData;
import Pages.AdvancedConfigsPage;
import Pages.ApplyConfigurations;
import Pages.CafePage;
import Pages.DirectoryPage;
import Pages.GroupwarePage;
import Pages.LoginPage;
import Pages.MainPage;

public class AddUserTest {
	WebDriver driver;
	LoginPage objLogin;
	DirectoryPage objDirectory;
	MainPage objMainPage;
	AdvancedConfigsPage objAdvDefPage;
	ApplyConfigurations objApplyConfigs;
	UploadFile objUploadFile;
	GroupwarePage objGroupware;
	CafePage objCafe;
	SSHUploader objSSH;
	xmlData objxml= new xmlData();
	String baseUrl,baseUrl2,ip,baseUrlcafe;
	String ConfigXmlFile="FilesXML/AddUsers.xml";
	
		
	@BeforeTest (alwaysRun=true)
	public void setup() throws ParserConfigurationException, SAXException, IOException{
		System.out.println("@BeforeTest: Setup");
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		String timeString=objxml.getxml(ConfigXmlFile,"implicitlyWait");
		long time=Integer.parseInt(timeString);
		driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
		baseUrl=objxml.getxml(ConfigXmlFile,"url");
		baseUrl2=objxml.getxml(ConfigXmlFile,"url2");
		baseUrlcafe=objxml.getxml(ConfigXmlFile,"cafe");
		ip=objxml.getxml(ConfigXmlFile,"ip");
	}
	
	@Test(groups={"AddIndividualUser"},priority=1)
	public void AddIndividualUser() throws Exception{
		System.out.println("@Test: Add Individual User");
		objLogin = new LoginPage(driver);
		objMainPage = new MainPage(driver);
		objAdvDefPage= new AdvancedConfigsPage(driver);
		objApplyConfigs= new ApplyConfigurations(driver);
		objDirectory= new DirectoryPage(driver);	
		
		driver.get(baseUrl);								
		objLogin.loginToPage(objxml.getxml(ConfigXmlFile,"username"),objxml.getxml(ConfigXmlFile,"password"));
		objAdvDefPage.goToDefenitionsPage();			
		objDirectory.AddIndividualUser();
		objApplyConfigs.goToApplyConfigsPage();
		objApplyConfigs.applyConfigs();
		Assert.assertTrue(objMainPage.getSuccessfullyUpdated().contains("Successfully updated!"));
		objMainPage.clickLogout();
		}
	
	@Test(groups={"VeryfyAddIndividualUser"},priority=2)
	public void VerifyAddIndividualUser() throws Exception{
		System.out.println("@Test: Verify User successfuly Added");
		objGroupware= new GroupwarePage(driver);
		objCafe= new CafePage(driver);
		int num=Integer.parseInt(objxml.getxml(ConfigXmlFile,"nUsers"));
		int i;
					
		driver.get(baseUrlcafe);
		driver.manage().window().maximize();
		objCafe.loginCafe("Administrator", "123");
		objCafe.SendMailbyCafe(objCafe.AllUsersMailList());
		objCafe.logout();
		
		for(i=0;i<num;i++){
			objCafe.loginCafe(objxml.getxml(ConfigXmlFile,"log"+i),objxml.getxml(ConfigXmlFile,"pw"+i));
			objCafe.gotoMyFiles();
			objCafe.uploadfile();		
			objCafe.CheckMailbyCafe();
		}
		
		//SSHUploader up = new SSHUploader();
			/*////////////////////////////////////////////////////////////////////////////////////////////////
			// Store the current window handle
			String winHandleBefore = driver.getWindowHandle();
			// Perform the click operation that opens new window
			objCafe.gotogroupware();
			// Switch to new window opened
			for(String winHandle : driver.getWindowHandles()){
				driver.switchTo().window(winHandle);
			}
			// Perform the actions on new window
			objGroupware.sendMail("sreis@sreis51.com");
			// Close the new window, if that window no more required
			driver.close();
			// Switch back to original browser (first window)
			driver.switchTo().window(winHandleBefore);
			// Continue with original browser (first window)
	
			*/////////////////////////////////////////////////////////////////////////////////////////////////
	
			//int num=Integer.parseInt(objxml.getxml(ConfigXmlFile,"nUsers"));
			//for(int i=0;i<num;i++){
				
			//	String ip=objxml.getxml(ConfigXmlFile,"ip");

			//up.connect(ip);	
			
			//String cmd=up.executeCommand("cd ~"+objxml.getxml(ConfigXmlFile,"log"+i).toLowerCase());
	    	//Assert.assertFalse(cmd.contains("No such file or directory"));
	    	
	    	//String cmd2=up.executeCommand("stat -c \"%a\" ~"+objxml.getxml(ConfigXmlFile,"log"+i).toLowerCase());
	    	//Assert.assertTrue(cmd2.contains("700"));
	    	
	    	//String cmd3=up.executeCommand("id "+objxml.getxml(ConfigXmlFile,"log"+i).toLowerCase());
	    	//Assert.assertFalse(cmd3.contains("No such user"));
	    	
	    	//String cmd5=up.executeCommand("slapcat | grep \"dn: cn="+objxml.getxml(ConfigXmlFile,"log"+i).toLowerCase()+",ou=auto.home\" |wc -l");
	    	//Assert.assertTrue(cmd5.contains("1"));
	    	//up.executeCommand("rm /home*/_accounts/"+objxml.getxml(ConfigXmlFile,"log"+i).toLowerCase()+"/Maildir/new/*; "
	    	//					+ "echo `date +%F_%T`' MailTest' |mailx "+objxml.getxml(ConfigXmlFile,"log"+i).toLowerCase()+"@`hostname -d`");
	    	
	    	//String cmd4=up.executeCommand("sleep 5; cat /home*/_accounts/"+objxml.getxml(ConfigXmlFile,"log"+i).toLowerCase()+"/Maildir/new/* | tail -1");
	    	//Assert.assertTrue(cmd4.contains("MailTest"));
	    	
	    	
	    	
	    	//String cmd6=up.executeCommand("ejabberdctl registered_users "+objxml.getxml(ConfigXmlFile,"domain"));
	    	//Assert.assertTrue(cmd6.contains(objxml.getxml(ConfigXmlFile,"log"+i)));
	    	
	   	    	
	    	//up.disconnect();	    	
		//}
	    		
	   // objGroupware.ReadXmlAndSendMail();	
		//driver.quit();
	}
	
	@Test(groups={"ModifyUser"},priority=1)
	public void ModifyUser() throws Exception{
		System.out.println("@Test: Modify User");
		objLogin = new LoginPage(driver);
		objMainPage = new MainPage(driver);
		objAdvDefPage= new AdvancedConfigsPage(driver);
		objApplyConfigs= new ApplyConfigurations(driver);
		objDirectory= new DirectoryPage(driver);
		objUploadFile= new UploadFile();
		
		driver.get(baseUrl);								
		objLogin.loginToPage(objxml.getxml(ConfigXmlFile,"username"),objxml.getxml(ConfigXmlFile,"password"));
		objAdvDefPage.goToDefenitionsPage();
		objDirectory.AddNewUser(objxml.getxml(ConfigXmlFile,"nameM0"),
								objxml.getxml(ConfigXmlFile,"nameM0"),
								objxml.getxml(ConfigXmlFile,"workareaM0"),
								objxml.getxml(ConfigXmlFile,"pwM0"),
								objxml.getxml(ConfigXmlFile,"pwwM0"));
		
		objApplyConfigs.goToApplyConfigsPage();
		objApplyConfigs.applyConfigs();
		Assert.assertTrue(objMainPage.getSuccessfullyUpdated().contains("Successfully updated!"));
		objMainPage.clickLogout();
		
		objLogin.loginToPage(objxml.getxml(ConfigXmlFile,"username"),objxml.getxml(ConfigXmlFile,"password"));
		objAdvDefPage.goToDefenitionsPage();
		objDirectory.ModifyUser(objxml.getxml(ConfigXmlFile,"nameM"),
								objxml.getxml(ConfigXmlFile,"workareaM"),
								objxml.getxml(ConfigXmlFile,"pwM"),
								objxml.getxml(ConfigXmlFile,"pwwM"));	
		objApplyConfigs.goToApplyConfigsPage();
		objApplyConfigs.applyConfigs();			
		Assert.assertTrue(objMainPage.getSuccessfullyUpdated().contains("Successfully updated!"));
		objMainPage.clickLogout();
	}
	
	@Test(groups={"VeryfyModifyUser"},priority=2)
	public void VerifyModifyUser() throws Exception{
		System.out.println("@Test: Verify User successfuly Modified");
		objGroupware= new GroupwarePage(driver);
		objLogin = new LoginPage(driver);
		objMainPage = new MainPage(driver);
		objAdvDefPage= new AdvancedConfigsPage(driver);
		objApplyConfigs= new ApplyConfigurations(driver);
		objDirectory= new DirectoryPage(driver);
		objUploadFile= new UploadFile();
		objCafe= new CafePage(driver);
		
		driver.get(baseUrlcafe);
		driver.manage().window().maximize();
		objCafe.loginCafe("Administrator", "123");
		objCafe.SendMailbyCafe(objxml.getxml(ConfigXmlFile,"logM").concat("@").concat(objxml.getxml(ConfigXmlFile,"domain")));
		objCafe.logout();
		
		objCafe.loginCafe(objxml.getxml(ConfigXmlFile,"logM"),objxml.getxml(ConfigXmlFile,"pwM"));
		objCafe.gotoMyFiles();
		objCafe.uploadfile();		
		objCafe.CheckMailbyCafe();
		
	}
	
	
	@Test(groups={"DeleteUser"},priority=1)
	public void DeleteUser() throws Exception{
		System.out.println("@Test: Delete User");
		objLogin = new LoginPage(driver);
		objMainPage = new MainPage(driver);
		objAdvDefPage= new AdvancedConfigsPage(driver);
		objApplyConfigs= new ApplyConfigurations(driver);
		objDirectory= new DirectoryPage(driver);
		objUploadFile= new UploadFile();
		
		driver.get(baseUrl);								
		objLogin.loginToPage(objxml.getxml(ConfigXmlFile,"username"),objxml.getxml(ConfigXmlFile,"password"));
		objAdvDefPage.goToDefenitionsPage();
		objDirectory.AddNewUser(objxml.getxml(ConfigXmlFile,"nameD"),
								objxml.getxml(ConfigXmlFile,"logD"),
								objxml.getxml(ConfigXmlFile,"workareaD"),
								objxml.getxml(ConfigXmlFile,"pwD"),
								objxml.getxml(ConfigXmlFile,"pwwD"));
		
		objApplyConfigs.goToApplyConfigsPage();
		objApplyConfigs.applyConfigs();
		Assert.assertTrue(objMainPage.getSuccessfullyUpdated().contains("Successfully updated!"));
		objMainPage.clickLogout();
		
		driver.get(baseUrl);								
		objLogin.loginToPage(objxml.getxml(ConfigXmlFile,"username"),objxml.getxml(ConfigXmlFile,"password"));
		objAdvDefPage.goToDefenitionsPage();	
		
		objDirectory.DeleteUser();	
		
		objApplyConfigs.goToApplyConfigsPage();
		objApplyConfigs.applyConfigs();			
		Assert.assertTrue(objMainPage.getSuccessfullyUpdated().contains("Successfully updated!"));
		objMainPage.clickLogout();
	}
	
	@Test(groups={"VeryfyDeletedUser"},priority=2)
	public void VerifyDeletedUser() throws Exception{
		System.out.println("@Test: Verify User successfuly Deleted");
		objGroupware= new GroupwarePage(driver);
		objCafe= new CafePage(driver);
		//SSHUploader up = new SSHUploader();	
		
		driver.get(baseUrlcafe);
		driver.manage().window().maximize();
		objCafe.loginCafe(objxml.getxml(ConfigXmlFile,"logD"),objxml.getxml(ConfigXmlFile,"pwD"));
		
		Assert.assertTrue(objCafe.getFailLoginMessage().contains("Incorrect username or password."));	
			
			//up.connect();	
			//up.connect(ip);
		
			//String cmd=up.executeCommand("cd ~"+objxml.getxml(ConfigXmlFile,"logD").toLowerCase());
	    	//Assert.assertTrue(cmd.contains("No such file or directory"));
	    	
	    	//String cmd2=up.executeCommand("stat -c \"%a\" ~"+objxml.getxml(ConfigXmlFile,"logM").toLowerCase());
	    	//Assert.assertTrue(cmd2.contains("700"));
	    	
	    	//String cmd3=up.executeCommand("id "+objxml.getxml(ConfigXmlFile,"logM").toLowerCase());
	    	//Assert.assertFalse(cmd3.contains("No such user"));
	    	
	    	//up.executeCommand("rm /home1/_accounts/administrator/Maildir/new/*; "
	    	//					+ "echo `date +%F_%T`' MailTest' |mailx administrator@`hostname -d`");
	    	
	    	//String cmd4=up.executeCommand("sleep 5; cat /home1/_accounts/administrator/Maildir/new/* | tail -1");
	    	//Assert.assertTrue(cmd4.contains("MailTest"));
	    	
	    	//String cmd5=up.executeCommand("slapcat | grep \"dn: cn="+objxml.getxml(ConfigXmlFile,"logM").toLowerCase()+",ou=auto.home\" |wc -l");
	    	//Assert.assertTrue(cmd5.contains("1"));
	    	
	    	//String cmd6=up.executeCommand("ejabberdctl registered_users "+objxml.getxml(ConfigXmlFile,"domain"));
	    	//Assert.assertTrue(cmd6.contains(objxml.getxml(ConfigXmlFile,"logM")));
	    	
	    	//String cmd7=up.executeCommand("login "+objxml.getxml(ConfigXmlFile,"log"+i));
	    	//Assert.assertTrue(cmd7.contains(objxml.getxml(ConfigXmlFile,"log"+i)));	    	
	    	//up.disconnect();	    	
	    		
			// objGroupware.ReadXmlAndSendMail();		
	}
	
	
	@Test(groups={"AddMassUsers"},priority=1)
	public void AddMassUser() throws Exception{
		System.out.println("@Test: Add Massive Users");
		objLogin = new LoginPage(driver);
		objMainPage = new MainPage(driver);
		objAdvDefPage= new AdvancedConfigsPage(driver);
		objApplyConfigs= new ApplyConfigurations(driver);
		objDirectory= new DirectoryPage(driver);
		objUploadFile= new UploadFile();
		
		driver.get(baseUrl);								
		objLogin.loginToPage(objxml.getxml(ConfigXmlFile,"username"),objxml.getxml(ConfigXmlFile,"password"));
		objAdvDefPage.goToDefenitionsPage();
		objDirectory.goToUserListPage();
		objDirectory.ClickMassOperation();
		objUploadFile.FileUploadRobot(driver, objxml.getxml(ConfigXmlFile, "userslist"));
		//objUploadFile.CVSUpload(driver,objxml.getxml(ConfigXmlFile,"userslist"));
		objApplyConfigs.goToApplyConfigsPage();
		objApplyConfigs.applyConfigs();			
		Assert.assertTrue(objMainPage.getSuccessfullyUpdated().contains("Successfully updated!"));
		objMainPage.clickLogout();
	}
	
	@Test(groups={"VerifyMass"},priority=2)
	public void VerifyMass() throws Exception{
		System.out.println("@Test: Verify Massive Users successfuly Added");
		//SSHUploader up = new SSHUploader();
		objGroupware= new GroupwarePage(driver);
		objCafe= new CafePage(driver);
		
		driver.get(baseUrlcafe);
		driver.manage().window().maximize();
		objCafe.loginCafe("Administrator", "123");
		objCafe.SendMailbyCafe(objCafe.GetMassList());
		objCafe.logout();

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
        		driver.get(baseUrlcafe);
        		driver.manage().window().maximize();
        		objCafe.loginCafe(name[2],name[9]);
        		objCafe.gotoMyFiles();
        		objCafe.uploadfile();		
        		objCafe.CheckMailbyCafe();
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
			//up.connect(ip);
	    	//String cmd2=up.executeCommand("stat -c \"%a\" ~"+objxml.getxml(ConfigXmlFile,"logM").toLowerCase());
	    	//Assert.assertTrue(cmd2.contains("700"));
	    	
	    	//String cmd3=up.executeCommand("id "+objxml.getxml(ConfigXmlFile,"logM").toLowerCase());
	    	//Assert.assertFalse(cmd3.contains("No such user"));
	    	
	    	//up.executeCommand("rm /home1/_accounts/administrator/Maildir/new/*; "
	    	//					+ "echo `date +%F_%T`' MailTest' |mailx administrator@`hostname -d`");
	    	
	    	//String cmd4=up.executeCommand("sleep 5; cat /home1/_accounts/administrator/Maildir/new/* | tail -1");
	    	//Assert.assertTrue(cmd4.contains("MailTest"));
	    	
	    	//String cmd5=up.executeCommand("slapcat | grep \"dn: cn="+objxml.getxml(ConfigXmlFile,"logM").toLowerCase()+",ou=auto.home\" |wc -l");
	    	//Assert.assertTrue(cmd5.contains("1"));
	    	
	    	//String cmd6=up.executeCommand("ejabberdctl registered_users "+objxml.getxml(ConfigXmlFile,"domain"));
	    	//Assert.assertTrue(cmd6.contains(objxml.getxml(ConfigXmlFile,"logM")));
	    	
	    	//String cmd7=up.executeCommand("login "+objxml.getxml(ConfigXmlFile,"log"+i));
	    	//Assert.assertTrue(cmd7.contains(objxml.getxml(ConfigXmlFile,"log"+i)));	    	
	    	//up.disconnect();	    			
	}
	
	@AfterMethod(groups = {"AddIndividualUser","AddMassUsers"})
	public void shutDownSelenium() {
		//objMainPage = new MainPage(driver);
		//objMainPage.clickLogout();
		driver.manage().deleteAllCookies();
	    //driver.quit();
	}
}
