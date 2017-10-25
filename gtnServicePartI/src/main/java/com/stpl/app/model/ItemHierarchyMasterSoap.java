package com.stpl.app.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author
 * @generated
 */
public class ItemHierarchyMasterSoap implements Serializable {
    private String _level1Alias;
    private String _level11Alias;
    private Date _createdDate;
    private String _level8Alias;
    private String _level14Alias;
    private String _level5Alias;
    private int _createdBy;
    private String _level10Alias;
    private int _itemHierarchyMasterSid;
    private String _level17Alias;
    private String _level9Alias;
    private String _level0Alias;
    private Date _modifiedDate;
    private String _level13Alias;
    private String _source;
    private String _level6Alias;
    private String _gtnBrand;
    private int _modifiedBy;
    private String _level3Alias;
    private String _level16Alias;
    private String _batchId;
    private String _level19Alias;
    private String _level20;
    private String _level2Alias;
    private String _level20Alias;
    private String _gtnTherapeuticClass;
    private String _level7Alias;
    private String _level0;
    private String _level1;
    private String _level2;
    private String _level3;
    private String _level12Alias;
    private String _level8;
    private String _level11;
    private String _level4Alias;
    private String _level9;
    private String _level12;
    private String _level13;
    private String _level14;
    private boolean _recordLockStatus;
    private String _level0Tag;
    private String _level4;
    private String _level5;
    private String _level6;
    private String _level15Alias;
    private String _level7;
    private String _level10;
    private String _level19;
    private String _level15;
    private String _level16;
    private String _gtnCompany;
    private String _level17;
    private String _level18Alias;
    private String _level18;
    private String _inboundStatus;

    public ItemHierarchyMasterSoap() {
    }

    public static ItemHierarchyMasterSoap toSoapModel(ItemHierarchyMaster model) {
        ItemHierarchyMasterSoap soapModel = new ItemHierarchyMasterSoap();

        soapModel.setLevel1Alias(model.getLevel1Alias());
        soapModel.setLevel11Alias(model.getLevel11Alias());
        soapModel.setCreatedDate(model.getCreatedDate());
        soapModel.setLevel8Alias(model.getLevel8Alias());
        soapModel.setLevel14Alias(model.getLevel14Alias());
        soapModel.setLevel5Alias(model.getLevel5Alias());
        soapModel.setCreatedBy(model.getCreatedBy());
        soapModel.setLevel10Alias(model.getLevel10Alias());
        soapModel.setItemHierarchyMasterSid(model.getItemHierarchyMasterSid());
        soapModel.setLevel17Alias(model.getLevel17Alias());
        soapModel.setLevel9Alias(model.getLevel9Alias());
        soapModel.setLevel0Alias(model.getLevel0Alias());
        soapModel.setModifiedDate(model.getModifiedDate());
        soapModel.setLevel13Alias(model.getLevel13Alias());
        soapModel.setSource(model.getSource());
        soapModel.setLevel6Alias(model.getLevel6Alias());
        soapModel.setGtnBrand(model.getGtnBrand());
        soapModel.setModifiedBy(model.getModifiedBy());
        soapModel.setLevel3Alias(model.getLevel3Alias());
        soapModel.setLevel16Alias(model.getLevel16Alias());
        soapModel.setBatchId(model.getBatchId());
        soapModel.setLevel19Alias(model.getLevel19Alias());
        soapModel.setLevel20(model.getLevel20());
        soapModel.setLevel2Alias(model.getLevel2Alias());
        soapModel.setLevel20Alias(model.getLevel20Alias());
        soapModel.setGtnTherapeuticClass(model.getGtnTherapeuticClass());
        soapModel.setLevel7Alias(model.getLevel7Alias());
        soapModel.setLevel0(model.getLevel0());
        soapModel.setLevel1(model.getLevel1());
        soapModel.setLevel2(model.getLevel2());
        soapModel.setLevel3(model.getLevel3());
        soapModel.setLevel12Alias(model.getLevel12Alias());
        soapModel.setLevel8(model.getLevel8());
        soapModel.setLevel11(model.getLevel11());
        soapModel.setLevel4Alias(model.getLevel4Alias());
        soapModel.setLevel9(model.getLevel9());
        soapModel.setLevel12(model.getLevel12());
        soapModel.setLevel13(model.getLevel13());
        soapModel.setLevel14(model.getLevel14());
        soapModel.setRecordLockStatus(model.getRecordLockStatus());
        soapModel.setLevel0Tag(model.getLevel0Tag());
        soapModel.setLevel4(model.getLevel4());
        soapModel.setLevel5(model.getLevel5());
        soapModel.setLevel6(model.getLevel6());
        soapModel.setLevel15Alias(model.getLevel15Alias());
        soapModel.setLevel7(model.getLevel7());
        soapModel.setLevel10(model.getLevel10());
        soapModel.setLevel19(model.getLevel19());
        soapModel.setLevel15(model.getLevel15());
        soapModel.setLevel16(model.getLevel16());
        soapModel.setGtnCompany(model.getGtnCompany());
        soapModel.setLevel17(model.getLevel17());
        soapModel.setLevel18Alias(model.getLevel18Alias());
        soapModel.setLevel18(model.getLevel18());
        soapModel.setInboundStatus(model.getInboundStatus());

        return soapModel;
    }

