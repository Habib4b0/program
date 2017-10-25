package com.stpl.app.parttwo.model;

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
public class VwItemIdentifierSoap implements Serializable {
    private String _itemStatus;
    private int _itemIdentifierSid;
    private Date _endDate;
    private String _itemId;
    private Date _modifiedDate;
    private String _entityCode;
    private Date _startDate;
    private Date _createdDate;
    private String _createdBy;
    private String _source;
    private String _batchId;
    private String _addChgDelIndicator;
    private String _itemName;
    private String _itemIdentifier;
    private String _identifierCodeQualifierName;
    private String _modifiedBy;
    private String _itemNo;
    private String _identifierCodeQualifier;

    public VwItemIdentifierSoap() {
    }

    public static VwItemIdentifierSoap toSoapModel(VwItemIdentifier model) {
        VwItemIdentifierSoap soapModel = new VwItemIdentifierSoap();

        soapModel.setItemStatus(model.getItemStatus());
        soapModel.setItemIdentifierSid(model.getItemIdentifierSid());
        soapModel.setEndDate(model.getEndDate());
        soapModel.setItemId(model.getItemId());
        soapModel.setModifiedDate(model.getModifiedDate());
        soapModel.setEntityCode(model.getEntityCode());
        soapModel.setStartDate(model.getStartDate());
        soapModel.setCreatedDate(model.getCreatedDate());
        soapModel.setCreatedBy(model.getCreatedBy());
        soapModel.setSource(model.getSource());
        soapModel.setBatchId(model.getBatchId());
        soapModel.setAddChgDelIndicator(model.getAddChgDelIndicator());
        soapModel.setItemName(model.getItemName());
        soapModel.setItemIdentifier(model.getItemIdentifier());
        soapModel.setIdentifierCodeQualifierName(model.getIdentifierCodeQualifierName());
        soapModel.setModifiedBy(model.getModifiedBy());
        soapModel.setItemNo(model.getItemNo());
        soapModel.setIdentifierCodeQualifier(model.getIdentifierCodeQualifier());

        return soapModel;
    }

    public static VwItemIdentifierSoap[] toSoapModels(VwItemIdentifier[] models) {
        VwItemIdentifierSoap[] soapModels = new VwItemIdentifierSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static VwItemIdentifierSoap[][] toSoapModels(
        VwItemIdentifier[][] models) {
        VwItemIdentifierSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new VwItemIdentifierSoap[models.length][models[0].length];
        } else {
            soapModels = new VwItemIdentifierSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static VwItemIdentifierSoap[] toSoapModels(
        List<VwItemIdentifier> models) {
        List<VwItemIdentifierSoap> soapModels = new ArrayList<VwItemIdentifierSoap>(models.size());

        for (VwItemIdentifier model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new VwItemIdentifierSoap[soapModels.size()]);
    }

    public int getPrimaryKey() {
        return _itemIdentifierSid;
    }

    public void setPrimaryKey(int pk) {
        setItemIdentifierSid(pk);
    }

    public String getItemStatus() {
        return _itemStatus;
    }

    public void setItemStatus(String itemStatus) {
        _itemStatus = itemStatus;
    }

    public int getItemIdentifierSid() {
        return _itemIdentifierSid;
    }

    public void setItemIdentifierSid(int itemIdentifierSid) {
        _itemIdentifierSid = itemIdentifierSid;
    }

    public Date getEndDate() {
        return _endDate;
    }

    public void setEndDate(Date endDate) {
        _endDate = endDate;
    }

    public String getItemId() {
        return _itemId;
    }

    public void setItemId(String itemId) {
        _itemId = itemId;
    }

    public Date getModifiedDate() {
        return _modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        _modifiedDate = modifiedDate;
    }

    public String getEntityCode() {
        return _entityCode;
    }

    public void setEntityCode(String entityCode) {
        _entityCode = entityCode;
    }

    public Date getStartDate() {
        return _startDate;
    }

    public void setStartDate(Date startDate) {
        _startDate = startDate;
    }

    public Date getCreatedDate() {
        return _createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        _createdDate = createdDate;
    }

    public String getCreatedBy() {
        return _createdBy;
    }

    public void setCreatedBy(String createdBy) {
        _createdBy = createdBy;
    }

    public String getSource() {
        return _source;
    }

    public void setSource(String source) {
        _source = source;
    }

    public String getBatchId() {
        return _batchId;
    }

    public void setBatchId(String batchId) {
        _batchId = batchId;
    }

    public String getAddChgDelIndicator() {
        return _addChgDelIndicator;
    }

    public void setAddChgDelIndicator(String addChgDelIndicator) {
        _addChgDelIndicator = addChgDelIndicator;
    }

    public String getItemName() {
        return _itemName;
    }

    public void setItemName(String itemName) {
        _itemName = itemName;
    }

    public String getItemIdentifier() {
        return _itemIdentifier;
    }

    public void setItemIdentifier(String itemIdentifier) {
        _itemIdentifier = itemIdentifier;
    }

    public String getIdentifierCodeQualifierName() {
        return _identifierCodeQualifierName;
    }

    public void setIdentifierCodeQualifierName(
        String identifierCodeQualifierName) {
        _identifierCodeQualifierName = identifierCodeQualifierName;
    }

    public String getModifiedBy() {
        return _modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        _modifiedBy = modifiedBy;
    }

    public String getItemNo() {
        return _itemNo;
    }

    public void setItemNo(String itemNo) {
        _itemNo = itemNo;
    }

    public String getIdentifierCodeQualifier() {
        return _identifierCodeQualifier;
    }

    public void setIdentifierCodeQualifier(String identifierCodeQualifier) {
        _identifierCodeQualifier = identifierCodeQualifier;
    }
}
