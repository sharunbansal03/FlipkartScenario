package flipkart.Search.TestScripts;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Reporter;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class PrintProductsWithMatchingMemoryTest {

	// This test will capture all the redmi phones whose ram is 4GB
	@Test
	public void printPhonesWithRamTest() {
		// Step 1: Launch Browser, maximize and set implicit wait for all elements
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		Reporter.log("[Step]: Launched Chrome Browser", true);

		// Step 2: Navigate to Flipkart.com
		driver.get("https://www.flipkart.com/");
		Reporter.log("[Step]: Navigated to flipkart.com", true);

		// Step 3: Handle and close 'Login' pop-up
		driver.findElement(By.xpath("//button[text()='✕']")).click();
		Reporter.log("[Step]: Closed login pop-up", true);

		// Step 4: Search for 'redMi"
		driver.findElement(By.name("q")).sendKeys("redMi");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		Reporter.log("[Step]: Searched for 'redMi'", true);

		// Step 5: Capture phones whose Ram is 4 GB
		List<WebElement> phonesWith4GBRamTitles = driver.findElements(By
				.xpath("//li[starts-with(text(),'4 GB RAM')]/ancestor::div/preceding-sibling::div[@class='_4rR01T']"));

		// Step 6: Print in console
		System.out.println(" ****** List of redMi phones with 4 GB RAM  ****** ");
		for (WebElement phoneName : phonesWith4GBRamTitles) {
			Reporter.log(phoneName.getText(), true);
		}

		// Step 7: Close the browser
		driver.close();
	}

}
