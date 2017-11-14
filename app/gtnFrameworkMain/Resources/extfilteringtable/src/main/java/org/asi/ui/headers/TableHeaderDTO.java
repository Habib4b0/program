/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.asi.ui.headers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * The Class TableHeaderDTO.
 *
 * @author Abhiram
 */
public class TableHeaderDTO {

    /** The single column. */
    List<Object> singleColumn = new ArrayList<Object>();
    
    /** The double column. */
    List<Object> doubleColumn = new ArrayList<Object>();
    
    /** The triple column. */
    List<Object> tripleColumn = new ArrayList<Object>();
    
    /** The single header. */
    List<String> singleHeader = new ArrayList<String>();
    
    /** The double header. */
    List<String> doubleHeader = new ArrayList<String>();
    
    /** The triple header. */
    List<String> tripleHeader = new ArrayList<String>();
    
    /** The double header map. */
    Map<Object, Object[]> doubleHeaderMap = new HashMap<Object, Object[]>();
    
    /** The triple header map. */
    Map<Object, Object[]> tripleHeaderMap = new HashMap<Object, Object[]>();
    
    /** The single headers. */
    Map<Object, String> singleHeaders = new HashMap<Object, String>();
    
    /** The double headers. */
    Map<Object, String> doubleHeaders = new HashMap<Object, String>();
    
    /** The triple headers. */
    Map<Object, String> tripleHeaders = new HashMap<Object, String>();
    
    /** The properties. */
    Map<Object, Class> properties = new HashMap<Object, Class>();
    
    /**
     * Gets the properties.
     *
     * @return the properties
     */
    public Map<Object, Class> getProperties() {
        return properties;
    }

    /**
     * Sets the properties.
     *
     * @param properties the properties
     */
    public void setProperties(Map<Object, Class> properties) {
        this.properties = properties;
    }
    
    /**
     * Adds the property.
     *
     * @param column the column
     * @param clazz the clazz
     */
    public void addProperty(Object column, Class clazz) {
        this.properties.put(column,clazz);
        
    }
    
    /**
     * Removes the property.
     *
     * @param column the column
     * @return the class
     */
    public Class removeProperty(Object column) {
        return properties.remove(column);
        
    }
    
