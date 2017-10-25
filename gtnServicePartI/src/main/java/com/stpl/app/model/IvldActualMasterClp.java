package com.stpl.app.model;

import com.stpl.app.service.ClpSerializer;
import com.stpl.app.service.IvldActualMasterLocalServiceUtil;

import com.stpl.portal.kernel.bean.AutoEscapeBeanHandler;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.ProxyUtil;
import com.stpl.portal.kernel.util.StringBundler;
import com.stpl.portal.model.BaseModel;
import com.stpl.portal.model.impl.BaseModelImpl;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class IvldActualMasterClp extends BaseModelImpl<IvldActualMaster>
    implements IvldActualMaster {
    private String _actualIntfid;
    private String _accountId;
    private String _programStateCode;
    private String _settlementNo;
    private String _accrualActualEndDate;
    private String _actualId;
    private Date _modifiedDate;
    private String _divisionId;
    private String _organizationKey;
    private String _quantityInclusion;
    private String _cashPaidDate;
    private String _source;
    private String _addChgDelIndicator;
    private String _analysisCode;
    private String _accrualActualStartDate;
    private String _modifiedBy;
    private String _salesAmount;
    private String _quantity;
    private String _sentOut;
    private String _accountNo;
    private String _reasonForFailure;
    private String _accountName;
    private String _provisionId;
    private String _amount;
    private String _marketId;
    private String _isActive;
    private String _settlementMethodNo;
    private String _itemIdentifierCodeQualifier;
    private String _priceAdjustmentName;
    private String _errorField;
    private String _recordSequence;
    private String _mandatedDiscountAmount;
    private String _parentcomDivmktBrandProdkey;
    private String _price;
    private String _dispensedDate;
    private String _itemId;
    private String _accrualProcessed;
    private String _uploadDate;
    private String _createdBy;
    private Date _createdDate;
    private String _invoiceLineNo;
    private String _errorCode;
    private Date _intfInsertedDate;
    private String _itemNo;
    private String _reprocessedFlag;
    private String _acctIdentifierCodeQualifier;
    private String _contractId;
    private String _brandId;
    private String _comDivMktBrandProdKey;
    private String _claimIndicator;
    private int _ivldActualMasterSid;
    private String _batchId;
    private String _provisionClaimIndicator;
    private boolean _checkRecord;
    private BaseModel<?> _ivldActualMasterRemoteModel;

    public IvldActualMasterClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return IvldActualMaster.class;
    }

    @Override
    public String getModelClassName() {
        return IvldActualMaster.class.getName();
    }

    @Override
    public int getPrimaryKey() {
        return _ivldActualMasterSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setIvldActualMasterSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _ivldActualMasterSid;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Integer) primaryKeyObj).intValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("actualIntfid", getActualIntfid());
        attributes.put("accountId", getAccountId());
        attributes.put("programStateCode", getProgramStateCode());
        attributes.put("settlementNo", getSettlementNo());
        attributes.put("accrualActualEndDate", getAccrualActualEndDate());
        attributes.put("actualId", getActualId());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("divisionId", getDivisionId());
        attributes.put("organizationKey", getOrganizationKey());
        attributes.put("quantityInclusion", getQuantityInclusion());
        attributes.put("cashPaidDate", getCashPaidDate());
        attributes.put("source", getSource());
        attributes.put("addChgDelIndicator", getAddChgDelIndicator());
        attributes.put("analysisCode", getAnalysisCode());
        attributes.put("accrualActualStartDate", getAccrualActualStartDate());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("salesAmount", getSalesAmount());
        attributes.put("quantity", getQuantity());
        attributes.put("sentOut", getSentOut());
        attributes.put("accountNo", getAccountNo());
        attributes.put("reasonForFailure", getReasonForFailure());
        attributes.put("accountName", getAccountName());
        attributes.put("provisionId", getProvisionId());
        attributes.put("amount", getAmount());
        attributes.put("marketId", getMarketId());
        attributes.put("isActive", getIsActive());
        attributes.put("settlementMethodNo", getSettlementMethodNo());
        attributes.put("itemIdentifierCodeQualifier",
            getItemIdentifierCodeQualifier());
        attributes.put("priceAdjustmentName", getPriceAdjustmentName());
        attributes.put("errorField", getErrorField());
        attributes.put("recordSequence", getRecordSequence());
        attributes.put("mandatedDiscountAmount", getMandatedDiscountAmount());
        attributes.put("parentcomDivmktBrandProdkey",
            getParentcomDivmktBrandProdkey());
        attributes.put("price", getPrice());
        attributes.put("dispensedDate", getDispensedDate());
        attributes.put("itemId", getItemId());
        attributes.put("accrualProcessed", getAccrualProcessed());
        attributes.put("uploadDate", getUploadDate());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("invoiceLineNo", getInvoiceLineNo());
        attributes.put("errorCode", getErrorCode());
        attributes.put("intfInsertedDate", getIntfInsertedDate());
        attributes.put("itemNo", getItemNo());
        attributes.put("reprocessedFlag", getReprocessedFlag());
        attributes.put("acctIdentifierCodeQualifier",
            getAcctIdentifierCodeQualifier());
        attributes.put("contractId", getContractId());
        attributes.put("brandId", getBrandId());
        attributes.put("comDivMktBrandProdKey", getComDivMktBrandProdKey());
        attributes.put("claimIndicator", getClaimIndicator());
        attributes.put("ivldActualMasterSid", getIvldActualMasterSid());
        attributes.put("batchId", getBatchId());
        attributes.put("provisionClaimIndicator", getProvisionClaimIndicator());
        attributes.put("checkRecord", getCheckRecord());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        String actualIntfid = (String) attributes.get("actualIntfid");

        if (actualIntfid != null) {
            setActualIntfid(actualIntfid);
        }

        String accountId = (String) attributes.get("accountId");

        if (accountId != null) {
            setAccountId(accountId);
        }

        String programStateCode = (String) attributes.get("programStateCode");

        if (programStateCode != null) {
            setProgramStateCode(programStateCode);
        }

        String settlementNo = (String) attributes.get("settlementNo");

        if (settlementNo != null) {
            setSettlementNo(settlementNo);
        }

        String accrualActualEndDate = (String) attributes.get(
                "accrualActualEndDate");

        if (accrualActualEndDate != null) {
            setAccrualActualEndDate(accrualActualEndDate);
        }

        String actualId = (String) attributes.get("actualId");

        if (actualId != null) {
            setActualId(actualId);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        String divisionId = (String) attributes.get("divisionId");

        if (divisionId != null) {
            setDivisionId(divisionId);
        }

        String organizationKey = (String) attributes.get("organizationKey");

        if (organizationKey != null) {
            setOrganizationKey(organizationKey);
        }

        String quantityInclusion = (String) attributes.get("quantityInclusion");

        if (quantityInclusion != null) {
            setQuantityInclusion(quantityInclusion);
        }

        String cashPaidDate = (String) attributes.get("cashPaidDate");

        if (cashPaidDate != null) {
            setCashPaidDate(cashPaidDate);
        }

        String source = (String) attributes.get("source");

        if (source != null) {
            setSource(source);
        }

        String addChgDelIndicator = (String) attributes.get(
                "addChgDelIndicator");

        if (addChgDelIndicator != null) {
            setAddChgDelIndicator(addChgDelIndicator);
        }

        String analysisCode = (String) attributes.get("analysisCode");

        if (analysisCode != null) {
            setAnalysisCode(analysisCode);
        }

        String accrualActualStartDate = (String) attributes.get(
                "accrualActualStartDate");

        if (accrualActualStartDate != null) {
            setAccrualActualStartDate(accrualActualStartDate);
        }

        String modifiedBy = (String) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        String salesAmount = (String) attributes.get("salesAmount");

        if (salesAmount != null) {
            setSalesAmount(salesAmount);
        }

        String quantity = (String) attributes.get("quantity");

        if (quantity != null) {
            setQuantity(quantity);
        }

        String sentOut = (String) attributes.get("sentOut");

        if (sentOut != null) {
            setSentOut(sentOut);
        }

        String accountNo = (String) attributes.get("accountNo");

        if (accountNo != null) {
            setAccountNo(accountNo);
        }

        String reasonForFailure = (String) attributes.get("reasonForFailure");

        if (reasonForFailure != null) {
            setReasonForFailure(reasonForFailure);
        }

        String accountName = (String) attributes.get("accountName");

        if (accountName != null) {
            setAccountName(accountName);
        }

        String provisionId = (String) attributes.get("provisionId");

        if (provisionId != null) {
            setProvisionId(provisionId);
        }

        String amount = (String) attributes.get("amount");

        if (amount != null) {
            setAmount(amount);
        }

        String marketId = (String) attributes.get("marketId");

        if (marketId != null) {
            setMarketId(marketId);
        }

        String isActive = (String) attributes.get("isActive");

        if (isActive != null) {
            setIsActive(isActive);
        }

        String settlementMethodNo = (String) attributes.get(
                "settlementMethodNo");

        if (settlementMethodNo != null) {
            setSettlementMethodNo(settlementMethodNo);
        }

        String itemIdentifierCodeQualifier = (String) attributes.get(
                "itemIdentifierCodeQualifier");

        if (itemIdentifierCodeQualifier != null) {
            setItemIdentifierCodeQualifier(itemIdentifierCodeQualifier);
        }

        String priceAdjustmentName = (String) attributes.get(
                "priceAdjustmentName");

        if (priceAdjustmentName != null) {
            setPriceAdjustmentName(priceAdjustmentName);
        }

        String errorField = (String) attributes.get("errorField");

        if (errorField != null) {
            setErrorField(errorField);
        }

        String recordSequence = (String) attributes.get("recordSequence");

        if (recordSequence != null) {
            setRecordSequence(recordSequence);
        }

        String mandatedDiscountAmount = (String) attributes.get(
                "mandatedDiscountAmount");

        if (mandatedDiscountAmount != null) {
            setMandatedDiscountAmount(mandatedDiscountAmount);
        }

        String parentcomDivmktBrandProdkey = (String) attributes.get(
                "parentcomDivmktBrandProdkey");

        if (parentcomDivmktBrandProdkey != null) {
            setParentcomDivmktBrandProdkey(parentcomDivmktBrandProdkey);
        }

        String price = (String) attributes.get("price");

        if (price != null) {
            setPrice(price);
        }

        String dispensedDate = (String) attributes.get("dispensedDate");

        if (dispensedDate != null) {
            setDispensedDate(dispensedDate);
        }

        String itemId = (String) attributes.get("itemId");

        if (itemId != null) {
            setItemId(itemId);
        }

        String accrualProcessed = (String) attributes.get("accrualProcessed");

        if (accrualProcessed != null) {
            setAccrualProcessed(accrualProcessed);
        }

        String uploadDate = (String) attributes.get("uploadDate");

        if (uploadDate != null) {
            setUploadDate(uploadDate);
        }

        String createdBy = (String) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        String invoiceLineNo = (String) attributes.get("invoiceLineNo");

        if (invoiceLineNo != null) {
            setInvoiceLineNo(invoiceLineNo);
        }

        String errorCode = (String) attributes.get("errorCode");

        if (errorCode != null) {
            setErrorCode(errorCode);
        }

        Date intfInsertedDate = (Date) attributes.get("intfInsertedDate");

        if (intfInsertedDate != null) {
            setIntfInsertedDate(intfInsertedDate);
        }

        String itemNo = (String) attributes.get("itemNo");

        if (itemNo != null) {
            setItemNo(itemNo);
        }

        String reprocessedFlag = (String) attributes.get("reprocessedFlag");

        if (reprocessedFlag != null) {
            setReprocessedFlag(reprocessedFlag);
        }

        String acctIdentifierCodeQualifier = (String) attributes.get(
                "acctIdentifierCodeQualifier");

        if (acctIdentifierCodeQualifier != null) {
            setAcctIdentifierCodeQualifier(acctIdentifierCodeQualifier);
        }

        String contractId = (String) attributes.get("contractId");

        if (contractId != null) {
            setContractId(contractId);
        }

        String brandId = (String) attributes.get("brandId");

        if (brandId != null) {
            setBrandId(brandId);
        }

        String comDivMktBrandProdKey = (String) attributes.get(
                "comDivMktBrandProdKey");

        if (comDivMktBrandProdKey != null) {
            setComDivMktBrandProdKey(comDivMktBrandProdKey);
        }

        String claimIndicator = (String) attributes.get("claimIndicator");

        if (claimIndicator != null) {
            setClaimIndicator(claimIndicator);
        }

        Integer ivldActualMasterSid = (Integer) attributes.get(
                "ivldActualMasterSid");

        if (ivldActualMasterSid != null) {
            setIvldActualMasterSid(ivldActualMasterSid);
        }

        String batchId = (String) attributes.get("batchId");

        if (batchId != null) {
            setBatchId(batchId);
        }

        String provisionClaimIndicator = (String) attributes.get(
                "provisionClaimIndicator");

        if (provisionClaimIndicator != null) {
            setProvisionClaimIndicator(provisionClaimIndicator);
        }

        Boolean checkRecord = (Boolean) attributes.get("checkRecord");

        if (checkRecord != null) {
            setCheckRecord(checkRecord);
        }
    }

    @Override
    public String getActualIntfid() {
        return _actualIntfid;
    }

    @Override
    public void setActualIntfid(String actualIntfid) {
        _actualIntfid = actualIntfid;

        if (_ivldActualMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldActualMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setActualIntfid", String.class);

                method.invoke(_ivldActualMasterRemoteModel, actualIntfid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getAccountId() {
        return _accountId;
    }

    @Override
    public void setAccountId(String accountId) {
        _accountId = accountId;

        if (_ivldActualMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldActualMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setAccountId", String.class);

                method.invoke(_ivldActualMasterRemoteModel, accountId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getProgramStateCode() {
        return _programStateCode;
    }

    @Override
    public void setProgramStateCode(String programStateCode) {
        _programStateCode = programStateCode;

        if (_ivldActualMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldActualMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setProgramStateCode",
                        String.class);

                method.invoke(_ivldActualMasterRemoteModel, programStateCode);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getSettlementNo() {
        return _settlementNo;
    }

    @Override
    public void setSettlementNo(String settlementNo) {
        _settlementNo = settlementNo;

        if (_ivldActualMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldActualMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setSettlementNo", String.class);

                method.invoke(_ivldActualMasterRemoteModel, settlementNo);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getAccrualActualEndDate() {
        return _accrualActualEndDate;
    }

    @Override
    public void setAccrualActualEndDate(String accrualActualEndDate) {
        _accrualActualEndDate = accrualActualEndDate;

        if (_ivldActualMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldActualMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setAccrualActualEndDate",
                        String.class);

                method.invoke(_ivldActualMasterRemoteModel, accrualActualEndDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getActualId() {
        return _actualId;
    }

    @Override
    public void setActualId(String actualId) {
        _actualId = actualId;

        if (_ivldActualMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldActualMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setActualId", String.class);

                method.invoke(_ivldActualMasterRemoteModel, actualId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getModifiedDate() {
        return _modifiedDate;
    }

    @Override
    public void setModifiedDate(Date modifiedDate) {
        _modifiedDate = modifiedDate;

        if (_ivldActualMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldActualMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedDate", Date.class);

                method.invoke(_ivldActualMasterRemoteModel, modifiedDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getDivisionId() {
        return _divisionId;
    }

    @Override
    public void setDivisionId(String divisionId) {
        _divisionId = divisionId;

        if (_ivldActualMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldActualMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setDivisionId", String.class);

                method.invoke(_ivldActualMasterRemoteModel, divisionId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getOrganizationKey() {
        return _organizationKey;
    }

    @Override
    public void setOrganizationKey(String organizationKey) {
        _organizationKey = organizationKey;

        if (_ivldActualMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldActualMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setOrganizationKey",
                        String.class);

                method.invoke(_ivldActualMasterRemoteModel, organizationKey);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getQuantityInclusion() {
        return _quantityInclusion;
    }

    @Override
    public void setQuantityInclusion(String quantityInclusion) {
        _quantityInclusion = quantityInclusion;

        if (_ivldActualMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldActualMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setQuantityInclusion",
                        String.class);

                method.invoke(_ivldActualMasterRemoteModel, quantityInclusion);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getCashPaidDate() {
        return _cashPaidDate;
    }

    @Override
    public void setCashPaidDate(String cashPaidDate) {
        _cashPaidDate = cashPaidDate;

        if (_ivldActualMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldActualMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCashPaidDate", String.class);

                method.invoke(_ivldActualMasterRemoteModel, cashPaidDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getSource() {
        return _source;
    }

    @Override
    public void setSource(String source) {
        _source = source;

        if (_ivldActualMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldActualMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setSource", String.class);

                method.invoke(_ivldActualMasterRemoteModel, source);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getAddChgDelIndicator() {
        return _addChgDelIndicator;
    }

    @Override
    public void setAddChgDelIndicator(String addChgDelIndicator) {
        _addChgDelIndicator = addChgDelIndicator;

        if (_ivldActualMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldActualMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setAddChgDelIndicator",
                        String.class);

                method.invoke(_ivldActualMasterRemoteModel, addChgDelIndicator);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getAnalysisCode() {
        return _analysisCode;
    }

    @Override
    public void setAnalysisCode(String analysisCode) {
        _analysisCode = analysisCode;

        if (_ivldActualMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldActualMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setAnalysisCode", String.class);

                method.invoke(_ivldActualMasterRemoteModel, analysisCode);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getAccrualActualStartDate() {
        return _accrualActualStartDate;
    }

    @Override
    public void setAccrualActualStartDate(String accrualActualStartDate) {
        _accrualActualStartDate = accrualActualStartDate;

        if (_ivldActualMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldActualMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setAccrualActualStartDate",
                        String.class);

                method.invoke(_ivldActualMasterRemoteModel,
                    accrualActualStartDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getModifiedBy() {
        return _modifiedBy;
    }

    @Override
    public void setModifiedBy(String modifiedBy) {
        _modifiedBy = modifiedBy;

        if (_ivldActualMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldActualMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedBy", String.class);

                method.invoke(_ivldActualMasterRemoteModel, modifiedBy);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getSalesAmount() {
        return _salesAmount;
    }

    @Override
    public void setSalesAmount(String salesAmount) {
        _salesAmount = salesAmount;

        if (_ivldActualMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldActualMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setSalesAmount", String.class);

                method.invoke(_ivldActualMasterRemoteModel, salesAmount);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getQuantity() {
        return _quantity;
    }

    @Override
    public void setQuantity(String quantity) {
        _quantity = quantity;

        if (_ivldActualMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldActualMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setQuantity", String.class);

                method.invoke(_ivldActualMasterRemoteModel, quantity);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getSentOut() {
        return _sentOut;
    }

    @Override
    public void setSentOut(String sentOut) {
        _sentOut = sentOut;

        if (_ivldActualMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldActualMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setSentOut", String.class);

                method.invoke(_ivldActualMasterRemoteModel, sentOut);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getAccountNo() {
        return _accountNo;
    }

    @Override
    public void setAccountNo(String accountNo) {
        _accountNo = accountNo;

        if (_ivldActualMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldActualMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setAccountNo", String.class);

                method.invoke(_ivldActualMasterRemoteModel, accountNo);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getReasonForFailure() {
        return _reasonForFailure;
    }

    @Override
    public void setReasonForFailure(String reasonForFailure) {
        _reasonForFailure = reasonForFailure;

        if (_ivldActualMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldActualMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setReasonForFailure",
                        String.class);

                method.invoke(_ivldActualMasterRemoteModel, reasonForFailure);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getAccountName() {
        return _accountName;
    }

    @Override
    public void setAccountName(String accountName) {
        _accountName = accountName;

        if (_ivldActualMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldActualMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setAccountName", String.class);

                method.invoke(_ivldActualMasterRemoteModel, accountName);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getProvisionId() {
        return _provisionId;
    }

    @Override
    public void setProvisionId(String provisionId) {
        _provisionId = provisionId;

        if (_ivldActualMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldActualMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setProvisionId", String.class);

                method.invoke(_ivldActualMasterRemoteModel, provisionId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getAmount() {
        return _amount;
    }

    @Override
    public void setAmount(String amount) {
        _amount = amount;

        if (_ivldActualMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldActualMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setAmount", String.class);

                method.invoke(_ivldActualMasterRemoteModel, amount);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getMarketId() {
        return _marketId;
    }

    @Override
    public void setMarketId(String marketId) {
        _marketId = marketId;

        if (_ivldActualMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldActualMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setMarketId", String.class);

                method.invoke(_ivldActualMasterRemoteModel, marketId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getIsActive() {
        return _isActive;
    }

    @Override
    public void setIsActive(String isActive) {
        _isActive = isActive;

        if (_ivldActualMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldActualMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setIsActive", String.class);

                method.invoke(_ivldActualMasterRemoteModel, isActive);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getSettlementMethodNo() {
        return _settlementMethodNo;
    }

    @Override
    public void setSettlementMethodNo(String settlementMethodNo) {
        _settlementMethodNo = settlementMethodNo;

        if (_ivldActualMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldActualMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setSettlementMethodNo",
                        String.class);

                method.invoke(_ivldActualMasterRemoteModel, settlementMethodNo);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getItemIdentifierCodeQualifier() {
        return _itemIdentifierCodeQualifier;
    }

    @Override
    public void setItemIdentifierCodeQualifier(
        String itemIdentifierCodeQualifier) {
        _itemIdentifierCodeQualifier = itemIdentifierCodeQualifier;

        if (_ivldActualMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldActualMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setItemIdentifierCodeQualifier",
                        String.class);

                method.invoke(_ivldActualMasterRemoteModel,
                    itemIdentifierCodeQualifier);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getPriceAdjustmentName() {
        return _priceAdjustmentName;
    }

    @Override
    public void setPriceAdjustmentName(String priceAdjustmentName) {
        _priceAdjustmentName = priceAdjustmentName;

        if (_ivldActualMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldActualMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setPriceAdjustmentName",
                        String.class);

                method.invoke(_ivldActualMasterRemoteModel, priceAdjustmentName);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getErrorField() {
        return _errorField;
    }

    @Override
    public void setErrorField(String errorField) {
        _errorField = errorField;

        if (_ivldActualMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldActualMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setErrorField", String.class);

                method.invoke(_ivldActualMasterRemoteModel, errorField);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getRecordSequence() {
        return _recordSequence;
    }

    @Override
    public void setRecordSequence(String recordSequence) {
        _recordSequence = recordSequence;

        if (_ivldActualMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldActualMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setRecordSequence",
                        String.class);

                method.invoke(_ivldActualMasterRemoteModel, recordSequence);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getMandatedDiscountAmount() {
        return _mandatedDiscountAmount;
    }

    @Override
    public void setMandatedDiscountAmount(String mandatedDiscountAmount) {
        _mandatedDiscountAmount = mandatedDiscountAmount;

        if (_ivldActualMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldActualMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setMandatedDiscountAmount",
                        String.class);

                method.invoke(_ivldActualMasterRemoteModel,
                    mandatedDiscountAmount);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getParentcomDivmktBrandProdkey() {
        return _parentcomDivmktBrandProdkey;
    }

    @Override
    public void setParentcomDivmktBrandProdkey(
        String parentcomDivmktBrandProdkey) {
        _parentcomDivmktBrandProdkey = parentcomDivmktBrandProdkey;

        if (_ivldActualMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldActualMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setParentcomDivmktBrandProdkey",
                        String.class);

                method.invoke(_ivldActualMasterRemoteModel,
                    parentcomDivmktBrandProdkey);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getPrice() {
        return _price;
    }

    @Override
    public void setPrice(String price) {
        _price = price;

        if (_ivldActualMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldActualMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setPrice", String.class);

                method.invoke(_ivldActualMasterRemoteModel, price);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getDispensedDate() {
        return _dispensedDate;
    }

    @Override
    public void setDispensedDate(String dispensedDate) {
        _dispensedDate = dispensedDate;

        if (_ivldActualMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldActualMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setDispensedDate", String.class);

                method.invoke(_ivldActualMasterRemoteModel, dispensedDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getItemId() {
        return _itemId;
    }

    @Override
    public void setItemId(String itemId) {
        _itemId = itemId;

        if (_ivldActualMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldActualMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setItemId", String.class);

                method.invoke(_ivldActualMasterRemoteModel, itemId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getAccrualProcessed() {
        return _accrualProcessed;
    }

    @Override
    public void setAccrualProcessed(String accrualProcessed) {
        _accrualProcessed = accrualProcessed;

        if (_ivldActualMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldActualMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setAccrualProcessed",
                        String.class);

                method.invoke(_ivldActualMasterRemoteModel, accrualProcessed);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getUploadDate() {
        return _uploadDate;
    }

    @Override
    public void setUploadDate(String uploadDate) {
        _uploadDate = uploadDate;

        if (_ivldActualMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldActualMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setUploadDate", String.class);

                method.invoke(_ivldActualMasterRemoteModel, uploadDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getCreatedBy() {
        return _createdBy;
    }

    @Override
    public void setCreatedBy(String createdBy) {
        _createdBy = createdBy;

        if (_ivldActualMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldActualMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedBy", String.class);

                method.invoke(_ivldActualMasterRemoteModel, createdBy);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getCreatedDate() {
        return _createdDate;
    }

    @Override
    public void setCreatedDate(Date createdDate) {
        _createdDate = createdDate;

        if (_ivldActualMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldActualMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedDate", Date.class);

                method.invoke(_ivldActualMasterRemoteModel, createdDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getInvoiceLineNo() {
        return _invoiceLineNo;
    }

    @Override
    public void setInvoiceLineNo(String invoiceLineNo) {
        _invoiceLineNo = invoiceLineNo;

        if (_ivldActualMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldActualMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setInvoiceLineNo", String.class);

                method.invoke(_ivldActualMasterRemoteModel, invoiceLineNo);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getErrorCode() {
        return _errorCode;
    }

    @Override
    public void setErrorCode(String errorCode) {
        _errorCode = errorCode;

        if (_ivldActualMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldActualMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setErrorCode", String.class);

                method.invoke(_ivldActualMasterRemoteModel, errorCode);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getIntfInsertedDate() {
        return _intfInsertedDate;
    }

    @Override
    public void setIntfInsertedDate(Date intfInsertedDate) {
        _intfInsertedDate = intfInsertedDate;

        if (_ivldActualMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldActualMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setIntfInsertedDate",
                        Date.class);

                method.invoke(_ivldActualMasterRemoteModel, intfInsertedDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getItemNo() {
        return _itemNo;
    }

    @Override
    public void setItemNo(String itemNo) {
        _itemNo = itemNo;

        if (_ivldActualMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldActualMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setItemNo", String.class);

                method.invoke(_ivldActualMasterRemoteModel, itemNo);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getReprocessedFlag() {
        return _reprocessedFlag;
    }

    @Override
    public void setReprocessedFlag(String reprocessedFlag) {
        _reprocessedFlag = reprocessedFlag;

        if (_ivldActualMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldActualMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setReprocessedFlag",
                        String.class);

                method.invoke(_ivldActualMasterRemoteModel, reprocessedFlag);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getAcctIdentifierCodeQualifier() {
        return _acctIdentifierCodeQualifier;
    }

    @Override
    public void setAcctIdentifierCodeQualifier(
        String acctIdentifierCodeQualifier) {
        _acctIdentifierCodeQualifier = acctIdentifierCodeQualifier;

        if (_ivldActualMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldActualMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setAcctIdentifierCodeQualifier",
                        String.class);

                method.invoke(_ivldActualMasterRemoteModel,
                    acctIdentifierCodeQualifier);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getContractId() {
        return _contractId;
    }

    @Override
    public void setContractId(String contractId) {
        _contractId = contractId;

        if (_ivldActualMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldActualMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setContractId", String.class);

                method.invoke(_ivldActualMasterRemoteModel, contractId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getBrandId() {
        return _brandId;
    }

    @Override
    public void setBrandId(String brandId) {
        _brandId = brandId;

        if (_ivldActualMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldActualMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setBrandId", String.class);

                method.invoke(_ivldActualMasterRemoteModel, brandId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getComDivMktBrandProdKey() {
        return _comDivMktBrandProdKey;
    }

    @Override
    public void setComDivMktBrandProdKey(String comDivMktBrandProdKey) {
        _comDivMktBrandProdKey = comDivMktBrandProdKey;

        if (_ivldActualMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldActualMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setComDivMktBrandProdKey",
                        String.class);

                method.invoke(_ivldActualMasterRemoteModel,
                    comDivMktBrandProdKey);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getClaimIndicator() {
        return _claimIndicator;
    }

    @Override
    public void setClaimIndicator(String claimIndicator) {
        _claimIndicator = claimIndicator;

        if (_ivldActualMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldActualMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setClaimIndicator",
                        String.class);

                method.invoke(_ivldActualMasterRemoteModel, claimIndicator);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getIvldActualMasterSid() {
        return _ivldActualMasterSid;
    }

    @Override
    public void setIvldActualMasterSid(int ivldActualMasterSid) {
        _ivldActualMasterSid = ivldActualMasterSid;

        if (_ivldActualMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldActualMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setIvldActualMasterSid",
                        int.class);

                method.invoke(_ivldActualMasterRemoteModel, ivldActualMasterSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getBatchId() {
        return _batchId;
    }

    @Override
    public void setBatchId(String batchId) {
        _batchId = batchId;

        if (_ivldActualMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldActualMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setBatchId", String.class);

                method.invoke(_ivldActualMasterRemoteModel, batchId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getProvisionClaimIndicator() {
        return _provisionClaimIndicator;
    }

    @Override
    public void setProvisionClaimIndicator(String provisionClaimIndicator) {
        _provisionClaimIndicator = provisionClaimIndicator;

        if (_ivldActualMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldActualMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setProvisionClaimIndicator",
                        String.class);

                method.invoke(_ivldActualMasterRemoteModel,
                    provisionClaimIndicator);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public boolean getCheckRecord() {
        return _checkRecord;
    }

    @Override
    public boolean isCheckRecord() {
        return _checkRecord;
    }

    @Override
    public void setCheckRecord(boolean checkRecord) {
        _checkRecord = checkRecord;

        if (_ivldActualMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldActualMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCheckRecord", boolean.class);

                method.invoke(_ivldActualMasterRemoteModel, checkRecord);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getIvldActualMasterRemoteModel() {
        return _ivldActualMasterRemoteModel;
    }

    public void setIvldActualMasterRemoteModel(
        BaseModel<?> ivldActualMasterRemoteModel) {
        _ivldActualMasterRemoteModel = ivldActualMasterRemoteModel;
    }

    public Object invokeOnRemoteModel(String methodName,
        Class<?>[] parameterTypes, Object[] parameterValues)
        throws Exception {
        Object[] remoteParameterValues = new Object[parameterValues.length];

        for (int i = 0; i < parameterValues.length; i++) {
            if (parameterValues[i] != null) {
                remoteParameterValues[i] = ClpSerializer.translateInput(parameterValues[i]);
            }
        }

        Class<?> remoteModelClass = _ivldActualMasterRemoteModel.getClass();

        ClassLoader remoteModelClassLoader = remoteModelClass.getClassLoader();

        Class<?>[] remoteParameterTypes = new Class[parameterTypes.length];

        for (int i = 0; i < parameterTypes.length; i++) {
            if (parameterTypes[i].isPrimitive()) {
                remoteParameterTypes[i] = parameterTypes[i];
            } else {
                String parameterTypeName = parameterTypes[i].getName();

                remoteParameterTypes[i] = remoteModelClassLoader.loadClass(parameterTypeName);
            }
        }

        Method method = remoteModelClass.getMethod(methodName,
                remoteParameterTypes);

        Object returnValue = method.invoke(_ivldActualMasterRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            IvldActualMasterLocalServiceUtil.addIvldActualMaster(this);
        } else {
            IvldActualMasterLocalServiceUtil.updateIvldActualMaster(this);
        }
    }

    @Override
    public IvldActualMaster toEscapedModel() {
        return (IvldActualMaster) ProxyUtil.newProxyInstance(IvldActualMaster.class.getClassLoader(),
            new Class[] { IvldActualMaster.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        IvldActualMasterClp clone = new IvldActualMasterClp();

        clone.setActualIntfid(getActualIntfid());
        clone.setAccountId(getAccountId());
        clone.setProgramStateCode(getProgramStateCode());
        clone.setSettlementNo(getSettlementNo());
        clone.setAccrualActualEndDate(getAccrualActualEndDate());
        clone.setActualId(getActualId());
        clone.setModifiedDate(getModifiedDate());
        clone.setDivisionId(getDivisionId());
        clone.setOrganizationKey(getOrganizationKey());
        clone.setQuantityInclusion(getQuantityInclusion());
        clone.setCashPaidDate(getCashPaidDate());
        clone.setSource(getSource());
        clone.setAddChgDelIndicator(getAddChgDelIndicator());
        clone.setAnalysisCode(getAnalysisCode());
        clone.setAccrualActualStartDate(getAccrualActualStartDate());
        clone.setModifiedBy(getModifiedBy());
        clone.setSalesAmount(getSalesAmount());
        clone.setQuantity(getQuantity());
        clone.setSentOut(getSentOut());
        clone.setAccountNo(getAccountNo());
        clone.setReasonForFailure(getReasonForFailure());
        clone.setAccountName(getAccountName());
        clone.setProvisionId(getProvisionId());
        clone.setAmount(getAmount());
        clone.setMarketId(getMarketId());
        clone.setIsActive(getIsActive());
        clone.setSettlementMethodNo(getSettlementMethodNo());
        clone.setItemIdentifierCodeQualifier(getItemIdentifierCodeQualifier());
        clone.setPriceAdjustmentName(getPriceAdjustmentName());
        clone.setErrorField(getErrorField());
        clone.setRecordSequence(getRecordSequence());
        clone.setMandatedDiscountAmount(getMandatedDiscountAmount());
        clone.setParentcomDivmktBrandProdkey(getParentcomDivmktBrandProdkey());
        clone.setPrice(getPrice());
        clone.setDispensedDate(getDispensedDate());
        clone.setItemId(getItemId());
        clone.setAccrualProcessed(getAccrualProcessed());
        clone.setUploadDate(getUploadDate());
        clone.setCreatedBy(getCreatedBy());
        clone.setCreatedDate(getCreatedDate());
        clone.setInvoiceLineNo(getInvoiceLineNo());
        clone.setErrorCode(getErrorCode());
        clone.setIntfInsertedDate(getIntfInsertedDate());
        clone.setItemNo(getItemNo());
        clone.setReprocessedFlag(getReprocessedFlag());
        clone.setAcctIdentifierCodeQualifier(getAcctIdentifierCodeQualifier());
        clone.setContractId(getContractId());
        clone.setBrandId(getBrandId());
        clone.setComDivMktBrandProdKey(getComDivMktBrandProdKey());
        clone.setClaimIndicator(getClaimIndicator());
        clone.setIvldActualMasterSid(getIvldActualMasterSid());
        clone.setBatchId(getBatchId());
        clone.setProvisionClaimIndicator(getProvisionClaimIndicator());
        clone.setCheckRecord(getCheckRecord());

        return clone;
    }

    @Override
    public int compareTo(IvldActualMaster ivldActualMaster) {
        int primaryKey = ivldActualMaster.getPrimaryKey();

        if (getPrimaryKey() < primaryKey) {
            return -1;
        } else if (getPrimaryKey() > primaryKey) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof IvldActualMasterClp)) {
            return false;
        }

        IvldActualMasterClp ivldActualMaster = (IvldActualMasterClp) obj;

        int primaryKey = ivldActualMaster.getPrimaryKey();

        if (getPrimaryKey() == primaryKey) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return getPrimaryKey();
    }

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(109);

        sb.append("{actualIntfid=");
        sb.append(getActualIntfid());
        sb.append(", accountId=");
        sb.append(getAccountId());
        sb.append(", programStateCode=");
        sb.append(getProgramStateCode());
        sb.append(", settlementNo=");
        sb.append(getSettlementNo());
        sb.append(", accrualActualEndDate=");
        sb.append(getAccrualActualEndDate());
        sb.append(", actualId=");
        sb.append(getActualId());
        sb.append(", modifiedDate=");
        sb.append(getModifiedDate());
        sb.append(", divisionId=");
        sb.append(getDivisionId());
        sb.append(", organizationKey=");
        sb.append(getOrganizationKey());
        sb.append(", quantityInclusion=");
        sb.append(getQuantityInclusion());
        sb.append(", cashPaidDate=");
        sb.append(getCashPaidDate());
        sb.append(", source=");
        sb.append(getSource());
        sb.append(", addChgDelIndicator=");
        sb.append(getAddChgDelIndicator());
        sb.append(", analysisCode=");
        sb.append(getAnalysisCode());
        sb.append(", accrualActualStartDate=");
        sb.append(getAccrualActualStartDate());
        sb.append(", modifiedBy=");
        sb.append(getModifiedBy());
        sb.append(", salesAmount=");
        sb.append(getSalesAmount());
        sb.append(", quantity=");
        sb.append(getQuantity());
        sb.append(", sentOut=");
        sb.append(getSentOut());
        sb.append(", accountNo=");
        sb.append(getAccountNo());
        sb.append(", reasonForFailure=");
        sb.append(getReasonForFailure());
        sb.append(", accountName=");
        sb.append(getAccountName());
        sb.append(", provisionId=");
        sb.append(getProvisionId());
        sb.append(", amount=");
        sb.append(getAmount());
        sb.append(", marketId=");
        sb.append(getMarketId());
        sb.append(", isActive=");
        sb.append(getIsActive());
        sb.append(", settlementMethodNo=");
        sb.append(getSettlementMethodNo());
        sb.append(", itemIdentifierCodeQualifier=");
        sb.append(getItemIdentifierCodeQualifier());
        sb.append(", priceAdjustmentName=");
        sb.append(getPriceAdjustmentName());
        sb.append(", errorField=");
        sb.append(getErrorField());
        sb.append(", recordSequence=");
        sb.append(getRecordSequence());
        sb.append(", mandatedDiscountAmount=");
        sb.append(getMandatedDiscountAmount());
        sb.append(", parentcomDivmktBrandProdkey=");
        sb.append(getParentcomDivmktBrandProdkey());
        sb.append(", price=");
        sb.append(getPrice());
        sb.append(", dispensedDate=");
        sb.append(getDispensedDate());
        sb.append(", itemId=");
        sb.append(getItemId());
        sb.append(", accrualProcessed=");
        sb.append(getAccrualProcessed());
        sb.append(", uploadDate=");
        sb.append(getUploadDate());
        sb.append(", createdBy=");
        sb.append(getCreatedBy());
        sb.append(", createdDate=");
        sb.append(getCreatedDate());
        sb.append(", invoiceLineNo=");
        sb.append(getInvoiceLineNo());
        sb.append(", errorCode=");
        sb.append(getErrorCode());
        sb.append(", intfInsertedDate=");
        sb.append(getIntfInsertedDate());
        sb.append(", itemNo=");
        sb.append(getItemNo());
        sb.append(", reprocessedFlag=");
        sb.append(getReprocessedFlag());
        sb.append(", acctIdentifierCodeQualifier=");
        sb.append(getAcctIdentifierCodeQualifier());
        sb.append(", contractId=");
        sb.append(getContractId());
        sb.append(", brandId=");
        sb.append(getBrandId());
        sb.append(", comDivMktBrandProdKey=");
        sb.append(getComDivMktBrandProdKey());
        sb.append(", claimIndicator=");
        sb.append(getClaimIndicator());
        sb.append(", ivldActualMasterSid=");
        sb.append(getIvldActualMasterSid());
        sb.append(", batchId=");
        sb.append(getBatchId());
        sb.append(", provisionClaimIndicator=");
        sb.append(getProvisionClaimIndicator());
        sb.append(", checkRecord=");
        sb.append(getCheckRecord());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(166);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.IvldActualMaster");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>actualIntfid</column-name><column-value><![CDATA[");
        sb.append(getActualIntfid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>accountId</column-name><column-value><![CDATA[");
        sb.append(getAccountId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>programStateCode</column-name><column-value><![CDATA[");
        sb.append(getProgramStateCode());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>settlementNo</column-name><column-value><![CDATA[");
        sb.append(getSettlementNo());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>accrualActualEndDate</column-name><column-value><![CDATA[");
        sb.append(getAccrualActualEndDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>actualId</column-name><column-value><![CDATA[");
        sb.append(getActualId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
        sb.append(getModifiedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>divisionId</column-name><column-value><![CDATA[");
        sb.append(getDivisionId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>organizationKey</column-name><column-value><![CDATA[");
        sb.append(getOrganizationKey());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>quantityInclusion</column-name><column-value><![CDATA[");
        sb.append(getQuantityInclusion());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>cashPaidDate</column-name><column-value><![CDATA[");
        sb.append(getCashPaidDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>source</column-name><column-value><![CDATA[");
        sb.append(getSource());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>addChgDelIndicator</column-name><column-value><![CDATA[");
        sb.append(getAddChgDelIndicator());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>analysisCode</column-name><column-value><![CDATA[");
        sb.append(getAnalysisCode());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>accrualActualStartDate</column-name><column-value><![CDATA[");
        sb.append(getAccrualActualStartDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedBy</column-name><column-value><![CDATA[");
        sb.append(getModifiedBy());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>salesAmount</column-name><column-value><![CDATA[");
        sb.append(getSalesAmount());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>quantity</column-name><column-value><![CDATA[");
        sb.append(getQuantity());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>sentOut</column-name><column-value><![CDATA[");
        sb.append(getSentOut());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>accountNo</column-name><column-value><![CDATA[");
        sb.append(getAccountNo());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>reasonForFailure</column-name><column-value><![CDATA[");
        sb.append(getReasonForFailure());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>accountName</column-name><column-value><![CDATA[");
        sb.append(getAccountName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>provisionId</column-name><column-value><![CDATA[");
        sb.append(getProvisionId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>amount</column-name><column-value><![CDATA[");
        sb.append(getAmount());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>marketId</column-name><column-value><![CDATA[");
        sb.append(getMarketId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>isActive</column-name><column-value><![CDATA[");
        sb.append(getIsActive());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>settlementMethodNo</column-name><column-value><![CDATA[");
        sb.append(getSettlementMethodNo());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemIdentifierCodeQualifier</column-name><column-value><![CDATA[");
        sb.append(getItemIdentifierCodeQualifier());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>priceAdjustmentName</column-name><column-value><![CDATA[");
        sb.append(getPriceAdjustmentName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>errorField</column-name><column-value><![CDATA[");
        sb.append(getErrorField());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>recordSequence</column-name><column-value><![CDATA[");
        sb.append(getRecordSequence());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>mandatedDiscountAmount</column-name><column-value><![CDATA[");
        sb.append(getMandatedDiscountAmount());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>parentcomDivmktBrandProdkey</column-name><column-value><![CDATA[");
        sb.append(getParentcomDivmktBrandProdkey());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>price</column-name><column-value><![CDATA[");
        sb.append(getPrice());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>dispensedDate</column-name><column-value><![CDATA[");
        sb.append(getDispensedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemId</column-name><column-value><![CDATA[");
        sb.append(getItemId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>accrualProcessed</column-name><column-value><![CDATA[");
        sb.append(getAccrualProcessed());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>uploadDate</column-name><column-value><![CDATA[");
        sb.append(getUploadDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>createdBy</column-name><column-value><![CDATA[");
        sb.append(getCreatedBy());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>createdDate</column-name><column-value><![CDATA[");
        sb.append(getCreatedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>invoiceLineNo</column-name><column-value><![CDATA[");
        sb.append(getInvoiceLineNo());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>errorCode</column-name><column-value><![CDATA[");
        sb.append(getErrorCode());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>intfInsertedDate</column-name><column-value><![CDATA[");
        sb.append(getIntfInsertedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemNo</column-name><column-value><![CDATA[");
        sb.append(getItemNo());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>reprocessedFlag</column-name><column-value><![CDATA[");
        sb.append(getReprocessedFlag());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>acctIdentifierCodeQualifier</column-name><column-value><![CDATA[");
        sb.append(getAcctIdentifierCodeQualifier());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>contractId</column-name><column-value><![CDATA[");
        sb.append(getContractId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>brandId</column-name><column-value><![CDATA[");
        sb.append(getBrandId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>comDivMktBrandProdKey</column-name><column-value><![CDATA[");
        sb.append(getComDivMktBrandProdKey());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>claimIndicator</column-name><column-value><![CDATA[");
        sb.append(getClaimIndicator());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>ivldActualMasterSid</column-name><column-value><![CDATA[");
        sb.append(getIvldActualMasterSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>batchId</column-name><column-value><![CDATA[");
        sb.append(getBatchId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>provisionClaimIndicator</column-name><column-value><![CDATA[");
        sb.append(getProvisionClaimIndicator());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>checkRecord</column-name><column-value><![CDATA[");
        sb.append(getCheckRecord());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
