package com.stpl.gtn.gtn2o.ui.framework.engine.base;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.asi.calendarfield.CalendarField;
import org.asi.ui.customtextfield.CustomTextField;
import org.asi.ui.extfilteringtable.ExtFilterTable;
import org.asi.ui.extfilteringtable.freezetable.FreezePagedTreeTable;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.calendarfield.GtnUIFrameworkCalendarComponent;
import com.stpl.gtn.gtn2o.ui.framework.component.duallistbox.GtnUIFrameworkDualListBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.duallistbox.bean.GtnFrameworkDualListBoxBean;
import com.stpl.gtn.gtn2o.ui.framework.component.notestab.util.GtnUIFrameworkNotesTab;
import com.stpl.gtn.gtn2o.ui.framework.component.table.newpagedtreetable.GtnUIFrameworkNewPagedTreeTableComponent;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableComponent;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableLogic;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtreetable.GtnUIFrameworkPagedTreeTableLogic;
import com.stpl.gtn.gtn2o.ui.framework.component.tree.GtnUIFrameworkTreeComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.components.GtnWebServiceSearchCriteria;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;
import com.vaadin.v7.data.Container;
import com.vaadin.v7.data.Property;
import com.vaadin.server.BrowserWindowOpener;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.v7.ui.AbstractSelect;
import com.vaadin.ui.Button;
import com.vaadin.v7.ui.ComboBox;
import com.vaadin.v7.ui.DateField;

import org.asi.ui.extfilteringtable.ExtCustomTable;
import com.vaadin.v7.ui.Field;
import com.vaadin.ui.HasComponents;
import com.vaadin.v7.ui.OptionGroup;
import com.vaadin.v7.ui.PopupDateField;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TabSheet.Tab;
import com.vaadin.v7.ui.Tree;
import com.vaadin.ui.UI;
import org.asi.container.ExtContainer;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;

public class GtnUIFrameworkBaseComponent {

	private final AbstractComponent component;

	private final String componentId;

	private GtnUIFrameworkBaseComponent(AbstractComponent component, String componentId) {
		this.component = component;
		this.componentId = componentId;
	}

	public static GtnUIFrameworkBaseComponent returnBaseComponent(String componentId,
			WeakReference<AbstractComponent> weakReferenceComponentFromMap) {
		String key;
		AbstractComponent component = null;
		if (weakReferenceComponentFromMap == null || weakReferenceComponentFromMap.get() == null) {
			key = "dummyComponent";

		} else {
			String componentIdinMap = getComponentIdInMap(weakReferenceComponentFromMap);
			key = componentIdinMap + "_" + weakReferenceComponentFromMap.get().hashCode();
			component = weakReferenceComponentFromMap.get();
		}

		Map<String, GtnUIFrameworkBaseComponent> baseComponentMap = ((GtnUIFrameworkComponentData) UI.getCurrent()
				.getData()).getFrameworkConfigMap().getBaseComponentMap();
		GtnUIFrameworkBaseComponent baseComponent = baseComponentMap.get(key);
		if (baseComponent != null) {
			return baseComponent;
		}

		baseComponent = new GtnUIFrameworkBaseComponent(component, componentId);
		baseComponentMap.put(key, baseComponent);

		return baseComponent;
	}

	private static String getComponentIdInMap(WeakReference<AbstractComponent> weakReferenceComponentFromMap) {
		GtnUIFrameworkComponentData componentData = (GtnUIFrameworkComponentData) weakReferenceComponentFromMap.get()
				.getData();

		return componentData.getComponentIdInMap();
	}

	public AbstractComponent getComponent() {
		return component;
	}

	public GtnUIFrameworkComponentData getComponentData() {
		return (GtnUIFrameworkComponentData) component.getData();
	}

	public GtnUIFrameworkComponentConfig getComponentConfig() {
		return this.getComponentData().getCurrentComponentConfig();
	}

	public GtnUIFrameworkDualListBoxConfig getGtnUIFrameworkDualListBoxConfig() {
		return this.getComponentConfig().getGtnUIFrameworkDualListBoxConfig();
	}

	public Object getData() {
		return component.getData();
	}

	public String getComponentId() {
		return componentId;
	}

	public void setCustomData(Object customDataObject) {
		GtnUIFrameworkComponentData componentData = (GtnUIFrameworkComponentData) component.getData();
		componentData.setCustomData(customDataObject);
	}

	public Integer getIntegerFromField() throws GtnFrameworkValidationFailedException {
		try {
			Field<?> filed = (Field<?>) this.component;
			if (isEmpty(filed.getValue())) {
				return 0;
			}
			return Integer.valueOf(getString(filed.getValue()).trim());
		} catch (NumberFormatException typeException) {
			throw new GtnFrameworkValidationFailedException(componentId, typeException);
		}
	}

	public String getStringFromField() {
		Field<?> field = (Field<?>) this.component;
		return getString(field.getValue()).trim();
	}

	public Object getObjectFromField() throws GtnFrameworkValidationFailedException {
		try {
			Field<?> field = (Field<?>) this.component;
			return field.getValue();
		} catch (Exception typeException) {
			throw new GtnFrameworkValidationFailedException(componentId, typeException);
		}
	}

