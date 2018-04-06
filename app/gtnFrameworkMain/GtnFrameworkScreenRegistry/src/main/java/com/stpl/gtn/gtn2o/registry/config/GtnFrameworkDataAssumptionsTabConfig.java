package com.stpl.gtn.gtn2o.registry.config;

import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;

public class GtnFrameworkDataAssumptionsTabConfig {

	public void addDataAssumptionsLayout(List<GtnUIFrameworkComponentConfig> dataAssumptionsTabConfigList, String nameSpace) {
		addDataAssumptionsPanel(dataAssumptionsTabConfigList, nameSpace);
		addDataAssumptionsButtonLayout(dataAssumptionsTabConfigList, nameSpace);
	}

	public void addDataAssumptionsPanel(List<GtnUIFrameworkComponentConfig> dataAssumptionsTabConfigList, String nameSpace) {
		GtnUIFrameworkComponentConfig dataAssumptionsPanel = new GtnUIFrameworkComponentConfig();
		dataAssumptionsPanel.setComponentType(GtnUIFrameworkComponentType.PANEL);
		dataAssumptionsPanel.setComponentName("File Information");
		dataAssumptionsPanel.setComponentId(nameSpace + "_" + "fileInformation");
		dataAssumptionsPanel.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);	
		dataAssumptionsPanel.setAddToParent(Boolean.FALSE);
		dataAssumptionsPanel.setSpacing(true);
		dataAssumptionsTabConfigList.add(dataAssumptionsPanel);
		
