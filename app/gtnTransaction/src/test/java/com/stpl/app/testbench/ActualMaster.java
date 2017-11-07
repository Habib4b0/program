package com.stpl.app.testbench;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.vaadin.testbench.By;
import com.vaadin.testbench.TestBenchTestCase;
import com.vaadin.testbench.elements.ButtonElement;
import com.vaadin.testbench.elements.LabelElement;
import com.vaadin.testbench.elements.NotificationElement;
import com.vaadin.testbench.elements.TabSheetElement;
import com.vaadin.testbench.elements.TextFieldElement;
import com.vaadin.testbench.elements.WindowElement;

public class ActualMaster extends TestBenchTestCase {

	@Before
	public void setUp() throws InterruptedException {

		setDriver(new FirefoxDriver());		
		getDriver().get("https://galderma-test.sysbiz.org/");
		driver.manage().window().maximize();
		findElement(By.id("_58_login")).sendKeys("admin");
		findElement(By.id("_58_password")).sendKeys("admin");
		findElement(By.className("btn-primary")).click();		
		
		 findElement(By.xpath(".//span[contains(text(),'Transaction')]")).click();
		
		findElement(By.xpath(".//span[contains(text(),'Actual Master')]")).click();
	}

	@Test
	public void testTM1() {

		$(TextFieldElement.class).get(0).sendKeys("*");
		$(ButtonElement.class).caption("Search").first().click();
		Assert.assertEquals("Search Completed", $(NotificationElement.class).first().getText());
				
	}

	@Test
	public void testTM2() {
		$(ButtonElement.class).caption("Search").first().click();
		Assert.assertEquals("Search Completed", $(NotificationElement.class)
				.first().getText());
		findElement(By.xpath("//div[@class='v-scrollable v-table-body-wrapper v-table-body']"))
		.findElement(By.tagName("tbody")).findElement(By.xpath(".//tr[1]")).isDisplayed();
	}

	@Test
	public void testTM3() {
		$(TextFieldElement.class).get(0).sendKeys("*");
		$(ButtonElement.class).caption("Search").first().click();
		Assert.assertEquals("Search Completed", $(NotificationElement.class).first().getText());	
		$(ButtonElement.class).caption("Reset").first().click();
		$(WindowElement.class).first().$(ButtonElement.class).caption("Yes")
	    .first().click();
		
	}

	@Test
	public void testTM4() {
		$(TextFieldElement.class).get(0).sendKeys("#%$#$%");
		$(ButtonElement.class).caption("Search").first().click();
		if (!$(NotificationElement.class).exists()) {
			Assert.assertTrue("Error Message is not Present",
					$(LabelElement.class).id("errorMsg").isDisplayed());
		}
		if ($(NotificationElement.class).exists()) {
			Assert.assertFalse("Special Character is Allowing during Search",
					$(NotificationElement.class).first().isDisplayed());
		}
	}

	@Test
	public void testTM5() {

		$(TextFieldElement.class).get(0).sendKeys("*");
		$(ButtonElement.class).caption("Search").first().click();
		Assert.assertEquals("Search Completed", $(NotificationElement.class)
				.first().getText());
		findElement(By.xpath("//div[@class='v-scrollable v-table-body-wrapper v-table-body']"))
		.findElement(By.tagName("tbody")).findElement(By.xpath(".//tr[1]")).click();
		$(TabSheetElement.class).first().openTab(0);
		$(TabSheetElement.class).first().openTab(1);
		$(ButtonElement.class).caption("BACK").first().click();

	}

	@After
	public void end() {
		driver.quit();
	}

}