	public Date getDateFromDateField() throws GtnFrameworkValidationFailedException {
		try {
			PopupDateField dateField = (PopupDateField) this.component;
			return dateField.getValue();
		} catch (Exception typeException) {
			throw new GtnFrameworkValidationFailedException(componentId, typeException);
		}
	}

	public GtnWsRecordBean getValueFromDataTable() throws GtnFrameworkValidationFailedException {
		try {
			AbstractSelect resultTable = (AbstractSelect) this.getComponentData().getCustomData();
			return (GtnWsRecordBean) (resultTable.getValue());
		} catch (Exception typeException) {
			throw new GtnFrameworkValidationFailedException(componentId, typeException);
		}
	}

	@SuppressWarnings("unchecked")
	public List<GtnWsRecordBean> getItemsFromDataTable() throws GtnFrameworkValidationFailedException {
		try {
			AbstractSelect resultRable = (AbstractSelect) this.getComponentData().getCustomData();
			return (List<GtnWsRecordBean>) (resultRable.getItemIds());
		} catch (Exception typeException) {
			throw new GtnFrameworkValidationFailedException(componentId, typeException);
		}
	}

	public GtnWsRecordBean getValueFromPagedDataTable() throws GtnFrameworkValidationFailedException {
		try {
			ExtCustomTable resultTable = (ExtCustomTable) this.getComponentData().getCustomData();
			return (GtnWsRecordBean) (resultTable.getValue());
		} catch (Exception typeException) {
			throw new GtnFrameworkValidationFailedException(componentId, typeException);
		}
	}

	@SuppressWarnings("unchecked")
	public Set<GtnWsRecordBean> getValueFromMultiSelectPagedDataTable() throws GtnFrameworkValidationFailedException {
		try {
			ExtCustomTable resultTable = (ExtCustomTable) this.getComponentData().getCustomData();
			return (Set<GtnWsRecordBean>) (resultTable.getValue());
		} catch (Exception typeException) {
			throw new GtnFrameworkValidationFailedException(componentId, typeException);
		}
	}

	@SuppressWarnings("unchecked")
	public ExtPagedTable<ExtContainer<GtnWsRecordBean>> getExtPagedTable() {
		return (ExtPagedTable<ExtContainer<GtnWsRecordBean>>) this.getComponentData().getCustomData();
	}

	public ExtCustomTable getExtCustomTable() {
		return (ExtCustomTable) this.getComponentData().getCustomData();

	}

	public AbstractSelect getGridComponent() throws GtnFrameworkValidationFailedException {
		try {
			GtnUIFrameworkComponentData componentData = this.getComponentData();
			return (AbstractSelect) componentData.getCustomData();
		} catch (Exception typeException) {
			throw new GtnFrameworkValidationFailedException(componentId, typeException);
		}

	}

	public ExtFilterTable getExtFilterTable() throws GtnFrameworkValidationFailedException {
		try {
			return (ExtFilterTable) this.getComponentData().getCustomData();
		} catch (Exception typeException) {
			throw new GtnFrameworkValidationFailedException(componentId, typeException);
		}

	}

	public List<Object> getNotesTabValue() throws GtnFrameworkValidationFailedException {

		try {
			GtnUIFrameworkNotesTab notesTab = (GtnUIFrameworkNotesTab) this.component;
			return notesTab.getNotesTabData();
		} catch (Exception typeException) {
			throw new GtnFrameworkValidationFailedException(componentId, typeException);
		}

	}

	public void setNotesTabValue(List<Object> valueList) throws GtnFrameworkValidationFailedException {

		try {
			GtnUIFrameworkNotesTab notesTab = (GtnUIFrameworkNotesTab) this.component;
			notesTab.setNotesTabData(valueList);
		} catch (Exception typeException) {
			throw new GtnFrameworkValidationFailedException(componentId, typeException);
		}

	}

	public GtnUIFrameworkPagedTableLogic getLogicFromPagedDataTable() {
		return (GtnUIFrameworkPagedTableLogic) getExtPagedTable().getContainerLogic();
	}

	@SuppressWarnings("unchecked")
	public Collection<GtnWsRecordBean> getItemsFromTable() {
		ExtCustomTable customData = (ExtCustomTable) this.getComponentData().getCustomData();
		return (Collection<GtnWsRecordBean>) customData.getContainerDataSource().getItemIds();
	}

	public Set<Container.Filter> getFiltersFromPagedDataTable() {
		return getLogicFromPagedDataTable().getFilters();
	}

	public TabSheet getAsTabSheet() {
		return (TabSheet) this.getComponent();

	}

	public Object getIdFromField() {

		Object value = this.getComponentData().getCustomData();
		if (value instanceof GtnWsRecordBean) {
			GtnWsRecordBean dto = (GtnWsRecordBean) value;
			return dto.getPropertyValueByIndex(dto.getProperties().size() - 1);

		}
		return value;
	}

