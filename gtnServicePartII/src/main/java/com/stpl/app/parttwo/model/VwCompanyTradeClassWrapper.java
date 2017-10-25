package com.stpl.app.parttwo.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link VwCompanyTradeClass}.
 * </p>
 *
 * @author
 * @see VwCompanyTradeClass
 * @generated
 */
public class VwCompanyTradeClassWrapper implements VwCompanyTradeClass,
    ModelWrapper<VwCompanyTradeClass> {
    private VwCompanyTradeClass _vwCompanyTradeClass;

    public VwCompanyTradeClassWrapper(VwCompanyTradeClass vwCompanyTradeClass) {
        _vwCompanyTradeClass = vwCompanyTradeClass;
    }

    @Override
    public Class<?> getModelClass() {
        return VwCompanyTradeClass.class;
    }

    @Override
    public String getModelClassName() {
        return VwCompanyTradeClass.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("priorTradeClass", getPriorTradeClass());
        attributes.put("companyTradeClassSid", getCompanyTradeClassSid());
        attributes.put("companyId", getCompanyId());
        attributes.put("lastUpdatedDate", getLastUpdatedDate());
        attributes.put("priorTradeClassStartDate", getPriorTradeClassStartDate());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("tradeClassEndDate", getTradeClassEndDate());
        attributes.put("tradeClassStartDate", getTradeClassStartDate());
        attributes.put("source", getSource());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("companyTradeClass", getCompanyTradeClass());
        attributes.put("batchId", getBatchId());
        attributes.put("addChgDelIndicator", getAddChgDelIndicator());
        attributes.put("modifiedBy", getModifiedBy());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        String priorTradeClass = (String) attributes.get("priorTradeClass");

        if (priorTradeClass != null) {
            setPriorTradeClass(priorTradeClass);
        }

        Integer companyTradeClassSid = (Integer) attributes.get(
                "companyTradeClassSid");

        if (companyTradeClassSid != null) {
            setCompanyTradeClassSid(companyTradeClassSid);
        }

        String companyId = (String) attributes.get("companyId");

        if (companyId != null) {
            setCompanyId(companyId);
        }

        Date lastUpdatedDate = (Date) attributes.get("lastUpdatedDate");

        if (lastUpdatedDate != null) {
            setLastUpdatedDate(lastUpdatedDate);
        }

        Date priorTradeClassStartDate = (Date) attributes.get(
                "priorTradeClassStartDate");

        if (priorTradeClassStartDate != null) {
            setPriorTradeClassStartDate(priorTradeClassStartDate);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        Date tradeClassEndDate = (Date) attributes.get("tradeClassEndDate");

        if (tradeClassEndDate != null) {
            setTradeClassEndDate(tradeClassEndDate);
        }

        Date tradeClassStartDate = (Date) attributes.get("tradeClassStartDate");

        if (tradeClassStartDate != null) {
            setTradeClassStartDate(tradeClassStartDate);
        }

        String source = (String) attributes.get("source");

        if (source != null) {
            setSource(source);
        }

        String createdBy = (String) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        String companyTradeClass = (String) attributes.get("companyTradeClass");

        if (companyTradeClass != null) {
            setCompanyTradeClass(companyTradeClass);
        }

        String batchId = (String) attributes.get("batchId");

        if (batchId != null) {
            setBatchId(batchId);
        }

        String addChgDelIndicator = (String) attributes.get(
                "addChgDelIndicator");

        if (addChgDelIndicator != null) {
            setAddChgDelIndicator(addChgDelIndicator);
        }

        String modifiedBy = (String) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }
    }

    /**
    * Returns the primary key of this vw company trade class.
    *
    * @return the primary key of this vw company trade class
    */
    @Override
    public int getPrimaryKey() {
        return _vwCompanyTradeClass.getPrimaryKey();
    }

    /**
    * Sets the primary key of this vw company trade class.
    *
    * @param primaryKey the primary key of this vw company trade class
    */
    @Override
    public void setPrimaryKey(int primaryKey) {
        _vwCompanyTradeClass.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the prior trade class of this vw company trade class.
    *
    * @return the prior trade class of this vw company trade class
    */
    @Override
    public java.lang.String getPriorTradeClass() {
        return _vwCompanyTradeClass.getPriorTradeClass();
    }

    /**
    * Sets the prior trade class of this vw company trade class.
    *
    * @param priorTradeClass the prior trade class of this vw company trade class
    */
    @Override
    public void setPriorTradeClass(java.lang.String priorTradeClass) {
        _vwCompanyTradeClass.setPriorTradeClass(priorTradeClass);
    }

    /**
    * Returns the company trade class sid of this vw company trade class.
    *
    * @return the company trade class sid of this vw company trade class
    */
    @Override
    public int getCompanyTradeClassSid() {
        return _vwCompanyTradeClass.getCompanyTradeClassSid();
    }

    /**
    * Sets the company trade class sid of this vw company trade class.
    *
    * @param companyTradeClassSid the company trade class sid of this vw company trade class
    */
    @Override
    public void setCompanyTradeClassSid(int companyTradeClassSid) {
        _vwCompanyTradeClass.setCompanyTradeClassSid(companyTradeClassSid);
    }

    /**
    * Returns the company ID of this vw company trade class.
    *
    * @return the company ID of this vw company trade class
    */
    @Override
    public java.lang.String getCompanyId() {
        return _vwCompanyTradeClass.getCompanyId();
    }

    /**
    * Sets the company ID of this vw company trade class.
    *
    * @param companyId the company ID of this vw company trade class
    */
    @Override
    public void setCompanyId(java.lang.String companyId) {
        _vwCompanyTradeClass.setCompanyId(companyId);
    }

    /**
    * Returns the last updated date of this vw company trade class.
    *
    * @return the last updated date of this vw company trade class
    */
    @Override
    public java.util.Date getLastUpdatedDate() {
        return _vwCompanyTradeClass.getLastUpdatedDate();
    }

    /**
    * Sets the last updated date of this vw company trade class.
    *
    * @param lastUpdatedDate the last updated date of this vw company trade class
    */
    @Override
    public void setLastUpdatedDate(java.util.Date lastUpdatedDate) {
        _vwCompanyTradeClass.setLastUpdatedDate(lastUpdatedDate);
    }

    /**
    * Returns the prior trade class start date of this vw company trade class.
    *
    * @return the prior trade class start date of this vw company trade class
    */
    @Override
    public java.util.Date getPriorTradeClassStartDate() {
        return _vwCompanyTradeClass.getPriorTradeClassStartDate();
    }

    /**
    * Sets the prior trade class start date of this vw company trade class.
    *
    * @param priorTradeClassStartDate the prior trade class start date of this vw company trade class
    */
    @Override
    public void setPriorTradeClassStartDate(
        java.util.Date priorTradeClassStartDate) {
        _vwCompanyTradeClass.setPriorTradeClassStartDate(priorTradeClassStartDate);
    }

    /**
    * Returns the modified date of this vw company trade class.
    *
    * @return the modified date of this vw company trade class
    */
    @Override
    public java.util.Date getModifiedDate() {
        return _vwCompanyTradeClass.getModifiedDate();
    }

    /**
    * Sets the modified date of this vw company trade class.
    *
    * @param modifiedDate the modified date of this vw company trade class
    */
    @Override
    public void setModifiedDate(java.util.Date modifiedDate) {
        _vwCompanyTradeClass.setModifiedDate(modifiedDate);
    }

    /**
    * Returns the trade class end date of this vw company trade class.
    *
    * @return the trade class end date of this vw company trade class
    */
    @Override
    public java.util.Date getTradeClassEndDate() {
        return _vwCompanyTradeClass.getTradeClassEndDate();
    }

    /**
    * Sets the trade class end date of this vw company trade class.
    *
    * @param tradeClassEndDate the trade class end date of this vw company trade class
    */
    @Override
    public void setTradeClassEndDate(java.util.Date tradeClassEndDate) {
        _vwCompanyTradeClass.setTradeClassEndDate(tradeClassEndDate);
    }

    /**
    * Returns the trade class start date of this vw company trade class.
    *
    * @return the trade class start date of this vw company trade class
    */
    @Override
    public java.util.Date getTradeClassStartDate() {
        return _vwCompanyTradeClass.getTradeClassStartDate();
    }

    /**
    * Sets the trade class start date of this vw company trade class.
    *
    * @param tradeClassStartDate the trade class start date of this vw company trade class
    */
    @Override
    public void setTradeClassStartDate(java.util.Date tradeClassStartDate) {
        _vwCompanyTradeClass.setTradeClassStartDate(tradeClassStartDate);
    }

    /**
    * Returns the source of this vw company trade class.
    *
    * @return the source of this vw company trade class
    */
    @Override
    public java.lang.String getSource() {
        return _vwCompanyTradeClass.getSource();
    }

    /**
    * Sets the source of this vw company trade class.
    *
    * @param source the source of this vw company trade class
    */
    @Override
    public void setSource(java.lang.String source) {
        _vwCompanyTradeClass.setSource(source);
    }

    /**
    * Returns the created by of this vw company trade class.
    *
    * @return the created by of this vw company trade class
    */
    @Override
    public java.lang.String getCreatedBy() {
        return _vwCompanyTradeClass.getCreatedBy();
    }

    /**
    * Sets the created by of this vw company trade class.
    *
    * @param createdBy the created by of this vw company trade class
    */
    @Override
    public void setCreatedBy(java.lang.String createdBy) {
        _vwCompanyTradeClass.setCreatedBy(createdBy);
    }

    /**
    * Returns the created date of this vw company trade class.
    *
    * @return the created date of this vw company trade class
    */
    @Override
    public java.util.Date getCreatedDate() {
        return _vwCompanyTradeClass.getCreatedDate();
    }

    /**
    * Sets the created date of this vw company trade class.
    *
    * @param createdDate the created date of this vw company trade class
    */
    @Override
    public void setCreatedDate(java.util.Date createdDate) {
        _vwCompanyTradeClass.setCreatedDate(createdDate);
    }

    /**
    * Returns the company trade class of this vw company trade class.
    *
    * @return the company trade class of this vw company trade class
    */
    @Override
    public java.lang.String getCompanyTradeClass() {
        return _vwCompanyTradeClass.getCompanyTradeClass();
    }

    /**
    * Sets the company trade class of this vw company trade class.
    *
    * @param companyTradeClass the company trade class of this vw company trade class
    */
    @Override
    public void setCompanyTradeClass(java.lang.String companyTradeClass) {
        _vwCompanyTradeClass.setCompanyTradeClass(companyTradeClass);
    }

    /**
    * Returns the batch ID of this vw company trade class.
    *
    * @return the batch ID of this vw company trade class
    */
    @Override
    public java.lang.String getBatchId() {
        return _vwCompanyTradeClass.getBatchId();
    }

    /**
    * Sets the batch ID of this vw company trade class.
    *
    * @param batchId the batch ID of this vw company trade class
    */
    @Override
    public void setBatchId(java.lang.String batchId) {
        _vwCompanyTradeClass.setBatchId(batchId);
    }

    /**
    * Returns the add chg del indicator of this vw company trade class.
    *
    * @return the add chg del indicator of this vw company trade class
    */
    @Override
    public java.lang.String getAddChgDelIndicator() {
        return _vwCompanyTradeClass.getAddChgDelIndicator();
    }

    /**
    * Sets the add chg del indicator of this vw company trade class.
    *
    * @param addChgDelIndicator the add chg del indicator of this vw company trade class
    */
    @Override
    public void setAddChgDelIndicator(java.lang.String addChgDelIndicator) {
        _vwCompanyTradeClass.setAddChgDelIndicator(addChgDelIndicator);
    }

    /**
    * Returns the modified by of this vw company trade class.
    *
    * @return the modified by of this vw company trade class
    */
    @Override
    public java.lang.String getModifiedBy() {
        return _vwCompanyTradeClass.getModifiedBy();
    }

    /**
    * Sets the modified by of this vw company trade class.
    *
    * @param modifiedBy the modified by of this vw company trade class
    */
    @Override
    public void setModifiedBy(java.lang.String modifiedBy) {
        _vwCompanyTradeClass.setModifiedBy(modifiedBy);
    }

    @Override
    public boolean isNew() {
        return _vwCompanyTradeClass.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _vwCompanyTradeClass.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _vwCompanyTradeClass.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _vwCompanyTradeClass.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _vwCompanyTradeClass.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _vwCompanyTradeClass.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _vwCompanyTradeClass.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _vwCompanyTradeClass.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _vwCompanyTradeClass.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _vwCompanyTradeClass.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _vwCompanyTradeClass.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new VwCompanyTradeClassWrapper((VwCompanyTradeClass) _vwCompanyTradeClass.clone());
    }

    @Override
    public int compareTo(VwCompanyTradeClass vwCompanyTradeClass) {
        return _vwCompanyTradeClass.compareTo(vwCompanyTradeClass);
    }

    @Override
    public int hashCode() {
        return _vwCompanyTradeClass.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<VwCompanyTradeClass> toCacheModel() {
        return _vwCompanyTradeClass.toCacheModel();
    }

    @Override
    public VwCompanyTradeClass toEscapedModel() {
        return new VwCompanyTradeClassWrapper(_vwCompanyTradeClass.toEscapedModel());
    }

    @Override
    public VwCompanyTradeClass toUnescapedModel() {
        return new VwCompanyTradeClassWrapper(_vwCompanyTradeClass.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _vwCompanyTradeClass.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _vwCompanyTradeClass.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _vwCompanyTradeClass.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof VwCompanyTradeClassWrapper)) {
            return false;
        }

        VwCompanyTradeClassWrapper vwCompanyTradeClassWrapper = (VwCompanyTradeClassWrapper) obj;

        if (Validator.equals(_vwCompanyTradeClass,
                    vwCompanyTradeClassWrapper._vwCompanyTradeClass)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public VwCompanyTradeClass getWrappedVwCompanyTradeClass() {
        return _vwCompanyTradeClass;
    }

    @Override
    public VwCompanyTradeClass getWrappedModel() {
        return _vwCompanyTradeClass;
    }

    @Override
    public void resetOriginalValues() {
        _vwCompanyTradeClass.resetOriginalValues();
    }
}
