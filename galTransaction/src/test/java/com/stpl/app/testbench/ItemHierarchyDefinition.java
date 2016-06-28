package com.stpl.app.testbench;




import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.vaadin.testbench.TestBenchTestCase;
import com.vaadin.testbench.elements.ButtonElement;
import com.vaadin.testbench.elements.LabelElement;
import com.vaadin.testbench.elements.NotificationElement;
import com.vaadin.testbench.elements.TextFieldElement;
import com.vaadin.testbench.elements.WindowElement;

public class ItemHierarchyDefinition extends TestBenchTestCase{
	
	@Before
	public void setUp() throws Exception {		
		setDriver(new FirefoxDriver());		
		getDriver().get("https://galderma-test.sysbiz.org/");
		driver.manage().window().maximize();
		findElement(By.id("_58_login")).sendKeys("admin");
		findElement(By.id("_58_password")).sendKeys("admin");
		findElement(By.className("btn-primary")).click();		
		
		findElement(By.xpath(".//span[contains(text(),' Transaction Mgmt')]")).click();		
		findElement(By.xpath(".//span[contains(text(),' Item Hierarchy Definition')]")).click();
		
	}
	@Test
	public void itemHierarchyDefinition1() {
		
			$(TextFieldElement.class).get(0).sendKeys("*");
			$(ButtonElement.class).caption("Search").first().click();
			Assert.assertEquals("Search operation failed", "Search Completed",$(NotificationElement.class).first().getText());
	}
	
	@Test
	public void itemHierarchyDefinition2() {
		$(ButtonElement.class).caption("Search").first().click();
		Assert.assertEquals("Search operation failed", "Search Completed",$(NotificationElement.class).first().getText());
		findElement(By.xpath("//div[@class='v-scrollable v-table-body-wrapper v-table-body']"))
		.findElement(By.tagName("tbody")).findElement(By.xpath(".//tr[1]")).isDisplayed();
		
	}
	@Test
	public void itemHierarchyDefinition3() {
		$(TextFieldElement.class).get(0).sendKeys("#%$#$%");
		$(ButtonElement.class).caption("Search").first().click();
			Assert.assertTrue("Special Characters are allowed during Search",
					$(LabelElement.class).id("errorMsg").isDisplayed());

	}


	@Test
	public void itemHierarchyDefinition5() {
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
