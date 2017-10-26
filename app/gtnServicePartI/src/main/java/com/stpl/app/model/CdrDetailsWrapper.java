package com.stpl.app.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link CdrDetails}.
 * </p>
 *
 * @author
 * @see CdrDetails
 * @generated
 */
public class CdrDetailsWrapper implements CdrDetails, ModelWrapper<CdrDetails> {
    private CdrDetails _cdrDetails;

    public CdrDetailsWrapper(CdrDetails cdrDetails) {
        _cdrDetails = cdrDetails;
    }

    @Override
    public Class<?> getModelClass() {
        return CdrDetails.class;
    }

    @Override
    public String getModelClassName() {
        return CdrDetails.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("createdBy", getCreatedBy());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("lineType", getLineType());
        attributes.put("keyword", getKeyword());
        attributes.put("itemGroupMsAssociation", getItemGroupMsAssociation());
        attributes.put("value", getValue());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("logicalOperator", getLogicalOperator());
        attributes.put("operator", getOperator());
        attributes.put("cdrDetailsSid", getCdrDetailsSid());
        attributes.put("cdrModelSid", getCdrModelSid());
        attributes.put("comparison", getComparison());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Integer createdBy = (Integer) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        Integer modifiedBy = (Integer) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        String lineType = (String) attributes.get("lineType");

        if (lineType != null) {
            setLineType(lineType);
        }

        String keyword = (String) attributes.get("keyword");

        if (keyword != null) {
            setKeyword(keyword);
        }

        String itemGroupMsAssociation = (String) attributes.get(
                "itemGroupMsAssociation");

        if (itemGroupMsAssociation != null) {
            setItemGroupMsAssociation(itemGroupMsAssociation);
        }

        Double value = (Double) attributes.get("value");

        if (value != null) {
            setValue(value);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        String logicalOperator = (String) attributes.get("logicalOperator");

        if (logicalOperator != null) {
            setLogicalOperator(logicalOperator);
        }

        String operator = (String) attributes.get("operator");

        if (operator != null) {
            setOperator(operator);
        }

        Integer cdrDetailsSid = (Integer) attributes.get("cdrDetailsSid");

        if (cdrDetailsSid != null) {
            setCdrDetailsSid(cdrDetailsSid);
        }

        Integer cdrModelSid = (Integer) attributes.get("cdrModelSid");

        if (cdrModelSid != null) {
            setCdrModelSid(cdrModelSid);
        }

        String comparison = (String) attributes.get("comparison");

        if (comparison != null) {
            setComparison(comparison);
        }
    }

    /**
    * Returns the primary key of this cdr details.
    *
    * @return the primary key of this cdr details
    */
    @Override
    public int getPrimaryKey() {
        return _cdrDetails.getPrimaryKey();
    }

    /**
    * Sets the primary key of this cdr details.
    *
    * @param primaryKey the primary key of this cdr details
    */
    @Override
    public void setPrimaryKey(int primaryKey) {
        _cdrDetails.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the created by of this cdr details.
    *
    * @return the created by of this cdr details
    */
    @Override
    public int getCreatedBy() {
        return _cdrDetails.getCreatedBy();
    }

    /**
    * Sets the created by of this cdr details.
    *
    * @param createdBy the created by of this cdr details
    */
    @Override
    public void setCreatedBy(int createdBy) {
        _cdrDetails.setCreatedBy(createdBy);
    }

    /**
    * Returns the modified by of this cdr details.
    *
    * @return the modified by of this cdr details
    */
    @Override
    public int getModifiedBy() {
        return _cdrDetails.getModifiedBy();
    }

    /**
    * Sets the modified by of this cdr details.
    *
    * @param modifiedBy the modified by of this cdr details
    */
    @Override
    public void setModifiedBy(int modifiedBy) {
        _cdrDetails.setModifiedBy(modifiedBy);
    }

    /**
    * Returns the created date of this cdr details.
    *
    * @return the created date of this cdr details
    */
    @Override
    public java.util.Date getCreatedDate() {
        return _cdrDetails.getCreatedDate();
    }

    /**
    * Sets the created date of this cdr details.
    *
    * @param createdDate the created date of this cdr details
    */
    @Override
    public void setCreatedDate(java.util.Date createdDate) {
        _cdrDetails.setCreatedDate(createdDate);
    }

    /**
    * Returns the line type of this cdr details.
    *
    * @return the line type of this cdr details
    */
    @Override
    public java.lang.String getLineType() {
        return _cdrDetails.getLineType();
    }

    /**
    * Sets the line type of this cdr details.
    *
    * @param lineType the line type of this cdr details
    */
    @Override
    public void setLineType(java.lang.String lineType) {
        _cdrDetails.setLineType(lineType);
    }

    /**
    * Returns the keyword of this cdr details.
    *
    * @return the keyword of this cdr details
    */
    @Override
    public java.lang.String getKeyword() {
        return _cdrDetails.getKeyword();
    }

    /**
    * Sets the keyword of this cdr details.
    *
    * @param keyword the keyword of this cdr details
    */
    @Override
    public void setKeyword(java.lang.String keyword) {
        _cdrDetails.setKeyword(keyword);
    }

    /**
    * Returns the item group ms association of this cdr details.
    *
    * @return the item group ms association of this cdr details
    */
    @Override
    public java.lang.String getItemGroupMsAssociation() {
        return _cdrDetails.getItemGroupMsAssociation();
    }

    /**
    * Sets the item group ms association of this cdr details.
    *
    * @param itemGroupMsAssociation the item group ms association of this cdr details
    */
    @Override
    public void setItemGroupMsAssociation(
        java.lang.String itemGroupMsAssociation) {
        _cdrDetails.setItemGroupMsAssociation(itemGroupMsAssociation);
    }

    /**
    * Returns the value of this cdr details.
    *
    * @return the value of this cdr details
    */
    @Override
    public double getValue() {
        return _cdrDetails.getValue();
    }

    /**
    * Sets the value of this cdr details.
    *
    * @param value the value of this cdr details
    */
    @Override
    public void setValue(double value) {
        _cdrDetails.setValue(value);
    }

    /**
    * Returns the modified date of this cdr details.
    *
    * @return the modified date of this cdr details
    */
    @Override
    public java.util.Date getModifiedDate() {
        return _cdrDetails.getModifiedDate();
    }

    /**
    * Sets the modified date of this cdr details.
    *
    * @param modifiedDate the modified date of this cdr details
    */
    @Override
    public void setModifiedDate(java.util.Date modifiedDate) {
        _cdrDetails.setModifiedDate(modifiedDate);
    }

    /**
    * Returns the logical operator of this cdr details.
    *
    * @return the logical operator of this cdr details
    */
    @Override
    public java.lang.String getLogicalOperator() {
        return _cdrDetails.getLogicalOperator();
    }

    /**
    * Sets the logical operator of this cdr details.
    *
    * @param logicalOperator the logical operator of this cdr details
    */
    @Override
    public void setLogicalOperator(java.lang.String logicalOperator) {
        _cdrDetails.setLogicalOperator(logicalOperator);
    }

    /**
    * Returns the operator of this cdr details.
    *
    * @return the operator of this cdr details
    */
    @Override
    public java.lang.String getOperator() {
        return _cdrDetails.getOperator();
    }

    /**
    * Sets the operator of this cdr details.
    *
    * @param operator the operator of this cdr details
    */
    @Override
    public void setOperator(java.lang.String operator) {
        _cdrDetails.setOperator(operator);
    }

    /**
    * Returns the cdr details sid of this cdr details.
    *
    * @return the cdr details sid of this cdr details
    */
    @Override
    public int getCdrDetailsSid() {
        return _cdrDetails.getCdrDetailsSid();
    }

    /**
    * Sets the cdr details sid of this cdr details.
    *
    * @param cdrDetailsSid the cdr details sid of this cdr details
    */
    @Override
    public void setCdrDetailsSid(int cdrDetailsSid) {
        _cdrDetails.setCdrDetailsSid(cdrDetailsSid);
    }

    /**
    * Returns the cdr model sid of this cdr details.
    *
    * @return the cdr model sid of this cdr details
    */
    @Override
    public int getCdrModelSid() {
        return _cdrDetails.getCdrModelSid();
    }

    /**
    * Sets the cdr model sid of this cdr details.
    *
    * @param cdrModelSid the cdr model sid of this cdr details
    */
    @Override
    public void setCdrModelSid(int cdrModelSid) {
        _cdrDetails.setCdrModelSid(cdrModelSid);
    }

    /**
    * Returns the comparison of this cdr details.
    *
    * @return the comparison of this cdr details
    */
    @Override
    public java.lang.String getComparison() {
        return _cdrDetails.getComparison();
    }

    /**
    * Sets the comparison of this cdr details.
    *
    * @param comparison the comparison of this cdr details
    */
    @Override
    public void setComparison(java.lang.String comparison) {
        _cdrDetails.setComparison(comparison);
    }

    @Override
    public boolean isNew() {
        return _cdrDetails.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _cdrDetails.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _cdrDetails.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _cdrDetails.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _cdrDetails.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _cdrDetails.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _cdrDetails.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _cdrDetails.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _cdrDetails.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _cdrDetails.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _cdrDetails.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new CdrDetailsWrapper((CdrDetails) _cdrDetails.clone());
    }

    @Override
    public int compareTo(CdrDetails cdrDetails) {
        return _cdrDetails.compareTo(cdrDetails);
    }

    @Override
    public int hashCode() {
        return _cdrDetails.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<CdrDetails> toCacheModel() {
        return _cdrDetails.toCacheModel();
    }

    @Override
    public CdrDetails toEscapedModel() {
        return new CdrDetailsWrapper(_cdrDetails.toEscapedModel());
    }

    @Override
    public CdrDetails toUnescapedModel() {
        return new CdrDetailsWrapper(_cdrDetails.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _cdrDetails.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _cdrDetails.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _cdrDetails.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof CdrDetailsWrapper)) {
            return false;
        }

        CdrDetailsWrapper cdrDetailsWrapper = (CdrDetailsWrapper) obj;

        if (Validator.equals(_cdrDetails, cdrDetailsWrapper._cdrDetails)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public CdrDetails getWrappedCdrDetails() {
        return _cdrDetails;
    }

    @Override
    public CdrDetails getWrappedModel() {
        return _cdrDetails;
    }

    @Override
    public void resetOriginalValues() {
        _cdrDetails.resetOriginalValues();
    }
}
