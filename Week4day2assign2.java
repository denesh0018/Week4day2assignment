

package week4day2Assignment;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Week4day2assign2 {

	public static void main(String[] args) throws InterruptedException {

		//Setup Chrome Driver
		WebDriverManager.chromedriver().setup();

		//To disable the unwanted popups
		ChromeOptions opt=new ChromeOptions();
		opt.addArguments("--disable-notifications");


		//open the browser
		ChromeDriver driver= new ChromeDriver(opt);

		//Load the URL//1)Go to https://www.nykaa.com/
		driver.get("https://www.nykaa.com/");

		Thread.sleep(1000);
		//Maximime the window screen

		driver.manage().window().maximize();

		//Implicit Wait
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1000));


		//2) Mouseover on Brands and Search L'Oreal Paris
		WebElement mouse = driver.findElement(By.id("brand_arrowUp"));
		WebElement text = driver.findElement(By.id("brandSearchBox"));
		Actions act=new Actions(driver);
		act.moveToElement(mouse).pause(100).perform();
		text.sendKeys("L'Oreal Paris");
		text.sendKeys(Keys.TAB);


		//3) Click L'Oreal Paris
		driver.findElement(By.xpath("//div[@id='list_L']/following::div/a[contains(text(),\"L'Oreal Paris\")]")).click();
		//4) Check the title contains L'Oreal Paris(Hint-GetTitle)
		String title = driver.getTitle();
		if(title.contains("L'Oreal Paris"))
		{
			System.out.println("Title contains L'Oreal Paris");
		}
		else
		{
			System.out.println("Title Not contains L'Oreal Paris");
		}
		//5) Click sort By and select customer top rated
		
		driver.findElement(By.xpath("//span[contains(text(),'Sort By : popularity')]")).click();
		driver.findElement(By.xpath("//label[@for='radio_customer top rated_undefined']/div[@class='control-indicator radio ']")).click();
		//6) Click Category and click Hair->Click haircare->Shampoo
		driver.findElement(By.xpath("//span[contains(text(),'Category')]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//span[text()='Hair']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//span[text()='Hair Care']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//span[text()='Shampoo']")).click();
		//7) Click->Concern->Color Protection
		driver.findElement(By.xpath("//span[text()='Concern']")).click();
		driver.findElement(By.xpath("//span[text()='Color Protection']")).click();

		//8)check whether the Filter is applied with Shampoo
		String text2 = driver.findElement(By.xpath("//span[text()='Filters Applied']/parent::div/following-sibling::div")).getText();
		System.out.println(text2);
		if(text2.contains("Shampoo"))
		{
			System.out.println("Filter contians shampoo in it");
		}
		else
		{
			System.out.println("Filter contians no shampoo in it");
		}
		
		Thread.sleep(1000);
		//9) Click on L'Oreal Paris Colour Protect Shampoo
		driver.findElement(By.xpath("//div[text()=\"L'Oreal Paris Colour Protect Shampoo\"]")).click();
		//10) GO to the new window and select size as 175ml
		//To move to next window open
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> window1= new ArrayList<String>(windowHandles);
		driver.switchTo().window(window1.get(1));
		// For dropdown
		WebElement findElement = driver.findElement(By.xpath("//select[@title='SIZE']"));
		Select dd=new Select(findElement);
		dd.selectByVisibleText("175ml");
		
		Thread.sleep(1000);
		//11) Print the MRP of the product
		String text3 = driver.findElement(By.xpath("//div[text()='inclusive of all taxes']/preceding-sibling::div/following-sibling::div/div/span[2]")).getText();
		System.out.println(text3);
		//12) Click on ADD to BAG
		driver.findElement(By.xpath("(//span[text()='Add to Bag'])[1]")).click();
		
		//13) Go to Shopping Bag 
		driver.findElement(By.xpath("(//span[text()='Account']/following::button)[1]")).click();
		
		
		//14) Print the Grand Total amount		
		driver.switchTo().frame(driver.findElement(By.className("css-acpm4k")));
	
		Thread.sleep(1000);		
	String text4 = driver.findElement(By.xpath("//div[@class='name medium-strong']/following-sibling::div")).getText();
	System.out.println(text4);
		//15) Click Proceed
		driver.findElement(By.xpath("//span[text()='Proceed']")).click();
		//16) Click on Continue as Guest
		driver.findElement(By.xpath("//button[text()='CONTINUE AS GUEST']")).click();
		//17) Check if this grand total is the same in step 14
		String text5 = driver.findElement(By.xpath("//div[@class='payment-details-tbl grand-total-cell prl20']")).getText();
		System.out.println(text5);
		if (text5.contains(text4))
		{
			System.out.println("Both values are same");
		}
		else
			{
			System.out.println("Both values are different");
		}
		//18) Close all windows 
		driver.quit();


	}

}
