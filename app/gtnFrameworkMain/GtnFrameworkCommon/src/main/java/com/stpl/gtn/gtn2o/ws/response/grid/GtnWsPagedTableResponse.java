package com.stpl.gtn.gtn2o.ws.response.grid;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.forecast.bean.DataSelectionBean;
import com.stpl.gtn.gtn2o.ws.forecast.bean.GtnForecastBean;
import com.stpl.gtn.gtn2o.ws.response.forecast.GtnWsForecastResponse;

/**
 * @author Kalpana.Ramanana
 *
 */
public class GtnWsPagedTableResponse {

    public GtnWsPagedTableResponse() {
        super();
    }

    private GtnWsForecastResponse gtnWsForecastResponsePagedTable;

    private List<GtnWsRecordBean> gtnWsRecordBeanListPagedTable;

    private GtnForecastBean gtnForecastBeanPagedTree;

    private List<GtnWsRecordBean> forecastPagedTableBeanListPagedTree;

    private List<String> resultsPagedTree;

    private List<String> editableFieldsPagedTree;

    private List<String> editablePeriodsPagedTree;

    private DataSelectionBean dataSelectionBeanPagedTree;

    private boolean checkAllPagedTree;

    /**
     * The properties.
     */
    @SuppressWarnings("rawtypes")
    private Map<Object, Class> propertiesPagedTree = new HashMap<>();
    /**
     * The single column.
     */
    private List<Object> singleColumnPagedTree = new ArrayList<>();

    /**
     * The single header.
     */
    private List<String> singleHeaderPagedTree = new ArrayList<>();

    /**
     * The double column.
     */
    private List<Object> doubleColumnPagedTree = new ArrayList<>();
    /**
     * The double header.
     */
    private List<String> doubleHeaderPagedTree = new ArrayList<>();

    /**
     * Triple Column
     */
    private List<Object> tripleColumnPagedTree = new ArrayList<>();
    /**
     * Triple Header
     */
    private List<String> tripleHeaderPagedTree = new ArrayList<>();

    /**
     * The double header map.
     */
    private Map<Object, Object[]> doubleHeaderMapPagedTree = new HashMap<>();
        /**
     * The Triple header map.
     */
    private Map<Object, Object[]> tripleHeaderMapPagedTree = new HashMap<>();

    private int tableCountPagedTree;

    private Map<String, Integer> countMapPagedTree;

    public GtnForecastBean getGtnForecastBean() {
        return gtnForecastBeanPagedTree;
    }

    public void setGtnForecastBean(GtnForecastBean gtnForecastBean) {
        this.gtnForecastBeanPagedTree = gtnForecastBean;
    }

    public List<GtnWsRecordBean> getForecastPagedTableBeanList() {
        return forecastPagedTableBeanListPagedTree != null ? new ArrayList<>(forecastPagedTableBeanListPagedTree)
                : forecastPagedTableBeanListPagedTree;
    }

    public void setForecastPagedTableBeanList(List<GtnWsRecordBean> forecastPagedTableBeanList) {
        this.forecastPagedTableBeanListPagedTree = forecastPagedTableBeanList != null
                ? new ArrayList<>(forecastPagedTableBeanList)
                : forecastPagedTableBeanList;
    }

    public int getTableCount() {
        return tableCountPagedTree;
    }

    public void setTableCount(int tableCount) {
        this.tableCountPagedTree = tableCount;
    }

    /**
     * Adds the single column.
     *
     * @param column the column
     * @param header the header
     * @param clazz the clazz
     */
    public void addSingleColumn(Object column, String header, Class<?> clazz) {
        this.singleColumnPagedTree.add(column.toString());
        this.singleHeaderPagedTree.add(header);
        addProperty(column, clazz);
    }

    /**
     * Adds the property.
     *
     * @param column the column
     * @param clazz the clazz
     */
    public void addProperty(Object column, Class<?> clazz) {
        this.propertiesPagedTree.put(column, clazz);
    }

    /**
     * Adds the double column.
     *
     * @param column the column
     * @param header the header
     */
    public void addDoubleColumn(Object column, String header) {
        this.doubleColumnPagedTree.add(column);
        this.doubleHeaderPagedTree.add(header);
    }

