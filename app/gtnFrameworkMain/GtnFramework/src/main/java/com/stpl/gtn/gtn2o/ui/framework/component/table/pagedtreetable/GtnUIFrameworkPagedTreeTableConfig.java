package com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtreetable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;

/**
 *
 * @author Mohamed.Shahul
 */
public class GtnUIFrameworkPagedTreeTableConfig {

	private String[] leftTableVisibleHeader = null;
	private String[] rightTableVisibleHeader = null;
	private String[] tableColumnDataType = null;
	private Object[] leftTableColumnMappingId = null;
	private Object[] rightTableColumnMappingId = null;

	private String tableHeight = null;
	private String countUrl = null;
	private String resultSetUrl = null;
	private List<String> leftTableEditableColumnList = null;
	private List<String> rightTableEditableColumnList = null;
	private List<GtnUIFrameworkComponentConfig> leftTableEditableComponentConfig = null;
	private List<GtnUIFrameworkComponentConfig> rightTableEditableComponentConfig = null;
	private String[] keyFieldFactory = null;
	private String[] valueFieldFactory = null;
	private boolean doubleHeaderVisible = false;

	private float splitPosition = 300;

	private float maxSplitPosition = 1000;

	private float minSplitPosition = 200;

	private int pageLength = 0;

	private int itemPerPage = 0;

	private List<Object> leftTableDoubleHeaderVisibleColumns = new ArrayList<>();

	private List<Object> rightTableDoubleHeaderVisibleColumns = new ArrayList<>();

	private List<String> leftTableDoubleHeaderVisibleHeaders = new ArrayList<>();

	private List<String> rightTableDoubleVisibleHeaders = new ArrayList<>();

	private Map<Object, Object[]> leftTableDoubleHeaderMap = new HashMap<>();

	private Map<Object, Object[]> rightTableDoubleHeaderMap = new HashMap<>();

	private Map<Object, String> singleHeaderMap = new HashMap<>();

	private boolean leftTableEditable = false;

	private boolean rightTableEditable = false;

	private String leftTableCustomFieldFactoryClassname = null;

	private String rightTableCustomFieldFactoryClassname = null;

	private List<GtnUIFrameWorkActionConfig> checkBoxActionConfigList = null;

	private List<String> checkBoxVisibleColoumn = new ArrayList<>();

	private String countWsUrl = null;

	private String leftHeader = null;

	private String rightHeader = null;

	private List<GtnUIFrameWorkActionConfig> componentconfigActionlist = null;

	private String moduleName = null;

	private String bulkDataUrl = null;

	private String bulkDataWsUrl = null;

	private String fillCountUrl = null;

	private String fillCountWsUrl = null;

	private String leftHeaderUrl = null;

	private String leftWsHeaderUrl = null;

	private String righttHeaderUrl = null;

	private String rightWsHeaderUrl = null;

	private String leftHeaderCustomClassLoadUrl = null;

	private String rightHeaderCustomClassLoadUrl = null;

	private GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = null;

	public String getLeftHeader() {
		return leftHeader;
	}

	public void setLeftHeader(String leftHeader) {
		this.leftHeader = leftHeader;
	}

	public String getRightHeader() {
		return rightHeader;
	}

	public void setRightHeader(String rightHeader) {
		this.rightHeader = rightHeader;
	}

	public List<GtnUIFrameWorkActionConfig> getComponentconfigActionlist() {
		return componentconfigActionlist == null ? componentconfigActionlist
				: Collections.unmodifiableList(componentconfigActionlist);
	}

	public void setComponentconfigActionlist(List<GtnUIFrameWorkActionConfig> componentconfigActionlist) {
		this.componentconfigActionlist = new ArrayList<>(componentconfigActionlist);
	}

	public String[] getTableColumnDataType() {
		return tableColumnDataType == null ? tableColumnDataType : tableColumnDataType.clone();
	}

	public void setTableColumnDataType(String[] tableColumnDataType) {
		this.tableColumnDataType = tableColumnDataType.clone();
	}

	public String getCountUrl() {
		return countUrl;
	}

	public void setCountUrl(String countUrl) {
		this.countUrl = countUrl;
	}

	public String getResultSetUrl() {
		return resultSetUrl;
	}

	public void setResultSetUrl(String resultSetUrl) {
		this.resultSetUrl = resultSetUrl;
	}

