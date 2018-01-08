package com.stpl.app.testbench;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.vaadin.testbench.By;
import com.vaadin.testbench.TestBenchTestCase;
import com.vaadin.testbench.elements.ButtonElement;
import com.vaadin.testbench.elements.ComboBoxElement;
import com.vaadin.testbench.elements.LabelElement;
import com.vaadin.testbench.elements.NotificationElement;
import com.vaadin.testbench.elements.TabSheetElement;
import com.vaadin.testbench.elements.TextAreaElement;
import com.vaadin.testbench.elements.TextFieldElement;
import com.vaadin.testbench.elements.WindowElement;

public class RebatePlanTest extends TestBenchTestCase {
	
	String rpName="TestRP120220156";
	String rpEditName="EditTestRP12022015";
	
	
	// Search
	@Test
	public void testRebatePlan1() {

		$(TextFieldElement.class).get(0).sendKeys("*");
		$(ButtonElement.class).caption("SEARCH").first().click();		
		Assert.assertEquals("Search Completed", $(NotificationElement.class).first().getText());

	}
	
	@Test
	public void testRebatePlan2() {

		$(ButtonElement.class).caption("SEARCH").first().click();
		Assert.assertTrue("Please enter search criteria",$(WindowElement.class).exists());
		$(WindowElement.class).first().$(ButtonElement.class).first().click();	
	}
	
	
	// Add
	@Test
	public void testRebatePlan3() {		
		
		$(ButtonElement.class).caption("ADD").first().click();
		Assert.assertTrue("Add Button fails", $(TabSheetElement.class).exists());
		
		// Tab 1
		$(TabSheetElement.class).first().openTab(0);
		$(TextFieldElement.class).get(3).sendKeys(rpName);
		$(TextFieldElement.class).get(4).sendKeys(rpName);
		String rpNo = $(TextFieldElement.class).get(3).getValue();
		$(TextFieldElement.class).get(5).sendKeys(rpName);
		String rpName = $(TextFieldElement.class).get(5).getValue();
		$(ComboBoxElement.class).get(0).selectByText("Active");
		$(ComboBoxElement.class).get(1).selectByText("Managed Care");		

		// Tab 2
		$(TabSheetElement.class).first().openTab(1);
		$(ComboBoxElement.class).get(0).selectByText("Wac");
		$(ComboBoxElement.class).get(1).selectByText("Tier");
		$(ComboBoxElement.class).get(2).selectByText("FC");
		$(TextFieldElement.class).get(3).sendKeys("123456");
		$(TextFieldElement.class).get(4).sendKeys("20");
		$(TextFieldElement.class).get(5).sendKeys("5");
		$(TextFieldElement.class).get(6).sendKeys("30");
		$(TextFieldElement.class).get(7).sendKeys("80");
		$(TextFieldElement.class).get(8).sendKeys("90");
		$(TextFieldElement.class).get(9).sendKeys("100");	
		$(ComboBoxElement.class).get(6).selectByText("$");
		$(ButtonElement.class).caption("Add").first().click();		

		// Tab 3
		$(TabSheetElement.class).first().openTab(2);		
		$(TextAreaElement.class).first().setValue("Data entered in TextArea during Testbench Test");
		$(ButtonElement.class).caption("Save").first().click();
		$(WindowElement.class).first().$(ButtonElement.class).caption("Yes")
		.first().click();
		
		Assert.assertEquals(rpNo+","+rpName +" has been successfully saved"
				, $(NotificationElement.class).first().getText());
	}
	
	@Test
	public void testRebatePlan4() {		
	
		$(ButtonElement.class).caption("ADD").first().click();
		Assert.assertTrue("Add Button fails", $(TabSheetElement.class).exists());
		$(TabSheetElement.class).first().openTab(0);
		$(ButtonElement.class).caption("Save").first().click();	
		Assert.assertTrue("Error Message is not displayed", $(LabelElement.class).id("ErrorMessage").isDisplayed());
	
	}

