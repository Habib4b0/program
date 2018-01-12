/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.discount.dto;

import com.stpl.ifs.util.HelperDTO;
import java.util.Date;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author vigneshkanna
 */
public class LookupDTO {

    private String cfpId = StringUtils.EMPTY;
    private String cfpNo = StringUtils.EMPTY;
    private String cfpName = StringUtils.EMPTY;
    private HelperDTO cfpStatus;
    private HelperDTO cfpType;
    private HelperDTO cfpCategory ;
    private String cfpDesignation = StringUtils.EMPTY;
    private String cfpPlanId = StringUtils.EMPTY;
    private String cfpPlanName = StringUtils.EMPTY;
    private String cfpTradeClass = StringUtils.EMPTY;
    private Date cfpStartDate;
    private Date cfpEndDate;
    private int cfpSystemId = 0;
    private String ifpId = StringUtils.EMPTY;
    private String ifpNo = StringUtils.EMPTY;
    private String ifpName = StringUtils.EMPTY;
    private HelperDTO ifpStatus;
    private HelperDTO ifpType;
    private HelperDTO ifpCategory ;
    private String ifpDesignation = StringUtils.EMPTY;
    private String ifpPlanId = StringUtils.EMPTY;
    private String ifpPlanName = StringUtils.EMPTY;
    private Date ifpStartDate;
    private Date ifpEndDate;
    private int ifpSystemId = 0;
    private String psId = StringUtils.EMPTY;
    private String psNo = StringUtils.EMPTY;
    private String psName = StringUtils.EMPTY;
    private HelperDTO psStatus;
    private HelperDTO psType;
    private HelperDTO psCategory ;
    private String psDesignation = StringUtils.EMPTY;
    private String parentPsId = StringUtils.EMPTY;
    private String parentPsName = StringUtils.EMPTY;
    private String psTradeClass = StringUtils.EMPTY;
    private Date psStartDate;
    private Date psEndDate;
    private int psSystemId = 0;
    private String rsId = StringUtils.EMPTY;
    private String rsNo = StringUtils.EMPTY;
    private String rsName = StringUtils.EMPTY;
    private HelperDTO rsStatus;
    private HelperDTO rsType;
    private HelperDTO rsCategory ;
    private String rsDesignation = StringUtils.EMPTY;
    private String parentrsId = StringUtils.EMPTY;
    private String parentrsName = StringUtils.EMPTY;
    private String rsTradeClass = StringUtils.EMPTY;
    private Date rsStartDate;
    private Date rsEndDate;
    private HelperDTO rebateProgramType;
    private int rsSystemId = 0;
    private String itemId = StringUtils.EMPTY;
    private String itemNo = StringUtils.EMPTY;
    private String itemName = StringUtils.EMPTY;
    private String itemStatus = StringUtils.EMPTY;
    private String itemType = StringUtils.EMPTY;
    private int itemSystemId = 0;
    private String custId = StringUtils.EMPTY;
    private String custNo = StringUtils.EMPTY;
    private String custName = StringUtils.EMPTY;
    private String custStatus = StringUtils.EMPTY;
    private String custType = StringUtils.EMPTY;
    private int custSystemId = 0;
    private String chId = StringUtils.EMPTY;
    private String chNo = StringUtils.EMPTY;
    private String chName = StringUtils.EMPTY;
    private String chStatus = StringUtils.EMPTY;
    private String chType = StringUtils.EMPTY;
    private int chSystemId = 0;

    private String formulaId = StringUtils.EMPTY;
    private String formulaNo = StringUtils.EMPTY;
    private String formulaName = StringUtils.EMPTY;
    private String formulaSysId = StringUtils.EMPTY;
    private Integer count = 0;
    private Integer startIndex = 0;
    private Integer offSet = 0;
    private Integer endIndex = 0;
    private boolean isCount = false;
    private String rebatePlanId = StringUtils.EMPTY;
    private String rebatePlanName = StringUtils.EMPTY;
    private String rebatePlanNo = StringUtils.EMPTY;
    private String rebatePlanStatus = StringUtils.EMPTY;
    private String rebatePlanType = StringUtils.EMPTY;
    private String rebatePlanSysId = StringUtils.EMPTY;

    public String getCfpId() {
        return cfpId;
    }

    public void setCfpId(String cfpId) {
        this.cfpId = cfpId;
    }

