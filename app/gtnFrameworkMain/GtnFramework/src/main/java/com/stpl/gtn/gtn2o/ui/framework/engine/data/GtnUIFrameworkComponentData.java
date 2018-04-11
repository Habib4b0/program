package com.stpl.gtn.gtn2o.ui.framework.engine.data;

import com.stpl.addons.grid.paged.component.PagedGrid;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponent;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.fieldfactory.GtnUIFrameworkActionParameter;
import com.stpl.gtn.gtn2o.ui.framework.component.table.newpagedtreetable.GtnUIFrameworkNewPagedTreeTableLogic;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableLogic;
import com.stpl.gtn.gtn2o.ui.framework.component.tabsheet.GtnUIFrameworkTabConfig;
import com.stpl.gtn.gtn2o.ui.framework.config.GtnUIFrameworkConfigMap;
import com.stpl.gtn.gtn2o.ui.framework.engine.session.GtnUIFrameworkSessionBean;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkSupportedValidationType;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.asi.ui.customwindow.CustomWindow;

public class GtnUIFrameworkComponentData {

	public GtnUIFrameworkComponentData() {
		super();
	}

	private String viewId;

	private String parentViewId;

	private String currentChildViewId;

	private String componentIdInMap;

	private GtnUIFrameworkViewConfig currentViewConfig;

	private GtnUIFrameworkComponent currentGtnComponent;

	private GtnUIFrameworkConfigMap frameworkConfigMap;

	private GtnUIFrameworkComponentConfig currentComponentConfig;

	private GtnUIFrameworkPagedTableLogic currentPageTableLogic;

	private GtnUIFrameworkNewPagedTreeTableLogic newPageTableLogic;

	private GtnUIFrameworkSessionBean currentSesssionBean;

	private Object customData;

	private List<Object> customDataList;

	private List<GtnUIFrameworkSupportedValidationType> gtnUIFrameworkSupportedValidationTypeList;

	private String fieldFactoryComponentId;

	private GtnUIFrameworkWebserviceRequest customPagedTreeTableRequest;

	private GtnUIFrameworkActionParameter actionParameter;

	private Object sharedPopupData;

	private CustomWindow customWindow;

	private GtnUIFrameworkTabConfig gtnUIFrameworkTabConfig;

	private boolean isTabLoaded = false;

	private int defaultTabsheetTabIndex = 0;

	private List<GtnWsRecordBean> dataTableRecordList;

	private String sourceComponentId;
        
        private PagedGrid  pagedGrid;
        
        GtnUIFrameworkPagedTableConfig tableConfig;

	public String getSourceComponentId() {
		return sourceComponentId;
	}

	public void setSourceComponentId(String sourceComponentId) {
		this.sourceComponentId = sourceComponentId;
	}

	public String getFieldFactoryComponentId() {
		return fieldFactoryComponentId;
	}

	public void setFieldFactoryComponentId(String fieldFactoryComponentId) {
		this.fieldFactoryComponentId = fieldFactoryComponentId;
	}

	public String getViewId() {
		return viewId;
	}

	public void setViewId(String viewId) {
		this.viewId = viewId;
	}

	public GtnUIFrameworkViewConfig getCurrentViewConfig() {
		return currentViewConfig;
	}

	public void setCurrentViewConfig(GtnUIFrameworkViewConfig currentViewConfig) {
		this.currentViewConfig = currentViewConfig;
	}

	public GtnUIFrameworkComponentConfig getCurrentComponentConfig() {
		return currentComponentConfig;
	}

	public void setCurrentComponentConfig(GtnUIFrameworkComponentConfig currentComponentConfig) {
		this.currentComponentConfig = currentComponentConfig;
	}

	public Object getCustomData() {
		return customData;
	}

	public void setCustomData(Object customData) {
		this.customData = customData;
	}

	public List<Object> getCustomDataList() {
		return customDataList == null ? customDataList : Collections.unmodifiableList(customDataList);
	}

	public void setCustomDataList(List<Object> customDataList) {
		this.customDataList = new ArrayList<>(customDataList);
	}

	public GtnUIFrameworkConfigMap getFrameworkConfigMap() {
		return frameworkConfigMap;
	}

	public void setFrameworkConfigMap(GtnUIFrameworkConfigMap frameworkConfigMap) {
		this.frameworkConfigMap = frameworkConfigMap;
	}

	public GtnUIFrameworkComponent getCurrentGtnComponent() {
		return currentGtnComponent;
	}

	public void setCurrentGtnComponent(GtnUIFrameworkComponent currentGtnComponent) {
		this.currentGtnComponent = currentGtnComponent;
	}

	public String getComponentIdInMap() {
		return componentIdInMap;
	}

	public void setComponentIdInMap(String componentIdInMap) {
		this.componentIdInMap = componentIdInMap;
	}

