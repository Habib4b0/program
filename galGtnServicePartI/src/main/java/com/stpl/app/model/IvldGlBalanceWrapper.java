package com.stpl.app.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link IvldGlBalance}.
 * </p>
 *
 * @author
 * @see IvldGlBalance
 * @generated
 */
public class IvldGlBalanceWrapper implements IvldGlBalance,
    ModelWrapper<IvldGlBalance> {
    private IvldGlBalance _ivldGlBalance;

    public IvldGlBalanceWrapper(IvldGlBalance ivldGlBalance) {
        _ivldGlBalance = ivldGlBalance;
    }

    @Override
    public Class<?> getModelClass() {
        return IvldGlBalance.class;
    }

    @Override
    public String getModelClassName() {
        return IvldGlBalance.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("balance", getBalance());
        attributes.put("accountNo", getAccountNo());
        attributes.put("reasonForFailure", getReasonForFailure());
        attributes.put("accountId", getAccountId());
        attributes.put("year", getYear());
        attributes.put("period", getPeriod());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("isActive", getIsActive());
        attributes.put("source", getSource());
        attributes.put("uploadDate", getUploadDate());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("addChgDelIndicator", getAddChgDelIndicator());
        attributes.put("batchId", getBatchId());
        attributes.put("closeIndicator", getCloseIndicator());
        attributes.put("insertedDate", getInsertedDate());
        attributes.put("errorField", getErrorField());
        attributes.put("ivldGlBalanceSid", getIvldGlBalanceSid());
        attributes.put("errorCode", getErrorCode());
        attributes.put("glBalanceIntfid", getGlBalanceIntfid());
        attributes.put("intfInsertedDate", getIntfInsertedDate());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("reprocessedFlag", getReprocessedFlag());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        String balance = (String) attributes.get("balance");

        if (balance != null) {
            setBalance(balance);
        }

        String accountNo = (String) attributes.get("accountNo");

        if (accountNo != null) {
            setAccountNo(accountNo);
        }

        String reasonForFailure = (String) attributes.get("reasonForFailure");

        if (reasonForFailure != null) {
            setReasonForFailure(reasonForFailure);
        }

        String accountId = (String) attributes.get("accountId");

        if (accountId != null) {
            setAccountId(accountId);
        }

        String year = (String) attributes.get("year");

        if (year != null) {
            setYear(year);
        }

        String period = (String) attributes.get("period");

        if (period != null) {
            setPeriod(period);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        String isActive = (String) attributes.get("isActive");

        if (isActive != null) {
            setIsActive(isActive);
        }

        String source = (String) attributes.get("source");

        if (source != null) {
            setSource(source);
        }

        String uploadDate = (String) attributes.get("uploadDate");

        if (uploadDate != null) {
            setUploadDate(uploadDate);
        }

        String createdBy = (String) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        String addChgDelIndicator = (String) attributes.get(
                "addChgDelIndicator");

        if (addChgDelIndicator != null) {
            setAddChgDelIndicator(addChgDelIndicator);
        }

        String batchId = (String) attributes.get("batchId");

        if (batchId != null) {
            setBatchId(batchId);
        }

        String closeIndicator = (String) attributes.get("closeIndicator");

        if (closeIndicator != null) {
            setCloseIndicator(closeIndicator);
        }

        String insertedDate = (String) attributes.get("insertedDate");

        if (insertedDate != null) {
            setInsertedDate(insertedDate);
        }

        String errorField = (String) attributes.get("errorField");

        if (errorField != null) {
            setErrorField(errorField);
        }

        Integer ivldGlBalanceSid = (Integer) attributes.get("ivldGlBalanceSid");

        if (ivldGlBalanceSid != null) {
            setIvldGlBalanceSid(ivldGlBalanceSid);
        }

        String errorCode = (String) attributes.get("errorCode");

        if (errorCode != null) {
            setErrorCode(errorCode);
        }

        String glBalanceIntfid = (String) attributes.get("glBalanceIntfid");

        if (glBalanceIntfid != null) {
            setGlBalanceIntfid(glBalanceIntfid);
        }

        Date intfInsertedDate = (Date) attributes.get("intfInsertedDate");

        if (intfInsertedDate != null) {
            setIntfInsertedDate(intfInsertedDate);
        }

        String modifiedBy = (String) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        String reprocessedFlag = (String) attributes.get("reprocessedFlag");

        if (reprocessedFlag != null) {
            setReprocessedFlag(reprocessedFlag);
        }
    }

    /**
    * Returns the primary key of this ivld gl balance.
    *
    * @return the primary key of this ivld gl balance
    */
    @Override
    public int getPrimaryKey() {
        return _ivldGlBalance.getPrimaryKey();
    }

    /**
    * Sets the primary key of this ivld gl balance.
    *
    * @param primaryKey the primary key of this ivld gl balance
    */
    @Override
    public void setPrimaryKey(int primaryKey) {
        _ivldGlBalance.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the balance of this ivld gl balance.
    *
    * @return the balance of this ivld gl balance
    */
    @Override
    public java.lang.String getBalance() {
        return _ivldGlBalance.getBalance();
    }

    /**
    * Sets the balance of this ivld gl balance.
    *
    * @param balance the balance of this ivld gl balance
    */
    @Override
    public void setBalance(java.lang.String balance) {
        _ivldGlBalance.setBalance(balance);
    }

    /**
    * Returns the account no of this ivld gl balance.
    *
    * @return the account no of this ivld gl balance
    */
    @Override
    public java.lang.String getAccountNo() {
        return _ivldGlBalance.getAccountNo();
    }

    /**
    * Sets the account no of this ivld gl balance.
    *
    * @param accountNo the account no of this ivld gl balance
    */
    @Override
    public void setAccountNo(java.lang.String accountNo) {
        _ivldGlBalance.setAccountNo(accountNo);
    }

    /**
    * Returns the reason for failure of this ivld gl balance.
    *
    * @return the reason for failure of this ivld gl balance
    */
    @Override
    public java.lang.String getReasonForFailure() {
        return _ivldGlBalance.getReasonForFailure();
    }

    /**
    * Sets the reason for failure of this ivld gl balance.
    *
    * @param reasonForFailure the reason for failure of this ivld gl balance
    */
    @Override
    public void setReasonForFailure(java.lang.String reasonForFailure) {
        _ivldGlBalance.setReasonForFailure(reasonForFailure);
    }

    /**
    * Returns the account ID of this ivld gl balance.
    *
    * @return the account ID of this ivld gl balance
    */
    @Override
    public java.lang.String getAccountId() {
        return _ivldGlBalance.getAccountId();
    }

    /**
    * Sets the account ID of this ivld gl balance.
    *
    * @param accountId the account ID of this ivld gl balance
    */
    @Override
    public void setAccountId(java.lang.String accountId) {
        _ivldGlBalance.setAccountId(accountId);
    }

    /**
    * Returns the year of this ivld gl balance.
    *
    * @return the year of this ivld gl balance
    */
    @Override
    public java.lang.String getYear() {
        return _ivldGlBalance.getYear();
    }

    /**
    * Sets the year of this ivld gl balance.
    *
    * @param year the year of this ivld gl balance
    */
    @Override
    public void setYear(java.lang.String year) {
        _ivldGlBalance.setYear(year);
    }

    /**
    * Returns the period of this ivld gl balance.
    *
    * @return the period of this ivld gl balance
    */
    @Override
    public java.lang.String getPeriod() {
        return _ivldGlBalance.getPeriod();
    }

    /**
    * Sets the period of this ivld gl balance.
    *
    * @param period the period of this ivld gl balance
    */
    @Override
    public void setPeriod(java.lang.String period) {
        _ivldGlBalance.setPeriod(period);
    }

    /**
    * Returns the modified date of this ivld gl balance.
    *
    * @return the modified date of this ivld gl balance
    */
    @Override
    public java.util.Date getModifiedDate() {
        return _ivldGlBalance.getModifiedDate();
    }

    /**
    * Sets the modified date of this ivld gl balance.
    *
    * @param modifiedDate the modified date of this ivld gl balance
    */
    @Override
    public void setModifiedDate(java.util.Date modifiedDate) {
        _ivldGlBalance.setModifiedDate(modifiedDate);
    }

    /**
    * Returns the is active of this ivld gl balance.
    *
    * @return the is active of this ivld gl balance
    */
    @Override
    public java.lang.String getIsActive() {
        return _ivldGlBalance.getIsActive();
    }

    /**
    * Sets the is active of this ivld gl balance.
    *
    * @param isActive the is active of this ivld gl balance
    */
    @Override
    public void setIsActive(java.lang.String isActive) {
        _ivldGlBalance.setIsActive(isActive);
    }

    /**
    * Returns the source of this ivld gl balance.
    *
    * @return the source of this ivld gl balance
    */
    @Override
    public java.lang.String getSource() {
        return _ivldGlBalance.getSource();
    }

    /**
    * Sets the source of this ivld gl balance.
    *
    * @param source the source of this ivld gl balance
    */
    @Override
    public void setSource(java.lang.String source) {
        _ivldGlBalance.setSource(source);
    }

    /**
    * Returns the upload date of this ivld gl balance.
    *
    * @return the upload date of this ivld gl balance
    */
    @Override
    public java.lang.String getUploadDate() {
        return _ivldGlBalance.getUploadDate();
    }

    /**
    * Sets the upload date of this ivld gl balance.
    *
    * @param uploadDate the upload date of this ivld gl balance
    */
    @Override
    public void setUploadDate(java.lang.String uploadDate) {
        _ivldGlBalance.setUploadDate(uploadDate);
    }

    /**
    * Returns the created by of this ivld gl balance.
    *
    * @return the created by of this ivld gl balance
    */
    @Override
    public java.lang.String getCreatedBy() {
        return _ivldGlBalance.getCreatedBy();
    }

    /**
    * Sets the created by of this ivld gl balance.
    *
    * @param createdBy the created by of this ivld gl balance
    */
    @Override
    public void setCreatedBy(java.lang.String createdBy) {
        _ivldGlBalance.setCreatedBy(createdBy);
    }

    /**
    * Returns the created date of this ivld gl balance.
    *
    * @return the created date of this ivld gl balance
    */
    @Override
    public java.util.Date getCreatedDate() {
        return _ivldGlBalance.getCreatedDate();
    }

    /**
    * Sets the created date of this ivld gl balance.
    *
    * @param createdDate the created date of this ivld gl balance
    */
    @Override
    public void setCreatedDate(java.util.Date createdDate) {
        _ivldGlBalance.setCreatedDate(createdDate);
    }

    /**
    * Returns the add chg del indicator of this ivld gl balance.
    *
    * @return the add chg del indicator of this ivld gl balance
    */
    @Override
    public java.lang.String getAddChgDelIndicator() {
        return _ivldGlBalance.getAddChgDelIndicator();
    }

    /**
    * Sets the add chg del indicator of this ivld gl balance.
    *
    * @param addChgDelIndicator the add chg del indicator of this ivld gl balance
    */
    @Override
    public void setAddChgDelIndicator(java.lang.String addChgDelIndicator) {
        _ivldGlBalance.setAddChgDelIndicator(addChgDelIndicator);
    }

    /**
    * Returns the batch ID of this ivld gl balance.
    *
    * @return the batch ID of this ivld gl balance
    */
    @Override
    public java.lang.String getBatchId() {
        return _ivldGlBalance.getBatchId();
    }

    /**
    * Sets the batch ID of this ivld gl balance.
    *
    * @param batchId the batch ID of this ivld gl balance
    */
    @Override
    public void setBatchId(java.lang.String batchId) {
        _ivldGlBalance.setBatchId(batchId);
    }

    /**
    * Returns the close indicator of this ivld gl balance.
    *
    * @return the close indicator of this ivld gl balance
    */
    @Override
    public java.lang.String getCloseIndicator() {
        return _ivldGlBalance.getCloseIndicator();
    }

    /**
    * Sets the close indicator of this ivld gl balance.
    *
    * @param closeIndicator the close indicator of this ivld gl balance
    */
    @Override
    public void setCloseIndicator(java.lang.String closeIndicator) {
        _ivldGlBalance.setCloseIndicator(closeIndicator);
    }

    /**
    * Returns the inserted date of this ivld gl balance.
    *
    * @return the inserted date of this ivld gl balance
    */
    @Override
    public java.lang.String getInsertedDate() {
        return _ivldGlBalance.getInsertedDate();
    }

    /**
    * Sets the inserted date of this ivld gl balance.
    *
    * @param insertedDate the inserted date of this ivld gl balance
    */
    @Override
    public void setInsertedDate(java.lang.String insertedDate) {
        _ivldGlBalance.setInsertedDate(insertedDate);
    }

    /**
    * Returns the error field of this ivld gl balance.
    *
    * @return the error field of this ivld gl balance
    */
    @Override
    public java.lang.String getErrorField() {
        return _ivldGlBalance.getErrorField();
    }

    /**
    * Sets the error field of this ivld gl balance.
    *
    * @param errorField the error field of this ivld gl balance
    */
    @Override
    public void setErrorField(java.lang.String errorField) {
        _ivldGlBalance.setErrorField(errorField);
    }

    /**
    * Returns the ivld gl balance sid of this ivld gl balance.
    *
    * @return the ivld gl balance sid of this ivld gl balance
    */
    @Override
    public int getIvldGlBalanceSid() {
        return _ivldGlBalance.getIvldGlBalanceSid();
    }

    /**
    * Sets the ivld gl balance sid of this ivld gl balance.
    *
    * @param ivldGlBalanceSid the ivld gl balance sid of this ivld gl balance
    */
    @Override
    public void setIvldGlBalanceSid(int ivldGlBalanceSid) {
        _ivldGlBalance.setIvldGlBalanceSid(ivldGlBalanceSid);
    }

    /**
    * Returns the error code of this ivld gl balance.
    *
    * @return the error code of this ivld gl balance
    */
    @Override
    public java.lang.String getErrorCode() {
        return _ivldGlBalance.getErrorCode();
    }

    /**
    * Sets the error code of this ivld gl balance.
    *
    * @param errorCode the error code of this ivld gl balance
    */
    @Override
    public void setErrorCode(java.lang.String errorCode) {
        _ivldGlBalance.setErrorCode(errorCode);
    }

    /**
    * Returns the gl balance intfid of this ivld gl balance.
    *
    * @return the gl balance intfid of this ivld gl balance
    */
    @Override
    public java.lang.String getGlBalanceIntfid() {
        return _ivldGlBalance.getGlBalanceIntfid();
    }

    /**
    * Sets the gl balance intfid of this ivld gl balance.
    *
    * @param glBalanceIntfid the gl balance intfid of this ivld gl balance
    */
    @Override
    public void setGlBalanceIntfid(java.lang.String glBalanceIntfid) {
        _ivldGlBalance.setGlBalanceIntfid(glBalanceIntfid);
    }

    /**
    * Returns the intf inserted date of this ivld gl balance.
    *
    * @return the intf inserted date of this ivld gl balance
    */
    @Override
    public java.util.Date getIntfInsertedDate() {
        return _ivldGlBalance.getIntfInsertedDate();
    }

    /**
    * Sets the intf inserted date of this ivld gl balance.
    *
    * @param intfInsertedDate the intf inserted date of this ivld gl balance
    */
    @Override
    public void setIntfInsertedDate(java.util.Date intfInsertedDate) {
        _ivldGlBalance.setIntfInsertedDate(intfInsertedDate);
    }

    /**
    * Returns the modified by of this ivld gl balance.
    *
    * @return the modified by of this ivld gl balance
    */
    @Override
    public java.lang.String getModifiedBy() {
        return _ivldGlBalance.getModifiedBy();
    }

    /**
    * Sets the modified by of this ivld gl balance.
    *
    * @param modifiedBy the modified by of this ivld gl balance
    */
    @Override
    public void setModifiedBy(java.lang.String modifiedBy) {
        _ivldGlBalance.setModifiedBy(modifiedBy);
    }

    /**
    * Returns the reprocessed flag of this ivld gl balance.
    *
    * @return the reprocessed flag of this ivld gl balance
    */
    @Override
    public java.lang.String getReprocessedFlag() {
        return _ivldGlBalance.getReprocessedFlag();
    }

    /**
    * Sets the reprocessed flag of this ivld gl balance.
    *
    * @param reprocessedFlag the reprocessed flag of this ivld gl balance
    */
    @Override
    public void setReprocessedFlag(java.lang.String reprocessedFlag) {
        _ivldGlBalance.setReprocessedFlag(reprocessedFlag);
    }

    @Override
    public boolean isNew() {
        return _ivldGlBalance.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _ivldGlBalance.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _ivldGlBalance.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _ivldGlBalance.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _ivldGlBalance.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _ivldGlBalance.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _ivldGlBalance.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _ivldGlBalance.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _ivldGlBalance.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _ivldGlBalance.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _ivldGlBalance.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new IvldGlBalanceWrapper((IvldGlBalance) _ivldGlBalance.clone());
    }

    @Override
    public int compareTo(IvldGlBalance ivldGlBalance) {
        return _ivldGlBalance.compareTo(ivldGlBalance);
    }

    @Override
    public int hashCode() {
        return _ivldGlBalance.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<IvldGlBalance> toCacheModel() {
        return _ivldGlBalance.toCacheModel();
    }

    @Override
    public IvldGlBalance toEscapedModel() {
        return new IvldGlBalanceWrapper(_ivldGlBalance.toEscapedModel());
    }

    @Override
    public IvldGlBalance toUnescapedModel() {
        return new IvldGlBalanceWrapper(_ivldGlBalance.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _ivldGlBalance.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _ivldGlBalance.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _ivldGlBalance.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof IvldGlBalanceWrapper)) {
            return false;
        }

        IvldGlBalanceWrapper ivldGlBalanceWrapper = (IvldGlBalanceWrapper) obj;

        if (Validator.equals(_ivldGlBalance, ivldGlBalanceWrapper._ivldGlBalance)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public IvldGlBalance getWrappedIvldGlBalance() {
        return _ivldGlBalance;
    }

    @Override
    public IvldGlBalance getWrappedModel() {
        return _ivldGlBalance;
    }

    @Override
    public void resetOriginalValues() {
        _ivldGlBalance.resetOriginalValues();
    }
}