	public String getCaptionFromComboBox() throws GtnFrameworkValidationFailedException {
		try {
			ComboBox comboBox = (ComboBox) this.getComponent();
			return getString(comboBox.getItemCaption(comboBox.getValue())).trim();
		} catch (Exception typeException) {
			throw new GtnFrameworkValidationFailedException(componentId, typeException);
		}

	}

	private static String getString(Object value) {
		if (isNull(value)) {
			return "";
		}
		return String.valueOf(value);
	}

	private static boolean isEmpty(Object value) {
		return getString(value).isEmpty();
	}

	private static boolean isNull(Object value) {
		return value == null || "null".equals(String.valueOf(value));
	}

	@SuppressWarnings("unchecked")
	public Set<GtnWsRecordBean> getValuesFromPagedDataTable() throws GtnFrameworkValidationFailedException {
		try {
			AbstractSelect resultTable = (AbstractSelect) this.getComponentData().getCustomData();
			return (Set<GtnWsRecordBean>) (resultTable.getValue());
		} catch (Exception typeException) {
			throw new GtnFrameworkValidationFailedException(componentId, typeException);
		}
	}

	@SuppressWarnings("unchecked")
	public Object getValueFromComponent() throws GtnFrameworkValidationFailedException {
		try {
			if (getComponent() instanceof Property) {
				return ((Property<Object>) getComponent()).getValue();
			}
			if (getComponent() instanceof HasComponents && getComponentData().getCustomData() instanceof Property) {
				return ((Property<Object>) getComponentData().getCustomData()).getValue();
			}
			if (getComponent() instanceof ExtCustomTable) {
				return ((ExtCustomTable) getComponentData().getCustomData()).getValue();
			}
			return null;
		} catch (Exception typeException) {
			throw new GtnFrameworkValidationFailedException(componentId, typeException);
		}
	}

	public Object validateAndGetValue() throws GtnFrameworkValidationFailedException {
		validate();
		return getObjectFromField();
	}

	public void validate() throws GtnFrameworkValidationFailedException {
		if (getComponent() instanceof Field<?>) {
			try {
				((Field<?>) getComponent()).validate();
			} catch (Exception e) {
				throw new GtnFrameworkValidationFailedException(e.getMessage(), componentId);
			}
		}
	}

	public void addItemToDataTable(Object item) throws GtnFrameworkValidationFailedException {
		try {
			AbstractSelect tree = (AbstractSelect) getComponentData().getCustomData();
			tree.getContainerDataSource().addItem(item);
		} catch (Exception typeException) {
			throw new GtnFrameworkValidationFailedException(componentId, typeException);
		}
	}

	public void addItemToTreeDataTable(Object item, boolean childrenAllowed)
			throws GtnFrameworkValidationFailedException {
		try {
			AbstractSelect tree = (AbstractSelect) getComponentData().getCustomData();
			Container.Hierarchical container = (Container.Hierarchical) tree.getContainerDataSource();
			container.addItem(item);
			container.setChildrenAllowed(item, childrenAllowed);
		} catch (Exception typeException) {
			throw new GtnFrameworkValidationFailedException(componentId, typeException);
		}
	}

	public void removeItemFromDataTable(Object item) throws GtnFrameworkValidationFailedException {
		try {
			AbstractSelect selectComponent = (AbstractSelect) this.getComponentData().getCustomData();
			selectComponent.getContainerDataSource().removeItem(item);
		} catch (Exception typeException) {
			throw new GtnFrameworkValidationFailedException(componentId, typeException);
		}
	}

	public void addItemsToTreeDataTable(Object parentItemId, Collection<?> items, boolean childrenAllowed) {
		if (getComponentData().getCustomData() instanceof Tree) {
			((GtnUIFrameworkTreeComponent) (getComponentConfig().getComponentType().getGtnComponent()))
					.addItemsToTreeDataTable((Tree) (getComponentData().getCustomData()), parentItemId, items,
							childrenAllowed);
		}
	}

	public void removeItemsFromMultiSelectDataTable() throws GtnFrameworkValidationFailedException {
		try {
			Set<?> itemSet = getValuesFromPagedDataTable();
			AbstractSelect tree = (AbstractSelect) this.getComponentData().getCustomData();
			for (Object item : itemSet) {
				tree.getContainerDataSource().removeItem(item);
			}
		} catch (GtnFrameworkValidationFailedException | UnsupportedOperationException typeException) {
			throw new GtnFrameworkValidationFailedException(componentId, typeException);
		}
	}

	public List<GtnWsRecordBean> removeSelectedTreeItems(String initialTableId, boolean hasMultipleTable)
			throws GtnFrameworkValidationFailedException {
		List<GtnWsRecordBean> returnList = null;
		if (getComponentData().getCustomData() instanceof Tree) {
			returnList = ((GtnUIFrameworkTreeComponent) (getComponentConfig().getComponentType().getGtnComponent()))
					.removeSelectedTreeItems((Tree) (getComponentData().getCustomData()), initialTableId,
							hasMultipleTable);
		}
		return returnList;
	}