    public String getCfpNo() {
        return cfpNo;
    }

    public void setCfpNo(String cfpNo) {
        this.cfpNo = cfpNo;
    }

    public String getCfpName() {
        return cfpName;
    }

    public void setCfpName(String cfpName) {
        this.cfpName = cfpName;
    }

    public HelperDTO getCfpStatus() {
        return cfpStatus;
    }

    public void setCfpStatus(HelperDTO cfpStatus) {
        this.cfpStatus = cfpStatus;
    }

    public HelperDTO getCfpType() {
        return cfpType;
    }

    public void setCfpType(HelperDTO cfpType) {
        this.cfpType = cfpType;
    }

    public int getCfpSystemId() {
        return cfpSystemId;
    }

    public void setCfpSystemId(int cfpSystemId) {
        this.cfpSystemId = cfpSystemId;
    }

    public String getIfpId() {
        return ifpId;
    }

    public void setIfpId(String ifpId) {
        this.ifpId = ifpId;
    }

    public String getIfpNo() {
        return ifpNo;
    }

    public void setIfpNo(String ifpNo) {
        this.ifpNo = ifpNo;
    }

    public String getIfpName() {
        return ifpName;
    }

    public void setIfpName(String ifpName) {
        this.ifpName = ifpName;
    }

    public HelperDTO getIfpStatus() {
        return ifpStatus;
    }

    public void setIfpStatus(HelperDTO ifpStatus) {
        this.ifpStatus = ifpStatus;
    }

    public HelperDTO getIfpType() {
        return ifpType;
    }

    public void setIfpType(HelperDTO ifpType) {
        this.ifpType = ifpType;
    }

    public int getIfpSystemId() {
        return ifpSystemId;
    }

    public void setIfpSystemId(int ifpSystemId) {
        this.ifpSystemId = ifpSystemId;
    }

    public String getPsId() {
        return psId;
    }

    public void setPsId(String psId) {
        this.psId = psId;
    }

    public String getPsNo() {
        return psNo;
    }

    public void setPsNo(String psNo) {
        this.psNo = psNo;
    }

    public String getPsName() {
        return psName;
    }

    public void setPsName(String psName) {
        this.psName = psName;
    }

    public HelperDTO getPsStatus() {
        return psStatus;
    }

    public void setPsStatus(HelperDTO psStatus) {
        this.psStatus = psStatus;
    }

    public HelperDTO getPsType() {
        return psType;
    }

    public void setPsType(HelperDTO psType) {
        this.psType = psType;
    }

    public int getPsSystemId() {
        return psSystemId;
    }

    public void setPsSystemId(int psSystemId) {
        this.psSystemId = psSystemId;
    }

    public String getRsId() {
        return rsId;
    }

    public void setRsId(String rsId) {
        this.rsId = rsId;
    }

    public String getRsNo() {
        return rsNo;
    }

    public void setRsNo(String rsNo) {
        this.rsNo = rsNo;
    }

    public String getRsName() {
        return rsName;
    }

    public void setRsName(String rsName) {
        this.rsName = rsName;
    }

    public HelperDTO getRsStatus() {
        return rsStatus;
    }

    public void setRsStatus(HelperDTO rsStatus) {
        this.rsStatus = rsStatus;
    }

    public HelperDTO getRsType() {
        return rsType;
    }

    public void setRsType(HelperDTO rsType) {
        this.rsType = rsType;
    }

    public int getRsSystemId() {
        return rsSystemId;
    }

