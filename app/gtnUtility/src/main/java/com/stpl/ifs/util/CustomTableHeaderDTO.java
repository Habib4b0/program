/*
 * To change this license header, choose License HistoryHeaders in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.ifs.util;

import org.asi.ui.headers.TableHeaderDTO;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Abhiram
 */
public class CustomTableHeaderDTO extends TableHeaderDTO {

    List<Object> singleHistoryColumn = new ArrayList<Object>();
    List<Object> doubleHistoryColumn = new ArrayList<Object>();
    List<Object> tripleHistoryColumn = new ArrayList<Object>();
    List<String> singleHistoryHeader = new ArrayList<String>();
    List<String> doubleHistoryHeader = new ArrayList<String>();
    List<String> tripleHistoryHeader = new ArrayList<String>();
    Map<Object, Object[]> doubleHistoryHeaderMap = new HashMap<Object, Object[]>();
    Map<Object, Object[]> tripleHistoryHeaderMap = new HashMap<Object, Object[]>();

    List<Object> singleProjectedColumn = new ArrayList<Object>();
    List<Object> doubleProjectedColumn = new ArrayList<Object>();
    List<Object> tripleProjectedColumn = new ArrayList<Object>();
    List<String> singleProjectedHeader = new ArrayList<String>();
    List<String> doubleProjectedHeader = new ArrayList<String>();
    List<String> tripleProjectedHeader = new ArrayList<String>();
    Map<Object, Object[]> doubleProjectedHeaderMap = new HashMap<Object, Object[]>();
    Map<Object, Object[]> tripleProjectedHeaderMap = new HashMap<Object, Object[]>();
    
     List<Object> singleForecastColumn = new ArrayList<Object>();
    List<Object> doubleForecastColumn = new ArrayList<Object>();
    List<Object> tripleForecastColumn = new ArrayList<Object>();
    List<String> singleForecastHeader = new ArrayList<String>();
    List<String> doubleForecastHeader = new ArrayList<String>();
    List<String> tripleForecastHeader = new ArrayList<String>();
    Map<Object, Object[]> doubleForecastHeaderMap = new HashMap<Object, Object[]>();
    Map<Object, Object[]> tripleForecastHeaderMap = new HashMap<Object, Object[]>();

    private int startYear;
    private int endYear;
    private int frequencyDivision;
    private int historyNum;
    private int projectionNum;
    private int currentPeriod;
    private int currentYear;
    private int startPeriod;
    private int endPeriod;
    private int ProjectionOrder;
    private String frequency;

    public List<Object> getSingleHistoryColumns() {
        return singleHistoryColumn;
    }

    public List<String> getSingleHistoryHeaders() {
        return singleHistoryHeader;
    }

    public void setSingleHistoryColumns(List<Object> columns, List<String> headers) {
        if (columns.size() != headers.size()) {
            throw new IllegalArgumentException(
                    HeaderUtils.THE_SIZE_OF_COLUMNS_AND_HEADERS_MUST_SAME);
        }
        this.singleHistoryColumn = columns;
        this.singleHistoryHeader = headers;
    }

    public void addSingleHistoryColumn(Object column, String header) {
        this.singleHistoryColumn.add(column);
        this.singleHistoryHeader.add(header);
    }

    public void addSingleHistoryColumn(Object column) {
        addSingleHistoryColumn(column, "");
    }

    public List<Object> getDoubleHistoryColumns() {
        return doubleHistoryColumn;
    }

    public List<String> getDoubleHistoryHeaders() {
        return doubleHistoryHeader;
    }

    public void setDoubleHistoryColumns(List<Object> columns, List<String> headers) {
        if (columns.size() != headers.size()) {
            throw new IllegalArgumentException(
                    HeaderUtils.THE_SIZE_OF_COLUMNS_AND_HEADERS_MUST_SAME);
        }
        this.doubleHistoryColumn = columns;
        this.doubleHistoryHeader = headers;
    }

    public void addDoubleHistoryColumn(Object column, String header) {
        this.doubleHistoryColumn.add(column);
        this.doubleHistoryHeader.add(header);
    }

    public void addDoubleHistoryColumn(Object column) {
        addDoubleHistoryColumn(column, "");
    }

    public List<Object> getTripleHistoryColumns() {
        return tripleHistoryColumn;
    }

    public List<String> getTripleHistoryHeader() {
        return tripleHistoryHeader;
    }

    public void setTripleHistoryColumns(List<Object> columns, List<String> headers) {
        if (columns.size() != headers.size()) {
            throw new IllegalArgumentException(
                    HeaderUtils.THE_SIZE_OF_COLUMNS_AND_HEADERS_MUST_SAME);
        }
        this.tripleHistoryColumn = columns;
        this.tripleHistoryHeader = headers;
    }

    public void addTripleHistoryColumn(Object column, String header) {
        this.tripleHistoryColumn.add(column);
        this.tripleHistoryHeader.add(header);
    }

