/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.businessprocess.pipelineinventory.dto;

import com.stpl.app.arm.common.CommonLogic;
import com.vaadin.v7.data.Container;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.paged.logic.SortByColumn;

/**
 *
 * @author Porchelvi.Gunasekara
 */
public class CustomerGroupDTO implements Comparable<CustomerGroupDTO> {

    private String customerGroupSid = StringUtils.EMPTY;

    private String customerGroupName = StringUtils.EMPTY;

    private String customerGroupNo = StringUtils.EMPTY;

    private String customerGroupDesc = StringUtils.EMPTY;

    private String companyMasterSid = StringUtils.EMPTY;
    private String customerName = StringUtils.EMPTY;
    private Integer startIndex = 0;

    private Integer endIndex = 0;

    private Boolean indicator;

    private boolean include = false;
    private List<SortByColumn> sortedColumns;
    private Set<Container.Filter> filters = new HashSet<>();

    private Boolean selectedFlag = false;
    private int projectionId = 0;

    private boolean editFlag = false;
    private boolean viewFlag = false;
    private String viewSid = StringUtils.EMPTY;

    public CustomerGroupDTO() {
        /*
        THE DEFAULT CONSTRUCTOR
         */
    }

    public String getCustomerGroupSid() {
        return customerGroupSid;
    }

    public void setCustomerGroupSid(String customerGroupSid) {
        this.customerGroupSid = customerGroupSid;
    }

    public String getCustomerGroupName() {
        return customerGroupName;
    }

    public void setCustomerGroupName(String customerGroupName) {
        this.customerGroupName = customerGroupName;
    }

    public String getCustomerGroupNo() {
        return customerGroupNo;
    }

    public void setCustomerGroupNo(String customerGroupNo) {
        this.customerGroupNo = customerGroupNo;
    }

    public String getCustomerGroupDesc() {
        return customerGroupDesc;
    }

    public void setCustomerGroupDesc(String customerGroupDesc) {
        this.customerGroupDesc = customerGroupDesc;
    }

    public Integer getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(Integer startIndex) {
        this.startIndex = startIndex;
    }

    public Integer getEndIndex() {
        return endIndex;
    }

    public void setEndIndex(Integer endIndex) {
        this.endIndex = endIndex;
    }

    public Boolean getIndicator() {
        return indicator;
    }

    public void setIndicator(Boolean indicator) {
        this.indicator = indicator;
    }

    public boolean isInclude() {
        return include;
    }

    public void setInclude(boolean include) {
        this.include = include;
    }

    public String getCompanyMasterSid() {
        return companyMasterSid;
    }

    public void setCompanyMasterSid(String companyMasterSid) {
        this.companyMasterSid = companyMasterSid;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public List<SortByColumn> getSortedColumns() {
        return CommonLogic.getInstance().getArrayListCloned(sortedColumns);
    }

    public void setSortedColumns(List<SortByColumn> sortedColumns) {
        this.sortedColumns = CommonLogic.getInstance().getArrayListCloned(sortedColumns);
    }

    public Set<Container.Filter> getFilters() {
        return filters == null ? null : new HashSet<>(filters);
    }

    public void clearFilters() {
        filters.clear();
    }

    public void addFilter(Container.Filter filter) {
        this.filters.add(filter);
    }

    public void removeFilter(Container.Filter filter) {
        this.filters.remove(filter);
    }

    public void setFilters(Set<Container.Filter> filters) {
        this.filters = filters == null ? null : new HashSet<>(filters);
    }

    @Override
    public int compareTo(CustomerGroupDTO o) {
        if (StringUtils.EMPTY.equals(this.companyMasterSid)) {
            return this.companyMasterSid.compareTo(o.getCompanyMasterSid());
        }
        if (StringUtils.EMPTY.equals(this.customerGroupSid)) {
            return this.customerGroupSid.compareTo(o.getCustomerGroupSid());
        }
        return 0;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    public Boolean getSelectedFlag() {
        return selectedFlag;
    }

    public void setSelectedFlag(Boolean selectedFlag) {
        this.selectedFlag = selectedFlag;
    }

    public int getProjectionId() {
        return projectionId;
    }

    public void setProjectionId(int projectionId) {
        this.projectionId = projectionId;
    }

    public boolean isEditFlag() {
        return editFlag;
    }

    public void setEditFlag(boolean editFlag) {
        this.editFlag = editFlag;
    }

    public boolean isViewFlag() {
        return viewFlag;
    }

    public void setViewFlag(boolean viewFlag) {
        this.viewFlag = viewFlag;
    }

    public String getViewSid() {
        return viewSid;
    }

    public void setViewSid(String viewSid) {
        this.viewSid = viewSid;
    }

}