    /**
     * Gets the property.
     *
     * @param column the column
     * @return the property
     */
    public Class getProperty(Object column) {
         return  this.properties.get(column);
        
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
     * Sets the single columns.
     *
     * @param columns the columns
     * @param headers the headers
     * @param properties the properties
     */
    public void setSingleColumns(List<Object> columns, List<String> headers, List<Class> properties) {
        if (columns.size() != headers.size()||columns.size() != headers.size()||columns.size() != headers.size()) {
            throw new IllegalArgumentException(
                    "The size of headers, columns and properties must same");
        }
        for(int i=0;i<columns.size();i++){
            addSingleColumn(columns.get(i),headers.get(i),properties.get(i));
        }
    }
    
    /**
     * Sets the single columns.
     *
     * @param columns the columns
     * @param properties the properties
     */
    public void setSingleColumns(List<Object> columns,List<Class> properties) {
        if (columns.size() != properties.size()) {
            throw new IllegalArgumentException(
                    "The size of columns and properties must same");
        }
        for(int i=0;i<columns.size();i++){
            addSingleColumn(columns.get(i),properties.get(i));
        }
    }
    
    /**
     * Sets the single columns.
     *
     * @param columns the columns
     * @param properties the properties
     */
    public void setSingleColumns(List<Object> columns) {
        for(int i=0;i<columns.size();i++){
            addSingleColumn(columns.get(i),String.class);
        }
    }
    
    /**
     * Adds the single column.
     *
     * @param column the column
     * @param header the header
     * @param clazz the clazz
     */
    public void addSingleColumn(Object column, String header, Class clazz) {
        this.singleColumn.add(column);
        this.singleHeader.add(header);
        this.singleHeaders.put(column, header);
        addProperty(column,clazz);
    }

    /**
     * Adds the single column.
     *
     * @param column the column
     * @param clazz the clazz
     */
    public void addSingleColumn(Object column, Class clazz) {
        addSingleColumn(column,"", clazz);
    }
    
    /**
     * Removes the single column.
     *
     * @param column the column
     */
    public void removeSingleColumn(Object column) {
        String s = this.singleHeaders.remove(column);
        if (s != null) {
            this.singleColumn.remove(column);
            this.singleHeader.remove(s);
            removeProperty(column);
        }
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
     * Sets the double columns.
     *
     * @param columns the columns
     * @param headers the headers
     */
    public void setDoubleColumns(List<Object> columns, List<String> headers) {
        this.doubleColumn = columns;
        this.doubleHeader = headers;
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
        this.doubleHeaders.put(column, header);
    }
    
    /**
     * Adds the double column.
     *
     * @param column the column
     */
    public void addDoubleColumn(Object column) {
        this.doubleColumn.add(column);
        this.doubleHeader.add("");
        this.doubleHeaders.put(column, "");
    }
    
    /**
     * Removes the double column.
     *
     * @param column the column
     */
    public void removeDoubleColumn(Object column) {
        String s = this.doubleHeaders.remove(column);
        if (s != null) {
            this.doubleColumn.remove(column);
            this.doubleHeader.remove(s);
        }
    }
    
    /**
     * Gets the triple columns.
     *
     * @return the triple columns
     */
    public List<Object> getTripleColumns() {
        return tripleColumn;
    }

    /**
     * Gets the triple header.
     *
     * @return the triple header
     */
    public List<String> getTripleHeader() {
        return tripleHeader;
    }

    /**
     * Sets the triple columns.
     *
     * @param columns the columns
     * @param headers the headers
     */
    public void setTripleColumns(List<Object> columns, List<String> headers) {
        this.tripleColumn = columns;
        this.tripleHeader = headers;
    }

    /**
     * Adds the triple column.
     *
     * @param column the column
     * @param header the header
     */
    public void addTripleColumn(Object column, String header) {
        this.tripleColumn.add(column);
        this.tripleHeader.add(header);
        this.tripleHeaders.put(column, header);
    }
    
    /**
     * Adds the triple column.
     *
     * @param column the column
     */
    public void addTripleColumn(Object column) {
        this.tripleColumn.add(column);
        this.tripleHeader.add("");
        this.tripleHeaders.put(column, "");
    }
    
    /**
     * Removes the triple column.
     *
     * @param column the column
     */
    public void removeTripleColumn(Object column) {
        String s = this.tripleHeaders.remove(column);
        if (s != null) {
            this.tripleColumn.remove(column);
            this.tripleHeader.remove(s);
        }
    }
    
    /**
     * Gets the double header maps.
     *
     * @return the double header maps
     */
    public Map<Object, Object[]> getDoubleHeaderMaps() {
        return doubleHeaderMap;
    }

    /**
     * Sets the double header maps.
     *
     * @param doubleHeaderMap the double header map
     */
    public void setDoubleHeaderMaps(Map<Object, Object[]> doubleHeaderMap) {
        this.doubleHeaderMap = doubleHeaderMap;
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
     * Removes the double header map.
     *
     * @param doubleHeader the double header
     * @return the object[]
     */
    public Object[] removeDoubleHeaderMap(Object doubleHeader) {
        return this.doubleHeaderMap.remove(doubleHeader);
    }
    
    /**
     * Gets the triple header maps.
     *
     * @return the triple header maps
     */
    public Map<Object, Object[]> getTripleHeaderMaps() {
        return tripleHeaderMap;
    }

    /**
     * Sets the triple header maps.
     *
     * @param tripleHeaderMap the triple header map
     */
    public void setTripleHeaderMaps(Map<Object, Object[]> tripleHeaderMap) {
        this.tripleHeaderMap = tripleHeaderMap;
    }

    /**
     * Adds the triple header map.
     *
     * @param tripleHeader the triple header
     * @param doubleHeader the double header
     */
    public void addTripleHeaderMap(Object tripleHeader, Object[] doubleHeader) {
        this.tripleHeaderMap.put(tripleHeader, doubleHeader);
    }
    
    /**
     * Removes the triple header map.
     *
     * @param tripleHeader the triple header
     * @return the object[]
     */
    public Object[] removeTripleHeaderMap(Object tripleHeader) {
        return this.tripleHeaderMap.remove(tripleHeader);
    }
    
    /**
     * Gets the single header.
     *
     * @param column the column
     * @return the single header
     */
    public String getSingleHeader(Object column) {
        return singleHeaders.get(column);
    }
    
    /**
     * Gets the double header.
     *
     * @param column the column
     * @return the double header
     */
    public String getDoubleHeader(Object column) {
        return doubleHeaders.get(column);
    }
    
    /**
     * Gets the triple header.
     *
     * @param column the column
     * @return the triple header
     */
    public String getTripleHeader(Object column) {
        return tripleHeaders.get(column);
    }
}