    public void setRsSystemId(int rsSystemId) {
        this.rsSystemId = rsSystemId;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemNo() {
        return itemNo;
    }

    public void setItemNo(String itemNo) {
        this.itemNo = itemNo;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemStatus() {
        return itemStatus;
    }

    public void setItemStatus(String itemStatus) {
        this.itemStatus = itemStatus;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public int getItemSystemId() {
        return itemSystemId;
    }

    public void setItemSystemId(int itemSystemId) {
        this.itemSystemId = itemSystemId;
    }

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public String getCustNo() {
        return custNo;
    }

    public void setCustNo(String custNo) {
        this.custNo = custNo;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getCustStatus() {
        return custStatus;
    }

    public void setCustStatus(String custStatus) {
        this.custStatus = custStatus;
    }

    public String getCustType() {
        return custType;
    }

    public void setCustType(String custType) {
        this.custType = custType;
    }

    public int getCustSystemId() {
        return custSystemId;
    }

    public void setCustSystemId(int custSystemId) {
        this.custSystemId = custSystemId;
    }

    public String getChId() {
        return chId;
    }

    public void setChId(String chId) {
        this.chId = chId;
    }

    public String getChNo() {
        return chNo;
    }

    public void setChNo(String chNo) {
        this.chNo = chNo;
    }

    public String getChName() {
        return chName;
    }

    public void setChName(String chName) {
        this.chName = chName;
    }

    public String getChStatus() {
        return chStatus;
    }

    public void setChStatus(String chStatus) {
        this.chStatus = chStatus;
    }

    public String getChType() {
        return chType;
    }

    public void setChType(String chType) {
        this.chType = chType;
    }

    public int getChSystemId() {
        return chSystemId;
    }

    public void setChSystemId(int chSystemId) {
        this.chSystemId = chSystemId;
    }

    public String getFormulaId() {
        return formulaId;
    }

    public void setFormulaId(String formulaId) {
        this.formulaId = formulaId;
    }

    public String getFormulaNo() {
        return formulaNo;
    }

    public void setFormulaNo(String formulaNo) {
        this.formulaNo = formulaNo;
    }

    public String getFormulaName() {
        return formulaName;
    }

    public void setFormulaName(String formulaName) {
        this.formulaName = formulaName;
    }

    public String getFormulaSysId() {
        return formulaSysId;
    }

    public void setFormulaSysId(String formulaSysId) {
        this.formulaSysId = formulaSysId;
    }

    public HelperDTO getCfpCategory() {
        return cfpCategory;
    }

    public void setCfpCategory(HelperDTO cfpCategory) {
        this.cfpCategory = cfpCategory;
    }

    public String getCfpDesignation() {
        return cfpDesignation;
    }

    public void setCfpDesignation(String cfpDesignation) {
        this.cfpDesignation = cfpDesignation;
    }

    public String getCfpPlanId() {
        return cfpPlanId;
    }

    public void setCfpPlanId(String cfpPlanId) {
        this.cfpPlanId = cfpPlanId;
    }

    public String getCfpPlanName() {
        return cfpPlanName;
    }

    public void setCfpPlanName(String cfpPlanName) {
        this.cfpPlanName = cfpPlanName;
    }

    public String getCfpTradeClass() {
        return cfpTradeClass;
    }

    public void setCfpTradeClass(String cfpTradeClass) {
        this.cfpTradeClass = cfpTradeClass;
    }

    public Date getCfpStartDate() {
        return cfpStartDate == null ? null : (Date) cfpStartDate.clone();
    }

    public void setCfpStartDate(Date cfpStartDate) {
        this.cfpStartDate = cfpStartDate == null ? null : (Date) cfpStartDate.clone();
    }

    public Date getCfpEndDate() {
        return cfpEndDate == null ? null : (Date) cfpEndDate.clone();
    }

    public void setCfpEndDate(Date cfpEndDate) {
        this.cfpEndDate = cfpEndDate == null ? null : (Date) cfpEndDate.clone();
    }

    public HelperDTO getIfpCategory() {
        return ifpCategory;
    }

    public void setIfpCategory(HelperDTO ifpCategory) {
        this.ifpCategory = ifpCategory;
    }

    public String getIfpDesignation() {
        return ifpDesignation;
    }

    public void setIfpDesignation(String ifpDesignation) {
        this.ifpDesignation = ifpDesignation;
    }

    public String getIfpPlanId() {
        return ifpPlanId;
    }

    public void setIfpPlanId(String ifpPlanId) {
        this.ifpPlanId = ifpPlanId;
    }

    public String getIfpPlanName() {
        return ifpPlanName;
    }

    public void setIfpPlanName(String ifpPlanName) {
        this.ifpPlanName = ifpPlanName;
    }

    public Date getIfpStartDate() {
        return ifpStartDate == null ? null : (Date) ifpStartDate.clone();
    }

    public void setIfpStartDate(Date ifpStartDate) {
        this.ifpStartDate = ifpStartDate == null ? null : (Date) ifpStartDate.clone();
    }

    public Date getIfpEndDate() {
        return ifpEndDate == null ? null : (Date) ifpEndDate.clone();
    }

    public void setIfpEndDate(Date ifpEndDate) {
        this.ifpEndDate = ifpEndDate == null ? null : (Date) ifpEndDate.clone();
    }

    public HelperDTO getPsCategory() {
        return psCategory;
    }

    public void setPsCategory(HelperDTO psCategory) {
        this.psCategory = psCategory;
    }

    public String getPsDesignation() {
        return psDesignation;
    }

    public void setPsDesignation(String psDesignation) {
        this.psDesignation = psDesignation;
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

    public String getPsTradeClass() {
        return psTradeClass;
    }

    public void setPsTradeClass(String psTradeClass) {
        this.psTradeClass = psTradeClass;
    }

    public Date getPsStartDate() {
        return psStartDate == null ? null : (Date) psStartDate.clone();
    }

    public void setPsStartDate(Date psStartDate) {
        this.psStartDate = psStartDate == null ? null : (Date) psStartDate.clone();
    }

    public Date getPsEndDate() {
        return psEndDate == null ? null : (Date) psEndDate.clone();
    }

    public void setPsEndDate(Date psEndDate) {
        this.psEndDate = psEndDate == null ? null : (Date) psEndDate.clone();
    }

    public HelperDTO getRsCategory() {
        return rsCategory;
    }

    public void setRsCategory(HelperDTO rsCategory) {
        this.rsCategory = rsCategory;
    }

    public String getRsDesignation() {
        return rsDesignation;
    }

    public void setRsDesignation(String rsDesignation) {
        this.rsDesignation = rsDesignation;
    }

    public String getParentrsId() {
        return parentrsId;
    }

    public void setParentrsId(String parentrsId) {
        this.parentrsId = parentrsId;
    }

    public String getParentrsName() {
        return parentrsName;
    }

    public void setParentrsName(String parentrsName) {
        this.parentrsName = parentrsName;
    }

    public String getRsTradeClass() {
        return rsTradeClass;
    }

    public void setRsTradeClass(String rsTradeClass) {
        this.rsTradeClass = rsTradeClass;
    }

    public Date getRsStartDate() {
        return rsStartDate == null ? null : (Date) rsStartDate.clone();
    }

    public void setRsStartDate(Date rsStartDate) {
        this.rsStartDate = rsStartDate == null ? null : (Date) rsStartDate.clone();
    }

    public Date getRsEndDate() {
        return rsEndDate == null ? null : (Date) rsEndDate.clone();
    }

    public void setRsEndDate(Date rsEndDate) {
        this.rsEndDate = rsEndDate == null ? null : (Date) rsEndDate.clone();
    }


    public HelperDTO getRebateProgramType() {
        return rebateProgramType;
    }

    public void setRebateProgramType(HelperDTO rebateProgramType) {
        this.rebateProgramType = rebateProgramType;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(Integer startIndex) {
        this.startIndex = startIndex;
    }

    public Integer getOffSet() {
        return offSet;
    }

    public void setOffSet(Integer offSet) {
        this.offSet = offSet;
    }

    public Integer getEndIndex() {
        return endIndex;
    }

    public void setEndIndex(Integer endIndex) {
        this.endIndex = endIndex;
    }

    public boolean isIsCount() {
        return isCount;
    }

    public void setIsCount(boolean isCount) {
        this.isCount = isCount;
    }

    public String getRebatePlanId() {
        return rebatePlanId;
    }

    public void setRebatePlanId(String rebatePlanId) {
        this.rebatePlanId = rebatePlanId;
    }

    public String getRebatePlanName() {
        return rebatePlanName;
    }

    public void setRebatePlanName(String rebatePlanName) {
        this.rebatePlanName = rebatePlanName;
    }

    public String getRebatePlanNo() {
        return rebatePlanNo;
    }

    public void setRebatePlanNo(String rebatePlanNo) {
        this.rebatePlanNo = rebatePlanNo;
    }

    public String getRebatePlanStatus() {
        return rebatePlanStatus;
    }

    public void setRebatePlanStatus(String rebatePlanStatus) {
        this.rebatePlanStatus = rebatePlanStatus;
    }

    public String getRebatePlanType() {
        return rebatePlanType;
    }

    public void setRebatePlanType(String rebatePlanType) {
        this.rebatePlanType = rebatePlanType;
    }

    public String getRebatePlanSysId() {
        return rebatePlanSysId;
    }

    public void setRebatePlanSysId(String rebatePlanSysId) {
        this.rebatePlanSysId = rebatePlanSysId;
    }

}
