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
public class DeductionGroupSoap implements Serializable {
    private String _deductionFilter;
    private Date _createdDate;
    private int _createdBy;
    private int _deductionGroupSid;
    private String _deductionGroupName;
    private int _versionNo;
    private String _deductionGroupDescription;
    private int _modifiedBy;
    private String _deductionGroupNo;
    private Date _modifiedDate;

    public DeductionGroupSoap() {
    }

    public static DeductionGroupSoap toSoapModel(DeductionGroup model) {
        DeductionGroupSoap soapModel = new DeductionGroupSoap();

        soapModel.setDeductionFilter(model.getDeductionFilter());
        soapModel.setCreatedDate(model.getCreatedDate());
        soapModel.setCreatedBy(model.getCreatedBy());
        soapModel.setDeductionGroupSid(model.getDeductionGroupSid());
        soapModel.setDeductionGroupName(model.getDeductionGroupName());
        soapModel.setVersionNo(model.getVersionNo());
        soapModel.setDeductionGroupDescription(model.getDeductionGroupDescription());
        soapModel.setModifiedBy(model.getModifiedBy());
        soapModel.setDeductionGroupNo(model.getDeductionGroupNo());
        soapModel.setModifiedDate(model.getModifiedDate());

        return soapModel;
    }

    public static DeductionGroupSoap[] toSoapModels(DeductionGroup[] models) {
        DeductionGroupSoap[] soapModels = new DeductionGroupSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static DeductionGroupSoap[][] toSoapModels(DeductionGroup[][] models) {
        DeductionGroupSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new DeductionGroupSoap[models.length][models[0].length];
        } else {
            soapModels = new DeductionGroupSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static DeductionGroupSoap[] toSoapModels(List<DeductionGroup> models) {
        List<DeductionGroupSoap> soapModels = new ArrayList<DeductionGroupSoap>(models.size());

        for (DeductionGroup model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new DeductionGroupSoap[soapModels.size()]);
    }

    public int getPrimaryKey() {
        return _deductionGroupSid;
    }

    public void setPrimaryKey(int pk) {
        setDeductionGroupSid(pk);
    }

    public String getDeductionFilter() {
        return _deductionFilter;
    }

    public void setDeductionFilter(String deductionFilter) {
        _deductionFilter = deductionFilter;
    }

    public Date getCreatedDate() {
        return _createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        _createdDate = createdDate;
    }

    public int getCreatedBy() {
        return _createdBy;
    }

    public void setCreatedBy(int createdBy) {
        _createdBy = createdBy;
    }

    public int getDeductionGroupSid() {
        return _deductionGroupSid;
    }

    public void setDeductionGroupSid(int deductionGroupSid) {
        _deductionGroupSid = deductionGroupSid;
    }

    public String getDeductionGroupName() {
        return _deductionGroupName;
    }

    public void setDeductionGroupName(String deductionGroupName) {
        _deductionGroupName = deductionGroupName;
    }

    public int getVersionNo() {
        return _versionNo;
    }

    public void setVersionNo(int versionNo) {
        _versionNo = versionNo;
    }

    public String getDeductionGroupDescription() {
        return _deductionGroupDescription;
    }

    public void setDeductionGroupDescription(String deductionGroupDescription) {
        _deductionGroupDescription = deductionGroupDescription;
    }

    public int getModifiedBy() {
        return _modifiedBy;
    }

    public void setModifiedBy(int modifiedBy) {
        _modifiedBy = modifiedBy;
    }

    public String getDeductionGroupNo() {
        return _deductionGroupNo;
    }

    public void setDeductionGroupNo(String deductionGroupNo) {
        _deductionGroupNo = deductionGroupNo;
    }

    public Date getModifiedDate() {
        return _modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        _modifiedDate = modifiedDate;
    }
}
