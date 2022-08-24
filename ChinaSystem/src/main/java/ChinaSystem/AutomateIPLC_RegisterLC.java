package ChinaSystem;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Utils.ReadData;
import io.github.bonigarcia.wdm.WebDriverManager;
import utils.DataLibrary;

public class AutomateIPLC_RegisterLC{
	static String text;
	static Properties prop;
	 static FileInputStream objFile;
	 public static ChromeDriver driver;
	static String TEXT1;
	static String textforDescripancy;
	static JavascriptExecutor Js;
	static String text2;
	@DataProvider(name = "fetchData")
	public Object[][] fetchData() throws IOException {
		return ReadData.readExcelData("data.xlsx");
	}	
	@Test(dataProvider = "fetchData")
	public void TEST(String CS_Unit,String CS_Bank,String Password,String ExpiryDate,String OfficerCode) throws InterruptedException, IOException {
	
	 prop=new Properties();
		 objFile=new FileInputStream(new File("D:/codes/ChinaSystem/data.properties"));
		prop.load(objFile);
		
		WebDriverManager.chromedriver().setup();
		 driver = new ChromeDriver();
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		
		driver.get("http://192.168.2.40:7001/EximBillWeb/");
		driver.findElement(By.id("C_BUSINESS_UNIT")).sendKeys(CS_Unit);
		driver.findElement(By.id("C_USER_ID")).sendKeys(CS_Bank);
		driver.findElement(By.id("C_PASSWORD")).sendKeys(Password);
		driver.findElement(By.className("MuiButton-label")).click();
        Thread.sleep(1000);
		driver.findElement(By.xpath("//span[text()='Import Letter of Credit']")).click();
		driver.findElement(By.xpath("//span[text()='IPLC Issuance']")).click();
		driver.findElement(By.xpath("//span[text()='Register Letter of Credit']")).click();

		//Thread.sleep(8000);
		driver.switchTo().frame(1);
		Select element =new Select(driver.findElement(By.id("APLB_RULE")));
		element.selectByVisibleText(prop.getProperty("dropdownforrule"));
		String TEXT = element.getFirstSelectedOption().getText();
		
		System.out.println(TEXT);


		
		if(TEXT.equals("OTHER")) {
			driver.findElement(By.id("APLB_RULE_NARR")).sendKeys("New Version");
		}
		Select element1 =new Select(driver.findElement(By.id("AVAL_BY")));
		element1.selectByVisibleText(prop.getProperty("dropdownAvlBy"));
		 TEXT1 = element1.getFirstSelectedOption().getText();
		if(TEXT1.equals("By Acceptance")) {
			Select ele = new Select( driver.findElement(By.id("TENOR_TYPE")));
			ele.selectByVisibleText(prop.getProperty("dropdowntenor"));
		}
		else if(TEXT1.equals("By Negotiation")) {
			Select ele = new Select( driver.findElement(By.id("TENOR_TYPE")));
			ele.selectByVisibleText(prop.getProperty("dropdowntenor"));
		
		}
		else if(TEXT1.equals("By Def Payment")) {
			Select ele = new Select( driver.findElement(By.id("TENOR_TYPE")));
			ele.selectByVisibleText(prop.getProperty("dropdowntenor"));
			
		}
		else {
			System.out.println("nothing");
		}

		WebElement amt = driver.findElement(By.id("LC_AMT"));
		amt.click();

		amt.sendKeys("48000.00");
		Select element2 =new Select(driver.findElement(By.id("FORM_OF_LC")));
		element2.selectByIndex(0);
		driver.findElement(By.id("EXPIRY_DT")).sendKeys(ExpiryDate);
//		driver.findElement(By.id("EXPIRY_DT")).sendKeys("11.09.2022");
        driver.findElement(By.id("C")).click();
       
		driver.findElement(By.name("APPL_ID_BTN")).click();
//	    Thread.sleep(8000);

      
		
		
		

//		WebDriverWait wait= new WebDriverWait(driver, 30);
	
		
		  String handle1 = driver.getWindowHandle();
//        List<String>List=new ArrayList<String>(handle1);
       
		driver.switchTo().window(handle1);
		driver.switchTo().frame(5);
		Thread.sleep(8000);
		String property = prop.getProperty("custinfofromCUBK");
		System.out.println(property);
		
	
		driver.findElement(By.linkText(prop.getProperty("custinfofromCUBK"))).click();
		Thread.sleep(8000);
		WebDriverWait wait= new WebDriverWait(driver, 10);
		driver.switchTo().frame(1);
		driver.findElement(By.name("BENE_ID_BTN")).click();
		 String handle2 = driver.getWindowHandle();

		driver.switchTo().window(handle2);
		driver.switchTo().frame(5);
		driver.findElement(By.linkText(prop.getProperty("custinfofromCUBK1"))).click();
		driver.switchTo().frame(1);
//		driver.switchTo().frame(5);
		driver.findElement(By.id("AC_OFFICER_CODE")).sendKeys(OfficerCode);
		Thread.sleep(1000);
		Select element3 =new Select (driver.findElement(By.id("SAME_AS_APPL_FLG")));
		element3.selectByIndex(1);
		Thread.sleep(1000);
		driver.findElement(By.id("B")).click();
		driver.findElement(By.name("ASSET_ACNO_BTN")).click();
		 String handle3 = driver.getWindowHandle();
//       List<String>List=new ArrayList<String>(handle1);
      
		driver.switchTo().window(handle3);
		driver.switchTo().frame(5);
		driver.findElement(By.linkText("73011501")).click();
		driver.switchTo().frame(1);
		driver.findElement(By.name("APPL_AC_MRGN_BTN")).click();
		 String handle4 = driver.getWindowHandle();
//       List<String>List=new ArrayList<String>(handle1);
      
		driver.switchTo().window(handle4);
		driver.switchTo().frame(5);
		
		driver.findElement(By.linkText("93011501")).click();
		driver.switchTo().defaultContent();
		
		driver.switchTo().frame(0);
		driver.switchTo().frame(0);
		 text = driver.findElement(By.id("referenceNumber")).getText();
		System.out.println(text);
		driver.switchTo().defaultContent();
		driver.switchTo().frame(0);
		
		
		driver.findElement(By.className("MuiBottomNavigationAction-label")).click();
		driver.findElement(By.name("cancel")).click();
		driver.switchTo().defaultContent();
		Dimension size = driver.manage().window().getSize();
		System.out.println(size);
		
		this.Supervisorrelease();
	}


