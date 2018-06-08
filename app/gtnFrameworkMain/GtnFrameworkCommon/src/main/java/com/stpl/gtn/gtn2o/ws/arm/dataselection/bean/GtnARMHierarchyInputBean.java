/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.arm.dataselection.bean;

import com.stpl.gtn.gtn2o.ws.forecast.bean.GtnFrameworkRelationshipLevelDefintionBean;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GtnARMHierarchyInputBean {

    private String framedQuery;

    private int relationShipBuilderSid;

    private int hierarchyDefinitionSid;

    private int hierarchyLevelDefinitionSid;

    private int relationVersionNo;

    private int hierarchyVersionNo;

    private int levelNo;

    private boolean isNdc;

    private String deductionValues;

    private int businessUnit;

    private int glCompany;

    private List<GtnFrameworkRelationshipLevelDefintionBean> selectedCustomerList;

    private List<GtnFrameworkRelationshipLevelDefintionBean> selectedProductList;
    
    private int selectedCustomerRelationShipBuilderVersionNo;
    
    private int selectedCustomerHierarcySid;
    
    private int selectedCustomerHierarchyVersionNo;
    
    private int selectedProductRelationShipBuilderVersionNo;
    
    private int selectedProductHierarcySid;
    
    private int selectedProductRelationShipBuilderSid;
    
    private int selectedProductHierarchyVersionNo;
    
    private int projectionId;
    
    private Map<String, String> tempTableMap;

    public String getFramedQuery() {
        return framedQuery;
    }

    public void setFramedQuery(String framedQuery) {
        this.framedQuery = framedQuery;
    }

    public int getRelationShipBuilderSid() {
        return relationShipBuilderSid;
    }

    public void setRelationShipBuilderSid(int relationShipBuilderSid) {
        this.relationShipBuilderSid = relationShipBuilderSid;
    }

    public int getHierarchyDefinitionSid() {
        return hierarchyDefinitionSid;
    }

    public void setHierarchyDefinitionSid(int hierarchyDefinitionSid) {
        this.hierarchyDefinitionSid = hierarchyDefinitionSid;
    }

    public int getHierarchyLevelDefinitionSid() {
        return hierarchyLevelDefinitionSid;
    }

    public void setHierarchyLevelDefinitionSid(int hierarchyLevelDefinitionSid) {
        this.hierarchyLevelDefinitionSid = hierarchyLevelDefinitionSid;
    }

    public int getRelationVersionNo() {
        return relationVersionNo;
    }

    public void setRelationVersionNo(int relationVersionNo) {
        this.relationVersionNo = relationVersionNo;
    }

    public int getHierarchyVersionNo() {
        return hierarchyVersionNo;
    }

    public void setHierarchyVersionNo(int hierarchyVersionNo) {
        this.hierarchyVersionNo = hierarchyVersionNo;
    }

    public int getLevelNo() {
        return levelNo;
    }

    public void setLevelNo(int levelNo) {
        this.levelNo = levelNo;
    }

    public String getDeductionValues() {
        return deductionValues;
    }

    public void setDeductionValues(String deductionValues) {
        this.deductionValues = deductionValues;
    }

    public boolean isIsNdc() {
        return isNdc;
    }

    public void setIsNdc(boolean isNdc) {
        this.isNdc = isNdc;
    }

    public int getBusinessUnit() {
        return businessUnit;
    }

    public void setBusinessUnit(int businessUnit) {
        this.businessUnit = businessUnit;
    }

    public int getGlCompany() {
        return glCompany;
    }

    public void setGlCompany(int glCompany) {
        this.glCompany = glCompany;
    }

    public List<GtnFrameworkRelationshipLevelDefintionBean> getSelectedCustomerList() {
        return new ArrayList<>(selectedCustomerList);
    }

    public void setSelectedCustomerList(List<GtnFrameworkRelationshipLevelDefintionBean> selectedCustomerList) {
        this.selectedCustomerList = new ArrayList<>(selectedCustomerList);
    }

    public List<GtnFrameworkRelationshipLevelDefintionBean> getSelectedProductList() {
        return new ArrayList<>(selectedProductList);
    }

    public void setSelectedProductList(List<GtnFrameworkRelationshipLevelDefintionBean> selectedProductList) {
        this.selectedProductList = new ArrayList<>(selectedProductList);
    }

    public int getSelectedCustomerRelationShipBuilderVersionNo() {
        return selectedCustomerRelationShipBuilderVersionNo;
    }

    public void setSelectedCustomerRelationShipBuilderVersionNo(int selectedCustomerRelationShipBuilderVersionNo) {
        this.selectedCustomerRelationShipBuilderVersionNo = selectedCustomerRelationShipBuilderVersionNo;
    }

    public int getSelectedCustomerHierarcySid() {
        return selectedCustomerHierarcySid;
    }

    public void setSelectedCustomerHierarcySid(int selectedCustomerHierarcySid) {
        this.selectedCustomerHierarcySid = selectedCustomerHierarcySid;
    }

    public int getSelectedCustomerHierarchyVersionNo() {
        return selectedCustomerHierarchyVersionNo;
    }

    public void setSelectedCustomerHierarchyVersionNo(int selectedCustomerHierarchyVersionNo) {
        this.selectedCustomerHierarchyVersionNo = selectedCustomerHierarchyVersionNo;
    }

    public int getSelectedProductRelationShipBuilderVersionNo() {
        return selectedProductRelationShipBuilderVersionNo;
    }

    public void setSelectedProductRelationShipBuilderVersionNo(int selectedProductRelationShipBuilderVersionNo) {
        this.selectedProductRelationShipBuilderVersionNo = selectedProductRelationShipBuilderVersionNo;
    }

    public int getSelectedProductHierarcySid() {
        return selectedProductHierarcySid;
    }

    public void setSelectedProductHierarcySid(int selectedProductHierarcySid) {
        this.selectedProductHierarcySid = selectedProductHierarcySid;
    }

    public int getSelectedProductRelationShipBuilderSid() {
        return selectedProductRelationShipBuilderSid;
    }

    public void setSelectedProductRelationShipBuilderSid(int selectedProductRelationShipBuilderSid) {
        this.selectedProductRelationShipBuilderSid = selectedProductRelationShipBuilderSid;
    }

    public int getSelectedProductHierarchyVersionNo() {
        return selectedProductHierarchyVersionNo;
    }

    public void setSelectedProductHierarchyVersionNo(int selectedProductHierarchyVersionNo) {
        this.selectedProductHierarchyVersionNo = selectedProductHierarchyVersionNo;
    }

    public Map<String, String> getTempTableMap() {
        return tempTableMap;
    }

    public void setTempTableMap(Map<String, String> tempTableMap) {
        this.tempTableMap = tempTableMap;
    }

    public int getProjectionId() {
        return projectionId;
    }

    public void setProjectionId(int projectionId) {
        this.projectionId = projectionId;
    }
}
