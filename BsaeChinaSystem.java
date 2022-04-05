package chinaSystem;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BsaeChinaSystem {
	static ChromeDriver driver;
	@BeforeMethod
	public void baseclass() {
		String Acttitle="China SystemsEximbills Enterprise";
		WebDriverManager.chromedriver().setup();
		 driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://192.168.2.120:9087/EximBillWeb/SYS_index.htm");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.findElement(By.name("C_BUSINESS_UNIT")).sendKeys("CSBANK");
		driver.findElement(By.name("C_USER_ID")).sendKeys("CSBANKOP");
		driver.findElement(By.id("tipLogPwd")).sendKeys("1Q1Q1Q1Q");
		driver.findElement(By.id("Image1")).click();
		driver.switchTo().frame(3);
		driver.findElement(By.name("Import Letter of Credit")).click();

	} 

}
