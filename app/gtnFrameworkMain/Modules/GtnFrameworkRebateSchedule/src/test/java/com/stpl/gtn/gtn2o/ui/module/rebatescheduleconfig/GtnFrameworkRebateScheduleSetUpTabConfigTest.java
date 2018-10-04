package com.stpl.gtn.gtn2o.ui.module.rebatescheduleconfig;

import java.util.LinkedList;
import java.util.List;
import org.junit.*;
import static org.junit.Assert.*;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;


public class GtnFrameworkRebateScheduleSetUpTabConfigTest {
	
	@Test
	public void testGtnFrameworkRebateScheduleSetUpTabConfig_1()
		throws Exception {
		GtnFrameworkRebateScheduleSetUpTabConfig result = new GtnFrameworkRebateScheduleSetUpTabConfig();
		assertNotNull(result);
	}

	
	@Test
	public void testAddPriceSchedulePriocingTab_1()
		throws Exception {
		GtnFrameworkRebateScheduleSetUpTabConfig fixture = new GtnFrameworkRebateScheduleSetUpTabConfig();
		List<GtnUIFrameworkComponentConfig> componentList = new LinkedList();

		fixture.addPriceSchedulePriocingTab(componentList);

	}

	
	@Test
	public void testCreateTableFieldFactoryComponents_1()
		throws Exception {
		List<String> propertyIds = new LinkedList();

		List<GtnUIFrameworkComponentConfig> result = GtnFrameworkRebateScheduleSetUpTabConfig.createTableFieldFactoryComponents(propertyIds);

		assertNotNull(result);
		assertEquals(0, result.size());
	}

	
	@Test
	public void testCreateTableFieldFactoryComponents_2()
		throws Exception {
		List<String> propertyIds = new LinkedList();

		List<GtnUIFrameworkComponentConfig> result = GtnFrameworkRebateScheduleSetUpTabConfig.createTableFieldFactoryComponents(propertyIds);

		assertNotNull(result);
		assertEquals(0, result.size());
	}

	
	@Test
	public void testCreateTableFieldFactoryComponents_3()
		throws Exception {
		List<String> propertyIds = new LinkedList();

		List<GtnUIFrameworkComponentConfig> result = GtnFrameworkRebateScheduleSetUpTabConfig.createTableFieldFactoryComponents(propertyIds);

		assertNotNull(result);
		assertEquals(0, result.size());
	}


	@Test
	public void testCreateTableFieldFactoryComponents_4()
		throws Exception {
		List<String> propertyIds = new LinkedList();

		List<GtnUIFrameworkComponentConfig> result = GtnFrameworkRebateScheduleSetUpTabConfig.createTableFieldFactoryComponents(propertyIds);

		assertNotNull(result);
		assertEquals(0, result.size());
	}

	
	@Test
	public void testCreateTableFieldFactoryComponents_5()
		throws Exception {
		List<String> propertyIds = new LinkedList();

		List<GtnUIFrameworkComponentConfig> result = GtnFrameworkRebateScheduleSetUpTabConfig.createTableFieldFactoryComponents(propertyIds);

		assertNotNull(result);
		assertEquals(0, result.size());
	}

	
	@Test
	public void testCreateTableFieldFactoryComponents_6()
		throws Exception {
		List<String> propertyIds = new LinkedList();

		List<GtnUIFrameworkComponentConfig> result = GtnFrameworkRebateScheduleSetUpTabConfig.createTableFieldFactoryComponents(propertyIds);

		assertNotNull(result);
		assertEquals(0, result.size());
	}


	@Test
	public void testGetComboBoxType_1()
		throws Exception {
		String propertyId = "rsStatus";

		String result = GtnFrameworkRebateScheduleSetUpTabConfig.getComboBoxType(propertyId);

		assertEquals("STATUS", result);
	}

	
	@Test
	public void testGetComboBoxType_2()
		throws Exception {
		String propertyId = "psPPPriceType";

		String result = GtnFrameworkRebateScheduleSetUpTabConfig.getComboBoxType(propertyId);

		assertEquals("ItemPricingQualifier", result);
	}