    public void addTripleHistoryColumn(Object column) {
        addTripleHistoryColumn(column, "");
    }

    public Map<Object, Object[]> getDoubleHistoryHeaderMaps() {
        return doubleHistoryHeaderMap;
    }

    public void setDoubleHistoryHeaderMaps(Map<Object, Object[]> doubleHistoryHeaderMap) {
        this.doubleHistoryHeaderMap = doubleHistoryHeaderMap;
    }

    public void addDoubleHistoryHeaderMap(Object doubleHistoryHeader, Object[] singleHistoryHeader) {
        this.doubleHistoryHeaderMap.put(doubleHistoryHeader, singleHistoryHeader);
    }

    public Map<Object, Object[]> getTripleHistoryHeaderMaps() {
        return tripleHistoryHeaderMap;
    }

    public void setTripleHistoryHeaderMaps(Map<Object, Object[]> tripleHistoryHeaderMap) {
        this.tripleHistoryHeaderMap = tripleHistoryHeaderMap;
    }

    public void addTripleHistoryHeaderMap(Object tripleHistoryHeader, Object[] doubleHistoryHeader) {
        this.tripleHistoryHeaderMap.put(tripleHistoryHeader, doubleHistoryHeader);
    }

    public List<Object> getSingleProjectedColumns() {
        return singleProjectedColumn;
    }

    public List<String> getSingleProjectedHeaders() {
        return singleProjectedHeader;
    }

    public void setSingleProjectedColumns(List<Object> columns, List<String> headers) {
        if (columns.size() != headers.size()) {
            throw new IllegalArgumentException(
                    HeaderUtils.THE_SIZE_OF_COLUMNS_AND_HEADERS_MUST_SAME);
        }
        this.singleProjectedColumn = columns;
        this.singleProjectedHeader = headers;
    }

    public void addSingleProjectedColumn(Object column, String header) {
        this.singleProjectedColumn.add(column);
        this.singleProjectedHeader.add(header);
    }

    public void addSingleProjectedColumn(Object column) {
        addSingleProjectedColumn(column, "");
    }

    public List<Object> getDoubleProjectedColumns() {
        return doubleProjectedColumn;
    }

    public List<String> getDoubleProjectedHeaders() {
        return doubleProjectedHeader;
    }

    public void setDoubleProjectedColumns(List<Object> columns, List<String> headers) {
        if (columns.size() != headers.size()) {
            throw new IllegalArgumentException(
                    HeaderUtils.THE_SIZE_OF_COLUMNS_AND_HEADERS_MUST_SAME);
        }
        this.doubleProjectedColumn = columns;
        this.doubleProjectedHeader = headers;
    }

    public void addDoubleProjectedColumn(Object column, String header) {
        this.doubleProjectedColumn.add(column);
        this.doubleProjectedHeader.add(header);
    }

    public void addDoubleProjectedColumn(Object column) {
        addDoubleProjectedColumn(column, "");
    }

    public List<Object> getTripleProjectedColumns() {
        return tripleProjectedColumn;
    }

    public List<String> getTripleProjectedHeader() {
        return tripleProjectedHeader;
    }

    public void setTripleProjectedColumns(List<Object> columns, List<String> headers) {
        if (columns.size() != headers.size()) {
            throw new IllegalArgumentException(
                    HeaderUtils.THE_SIZE_OF_COLUMNS_AND_HEADERS_MUST_SAME);
        }
        this.tripleProjectedColumn = columns;
        this.tripleProjectedHeader = headers;
    }

    public void addTripleProjectedColumn(Object column, String header) {
        this.tripleProjectedColumn.add(column);
        this.tripleProjectedHeader.add(header);
    }

    public void addTripleProjectedColumn(Object column) {
        addTripleProjectedColumn(column, "");
    }

    public Map<Object, Object[]> getDoubleProjectedHeaderMaps() {
        return doubleProjectedHeaderMap;
    }

    public void setDoubleProjectedHeaderMaps(Map<Object, Object[]> doubleProjectedHeaderMap) {
        this.doubleProjectedHeaderMap = doubleProjectedHeaderMap;
    }

    public void addDoubleProjectedHeaderMap(Object doubleProjectedHeader, Object[] singleProjectedHeader) {
        this.doubleProjectedHeaderMap.put(doubleProjectedHeader, singleProjectedHeader);
    }

    public Map<Object, Object[]> getTripleProjectedHeaderMaps() {
        return tripleProjectedHeaderMap;
    }

    public void setTripleProjectedHeaderMaps(Map<Object, Object[]> tripleProjectedHeaderMap) {
        this.tripleProjectedHeaderMap = tripleProjectedHeaderMap;
    }

    public void addTripleProjectedHeaderMap(Object tripleProjectedHeader, Object[] doubleProjectedHeader) {
        this.tripleProjectedHeaderMap.put(tripleProjectedHeader, doubleProjectedHeader);
    }

    public int getStartYear() {
        return startYear;
    }

    public void setStartYear(int startYear) {
        this.startYear = startYear;
    }

