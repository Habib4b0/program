package com.stpl.app.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link IvldFormulaDetails}.
 * </p>
 *
 * @author
 * @see IvldFormulaDetails
 * @generated
 */
public class IvldFormulaDetailsWrapper implements IvldFormulaDetails,
    ModelWrapper<IvldFormulaDetails> {
    private IvldFormulaDetails _ivldFormulaDetails;

    public IvldFormulaDetailsWrapper(IvldFormulaDetails ivldFormulaDetails) {
        _ivldFormulaDetails = ivldFormulaDetails;
    }

    @Override
    public Class<?> getModelClass() {
        return IvldFormulaDetails.class;
    }

    @Override
    public String getModelClassName() {
        return IvldFormulaDetails.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("endDate", getEndDate());
        attributes.put("rebatePercent1", getRebatePercent1());
        attributes.put("itemId", getItemId());
        attributes.put("rebatePercent2", getRebatePercent2());
        attributes.put("formulaDesc", getFormulaDesc());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("rebatePercent3", getRebatePercent3());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("source", getSource());
        attributes.put("addChgDelIndicator", getAddChgDelIndicator());
        attributes.put("errorCode", getErrorCode());
        attributes.put("formulaId", getFormulaId());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("intfInsertedDate", getIntfInsertedDate());
        attributes.put("reprocessedFlag", getReprocessedFlag());
        attributes.put("formulaDetailsIntfid", getFormulaDetailsIntfid());
        attributes.put("reasonForFailure", getReasonForFailure());
        attributes.put("contractPrice1", getContractPrice1());
        attributes.put("companyId", getCompanyId());
        attributes.put("contractPrice2", getContractPrice2());
        attributes.put("formulaNo", getFormulaNo());
        attributes.put("startDate", getStartDate());
        attributes.put("batchId", getBatchId());
        attributes.put("errorField", getErrorField());
        attributes.put("contractPrice3", getContractPrice3());
        attributes.put("ivldFormulaDetailsSid", getIvldFormulaDetailsSid());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        String endDate = (String) attributes.get("endDate");

        if (endDate != null) {
            setEndDate(endDate);
        }

        String rebatePercent1 = (String) attributes.get("rebatePercent1");

        if (rebatePercent1 != null) {
            setRebatePercent1(rebatePercent1);
        }

        String itemId = (String) attributes.get("itemId");

        if (itemId != null) {
            setItemId(itemId);
        }

        String rebatePercent2 = (String) attributes.get("rebatePercent2");

        if (rebatePercent2 != null) {
            setRebatePercent2(rebatePercent2);
        }

        String formulaDesc = (String) attributes.get("formulaDesc");

        if (formulaDesc != null) {
            setFormulaDesc(formulaDesc);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        String rebatePercent3 = (String) attributes.get("rebatePercent3");

        if (rebatePercent3 != null) {
            setRebatePercent3(rebatePercent3);
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

        String errorCode = (String) attributes.get("errorCode");

        if (errorCode != null) {
            setErrorCode(errorCode);
        }

        String formulaId = (String) attributes.get("formulaId");

        if (formulaId != null) {
            setFormulaId(formulaId);
        }

        String modifiedBy = (String) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        Date intfInsertedDate = (Date) attributes.get("intfInsertedDate");

        if (intfInsertedDate != null) {
            setIntfInsertedDate(intfInsertedDate);
        }

        String reprocessedFlag = (String) attributes.get("reprocessedFlag");

        if (reprocessedFlag != null) {
            setReprocessedFlag(reprocessedFlag);
        }

        String formulaDetailsIntfid = (String) attributes.get(
                "formulaDetailsIntfid");

        if (formulaDetailsIntfid != null) {
            setFormulaDetailsIntfid(formulaDetailsIntfid);
        }

        String reasonForFailure = (String) attributes.get("reasonForFailure");

        if (reasonForFailure != null) {
            setReasonForFailure(reasonForFailure);
        }

        String contractPrice1 = (String) attributes.get("contractPrice1");

        if (contractPrice1 != null) {
            setContractPrice1(contractPrice1);
        }

        String companyId = (String) attributes.get("companyId");

        if (companyId != null) {
            setCompanyId(companyId);
        }

        String contractPrice2 = (String) attributes.get("contractPrice2");

        if (contractPrice2 != null) {
            setContractPrice2(contractPrice2);
        }

        String formulaNo = (String) attributes.get("formulaNo");

        if (formulaNo != null) {
            setFormulaNo(formulaNo);
        }

        String startDate = (String) attributes.get("startDate");

        if (startDate != null) {
            setStartDate(startDate);
        }

        String batchId = (String) attributes.get("batchId");

        if (batchId != null) {
            setBatchId(batchId);
        }

        String errorField = (String) attributes.get("errorField");

        if (errorField != null) {
            setErrorField(errorField);
        }

        String contractPrice3 = (String) attributes.get("contractPrice3");

        if (contractPrice3 != null) {
            setContractPrice3(contractPrice3);
        }

        Integer ivldFormulaDetailsSid = (Integer) attributes.get(
                "ivldFormulaDetailsSid");

        if (ivldFormulaDetailsSid != null) {
            setIvldFormulaDetailsSid(ivldFormulaDetailsSid);
        }
    }

    /**
    * Returns the primary key of this ivld formula details.
    *
    * @return the primary key of this ivld formula details
    */
    @Override
    public int getPrimaryKey() {
        return _ivldFormulaDetails.getPrimaryKey();
    }

    /**
    * Sets the primary key of this ivld formula details.
    *
    * @param primaryKey the primary key of this ivld formula details
    */
    @Override
    public void setPrimaryKey(int primaryKey) {
        _ivldFormulaDetails.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the end date of this ivld formula details.
    *
    * @return the end date of this ivld formula details
    */
    @Override
    public java.lang.String getEndDate() {
        return _ivldFormulaDetails.getEndDate();
    }

    /**
    * Sets the end date of this ivld formula details.
    *
    * @param endDate the end date of this ivld formula details
    */
    @Override
    public void setEndDate(java.lang.String endDate) {
        _ivldFormulaDetails.setEndDate(endDate);
    }

    /**
    * Returns the rebate percent1 of this ivld formula details.
    *
    * @return the rebate percent1 of this ivld formula details
    */
    @Override
    public java.lang.String getRebatePercent1() {
        return _ivldFormulaDetails.getRebatePercent1();
    }

    /**
    * Sets the rebate percent1 of this ivld formula details.
    *
    * @param rebatePercent1 the rebate percent1 of this ivld formula details
    */
    @Override
    public void setRebatePercent1(java.lang.String rebatePercent1) {
        _ivldFormulaDetails.setRebatePercent1(rebatePercent1);
    }

    /**
    * Returns the item ID of this ivld formula details.
    *
    * @return the item ID of this ivld formula details
    */
    @Override
    public java.lang.String getItemId() {
        return _ivldFormulaDetails.getItemId();
    }

    /**
    * Sets the item ID of this ivld formula details.
    *
    * @param itemId the item ID of this ivld formula details
    */
    @Override
    public void setItemId(java.lang.String itemId) {
        _ivldFormulaDetails.setItemId(itemId);
    }

    /**
    * Returns the rebate percent2 of this ivld formula details.
    *
    * @return the rebate percent2 of this ivld formula details
    */
    @Override
    public java.lang.String getRebatePercent2() {
        return _ivldFormulaDetails.getRebatePercent2();
    }

    /**
    * Sets the rebate percent2 of this ivld formula details.
    *
    * @param rebatePercent2 the rebate percent2 of this ivld formula details
    */
    @Override
    public void setRebatePercent2(java.lang.String rebatePercent2) {
        _ivldFormulaDetails.setRebatePercent2(rebatePercent2);
    }

    /**
    * Returns the formula desc of this ivld formula details.
    *
    * @return the formula desc of this ivld formula details
    */
    @Override
    public java.lang.String getFormulaDesc() {
        return _ivldFormulaDetails.getFormulaDesc();
    }

    /**
    * Sets the formula desc of this ivld formula details.
    *
    * @param formulaDesc the formula desc of this ivld formula details
    */
    @Override
    public void setFormulaDesc(java.lang.String formulaDesc) {
        _ivldFormulaDetails.setFormulaDesc(formulaDesc);
    }

    /**
    * Returns the modified date of this ivld formula details.
    *
    * @return the modified date of this ivld formula details
    */
    @Override
    public java.util.Date getModifiedDate() {
        return _ivldFormulaDetails.getModifiedDate();
    }

    /**
    * Sets the modified date of this ivld formula details.
    *
    * @param modifiedDate the modified date of this ivld formula details
    */
    @Override
    public void setModifiedDate(java.util.Date modifiedDate) {
        _ivldFormulaDetails.setModifiedDate(modifiedDate);
    }

    /**
    * Returns the rebate percent3 of this ivld formula details.
    *
    * @return the rebate percent3 of this ivld formula details
    */
    @Override
    public java.lang.String getRebatePercent3() {
        return _ivldFormulaDetails.getRebatePercent3();
    }

    /**
    * Sets the rebate percent3 of this ivld formula details.
    *
    * @param rebatePercent3 the rebate percent3 of this ivld formula details
    */
    @Override
    public void setRebatePercent3(java.lang.String rebatePercent3) {
        _ivldFormulaDetails.setRebatePercent3(rebatePercent3);
    }

    /**
    * Returns the created by of this ivld formula details.
    *
    * @return the created by of this ivld formula details
    */
    @Override
    public java.lang.String getCreatedBy() {
        return _ivldFormulaDetails.getCreatedBy();
    }

    /**
    * Sets the created by of this ivld formula details.
    *
    * @param createdBy the created by of this ivld formula details
    */
    @Override
    public void setCreatedBy(java.lang.String createdBy) {
        _ivldFormulaDetails.setCreatedBy(createdBy);
    }

    /**
    * Returns the created date of this ivld formula details.
    *
    * @return the created date of this ivld formula details
    */
    @Override
    public java.util.Date getCreatedDate() {
        return _ivldFormulaDetails.getCreatedDate();
    }

    /**
    * Sets the created date of this ivld formula details.
    *
    * @param createdDate the created date of this ivld formula details
    */
    @Override
    public void setCreatedDate(java.util.Date createdDate) {
        _ivldFormulaDetails.setCreatedDate(createdDate);
    }

    /**
    * Returns the source of this ivld formula details.
    *
    * @return the source of this ivld formula details
    */
    @Override
    public java.lang.String getSource() {
        return _ivldFormulaDetails.getSource();
    }

    /**
    * Sets the source of this ivld formula details.
    *
    * @param source the source of this ivld formula details
    */
    @Override
    public void setSource(java.lang.String source) {
        _ivldFormulaDetails.setSource(source);
    }

    /**
    * Returns the add chg del indicator of this ivld formula details.
    *
    * @return the add chg del indicator of this ivld formula details
    */
    @Override
    public java.lang.String getAddChgDelIndicator() {
        return _ivldFormulaDetails.getAddChgDelIndicator();
    }

    /**
    * Sets the add chg del indicator of this ivld formula details.
    *
    * @param addChgDelIndicator the add chg del indicator of this ivld formula details
    */
    @Override
    public void setAddChgDelIndicator(java.lang.String addChgDelIndicator) {
        _ivldFormulaDetails.setAddChgDelIndicator(addChgDelIndicator);
    }

    /**
    * Returns the error code of this ivld formula details.
    *
    * @return the error code of this ivld formula details
    */
    @Override
    public java.lang.String getErrorCode() {
        return _ivldFormulaDetails.getErrorCode();
    }

    /**
    * Sets the error code of this ivld formula details.
    *
    * @param errorCode the error code of this ivld formula details
    */
    @Override
    public void setErrorCode(java.lang.String errorCode) {
        _ivldFormulaDetails.setErrorCode(errorCode);
    }

    /**
    * Returns the formula ID of this ivld formula details.
    *
    * @return the formula ID of this ivld formula details
    */
    @Override
    public java.lang.String getFormulaId() {
        return _ivldFormulaDetails.getFormulaId();
    }

    /**
    * Sets the formula ID of this ivld formula details.
    *
    * @param formulaId the formula ID of this ivld formula details
    */
    @Override
    public void setFormulaId(java.lang.String formulaId) {
        _ivldFormulaDetails.setFormulaId(formulaId);
    }

    /**
    * Returns the modified by of this ivld formula details.
    *
    * @return the modified by of this ivld formula details
    */
    @Override
    public java.lang.String getModifiedBy() {
        return _ivldFormulaDetails.getModifiedBy();
    }

    /**
    * Sets the modified by of this ivld formula details.
    *
    * @param modifiedBy the modified by of this ivld formula details
    */
    @Override
    public void setModifiedBy(java.lang.String modifiedBy) {
        _ivldFormulaDetails.setModifiedBy(modifiedBy);
    }

    /**
    * Returns the intf inserted date of this ivld formula details.
    *
    * @return the intf inserted date of this ivld formula details
    */
    @Override
    public java.util.Date getIntfInsertedDate() {
        return _ivldFormulaDetails.getIntfInsertedDate();
    }

    /**
    * Sets the intf inserted date of this ivld formula details.
    *
    * @param intfInsertedDate the intf inserted date of this ivld formula details
    */
    @Override
    public void setIntfInsertedDate(java.util.Date intfInsertedDate) {
        _ivldFormulaDetails.setIntfInsertedDate(intfInsertedDate);
    }

    /**
    * Returns the reprocessed flag of this ivld formula details.
    *
    * @return the reprocessed flag of this ivld formula details
    */
    @Override
    public java.lang.String getReprocessedFlag() {
        return _ivldFormulaDetails.getReprocessedFlag();
    }

    /**
    * Sets the reprocessed flag of this ivld formula details.
    *
    * @param reprocessedFlag the reprocessed flag of this ivld formula details
    */
    @Override
    public void setReprocessedFlag(java.lang.String reprocessedFlag) {
        _ivldFormulaDetails.setReprocessedFlag(reprocessedFlag);
    }

    /**
    * Returns the formula details intfid of this ivld formula details.
    *
    * @return the formula details intfid of this ivld formula details
    */
    @Override
    public java.lang.String getFormulaDetailsIntfid() {
        return _ivldFormulaDetails.getFormulaDetailsIntfid();
    }

    /**
    * Sets the formula details intfid of this ivld formula details.
    *
    * @param formulaDetailsIntfid the formula details intfid of this ivld formula details
    */
    @Override
    public void setFormulaDetailsIntfid(java.lang.String formulaDetailsIntfid) {
        _ivldFormulaDetails.setFormulaDetailsIntfid(formulaDetailsIntfid);
    }

    /**
    * Returns the reason for failure of this ivld formula details.
    *
    * @return the reason for failure of this ivld formula details
    */
    @Override
    public java.lang.String getReasonForFailure() {
        return _ivldFormulaDetails.getReasonForFailure();
    }

    /**
    * Sets the reason for failure of this ivld formula details.
    *
    * @param reasonForFailure the reason for failure of this ivld formula details
    */
    @Override
    public void setReasonForFailure(java.lang.String reasonForFailure) {
        _ivldFormulaDetails.setReasonForFailure(reasonForFailure);
    }

    /**
    * Returns the contract price1 of this ivld formula details.
    *
    * @return the contract price1 of this ivld formula details
    */
    @Override
    public java.lang.String getContractPrice1() {
        return _ivldFormulaDetails.getContractPrice1();
    }

    /**
    * Sets the contract price1 of this ivld formula details.
    *
    * @param contractPrice1 the contract price1 of this ivld formula details
    */
    @Override
    public void setContractPrice1(java.lang.String contractPrice1) {
        _ivldFormulaDetails.setContractPrice1(contractPrice1);
    }

    /**
    * Returns the company ID of this ivld formula details.
    *
    * @return the company ID of this ivld formula details
    */
    @Override
    public java.lang.String getCompanyId() {
        return _ivldFormulaDetails.getCompanyId();
    }

    /**
    * Sets the company ID of this ivld formula details.
    *
    * @param companyId the company ID of this ivld formula details
    */
    @Override
    public void setCompanyId(java.lang.String companyId) {
        _ivldFormulaDetails.setCompanyId(companyId);
    }

    /**
    * Returns the contract price2 of this ivld formula details.
    *
    * @return the contract price2 of this ivld formula details
    */
    @Override
    public java.lang.String getContractPrice2() {
        return _ivldFormulaDetails.getContractPrice2();
    }

    /**
    * Sets the contract price2 of this ivld formula details.
    *
    * @param contractPrice2 the contract price2 of this ivld formula details
    */
    @Override
    public void setContractPrice2(java.lang.String contractPrice2) {
        _ivldFormulaDetails.setContractPrice2(contractPrice2);
    }

    /**
    * Returns the formula no of this ivld formula details.
    *
    * @return the formula no of this ivld formula details
    */
    @Override
    public java.lang.String getFormulaNo() {
        return _ivldFormulaDetails.getFormulaNo();
    }

    /**
    * Sets the formula no of this ivld formula details.
    *
    * @param formulaNo the formula no of this ivld formula details
    */
    @Override
    public void setFormulaNo(java.lang.String formulaNo) {
        _ivldFormulaDetails.setFormulaNo(formulaNo);
    }

    /**
    * Returns the start date of this ivld formula details.
    *
    * @return the start date of this ivld formula details
    */
    @Override
    public java.lang.String getStartDate() {
        return _ivldFormulaDetails.getStartDate();
    }

    /**
    * Sets the start date of this ivld formula details.
    *
    * @param startDate the start date of this ivld formula details
    */
    @Override
    public void setStartDate(java.lang.String startDate) {
        _ivldFormulaDetails.setStartDate(startDate);
    }

    /**
    * Returns the batch ID of this ivld formula details.
    *
    * @return the batch ID of this ivld formula details
    */
    @Override
    public java.lang.String getBatchId() {
        return _ivldFormulaDetails.getBatchId();
    }

    /**
    * Sets the batch ID of this ivld formula details.
    *
    * @param batchId the batch ID of this ivld formula details
    */
    @Override
    public void setBatchId(java.lang.String batchId) {
        _ivldFormulaDetails.setBatchId(batchId);
    }

    /**
    * Returns the error field of this ivld formula details.
    *
    * @return the error field of this ivld formula details
    */
    @Override
    public java.lang.String getErrorField() {
        return _ivldFormulaDetails.getErrorField();
    }

    /**
    * Sets the error field of this ivld formula details.
    *
    * @param errorField the error field of this ivld formula details
    */
    @Override
    public void setErrorField(java.lang.String errorField) {
        _ivldFormulaDetails.setErrorField(errorField);
    }

    /**
    * Returns the contract price3 of this ivld formula details.
    *
    * @return the contract price3 of this ivld formula details
    */
    @Override
    public java.lang.String getContractPrice3() {
        return _ivldFormulaDetails.getContractPrice3();
    }

    /**
    * Sets the contract price3 of this ivld formula details.
    *
    * @param contractPrice3 the contract price3 of this ivld formula details
    */
    @Override
    public void setContractPrice3(java.lang.String contractPrice3) {
        _ivldFormulaDetails.setContractPrice3(contractPrice3);
    }

    /**
    * Returns the ivld formula details sid of this ivld formula details.
    *
    * @return the ivld formula details sid of this ivld formula details
    */
    @Override
    public int getIvldFormulaDetailsSid() {
        return _ivldFormulaDetails.getIvldFormulaDetailsSid();
    }

    /**
    * Sets the ivld formula details sid of this ivld formula details.
    *
    * @param ivldFormulaDetailsSid the ivld formula details sid of this ivld formula details
    */
    @Override
    public void setIvldFormulaDetailsSid(int ivldFormulaDetailsSid) {
        _ivldFormulaDetails.setIvldFormulaDetailsSid(ivldFormulaDetailsSid);
    }

    @Override
    public boolean isNew() {
        return _ivldFormulaDetails.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _ivldFormulaDetails.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _ivldFormulaDetails.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _ivldFormulaDetails.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _ivldFormulaDetails.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _ivldFormulaDetails.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _ivldFormulaDetails.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _ivldFormulaDetails.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _ivldFormulaDetails.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _ivldFormulaDetails.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _ivldFormulaDetails.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new IvldFormulaDetailsWrapper((IvldFormulaDetails) _ivldFormulaDetails.clone());
    }

    @Override
    public int compareTo(IvldFormulaDetails ivldFormulaDetails) {
        return _ivldFormulaDetails.compareTo(ivldFormulaDetails);
    }

    @Override
    public int hashCode() {
        return _ivldFormulaDetails.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<IvldFormulaDetails> toCacheModel() {
        return _ivldFormulaDetails.toCacheModel();
    }

    @Override
    public IvldFormulaDetails toEscapedModel() {
        return new IvldFormulaDetailsWrapper(_ivldFormulaDetails.toEscapedModel());
    }

    @Override
    public IvldFormulaDetails toUnescapedModel() {
        return new IvldFormulaDetailsWrapper(_ivldFormulaDetails.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _ivldFormulaDetails.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _ivldFormulaDetails.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _ivldFormulaDetails.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof IvldFormulaDetailsWrapper)) {
            return false;
        }

        IvldFormulaDetailsWrapper ivldFormulaDetailsWrapper = (IvldFormulaDetailsWrapper) obj;

        if (Validator.equals(_ivldFormulaDetails,
                    ivldFormulaDetailsWrapper._ivldFormulaDetails)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public IvldFormulaDetails getWrappedIvldFormulaDetails() {
        return _ivldFormulaDetails;
    }

    @Override
    public IvldFormulaDetails getWrappedModel() {
        return _ivldFormulaDetails;
    }

    @Override
    public void resetOriginalValues() {
        _ivldFormulaDetails.resetOriginalValues();
    }
}
