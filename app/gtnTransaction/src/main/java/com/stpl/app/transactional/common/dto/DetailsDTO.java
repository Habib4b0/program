/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.transactional.common.dto;

import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author sooriya.lakshmanan
 */
public class DetailsDTO implements Serializable {

    private String propertyName = StringUtils.EMPTY;
    private String categoryName = StringUtils.EMPTY;
    private String displayName = StringUtils.EMPTY;
    private String tableName = StringUtils.EMPTY;
    private String invalidTableName = StringUtils.EMPTY;
    private String validation = StringUtils.EMPTY;
    private String className = StringUtils.EMPTY;
    private String propertyIndex = StringUtils.EMPTY;
    private int screenCount;
    private String table = StringUtils.EMPTY;
    private String invalidRecordId =StringUtils.EMPTY ;
    private Date invalidFromDate ;
    private Date invalidToDate;
    private String searchTable = StringUtils.EMPTY;
    private String typeDdlb = StringUtils.EMPTY;
    private String levelDdlb = StringUtils.EMPTY;
    private int totalCount = 0;

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getValidation() {
        return validation;
    }

    public void setValidation(String validation) {
        this.validation = validation;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public int getScreenCount() {
        return screenCount;
    }

    public void setScreenCount(int screenCount) {
        this.screenCount = screenCount;
    }

    public String getPropertyIndex() {
        return propertyIndex;
    }

    public void setPropertyIndex(String propertyIndex) {
        this.propertyIndex = propertyIndex;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getInvalidRecordId() {
        return invalidRecordId;
    }

    public void setInvalidRecordId(String invalidRecordId) {
        this.invalidRecordId = invalidRecordId;
    }

    public Date getInvalidFromDate() {
        return invalidFromDate;
    }

    public void setInvalidFromDate(Date invalidFromDate) {
        this.invalidFromDate = invalidFromDate;
    }

    public Date getInvalidToDate() {
        return invalidToDate;
    }

    public void setInvalidToDate(Date invalidToDate) {
        this.invalidToDate = invalidToDate;
    }

    public String getInvalidTableName() {
        return invalidTableName;
    }

    public void setInvalidTableName(String invalidTableName) {
        this.invalidTableName = invalidTableName;
    }

    public String getSearchTable() {
        return searchTable;
    }

    public void setSearchTable(String searchTable) {
        this.searchTable = searchTable;
    }
    
    public String getTypeDdlb() {
        return typeDdlb;
}

    public void setTypeDdlb(String typeDdlb) {
        this.typeDdlb = typeDdlb;
    }

    public String getLevelDdlb() {
        return levelDdlb;
    }

    public void setLevelDdlb(String levelDdlb) {
        this.levelDdlb = levelDdlb;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    }
