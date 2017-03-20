package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class ApplyConfigurations {
	
	WebDriver driver;
	MainPage objMainPage;
	
	@FindBy(id="menuheader5")
	WebElement ApplyConfigsMenu;	
	@FindBy(name="f_actualiza")
	WebElement ApplyBtn;
	@FindBy(name="description")
	WebElement description;
	
	public ApplyConfigurations(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	public void goToApplyConfigsPage(){
		objMainPage=new MainPage(driver);
		objMainPage.switchToFrame("Default");
		ApplyConfigsMenu.click();
	}	
	public void applyconfigWithDescription(String Description){
		objMainPage=new MainPage(driver);
		WebDriverWait wait = new WebDriverWait(driver, 30000);
		objMainPage.switchToFrame("Default");
		this.goToApplyConfigsPage();
		objMainPage.switchToFrame("Actualizar_def_confirmacao");
		description.sendKeys(Description);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.name("f_actualiza")));
		ApplyBtn.click();
	}
	public void applyConfigs(){
		objMainPage=new MainPage(driver);
		WebDriverWait wait = new WebDriverWait(driver, 30000);
		objMainPage.switchToFrame("Default");
		this.goToApplyConfigsPage();
		objMainPage.switchToFrame("Actualizar_def_confirmacao");
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.name("f_actualiza")));
		ApplyBtn.click();
	}
	
	//WebElement myDynamicElement = (new WebDriverWait(driver, 10))
	//		  .until(ExpectedConditions.presenceOfElementLocated(By.id("myDynamicElement")));
	/*public void WaitTilWebElement(){
		WebElement wait = (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.name("f_actualiza")));
	}*/
	
}

