package com.stpl.app.testbench;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.vaadin.testbench.By;
import com.vaadin.testbench.TestBenchTestCase;
import com.vaadin.testbench.elements.ButtonElement;
import com.vaadin.testbench.elements.TextFieldElement;
import com.vaadin.testbench.elements.WindowElement;

public class ContractDashboardTest extends TestBenchTestCase {
	
	String contract="CON1230201409";
	String testTreeContract=ContractHeaderTest.aContract;

	
	@Test
	public void cdbTest5(){				
		$(ButtonElement.class).caption("Search").get(0).click();
		Assert.assertTrue("Notification Window is present",$(WindowElement.class).exists());		
		$(WindowElement.class).first().$(ButtonElement.class).caption("Ok")
		.first().click();	
	}
        
        	@Test
	public void cdbTest6(){		
            
            $(TextFieldElement.class).get(1).setValue("*");
		$(ButtonElement.class).caption("Search").get(0).click();
	findElement(By.xpath("//div[@class='v-scrollable v-table-body-wrapper v-table-body']"))
		.findElement(By.tagName("tbody")).findElement(By.xpath(".//tr[1]")).isDisplayed();;	
	}
	
	@Before
	public void setUp() throws InterruptedException {
		
		setDriver(new FirefoxDriver());		
		getDriver().get("https://galderma-test.sysbiz.org/");
		driver.manage().window().maximize();
		findElement(By.id("_58_login")).sendKeys("admin");
		findElement(By.id("_58_password")).sendKeys("admin");
		findElement(By.className("btn-primary")).click();	
		
		findElement(By.xpath(".//span[contains(text(),'Contract')]")).click();
		
		findElement(By.xpath(".//span[contains(text(),'Contract Dashboard')]")).click();
	}
	
	@After
	public void closeBrowser() {
	}
	
	
	
}
