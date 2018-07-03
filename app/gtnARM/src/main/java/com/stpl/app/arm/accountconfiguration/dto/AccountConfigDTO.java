/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.accountconfiguration.dto;

import com.stpl.app.arm.common.CommonLogic;
import com.stpl.ifs.util.HelperDTO;
import com.vaadin.v7.data.Container;
import com.vaadin.ui.Component;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.paged.logic.SortByColumn;

/**
 *
 * @author Srithar.R
 */
public class AccountConfigDTO {

    private String companyNo = StringUtils.EMPTY;

    private String companyName = StringUtils.EMPTY;
    private String businessUnitNo = StringUtils.EMPTY;
    private String businessUnitName = StringUtils.EMPTY;
    private String account = StringUtils.EMPTY;
    private String costCentre = StringUtils.EMPTY;
    private String brand = StringUtils.EMPTY;
    private String createdBy = StringUtils.EMPTY;
    private String source = StringUtils.EMPTY;
    private String accountDdlb;
    private String companyIdWithName = StringUtils.EMPTY;
    private String buIdWithName = StringUtils.EMPTY;
    private String brandWithIdName = StringUtils.EMPTY;
    private String createdDate;
    private String modifiedDate;
    private int companyDdlb;

    private int businessDdlb;
    private int brandDdlb;
    private int start;
    private int offset;
    private int companySid;
    private int buSid;
    private int brandSid;
    private HelperDTO companyNoHelperDto;
    private HelperDTO businessNoHelperDto;

    private boolean count;
    private boolean checkRecord;
    private int masterId;
    private Set<Container.Filter> filters = new HashSet<>();
    private List<SortByColumn> sortByColumns = new ArrayList<>();
    private Map<String, Component> fieldFactoryComponentMap = new HashMap<>();
    private boolean countCheck;

    public AccountConfigDTO() {
        /*
        THE DEFAULT CONSTRUCTOR
         */
    }

    public String getCompanyNo() {
        return companyNo;
    }

    public void setCompanyNo(String companyNo) {
        this.companyNo = companyNo;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getBusinessUnitNo() {
        return businessUnitNo;
    }

    public void setBusinessUnitNo(String businessUnitNo) {
        this.businessUnitNo = businessUnitNo;
    }

    public String getBusinessUnitName() {
        return businessUnitName;
    }

    public void setBusinessUnitName(String businessUnitName) {
        this.businessUnitName = businessUnitName;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getCostCentre() {
        return costCentre;
    }

    public void setCostCentre(String costCentre) {
        this.costCentre = costCentre;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(String modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public int getCompanyDdlb() {
        return companyDdlb;
    }

    public void setCompanyDdlb(int companyDdlb) {
        this.companyDdlb = companyDdlb;
    }

    public int getBusinessDdlb() {
        return businessDdlb;
    }

    public void setBusinessDdlb(int businessDdlb) {
        this.businessDdlb = businessDdlb;
    }

    public int getBrandDdlb() {
        return brandDdlb;
    }

    public void setBrandDdlb(int brandDdlb) {
        this.brandDdlb = brandDdlb;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public boolean isCount() {
        return count;
    }

    public void setCount(boolean count) {
        this.count = count;
    }

    public int getCompanySid() {
        return companySid;
    }

    public void setCompanySid(int companySid) {
        this.companySid = companySid;
    }

    public int getBuSid() {
        return buSid;
    }

    public void setBuSid(int buSid) {
        this.buSid = buSid;
    }

    public int getBrandSid() {
        return brandSid;
    }

    public void setBrandSid(int brandSid) {
        this.brandSid = brandSid;
    }

    public Set<Container.Filter> getFilters() {
        return filters == null ? null : new HashSet<>(filters);
    }

    public void setFilters(Set<Container.Filter> filters) {
        this.filters = filters == null ? null : new HashSet<>(filters);
    }

    public List<SortByColumn> getSortByColumns() {
        return CommonLogic.getInstance().getArrayListCloned(sortByColumns);
    }

    public void setSortByColumns(List<SortByColumn> sortByColumns) {
        this.sortByColumns = CommonLogic.getInstance().getArrayListCloned(sortByColumns);
    }

    public String getAccountDdlb() {
        return accountDdlb;
    }

    public void setAccountDdlb(String accountDdlb) {
        this.accountDdlb = accountDdlb;
    }

    public boolean getCheckRecord() {
        return checkRecord;
    }

    public void setCheckRecord(boolean checkRecord) {
        this.checkRecord = checkRecord;
    }

    public int getMasterId() {
        return masterId;
    }

    public void setMasterId(int masterId) {
        this.masterId = masterId;
    }

    public HelperDTO getCompanyNoHelperDto() {
        return companyNoHelperDto;
    }

    public void setCompanyNoHelperDto(HelperDTO companyNoHelperDto) {
        this.companyNoHelperDto = companyNoHelperDto;
    }

    public HelperDTO getBusinessNoHelperDto() {
        return businessNoHelperDto;
    }

    public void setBusinessNoHelperDto(HelperDTO businessNoHelperDto) {
        this.businessNoHelperDto = businessNoHelperDto;
    }

    public Map<String, Component> getFieldFactoryComponentMap() {
        return Collections.unmodifiableMap(fieldFactoryComponentMap);
    }

    public Component getFieldFactoryComponent(String propertyId) {
        if (propertyId == null) {
            throw new NullPointerException("Property for getting the component is null");
        }
        return fieldFactoryComponentMap.get(propertyId);
    }

    public void setFieldFactoryComponentMap(Map<String, Component> fieldFactoryComponentMap) {
        this.fieldFactoryComponentMap = fieldFactoryComponentMap;
    }

    public void addFieldFactoryMap(String propertyId, Component value) {
        this.fieldFactoryComponentMap.put(propertyId, value);
    }

    public String getCompanyIdWithName() {
        return companyIdWithName;
    }

    public void setCompanyIdWithName(String companyIdWithName) {
        this.companyIdWithName = companyIdWithName;
    }

    public String getBuIdWithName() {
        return buIdWithName;
    }

    public void setBuIdWithName(String buIdWithName) {
        this.buIdWithName = buIdWithName;
    }

    public String getBrandWithIdName() {
        return brandWithIdName;
    }

    public void setBrandWithIdName(String brandWithIdName) {
        this.brandWithIdName = brandWithIdName;
    }

    public boolean isCountCheck() {
        return countCheck;
    }

    public void setCountCheck(boolean countCheck) {
        this.countCheck = countCheck;
    }

}
