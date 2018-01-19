/**
 * 
 */
package com.stpl.app.testbench;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.vaadin.testbench.By;
import com.vaadin.testbench.TestBenchTestCase;
import com.vaadin.testbench.elements.ButtonElement;
import com.vaadin.testbench.elements.CheckBoxElement;
import com.vaadin.testbench.elements.ComboBoxElement;
import com.vaadin.testbench.elements.LabelElement;
import com.vaadin.testbench.elements.NotificationElement;
import com.vaadin.testbench.elements.OptionGroupElement;
import com.vaadin.testbench.elements.TabSheetElement;
import com.vaadin.testbench.elements.TextAreaElement;
import com.vaadin.testbench.elements.TextFieldElement;
import com.vaadin.testbench.elements.WindowElement;

/**
 * @author sibi
 *
 */
public class RebateScheduleTest extends TestBenchTestCase {
	
        private static final org.jboss.logging.Logger LOGGER = org.jboss.logging.Logger.getLogger(RebateScheduleTest.class);
	private String rsName = "TestRS12022015";
	private String rsEditName="EditTestRS12022015";

	// Search

 @Test
	public void testRebateSchedule1() {

		$(TextFieldElement.class).get(0).sendKeys("*");
		$(ButtonElement.class).caption("SEARCH").first().click();		
		Assert.assertEquals("Search Completed", $(NotificationElement.class).first().getText());

	}

	 @Test
	public void testRebateSchedule2() {
     
		$(ButtonElement.class).caption("SEARCH").first().click();
		Assert.assertTrue("Please enter search criteria",$(WindowElement.class).exists());
		$(WindowElement.class).first().$(ButtonElement.class).first().click();	

	}

	// Add

	@Test
	public void testRebateSchedule3() {		
		
		$(ButtonElement.class).caption("ADD").first().click();
		Assert.assertTrue("Add Button fails", $(TabSheetElement.class).exists());
		
		// Tab 1
		$(TabSheetElement.class).first().openTab(0);
		$(TextFieldElement.class).get(3).sendKeys(rsName);
		String rsNo=$(TextFieldElement.class).get(3).getValue();
		$(TextFieldElement.class).get(4).sendKeys(rsName);
		$(TextFieldElement.class).get(5).sendKeys(rsName);
		String rsName= $(TextFieldElement.class).get(5).getValue();
		$(ComboBoxElement.class).get(0).selectByText("Active");
		$(ComboBoxElement.class).get(1).selectByText("FCI");
		$(ComboBoxElement.class).get(2).selectByText("Extdat");
		$(ComboBoxElement.class).get(3).selectByText("Base");
		$(ComboBoxElement.class).get(4).selectByText("Trade Class-1");
		$(ComboBoxElement.class).get(15).selectByText("Annually");
		$(ComboBoxElement.class).get(22).selectByText("Credit Memo");
		
		//Tab 2
		$(TabSheetElement.class).first().openTab(1);
		$(ComboBoxElement.class).get(0).selectByText("Medicaid");
		$(ComboBoxElement.class).get(1).selectByText("Standard Validation");

		// Tab 2
		$(TabSheetElement.class).first().openTab(2);
		$(ComboBoxElement.class).get(0).selectByText("IFP ID");
		$(TextFieldElement.class).get(3).sendKeys("i*");
		$(ButtonElement.class).caption("Search").first().click();
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			LOGGER.error(e);
		}
		findElement(By.xpath("//div[@class='v-scrollable v-table-body-wrapper v-table-body']"))
        .findElement(By.tagName("tbody")).findElement(By.xpath(".//tr[3]")).click();
		findElement(By.id("addBtn")).findElement(By.xpath(".//span[contains(text(),'>')]"))
		.click();

		// Tab 3
		$(TabSheetElement.class).first().openTab(3);
		$(OptionGroupElement.class).get(0).setValue("Enable");
		$(CheckBoxElement.class).get(0).click();
		$(ComboBoxElement.class).get(0).selectByText("RS Status");
		$(ComboBoxElement.class).get(1).selectByText("Active");
		$(ButtonElement.class).caption("Populate").first().click();
		$(ComboBoxElement.class).get(0).selectByText("Start Date");
		findElement(By.id("MassDate")).findElement(By.tagName("input")).sendKeys("10/02/2015");
		$(ButtonElement.class).caption("Populate").first().click();
		$(ComboBoxElement.class).get(0).selectByText("End Date");
		findElement(By.id("MassDate")).findElement(By.tagName("input")).sendKeys("12/12/2015");
		$(ButtonElement.class).caption("Populate").first().click();
		findElement(By.xpath("//div[@class='v-table-caption-container v-table-caption-container-align-left']")).click();

