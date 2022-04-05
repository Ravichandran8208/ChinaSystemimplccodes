package chinaSystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import io.cucumber.messages.types.Duration;

public class Presentationiplc extends BsaeChinaSystem {
	@Test
 public void presentatoin() throws InterruptedException {
	driver.findElement(By.name("IPLC Presentation")).click();
	driver.findElement(By.linkText("Register Document LC")).click();
	driver.switchTo().defaultContent();
	driver.switchTo().frame(4);
	Thread.sleep(500);
	WebElement element = driver.findElement(By.name("OP2"));
	element.sendKeys("IP001795BEDV");
//	String text = element.getText();
//	System.out.println(text);
	driver.switchTo().defaultContent();
	driver.switchTo().frame(1);
	driver.findElement(By.id("_next")).click();
	Thread.sleep(500);
	driver.findElement(By.id("_next")).click();
	driver.switchTo().defaultContent();
	driver.switchTo().frame(4);
//	WebElement element2 = driver.findElement(By.id("PRES_AMT"));
//	element2.click();
//	element2.sendKeys("450000");
	
	Thread.sleep(300);
//	driver.findElement(By.id("PRES_DT")).sendKeys("2022-04-04");
//	Alert alert= driver.switchTo().alert();
//	alert.accept();
	
////	driver.switchTo().defaultContent();
//	driver.switchTo().frame(4);
	WebElement element3 = driver.findElement(By.id("PRES_BK_REF"));
//	WebDriverWait t= new WebDriverWait(driver, 20);
//	t.until(ExpectedConditions.elementToBeClickable(element3));
	element3	.sendKeys("78515648546");
	WebElement element2 = driver.findElement(By.id("PRES_AMT"));
	element2.click();
	element2.sendKeys("450000");
	
	
	driver.findElement(By.id("B")).click();
	Alert alert= driver.switchTo().alert();
	alert.accept();
	Select dd1 =new Select (driver.findElement(By.id("DOC_PRES_BY")));
	dd1.selectByIndex(2);
	
//	Thread.sleep(3000);
//	Set<String> set = driver.getWindowHandles();
//	List<String>list=new ArrayList<String>(set);
//	System.out.println(list);
//	driver.switchTo().window(list.get(1));
//	String title1 = driver.getTitle();
//	System.out.println(title1);
//	Thread.sleep(3000);
//	driver.findElement(By.linkText("BK000042")).click();
//	driver.switchTo().window(list.get(0));
//	driver.switchTo().frame(4);
	driver.findElement(By.id("C")).click();
	driver.findElement(By.id("DRAFT_1")).sendKeys("1");
	driver.findElement(By.id("DRAFT_2")).sendKeys("1");
	driver.switchTo().defaultContent();
	driver.switchTo().frame(1);
	driver.findElement(By.id("_confirm")).click();
	Thread.sleep(3000);
	driver.findElement(By.id("_cancel")).click();
	

}
}
