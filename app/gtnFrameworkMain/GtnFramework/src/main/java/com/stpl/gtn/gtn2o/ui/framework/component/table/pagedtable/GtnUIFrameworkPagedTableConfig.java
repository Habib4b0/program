package com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.filter.GtnUIFrameworkPagedTableCustomFilterConfig;
import com.stpl.gtn.gtn2o.ws.bean.search.GtnWsSearchQueryConfigLoaderType;

public class GtnUIFrameworkPagedTableConfig {

    public GtnUIFrameworkPagedTableConfig() {
        super();
    }

    private String[] tableVisibleHeader = null;
    private Class<?>[] tableColumnDataType = null;
    private String tableColumnDataTypeURL = null;
    private Object[] tableColumnMappingId = null;
    private Object[] extraColumn = null;
    private Class<?>[] extraColumnDataType = null;
    private String countUrl = null;
    private String resultSetUrl = null;
    private boolean filterBar = false;
    private boolean selectable = false;
    private boolean editable = false;
    private boolean itemClickListener = false;
    private String moduleName = "";
    private String queryName = "";
    private int itemPerPage = 10;
    private int pageLength = 10;
    private List<String> editableColumnList = null;
    private List<GtnUIFrameworkComponentConfig> editableField = null;
    private Map<String, GtnUIFrameworkPagedTableCustomFilterConfig> customFilterConfigMap = null;
    private String columnCheckBoxId = "";
    private String customFieldFactoryClassname = null;
    private GtnWsSearchQueryConfigLoaderType searchQueryConfigLoaderType = null;
    private String[] tableDateColumnObject = null;
    private String[] tableDateColumnFormat = null;
    private List<GtnUIFrameWorkActionConfig> gtnUIFrameWorkActionConfigList = new ArrayList<>();
    private boolean doubleClickEnable = false;
    private boolean multiSelect = false;
    private boolean sinkItemPerPageWithPageLength = true;
    private List<GtnUIFrameWorkActionConfig> postCreationActionConfigList = new ArrayList<>();
    private List<GtnUIFrameWorkActionConfig> itemDoubleClickActionConfigList = new ArrayList<>();
    private List<GtnUIFrameWorkActionConfig> itemClickActionConfigList = new ArrayList<>();
    private GtnUIFrameworkTableDragMode dragMode = GtnUIFrameworkTableDragMode.NONE;
    private GtnUIFrameWorkActionConfig recordTypeManageActionConfig = null;
    private List<GtnUIFrameWorkActionConfig> columnCheckActionConfigList = new ArrayList<>();
    private String defaultDateFormat = "MM/dd/yyyy";
    private boolean captionVisible = false;
    private Object[] invisibleFilterPropertyIds = null;
    private List<String> integerFormatPropertyList = null;
    private List<String> generatedColumnList = null;
    private List<GtnUIFrameworkComponentConfig> generatedColumn = null;
    private boolean sortingEnable = true;
    private boolean doubleHeaderVisible = false;
    private Object[] tableDoubleHeaderVisibleColumns = null;
    private String[] tableDoubleHeaderVisibleHeaders = null;
    private Map<Object, Object[]> tableDoubleHeaderMap = new HashMap<>();
    private Object[] doubleHeaderObject = null;
    private String[] doubleHeaderColumnAlignment = null;
    private Object[] columnToAlign = null;
    private String[] columnAlignment = null;
    // added for current history future Query
    private String recordTypeComponentId = null;
    private String recordTypeStartDate = null;
    private String recordTypeEndDate = null;
    private List<String> checkAllColumnList;
    private String countQuery;
    private String dataQuery;
    private HashMap<String, Object> filterValueMap=new HashMap<>();


    public List<String> getColumnHeaders() {
        return columnHeaders;
    }

    public void setColumnHeaders(List<String> columnHeaders) {
        this.columnHeaders = columnHeaders;
    }
    private List<String> columnHeaders;
   
	public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getQueryName() {
        return queryName;
    }

    public void setQueryName(String queryName) {
        this.queryName = queryName;
    }

