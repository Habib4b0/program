/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.businessprocess.commontemplates;

import com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AbstractSelectionDTO;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Asha.Ravi
 */
public class SummarySelection extends AbstractSelectionDTO {

    private List<String[]> variables;
    private List<String> deductionVariablesValues;
    private List<String> deductionVariablesName;
    private List<Integer> deductionVariableIds;
    private String frequency;
    private List<String> selectedVariables;
    private HashMap<Object, String> headerVisibleColumnMap;
    private Map<Integer, String> summaryLevel;

    private int levelNo;

    public List<String> getSelectedVariables() {
        return selectedVariables;
    }

    public void setSelectedVariables(List<String> selectedVariables) {
        this.selectedVariables = selectedVariables;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public List<String[]> getVariables() {
        return variables;
    }

    public void setVariables(List<String[]> variables) {
        this.variables = variables;
    }

    public List<String> getDeductionVariablesValues() {
        return deductionVariablesValues;
    }

    public void setDeductionVariablesValues(List<String> deductionVariablesValues) {
        this.deductionVariablesValues = deductionVariablesValues;
    }

    public List<Integer> getDeductionVariableIds() {
        return deductionVariableIds;
    }

    public void setDeductionVariableIds(List<Integer> deductionVariableIds) {
        this.deductionVariableIds = deductionVariableIds;
    }

    public HashMap<Object, String> getHeaderVisibleColumnMap() {
        return headerVisibleColumnMap;
    }

    public void setHeaderVisibleColumnMap(HashMap<Object, String> headerVisibleColumnMap) {
        this.headerVisibleColumnMap = headerVisibleColumnMap;
    }

    public Map<Integer, String> getSummaryLevel() {
        return summaryLevel;
    }

    public void setSummaryLevel(Map<Integer, String> summaryLevel) {
        this.summaryLevel = summaryLevel;
    }

    public int getLevelNo() {
        return levelNo;
    }

    public void setLevelNo(int levelNo) {
        this.levelNo = levelNo;
    }

    public List<String> getDeductionVariablesName() {
        return deductionVariablesName;
    }

    public void setDeductionVariablesName(List<String> deductionVariablesName) {
        this.deductionVariablesName = deductionVariablesName;
    }

}
