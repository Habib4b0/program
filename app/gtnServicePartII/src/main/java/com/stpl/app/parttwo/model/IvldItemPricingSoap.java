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
public class IvldItemPricingSoap implements Serializable {
    private String _itemNo;
    private String _modifiedBy;
    private String _pricingCodeQualifierName;
    private Date _createdDate;
    private String _endDate;
    private String _batchId;
    private String _itemName;
    private String _errorCode;
    private String _reprocessedFlag;
    private String _itemPricingIntfid;
    private int _ivldItemPricingSid;
    private String _pricingCodeStatus;
    private String _createdBy;
    private String _itemId;
    private String _errorField;
    private String _startDate;
    private String _itemUom;
    private Date _modifiedDate;
    private String _reasonForFailure;
    private String _source;
    private String _pricingCodeQualifier;
    private String _addChgDelIndicator;
    private String _entityCode;
    private String _itemPrice;
    private Date _intfInsertedDate;
    private boolean _checkRecord;
    private String _itemPriceprecision;

    public IvldItemPricingSoap() {
    }

    public static IvldItemPricingSoap toSoapModel(IvldItemPricing model) {
        IvldItemPricingSoap soapModel = new IvldItemPricingSoap();

        soapModel.setItemNo(model.getItemNo());
        soapModel.setModifiedBy(model.getModifiedBy());
        soapModel.setPricingCodeQualifierName(model.getPricingCodeQualifierName());
        soapModel.setCreatedDate(model.getCreatedDate());
        soapModel.setEndDate(model.getEndDate());
        soapModel.setBatchId(model.getBatchId());
        soapModel.setItemName(model.getItemName());
        soapModel.setErrorCode(model.getErrorCode());
        soapModel.setReprocessedFlag(model.getReprocessedFlag());
        soapModel.setItemPricingIntfid(model.getItemPricingIntfid());
        soapModel.setIvldItemPricingSid(model.getIvldItemPricingSid());
        soapModel.setPricingCodeStatus(model.getPricingCodeStatus());
        soapModel.setCreatedBy(model.getCreatedBy());
        soapModel.setItemId(model.getItemId());
        soapModel.setErrorField(model.getErrorField());
        soapModel.setStartDate(model.getStartDate());
        soapModel.setItemUom(model.getItemUom());
        soapModel.setModifiedDate(model.getModifiedDate());
        soapModel.setReasonForFailure(model.getReasonForFailure());
        soapModel.setSource(model.getSource());
        soapModel.setPricingCodeQualifier(model.getPricingCodeQualifier());
        soapModel.setAddChgDelIndicator(model.getAddChgDelIndicator());
        soapModel.setEntityCode(model.getEntityCode());
        soapModel.setItemPrice(model.getItemPrice());
        soapModel.setIntfInsertedDate(model.getIntfInsertedDate());
        soapModel.setCheckRecord(model.getCheckRecord());
        soapModel.setItemPriceprecision(model.getItemPriceprecision());

        return soapModel;
    }