    public String[] getTableVisibleHeader() {
        return tableVisibleHeader == null ? tableVisibleHeader : tableVisibleHeader.clone();
    }

    public void setTableVisibleHeader(String[] tableVisibleHeader) {
        this.tableVisibleHeader = tableVisibleHeader.clone();
    }

    public Class<?>[] getTableColumnDataType() {
        return tableColumnDataType == null ? tableColumnDataType : tableColumnDataType.clone();
    }

    public void setTableColumnDataType(Class<?>[] tableColumnDataType) {
        this.tableColumnDataType = tableColumnDataType.clone();
    }

    public boolean isFilterBar() {
        return filterBar;
    }

    public void setFilterBar(boolean filterBar) {
        this.filterBar = filterBar;
    }

    public boolean isSelectable() {
        return selectable;
    }

    public void setSelectable(boolean selectable) {
        this.selectable = selectable;
    }

    public boolean isEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
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

    public Object[] getTableColumnMappingId() {
        return tableColumnMappingId == null ? tableColumnMappingId : tableColumnMappingId.clone();
    }

    public void setTableColumnMappingId(Object[] tableColumnMappingId) {
        this.tableColumnMappingId = tableColumnMappingId.clone();
    }

    public Map<String, GtnUIFrameworkPagedTableCustomFilterConfig> getCustomFilterConfigMap() {
        return customFilterConfigMap;
    }

    public void setCustomFilterConfigMap(
            Map<String, GtnUIFrameworkPagedTableCustomFilterConfig> customFilterConfigMap) {
        this.customFilterConfigMap = customFilterConfigMap;
    }

    public int getItemPerPage() {
        return itemPerPage;
    }

    public void setItemPerPage(int itemPerPage) {
        this.itemPerPage = itemPerPage;
    }

    public int getPageLength() {
        return pageLength;
    }

    public void setPageLength(int pageLength) {
        this.pageLength = pageLength;
    }

    public Object[] getExtraColumn() {
        return extraColumn == null ? extraColumn : extraColumn.clone();
    }

    public void setExtraColumn(Object[] extraColumn) {
        this.extraColumn = extraColumn.clone();
    }

    public String getColumnCheckBoxId() {
        return columnCheckBoxId;
    }

    public void setColumnCheckBoxId(String columnCheckBoxId) {
        this.columnCheckBoxId = columnCheckBoxId;
    }

    public String getCustomFieldFactoryClassname() {
        return customFieldFactoryClassname;
    }

    public void setCustomFieldFactoryClassname(String customFieldFactoryClassname) {
        this.customFieldFactoryClassname = customFieldFactoryClassname;
    }

    public GtnWsSearchQueryConfigLoaderType getSearchQueryConfigLoaderType() {
        return searchQueryConfigLoaderType;
    }

    public void setSearchQueryConfigLoaderType(GtnWsSearchQueryConfigLoaderType searchQueryConfigLoaderType) {
        this.searchQueryConfigLoaderType = searchQueryConfigLoaderType;
    }

    public String[] getTableDateColumnObject() {
        return tableDateColumnObject == null ? tableDateColumnObject : tableDateColumnObject.clone();
    }

    public void setTableDateColumnObject(String[] tableDateColumnObject) {
        this.tableDateColumnObject = tableDateColumnObject.clone();
    }

    public String[] getTableDateColumnFormat() {
        return tableDateColumnFormat == null ? tableDateColumnFormat : tableDateColumnFormat.clone();
    }

    public void setTableDateColumnFormat(String[] tableDateColumnFormat) {
        this.tableDateColumnFormat = tableDateColumnFormat.clone();
    }

    public boolean isMultiSelect() {
        return multiSelect;
    }

    public void setMultiSelect(boolean multiSelect) {
        this.multiSelect = multiSelect;
    }

    public List<GtnUIFrameWorkActionConfig> getGtnUIFrameWorkActionConfigList() {
        return gtnUIFrameWorkActionConfigList == null ? gtnUIFrameWorkActionConfigList
                : Collections.unmodifiableList(gtnUIFrameWorkActionConfigList);
    }

