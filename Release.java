package chinaSystem;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class Release extends BsaeChinaSystem {
	@Test
	public void mothodrelease() throws InterruptedException {
		
//		driver.switchTo().frame(3);
		driver.findElement(By.name("IPLC Maintenance")).click();
		driver.findElement(By.linkText("Supervisor Release")).click();
		driver.switchTo().defaultContent();
		driver.switchTo().frame(4);
		Thread.sleep(500);
		driver.findElement(By.name("OP2")).sendKeys("IP001726BEDV");
		driver.switchTo().defaultContent();
		driver.switchTo().frame(1);
		driver.findElement(By.id("_next")).click();
		driver.switchTo().defaultContent();
		driver.switchTo().frame(4);
		Thread.sleep(500);
		driver.findElement(By.name("transaction")).click();
		driver.switchTo().defaultContent();
		driver.switchTo().frame(1);
		driver.findElement(By.id("_confirm")).click();
		Thread.sleep(3000);
		driver.findElement(By.id("_cancel")).click();
		

	}

}