		addDataAssumptionsPagedTableComponent(dataAssumptionsTabConfigList, dataAssumptionsPanel.getComponentId(), nameSpace);
	}

	public void addDataAssumptionsPagedTableComponent(List<GtnUIFrameworkComponentConfig> dataAssumptionsTabConfigList, String parentComponentId, String nameSpace) {
		GtnUIFrameworkComponentConfig dataAssumptionsPagedTableComponent =new GtnUIFrameworkComponentConfig();
		dataAssumptionsPagedTableComponent.setComponentType(GtnUIFrameworkComponentType.PAGEDTABLE);
		dataAssumptionsPagedTableComponent.setAuthorizationIncluded(true);
		dataAssumptionsPagedTableComponent.setComponentName("Data Assumptions Paged Table");											/*Ask*/
		dataAssumptionsPagedTableComponent.setComponentId(nameSpace + "_" + "dataAssumptionsPagedTableComponent");
		dataAssumptionsPagedTableComponent.setAddToParent(Boolean.TRUE);
		dataAssumptionsPagedTableComponent.setParentComponentId(parentComponentId);
		dataAssumptionsPagedTableComponent.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		
		dataAssumptionsTabConfigList.add(dataAssumptionsPagedTableComponent);
		
		GtnUIFrameworkPagedTableConfig dataAssumptionsPagedTableConfig = new GtnUIFrameworkPagedTableConfig();
		dataAssumptionsPagedTableConfig.setEditable(false);
		dataAssumptionsPagedTableConfig.setFilterBar(true);
		dataAssumptionsPagedTableConfig.setSelectable(true);
		dataAssumptionsPagedTableConfig.setItemPerPage(15);
		dataAssumptionsPagedTableConfig.setPageLength(5);
		dataAssumptionsPagedTableConfig.setSinkItemPerPageWithPageLength(false);
		dataAssumptionsPagedTableConfig.setTableColumnDataType(new Class<?>[] { GtnFrameworkCommonConstants.JAVALANG_STRING,
				GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING,
				GtnFrameworkCommonConstants.JAVALANG_STRING,GtnFrameworkCommonConstants.JAVA_LANG_INTEGER,
				GtnFrameworkCommonConstants.JAVA_UTIL_DATE,GtnFrameworkCommonConstants.JAVA_UTIL_DATE,
				GtnFrameworkCommonConstants.JAVA_LANG_STRING});
		dataAssumptionsPagedTableConfig.setTableVisibleHeader(new String[] { "File", "Company", "Business Unit", "Type","Version","Active From","From Period","To Period" });
		dataAssumptionsPagedTableConfig.setTableColumnMappingId(new Object[] {"file", "company", "businessUnit", "type","version","activeFrom","fromPeriod","toPeriod"});
		dataAssumptionsPagedTableComponent.setGtnPagedTableConfig(dataAssumptionsPagedTableConfig);
	}	

	public void addDataAssumptionsButtonLayout(List<GtnUIFrameworkComponentConfig> componentList, String nameSpace) {
		
		GtnUIFrameworkComponentConfig dataAssumptionsButtonLayoutConfig=new GtnUIFrameworkComponentConfig();
		dataAssumptionsButtonLayoutConfig.setComponentType(GtnUIFrameworkComponentType.PAGEDTABLE);
		dataAssumptionsButtonLayoutConfig.setAuthorizationIncluded(true);
		dataAssumptionsButtonLayoutConfig.setComponentId(nameSpace + "_" + "dataAssumptionsButtonLayout");
		dataAssumptionsButtonLayoutConfig.setAddToParent(Boolean.FALSE);
		dataAssumptionsButtonLayoutConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		
		componentList.add(dataAssumptionsButtonLayoutConfig);
		
		addDataAssumptionsButtonLayoutComponent(componentList, dataAssumptionsButtonLayoutConfig.getComponentId(), nameSpace);
	}

	public void addDataAssumptionsButtonLayoutComponent(List<GtnUIFrameworkComponentConfig> componentList, String parentComponentId, String nameSpace) {
		
		GtnUIFrameworkComponentConfig updateButtonConfig = new GtnUIFrameworkComponentConfig();
		updateButtonConfig.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		updateButtonConfig.setComponentId(nameSpace + "_" + "updateButtonConfig");
		updateButtonConfig.setComponentName("UPDATE");
		updateButtonConfig.setAddToParent(true);
		updateButtonConfig.setParentComponentId(parentComponentId);
		
		GtnUIFrameworkComponentConfig previousButtonConfig = new GtnUIFrameworkComponentConfig();
		previousButtonConfig.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		previousButtonConfig.setComponentId(nameSpace + "_" + "previousButtonConfig");
		previousButtonConfig.setComponentName("PREVIOUS");
		previousButtonConfig.setAddToParent(true);
		previousButtonConfig.setParentComponentId(parentComponentId);

		GtnUIFrameworkComponentConfig nextButtonConfig = new GtnUIFrameworkComponentConfig();
		nextButtonConfig.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		nextButtonConfig.setComponentId(nameSpace + "_" + "nextButtonConfig");
		nextButtonConfig.setComponentName("NEXT");
		nextButtonConfig.setAddToParent(true);
		nextButtonConfig.setParentComponentId(parentComponentId);

		GtnUIFrameworkComponentConfig closeButtonConfig = new GtnUIFrameworkComponentConfig();
		closeButtonConfig.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		closeButtonConfig.setComponentId(nameSpace + "_" + "closeButtonConfig");
		closeButtonConfig.setComponentName("CLOSE");
		closeButtonConfig.setAddToParent(true);
		closeButtonConfig.setParentComponentId(parentComponentId);
		
		GtnUIFrameworkComponentConfig submitButtonConfig = new GtnUIFrameworkComponentConfig();
		submitButtonConfig.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		submitButtonConfig.setComponentId(nameSpace + "_" + "submitButtonConfig");
		submitButtonConfig.setComponentName("SUBMIT");
		submitButtonConfig.setAddToParent(true);
		submitButtonConfig.setParentComponentId(parentComponentId);

		componentList.add(updateButtonConfig);
		componentList.add(previousButtonConfig);
		componentList.add(nextButtonConfig);
		componentList.add(closeButtonConfig);
		componentList.add(submitButtonConfig);
		
	}

}
