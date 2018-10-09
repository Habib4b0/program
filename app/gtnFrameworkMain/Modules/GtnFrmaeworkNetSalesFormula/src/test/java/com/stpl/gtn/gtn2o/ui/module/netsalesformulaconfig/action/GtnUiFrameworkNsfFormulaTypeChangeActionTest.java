package com.stpl.gtn.gtn2o.ui.module.netsalesformulaconfig.action;

import static org.junit.Assert.assertNotNull;
import static org.powermock.api.mockito.PowerMockito.doReturn;
import static org.powermock.api.mockito.PowerMockito.when;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ui.module.netsalesformulaconfig.util.GtnUIFrameworkNsfFormulaType;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;

/**
 * 
 * @author spandan.majumder
 * @version $Revision: 1.0
 */

@RunWith(PowerMockRunner.class)
@PrepareForTest(value = { GtnUIFrameworkGlobalUI.class, GtnUIFrameworkActionExecutor.class })
public class GtnUiFrameworkNsfFormulaTypeChangeActionTest {

	/**
	 * Run the void configureParams(GtnUIFrameWorkActionConfig) method test.
	 *
	 * @throws Exception
	 *
	 * 
	 */
	@Test
	public void testConfigureParams() throws Exception {

		GtnUiFrameworkNsfFormulaTypeChangeAction fixture = new GtnUiFrameworkNsfFormulaTypeChangeAction();
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();
		fixture.configureParams(gtnUIFrameWorkActionConfig);
	}

	/**
	 * Run the GtnUIFrameWorkAction createInstance() method test.
	 *
	 * @throws Exception
	 *
	 * 
	 */
	@Test
	public void testCreateInstance() throws Exception {

		GtnUiFrameworkNsfFormulaTypeChangeAction fixture = new GtnUiFrameworkNsfFormulaTypeChangeAction();
		GtnUIFrameWorkAction result = fixture.createInstance();
		assertNotNull(result);
	}

	/**
	 * Run the void doAction(String,GtnUIFrameWorkActionConfig) method test.
	 *
	 * @throws Exception
	 *
	 * 
	 */
	@Test
	public void testGtnUiFrameworkNsfFormulaTypeChangedoAction() throws Exception {

		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class);

		GtnUiFrameworkNsfFormulaTypeChangeAction fixture = new GtnUiFrameworkNsfFormulaTypeChangeAction();
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();

		List<Object> actionParemeterList = new ArrayList<Object>();
		actionParemeterList.add(0);
		actionParemeterList.add("viewId");
		gtnUIFrameWorkActionConfig.setActionParameterList(actionParemeterList);

		GtnUIFrameworkNsfFormulaType formulaTypeValue = Mockito.mock(GtnUIFrameworkNsfFormulaType.class);
		doReturn("test").when(formulaTypeValue).getFormulaTypeValue();

		GtnUIFrameworkBaseComponent baseComponent = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(baseComponent);

		GtnUIFrameworkComponentData componentData = Mockito.mock(GtnUIFrameworkComponentData.class);
		when(GtnUIFrameworkGlobalUI.getVaadinComponentData(Mockito.anyString())).thenReturn(componentData);

		GtnUIFrameworkComponentConfig componentConfig = Mockito.mock(GtnUIFrameworkComponentConfig.class);
		doReturn(componentConfig).when(componentData).getCurrentComponentConfig();

		GtnUIFrameworkPagedTableConfig pagedTableConfig = Mockito.mock(GtnUIFrameworkPagedTableConfig.class);
		doReturn(pagedTableConfig).when(componentConfig).getGtnPagedTableConfig();

