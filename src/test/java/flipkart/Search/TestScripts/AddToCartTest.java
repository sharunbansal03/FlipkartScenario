package flipkart.Search.TestScripts;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AddToCartTest {
	
	//Test to search the product and add to the cart on Flipkart.com
	@Test
	public void addProductToCartTest() {
		
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

		// Step 3: Search for "winter heater"
		driver.findElement(By.name("q")).sendKeys("winter heater");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		Reporter.log("[Step]: Searched for 'winter heater'", true);

		// Step 4: Select First product and click
		WebElement firstProductLink = driver.findElement(By.xpath("//a[@class='s1Q9rs']"));
		String firstProductTitle = firstProductLink.getAttribute("title");
		firstProductLink.click();
		Reporter.log("[Info]: Selected Product title is: " + firstProductTitle, true);
		Reporter.log("[Step]: Clicked on first product in search results", true);

		// Step 5: Switch to product window and Click Add to Cart button
		Set<String> windowIDs = driver.getWindowHandles();
		for (String windowId : windowIDs) {
			driver.switchTo().window(windowId);

			if (driver.getTitle().contains(firstProductTitle)) {
				driver.findElement(By.xpath("//button[text()='Add to cart']")).click();
				Reporter.log("[Step]: Clicked on 'Add to Cart' button", true);
				break;
			}
		}

		// Step 6: Verify product in cart page
		Assert.assertTrue(driver.findElements(By.xpath("//a[text()='" + firstProductTitle + "']")).size() > 0,
				"[ASSERTION FAILED]: Unable to add product to cart");
		Reporter.log("[ASSERTION PASSED]: Product successfully added to cart", true);

		// Step 7: Close the browser
		driver.quit();
	}

}
