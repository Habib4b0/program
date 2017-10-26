package com.stpl.app.parttwo.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link CffApprovalDetails}.
 * </p>
 *
 * @author
 * @see CffApprovalDetails
 * @generated
 */
public class CffApprovalDetailsWrapper implements CffApprovalDetails,
    ModelWrapper<CffApprovalDetails> {
    private CffApprovalDetails _cffApprovalDetails;

    public CffApprovalDetailsWrapper(CffApprovalDetails cffApprovalDetails) {
        _cffApprovalDetails = cffApprovalDetails;
    }

    @Override
    public Class<?> getModelClass() {
        return CffApprovalDetails.class;
    }

    @Override
    public String getModelClassName() {
        return CffApprovalDetails.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("approvalSequence", getApprovalSequence());
        attributes.put("approvedDate", getApprovedDate());
        attributes.put("approvedBy", getApprovedBy());
        attributes.put("approvalStatus", getApprovalStatus());
        attributes.put("cffMasterSid", getCffMasterSid());
        attributes.put("inboundStatus", getInboundStatus());
        attributes.put("cffApprovalDetailsSid", getCffApprovalDetailsSid());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Integer approvalSequence = (Integer) attributes.get("approvalSequence");

        if (approvalSequence != null) {
            setApprovalSequence(approvalSequence);
        }

        Date approvedDate = (Date) attributes.get("approvedDate");

        if (approvedDate != null) {
            setApprovedDate(approvedDate);
        }

        Integer approvedBy = (Integer) attributes.get("approvedBy");

        if (approvedBy != null) {
            setApprovedBy(approvedBy);
        }

        Integer approvalStatus = (Integer) attributes.get("approvalStatus");

        if (approvalStatus != null) {
            setApprovalStatus(approvalStatus);
        }

        Integer cffMasterSid = (Integer) attributes.get("cffMasterSid");

        if (cffMasterSid != null) {
            setCffMasterSid(cffMasterSid);
        }

        String inboundStatus = (String) attributes.get("inboundStatus");

        if (inboundStatus != null) {
            setInboundStatus(inboundStatus);
        }

        Integer cffApprovalDetailsSid = (Integer) attributes.get(
                "cffApprovalDetailsSid");

        if (cffApprovalDetailsSid != null) {
            setCffApprovalDetailsSid(cffApprovalDetailsSid);
        }
    }

    /**
    * Returns the primary key of this cff approval details.
    *
    * @return the primary key of this cff approval details
    */
    @Override
    public int getPrimaryKey() {
        return _cffApprovalDetails.getPrimaryKey();
    }

    /**
    * Sets the primary key of this cff approval details.
    *
    * @param primaryKey the primary key of this cff approval details
    */
    @Override
    public void setPrimaryKey(int primaryKey) {
        _cffApprovalDetails.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the approval sequence of this cff approval details.
    *
    * @return the approval sequence of this cff approval details
    */
    @Override
    public int getApprovalSequence() {
        return _cffApprovalDetails.getApprovalSequence();
    }

    /**
    * Sets the approval sequence of this cff approval details.
    *
    * @param approvalSequence the approval sequence of this cff approval details
    */
    @Override
    public void setApprovalSequence(int approvalSequence) {
        _cffApprovalDetails.setApprovalSequence(approvalSequence);
    }

    /**
    * Returns the approved date of this cff approval details.
    *
    * @return the approved date of this cff approval details
    */
    @Override
    public java.util.Date getApprovedDate() {
        return _cffApprovalDetails.getApprovedDate();
    }

    /**
    * Sets the approved date of this cff approval details.
    *
    * @param approvedDate the approved date of this cff approval details
    */
    @Override
    public void setApprovedDate(java.util.Date approvedDate) {
        _cffApprovalDetails.setApprovedDate(approvedDate);
    }

    /**
    * Returns the approved by of this cff approval details.
    *
    * @return the approved by of this cff approval details
    */
    @Override
    public int getApprovedBy() {
        return _cffApprovalDetails.getApprovedBy();
    }

    /**
    * Sets the approved by of this cff approval details.
    *
    * @param approvedBy the approved by of this cff approval details
    */
    @Override
    public void setApprovedBy(int approvedBy) {
        _cffApprovalDetails.setApprovedBy(approvedBy);
    }

    /**
    * Returns the approval status of this cff approval details.
    *
    * @return the approval status of this cff approval details
    */
    @Override
    public int getApprovalStatus() {
        return _cffApprovalDetails.getApprovalStatus();
    }

    /**
    * Sets the approval status of this cff approval details.
    *
    * @param approvalStatus the approval status of this cff approval details
    */
    @Override
    public void setApprovalStatus(int approvalStatus) {
        _cffApprovalDetails.setApprovalStatus(approvalStatus);
    }

    /**
    * Returns the cff master sid of this cff approval details.
    *
    * @return the cff master sid of this cff approval details
    */
    @Override
    public int getCffMasterSid() {
        return _cffApprovalDetails.getCffMasterSid();
    }

    /**
    * Sets the cff master sid of this cff approval details.
    *
    * @param cffMasterSid the cff master sid of this cff approval details
    */
    @Override
    public void setCffMasterSid(int cffMasterSid) {
        _cffApprovalDetails.setCffMasterSid(cffMasterSid);
    }

    /**
    * Returns the inbound status of this cff approval details.
    *
    * @return the inbound status of this cff approval details
    */
    @Override
    public java.lang.String getInboundStatus() {
        return _cffApprovalDetails.getInboundStatus();
    }

    /**
    * Sets the inbound status of this cff approval details.
    *
    * @param inboundStatus the inbound status of this cff approval details
    */
    @Override
    public void setInboundStatus(java.lang.String inboundStatus) {
        _cffApprovalDetails.setInboundStatus(inboundStatus);
    }

    /**
    * Returns the cff approval details sid of this cff approval details.
    *
    * @return the cff approval details sid of this cff approval details
    */
    @Override
    public int getCffApprovalDetailsSid() {
        return _cffApprovalDetails.getCffApprovalDetailsSid();
    }

    /**
    * Sets the cff approval details sid of this cff approval details.
    *
    * @param cffApprovalDetailsSid the cff approval details sid of this cff approval details
    */
    @Override
    public void setCffApprovalDetailsSid(int cffApprovalDetailsSid) {
        _cffApprovalDetails.setCffApprovalDetailsSid(cffApprovalDetailsSid);
    }

    @Override
    public boolean isNew() {
        return _cffApprovalDetails.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _cffApprovalDetails.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _cffApprovalDetails.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _cffApprovalDetails.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _cffApprovalDetails.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _cffApprovalDetails.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _cffApprovalDetails.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _cffApprovalDetails.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _cffApprovalDetails.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _cffApprovalDetails.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _cffApprovalDetails.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new CffApprovalDetailsWrapper((CffApprovalDetails) _cffApprovalDetails.clone());
    }

    @Override
    public int compareTo(CffApprovalDetails cffApprovalDetails) {
        return _cffApprovalDetails.compareTo(cffApprovalDetails);
    }

    @Override
    public int hashCode() {
        return _cffApprovalDetails.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<CffApprovalDetails> toCacheModel() {
        return _cffApprovalDetails.toCacheModel();
    }

    @Override
    public CffApprovalDetails toEscapedModel() {
        return new CffApprovalDetailsWrapper(_cffApprovalDetails.toEscapedModel());
    }

    @Override
    public CffApprovalDetails toUnescapedModel() {
        return new CffApprovalDetailsWrapper(_cffApprovalDetails.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _cffApprovalDetails.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _cffApprovalDetails.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _cffApprovalDetails.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof CffApprovalDetailsWrapper)) {
            return false;
        }

        CffApprovalDetailsWrapper cffApprovalDetailsWrapper = (CffApprovalDetailsWrapper) obj;

        if (Validator.equals(_cffApprovalDetails,
                    cffApprovalDetailsWrapper._cffApprovalDetails)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public CffApprovalDetails getWrappedCffApprovalDetails() {
        return _cffApprovalDetails;
    }

    @Override
    public CffApprovalDetails getWrappedModel() {
        return _cffApprovalDetails;
    }

    @Override
    public void resetOriginalValues() {
        _cffApprovalDetails.resetOriginalValues();
    }
}
