/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.accrualrateprojection.dto;

import com.stpl.app.gtnforecasting.sessionutils.SessionDTO;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author sibi
 */
public class AccrualRateSelectionDTO {

    private String projectionId;

    private String userId;

    private String sessionId;

    private Date startDate;

    private Date endDate;

    private boolean isRateOrAmount;
    
    private String rateBasis;
    
    private String priceBasis;

    private boolean isFilterValid;

    private int filterStartYear;

    private int filterEndYear;

    private int filterStartMonth;

    private int filterEndMonth;

    private String companyName = StringUtils.EMPTY;

    private String excludedField = StringUtils.EMPTY;
    
    private String companyMasterSid= StringUtils.EMPTY;
    
    private String companyId= StringUtils.EMPTY;
    
    private final ArrayList<String> availableVisibleColumns = new ArrayList();
    
    private final List<Object> filterList = new ArrayList();
    
    private final List<String> variableList = new ArrayList();
    
    private String periodBasis;
    
    private Object customer;
    
    private Object brand;
   
    private Object product;
    
    private SessionDTO sessionDto;

    public Date getStartDate() {
        return startDate == null ? null : (Date) startDate.clone();
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate == null ? null : (Date) startDate.clone();
    }

    public Date getEndDate() {
        return endDate == null ? null : (Date) endDate.clone();
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate == null ? null : (Date) endDate.clone();
    }

    public String getProjectionId() {
        return projectionId;
    }

    public void setProjectionId(String projectionId) {
        this.projectionId = projectionId;
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

    public boolean isRateOrAmount() {
        return isRateOrAmount;
    }

    public void setRateOrAmount(boolean isRateOrAmount) {
        this.isRateOrAmount = isRateOrAmount;
    }

    public String getRateBasis() {
        return rateBasis;
    }

    public void setRateBasis(String rateBasis) {
        this.rateBasis = rateBasis;
    }

    public boolean isFilterValid() {
        return isFilterValid;
    }

    public void setIsFilterValid(boolean isFilterValid) {
        this.isFilterValid = isFilterValid;
    }

    /**
     * Returns the copy of the list in the DTO.
     *
     * @return
     */
    public ArrayList<String> getAvailableVisibleColumns() {
        return (ArrayList) availableVisibleColumns.clone();
    }

    /**
     * Adds the element to the list.
     *
     * @param element
     */
    public void addToAvailableVisibleColumns(String element) {
        availableVisibleColumns.add(element);
    }

    /**
     * Clears the available visible columns list. Clear this list before adding
     * element before use based on need.
     */
    public void clearAvailableVisibleColumns() {
        availableVisibleColumns.clear();
    }

    public int getFilterStartYear() {
        return filterStartYear;
    }

    public void setFilterStartYear(int filterStartYear) {
        this.filterStartYear = filterStartYear;
    }

    public int getFilterEndYear() {
        return filterEndYear;
    }

    public void setFilterEndYear(int filterEndYear) {
        this.filterEndYear = filterEndYear;
    }

    public int getFilterStartMonth() {
        return filterStartMonth;
    }

    public void setFilterStartMonth(int filterStartMonth) {
        this.filterStartMonth = filterStartMonth;
    }

    public int getFilterEndMonth() {
        return filterEndMonth;
    }

    public void setFilterEndMonth(int filterEndMonth) {
        this.filterEndMonth = filterEndMonth;
    }
    public List<Object> getFilterList() {
        return filterList == null ? filterList : Collections.unmodifiableList(filterList);
    }
    public String getPriceBasis() {
        return priceBasis;
    }

    public void setPriceBasis(String priceBasis) {
        this.priceBasis = priceBasis;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

 

    public String getExcludedField() {
        return excludedField;
    }

    public void setExcludedField(String excludedField) {
        this.excludedField = excludedField;
    }    

    public String getCompanyMasterSid() {
        return companyMasterSid;
    }

    public void setCompanyMasterSid(String companyMasterSid) {
        this.companyMasterSid = companyMasterSid;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    
    public List<String> getVariableList() {
        return variableList == null ? variableList : Collections.unmodifiableList(variableList);
    }

    public String getPeriodBasis() {
        return periodBasis;
    }

    public void setPeriodBasis(String periodBasis) {
        this.periodBasis = periodBasis;
    }

    public Object getCustomer() {
        return customer;
    }

    public void setCustomer(Object customer) {
        this.customer = customer;
    }

    public Object getBrand() {
        return brand;
    }

    public void setBrand(Object brand) {
        this.brand = brand;
    }

    public Object getProduct() {
        return product;
    }

    public void setProduct(Object product) {
        this.product = product;
    }

    public SessionDTO getSessionDto() {
        return sessionDto;
    }

    public void setSessionDto(SessionDTO sessionDto) {
        this.sessionDto = sessionDto;
    }
    
}
