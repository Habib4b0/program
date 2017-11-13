package com.stpl.gtn.gtn2o.ui.framework.component.excelbutton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;

public class GtnUIFrameworkExcelButtonConfig {

	private boolean htmlContentAllowed = true;
	private String description = GtnFrameworkCommonStringConstants.EXPORT_TO_EXCEL;
	private String iconAlternateText = GtnFrameworkCommonStringConstants.EXCEL_EXPORT;
	private String iconUrl = GtnFrameworkCommonStringConstants.EXCEL_ICON_URL;
	private boolean isTreeTable = false;
	private String exportFileName = GtnFrameworkCommonStringConstants.STRING_EMPTY;
	private String headerServiceUrl = GtnFrameworkCommonStringConstants.STRING_EMPTY;
	private String loadDataServiceUrl = GtnFrameworkCommonStringConstants.STRING_EMPTY;
	private Object[] serviceInput;
	private String serviceType = GtnFrameworkCommonStringConstants.STRING_EMPTY;
	private boolean isTitleNeeded;
	private boolean isNeedTobeCollapsed = true;
	private boolean exportFromTable = false;
	private List<String> excludeColumnsList;
	private List<String> helperTableMapedPropertyIdList;
	private String exportTableId = GtnFrameworkCommonStringConstants.STRING_EMPTY;
	private boolean isNewTreeTable = false;
	private List<GtnWsRecordBean> exportList;
	private boolean writeFileInWebService = true;

	public GtnUIFrameworkExcelButtonConfig() {
		super();
	}

	public boolean isTreeTable() {
		return isTreeTable;
	}

	public void setTreeTable(boolean isTreeTable) {
		this.isTreeTable = isTreeTable;
	}

	public boolean isExportFromTable() {
		return exportFromTable;
	}

	/**
	 * if true table Logic will be used to export
	 * 
	 * @param exportFromTable
	 */
	public void setExportFromTable(boolean exportFromTable) {
		this.exportFromTable = exportFromTable;
	}

	public List<String> getExcludeColumnsList() {
		return excludeColumnsList == null ? excludeColumnsList : Collections.unmodifiableList(excludeColumnsList);
	}

	/**
	 * specified columns will be omitted (Works only if exportFromTable is true)
	 * 
	 * @param excludeColumnsList
	 */
	public void setExcludeColumnsList(List<String> excludeColumnsList) {
		this.excludeColumnsList = new ArrayList<>(excludeColumnsList);
	}

	public String getExportTableId() {
		return exportTableId;
	}

	/**
	 * component Id of table to Export
	 * 
	 * @param exportTableId
	 */
	public void setExportTableId(String exportTableId) {
		this.exportTableId = exportTableId;
	}

	public boolean isHtmlContentAllowed() {
		return htmlContentAllowed;
	}

	public void setHtmlContentAllowed(boolean htmlContentAllowed) {
		this.htmlContentAllowed = htmlContentAllowed;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIconAlternateText() {
		return iconAlternateText;
	}

	public void setIconAlternateText(String iconAlternateText) {
		this.iconAlternateText = iconAlternateText;
	}

	public String getIconUrl() {
		return iconUrl;
	}

	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}

	public boolean isIsTreeTable() {
		return isTreeTable;
	}

	public void setIsTreeTable(boolean isTreeTable) {
		this.isTreeTable = isTreeTable;
	}

	public String getExportFileName() {
		return exportFileName;
	}

	public void setExportFileName(String exportFileName) {
		this.exportFileName = exportFileName;
	}

	public String getHeaderServiceUrl() {
		return headerServiceUrl;
	}

	public void setHeaderServiceUrl(String headerServiceUrl) {
		this.headerServiceUrl = headerServiceUrl;
	}

	public String getLoadDataServiceUrl() {
		return loadDataServiceUrl;
	}

	public void setLoadDataServiceUrl(String loadDataServiceUrl) {
		this.loadDataServiceUrl = loadDataServiceUrl;
	}

	public Object[] getServiceInput() {
		return serviceInput == null ? serviceInput : serviceInput.clone();
	}

	public void setServiceInput(Object[] serviceInput) {
		this.serviceInput = serviceInput.clone();
	}

	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public boolean isTitleNeeded() {
		return isTitleNeeded;
	}

	public void setTitleNeeded(boolean isTitleNeeded) {
		this.isTitleNeeded = isTitleNeeded;
	}

	public List<String> getHelperTableMapedPropertyIdList() {
		return helperTableMapedPropertyIdList == null ? helperTableMapedPropertyIdList
				: Collections.unmodifiableList(helperTableMapedPropertyIdList);
	}

	public void setHelperTableMapedPropertyIdList(List<String> helperTableMapedPropertyIdList) {
		this.helperTableMapedPropertyIdList = new ArrayList<>(helperTableMapedPropertyIdList);
	}

	public boolean isIsNewTreeTable() {
		return isNewTreeTable;
	}

	public void setIsNewTreeTable(boolean isNewTreeTable) {
		this.isNewTreeTable = isNewTreeTable;
	}

	public List<GtnWsRecordBean> getExportList() {
		return exportList == null ? exportList : Collections.unmodifiableList(exportList);
	}

	public void setExportList(List<GtnWsRecordBean> exportList) {
		this.exportList = new ArrayList<>(exportList);
	}

	public boolean isWriteFileInWebService() {
		return writeFileInWebService;
	}

	/***
	 * Set True If Writing Excel Logic Present in Web Service
	 * 
	 * @return
	 */
	public void setWriteFileInWebService(boolean writeFileInWebService) {
		this.writeFileInWebService = writeFileInWebService;
	}

	public boolean isNeedTobeCollapsed() {
		return isNeedTobeCollapsed;
	}

	public void setNeedTobeCollapsed(boolean isNeedTobeCollapsed) {
		this.isNeedTobeCollapsed = isNeedTobeCollapsed;
	}

}
