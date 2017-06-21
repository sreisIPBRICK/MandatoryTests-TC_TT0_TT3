package Pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class AdvancedConfigsPage{

	WebDriver driver;
	MainPage objMainPage;
	
	@FindBy(id="menuheader4") WebElement AdvancedConfigsMenu;
	@FindBy(linkText="IPBrick")	WebElement IpbrickBtn;
	@FindBy(linkText="Update") WebElement UpdateBtn;
	@FindBy(linkText="Insert") WebElement Insert;
	@FindBy(linkText="Definitions")
	WebElement DefenitionsBtn;
	@FindBy(linkText= "ETH0")
	WebElement ETH0btn;
	@FindBy(linkText= "ETH1")
	WebElement ETH1btn;
	@FindBy(linkText="Modify")
	WebElement Modifybtn;
	
	@FindBy(css="a[href*='servidor_alterar']")
	//@FindBy(xpath="html/body/table/tbody/tr/td/table[2]/tbody/tr[1]/td/a")
	WebElement ModifyDomain;
	
	@FindBy(name="f_nome")
	WebElement f_nome;
	@FindBy(name="f_dominio")
	WebElement f_dominio;
	@FindBy(css="a[href*='interface_gw_alterar']")
	WebElement ModifyGateway;
	@FindBy(name="f_interface")
	WebElement f_interface;
	@FindBy(name="f_ipgateway_1")
	WebElement f_ipgateway_1;
	@FindBy(name="f_ipgateway_2")
	WebElement f_ipgateway_2;
	@FindBy(name="f_ipgateway_3")
	WebElement f_ipgateway_3;
	@FindBy(name="f_ipgateway_4")
	WebElement f_ipgateway_4;
	@FindBy(name="f_ip_1")
	WebElement f_ip_1;
	@FindBy(name="f_ip_2")
	WebElement f_ip_2;
	@FindBy(name="f_ip_3")
	WebElement f_ip_3;
	@FindBy(name="f_ip_4")
	WebElement f_ip_4;	
	@FindBy(name="f_accao")
	WebElement Apply;
	@FindBy(linkText="Disaster recovery")
	WebElement DisasterRecover;
	@FindBy(linkText="Configurations")
	WebElement ConfigurationsBtn;
	@FindBy(linkText="Replace")
	WebElement ReplaceBtn;
	@FindBy(linkText="Default")
	WebElement Default;
	@FindBy(css="a[href*='repor_def_base_ver']")
	WebElement OtherConfig;
	@FindBy(name="f_actualiza")
	WebElement f_actualiza;
	@FindBy(className="dados")
	WebElement dados;
	
	public AdvancedConfigsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void goToDefenitionsPage(){
		objMainPage= new MainPage(driver);
		objMainPage.switchToFrame("Default");
		IpbrickBtn.click();
		DefenitionsBtn.click();
	}
	
	public void goToUpdatesPage(){
		objMainPage= new MainPage(driver);
		objMainPage.switchToFrame("Default");
		IpbrickBtn.click();
		UpdateBtn.click();
		//objMainPage.switchToFrame("bugfixes_ver_lst");
		//Insert.click();
	}
	
	public void clickInsert(){
		objMainPage.switchToFrame("bugfixes_ver_lst");
		Insert.click();
	}
	
	public void goToReplacePage(){
		objMainPage= new MainPage(driver);
		objMainPage.switchToFrame("Default");
		AdvancedConfigsMenu.click();
		DisasterRecover.click();
		ConfigurationsBtn.click();
		ReplaceBtn.click();
		
	}
	
	public void ReplaceDefaultConfigs(){
		objMainPage.switchToFrame("repor_def_base_ver_lista");
		Default.click();
		f_actualiza.click();
	}
	
	public void ReplaceOtherDefaultConfigs(){
		objMainPage.switchToFrame("repor_def_base_ver_lista");
		OtherConfig.click();
		f_actualiza.click();
	}
		
	public void goToModifyDomainDefenitions(){
		objMainPage= new MainPage(driver);
		objMainPage.switchToFrame("Servidor_ver");
		ModifyDomain.click();
		
	}
	
	public void changeDomainAndName(String a,String b){
		f_nome.clear();
		f_nome.sendKeys(a);
		
		f_dominio.clear();
		f_dominio.sendKeys(b);
		
		Apply.click();
		driver.switchTo().alert().accept();
	}
	
	public String getNameValueFromBox(){
		String name=f_nome.getAttribute("value");
		return name;
	}
	
	public String getDomainValueFromBox(){
		String domain=f_dominio.getAttribute("value");
		return domain;
	}
	
	public void goToModifyGateway(){
		objMainPage= new MainPage(driver);
		objMainPage.switchToFrame("Servidor_ver");
		ModifyGateway.click();
	}
	
	public void goToModifyGateway2(){
		objMainPage= new MainPage(driver);
		objMainPage.switchToFrame("Servidor_ver");
		ModifyGateway.click();
	}
	
	public void SelectDropDownValue(String eth){
		Select option= new Select(f_interface);
		option.selectByVisibleText(eth);
	}
	
	
	public void chengeGateway(String eth,String a, String b, String c, String d){
		
		this.SelectDropDownValue(eth);
		
		f_ipgateway_1.clear();
		f_ipgateway_1.sendKeys(a);
		
		f_ipgateway_2.clear();
		f_ipgateway_2.sendKeys(b);
		
		f_ipgateway_3.clear();
		f_ipgateway_3.sendKeys(c);
		
		f_ipgateway_4.clear();
		f_ipgateway_4.sendKeys(d);
		
		Apply.click();
		driver.switchTo().alert().accept();
	}
	
	public String getGatewayFromBox(String eth){
		this.SelectDropDownValue(eth);
		String gateway=f_ipgateway_1.getAttribute("value")
				.concat(".").concat(f_ipgateway_2.getAttribute("value")).concat(".")
				.concat(f_ipgateway_3.getAttribute("value")).concat(".")
				.concat(f_ipgateway_4.getAttribute("value"));
		return gateway;
	}
	
	public void goToModifyEthXPage(String ETHX){
		objMainPage= new MainPage(driver);
		objMainPage.switchToFrame("Servidor_ver");
		
		if(ETHX.equals("0")){
			ETH0btn.click();
			Modifybtn.click();
		}
		if(ETHX.equals("1")){
			ETH1btn.click();
			Modifybtn.click();
			}
	}
	
	public  String getIPvalueFromBoxFild(){
		String IP=f_ip_1.getAttribute("value")
				.concat(".").concat(f_ip_2.getAttribute("value")).concat(".")
				.concat(f_ip_3.getAttribute("value")).concat(".")
				.concat(f_ip_4.getAttribute("value"));	
		return IP;
	}
	
	public String getDadosOfupdate(){
		objMainPage= new MainPage(driver);
		objMainPage.switchToFrame("Default");
		AdvancedConfigsMenu.click();
		IpbrickBtn.click();
		UpdateBtn.click();
		objMainPage.switchToFrame("bugfixes_ver_lst");
		String UpdateData=dados.getText();//.getAttribute("value");
		return UpdateData;
	}
	
	public void changeIP(String a,String b, String c,String d){
		f_ip_1.clear();
		f_ip_1.sendKeys(a);
		
		f_ip_2.clear();
		f_ip_2.sendKeys(b);
		
		f_ip_3.clear();
		f_ip_3.sendKeys(c);
		
		f_ip_4.clear();
		f_ip_4.sendKeys(d);
		
		Apply.click();
		driver.switchTo().alert().accept();		
	}
	
}