    public static IvldItemPricingSoap[] toSoapModels(IvldItemPricing[] models) {
        IvldItemPricingSoap[] soapModels = new IvldItemPricingSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static IvldItemPricingSoap[][] toSoapModels(
        IvldItemPricing[][] models) {
        IvldItemPricingSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new IvldItemPricingSoap[models.length][models[0].length];
        } else {
            soapModels = new IvldItemPricingSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static IvldItemPricingSoap[] toSoapModels(
        List<IvldItemPricing> models) {
        List<IvldItemPricingSoap> soapModels = new ArrayList<IvldItemPricingSoap>(models.size());

        for (IvldItemPricing model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new IvldItemPricingSoap[soapModels.size()]);
    }

    public int getPrimaryKey() {
        return _ivldItemPricingSid;
    }

    public void setPrimaryKey(int pk) {
        setIvldItemPricingSid(pk);
    }

    public String getItemNo() {
        return _itemNo;
    }

    public void setItemNo(String itemNo) {
        _itemNo = itemNo;
    }

    public String getModifiedBy() {
        return _modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        _modifiedBy = modifiedBy;
    }

    public String getPricingCodeQualifierName() {
        return _pricingCodeQualifierName;
    }

    public void setPricingCodeQualifierName(String pricingCodeQualifierName) {
        _pricingCodeQualifierName = pricingCodeQualifierName;
    }

    public Date getCreatedDate() {
        return _createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        _createdDate = createdDate;
    }

    public String getEndDate() {
        return _endDate;
    }

    public void setEndDate(String endDate) {
        _endDate = endDate;
    }

    public String getBatchId() {
        return _batchId;
    }

    public void setBatchId(String batchId) {
        _batchId = batchId;
    }

    public String getItemName() {
        return _itemName;
    }

    public void setItemName(String itemName) {
        _itemName = itemName;
    }

    public String getErrorCode() {
        return _errorCode;
    }

    public void setErrorCode(String errorCode) {
        _errorCode = errorCode;
    }

    public String getReprocessedFlag() {
        return _reprocessedFlag;
    }

    public void setReprocessedFlag(String reprocessedFlag) {
        _reprocessedFlag = reprocessedFlag;
    }

    public String getItemPricingIntfid() {
        return _itemPricingIntfid;
    }

    public void setItemPricingIntfid(String itemPricingIntfid) {
        _itemPricingIntfid = itemPricingIntfid;
    }

    public int getIvldItemPricingSid() {
        return _ivldItemPricingSid;
    }

    public void setIvldItemPricingSid(int ivldItemPricingSid) {
        _ivldItemPricingSid = ivldItemPricingSid;
    }

    public String getPricingCodeStatus() {
        return _pricingCodeStatus;
    }

    public void setPricingCodeStatus(String pricingCodeStatus) {
        _pricingCodeStatus = pricingCodeStatus;
    }

    public String getCreatedBy() {
        return _createdBy;
    }

    public void setCreatedBy(String createdBy) {
        _createdBy = createdBy;
    }

    public String getItemId() {
        return _itemId;
    }

    public void setItemId(String itemId) {
        _itemId = itemId;
    }

    public String getErrorField() {
        return _errorField;
    }

    public void setErrorField(String errorField) {
        _errorField = errorField;
    }

    public String getStartDate() {
        return _startDate;
    }

    public void setStartDate(String startDate) {
        _startDate = startDate;
    }

    public String getItemUom() {
        return _itemUom;
    }

    public void setItemUom(String itemUom) {
        _itemUom = itemUom;
    }

    public Date getModifiedDate() {
        return _modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        _modifiedDate = modifiedDate;
    }

    public String getReasonForFailure() {
        return _reasonForFailure;
    }

    public void setReasonForFailure(String reasonForFailure) {
        _reasonForFailure = reasonForFailure;
    }

    public String getSource() {
        return _source;
    }

    public void setSource(String source) {
        _source = source;
    }

    public String getPricingCodeQualifier() {
        return _pricingCodeQualifier;
    }

    public void setPricingCodeQualifier(String pricingCodeQualifier) {
        _pricingCodeQualifier = pricingCodeQualifier;
    }

    public String getAddChgDelIndicator() {
        return _addChgDelIndicator;
    }

    public void setAddChgDelIndicator(String addChgDelIndicator) {
        _addChgDelIndicator = addChgDelIndicator;
    }

    public String getEntityCode() {
        return _entityCode;
    }

    public void setEntityCode(String entityCode) {
        _entityCode = entityCode;
    }

    public String getItemPrice() {
        return _itemPrice;
    }

    public void setItemPrice(String itemPrice) {
        _itemPrice = itemPrice;
    }

    public Date getIntfInsertedDate() {
        return _intfInsertedDate;
    }

    public void setIntfInsertedDate(Date intfInsertedDate) {
        _intfInsertedDate = intfInsertedDate;
    }

    public boolean getCheckRecord() {
        return _checkRecord;
    }

    public boolean isCheckRecord() {
        return _checkRecord;
    }

    public void setCheckRecord(boolean checkRecord) {
        _checkRecord = checkRecord;
    }

    public String getItemPriceprecision() {
        return _itemPriceprecision;
    }

    public void setItemPriceprecision(String itemPriceprecision) {
        _itemPriceprecision = itemPriceprecision;
    }
}