	public void Supervisorrelease() throws InterruptedException {
//		--------------------------Release---------------------------------------------------------------

		WebElement findElement = driver.findElement(By.xpath("//span[text()='IPLC Maintenance']"));
		 Js=(JavascriptExecutor)driver;
		System.out.println("hi");
		Js.executeScript("arguments[0].scrollIntoView();", findElement);
		Thread.sleep(8000);
		
		findElement.click();
		
		driver.findElement(By.xpath("//span[text()='Supervisor Release']")).click();
		driver.switchTo().frame(1);
		driver.findElement(By.id("CatalogSwitch")).click();
		Thread.sleep(8000);
		driver.findElement(By.name("OP2")).sendKeys(text);
		Thread.sleep(8000);
		driver.findElement(By.xpath("//div[@id='FILTER_ETL_DIALOG']/div/div[3]/div/div[3]/button[2]/span[1]")).click();
		driver.findElement(By.id("transaction")).click();
		driver.switchTo().defaultContent();
		driver.switchTo().frame(0);
		driver.findElement(By.className("MuiBottomNavigationAction-label")).click();
		driver.findElement(By.name("cancel")).click();
	
//		--------------------------------------Release--------------------------------------------------------
		this.Issuelc();
	}
	/**
	 * @throws InterruptedException
	 */
	public void Issuelc() throws InterruptedException {
		driver.switchTo().defaultContent();
//		driver.switchTo().frame(1);
		WebElement element = driver.findElement(By.xpath("//span[text()='Issue Letter of Credit']"));
		Js.executeScript("arguments[0].scrollIntoView();", element);
		element.click();
		driver.switchTo().frame(1);
		Thread.sleep(8000);
		driver.findElement(By.id("CatalogSwitch")).click();
		
		driver.findElement(By.name("OP2")).sendKeys(text);
		Thread.sleep(8000);
		driver.findElement(By.xpath("//div[@id='FILTER_ETL_DIALOG']/div/div[3]/div/div[3]/button[2]/span[1]")).click();
		driver.switchTo().defaultContent();
		driver.switchTo().frame(0);
		driver.findElement(By.name("next")).click();
		driver.switchTo().defaultContent();
		driver.switchTo().frame(1);
		
		driver.findElement(By.id("D")).click();
		if(TEXT1.equals("By Payment")) {
			driver.findElement(By.id("DRW_ID_BTN")).click();
			Alert alert = driver.switchTo().alert();
			alert.accept();
			String handle3 = driver.getWindowHandle();
			driver.switchTo().window(handle3);
			driver.switchTo().frame(5);
			driver.findElement(By.linkText(prop.getProperty("custinfofromCUBKFORISSUELC1"))).click();
			
		}
		else if(TEXT1.equals("By Acceptance")) {
			driver.findElement(By.id("DRAFTS_AT")).sendKeys("something");
			driver.findElement(By.id("DRW_ID_BTN")).click();
			Alert alert = driver.switchTo().alert();
			alert.accept();
			String handle3 = driver.getWindowHandle();
			driver.switchTo().window(handle3);
			driver.switchTo().frame(5);
			driver.findElement(By.linkText(prop.getProperty("custinfofromCUBKFORISSUELC1"))).click();
		}
		else if(TEXT1.equals("By Negotiation")) {
			driver.findElement( By.id("DEF_PMT_DET")).sendKeys("this feild for negotiation");
		}
		else if(TEXT1.equals("By Def Payment")) {
			driver.findElement( By.id("DEF_PMT_DET")).sendKeys("this feild for Def Payment");
			}
		else if(TEXT1.equals("BY MIXED PYMT")) {
			driver.findElement(By.id("MIX_PMT_DETL")).sendKeys("this feild for Mixed Payment");
			driver.findElement(By.id("K")).click();
			driver.findElement(By.id("PaymentTerms_ADD")).click();
			Select element4 =new Select(driver.findElement(By.name("CPYT_C_SDA_FLAG")));
			element4.selectByVisibleText("Sight");
			String text2 = element4.getFirstSelectedOption().getText();
			WebElement eleForamtpercentage = driver.findElement(By.name("CPYT_C_PAY_PER"));
			eleForamtpercentage.clear();
			eleForamtpercentage.sendKeys(prop.getProperty("amt%ofmixedpaymentsight"));
			String text3 = eleForamtpercentage.getText();
			System.out.println(text3);
			
			if(!text3.equals("100")) {
			
			if(text2.equals("Deferred")) {
				driver.findElement(By.id("PaymentTerms_ADD")).click();
				driver.findElement(By.name("CPYT_I_TENOR_DAYS")).sendKeys("2");
				Select element5 =new Select (driver.findElement(By.name("CPYT_C_TENOR_TYPE")));
				element5.selectByVisibleText(prop.getProperty("dropdownmixpay"));
				eleForamtpercentage.sendKeys(prop.getProperty("amt%ofmixedpaymentDef"));
			
			}
				
				
			}
			if(text2.equals("Acceptance")) {
				driver.findElement(By.name("CPYT_I_TENOR_DAYS")).sendKeys("2");
				Select element5 =new Select (driver.findElement(By.name("CPYT_C_TENOR_TYPE")));
				element5.selectByVisibleText(prop.getProperty("dropdownmixpay"));
				eleForamtpercentage.sendKeys(prop.getProperty("amt%ofmixedpaymentAcc"));
			}
			else {
				System.out.println("nothing");
			
			}
			driver.findElement(By.id("PaymentTerms_SAVE")).click();
		}
		
		
		driver.switchTo().defaultContent();
		driver.switchTo().frame(1);
		driver.findElement(By.id("H")).click();
		driver.findElement(By.id("CHG_FLD_LOCAL_CUST_AC_NO")).sendKeys("564654654654");
		driver.switchTo().defaultContent();
		driver.switchTo().frame(0);
		driver.findElement(By.className("MuiBottomNavigationAction-label")).click();
		driver.findElement(By.name("cancel")).click();
		driver.switchTo().defaultContent();
		this.Supervisorrelease1();
	}
	public void Supervisorrelease1() throws InterruptedException {
//		--------------------------Release---------------------------------------------------------------

		WebElement findElement = driver.findElement(By.xpath("//span[text()='IPLC Maintenance']"));
		 Js=(JavascriptExecutor)driver;
		
		Js.executeScript("arguments[0].scrollIntoView();", findElement);
		Thread.sleep(8000);
		
		findElement.click();
		
		driver.findElement(By.xpath("//span[text()='Supervisor Release']")).click();
		driver.switchTo().frame(1);
		Thread.sleep(8000);
		driver.findElement(By.id("CatalogSwitch")).click();
		Thread.sleep(8000);
		driver.findElement(By.name("OP2")).sendKeys(text);
		Thread.sleep(8000);
		driver.findElement(By.xpath("//div[@id='FILTER_ETL_DIALOG']/div/div[3]/div/div[3]/button[2]/span[1]")).click();
		driver.findElement(By.id("transaction")).click();
		driver.switchTo().defaultContent();
		driver.switchTo().frame(0);
		driver.findElement(By.className("MuiBottomNavigationAction-label")).click();
		driver.findElement(By.name("cancel")).click();
		this.registerDoc();
	
//		--------------------------------------Release--------------------------------------------------------
	}
		public void registerDoc() throws InterruptedException {
			driver.switchTo().defaultContent();
			WebElement findElement = driver.findElement(By.xpath("//span[text()='IPLC Presentation']"));
			 Js=(JavascriptExecutor)driver;
			
			Js.executeScript("arguments[0].scrollIntoView();", findElement);
			Thread.sleep(8000);
			
			findElement.click();
			
			driver.findElement(By.xpath("//span[text()='Register Document LC']")).click();
			driver.switchTo().frame(1);
			Thread.sleep(8000);
			driver.findElement(By.id("CatalogSwitch")).click();
			Thread.sleep(8000);
			driver.findElement(By.name("OP2")).sendKeys(text);
			Thread.sleep(8000);
			driver.findElement(By.xpath("//div[@id='FILTER_ETL_DIALOG']/div/div[3]/div/div[3]/button[2]/span[1]")).click();
			driver.switchTo().defaultContent();
			driver.switchTo().frame(0);
			driver.findElement(By.name("next")).click();
			driver.switchTo().defaultContent();
			driver.switchTo().frame(1);
			WebElement presamt = driver.findElement(By.id("PRES_AMT"));
			presamt.click();
			presamt.sendKeys("500");
			driver.findElement(By.id("PRES_BK_REF")).sendKeys("654654");
			driver.findElement(By.id("C")).click();
			driver.findElement(By.id("DRAFT_1")).sendKeys("1");
			driver.findElement(By.id("DRAFT_2")).sendKeys("1");
			driver.switchTo().defaultContent();
			driver.switchTo().frame(0);
			driver.findElement(By.className("MuiBottomNavigationAction-label")).click();
			driver.findElement(By.name("cancel")).click();
			this.Supervisorrelease2();

		}
		