    public int getEndYear() {
        return endYear;
    }

    public void setEndYear(int endYear) {
        this.endYear = endYear;
    }

    public int getFrequencyDivision() {
        return frequencyDivision;
    }

    public void setFrequencyDivision(int frequencyDivision) {
        this.frequencyDivision = frequencyDivision;
    }

    public int getHistoryNum() {
        return historyNum;
    }

    public void setHistoryNum(int historyNum) {
        this.historyNum = historyNum;
    }

    public int getProjectionNum() {
        return projectionNum;
    }

    public void setProjectionNum(int projectionNum) {
        this.projectionNum = projectionNum;
    }

    public int getCurrentPeriod() {
        return currentPeriod;
    }

    public void setCurrentPeriod(int currentPeriod) {
        this.currentPeriod = currentPeriod;
    }

    public int getCurrentYear() {
        return currentYear;
    }

    public void setCurrentYear(int currentYear) {
        this.currentYear = currentYear;
    }

    public int getStartPeriod() {
        return startPeriod;
    }

    public void setStartPeriod(int startPeriod) {
        this.startPeriod = startPeriod;
    }

    public int getEndPeriod() {
        return endPeriod;
    }

    public void setEndPeriod(int endPeriod) {
        this.endPeriod = endPeriod;
    }

    public int getProjectionOrder() {
        return ProjectionOrder;
    }

    public void setProjectionOrder(int ProjectionOrder) {
        this.ProjectionOrder = ProjectionOrder;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

       public List<Object> getSingleForecastColumns() {
        return singleForecastColumn;
    }

    public List<String> getSingleForecastHeaders() {
        return singleForecastHeader;
    }

    public void setSingleForecastColumns(List<Object> columns, List<String> headers) {
        if (columns.size() != headers.size()) {
            throw new IllegalArgumentException(
                    HeaderUtils.THE_SIZE_OF_COLUMNS_AND_HEADERS_MUST_SAME);
        }
        this.singleForecastColumn = columns;
        this.singleForecastHeader = headers;
    }

    public void addSingleForecastColumn(Object column, String header) {
        this.singleForecastColumn.add(column);
        this.singleForecastHeader.add(header);
    }

    public void addSingleForecastColumn(Object column) {
        addSingleForecastColumn(column, "");
    }

    public List<Object> getDoubleForecastColumns() {
        return doubleForecastColumn;
    }

    public List<String> getDoubleForecastHeaders() {
        return doubleForecastHeader;
    }

    public void setDoubleForecastColumns(List<Object> columns, List<String> headers) {
        if (columns.size() != headers.size()) {
            throw new IllegalArgumentException(
                    HeaderUtils.THE_SIZE_OF_COLUMNS_AND_HEADERS_MUST_SAME);
        }
        this.doubleForecastColumn = columns;
        this.doubleForecastHeader = headers;
    }

    public void addDoubleForecastColumn(Object column, String header) {
        this.doubleForecastColumn.add(column);
        this.doubleForecastHeader.add(header);
    }

    public void addDoubleForecastColumn(Object column) {
        addDoubleForecastColumn(column, "");
    }

    public List<Object> getTripleForecastColumns() {
        return tripleForecastColumn;
    }

    public List<String> getTripleForecastHeader() {
        return tripleForecastHeader;
    }

    public void setTripleForecastColumns(List<Object> columns, List<String> headers) {
        if (columns.size() != headers.size()) {
            throw new IllegalArgumentException(
                    HeaderUtils.THE_SIZE_OF_COLUMNS_AND_HEADERS_MUST_SAME);
        }
        this.tripleForecastColumn = columns;
        this.tripleForecastHeader = headers;
    }

    public void addTripleForecastColumn(Object column, String header) {
        this.tripleForecastColumn.add(column);
        this.tripleForecastHeader.add(header);
    }

    public void addTripleForecastColumn(Object column) {
        addTripleForecastColumn(column, "");
    }

    public Map<Object, Object[]> getDoubleForecastHeaderMaps() {
        return doubleForecastHeaderMap;
    }

    public void setDoubleForecastHeaderMaps(Map<Object, Object[]> doubleForecastHeaderMap) {
        this.doubleForecastHeaderMap = doubleForecastHeaderMap;
    }

    public void addDoubleForecastHeaderMap(Object doubleForecastHeader, Object[] singleForecastHeader) {
        this.doubleForecastHeaderMap.put(doubleForecastHeader, singleForecastHeader);
    }

    public Map<Object, Object[]> getTripleForecastHeaderMaps() {
        return tripleForecastHeaderMap;
    }

    public void setTripleForecastHeaderMaps(Map<Object, Object[]> tripleForecastHeaderMap) {
        this.tripleForecastHeaderMap = tripleForecastHeaderMap;
    }

    public void addTripleForecastHeaderMap(Object tripleForecastHeader, Object[] doubleForecastHeader) {
        this.tripleForecastHeaderMap.put(tripleForecastHeader, doubleForecastHeader);
    }

}
