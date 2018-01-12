/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.itemmanagement.itemabstract.dto;

import com.stpl.ifs.util.HelperDTO;
import java.util.Date;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author mohamed.hameed
 */
public class ComponentLookUpDTO {

    private String componentId = StringUtils.EMPTY;
    private String componentNo = StringUtils.EMPTY;
    private String componentName = StringUtils.EMPTY;
    private String componentStatus = StringUtils.EMPTY;
    private String componentType = StringUtils.EMPTY;
    private HelperDTO componentStatus_DTO;
    private HelperDTO componentType_DTO;
    private boolean isCount = false;
    private Integer startIndex = 0;
    private Integer endIndex = 0;
    private Date startDate;
    private Date endDate;
    private String category = StringUtils.EMPTY;
    private String designation = StringUtils.EMPTY;
    private String planId = StringUtils.EMPTY;
    private String planName = StringUtils.EMPTY;
    private String tradeClass = StringUtils.EMPTY;
    private String parentPsId = StringUtils.EMPTY;
    private String parentPsName = StringUtils.EMPTY;
    private String masterSid = StringUtils.EMPTY;
    private String componentSid = StringUtils.EMPTY;
    private HelperDTO componentCategory_DTO;
    private HelperDTO rsProgramType_DTO;
    private String rsProgramType = StringUtils.EMPTY;
    private String startNewDate = StringUtils.EMPTY;
    private String endNewDate = StringUtils.EMPTY;

    public String getComponentId() {
        return componentId;
    }

    public void setComponentId(String componentId) {
        this.componentId = componentId;
    }

    public String getComponentNo() {
        return componentNo;
    }

    public void setComponentNo(String componentNo) {
        this.componentNo = componentNo;
    }

    public String getComponentName() {
        return componentName;
    }

    public void setComponentName(String componentName) {
        this.componentName = componentName;
    }

    public String getComponentStatus() {
        return componentStatus;
    }

    public void setComponentStatus(String componentStatus) {
        this.componentStatus = componentStatus;
    }

    public String getComponentType() {
        return componentType;
    }

    public void setComponentType(String componentType) {
        this.componentType = componentType;
    }

    public boolean isIsCount() {
        return isCount;
    }

    public void setIsCount(boolean isCount) {
        this.isCount = isCount;
    }

    public HelperDTO getComponentStatus_DTO() {
        return componentStatus_DTO;
    }

    public void setComponentStatus_DTO(HelperDTO componentStatus_DTO) {
        this.componentStatus_DTO = componentStatus_DTO;
    }

    public HelperDTO getComponentType_DTO() {
        return componentType_DTO;
    }

    public void setComponentType_DTO(HelperDTO componentType_DTO) {
        this.componentType_DTO = componentType_DTO;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getPlanId() {
        return planId;
    }

    public void setPlanId(String planId) {
        this.planId = planId;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public String getTradeClass() {
        return tradeClass;
    }

    public void setTradeClass(String tradeClass) {
        this.tradeClass = tradeClass;
    }

    public String getParentPsId() {
        return parentPsId;
    }

    public void setParentPsId(String parentPsId) {
        this.parentPsId = parentPsId;
    }

    public String getParentPsName() {
        return parentPsName;
    }

    public void setParentPsName(String parentPsName) {
        this.parentPsName = parentPsName;
    }

    public String getMasterSid() {
        return masterSid;
    }

    public void setMasterSid(String masterSid) {
        this.masterSid = masterSid;
    }

    public String getComponentSid() {
        return componentSid;
    }

    public void setComponentSid(String componentSid) {
        this.componentSid = componentSid;
    }

    public HelperDTO getComponentCategory_DTO() {
        return componentCategory_DTO;
    }

    public void setComponentCategory_DTO(HelperDTO componentCategory_DTO) {
        this.componentCategory_DTO = componentCategory_DTO;
    }

    public HelperDTO getRsProgramType_DTO() {
        return rsProgramType_DTO;
    }

    public void setRsProgramType_DTO(HelperDTO rsProgramType_DTO) {
        this.rsProgramType_DTO = rsProgramType_DTO;
    }

    public String getRsProgramType() {
        return rsProgramType;
    }

    public void setRsProgramType(String rsProgramType) {
        this.rsProgramType = rsProgramType;
    }

    public String getStartNewDate() {
        return startNewDate;
    }

    public void setStartNewDate(String startNewDate) {
        this.startNewDate = startNewDate;
    }

    public String getEndNewDate() {
        return endNewDate;
    }

    public void setEndNewDate(String endNewDate) {
        this.endNewDate = endNewDate;
    }

}