		fixture.doAction(componentId, gtnUIFrameWorkActionConfig);
	}

	@Test
	public void testGtnUiFrameworkNsfFormulaTypeChangedoAction_elseBlock_1() throws Exception {

		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class, GtnUIFrameworkActionExecutor.class);

		GtnUiFrameworkNsfFormulaTypeChangeAction fixture = new GtnUiFrameworkNsfFormulaTypeChangeAction();
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();

		List<Object> actionParemeterList = new ArrayList<Object>();
		actionParemeterList.add(0);
		actionParemeterList.add("viewId");
		gtnUIFrameWorkActionConfig.setActionParameterList(actionParemeterList);

		GtnUIFrameworkNsfFormulaType formulaTypeValue = Mockito.mock(GtnUIFrameworkNsfFormulaType.class);
		doReturn("test").when(formulaTypeValue).getFormulaTypeValue();

		GtnUIFrameworkBaseComponent baseComponent = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(baseComponent);

		Collection<GtnWsRecordBean> recordBeans = new Collection<GtnWsRecordBean>() {

			@Override
			public <T> T[] toArray(T[] a) {
				// Auto-generated method stub
				return null;
			}

			@Override
			public Object[] toArray() {
				// Auto-generated method stub
				return null;
			}

			@Override
			public int size() {
				// Auto-generated method stub
				return 0;
			}

			@Override
			public boolean retainAll(Collection<?> c) {
				// Auto-generated method stub
				return false;
			}

			@Override
			public boolean removeAll(Collection<?> c) {
				// Auto-generated method stub
				return false;
			}

			@Override
			public boolean remove(Object o) {
				// Auto-generated method stub
				return false;
			}

			@Override
			public Iterator<GtnWsRecordBean> iterator() {
				// Auto-generated method stub
				return null;
			}

			@Override
			public boolean isEmpty() {
				// Auto-generated method stub
				return false;
			}

			@Override
			public boolean containsAll(Collection<?> c) {
				// Auto-generated method stub
				return false;
			}

			@Override
			public boolean contains(Object o) {
				// Auto-generated method stub
				return false;
			}

			@Override
			public void clear() {
				// Auto-generated method stub

			}

			@Override
			public boolean addAll(Collection<? extends GtnWsRecordBean> c) {
				// Auto-generated method stub
				return false;
			}

			@Override
			public boolean add(GtnWsRecordBean e) {
				// Auto-generated method stub
				return false;
			}
		};
		// getItemsFromTable is not empty
		doReturn(recordBeans).when(baseComponent).getItemsFromTable();

		// getValueFromComponent is not null
		doReturn("notNull").when(baseComponent).getValueFromComponent();

		GtnUIFrameworkComponentData componentData = Mockito.mock(GtnUIFrameworkComponentData.class);
		when(GtnUIFrameworkGlobalUI.getVaadinComponentData(Mockito.anyString())).thenReturn(componentData);

		GtnUIFrameworkComponentConfig componentConfig = Mockito.mock(GtnUIFrameworkComponentConfig.class);
		doReturn(componentConfig).when(componentData).getCurrentComponentConfig();

		GtnUIFrameworkPagedTableConfig pagedTableConfig = Mockito.mock(GtnUIFrameworkPagedTableConfig.class);
		doReturn(pagedTableConfig).when(componentConfig).getGtnPagedTableConfig();

		fixture.doAction(componentId, gtnUIFrameWorkActionConfig);
	}

	@Test
	public void testGtnUiFrameworkNsfFormulaTypeChangedoAction_elseBlock_2() throws Exception {

		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class, GtnUIFrameworkActionExecutor.class);

		GtnUiFrameworkNsfFormulaTypeChangeAction fixture = new GtnUiFrameworkNsfFormulaTypeChangeAction();
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();

		List<Object> actionParemeterList = new ArrayList<Object>();
		actionParemeterList.add(0);
		actionParemeterList.add("viewId");
		gtnUIFrameWorkActionConfig.setActionParameterList(actionParemeterList);

		GtnUIFrameworkNsfFormulaType formulaTypeValue = Mockito.mock(GtnUIFrameworkNsfFormulaType.class);

		// getFormulaTypeValue is not test
		doReturn("").when(formulaTypeValue).getFormulaTypeValue();

		GtnUIFrameworkBaseComponent baseComponent = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(baseComponent);

		Collection<GtnWsRecordBean> recordBeans = new Collection<GtnWsRecordBean>() {

			@Override
			public <T> T[] toArray(T[] a) {
				// Auto-generated method stub
				return null;
			}

			@Override
			public Object[] toArray() {
				// Auto-generated method stub
				return null;
			}

			@Override
			public int size() {
				// Auto-generated method stub
				return 0;
			}

			@Override
			public boolean retainAll(Collection<?> c) {
				// Auto-generated method stub
				return false;
			}

			@Override
			public boolean removeAll(Collection<?> c) {
				// Auto-generated method stub
				return false;
			}

			@Override
			public boolean remove(Object o) {
				// Auto-generated method stub
				return false;
			}

			@Override
			public Iterator<GtnWsRecordBean> iterator() {
				// Auto-generated method stub
				return null;
			}

			@Override
			public boolean isEmpty() {
				// Auto-generated method stub
				return false;
			}

			@Override
			public boolean containsAll(Collection<?> c) {
				// Auto-generated method stub
				return false;
			}

			@Override
			public boolean contains(Object o) {
				// Auto-generated method stub
				return false;
			}

			@Override
			public void clear() {
				// Auto-generated method stub

			}

			@Override
			public boolean addAll(Collection<? extends GtnWsRecordBean> c) {
				// Auto-generated method stub
				return false;
			}

			@Override
			public boolean add(GtnWsRecordBean e) {
				// Auto-generated method stub
				return false;
			}
		};

		// getItemsFromTable is not empty
		doReturn(recordBeans).when(baseComponent).getItemsFromTable();

		// getValueFromComponent is "null"
		doReturn("null").when(baseComponent).getValueFromComponent();

		GtnUIFrameworkComponentData componentData = Mockito.mock(GtnUIFrameworkComponentData.class);
		when(GtnUIFrameworkGlobalUI.getVaadinComponentData(Mockito.anyString())).thenReturn(componentData);

		GtnUIFrameworkComponentConfig componentConfig = Mockito.mock(GtnUIFrameworkComponentConfig.class);
		doReturn(componentConfig).when(componentData).getCurrentComponentConfig();

		GtnUIFrameworkPagedTableConfig pagedTableConfig = Mockito.mock(GtnUIFrameworkPagedTableConfig.class);
		doReturn(pagedTableConfig).when(componentConfig).getGtnPagedTableConfig();

		fixture.doAction(componentId, gtnUIFrameWorkActionConfig);
	}

	@Test
	public void testGtnUiFrameworkNsfFormulaTypeChangedoAction_elseBlock_3() throws Exception {

		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class, GtnUIFrameworkActionExecutor.class);

		GtnUiFrameworkNsfFormulaTypeChangeAction fixture = new GtnUiFrameworkNsfFormulaTypeChangeAction();
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();

		List<Object> actionParemeterList = new ArrayList<Object>();
		actionParemeterList.add(0);
		actionParemeterList.add("viewId");
		gtnUIFrameWorkActionConfig.setActionParameterList(actionParemeterList);

		GtnUIFrameworkNsfFormulaType formulaTypeValue = Mockito.mock(GtnUIFrameworkNsfFormulaType.class);

		// getFormulaTypeValue is not test
		doReturn("").when(formulaTypeValue).getFormulaTypeValue();

		GtnUIFrameworkBaseComponent baseComponent = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(baseComponent);

		GtnWsRecordBean recordBean = Mockito.mock(GtnWsRecordBean.class);

		Collection<GtnWsRecordBean> recordBeans = new Collection<GtnWsRecordBean>() {

			@Override
			public <T> T[] toArray(T[] a) {
				// Auto-generated method stub
				return null;
			}

			@Override
			public Object[] toArray() {
				// Auto-generated method stub
				return null;
			}

			@Override
			public int size() {
				// Auto-generated method stub
				return 0;
			}

			@Override
			public boolean retainAll(Collection<?> c) {
				// Auto-generated method stub
				return false;
			}

			@Override
			public boolean removeAll(Collection<?> c) {
				// Auto-generated method stub
				return false;
			}

			@Override
			public boolean remove(Object o) {
				// Auto-generated method stub
				return false;
			}

			@Override
			public Iterator<GtnWsRecordBean> iterator() {
				// Auto-generated method stub
				return null;
			}

			@Override
			public boolean isEmpty() {
				// Auto-generated method stub
				return false;
			}

			@Override
			public boolean containsAll(Collection<?> c) {
				// Auto-generated method stub
				return false;
			}

			@Override
			public boolean contains(Object o) {
				// Auto-generated method stub
				return false;
			}

			@Override
			public void clear() {
				// Auto-generated method stub

			}

			@Override
			public boolean addAll(Collection<? extends GtnWsRecordBean> c) {
				// Auto-generated method stub
				return false;
			}

			@Override
			public boolean add(GtnWsRecordBean e) {
				// Auto-generated method stub
				return false;
			}
		};

		// getItemsFromTable is not empty
		recordBeans.add(recordBean);
		doReturn(recordBeans).when(baseComponent).getItemsFromTable();

		// getValueFromComponent is null
		doReturn(null).when(baseComponent).getValueFromComponent();

		GtnUIFrameworkComponentData componentData = Mockito.mock(GtnUIFrameworkComponentData.class);
		when(GtnUIFrameworkGlobalUI.getVaadinComponentData(Mockito.anyString())).thenReturn(componentData);

		GtnUIFrameworkComponentConfig componentConfig = Mockito.mock(GtnUIFrameworkComponentConfig.class);
		doReturn(componentConfig).when(componentData).getCurrentComponentConfig();

		GtnUIFrameworkPagedTableConfig pagedTableConfig = Mockito.mock(GtnUIFrameworkPagedTableConfig.class);
		doReturn(pagedTableConfig).when(componentConfig).getGtnPagedTableConfig();

		fixture.doAction(componentId, gtnUIFrameWorkActionConfig);
	}

	@Test
	public void testGtnUiFrameworkNsfFormulaTypeChangedoAction_performActionForComboBox_if_formulaType_contract()
			throws Exception {

		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class, GtnUIFrameworkActionExecutor.class);

		GtnUiFrameworkNsfFormulaTypeChangeAction fixture = new GtnUiFrameworkNsfFormulaTypeChangeAction();
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();

		List<Object> actionParemeterList = new ArrayList<Object>();
		actionParemeterList.add(0);
		actionParemeterList.add("viewId");
		gtnUIFrameWorkActionConfig.setActionParameterList(actionParemeterList);

		GtnUIFrameworkNsfFormulaType formulaTypeValue = Mockito.mock(GtnUIFrameworkNsfFormulaType.class);

		// getFormulaTypeValue is not test
		doReturn("").when(formulaTypeValue).getFormulaTypeValue();

		GtnUIFrameworkBaseComponent baseComponent = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(baseComponent);

		// getCaptionFromComboBox = Contract
		doReturn("Contract").when(baseComponent).getCaptionFromComboBox();

		GtnWsRecordBean recordBean = Mockito.mock(GtnWsRecordBean.class);

		Collection<GtnWsRecordBean> recordBeans = new Collection<GtnWsRecordBean>() {

			@Override
			public <T> T[] toArray(T[] a) {
				// Auto-generated method stub
				return null;
			}

			@Override
			public Object[] toArray() {
				// Auto-generated method stub
				return null;
			}

			@Override
			public int size() {
				// Auto-generated method stub
				return 0;
			}

			@Override
			public boolean retainAll(Collection<?> c) {
				// Auto-generated method stub
				return false;
			}

			@Override
			public boolean removeAll(Collection<?> c) {
				// Auto-generated method stub
				return false;
			}

			@Override
			public boolean remove(Object o) {
				// Auto-generated method stub
				return false;
			}

			@Override
			public Iterator<GtnWsRecordBean> iterator() {
				// Auto-generated method stub
				return null;
			}

			@Override
			public boolean isEmpty() {
				// Auto-generated method stub
				return false;
			}

			@Override
			public boolean containsAll(Collection<?> c) {
				// Auto-generated method stub
				return false;
			}

			@Override
			public boolean contains(Object o) {
				// Auto-generated method stub
				return false;
			}

			@Override
			public void clear() {
				// Auto-generated method stub

			}

			@Override
			public boolean addAll(Collection<? extends GtnWsRecordBean> c) {
				// Auto-generated method stub
				return false;
			}

			@Override
			public boolean add(GtnWsRecordBean e) {
				// Auto-generated method stub
				return false;
			}
		};

		// getItemsFromTable is not empty
		recordBeans.add(recordBean);
		doReturn(recordBeans).when(baseComponent).getItemsFromTable();

		// getValueFromComponent is null
		doReturn(null).when(baseComponent).getValueFromComponent();

		GtnUIFrameworkComponentData componentData = Mockito.mock(GtnUIFrameworkComponentData.class);
		when(GtnUIFrameworkGlobalUI.getVaadinComponentData(Mockito.anyString())).thenReturn(componentData);

		GtnUIFrameworkComponentConfig componentConfig = Mockito.mock(GtnUIFrameworkComponentConfig.class);
		doReturn(componentConfig).when(componentData).getCurrentComponentConfig();

		GtnUIFrameworkPagedTableConfig pagedTableConfig = Mockito.mock(GtnUIFrameworkPagedTableConfig.class);
		doReturn(pagedTableConfig).when(componentConfig).getGtnPagedTableConfig();

		fixture.doAction(componentId, gtnUIFrameWorkActionConfig);
	}

	@Test
	public void testGtnUiFrameworkNsfFormulaTypeChangedoAction_performActionForComboBox_if_formulaType_isNull()
			throws Exception {

		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class, GtnUIFrameworkActionExecutor.class);

		GtnUiFrameworkNsfFormulaTypeChangeAction fixture = new GtnUiFrameworkNsfFormulaTypeChangeAction();
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();

		List<Object> actionParemeterList = new ArrayList<Object>();
		actionParemeterList.add(0);
		actionParemeterList.add("viewId");
		gtnUIFrameWorkActionConfig.setActionParameterList(actionParemeterList);

		GtnUIFrameworkNsfFormulaType formulaTypeValue = Mockito.mock(GtnUIFrameworkNsfFormulaType.class);

		// getFormulaTypeValue is not test
		doReturn("").when(formulaTypeValue).getFormulaTypeValue();

		GtnUIFrameworkBaseComponent baseComponent = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(baseComponent);

		// getCaptionFromComboBox = null
		doReturn(null).when(baseComponent).getCaptionFromComboBox();

		GtnWsRecordBean recordBean = Mockito.mock(GtnWsRecordBean.class);

		Collection<GtnWsRecordBean> recordBeans = new Collection<GtnWsRecordBean>() {

			@Override
			public <T> T[] toArray(T[] a) {
				// Auto-generated method stub
				return null;
			}

			@Override
			public Object[] toArray() {
				// Auto-generated method stub
				return null;
			}

			@Override
			public int size() {
				// Auto-generated method stub
				return 0;
			}

			@Override
			public boolean retainAll(Collection<?> c) {
				// Auto-generated method stub
				return false;
			}

			@Override
			public boolean removeAll(Collection<?> c) {
				// Auto-generated method stub
				return false;
			}

			@Override
			public boolean remove(Object o) {
				// Auto-generated method stub
				return false;
			}

			@Override
			public Iterator<GtnWsRecordBean> iterator() {
				// Auto-generated method stub
				return null;
			}

			@Override
			public boolean isEmpty() {
				// Auto-generated method stub
				return false;
			}

			@Override
			public boolean containsAll(Collection<?> c) {
				// Auto-generated method stub
				return false;
			}

			@Override
			public boolean contains(Object o) {
				// Auto-generated method stub
				return false;
			}

			@Override
			public void clear() {
				// Auto-generated method stub

			}

			@Override
			public boolean addAll(Collection<? extends GtnWsRecordBean> c) {
				// Auto-generated method stub
				return false;
			}

			@Override
			public boolean add(GtnWsRecordBean e) {
				// Auto-generated method stub
				return false;
			}
		};

		// getItemsFromTable is not empty
		recordBeans.add(recordBean);
		doReturn(recordBeans).when(baseComponent).getItemsFromTable();

		// getValueFromComponent is null
		doReturn(null).when(baseComponent).getValueFromComponent();

		GtnUIFrameworkComponentData componentData = Mockito.mock(GtnUIFrameworkComponentData.class);
		when(GtnUIFrameworkGlobalUI.getVaadinComponentData(Mockito.anyString())).thenReturn(componentData);

		GtnUIFrameworkComponentConfig componentConfig = Mockito.mock(GtnUIFrameworkComponentConfig.class);
		doReturn(componentConfig).when(componentData).getCurrentComponentConfig();

		GtnUIFrameworkPagedTableConfig pagedTableConfig = Mockito.mock(GtnUIFrameworkPagedTableConfig.class);
		doReturn(pagedTableConfig).when(componentConfig).getGtnPagedTableConfig();

		fixture.doAction(componentId, gtnUIFrameWorkActionConfig);
	}

	@Test
	public void testGtnUiFrameworkNsfFormulaTypeChangedoAction_performActionForComboBox_if_formulaType_isNotContract()
			throws Exception {

		PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class, GtnUIFrameworkActionExecutor.class);

		GtnUiFrameworkNsfFormulaTypeChangeAction fixture = new GtnUiFrameworkNsfFormulaTypeChangeAction();
		String componentId = "";
		GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = new GtnUIFrameWorkActionConfig();

		List<Object> actionParemeterList = new ArrayList<Object>();
		actionParemeterList.add(0);
		actionParemeterList.add("viewId");
		gtnUIFrameWorkActionConfig.setActionParameterList(actionParemeterList);

		GtnUIFrameworkNsfFormulaType formulaTypeValue = Mockito.mock(GtnUIFrameworkNsfFormulaType.class);

		// getFormulaTypeValue is not test
		doReturn("").when(formulaTypeValue).getFormulaTypeValue();

		GtnUIFrameworkBaseComponent baseComponent = Mockito.mock(GtnUIFrameworkBaseComponent.class);
		when(GtnUIFrameworkGlobalUI.getVaadinBaseComponent(Mockito.anyString())).thenReturn(baseComponent);

		// getCaptionFromComboBox = not contract
		doReturn("isNotContract").when(baseComponent).getCaptionFromComboBox();

		GtnWsRecordBean recordBean = Mockito.mock(GtnWsRecordBean.class);

		Collection<GtnWsRecordBean> recordBeans = new Collection<GtnWsRecordBean>() {

			@Override
			public <T> T[] toArray(T[] a) {
				// Auto-generated method stub
				return null;
			}

			@Override
			public Object[] toArray() {
				// Auto-generated method stub
				return null;
			}

			@Override
			public int size() {
				// Auto-generated method stub
				return 0;
			}

			@Override
			public boolean retainAll(Collection<?> c) {
				// Auto-generated method stub
				return false;
			}

			@Override
			public boolean removeAll(Collection<?> c) {
				// Auto-generated method stub
				return false;
			}

			@Override
			public boolean remove(Object o) {
				// Auto-generated method stub
				return false;
			}

			@Override
			public Iterator<GtnWsRecordBean> iterator() {
				// Auto-generated method stub
				return null;
			}

			@Override
			public boolean isEmpty() {
				// Auto-generated method stub
				return false;
			}

			@Override
			public boolean containsAll(Collection<?> c) {
				// Auto-generated method stub
				return false;
			}

			@Override
			public boolean contains(Object o) {
				// Auto-generated method stub
				return false;
			}

			@Override
			public void clear() {
				// Auto-generated method stub

			}

			@Override
			public boolean addAll(Collection<? extends GtnWsRecordBean> c) {
				// Auto-generated method stub
				return false;
			}

			@Override
			public boolean add(GtnWsRecordBean e) {
				// Auto-generated method stub
				return false;
			}
		};

		// getItemsFromTable is not empty
		recordBeans.add(recordBean);
		doReturn(recordBeans).when(baseComponent).getItemsFromTable();

		// getValueFromComponent is null
		doReturn(null).when(baseComponent).getValueFromComponent();

		GtnUIFrameworkComponentData componentData = Mockito.mock(GtnUIFrameworkComponentData.class);
		when(GtnUIFrameworkGlobalUI.getVaadinComponentData(Mockito.anyString())).thenReturn(componentData);

		GtnUIFrameworkComponentConfig componentConfig = Mockito.mock(GtnUIFrameworkComponentConfig.class);
		doReturn(componentConfig).when(componentData).getCurrentComponentConfig();

		GtnUIFrameworkPagedTableConfig pagedTableConfig = Mockito.mock(GtnUIFrameworkPagedTableConfig.class);
		doReturn(pagedTableConfig).when(componentConfig).getGtnPagedTableConfig();

		fixture.doAction(componentId, gtnUIFrameWorkActionConfig);
	}

	/**
	 * Perform pre-test initialization.
	 *
	 * @throws Exception
	 *             if the initialization fails for some reason
	 *
	 * 
	 */
	@Before
	public void setUp() throws Exception {
		// add additional set up code here
	}

	/**
	 * Perform post-test clean-up.
	 *
	 * @throws Exception
	 *             if the clean-up fails for some reason
	 *
	 * 
	 */
	@After
	public void tearDown() throws Exception {
		// Add additional tear down code here
	}

}