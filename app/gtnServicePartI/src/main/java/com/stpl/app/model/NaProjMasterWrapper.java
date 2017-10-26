package com.stpl.app.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link NaProjMaster}.
 * </p>
 *
 * @author
 * @see NaProjMaster
 * @generated
 */
public class NaProjMasterWrapper implements NaProjMaster,
    ModelWrapper<NaProjMaster> {
    private NaProjMaster _naProjMaster;

    public NaProjMasterWrapper(NaProjMaster naProjMaster) {
        _naProjMaster = naProjMaster;
    }

    @Override
    public Class<?> getModelClass() {
        return NaProjMaster.class;
    }

    @Override
    public String getModelClassName() {
        return NaProjMaster.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("naProjName", getNaProjName());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("saveFlag", getSaveFlag());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("naProjMasterSid", getNaProjMasterSid());
        attributes.put("itemGroupSid", getItemGroupSid());
        attributes.put("therapeuticClass", getTherapeuticClass());
        attributes.put("companyMasterSid", getCompanyMasterSid());
        attributes.put("businessUnit", getBusinessUnit());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        String naProjName = (String) attributes.get("naProjName");

        if (naProjName != null) {
            setNaProjName(naProjName);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        Integer createdBy = (Integer) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        Boolean saveFlag = (Boolean) attributes.get("saveFlag");

        if (saveFlag != null) {
            setSaveFlag(saveFlag);
        }

        Integer modifiedBy = (Integer) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        Integer naProjMasterSid = (Integer) attributes.get("naProjMasterSid");

        if (naProjMasterSid != null) {
            setNaProjMasterSid(naProjMasterSid);
        }

        Integer itemGroupSid = (Integer) attributes.get("itemGroupSid");

        if (itemGroupSid != null) {
            setItemGroupSid(itemGroupSid);
        }

        Integer therapeuticClass = (Integer) attributes.get("therapeuticClass");

        if (therapeuticClass != null) {
            setTherapeuticClass(therapeuticClass);
        }

        Integer companyMasterSid = (Integer) attributes.get("companyMasterSid");

        if (companyMasterSid != null) {
            setCompanyMasterSid(companyMasterSid);
        }

        Integer businessUnit = (Integer) attributes.get("businessUnit");

        if (businessUnit != null) {
            setBusinessUnit(businessUnit);
        }
    }

    /**
    * Returns the primary key of this na proj master.
    *
    * @return the primary key of this na proj master
    */
    @Override
    public int getPrimaryKey() {
        return _naProjMaster.getPrimaryKey();
    }

    /**
    * Sets the primary key of this na proj master.
    *
    * @param primaryKey the primary key of this na proj master
    */
    @Override
    public void setPrimaryKey(int primaryKey) {
        _naProjMaster.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the na proj name of this na proj master.
    *
    * @return the na proj name of this na proj master
    */
    @Override
    public java.lang.String getNaProjName() {
        return _naProjMaster.getNaProjName();
    }

    /**
    * Sets the na proj name of this na proj master.
    *
    * @param naProjName the na proj name of this na proj master
    */
    @Override
    public void setNaProjName(java.lang.String naProjName) {
        _naProjMaster.setNaProjName(naProjName);
    }

    /**
    * Returns the created date of this na proj master.
    *
    * @return the created date of this na proj master
    */
    @Override
    public java.util.Date getCreatedDate() {
        return _naProjMaster.getCreatedDate();
    }

    /**
    * Sets the created date of this na proj master.
    *
    * @param createdDate the created date of this na proj master
    */
    @Override
    public void setCreatedDate(java.util.Date createdDate) {
        _naProjMaster.setCreatedDate(createdDate);
    }

    /**
    * Returns the created by of this na proj master.
    *
    * @return the created by of this na proj master
    */
    @Override
    public int getCreatedBy() {
        return _naProjMaster.getCreatedBy();
    }

    /**
    * Sets the created by of this na proj master.
    *
    * @param createdBy the created by of this na proj master
    */
    @Override
    public void setCreatedBy(int createdBy) {
        _naProjMaster.setCreatedBy(createdBy);
    }

    /**
    * Returns the save flag of this na proj master.
    *
    * @return the save flag of this na proj master
    */
    @Override
    public boolean getSaveFlag() {
        return _naProjMaster.getSaveFlag();
    }

    /**
    * Returns <code>true</code> if this na proj master is save flag.
    *
    * @return <code>true</code> if this na proj master is save flag; <code>false</code> otherwise
    */
    @Override
    public boolean isSaveFlag() {
        return _naProjMaster.isSaveFlag();
    }

    /**
    * Sets whether this na proj master is save flag.
    *
    * @param saveFlag the save flag of this na proj master
    */
    @Override
    public void setSaveFlag(boolean saveFlag) {
        _naProjMaster.setSaveFlag(saveFlag);
    }

    /**
    * Returns the modified by of this na proj master.
    *
    * @return the modified by of this na proj master
    */
    @Override
    public int getModifiedBy() {
        return _naProjMaster.getModifiedBy();
    }

    /**
    * Sets the modified by of this na proj master.
    *
    * @param modifiedBy the modified by of this na proj master
    */
    @Override
    public void setModifiedBy(int modifiedBy) {
        _naProjMaster.setModifiedBy(modifiedBy);
    }

    /**
    * Returns the modified date of this na proj master.
    *
    * @return the modified date of this na proj master
    */
    @Override
    public java.util.Date getModifiedDate() {
        return _naProjMaster.getModifiedDate();
    }

    /**
    * Sets the modified date of this na proj master.
    *
    * @param modifiedDate the modified date of this na proj master
    */
    @Override
    public void setModifiedDate(java.util.Date modifiedDate) {
        _naProjMaster.setModifiedDate(modifiedDate);
    }

    /**
    * Returns the na proj master sid of this na proj master.
    *
    * @return the na proj master sid of this na proj master
    */
    @Override
    public int getNaProjMasterSid() {
        return _naProjMaster.getNaProjMasterSid();
    }

    /**
    * Sets the na proj master sid of this na proj master.
    *
    * @param naProjMasterSid the na proj master sid of this na proj master
    */
    @Override
    public void setNaProjMasterSid(int naProjMasterSid) {
        _naProjMaster.setNaProjMasterSid(naProjMasterSid);
    }

    /**
    * Returns the item group sid of this na proj master.
    *
    * @return the item group sid of this na proj master
    */
    @Override
    public int getItemGroupSid() {
        return _naProjMaster.getItemGroupSid();
    }

    /**
    * Sets the item group sid of this na proj master.
    *
    * @param itemGroupSid the item group sid of this na proj master
    */
    @Override
    public void setItemGroupSid(int itemGroupSid) {
        _naProjMaster.setItemGroupSid(itemGroupSid);
    }

    /**
    * Returns the therapeutic class of this na proj master.
    *
    * @return the therapeutic class of this na proj master
    */
    @Override
    public int getTherapeuticClass() {
        return _naProjMaster.getTherapeuticClass();
    }

    /**
    * Sets the therapeutic class of this na proj master.
    *
    * @param therapeuticClass the therapeutic class of this na proj master
    */
    @Override
    public void setTherapeuticClass(int therapeuticClass) {
        _naProjMaster.setTherapeuticClass(therapeuticClass);
    }

    /**
    * Returns the company master sid of this na proj master.
    *
    * @return the company master sid of this na proj master
    */
    @Override
    public int getCompanyMasterSid() {
        return _naProjMaster.getCompanyMasterSid();
    }

    /**
    * Sets the company master sid of this na proj master.
    *
    * @param companyMasterSid the company master sid of this na proj master
    */
    @Override
    public void setCompanyMasterSid(int companyMasterSid) {
        _naProjMaster.setCompanyMasterSid(companyMasterSid);
    }

    /**
    * Returns the business unit of this na proj master.
    *
    * @return the business unit of this na proj master
    */
    @Override
    public int getBusinessUnit() {
        return _naProjMaster.getBusinessUnit();
    }

    /**
    * Sets the business unit of this na proj master.
    *
    * @param businessUnit the business unit of this na proj master
    */
    @Override
    public void setBusinessUnit(int businessUnit) {
        _naProjMaster.setBusinessUnit(businessUnit);
    }

    @Override
    public boolean isNew() {
        return _naProjMaster.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _naProjMaster.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _naProjMaster.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _naProjMaster.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _naProjMaster.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _naProjMaster.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _naProjMaster.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _naProjMaster.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _naProjMaster.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _naProjMaster.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _naProjMaster.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new NaProjMasterWrapper((NaProjMaster) _naProjMaster.clone());
    }

    @Override
    public int compareTo(NaProjMaster naProjMaster) {
        return _naProjMaster.compareTo(naProjMaster);
    }

    @Override
    public int hashCode() {
        return _naProjMaster.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<NaProjMaster> toCacheModel() {
        return _naProjMaster.toCacheModel();
    }

    @Override
    public NaProjMaster toEscapedModel() {
        return new NaProjMasterWrapper(_naProjMaster.toEscapedModel());
    }

    @Override
    public NaProjMaster toUnescapedModel() {
        return new NaProjMasterWrapper(_naProjMaster.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _naProjMaster.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _naProjMaster.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _naProjMaster.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof NaProjMasterWrapper)) {
            return false;
        }

        NaProjMasterWrapper naProjMasterWrapper = (NaProjMasterWrapper) obj;

        if (Validator.equals(_naProjMaster, naProjMasterWrapper._naProjMaster)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public NaProjMaster getWrappedNaProjMaster() {
        return _naProjMaster;
    }

    @Override
    public NaProjMaster getWrappedModel() {
        return _naProjMaster;
    }

    @Override
    public void resetOriginalValues() {
        _naProjMaster.resetOriginalValues();
    }
}