	public int getPageLength() {
		return pageLength;
	}

	public void setPageLength(int pageLength) {
		this.pageLength = pageLength;
	}

	public int getItemPerPage() {
		return itemPerPage;
	}

	public void setItemPerPage(int itemPerPage) {
		this.itemPerPage = itemPerPage;
	}

	public float getSplitPosition() {
		return splitPosition;
	}

	public void setSplitPosition(float splitPosition) {
		this.splitPosition = splitPosition;
	}

	public float getMaxSplitPosition() {
		return maxSplitPosition;
	}

	public void setMaxSplitPosition(float maxSplitPosition) {
		this.maxSplitPosition = maxSplitPosition;
	}

	public float getMinSplitPosition() {
		return minSplitPosition;
	}

	public void setMinSplitPosition(float minSplitPosition) {
		this.minSplitPosition = minSplitPosition;
	}

	public String[] getLeftTableVisibleHeader() {
		return leftTableVisibleHeader == null ? leftTableVisibleHeader : leftTableVisibleHeader.clone();
	}

	public void setLeftTableVisibleHeader(String[] leftTableVisibleHeader) {
		this.leftTableVisibleHeader = leftTableVisibleHeader.clone();
	}

	public String[] getRightTableVisibleHeader() {
		return rightTableVisibleHeader == null ? rightTableVisibleHeader : rightTableVisibleHeader.clone();
	}

	public void setRightTableVisibleHeader(String[] rightTableVisibleHeader) {
		this.rightTableVisibleHeader = rightTableVisibleHeader.clone();
	}

	public Object[] getLeftTableColumnMappingId() {
		return leftTableColumnMappingId == null ? leftTableColumnMappingId : leftTableColumnMappingId.clone();
	}

	public void setLeftTableColumnMappingId(Object[] leftTableColumnMappingId) {
		this.leftTableColumnMappingId = leftTableColumnMappingId.clone();
	}

	public Object[] getRightTableColumnMappingId() {
		return rightTableColumnMappingId == null ? rightTableColumnMappingId : rightTableColumnMappingId.clone();
	}

	public void setRightTableColumnMappingId(Object[] rightTableColumnMappingId) {
		this.rightTableColumnMappingId = rightTableColumnMappingId.clone();
	}

	public String getTableHeight() {
		return tableHeight;
	}

	public void setTableHeight(String tableHeight) {
		this.tableHeight = tableHeight;
	}

	public String[] getKeyFieldFactory() {
		return keyFieldFactory == null ? keyFieldFactory : keyFieldFactory.clone();
	}

	public void setKeyFieldFactory(String[] keyFieldFactory) {
		this.keyFieldFactory = keyFieldFactory.clone();
	}

	public String[] getValueFieldFactory() {
		return valueFieldFactory == null ? valueFieldFactory : valueFieldFactory.clone();
	}

	public void setValueFieldFactory(String[] valueFieldFactory) {
		this.valueFieldFactory = valueFieldFactory.clone();
	}

	public boolean isDoubleHeaderVisible() {
		return doubleHeaderVisible;
	}

	public void setDoubleHeaderVisible(boolean doubleHeaderVisible) {
		this.doubleHeaderVisible = doubleHeaderVisible;
	}

	public List<Object> getLeftTableDoubleHeaderVisibleColumns() {
		return leftTableDoubleHeaderVisibleColumns == null ? leftTableDoubleHeaderVisibleColumns
				: Collections.unmodifiableList(leftTableDoubleHeaderVisibleColumns);
	}

	public void setLeftTableDoubleHeaderVisibleColumns(List<Object> leftTableDoubleHeaderVisibleColumns) {
		this.leftTableDoubleHeaderVisibleColumns = new ArrayList<>(leftTableDoubleHeaderVisibleColumns);
	}

	public List<Object> getRightTableDoubleHeaderVisibleColumns() {
		return rightTableDoubleHeaderVisibleColumns == null ? rightTableDoubleHeaderVisibleColumns
				: Collections.unmodifiableList(rightTableDoubleHeaderVisibleColumns);
	}

	public void setRightTableDoubleHeaderVisibleColumns(List<Object> rightTableDoubleHeaderVisibleColumns) {
		this.rightTableDoubleHeaderVisibleColumns = new ArrayList<>(rightTableDoubleHeaderVisibleColumns);
	}