	public List<GtnWsRecordBean> removeParentAndChildTreeItems(String initialTableId, boolean hasMultipleTable)
			throws GtnFrameworkValidationFailedException {
		List<GtnWsRecordBean> returnListToRemove = null;
		if (getComponentData().getCustomData() instanceof Tree) {
			returnListToRemove = ((GtnUIFrameworkTreeComponent) (getComponentConfig().getComponentType()
					.getGtnComponent())).removeParentAndChildTreeItems((Tree) (getComponentData().getCustomData()),
							initialTableId, hasMultipleTable);
		}
		return returnListToRemove;
	}

	@SuppressWarnings("unchecked")
	public void setTableValue(Object newValue) {
		Field<Object> vaadinField = (Field<Object>) getComponentData().getCustomData();
		vaadinField.setValue(newValue);
	}

	public List<GtnWsRecordBean> getTreeNodes() {
		List<GtnWsRecordBean> treeNodes = null;
		if (getComponentData().getCustomData() instanceof Tree) {
			treeNodes = ((GtnUIFrameworkTreeComponent) (getComponentConfig().getComponentType().getGtnComponent()))
					.getTreeNodes((Tree) (getComponentData().getCustomData()));
		}
		return treeNodes;
	}

	public List<GtnWsRecordBean> getAllTreeNodes() {
		List<GtnWsRecordBean> treeNodes = new ArrayList<>();
		if (getComponentData().getCustomData() instanceof Tree) {
			treeNodes = ((GtnUIFrameworkTreeComponent) (getComponentConfig().getComponentType().getGtnComponent()))
					.getAllTreeNodes((Tree) (getComponentData().getCustomData()));
		}
		return treeNodes;
	}

	public void loadTreeFromTreeNode(List<GtnWsRecordBean> nodes) {
		if (getComponentData().getCustomData() instanceof Tree) {
			((GtnUIFrameworkTreeComponent) (getComponentConfig().getComponentType().getGtnComponent()))
					.loadTreeFromTreeNode((Tree) (getComponentData().getCustomData()), nodes);
		}
	}

	public List<GtnWsRecordBean> getChildNodes(GtnWsRecordBean parent) {
		List<GtnWsRecordBean> treeNodes = new ArrayList<>();
		if (getComponentData().getCustomData() instanceof Tree) {
			treeNodes = ((GtnUIFrameworkTreeComponent) (getComponentConfig().getComponentType().getGtnComponent()))
					.getChildNodes((Tree) (getComponentData().getCustomData()), parent);
		}
		return treeNodes;
	}

	public void addItemsToGrid(List<GtnWsRecordBean> itemLiist) throws GtnFrameworkGeneralException {
		try {
			AbstractSelect selectComponent = (AbstractSelect) getComponentData().getCustomData();
			Container container = (selectComponent.getContainerDataSource());
			for (GtnWsRecordBean extListDTO : itemLiist) {
				container.addItem(extListDTO);
			}
		} catch (Exception typeException) {
			throw new GtnFrameworkValidationFailedException(componentId, typeException);
		}
	}

	public void removeItemsFromGrid(List<GtnWsRecordBean> itemLiist) throws GtnFrameworkGeneralException {
		try {
			AbstractSelect selectComponent = (AbstractSelect) getComponentData().getCustomData();
			Container container = (selectComponent.getContainerDataSource());
			for (GtnWsRecordBean extListDTO : itemLiist) {
				container.removeItem(extListDTO);
			}
		} catch (Exception typeException) {
			throw new GtnFrameworkValidationFailedException(componentId, typeException);
		}
	}

	public void expandTreeItem(Object itemId) throws GtnFrameworkGeneralException {
		try {
			Tree treeComponent = (Tree) getComponentData().getCustomData();
			treeComponent.expandItem(itemId);
		} catch (Exception typeException) {
			throw new GtnFrameworkValidationFailedException(componentId, typeException);
		}
	}

	public void collapseTreeItem(Object itemId) throws GtnFrameworkGeneralException {
		try {
			Tree treeComponent = (Tree) getComponentData().getCustomData();
			treeComponent.collapseItem(itemId);
		} catch (Exception typeException) {
			throw new GtnFrameworkValidationFailedException(componentId, typeException);
		}
	}

	public void loadDefaultCombobox(Object itemIdToAdd, Object itemIdToSelect) {
		ComboBox vaadinComboBox = (ComboBox) getComponent();
		vaadinComboBox.removeAllItems();
		vaadinComboBox.addItem(itemIdToAdd);
		vaadinComboBox.setNullSelectionAllowed(Boolean.TRUE);
		vaadinComboBox.setNullSelectionItemId(0);
		vaadinComboBox.select(itemIdToSelect);
	}

	public List<GtnWsRecordBean> getDualListBoxRightTableData() {
		GtnUIFrameworkComponentData dualListBoxData = this.getComponentData();
		GtnFrameworkDualListBoxBean dualListBoxBean = (GtnFrameworkDualListBoxBean) dualListBoxData.getCustomData();
		return dualListBoxBean.getDataFromRightTable();
	}

