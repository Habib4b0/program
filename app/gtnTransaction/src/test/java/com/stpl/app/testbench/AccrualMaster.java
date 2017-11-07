package com.stpl.app.testbench;

import com.vaadin.testbench.By;
import com.vaadin.testbench.TestBenchTestCase;
import com.vaadin.testbench.elements.ButtonElement;
import com.vaadin.testbench.elements.LabelElement;
import com.vaadin.testbench.elements.NotificationElement;
import com.vaadin.testbench.elements.TextFieldElement;
import com.vaadin.testbench.elements.WindowElement;


import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.firefox.FirefoxDriver;



public class AccrualMaster extends TestBenchTestCase{	

	@Before
	public void setUp() throws Exception {		
		setDriver(new FirefoxDriver());		
		getDriver().get("https://galderma-test.sysbiz.org/");
		driver.manage().window().maximize();
		findElement(By.id("_58_login")).sendKeys("admin");
		findElement(By.id("_58_password")).sendKeys("admin");
		findElement(By.className("btn-primary")).click();	
		findElement(By.xpath(".//span[contains(text(),' Transaction Mgmt')]")).click();		
		findElement(By.xpath(".//span[contains(text(),' Accrual Details')]")).click();
		
	}
	@Test
	public void AcrualMasterTest1() {
		
			$(TextFieldElement.class).get(0).sendKeys("*");
			$(ButtonElement.class).caption("Search").first().click();
			Assert.assertEquals("Search operation failed", "Search Completed",$(NotificationElement.class).first().getText());
	}
	
	@Test
	public void ActualMasterTest2() {
		$(ButtonElement.class).caption("Search").first().click();
		Assert.assertEquals("Search operation failed", "Search Completed",$(NotificationElement.class).first().getText());
	}
	
	@Test
	public void AcrualMasterTest3() {
		$(TextFieldElement.class).get(0).sendKeys("#%$#$%");
		$(ButtonElement.class).caption("Search").first().click();
		if (!$(NotificationElement.class).first().isDisplayed()) {
			Assert.assertTrue("Error Message is not Present",
					$(LabelElement.class).id("errorMsg").isDisplayed());

		}
			Assert.assertFalse("Special Characters are allowed during Search",
				$(NotificationElement.class).first().isDisplayed());

	}
	@Test
	public void AcrualMasterTest4() throws Exception {
		$(ButtonElement.class).caption("Search").first().click();
		Assert.assertEquals("Search operation failed", "Search Completed",$(NotificationElement.class).first().getText());
		findElement(By.xpath("//div[@class='v-scrollable v-table-body-wrapper v-table-body']"))
		.findElement(By.tagName("tbody")).findElement(By.xpath(".//tr[1]")).click();
		$(ButtonElement.class).caption("Back").first().click();
		
	}
	
	
	@Test
	public void AcrualMasterTest5() {
		$(TextFieldElement.class).get(0).sendKeys("*");
		$(ButtonElement.class).caption("Search").first().click();
		Assert.assertEquals("Search Completed", $(NotificationElement.class).first().getText());	
		$(ButtonElement.class).caption("Reset").first().click();
		$(WindowElement.class).first().$(ButtonElement.class).caption("Yes")
	    .first().click();
	}
	@After
	public void closeMethod() {
		getDriver().quit();
	}
}
