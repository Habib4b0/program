/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.adminconsole.itemgroup.dto;

import com.stpl.ifs.util.HelperDTO;
import java.util.Date;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author shrihariharan
 */
public class ItemGroupDTO {

    private static final long serialVersionUID = 255944762051369498L;

    private String itemGroupName = StringUtils.EMPTY;

    private String itemGroupDesc = StringUtils.EMPTY;

    private String itemGroupNo = StringUtils.EMPTY;

    private String company = StringUtils.EMPTY;

    private Date createdDate;

    private Date modifiedDate;

    private String createdBy;

    private String modifiedBy;

    private int itemGroupSystemId;

    private HelperDTO companyDdlb;

    private String companyNo;

    private int versionNo;

    private int companySystemId = 0;

    private String itemFilter = StringUtils.EMPTY;

    public String getItemGroupName() {
        return itemGroupName;
    }

    public void setItemGroupName(final String itemGroupName) {
        this.itemGroupName = itemGroupName;
    }

    public String getItemGroupDesc() {
        return itemGroupDesc;
    }

    public void setItemGroupDesc(final String itemGroupDesc) {
        this.itemGroupDesc = itemGroupDesc;
    }

    public String getItemGroupNo() {
        return itemGroupNo;
    }

    public void setItemGroupNo(final String itemGroupNo) {
        this.itemGroupNo = itemGroupNo;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(final String company) {
        this.company = company;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(final Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(final Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public int getItemGroupSystemId() {
        return itemGroupSystemId;
    }

    public void setItemGroupSystemId(final int itemGroupSystemId) {
        this.itemGroupSystemId = itemGroupSystemId;
    }

    public HelperDTO getCompanyDdlb() {
        return companyDdlb;
    }

    public void setCompanyDdlb(final HelperDTO companyDdlb) {
        this.companyDdlb = companyDdlb;
    }

    public String getCompanyNo() {
        return companyNo;
    }

    public void setCompanyNo(final String companyNo) {
        this.companyNo = companyNo;
    }

    public int getVersionNo() {
        return versionNo;
    }

    public void setVersionNo(final int versionNo) {
        this.versionNo = versionNo;
    }

    public int getCompanySystemId() {
        return companySystemId;
    }

    public void setCompanySystemId(int companySystemId) {
        this.companySystemId = companySystemId;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public String getItemFilter() {
        return itemFilter;
    }

    public void setItemFilter(String itemFilter) {
        this.itemFilter = itemFilter;
    }

}
