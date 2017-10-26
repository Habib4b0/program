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
public class CdrModelSoap implements Serializable {
    private int _createdBy;
    private int _ruleCategory;
    private int _ruleType;
    private int _modifiedBy;
    private String _internalNotes;
    private Date _createdDate;
    private String _ruleName;
    private int _cdrModelSid;
    private String _ruleNo;
    private Date _modifiedDate;

    public CdrModelSoap() {
    }

    public static CdrModelSoap toSoapModel(CdrModel model) {
        CdrModelSoap soapModel = new CdrModelSoap();

        soapModel.setCreatedBy(model.getCreatedBy());
        soapModel.setRuleCategory(model.getRuleCategory());
        soapModel.setRuleType(model.getRuleType());
        soapModel.setModifiedBy(model.getModifiedBy());
        soapModel.setInternalNotes(model.getInternalNotes());
        soapModel.setCreatedDate(model.getCreatedDate());
        soapModel.setRuleName(model.getRuleName());
        soapModel.setCdrModelSid(model.getCdrModelSid());
        soapModel.setRuleNo(model.getRuleNo());
        soapModel.setModifiedDate(model.getModifiedDate());

        return soapModel;
    }

    public static CdrModelSoap[] toSoapModels(CdrModel[] models) {
        CdrModelSoap[] soapModels = new CdrModelSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static CdrModelSoap[][] toSoapModels(CdrModel[][] models) {
        CdrModelSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new CdrModelSoap[models.length][models[0].length];
        } else {
            soapModels = new CdrModelSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static CdrModelSoap[] toSoapModels(List<CdrModel> models) {
        List<CdrModelSoap> soapModels = new ArrayList<CdrModelSoap>(models.size());

        for (CdrModel model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new CdrModelSoap[soapModels.size()]);
    }

    public int getPrimaryKey() {
        return _cdrModelSid;
    }

    public void setPrimaryKey(int pk) {
        setCdrModelSid(pk);
    }

    public int getCreatedBy() {
        return _createdBy;
    }

    public void setCreatedBy(int createdBy) {
        _createdBy = createdBy;
    }

    public int getRuleCategory() {
        return _ruleCategory;
    }

    public void setRuleCategory(int ruleCategory) {
        _ruleCategory = ruleCategory;
    }

    public int getRuleType() {
        return _ruleType;
    }

    public void setRuleType(int ruleType) {
        _ruleType = ruleType;
    }

    public int getModifiedBy() {
        return _modifiedBy;
    }

    public void setModifiedBy(int modifiedBy) {
        _modifiedBy = modifiedBy;
    }

    public String getInternalNotes() {
        return _internalNotes;
    }

    public void setInternalNotes(String internalNotes) {
        _internalNotes = internalNotes;
    }

    public Date getCreatedDate() {
        return _createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        _createdDate = createdDate;
    }

    public String getRuleName() {
        return _ruleName;
    }

    public void setRuleName(String ruleName) {
        _ruleName = ruleName;
    }

    public int getCdrModelSid() {
        return _cdrModelSid;
    }

    public void setCdrModelSid(int cdrModelSid) {
        _cdrModelSid = cdrModelSid;
    }

    public String getRuleNo() {
        return _ruleNo;
    }

    public void setRuleNo(String ruleNo) {
        _ruleNo = ruleNo;
    }

    public Date getModifiedDate() {
        return _modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        _modifiedDate = modifiedDate;
    }
}