		public void Supervisorrelease2() throws InterruptedException {
//			--------------------------Release---------------------------------------------------------------
			driver.switchTo().defaultContent();
			WebElement findElement = driver.findElement(By.xpath("//span[text()='IPLC Maintenance']"));
			 Js=(JavascriptExecutor)driver;
			
			Js.executeScript("arguments[0].scrollIntoView();", findElement);
			Thread.sleep(8000);
			
			findElement.click();
			
			driver.findElement(By.xpath("//span[text()='Supervisor Release']")).click();
			driver.switchTo().frame(1);
			Thread.sleep(8000);
			driver.findElement(By.id("CatalogSwitch")).click();
			Thread.sleep(8000);
			driver.findElement(By.name("OP2")).sendKeys(text);
			Thread.sleep(8000);
			driver.findElement(By.xpath("//div[@id='FILTER_ETL_DIALOG']/div/div[3]/div/div[3]/button[2]/span[1]")).click();
			driver.findElement(By.id("transaction")).click();
			driver.switchTo().defaultContent();
			driver.switchTo().frame(0);
			driver.findElement(By.className("MuiBottomNavigationAction-label")).click();
			driver.findElement(By.name("cancel")).click();
			this.checkDoc();
		
//			--------------------------------------Release--------------------------------------------------------
			

		}
		public void checkDoc() throws InterruptedException {
			driver.switchTo().defaultContent();
			WebElement findElement = driver.findElement(By.xpath("//span[text()='IPLC Presentation']"));
			 Js=(JavascriptExecutor)driver;
			
			Js.executeScript("arguments[0].scrollIntoView();", findElement);
			Thread.sleep(8000);
			
			findElement.click();
			
			driver.findElement(By.xpath("//span[text()='Check Document']")).click();
			driver.switchTo().frame(1);
			Thread.sleep(8000);
			driver.findElement(By.id("CatalogSwitch")).click();
			Thread.sleep(8000);
			driver.findElement(By.name("OP2")).sendKeys(text);
			Thread.sleep(8000);
			driver.findElement(By.xpath("//div[@id='FILTER_ETL_DIALOG']/div/div[3]/div/div[3]/button[2]/span[1]")).click();
			driver.switchTo().defaultContent();
			driver.switchTo().frame(0);
			driver.findElement(By.name("next")).click();
			driver.switchTo().defaultContent();
			driver.switchTo().frame(1);
			Select dddocstate =new Select(driver.findElement(By.id("DOC_STAT")));
			dddocstate.selectByVisibleText(prop.getProperty("checkDoc"));
			 textforDescripancy = dddocstate.getFirstSelectedOption().getText();
			 if(textforDescripancy.equals("Discrepancy Found")) {
				 driver.findElement(By.id("DISC_DET")).sendKeys("Discrepancy Found");			
				 }
			driver.switchTo().defaultContent();
			driver.switchTo().frame(0);
			driver.findElement(By.className("MuiBottomNavigationAction-label")).click();
			driver.findElement(By.name("cancel")).click();
			
			
				
				
				
				
			
			this.Supervisorrelease3();
			
			
			

		}
		public void Supervisorrelease3() throws InterruptedException {
			driver.switchTo().defaultContent();
			WebElement findElement = driver.findElement(By.xpath("//span[text()='IPLC Maintenance']"));
			 Js=(JavascriptExecutor)driver;
			
			Js.executeScript("arguments[0].scrollIntoView();", findElement);
			Thread.sleep(8000);
			
			findElement.click();
			
			driver.findElement(By.xpath("//span[text()='Supervisor Release']")).click();
			driver.switchTo().frame(1);
			Thread.sleep(8000);
			driver.findElement(By.id("CatalogSwitch")).click();
			Thread.sleep(8000);
			driver.findElement(By.name("OP2")).sendKeys(text);
			Thread.sleep(8000);
			driver.findElement(By.xpath("//div[@id='FILTER_ETL_DIALOG']/div/div[3]/div/div[3]/button[2]/span[1]")).click();
			driver.findElement(By.id("transaction")).click();
			driver.switchTo().defaultContent();
			driver.switchTo().frame(0);
			driver.findElement(By.className("MuiBottomNavigationAction-label")).click();
			driver.findElement(By.name("cancel")).click();
			if(textforDescripancy.equals("Discrepancy Found")) {

				driver.switchTo().defaultContent();
				WebElement findElement1 = driver.findElement(By.xpath("//span[text()='IPLC Discrepancies']"));
				 Js=(JavascriptExecutor)driver;
				
				Js.executeScript("arguments[0].scrollIntoView();", findElement1);
				findElement1.click();
				Thread.sleep(8000);
				driver.findElement(By.xpath("//span[text()='Register Discrepancies']")).click();
				driver.switchTo().frame(1);
				Thread.sleep(8000);
				driver.findElement(By.id("CatalogSwitch")).click();
				Thread.sleep(8000);
				driver.findElement(By.name("OP2")).sendKeys(text);
				Thread.sleep(8000);
				driver.findElement(By.xpath("//div[@id='FILTER_ETL_DIALOG']/div/div[3]/div/div[3]/button[2]/span[1]")).click();
				driver.switchTo().defaultContent();
				driver.switchTo().frame(0);
				driver.findElement(By.name("next")).click();
				driver.switchTo().defaultContent();
				driver.switchTo().frame(1);
				driver.findElement(By.id("C")).click();
				Select dd3=new Select(driver.findElement(By.id("ADV_APPL_FLG")));
				dd3.selectByVisibleText("Yes");
				Select dd4=new Select(driver.findElement(By.id("REL_IN_TRUST_FLG")));
				dd4.selectByVisibleText("Yes");
				driver.switchTo().defaultContent();
				driver.switchTo().frame(0);
				driver.findElement(By.className("MuiBottomNavigationAction-label")).click();
				driver.findElement(By.name("cancel")).click();
				driver.switchTo().defaultContent();
				
				Thread.sleep(8000);
				driver.findElement(By.xpath("//span[text()='Discrepancy Response MT752']")).click();
				driver.switchTo().defaultContent();
				driver.switchTo().frame(1);
				Thread.sleep(8000);
				driver.findElement(By.id("CatalogSwitch")).click();
				Thread.sleep(8000);
				driver.findElement(By.name("OP2")).sendKeys(text);
				Thread.sleep(8000);
				driver.findElement(By.xpath("//div[@id='FILTER_ETL_DIALOG']/div/div[3]/div/div[3]/button[2]/span[1]")).click();
				driver.switchTo().defaultContent();
				driver.switchTo().frame(0);
				driver.findElement(By.name("next")).click();
				driver.switchTo().defaultContent();
				driver.switchTo().frame(1);
				Select dd5 =new Select(driver.findElement(By.id("APPL_RESP_DISC")));
				dd5.selectByVisibleText("Discrepancies Accepted");
				driver.switchTo().defaultContent();
				driver.switchTo().frame(0);
				driver.findElement(By.className("MuiBottomNavigationAction-label")).click();
				driver.findElement(By.name("cancel")).click();
			}
				
			this.payAccept();
		}
		public void payAccept() throws InterruptedException {
			driver.switchTo().defaultContent();
			WebElement findElement = driver.findElement(By.xpath("//span[text()='IPLC Settlement']"));
			 Js=(JavascriptExecutor)driver;
			
			Js.executeScript("arguments[0].scrollIntoView();", findElement);
			Thread.sleep(8000);
			
			findElement.click();
			
			driver.findElement(By.xpath("//span[text()='Pay/Accept']")).click();
			driver.switchTo().frame(1);
			Thread.sleep(8000);
			driver.findElement(By.id("CatalogSwitch")).click();
			Thread.sleep(8000);
			driver.findElement(By.name("OP2")).sendKeys(text);
			Thread.sleep(8000);
			driver.findElement(By.xpath("//div[@id='FILTER_ETL_DIALOG']/div/div[3]/div/div[3]/button[2]/span[1]")).click();
			driver.switchTo().defaultContent();
			driver.switchTo().frame(0);
			driver.findElement(By.name("next")).click();
			driver.switchTo().defaultContent();
			driver.switchTo().frame(1);
			driver.findElement(By.id("TENOR_START_DT")).sendKeys("2022-10-10");
			WebElement element = driver.findElement(By.id("STL_AMT"));
			element.click();
			element.sendKeys("300");
			Select dd1=new Select(driver.findElement(By.id("SEPARATE_CHG_FLG")));
			dd1.selectByVisibleText("No");
			 text2 = dd1.getFirstSelectedOption().getText();
			if(text2.equals("Yes")) {
				driver.findElement(By.id("G")).click();
				driver.findElement(By.id("CHG_FLD_LOCAL_CUST_AC_NO")).sendKeys("34156416516");
				
			}
			
			driver.findElement(By.id("C")).click();
			Select dd5 = new Select(driver.findElement(By.name("ACPT_MSG")));
			dd5.selectByVisibleText("Mail");
			driver.findElement(By.id("D")).click();
			driver.findElement(By.xpath("//input[@id='GridDO_Child_0_0']")).click();
			driver.findElement(By.id("PaymentInstrDeal_EDIT")).click();
			if(TEXT1.equals("By Payment")) {
			driver.findElement(By.id("do_PaymentDebitHeader_Tab")).click();
			driver.findElement(By.id("PaymentDebit_ADD")).click();
			driver.findElement(By.name("CPYT_DR_VAL_DATE")).sendKeys("2022-09-17");
			driver.findElement(By.name("CPYT_DR_AC")).sendKeys("48465465454");
			driver.findElement(By.id("PaymentDebit_SAVE")).click();
			driver.findElement(By.id("do_PaymentCreditHeader_Tab")).click();
			driver.findElement(By.id("PaymentCredit_ADD")).click();
			driver.findElement(By.name("CPYT_CR_VAL_DATE")).sendKeys("2022-09-23");
			driver.findElement(By.name("CPYT_ASSGN_ID")).sendKeys("6546465");
			driver.findElement(By.id("PaymentCredit_SAVE")).click();
			}
			
			else {
				System.out.println("nothing");
			}
			if(TEXT1.equals("By Acceptance")) {
			driver.findElement(By.name("CPYT_D_MAT_DATE")).sendKeys("2022-09-09");
			}
			else if(TEXT1.equals("By Negotiation")) {
				driver.findElement(By.name("CPYT_D_MAT_DATE")).sendKeys("2022-09-09");
				}
			else if(TEXT1.equals("By Def Payment ")) {
				driver.findElement(By.name("CPYT_D_MAT_DATE")).sendKeys("2022-09-09");
				}
			else if(TEXT1.equals("BY MIXED PYMT")) {
				driver.findElement(By.name("CPYT_D_MAT_DATE")).sendKeys("2022-09-09");
				if(text2.equals("Sight")){
					driver.findElement(By.id("do_PaymentDebitHeader_Tab")).click();
					driver.findElement(By.id("PaymentDebit_ADD")).click();
					driver.findElement(By.name("CPYT_DR_VAL_DATE")).sendKeys("2022-09-17");
					driver.findElement(By.name("CPYT_DR_AC")).sendKeys("48465465454");
					driver.findElement(By.id("PaymentDebit_SAVE")).click();
					driver.findElement(By.id("do_PaymentCreditHeader_Tab")).click();
					driver.findElement(By.id("PaymentCredit_ADD")).click();
					driver.findElement(By.name("CPYT_CR_VAL_DATE")).sendKeys("2022-09-23");
					driver.findElement(By.name("CPYT_ASSGN_ID")).sendKeys("6546465");
					driver.findElement(By.id("PaymentCredit_SAVE")).click();
					
				}
				else {
					System.out.println("nothing");
				}
			}
			else {
				System.out.println("nothing");
			}
			driver.findElement(By.id("PaymentInstrDeal_SAVE")).click();
			driver.switchTo().defaultContent();
			driver.switchTo().frame(0);
			driver.findElement(By.className("MuiBottomNavigationAction-label")).click();
			driver.findElement(By.name("cancel")).click();
			this.Supervisorrelease4();
		}
			
			
			public void Supervisorrelease4() throws InterruptedException {
//				--------------------------Release---------------------------------------------------------------
				driver.switchTo().defaultContent();
				WebElement findElement6 = driver.findElement(By.xpath("//span[text()='IPLC Maintenance']"));
				 Js=(JavascriptExecutor)driver;
				
				Js.executeScript("arguments[0].scrollIntoView();", findElement6);
				Thread.sleep(8000);
				
				findElement6.click();
				
				driver.findElement(By.xpath("//span[text()='Supervisor Release']")).click();
				driver.switchTo().frame(1);
				Thread.sleep(8000);
				driver.findElement(By.id("CatalogSwitch")).click();
				Thread.sleep(8000);
				driver.findElement(By.name("OP2")).sendKeys(text);
				Thread.sleep(8000);
				driver.findElement(By.xpath("//div[@id='FILTER_ETL_DIALOG']/div/div[3]/div/div[3]/button[2]/span[1]")).click();
				driver.findElement(By.id("transaction")).click();
				driver.switchTo().defaultContent();
				driver.switchTo().frame(0);
				driver.findElement(By.className("MuiBottomNavigationAction-label")).click();
				driver.findElement(By.name("cancel")).click();
				this.PayAtMat();
			
			}
//				--------------------------------------Release--------------------------------------------------------
			public void PayAtMat() throws InterruptedException {
				

			
			
			
				driver.switchTo().defaultContent();
				WebElement findElement0 = driver.findElement(By.xpath("//span[text()='IPLC Settlement']"));
				 Js=(JavascriptExecutor)driver;
				
				Js.executeScript("arguments[0].scrollIntoView();", findElement0);
				Thread.sleep(8000);
				
				findElement0.click();
				
				driver.findElement(By.xpath("//span[text()='Payment at Maturity']")).click();
				driver.switchTo().frame(1);
				Thread.sleep(8000);
				driver.findElement(By.id("CatalogSwitch")).click();
				Thread.sleep(8000);
				driver.findElement(By.name("OP2")).sendKeys(text);
				Thread.sleep(8000);
				driver.findElement(By.xpath("//div[@id='FILTER_ETL_DIALOG']/div/div[3]/div/div[3]/button[2]/span[1]")).click();
				driver.switchTo().defaultContent();
				driver.switchTo().frame(0);
				driver.findElement(By.name("next")).click();
				driver.switchTo().defaultContent();
				driver.switchTo().frame(1);
				WebElement amt1 = driver.findElement(By.id("STL_AMT"));
				amt1.click();
				amt1.sendKeys("150");
				Select dd2=new Select(driver.findElement(By.id("SEPARATE_CHG_FLG")));
				dd2.selectByVisibleText("No");
				String text3 = dd2.getFirstSelectedOption().getText();
				if(text3.equals("Yes")) {
					driver.findElement(By.id("G")).click();
					driver.findElement(By.id("CHG_FLD_LOCAL_CUST_AC_NO")).sendKeys("34156416516");
					
				}
//				driver.findElement(By.id("C")).click();
//				Select dd6 = new Select(driver.findElement(By.name("ACPT_MSG")));
//				dd6.selectByVisibleText("Mail");
				driver.findElement(By.id("D")).click();
//				driver.findElement(By.xpath("//input[@id='GridDO_Child_0_0']")).click();
//				driver.findElement(By.id("PaymentInstrDeal_EDIT")).click();
		
				driver.findElement(By.id("do_PaymentDebitHeader_Tab")).click();
				driver.findElement(By.id("PaymentDebit_ADD")).click();
				driver.findElement(By.name("CPYT_DR_VAL_DATE")).sendKeys("2022-09-17");
				driver.findElement(By.name("CPYT_DR_AC")).sendKeys("48465465454");
				driver.findElement(By.id("PaymentDebit_SAVE")).click();
				driver.findElement(By.id("do_PaymentCreditHeader_Tab")).click();
				driver.findElement(By.id("PaymentCredit_ADD")).click();
				driver.findElement(By.name("CPYT_CR_VAL_DATE")).sendKeys("2022-09-23");
				driver.findElement(By.name("CPYT_ASSGN_ID")).sendKeys("6546465");
				driver.findElement(By.id("PaymentCredit_SAVE")).click();
//				driver.findElement(By.id("PaymentInstrDeal_SAVE")).click();
				driver.switchTo().defaultContent();
				driver.switchTo().frame(0);
				driver.findElement(By.className("MuiBottomNavigationAction-label")).click();
				driver.findElement(By.name("cancel")).click();
				this.Supervisorrelease5();
			}
			public void Supervisorrelease5() throws InterruptedException {
//				--------------------------Release---------------------------------------------------------------
				driver.switchTo().defaultContent();
				WebElement findElement6 = driver.findElement(By.xpath("//span[text()='IPLC Maintenance']"));
				 Js=(JavascriptExecutor)driver;
				
				Js.executeScript("arguments[0].scrollIntoView();", findElement6);
				Thread.sleep(8000);
				
				findElement6.click();
				
				driver.findElement(By.xpath("//span[text()='Supervisor Release']")).click();
				driver.switchTo().frame(1);
				Thread.sleep(8000);
				driver.findElement(By.id("CatalogSwitch")).click();
				Thread.sleep(8000);
				driver.findElement(By.name("OP2")).sendKeys(text);
				Thread.sleep(8000);
				driver.findElement(By.xpath("//div[@id='FILTER_ETL_DIALOG']/div/div[3]/div/div[3]/button[2]/span[1]")).click();
				driver.findElement(By.id("transaction")).click();
				driver.switchTo().defaultContent();
				driver.switchTo().frame(0);
				driver.findElement(By.className("MuiBottomNavigationAction-label")).click();
				driver.findElement(By.name("cancel")).click();
			
			
			}
//				--------------------------------------Release-------------------------------------------------
			
				
				
				
			
			
			
			

		}
		
		
		
		
		
		
		
		

	
		
		
		
		

		
		
		
	


		
		

	

