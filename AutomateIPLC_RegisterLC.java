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

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Utils.ReadData;
import io.github.bonigarcia.wdm.WebDriverManager;
import utils.DataLibrary;

public class AutomateIPLC_RegisterLC{
	@DataProvider(name = "fetchData")
	public Object[][] fetchData() throws IOException {
		return ReadData.readExcelData("data.xlsx");
	}	
	@Test(dataProvider = "fetchData")
	public void TEST(String CS_Unit,String CS_Bank,String Password,String ExpiryDate,String OfficerCode) throws InterruptedException, IOException {

		Properties prop=new Properties();
		
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		driver.get("http://192.168.2.40:7001/EximBillWeb/");
		driver.findElement(By.id("C_BUSINESS_UNIT")).sendKeys(CS_Unit);
		driver.findElement(By.id("C_USER_ID")).sendKeys(CS_Bank);
		driver.findElement(By.id("C_PASSWORD")).sendKeys(Password);
		driver.findElement(By.className("MuiButton-label")).click();
        Thread.sleep(1000);
		driver.findElement(By.xpath("//span[text()='Import Letter of Credit']")).click();
		driver.findElement(By.xpath("//span[text()='IPLC Issuance']")).click();
		driver.findElement(By.xpath("//span[text()='Register Letter of Credit']")).click();

		//Thread.sleep(3000);
		driver.switchTo().frame(1);
		Select element =new Select(driver.findElement(By.id("APLB_RULE")));
		element.selectByIndex(1);
		Select element1 =new Select(driver.findElement(By.id("AVAL_BY")));
		element1.selectByIndex(1);
		WebElement amt = driver.findElement(By.id("LC_AMT"));
		amt.click();

		amt.sendKeys("45000.00");
		Select element2 =new Select(driver.findElement(By.id("FORM_OF_LC")));
		element2.selectByIndex(0);
		driver.findElement(By.id("EXPIRY_DT")).sendKeys(ExpiryDate);
//		driver.findElement(By.id("EXPIRY_DT")).sendKeys("11.09.2022");
        driver.findElement(By.id("C")).click();
       
		driver.findElement(By.name("APPL_ID_BTN")).click();
//	    Thread.sleep(5000);

        String handle1 = driver.getWindowHandle();
//        List<String>List=new ArrayList<String>(handle1);
       
		driver.switchTo().window(handle1);
		driver.switchTo().frame(5);
		
		

//		WebDriverWait wait= new WebDriverWait(driver, 30);
		FileInputStream objFile=new FileInputStream(new File("D:/codes/ChinaSystem/data.properties"));
		prop.load(objFile);
		
		
	
		driver.findElement(By.linkText(prop.getProperty("custinfofromCUBK"))).click();
		Thread.sleep(3000);
		WebDriverWait wait= new WebDriverWait(driver, 10);
		driver.switchTo().frame(1);
		driver.findElement(By.name("BENE_ID_BTN")).click();
		 String handle2 = driver.getWindowHandle();

		driver.switchTo().window(handle2);
		driver.switchTo().frame(5);
		driver.findElement(By.linkText("C006582")).click();
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
		driver.switchTo().frame(0);
		driver.findElement(By.className("MuiBottomNavigationAction-label")).click();
		
		
		
		
	


	}

}
