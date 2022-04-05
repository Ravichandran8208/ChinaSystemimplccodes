package chinaSystem;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

public class CheckDocument extends BsaeChinaSystem {
	@Test
	public void checking() throws InterruptedException {
		driver.findElement(By.name("IPLC Presentation")).click();
		driver.findElement(By.linkText("Check Document")).click();
		driver.switchTo().defaultContent();
		driver.switchTo().frame(4);
		Thread.sleep(500);

		driver.findElement(By.name("OP2")).sendKeys("IP001795BEDV");
		driver.switchTo().defaultContent();
		driver.switchTo().frame(1);
		driver.findElement(By.id("_next")).click();
		Thread.sleep(500);
		driver.findElement(By.id("_next")).click();
		Thread.sleep(500);
		driver.switchTo().defaultContent();
		driver.switchTo().frame(4);
		Select dd1 =new Select( driver.findElement(By.id("MAIL_CONT")));
		dd1.selectByIndex(1);
		//		alert.accept();
		//	
		//		Alert alert= driver.switchTo().alert();
		//		alert.accept();
		driver.switchTo().defaultContent();
		driver.switchTo().frame(1);
		driver.findElement(By.id("_confirm")).click();
		Thread.sleep(500);
		Alert alert= driver.switchTo().alert();
		alert.accept();
		driver.switchTo().defaultContent();
		driver.switchTo().frame(4);
		Thread.sleep(500);
		Select dd3 = new Select (driver.findElement(By.id("DOC_STAT")));
		dd3.selectByIndex(1);
		//		Thread.sleep(3000);
		driver.switchTo().defaultContent();
		driver.switchTo().frame(1);
		driver.findElement(By.id("_confirm")).click();
		Thread.sleep(500);
		driver.findElement(By.id("_cancel")).click();



	}

}
