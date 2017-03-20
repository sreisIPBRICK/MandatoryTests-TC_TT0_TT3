package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class UCoIPpage {
	
	WebDriver driver;
	MainPage objMainPage;
	
	@FindBy(id="menuheader2")
	WebElement UCoIPMenu;
	@FindBy(linkText="VoIP")
	WebElement VoIP;
	@FindBy(linkText="Routes Management")
	WebElement RouteManagement;
	@FindBy(xpath="html/body/table/tbody/tr/td/table[4]/tbody/tr/td/a")
	WebElement Insert;
	@FindBy(name="nome")
	WebElement name;
	@FindBy(name="sipserver")
	WebElement sipserver;
	@FindBy(name="inserir")
	WebElement inserir;
	@FindBy(linkText="Insert")
	WebElement Insertt;
	@FindBy(name="prefixovoip")
	WebElement prefixovoip;
	@FindBy(name="accao")
	WebElement accao;
	
	
	public UCoIPpage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void goToRouteManagement(){
		objMainPage= new MainPage(driver);
		objMainPage.switchToFrame("Default");
		UCoIPMenu.click();
		VoIP.click();
		RouteManagement.click();
	}
	public void InsertOutboundRoute(String str1, String str2, String str3){
		objMainPage= new MainPage(driver);
		objMainPage.switchToFrame("voip_rotas");
		Insert.click();
		name.sendKeys(str1);
		sipserver.sendKeys(str2);
		inserir.click();
		
		Insertt.click();
		
		prefixovoip.sendKeys(str3);
		accao.click();
	}
	public void RouteDefenitions(String str1){
		objMainPage= new MainPage(driver);
		objMainPage.switchToFrame("voip_rotas");
		
		Insertt.click();
		prefixovoip.sendKeys(str1);
		
	}
}
