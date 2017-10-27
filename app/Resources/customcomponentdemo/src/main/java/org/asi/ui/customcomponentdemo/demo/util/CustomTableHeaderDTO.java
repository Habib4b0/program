/*
 * To change this license header, choose License HistoryHeaders in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.asi.ui.customcomponentdemo.demo.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.asi.ui.headers.TableHeaderDTO;

/**
 *
 * @author Abhiram
 */
public class CustomTableHeaderDTO extends TableHeaderDTO{
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
    

    public List<Object> getSingleHistoryColumns() {
        return singleHistoryColumn;
    }

    public List<String> getSingleHistoryHeaders() {
        return singleHistoryHeader;
    }

    public void setSingleHistoryColumns(List<Object> columns, List<String> headers) {
        if (columns.size() != headers.size()) {
            throw new IllegalArgumentException(
                    "The size of columns and headers must same");
        }
        this.singleHistoryColumn = columns;
        this.singleHistoryHeader = headers;
    }

    public void addSingleHistoryColumn(Object column, String header) {
        this.singleHistoryColumn.add(column);
        this.singleHistoryHeader.add(header);
    }
     public void addSingleHistoryColumn(Object column) {
        addSingleHistoryColumn(column,"");
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
                    "The size of columns and headers must same");
        }
        this.doubleHistoryColumn = columns;
        this.doubleHistoryHeader = headers;
    }

    public void addDoubleHistoryColumn(Object column, String header) {
        this.doubleHistoryColumn.add(column);
        this.doubleHistoryHeader.add(header);
    }
    public void addDoubleHistoryColumn(Object column) {
        addDoubleHistoryColumn(column,"");
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
                    "The size of columns and headers must same");
        }
        this.tripleHistoryColumn = columns;
        this.tripleHistoryHeader = headers;
    }

    public void addTripleHistoryColumn(Object column, String header) {
        this.tripleHistoryColumn.add(column);
        this.tripleHistoryHeader.add(header);
    }
    public void addTripleHistoryColumn(Object column) {
        addTripleHistoryColumn(column,"");
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
                    "The size of columns and headers must same");
        }
        this.singleProjectedColumn = columns;
        this.singleProjectedHeader = headers;
    }

    public void addSingleProjectedColumn(Object column, String header) {
        this.singleProjectedColumn.add(column);
        this.singleProjectedHeader.add(header);
    }
     public void addSingleProjectedColumn(Object column) {
        addSingleProjectedColumn(column,"");
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
                    "The size of columns and headers must same");
        }
        this.doubleProjectedColumn = columns;
        this.doubleProjectedHeader = headers;
    }

    public void addDoubleProjectedColumn(Object column, String header) {
        this.doubleProjectedColumn.add(column);
        this.doubleProjectedHeader.add(header);
    }
     public void addDoubleProjectedColumn(Object column) {
        addDoubleProjectedColumn(column,"");
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
                    "The size of columns and headers must same");
        }
        this.tripleProjectedColumn = columns;
        this.tripleProjectedHeader = headers;
    }

    public void addTripleProjectedColumn(Object column, String header) {
        this.tripleProjectedColumn.add(column);
        this.tripleProjectedHeader.add(header);
    }
     public void addTripleProjectedColumn(Object column) {
        addTripleProjectedColumn(column,"");
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
   
}