	public void loadDualListBoxRightTableData(List<String> inputList, String componentId)
			throws GtnFrameworkGeneralException {
		GtnUIFrameworkComponentData dualListBoxData = this.getComponentData();

		GtnUIFrameWorkActionConfig bulkLoadRightTableActionConfig = new GtnUIFrameWorkActionConfig();
		bulkLoadRightTableActionConfig.setActionType(GtnUIFrameworkActionType.DUAL_LISTBOX_RIGHT_TABLE_BULK_LOADACTION);
		List<Object> actionParametersList = new ArrayList<>(2);
		actionParametersList.add(dualListBoxData);
		actionParametersList.add(inputList);
		bulkLoadRightTableActionConfig.setActionParameterList(actionParametersList);
		GtnUIFrameworkActionExecutor.executeSingleAction(componentId, bulkLoadRightTableActionConfig);
	}

	public List<GtnWsRecordBean> getRootNodes() {
		List<GtnWsRecordBean> treeNodes = new ArrayList<>();
		if (getComponentData().getCustomData() instanceof Tree) {
			treeNodes = ((GtnUIFrameworkTreeComponent) (getComponentConfig().getComponentType().getGtnComponent()))
					.getRootNodes((Tree) (getComponentData().getCustomData()));
		}
		return treeNodes;
	}

	public void addItemToTreeDataTable(Object parentItemId, Object itemId, boolean childrenAllowed)
			throws GtnFrameworkValidationFailedException {
		if (getComponentData().getCustomData() instanceof Tree) {
			((GtnUIFrameworkTreeComponent) (getComponentConfig().getComponentType().getGtnComponent()))
					.addItemToTreeDataTable((Tree) (getComponentData().getCustomData()), parentItemId, itemId,
							childrenAllowed);
		}
	}

	public void resetFilter() {
		ExtFilterTable resultsTable = (ExtFilterTable) getComponentData().getCustomData();
		resultsTable.resetFilters();
		removeAllContainerFilters();
	}

	public void setFilterFieldVisible(Object propertyId, boolean visible) {
		ExtFilterTable resultsTable = (ExtFilterTable) getComponentData().getCustomData();
		resultsTable.setFilterFieldVisible(propertyId, visible);
	}

	public boolean setFilterFieldValue(Object propertyId, Object value) {
		ExtFilterTable resultsTable = (ExtFilterTable) getComponentData().getCustomData();
		return resultsTable.setFilterFieldValue(propertyId, value);
	}

	public Object getFilterFieldValue(Object propertyId) {
		ExtFilterTable resultsTable = (ExtFilterTable) getComponentData().getCustomData();
		return resultsTable.getFilterFieldValue(propertyId);
	}

	public void removeAllContainerFilters() {
		AbstractSelect grid = (AbstractSelect) getComponentData().getCustomData();
		Container container = grid.getContainerDataSource();
		if (container instanceof Container.Filterable) {
			((Container.Filterable) container).removeAllContainerFilters();
		}
	}

	public void setVisible(boolean visibleFlag) {
		component.setVisible(visibleFlag);
	}

	public void setEnable(boolean enableFlag) {
		component.setEnabled(enableFlag);
	}

	public boolean isVisible() {
		return component.isVisible();
	}

	public boolean isEnabled() {
		return component.isEnabled();
	}

	@SuppressWarnings("unchecked")
	public void setFieldRequired(boolean visibleFlag) {
		((Field<Object>) component).setRequired(visibleFlag);
	}

	public void clickButton() {
		Button button = (Button) this.getComponent();
		button.click();
	}

	public void setTreeItemCaption(Object itemId, String caption) {
		Tree tree = (Tree) (getComponentData().getCustomData());
		tree.setItemCaption(itemId, caption);
	}

	public boolean hasChildrenOfTreeItem(Object itemId) {
		Tree tree = (Tree) (getComponentData().getCustomData());
		return tree.hasChildren(itemId);
	}

	public void removeAllGridItems() throws GtnFrameworkValidationFailedException {
		try {
			AbstractSelect grid = (AbstractSelect) getComponentData().getCustomData();
			Container container = grid.getContainerDataSource();
			container.removeAllItems();
		} catch (Exception typeException) {
			throw new GtnFrameworkValidationFailedException(componentId, typeException);
		}
	}

	public void setLeftTreeTableHeaderCheckBox(boolean isCheckAll, String propertyId) {
		GtnUIFrameworkComponentData componentData = this.getComponentData();
		FreezePagedTreeTable resultsTable = (FreezePagedTreeTable) componentData.getCustomDataList().get(0);
		resultsTable.getLeftFreezeAsTable().setColumnCheckBox(propertyId, true, isCheckAll);
	}

	@SuppressWarnings("unchecked")
	public void reloadPagedTreeTable() {
		GtnUIFrameworkComponentData componentData = this.getComponentData();
		FreezePagedTreeTable resultsTable = (FreezePagedTreeTable) componentData.getCustomDataList().get(0);
		GtnUIFrameworkPagedTreeTableLogic tableLogic = (GtnUIFrameworkPagedTreeTableLogic) resultsTable
				.getLeftFreezeAsTable().getContainerLogic();
		Set<String> hierarchyNo = (Set<String>) componentData.getCustomDataList().get(1);
		tableLogic.forRefresh(hierarchyNo);
		tableLogic.setCurrentPage(tableLogic.getCurrentPage());
	}

