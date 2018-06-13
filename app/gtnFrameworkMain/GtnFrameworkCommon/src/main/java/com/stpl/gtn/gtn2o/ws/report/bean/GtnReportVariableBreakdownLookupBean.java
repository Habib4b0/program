/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.report.bean;

import java.util.List;

/**
 *
 * @author gokulkumar.murugesan
 */
public class GtnReportVariableBreakdownLookupBean {
    
    private int masterSid;
    private int period;
    private int year;
    private int selectedVariable;
    private List<Object[]> resultList;

    public List<Object[]> getResultList() {
        return resultList;
    }

    public void setResultList(List<Object[]> resultList) {
        this.resultList = resultList;
    }
    public int getMasterSid() {
        return masterSid;
    }

    public void setMasterSid(int masterSid) {
        this.masterSid = masterSid;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getSelectedVariable() {
        return selectedVariable;
    }

    public void setSelectedVariable(int selectedVariable) {
        this.selectedVariable = selectedVariable;
    }
   
}