		// Tab 4
		$(TabSheetElement.class).first().openTab(4);
		$(TextAreaElement.class).first().setValue("Data entered in TextArea during Testbench Test");
		$(ButtonElement.class).caption("Save").first().click();
		Assert.assertTrue("No confirmation message", $(WindowElement.class)	.exists());
		$(WindowElement.class).first().$(ButtonElement.class).caption("Yes").first().click();		
		Assert.assertEquals(rsNo+","+rsName +" has been successfully saved"
				, $(NotificationElement.class).first().getText());
	}
	
	
	@Test
	public void testRebateSchedule4() {		
		
		$(ButtonElement.class).caption("ADD").first().click();
		Assert.assertTrue("Add Button fails", $(TabSheetElement.class).exists());
		$(ButtonElement.class).caption("Save").first().click();
		Assert.assertTrue("Error Message is not displayed", $(LabelElement.class).id("ErrorMessage").isDisplayed());
	}

	// Edit

	@Test
	public void testRebateSchedule5() {		
		
		$(TextFieldElement.class).get(0).sendKeys(rsName);
		$(ButtonElement.class).caption("SEARCH").first().click();		
		Assert.assertEquals("Search Completed", $(NotificationElement.class).first().getText());
		$(NotificationElement.class).first().close();
	   findElement(By.xpath("//div[@class='v-scrollable v-table-body-wrapper v-table-body']"))
       .findElement(By.tagName("tbody")).findElement(By.xpath(".//tr[1]")).click();
		$(ButtonElement.class).caption("EDIT").first().click();

		// Tab 1
		$(TabSheetElement.class).first().openTab(0);
		$(TextFieldElement.class).get(3).clear();
		$(TextFieldElement.class).get(3).sendKeys(rsEditName);
		String rsNo =$(TextFieldElement.class).get(3).getValue();
		$(TextFieldElement.class).get(4).clear();
		$(TextFieldElement.class).get(4).sendKeys(rsEditName);
		$(TextFieldElement.class).get(5).clear();
		$(TextFieldElement.class).get(5).sendKeys(rsEditName);
		String rsName =$(TextFieldElement.class).get(5).getValue();
		$(ComboBoxElement.class).get(0).selectByText("Divested");
		$(ComboBoxElement.class).get(1).selectByText("Volume");
		$(ComboBoxElement.class).get(2).selectByText("Pfd");
		$(ComboBoxElement.class).get(3).selectByText("H_none");	


		// Tab 2
		$(TabSheetElement.class).first().openTab(2);		

		// Tab 3
		$(TabSheetElement.class).first().openTab(3);
		$(OptionGroupElement.class).get(0).setValue("Enable");		
		$(ComboBoxElement.class).get(0).selectByText("RS Status");
		$(ComboBoxElement.class).get(1).selectByText("Divested");
		$(ButtonElement.class).caption("Populate All").first().click();			
		findElement(By.xpath("//div[@class='v-table-caption-container v-table-caption-container-align-left']")).click();

		// Tab 4
		$(TabSheetElement.class).first().openTab(4);
		$(TextAreaElement.class).first().setValue("Data entered in TextArea during Testbench Test");
		$(ButtonElement.class).caption("Update").first().click();
		$(WindowElement.class).first().$(ButtonElement.class).caption("Yes")
	    .first().click();
		Assert.assertEquals(rsNo+","+rsName +" has been successfully saved"
			, $(NotificationElement.class).first().getText());
	}
	
	@Test
	public void testRebateSchedule6() {		
		
		$(TextFieldElement.class).get(0).sendKeys(rsEditName);
		$(ButtonElement.class).caption("SEARCH").first().click();		
		Assert.assertEquals("Search Completed", $(NotificationElement.class).first().getText());
		$(NotificationElement.class).first().close();
		findElement(By.xpath("//div[@class='v-scrollable v-table-body-wrapper v-table-body']"))
      .findElement(By.tagName("tbody")).findElement(By.xpath(".//tr[1]")).click();
		$(ButtonElement.class).caption("EDIT").first().click();
		$(TabSheetElement.class).first().openTab(0);
		$(TextFieldElement.class).get(3).clear();
		$(TextFieldElement.class).get(4).clear();
		$(TextFieldElement.class).get(5).clear();
		$(ButtonElement.class).caption("Update").first().click();
		Assert.assertTrue("", $(LabelElement.class).id("ErrorMessage").isDisplayed());
	}

	// View

	@Test
	public void testRebateSchedule7() {

		$(TextFieldElement.class).get(0).sendKeys(rsEditName);
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
		$(TabSheetElement.class).first().openTab(3);
		$(ButtonElement.class).caption("Back").first().click();

	}

	// Delete

	@Test
	public void testRebateSchedule8() {

		$(TextFieldElement.class).get(0).sendKeys(rsEditName);
		$(ButtonElement.class).caption("SEARCH").first().click();
		Assert.assertEquals("Search Completed", $(NotificationElement.class).first().getText());
		$(NotificationElement.class).first().close();
		findElement(By.xpath("//div[@class='v-scrollable v-table-body-wrapper v-table-body']"))
       .findElement(By.tagName("tbody")).findElement(By.xpath(".//tr[1]")).click();
		$(ButtonElement.class).caption("EDIT").first().click();
		String rsNo = $(TextFieldElement.class).get(3).getValue();
		String rsName = $(TextFieldElement.class).get(5).getValue();
		$(ButtonElement.class).caption("Delete").first().click();
		if (!$(WindowElement.class).exists()) {
			Assert.fail($(NotificationElement.class).first().getText());
		}
		$(WindowElement.class).first().$(ButtonElement.class).first().click();	
		Assert.assertTrue($(NotificationElement.class).exists());
		String expected = rsNo+","+rsName +" has been successfully deleted";

		Assert.assertEquals(expected, $(NotificationElement.class).first()
				.getText());
	}
	@Before
	public void setUp() throws InterruptedException {

		setDriver(new FirefoxDriver());
		driver.get("https://galderma-test.sysbiz.org/");
	
		driver.manage().window().maximize();
		findElement(By.id("_58_login")).sendKeys("admin");
		findElement(By.id("_58_password")).sendKeys("admin");
		findElement(By.className("btn-primary")).click();		

		// Use this if menu is not Expanded.
		
	 findElement(By.xpath(".//span[contains(text(),'Global')]")).click();

		
		findElement(By.xpath(".//span[contains(text(),'Rebate Schedule')]")).click();
	}

	@After
	public void closeBrowser() {
		driver.quit();
	}

}