	public void setTabVisible(String tabComponentId, boolean visibleFlag) throws GtnFrameworkValidationFailedException {
		try {
			getTabConfigbyComponentId(tabComponentId).setVisible(visibleFlag);
		} catch (Exception typeException) {
			throw new GtnFrameworkValidationFailedException(componentId, typeException);
		}
	}

	public int getTabSheetSelectedTabIndex() throws GtnFrameworkValidationFailedException {
		int index = 0;
		try {
			TabSheet tabSheet = getAsTabSheet();
			index = tabSheet.getTabPosition(tabSheet.getTab(tabSheet.getSelectedTab()));
		} catch (Exception typeException) {
			throw new GtnFrameworkValidationFailedException(componentId, typeException);
		}
		return index;
	}

	public List<Object> getTableRecordHeader() throws GtnFrameworkValidationFailedException {
		List<Object> recordHeader = new ArrayList<>();
		try {
			AbstractSelect grid = (AbstractSelect) getComponentData().getCustomData();
			@SuppressWarnings("unchecked")
			ExtContainer<GtnWsRecordBean> container = (ExtContainer<GtnWsRecordBean>) grid.getContainerDataSource();
			recordHeader = container.getRecordHeader();
		} catch (Exception typeException) {
			throw new GtnFrameworkValidationFailedException(componentId, typeException);
		}
		return recordHeader;
	}

	public Class<?> getTableColumnProperty(String propertyId) throws GtnFrameworkValidationFailedException {
		Class<?> type;
		try {
			AbstractSelect grid = (AbstractSelect) getComponentData().getCustomData();
			@SuppressWarnings("unchecked")
			ExtContainer<GtnWsRecordBean> container = (ExtContainer<GtnWsRecordBean>) grid.getContainerDataSource();
			type = container.getColumnProperty(propertyId);
		} catch (Exception typeException) {
			throw new GtnFrameworkValidationFailedException(componentId, typeException);
		}
		return type;
	}

	@SuppressWarnings("unchecked")
	public void setTableColumnValue(Object itemId, Object propertyId, Object value)
			throws GtnFrameworkValidationFailedException {
		try {
			AbstractSelect grid = (AbstractSelect) getComponentData().getCustomData();
			ExtContainer<GtnWsRecordBean> container = (ExtContainer<GtnWsRecordBean>) grid.getContainerDataSource();
			container.getContainerProperty(itemId, propertyId).setValue(value);
		} catch (Exception typeException) {
			throw new GtnFrameworkValidationFailedException(componentId, typeException);
		}
	}

	public void setTableRefresh(boolean refresh) throws GtnFrameworkValidationFailedException {
		try {
			ExtCustomTable table = (ExtCustomTable) getComponentData().getCustomData();
			table.setRefresh(refresh);
		} catch (Exception typeException) {
			throw new GtnFrameworkValidationFailedException(componentId, typeException);
		}
	}

	public List<GtnWebServiceSearchCriteria> getPagedTableSearchCriteriaList() {
		if (getComponentConfig().getComponentType().getGtnComponent() instanceof GtnUIFrameworkPagedTableComponent) {
			return ((GtnUIFrameworkPagedTableComponent) (getComponentConfig().getComponentType().getGtnComponent()))
					.getPagedTableSearchCriteriaList(getComponentData());
		}
		if (getComponentConfig().getComponentType()
				.getGtnComponent() instanceof GtnUIFrameworkNewPagedTreeTableComponent) {
			return ((GtnUIFrameworkNewPagedTreeTableComponent) (getComponentConfig().getComponentType()
					.getGtnComponent())).getPagedTableSearchCriteriaList(getComponentData());
		}
		return Collections.emptyList();
	}

	public void setTableColumnHeader(Object propertyId, String header) {
		ExtCustomTable resultTable = (ExtCustomTable) getComponentData().getCustomData();
		resultTable.setColumnHeader(propertyId, header);
	}

	public void setTableColumns(Object[] propertyIds) {
		ExtCustomTable resultTable = (ExtCustomTable) getComponentData().getCustomData();
		resultTable.setVisibleColumns(propertyIds);
	}

	public Object[] getTableColumns() {
		ExtCustomTable resultTable = (ExtCustomTable) getComponentData().getCustomData();
		return resultTable.getVisibleColumns();
	}

	public void setTableColumnHeaders(String[] headers) {
		ExtCustomTable resultTable = (ExtCustomTable) getComponentData().getCustomData();
		resultTable.setColumnHeaders(headers);
	}

	public void setCaption(String caption) {

		component.setCaption(caption);
	}

	@SuppressWarnings("rawtypes")
	public void setPagedTableHeaderCheckBox(boolean isCheckAll, String propertyId) {
		GtnUIFrameworkComponentData componentData = this.getComponentData();
		ExtPagedTable resultsTable = (ExtPagedTable) componentData.getCustomData();
		resultsTable.setColumnCheckBox(propertyId, true, isCheckAll);
	}

