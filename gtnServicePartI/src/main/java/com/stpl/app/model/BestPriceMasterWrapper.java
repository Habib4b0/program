package com.stpl.app.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link BestPriceMaster}.
 * </p>
 *
 * @author
 * @see BestPriceMaster
 * @generated
 */
public class BestPriceMasterWrapper implements BestPriceMaster,
    ModelWrapper<BestPriceMaster> {
    private BestPriceMaster _bestPriceMaster;

    public BestPriceMasterWrapper(BestPriceMaster bestPriceMaster) {
        _bestPriceMaster = bestPriceMaster;
    }

    @Override
    public Class<?> getModelClass() {
        return BestPriceMaster.class;
    }

    @Override
    public String getModelClassName() {
        return BestPriceMaster.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("initialBestPrice", getInitialBestPrice());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("itemNo", getItemNo());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("accountId", getAccountId());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("itemId", getItemId());
        attributes.put("itemDesc", getItemDesc());
        attributes.put("sellingPrice", getSellingPrice());
        attributes.put("contractId", getContractId());
        attributes.put("contractNo", getContractNo());
        attributes.put("batchId", getBatchId());
        attributes.put("bestPriceMasterSid", getBestPriceMasterSid());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("recordLockStatus", getRecordLockStatus());
        attributes.put("beginningWacPackage", getBeginningWacPackage());
        attributes.put("initialDiscount", getInitialDiscount());
        attributes.put("period", getPeriod());
        attributes.put("source", getSource());
        attributes.put("contractStartDate", getContractStartDate());
        attributes.put("contractEndDate", getContractEndDate());
        attributes.put("inboundStatus", getInboundStatus());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Double initialBestPrice = (Double) attributes.get("initialBestPrice");

        if (initialBestPrice != null) {
            setInitialBestPrice(initialBestPrice);
        }

        Integer createdBy = (Integer) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        String itemNo = (String) attributes.get("itemNo");

        if (itemNo != null) {
            setItemNo(itemNo);
        }

        Integer modifiedBy = (Integer) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        String accountId = (String) attributes.get("accountId");

        if (accountId != null) {
            setAccountId(accountId);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        String itemId = (String) attributes.get("itemId");

        if (itemId != null) {
            setItemId(itemId);
        }

        String itemDesc = (String) attributes.get("itemDesc");

        if (itemDesc != null) {
            setItemDesc(itemDesc);
        }

        Double sellingPrice = (Double) attributes.get("sellingPrice");

        if (sellingPrice != null) {
            setSellingPrice(sellingPrice);
        }

        String contractId = (String) attributes.get("contractId");

        if (contractId != null) {
            setContractId(contractId);
        }

        String contractNo = (String) attributes.get("contractNo");

        if (contractNo != null) {
            setContractNo(contractNo);
        }

        String batchId = (String) attributes.get("batchId");

        if (batchId != null) {
            setBatchId(batchId);
        }

        Integer bestPriceMasterSid = (Integer) attributes.get(
                "bestPriceMasterSid");

        if (bestPriceMasterSid != null) {
            setBestPriceMasterSid(bestPriceMasterSid);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        Boolean recordLockStatus = (Boolean) attributes.get("recordLockStatus");

        if (recordLockStatus != null) {
            setRecordLockStatus(recordLockStatus);
        }

        Double beginningWacPackage = (Double) attributes.get(
                "beginningWacPackage");

        if (beginningWacPackage != null) {
            setBeginningWacPackage(beginningWacPackage);
        }

        Double initialDiscount = (Double) attributes.get("initialDiscount");

        if (initialDiscount != null) {
            setInitialDiscount(initialDiscount);
        }

        String period = (String) attributes.get("period");

        if (period != null) {
            setPeriod(period);
        }

        String source = (String) attributes.get("source");

        if (source != null) {
            setSource(source);
        }

        Date contractStartDate = (Date) attributes.get("contractStartDate");

        if (contractStartDate != null) {
            setContractStartDate(contractStartDate);
        }

        Date contractEndDate = (Date) attributes.get("contractEndDate");

        if (contractEndDate != null) {
            setContractEndDate(contractEndDate);
        }

        String inboundStatus = (String) attributes.get("inboundStatus");

        if (inboundStatus != null) {
            setInboundStatus(inboundStatus);
        }
    }

    /**
    * Returns the primary key of this best price master.
    *
    * @return the primary key of this best price master
    */
    @Override
    public int getPrimaryKey() {
        return _bestPriceMaster.getPrimaryKey();
    }

    /**
    * Sets the primary key of this best price master.
    *
    * @param primaryKey the primary key of this best price master
    */
    @Override
    public void setPrimaryKey(int primaryKey) {
        _bestPriceMaster.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the initial best price of this best price master.
    *
    * @return the initial best price of this best price master
    */
    @Override
    public double getInitialBestPrice() {
        return _bestPriceMaster.getInitialBestPrice();
    }

    /**
    * Sets the initial best price of this best price master.
    *
    * @param initialBestPrice the initial best price of this best price master
    */
    @Override
    public void setInitialBestPrice(double initialBestPrice) {
        _bestPriceMaster.setInitialBestPrice(initialBestPrice);
    }

    /**
    * Returns the created by of this best price master.
    *
    * @return the created by of this best price master
    */
    @Override
    public int getCreatedBy() {
        return _bestPriceMaster.getCreatedBy();
    }

    /**
    * Sets the created by of this best price master.
    *
    * @param createdBy the created by of this best price master
    */
    @Override
    public void setCreatedBy(int createdBy) {
        _bestPriceMaster.setCreatedBy(createdBy);
    }

    /**
    * Returns the item no of this best price master.
    *
    * @return the item no of this best price master
    */
    @Override
    public java.lang.String getItemNo() {
        return _bestPriceMaster.getItemNo();
    }

    /**
    * Sets the item no of this best price master.
    *
    * @param itemNo the item no of this best price master
    */
    @Override
    public void setItemNo(java.lang.String itemNo) {
        _bestPriceMaster.setItemNo(itemNo);
    }

    /**
    * Returns the modified by of this best price master.
    *
    * @return the modified by of this best price master
    */
    @Override
    public int getModifiedBy() {
        return _bestPriceMaster.getModifiedBy();
    }

    /**
    * Sets the modified by of this best price master.
    *
    * @param modifiedBy the modified by of this best price master
    */
    @Override
    public void setModifiedBy(int modifiedBy) {
        _bestPriceMaster.setModifiedBy(modifiedBy);
    }

    /**
    * Returns the account ID of this best price master.
    *
    * @return the account ID of this best price master
    */
    @Override
    public java.lang.String getAccountId() {
        return _bestPriceMaster.getAccountId();
    }

    /**
    * Sets the account ID of this best price master.
    *
    * @param accountId the account ID of this best price master
    */
    @Override
    public void setAccountId(java.lang.String accountId) {
        _bestPriceMaster.setAccountId(accountId);
    }

    /**
    * Returns the created date of this best price master.
    *
    * @return the created date of this best price master
    */
    @Override
    public java.util.Date getCreatedDate() {
        return _bestPriceMaster.getCreatedDate();
    }

    /**
    * Sets the created date of this best price master.
    *
    * @param createdDate the created date of this best price master
    */
    @Override
    public void setCreatedDate(java.util.Date createdDate) {
        _bestPriceMaster.setCreatedDate(createdDate);
    }

    /**
    * Returns the item ID of this best price master.
    *
    * @return the item ID of this best price master
    */
    @Override
    public java.lang.String getItemId() {
        return _bestPriceMaster.getItemId();
    }

    /**
    * Sets the item ID of this best price master.
    *
    * @param itemId the item ID of this best price master
    */
    @Override
    public void setItemId(java.lang.String itemId) {
        _bestPriceMaster.setItemId(itemId);
    }

    /**
    * Returns the item desc of this best price master.
    *
    * @return the item desc of this best price master
    */
    @Override
    public java.lang.String getItemDesc() {
        return _bestPriceMaster.getItemDesc();
    }

    /**
    * Sets the item desc of this best price master.
    *
    * @param itemDesc the item desc of this best price master
    */
    @Override
    public void setItemDesc(java.lang.String itemDesc) {
        _bestPriceMaster.setItemDesc(itemDesc);
    }

    /**
    * Returns the selling price of this best price master.
    *
    * @return the selling price of this best price master
    */
    @Override
    public double getSellingPrice() {
        return _bestPriceMaster.getSellingPrice();
    }

    /**
    * Sets the selling price of this best price master.
    *
    * @param sellingPrice the selling price of this best price master
    */
    @Override
    public void setSellingPrice(double sellingPrice) {
        _bestPriceMaster.setSellingPrice(sellingPrice);
    }

    /**
    * Returns the contract ID of this best price master.
    *
    * @return the contract ID of this best price master
    */
    @Override
    public java.lang.String getContractId() {
        return _bestPriceMaster.getContractId();
    }

    /**
    * Sets the contract ID of this best price master.
    *
    * @param contractId the contract ID of this best price master
    */
    @Override
    public void setContractId(java.lang.String contractId) {
        _bestPriceMaster.setContractId(contractId);
    }

    /**
    * Returns the contract no of this best price master.
    *
    * @return the contract no of this best price master
    */
    @Override
    public java.lang.String getContractNo() {
        return _bestPriceMaster.getContractNo();
    }

    /**
    * Sets the contract no of this best price master.
    *
    * @param contractNo the contract no of this best price master
    */
    @Override
    public void setContractNo(java.lang.String contractNo) {
        _bestPriceMaster.setContractNo(contractNo);
    }

    /**
    * Returns the batch ID of this best price master.
    *
    * @return the batch ID of this best price master
    */
    @Override
    public java.lang.String getBatchId() {
        return _bestPriceMaster.getBatchId();
    }

    /**
    * Sets the batch ID of this best price master.
    *
    * @param batchId the batch ID of this best price master
    */
    @Override
    public void setBatchId(java.lang.String batchId) {
        _bestPriceMaster.setBatchId(batchId);
    }

    /**
    * Returns the best price master sid of this best price master.
    *
    * @return the best price master sid of this best price master
    */
    @Override
    public int getBestPriceMasterSid() {
        return _bestPriceMaster.getBestPriceMasterSid();
    }

    /**
    * Sets the best price master sid of this best price master.
    *
    * @param bestPriceMasterSid the best price master sid of this best price master
    */
    @Override
    public void setBestPriceMasterSid(int bestPriceMasterSid) {
        _bestPriceMaster.setBestPriceMasterSid(bestPriceMasterSid);
    }

    /**
    * Returns the modified date of this best price master.
    *
    * @return the modified date of this best price master
    */
    @Override
    public java.util.Date getModifiedDate() {
        return _bestPriceMaster.getModifiedDate();
    }

    /**
    * Sets the modified date of this best price master.
    *
    * @param modifiedDate the modified date of this best price master
    */
    @Override
    public void setModifiedDate(java.util.Date modifiedDate) {
        _bestPriceMaster.setModifiedDate(modifiedDate);
    }

    /**
    * Returns the record lock status of this best price master.
    *
    * @return the record lock status of this best price master
    */
    @Override
    public boolean getRecordLockStatus() {
        return _bestPriceMaster.getRecordLockStatus();
    }

    /**
    * Returns <code>true</code> if this best price master is record lock status.
    *
    * @return <code>true</code> if this best price master is record lock status; <code>false</code> otherwise
    */
    @Override
    public boolean isRecordLockStatus() {
        return _bestPriceMaster.isRecordLockStatus();
    }

    /**
    * Sets whether this best price master is record lock status.
    *
    * @param recordLockStatus the record lock status of this best price master
    */
    @Override
    public void setRecordLockStatus(boolean recordLockStatus) {
        _bestPriceMaster.setRecordLockStatus(recordLockStatus);
    }

    /**
    * Returns the beginning wac package of this best price master.
    *
    * @return the beginning wac package of this best price master
    */
    @Override
    public double getBeginningWacPackage() {
        return _bestPriceMaster.getBeginningWacPackage();
    }

    /**
    * Sets the beginning wac package of this best price master.
    *
    * @param beginningWacPackage the beginning wac package of this best price master
    */
    @Override
    public void setBeginningWacPackage(double beginningWacPackage) {
        _bestPriceMaster.setBeginningWacPackage(beginningWacPackage);
    }

    /**
    * Returns the initial discount of this best price master.
    *
    * @return the initial discount of this best price master
    */
    @Override
    public double getInitialDiscount() {
        return _bestPriceMaster.getInitialDiscount();
    }

    /**
    * Sets the initial discount of this best price master.
    *
    * @param initialDiscount the initial discount of this best price master
    */
    @Override
    public void setInitialDiscount(double initialDiscount) {
        _bestPriceMaster.setInitialDiscount(initialDiscount);
    }

    /**
    * Returns the period of this best price master.
    *
    * @return the period of this best price master
    */
    @Override
    public java.lang.String getPeriod() {
        return _bestPriceMaster.getPeriod();
    }

    /**
    * Sets the period of this best price master.
    *
    * @param period the period of this best price master
    */
    @Override
    public void setPeriod(java.lang.String period) {
        _bestPriceMaster.setPeriod(period);
    }

    /**
    * Returns the source of this best price master.
    *
    * @return the source of this best price master
    */
    @Override
    public java.lang.String getSource() {
        return _bestPriceMaster.getSource();
    }

    /**
    * Sets the source of this best price master.
    *
    * @param source the source of this best price master
    */
    @Override
    public void setSource(java.lang.String source) {
        _bestPriceMaster.setSource(source);
    }

    /**
    * Returns the contract start date of this best price master.
    *
    * @return the contract start date of this best price master
    */
    @Override
    public java.util.Date getContractStartDate() {
        return _bestPriceMaster.getContractStartDate();
    }

    /**
    * Sets the contract start date of this best price master.
    *
    * @param contractStartDate the contract start date of this best price master
    */
    @Override
    public void setContractStartDate(java.util.Date contractStartDate) {
        _bestPriceMaster.setContractStartDate(contractStartDate);
    }

    /**
    * Returns the contract end date of this best price master.
    *
    * @return the contract end date of this best price master
    */
    @Override
    public java.util.Date getContractEndDate() {
        return _bestPriceMaster.getContractEndDate();
    }

    /**
    * Sets the contract end date of this best price master.
    *
    * @param contractEndDate the contract end date of this best price master
    */
    @Override
    public void setContractEndDate(java.util.Date contractEndDate) {
        _bestPriceMaster.setContractEndDate(contractEndDate);
    }

    /**
    * Returns the inbound status of this best price master.
    *
    * @return the inbound status of this best price master
    */
    @Override
    public java.lang.String getInboundStatus() {
        return _bestPriceMaster.getInboundStatus();
    }

    /**
    * Sets the inbound status of this best price master.
    *
    * @param inboundStatus the inbound status of this best price master
    */
    @Override
    public void setInboundStatus(java.lang.String inboundStatus) {
        _bestPriceMaster.setInboundStatus(inboundStatus);
    }

    @Override
    public boolean isNew() {
        return _bestPriceMaster.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _bestPriceMaster.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _bestPriceMaster.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _bestPriceMaster.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _bestPriceMaster.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _bestPriceMaster.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _bestPriceMaster.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _bestPriceMaster.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _bestPriceMaster.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _bestPriceMaster.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _bestPriceMaster.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new BestPriceMasterWrapper((BestPriceMaster) _bestPriceMaster.clone());
    }

    @Override
    public int compareTo(BestPriceMaster bestPriceMaster) {
        return _bestPriceMaster.compareTo(bestPriceMaster);
    }

    @Override
    public int hashCode() {
        return _bestPriceMaster.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<BestPriceMaster> toCacheModel() {
        return _bestPriceMaster.toCacheModel();
    }

    @Override
    public BestPriceMaster toEscapedModel() {
        return new BestPriceMasterWrapper(_bestPriceMaster.toEscapedModel());
    }

    @Override
    public BestPriceMaster toUnescapedModel() {
        return new BestPriceMasterWrapper(_bestPriceMaster.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _bestPriceMaster.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _bestPriceMaster.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _bestPriceMaster.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof BestPriceMasterWrapper)) {
            return false;
        }

        BestPriceMasterWrapper bestPriceMasterWrapper = (BestPriceMasterWrapper) obj;

        if (Validator.equals(_bestPriceMaster,
                    bestPriceMasterWrapper._bestPriceMaster)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public BestPriceMaster getWrappedBestPriceMaster() {
        return _bestPriceMaster;
    }

    @Override
    public BestPriceMaster getWrappedModel() {
        return _bestPriceMaster;
    }

    @Override
    public void resetOriginalValues() {
        _bestPriceMaster.resetOriginalValues();
    }
}
