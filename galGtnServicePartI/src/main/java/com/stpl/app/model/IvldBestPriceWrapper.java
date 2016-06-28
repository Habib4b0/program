package com.stpl.app.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link IvldBestPrice}.
 * </p>
 *
 * @author
 * @see IvldBestPrice
 * @generated
 */
public class IvldBestPriceWrapper implements IvldBestPrice,
    ModelWrapper<IvldBestPrice> {
    private IvldBestPrice _ivldBestPrice;

    public IvldBestPriceWrapper(IvldBestPrice ivldBestPrice) {
        _ivldBestPrice = ivldBestPrice;
    }

    @Override
    public Class<?> getModelClass() {
        return IvldBestPrice.class;
    }

    @Override
    public String getModelClassName() {
        return IvldBestPrice.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("itemDesc", getItemDesc());
        attributes.put("bestPriceIntfid", getBestPriceIntfid());
        attributes.put("accountId", getAccountId());
        attributes.put("sellingPrice", getSellingPrice());
        attributes.put("period", getPeriod());
        attributes.put("itemId", getItemId());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("source", getSource());
        attributes.put("addChgDelIndicator", getAddChgDelIndicator());
        attributes.put("initialDiscount", getInitialDiscount());
        attributes.put("errorCode", getErrorCode());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("intfInsertedDate", getIntfInsertedDate());
        attributes.put("itemNo", getItemNo());
        attributes.put("reprocessedFlag", getReprocessedFlag());
        attributes.put("contractEndDate", getContractEndDate());
        attributes.put("ivldBestPriceSid", getIvldBestPriceSid());
        attributes.put("contractId", getContractId());
        attributes.put("beginningWacPackage", getBeginningWacPackage());
        attributes.put("reasonForFailure", getReasonForFailure());
        attributes.put("contractStartDate", getContractStartDate());
        attributes.put("batchId", getBatchId());
        attributes.put("errorField", getErrorField());
        attributes.put("initialBestPrice", getInitialBestPrice());
        attributes.put("contractNo", getContractNo());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        String itemDesc = (String) attributes.get("itemDesc");

        if (itemDesc != null) {
            setItemDesc(itemDesc);
        }

        String bestPriceIntfid = (String) attributes.get("bestPriceIntfid");

        if (bestPriceIntfid != null) {
            setBestPriceIntfid(bestPriceIntfid);
        }

        String accountId = (String) attributes.get("accountId");

        if (accountId != null) {
            setAccountId(accountId);
        }

        String sellingPrice = (String) attributes.get("sellingPrice");

        if (sellingPrice != null) {
            setSellingPrice(sellingPrice);
        }

        String period = (String) attributes.get("period");

        if (period != null) {
            setPeriod(period);
        }

        String itemId = (String) attributes.get("itemId");

        if (itemId != null) {
            setItemId(itemId);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        String createdBy = (String) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        String source = (String) attributes.get("source");

        if (source != null) {
            setSource(source);
        }

        String addChgDelIndicator = (String) attributes.get(
                "addChgDelIndicator");

        if (addChgDelIndicator != null) {
            setAddChgDelIndicator(addChgDelIndicator);
        }

        String initialDiscount = (String) attributes.get("initialDiscount");

        if (initialDiscount != null) {
            setInitialDiscount(initialDiscount);
        }

        String errorCode = (String) attributes.get("errorCode");

        if (errorCode != null) {
            setErrorCode(errorCode);
        }

        String modifiedBy = (String) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        Date intfInsertedDate = (Date) attributes.get("intfInsertedDate");

        if (intfInsertedDate != null) {
            setIntfInsertedDate(intfInsertedDate);
        }

        String itemNo = (String) attributes.get("itemNo");

        if (itemNo != null) {
            setItemNo(itemNo);
        }

        String reprocessedFlag = (String) attributes.get("reprocessedFlag");

        if (reprocessedFlag != null) {
            setReprocessedFlag(reprocessedFlag);
        }

        String contractEndDate = (String) attributes.get("contractEndDate");

        if (contractEndDate != null) {
            setContractEndDate(contractEndDate);
        }

        Integer ivldBestPriceSid = (Integer) attributes.get("ivldBestPriceSid");

        if (ivldBestPriceSid != null) {
            setIvldBestPriceSid(ivldBestPriceSid);
        }

        String contractId = (String) attributes.get("contractId");

        if (contractId != null) {
            setContractId(contractId);
        }

        String beginningWacPackage = (String) attributes.get(
                "beginningWacPackage");

        if (beginningWacPackage != null) {
            setBeginningWacPackage(beginningWacPackage);
        }

        String reasonForFailure = (String) attributes.get("reasonForFailure");

        if (reasonForFailure != null) {
            setReasonForFailure(reasonForFailure);
        }

        String contractStartDate = (String) attributes.get("contractStartDate");

        if (contractStartDate != null) {
            setContractStartDate(contractStartDate);
        }

        String batchId = (String) attributes.get("batchId");

        if (batchId != null) {
            setBatchId(batchId);
        }

        String errorField = (String) attributes.get("errorField");

        if (errorField != null) {
            setErrorField(errorField);
        }

        String initialBestPrice = (String) attributes.get("initialBestPrice");

        if (initialBestPrice != null) {
            setInitialBestPrice(initialBestPrice);
        }

        String contractNo = (String) attributes.get("contractNo");

        if (contractNo != null) {
            setContractNo(contractNo);
        }
    }

    /**
    * Returns the primary key of this ivld best price.
    *
    * @return the primary key of this ivld best price
    */
    @Override
    public int getPrimaryKey() {
        return _ivldBestPrice.getPrimaryKey();
    }

    /**
    * Sets the primary key of this ivld best price.
    *
    * @param primaryKey the primary key of this ivld best price
    */
    @Override
    public void setPrimaryKey(int primaryKey) {
        _ivldBestPrice.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the item desc of this ivld best price.
    *
    * @return the item desc of this ivld best price
    */
    @Override
    public java.lang.String getItemDesc() {
        return _ivldBestPrice.getItemDesc();
    }

    /**
    * Sets the item desc of this ivld best price.
    *
    * @param itemDesc the item desc of this ivld best price
    */
    @Override
    public void setItemDesc(java.lang.String itemDesc) {
        _ivldBestPrice.setItemDesc(itemDesc);
    }

    /**
    * Returns the best price intfid of this ivld best price.
    *
    * @return the best price intfid of this ivld best price
    */
    @Override
    public java.lang.String getBestPriceIntfid() {
        return _ivldBestPrice.getBestPriceIntfid();
    }

    /**
    * Sets the best price intfid of this ivld best price.
    *
    * @param bestPriceIntfid the best price intfid of this ivld best price
    */
    @Override
    public void setBestPriceIntfid(java.lang.String bestPriceIntfid) {
        _ivldBestPrice.setBestPriceIntfid(bestPriceIntfid);
    }

    /**
    * Returns the account ID of this ivld best price.
    *
    * @return the account ID of this ivld best price
    */
    @Override
    public java.lang.String getAccountId() {
        return _ivldBestPrice.getAccountId();
    }

    /**
    * Sets the account ID of this ivld best price.
    *
    * @param accountId the account ID of this ivld best price
    */
    @Override
    public void setAccountId(java.lang.String accountId) {
        _ivldBestPrice.setAccountId(accountId);
    }

    /**
    * Returns the selling price of this ivld best price.
    *
    * @return the selling price of this ivld best price
    */
    @Override
    public java.lang.String getSellingPrice() {
        return _ivldBestPrice.getSellingPrice();
    }

    /**
    * Sets the selling price of this ivld best price.
    *
    * @param sellingPrice the selling price of this ivld best price
    */
    @Override
    public void setSellingPrice(java.lang.String sellingPrice) {
        _ivldBestPrice.setSellingPrice(sellingPrice);
    }

    /**
    * Returns the period of this ivld best price.
    *
    * @return the period of this ivld best price
    */
    @Override
    public java.lang.String getPeriod() {
        return _ivldBestPrice.getPeriod();
    }

    /**
    * Sets the period of this ivld best price.
    *
    * @param period the period of this ivld best price
    */
    @Override
    public void setPeriod(java.lang.String period) {
        _ivldBestPrice.setPeriod(period);
    }

    /**
    * Returns the item ID of this ivld best price.
    *
    * @return the item ID of this ivld best price
    */
    @Override
    public java.lang.String getItemId() {
        return _ivldBestPrice.getItemId();
    }

    /**
    * Sets the item ID of this ivld best price.
    *
    * @param itemId the item ID of this ivld best price
    */
    @Override
    public void setItemId(java.lang.String itemId) {
        _ivldBestPrice.setItemId(itemId);
    }

    /**
    * Returns the modified date of this ivld best price.
    *
    * @return the modified date of this ivld best price
    */
    @Override
    public java.util.Date getModifiedDate() {
        return _ivldBestPrice.getModifiedDate();
    }

    /**
    * Sets the modified date of this ivld best price.
    *
    * @param modifiedDate the modified date of this ivld best price
    */
    @Override
    public void setModifiedDate(java.util.Date modifiedDate) {
        _ivldBestPrice.setModifiedDate(modifiedDate);
    }

    /**
    * Returns the created by of this ivld best price.
    *
    * @return the created by of this ivld best price
    */
    @Override
    public java.lang.String getCreatedBy() {
        return _ivldBestPrice.getCreatedBy();
    }

    /**
    * Sets the created by of this ivld best price.
    *
    * @param createdBy the created by of this ivld best price
    */
    @Override
    public void setCreatedBy(java.lang.String createdBy) {
        _ivldBestPrice.setCreatedBy(createdBy);
    }

    /**
    * Returns the created date of this ivld best price.
    *
    * @return the created date of this ivld best price
    */
    @Override
    public java.util.Date getCreatedDate() {
        return _ivldBestPrice.getCreatedDate();
    }

    /**
    * Sets the created date of this ivld best price.
    *
    * @param createdDate the created date of this ivld best price
    */
    @Override
    public void setCreatedDate(java.util.Date createdDate) {
        _ivldBestPrice.setCreatedDate(createdDate);
    }

    /**
    * Returns the source of this ivld best price.
    *
    * @return the source of this ivld best price
    */
    @Override
    public java.lang.String getSource() {
        return _ivldBestPrice.getSource();
    }

    /**
    * Sets the source of this ivld best price.
    *
    * @param source the source of this ivld best price
    */
    @Override
    public void setSource(java.lang.String source) {
        _ivldBestPrice.setSource(source);
    }

    /**
    * Returns the add chg del indicator of this ivld best price.
    *
    * @return the add chg del indicator of this ivld best price
    */
    @Override
    public java.lang.String getAddChgDelIndicator() {
        return _ivldBestPrice.getAddChgDelIndicator();
    }

    /**
    * Sets the add chg del indicator of this ivld best price.
    *
    * @param addChgDelIndicator the add chg del indicator of this ivld best price
    */
    @Override
    public void setAddChgDelIndicator(java.lang.String addChgDelIndicator) {
        _ivldBestPrice.setAddChgDelIndicator(addChgDelIndicator);
    }

    /**
    * Returns the initial discount of this ivld best price.
    *
    * @return the initial discount of this ivld best price
    */
    @Override
    public java.lang.String getInitialDiscount() {
        return _ivldBestPrice.getInitialDiscount();
    }

    /**
    * Sets the initial discount of this ivld best price.
    *
    * @param initialDiscount the initial discount of this ivld best price
    */
    @Override
    public void setInitialDiscount(java.lang.String initialDiscount) {
        _ivldBestPrice.setInitialDiscount(initialDiscount);
    }

    /**
    * Returns the error code of this ivld best price.
    *
    * @return the error code of this ivld best price
    */
    @Override
    public java.lang.String getErrorCode() {
        return _ivldBestPrice.getErrorCode();
    }

    /**
    * Sets the error code of this ivld best price.
    *
    * @param errorCode the error code of this ivld best price
    */
    @Override
    public void setErrorCode(java.lang.String errorCode) {
        _ivldBestPrice.setErrorCode(errorCode);
    }

    /**
    * Returns the modified by of this ivld best price.
    *
    * @return the modified by of this ivld best price
    */
    @Override
    public java.lang.String getModifiedBy() {
        return _ivldBestPrice.getModifiedBy();
    }

    /**
    * Sets the modified by of this ivld best price.
    *
    * @param modifiedBy the modified by of this ivld best price
    */
    @Override
    public void setModifiedBy(java.lang.String modifiedBy) {
        _ivldBestPrice.setModifiedBy(modifiedBy);
    }

    /**
    * Returns the intf inserted date of this ivld best price.
    *
    * @return the intf inserted date of this ivld best price
    */
    @Override
    public java.util.Date getIntfInsertedDate() {
        return _ivldBestPrice.getIntfInsertedDate();
    }

    /**
    * Sets the intf inserted date of this ivld best price.
    *
    * @param intfInsertedDate the intf inserted date of this ivld best price
    */
    @Override
    public void setIntfInsertedDate(java.util.Date intfInsertedDate) {
        _ivldBestPrice.setIntfInsertedDate(intfInsertedDate);
    }

    /**
    * Returns the item no of this ivld best price.
    *
    * @return the item no of this ivld best price
    */
    @Override
    public java.lang.String getItemNo() {
        return _ivldBestPrice.getItemNo();
    }

    /**
    * Sets the item no of this ivld best price.
    *
    * @param itemNo the item no of this ivld best price
    */
    @Override
    public void setItemNo(java.lang.String itemNo) {
        _ivldBestPrice.setItemNo(itemNo);
    }

    /**
    * Returns the reprocessed flag of this ivld best price.
    *
    * @return the reprocessed flag of this ivld best price
    */
    @Override
    public java.lang.String getReprocessedFlag() {
        return _ivldBestPrice.getReprocessedFlag();
    }

    /**
    * Sets the reprocessed flag of this ivld best price.
    *
    * @param reprocessedFlag the reprocessed flag of this ivld best price
    */
    @Override
    public void setReprocessedFlag(java.lang.String reprocessedFlag) {
        _ivldBestPrice.setReprocessedFlag(reprocessedFlag);
    }

    /**
    * Returns the contract end date of this ivld best price.
    *
    * @return the contract end date of this ivld best price
    */
    @Override
    public java.lang.String getContractEndDate() {
        return _ivldBestPrice.getContractEndDate();
    }

    /**
    * Sets the contract end date of this ivld best price.
    *
    * @param contractEndDate the contract end date of this ivld best price
    */
    @Override
    public void setContractEndDate(java.lang.String contractEndDate) {
        _ivldBestPrice.setContractEndDate(contractEndDate);
    }

    /**
    * Returns the ivld best price sid of this ivld best price.
    *
    * @return the ivld best price sid of this ivld best price
    */
    @Override
    public int getIvldBestPriceSid() {
        return _ivldBestPrice.getIvldBestPriceSid();
    }

    /**
    * Sets the ivld best price sid of this ivld best price.
    *
    * @param ivldBestPriceSid the ivld best price sid of this ivld best price
    */
    @Override
    public void setIvldBestPriceSid(int ivldBestPriceSid) {
        _ivldBestPrice.setIvldBestPriceSid(ivldBestPriceSid);
    }

    /**
    * Returns the contract ID of this ivld best price.
    *
    * @return the contract ID of this ivld best price
    */
    @Override
    public java.lang.String getContractId() {
        return _ivldBestPrice.getContractId();
    }

    /**
    * Sets the contract ID of this ivld best price.
    *
    * @param contractId the contract ID of this ivld best price
    */
    @Override
    public void setContractId(java.lang.String contractId) {
        _ivldBestPrice.setContractId(contractId);
    }

    /**
    * Returns the beginning wac package of this ivld best price.
    *
    * @return the beginning wac package of this ivld best price
    */
    @Override
    public java.lang.String getBeginningWacPackage() {
        return _ivldBestPrice.getBeginningWacPackage();
    }

    /**
    * Sets the beginning wac package of this ivld best price.
    *
    * @param beginningWacPackage the beginning wac package of this ivld best price
    */
    @Override
    public void setBeginningWacPackage(java.lang.String beginningWacPackage) {
        _ivldBestPrice.setBeginningWacPackage(beginningWacPackage);
    }

    /**
    * Returns the reason for failure of this ivld best price.
    *
    * @return the reason for failure of this ivld best price
    */
    @Override
    public java.lang.String getReasonForFailure() {
        return _ivldBestPrice.getReasonForFailure();
    }

    /**
    * Sets the reason for failure of this ivld best price.
    *
    * @param reasonForFailure the reason for failure of this ivld best price
    */
    @Override
    public void setReasonForFailure(java.lang.String reasonForFailure) {
        _ivldBestPrice.setReasonForFailure(reasonForFailure);
    }

    /**
    * Returns the contract start date of this ivld best price.
    *
    * @return the contract start date of this ivld best price
    */
    @Override
    public java.lang.String getContractStartDate() {
        return _ivldBestPrice.getContractStartDate();
    }

    /**
    * Sets the contract start date of this ivld best price.
    *
    * @param contractStartDate the contract start date of this ivld best price
    */
    @Override
    public void setContractStartDate(java.lang.String contractStartDate) {
        _ivldBestPrice.setContractStartDate(contractStartDate);
    }

    /**
    * Returns the batch ID of this ivld best price.
    *
    * @return the batch ID of this ivld best price
    */
    @Override
    public java.lang.String getBatchId() {
        return _ivldBestPrice.getBatchId();
    }

    /**
    * Sets the batch ID of this ivld best price.
    *
    * @param batchId the batch ID of this ivld best price
    */
    @Override
    public void setBatchId(java.lang.String batchId) {
        _ivldBestPrice.setBatchId(batchId);
    }

    /**
    * Returns the error field of this ivld best price.
    *
    * @return the error field of this ivld best price
    */
    @Override
    public java.lang.String getErrorField() {
        return _ivldBestPrice.getErrorField();
    }

    /**
    * Sets the error field of this ivld best price.
    *
    * @param errorField the error field of this ivld best price
    */
    @Override
    public void setErrorField(java.lang.String errorField) {
        _ivldBestPrice.setErrorField(errorField);
    }

    /**
    * Returns the initial best price of this ivld best price.
    *
    * @return the initial best price of this ivld best price
    */
    @Override
    public java.lang.String getInitialBestPrice() {
        return _ivldBestPrice.getInitialBestPrice();
    }

    /**
    * Sets the initial best price of this ivld best price.
    *
    * @param initialBestPrice the initial best price of this ivld best price
    */
    @Override
    public void setInitialBestPrice(java.lang.String initialBestPrice) {
        _ivldBestPrice.setInitialBestPrice(initialBestPrice);
    }

    /**
    * Returns the contract no of this ivld best price.
    *
    * @return the contract no of this ivld best price
    */
    @Override
    public java.lang.String getContractNo() {
        return _ivldBestPrice.getContractNo();
    }

    /**
    * Sets the contract no of this ivld best price.
    *
    * @param contractNo the contract no of this ivld best price
    */
    @Override
    public void setContractNo(java.lang.String contractNo) {
        _ivldBestPrice.setContractNo(contractNo);
    }

    @Override
    public boolean isNew() {
        return _ivldBestPrice.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _ivldBestPrice.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _ivldBestPrice.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _ivldBestPrice.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _ivldBestPrice.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _ivldBestPrice.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _ivldBestPrice.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _ivldBestPrice.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _ivldBestPrice.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _ivldBestPrice.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _ivldBestPrice.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new IvldBestPriceWrapper((IvldBestPrice) _ivldBestPrice.clone());
    }

    @Override
    public int compareTo(IvldBestPrice ivldBestPrice) {
        return _ivldBestPrice.compareTo(ivldBestPrice);
    }

    @Override
    public int hashCode() {
        return _ivldBestPrice.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<IvldBestPrice> toCacheModel() {
        return _ivldBestPrice.toCacheModel();
    }

    @Override
    public IvldBestPrice toEscapedModel() {
        return new IvldBestPriceWrapper(_ivldBestPrice.toEscapedModel());
    }

    @Override
    public IvldBestPrice toUnescapedModel() {
        return new IvldBestPriceWrapper(_ivldBestPrice.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _ivldBestPrice.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _ivldBestPrice.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _ivldBestPrice.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof IvldBestPriceWrapper)) {
            return false;
        }

        IvldBestPriceWrapper ivldBestPriceWrapper = (IvldBestPriceWrapper) obj;

        if (Validator.equals(_ivldBestPrice, ivldBestPriceWrapper._ivldBestPrice)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public IvldBestPrice getWrappedIvldBestPrice() {
        return _ivldBestPrice;
    }

    @Override
    public IvldBestPrice getWrappedModel() {
        return _ivldBestPrice;
    }

    @Override
    public void resetOriginalValues() {
        _ivldBestPrice.resetOriginalValues();
    }
}