    /**
     * Adds the double header map.
     *
     * @param doubleHeader the double header
     * @param singleHeader the single header
     */
    public void addDoubleHeaderMap(Object doubleHeader, Object[] singleHeader) {
        this.doubleHeaderMapPagedTree.put(doubleHeader, singleHeader);
    }

    /**
     * Gets the properties.
     *
     * @return the properties
     */
    @SuppressWarnings("rawtypes")
    public Map<Object, Class> getProperties() {
        return propertiesPagedTree;
    }

    /**
     * Gets the single columns.
     *
     * @return the single columns
     */
    public List<Object> getSingleColumns() {
        return singleColumnPagedTree;
    }

    /**
     * Gets the single headers.
     *
     * @return the single headers
     */
    public List<String> getSingleHeaders() {
        return singleHeaderPagedTree;
    }

    /**
     * Gets the double columns.
     *
     * @return the double columns
     */
    public List<Object> getDoubleColumns() {
        return doubleColumnPagedTree;
    }

    /**
     * Gets the double headers.
     *
     * @return the double headers
     */
    public List<String> getDoubleHeaders() {
        return doubleHeaderPagedTree;
    }

    /**
     * Gets the double header maps.
     *
     * @return the double header maps
     */
    public Map<Object, Object[]> getDoubleHeaderMaps() {
        return doubleHeaderMapPagedTree;
    }

    public List<String> getResults() {
        return resultsPagedTree != null ? Collections.unmodifiableList(resultsPagedTree) : resultsPagedTree;
    }

    public void setResults(List<String> results) {
        this.resultsPagedTree = results != null ? Collections.unmodifiableList(results) : results;
    }

    public List<String> getEditableFields() {
        return editableFieldsPagedTree != null ? new ArrayList<>(editableFieldsPagedTree) : editableFieldsPagedTree;
    }

    public void setEditableFields(List<String> editableFields) {
        this.editableFieldsPagedTree = editableFields != null ? new ArrayList<>(editableFields) : editableFields;
    }

    public List<String> getEditablePeriods() {
        return editablePeriodsPagedTree;
    }

    public void setEditablePeriods(List<String> editablePeriods) {
        this.editablePeriodsPagedTree = editablePeriods;
    }

    public Map<String, Integer> getCountMap() {
        return countMapPagedTree;
    }

    public void setCountMap(Map<String, Integer> countMap) {
        this.countMapPagedTree = countMap;
    }

    public DataSelectionBean getDataSelectionBean() {
        return dataSelectionBeanPagedTree;
    }

    public void setDataSelectionBean(DataSelectionBean dataSelectionBean) {
        this.dataSelectionBeanPagedTree = dataSelectionBean;
    }

    public GtnWsForecastResponse getGtnWsForecastResponse() {
        return gtnWsForecastResponsePagedTable;
    }

    public void setGtnWsForecastResponse(GtnWsForecastResponse gtnWsForecastResponse) {
        this.gtnWsForecastResponsePagedTable = gtnWsForecastResponse;
    }

    public List<GtnWsRecordBean> getGtnWsRecordBeanList() {
        return gtnWsRecordBeanListPagedTable;
    }

    public void setGtnWsRecordBeanList(List<GtnWsRecordBean> gtnWsRecordBeanList) {
        this.gtnWsRecordBeanListPagedTable = gtnWsRecordBeanList;
    }

    public boolean isCheckAll() {
        return checkAllPagedTree;
    }

    public void setCheckAll(boolean checkAll) {
        this.checkAllPagedTree = checkAll;
    }
/**
     * Adds the Triple column.
     *
     * @param column the column
     * @param header the header
     */
    public void addTripleColumn(Object column, String header) {
        this.tripleColumnPagedTree.add(column);
        this.tripleHeaderPagedTree.add(header);
    }

    /**
     * Adds the Triple header map.
     *
     * @param tripleHeader the double header
     * @param doubleHeader the single header
     */
    public void addTripleHeaderMap(Object tripleHeader, Object[] doubleHeader) {
        this.tripleHeaderMapPagedTree.put(tripleHeader, doubleHeader);
    }

    public List<Object> getTripleColumn() {
        return tripleColumnPagedTree;
    }

    public List<String> getTripleHeader() {
        return tripleHeaderPagedTree;
    }

    public Map<Object, Object[]> getTripleHeaderMap() {
        return tripleHeaderMapPagedTree;
    }

}
