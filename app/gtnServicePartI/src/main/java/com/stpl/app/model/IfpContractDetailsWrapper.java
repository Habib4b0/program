package com.stpl.app.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link IfpContractDetails}.
 * </p>
 *
 * @author
 * @see IfpContractDetails
 * @generated
 */
public class IfpContractDetailsWrapper implements IfpContractDetails,
    ModelWrapper<IfpContractDetails> {
    private IfpContractDetails _ifpContractDetails;

    public IfpContractDetailsWrapper(IfpContractDetails ifpContractDetails) {
        _ifpContractDetails = ifpContractDetails;
    }

    @Override
    public Class<?> getModelClass() {
        return IfpContractDetails.class;
    }

    @Override
    public String getModelClassName() {
        return IfpContractDetails.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("itemStatus", getItemStatus());
        attributes.put("itemMasterSid", getItemMasterSid());
        attributes.put("ifpContractAttachedDate", getIfpContractAttachedDate());
        attributes.put("itemEndDate", getItemEndDate());
        attributes.put("totalVolumeCommitment", getTotalVolumeCommitment());
        attributes.put("totalDollarCommitment", getTotalDollarCommitment());
        attributes.put("ifpContractAttachedStatus",
            getIfpContractAttachedStatus());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("totalMarketshareCommitment",
            getTotalMarketshareCommitment());
        attributes.put("recordLockStatus", getRecordLockStatus());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("source", getSource());
        attributes.put("itemStartDate", getItemStartDate());
        attributes.put("batchId", getBatchId());
        attributes.put("ifpContractDetailsSid", getIfpContractDetailsSid());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("inboundStatus", getInboundStatus());
        attributes.put("ifpContractSid", getIfpContractSid());
        attributes.put("commitmentPeriod", getCommitmentPeriod());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Integer itemStatus = (Integer) attributes.get("itemStatus");

        if (itemStatus != null) {
            setItemStatus(itemStatus);
        }

        Integer itemMasterSid = (Integer) attributes.get("itemMasterSid");

        if (itemMasterSid != null) {
            setItemMasterSid(itemMasterSid);
        }

        Date ifpContractAttachedDate = (Date) attributes.get(
                "ifpContractAttachedDate");

        if (ifpContractAttachedDate != null) {
            setIfpContractAttachedDate(ifpContractAttachedDate);
        }

        Date itemEndDate = (Date) attributes.get("itemEndDate");

        if (itemEndDate != null) {
            setItemEndDate(itemEndDate);
        }

        String totalVolumeCommitment = (String) attributes.get(
                "totalVolumeCommitment");

        if (totalVolumeCommitment != null) {
            setTotalVolumeCommitment(totalVolumeCommitment);
        }

        String totalDollarCommitment = (String) attributes.get(
                "totalDollarCommitment");

        if (totalDollarCommitment != null) {
            setTotalDollarCommitment(totalDollarCommitment);
        }

        Integer ifpContractAttachedStatus = (Integer) attributes.get(
                "ifpContractAttachedStatus");

        if (ifpContractAttachedStatus != null) {
            setIfpContractAttachedStatus(ifpContractAttachedStatus);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        String totalMarketshareCommitment = (String) attributes.get(
                "totalMarketshareCommitment");

        if (totalMarketshareCommitment != null) {
            setTotalMarketshareCommitment(totalMarketshareCommitment);
        }

        Boolean recordLockStatus = (Boolean) attributes.get("recordLockStatus");

        if (recordLockStatus != null) {
            setRecordLockStatus(recordLockStatus);
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

        Date itemStartDate = (Date) attributes.get("itemStartDate");

        if (itemStartDate != null) {
            setItemStartDate(itemStartDate);
        }

        String batchId = (String) attributes.get("batchId");

        if (batchId != null) {
            setBatchId(batchId);
        }

        Integer ifpContractDetailsSid = (Integer) attributes.get(
                "ifpContractDetailsSid");

        if (ifpContractDetailsSid != null) {
            setIfpContractDetailsSid(ifpContractDetailsSid);
        }

        Integer modifiedBy = (Integer) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        String inboundStatus = (String) attributes.get("inboundStatus");

        if (inboundStatus != null) {
            setInboundStatus(inboundStatus);
        }

        Integer ifpContractSid = (Integer) attributes.get("ifpContractSid");

        if (ifpContractSid != null) {
            setIfpContractSid(ifpContractSid);
        }

        String commitmentPeriod = (String) attributes.get("commitmentPeriod");

        if (commitmentPeriod != null) {
            setCommitmentPeriod(commitmentPeriod);
        }
    }

    /**
    * Returns the primary key of this ifp contract details.
    *
    * @return the primary key of this ifp contract details
    */
    @Override
    public int getPrimaryKey() {
        return _ifpContractDetails.getPrimaryKey();
    }

    /**
    * Sets the primary key of this ifp contract details.
    *
    * @param primaryKey the primary key of this ifp contract details
    */
    @Override
    public void setPrimaryKey(int primaryKey) {
        _ifpContractDetails.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the item status of this ifp contract details.
    *
    * @return the item status of this ifp contract details
    */
    @Override
    public int getItemStatus() {
        return _ifpContractDetails.getItemStatus();
    }

    /**
    * Sets the item status of this ifp contract details.
    *
    * @param itemStatus the item status of this ifp contract details
    */
    @Override
    public void setItemStatus(int itemStatus) {
        _ifpContractDetails.setItemStatus(itemStatus);
    }

    /**
    * Returns the item master sid of this ifp contract details.
    *
    * @return the item master sid of this ifp contract details
    */
    @Override
    public int getItemMasterSid() {
        return _ifpContractDetails.getItemMasterSid();
    }

    /**
    * Sets the item master sid of this ifp contract details.
    *
    * @param itemMasterSid the item master sid of this ifp contract details
    */
    @Override
    public void setItemMasterSid(int itemMasterSid) {
        _ifpContractDetails.setItemMasterSid(itemMasterSid);
    }

    /**
    * Returns the ifp contract attached date of this ifp contract details.
    *
    * @return the ifp contract attached date of this ifp contract details
    */
    @Override
    public java.util.Date getIfpContractAttachedDate() {
        return _ifpContractDetails.getIfpContractAttachedDate();
    }

    /**
    * Sets the ifp contract attached date of this ifp contract details.
    *
    * @param ifpContractAttachedDate the ifp contract attached date of this ifp contract details
    */
    @Override
    public void setIfpContractAttachedDate(
        java.util.Date ifpContractAttachedDate) {
        _ifpContractDetails.setIfpContractAttachedDate(ifpContractAttachedDate);
    }

    /**
    * Returns the item end date of this ifp contract details.
    *
    * @return the item end date of this ifp contract details
    */
    @Override
    public java.util.Date getItemEndDate() {
        return _ifpContractDetails.getItemEndDate();
    }

    /**
    * Sets the item end date of this ifp contract details.
    *
    * @param itemEndDate the item end date of this ifp contract details
    */
    @Override
    public void setItemEndDate(java.util.Date itemEndDate) {
        _ifpContractDetails.setItemEndDate(itemEndDate);
    }

    /**
    * Returns the total volume commitment of this ifp contract details.
    *
    * @return the total volume commitment of this ifp contract details
    */
    @Override
    public java.lang.String getTotalVolumeCommitment() {
        return _ifpContractDetails.getTotalVolumeCommitment();
    }

    /**
    * Sets the total volume commitment of this ifp contract details.
    *
    * @param totalVolumeCommitment the total volume commitment of this ifp contract details
    */
    @Override
    public void setTotalVolumeCommitment(java.lang.String totalVolumeCommitment) {
        _ifpContractDetails.setTotalVolumeCommitment(totalVolumeCommitment);
    }

    /**
    * Returns the total dollar commitment of this ifp contract details.
    *
    * @return the total dollar commitment of this ifp contract details
    */
    @Override
    public java.lang.String getTotalDollarCommitment() {
        return _ifpContractDetails.getTotalDollarCommitment();
    }

    /**
    * Sets the total dollar commitment of this ifp contract details.
    *
    * @param totalDollarCommitment the total dollar commitment of this ifp contract details
    */
    @Override
    public void setTotalDollarCommitment(java.lang.String totalDollarCommitment) {
        _ifpContractDetails.setTotalDollarCommitment(totalDollarCommitment);
    }

    /**
    * Returns the ifp contract attached status of this ifp contract details.
    *
    * @return the ifp contract attached status of this ifp contract details
    */
    @Override
    public int getIfpContractAttachedStatus() {
        return _ifpContractDetails.getIfpContractAttachedStatus();
    }

    /**
    * Sets the ifp contract attached status of this ifp contract details.
    *
    * @param ifpContractAttachedStatus the ifp contract attached status of this ifp contract details
    */
    @Override
    public void setIfpContractAttachedStatus(int ifpContractAttachedStatus) {
        _ifpContractDetails.setIfpContractAttachedStatus(ifpContractAttachedStatus);
    }

    /**
    * Returns the modified date of this ifp contract details.
    *
    * @return the modified date of this ifp contract details
    */
    @Override
    public java.util.Date getModifiedDate() {
        return _ifpContractDetails.getModifiedDate();
    }

    /**
    * Sets the modified date of this ifp contract details.
    *
    * @param modifiedDate the modified date of this ifp contract details
    */
    @Override
    public void setModifiedDate(java.util.Date modifiedDate) {
        _ifpContractDetails.setModifiedDate(modifiedDate);
    }

    /**
    * Returns the total marketshare commitment of this ifp contract details.
    *
    * @return the total marketshare commitment of this ifp contract details
    */
    @Override
    public java.lang.String getTotalMarketshareCommitment() {
        return _ifpContractDetails.getTotalMarketshareCommitment();
    }

    /**
    * Sets the total marketshare commitment of this ifp contract details.
    *
    * @param totalMarketshareCommitment the total marketshare commitment of this ifp contract details
    */
    @Override
    public void setTotalMarketshareCommitment(
        java.lang.String totalMarketshareCommitment) {
        _ifpContractDetails.setTotalMarketshareCommitment(totalMarketshareCommitment);
    }

    /**
    * Returns the record lock status of this ifp contract details.
    *
    * @return the record lock status of this ifp contract details
    */
    @Override
    public boolean getRecordLockStatus() {
        return _ifpContractDetails.getRecordLockStatus();
    }

    /**
    * Returns <code>true</code> if this ifp contract details is record lock status.
    *
    * @return <code>true</code> if this ifp contract details is record lock status; <code>false</code> otherwise
    */
    @Override
    public boolean isRecordLockStatus() {
        return _ifpContractDetails.isRecordLockStatus();
    }

    /**
    * Sets whether this ifp contract details is record lock status.
    *
    * @param recordLockStatus the record lock status of this ifp contract details
    */
    @Override
    public void setRecordLockStatus(boolean recordLockStatus) {
        _ifpContractDetails.setRecordLockStatus(recordLockStatus);
    }

    /**
    * Returns the created date of this ifp contract details.
    *
    * @return the created date of this ifp contract details
    */
    @Override
    public java.util.Date getCreatedDate() {
        return _ifpContractDetails.getCreatedDate();
    }

    /**
    * Sets the created date of this ifp contract details.
    *
    * @param createdDate the created date of this ifp contract details
    */
    @Override
    public void setCreatedDate(java.util.Date createdDate) {
        _ifpContractDetails.setCreatedDate(createdDate);
    }

    /**
    * Returns the created by of this ifp contract details.
    *
    * @return the created by of this ifp contract details
    */
    @Override
    public int getCreatedBy() {
        return _ifpContractDetails.getCreatedBy();
    }

    /**
    * Sets the created by of this ifp contract details.
    *
    * @param createdBy the created by of this ifp contract details
    */
    @Override
    public void setCreatedBy(int createdBy) {
        _ifpContractDetails.setCreatedBy(createdBy);
    }

    /**
    * Returns the source of this ifp contract details.
    *
    * @return the source of this ifp contract details
    */
    @Override
    public java.lang.String getSource() {
        return _ifpContractDetails.getSource();
    }

    /**
    * Sets the source of this ifp contract details.
    *
    * @param source the source of this ifp contract details
    */
    @Override
    public void setSource(java.lang.String source) {
        _ifpContractDetails.setSource(source);
    }

    /**
    * Returns the item start date of this ifp contract details.
    *
    * @return the item start date of this ifp contract details
    */
    @Override
    public java.util.Date getItemStartDate() {
        return _ifpContractDetails.getItemStartDate();
    }

    /**
    * Sets the item start date of this ifp contract details.
    *
    * @param itemStartDate the item start date of this ifp contract details
    */
    @Override
    public void setItemStartDate(java.util.Date itemStartDate) {
        _ifpContractDetails.setItemStartDate(itemStartDate);
    }

    /**
    * Returns the batch ID of this ifp contract details.
    *
    * @return the batch ID of this ifp contract details
    */
    @Override
    public java.lang.String getBatchId() {
        return _ifpContractDetails.getBatchId();
    }

    /**
    * Sets the batch ID of this ifp contract details.
    *
    * @param batchId the batch ID of this ifp contract details
    */
    @Override
    public void setBatchId(java.lang.String batchId) {
        _ifpContractDetails.setBatchId(batchId);
    }

    /**
    * Returns the ifp contract details sid of this ifp contract details.
    *
    * @return the ifp contract details sid of this ifp contract details
    */
    @Override
    public int getIfpContractDetailsSid() {
        return _ifpContractDetails.getIfpContractDetailsSid();
    }

    /**
    * Sets the ifp contract details sid of this ifp contract details.
    *
    * @param ifpContractDetailsSid the ifp contract details sid of this ifp contract details
    */
    @Override
    public void setIfpContractDetailsSid(int ifpContractDetailsSid) {
        _ifpContractDetails.setIfpContractDetailsSid(ifpContractDetailsSid);
    }

    /**
    * Returns the modified by of this ifp contract details.
    *
    * @return the modified by of this ifp contract details
    */
    @Override
    public int getModifiedBy() {
        return _ifpContractDetails.getModifiedBy();
    }

    /**
    * Sets the modified by of this ifp contract details.
    *
    * @param modifiedBy the modified by of this ifp contract details
    */
    @Override
    public void setModifiedBy(int modifiedBy) {
        _ifpContractDetails.setModifiedBy(modifiedBy);
    }

    /**
    * Returns the inbound status of this ifp contract details.
    *
    * @return the inbound status of this ifp contract details
    */
    @Override
    public java.lang.String getInboundStatus() {
        return _ifpContractDetails.getInboundStatus();
    }

    /**
    * Sets the inbound status of this ifp contract details.
    *
    * @param inboundStatus the inbound status of this ifp contract details
    */
    @Override
    public void setInboundStatus(java.lang.String inboundStatus) {
        _ifpContractDetails.setInboundStatus(inboundStatus);
    }

    /**
    * Returns the ifp contract sid of this ifp contract details.
    *
    * @return the ifp contract sid of this ifp contract details
    */
    @Override
    public int getIfpContractSid() {
        return _ifpContractDetails.getIfpContractSid();
    }

    /**
    * Sets the ifp contract sid of this ifp contract details.
    *
    * @param ifpContractSid the ifp contract sid of this ifp contract details
    */
    @Override
    public void setIfpContractSid(int ifpContractSid) {
        _ifpContractDetails.setIfpContractSid(ifpContractSid);
    }

    /**
    * Returns the commitment period of this ifp contract details.
    *
    * @return the commitment period of this ifp contract details
    */
    @Override
    public java.lang.String getCommitmentPeriod() {
        return _ifpContractDetails.getCommitmentPeriod();
    }

    /**
    * Sets the commitment period of this ifp contract details.
    *
    * @param commitmentPeriod the commitment period of this ifp contract details
    */
    @Override
    public void setCommitmentPeriod(java.lang.String commitmentPeriod) {
        _ifpContractDetails.setCommitmentPeriod(commitmentPeriod);
    }

    @Override
    public boolean isNew() {
        return _ifpContractDetails.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _ifpContractDetails.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _ifpContractDetails.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _ifpContractDetails.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _ifpContractDetails.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _ifpContractDetails.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _ifpContractDetails.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _ifpContractDetails.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _ifpContractDetails.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _ifpContractDetails.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _ifpContractDetails.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new IfpContractDetailsWrapper((IfpContractDetails) _ifpContractDetails.clone());
    }

    @Override
    public int compareTo(IfpContractDetails ifpContractDetails) {
        return _ifpContractDetails.compareTo(ifpContractDetails);
    }

    @Override
    public int hashCode() {
        return _ifpContractDetails.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<IfpContractDetails> toCacheModel() {
        return _ifpContractDetails.toCacheModel();
    }

    @Override
    public IfpContractDetails toEscapedModel() {
        return new IfpContractDetailsWrapper(_ifpContractDetails.toEscapedModel());
    }

    @Override
    public IfpContractDetails toUnescapedModel() {
        return new IfpContractDetailsWrapper(_ifpContractDetails.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _ifpContractDetails.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _ifpContractDetails.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _ifpContractDetails.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof IfpContractDetailsWrapper)) {
            return false;
        }

        IfpContractDetailsWrapper ifpContractDetailsWrapper = (IfpContractDetailsWrapper) obj;

        if (Validator.equals(_ifpContractDetails,
                    ifpContractDetailsWrapper._ifpContractDetails)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public IfpContractDetails getWrappedIfpContractDetails() {
        return _ifpContractDetails;
    }

    @Override
    public IfpContractDetails getWrappedModel() {
        return _ifpContractDetails;
    }

    @Override
    public void resetOriginalValues() {
        _ifpContractDetails.resetOriginalValues();
    }
}
