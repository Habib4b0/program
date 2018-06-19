/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.entity.customviewforecast;

/**
 *
 * @author Karthik.Raja
 */
public class CustomViewVariables implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    private char variableIndicator;
    private int customViewDetailsSid;
    private int variableId;

    public CustomViewVariables() {
    }

    public char getVariableIndicator() {
        return variableIndicator;
    }

    public void setVariableIndicator(char variableIndicator) {
        this.variableIndicator = variableIndicator;
    }

    public int getCustomViewDetailsSid() {
        return customViewDetailsSid;
    }

    public void setCustomViewDetailsSid(int customViewDetailsSid) {
        this.customViewDetailsSid = customViewDetailsSid;
    }

    public int getVariableId() {
        return variableId;
    }

    public void setVariableId(int variableId) {
        this.variableId = variableId;
    }

    public CustomViewVariables(char variableIndicator, int customViewDetailsSid, int variableId) {
        this.variableIndicator = variableIndicator;
        this.customViewDetailsSid = customViewDetailsSid;
        this.variableId = variableId;
    }

   
}
