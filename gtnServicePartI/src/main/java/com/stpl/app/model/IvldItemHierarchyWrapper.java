package com.stpl.app.model;

import com.stpl.portal.kernel.util.Validator;
import com.stpl.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link IvldItemHierarchy}.
 * </p>
 *
 * @author
 * @see IvldItemHierarchy
 * @generated
 */
public class IvldItemHierarchyWrapper implements IvldItemHierarchy,
    ModelWrapper<IvldItemHierarchy> {
    private IvldItemHierarchy _ivldItemHierarchy;

    public IvldItemHierarchyWrapper(IvldItemHierarchy ivldItemHierarchy) {
        _ivldItemHierarchy = ivldItemHierarchy;
    }

    @Override
    public Class<?> getModelClass() {
        return IvldItemHierarchy.class;
    }

    @Override
    public String getModelClassName() {
        return IvldItemHierarchy.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("level1Alias", getLevel1Alias());
        attributes.put("level9Alias", getLevel9Alias());
        attributes.put("level3Alias", getLevel3Alias());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("level13Alias", getLevel13Alias());
        attributes.put("gtnCompany", getGtnCompany());
        attributes.put("source", getSource());
        attributes.put("level15Alias", getLevel15Alias());
        attributes.put("addChgDelIndicator", getAddChgDelIndicator());
        attributes.put("ivldItemHierarchySid", getIvldItemHierarchySid());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("level6Alias", getLevel6Alias());
        attributes.put("reasonForFailure", getReasonForFailure());
        attributes.put("level10Alias", getLevel10Alias());
        attributes.put("itemHierarchyIntfid", getItemHierarchyIntfid());
        attributes.put("level5Alias", getLevel5Alias());
        attributes.put("level18Alias", getLevel18Alias());
        attributes.put("errorField", getErrorField());
        attributes.put("gtnTherapeuticClass", getGtnTherapeuticClass());
        attributes.put("level8", getLevel8());
        attributes.put("level9", getLevel9());
        attributes.put("level11Alias", getLevel11Alias());
        attributes.put("level20", getLevel20());
        attributes.put("level4", getLevel4());
        attributes.put("level5", getLevel5());
        attributes.put("level6", getLevel6());
        attributes.put("level7", getLevel7());
        attributes.put("level16Alias", getLevel16Alias());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("gtnBrand", getGtnBrand());
        attributes.put("level2Alias", getLevel2Alias());
        attributes.put("level1", getLevel1());
        attributes.put("level0", getLevel0());
        attributes.put("errorCode", getErrorCode());
        attributes.put("level3", getLevel3());
        attributes.put("level17Alias", getLevel17Alias());
        attributes.put("level20Alias", getLevel20Alias());
        attributes.put("intfInsertedDate", getIntfInsertedDate());
        attributes.put("level7Alias", getLevel7Alias());
        attributes.put("level2", getLevel2());
        attributes.put("reprocessedFlag", getReprocessedFlag());
        attributes.put("level8Alias", getLevel8Alias());
        attributes.put("level10", getLevel10());
        attributes.put("level4Alias", getLevel4Alias());
        attributes.put("level12", getLevel12());
        attributes.put("level11", getLevel11());
        attributes.put("level14", getLevel14());
        attributes.put("level0Tag", getLevel0Tag());
        attributes.put("level13", getLevel13());
        attributes.put("level16", getLevel16());
        attributes.put("level15", getLevel15());
        attributes.put("level18", getLevel18());
        attributes.put("level17", getLevel17());
        attributes.put("level19", getLevel19());
        attributes.put("level12Alias", getLevel12Alias());
        attributes.put("level19Alias", getLevel19Alias());
        attributes.put("batchId", getBatchId());
        attributes.put("level0Alias", getLevel0Alias());
        attributes.put("level14Alias", getLevel14Alias());
        attributes.put("checkRecord", getCheckRecord());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        String level1Alias = (String) attributes.get("level1Alias");

        if (level1Alias != null) {
            setLevel1Alias(level1Alias);
        }

        String level9Alias = (String) attributes.get("level9Alias");

        if (level9Alias != null) {
            setLevel9Alias(level9Alias);
        }

        String level3Alias = (String) attributes.get("level3Alias");

        if (level3Alias != null) {
            setLevel3Alias(level3Alias);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        String level13Alias = (String) attributes.get("level13Alias");

        if (level13Alias != null) {
            setLevel13Alias(level13Alias);
        }

        String gtnCompany = (String) attributes.get("gtnCompany");

        if (gtnCompany != null) {
            setGtnCompany(gtnCompany);
        }

        String source = (String) attributes.get("source");

        if (source != null) {
            setSource(source);
        }

        String level15Alias = (String) attributes.get("level15Alias");

        if (level15Alias != null) {
            setLevel15Alias(level15Alias);
        }

        String addChgDelIndicator = (String) attributes.get(
                "addChgDelIndicator");

        if (addChgDelIndicator != null) {
            setAddChgDelIndicator(addChgDelIndicator);
        }

        Integer ivldItemHierarchySid = (Integer) attributes.get(
                "ivldItemHierarchySid");

        if (ivldItemHierarchySid != null) {
            setIvldItemHierarchySid(ivldItemHierarchySid);
        }

        String modifiedBy = (String) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        String level6Alias = (String) attributes.get("level6Alias");

        if (level6Alias != null) {
            setLevel6Alias(level6Alias);
        }

        String reasonForFailure = (String) attributes.get("reasonForFailure");

        if (reasonForFailure != null) {
            setReasonForFailure(reasonForFailure);
        }

        String level10Alias = (String) attributes.get("level10Alias");

        if (level10Alias != null) {
            setLevel10Alias(level10Alias);
        }

        String itemHierarchyIntfid = (String) attributes.get(
                "itemHierarchyIntfid");

        if (itemHierarchyIntfid != null) {
            setItemHierarchyIntfid(itemHierarchyIntfid);
        }

        String level5Alias = (String) attributes.get("level5Alias");

        if (level5Alias != null) {
            setLevel5Alias(level5Alias);
        }

        String level18Alias = (String) attributes.get("level18Alias");

        if (level18Alias != null) {
            setLevel18Alias(level18Alias);
        }

        String errorField = (String) attributes.get("errorField");

        if (errorField != null) {
            setErrorField(errorField);
        }

        String gtnTherapeuticClass = (String) attributes.get(
                "gtnTherapeuticClass");

        if (gtnTherapeuticClass != null) {
            setGtnTherapeuticClass(gtnTherapeuticClass);
        }

        String level8 = (String) attributes.get("level8");

        if (level8 != null) {
            setLevel8(level8);
        }

        String level9 = (String) attributes.get("level9");

        if (level9 != null) {
            setLevel9(level9);
        }

        String level11Alias = (String) attributes.get("level11Alias");

        if (level11Alias != null) {
            setLevel11Alias(level11Alias);
        }

        String level20 = (String) attributes.get("level20");

        if (level20 != null) {
            setLevel20(level20);
        }

        String level4 = (String) attributes.get("level4");

        if (level4 != null) {
            setLevel4(level4);
        }

        String level5 = (String) attributes.get("level5");

        if (level5 != null) {
            setLevel5(level5);
        }

        String level6 = (String) attributes.get("level6");

        if (level6 != null) {
            setLevel6(level6);
        }

        String level7 = (String) attributes.get("level7");

        if (level7 != null) {
            setLevel7(level7);
        }

        String level16Alias = (String) attributes.get("level16Alias");

        if (level16Alias != null) {
            setLevel16Alias(level16Alias);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        String createdBy = (String) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        String gtnBrand = (String) attributes.get("gtnBrand");

        if (gtnBrand != null) {
            setGtnBrand(gtnBrand);
        }

        String level2Alias = (String) attributes.get("level2Alias");

        if (level2Alias != null) {
            setLevel2Alias(level2Alias);
        }

        String level1 = (String) attributes.get("level1");

        if (level1 != null) {
            setLevel1(level1);
        }

        String level0 = (String) attributes.get("level0");

        if (level0 != null) {
            setLevel0(level0);
        }

        String errorCode = (String) attributes.get("errorCode");

        if (errorCode != null) {
            setErrorCode(errorCode);
        }

        String level3 = (String) attributes.get("level3");

        if (level3 != null) {
            setLevel3(level3);
        }

        String level17Alias = (String) attributes.get("level17Alias");

        if (level17Alias != null) {
            setLevel17Alias(level17Alias);
        }

        String level20Alias = (String) attributes.get("level20Alias");

        if (level20Alias != null) {
            setLevel20Alias(level20Alias);
        }

        Date intfInsertedDate = (Date) attributes.get("intfInsertedDate");

        if (intfInsertedDate != null) {
            setIntfInsertedDate(intfInsertedDate);
        }

        String level7Alias = (String) attributes.get("level7Alias");

        if (level7Alias != null) {
            setLevel7Alias(level7Alias);
        }

        String level2 = (String) attributes.get("level2");

        if (level2 != null) {
            setLevel2(level2);
        }

        String reprocessedFlag = (String) attributes.get("reprocessedFlag");

        if (reprocessedFlag != null) {
            setReprocessedFlag(reprocessedFlag);
        }

        String level8Alias = (String) attributes.get("level8Alias");

        if (level8Alias != null) {
            setLevel8Alias(level8Alias);
        }

        String level10 = (String) attributes.get("level10");

        if (level10 != null) {
            setLevel10(level10);
        }

        String level4Alias = (String) attributes.get("level4Alias");

        if (level4Alias != null) {
            setLevel4Alias(level4Alias);
        }

        String level12 = (String) attributes.get("level12");

        if (level12 != null) {
            setLevel12(level12);
        }

        String level11 = (String) attributes.get("level11");

        if (level11 != null) {
            setLevel11(level11);
        }

        String level14 = (String) attributes.get("level14");

        if (level14 != null) {
            setLevel14(level14);
        }

        String level0Tag = (String) attributes.get("level0Tag");

        if (level0Tag != null) {
            setLevel0Tag(level0Tag);
        }

        String level13 = (String) attributes.get("level13");

        if (level13 != null) {
            setLevel13(level13);
        }

        String level16 = (String) attributes.get("level16");

        if (level16 != null) {
            setLevel16(level16);
        }

        String level15 = (String) attributes.get("level15");

        if (level15 != null) {
            setLevel15(level15);
        }

        String level18 = (String) attributes.get("level18");

        if (level18 != null) {
            setLevel18(level18);
        }

        String level17 = (String) attributes.get("level17");

        if (level17 != null) {
            setLevel17(level17);
        }

        String level19 = (String) attributes.get("level19");

        if (level19 != null) {
            setLevel19(level19);
        }

        String level12Alias = (String) attributes.get("level12Alias");

        if (level12Alias != null) {
            setLevel12Alias(level12Alias);
        }

        String level19Alias = (String) attributes.get("level19Alias");

        if (level19Alias != null) {
            setLevel19Alias(level19Alias);
        }

        String batchId = (String) attributes.get("batchId");

        if (batchId != null) {
            setBatchId(batchId);
        }

        String level0Alias = (String) attributes.get("level0Alias");

        if (level0Alias != null) {
            setLevel0Alias(level0Alias);
        }

        String level14Alias = (String) attributes.get("level14Alias");

        if (level14Alias != null) {
            setLevel14Alias(level14Alias);
        }

        Boolean checkRecord = (Boolean) attributes.get("checkRecord");

        if (checkRecord != null) {
            setCheckRecord(checkRecord);
        }
    }

    /**
    * Returns the primary key of this ivld item hierarchy.
    *
    * @return the primary key of this ivld item hierarchy
    */
    @Override
    public int getPrimaryKey() {
        return _ivldItemHierarchy.getPrimaryKey();
    }

    /**
    * Sets the primary key of this ivld item hierarchy.
    *
    * @param primaryKey the primary key of this ivld item hierarchy
    */
    @Override
    public void setPrimaryKey(int primaryKey) {
        _ivldItemHierarchy.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the level1 alias of this ivld item hierarchy.
    *
    * @return the level1 alias of this ivld item hierarchy
    */
    @Override
    public java.lang.String getLevel1Alias() {
        return _ivldItemHierarchy.getLevel1Alias();
    }

    /**
    * Sets the level1 alias of this ivld item hierarchy.
    *
    * @param level1Alias the level1 alias of this ivld item hierarchy
    */
    @Override
    public void setLevel1Alias(java.lang.String level1Alias) {
        _ivldItemHierarchy.setLevel1Alias(level1Alias);
    }

    /**
    * Returns the level9 alias of this ivld item hierarchy.
    *
    * @return the level9 alias of this ivld item hierarchy
    */
    @Override
    public java.lang.String getLevel9Alias() {
        return _ivldItemHierarchy.getLevel9Alias();
    }

    /**
    * Sets the level9 alias of this ivld item hierarchy.
    *
    * @param level9Alias the level9 alias of this ivld item hierarchy
    */
    @Override
    public void setLevel9Alias(java.lang.String level9Alias) {
        _ivldItemHierarchy.setLevel9Alias(level9Alias);
    }

    /**
    * Returns the level3 alias of this ivld item hierarchy.
    *
    * @return the level3 alias of this ivld item hierarchy
    */
    @Override
    public java.lang.String getLevel3Alias() {
        return _ivldItemHierarchy.getLevel3Alias();
    }

    /**
    * Sets the level3 alias of this ivld item hierarchy.
    *
    * @param level3Alias the level3 alias of this ivld item hierarchy
    */
    @Override
    public void setLevel3Alias(java.lang.String level3Alias) {
        _ivldItemHierarchy.setLevel3Alias(level3Alias);
    }

    /**
    * Returns the modified date of this ivld item hierarchy.
    *
    * @return the modified date of this ivld item hierarchy
    */
    @Override
    public java.util.Date getModifiedDate() {
        return _ivldItemHierarchy.getModifiedDate();
    }

    /**
    * Sets the modified date of this ivld item hierarchy.
    *
    * @param modifiedDate the modified date of this ivld item hierarchy
    */
    @Override
    public void setModifiedDate(java.util.Date modifiedDate) {
        _ivldItemHierarchy.setModifiedDate(modifiedDate);
    }

    /**
    * Returns the level13 alias of this ivld item hierarchy.
    *
    * @return the level13 alias of this ivld item hierarchy
    */
    @Override
    public java.lang.String getLevel13Alias() {
        return _ivldItemHierarchy.getLevel13Alias();
    }

    /**
    * Sets the level13 alias of this ivld item hierarchy.
    *
    * @param level13Alias the level13 alias of this ivld item hierarchy
    */
    @Override
    public void setLevel13Alias(java.lang.String level13Alias) {
        _ivldItemHierarchy.setLevel13Alias(level13Alias);
    }

    /**
    * Returns the gtn company of this ivld item hierarchy.
    *
    * @return the gtn company of this ivld item hierarchy
    */
    @Override
    public java.lang.String getGtnCompany() {
        return _ivldItemHierarchy.getGtnCompany();
    }

    /**
    * Sets the gtn company of this ivld item hierarchy.
    *
    * @param gtnCompany the gtn company of this ivld item hierarchy
    */
    @Override
    public void setGtnCompany(java.lang.String gtnCompany) {
        _ivldItemHierarchy.setGtnCompany(gtnCompany);
    }

    /**
    * Returns the source of this ivld item hierarchy.
    *
    * @return the source of this ivld item hierarchy
    */
    @Override
    public java.lang.String getSource() {
        return _ivldItemHierarchy.getSource();
    }

    /**
    * Sets the source of this ivld item hierarchy.
    *
    * @param source the source of this ivld item hierarchy
    */
    @Override
    public void setSource(java.lang.String source) {
        _ivldItemHierarchy.setSource(source);
    }

    /**
    * Returns the level15 alias of this ivld item hierarchy.
    *
    * @return the level15 alias of this ivld item hierarchy
    */
    @Override
    public java.lang.String getLevel15Alias() {
        return _ivldItemHierarchy.getLevel15Alias();
    }

    /**
    * Sets the level15 alias of this ivld item hierarchy.
    *
    * @param level15Alias the level15 alias of this ivld item hierarchy
    */
    @Override
    public void setLevel15Alias(java.lang.String level15Alias) {
        _ivldItemHierarchy.setLevel15Alias(level15Alias);
    }

    /**
    * Returns the add chg del indicator of this ivld item hierarchy.
    *
    * @return the add chg del indicator of this ivld item hierarchy
    */
    @Override
    public java.lang.String getAddChgDelIndicator() {
        return _ivldItemHierarchy.getAddChgDelIndicator();
    }

    /**
    * Sets the add chg del indicator of this ivld item hierarchy.
    *
    * @param addChgDelIndicator the add chg del indicator of this ivld item hierarchy
    */
    @Override
    public void setAddChgDelIndicator(java.lang.String addChgDelIndicator) {
        _ivldItemHierarchy.setAddChgDelIndicator(addChgDelIndicator);
    }

    /**
    * Returns the ivld item hierarchy sid of this ivld item hierarchy.
    *
    * @return the ivld item hierarchy sid of this ivld item hierarchy
    */
    @Override
    public int getIvldItemHierarchySid() {
        return _ivldItemHierarchy.getIvldItemHierarchySid();
    }

    /**
    * Sets the ivld item hierarchy sid of this ivld item hierarchy.
    *
    * @param ivldItemHierarchySid the ivld item hierarchy sid of this ivld item hierarchy
    */
    @Override
    public void setIvldItemHierarchySid(int ivldItemHierarchySid) {
        _ivldItemHierarchy.setIvldItemHierarchySid(ivldItemHierarchySid);
    }

    /**
    * Returns the modified by of this ivld item hierarchy.
    *
    * @return the modified by of this ivld item hierarchy
    */
    @Override
    public java.lang.String getModifiedBy() {
        return _ivldItemHierarchy.getModifiedBy();
    }

    /**
    * Sets the modified by of this ivld item hierarchy.
    *
    * @param modifiedBy the modified by of this ivld item hierarchy
    */
    @Override
    public void setModifiedBy(java.lang.String modifiedBy) {
        _ivldItemHierarchy.setModifiedBy(modifiedBy);
    }

    /**
    * Returns the level6 alias of this ivld item hierarchy.
    *
    * @return the level6 alias of this ivld item hierarchy
    */
    @Override
    public java.lang.String getLevel6Alias() {
        return _ivldItemHierarchy.getLevel6Alias();
    }

    /**
    * Sets the level6 alias of this ivld item hierarchy.
    *
    * @param level6Alias the level6 alias of this ivld item hierarchy
    */
    @Override
    public void setLevel6Alias(java.lang.String level6Alias) {
        _ivldItemHierarchy.setLevel6Alias(level6Alias);
    }

    /**
    * Returns the reason for failure of this ivld item hierarchy.
    *
    * @return the reason for failure of this ivld item hierarchy
    */
    @Override
    public java.lang.String getReasonForFailure() {
        return _ivldItemHierarchy.getReasonForFailure();
    }

    /**
    * Sets the reason for failure of this ivld item hierarchy.
    *
    * @param reasonForFailure the reason for failure of this ivld item hierarchy
    */
    @Override
    public void setReasonForFailure(java.lang.String reasonForFailure) {
        _ivldItemHierarchy.setReasonForFailure(reasonForFailure);
    }

    /**
    * Returns the level10 alias of this ivld item hierarchy.
    *
    * @return the level10 alias of this ivld item hierarchy
    */
    @Override
    public java.lang.String getLevel10Alias() {
        return _ivldItemHierarchy.getLevel10Alias();
    }

    /**
    * Sets the level10 alias of this ivld item hierarchy.
    *
    * @param level10Alias the level10 alias of this ivld item hierarchy
    */
    @Override
    public void setLevel10Alias(java.lang.String level10Alias) {
        _ivldItemHierarchy.setLevel10Alias(level10Alias);
    }

    /**
    * Returns the item hierarchy intfid of this ivld item hierarchy.
    *
    * @return the item hierarchy intfid of this ivld item hierarchy
    */
    @Override
    public java.lang.String getItemHierarchyIntfid() {
        return _ivldItemHierarchy.getItemHierarchyIntfid();
    }

    /**
    * Sets the item hierarchy intfid of this ivld item hierarchy.
    *
    * @param itemHierarchyIntfid the item hierarchy intfid of this ivld item hierarchy
    */
    @Override
    public void setItemHierarchyIntfid(java.lang.String itemHierarchyIntfid) {
        _ivldItemHierarchy.setItemHierarchyIntfid(itemHierarchyIntfid);
    }

    /**
    * Returns the level5 alias of this ivld item hierarchy.
    *
    * @return the level5 alias of this ivld item hierarchy
    */
    @Override
    public java.lang.String getLevel5Alias() {
        return _ivldItemHierarchy.getLevel5Alias();
    }

    /**
    * Sets the level5 alias of this ivld item hierarchy.
    *
    * @param level5Alias the level5 alias of this ivld item hierarchy
    */
    @Override
    public void setLevel5Alias(java.lang.String level5Alias) {
        _ivldItemHierarchy.setLevel5Alias(level5Alias);
    }

    /**
    * Returns the level18 alias of this ivld item hierarchy.
    *
    * @return the level18 alias of this ivld item hierarchy
    */
    @Override
    public java.lang.String getLevel18Alias() {
        return _ivldItemHierarchy.getLevel18Alias();
    }

    /**
    * Sets the level18 alias of this ivld item hierarchy.
    *
    * @param level18Alias the level18 alias of this ivld item hierarchy
    */
    @Override
    public void setLevel18Alias(java.lang.String level18Alias) {
        _ivldItemHierarchy.setLevel18Alias(level18Alias);
    }

    /**
    * Returns the error field of this ivld item hierarchy.
    *
    * @return the error field of this ivld item hierarchy
    */
    @Override
    public java.lang.String getErrorField() {
        return _ivldItemHierarchy.getErrorField();
    }

    /**
    * Sets the error field of this ivld item hierarchy.
    *
    * @param errorField the error field of this ivld item hierarchy
    */
    @Override
    public void setErrorField(java.lang.String errorField) {
        _ivldItemHierarchy.setErrorField(errorField);
    }

    /**
    * Returns the gtn therapeutic class of this ivld item hierarchy.
    *
    * @return the gtn therapeutic class of this ivld item hierarchy
    */
    @Override
    public java.lang.String getGtnTherapeuticClass() {
        return _ivldItemHierarchy.getGtnTherapeuticClass();
    }

    /**
    * Sets the gtn therapeutic class of this ivld item hierarchy.
    *
    * @param gtnTherapeuticClass the gtn therapeutic class of this ivld item hierarchy
    */
    @Override
    public void setGtnTherapeuticClass(java.lang.String gtnTherapeuticClass) {
        _ivldItemHierarchy.setGtnTherapeuticClass(gtnTherapeuticClass);
    }

    /**
    * Returns the level8 of this ivld item hierarchy.
    *
    * @return the level8 of this ivld item hierarchy
    */
    @Override
    public java.lang.String getLevel8() {
        return _ivldItemHierarchy.getLevel8();
    }

    /**
    * Sets the level8 of this ivld item hierarchy.
    *
    * @param level8 the level8 of this ivld item hierarchy
    */
    @Override
    public void setLevel8(java.lang.String level8) {
        _ivldItemHierarchy.setLevel8(level8);
    }

    /**
    * Returns the level9 of this ivld item hierarchy.
    *
    * @return the level9 of this ivld item hierarchy
    */
    @Override
    public java.lang.String getLevel9() {
        return _ivldItemHierarchy.getLevel9();
    }

    /**
    * Sets the level9 of this ivld item hierarchy.
    *
    * @param level9 the level9 of this ivld item hierarchy
    */
    @Override
    public void setLevel9(java.lang.String level9) {
        _ivldItemHierarchy.setLevel9(level9);
    }

    /**
    * Returns the level11 alias of this ivld item hierarchy.
    *
    * @return the level11 alias of this ivld item hierarchy
    */
    @Override
    public java.lang.String getLevel11Alias() {
        return _ivldItemHierarchy.getLevel11Alias();
    }

    /**
    * Sets the level11 alias of this ivld item hierarchy.
    *
    * @param level11Alias the level11 alias of this ivld item hierarchy
    */
    @Override
    public void setLevel11Alias(java.lang.String level11Alias) {
        _ivldItemHierarchy.setLevel11Alias(level11Alias);
    }

    /**
    * Returns the level20 of this ivld item hierarchy.
    *
    * @return the level20 of this ivld item hierarchy
    */
    @Override
    public java.lang.String getLevel20() {
        return _ivldItemHierarchy.getLevel20();
    }

    /**
    * Sets the level20 of this ivld item hierarchy.
    *
    * @param level20 the level20 of this ivld item hierarchy
    */
    @Override
    public void setLevel20(java.lang.String level20) {
        _ivldItemHierarchy.setLevel20(level20);
    }

    /**
    * Returns the level4 of this ivld item hierarchy.
    *
    * @return the level4 of this ivld item hierarchy
    */
    @Override
    public java.lang.String getLevel4() {
        return _ivldItemHierarchy.getLevel4();
    }

    /**
    * Sets the level4 of this ivld item hierarchy.
    *
    * @param level4 the level4 of this ivld item hierarchy
    */
    @Override
    public void setLevel4(java.lang.String level4) {
        _ivldItemHierarchy.setLevel4(level4);
    }

    /**
    * Returns the level5 of this ivld item hierarchy.
    *
    * @return the level5 of this ivld item hierarchy
    */
    @Override
    public java.lang.String getLevel5() {
        return _ivldItemHierarchy.getLevel5();
    }

    /**
    * Sets the level5 of this ivld item hierarchy.
    *
    * @param level5 the level5 of this ivld item hierarchy
    */
    @Override
    public void setLevel5(java.lang.String level5) {
        _ivldItemHierarchy.setLevel5(level5);
    }

    /**
    * Returns the level6 of this ivld item hierarchy.
    *
    * @return the level6 of this ivld item hierarchy
    */
    @Override
    public java.lang.String getLevel6() {
        return _ivldItemHierarchy.getLevel6();
    }

    /**
    * Sets the level6 of this ivld item hierarchy.
    *
    * @param level6 the level6 of this ivld item hierarchy
    */
    @Override
    public void setLevel6(java.lang.String level6) {
        _ivldItemHierarchy.setLevel6(level6);
    }

    /**
    * Returns the level7 of this ivld item hierarchy.
    *
    * @return the level7 of this ivld item hierarchy
    */
    @Override
    public java.lang.String getLevel7() {
        return _ivldItemHierarchy.getLevel7();
    }

    /**
    * Sets the level7 of this ivld item hierarchy.
    *
    * @param level7 the level7 of this ivld item hierarchy
    */
    @Override
    public void setLevel7(java.lang.String level7) {
        _ivldItemHierarchy.setLevel7(level7);
    }

    /**
    * Returns the level16 alias of this ivld item hierarchy.
    *
    * @return the level16 alias of this ivld item hierarchy
    */
    @Override
    public java.lang.String getLevel16Alias() {
        return _ivldItemHierarchy.getLevel16Alias();
    }

    /**
    * Sets the level16 alias of this ivld item hierarchy.
    *
    * @param level16Alias the level16 alias of this ivld item hierarchy
    */
    @Override
    public void setLevel16Alias(java.lang.String level16Alias) {
        _ivldItemHierarchy.setLevel16Alias(level16Alias);
    }

    /**
    * Returns the created date of this ivld item hierarchy.
    *
    * @return the created date of this ivld item hierarchy
    */
    @Override
    public java.util.Date getCreatedDate() {
        return _ivldItemHierarchy.getCreatedDate();
    }

    /**
    * Sets the created date of this ivld item hierarchy.
    *
    * @param createdDate the created date of this ivld item hierarchy
    */
    @Override
    public void setCreatedDate(java.util.Date createdDate) {
        _ivldItemHierarchy.setCreatedDate(createdDate);
    }

    /**
    * Returns the created by of this ivld item hierarchy.
    *
    * @return the created by of this ivld item hierarchy
    */
    @Override
    public java.lang.String getCreatedBy() {
        return _ivldItemHierarchy.getCreatedBy();
    }

    /**
    * Sets the created by of this ivld item hierarchy.
    *
    * @param createdBy the created by of this ivld item hierarchy
    */
    @Override
    public void setCreatedBy(java.lang.String createdBy) {
        _ivldItemHierarchy.setCreatedBy(createdBy);
    }

    /**
    * Returns the gtn brand of this ivld item hierarchy.
    *
    * @return the gtn brand of this ivld item hierarchy
    */
    @Override
    public java.lang.String getGtnBrand() {
        return _ivldItemHierarchy.getGtnBrand();
    }

    /**
    * Sets the gtn brand of this ivld item hierarchy.
    *
    * @param gtnBrand the gtn brand of this ivld item hierarchy
    */
    @Override
    public void setGtnBrand(java.lang.String gtnBrand) {
        _ivldItemHierarchy.setGtnBrand(gtnBrand);
    }

    /**
    * Returns the level2 alias of this ivld item hierarchy.
    *
    * @return the level2 alias of this ivld item hierarchy
    */
    @Override
    public java.lang.String getLevel2Alias() {
        return _ivldItemHierarchy.getLevel2Alias();
    }

    /**
    * Sets the level2 alias of this ivld item hierarchy.
    *
    * @param level2Alias the level2 alias of this ivld item hierarchy
    */
    @Override
    public void setLevel2Alias(java.lang.String level2Alias) {
        _ivldItemHierarchy.setLevel2Alias(level2Alias);
    }

    /**
    * Returns the level1 of this ivld item hierarchy.
    *
    * @return the level1 of this ivld item hierarchy
    */
    @Override
    public java.lang.String getLevel1() {
        return _ivldItemHierarchy.getLevel1();
    }

    /**
    * Sets the level1 of this ivld item hierarchy.
    *
    * @param level1 the level1 of this ivld item hierarchy
    */
    @Override
    public void setLevel1(java.lang.String level1) {
        _ivldItemHierarchy.setLevel1(level1);
    }

    /**
    * Returns the level0 of this ivld item hierarchy.
    *
    * @return the level0 of this ivld item hierarchy
    */
    @Override
    public java.lang.String getLevel0() {
        return _ivldItemHierarchy.getLevel0();
    }

    /**
    * Sets the level0 of this ivld item hierarchy.
    *
    * @param level0 the level0 of this ivld item hierarchy
    */
    @Override
    public void setLevel0(java.lang.String level0) {
        _ivldItemHierarchy.setLevel0(level0);
    }

    /**
    * Returns the error code of this ivld item hierarchy.
    *
    * @return the error code of this ivld item hierarchy
    */
    @Override
    public java.lang.String getErrorCode() {
        return _ivldItemHierarchy.getErrorCode();
    }

    /**
    * Sets the error code of this ivld item hierarchy.
    *
    * @param errorCode the error code of this ivld item hierarchy
    */
    @Override
    public void setErrorCode(java.lang.String errorCode) {
        _ivldItemHierarchy.setErrorCode(errorCode);
    }

    /**
    * Returns the level3 of this ivld item hierarchy.
    *
    * @return the level3 of this ivld item hierarchy
    */
    @Override
    public java.lang.String getLevel3() {
        return _ivldItemHierarchy.getLevel3();
    }

    /**
    * Sets the level3 of this ivld item hierarchy.
    *
    * @param level3 the level3 of this ivld item hierarchy
    */
    @Override
    public void setLevel3(java.lang.String level3) {
        _ivldItemHierarchy.setLevel3(level3);
    }

    /**
    * Returns the level17 alias of this ivld item hierarchy.
    *
    * @return the level17 alias of this ivld item hierarchy
    */
    @Override
    public java.lang.String getLevel17Alias() {
        return _ivldItemHierarchy.getLevel17Alias();
    }

    /**
    * Sets the level17 alias of this ivld item hierarchy.
    *
    * @param level17Alias the level17 alias of this ivld item hierarchy
    */
    @Override
    public void setLevel17Alias(java.lang.String level17Alias) {
        _ivldItemHierarchy.setLevel17Alias(level17Alias);
    }

    /**
    * Returns the level20 alias of this ivld item hierarchy.
    *
    * @return the level20 alias of this ivld item hierarchy
    */
    @Override
    public java.lang.String getLevel20Alias() {
        return _ivldItemHierarchy.getLevel20Alias();
    }

    /**
    * Sets the level20 alias of this ivld item hierarchy.
    *
    * @param level20Alias the level20 alias of this ivld item hierarchy
    */
    @Override
    public void setLevel20Alias(java.lang.String level20Alias) {
        _ivldItemHierarchy.setLevel20Alias(level20Alias);
    }

    /**
    * Returns the intf inserted date of this ivld item hierarchy.
    *
    * @return the intf inserted date of this ivld item hierarchy
    */
    @Override
    public java.util.Date getIntfInsertedDate() {
        return _ivldItemHierarchy.getIntfInsertedDate();
    }

    /**
    * Sets the intf inserted date of this ivld item hierarchy.
    *
    * @param intfInsertedDate the intf inserted date of this ivld item hierarchy
    */
    @Override
    public void setIntfInsertedDate(java.util.Date intfInsertedDate) {
        _ivldItemHierarchy.setIntfInsertedDate(intfInsertedDate);
    }

    /**
    * Returns the level7 alias of this ivld item hierarchy.
    *
    * @return the level7 alias of this ivld item hierarchy
    */
    @Override
    public java.lang.String getLevel7Alias() {
        return _ivldItemHierarchy.getLevel7Alias();
    }

    /**
    * Sets the level7 alias of this ivld item hierarchy.
    *
    * @param level7Alias the level7 alias of this ivld item hierarchy
    */
    @Override
    public void setLevel7Alias(java.lang.String level7Alias) {
        _ivldItemHierarchy.setLevel7Alias(level7Alias);
    }

    /**
    * Returns the level2 of this ivld item hierarchy.
    *
    * @return the level2 of this ivld item hierarchy
    */
    @Override
    public java.lang.String getLevel2() {
        return _ivldItemHierarchy.getLevel2();
    }

    /**
    * Sets the level2 of this ivld item hierarchy.
    *
    * @param level2 the level2 of this ivld item hierarchy
    */
    @Override
    public void setLevel2(java.lang.String level2) {
        _ivldItemHierarchy.setLevel2(level2);
    }

    /**
    * Returns the reprocessed flag of this ivld item hierarchy.
    *
    * @return the reprocessed flag of this ivld item hierarchy
    */
    @Override
    public java.lang.String getReprocessedFlag() {
        return _ivldItemHierarchy.getReprocessedFlag();
    }

    /**
    * Sets the reprocessed flag of this ivld item hierarchy.
    *
    * @param reprocessedFlag the reprocessed flag of this ivld item hierarchy
    */
    @Override
    public void setReprocessedFlag(java.lang.String reprocessedFlag) {
        _ivldItemHierarchy.setReprocessedFlag(reprocessedFlag);
    }

    /**
    * Returns the level8 alias of this ivld item hierarchy.
    *
    * @return the level8 alias of this ivld item hierarchy
    */
    @Override
    public java.lang.String getLevel8Alias() {
        return _ivldItemHierarchy.getLevel8Alias();
    }

    /**
    * Sets the level8 alias of this ivld item hierarchy.
    *
    * @param level8Alias the level8 alias of this ivld item hierarchy
    */
    @Override
    public void setLevel8Alias(java.lang.String level8Alias) {
        _ivldItemHierarchy.setLevel8Alias(level8Alias);
    }

    /**
    * Returns the level10 of this ivld item hierarchy.
    *
    * @return the level10 of this ivld item hierarchy
    */
    @Override
    public java.lang.String getLevel10() {
        return _ivldItemHierarchy.getLevel10();
    }

    /**
    * Sets the level10 of this ivld item hierarchy.
    *
    * @param level10 the level10 of this ivld item hierarchy
    */
    @Override
    public void setLevel10(java.lang.String level10) {
        _ivldItemHierarchy.setLevel10(level10);
    }

    /**
    * Returns the level4 alias of this ivld item hierarchy.
    *
    * @return the level4 alias of this ivld item hierarchy
    */
    @Override
    public java.lang.String getLevel4Alias() {
        return _ivldItemHierarchy.getLevel4Alias();
    }

    /**
    * Sets the level4 alias of this ivld item hierarchy.
    *
    * @param level4Alias the level4 alias of this ivld item hierarchy
    */
    @Override
    public void setLevel4Alias(java.lang.String level4Alias) {
        _ivldItemHierarchy.setLevel4Alias(level4Alias);
    }

    /**
    * Returns the level12 of this ivld item hierarchy.
    *
    * @return the level12 of this ivld item hierarchy
    */
    @Override
    public java.lang.String getLevel12() {
        return _ivldItemHierarchy.getLevel12();
    }

    /**
    * Sets the level12 of this ivld item hierarchy.
    *
    * @param level12 the level12 of this ivld item hierarchy
    */
    @Override
    public void setLevel12(java.lang.String level12) {
        _ivldItemHierarchy.setLevel12(level12);
    }

    /**
    * Returns the level11 of this ivld item hierarchy.
    *
    * @return the level11 of this ivld item hierarchy
    */
    @Override
    public java.lang.String getLevel11() {
        return _ivldItemHierarchy.getLevel11();
    }

    /**
    * Sets the level11 of this ivld item hierarchy.
    *
    * @param level11 the level11 of this ivld item hierarchy
    */
    @Override
    public void setLevel11(java.lang.String level11) {
        _ivldItemHierarchy.setLevel11(level11);
    }

    /**
    * Returns the level14 of this ivld item hierarchy.
    *
    * @return the level14 of this ivld item hierarchy
    */
    @Override
    public java.lang.String getLevel14() {
        return _ivldItemHierarchy.getLevel14();
    }

    /**
    * Sets the level14 of this ivld item hierarchy.
    *
    * @param level14 the level14 of this ivld item hierarchy
    */
    @Override
    public void setLevel14(java.lang.String level14) {
        _ivldItemHierarchy.setLevel14(level14);
    }

    /**
    * Returns the level0 tag of this ivld item hierarchy.
    *
    * @return the level0 tag of this ivld item hierarchy
    */
    @Override
    public java.lang.String getLevel0Tag() {
        return _ivldItemHierarchy.getLevel0Tag();
    }

    /**
    * Sets the level0 tag of this ivld item hierarchy.
    *
    * @param level0Tag the level0 tag of this ivld item hierarchy
    */
    @Override
    public void setLevel0Tag(java.lang.String level0Tag) {
        _ivldItemHierarchy.setLevel0Tag(level0Tag);
    }

    /**
    * Returns the level13 of this ivld item hierarchy.
    *
    * @return the level13 of this ivld item hierarchy
    */
    @Override
    public java.lang.String getLevel13() {
        return _ivldItemHierarchy.getLevel13();
    }

    /**
    * Sets the level13 of this ivld item hierarchy.
    *
    * @param level13 the level13 of this ivld item hierarchy
    */
    @Override
    public void setLevel13(java.lang.String level13) {
        _ivldItemHierarchy.setLevel13(level13);
    }

    /**
    * Returns the level16 of this ivld item hierarchy.
    *
    * @return the level16 of this ivld item hierarchy
    */
    @Override
    public java.lang.String getLevel16() {
        return _ivldItemHierarchy.getLevel16();
    }

    /**
    * Sets the level16 of this ivld item hierarchy.
    *
    * @param level16 the level16 of this ivld item hierarchy
    */
    @Override
    public void setLevel16(java.lang.String level16) {
        _ivldItemHierarchy.setLevel16(level16);
    }

    /**
    * Returns the level15 of this ivld item hierarchy.
    *
    * @return the level15 of this ivld item hierarchy
    */
    @Override
    public java.lang.String getLevel15() {
        return _ivldItemHierarchy.getLevel15();
    }

    /**
    * Sets the level15 of this ivld item hierarchy.
    *
    * @param level15 the level15 of this ivld item hierarchy
    */
    @Override
    public void setLevel15(java.lang.String level15) {
        _ivldItemHierarchy.setLevel15(level15);
    }

    /**
    * Returns the level18 of this ivld item hierarchy.
    *
    * @return the level18 of this ivld item hierarchy
    */
    @Override
    public java.lang.String getLevel18() {
        return _ivldItemHierarchy.getLevel18();
    }

    /**
    * Sets the level18 of this ivld item hierarchy.
    *
    * @param level18 the level18 of this ivld item hierarchy
    */
    @Override
    public void setLevel18(java.lang.String level18) {
        _ivldItemHierarchy.setLevel18(level18);
    }

    /**
    * Returns the level17 of this ivld item hierarchy.
    *
    * @return the level17 of this ivld item hierarchy
    */
    @Override
    public java.lang.String getLevel17() {
        return _ivldItemHierarchy.getLevel17();
    }

    /**
    * Sets the level17 of this ivld item hierarchy.
    *
    * @param level17 the level17 of this ivld item hierarchy
    */
    @Override
    public void setLevel17(java.lang.String level17) {
        _ivldItemHierarchy.setLevel17(level17);
    }

    /**
    * Returns the level19 of this ivld item hierarchy.
    *
    * @return the level19 of this ivld item hierarchy
    */
    @Override
    public java.lang.String getLevel19() {
        return _ivldItemHierarchy.getLevel19();
    }

    /**
    * Sets the level19 of this ivld item hierarchy.
    *
    * @param level19 the level19 of this ivld item hierarchy
    */
    @Override
    public void setLevel19(java.lang.String level19) {
        _ivldItemHierarchy.setLevel19(level19);
    }

    /**
    * Returns the level12 alias of this ivld item hierarchy.
    *
    * @return the level12 alias of this ivld item hierarchy
    */
    @Override
    public java.lang.String getLevel12Alias() {
        return _ivldItemHierarchy.getLevel12Alias();
    }

    /**
    * Sets the level12 alias of this ivld item hierarchy.
    *
    * @param level12Alias the level12 alias of this ivld item hierarchy
    */
    @Override
    public void setLevel12Alias(java.lang.String level12Alias) {
        _ivldItemHierarchy.setLevel12Alias(level12Alias);
    }

    /**
    * Returns the level19 alias of this ivld item hierarchy.
    *
    * @return the level19 alias of this ivld item hierarchy
    */
    @Override
    public java.lang.String getLevel19Alias() {
        return _ivldItemHierarchy.getLevel19Alias();
    }

    /**
    * Sets the level19 alias of this ivld item hierarchy.
    *
    * @param level19Alias the level19 alias of this ivld item hierarchy
    */
    @Override
    public void setLevel19Alias(java.lang.String level19Alias) {
        _ivldItemHierarchy.setLevel19Alias(level19Alias);
    }

    /**
    * Returns the batch ID of this ivld item hierarchy.
    *
    * @return the batch ID of this ivld item hierarchy
    */
    @Override
    public java.lang.String getBatchId() {
        return _ivldItemHierarchy.getBatchId();
    }

    /**
    * Sets the batch ID of this ivld item hierarchy.
    *
    * @param batchId the batch ID of this ivld item hierarchy
    */
    @Override
    public void setBatchId(java.lang.String batchId) {
        _ivldItemHierarchy.setBatchId(batchId);
    }

    /**
    * Returns the level0 alias of this ivld item hierarchy.
    *
    * @return the level0 alias of this ivld item hierarchy
    */
    @Override
    public java.lang.String getLevel0Alias() {
        return _ivldItemHierarchy.getLevel0Alias();
    }

    /**
    * Sets the level0 alias of this ivld item hierarchy.
    *
    * @param level0Alias the level0 alias of this ivld item hierarchy
    */
    @Override
    public void setLevel0Alias(java.lang.String level0Alias) {
        _ivldItemHierarchy.setLevel0Alias(level0Alias);
    }

    /**
    * Returns the level14 alias of this ivld item hierarchy.
    *
    * @return the level14 alias of this ivld item hierarchy
    */
    @Override
    public java.lang.String getLevel14Alias() {
        return _ivldItemHierarchy.getLevel14Alias();
    }

    /**
    * Sets the level14 alias of this ivld item hierarchy.
    *
    * @param level14Alias the level14 alias of this ivld item hierarchy
    */
    @Override
    public void setLevel14Alias(java.lang.String level14Alias) {
        _ivldItemHierarchy.setLevel14Alias(level14Alias);
    }

    /**
    * Returns the check record of this ivld item hierarchy.
    *
    * @return the check record of this ivld item hierarchy
    */
    @Override
    public boolean getCheckRecord() {
        return _ivldItemHierarchy.getCheckRecord();
    }

    /**
    * Returns <code>true</code> if this ivld item hierarchy is check record.
    *
    * @return <code>true</code> if this ivld item hierarchy is check record; <code>false</code> otherwise
    */
    @Override
    public boolean isCheckRecord() {
        return _ivldItemHierarchy.isCheckRecord();
    }

    /**
    * Sets whether this ivld item hierarchy is check record.
    *
    * @param checkRecord the check record of this ivld item hierarchy
    */
    @Override
    public void setCheckRecord(boolean checkRecord) {
        _ivldItemHierarchy.setCheckRecord(checkRecord);
    }

    @Override
    public boolean isNew() {
        return _ivldItemHierarchy.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _ivldItemHierarchy.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _ivldItemHierarchy.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _ivldItemHierarchy.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _ivldItemHierarchy.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _ivldItemHierarchy.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _ivldItemHierarchy.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.stpl.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _ivldItemHierarchy.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.model.BaseModel<?> baseModel) {
        _ivldItemHierarchy.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portlet.expando.model.ExpandoBridge expandoBridge) {
        _ivldItemHierarchy.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.stpl.portal.service.ServiceContext serviceContext) {
        _ivldItemHierarchy.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new IvldItemHierarchyWrapper((IvldItemHierarchy) _ivldItemHierarchy.clone());
    }

    @Override
    public int compareTo(IvldItemHierarchy ivldItemHierarchy) {
        return _ivldItemHierarchy.compareTo(ivldItemHierarchy);
    }

    @Override
    public int hashCode() {
        return _ivldItemHierarchy.hashCode();
    }

    @Override
    public com.stpl.portal.model.CacheModel<IvldItemHierarchy> toCacheModel() {
        return _ivldItemHierarchy.toCacheModel();
    }

    @Override
    public IvldItemHierarchy toEscapedModel() {
        return new IvldItemHierarchyWrapper(_ivldItemHierarchy.toEscapedModel());
    }

    @Override
    public IvldItemHierarchy toUnescapedModel() {
        return new IvldItemHierarchyWrapper(_ivldItemHierarchy.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _ivldItemHierarchy.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _ivldItemHierarchy.toXmlString();
    }

    @Override
    public void persist()
        throws com.stpl.portal.kernel.exception.SystemException {
        _ivldItemHierarchy.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof IvldItemHierarchyWrapper)) {
            return false;
        }

        IvldItemHierarchyWrapper ivldItemHierarchyWrapper = (IvldItemHierarchyWrapper) obj;

        if (Validator.equals(_ivldItemHierarchy,
                    ivldItemHierarchyWrapper._ivldItemHierarchy)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public IvldItemHierarchy getWrappedIvldItemHierarchy() {
        return _ivldItemHierarchy;
    }

    @Override
    public IvldItemHierarchy getWrappedModel() {
        return _ivldItemHierarchy;
    }

    @Override
    public void resetOriginalValues() {
        _ivldItemHierarchy.resetOriginalValues();
    }
}
