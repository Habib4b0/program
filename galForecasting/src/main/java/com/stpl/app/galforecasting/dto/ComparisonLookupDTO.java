/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.galforecasting.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author Mohamed.hameed
 */
public class ComparisonLookupDTO {

    private String workflowStatus = StringUtils.EMPTY;
    private String marketType= StringUtils.EMPTY;
    private String brand= StringUtils.EMPTY;
    private String projectionName= StringUtils.EMPTY;
    private String contractHolder= StringUtils.EMPTY;
    private String ndcNo= StringUtils.EMPTY;
    private String projectionDescription= StringUtils.EMPTY;
    private String contract= StringUtils.EMPTY;
    private String ndcName= StringUtils.EMPTY;
    private String createdDateFrom;
    private String createdDateTo;
    private int projectionId = 0;
    private String createdBy = StringUtils.EMPTY;
    List<ComparisonLookupDTO> selected = new ArrayList<ComparisonLookupDTO>();
    List<Integer> projIdList = new ArrayList<Integer>();
    List<String> projNameList = new ArrayList<String>();
    Map<Integer, String> projectionMap = new HashMap<Integer, String>();

    public String getWorkflowStatus() {
        return workflowStatus;
    }

    public void setWorkflowStatus(String workflowStatus) {
        this.workflowStatus = workflowStatus;
    }

    public String getMarketType() {
        return marketType;
    }

    public void setMarketType(String marketType) {
        this.marketType = marketType;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getProjectionName() {
        return projectionName;
    }

    public void setProjectionName(String projectionName) {
        this.projectionName = projectionName;
    }

    public String getContractHolder() {
        return contractHolder;
    }

    public void setContractHolder(String contractHolder) {
        this.contractHolder = contractHolder;
    }

    public String getNdcNo() {
        return ndcNo;
    }

    public void setNdcNo(String ndcNo) {
        this.ndcNo = ndcNo;
    }

    public String getProjectionDescription() {
        return projectionDescription;
    }

    public void setProjectionDescription(String projectionDescription) {
        this.projectionDescription = projectionDescription;
    }

    public String getContract() {
        return contract;
    }

    public void setContract(String contract) {
        this.contract = contract;
    }

    public String getNdcName() {
        return ndcName;
    }

    public void setNdcName(String ndcName) {
        this.ndcName = ndcName;
    }

    public String getCreatedDateFrom() {
        return createdDateFrom;
    }

    public void setCreatedDateFrom(String createdDateFrom) {
        this.createdDateFrom = createdDateFrom;
    }

    public String getCreatedDateTo() {
        return createdDateTo;
    }

    public void setCreatedDateTo(String createdDateTo) {
        this.createdDateTo = createdDateTo;
    }

    public List<ComparisonLookupDTO> getSelected() {
        return selected;
    }

    public void setSelected(List<ComparisonLookupDTO> selected) {
        this.selected = selected;
    }

    public int getProjectionId() {
        return projectionId;
    }

    public void setProjectionId(int projectionId) {
        this.projectionId = projectionId;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public List<Integer> getProjIdList() {
        return projIdList;
    }

    public void setProjIdList(List<Integer> projIdList) {
        this.projIdList = projIdList;
    }

    public List<String> getProjNameList() {
        return projNameList;
    }

    public void setProjNameList(List<String> projNameList) {
        this.projNameList = projNameList;
    }

    public Map<Integer, String> getProjectionMap() {
        return projectionMap;
    }

    public void setProjectionMap(Map<Integer, String> projectionMap) {
        this.projectionMap = projectionMap;
    }

 
}
