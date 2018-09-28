/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.businessprocess.commontemplates;

import com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AbstractSelectionDTO;
import com.stpl.app.arm.common.CommonLogic;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Asha.Ravi
 */
public class SummarySelection extends AbstractSelectionDTO implements Serializable {

    private List<String> deductionVariablesValues;
    private List<String> deductionVariablesName;
    private List<Integer> deductionVariableIds;
    private String frequency;
    private transient Map<Object, String> headerVisibleColumnMap;
    private Map<Integer, String> summaryLevel;

    private int levelNo;

    public SummarySelection() {
        /*
        THE DEFAULT CONSTRUCTOR
         */
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public List<String> getDeductionVariablesValues() {
        return CommonLogic.getInstance().getArrayListCloned(deductionVariablesValues);
    }

    public void setDeductionVariablesValues(List<String> deductionVariablesValues) {
        this.deductionVariablesValues = CommonLogic.getInstance().getArrayListCloned(deductionVariablesValues);
    }

    public List<Integer> getDeductionVariableIds() {
        return CommonLogic.getInstance().getArrayListCloned(deductionVariableIds);
    }

    public void setDeductionVariableIds(List<Integer> deductionVariableIds) {
        this.deductionVariableIds = CommonLogic.getInstance().getArrayListCloned(deductionVariableIds);
    }

    public Map<Object, String> getHeaderVisibleColumnMap() {
        return headerVisibleColumnMap;
    }

    public void setHeaderVisibleColumnMap(Map<Object, String> headerVisibleColumnMap) {
        this.headerVisibleColumnMap = headerVisibleColumnMap;
    }

    public Map<Integer, String> getSummaryLevel() {
        return summaryLevel;
    }

    public void setSummaryLevel(Map<Integer, String> summaryLevel) {
        this.summaryLevel = summaryLevel;
    }

    @Override
    public int getLevelNo() {
        return levelNo;
    }

    @Override
    public void setLevelNo(int levelNo) {
        this.levelNo = levelNo;
    }

    public List<String> getDeductionVariablesName() {
        return CommonLogic.getInstance().getArrayListCloned(deductionVariablesName);
    }

    public void setDeductionVariablesName(List<String> deductionVariablesName) {
        this.deductionVariablesName = CommonLogic.getInstance().getArrayListCloned(deductionVariablesName);
    }

}
