package git.demo.start.class_test;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class flight {
	static WebDriver driver;
	static String flight_evening_fastest_company;
	static String flight_evening_fastest_no;

	public static void main(String[] args) throws InterruptedException, AWTException {
		System.setProperty("webdriver.chrome.driver","chromedriver.exe");
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.makemytrip.com/");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//input[@id='fromCity']")).sendKeys("Kolkata");
		driver.findElement(By.xpath("//input[@id='toCity']")).sendKeys("Bangalore");
Robot r=new Robot();
r.keyPress(KeyEvent.VK_TAB);
r.keyRelease(KeyEvent.VK_TAB);
r.keyPress(KeyEvent.VK_TAB);
r.keyRelease(KeyEvent.VK_TAB);
r.keyPress(KeyEvent.VK_TAB);
r.keyRelease(KeyEvent.VK_TAB);

		Thread.sleep(3000);
		WebElement button=driver.findElement(By.xpath("//a[text()='Search']"));
		
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", button);
		Thread.sleep(7000);
		driver.findElement(By.xpath("//span[text()='Duration']/parent::span")).click();
		Thread.sleep(3000);
		List<WebElement> lst=driver.findElements(By.xpath("//div[@class='dept-time']"));
		List<WebElement> lst1=driver.findElements(By.xpath("//span[@class='airways-name ']"));
		List<WebElement> lst2=driver.findElements(By.xpath("//p[@class='fli-code']"));
		List<WebElement> lst3=driver.findElements(By.xpath("//button[text()='View Fares']"));
		String date_flight=driver.findElement(By.xpath("//input[@id='departure']")).getAttribute("value");
		int temp=0;
		int counter=0;
		String s="";
		for(WebElement wb:lst)
		{
		 s=wb.getText();
			String[] s1=s.split(":");
			int i=Integer.parseInt(s1[0]);
			if(i>18 && i<20)
			{
				flight_evening_fastest_company=lst1.get(counter).getText();
				flight_evening_fastest_no=lst2.get(counter).getText();
				temp++;
				lst3.get(counter).click();
				Thread.sleep(3000);
				List<WebElement> lst4=driver.findElements(By.xpath("//button[text()='Hide Fares']/ancestor::div[@class='fli-list one-way']/div[3]/div/div[2]/div[2]/div[2]/button"));
			lst4.get(0).click();
			break;
			
			}
			
			counter++;
		}
		
		if(temp==0)
		{
			System.out.println("There are no fastest flight in evening from 6:00P.m to 8:00P.m for "+date_flight);
		}else
		{
			System.out.println("The fastest evening flight for "+date_flight+" is "+flight_evening_fastest_company+" with flight code "+flight_evening_fastest_no+" at "+s);
		}
	}

}
