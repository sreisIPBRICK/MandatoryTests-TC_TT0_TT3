package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage {

	WebDriver driver;
	
	@FindBy(xpath=".//*[@id='logout']/a")
	WebElement Logout;
	
	@FindBy(id= "username")
	WebElement UserName;
	
	@FindBy(xpath="html/body/table/tbody/tr/td/center/h1")
	WebElement Successupdate;
	
	public MainPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void clickProfile() {
		this.switchToFrame("Default");
		UserName.click();
	}
	
	public void clickLogout(){
		this.switchToFrame("Default");
		Logout.click();
	}

	public void switchToFrame(String frame){
		if(frame.contains("Servidor_ver"))
			driver.switchTo()
			.frame(driver.findElement(By.cssSelector("iframe[src='corpo.php?pagina=servidor_ver']")));	
	
		if(frame.contains("Default"))
			driver.switchTo().defaultContent();
		
		if(frame.contains("Actualizar_def_confirmacao"))
			driver.switchTo()
			.frame(driver.findElement(By.cssSelector("iframe[src='corpo.php?pagina=actualizar_def_confirmacao']")));
		
		if(frame.contains("repor_def_base_ver_lista"))
			driver.switchTo()
			.frame(driver.findElement(By.cssSelector("iframe[src='corpo.php?pagina=repor_def_base_ver_lista']")));
		
		if(frame.contains("utilizador_ver_lista"))
			driver.switchTo()
			.frame(driver.findElement(By.cssSelector("iframe[src='corpo.php?pagina=utilizador_ver_lista']")));
		
		if(frame.contains("bugfixes_ver_lst"))
			driver.switchTo()
			.frame(driver.findElement(By.cssSelector("iframe[src='corpo.php?pagina=bugfixes_ver_lst']")));
		
		if(frame.contains("servicos"))
			driver.switchTo()
			.frame(driver.findElement(By.cssSelector("iframe[src='corpo.php?pagina=servicos']")));
	
		if(frame.contains("servicos_alt"))
			driver.switchTo()
			.frame(driver.findElement(By.cssSelector("iframe[src='corpo.php?pagina=servicos_alt']")));
		
		if(frame.contains("servidor_alterar"))
			driver.switchTo()
			.frame(driver.findElement(By.cssSelector("iframe[src='corpo.php?pagina=servidor_alterar']")));
		
		if(frame.contains("maquinas_ver_lista"))
			driver.switchTo()
			.frame(driver.findElement(By.cssSelector("iframe[src='corpo.php?pagina=maquinas_ver_lista']")));
		
		if(frame.contains("voip_rotas"))
			driver.switchTo()
			.frame(driver.findElement(By.cssSelector("iframe[src='corpo.php?pagina=voip_rotas']")));
		
		if(frame.contains("composeMessage"))
			driver.switchTo().frame(driver.findElement(By.cssSelector("iframe[title='Rich text editor, composeMessage']")));
		
		if(frame.contains("Messagebody"))
			driver.switchTo().frame("lfAkiCKFtZMoo_nAXG-4How");
	}
	
	public String getHomePageDashboardUserName() {
		return UserName.getText();
	}
	public String getSuccessfullyUpdated(){
		String str = Successupdate.getText();
		System.out.println(str);
		return str;
	}
}