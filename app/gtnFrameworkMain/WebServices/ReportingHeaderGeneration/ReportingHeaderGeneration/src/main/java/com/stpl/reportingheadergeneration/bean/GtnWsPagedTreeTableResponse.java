package com.stpl.reportingheadergeneration.bean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GtnWsPagedTreeTableResponse {

    public GtnWsPagedTreeTableResponse() {
        super();
    }

    private GtnForecastBean gtnForecastBean;

    private List<String> results;

    private boolean checkAll;

    /**
     * The properties.
     */
    @SuppressWarnings("rawtypes")
    private Map<Object, Class> properties = new HashMap<>();
    /**
     * The single column.
     */
    private List<Object> singleColumn = new ArrayList<>();

    /**
     * The single header.
     */
    private List<String> singleHeader = new ArrayList<>();

    /**
     * The double column.
     */
    private List<Object> doubleColumn = new ArrayList<>();
    /**
     * The double header.
     */
    private List<String> doubleHeader = new ArrayList<>();

    /**
     * Triple Column
     */
    private List<Object> tripleColumn = new ArrayList<>();
    /**
     * Triple Header
     */
    private List<String> tripleHeader = new ArrayList<>();

    /**
     * The double header map.
     */
    private Map<Object, Object[]> doubleHeaderMap = new HashMap<>();

    /**
     * The Triple header map.
     */
    private Map<Object, Object[]> tripleHeaderMap = new HashMap<>();

    private int tableCount;

    private Map<String, Integer> countMap;

    public GtnForecastBean getGtnForecastBean() {
        return gtnForecastBean;
    }

    public void setGtnForecastBean(GtnForecastBean gtnForecastBean) {
        this.gtnForecastBean = gtnForecastBean;
    }

    public int getTableCount() {
        return tableCount;
    }

    public void setTableCount(int tableCount) {
        this.tableCount = tableCount;
    }

    /**
     * Adds the single column.
     *
     * @param column the column
     * @param header the header
     * @param clazz the clazz
     */
    public void addSingleColumn(Object column, String header, Class<?> clazz) {
        this.singleColumn.add(column.toString());
        this.singleHeader.add(header);
        addProperty(column, clazz);
    }

    /**
     * Adds the property.
     *
     * @param column the column
     * @param clazz the clazz
     */
    public void addProperty(Object column, Class<?> clazz) {
        this.properties.put(column, clazz);
    }

    /**
     * Adds the double column.
     *
     * @param column the column
     * @param header the header
     */
    public void addDoubleColumn(Object column, String header) {
        this.doubleColumn.add(column);
        this.doubleHeader.add(header);
    }

    /**
     * Adds the double header map.
     *
     * @param doubleHeader the double header
     * @param singleHeader the single header
     */
    public void addDoubleHeaderMap(Object doubleHeader, Object[] singleHeader) {
        this.doubleHeaderMap.put(doubleHeader, singleHeader);
    }

    /**
     * Gets the properties.
     *
     * @return the properties
     */
    @SuppressWarnings("rawtypes")
    public Map<Object, Class> getProperties() {
        return properties;
    }

    /**
     * Gets the single columns.
     *
     * @return the single columns
     */
    public List<Object> getSingleColumns() {
        return singleColumn;
    }

    /**
     * Gets the single headers.
     *
     * @return the single headers
     */
    public List<String> getSingleHeaders() {
        return singleHeader;
    }

    /**
     * Gets the double columns.
     *
     * @return the double columns
     */
    public List<Object> getDoubleColumns() {
        return doubleColumn;
    }

    /**
     * Gets the double headers.
     *
     * @return the double headers
     */
    public List<String> getDoubleHeaders() {
        return doubleHeader;
    }

    /**
     * Gets the double header maps.
     *
     * @return the double header maps
     */
    public Map<Object, Object[]> getDoubleHeaderMaps() {
        return doubleHeaderMap;
    }

    public List<String> getResults() {
        return results != null ? Collections.unmodifiableList(results) : results;
    }

    public void setResults(List<String> results) {
        this.results = results != null ? Collections.unmodifiableList(results) : results;
    }

    public Map<String, Integer> getCountMap() {
        return countMap;
    }

    public void setCountMap(Map<String, Integer> countMap) {
        this.countMap = countMap;
    }

    public boolean isCheckAll() {
        return checkAll;
    }

    public void setCheckAll(boolean checkAll) {
        this.checkAll = checkAll;
    }

    /**
     * Adds the Triple column.
     *
     * @param column the column
     * @param header the header
     */
    public void addTripleColumn(Object column, String header) {
        this.tripleColumn.add(column);
        this.tripleHeader.add(header);
    }

    /**
     * Adds the Triple header map.
     *
     * @param tripleHeader the double header
     * @param doubleHeader the single header
     */
    public void addTripleHeaderMap(Object tripleHeader, Object[] doubleHeader) {
        this.tripleHeaderMap.put(tripleHeader, doubleHeader);
    }

    public List<Object> getTripleColumn() {
        return tripleColumn;
    }

    public List<String> getTripleHeader() {
        return tripleHeader;
    }

    public Map<Object, Object[]> getTripleHeaderMap() {
        return tripleHeaderMap;
    }

}
