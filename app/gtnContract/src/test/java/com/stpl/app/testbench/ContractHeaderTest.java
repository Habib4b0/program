package com.stpl.app.testbench;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.vaadin.testbench.By;
import com.vaadin.testbench.TestBenchTestCase;
import com.vaadin.testbench.elements.ButtonElement;
import com.vaadin.testbench.elements.NotificationElement;
import com.vaadin.testbench.elements.TextFieldElement;
import com.vaadin.testbench.elements.WindowElement;

public class ContractHeaderTest extends TestBenchTestCase {

	String contract="TestCon13022015";
	String editContract="EditTestCon13022015";
	public static String aContract="Test1Con13022015";
	
	@Before
	public void setUp() throws InterruptedException {
		
		setDriver(new FirefoxDriver());		
		getDriver().get("https://galderma-test.sysbiz.org/");
		driver.manage().window().maximize();
		findElement(By.id("_58_login")).sendKeys("admin");
		findElement(By.id("_58_password")).sendKeys("admin");
		findElement(By.className("btn-primary")).click();	
				
		findElement(By.xpath(".//span[contains(text(),'Contract')]")).click();		
		findElement(By.xpath(".//span[contains(text(),'Contract Header')]")).click();
	}
	
	@After
	public void closeBrowser() {
		driver.quit();
	}
		
	 @Test
	public void chTest1(){
		
		$(TextFieldElement.class).get(0).sendKeys("*");		
		$(ButtonElement.class).caption("SEARCH").first().click();		
		Assert.assertEquals("Search Completed", $(NotificationElement.class).first().getText());
		
	}
	
	 @Test
	public void chTest2(){
	
		$(ButtonElement.class).caption("SEARCH").first().click();		
		Assert.assertTrue("Please Enter Search Criteria", $(WindowElement.class).first().isDisplayed());
		
	}
	
}
