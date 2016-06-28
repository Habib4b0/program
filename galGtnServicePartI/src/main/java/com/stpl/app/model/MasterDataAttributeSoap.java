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
public class MasterDataAttributeSoap implements Serializable {
    private String _column15;
    private String _column14;
    private String _column17;
    private String _column16;
    private String _column11;
    private int _modifiedBy;
    private String _column10;
    private Date _createdDate;
    private String _column13;
    private String _column12;
    private String _batchId;
    private String _column19;
    private String _column18;
    private String _masterAttribute;
    private int _createdBy;
    private String _masterType;
    private String _masterId;
    private String _column20;
    private String _column9;
    private Date _modifiedDate;
    private String _column6;
    private int _masterDataAttributeSid;
    private String _column5;
    private String _column8;
    private String _column7;
    private boolean _recordLockStatus;
    private String _column2;
    private String _column1;
    private String _column4;
    private String _column3;
    private String _source;
    private String _inboundStatus;

    public MasterDataAttributeSoap() {
    }

    public static MasterDataAttributeSoap toSoapModel(MasterDataAttribute model) {
        MasterDataAttributeSoap soapModel = new MasterDataAttributeSoap();

        soapModel.setColumn15(model.getColumn15());
        soapModel.setColumn14(model.getColumn14());
        soapModel.setColumn17(model.getColumn17());
        soapModel.setColumn16(model.getColumn16());
        soapModel.setColumn11(model.getColumn11());
        soapModel.setModifiedBy(model.getModifiedBy());
        soapModel.setColumn10(model.getColumn10());
        soapModel.setCreatedDate(model.getCreatedDate());
        soapModel.setColumn13(model.getColumn13());
        soapModel.setColumn12(model.getColumn12());
        soapModel.setBatchId(model.getBatchId());
        soapModel.setColumn19(model.getColumn19());
        soapModel.setColumn18(model.getColumn18());
        soapModel.setMasterAttribute(model.getMasterAttribute());
        soapModel.setCreatedBy(model.getCreatedBy());
        soapModel.setMasterType(model.getMasterType());
        soapModel.setMasterId(model.getMasterId());
        soapModel.setColumn20(model.getColumn20());
        soapModel.setColumn9(model.getColumn9());
        soapModel.setModifiedDate(model.getModifiedDate());
        soapModel.setColumn6(model.getColumn6());
        soapModel.setMasterDataAttributeSid(model.getMasterDataAttributeSid());
        soapModel.setColumn5(model.getColumn5());
        soapModel.setColumn8(model.getColumn8());
        soapModel.setColumn7(model.getColumn7());
        soapModel.setRecordLockStatus(model.getRecordLockStatus());
        soapModel.setColumn2(model.getColumn2());
        soapModel.setColumn1(model.getColumn1());
        soapModel.setColumn4(model.getColumn4());
        soapModel.setColumn3(model.getColumn3());
        soapModel.setSource(model.getSource());
        soapModel.setInboundStatus(model.getInboundStatus());

        return soapModel;
    }

