/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.util;

import java.io.Serializable;

/**
 *
 * @author KarthikeyanS
 */
public class CommonLazyUtilDTO implements Serializable {

    private String searchFields;
    private String searchFieldsCompany;
    private String searchValue;
    private String helperTableSid;
    private boolean searchFlag = false;
    private String userId;
    private String sessionId;
    private String date;
    private String cfpNo;
    private String cfpName;

    public String getHelperTableSid() {
        return helperTableSid;
    }

    public void setHelperTableSid(String helperTableSid) {
        this.helperTableSid = helperTableSid;
    }

    public String getCfpNo() {
        return cfpNo;
    }

    public void setCfpNo(String cfpNo) {
        this.cfpNo = cfpNo;
    }

    public String getCfpName() {
        return cfpName;
    }

    public void setCfpName(String cfpName) {
        this.cfpName = cfpName;
    }

    public String getSearchFieldsCompany() {
        return searchFieldsCompany;
    }

    public void setSearchFieldsCompany(String searchFieldsCompany) {
        this.searchFieldsCompany = searchFieldsCompany;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getSearchFields() {
        return searchFields;
    }

    public void setSearchFields(String searchFields) {
        this.searchFields = searchFields;
    }

    public String getSearchValue() {
        return searchValue;
    }

    public void setSearchValue(String searchValue) {
        this.searchValue = searchValue;
    }

    public boolean getSearchFlag() {
        return searchFlag;
    }

    public void setSearchFlag(boolean searchFlag) {
        this.searchFlag = searchFlag;
    }
}
