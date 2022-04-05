package chinaSystem;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Automateiplc extends BsaeChinaSystem {
	
	@Test
	public void flow() throws InterruptedException {
		
		
		driver.findElement(By.name("IPLC Issuance")).click();
		Thread.sleep(500);
		driver.findElement(By.linkText("Register Letter of Credit")).click();
		driver.switchTo().defaultContent();
		driver.switchTo().frame(4);
		Thread.sleep(500);
		Select dd1 =new Select( driver.findElement(By.id("AVAL_BY")));
		dd1.selectByValue("BY ACCEPTANCE");
		WebElement ele = driver.findElement(By.id("LC_AMT"));
		ele.click();

		ele.sendKeys("4500000");
		Thread.sleep(3000);

		//		driver.findElement(By.id("LC_BAL")).sendKeys("4500000");
		//		driver.findElement(By.id("LOCAL_AMT")).sendKeys("4500000");
		driver.findElement(By.id("EXPIRY_DT")).sendKeys("2022-06-09");
		Select dd2 =new Select( driver.findElement(By.id("TENOR_TYPE")));
		dd2.selectByValue("DAYS AFTER SIGHT");
		Select dd3 =new Select( driver.findElement(By.id("AMT_SPEC")));
		dd3.selectByIndex(0);
		driver.switchTo().defaultContent();
		driver.switchTo().frame(4);
		//		driver.findElement(By.id("B")).click();
		driver.findElement(By.id("C")).click();
//		String title = driver.getTitle();
//		System.out.println(title);
		driver.findElement(By.name("APPL_ID_BTN")).click();
		Alert alert = driver.switchTo().alert();
		alert.accept();
		Thread.sleep(3000);
		Set<String> set = driver.getWindowHandles();
		List<String>list=new ArrayList<String>(set);
		System.out.println(list);
		driver.switchTo().window(list.get(1));
		String title1 = driver.getTitle();
		System.out.println(title1);
		Thread.sleep(3000);
		driver.findElement(By.linkText("C000495")).click();
		driver.switchTo().window(list.get(0));
		driver.switchTo().frame(4);
		driver.findElement(By.id("APPL_REF")).sendKeys("456987232");
		Thread.sleep(3000);
		driver.findElement(By.id("APPL_EMAIL")).sendKeys("rak@gmail.com");
		driver.findElement(By.id("AC_OFFICER_CODE")).sendKeys("245789632");
		Select dd4 =new Select( driver.findElement(By.id("SAME_AS_APPL_FLG")));
		dd4.selectByIndex(1);
		driver.findElement(By.name("ADV_BK_ID_BTN")).click();
		Thread.sleep(3000);
//		Alert alert2 = driver.switchTo().alert();
		alert.accept();
		
		String title2 = driver.getTitle();
		System.out.println(title2);
		Thread.sleep(3000);
		Set<String> set1 = driver.getWindowHandles();
		List<String>list1=new ArrayList<String>(set1);
		System.out.println(list1.size());
		driver.switchTo().window(list1.get(1));
		if(list1.equals(list)) {
			System.out.println("both windows are in same ");
		}
		else
			System.out.println("different windows");

		
		driver.findElement(By.linkText("BK000042")).click();
		driver.switchTo().window(list1.get(0));
		driver.switchTo().frame(4);
		Thread.sleep(3000);
		driver.findElement(By.name("ADV_THRU_BK_ID_BTN")).click();
		alert.accept();
		Thread.sleep(3000);
		Set<String> set2 = driver.getWindowHandles();
		List<String>list2=new ArrayList<String>(set2);
		System.out.println(list2.size());
		driver.switchTo().window(list2.get(1));
		driver.findElement(By.linkText("BK000044")).click();
		driver.switchTo().window(list1.get(0));
		driver.switchTo().frame(4);
		Thread.sleep(300);
		driver.findElement(By.id("BENE_EMAIL")).sendKeys("rak@gmail.com");
		Thread.sleep(3000);
		driver.findElement(By.name("BENE_ID_BTN")).click();
		alert.accept();
		Thread.sleep(3000);
		Set<String> set3 = driver.getWindowHandles();
		List<String>list3=new ArrayList<String>(set3);
		System.out.println(list3.size());
		driver.switchTo().window(list3.get(1));
		driver.findElement(By.linkText("C000018")).click();
		driver.switchTo().window(list1.get(0));
		driver.switchTo().frame(4);
		driver.findElement(By.id("B")).click();
		driver.findElement(By.name("ASSET_ACNO_BTN")).click();
		Thread.sleep(3000);
		Set<String> set4 = driver.getWindowHandles();
		List<String>list4=new ArrayList<String>(set4);
		System.out.println(list4.size());
		driver.switchTo().window(list4.get(1));
		driver.findElement(By.linkText("BANK12345")).click();
		driver.switchTo().window(list1.get(0));
		driver.switchTo().frame(4);
		driver.findElement(By.name("APPL_AC_MRGN_BTN")).click();
		Thread.sleep(3000);
		Set<String> set5 = driver.getWindowHandles();
		List<String>list5=new ArrayList<String>(set5);
		System.out.println(list5.size());
		driver.switchTo().window(list5.get(1));
		driver.findElement(By.linkText("CUST54321")).click();
		driver.switchTo().window(list1.get(0));
		driver.switchTo().frame(4);
		driver.findElement(By.id("F")).click();
		WebElement findElement = driver.findElement(By.id("CHG_LOCAL_CUST_PAY_RATE"));
		findElement.click();
		findElement.sendKeys("84");
		WebElement findElement2= driver.findElement(By.id("CHG_FOREIGN_CUST_PAY_RATE"));
		findElement2.click();
		findElement2.sendKeys("26");
		String text = driver.findElement(By.id("C_MAIN_REF")).getText();
		System.out.println(text);
		driver.switchTo().defaultContent();
		driver.switchTo().frame(1);
		driver.findElement(By.id("_confirm")).click();
		alert.accept();
		driver.switchTo().defaultContent();
		driver.switchTo().frame(4);
		driver.findElement(By.id("BENE_EMAIL")).sendKeys("rak@gmail.com");
		driver.switchTo().defaultContent();
		driver.switchTo().frame(1);
		driver.findElement(By.id("_confirm")).click();
		Thread.sleep(3000);
		driver.findElement(By.id("_cancel")).click();
		
		
	}





}
