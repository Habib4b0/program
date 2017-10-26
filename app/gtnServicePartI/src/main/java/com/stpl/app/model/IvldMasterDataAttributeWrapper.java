package com.stpl.app.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link IvldMasterDataAttribute}.
 * </p>
 *
 * @author
 * @see IvldMasterDataAttribute
 * @generated
 */
public class IvldMasterDataAttributeWrapper implements IvldMasterDataAttribute,
    ModelWrapper<IvldMasterDataAttribute> {
    private IvldMasterDataAttribute _ivldMasterDataAttribute;

    public IvldMasterDataAttributeWrapper(
        IvldMasterDataAttribute ivldMasterDataAttribute) {
        _ivldMasterDataAttribute = ivldMasterDataAttribute;
    }

    @Override
    public Class<?> getModelClass() {
        return IvldMasterDataAttribute.class;
    }

    @Override
    public String getModelClassName() {
        return IvldMasterDataAttribute.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("column19", getColumn19());
        attributes.put("column18", getColumn18());
        attributes.put("masterAttribute", getMasterAttribute());
        attributes.put("masterType", getMasterType());
        attributes.put("dataAttributeIntfid", getDataAttributeIntfid());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("source", getSource());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("addChgDelIndicator", getAddChgDelIndicator());
        attributes.put("masterId", getMasterId());
        attributes.put("column10", getColumn10());
        attributes.put("column11", getColumn11());
        attributes.put("errorCode", getErrorCode());
        attributes.put("column12", getColumn12());
        attributes.put("intfInsertedDate", getIntfInsertedDate());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("column13", getColumn13());
        attributes.put("column14", getColumn14());
        attributes.put("reprocessedFlag", getReprocessedFlag());
        attributes.put("column15", getColumn15());
        attributes.put("column16", getColumn16());
        attributes.put("column17", getColumn17());
        attributes.put("column4", getColumn4());
        attributes.put("column3", getColumn3());
        attributes.put("column2", getColumn2());
        attributes.put("column1", getColumn1());
        attributes.put("column8", getColumn8());
        attributes.put("reasonForFailure", getReasonForFailure());
        attributes.put("column7", getColumn7());
        attributes.put("column6", getColumn6());
        attributes.put("column5", getColumn5());
        attributes.put("column20", getColumn20());
        attributes.put("batchId", getBatchId());
        attributes.put("errorField", getErrorField());
        attributes.put("column9", getColumn9());
        attributes.put("ivldMasterDataAtbteSid", getIvldMasterDataAtbteSid());
        attributes.put("checkRecord", getCheckRecord());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
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

        String masterType = (String) attributes.get("masterType");

        if (masterType != null) {
            setMasterType(masterType);
        }

        String dataAttributeIntfid = (String) attributes.get(
                "dataAttributeIntfid");

        if (dataAttributeIntfid != null) {
            setDataAttributeIntfid(dataAttributeIntfid);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        String source = (String) attributes.get("source");

        if (source != null) {
            setSource(source);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        String createdBy = (String) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        String addChgDelIndicator = (String) attributes.get(
                "addChgDelIndicator");

        if (addChgDelIndicator != null) {
            setAddChgDelIndicator(addChgDelIndicator);
        }

        String masterId = (String) attributes.get("masterId");

        if (masterId != null) {
            setMasterId(masterId);
        }

        String column10 = (String) attributes.get("column10");

        if (column10 != null) {
            setColumn10(column10);
        }

        String column11 = (String) attributes.get("column11");

        if (column11 != null) {
            setColumn11(column11);
        }

        String errorCode = (String) attributes.get("errorCode");

        if (errorCode != null) {
            setErrorCode(errorCode);
        }

        String column12 = (String) attributes.get("column12");

        if (column12 != null) {
            setColumn12(column12);
        }

        Date intfInsertedDate = (Date) attributes.get("intfInsertedDate");

        if (intfInsertedDate != null) {
            setIntfInsertedDate(intfInsertedDate);
        }

        String modifiedBy = (String) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        String column13 = (String) attributes.get("column13");

        if (column13 != null) {
            setColumn13(column13);
        }

        String column14 = (String) attributes.get("column14");

        if (column14 != null) {
            setColumn14(column14);
        }

        String reprocessedFlag = (String) attributes.get("reprocessedFlag");

        if (reprocessedFlag != null) {
            setReprocessedFlag(reprocessedFlag);
        }

        String column15 = (String) attributes.get("column15");

        if (column15 != null) {
            setColumn15(column15);
        }

        String column16 = (String) attributes.get("column16");

        if (column16 != null) {
            setColumn16(column16);
        }

        String column17 = (String) attributes.get("column17");

        if (column17 != null) {
            setColumn17(column17);
        }

        String column4 = (String) attributes.get("column4");

        if (column4 != null) {
            setColumn4(column4);
        }

        String column3 = (String) attributes.get("column3");

        if (column3 != null) {
            setColumn3(column3);
        }

        String column2 = (String) attributes.get("column2");

        if (column2 != null) {
            setColumn2(column2);
        }

        String column1 = (String) attributes.get("column1");

        if (column1 != null) {
            setColumn1(column1);
        }

        String column8 = (String) attributes.get("column8");

        if (column8 != null) {
            setColumn8(column8);
        }

        String reasonForFailure = (String) attributes.get("reasonForFailure");

        if (reasonForFailure != null) {
            setReasonForFailure(reasonForFailure);
        }

        String column7 = (String) attributes.get("column7");

        if (column7 != null) {
            setColumn7(column7);
        }

        String column6 = (String) attributes.get("column6");

        if (column6 != null) {
            setColumn6(column6);
        }

        String column5 = (String) attributes.get("column5");

        if (column5 != null) {
            setColumn5(column5);
        }

        String column20 = (String) attributes.get("column20");

        if (column20 != null) {
            setColumn20(column20);
        }

        String batchId = (String) attributes.get("batchId");

        if (batchId != null) {
            setBatchId(batchId);
        }

        String errorField = (String) attributes.get("errorField");

        if (errorField != null) {
            setErrorField(errorField);
        }

        String column9 = (String) attributes.get("column9");

        if (column9 != null) {
            setColumn9(column9);
        }

        Integer ivldMasterDataAtbteSid = (Integer) attributes.get(
                "ivldMasterDataAtbteSid");

        if (ivldMasterDataAtbteSid != null) {
            setIvldMasterDataAtbteSid(ivldMasterDataAtbteSid);
        }

        Boolean checkRecord = (Boolean) attributes.get("checkRecord");

        if (checkRecord != null) {
            setCheckRecord(checkRecord);
        }
    }

    /**
    * Returns the primary key of this ivld master data attribute.
    *
    * @return the primary key of this ivld master data attribute
    */
    @Override
    public int getPrimaryKey() {
        return _ivldMasterDataAttribute.getPrimaryKey();
    }

    /**
    * Sets the primary key of this ivld master data attribute.
    *
    * @param primaryKey the primary key of this ivld master data attribute
    */
    @Override
    public void setPrimaryKey(int primaryKey) {
        _ivldMasterDataAttribute.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the column19 of this ivld master data attribute.
    *
    * @return the column19 of this ivld master data attribute
    */
    @Override
    public java.lang.String getColumn19() {
        return _ivldMasterDataAttribute.getColumn19();
    }

    /**
    * Sets the column19 of this ivld master data attribute.
    *
    * @param column19 the column19 of this ivld master data attribute
    */
    @Override
    public void setColumn19(java.lang.String column19) {
        _ivldMasterDataAttribute.setColumn19(column19);
    }

    /**
    * Returns the column18 of this ivld master data attribute.
    *
    * @return the column18 of this ivld master data attribute
    */
    @Override
    public java.lang.String getColumn18() {
        return _ivldMasterDataAttribute.getColumn18();
    }

    /**
    * Sets the column18 of this ivld master data attribute.
    *
    * @param column18 the column18 of this ivld master data attribute
    */
    @Override
    public void setColumn18(java.lang.String column18) {
        _ivldMasterDataAttribute.setColumn18(column18);
    }

    /**
    * Returns the master attribute of this ivld master data attribute.
    *
    * @return the master attribute of this ivld master data attribute
    */
    @Override
    public java.lang.String getMasterAttribute() {
        return _ivldMasterDataAttribute.getMasterAttribute();
    }

    /**
    * Sets the master attribute of this ivld master data attribute.
    *
    * @param masterAttribute the master attribute of this ivld master data attribute
    */
    @Override
    public void setMasterAttribute(java.lang.String masterAttribute) {
        _ivldMasterDataAttribute.setMasterAttribute(masterAttribute);
    }

    /**
    * Returns the master type of this ivld master data attribute.
    *
    * @return the master type of this ivld master data attribute
    */
    @Override
    public java.lang.String getMasterType() {
        return _ivldMasterDataAttribute.getMasterType();
    }

    /**
    * Sets the master type of this ivld master data attribute.
    *
    * @param masterType the master type of this ivld master data attribute
    */
    @Override
    public void setMasterType(java.lang.String masterType) {
        _ivldMasterDataAttribute.setMasterType(masterType);
    }

    /**
    * Returns the data attribute intfid of this ivld master data attribute.
    *
    * @return the data attribute intfid of this ivld master data attribute
    */
    @Override
    public java.lang.String getDataAttributeIntfid() {
        return _ivldMasterDataAttribute.getDataAttributeIntfid();
    }

    /**
    * Sets the data attribute intfid of this ivld master data attribute.
    *
    * @param dataAttributeIntfid the data attribute intfid of this ivld master data attribute
    */
    @Override
    public void setDataAttributeIntfid(java.lang.String dataAttributeIntfid) {
        _ivldMasterDataAttribute.setDataAttributeIntfid(dataAttributeIntfid);
    }

    /**
    * Returns the modified date of this ivld master data attribute.
    *
    * @return the modified date of this ivld master data attribute
    */
    @Override
    public java.util.Date getModifiedDate() {
        return _ivldMasterDataAttribute.getModifiedDate();
    }

    /**
    * Sets the modified date of this ivld master data attribute.
    *
    * @param modifiedDate the modified date of this ivld master data attribute
    */
    @Override
    public void setModifiedDate(java.util.Date modifiedDate) {
        _ivldMasterDataAttribute.setModifiedDate(modifiedDate);
    }

    /**
    * Returns the source of this ivld master data attribute.
    *
    * @return the source of this ivld master data attribute
    */
    @Override
    public java.lang.String getSource() {
        return _ivldMasterDataAttribute.getSource();
    }

    /**
    * Sets the source of this ivld master data attribute.
    *
    * @param source the source of this ivld master data attribute
    */
    @Override
    public void setSource(java.lang.String source) {
        _ivldMasterDataAttribute.setSource(source);
    }

    /**
    * Returns the created date of this ivld master data attribute.
    *
    * @return the created date of this ivld master data attribute
    */
    @Override
    public java.util.Date getCreatedDate() {
        return _ivldMasterDataAttribute.getCreatedDate();
    }

    /**
    * Sets the created date of this ivld master data attribute.
    *
    * @param createdDate the created date of this ivld master data attribute
    */
    @Override
    public void setCreatedDate(java.util.Date createdDate) {
        _ivldMasterDataAttribute.setCreatedDate(createdDate);
    }

    /**
    * Returns the created by of this ivld master data attribute.
    *
    * @return the created by of this ivld master data attribute
    */
    @Override
    public java.lang.String getCreatedBy() {
        return _ivldMasterDataAttribute.getCreatedBy();
    }

    /**
    * Sets the created by of this ivld master data attribute.
    *
    * @param createdBy the created by of this ivld master data attribute
    */
    @Override
    public void setCreatedBy(java.lang.String createdBy) {
        _ivldMasterDataAttribute.setCreatedBy(createdBy);
    }

    /**
    * Returns the add chg del indicator of this ivld master data attribute.
    *
    * @return the add chg del indicator of this ivld master data attribute
    */
    @Override
    public java.lang.String getAddChgDelIndicator() {
        return _ivldMasterDataAttribute.getAddChgDelIndicator();
    }

    /**
    * Sets the add chg del indicator of this ivld master data attribute.
    *
    * @param addChgDelIndicator the add chg del indicator of this ivld master data attribute
    */
    @Override
    public void setAddChgDelIndicator(java.lang.String addChgDelIndicator) {
        _ivldMasterDataAttribute.setAddChgDelIndicator(addChgDelIndicator);
    }

    /**
    * Returns the master ID of this ivld master data attribute.
    *
    * @return the master ID of this ivld master data attribute
    */
    @Override
    public java.lang.String getMasterId() {
        return _ivldMasterDataAttribute.getMasterId();
    }

    /**
    * Sets the master ID of this ivld master data attribute.
    *
    * @param masterId the master ID of this ivld master data attribute
    */
    @Override
    public void setMasterId(java.lang.String masterId) {
        _ivldMasterDataAttribute.setMasterId(masterId);
    }

    /**
    * Returns the column10 of this ivld master data attribute.
    *
    * @return the column10 of this ivld master data attribute
    */
    @Override
    public java.lang.String getColumn10() {
        return _ivldMasterDataAttribute.getColumn10();
    }

    /**
    * Sets the column10 of this ivld master data attribute.
    *
    * @param column10 the column10 of this ivld master data attribute
    */
    @Override
    public void setColumn10(java.lang.String column10) {
        _ivldMasterDataAttribute.setColumn10(column10);
    }

    /**
    * Returns the column11 of this ivld master data attribute.
    *
    * @return the column11 of this ivld master data attribute
    */
    @Override
    public java.lang.String getColumn11() {
        return _ivldMasterDataAttribute.getColumn11();
    }

    /**
    * Sets the column11 of this ivld master data attribute.
    *
    * @param column11 the column11 of this ivld master data attribute
    */
    @Override
    public void setColumn11(java.lang.String column11) {
        _ivldMasterDataAttribute.setColumn11(column11);
    }

    /**
    * Returns the error code of this ivld master data attribute.
    *
    * @return the error code of this ivld master data attribute
    */
    @Override
    public java.lang.String getErrorCode() {
        return _ivldMasterDataAttribute.getErrorCode();
    }

    /**
    * Sets the error code of this ivld master data attribute.
    *
    * @param errorCode the error code of this ivld master data attribute
    */
    @Override
    public void setErrorCode(java.lang.String errorCode) {
        _ivldMasterDataAttribute.setErrorCode(errorCode);
    }

    /**
    * Returns the column12 of this ivld master data attribute.
    *
    * @return the column12 of this ivld master data attribute
    */
    @Override
    public java.lang.String getColumn12() {
        return _ivldMasterDataAttribute.getColumn12();
    }

    /**
    * Sets the column12 of this ivld master data attribute.
    *
    * @param column12 the column12 of this ivld master data attribute
    */
    @Override
    public void setColumn12(java.lang.String column12) {
        _ivldMasterDataAttribute.setColumn12(column12);
    }

    /**
    * Returns the intf inserted date of this ivld master data attribute.
    *
    * @return the intf inserted date of this ivld master data attribute
    */
    @Override
    public java.util.Date getIntfInsertedDate() {
        return _ivldMasterDataAttribute.getIntfInsertedDate();
    }

    /**
    * Sets the intf inserted date of this ivld master data attribute.
    *
    * @param intfInsertedDate the intf inserted date of this ivld master data attribute
    */
    @Override
    public void setIntfInsertedDate(java.util.Date intfInsertedDate) {
        _ivldMasterDataAttribute.setIntfInsertedDate(intfInsertedDate);
    }

    /**
    * Returns the modified by of this ivld master data attribute.
    *
    * @return the modified by of this ivld master data attribute
    */
    @Override
    public java.lang.String getModifiedBy() {
        return _ivldMasterDataAttribute.getModifiedBy();
    }

    /**
    * Sets the modified by of this ivld master data attribute.
    *
    * @param modifiedBy the modified by of this ivld master data attribute
    */
    @Override
    public void setModifiedBy(java.lang.String modifiedBy) {
        _ivldMasterDataAttribute.setModifiedBy(modifiedBy);
    }

    /**
    * Returns the column13 of this ivld master data attribute.
    *
    * @return the column13 of this ivld master data attribute
    */
    @Override
    public java.lang.String getColumn13() {
        return _ivldMasterDataAttribute.getColumn13();
    }

    /**
    * Sets the column13 of this ivld master data attribute.
    *
    * @param column13 the column13 of this ivld master data attribute
    */
    @Override
    public void setColumn13(java.lang.String column13) {
        _ivldMasterDataAttribute.setColumn13(column13);
    }

    /**
    * Returns the column14 of this ivld master data attribute.
    *
    * @return the column14 of this ivld master data attribute
    */
    @Override
    public java.lang.String getColumn14() {
        return _ivldMasterDataAttribute.getColumn14();
    }

    /**
    * Sets the column14 of this ivld master data attribute.
    *
    * @param column14 the column14 of this ivld master data attribute
    */
    @Override
    public void setColumn14(java.lang.String column14) {
        _ivldMasterDataAttribute.setColumn14(column14);
    }

    /**
    * Returns the reprocessed flag of this ivld master data attribute.
    *
    * @return the reprocessed flag of this ivld master data attribute
    */
    @Override
    public java.lang.String getReprocessedFlag() {
        return _ivldMasterDataAttribute.getReprocessedFlag();
    }

    /**
    * Sets the reprocessed flag of this ivld master data attribute.
    *
    * @param reprocessedFlag the reprocessed flag of this ivld master data attribute
    */
    @Override
    public void setReprocessedFlag(java.lang.String reprocessedFlag) {
        _ivldMasterDataAttribute.setReprocessedFlag(reprocessedFlag);
    }

    /**
    * Returns the column15 of this ivld master data attribute.
    *
    * @return the column15 of this ivld master data attribute
    */
    @Override
    public java.lang.String getColumn15() {
        return _ivldMasterDataAttribute.getColumn15();
    }

    /**
    * Sets the column15 of this ivld master data attribute.
    *
    * @param column15 the column15 of this ivld master data attribute
    */
    @Override
    public void setColumn15(java.lang.String column15) {
        _ivldMasterDataAttribute.setColumn15(column15);
    }

    /**
    * Returns the column16 of this ivld master data attribute.
    *
    * @return the column16 of this ivld master data attribute
    */
    @Override
    public java.lang.String getColumn16() {
        return _ivldMasterDataAttribute.getColumn16();
    }

    /**
    * Sets the column16 of this ivld master data attribute.
    *
    * @param column16 the column16 of this ivld master data attribute
    */
    @Override
    public void setColumn16(java.lang.String column16) {
        _ivldMasterDataAttribute.setColumn16(column16);
    }

    /**
    * Returns the column17 of this ivld master data attribute.
    *
    * @return the column17 of this ivld master data attribute
    */
    @Override
    public java.lang.String getColumn17() {
        return _ivldMasterDataAttribute.getColumn17();
    }

    /**
    * Sets the column17 of this ivld master data attribute.
    *
    * @param column17 the column17 of this ivld master data attribute
    */
    @Override
    public void setColumn17(java.lang.String column17) {
        _ivldMasterDataAttribute.setColumn17(column17);
    }

    /**
    * Returns the column4 of this ivld master data attribute.
    *
    * @return the column4 of this ivld master data attribute
    */
    @Override
    public java.lang.String getColumn4() {
        return _ivldMasterDataAttribute.getColumn4();
    }

    /**
    * Sets the column4 of this ivld master data attribute.
    *
    * @param column4 the column4 of this ivld master data attribute
    */
    @Override
    public void setColumn4(java.lang.String column4) {
        _ivldMasterDataAttribute.setColumn4(column4);
    }

    /**
    * Returns the column3 of this ivld master data attribute.
    *
    * @return the column3 of this ivld master data attribute
    */
    @Override
    public java.lang.String getColumn3() {
        return _ivldMasterDataAttribute.getColumn3();
    }

    /**
    * Sets the column3 of this ivld master data attribute.
    *
    * @param column3 the column3 of this ivld master data attribute
    */
    @Override
    public void setColumn3(java.lang.String column3) {
        _ivldMasterDataAttribute.setColumn3(column3);
    }

    /**
    * Returns the column2 of this ivld master data attribute.
    *
    * @return the column2 of this ivld master data attribute
    */
    @Override
    public java.lang.String getColumn2() {
        return _ivldMasterDataAttribute.getColumn2();
    }

    /**
    * Sets the column2 of this ivld master data attribute.
    *
    * @param column2 the column2 of this ivld master data attribute
    */
    @Override
    public void setColumn2(java.lang.String column2) {
        _ivldMasterDataAttribute.setColumn2(column2);
    }

    /**
    * Returns the column1 of this ivld master data attribute.
    *
    * @return the column1 of this ivld master data attribute
    */
    @Override
    public java.lang.String getColumn1() {
        return _ivldMasterDataAttribute.getColumn1();
    }

    /**
    * Sets the column1 of this ivld master data attribute.
    *
    * @param column1 the column1 of this ivld master data attribute
    */
    @Override
    public void setColumn1(java.lang.String column1) {
        _ivldMasterDataAttribute.setColumn1(column1);
    }

    /**
    * Returns the column8 of this ivld master data attribute.
    *
    * @return the column8 of this ivld master data attribute
    */
    @Override
    public java.lang.String getColumn8() {
        return _ivldMasterDataAttribute.getColumn8();
    }

    /**
    * Sets the column8 of this ivld master data attribute.
    *
    * @param column8 the column8 of this ivld master data attribute
    */
    @Override
    public void setColumn8(java.lang.String column8) {
        _ivldMasterDataAttribute.setColumn8(column8);
    }

    /**
    * Returns the reason for failure of this ivld master data attribute.
    *
    * @return the reason for failure of this ivld master data attribute
    */
    @Override
    public java.lang.String getReasonForFailure() {
        return _ivldMasterDataAttribute.getReasonForFailure();
    }

    /**
    * Sets the reason for failure of this ivld master data attribute.
    *
    * @param reasonForFailure the reason for failure of this ivld master data attribute
    */
    @Override
    public void setReasonForFailure(java.lang.String reasonForFailure) {
        _ivldMasterDataAttribute.setReasonForFailure(reasonForFailure);
    }

    /**
    * Returns the column7 of this ivld master data attribute.
    *
    * @return the column7 of this ivld master data attribute
    */
    @Override
    public java.lang.String getColumn7() {
        return _ivldMasterDataAttribute.getColumn7();
    }

    /**
    * Sets the column7 of this ivld master data attribute.
    *
    * @param column7 the column7 of this ivld master data attribute
    */
    @Override
    public void setColumn7(java.lang.String column7) {
        _ivldMasterDataAttribute.setColumn7(column7);
    }

    /**
    * Returns the column6 of this ivld master data attribute.
    *
    * @return the column6 of this ivld master data attribute
    */
    @Override
    public java.lang.String getColumn6() {
        return _ivldMasterDataAttribute.getColumn6();
    }

    /**
    * Sets the column6 of this ivld master data attribute.
    *
    * @param column6 the column6 of this ivld master data attribute
    */
    @Override
    public void setColumn6(java.lang.String column6) {
        _ivldMasterDataAttribute.setColumn6(column6);
    }

    /**
    * Returns the column5 of this ivld master data attribute.
    *
    * @return the column5 of this ivld master data attribute
    */
    @Override
    public java.lang.String getColumn5() {
        return _ivldMasterDataAttribute.getColumn5();
    }

    /**
    * Sets the column5 of this ivld master data attribute.
    *
    * @param column5 the column5 of this ivld master data attribute
    */
    @Override
    public void setColumn5(java.lang.String column5) {
        _ivldMasterDataAttribute.setColumn5(column5);
    }

    /**
    * Returns the column20 of this ivld master data attribute.
    *
    * @return the column20 of this ivld master data attribute
    */
    @Override
    public java.lang.String getColumn20() {
        return _ivldMasterDataAttribute.getColumn20();
    }

    /**
    * Sets the column20 of this ivld master data attribute.
    *
    * @param column20 the column20 of this ivld master data attribute
    */
    @Override
    public void setColumn20(java.lang.String column20) {
        _ivldMasterDataAttribute.setColumn20(column20);
    }

    /**
    * Returns the batch ID of this ivld master data attribute.
    *
    * @return the batch ID of this ivld master data attribute
    */
    @Override
    public java.lang.String getBatchId() {
        return _ivldMasterDataAttribute.getBatchId();
    }

    /**
    * Sets the batch ID of this ivld master data attribute.
    *
    * @param batchId the batch ID of this ivld master data attribute
    */
    @Override
    public void setBatchId(java.lang.String batchId) {
        _ivldMasterDataAttribute.setBatchId(batchId);
    }

    /**
    * Returns the error field of this ivld master data attribute.
    *
    * @return the error field of this ivld master data attribute
    */
    @Override
    public java.lang.String getErrorField() {
        return _ivldMasterDataAttribute.getErrorField();
    }

    /**
    * Sets the error field of this ivld master data attribute.
    *
    * @param errorField the error field of this ivld master data attribute
    */
    @Override
    public void setErrorField(java.lang.String errorField) {
        _ivldMasterDataAttribute.setErrorField(errorField);
    }

    /**
    * Returns the column9 of this ivld master data attribute.
    *
    * @return the column9 of this ivld master data attribute
    */
    @Override
    public java.lang.String getColumn9() {
        return _ivldMasterDataAttribute.getColumn9();
    }

    /**
    * Sets the column9 of this ivld master data attribute.
    *
    * @param column9 the column9 of this ivld master data attribute
    */
    @Override
    public void setColumn9(java.lang.String column9) {
        _ivldMasterDataAttribute.setColumn9(column9);
    }

    /**
    * Returns the ivld master data atbte sid of this ivld master data attribute.
    *
    * @return the ivld master data atbte sid of this ivld master data attribute
    */
    @Override
    public int getIvldMasterDataAtbteSid() {
        return _ivldMasterDataAttribute.getIvldMasterDataAtbteSid();
    }

    /**
    * Sets the ivld master data atbte sid of this ivld master data attribute.
    *
    * @param ivldMasterDataAtbteSid the ivld master data atbte sid of this ivld master data attribute
    */
    @Override
    public void setIvldMasterDataAtbteSid(int ivldMasterDataAtbteSid) {
        _ivldMasterDataAttribute.setIvldMasterDataAtbteSid(ivldMasterDataAtbteSid);
    }

    /**
    * Returns the check record of this ivld master data attribute.
    *
    * @return the check record of this ivld master data attribute
    */
    @Override
    public boolean getCheckRecord() {
        return _ivldMasterDataAttribute.getCheckRecord();
    }

    /**
    * Returns <code>true</code> if this ivld master data attribute is check record.
    *
    * @return <code>true</code> if this ivld master data attribute is check record; <code>false</code> otherwise
    */
    @Override
    public boolean isCheckRecord() {
        return _ivldMasterDataAttribute.isCheckRecord();
    }

    /**
    * Sets whether this ivld master data attribute is check record.
    *
    * @param checkRecord the check record of this ivld master data attribute
    */
    @Override
    public void setCheckRecord(boolean checkRecord) {
        _ivldMasterDataAttribute.setCheckRecord(checkRecord);
    }

    @Override
    public boolean isNew() {
        return _ivldMasterDataAttribute.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _ivldMasterDataAttribute.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _ivldMasterDataAttribute.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _ivldMasterDataAttribute.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _ivldMasterDataAttribute.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _ivldMasterDataAttribute.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _ivldMasterDataAttribute.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _ivldMasterDataAttribute.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _ivldMasterDataAttribute.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _ivldMasterDataAttribute.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _ivldMasterDataAttribute.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new IvldMasterDataAttributeWrapper((IvldMasterDataAttribute) _ivldMasterDataAttribute.clone());
    }

    @Override
    public int compareTo(IvldMasterDataAttribute ivldMasterDataAttribute) {
        return _ivldMasterDataAttribute.compareTo(ivldMasterDataAttribute);
    }

    @Override
    public int hashCode() {
        return _ivldMasterDataAttribute.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<IvldMasterDataAttribute> toCacheModel() {
        return _ivldMasterDataAttribute.toCacheModel();
    }

    @Override
    public IvldMasterDataAttribute toEscapedModel() {
        return new IvldMasterDataAttributeWrapper(_ivldMasterDataAttribute.toEscapedModel());
    }

    @Override
    public IvldMasterDataAttribute toUnescapedModel() {
        return new IvldMasterDataAttributeWrapper(_ivldMasterDataAttribute.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _ivldMasterDataAttribute.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _ivldMasterDataAttribute.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _ivldMasterDataAttribute.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof IvldMasterDataAttributeWrapper)) {
            return false;
        }

        IvldMasterDataAttributeWrapper ivldMasterDataAttributeWrapper = (IvldMasterDataAttributeWrapper) obj;

        if (Validator.equals(_ivldMasterDataAttribute,
                    ivldMasterDataAttributeWrapper._ivldMasterDataAttribute)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public IvldMasterDataAttribute getWrappedIvldMasterDataAttribute() {
        return _ivldMasterDataAttribute;
    }

    @Override
    public IvldMasterDataAttribute getWrappedModel() {
        return _ivldMasterDataAttribute;
    }

    @Override
    public void resetOriginalValues() {
        _ivldMasterDataAttribute.resetOriginalValues();
    }
}