	@SuppressWarnings("unchecked")
	public void setPropertyValue(Object value) {
		Property<Object> vaadinProperty = (Property<Object>) getComponent();
		boolean isReadOnly = vaadinProperty.isReadOnly();
		vaadinProperty.setReadOnly(false);
		vaadinProperty.setValue(GtnUIFrameworkGlobalUI.getConvertedPropertyValue(vaadinProperty.getType(), value));
		vaadinProperty.setReadOnly(isReadOnly);
	}

	@SuppressWarnings({ "unchecked" })
	public void loadFieldValue(Object newValue) {
		((Field<Object>) this.component).setValue(getString(newValue));
	}

	public void setComponentEnable(boolean newValue) {
		this.component.setEnabled(newValue);
	}

	public void setComponentVisible(boolean newValue) {
		this.component.setVisible(newValue);
	}

	public void setTabComponentVisible(int tabIndex, boolean newValue) {
		TabSheet tabsheet = (TabSheet) this.component;
		tabsheet.getTab(tabIndex).setVisible(newValue);
	}

	@SuppressWarnings({ "unchecked" })
	public void loadDateValue(Object newValue) {
		((Field<Object>) this.component).setValue(newValue);
	}

	@SuppressWarnings({ "unchecked" })
	public void loadComboBoxComponentValue(Integer newValue) {
		((Field<Object>) this.component).setValue(newValue);
	}

	public void setComponentReadOnly(boolean newValue) {
		if(this.component instanceof  Field<?>) {
			(( Field<?>)this.component).setReadOnly(newValue);
		}else if(this.component instanceof  DateField) {
		((DateField)this.component).setReadOnly(newValue);
		}
		
	}

	public boolean isReadOnly() {
		boolean isReadable=true;
		if(this.component instanceof  Field<?>) {
			isReadable=(( Field<?>)this.component).isReadOnly();
		}else if(this.component instanceof  DateField) {
			isReadable=((DateField)this.component).isReadOnly();
			}
		return isReadable;
	}

	public void closeUI() {
		getComponent().getUI().close();
	}

	public boolean isValidFieldValue() {
		if (getComponent() instanceof Field<?>) {
			return ((Field<?>) getComponent()).isValid();
		}
		return false;
	}

	public void setCalendarFieldRangeStart(Date date) {
		((CalendarField) getComponent()).setRangeStart(date);
	}

	public void setCalendarFieldRangeEnd(Date date) {
		((CalendarField) getComponent()).setRangeEnd(date);
	}

	public void clearAllCalendarValue() {
		((CalendarField) getComponent()).clearAllValue();
	}

