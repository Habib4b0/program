package com.stpl.app.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link RebatePlanMaster}.
 * </p>
 *
 * @author
 * @see RebatePlanMaster
 * @generated
 */
public class RebatePlanMasterWrapper implements RebatePlanMaster,
    ModelWrapper<RebatePlanMaster> {
    private RebatePlanMaster _rebatePlanMaster;

    public RebatePlanMasterWrapper(RebatePlanMaster rebatePlanMaster) {
        _rebatePlanMaster = rebatePlanMaster;
    }

    @Override
    public Class<?> getModelClass() {
        return RebatePlanMaster.class;
    }

    @Override
    public String getModelClassName() {
        return RebatePlanMaster.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("selfGrowthIndicator", getSelfGrowthIndicator());
        attributes.put("rebateStructure", getRebateStructure());
        attributes.put("marketShareFrom", getMarketShareFrom());
        attributes.put("secondaryRebatePlanNo", getSecondaryRebatePlanNo());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("rebateRangeBasedOn", getRebateRangeBasedOn());
        attributes.put("cdrModelSid", getCdrModelSid());
        attributes.put("rebateRule", getRebateRule());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("source", getSource());
        attributes.put("rebateBasedOn", getRebateBasedOn());
        attributes.put("rebatePlanType", getRebatePlanType());
        attributes.put("rebatePlanId", getRebatePlanId());
        attributes.put("manfCompanyMasterSid", getManfCompanyMasterSid());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("inboundStatus", getInboundStatus());
        attributes.put("secondaryRebatePlanId", getSecondaryRebatePlanId());
        attributes.put("marketShareIndicator", getMarketShareIndicator());
        attributes.put("bogoEligible", getBogoEligible());
        attributes.put("marketShareTo", getMarketShareTo());
        attributes.put("rebatePlanStatus", getRebatePlanStatus());
        attributes.put("rebatePlanMasterSid", getRebatePlanMasterSid());
        attributes.put("marketShareReference", getMarketShareReference());
        attributes.put("netSalesFormulaMasterSid", getNetSalesFormulaMasterSid());
        attributes.put("selfGrowthFrom", getSelfGrowthFrom());
        attributes.put("internalNotes", getInternalNotes());
        attributes.put("secondaryRebatePlanName", getSecondaryRebatePlanName());
        attributes.put("recordLockStatus", getRecordLockStatus());
        attributes.put("rebatePlanName", getRebatePlanName());
        attributes.put("selfGrowthReference", getSelfGrowthReference());
        attributes.put("batchId", getBatchId());
        attributes.put("formulaType", getFormulaType());
        attributes.put("selfGrowthTo", getSelfGrowthTo());
        attributes.put("rebatePlanNo", getRebatePlanNo());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        String selfGrowthIndicator = (String) attributes.get(
                "selfGrowthIndicator");

        if (selfGrowthIndicator != null) {
            setSelfGrowthIndicator(selfGrowthIndicator);
        }

        String rebateStructure = (String) attributes.get("rebateStructure");

        if (rebateStructure != null) {
            setRebateStructure(rebateStructure);
        }

        Date marketShareFrom = (Date) attributes.get("marketShareFrom");

        if (marketShareFrom != null) {
            setMarketShareFrom(marketShareFrom);
        }

        String secondaryRebatePlanNo = (String) attributes.get(
                "secondaryRebatePlanNo");

        if (secondaryRebatePlanNo != null) {
            setSecondaryRebatePlanNo(secondaryRebatePlanNo);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        Integer rebateRangeBasedOn = (Integer) attributes.get(
                "rebateRangeBasedOn");

        if (rebateRangeBasedOn != null) {
            setRebateRangeBasedOn(rebateRangeBasedOn);
        }

        String cdrModelSid = (String) attributes.get("cdrModelSid");

        if (cdrModelSid != null) {
            setCdrModelSid(cdrModelSid);
        }

        String rebateRule = (String) attributes.get("rebateRule");

        if (rebateRule != null) {
            setRebateRule(rebateRule);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        Integer createdBy = (Integer) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        String source = (String) attributes.get("source");

        if (source != null) {
            setSource(source);
        }

        Integer rebateBasedOn = (Integer) attributes.get("rebateBasedOn");

        if (rebateBasedOn != null) {
            setRebateBasedOn(rebateBasedOn);
        }

        Integer rebatePlanType = (Integer) attributes.get("rebatePlanType");

        if (rebatePlanType != null) {
            setRebatePlanType(rebatePlanType);
        }

        String rebatePlanId = (String) attributes.get("rebatePlanId");

        if (rebatePlanId != null) {
            setRebatePlanId(rebatePlanId);
        }

        Integer manfCompanyMasterSid = (Integer) attributes.get(
                "manfCompanyMasterSid");

        if (manfCompanyMasterSid != null) {
            setManfCompanyMasterSid(manfCompanyMasterSid);
        }

        Integer modifiedBy = (Integer) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        String inboundStatus = (String) attributes.get("inboundStatus");

        if (inboundStatus != null) {
            setInboundStatus(inboundStatus);
        }

        String secondaryRebatePlanId = (String) attributes.get(
                "secondaryRebatePlanId");

        if (secondaryRebatePlanId != null) {
            setSecondaryRebatePlanId(secondaryRebatePlanId);
        }

        String marketShareIndicator = (String) attributes.get(
                "marketShareIndicator");

        if (marketShareIndicator != null) {
            setMarketShareIndicator(marketShareIndicator);
        }

        String bogoEligible = (String) attributes.get("bogoEligible");

        if (bogoEligible != null) {
            setBogoEligible(bogoEligible);
        }

        Date marketShareTo = (Date) attributes.get("marketShareTo");

        if (marketShareTo != null) {
            setMarketShareTo(marketShareTo);
        }

        Integer rebatePlanStatus = (Integer) attributes.get("rebatePlanStatus");

        if (rebatePlanStatus != null) {
            setRebatePlanStatus(rebatePlanStatus);
        }

        Integer rebatePlanMasterSid = (Integer) attributes.get(
                "rebatePlanMasterSid");

        if (rebatePlanMasterSid != null) {
            setRebatePlanMasterSid(rebatePlanMasterSid);
        }

        String marketShareReference = (String) attributes.get(
                "marketShareReference");

        if (marketShareReference != null) {
            setMarketShareReference(marketShareReference);
        }

        String netSalesFormulaMasterSid = (String) attributes.get(
                "netSalesFormulaMasterSid");

        if (netSalesFormulaMasterSid != null) {
            setNetSalesFormulaMasterSid(netSalesFormulaMasterSid);
        }

        Date selfGrowthFrom = (Date) attributes.get("selfGrowthFrom");

        if (selfGrowthFrom != null) {
            setSelfGrowthFrom(selfGrowthFrom);
        }

        String internalNotes = (String) attributes.get("internalNotes");

        if (internalNotes != null) {
            setInternalNotes(internalNotes);
        }

        String secondaryRebatePlanName = (String) attributes.get(
                "secondaryRebatePlanName");

        if (secondaryRebatePlanName != null) {
            setSecondaryRebatePlanName(secondaryRebatePlanName);
        }

        Boolean recordLockStatus = (Boolean) attributes.get("recordLockStatus");

        if (recordLockStatus != null) {
            setRecordLockStatus(recordLockStatus);
        }

        String rebatePlanName = (String) attributes.get("rebatePlanName");

        if (rebatePlanName != null) {
            setRebatePlanName(rebatePlanName);
        }

        String selfGrowthReference = (String) attributes.get(
                "selfGrowthReference");

        if (selfGrowthReference != null) {
            setSelfGrowthReference(selfGrowthReference);
        }

        String batchId = (String) attributes.get("batchId");

        if (batchId != null) {
            setBatchId(batchId);
        }

        Integer formulaType = (Integer) attributes.get("formulaType");

        if (formulaType != null) {
            setFormulaType(formulaType);
        }

        Date selfGrowthTo = (Date) attributes.get("selfGrowthTo");

        if (selfGrowthTo != null) {
            setSelfGrowthTo(selfGrowthTo);
        }

        String rebatePlanNo = (String) attributes.get("rebatePlanNo");

        if (rebatePlanNo != null) {
            setRebatePlanNo(rebatePlanNo);
        }
    }

    /**
    * Returns the primary key of this rebate plan master.
    *
    * @return the primary key of this rebate plan master
    */
    @Override
    public int getPrimaryKey() {
        return _rebatePlanMaster.getPrimaryKey();
    }

    /**
    * Sets the primary key of this rebate plan master.
    *
    * @param primaryKey the primary key of this rebate plan master
    */
    @Override
    public void setPrimaryKey(int primaryKey) {
        _rebatePlanMaster.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the self growth indicator of this rebate plan master.
    *
    * @return the self growth indicator of this rebate plan master
    */
    @Override
    public java.lang.String getSelfGrowthIndicator() {
        return _rebatePlanMaster.getSelfGrowthIndicator();
    }

    /**
    * Sets the self growth indicator of this rebate plan master.
    *
    * @param selfGrowthIndicator the self growth indicator of this rebate plan master
    */
    @Override
    public void setSelfGrowthIndicator(java.lang.String selfGrowthIndicator) {
        _rebatePlanMaster.setSelfGrowthIndicator(selfGrowthIndicator);
    }

    /**
    * Returns the rebate structure of this rebate plan master.
    *
    * @return the rebate structure of this rebate plan master
    */
    @Override
    public java.lang.String getRebateStructure() {
        return _rebatePlanMaster.getRebateStructure();
    }

    /**
    * Sets the rebate structure of this rebate plan master.
    *
    * @param rebateStructure the rebate structure of this rebate plan master
    */
    @Override
    public void setRebateStructure(java.lang.String rebateStructure) {
        _rebatePlanMaster.setRebateStructure(rebateStructure);
    }

    /**
    * Returns the market share from of this rebate plan master.
    *
    * @return the market share from of this rebate plan master
    */
    @Override
    public java.util.Date getMarketShareFrom() {
        return _rebatePlanMaster.getMarketShareFrom();
    }

    /**
    * Sets the market share from of this rebate plan master.
    *
    * @param marketShareFrom the market share from of this rebate plan master
    */
    @Override
    public void setMarketShareFrom(java.util.Date marketShareFrom) {
        _rebatePlanMaster.setMarketShareFrom(marketShareFrom);
    }

    /**
    * Returns the secondary rebate plan no of this rebate plan master.
    *
    * @return the secondary rebate plan no of this rebate plan master
    */
    @Override
    public java.lang.String getSecondaryRebatePlanNo() {
        return _rebatePlanMaster.getSecondaryRebatePlanNo();
    }

    /**
    * Sets the secondary rebate plan no of this rebate plan master.
    *
    * @param secondaryRebatePlanNo the secondary rebate plan no of this rebate plan master
    */
    @Override
    public void setSecondaryRebatePlanNo(java.lang.String secondaryRebatePlanNo) {
        _rebatePlanMaster.setSecondaryRebatePlanNo(secondaryRebatePlanNo);
    }

    /**
    * Returns the modified date of this rebate plan master.
    *
    * @return the modified date of this rebate plan master
    */
    @Override
    public java.util.Date getModifiedDate() {
        return _rebatePlanMaster.getModifiedDate();
    }

    /**
    * Sets the modified date of this rebate plan master.
    *
    * @param modifiedDate the modified date of this rebate plan master
    */
    @Override
    public void setModifiedDate(java.util.Date modifiedDate) {
        _rebatePlanMaster.setModifiedDate(modifiedDate);
    }

    /**
    * Returns the rebate range based on of this rebate plan master.
    *
    * @return the rebate range based on of this rebate plan master
    */
    @Override
    public int getRebateRangeBasedOn() {
        return _rebatePlanMaster.getRebateRangeBasedOn();
    }

    /**
    * Sets the rebate range based on of this rebate plan master.
    *
    * @param rebateRangeBasedOn the rebate range based on of this rebate plan master
    */
    @Override
    public void setRebateRangeBasedOn(int rebateRangeBasedOn) {
        _rebatePlanMaster.setRebateRangeBasedOn(rebateRangeBasedOn);
    }

    /**
    * Returns the cdr model sid of this rebate plan master.
    *
    * @return the cdr model sid of this rebate plan master
    */
    @Override
    public java.lang.String getCdrModelSid() {
        return _rebatePlanMaster.getCdrModelSid();
    }

    /**
    * Sets the cdr model sid of this rebate plan master.
    *
    * @param cdrModelSid the cdr model sid of this rebate plan master
    */
    @Override
    public void setCdrModelSid(java.lang.String cdrModelSid) {
        _rebatePlanMaster.setCdrModelSid(cdrModelSid);
    }

    /**
    * Returns the rebate rule of this rebate plan master.
    *
    * @return the rebate rule of this rebate plan master
    */
    @Override
    public java.lang.String getRebateRule() {
        return _rebatePlanMaster.getRebateRule();
    }

    /**
    * Sets the rebate rule of this rebate plan master.
    *
    * @param rebateRule the rebate rule of this rebate plan master
    */
    @Override
    public void setRebateRule(java.lang.String rebateRule) {
        _rebatePlanMaster.setRebateRule(rebateRule);
    }

    /**
    * Returns the created date of this rebate plan master.
    *
    * @return the created date of this rebate plan master
    */
    @Override
    public java.util.Date getCreatedDate() {
        return _rebatePlanMaster.getCreatedDate();
    }

    /**
    * Sets the created date of this rebate plan master.
    *
    * @param createdDate the created date of this rebate plan master
    */
    @Override
    public void setCreatedDate(java.util.Date createdDate) {
        _rebatePlanMaster.setCreatedDate(createdDate);
    }

    /**
    * Returns the created by of this rebate plan master.
    *
    * @return the created by of this rebate plan master
    */
    @Override
    public int getCreatedBy() {
        return _rebatePlanMaster.getCreatedBy();
    }

    /**
    * Sets the created by of this rebate plan master.
    *
    * @param createdBy the created by of this rebate plan master
    */
    @Override
    public void setCreatedBy(int createdBy) {
        _rebatePlanMaster.setCreatedBy(createdBy);
    }

    /**
    * Returns the source of this rebate plan master.
    *
    * @return the source of this rebate plan master
    */
    @Override
    public java.lang.String getSource() {
        return _rebatePlanMaster.getSource();
    }

    /**
    * Sets the source of this rebate plan master.
    *
    * @param source the source of this rebate plan master
    */
    @Override
    public void setSource(java.lang.String source) {
        _rebatePlanMaster.setSource(source);
    }

    /**
    * Returns the rebate based on of this rebate plan master.
    *
    * @return the rebate based on of this rebate plan master
    */
    @Override
    public int getRebateBasedOn() {
        return _rebatePlanMaster.getRebateBasedOn();
    }

    /**
    * Sets the rebate based on of this rebate plan master.
    *
    * @param rebateBasedOn the rebate based on of this rebate plan master
    */
    @Override
    public void setRebateBasedOn(int rebateBasedOn) {
        _rebatePlanMaster.setRebateBasedOn(rebateBasedOn);
    }

    /**
    * Returns the rebate plan type of this rebate plan master.
    *
    * @return the rebate plan type of this rebate plan master
    */
    @Override
    public int getRebatePlanType() {
        return _rebatePlanMaster.getRebatePlanType();
    }

    /**
    * Sets the rebate plan type of this rebate plan master.
    *
    * @param rebatePlanType the rebate plan type of this rebate plan master
    */
    @Override
    public void setRebatePlanType(int rebatePlanType) {
        _rebatePlanMaster.setRebatePlanType(rebatePlanType);
    }

    /**
    * Returns the rebate plan ID of this rebate plan master.
    *
    * @return the rebate plan ID of this rebate plan master
    */
    @Override
    public java.lang.String getRebatePlanId() {
        return _rebatePlanMaster.getRebatePlanId();
    }

    /**
    * Sets the rebate plan ID of this rebate plan master.
    *
    * @param rebatePlanId the rebate plan ID of this rebate plan master
    */
    @Override
    public void setRebatePlanId(java.lang.String rebatePlanId) {
        _rebatePlanMaster.setRebatePlanId(rebatePlanId);
    }

    /**
    * Returns the manf company master sid of this rebate plan master.
    *
    * @return the manf company master sid of this rebate plan master
    */
    @Override
    public int getManfCompanyMasterSid() {
        return _rebatePlanMaster.getManfCompanyMasterSid();
    }

    /**
    * Sets the manf company master sid of this rebate plan master.
    *
    * @param manfCompanyMasterSid the manf company master sid of this rebate plan master
    */
    @Override
    public void setManfCompanyMasterSid(int manfCompanyMasterSid) {
        _rebatePlanMaster.setManfCompanyMasterSid(manfCompanyMasterSid);
    }

    /**
    * Returns the modified by of this rebate plan master.
    *
    * @return the modified by of this rebate plan master
    */
    @Override
    public int getModifiedBy() {
        return _rebatePlanMaster.getModifiedBy();
    }

    /**
    * Sets the modified by of this rebate plan master.
    *
    * @param modifiedBy the modified by of this rebate plan master
    */
    @Override
    public void setModifiedBy(int modifiedBy) {
        _rebatePlanMaster.setModifiedBy(modifiedBy);
    }

    /**
    * Returns the inbound status of this rebate plan master.
    *
    * @return the inbound status of this rebate plan master
    */
    @Override
    public java.lang.String getInboundStatus() {
        return _rebatePlanMaster.getInboundStatus();
    }

    /**
    * Sets the inbound status of this rebate plan master.
    *
    * @param inboundStatus the inbound status of this rebate plan master
    */
    @Override
    public void setInboundStatus(java.lang.String inboundStatus) {
        _rebatePlanMaster.setInboundStatus(inboundStatus);
    }

    /**
    * Returns the secondary rebate plan ID of this rebate plan master.
    *
    * @return the secondary rebate plan ID of this rebate plan master
    */
    @Override
    public java.lang.String getSecondaryRebatePlanId() {
        return _rebatePlanMaster.getSecondaryRebatePlanId();
    }

    /**
    * Sets the secondary rebate plan ID of this rebate plan master.
    *
    * @param secondaryRebatePlanId the secondary rebate plan ID of this rebate plan master
    */
    @Override
    public void setSecondaryRebatePlanId(java.lang.String secondaryRebatePlanId) {
        _rebatePlanMaster.setSecondaryRebatePlanId(secondaryRebatePlanId);
    }

    /**
    * Returns the market share indicator of this rebate plan master.
    *
    * @return the market share indicator of this rebate plan master
    */
    @Override
    public java.lang.String getMarketShareIndicator() {
        return _rebatePlanMaster.getMarketShareIndicator();
    }

    /**
    * Sets the market share indicator of this rebate plan master.
    *
    * @param marketShareIndicator the market share indicator of this rebate plan master
    */
    @Override
    public void setMarketShareIndicator(java.lang.String marketShareIndicator) {
        _rebatePlanMaster.setMarketShareIndicator(marketShareIndicator);
    }

    /**
    * Returns the bogo eligible of this rebate plan master.
    *
    * @return the bogo eligible of this rebate plan master
    */
    @Override
    public java.lang.String getBogoEligible() {
        return _rebatePlanMaster.getBogoEligible();
    }

    /**
    * Sets the bogo eligible of this rebate plan master.
    *
    * @param bogoEligible the bogo eligible of this rebate plan master
    */
    @Override
    public void setBogoEligible(java.lang.String bogoEligible) {
        _rebatePlanMaster.setBogoEligible(bogoEligible);
    }

    /**
    * Returns the market share to of this rebate plan master.
    *
    * @return the market share to of this rebate plan master
    */
    @Override
    public java.util.Date getMarketShareTo() {
        return _rebatePlanMaster.getMarketShareTo();
    }

    /**
    * Sets the market share to of this rebate plan master.
    *
    * @param marketShareTo the market share to of this rebate plan master
    */
    @Override
    public void setMarketShareTo(java.util.Date marketShareTo) {
        _rebatePlanMaster.setMarketShareTo(marketShareTo);
    }

    /**
    * Returns the rebate plan status of this rebate plan master.
    *
    * @return the rebate plan status of this rebate plan master
    */
    @Override
    public int getRebatePlanStatus() {
        return _rebatePlanMaster.getRebatePlanStatus();
    }

    /**
    * Sets the rebate plan status of this rebate plan master.
    *
    * @param rebatePlanStatus the rebate plan status of this rebate plan master
    */
    @Override
    public void setRebatePlanStatus(int rebatePlanStatus) {
        _rebatePlanMaster.setRebatePlanStatus(rebatePlanStatus);
    }

    /**
    * Returns the rebate plan master sid of this rebate plan master.
    *
    * @return the rebate plan master sid of this rebate plan master
    */
    @Override
    public int getRebatePlanMasterSid() {
        return _rebatePlanMaster.getRebatePlanMasterSid();
    }

    /**
    * Sets the rebate plan master sid of this rebate plan master.
    *
    * @param rebatePlanMasterSid the rebate plan master sid of this rebate plan master
    */
    @Override
    public void setRebatePlanMasterSid(int rebatePlanMasterSid) {
        _rebatePlanMaster.setRebatePlanMasterSid(rebatePlanMasterSid);
    }

    /**
    * Returns the market share reference of this rebate plan master.
    *
    * @return the market share reference of this rebate plan master
    */
    @Override
    public java.lang.String getMarketShareReference() {
        return _rebatePlanMaster.getMarketShareReference();
    }

    /**
    * Sets the market share reference of this rebate plan master.
    *
    * @param marketShareReference the market share reference of this rebate plan master
    */
    @Override
    public void setMarketShareReference(java.lang.String marketShareReference) {
        _rebatePlanMaster.setMarketShareReference(marketShareReference);
    }

    /**
    * Returns the net sales formula master sid of this rebate plan master.
    *
    * @return the net sales formula master sid of this rebate plan master
    */
    @Override
    public java.lang.String getNetSalesFormulaMasterSid() {
        return _rebatePlanMaster.getNetSalesFormulaMasterSid();
    }

    /**
    * Sets the net sales formula master sid of this rebate plan master.
    *
    * @param netSalesFormulaMasterSid the net sales formula master sid of this rebate plan master
    */
    @Override
    public void setNetSalesFormulaMasterSid(
        java.lang.String netSalesFormulaMasterSid) {
        _rebatePlanMaster.setNetSalesFormulaMasterSid(netSalesFormulaMasterSid);
    }

    /**
    * Returns the self growth from of this rebate plan master.
    *
    * @return the self growth from of this rebate plan master
    */
    @Override
    public java.util.Date getSelfGrowthFrom() {
        return _rebatePlanMaster.getSelfGrowthFrom();
    }

    /**
    * Sets the self growth from of this rebate plan master.
    *
    * @param selfGrowthFrom the self growth from of this rebate plan master
    */
    @Override
    public void setSelfGrowthFrom(java.util.Date selfGrowthFrom) {
        _rebatePlanMaster.setSelfGrowthFrom(selfGrowthFrom);
    }

    /**
    * Returns the internal notes of this rebate plan master.
    *
    * @return the internal notes of this rebate plan master
    */
    @Override
    public java.lang.String getInternalNotes() {
        return _rebatePlanMaster.getInternalNotes();
    }

    /**
    * Sets the internal notes of this rebate plan master.
    *
    * @param internalNotes the internal notes of this rebate plan master
    */
    @Override
    public void setInternalNotes(java.lang.String internalNotes) {
        _rebatePlanMaster.setInternalNotes(internalNotes);
    }

    /**
    * Returns the secondary rebate plan name of this rebate plan master.
    *
    * @return the secondary rebate plan name of this rebate plan master
    */
    @Override
    public java.lang.String getSecondaryRebatePlanName() {
        return _rebatePlanMaster.getSecondaryRebatePlanName();
    }

    /**
    * Sets the secondary rebate plan name of this rebate plan master.
    *
    * @param secondaryRebatePlanName the secondary rebate plan name of this rebate plan master
    */
    @Override
    public void setSecondaryRebatePlanName(
        java.lang.String secondaryRebatePlanName) {
        _rebatePlanMaster.setSecondaryRebatePlanName(secondaryRebatePlanName);
    }

    /**
    * Returns the record lock status of this rebate plan master.
    *
    * @return the record lock status of this rebate plan master
    */
    @Override
    public boolean getRecordLockStatus() {
        return _rebatePlanMaster.getRecordLockStatus();
    }

    /**
    * Returns <code>true</code> if this rebate plan master is record lock status.
    *
    * @return <code>true</code> if this rebate plan master is record lock status; <code>false</code> otherwise
    */
    @Override
    public boolean isRecordLockStatus() {
        return _rebatePlanMaster.isRecordLockStatus();
    }

    /**
    * Sets whether this rebate plan master is record lock status.
    *
    * @param recordLockStatus the record lock status of this rebate plan master
    */
    @Override
    public void setRecordLockStatus(boolean recordLockStatus) {
        _rebatePlanMaster.setRecordLockStatus(recordLockStatus);
    }

    /**
    * Returns the rebate plan name of this rebate plan master.
    *
    * @return the rebate plan name of this rebate plan master
    */
    @Override
    public java.lang.String getRebatePlanName() {
        return _rebatePlanMaster.getRebatePlanName();
    }

    /**
    * Sets the rebate plan name of this rebate plan master.
    *
    * @param rebatePlanName the rebate plan name of this rebate plan master
    */
    @Override
    public void setRebatePlanName(java.lang.String rebatePlanName) {
        _rebatePlanMaster.setRebatePlanName(rebatePlanName);
    }

    /**
    * Returns the self growth reference of this rebate plan master.
    *
    * @return the self growth reference of this rebate plan master
    */
    @Override
    public java.lang.String getSelfGrowthReference() {
        return _rebatePlanMaster.getSelfGrowthReference();
    }

    /**
    * Sets the self growth reference of this rebate plan master.
    *
    * @param selfGrowthReference the self growth reference of this rebate plan master
    */
    @Override
    public void setSelfGrowthReference(java.lang.String selfGrowthReference) {
        _rebatePlanMaster.setSelfGrowthReference(selfGrowthReference);
    }

    /**
    * Returns the batch ID of this rebate plan master.
    *
    * @return the batch ID of this rebate plan master
    */
    @Override
    public java.lang.String getBatchId() {
        return _rebatePlanMaster.getBatchId();
    }

    /**
    * Sets the batch ID of this rebate plan master.
    *
    * @param batchId the batch ID of this rebate plan master
    */
    @Override
    public void setBatchId(java.lang.String batchId) {
        _rebatePlanMaster.setBatchId(batchId);
    }

    /**
    * Returns the formula type of this rebate plan master.
    *
    * @return the formula type of this rebate plan master
    */
    @Override
    public int getFormulaType() {
        return _rebatePlanMaster.getFormulaType();
    }

    /**
    * Sets the formula type of this rebate plan master.
    *
    * @param formulaType the formula type of this rebate plan master
    */
    @Override
    public void setFormulaType(int formulaType) {
        _rebatePlanMaster.setFormulaType(formulaType);
    }

    /**
    * Returns the self growth to of this rebate plan master.
    *
    * @return the self growth to of this rebate plan master
    */
    @Override
    public java.util.Date getSelfGrowthTo() {
        return _rebatePlanMaster.getSelfGrowthTo();
    }

    /**
    * Sets the self growth to of this rebate plan master.
    *
    * @param selfGrowthTo the self growth to of this rebate plan master
    */
    @Override
    public void setSelfGrowthTo(java.util.Date selfGrowthTo) {
        _rebatePlanMaster.setSelfGrowthTo(selfGrowthTo);
    }

    /**
    * Returns the rebate plan no of this rebate plan master.
    *
    * @return the rebate plan no of this rebate plan master
    */
    @Override
    public java.lang.String getRebatePlanNo() {
        return _rebatePlanMaster.getRebatePlanNo();
    }

    /**
    * Sets the rebate plan no of this rebate plan master.
    *
    * @param rebatePlanNo the rebate plan no of this rebate plan master
    */
    @Override
    public void setRebatePlanNo(java.lang.String rebatePlanNo) {
        _rebatePlanMaster.setRebatePlanNo(rebatePlanNo);
    }

    @Override
    public boolean isNew() {
        return _rebatePlanMaster.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _rebatePlanMaster.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _rebatePlanMaster.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _rebatePlanMaster.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _rebatePlanMaster.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _rebatePlanMaster.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _rebatePlanMaster.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _rebatePlanMaster.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _rebatePlanMaster.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _rebatePlanMaster.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _rebatePlanMaster.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new RebatePlanMasterWrapper((RebatePlanMaster) _rebatePlanMaster.clone());
    }

    @Override
    public int compareTo(RebatePlanMaster rebatePlanMaster) {
        return _rebatePlanMaster.compareTo(rebatePlanMaster);
    }

    @Override
    public int hashCode() {
        return _rebatePlanMaster.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<RebatePlanMaster> toCacheModel() {
        return _rebatePlanMaster.toCacheModel();
    }

    @Override
    public RebatePlanMaster toEscapedModel() {
        return new RebatePlanMasterWrapper(_rebatePlanMaster.toEscapedModel());
    }

    @Override
    public RebatePlanMaster toUnescapedModel() {
        return new RebatePlanMasterWrapper(_rebatePlanMaster.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _rebatePlanMaster.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _rebatePlanMaster.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _rebatePlanMaster.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof RebatePlanMasterWrapper)) {
            return false;
        }

        RebatePlanMasterWrapper rebatePlanMasterWrapper = (RebatePlanMasterWrapper) obj;

        if (Validator.equals(_rebatePlanMaster,
                    rebatePlanMasterWrapper._rebatePlanMaster)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public RebatePlanMaster getWrappedRebatePlanMaster() {
        return _rebatePlanMaster;
    }

    @Override
    public RebatePlanMaster getWrappedModel() {
        return _rebatePlanMaster;
    }

    @Override
    public void resetOriginalValues() {
        _rebatePlanMaster.resetOriginalValues();
    }
}
