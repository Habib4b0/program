/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.displayformat.bean;

import org.apache.commons.lang.StringUtils;

/**
 *
 * @author Jayaram.LeelaRam
 */
public class RelationshipLevelValuesBean {

    private int noOfSelectFormed = 0;
    private String query = StringUtils.EMPTY;
    private String defaultGroupBy = StringUtils.EMPTY;

    public int getNoOfSelectFormed() {
        return noOfSelectFormed;
    }

    public void setNoOfSelectFormed(int noOfSelectFormed) {
        this.noOfSelectFormed = noOfSelectFormed;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getDefaultGroupBy() {
        return defaultGroupBy;
    }

    public void setDefaultGroupBy(String defaultGroupBy) {
        this.defaultGroupBy += defaultGroupBy;
    }

}
