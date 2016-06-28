/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.galforecasting.nationalassumptions.dto;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Manasa
 */
public class SessionDTO implements Serializable{
    private Date fromDate;
    private Date toDate;
    private String queryName;
    private int offset;
    private int start;
    private boolean pageFlag;

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public String getQueryName() {
        return queryName;
    }

    public void setQueryName(String queryName) {
        this.queryName = queryName;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public boolean isPageFlag() {
        return pageFlag;
    }

    public void setPageFlag(boolean pageFlag) {
        this.pageFlag = pageFlag;
    }

}
