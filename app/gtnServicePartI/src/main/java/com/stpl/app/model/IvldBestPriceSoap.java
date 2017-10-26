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
public class IvldBestPriceSoap implements Serializable {
    private String _itemDesc;
    private String _bestPriceIntfid;
    private String _accountId;
    private String _sellingPrice;
    private String _period;
    private String _itemId;
    private Date _modifiedDate;
    private String _createdBy;
    private Date _createdDate;
    private String _source;
    private String _addChgDelIndicator;
    private String _initialDiscount;
    private String _errorCode;
    private String _modifiedBy;
    private Date _intfInsertedDate;
    private String _itemNo;
    private String _reprocessedFlag;
    private String _contractEndDate;
    private int _ivldBestPriceSid;
    private String _contractId;
    private String _beginningWacPackage;
    private String _reasonForFailure;
    private String _contractStartDate;
    private String _batchId;
    private String _errorField;
    private String _initialBestPrice;
    private String _contractNo;
    private boolean _checkRecord;

    public IvldBestPriceSoap() {
    }

    public static IvldBestPriceSoap toSoapModel(IvldBestPrice model) {
        IvldBestPriceSoap soapModel = new IvldBestPriceSoap();

        soapModel.setItemDesc(model.getItemDesc());
        soapModel.setBestPriceIntfid(model.getBestPriceIntfid());
        soapModel.setAccountId(model.getAccountId());
        soapModel.setSellingPrice(model.getSellingPrice());
        soapModel.setPeriod(model.getPeriod());
        soapModel.setItemId(model.getItemId());
        soapModel.setModifiedDate(model.getModifiedDate());
        soapModel.setCreatedBy(model.getCreatedBy());
        soapModel.setCreatedDate(model.getCreatedDate());
        soapModel.setSource(model.getSource());
        soapModel.setAddChgDelIndicator(model.getAddChgDelIndicator());
        soapModel.setInitialDiscount(model.getInitialDiscount());
        soapModel.setErrorCode(model.getErrorCode());
        soapModel.setModifiedBy(model.getModifiedBy());
        soapModel.setIntfInsertedDate(model.getIntfInsertedDate());
        soapModel.setItemNo(model.getItemNo());
        soapModel.setReprocessedFlag(model.getReprocessedFlag());
        soapModel.setContractEndDate(model.getContractEndDate());
        soapModel.setIvldBestPriceSid(model.getIvldBestPriceSid());
        soapModel.setContractId(model.getContractId());
        soapModel.setBeginningWacPackage(model.getBeginningWacPackage());
        soapModel.setReasonForFailure(model.getReasonForFailure());
        soapModel.setContractStartDate(model.getContractStartDate());
        soapModel.setBatchId(model.getBatchId());
        soapModel.setErrorField(model.getErrorField());
        soapModel.setInitialBestPrice(model.getInitialBestPrice());
        soapModel.setContractNo(model.getContractNo());
        soapModel.setCheckRecord(model.getCheckRecord());

        return soapModel;
    }

