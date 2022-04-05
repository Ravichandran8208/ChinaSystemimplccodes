package chinaSystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class IssueLOC extends BsaeChinaSystem{
	@Test

	public void issueloc() throws Exception {
		// TODO Auto-generated method stub

	
	driver.findElement(By.name("IPLC Issuance")).click();
	Thread.sleep(500);
	driver.findElement(By.linkText("Issue Letter of Credit")).click();
	driver.switchTo().defaultContent();
	driver.switchTo().frame(4);
	Thread.sleep(500);
	WebElement element = driver.findElement(By.name("OP2"));
	element.sendKeys("IP001795BEDV");
	driver.switchTo().defaultContent();
	driver.switchTo().frame(1);
	driver.findElement(By.id("_next")).click();
//	driver.switchTo().defaultContent();
//	driver.switchTo().frame(1);
	Thread.sleep(500);
	driver.findElement(By.id("_next")).click();
	Thread.sleep(500);
	driver.switchTo().defaultContent();
//	Alert alert = driver.switchTo().alert();
//	Thread.sleep(3000);
//	alert.accept();
	driver.switchTo().frame(4);
	Alert alert = driver.switchTo().alert();
//	Thread.sleep(3000);
	alert.accept();
	driver.findElement(By.id("D")).click();
	driver.findElement(By.id("DRW_ID_BTN")).click();
	alert.accept();
	Thread.sleep(3000);
	Set<String> set = driver.getWindowHandles();
	List<String>list=new ArrayList<String>(set);
	System.out.println(list);
	driver.switchTo().window(list.get(1));
	String title1 = driver.getTitle();
	System.out.println(title1);
	Thread.sleep(3000);
	driver.findElement(By.linkText("BDGLCH22XXX")).click();
	driver.switchTo().window(list.get(0));
	driver.switchTo().frame(4);
	driver.findElement(By.id("H")).click();
	driver.findElement(By.id("CHG_FLD_LOCAL_CUST_AC_NO")).sendKeys("1254845665456");
	driver.switchTo().defaultContent();
	driver.switchTo().frame(1);
	driver.findElement(By.id("_confirm")).click();
	Thread.sleep(3000);
	driver.findElement(By.id("_cancel")).click();
	

}
}