	// Edit
	@Test
	public void testRebatePlan5() {		
		
		$(TextFieldElement.class).get(0).sendKeys(rpName);
		$(ButtonElement.class).caption("SEARCH").first().click();		
		Assert.assertEquals("Search Completed", $(NotificationElement.class).first().getText());	
		$(NotificationElement.class).first().close();
		findElement(By.xpath("//div[@class='v-scrollable v-table-body-wrapper v-table-body']"))
        .findElement(By.tagName("tbody")).findElement(By.xpath(".//tr[1]")).click();		
		$(ButtonElement.class).caption("EDIT").first().click();
		
		// Tab 1
		$(TabSheetElement.class).first().openTab(0);
		$(TextFieldElement.class).get(3).clear();
		$(TextFieldElement.class).get(4).clear();
		$(TextFieldElement.class).get(5).clear();
		$(TextFieldElement.class).get(3).sendKeys(rpEditName);
		String rpNo=$(TextFieldElement.class).get(3).getValue();
		$(TextFieldElement.class).get(4).sendKeys(rpEditName);
		$(TextFieldElement.class).get(5).sendKeys(rpEditName);	
		String rpName=$(TextFieldElement.class).get(5).getValue();
		$(ComboBoxElement.class).get(1).selectByText("Medicaid");		

		// Tab 2		
		$(TabSheetElement.class).first().openTab(1);
		findElement(By.xpath("//div[@class='v-scrollable v-table-body-wrapper v-table-body']"))
		.findElement(By.tagName("tbody")).findElement(By.xpath(".//tr[1]")).click();
		$(ButtonElement.class).caption("Remove").first().click();
		$(WindowElement.class).first().$(ButtonElement.class).first().click();
		$(ComboBoxElement.class).get(0).selectByText("WAC");
		$(ComboBoxElement.class).get(1).selectByText("Tier");
		$(TextFieldElement.class).get(3).sendKeys("5768");
		$(TextFieldElement.class).get(5).sendKeys("10");
		$(TextFieldElement.class).get(6).sendKeys("10");
		$(TextFieldElement.class).get(7).clear();
		$(TextFieldElement.class).get(7).sendKeys("77");
		$(TextFieldElement.class).get(8).sendKeys("255");
		$(TextFieldElement.class).get(9).sendKeys("256");	
		$(ComboBoxElement.class).get(6).selectByText("$");
		$(ButtonElement.class).caption("Add").first().click();		

		// Tab 3
		$(TabSheetElement.class).first().openTab(2);		
		$(TextAreaElement.class).first().setValue("Data entered in TextArea during Testbench Test");
		$(ButtonElement.class).caption("Update").first().click();	
		$(WindowElement.class).first().$(ButtonElement.class).caption("Yes")
	    .first().click();
		Assert.assertEquals(rpNo+","+rpName +" has been successfully saved"
			, $(NotificationElement.class).first().getText());
	}
	
	@Test
	public void testRebatePlan6() {

		$(TextFieldElement.class).get(0).sendKeys(rpEditName);
		$(ButtonElement.class).caption("SEARCH").first().click();
		Assert.assertEquals("Search Completed", $(NotificationElement.class).first().getText());
		$(NotificationElement.class).first().close();
		findElement(By.xpath("//div[@class='v-scrollable v-table-body-wrapper v-table-body']"))
		.findElement(By.tagName("tbody")).findElement(By.xpath(".//tr[1]")).click();		
		$(ButtonElement.class).caption("EDIT").first().click();
		$(TabSheetElement.class).first().openTab(0);
		$(TextFieldElement.class).get(3).clear();
		$(ButtonElement.class).caption("Update").first().click();
		Assert.assertTrue("Error Message is not displayed",	$(LabelElement.class).id("ErrorMessage").isDisplayed());

	}

	// View
	@Test
	public void testRebatePlan7() {

		$(TextFieldElement.class).get(0).sendKeys(rpEditName);
		$(ButtonElement.class).caption("SEARCH").first().click();		
		Assert.assertEquals("Search Completed", $(NotificationElement.class).first().getText());
		$(NotificationElement.class).first().close();
		findElement(By.xpath("//div[@class='v-scrollable v-table-body-wrapper v-table-body']"))
		.findElement(By.tagName("tbody")).findElement(By.xpath(".//tr[1]")).click();		
		$(ButtonElement.class).caption("VIEW").first().click();
		Assert.assertTrue("Not entered in View mode", $(TabSheetElement.class).exists());
		$(TabSheetElement.class).first().openTab(0);
		$(TabSheetElement.class).first().openTab(1);
		$(TabSheetElement.class).first().openTab(2);
		$(ButtonElement.class).caption("Back").first().click();

	}
	
	
	// Delete	
	
	@Test
	public void testRebatePlan8() {

		$(TextFieldElement.class).get(0).sendKeys(rpEditName);
		$(ButtonElement.class).caption("SEARCH").first().click();
		Assert.assertEquals("Search Completed", $(NotificationElement.class).first().getText());
		$(NotificationElement.class).first().close();
	    findElement(By.xpath("//div[@class='v-scrollable v-table-body-wrapper v-table-body']"))
		.findElement(By.tagName("tbody")).findElement(By.xpath(".//tr[1]")).click();		
		$(ButtonElement.class).caption("EDIT").first().click();
		String rpNo = $(TextFieldElement.class).get(3).getValue();
		String 	rpName =$(TextFieldElement.class).get(5).getValue();
		$(ButtonElement.class).caption("Delete").first().click();
		if (!$(WindowElement.class).exists()) {
			Assert.fail($(NotificationElement.class).first().getText());
		}
		$(WindowElement.class).first().$(ButtonElement.class).first().click();	
		Assert.assertTrue($(NotificationElement.class).exists());
		String expected = rpNo+","+rpName +" has been successfully deleted";

		Assert.assertEquals(expected, $(NotificationElement.class).first()
				.getText());
	}
	
	
	
	@Before
	public void setUp() throws InterruptedException {
		
		setDriver(new FirefoxDriver());		
		driver.get("https://galderma-test.sysbiz.org/");
		//driver.get("http://localhost:8081/c/portal/login?p_l_id=17401");
		driver.manage().window().maximize();
		findElement(By.id("_58_login")).sendKeys("admin");
		findElement(By.id("_58_password")).sendKeys("admin");
		findElement(By.className("btn-primary")).click();		

		// Use this if menu is not Expanded.
		findElement(By.xpath(".//span[contains(text(),'Global')]")).click();
		
		findElement(By.xpath(".//span[contains(text(),'Rebate Plan')]")).click();
	}

	@After
	public void closeBrowser() {
		driver.quit();
	}
	
	

}