    public static IvldBestPriceSoap[] toSoapModels(IvldBestPrice[] models) {
        IvldBestPriceSoap[] soapModels = new IvldBestPriceSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static IvldBestPriceSoap[][] toSoapModels(IvldBestPrice[][] models) {
        IvldBestPriceSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new IvldBestPriceSoap[models.length][models[0].length];
        } else {
            soapModels = new IvldBestPriceSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static IvldBestPriceSoap[] toSoapModels(List<IvldBestPrice> models) {
        List<IvldBestPriceSoap> soapModels = new ArrayList<IvldBestPriceSoap>(models.size());

        for (IvldBestPrice model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new IvldBestPriceSoap[soapModels.size()]);
    }

    public int getPrimaryKey() {
        return _ivldBestPriceSid;
    }

    public void setPrimaryKey(int pk) {
        setIvldBestPriceSid(pk);
    }

    public String getItemDesc() {
        return _itemDesc;
    }

    public void setItemDesc(String itemDesc) {
        _itemDesc = itemDesc;
    }

    public String getBestPriceIntfid() {
        return _bestPriceIntfid;
    }

    public void setBestPriceIntfid(String bestPriceIntfid) {
        _bestPriceIntfid = bestPriceIntfid;
    }

    public String getAccountId() {
        return _accountId;
    }

    public void setAccountId(String accountId) {
        _accountId = accountId;
    }

    public String getSellingPrice() {
        return _sellingPrice;
    }

    public void setSellingPrice(String sellingPrice) {
        _sellingPrice = sellingPrice;
    }

    public String getPeriod() {
        return _period;
    }

    public void setPeriod(String period) {
        _period = period;
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

    public String getCreatedBy() {
        return _createdBy;
    }

    public void setCreatedBy(String createdBy) {
        _createdBy = createdBy;
    }

    public Date getCreatedDate() {
        return _createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        _createdDate = createdDate;
    }

    public String getSource() {
        return _source;
    }

    public void setSource(String source) {
        _source = source;
    }

    public String getAddChgDelIndicator() {
        return _addChgDelIndicator;
    }

    public void setAddChgDelIndicator(String addChgDelIndicator) {
        _addChgDelIndicator = addChgDelIndicator;
    }

    public String getInitialDiscount() {
        return _initialDiscount;
    }

    public void setInitialDiscount(String initialDiscount) {
        _initialDiscount = initialDiscount;
    }

    public String getErrorCode() {
        return _errorCode;
    }

    public void setErrorCode(String errorCode) {
        _errorCode = errorCode;
    }

    public String getModifiedBy() {
        return _modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        _modifiedBy = modifiedBy;
    }

    public Date getIntfInsertedDate() {
        return _intfInsertedDate;
    }

    public void setIntfInsertedDate(Date intfInsertedDate) {
        _intfInsertedDate = intfInsertedDate;
    }

    public String getItemNo() {
        return _itemNo;
    }

    public void setItemNo(String itemNo) {
        _itemNo = itemNo;
    }

    public String getReprocessedFlag() {
        return _reprocessedFlag;
    }

    public void setReprocessedFlag(String reprocessedFlag) {
        _reprocessedFlag = reprocessedFlag;
    }

    public String getContractEndDate() {
        return _contractEndDate;
    }

    public void setContractEndDate(String contractEndDate) {
        _contractEndDate = contractEndDate;
    }

    public int getIvldBestPriceSid() {
        return _ivldBestPriceSid;
    }

    public void setIvldBestPriceSid(int ivldBestPriceSid) {
        _ivldBestPriceSid = ivldBestPriceSid;
    }

    public String getContractId() {
        return _contractId;
    }

    public void setContractId(String contractId) {
        _contractId = contractId;
    }

    public String getBeginningWacPackage() {
        return _beginningWacPackage;
    }

    public void setBeginningWacPackage(String beginningWacPackage) {
        _beginningWacPackage = beginningWacPackage;
    }

    public String getReasonForFailure() {
        return _reasonForFailure;
    }

    public void setReasonForFailure(String reasonForFailure) {
        _reasonForFailure = reasonForFailure;
    }

    public String getContractStartDate() {
        return _contractStartDate;
    }

    public void setContractStartDate(String contractStartDate) {
        _contractStartDate = contractStartDate;
    }

    public String getBatchId() {
        return _batchId;
    }

    public void setBatchId(String batchId) {
        _batchId = batchId;
    }

    public String getErrorField() {
        return _errorField;
    }

    public void setErrorField(String errorField) {
        _errorField = errorField;
    }

    public String getInitialBestPrice() {
        return _initialBestPrice;
    }

    public void setInitialBestPrice(String initialBestPrice) {
        _initialBestPrice = initialBestPrice;
    }

    public String getContractNo() {
        return _contractNo;
    }

    public void setContractNo(String contractNo) {
        _contractNo = contractNo;
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
}