    public void setGtnUIFrameWorkActionConfigList(List<GtnUIFrameWorkActionConfig> gtnUIFrameWorkActionConfigList) {
        this.gtnUIFrameWorkActionConfigList = new ArrayList<>(gtnUIFrameWorkActionConfigList);
    }

    public boolean isItemClickListener() {
        return itemClickListener;
    }

    public void setItemClickListener(boolean itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public boolean isDoubleClickEnable() {
        return doubleClickEnable;
    }

    public void setDoubleClickEnable(boolean doubleClickEnable) {
        this.doubleClickEnable = doubleClickEnable;
    }

    public List<String> getEditableColumnList() {
        return editableColumnList == null ? editableColumnList : Collections.unmodifiableList(editableColumnList);
    }

    public void setEditableColumnList(List<String> editableColumnList) {
        this.editableColumnList = new ArrayList<>(editableColumnList);
    }

    public List<GtnUIFrameworkComponentConfig> getEditableField() {
        return editableField == null ? editableField : new ArrayList<>(editableField);
    }

    public void setEditableField(List<GtnUIFrameworkComponentConfig> editableField) {
        this.editableField = new ArrayList<>(editableField);
    }

    public void addPostCreationActionConfig(GtnUIFrameWorkActionConfig actionConfig) {
        if (this.postCreationActionConfigList == null) {
            this.postCreationActionConfigList = new ArrayList<>();
        }
        this.postCreationActionConfigList.add(actionConfig);
    }

    public List<GtnUIFrameWorkActionConfig> getPostCreationActionConfigList() {
        return postCreationActionConfigList == null ? postCreationActionConfigList
                : Collections.unmodifiableList(postCreationActionConfigList);
    }

    public void setPostCreationActionConfigList(List<GtnUIFrameWorkActionConfig> postCreationActionConfigList) {
        this.postCreationActionConfigList = new ArrayList<>(postCreationActionConfigList);
    }

    public String getTableColumnDataTypeURL() {
        return tableColumnDataTypeURL;
    }

    public void setTableColumnDataTypeURL(String tableColumnDataTypeURL) {
        this.tableColumnDataTypeURL = tableColumnDataTypeURL;
    }

    public boolean isSinkItemPerPageWithPageLength() {
        return sinkItemPerPageWithPageLength;
    }

    public void setSinkItemPerPageWithPageLength(boolean sinkItemPerPageWithPageLength) {
        this.sinkItemPerPageWithPageLength = sinkItemPerPageWithPageLength;
    }

    public List<GtnUIFrameWorkActionConfig> getItemDoubleClickActionConfigList() {
        return itemDoubleClickActionConfigList == null ? itemDoubleClickActionConfigList
                : Collections.unmodifiableList(itemDoubleClickActionConfigList);
    }

    public void setItemDoubleClickActionConfigList(List<GtnUIFrameWorkActionConfig> itemDoubleClickActionConfigList) {
        this.itemDoubleClickActionConfigList = new ArrayList<>(itemDoubleClickActionConfigList);
    }

    public void addItemDoubleClickActionConfig(GtnUIFrameWorkActionConfig actionConfig) {
        if (this.itemDoubleClickActionConfigList == null) {
            this.itemDoubleClickActionConfigList = new ArrayList<>();
        }
        this.itemDoubleClickActionConfigList.add(actionConfig);
    }

    public List<GtnUIFrameWorkActionConfig> getItemClickActionConfigList() {
        return itemClickActionConfigList == null ? itemClickActionConfigList
                : Collections.unmodifiableList(itemClickActionConfigList);
    }

    public void setItemClickActionConfigList(List<GtnUIFrameWorkActionConfig> itemClickActionConfigList) {
        this.itemClickActionConfigList = new ArrayList<>(itemClickActionConfigList);
    }

    public void addItemClickActionConfig(GtnUIFrameWorkActionConfig actionConfig) {
        if (this.itemClickActionConfigList == null) {
            this.itemClickActionConfigList = new ArrayList<>();
        }
        this.itemClickActionConfigList.add(actionConfig);
    }

    public GtnUIFrameworkTableDragMode getDragMode() {
        return dragMode;
    }

    public void setDragMode(GtnUIFrameworkTableDragMode dragMode) {
        this.dragMode = dragMode;
    }

    public String getDefaultDateFormat() {
        return defaultDateFormat;
    }

    public void setDefaultDateFormat(String defaultDateFormat) {
        this.defaultDateFormat = defaultDateFormat;
    }

    public boolean isCaptionVisible() {
        return captionVisible;
    }

    public void setCaptionVisible(boolean captionVisible) {
        this.captionVisible = captionVisible;
    }

    public GtnUIFrameWorkActionConfig getRecordTypeManageActionConfig() {
        return recordTypeManageActionConfig;
    }

    public void setRecordTypeManageActionConfig(GtnUIFrameWorkActionConfig recordTypeManageActionConfig) {
        this.recordTypeManageActionConfig = recordTypeManageActionConfig;
    }

    public Object[] getInvisibleFilterPropertyIds() {
        return invisibleFilterPropertyIds == null ? invisibleFilterPropertyIds : invisibleFilterPropertyIds.clone();
    }

    public void setInvisibleFilterPropertyIds(Object... invisibleFilterPropertyIds) {
        this.invisibleFilterPropertyIds = invisibleFilterPropertyIds.clone();
    }

    public List<GtnUIFrameWorkActionConfig> getColumnCheckActionConfigList() {
        return columnCheckActionConfigList == null ? columnCheckActionConfigList
                : Collections.unmodifiableList(columnCheckActionConfigList);
    }

    public void setColumnCheckActionConfigList(List<GtnUIFrameWorkActionConfig> columnCheckActionConfigList) {
        this.columnCheckActionConfigList = new ArrayList<>(columnCheckActionConfigList);
    }

    public void addColumnCheckActionConfig(GtnUIFrameWorkActionConfig actionConfig) {
        if (this.columnCheckActionConfigList == null) {
            this.columnCheckActionConfigList = new ArrayList<>();
        }
        this.columnCheckActionConfigList.add(actionConfig);
    }

    public List<String> getIntegerFormatPropertyList() {
        return integerFormatPropertyList == null ? integerFormatPropertyList
                : Collections.unmodifiableList(integerFormatPropertyList);
    }

    public void setIntegerFormatPropertyList(List<String> integerFormatPropertyList) {
        this.integerFormatPropertyList = new ArrayList<>(integerFormatPropertyList);
    }

    public List<String> getGeneratedColumnList() {
        return generatedColumnList == null ? generatedColumnList : Collections.unmodifiableList(generatedColumnList);
    }

    public void setGeneratedColumnList(List<String> generatedColumnList) {
        this.generatedColumnList = new ArrayList<>(generatedColumnList);
    }

    public List<GtnUIFrameworkComponentConfig> getGeneratedColumn() {
        return generatedColumn == null ? generatedColumn : Collections.unmodifiableList(generatedColumn);
    }

    public void setGeneratedColumn(List<GtnUIFrameworkComponentConfig> generatedColumn) {
        this.generatedColumn = new ArrayList<>(generatedColumn);
    }

    public boolean isSortingEnable() {
        return sortingEnable;
    }

    public void setSortingEnable(boolean sortingEnable) {
        this.sortingEnable = sortingEnable;
    }

    public boolean isDoubleHeaderVisible() {
        return doubleHeaderVisible;
    }

    public void setDoubleHeaderVisible(boolean doubleHeaderVisible) {
        this.doubleHeaderVisible = doubleHeaderVisible;
    }

    public Object[] getTableDoubleHeaderVisibleColumns() {
        return tableDoubleHeaderVisibleColumns == null ? tableDoubleHeaderVisibleColumns
                : tableDoubleHeaderVisibleColumns.clone();
    }

    public void setTableDoubleHeaderVisibleColumns(Object[] tableDoubleHeaderVisibleColumns) {
        this.tableDoubleHeaderVisibleColumns = tableDoubleHeaderVisibleColumns.clone();
    }

    public String[] getTableDoubleHeaderVisibleHeaders() {
        return tableDoubleHeaderVisibleHeaders == null ? tableDoubleHeaderVisibleHeaders
                : tableDoubleHeaderVisibleHeaders.clone();
    }

    public void setTableDoubleHeaderVisibleHeaders(String[] tableDoubleHeaderVisibleHeaders) {
        this.tableDoubleHeaderVisibleHeaders = tableDoubleHeaderVisibleHeaders.clone();
    }

    public Map<Object, Object[]> getTableDoubleHeaderMap() {
        return tableDoubleHeaderMap;
    }

    public void setTableDoubleHeaderMap(Map<Object, Object[]> tableDoubleHeaderMap) {
        this.tableDoubleHeaderMap = tableDoubleHeaderMap;
    }

    public Object[] getDoubleHeaderObject() {
        return doubleHeaderObject == null ? doubleHeaderObject : doubleHeaderObject.clone();
    }

    public void setDoubleHeaderObject(Object[] doubleHeaderObject) {
        this.doubleHeaderObject = doubleHeaderObject.clone();
    }

    public String[] getDoubleHeaderColumnAlignment() {
        return doubleHeaderColumnAlignment == null ? doubleHeaderColumnAlignment : doubleHeaderColumnAlignment.clone();
    }

    public void setDoubleHeaderColumnAlignment(String[] doubleHeaderColumnAlignment) {
        this.doubleHeaderColumnAlignment = doubleHeaderColumnAlignment.clone();
    }

    public Class<?>[] getExtraColumnDataType() {
        return extraColumnDataType == null ? extraColumnDataType : extraColumnDataType.clone();
    }

    public void setExtraColumnDataType(Class<?>[] extraColumnDataType) {
        this.extraColumnDataType = extraColumnDataType.clone();
    }

    public Object[] getColumnToAlign() {
        return columnToAlign == null ? null : columnToAlign.clone();
    }

    public void setColumnToAlign(Object[] columnToAlign) {
        this.columnToAlign = columnToAlign.clone();
    }

    public String[] getColumnAlignment() {
        return columnAlignment == null ? null : columnAlignment.clone();
    }

    public void setColumnAlignment(String[] columnAlignment) {
        this.columnAlignment = columnAlignment.clone();
    }

    public String getRecordTypeComponentId() {
        return recordTypeComponentId;
    }

    public void setRecordTypeComponentId(String recordTypeComponentId) {
        this.recordTypeComponentId = recordTypeComponentId;
    }

    public String getRecordTypeStartDate() {
        return recordTypeStartDate;
    }

    public void setRecordTypeStartDate(String recordTypeStartDate) {
        this.recordTypeStartDate = recordTypeStartDate;
    }

    public String getRecordTypeEndDate() {
        return recordTypeEndDate;
    }

    public void setRecordTypeEndDate(String recordTypeEndDate) {
        this.recordTypeEndDate = recordTypeEndDate;
    }

    public List<String> getCheckAllColumnList() {
        return checkAllColumnList == null ? checkAllColumnList : Collections.unmodifiableList(checkAllColumnList);
    }

    public void setCheckAllColumnList(List<String> checkAllColumnList) {
        this.checkAllColumnList = new ArrayList<>(checkAllColumnList);
    }

    public String getCountQuery() {
        return countQuery;
    }

    public void setCountQuery(String countQuery) {
        this.countQuery = countQuery;
    }

    public String getDataQuery() {
        return dataQuery;
    }

    public void setDataQuery(String dataQuery) {
        this.dataQuery = dataQuery;
    }
    Object[] dataQueryInputs;
    Object[] countQueryInputs;

    public Object[] getCountQueryInputs() {
        return countQueryInputs;
    }

    public void setCountQueryInputs(Object[] countQueryInputs) {
        this.countQueryInputs = countQueryInputs;
    }

    public Object[] getDataQueryInputs() {
        return dataQueryInputs;
    }

    public void setDataQueryInputs(Object[] dataQueryInputs) {
        this.dataQueryInputs = dataQueryInputs;
    }

    public HashMap<String, Object> getFilterValueMap() {
        return filterValueMap;
    }

    public void setFilterValueMap(HashMap<String, Object> filterValueMap) {
        this.filterValueMap = filterValueMap;
    }


}
