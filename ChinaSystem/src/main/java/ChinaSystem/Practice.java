package ChinaSystem;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Practice {
	@Test
	public void test() throws InterruptedException {
	
	WebDriverManager.chromedriver().setup();
	ChromeDriver driver = new ChromeDriver();
	
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
	
	driver.get("http://192.168.2.40:7001/EximBillWeb/");
	driver.findElement(By.id("C_BUSINESS_UNIT")).sendKeys("CSBANK");
	driver.findElement(By.id("C_USER_ID")).sendKeys("CSBANKOP");
	driver.findElement(By.id("C_PASSWORD")).sendKeys("1Q1Q1Q1Q");
	driver.findElement(By.className("MuiButton-label")).click();
   Thread.sleep(1000);
	driver.findElement(By.xpath("//span[text()='Import Letter of Credit']")).click();
	driver.findElement(By.xpath("//span[text()='IPLC Issuance']")).click();
	driver.findElement(By.xpath("//span[text()='Register Letter of Credit']")).click();

	//Thread.sleep(3000);
	driver.switchTo().frame(1);
	
	Select element =new Select(driver.findElement(By.id("APLB_RULE")));
	
	element.selectByVisibleText("OTHER");
	String TEXT = element.getFirstSelectedOption().getText();
	
	System.out.println(TEXT);


	
	if(TEXT.equals("OTHER")) {
		driver.findElement(By.id("APLB_RULE_NARR")).sendKeys("APLB_RULE_NARR");
	}
//	
	Select element1 =new Select(driver.findElement(By.id("AVAL_BY")));
	element1.selectByIndex(1);
	element1.selectByVisibleText("OTHER");
	String TEXT1 = element.getFirstSelectedOption().getText();

	
	
	
//	
	WebElement amt = driver.findElement(By.id("LC_AMT"));
	amt.click();
	amt.sendKeys("450000");


}
}
