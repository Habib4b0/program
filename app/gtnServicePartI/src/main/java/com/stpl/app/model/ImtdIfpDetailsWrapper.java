package com.stpl.app.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link ImtdIfpDetails}.
 * </p>
 *
 * @author
 * @see ImtdIfpDetails
 * @generated
 */
public class ImtdIfpDetailsWrapper implements ImtdIfpDetails,
    ModelWrapper<ImtdIfpDetails> {
    private ImtdIfpDetails _imtdIfpDetails;

    public ImtdIfpDetailsWrapper(ImtdIfpDetails imtdIfpDetails) {
        _imtdIfpDetails = imtdIfpDetails;
    }

    @Override
    public Class<?> getModelClass() {
        return ImtdIfpDetails.class;
    }

    @Override
    public String getModelClassName() {
        return ImtdIfpDetails.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("itemStatus", getItemStatus());
        attributes.put("ifpDetailsEndDate", getIfpDetailsEndDate());
        attributes.put("itemMasterSid", getItemMasterSid());
        attributes.put("imtdCreateddate", getImtdCreateddate());
        attributes.put("itemPackageSize", getItemPackageSize());
        attributes.put("ifpDetailsCreatedDate", getIfpDetailsCreatedDate());
        attributes.put("totalDollarCommitment", getTotalDollarCommitment());
        attributes.put("ifpDetailsCreatedBy", getIfpDetailsCreatedBy());
        attributes.put("itemId", getItemId());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("ifpDetailsModifiedBy", getIfpDetailsModifiedBy());
        attributes.put("ifpDetailsModifiedDate", getIfpDetailsModifiedDate());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("usersSid", getUsersSid());
        attributes.put("itemDesc", getItemDesc());
        attributes.put("itemStartDate", getItemStartDate());
        attributes.put("itemStrength", getItemStrength());
        attributes.put("contractMasterSid", getContractMasterSid());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("commitmentPeriod", getCommitmentPeriod());
        attributes.put("itemNo", getItemNo());
        attributes.put("ifpDetailsSid", getIfpDetailsSid());
        attributes.put("ifpModelSid", getIfpModelSid());
        attributes.put("itemTherapeuticClass", getItemTherapeuticClass());
        attributes.put("ifpDetailsStartDate", getIfpDetailsStartDate());
        attributes.put("itemForm", getItemForm());
        attributes.put("totalVolumeCommitment", getTotalVolumeCommitment());
        attributes.put("itemEndDate", getItemEndDate());
        attributes.put("checkBox", getCheckBox());
        attributes.put("ifpDetailsAttachedStatus", getIfpDetailsAttachedStatus());
        attributes.put("totalMarketshareCommitment",
            getTotalMarketshareCommitment());
        attributes.put("ifpDetailsAttachedDate", getIfpDetailsAttachedDate());
        attributes.put("imtdIfpDetailsSid", getImtdIfpDetailsSid());
        attributes.put("sessionId", getSessionId());
        attributes.put("itemName", getItemName());
        attributes.put("itemPrimaryUom", getItemPrimaryUom());
        attributes.put("operation", getOperation());
        attributes.put("itemBrand", getItemBrand());
        attributes.put("cfpModelSid", getCfpModelSid());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Integer itemStatus = (Integer) attributes.get("itemStatus");

        if (itemStatus != null) {
            setItemStatus(itemStatus);
        }

        Date ifpDetailsEndDate = (Date) attributes.get("ifpDetailsEndDate");

        if (ifpDetailsEndDate != null) {
            setIfpDetailsEndDate(ifpDetailsEndDate);
        }

        Integer itemMasterSid = (Integer) attributes.get("itemMasterSid");

        if (itemMasterSid != null) {
            setItemMasterSid(itemMasterSid);
        }

        Date imtdCreateddate = (Date) attributes.get("imtdCreateddate");

        if (imtdCreateddate != null) {
            setImtdCreateddate(imtdCreateddate);
        }

        String itemPackageSize = (String) attributes.get("itemPackageSize");

        if (itemPackageSize != null) {
            setItemPackageSize(itemPackageSize);
        }

        Date ifpDetailsCreatedDate = (Date) attributes.get(
                "ifpDetailsCreatedDate");

        if (ifpDetailsCreatedDate != null) {
            setIfpDetailsCreatedDate(ifpDetailsCreatedDate);
        }

        String totalDollarCommitment = (String) attributes.get(
                "totalDollarCommitment");

        if (totalDollarCommitment != null) {
            setTotalDollarCommitment(totalDollarCommitment);
        }

        String ifpDetailsCreatedBy = (String) attributes.get(
                "ifpDetailsCreatedBy");

        if (ifpDetailsCreatedBy != null) {
            setIfpDetailsCreatedBy(ifpDetailsCreatedBy);
        }

        String itemId = (String) attributes.get("itemId");

        if (itemId != null) {
            setItemId(itemId);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        String ifpDetailsModifiedBy = (String) attributes.get(
                "ifpDetailsModifiedBy");

        if (ifpDetailsModifiedBy != null) {
            setIfpDetailsModifiedBy(ifpDetailsModifiedBy);
        }

        Date ifpDetailsModifiedDate = (Date) attributes.get(
                "ifpDetailsModifiedDate");

        if (ifpDetailsModifiedDate != null) {
            setIfpDetailsModifiedDate(ifpDetailsModifiedDate);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        Integer createdBy = (Integer) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        Integer usersSid = (Integer) attributes.get("usersSid");

        if (usersSid != null) {
            setUsersSid(usersSid);
        }

        String itemDesc = (String) attributes.get("itemDesc");

        if (itemDesc != null) {
            setItemDesc(itemDesc);
        }

        Date itemStartDate = (Date) attributes.get("itemStartDate");

        if (itemStartDate != null) {
            setItemStartDate(itemStartDate);
        }

        String itemStrength = (String) attributes.get("itemStrength");

        if (itemStrength != null) {
            setItemStrength(itemStrength);
        }

        Integer contractMasterSid = (Integer) attributes.get(
                "contractMasterSid");

        if (contractMasterSid != null) {
            setContractMasterSid(contractMasterSid);
        }

        Integer modifiedBy = (Integer) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        String commitmentPeriod = (String) attributes.get("commitmentPeriod");

        if (commitmentPeriod != null) {
            setCommitmentPeriod(commitmentPeriod);
        }

        String itemNo = (String) attributes.get("itemNo");

        if (itemNo != null) {
            setItemNo(itemNo);
        }

        Integer ifpDetailsSid = (Integer) attributes.get("ifpDetailsSid");

        if (ifpDetailsSid != null) {
            setIfpDetailsSid(ifpDetailsSid);
        }

        Integer ifpModelSid = (Integer) attributes.get("ifpModelSid");

        if (ifpModelSid != null) {
            setIfpModelSid(ifpModelSid);
        }

        String itemTherapeuticClass = (String) attributes.get(
                "itemTherapeuticClass");

        if (itemTherapeuticClass != null) {
            setItemTherapeuticClass(itemTherapeuticClass);
        }

        Date ifpDetailsStartDate = (Date) attributes.get("ifpDetailsStartDate");

        if (ifpDetailsStartDate != null) {
            setIfpDetailsStartDate(ifpDetailsStartDate);
        }

        String itemForm = (String) attributes.get("itemForm");

        if (itemForm != null) {
            setItemForm(itemForm);
        }

        String totalVolumeCommitment = (String) attributes.get(
                "totalVolumeCommitment");

        if (totalVolumeCommitment != null) {
            setTotalVolumeCommitment(totalVolumeCommitment);
        }

        Date itemEndDate = (Date) attributes.get("itemEndDate");

        if (itemEndDate != null) {
            setItemEndDate(itemEndDate);
        }

        Boolean checkBox = (Boolean) attributes.get("checkBox");

        if (checkBox != null) {
            setCheckBox(checkBox);
        }

        Integer ifpDetailsAttachedStatus = (Integer) attributes.get(
                "ifpDetailsAttachedStatus");

        if (ifpDetailsAttachedStatus != null) {
            setIfpDetailsAttachedStatus(ifpDetailsAttachedStatus);
        }

        String totalMarketshareCommitment = (String) attributes.get(
                "totalMarketshareCommitment");

        if (totalMarketshareCommitment != null) {
            setTotalMarketshareCommitment(totalMarketshareCommitment);
        }

        Date ifpDetailsAttachedDate = (Date) attributes.get(
                "ifpDetailsAttachedDate");

        if (ifpDetailsAttachedDate != null) {
            setIfpDetailsAttachedDate(ifpDetailsAttachedDate);
        }

        Integer imtdIfpDetailsSid = (Integer) attributes.get(
                "imtdIfpDetailsSid");

        if (imtdIfpDetailsSid != null) {
            setImtdIfpDetailsSid(imtdIfpDetailsSid);
        }

        String sessionId = (String) attributes.get("sessionId");

        if (sessionId != null) {
            setSessionId(sessionId);
        }

        String itemName = (String) attributes.get("itemName");

        if (itemName != null) {
            setItemName(itemName);
        }

        String itemPrimaryUom = (String) attributes.get("itemPrimaryUom");

        if (itemPrimaryUom != null) {
            setItemPrimaryUom(itemPrimaryUom);
        }

        String operation = (String) attributes.get("operation");

        if (operation != null) {
            setOperation(operation);
        }

        String itemBrand = (String) attributes.get("itemBrand");

        if (itemBrand != null) {
            setItemBrand(itemBrand);
        }

        Integer cfpModelSid = (Integer) attributes.get("cfpModelSid");

        if (cfpModelSid != null) {
            setCfpModelSid(cfpModelSid);
        }
    }

    /**
    * Returns the primary key of this imtd ifp details.
    *
    * @return the primary key of this imtd ifp details
    */
    @Override
    public int getPrimaryKey() {
        return _imtdIfpDetails.getPrimaryKey();
    }

    /**
    * Sets the primary key of this imtd ifp details.
    *
    * @param primaryKey the primary key of this imtd ifp details
    */
    @Override
    public void setPrimaryKey(int primaryKey) {
        _imtdIfpDetails.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the item status of this imtd ifp details.
    *
    * @return the item status of this imtd ifp details
    */
    @Override
    public int getItemStatus() {
        return _imtdIfpDetails.getItemStatus();
    }

    /**
    * Sets the item status of this imtd ifp details.
    *
    * @param itemStatus the item status of this imtd ifp details
    */
    @Override
    public void setItemStatus(int itemStatus) {
        _imtdIfpDetails.setItemStatus(itemStatus);
    }

    /**
    * Returns the ifp details end date of this imtd ifp details.
    *
    * @return the ifp details end date of this imtd ifp details
    */
    @Override
    public java.util.Date getIfpDetailsEndDate() {
        return _imtdIfpDetails.getIfpDetailsEndDate();
    }

    /**
    * Sets the ifp details end date of this imtd ifp details.
    *
    * @param ifpDetailsEndDate the ifp details end date of this imtd ifp details
    */
    @Override
    public void setIfpDetailsEndDate(java.util.Date ifpDetailsEndDate) {
        _imtdIfpDetails.setIfpDetailsEndDate(ifpDetailsEndDate);
    }

    /**
    * Returns the item master sid of this imtd ifp details.
    *
    * @return the item master sid of this imtd ifp details
    */
    @Override
    public int getItemMasterSid() {
        return _imtdIfpDetails.getItemMasterSid();
    }

    /**
    * Sets the item master sid of this imtd ifp details.
    *
    * @param itemMasterSid the item master sid of this imtd ifp details
    */
    @Override
    public void setItemMasterSid(int itemMasterSid) {
        _imtdIfpDetails.setItemMasterSid(itemMasterSid);
    }

    /**
    * Returns the imtd createddate of this imtd ifp details.
    *
    * @return the imtd createddate of this imtd ifp details
    */
    @Override
    public java.util.Date getImtdCreateddate() {
        return _imtdIfpDetails.getImtdCreateddate();
    }

    /**
    * Sets the imtd createddate of this imtd ifp details.
    *
    * @param imtdCreateddate the imtd createddate of this imtd ifp details
    */
    @Override
    public void setImtdCreateddate(java.util.Date imtdCreateddate) {
        _imtdIfpDetails.setImtdCreateddate(imtdCreateddate);
    }

    /**
    * Returns the item package size of this imtd ifp details.
    *
    * @return the item package size of this imtd ifp details
    */
    @Override
    public java.lang.String getItemPackageSize() {
        return _imtdIfpDetails.getItemPackageSize();
    }

    /**
    * Sets the item package size of this imtd ifp details.
    *
    * @param itemPackageSize the item package size of this imtd ifp details
    */
    @Override
    public void setItemPackageSize(java.lang.String itemPackageSize) {
        _imtdIfpDetails.setItemPackageSize(itemPackageSize);
    }

    /**
    * Returns the ifp details created date of this imtd ifp details.
    *
    * @return the ifp details created date of this imtd ifp details
    */
    @Override
    public java.util.Date getIfpDetailsCreatedDate() {
        return _imtdIfpDetails.getIfpDetailsCreatedDate();
    }

    /**
    * Sets the ifp details created date of this imtd ifp details.
    *
    * @param ifpDetailsCreatedDate the ifp details created date of this imtd ifp details
    */
    @Override
    public void setIfpDetailsCreatedDate(java.util.Date ifpDetailsCreatedDate) {
        _imtdIfpDetails.setIfpDetailsCreatedDate(ifpDetailsCreatedDate);
    }

    /**
    * Returns the total dollar commitment of this imtd ifp details.
    *
    * @return the total dollar commitment of this imtd ifp details
    */
    @Override
    public java.lang.String getTotalDollarCommitment() {
        return _imtdIfpDetails.getTotalDollarCommitment();
    }

    /**
    * Sets the total dollar commitment of this imtd ifp details.
    *
    * @param totalDollarCommitment the total dollar commitment of this imtd ifp details
    */
    @Override
    public void setTotalDollarCommitment(java.lang.String totalDollarCommitment) {
        _imtdIfpDetails.setTotalDollarCommitment(totalDollarCommitment);
    }

    /**
    * Returns the ifp details created by of this imtd ifp details.
    *
    * @return the ifp details created by of this imtd ifp details
    */
    @Override
    public java.lang.String getIfpDetailsCreatedBy() {
        return _imtdIfpDetails.getIfpDetailsCreatedBy();
    }

    /**
    * Sets the ifp details created by of this imtd ifp details.
    *
    * @param ifpDetailsCreatedBy the ifp details created by of this imtd ifp details
    */
    @Override
    public void setIfpDetailsCreatedBy(java.lang.String ifpDetailsCreatedBy) {
        _imtdIfpDetails.setIfpDetailsCreatedBy(ifpDetailsCreatedBy);
    }

    /**
    * Returns the item ID of this imtd ifp details.
    *
    * @return the item ID of this imtd ifp details
    */
    @Override
    public java.lang.String getItemId() {
        return _imtdIfpDetails.getItemId();
    }

    /**
    * Sets the item ID of this imtd ifp details.
    *
    * @param itemId the item ID of this imtd ifp details
    */
    @Override
    public void setItemId(java.lang.String itemId) {
        _imtdIfpDetails.setItemId(itemId);
    }

    /**
    * Returns the modified date of this imtd ifp details.
    *
    * @return the modified date of this imtd ifp details
    */
    @Override
    public java.util.Date getModifiedDate() {
        return _imtdIfpDetails.getModifiedDate();
    }

    /**
    * Sets the modified date of this imtd ifp details.
    *
    * @param modifiedDate the modified date of this imtd ifp details
    */
    @Override
    public void setModifiedDate(java.util.Date modifiedDate) {
        _imtdIfpDetails.setModifiedDate(modifiedDate);
    }

    /**
    * Returns the ifp details modified by of this imtd ifp details.
    *
    * @return the ifp details modified by of this imtd ifp details
    */
    @Override
    public java.lang.String getIfpDetailsModifiedBy() {
        return _imtdIfpDetails.getIfpDetailsModifiedBy();
    }

    /**
    * Sets the ifp details modified by of this imtd ifp details.
    *
    * @param ifpDetailsModifiedBy the ifp details modified by of this imtd ifp details
    */
    @Override
    public void setIfpDetailsModifiedBy(java.lang.String ifpDetailsModifiedBy) {
        _imtdIfpDetails.setIfpDetailsModifiedBy(ifpDetailsModifiedBy);
    }

    /**
    * Returns the ifp details modified date of this imtd ifp details.
    *
    * @return the ifp details modified date of this imtd ifp details
    */
    @Override
    public java.util.Date getIfpDetailsModifiedDate() {
        return _imtdIfpDetails.getIfpDetailsModifiedDate();
    }

    /**
    * Sets the ifp details modified date of this imtd ifp details.
    *
    * @param ifpDetailsModifiedDate the ifp details modified date of this imtd ifp details
    */
    @Override
    public void setIfpDetailsModifiedDate(java.util.Date ifpDetailsModifiedDate) {
        _imtdIfpDetails.setIfpDetailsModifiedDate(ifpDetailsModifiedDate);
    }

    /**
    * Returns the created date of this imtd ifp details.
    *
    * @return the created date of this imtd ifp details
    */
    @Override
    public java.util.Date getCreatedDate() {
        return _imtdIfpDetails.getCreatedDate();
    }

    /**
    * Sets the created date of this imtd ifp details.
    *
    * @param createdDate the created date of this imtd ifp details
    */
    @Override
    public void setCreatedDate(java.util.Date createdDate) {
        _imtdIfpDetails.setCreatedDate(createdDate);
    }

    /**
    * Returns the created by of this imtd ifp details.
    *
    * @return the created by of this imtd ifp details
    */
    @Override
    public int getCreatedBy() {
        return _imtdIfpDetails.getCreatedBy();
    }

    /**
    * Sets the created by of this imtd ifp details.
    *
    * @param createdBy the created by of this imtd ifp details
    */
    @Override
    public void setCreatedBy(int createdBy) {
        _imtdIfpDetails.setCreatedBy(createdBy);
    }

    /**
    * Returns the users sid of this imtd ifp details.
    *
    * @return the users sid of this imtd ifp details
    */
    @Override
    public int getUsersSid() {
        return _imtdIfpDetails.getUsersSid();
    }

    /**
    * Sets the users sid of this imtd ifp details.
    *
    * @param usersSid the users sid of this imtd ifp details
    */
    @Override
    public void setUsersSid(int usersSid) {
        _imtdIfpDetails.setUsersSid(usersSid);
    }

    /**
    * Returns the item desc of this imtd ifp details.
    *
    * @return the item desc of this imtd ifp details
    */
    @Override
    public java.lang.String getItemDesc() {
        return _imtdIfpDetails.getItemDesc();
    }

    /**
    * Sets the item desc of this imtd ifp details.
    *
    * @param itemDesc the item desc of this imtd ifp details
    */
    @Override
    public void setItemDesc(java.lang.String itemDesc) {
        _imtdIfpDetails.setItemDesc(itemDesc);
    }

    /**
    * Returns the item start date of this imtd ifp details.
    *
    * @return the item start date of this imtd ifp details
    */
    @Override
    public java.util.Date getItemStartDate() {
        return _imtdIfpDetails.getItemStartDate();
    }

    /**
    * Sets the item start date of this imtd ifp details.
    *
    * @param itemStartDate the item start date of this imtd ifp details
    */
    @Override
    public void setItemStartDate(java.util.Date itemStartDate) {
        _imtdIfpDetails.setItemStartDate(itemStartDate);
    }

    /**
    * Returns the item strength of this imtd ifp details.
    *
    * @return the item strength of this imtd ifp details
    */
    @Override
    public java.lang.String getItemStrength() {
        return _imtdIfpDetails.getItemStrength();
    }

    /**
    * Sets the item strength of this imtd ifp details.
    *
    * @param itemStrength the item strength of this imtd ifp details
    */
    @Override
    public void setItemStrength(java.lang.String itemStrength) {
        _imtdIfpDetails.setItemStrength(itemStrength);
    }

    /**
    * Returns the contract master sid of this imtd ifp details.
    *
    * @return the contract master sid of this imtd ifp details
    */
    @Override
    public int getContractMasterSid() {
        return _imtdIfpDetails.getContractMasterSid();
    }

    /**
    * Sets the contract master sid of this imtd ifp details.
    *
    * @param contractMasterSid the contract master sid of this imtd ifp details
    */
    @Override
    public void setContractMasterSid(int contractMasterSid) {
        _imtdIfpDetails.setContractMasterSid(contractMasterSid);
    }

    /**
    * Returns the modified by of this imtd ifp details.
    *
    * @return the modified by of this imtd ifp details
    */
    @Override
    public int getModifiedBy() {
        return _imtdIfpDetails.getModifiedBy();
    }

    /**
    * Sets the modified by of this imtd ifp details.
    *
    * @param modifiedBy the modified by of this imtd ifp details
    */
    @Override
    public void setModifiedBy(int modifiedBy) {
        _imtdIfpDetails.setModifiedBy(modifiedBy);
    }

    /**
    * Returns the commitment period of this imtd ifp details.
    *
    * @return the commitment period of this imtd ifp details
    */
    @Override
    public java.lang.String getCommitmentPeriod() {
        return _imtdIfpDetails.getCommitmentPeriod();
    }

    /**
    * Sets the commitment period of this imtd ifp details.
    *
    * @param commitmentPeriod the commitment period of this imtd ifp details
    */
    @Override
    public void setCommitmentPeriod(java.lang.String commitmentPeriod) {
        _imtdIfpDetails.setCommitmentPeriod(commitmentPeriod);
    }

    /**
    * Returns the item no of this imtd ifp details.
    *
    * @return the item no of this imtd ifp details
    */
    @Override
    public java.lang.String getItemNo() {
        return _imtdIfpDetails.getItemNo();
    }

    /**
    * Sets the item no of this imtd ifp details.
    *
    * @param itemNo the item no of this imtd ifp details
    */
    @Override
    public void setItemNo(java.lang.String itemNo) {
        _imtdIfpDetails.setItemNo(itemNo);
    }

    /**
    * Returns the ifp details sid of this imtd ifp details.
    *
    * @return the ifp details sid of this imtd ifp details
    */
    @Override
    public int getIfpDetailsSid() {
        return _imtdIfpDetails.getIfpDetailsSid();
    }

    /**
    * Sets the ifp details sid of this imtd ifp details.
    *
    * @param ifpDetailsSid the ifp details sid of this imtd ifp details
    */
    @Override
    public void setIfpDetailsSid(int ifpDetailsSid) {
        _imtdIfpDetails.setIfpDetailsSid(ifpDetailsSid);
    }

    /**
    * Returns the ifp model sid of this imtd ifp details.
    *
    * @return the ifp model sid of this imtd ifp details
    */
    @Override
    public int getIfpModelSid() {
        return _imtdIfpDetails.getIfpModelSid();
    }

    /**
    * Sets the ifp model sid of this imtd ifp details.
    *
    * @param ifpModelSid the ifp model sid of this imtd ifp details
    */
    @Override
    public void setIfpModelSid(int ifpModelSid) {
        _imtdIfpDetails.setIfpModelSid(ifpModelSid);
    }

    /**
    * Returns the item therapeutic class of this imtd ifp details.
    *
    * @return the item therapeutic class of this imtd ifp details
    */
    @Override
    public java.lang.String getItemTherapeuticClass() {
        return _imtdIfpDetails.getItemTherapeuticClass();
    }

    /**
    * Sets the item therapeutic class of this imtd ifp details.
    *
    * @param itemTherapeuticClass the item therapeutic class of this imtd ifp details
    */
    @Override
    public void setItemTherapeuticClass(java.lang.String itemTherapeuticClass) {
        _imtdIfpDetails.setItemTherapeuticClass(itemTherapeuticClass);
    }

    /**
    * Returns the ifp details start date of this imtd ifp details.
    *
    * @return the ifp details start date of this imtd ifp details
    */
    @Override
    public java.util.Date getIfpDetailsStartDate() {
        return _imtdIfpDetails.getIfpDetailsStartDate();
    }

    /**
    * Sets the ifp details start date of this imtd ifp details.
    *
    * @param ifpDetailsStartDate the ifp details start date of this imtd ifp details
    */
    @Override
    public void setIfpDetailsStartDate(java.util.Date ifpDetailsStartDate) {
        _imtdIfpDetails.setIfpDetailsStartDate(ifpDetailsStartDate);
    }

    /**
    * Returns the item form of this imtd ifp details.
    *
    * @return the item form of this imtd ifp details
    */
    @Override
    public java.lang.String getItemForm() {
        return _imtdIfpDetails.getItemForm();
    }

    /**
    * Sets the item form of this imtd ifp details.
    *
    * @param itemForm the item form of this imtd ifp details
    */
    @Override
    public void setItemForm(java.lang.String itemForm) {
        _imtdIfpDetails.setItemForm(itemForm);
    }

    /**
    * Returns the total volume commitment of this imtd ifp details.
    *
    * @return the total volume commitment of this imtd ifp details
    */
    @Override
    public java.lang.String getTotalVolumeCommitment() {
        return _imtdIfpDetails.getTotalVolumeCommitment();
    }

    /**
    * Sets the total volume commitment of this imtd ifp details.
    *
    * @param totalVolumeCommitment the total volume commitment of this imtd ifp details
    */
    @Override
    public void setTotalVolumeCommitment(java.lang.String totalVolumeCommitment) {
        _imtdIfpDetails.setTotalVolumeCommitment(totalVolumeCommitment);
    }

    /**
    * Returns the item end date of this imtd ifp details.
    *
    * @return the item end date of this imtd ifp details
    */
    @Override
    public java.util.Date getItemEndDate() {
        return _imtdIfpDetails.getItemEndDate();
    }

    /**
    * Sets the item end date of this imtd ifp details.
    *
    * @param itemEndDate the item end date of this imtd ifp details
    */
    @Override
    public void setItemEndDate(java.util.Date itemEndDate) {
        _imtdIfpDetails.setItemEndDate(itemEndDate);
    }

    /**
    * Returns the check box of this imtd ifp details.
    *
    * @return the check box of this imtd ifp details
    */
    @Override
    public boolean getCheckBox() {
        return _imtdIfpDetails.getCheckBox();
    }

    /**
    * Returns <code>true</code> if this imtd ifp details is check box.
    *
    * @return <code>true</code> if this imtd ifp details is check box; <code>false</code> otherwise
    */
    @Override
    public boolean isCheckBox() {
        return _imtdIfpDetails.isCheckBox();
    }

    /**
    * Sets whether this imtd ifp details is check box.
    *
    * @param checkBox the check box of this imtd ifp details
    */
    @Override
    public void setCheckBox(boolean checkBox) {
        _imtdIfpDetails.setCheckBox(checkBox);
    }

    /**
    * Returns the ifp details attached status of this imtd ifp details.
    *
    * @return the ifp details attached status of this imtd ifp details
    */
    @Override
    public int getIfpDetailsAttachedStatus() {
        return _imtdIfpDetails.getIfpDetailsAttachedStatus();
    }

    /**
    * Sets the ifp details attached status of this imtd ifp details.
    *
    * @param ifpDetailsAttachedStatus the ifp details attached status of this imtd ifp details
    */
    @Override
    public void setIfpDetailsAttachedStatus(int ifpDetailsAttachedStatus) {
        _imtdIfpDetails.setIfpDetailsAttachedStatus(ifpDetailsAttachedStatus);
    }

    /**
    * Returns the total marketshare commitment of this imtd ifp details.
    *
    * @return the total marketshare commitment of this imtd ifp details
    */
    @Override
    public java.lang.String getTotalMarketshareCommitment() {
        return _imtdIfpDetails.getTotalMarketshareCommitment();
    }

    /**
    * Sets the total marketshare commitment of this imtd ifp details.
    *
    * @param totalMarketshareCommitment the total marketshare commitment of this imtd ifp details
    */
    @Override
    public void setTotalMarketshareCommitment(
        java.lang.String totalMarketshareCommitment) {
        _imtdIfpDetails.setTotalMarketshareCommitment(totalMarketshareCommitment);
    }

    /**
    * Returns the ifp details attached date of this imtd ifp details.
    *
    * @return the ifp details attached date of this imtd ifp details
    */
    @Override
    public java.util.Date getIfpDetailsAttachedDate() {
        return _imtdIfpDetails.getIfpDetailsAttachedDate();
    }

    /**
    * Sets the ifp details attached date of this imtd ifp details.
    *
    * @param ifpDetailsAttachedDate the ifp details attached date of this imtd ifp details
    */
    @Override
    public void setIfpDetailsAttachedDate(java.util.Date ifpDetailsAttachedDate) {
        _imtdIfpDetails.setIfpDetailsAttachedDate(ifpDetailsAttachedDate);
    }

    /**
    * Returns the imtd ifp details sid of this imtd ifp details.
    *
    * @return the imtd ifp details sid of this imtd ifp details
    */
    @Override
    public int getImtdIfpDetailsSid() {
        return _imtdIfpDetails.getImtdIfpDetailsSid();
    }

    /**
    * Sets the imtd ifp details sid of this imtd ifp details.
    *
    * @param imtdIfpDetailsSid the imtd ifp details sid of this imtd ifp details
    */
    @Override
    public void setImtdIfpDetailsSid(int imtdIfpDetailsSid) {
        _imtdIfpDetails.setImtdIfpDetailsSid(imtdIfpDetailsSid);
    }

    /**
    * Returns the session ID of this imtd ifp details.
    *
    * @return the session ID of this imtd ifp details
    */
    @Override
    public java.lang.String getSessionId() {
        return _imtdIfpDetails.getSessionId();
    }

    /**
    * Sets the session ID of this imtd ifp details.
    *
    * @param sessionId the session ID of this imtd ifp details
    */
    @Override
    public void setSessionId(java.lang.String sessionId) {
        _imtdIfpDetails.setSessionId(sessionId);
    }

    /**
    * Returns the item name of this imtd ifp details.
    *
    * @return the item name of this imtd ifp details
    */
    @Override
    public java.lang.String getItemName() {
        return _imtdIfpDetails.getItemName();
    }

    /**
    * Sets the item name of this imtd ifp details.
    *
    * @param itemName the item name of this imtd ifp details
    */
    @Override
    public void setItemName(java.lang.String itemName) {
        _imtdIfpDetails.setItemName(itemName);
    }

    /**
    * Returns the item primary uom of this imtd ifp details.
    *
    * @return the item primary uom of this imtd ifp details
    */
    @Override
    public java.lang.String getItemPrimaryUom() {
        return _imtdIfpDetails.getItemPrimaryUom();
    }

    /**
    * Sets the item primary uom of this imtd ifp details.
    *
    * @param itemPrimaryUom the item primary uom of this imtd ifp details
    */
    @Override
    public void setItemPrimaryUom(java.lang.String itemPrimaryUom) {
        _imtdIfpDetails.setItemPrimaryUom(itemPrimaryUom);
    }

    /**
    * Returns the operation of this imtd ifp details.
    *
    * @return the operation of this imtd ifp details
    */
    @Override
    public java.lang.String getOperation() {
        return _imtdIfpDetails.getOperation();
    }

    /**
    * Sets the operation of this imtd ifp details.
    *
    * @param operation the operation of this imtd ifp details
    */
    @Override
    public void setOperation(java.lang.String operation) {
        _imtdIfpDetails.setOperation(operation);
    }

    /**
    * Returns the item brand of this imtd ifp details.
    *
    * @return the item brand of this imtd ifp details
    */
    @Override
    public java.lang.String getItemBrand() {
        return _imtdIfpDetails.getItemBrand();
    }

    /**
    * Sets the item brand of this imtd ifp details.
    *
    * @param itemBrand the item brand of this imtd ifp details
    */
    @Override
    public void setItemBrand(java.lang.String itemBrand) {
        _imtdIfpDetails.setItemBrand(itemBrand);
    }

    /**
    * Returns the cfp model sid of this imtd ifp details.
    *
    * @return the cfp model sid of this imtd ifp details
    */
    @Override
    public int getCfpModelSid() {
        return _imtdIfpDetails.getCfpModelSid();
    }

    /**
    * Sets the cfp model sid of this imtd ifp details.
    *
    * @param cfpModelSid the cfp model sid of this imtd ifp details
    */
    @Override
    public void setCfpModelSid(int cfpModelSid) {
        _imtdIfpDetails.setCfpModelSid(cfpModelSid);
    }

    @Override
    public boolean isNew() {
        return _imtdIfpDetails.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _imtdIfpDetails.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _imtdIfpDetails.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _imtdIfpDetails.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _imtdIfpDetails.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _imtdIfpDetails.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _imtdIfpDetails.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _imtdIfpDetails.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _imtdIfpDetails.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _imtdIfpDetails.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _imtdIfpDetails.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new ImtdIfpDetailsWrapper((ImtdIfpDetails) _imtdIfpDetails.clone());
    }

    @Override
    public int compareTo(ImtdIfpDetails imtdIfpDetails) {
        return _imtdIfpDetails.compareTo(imtdIfpDetails);
    }

    @Override
    public int hashCode() {
        return _imtdIfpDetails.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<ImtdIfpDetails> toCacheModel() {
        return _imtdIfpDetails.toCacheModel();
    }

    @Override
    public ImtdIfpDetails toEscapedModel() {
        return new ImtdIfpDetailsWrapper(_imtdIfpDetails.toEscapedModel());
    }

    @Override
    public ImtdIfpDetails toUnescapedModel() {
        return new ImtdIfpDetailsWrapper(_imtdIfpDetails.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _imtdIfpDetails.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _imtdIfpDetails.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _imtdIfpDetails.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof ImtdIfpDetailsWrapper)) {
            return false;
        }

        ImtdIfpDetailsWrapper imtdIfpDetailsWrapper = (ImtdIfpDetailsWrapper) obj;

        if (Validator.equals(_imtdIfpDetails,
                    imtdIfpDetailsWrapper._imtdIfpDetails)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public ImtdIfpDetails getWrappedImtdIfpDetails() {
        return _imtdIfpDetails;
    }

    @Override
    public ImtdIfpDetails getWrappedModel() {
        return _imtdIfpDetails;
    }

    @Override
    public void resetOriginalValues() {
        _imtdIfpDetails.resetOriginalValues();
    }
}
