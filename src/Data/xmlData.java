package Data;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class xmlData {
	
	String string;
	
	public String getxml(String ConfigFile , String var) throws ParserConfigurationException, SAXException, IOException{// Get xml file
    
	File file = new File(ConfigFile);
    // Prepare XML
    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
    DocumentBuilder db = dbf.newDocumentBuilder();
    Document document = db.parse(file);
    String string = document.getElementsByTagName(var).item(0).getTextContent();
    //System.out.println(string);
    return string;
	}
	
	public String ReadcsvFile() throws InterruptedException, ParserConfigurationException, SAXException, IOException{
		//objLogin= new LoginPage(driver);
		String ConfigXmlFile="FilesXML/AddUsers.xml";
		String login = null;
		
		String csvFile = this.getxml(ConfigXmlFile,"userslist");/*"/home/sreis/workspace/userslist.csv";*/
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
                login = name[2]; 
                //objLogin.loginGroupware(name[2],name[9]);
                //System.out.println("ReadcvsFileAndSendMail2()"+ driver.getTitle());
                //Assert.assertTrue(driver.getTitle().contains("Mail ::"));
                //this.sendMail(name[4]);
                //delete mail
                //return login;
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
		return login;
        
	}
	
}
