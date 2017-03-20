package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class ServicesPage {
	
	WebDriver driver;
	MainPage objMainPage;
	
	@FindBy(id="menuheader4")
	WebElement AdvancedConfigsMenu;
	@FindBy(linkText="Services")
	WebElement ServicesBtn;
	@FindBy(css="a[href*='servicos_alt']")
	WebElement Modifybtn;
	@FindBy(linkText="System")
	WebElement System;
	@FindBy(name="f_accao")
	WebElement Updatebtn;

	
	@FindBy(name="f_activo_8")WebElement GetmailSTATE;
	@FindBy(name="f_modo_8")WebElement STARTGetmail;
	
	@FindBy(name="f_activo_38")WebElement radiusSTATE;
	@FindBy(name="f_modo_38")WebElement STARTradius;
	
	@FindBy(name="f_activo_28")WebElement callmanagerSTATE;
	@FindBy(name="f_modo_28")WebElement STARTcallmanager;
	
	@FindBy(name="f_activo_43")WebElement clamAVSTATE;
	@FindBy(name="f_modo_43")WebElement STARTclamAV;
	
	@FindBy(name="f_activo_27")WebElement CupsysSTATE;
	@FindBy(name="f_modo_27")WebElement STARTCupsys;
	
	@FindBy(name="f_activo_2")WebElement dhcpSTATE;
	@FindBy(name="f_modo_2")WebElement STARTdhcp;
	
	@FindBy(name="f_activo_20")WebElement faxSTATE;
	@FindBy(name="f_modo_20")WebElement STARTfax;
	
	@FindBy(name="f_activo_12")WebElement quotaSTATE;
	@FindBy(name="f_modo_12")WebElement STARTquota;
	
	@FindBy(name="f_activo_35")WebElement samba4STATE;
	@FindBy(name="f_modo_35")WebElement STARTsamba4;
	
	@FindBy(name="f_activo_16")WebElement backupserverSTATE;
	@FindBy(name="f_modo_16")WebElement STARTbackupserver;
	
	@FindBy(name="f_activo_26")WebElement timeserverSTATE;
	@FindBy(name="f_modo_26")WebElement STARTtimeserver;
	
	@FindBy(name="f_activo_42")WebElement snmpSTATE;
	@FindBy(name="f_modo_42")WebElement STARTsnmp;
	
	@FindBy(name="f_activo_45")WebElement snortSTATE;
	@FindBy(name="f_modo_45")WebElement STARTsnort;
	
	@FindBy(name="f_activo_44")WebElement spamAssassinSTATE;
	@FindBy(name="f_modo_44")WebElement STARTspamAssassin;
	
	@FindBy(name="f_activo_40")WebElement virtualizationserverSTATE;
	@FindBy(name="f_modo_40")WebElement STARTvirtualizationserver;
	
	@FindBy(name="f_activo_13")WebElement vpnipsecSTATE;
	@FindBy(name="f_modo_13")WebElement STARTvpnipsec;
	
	@FindBy(name="f_activo_18")WebElement vpnsslSTATE;
	@FindBy(name="f_modo_18")WebElement STARTvpnssl;
	
	@FindBy(name="f_activo_5")WebElement emailSTATE;
	@FindBy(name="f_modo_5")WebElement STARTemail;
	
	@FindBy(name="f_activo_10")WebElement dnsSTATE;
	@FindBy(name="f_modo_10")WebElement STARTdns;
	
	@FindBy(name="f_activo_6")WebElement firewallSTATE;
	@FindBy(name="f_modo_6")WebElement STARTfirewall;
	
	@FindBy(name="f_activo_25")WebElement ftpSTATE;
	@FindBy(name="f_modo_25")WebElement STARTftp;
	
	@FindBy(name="f_activo_21")WebElement popimapSTATE;
	@FindBy(name="f_modo_21")WebElement STARTpopimap;
	
	@FindBy(name="f_activo_22")WebElement imapsSTATE;
	@FindBy(name="f_modo_22")WebElement STARTimaps;
	
	@FindBy(name="f_activo_41")WebElement nfsSTATE;
	@FindBy(name="f_modo_41")WebElement STARTnfs;
	
	//@FindBy(name="f_activo_23")WebElement pop3STATE;
	//@FindBy(name="f_modo_23")WebElement STARTpop3;
	
	//@FindBy(name="f_activo_24")WebElement pop3sSTATE;
	//@FindBy(name="f_modo_24")WebElement STARTpop3s;
	
	@FindBy(name="f_activo_9")WebElement postegresqlSTATE;
	@FindBy(name="f_modo_9")WebElement STARTpostegresql;
	
	@FindBy(name="f_activo_4")WebElement proxySTATE;
	@FindBy(name="f_modo_4")WebElement STARTproxy;
	
	@FindBy(name="f_activo_11")WebElement sambaSTATE;
	@FindBy(name="f_modo_11")WebElement STARTsamba;
	
	@FindBy(name="f_activo_33")WebElement sshSTATE;
	@FindBy(name="f_modo_33")WebElement STARTssh;
	
	@FindBy(name="f_activo_17")WebElement voipSTATE;
	@FindBy(name="f_modo_17")WebElement STARTvoip;
	
	@FindBy(name="f_activo_7")WebElement vpnpptpSTATE;
	@FindBy(name="f_modo_7")WebElement STARTvpnpptp;
	
	@FindBy(name="f_activo_46")WebElement webrtcSTATE;
	@FindBy(name="f_modo_46")WebElement STARTwebrtc;
	
	@FindBy(name="f_activo_50")WebElement webrtc2sipSTATE;
	@FindBy(name="f_modo_50")WebElement STARTwebrtc2sip;
	
	public ServicesPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
/*	public String printState(){
		String x=dados.getAttribute("value");
		//System.out.println(x);
		return x;
	}*/
	
	public void goToServicesPage(){
		objMainPage= new MainPage(driver);
		objMainPage.switchToFrame("Default");
		AdvancedConfigsMenu.click();
		System.click();
		ServicesBtn.click();
		objMainPage.switchToFrame("servicos");
		Modifybtn.click();
	}
	
	public void StopService(){

		Select Getmail= new Select(GetmailSTATE);
		Select GetmailM=new Select(STARTGetmail);
		Getmail.selectByVisibleText("Disabled");
		GetmailM.selectByVisibleText("Manual");
		
		Select Callmanager= new Select(callmanagerSTATE);
		Select CallmanagerM=new Select(STARTcallmanager);
		Callmanager.selectByVisibleText("Disabled");
		CallmanagerM.selectByVisibleText("Manual");
		
		Select ClamAV= new Select(clamAVSTATE);
		Select ClamAVM=new Select(STARTclamAV);
		ClamAV.selectByVisibleText("Disabled");
		ClamAVM.selectByVisibleText("Manual");
		
		Select Cupsys= new Select(CupsysSTATE);
		Select CupsysM=new Select(STARTCupsys);
		Cupsys.selectByVisibleText("Disabled");
		CupsysM.selectByVisibleText("Manual");
		
		Select dhcp= new Select(dhcpSTATE);
		Select dhcpM=new Select(STARTdhcp);
		dhcp.selectByVisibleText("Disabled");
		dhcpM.selectByVisibleText("Manual");
		
		Select fax= new Select(faxSTATE);
		Select faxM=new Select(STARTfax);
		fax.selectByVisibleText("Disabled");
		faxM.selectByVisibleText("Manual");
		
		Select quota= new Select(quotaSTATE);
		Select quotaM=new Select(STARTquota);
		quota.selectByVisibleText("Disabled");
		quotaM.selectByVisibleText("Manual");
		
		Select radius= new Select(radiusSTATE);
		Select radiusM=new Select(STARTradius);
		radius.selectByVisibleText("Disabled");
		radiusM.selectByVisibleText("Manual");
		
		Select samba4= new Select(samba4STATE);
		Select samba4M=new Select(STARTsamba4);
		samba4.selectByVisibleText("Disabled");
		samba4M.selectByVisibleText("Manual");
		
		Select backupserver= new Select(backupserverSTATE);
		Select backupserverM=new Select(STARTbackupserver);
		backupserver.selectByVisibleText("Disabled");
		backupserverM.selectByVisibleText("Manual");
		
		Select timeserver= new Select(timeserverSTATE);
		Select timeserverM=new Select(STARTtimeserver);
		timeserver.selectByVisibleText("Disabled");
		timeserverM.selectByVisibleText("Manual");
		
		Select snmp= new Select(snmpSTATE);
		Select snmpM=new Select(STARTsnmp);
		snmp.selectByVisibleText("Disabled");
		snmpM.selectByVisibleText("Manual");
		
		Select snort= new Select(snortSTATE);
		Select snortM=new Select(STARTsnort);
		snort.selectByVisibleText("Disabled");
		snortM.selectByVisibleText("Manual");

		Select spamAssassin= new Select(spamAssassinSTATE);
		Select spamAssassinM=new Select(STARTspamAssassin);
		spamAssassin.selectByVisibleText("Disabled");
		spamAssassinM.selectByVisibleText("Manual");
		
		Select virtualizationserver= new Select(virtualizationserverSTATE);
		Select virtualizationserverM=new Select(STARTvirtualizationserver);
		virtualizationserver.selectByVisibleText("Disabled");
		virtualizationserverM.selectByVisibleText("Manual");
		
		Select vpnipsec= new Select(vpnipsecSTATE);
		Select vpnipsecM=new Select(STARTvpnipsec);
		vpnipsec.selectByVisibleText("Disabled");
		vpnipsecM.selectByVisibleText("Manual");
		
		Select vpnssl= new Select(vpnsslSTATE);
		Select vpnsslM=new Select(STARTvpnssl);
		vpnssl.selectByVisibleText("Disabled");
		vpnsslM.selectByVisibleText("Manual");
		
		Select email= new Select(emailSTATE);////////////
		Select emailM=new Select(STARTemail);
		email.selectByVisibleText("Disabled");
		emailM.selectByVisibleText("Manual");
		
		Select dns= new Select(dnsSTATE);
		Select dnsM=new Select(STARTdns);
		dns.selectByVisibleText("Disabled");
		dnsM.selectByVisibleText("Manual");
		
		Select firewall= new Select(firewallSTATE);
		Select firewallM=new Select(STARTfirewall);
		firewall.selectByVisibleText("Disabled");
		firewallM.selectByVisibleText("Manual");
		
		Select ftp= new Select(ftpSTATE);
		Select ftpM=new Select(STARTftp);
		ftp.selectByVisibleText("Disabled");
		ftpM.selectByVisibleText("Manual");
		
		Select nfs= new Select(nfsSTATE);
		Select nfsM=new Select(STARTnfs);
		nfs.selectByVisibleText("Disabled");
		nfsM.selectByVisibleText("Manual");
		
		Select imap= new Select(popimapSTATE);
		Select imapM=new Select(STARTpopimap);
		imap.selectByVisibleText("Disabled");
		imapM.selectByVisibleText("Manual");
		
		/*Select imaps= new Select(imapsSTATE);
		Select imapsM=new Select(STARTimaps);
		imaps.selectByVisibleText("Disabled");
		imapsM.selectByVisibleText("Manual");
		
		Select pop3= new Select(pop3STATE);
		Select pop3M=new Select(STARTpop3);
		pop3.selectByVisibleText("Disabled");
		pop3M.selectByVisibleText("Manual");
		
		Select pop3s= new Select(pop3sSTATE);
		Select pop3sM=new Select(STARTpop3s);
		pop3s.selectByVisibleText("Disabled");
		pop3sM.selectByVisibleText("Manual");*/
		
		Select postegresql= new Select(postegresqlSTATE);
		Select postegresqlM=new Select(STARTpostegresql);
		postegresql.selectByVisibleText("Disabled");
		postegresqlM.selectByVisibleText("Manual");
		
		Select proxy= new Select(proxySTATE);
		Select proxyM=new Select(STARTproxy);
		proxy.selectByVisibleText("Disabled");
		proxyM.selectByVisibleText("Manual");
		
		Select samba= new Select(sambaSTATE);
		Select sambaM=new Select(STARTsamba);
		samba.selectByVisibleText("Disabled");
		sambaM.selectByVisibleText("Manual");
		
		Select ssh= new Select(sshSTATE);
		Select sshM=new Select(STARTssh);
		ssh.selectByVisibleText("Disabled");
		sshM.selectByVisibleText("Manual");

		Select voip= new Select(voipSTATE);
		Select voipM=new Select(STARTvoip);
		voip.selectByVisibleText("Disabled");
		voipM.selectByVisibleText("Manual");
		
		Select vpnpptp= new Select(vpnpptpSTATE);
		Select vpnpptpM=new Select(STARTvpnpptp);
		vpnpptp.selectByVisibleText("Disabled");
		vpnpptpM.selectByVisibleText("Manual");
		
		Select webrtc= new Select(webrtcSTATE);
		Select webrtcM=new Select(STARTwebrtc);
		webrtc.selectByVisibleText("Disabled");
		webrtcM.selectByVisibleText("Manual");
		
		Select webrtc2sip= new Select(webrtc2sipSTATE);
		Select webrtc2sipM=new Select(STARTwebrtc2sip);
		webrtc2sip.selectByVisibleText("Disabled");
		webrtc2sipM.selectByVisibleText("Manual");
		
		Updatebtn.click();
	}
	public String getState(){
		String matrix=emailSTATE.getAttribute("value")
				.concat(webrtc2sipSTATE.getAttribute("value"))
				.concat(webrtcSTATE.getAttribute("value"))
				.concat(vpnpptpSTATE.getAttribute("value"))
				.concat(voipSTATE.getAttribute("value"))
				.concat(sshSTATE.getAttribute("value"))
				.concat(sambaSTATE.getAttribute("value"))
				.concat(proxySTATE.getAttribute("value"))
				.concat(postegresqlSTATE.getAttribute("value"))
				.concat(radiusSTATE.getAttribute("value"))
				//.concat(pop3STATE.getAttribute("value"))
				.concat(nfsSTATE.getAttribute("value"))
				//.concat(imapsSTATE.getAttribute("value"))
				.concat(popimapSTATE.getAttribute("value"))
				.concat(ftpSTATE.getAttribute("value"))
				.concat(firewallSTATE.getAttribute("value"))
				.concat(dnsSTATE.getAttribute("value"))
				.concat(vpnsslSTATE.getAttribute("value"))
				.concat(vpnipsecSTATE.getAttribute("value"))
				.concat(virtualizationserverSTATE.getAttribute("value"))
				.concat(spamAssassinSTATE.getAttribute("value"))
				.concat(snortSTATE.getAttribute("value"))
				.concat(snmpSTATE.getAttribute("value"))
				.concat(timeserverSTATE.getAttribute("value"))
				.concat(backupserverSTATE.getAttribute("value"))
				.concat(samba4STATE.getAttribute("value"))
				.concat(quotaSTATE.getAttribute("value"))
				.concat(faxSTATE.getAttribute("value"))
				.concat(dhcpSTATE.getAttribute("value"))
				.concat(CupsysSTATE.getAttribute("value"))
				.concat(clamAVSTATE.getAttribute("value"))
				.concat(callmanagerSTATE.getAttribute("value"))
				.concat(GetmailSTATE.getAttribute("value"));
		
		return matrix;
	}
	
}