	public GtnUIFrameworkPagedTableLogic getCurrentPageTableLogic() {
		return currentPageTableLogic;
	}

	public void setCurrentPageTableLogic(GtnUIFrameworkPagedTableLogic currentPageTableLogic) {
		this.currentPageTableLogic = currentPageTableLogic;
	}

	public GtnUIFrameworkSessionBean getCurrentSesssionBean() {
		return currentSesssionBean;
	}

	public void setCurrentSesssionBean(GtnUIFrameworkSessionBean currentSesssionBean) {
		this.currentSesssionBean = currentSesssionBean;
	}

	public void addCustomDataToList(Object customData) {
		if (customDataList == null) {
			customDataList = new ArrayList<>();
		}
		customDataList.add(customData);
	}

	public List<GtnUIFrameworkSupportedValidationType> getGtnUIFrameworkSupportedValidationTypeList() {
		return gtnUIFrameworkSupportedValidationTypeList == null ? gtnUIFrameworkSupportedValidationTypeList
				: Collections.unmodifiableList(gtnUIFrameworkSupportedValidationTypeList);
	}

	public void setGtnUIFrameworkSupportedValidationTypeList(
			List<GtnUIFrameworkSupportedValidationType> gtnUIFrameworkSupportedValidationTypeList) {
		this.gtnUIFrameworkSupportedValidationTypeList = new ArrayList<>(gtnUIFrameworkSupportedValidationTypeList);
	}

	public GtnUIFrameworkWebserviceRequest getCustomPagedTreeTableRequest() {
		return customPagedTreeTableRequest;
	}

	public void setCustomPagedTreeTableRequest(GtnUIFrameworkWebserviceRequest customPagedTreeTableRequest) {
		this.customPagedTreeTableRequest = customPagedTreeTableRequest;
	}

	public GtnUIFrameworkActionParameter getActionParameter() {
		return actionParameter;
	}

	public void setActionParameter(GtnUIFrameworkActionParameter actionParameter) {
		this.actionParameter = actionParameter;
	}

	public GtnUIFrameworkNewPagedTreeTableLogic getNewPageTableLogic() {
		return newPageTableLogic;
	}

	public void setNewPageTableLogic(GtnUIFrameworkNewPagedTreeTableLogic newPageTableLogic) {
		this.newPageTableLogic = newPageTableLogic;
	}

	public String getCurrentChildViewId() {
		return currentChildViewId;
	}

	public void setCurrentChildViewId(String currentChildViewId) {
		this.currentChildViewId = currentChildViewId;
	}

	public String getParentViewId() {
		return parentViewId;
	}

	public void setParentViewId(String parentViewId) {
		this.parentViewId = parentViewId;
	}

	public Object getSharedPopupData() {
		return sharedPopupData;
	}

	public void setSharedPopupData(Object sharedPopupData) {
		this.sharedPopupData = sharedPopupData;
	}

	public CustomWindow getCustomWindow() {
		return customWindow;
	}

	public void setCustomWindow(CustomWindow customWindow) {
		this.customWindow = customWindow;
	}

	public GtnUIFrameworkTabConfig getGtnUIFrameworkTabConfig() {
		return gtnUIFrameworkTabConfig;
	}

	public void setGtnUIFrameworkTabConfig(GtnUIFrameworkTabConfig gtnUIFrameworkTabConfig) {
		this.gtnUIFrameworkTabConfig = gtnUIFrameworkTabConfig;
	}

	public boolean isTabLoaded() {
		return isTabLoaded;
	}

	public void setTabLoaded(boolean isTabLoaded) {
		this.isTabLoaded = isTabLoaded;
	}

	public int getDefaultTabsheetTabIndex() {
		return defaultTabsheetTabIndex;
	}

	public void setDefaultTabsheetTabIndex(int defaultTabsheetTabIndex) {
		this.defaultTabsheetTabIndex = defaultTabsheetTabIndex;
	}

	public List<GtnWsRecordBean> getDataTableRecordList() {
		return dataTableRecordList == null ? dataTableRecordList : Collections.unmodifiableList(dataTableRecordList);
	}

	public void setDataTableRecordList(List<GtnWsRecordBean> dataTableRecordList) {
		this.dataTableRecordList = dataTableRecordList == null ? dataTableRecordList
				: new ArrayList<>(dataTableRecordList);
	}

	public void addCustomDataList(Object customData) {
		if (customDataList == null) {
			customDataList = new ArrayList<>();
		}
		this.customDataList.add(customData);

	}

    public PagedGrid getPagedGrid() {
        return pagedGrid;
    }

    public void setPagedGrid(PagedGrid pagedGrid) {
        this.pagedGrid = pagedGrid;
    }

    public GtnUIFrameworkPagedTableConfig getTableConfig() {
        return tableConfig;
    }

    public void setTableConfig(GtnUIFrameworkPagedTableConfig tableConfig) {
        this.tableConfig = tableConfig;
    }
   
}
