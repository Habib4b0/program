package com.stpl.gtn.gtn2o.ui.module.netsalesformulaconfig.util;

import static org.junit.Assert.assertNotNull;
import static org.powermock.api.mockito.PowerMockito.doReturn;
import static org.powermock.api.mockito.PowerMockito.when;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableLogic;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ws.components.GtnWebServiceSearchCriteria;
import com.stpl.gtn.gtn2o.ws.netsales.bean.GtnWsNsfUpdateBean;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.netsales.GtnWsNetSalesGeneralResponse;

/**
 * @author spandan.majumder
 * @version $Revision: 1.0 
 */

@RunWith(PowerMockRunner.class)
@PrepareForTest(value = { GtnUIFrameworkGlobalUI.class })
public class GtnFrameworkNSFCommonLogicTest {
	
	/**
	 * Perform pre-test initialization.
	 *
	 * @throws Exception
	 *         if the initialization fails for some reason
	 *
	 
	 */
	@Before
	public void setUp()
		throws Exception {
		// add additional set up code here
	}


	/**
	 * Run the List<GtnWebServiceSearchCriteria> getAdditioanlSearchCriteriaList() method test.
	 *
	 * @throws Exception
	 *
	 
	 */
	@Test
	public void testGetAdditioanlSearchCriteriaList_1()
		throws Exception {

		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class);
		when(GtnUIFrameworkGlobalUI.getCurrentUser()).thenReturn("20516");
		
		when(GtnUIFrameworkGlobalUI.getSessionProperty(Mockito.anyString())).thenReturn("20516");
		
		List<GtnWebServiceSearchCriteria> result = GtnFrameworkNSFCommonLogic.getAdditioanlSearchCriteriaList();

		// add additional test code here
		assertNotNull(result);
	}

	/**
	 * Run the GtnUIFrameworkWebserviceRequest getWsRequest(boolean) method test.
	 *
	 * @throws Exception
	 *
	 
	 */
	@Test
	public void testGetWsRequest_1()
		throws Exception {
		
		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class);
		
		boolean isSalesBasis = true;
		
		when(GtnUIFrameworkGlobalUI.getSessionProperty("sessionId")).thenReturn("20516");
		
		GtnUIFrameworkWebserviceRequest result = GtnFrameworkNSFCommonLogic.getWsRequest(isSalesBasis);
		assertNotNull(result);
	}

	/**
	 * Run the void reloadTable(String,String) method test.
	 *
	 * @throws Exception
	 *
	 
	 */
	@Test
	public void testReloadTable_1()
		throws Exception {
		
		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class);
		
		String viewId = "";
		String tableId = "";

		GtnUIFrameworkComponentData componentData = Mockito.mock(GtnUIFrameworkComponentData.class);
		when(GtnUIFrameworkGlobalUI.getVaadinComponentData(Mockito.anyString())).thenReturn(componentData);
		
		GtnUIFrameworkPagedTableLogic tableLogic = Mockito.mock(GtnUIFrameworkPagedTableLogic.class);
		doReturn(tableLogic).when(componentData).getCurrentPageTableLogic();
		
		when(GtnUIFrameworkGlobalUI.getSessionProperty("sessionId")).thenReturn("20516");
		
		GtnFrameworkNSFCommonLogic.reloadTable(viewId, tableId);

	}

	/**
	 * Run the boolean updateField(String,Object,boolean,int,boolean,String,boolean) method test.
	 *
	 * @throws Exception
	 *
	 
	 */
	@Test
	public void testUpdateField_1()
		throws Exception {
		
		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class);
		
		String column = "";
		Object value = new Object();
		boolean checkAll = true;
		int systemId = 1;
		boolean salesBasis = true;
		String url = "";
		boolean isPopulate = true;

		when(GtnUIFrameworkGlobalUI.getCurrentUser()).thenReturn("20516");
		when(GtnUIFrameworkGlobalUI.getSessionProperty("sessionId")).thenReturn(new Object());
		
		try{
			GtnFrameworkNSFCommonLogic.updateField(column, value, checkAll, systemId, salesBasis, url, isPopulate);
		}catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		

	}

}