    public static MasterDataAttributeSoap[] toSoapModels(
        MasterDataAttribute[] models) {
        MasterDataAttributeSoap[] soapModels = new MasterDataAttributeSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static MasterDataAttributeSoap[][] toSoapModels(
        MasterDataAttribute[][] models) {
        MasterDataAttributeSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new MasterDataAttributeSoap[models.length][models[0].length];
        } else {
            soapModels = new MasterDataAttributeSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static MasterDataAttributeSoap[] toSoapModels(
        List<MasterDataAttribute> models) {
        List<MasterDataAttributeSoap> soapModels = new ArrayList<MasterDataAttributeSoap>(models.size());

        for (MasterDataAttribute model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new MasterDataAttributeSoap[soapModels.size()]);
    }

    public int getPrimaryKey() {
        return _masterDataAttributeSid;
    }

    public void setPrimaryKey(int pk) {
        setMasterDataAttributeSid(pk);
    }

    public String getColumn15() {
        return _column15;
    }

    public void setColumn15(String column15) {
        _column15 = column15;
    }

    public String getColumn14() {
        return _column14;
    }

    public void setColumn14(String column14) {
        _column14 = column14;
    }

    public String getColumn17() {
        return _column17;
    }

    public void setColumn17(String column17) {
        _column17 = column17;
    }

    public String getColumn16() {
        return _column16;
    }

    public void setColumn16(String column16) {
        _column16 = column16;
    }

    public String getColumn11() {
        return _column11;
    }

    public void setColumn11(String column11) {
        _column11 = column11;
    }

    public int getModifiedBy() {
        return _modifiedBy;
    }

    public void setModifiedBy(int modifiedBy) {
        _modifiedBy = modifiedBy;
    }

    public String getColumn10() {
        return _column10;
    }

    public void setColumn10(String column10) {
        _column10 = column10;
    }

    public Date getCreatedDate() {
        return _createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        _createdDate = createdDate;
    }

    public String getColumn13() {
        return _column13;
    }

    public void setColumn13(String column13) {
        _column13 = column13;
    }

    public String getColumn12() {
        return _column12;
    }

    public void setColumn12(String column12) {
        _column12 = column12;
    }

    public String getBatchId() {
        return _batchId;
    }

    public void setBatchId(String batchId) {
        _batchId = batchId;
    }

    public String getColumn19() {
        return _column19;
    }

    public void setColumn19(String column19) {
        _column19 = column19;
    }

    public String getColumn18() {
        return _column18;
    }

    public void setColumn18(String column18) {
        _column18 = column18;
    }

    public String getMasterAttribute() {
        return _masterAttribute;
    }

    public void setMasterAttribute(String masterAttribute) {
        _masterAttribute = masterAttribute;
    }

    public int getCreatedBy() {
        return _createdBy;
    }

    public void setCreatedBy(int createdBy) {
        _createdBy = createdBy;
    }

    public String getMasterType() {
        return _masterType;
    }

    public void setMasterType(String masterType) {
        _masterType = masterType;
    }

    public String getMasterId() {
        return _masterId;
    }

    public void setMasterId(String masterId) {
        _masterId = masterId;
    }

    public String getColumn20() {
        return _column20;
    }

    public void setColumn20(String column20) {
        _column20 = column20;
    }

    public String getColumn9() {
        return _column9;
    }

    public void setColumn9(String column9) {
        _column9 = column9;
    }

    public Date getModifiedDate() {
        return _modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        _modifiedDate = modifiedDate;
    }

    public String getColumn6() {
        return _column6;
    }

    public void setColumn6(String column6) {
        _column6 = column6;
    }

    public int getMasterDataAttributeSid() {
        return _masterDataAttributeSid;
    }

    public void setMasterDataAttributeSid(int masterDataAttributeSid) {
        _masterDataAttributeSid = masterDataAttributeSid;
    }

    public String getColumn5() {
        return _column5;
    }

    public void setColumn5(String column5) {
        _column5 = column5;
    }

    public String getColumn8() {
        return _column8;
    }

    public void setColumn8(String column8) {
        _column8 = column8;
    }

    public String getColumn7() {
        return _column7;
    }

    public void setColumn7(String column7) {
        _column7 = column7;
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

    public String getColumn2() {
        return _column2;
    }

    public void setColumn2(String column2) {
        _column2 = column2;
    }

    public String getColumn1() {
        return _column1;
    }

    public void setColumn1(String column1) {
        _column1 = column1;
    }

    public String getColumn4() {
        return _column4;
    }

    public void setColumn4(String column4) {
        _column4 = column4;
    }

    public String getColumn3() {
        return _column3;
    }

    public void setColumn3(String column3) {
        _column3 = column3;
    }

    public String getSource() {
        return _source;
    }

    public void setSource(String source) {
        _source = source;
    }

    public String getInboundStatus() {
        return _inboundStatus;
    }

    public void setInboundStatus(String inboundStatus) {
        _inboundStatus = inboundStatus;
    }
}