	@Test
	public void testGetComboBoxType_3()
		throws Exception {
		String propertyId = "psBasePriceType";

		String result = GtnFrameworkRebateScheduleSetUpTabConfig.getComboBoxType(propertyId);

		assertEquals("BASE_PRICE_TYPE", result);
	}

	
	@Test
	public void testGetComboBoxType_4()
		throws Exception {
		String propertyId = "psSubseqPeriodPriceType";

		String result = GtnFrameworkRebateScheduleSetUpTabConfig.getComboBoxType(propertyId);

		assertEquals("ItemPricingQualifier", result);
	}

	
	@Test
	public void testGetComboBoxType_5()
		throws Exception {
		String propertyId = "psToleranceInterval";

		String result = GtnFrameworkRebateScheduleSetUpTabConfig.getComboBoxType(propertyId);

		assertEquals("PRICE_TOLERANCE_INTERVAL", result);
	}

	
	@Test
	public void testGetComboBoxType_6()
		throws Exception {
		String propertyId = "psResetInterval";

		String result = GtnFrameworkRebateScheduleSetUpTabConfig.getComboBoxType(propertyId);

		assertEquals("PRICE_TOLERANCE_INTERVAL", result);
	}

	
	@Test
	public void testGetComboBoxType_7()
		throws Exception {
		String propertyId = "psToleranceFreq";

		String result = GtnFrameworkRebateScheduleSetUpTabConfig.getComboBoxType(propertyId);

		assertEquals("PRICE_TOLERANCE_FREQUENCY", result);
	}

	@Test
	public void testGetComboBoxType_8()
		throws Exception {
		String propertyId = "psResetFrequency";

		String result = GtnFrameworkRebateScheduleSetUpTabConfig.getComboBoxType(propertyId);

		assertEquals("PRICE_TOLERANCE_FREQUENCY", result);
	}

	
	@Test
	public void testGetComboBoxType_9()
		throws Exception {
		String propertyId = "psToleranceType";

		String result = GtnFrameworkRebateScheduleSetUpTabConfig.getComboBoxType(propertyId);

		assertEquals("PRICE_TOLERANCE_TYPE", result);
	}

	@Test
	public void testGetComboBoxType_10()
		throws Exception {
		String propertyId = "psResetType";

		String result = GtnFrameworkRebateScheduleSetUpTabConfig.getComboBoxType(propertyId);

		assertEquals("RESET_TYPE", result);
	}

	@Test
	public void testGetComboBoxType_11()
		throws Exception {
		String propertyId = "psResetEligible";

		String result = GtnFrameworkRebateScheduleSetUpTabConfig.getComboBoxType(propertyId);

		assertEquals("LOCKED_STATUS", result);
	}

	
	@Test
	public void testGetComboBoxType_12()
		throws Exception {
		String propertyId = "psResetPriceType";

		String result = GtnFrameworkRebateScheduleSetUpTabConfig.getComboBoxType(propertyId);

		assertEquals("LOCKED_STATUS", result);
	}

	
	@Test
	public void testGetComboBoxType_13()
		throws Exception {
		String propertyId = "psNetPriceType";

		String result = GtnFrameworkRebateScheduleSetUpTabConfig.getComboBoxType(propertyId);

		assertEquals("LOCKED_STATUS", result);
	}

	
	@Test
	public void testGetComboBoxType_14()
		throws Exception {
		String propertyId = "psNetResetPriceType";

		String result = GtnFrameworkRebateScheduleSetUpTabConfig.getComboBoxType(propertyId);

		assertEquals("LOCKED_STATUS", result);
	}

	@Test
	public void testGetComboBoxType_15()
		throws Exception {
		String propertyId = "";

		String result = GtnFrameworkRebateScheduleSetUpTabConfig.getComboBoxType(propertyId);

		assertEquals(null, result);
	}

	
	
}