	public List<String> getLeftTableDoubleHeaderVisibleHeaders() {
		return leftTableDoubleHeaderVisibleHeaders == null ? leftTableDoubleHeaderVisibleHeaders
				: Collections.unmodifiableList(leftTableDoubleHeaderVisibleHeaders);
	}

	public void setLeftTableDoubleHeaderVisibleHeaders(List<String> leftTableDoubleHeaderVisibleHeaders) {
		this.leftTableDoubleHeaderVisibleHeaders = new ArrayList<>(leftTableDoubleHeaderVisibleHeaders);
	}

	public List<String> getRightTableDoubleVisibleHeaders() {
		return rightTableDoubleVisibleHeaders == null ? rightTableDoubleVisibleHeaders
				: Collections.unmodifiableList(rightTableDoubleVisibleHeaders);
	}

	public void setRightTableDoubleVisibleHeaders(List<String> rightTableDoubleVisibleHeaders) {
		this.rightTableDoubleVisibleHeaders = new ArrayList<>(rightTableDoubleVisibleHeaders);
	}

	public Map<Object, Object[]> getLeftTableDoubleHeaderMap() {
		return leftTableDoubleHeaderMap;
	}

	public void setLeftTableDoubleHeaderMap(Map<Object, Object[]> leftTableDoubleHeaderMap) {
		this.leftTableDoubleHeaderMap = leftTableDoubleHeaderMap;
	}

	public Map<Object, Object[]> getRightTableDoubleHeaderMap() {
		return rightTableDoubleHeaderMap;
	}

	public void setRightTableDoubleHeaderMap(Map<Object, Object[]> rightTableDoubleHeaderMap) {
		this.rightTableDoubleHeaderMap = rightTableDoubleHeaderMap;
	}

	public Map<Object, String> getSingleHeaderMap() {
		return singleHeaderMap;
	}

	public void setSingleHeaderMap(Map<Object, String> singleHeaderMap) {
		this.singleHeaderMap = singleHeaderMap;
	}

	public boolean isLeftTableEditable() {
		return leftTableEditable;
	}

	public void setLeftTableEditable(boolean leftTableEditable) {
		this.leftTableEditable = leftTableEditable;
	}

	public boolean isRightTableEditable() {
		return rightTableEditable;
	}

	public void setRightTableEditable(boolean rightTableEditable) {
		this.rightTableEditable = rightTableEditable;
	}

	public String getLeftTableCustomFieldFactoryClassname() {
		return leftTableCustomFieldFactoryClassname;
	}

	public void setLeftTableCustomFieldFactoryClassname(String leftTableCustomFieldFactoryClassname) {
		this.leftTableCustomFieldFactoryClassname = leftTableCustomFieldFactoryClassname;
	}

	public String getRightTableCustomFieldFactoryClassname() {
		return rightTableCustomFieldFactoryClassname;
	}

	public void setRightTableCustomFieldFactoryClassname(String rightTableCustomFieldFactoryClassname) {
		this.rightTableCustomFieldFactoryClassname = rightTableCustomFieldFactoryClassname;
	}

	public List<String> getLeftTableEditableColumnList() {
		return leftTableEditableColumnList == null ? leftTableEditableColumnList
				: Collections.unmodifiableList(leftTableEditableColumnList);
	}

	public void setLeftTableEditableColumnList(List<String> leftTableEditableColumnArray) {
		this.leftTableEditableColumnList = new ArrayList<>(leftTableEditableColumnArray);
	}

	public List<String> getRightTableEditableColumnList() {
		return rightTableEditableColumnList == null ? rightTableEditableColumnList
				: Collections.unmodifiableList(rightTableEditableColumnList);
	}

	public void setRightTableEditableColumnList(List<String> rightTableEditableColumnArray) {
		this.rightTableEditableColumnList = new ArrayList<>(rightTableEditableColumnArray);
	}

	public List<GtnUIFrameworkComponentConfig> getLeftTableEditableComponentConfig() {
		return leftTableEditableComponentConfig == null ? leftTableEditableComponentConfig
				: Collections.unmodifiableList(leftTableEditableComponentConfig);
	}

	public void setLeftTableEditableComponentConfig(
			List<GtnUIFrameworkComponentConfig> leftTableEditableComponentConfig) {
		this.leftTableEditableComponentConfig = new ArrayList<>(leftTableEditableComponentConfig);
	}

