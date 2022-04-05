package chinaSystem;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class Release2 extends BsaeChinaSystem {
	@Test
	public void releasemethod() throws InterruptedException {
		// TODO Auto-generated method stub

	
	driver.findElement(By.name("IPLC Maintenance")).click();
	driver.findElement(By.linkText("Supervisor Release")).click();
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
	driver.switchTo().defaultContent();
	driver.switchTo().frame(4);
	
	driver.findElement(By.name("transaction")).click();
	Alert alert= driver.switchTo().alert();
	alert.accept();
	driver.switchTo().defaultContent();
	driver.switchTo().frame(1);
//	Alert alert= driver.switchTo().alert();
//	alert.accept();
	Thread.sleep(500);
	driver.findElement(By.id("_confirm")).click();
	Thread.sleep(3000);
	driver.findElement(By.id("_cancel")).click();
	

}


}