    public static ItemHierarchyMasterSoap[] toSoapModels(
        ItemHierarchyMaster[] models) {
        ItemHierarchyMasterSoap[] soapModels = new ItemHierarchyMasterSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static ItemHierarchyMasterSoap[][] toSoapModels(
        ItemHierarchyMaster[][] models) {
        ItemHierarchyMasterSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new ItemHierarchyMasterSoap[models.length][models[0].length];
        } else {
            soapModels = new ItemHierarchyMasterSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static ItemHierarchyMasterSoap[] toSoapModels(
        List<ItemHierarchyMaster> models) {
        List<ItemHierarchyMasterSoap> soapModels = new ArrayList<ItemHierarchyMasterSoap>(models.size());

        for (ItemHierarchyMaster model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new ItemHierarchyMasterSoap[soapModels.size()]);
    }

    public int getPrimaryKey() {
        return _itemHierarchyMasterSid;
    }

    public void setPrimaryKey(int pk) {
        setItemHierarchyMasterSid(pk);
    }

    public String getLevel1Alias() {
        return _level1Alias;
    }

    public void setLevel1Alias(String level1Alias) {
        _level1Alias = level1Alias;
    }

    public String getLevel11Alias() {
        return _level11Alias;
    }

    public void setLevel11Alias(String level11Alias) {
        _level11Alias = level11Alias;
    }

    public Date getCreatedDate() {
        return _createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        _createdDate = createdDate;
    }

    public String getLevel8Alias() {
        return _level8Alias;
    }

    public void setLevel8Alias(String level8Alias) {
        _level8Alias = level8Alias;
    }

    public String getLevel14Alias() {
        return _level14Alias;
    }

    public void setLevel14Alias(String level14Alias) {
        _level14Alias = level14Alias;
    }

    public String getLevel5Alias() {
        return _level5Alias;
    }

    public void setLevel5Alias(String level5Alias) {
        _level5Alias = level5Alias;
    }

    public int getCreatedBy() {
        return _createdBy;
    }

    public void setCreatedBy(int createdBy) {
        _createdBy = createdBy;
    }

    public String getLevel10Alias() {
        return _level10Alias;
    }

    public void setLevel10Alias(String level10Alias) {
        _level10Alias = level10Alias;
    }

    public int getItemHierarchyMasterSid() {
        return _itemHierarchyMasterSid;
    }

    public void setItemHierarchyMasterSid(int itemHierarchyMasterSid) {
        _itemHierarchyMasterSid = itemHierarchyMasterSid;
    }

    public String getLevel17Alias() {
        return _level17Alias;
    }

    public void setLevel17Alias(String level17Alias) {
        _level17Alias = level17Alias;
    }

    public String getLevel9Alias() {
        return _level9Alias;
    }

    public void setLevel9Alias(String level9Alias) {
        _level9Alias = level9Alias;
    }

    public String getLevel0Alias() {
        return _level0Alias;
    }

    public void setLevel0Alias(String level0Alias) {
        _level0Alias = level0Alias;
    }

    public Date getModifiedDate() {
        return _modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        _modifiedDate = modifiedDate;
    }

    public String getLevel13Alias() {
        return _level13Alias;
    }

    public void setLevel13Alias(String level13Alias) {
        _level13Alias = level13Alias;
    }

    public String getSource() {
        return _source;
    }

    public void setSource(String source) {
        _source = source;
    }

    public String getLevel6Alias() {
        return _level6Alias;
    }

    public void setLevel6Alias(String level6Alias) {
        _level6Alias = level6Alias;
    }

    public String getGtnBrand() {
        return _gtnBrand;
    }

    public void setGtnBrand(String gtnBrand) {
        _gtnBrand = gtnBrand;
    }

    public int getModifiedBy() {
        return _modifiedBy;
    }

    public void setModifiedBy(int modifiedBy) {
        _modifiedBy = modifiedBy;
    }

    public String getLevel3Alias() {
        return _level3Alias;
    }

    public void setLevel3Alias(String level3Alias) {
        _level3Alias = level3Alias;
    }

    public String getLevel16Alias() {
        return _level16Alias;
    }

    public void setLevel16Alias(String level16Alias) {
        _level16Alias = level16Alias;
    }

    public String getBatchId() {
        return _batchId;
    }

    public void setBatchId(String batchId) {
        _batchId = batchId;
    }

    public String getLevel19Alias() {
        return _level19Alias;
    }

    public void setLevel19Alias(String level19Alias) {
        _level19Alias = level19Alias;
    }

    public String getLevel20() {
        return _level20;
    }

    public void setLevel20(String level20) {
        _level20 = level20;
    }

    public String getLevel2Alias() {
        return _level2Alias;
    }

    public void setLevel2Alias(String level2Alias) {
        _level2Alias = level2Alias;
    }

    public String getLevel20Alias() {
        return _level20Alias;
    }

    public void setLevel20Alias(String level20Alias) {
        _level20Alias = level20Alias;
    }

    public String getGtnTherapeuticClass() {
        return _gtnTherapeuticClass;
    }

    public void setGtnTherapeuticClass(String gtnTherapeuticClass) {
        _gtnTherapeuticClass = gtnTherapeuticClass;
    }

    public String getLevel7Alias() {
        return _level7Alias;
    }

    public void setLevel7Alias(String level7Alias) {
        _level7Alias = level7Alias;
    }

    public String getLevel0() {
        return _level0;
    }

    public void setLevel0(String level0) {
        _level0 = level0;
    }

    public String getLevel1() {
        return _level1;
    }

    public void setLevel1(String level1) {
        _level1 = level1;
    }

    public String getLevel2() {
        return _level2;
    }

    public void setLevel2(String level2) {
        _level2 = level2;
    }

    public String getLevel3() {
        return _level3;
    }

    public void setLevel3(String level3) {
        _level3 = level3;
    }

    public String getLevel12Alias() {
        return _level12Alias;
    }

    public void setLevel12Alias(String level12Alias) {
        _level12Alias = level12Alias;
    }

    public String getLevel8() {
        return _level8;
    }

    public void setLevel8(String level8) {
        _level8 = level8;
    }

    public String getLevel11() {
        return _level11;
    }

    public void setLevel11(String level11) {
        _level11 = level11;
    }

    public String getLevel4Alias() {
        return _level4Alias;
    }

    public void setLevel4Alias(String level4Alias) {
        _level4Alias = level4Alias;
    }

    public String getLevel9() {
        return _level9;
    }

    public void setLevel9(String level9) {
        _level9 = level9;
    }

    public String getLevel12() {
        return _level12;
    }

    public void setLevel12(String level12) {
        _level12 = level12;
    }

    public String getLevel13() {
        return _level13;
    }

    public void setLevel13(String level13) {
        _level13 = level13;
    }

    public String getLevel14() {
        return _level14;
    }

    public void setLevel14(String level14) {
        _level14 = level14;
    }

    public boolean getRecordLockStatus() {
        return _recordLockStatus;
    }

    public boolean isRecordLockStatus() {
        return _recordLockStatus;
    }

    public void setRecordLockStatus(boolean recordLockStatus) {
        _recordLockStatus = recordLockStatus;
    }

    public String getLevel0Tag() {
        return _level0Tag;
    }

    public void setLevel0Tag(String level0Tag) {
        _level0Tag = level0Tag;
    }

    public String getLevel4() {
        return _level4;
    }

    public void setLevel4(String level4) {
        _level4 = level4;
    }

    public String getLevel5() {
        return _level5;
    }

    public void setLevel5(String level5) {
        _level5 = level5;
    }

    public String getLevel6() {
        return _level6;
    }

    public void setLevel6(String level6) {
        _level6 = level6;
    }

    public String getLevel15Alias() {
        return _level15Alias;
    }

    public void setLevel15Alias(String level15Alias) {
        _level15Alias = level15Alias;
    }

    public String getLevel7() {
        return _level7;
    }

    public void setLevel7(String level7) {
        _level7 = level7;
    }

    public String getLevel10() {
        return _level10;
    }

    public void setLevel10(String level10) {
        _level10 = level10;
    }

    public String getLevel19() {
        return _level19;
    }

    public void setLevel19(String level19) {
        _level19 = level19;
    }

    public String getLevel15() {
        return _level15;
    }

    public void setLevel15(String level15) {
        _level15 = level15;
    }

    public String getLevel16() {
        return _level16;
    }

    public void setLevel16(String level16) {
        _level16 = level16;
    }

    public String getGtnCompany() {
        return _gtnCompany;
    }

    public void setGtnCompany(String gtnCompany) {
        _gtnCompany = gtnCompany;
    }

    public String getLevel17() {
        return _level17;
    }

    public void setLevel17(String level17) {
        _level17 = level17;
    }

    public String getLevel18Alias() {
        return _level18Alias;
    }

    public void setLevel18Alias(String level18Alias) {
        _level18Alias = level18Alias;
    }

    public String getLevel18() {
        return _level18;
    }

    public void setLevel18(String level18) {
        _level18 = level18;
    }

    public String getInboundStatus() {
        return _inboundStatus;
    }

    public void setInboundStatus(String inboundStatus) {
        _inboundStatus = inboundStatus;
    }
}