	public List<GtnUIFrameworkComponentConfig> getRightTableEditableComponentConfig() {
		return rightTableEditableComponentConfig == null ? rightTableEditableComponentConfig
				: Collections.unmodifiableList(rightTableEditableComponentConfig);
	}

	public void setRightTableEditableComponentConfig(
			List<GtnUIFrameworkComponentConfig> rightTableEditableComponentConfig) {
		this.rightTableEditableComponentConfig = new ArrayList<>(rightTableEditableComponentConfig);
	}

	public List<GtnUIFrameWorkActionConfig> getCheckBoxActionConfigList() {
		return checkBoxActionConfigList == null ? checkBoxActionConfigList
				: Collections.unmodifiableList(checkBoxActionConfigList);
	}

	public void setCheckBoxActionConfigList(List<GtnUIFrameWorkActionConfig> checkBoxActionConfigList) {
		this.checkBoxActionConfigList = new ArrayList<>(checkBoxActionConfigList);
	}

	public List<String> getCheckBoxVisibleColoumn() {
		return checkBoxVisibleColoumn == null ? checkBoxVisibleColoumn
				: Collections.unmodifiableList(checkBoxVisibleColoumn);
	}

	public void setCheckBoxVisibleColoumn(List<String> checkBoxVisibleColoumn) {
		this.checkBoxVisibleColoumn = new ArrayList<>(checkBoxVisibleColoumn);
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getBulkDataUrl() {
		return bulkDataUrl;
	}

	public void setBulkDataUrl(String bulkDataUrl) {
		this.bulkDataUrl = bulkDataUrl;
	}

	public String getFillCountUrl() {
		return fillCountUrl;
	}

	public void setFillCountUrl(String fillCountUrl) {
		this.fillCountUrl = fillCountUrl;
	}

	public String getFillCountWsUrl() {
		return fillCountWsUrl;
	}

	public void setFillCountWsUrl(String fillCountWsUrl) {
		this.fillCountWsUrl = fillCountWsUrl;
	}

	public String getCountWsUrl() {
		return countWsUrl;
	}

	public void setCountWsUrl(String countWsUrl) {
		this.countWsUrl = countWsUrl;
	}

	public String getBulkDataWsUrl() {
		return bulkDataWsUrl;
	}

	public void setBulkDataWsUrl(String bulkDataWsUrl) {
		this.bulkDataWsUrl = bulkDataWsUrl;
	}

	public String getLeftHeaderUrl() {
		return leftHeaderUrl;
	}

	public void setLeftHeaderUrl(String leftHeaderUrl) {
		this.leftHeaderUrl = leftHeaderUrl;
	}

	public String getLeftWsHeaderUrl() {
		return leftWsHeaderUrl;
	}

	public void setLeftWsHeaderUrl(String leftWsHeaderUrl) {
		this.leftWsHeaderUrl = leftWsHeaderUrl;
	}

	public String getRighttHeaderUrl() {
		return righttHeaderUrl;
	}

	public void setRighttHeaderUrl(String righttHeaderUrl) {
		this.righttHeaderUrl = righttHeaderUrl;
	}

	public String getRightWsHeaderUrl() {
		return rightWsHeaderUrl;
	}

	public void setRightWsHeaderUrl(String rightWsHeaderUrl) {
		this.rightWsHeaderUrl = rightWsHeaderUrl;
	}

	public String getLeftHeaderCustomClassLoadUrl() {
		return leftHeaderCustomClassLoadUrl;
	}

	public void setLeftHeaderCustomClassLoadUrl(String leftHeaderCustomClassLoadUrl) {
		this.leftHeaderCustomClassLoadUrl = leftHeaderCustomClassLoadUrl;
	}

	public String getRightHeaderCustomClassLoadUrl() {
		return rightHeaderCustomClassLoadUrl;
	}

	public void setRightHeaderCustomClassLoadUrl(String rightHeaderCustomClassLoadUrl) {
		this.rightHeaderCustomClassLoadUrl = rightHeaderCustomClassLoadUrl;
	}

	public GtnUIFrameWorkActionConfig getGtnUIFrameWorkActionConfig() {
		return gtnUIFrameWorkActionConfig;
	}

	public void setGtnUIFrameWorkActionConfig(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig) {
		this.gtnUIFrameWorkActionConfig = gtnUIFrameWorkActionConfig;
	}

}
