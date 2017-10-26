package com.stpl.app.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link MasterDataAttribute}.
 * </p>
 *
 * @author
 * @see MasterDataAttribute
 * @generated
 */
public class MasterDataAttributeWrapper implements MasterDataAttribute,
    ModelWrapper<MasterDataAttribute> {
    private MasterDataAttribute _masterDataAttribute;

    public MasterDataAttributeWrapper(MasterDataAttribute masterDataAttribute) {
        _masterDataAttribute = masterDataAttribute;
    }

    @Override
    public Class<?> getModelClass() {
        return MasterDataAttribute.class;
    }

    @Override
    public String getModelClassName() {
        return MasterDataAttribute.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("column15", getColumn15());
        attributes.put("column14", getColumn14());
        attributes.put("column17", getColumn17());
        attributes.put("column16", getColumn16());
        attributes.put("column11", getColumn11());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("column10", getColumn10());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("column13", getColumn13());
        attributes.put("column12", getColumn12());
        attributes.put("batchId", getBatchId());
        attributes.put("column19", getColumn19());
        attributes.put("column18", getColumn18());
        attributes.put("masterAttribute", getMasterAttribute());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("masterType", getMasterType());
        attributes.put("masterId", getMasterId());
        attributes.put("column20", getColumn20());
        attributes.put("column9", getColumn9());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("column6", getColumn6());
        attributes.put("masterDataAttributeSid", getMasterDataAttributeSid());
        attributes.put("column5", getColumn5());
        attributes.put("column8", getColumn8());
        attributes.put("column7", getColumn7());
        attributes.put("recordLockStatus", getRecordLockStatus());
        attributes.put("column2", getColumn2());
        attributes.put("column1", getColumn1());
        attributes.put("column4", getColumn4());
        attributes.put("column3", getColumn3());
        attributes.put("source", getSource());
        attributes.put("inboundStatus", getInboundStatus());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        String column15 = (String) attributes.get("column15");

        if (column15 != null) {
            setColumn15(column15);
        }

        String column14 = (String) attributes.get("column14");

        if (column14 != null) {
            setColumn14(column14);
        }

        String column17 = (String) attributes.get("column17");

        if (column17 != null) {
            setColumn17(column17);
        }

        String column16 = (String) attributes.get("column16");

        if (column16 != null) {
            setColumn16(column16);
        }

        String column11 = (String) attributes.get("column11");

        if (column11 != null) {
            setColumn11(column11);
        }

        Integer modifiedBy = (Integer) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        String column10 = (String) attributes.get("column10");

        if (column10 != null) {
            setColumn10(column10);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        String column13 = (String) attributes.get("column13");

        if (column13 != null) {
            setColumn13(column13);
        }

        String column12 = (String) attributes.get("column12");

        if (column12 != null) {
            setColumn12(column12);
        }

        String batchId = (String) attributes.get("batchId");

        if (batchId != null) {
            setBatchId(batchId);
        }

        String column19 = (String) attributes.get("column19");

        if (column19 != null) {
            setColumn19(column19);
        }

        String column18 = (String) attributes.get("column18");

        if (column18 != null) {
            setColumn18(column18);
        }

        String masterAttribute = (String) attributes.get("masterAttribute");

        if (masterAttribute != null) {
            setMasterAttribute(masterAttribute);
        }

        Integer createdBy = (Integer) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        String masterType = (String) attributes.get("masterType");

        if (masterType != null) {
            setMasterType(masterType);
        }

        String masterId = (String) attributes.get("masterId");

        if (masterId != null) {
            setMasterId(masterId);
        }

        String column20 = (String) attributes.get("column20");

        if (column20 != null) {
            setColumn20(column20);
        }

        String column9 = (String) attributes.get("column9");

        if (column9 != null) {
            setColumn9(column9);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        String column6 = (String) attributes.get("column6");

        if (column6 != null) {
            setColumn6(column6);
        }

        Integer masterDataAttributeSid = (Integer) attributes.get(
                "masterDataAttributeSid");

        if (masterDataAttributeSid != null) {
            setMasterDataAttributeSid(masterDataAttributeSid);
        }

        String column5 = (String) attributes.get("column5");

        if (column5 != null) {
            setColumn5(column5);
        }

        String column8 = (String) attributes.get("column8");

        if (column8 != null) {
            setColumn8(column8);
        }

        String column7 = (String) attributes.get("column7");

        if (column7 != null) {
            setColumn7(column7);
        }

        Boolean recordLockStatus = (Boolean) attributes.get("recordLockStatus");

        if (recordLockStatus != null) {
            setRecordLockStatus(recordLockStatus);
        }

        String column2 = (String) attributes.get("column2");

        if (column2 != null) {
            setColumn2(column2);
        }

        String column1 = (String) attributes.get("column1");

        if (column1 != null) {
            setColumn1(column1);
        }

        String column4 = (String) attributes.get("column4");

        if (column4 != null) {
            setColumn4(column4);
        }

        String column3 = (String) attributes.get("column3");

        if (column3 != null) {
            setColumn3(column3);
        }

        String source = (String) attributes.get("source");

        if (source != null) {
            setSource(source);
        }

        String inboundStatus = (String) attributes.get("inboundStatus");

        if (inboundStatus != null) {
            setInboundStatus(inboundStatus);
        }
    }

    /**
    * Returns the primary key of this master data attribute.
    *
    * @return the primary key of this master data attribute
    */
    @Override
    public int getPrimaryKey() {
        return _masterDataAttribute.getPrimaryKey();
    }

    /**
    * Sets the primary key of this master data attribute.
    *
    * @param primaryKey the primary key of this master data attribute
    */
    @Override
    public void setPrimaryKey(int primaryKey) {
        _masterDataAttribute.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the column15 of this master data attribute.
    *
    * @return the column15 of this master data attribute
    */
    @Override
    public java.lang.String getColumn15() {
        return _masterDataAttribute.getColumn15();
    }

    /**
    * Sets the column15 of this master data attribute.
    *
    * @param column15 the column15 of this master data attribute
    */
    @Override
    public void setColumn15(java.lang.String column15) {
        _masterDataAttribute.setColumn15(column15);
    }

    /**
    * Returns the column14 of this master data attribute.
    *
    * @return the column14 of this master data attribute
    */
    @Override
    public java.lang.String getColumn14() {
        return _masterDataAttribute.getColumn14();
    }

    /**
    * Sets the column14 of this master data attribute.
    *
    * @param column14 the column14 of this master data attribute
    */
    @Override
    public void setColumn14(java.lang.String column14) {
        _masterDataAttribute.setColumn14(column14);
    }

    /**
    * Returns the column17 of this master data attribute.
    *
    * @return the column17 of this master data attribute
    */
    @Override
    public java.lang.String getColumn17() {
        return _masterDataAttribute.getColumn17();
    }

    /**
    * Sets the column17 of this master data attribute.
    *
    * @param column17 the column17 of this master data attribute
    */
    @Override
    public void setColumn17(java.lang.String column17) {
        _masterDataAttribute.setColumn17(column17);
    }

    /**
    * Returns the column16 of this master data attribute.
    *
    * @return the column16 of this master data attribute
    */
    @Override
    public java.lang.String getColumn16() {
        return _masterDataAttribute.getColumn16();
    }

    /**
    * Sets the column16 of this master data attribute.
    *
    * @param column16 the column16 of this master data attribute
    */
    @Override
    public void setColumn16(java.lang.String column16) {
        _masterDataAttribute.setColumn16(column16);
    }

    /**
    * Returns the column11 of this master data attribute.
    *
    * @return the column11 of this master data attribute
    */
    @Override
    public java.lang.String getColumn11() {
        return _masterDataAttribute.getColumn11();
    }

    /**
    * Sets the column11 of this master data attribute.
    *
    * @param column11 the column11 of this master data attribute
    */
    @Override
    public void setColumn11(java.lang.String column11) {
        _masterDataAttribute.setColumn11(column11);
    }

    /**
    * Returns the modified by of this master data attribute.
    *
    * @return the modified by of this master data attribute
    */
    @Override
    public int getModifiedBy() {
        return _masterDataAttribute.getModifiedBy();
    }

    /**
    * Sets the modified by of this master data attribute.
    *
    * @param modifiedBy the modified by of this master data attribute
    */
    @Override
    public void setModifiedBy(int modifiedBy) {
        _masterDataAttribute.setModifiedBy(modifiedBy);
    }

    /**
    * Returns the column10 of this master data attribute.
    *
    * @return the column10 of this master data attribute
    */
    @Override
    public java.lang.String getColumn10() {
        return _masterDataAttribute.getColumn10();
    }

    /**
    * Sets the column10 of this master data attribute.
    *
    * @param column10 the column10 of this master data attribute
    */
    @Override
    public void setColumn10(java.lang.String column10) {
        _masterDataAttribute.setColumn10(column10);
    }

    /**
    * Returns the created date of this master data attribute.
    *
    * @return the created date of this master data attribute
    */
    @Override
    public java.util.Date getCreatedDate() {
        return _masterDataAttribute.getCreatedDate();
    }

    /**
    * Sets the created date of this master data attribute.
    *
    * @param createdDate the created date of this master data attribute
    */
    @Override
    public void setCreatedDate(java.util.Date createdDate) {
        _masterDataAttribute.setCreatedDate(createdDate);
    }

    /**
    * Returns the column13 of this master data attribute.
    *
    * @return the column13 of this master data attribute
    */
    @Override
    public java.lang.String getColumn13() {
        return _masterDataAttribute.getColumn13();
    }

    /**
    * Sets the column13 of this master data attribute.
    *
    * @param column13 the column13 of this master data attribute
    */
    @Override
    public void setColumn13(java.lang.String column13) {
        _masterDataAttribute.setColumn13(column13);
    }

    /**
    * Returns the column12 of this master data attribute.
    *
    * @return the column12 of this master data attribute
    */
    @Override
    public java.lang.String getColumn12() {
        return _masterDataAttribute.getColumn12();
    }

    /**
    * Sets the column12 of this master data attribute.
    *
    * @param column12 the column12 of this master data attribute
    */
    @Override
    public void setColumn12(java.lang.String column12) {
        _masterDataAttribute.setColumn12(column12);
    }

    /**
    * Returns the batch ID of this master data attribute.
    *
    * @return the batch ID of this master data attribute
    */
    @Override
    public java.lang.String getBatchId() {
        return _masterDataAttribute.getBatchId();
    }

    /**
    * Sets the batch ID of this master data attribute.
    *
    * @param batchId the batch ID of this master data attribute
    */
    @Override
    public void setBatchId(java.lang.String batchId) {
        _masterDataAttribute.setBatchId(batchId);
    }

    /**
    * Returns the column19 of this master data attribute.
    *
    * @return the column19 of this master data attribute
    */
    @Override
    public java.lang.String getColumn19() {
        return _masterDataAttribute.getColumn19();
    }

    /**
    * Sets the column19 of this master data attribute.
    *
    * @param column19 the column19 of this master data attribute
    */
    @Override
    public void setColumn19(java.lang.String column19) {
        _masterDataAttribute.setColumn19(column19);
    }

    /**
    * Returns the column18 of this master data attribute.
    *
    * @return the column18 of this master data attribute
    */
    @Override
    public java.lang.String getColumn18() {
        return _masterDataAttribute.getColumn18();
    }

    /**
    * Sets the column18 of this master data attribute.
    *
    * @param column18 the column18 of this master data attribute
    */
    @Override
    public void setColumn18(java.lang.String column18) {
        _masterDataAttribute.setColumn18(column18);
    }

    /**
    * Returns the master attribute of this master data attribute.
    *
    * @return the master attribute of this master data attribute
    */
    @Override
    public java.lang.String getMasterAttribute() {
        return _masterDataAttribute.getMasterAttribute();
    }

    /**
    * Sets the master attribute of this master data attribute.
    *
    * @param masterAttribute the master attribute of this master data attribute
    */
    @Override
    public void setMasterAttribute(java.lang.String masterAttribute) {
        _masterDataAttribute.setMasterAttribute(masterAttribute);
    }

    /**
    * Returns the created by of this master data attribute.
    *
    * @return the created by of this master data attribute
    */
    @Override
    public int getCreatedBy() {
        return _masterDataAttribute.getCreatedBy();
    }

    /**
    * Sets the created by of this master data attribute.
    *
    * @param createdBy the created by of this master data attribute
    */
    @Override
    public void setCreatedBy(int createdBy) {
        _masterDataAttribute.setCreatedBy(createdBy);
    }

    /**
    * Returns the master type of this master data attribute.
    *
    * @return the master type of this master data attribute
    */
    @Override
    public java.lang.String getMasterType() {
        return _masterDataAttribute.getMasterType();
    }

    /**
    * Sets the master type of this master data attribute.
    *
    * @param masterType the master type of this master data attribute
    */
    @Override
    public void setMasterType(java.lang.String masterType) {
        _masterDataAttribute.setMasterType(masterType);
    }

    /**
    * Returns the master ID of this master data attribute.
    *
    * @return the master ID of this master data attribute
    */
    @Override
    public java.lang.String getMasterId() {
        return _masterDataAttribute.getMasterId();
    }

    /**
    * Sets the master ID of this master data attribute.
    *
    * @param masterId the master ID of this master data attribute
    */
    @Override
    public void setMasterId(java.lang.String masterId) {
        _masterDataAttribute.setMasterId(masterId);
    }

    /**
    * Returns the column20 of this master data attribute.
    *
    * @return the column20 of this master data attribute
    */
    @Override
    public java.lang.String getColumn20() {
        return _masterDataAttribute.getColumn20();
    }

    /**
    * Sets the column20 of this master data attribute.
    *
    * @param column20 the column20 of this master data attribute
    */
    @Override
    public void setColumn20(java.lang.String column20) {
        _masterDataAttribute.setColumn20(column20);
    }

    /**
    * Returns the column9 of this master data attribute.
    *
    * @return the column9 of this master data attribute
    */
    @Override
    public java.lang.String getColumn9() {
        return _masterDataAttribute.getColumn9();
    }

    /**
    * Sets the column9 of this master data attribute.
    *
    * @param column9 the column9 of this master data attribute
    */
    @Override
    public void setColumn9(java.lang.String column9) {
        _masterDataAttribute.setColumn9(column9);
    }

    /**
    * Returns the modified date of this master data attribute.
    *
    * @return the modified date of this master data attribute
    */
    @Override
    public java.util.Date getModifiedDate() {
        return _masterDataAttribute.getModifiedDate();
    }

    /**
    * Sets the modified date of this master data attribute.
    *
    * @param modifiedDate the modified date of this master data attribute
    */
    @Override
    public void setModifiedDate(java.util.Date modifiedDate) {
        _masterDataAttribute.setModifiedDate(modifiedDate);
    }

    /**
    * Returns the column6 of this master data attribute.
    *
    * @return the column6 of this master data attribute
    */
    @Override
    public java.lang.String getColumn6() {
        return _masterDataAttribute.getColumn6();
    }

    /**
    * Sets the column6 of this master data attribute.
    *
    * @param column6 the column6 of this master data attribute
    */
    @Override
    public void setColumn6(java.lang.String column6) {
        _masterDataAttribute.setColumn6(column6);
    }

    /**
    * Returns the master data attribute sid of this master data attribute.
    *
    * @return the master data attribute sid of this master data attribute
    */
    @Override
    public int getMasterDataAttributeSid() {
        return _masterDataAttribute.getMasterDataAttributeSid();
    }

    /**
    * Sets the master data attribute sid of this master data attribute.
    *
    * @param masterDataAttributeSid the master data attribute sid of this master data attribute
    */
    @Override
    public void setMasterDataAttributeSid(int masterDataAttributeSid) {
        _masterDataAttribute.setMasterDataAttributeSid(masterDataAttributeSid);
    }

    /**
    * Returns the column5 of this master data attribute.
    *
    * @return the column5 of this master data attribute
    */
    @Override
    public java.lang.String getColumn5() {
        return _masterDataAttribute.getColumn5();
    }

    /**
    * Sets the column5 of this master data attribute.
    *
    * @param column5 the column5 of this master data attribute
    */
    @Override
    public void setColumn5(java.lang.String column5) {
        _masterDataAttribute.setColumn5(column5);
    }

    /**
    * Returns the column8 of this master data attribute.
    *
    * @return the column8 of this master data attribute
    */
    @Override
    public java.lang.String getColumn8() {
        return _masterDataAttribute.getColumn8();
    }

    /**
    * Sets the column8 of this master data attribute.
    *
    * @param column8 the column8 of this master data attribute
    */
    @Override
    public void setColumn8(java.lang.String column8) {
        _masterDataAttribute.setColumn8(column8);
    }

    /**
    * Returns the column7 of this master data attribute.
    *
    * @return the column7 of this master data attribute
    */
    @Override
    public java.lang.String getColumn7() {
        return _masterDataAttribute.getColumn7();
    }

    /**
    * Sets the column7 of this master data attribute.
    *
    * @param column7 the column7 of this master data attribute
    */
    @Override
    public void setColumn7(java.lang.String column7) {
        _masterDataAttribute.setColumn7(column7);
    }

    /**
    * Returns the record lock status of this master data attribute.
    *
    * @return the record lock status of this master data attribute
    */
    @Override
    public boolean getRecordLockStatus() {
        return _masterDataAttribute.getRecordLockStatus();
    }

    /**
    * Returns <code>true</code> if this master data attribute is record lock status.
    *
    * @return <code>true</code> if this master data attribute is record lock status; <code>false</code> otherwise
    */
    @Override
    public boolean isRecordLockStatus() {
        return _masterDataAttribute.isRecordLockStatus();
    }

    /**
    * Sets whether this master data attribute is record lock status.
    *
    * @param recordLockStatus the record lock status of this master data attribute
    */
    @Override
    public void setRecordLockStatus(boolean recordLockStatus) {
        _masterDataAttribute.setRecordLockStatus(recordLockStatus);
    }

    /**
    * Returns the column2 of this master data attribute.
    *
    * @return the column2 of this master data attribute
    */
    @Override
    public java.lang.String getColumn2() {
        return _masterDataAttribute.getColumn2();
    }

    /**
    * Sets the column2 of this master data attribute.
    *
    * @param column2 the column2 of this master data attribute
    */
    @Override
    public void setColumn2(java.lang.String column2) {
        _masterDataAttribute.setColumn2(column2);
    }

    /**
    * Returns the column1 of this master data attribute.
    *
    * @return the column1 of this master data attribute
    */
    @Override
    public java.lang.String getColumn1() {
        return _masterDataAttribute.getColumn1();
    }

    /**
    * Sets the column1 of this master data attribute.
    *
    * @param column1 the column1 of this master data attribute
    */
    @Override
    public void setColumn1(java.lang.String column1) {
        _masterDataAttribute.setColumn1(column1);
    }

    /**
    * Returns the column4 of this master data attribute.
    *
    * @return the column4 of this master data attribute
    */
    @Override
    public java.lang.String getColumn4() {
        return _masterDataAttribute.getColumn4();
    }

    /**
    * Sets the column4 of this master data attribute.
    *
    * @param column4 the column4 of this master data attribute
    */
    @Override
    public void setColumn4(java.lang.String column4) {
        _masterDataAttribute.setColumn4(column4);
    }

    /**
    * Returns the column3 of this master data attribute.
    *
    * @return the column3 of this master data attribute
    */
    @Override
    public java.lang.String getColumn3() {
        return _masterDataAttribute.getColumn3();
    }

    /**
    * Sets the column3 of this master data attribute.
    *
    * @param column3 the column3 of this master data attribute
    */
    @Override
    public void setColumn3(java.lang.String column3) {
        _masterDataAttribute.setColumn3(column3);
    }

    /**
    * Returns the source of this master data attribute.
    *
    * @return the source of this master data attribute
    */
    @Override
    public java.lang.String getSource() {
        return _masterDataAttribute.getSource();
    }

    /**
    * Sets the source of this master data attribute.
    *
    * @param source the source of this master data attribute
    */
    @Override
    public void setSource(java.lang.String source) {
        _masterDataAttribute.setSource(source);
    }

    /**
    * Returns the inbound status of this master data attribute.
    *
    * @return the inbound status of this master data attribute
    */
    @Override
    public java.lang.String getInboundStatus() {
        return _masterDataAttribute.getInboundStatus();
    }

    /**
    * Sets the inbound status of this master data attribute.
    *
    * @param inboundStatus the inbound status of this master data attribute
    */
    @Override
    public void setInboundStatus(java.lang.String inboundStatus) {
        _masterDataAttribute.setInboundStatus(inboundStatus);
    }

    @Override
    public boolean isNew() {
        return _masterDataAttribute.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _masterDataAttribute.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _masterDataAttribute.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _masterDataAttribute.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _masterDataAttribute.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _masterDataAttribute.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _masterDataAttribute.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _masterDataAttribute.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _masterDataAttribute.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _masterDataAttribute.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _masterDataAttribute.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new MasterDataAttributeWrapper((MasterDataAttribute) _masterDataAttribute.clone());
    }

    @Override
    public int compareTo(MasterDataAttribute masterDataAttribute) {
        return _masterDataAttribute.compareTo(masterDataAttribute);
    }

    @Override
    public int hashCode() {
        return _masterDataAttribute.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<MasterDataAttribute> toCacheModel() {
        return _masterDataAttribute.toCacheModel();
    }

    @Override
    public MasterDataAttribute toEscapedModel() {
        return new MasterDataAttributeWrapper(_masterDataAttribute.toEscapedModel());
    }

    @Override
    public MasterDataAttribute toUnescapedModel() {
        return new MasterDataAttributeWrapper(_masterDataAttribute.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _masterDataAttribute.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _masterDataAttribute.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _masterDataAttribute.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof MasterDataAttributeWrapper)) {
            return false;
        }

        MasterDataAttributeWrapper masterDataAttributeWrapper = (MasterDataAttributeWrapper) obj;

        if (Validator.equals(_masterDataAttribute,
                    masterDataAttributeWrapper._masterDataAttribute)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public MasterDataAttribute getWrappedMasterDataAttribute() {
        return _masterDataAttribute;
    }

    @Override
    public MasterDataAttribute getWrappedModel() {
        return _masterDataAttribute;
    }

    @Override
    public void resetOriginalValues() {
        _masterDataAttribute.resetOriginalValues();
    }
}