	public void setSelectedWeekDays(int... days) {
		if (getComponent() instanceof CalendarField) {
			((GtnUIFrameworkCalendarComponent) (getComponentConfig().getComponentType().getGtnComponent()))
					.setSelectedWeekDays((CalendarField) getComponent(), days);
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void loadContainer(String componentId, List<GtnWsRecordBean> resultLiist) {
		GtnUIFrameworkComponentData componentData;
		componentData = (GtnUIFrameworkComponentData) GtnUIFrameworkGlobalUI.getVaadinComponent(componentId).getData();
		ExtFilterTable resultTable = (ExtFilterTable) componentData.getCustomData();
		ExtContainer container = ((ExtContainer) resultTable.getContainerDataSource());
		container.removeAllItems();
		container.addAll(resultLiist);
	}

	public void selectOptionGroupValue(String itemId) {
		((OptionGroup) this.component).select(itemId);
	}

	public void refreshNotesTab() {
		GtnUIFrameworkNotesTab notesTab = (GtnUIFrameworkNotesTab) getComponent();
		notesTab.resetAddMode();
		notesTab.removeAndDisablingComponents(false);
	}

	public void setUnselect(String newValue) {
		((OptionGroup) this.component).unselect(newValue);
	}

	public String getCaption() {
		return this.component.getCaption();
	}

	private Tab getTabConfigbyComponentId(String tabComponentId) {
		AbstractComponent tabComponent = GtnUIFrameworkGlobalUI.getVaadinComponent(tabComponentId);
		return getAsTabSheet().getTab(tabComponent);
	}

	public void setSelectedTab(String tabComponentId) {
		getAsTabSheet().setSelectedTab(getTabConfigbyComponentId(tabComponentId));
	}

	public void removeAllItemsFromComboBox() {
		((ComboBox) this.component).removeAllItems();
	}

	public void setTableFieldEditable(boolean newValue) {
		ExtFilterTable resultTable = (ExtFilterTable) getComponentData().getCustomData();
		resultTable.setEditable(newValue);
	}

	public void loadDefaultComboboxAll(List<?> itemIdToAdd, Object itemIdToSelect) {
		ComboBox vaadinComboBox = (ComboBox) getComponent();
		vaadinComboBox.removeAllItems();
		vaadinComboBox.addItems(itemIdToAdd);
		vaadinComboBox.setNullSelectionAllowed(Boolean.TRUE);
		vaadinComboBox.setNullSelectionItemId(0);
		vaadinComboBox.select(itemIdToSelect);
	}

	public int getExtPagedTableSize() {
		ExtFilterTable resultTable = (ExtFilterTable) getComponentData().getCustomData();
		return resultTable.size();
	}

	public void setSelectedTabByPostion(int tabPosition) {
		((TabSheet) this.getComponent()).setSelectedTab(tabPosition);
	}

	public void resetTable() {
		resetFilter();
		ExtFilterTable resultTable = (ExtFilterTable) getComponentData().getCustomData();
		resultTable.removeAllItems();
	}

	public void setNotesTabEnable(boolean isEditable) {
		GtnUIFrameworkNotesTab notesTab = (GtnUIFrameworkNotesTab) getComponent();
		notesTab.setNotesTabEnable(isEditable);
	}

	public void resetMassupdateCombobox(List<?> itemIdToAdd) {
		ComboBox vaadinComboBox = (ComboBox) getComponent();
		vaadinComboBox.removeAllItems();
		vaadinComboBox.setNullSelectionAllowed(Boolean.TRUE);
		vaadinComboBox.setNullSelectionItemId(GtnFrameworkCommonStringConstants.SELECT_ONE);
		vaadinComboBox.addItem(GtnFrameworkCommonStringConstants.SELECT_ONE);
		vaadinComboBox.addItems(itemIdToAdd);
	}

	public void setTextFieldValue(String value) {
		((CustomTextField) this.component).setValue(value);
	}

	public void setUploaderValue(String value) {
		GtnUIFrameworkNotesTab notesTab = (GtnUIFrameworkNotesTab) getComponent();
		notesTab.setUploaderValue(value);
		notesTab.setFileNameField(value);
	}

	public void selectRow(GtnWsRecordBean valueFromPagedDataTable) {
		ExtPagedTable<?> extPagedTable = (ExtPagedTable<?>) this.getComponentData().getCustomData();
		extPagedTable.select(valueFromPagedDataTable);
	}

	@SuppressWarnings("unchecked")
	public void setContainerProperty(Object itemId, String propertyId, Object value) {
		ExtPagedTable<?> itemsTable = (ExtPagedTable<?>) this.getComponentData().getCustomData();
		itemsTable.getContainerProperty(itemId, propertyId).setValue(value);
	}

	public void setTableReadOnly(boolean isReadOnly) {

		ExtFilterTable extTable = (ExtFilterTable) this.getComponentData().getCustomData();
		extTable.setReadOnly(isReadOnly);
	}

	public void removeAllItemsFromExtFilterTable() throws GtnFrameworkValidationFailedException {
		try {
			ExtFilterTable extCustomTable = (ExtFilterTable) this.getComponentData().getCustomData();
			extCustomTable.getContainerDataSource().removeAllItems();
		} catch (Exception typeException) {
			throw new GtnFrameworkValidationFailedException(componentId, typeException);
		}

	}

	public boolean getTableColumnCheckboxValue(String columnId) {
		return getExtCustomTable().getColumnCheckBox(columnId);
	}

	public void resetTableFiter() {
		ExtFilterTable resultTable = (ExtFilterTable) this.getComponentData().getCustomData();
		resultTable.resetFilters();
	}

	public void browserExtend(BrowserWindowOpener opener) {
		opener.extend(this.getComponent());

	}

	public void removeAllItemsFromTable() {
		ExtCustomTable customTable = (ExtCustomTable) this.getComponentData().getCustomData();
		customTable.removeAllItems();

	}

	public boolean isComponentPresent() {
		return this.component == null ? false : true;
	}

	public void selectOptionGroupValue(String itemId, boolean isEnabled) {
		((OptionGroup) this.component).setItemEnabled(itemId, isEnabled);
	}

	public void loadMassUpdateComboBox(List<String> itemCodeList, List<String> itemValueList) {
		ComboBox massUpdateComboBox = ((ComboBox) this.component);
		massUpdateComboBox.removeAllItems();
		massUpdateComboBox.setNullSelectionAllowed(Boolean.TRUE);
		massUpdateComboBox.setNullSelectionItemId(0);
		massUpdateComboBox.addItem(0);
		massUpdateComboBox.setItemCaption(0, GtnFrameworkCommonStringConstants.SELECT_ONE);
		massUpdateComboBox.select(0);

		if (itemCodeList != null) {
			List<Integer> integerList = new ArrayList<>();
			for (String str : itemCodeList) {
				integerList.add(Integer.parseInt(str));
			}
			massUpdateComboBox.addItems(integerList);
			for (int valueIndex = 0; valueIndex < itemCodeList.size(); valueIndex++) {
				massUpdateComboBox.setItemCaption(Integer.parseInt(itemCodeList.get(valueIndex)),
						itemValueList.get(valueIndex));
			}

		}

	}

	public void loadComboBoxComponentValueWithDes(String newValue) {
		ComboBox comboBox = ((ComboBox) this.component);
		for (Object item : comboBox.getItemIds()) {
			if (newValue.equals(comboBox.getItemCaption(item))) {
				comboBox.setValue(item);
				comboBox.select(item);
			}
		}

	}

	public void setWidth(String widthString) {
		this.component.setWidth(widthString);
	}
        
}
