package com.stpl.app.model;

import com.stpl.app.service.ActualsMasterLocalServiceUtil;
import com.stpl.app.service.ClpSerializer;

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


public class ActualsMasterClp extends BaseModelImpl<ActualsMaster>
    implements ActualsMaster {
    private String _quantityInclusion;
    private double _mandatedDiscountAmount;
    private String _itemNo;
    private String _analysisCode;
    private String _recordSequence;
    private int _modifiedBy;
    private String _settlementMethodNo;
    private String _quantity;
    private String _accountId;
    private Date _createdDate;
    private String _provisionClaimIndicator;
    private Date _dispensedDate;
    private String _isActive;
    private String _batchId;
    private Date _accrualActualEndDate;
    private String _marketId;
    private String _brandId;
    private String _accountName;
    private String _amount;
    private int _actualsMasterSid;
    private String _acctIdentifierCodeQualifier;
    private String _organizationKey;
    private int _createdBy;
    private String _accrualProcessed;
    private String _parentcomDivmktBrandProdkey;
    private Date _cashPaidDate;
    private String _salesAmount;
    private Date _accrualActualStartDate;
    private String _settlementNo;
    private double _price;
    private Date _uploadDate;
    private String _claimIndicator;
    private String _itemId;
    private String _priceAdjustmentName;
    private String _contractId;
    private Date _modifiedDate;
    private String _actualId;
    private String _provisionId;
    private String _sentOut;
    private boolean _recordLockStatus;
    private String _divisionId;
    private String _itemIdentifierCodeQualifier;
    private String _programStateCode;
    private String _source;
    private String _invoiceLineNo;
    private String _accountNo;
    private String _comDivMktBrandProdKey;
    private String _inboundStatus;
    private BaseModel<?> _actualsMasterRemoteModel;

    public ActualsMasterClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return ActualsMaster.class;
    }

    @Override
    public String getModelClassName() {
        return ActualsMaster.class.getName();
    }

    @Override
    public int getPrimaryKey() {
        return _actualsMasterSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setActualsMasterSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _actualsMasterSid;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Integer) primaryKeyObj).intValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("quantityInclusion", getQuantityInclusion());
        attributes.put("mandatedDiscountAmount", getMandatedDiscountAmount());
        attributes.put("itemNo", getItemNo());
        attributes.put("analysisCode", getAnalysisCode());
        attributes.put("recordSequence", getRecordSequence());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("settlementMethodNo", getSettlementMethodNo());
        attributes.put("quantity", getQuantity());
        attributes.put("accountId", getAccountId());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("provisionClaimIndicator", getProvisionClaimIndicator());
        attributes.put("dispensedDate", getDispensedDate());
        attributes.put("isActive", getIsActive());
        attributes.put("batchId", getBatchId());
        attributes.put("accrualActualEndDate", getAccrualActualEndDate());
        attributes.put("marketId", getMarketId());
        attributes.put("brandId", getBrandId());
        attributes.put("accountName", getAccountName());
        attributes.put("amount", getAmount());
        attributes.put("actualsMasterSid", getActualsMasterSid());
        attributes.put("acctIdentifierCodeQualifier",
            getAcctIdentifierCodeQualifier());
        attributes.put("organizationKey", getOrganizationKey());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("accrualProcessed", getAccrualProcessed());
        attributes.put("parentcomDivmktBrandProdkey",
            getParentcomDivmktBrandProdkey());
        attributes.put("cashPaidDate", getCashPaidDate());
        attributes.put("salesAmount", getSalesAmount());
        attributes.put("accrualActualStartDate", getAccrualActualStartDate());
        attributes.put("settlementNo", getSettlementNo());
        attributes.put("price", getPrice());
        attributes.put("uploadDate", getUploadDate());
        attributes.put("claimIndicator", getClaimIndicator());
        attributes.put("itemId", getItemId());
        attributes.put("priceAdjustmentName", getPriceAdjustmentName());
        attributes.put("contractId", getContractId());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("actualId", getActualId());
        attributes.put("provisionId", getProvisionId());
        attributes.put("sentOut", getSentOut());
        attributes.put("recordLockStatus", getRecordLockStatus());
        attributes.put("divisionId", getDivisionId());
        attributes.put("itemIdentifierCodeQualifier",
            getItemIdentifierCodeQualifier());
        attributes.put("programStateCode", getProgramStateCode());
        attributes.put("source", getSource());
        attributes.put("invoiceLineNo", getInvoiceLineNo());
        attributes.put("accountNo", getAccountNo());
        attributes.put("comDivMktBrandProdKey", getComDivMktBrandProdKey());
        attributes.put("inboundStatus", getInboundStatus());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        String quantityInclusion = (String) attributes.get("quantityInclusion");

        if (quantityInclusion != null) {
            setQuantityInclusion(quantityInclusion);
        }

        Double mandatedDiscountAmount = (Double) attributes.get(
                "mandatedDiscountAmount");

        if (mandatedDiscountAmount != null) {
            setMandatedDiscountAmount(mandatedDiscountAmount);
        }

        String itemNo = (String) attributes.get("itemNo");

        if (itemNo != null) {
            setItemNo(itemNo);
        }

        String analysisCode = (String) attributes.get("analysisCode");

        if (analysisCode != null) {
            setAnalysisCode(analysisCode);
        }

        String recordSequence = (String) attributes.get("recordSequence");

        if (recordSequence != null) {
            setRecordSequence(recordSequence);
        }

        Integer modifiedBy = (Integer) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        String settlementMethodNo = (String) attributes.get(
                "settlementMethodNo");

        if (settlementMethodNo != null) {
            setSettlementMethodNo(settlementMethodNo);
        }

        String quantity = (String) attributes.get("quantity");

        if (quantity != null) {
            setQuantity(quantity);
        }

        String accountId = (String) attributes.get("accountId");

        if (accountId != null) {
            setAccountId(accountId);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        String provisionClaimIndicator = (String) attributes.get(
                "provisionClaimIndicator");

        if (provisionClaimIndicator != null) {
            setProvisionClaimIndicator(provisionClaimIndicator);
        }

        Date dispensedDate = (Date) attributes.get("dispensedDate");

        if (dispensedDate != null) {
            setDispensedDate(dispensedDate);
        }

        String isActive = (String) attributes.get("isActive");

        if (isActive != null) {
            setIsActive(isActive);
        }

        String batchId = (String) attributes.get("batchId");

        if (batchId != null) {
            setBatchId(batchId);
        }

        Date accrualActualEndDate = (Date) attributes.get(
                "accrualActualEndDate");

        if (accrualActualEndDate != null) {
            setAccrualActualEndDate(accrualActualEndDate);
        }

        String marketId = (String) attributes.get("marketId");

        if (marketId != null) {
            setMarketId(marketId);
        }

        String brandId = (String) attributes.get("brandId");

        if (brandId != null) {
            setBrandId(brandId);
        }

        String accountName = (String) attributes.get("accountName");

        if (accountName != null) {
            setAccountName(accountName);
        }

        String amount = (String) attributes.get("amount");

        if (amount != null) {
            setAmount(amount);
        }

        Integer actualsMasterSid = (Integer) attributes.get("actualsMasterSid");

        if (actualsMasterSid != null) {
            setActualsMasterSid(actualsMasterSid);
        }

        String acctIdentifierCodeQualifier = (String) attributes.get(
                "acctIdentifierCodeQualifier");

        if (acctIdentifierCodeQualifier != null) {
            setAcctIdentifierCodeQualifier(acctIdentifierCodeQualifier);
        }

        String organizationKey = (String) attributes.get("organizationKey");

        if (organizationKey != null) {
            setOrganizationKey(organizationKey);
        }

        Integer createdBy = (Integer) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        String accrualProcessed = (String) attributes.get("accrualProcessed");

        if (accrualProcessed != null) {
            setAccrualProcessed(accrualProcessed);
        }

        String parentcomDivmktBrandProdkey = (String) attributes.get(
                "parentcomDivmktBrandProdkey");

        if (parentcomDivmktBrandProdkey != null) {
            setParentcomDivmktBrandProdkey(parentcomDivmktBrandProdkey);
        }

        Date cashPaidDate = (Date) attributes.get("cashPaidDate");

        if (cashPaidDate != null) {
            setCashPaidDate(cashPaidDate);
        }

        String salesAmount = (String) attributes.get("salesAmount");

        if (salesAmount != null) {
            setSalesAmount(salesAmount);
        }

        Date accrualActualStartDate = (Date) attributes.get(
                "accrualActualStartDate");

        if (accrualActualStartDate != null) {
            setAccrualActualStartDate(accrualActualStartDate);
        }

        String settlementNo = (String) attributes.get("settlementNo");

        if (settlementNo != null) {
            setSettlementNo(settlementNo);
        }

        Double price = (Double) attributes.get("price");

        if (price != null) {
            setPrice(price);
        }

        Date uploadDate = (Date) attributes.get("uploadDate");

        if (uploadDate != null) {
            setUploadDate(uploadDate);
        }

        String claimIndicator = (String) attributes.get("claimIndicator");

        if (claimIndicator != null) {
            setClaimIndicator(claimIndicator);
        }

        String itemId = (String) attributes.get("itemId");

        if (itemId != null) {
            setItemId(itemId);
        }

        String priceAdjustmentName = (String) attributes.get(
                "priceAdjustmentName");

        if (priceAdjustmentName != null) {
            setPriceAdjustmentName(priceAdjustmentName);
        }

        String contractId = (String) attributes.get("contractId");

        if (contractId != null) {
            setContractId(contractId);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        String actualId = (String) attributes.get("actualId");

        if (actualId != null) {
            setActualId(actualId);
        }

        String provisionId = (String) attributes.get("provisionId");

        if (provisionId != null) {
            setProvisionId(provisionId);
        }

        String sentOut = (String) attributes.get("sentOut");

        if (sentOut != null) {
            setSentOut(sentOut);
        }

        Boolean recordLockStatus = (Boolean) attributes.get("recordLockStatus");

        if (recordLockStatus != null) {
            setRecordLockStatus(recordLockStatus);
        }

        String divisionId = (String) attributes.get("divisionId");

        if (divisionId != null) {
            setDivisionId(divisionId);
        }

        String itemIdentifierCodeQualifier = (String) attributes.get(
                "itemIdentifierCodeQualifier");

        if (itemIdentifierCodeQualifier != null) {
            setItemIdentifierCodeQualifier(itemIdentifierCodeQualifier);
        }

        String programStateCode = (String) attributes.get("programStateCode");

        if (programStateCode != null) {
            setProgramStateCode(programStateCode);
        }

        String source = (String) attributes.get("source");

        if (source != null) {
            setSource(source);
        }

        String invoiceLineNo = (String) attributes.get("invoiceLineNo");

        if (invoiceLineNo != null) {
            setInvoiceLineNo(invoiceLineNo);
        }

        String accountNo = (String) attributes.get("accountNo");

        if (accountNo != null) {
            setAccountNo(accountNo);
        }

        String comDivMktBrandProdKey = (String) attributes.get(
                "comDivMktBrandProdKey");

        if (comDivMktBrandProdKey != null) {
            setComDivMktBrandProdKey(comDivMktBrandProdKey);
        }

        String inboundStatus = (String) attributes.get("inboundStatus");

        if (inboundStatus != null) {
            setInboundStatus(inboundStatus);
        }
    }

    @Override
    public String getQuantityInclusion() {
        return _quantityInclusion;
    }

    @Override
    public void setQuantityInclusion(String quantityInclusion) {
        _quantityInclusion = quantityInclusion;

        if (_actualsMasterRemoteModel != null) {
            try {
                Class<?> clazz = _actualsMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setQuantityInclusion",
                        String.class);

                method.invoke(_actualsMasterRemoteModel, quantityInclusion);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getMandatedDiscountAmount() {
        return _mandatedDiscountAmount;
    }

    @Override
    public void setMandatedDiscountAmount(double mandatedDiscountAmount) {
        _mandatedDiscountAmount = mandatedDiscountAmount;

        if (_actualsMasterRemoteModel != null) {
            try {
                Class<?> clazz = _actualsMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setMandatedDiscountAmount",
                        double.class);

                method.invoke(_actualsMasterRemoteModel, mandatedDiscountAmount);
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

        if (_actualsMasterRemoteModel != null) {
            try {
                Class<?> clazz = _actualsMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setItemNo", String.class);

                method.invoke(_actualsMasterRemoteModel, itemNo);
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

        if (_actualsMasterRemoteModel != null) {
            try {
                Class<?> clazz = _actualsMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setAnalysisCode", String.class);

                method.invoke(_actualsMasterRemoteModel, analysisCode);
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

        if (_actualsMasterRemoteModel != null) {
            try {
                Class<?> clazz = _actualsMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setRecordSequence",
                        String.class);

                method.invoke(_actualsMasterRemoteModel, recordSequence);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getModifiedBy() {
        return _modifiedBy;
    }

    @Override
    public void setModifiedBy(int modifiedBy) {
        _modifiedBy = modifiedBy;

        if (_actualsMasterRemoteModel != null) {
            try {
                Class<?> clazz = _actualsMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedBy", int.class);

                method.invoke(_actualsMasterRemoteModel, modifiedBy);
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

        if (_actualsMasterRemoteModel != null) {
            try {
                Class<?> clazz = _actualsMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setSettlementMethodNo",
                        String.class);

                method.invoke(_actualsMasterRemoteModel, settlementMethodNo);
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

        if (_actualsMasterRemoteModel != null) {
            try {
                Class<?> clazz = _actualsMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setQuantity", String.class);

                method.invoke(_actualsMasterRemoteModel, quantity);
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

        if (_actualsMasterRemoteModel != null) {
            try {
                Class<?> clazz = _actualsMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setAccountId", String.class);

                method.invoke(_actualsMasterRemoteModel, accountId);
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

        if (_actualsMasterRemoteModel != null) {
            try {
                Class<?> clazz = _actualsMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedDate", Date.class);

                method.invoke(_actualsMasterRemoteModel, createdDate);
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

        if (_actualsMasterRemoteModel != null) {
            try {
                Class<?> clazz = _actualsMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setProvisionClaimIndicator",
                        String.class);

                method.invoke(_actualsMasterRemoteModel, provisionClaimIndicator);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getDispensedDate() {
        return _dispensedDate;
    }

    @Override
    public void setDispensedDate(Date dispensedDate) {
        _dispensedDate = dispensedDate;

        if (_actualsMasterRemoteModel != null) {
            try {
                Class<?> clazz = _actualsMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setDispensedDate", Date.class);

                method.invoke(_actualsMasterRemoteModel, dispensedDate);
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

        if (_actualsMasterRemoteModel != null) {
            try {
                Class<?> clazz = _actualsMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setIsActive", String.class);

                method.invoke(_actualsMasterRemoteModel, isActive);
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

        if (_actualsMasterRemoteModel != null) {
            try {
                Class<?> clazz = _actualsMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setBatchId", String.class);

                method.invoke(_actualsMasterRemoteModel, batchId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getAccrualActualEndDate() {
        return _accrualActualEndDate;
    }

    @Override
    public void setAccrualActualEndDate(Date accrualActualEndDate) {
        _accrualActualEndDate = accrualActualEndDate;

        if (_actualsMasterRemoteModel != null) {
            try {
                Class<?> clazz = _actualsMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setAccrualActualEndDate",
                        Date.class);

                method.invoke(_actualsMasterRemoteModel, accrualActualEndDate);
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

        if (_actualsMasterRemoteModel != null) {
            try {
                Class<?> clazz = _actualsMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setMarketId", String.class);

                method.invoke(_actualsMasterRemoteModel, marketId);
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

        if (_actualsMasterRemoteModel != null) {
            try {
                Class<?> clazz = _actualsMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setBrandId", String.class);

                method.invoke(_actualsMasterRemoteModel, brandId);
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

        if (_actualsMasterRemoteModel != null) {
            try {
                Class<?> clazz = _actualsMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setAccountName", String.class);

                method.invoke(_actualsMasterRemoteModel, accountName);
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

        if (_actualsMasterRemoteModel != null) {
            try {
                Class<?> clazz = _actualsMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setAmount", String.class);

                method.invoke(_actualsMasterRemoteModel, amount);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getActualsMasterSid() {
        return _actualsMasterSid;
    }

    @Override
    public void setActualsMasterSid(int actualsMasterSid) {
        _actualsMasterSid = actualsMasterSid;

        if (_actualsMasterRemoteModel != null) {
            try {
                Class<?> clazz = _actualsMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setActualsMasterSid", int.class);

                method.invoke(_actualsMasterRemoteModel, actualsMasterSid);
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

        if (_actualsMasterRemoteModel != null) {
            try {
                Class<?> clazz = _actualsMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setAcctIdentifierCodeQualifier",
                        String.class);

                method.invoke(_actualsMasterRemoteModel,
                    acctIdentifierCodeQualifier);
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

        if (_actualsMasterRemoteModel != null) {
            try {
                Class<?> clazz = _actualsMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setOrganizationKey",
                        String.class);

                method.invoke(_actualsMasterRemoteModel, organizationKey);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getCreatedBy() {
        return _createdBy;
    }

    @Override
    public void setCreatedBy(int createdBy) {
        _createdBy = createdBy;

        if (_actualsMasterRemoteModel != null) {
            try {
                Class<?> clazz = _actualsMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedBy", int.class);

                method.invoke(_actualsMasterRemoteModel, createdBy);
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

        if (_actualsMasterRemoteModel != null) {
            try {
                Class<?> clazz = _actualsMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setAccrualProcessed",
                        String.class);

                method.invoke(_actualsMasterRemoteModel, accrualProcessed);
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

        if (_actualsMasterRemoteModel != null) {
            try {
                Class<?> clazz = _actualsMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setParentcomDivmktBrandProdkey",
                        String.class);

                method.invoke(_actualsMasterRemoteModel,
                    parentcomDivmktBrandProdkey);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getCashPaidDate() {
        return _cashPaidDate;
    }

    @Override
    public void setCashPaidDate(Date cashPaidDate) {
        _cashPaidDate = cashPaidDate;

        if (_actualsMasterRemoteModel != null) {
            try {
                Class<?> clazz = _actualsMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCashPaidDate", Date.class);

                method.invoke(_actualsMasterRemoteModel, cashPaidDate);
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

        if (_actualsMasterRemoteModel != null) {
            try {
                Class<?> clazz = _actualsMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setSalesAmount", String.class);

                method.invoke(_actualsMasterRemoteModel, salesAmount);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getAccrualActualStartDate() {
        return _accrualActualStartDate;
    }

    @Override
    public void setAccrualActualStartDate(Date accrualActualStartDate) {
        _accrualActualStartDate = accrualActualStartDate;

        if (_actualsMasterRemoteModel != null) {
            try {
                Class<?> clazz = _actualsMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setAccrualActualStartDate",
                        Date.class);

                method.invoke(_actualsMasterRemoteModel, accrualActualStartDate);
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

        if (_actualsMasterRemoteModel != null) {
            try {
                Class<?> clazz = _actualsMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setSettlementNo", String.class);

                method.invoke(_actualsMasterRemoteModel, settlementNo);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getPrice() {
        return _price;
    }

    @Override
    public void setPrice(double price) {
        _price = price;

        if (_actualsMasterRemoteModel != null) {
            try {
                Class<?> clazz = _actualsMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setPrice", double.class);

                method.invoke(_actualsMasterRemoteModel, price);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getUploadDate() {
        return _uploadDate;
    }

    @Override
    public void setUploadDate(Date uploadDate) {
        _uploadDate = uploadDate;

        if (_actualsMasterRemoteModel != null) {
            try {
                Class<?> clazz = _actualsMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setUploadDate", Date.class);

                method.invoke(_actualsMasterRemoteModel, uploadDate);
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

        if (_actualsMasterRemoteModel != null) {
            try {
                Class<?> clazz = _actualsMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setClaimIndicator",
                        String.class);

                method.invoke(_actualsMasterRemoteModel, claimIndicator);
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

        if (_actualsMasterRemoteModel != null) {
            try {
                Class<?> clazz = _actualsMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setItemId", String.class);

                method.invoke(_actualsMasterRemoteModel, itemId);
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

        if (_actualsMasterRemoteModel != null) {
            try {
                Class<?> clazz = _actualsMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setPriceAdjustmentName",
                        String.class);

                method.invoke(_actualsMasterRemoteModel, priceAdjustmentName);
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

        if (_actualsMasterRemoteModel != null) {
            try {
                Class<?> clazz = _actualsMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setContractId", String.class);

                method.invoke(_actualsMasterRemoteModel, contractId);
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

        if (_actualsMasterRemoteModel != null) {
            try {
                Class<?> clazz = _actualsMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedDate", Date.class);

                method.invoke(_actualsMasterRemoteModel, modifiedDate);
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

        if (_actualsMasterRemoteModel != null) {
            try {
                Class<?> clazz = _actualsMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setActualId", String.class);

                method.invoke(_actualsMasterRemoteModel, actualId);
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

        if (_actualsMasterRemoteModel != null) {
            try {
                Class<?> clazz = _actualsMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setProvisionId", String.class);

                method.invoke(_actualsMasterRemoteModel, provisionId);
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

        if (_actualsMasterRemoteModel != null) {
            try {
                Class<?> clazz = _actualsMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setSentOut", String.class);

                method.invoke(_actualsMasterRemoteModel, sentOut);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public boolean getRecordLockStatus() {
        return _recordLockStatus;
    }

    @Override
    public boolean isRecordLockStatus() {
        return _recordLockStatus;
    }

    @Override
    public void setRecordLockStatus(boolean recordLockStatus) {
        _recordLockStatus = recordLockStatus;

        if (_actualsMasterRemoteModel != null) {
            try {
                Class<?> clazz = _actualsMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setRecordLockStatus",
                        boolean.class);

                method.invoke(_actualsMasterRemoteModel, recordLockStatus);
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

        if (_actualsMasterRemoteModel != null) {
            try {
                Class<?> clazz = _actualsMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setDivisionId", String.class);

                method.invoke(_actualsMasterRemoteModel, divisionId);
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

        if (_actualsMasterRemoteModel != null) {
            try {
                Class<?> clazz = _actualsMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setItemIdentifierCodeQualifier",
                        String.class);

                method.invoke(_actualsMasterRemoteModel,
                    itemIdentifierCodeQualifier);
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

        if (_actualsMasterRemoteModel != null) {
            try {
                Class<?> clazz = _actualsMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setProgramStateCode",
                        String.class);

                method.invoke(_actualsMasterRemoteModel, programStateCode);
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

        if (_actualsMasterRemoteModel != null) {
            try {
                Class<?> clazz = _actualsMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setSource", String.class);

                method.invoke(_actualsMasterRemoteModel, source);
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

        if (_actualsMasterRemoteModel != null) {
            try {
                Class<?> clazz = _actualsMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setInvoiceLineNo", String.class);

                method.invoke(_actualsMasterRemoteModel, invoiceLineNo);
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

        if (_actualsMasterRemoteModel != null) {
            try {
                Class<?> clazz = _actualsMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setAccountNo", String.class);

                method.invoke(_actualsMasterRemoteModel, accountNo);
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

        if (_actualsMasterRemoteModel != null) {
            try {
                Class<?> clazz = _actualsMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setComDivMktBrandProdKey",
                        String.class);

                method.invoke(_actualsMasterRemoteModel, comDivMktBrandProdKey);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getInboundStatus() {
        return _inboundStatus;
    }

    @Override
    public void setInboundStatus(String inboundStatus) {
        _inboundStatus = inboundStatus;

        if (_actualsMasterRemoteModel != null) {
            try {
                Class<?> clazz = _actualsMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setInboundStatus", String.class);

                method.invoke(_actualsMasterRemoteModel, inboundStatus);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getActualsMasterRemoteModel() {
        return _actualsMasterRemoteModel;
    }

    public void setActualsMasterRemoteModel(
        BaseModel<?> actualsMasterRemoteModel) {
        _actualsMasterRemoteModel = actualsMasterRemoteModel;
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

        Class<?> remoteModelClass = _actualsMasterRemoteModel.getClass();

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

        Object returnValue = method.invoke(_actualsMasterRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            ActualsMasterLocalServiceUtil.addActualsMaster(this);
        } else {
            ActualsMasterLocalServiceUtil.updateActualsMaster(this);
        }
    }

    @Override
    public ActualsMaster toEscapedModel() {
        return (ActualsMaster) ProxyUtil.newProxyInstance(ActualsMaster.class.getClassLoader(),
            new Class[] { ActualsMaster.class }, new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        ActualsMasterClp clone = new ActualsMasterClp();

        clone.setQuantityInclusion(getQuantityInclusion());
        clone.setMandatedDiscountAmount(getMandatedDiscountAmount());
        clone.setItemNo(getItemNo());
        clone.setAnalysisCode(getAnalysisCode());
        clone.setRecordSequence(getRecordSequence());
        clone.setModifiedBy(getModifiedBy());
        clone.setSettlementMethodNo(getSettlementMethodNo());
        clone.setQuantity(getQuantity());
        clone.setAccountId(getAccountId());
        clone.setCreatedDate(getCreatedDate());
        clone.setProvisionClaimIndicator(getProvisionClaimIndicator());
        clone.setDispensedDate(getDispensedDate());
        clone.setIsActive(getIsActive());
        clone.setBatchId(getBatchId());
        clone.setAccrualActualEndDate(getAccrualActualEndDate());
        clone.setMarketId(getMarketId());
        clone.setBrandId(getBrandId());
        clone.setAccountName(getAccountName());
        clone.setAmount(getAmount());
        clone.setActualsMasterSid(getActualsMasterSid());
        clone.setAcctIdentifierCodeQualifier(getAcctIdentifierCodeQualifier());
        clone.setOrganizationKey(getOrganizationKey());
        clone.setCreatedBy(getCreatedBy());
        clone.setAccrualProcessed(getAccrualProcessed());
        clone.setParentcomDivmktBrandProdkey(getParentcomDivmktBrandProdkey());
        clone.setCashPaidDate(getCashPaidDate());
        clone.setSalesAmount(getSalesAmount());
        clone.setAccrualActualStartDate(getAccrualActualStartDate());
        clone.setSettlementNo(getSettlementNo());
        clone.setPrice(getPrice());
        clone.setUploadDate(getUploadDate());
        clone.setClaimIndicator(getClaimIndicator());
        clone.setItemId(getItemId());
        clone.setPriceAdjustmentName(getPriceAdjustmentName());
        clone.setContractId(getContractId());
        clone.setModifiedDate(getModifiedDate());
        clone.setActualId(getActualId());
        clone.setProvisionId(getProvisionId());
        clone.setSentOut(getSentOut());
        clone.setRecordLockStatus(getRecordLockStatus());
        clone.setDivisionId(getDivisionId());
        clone.setItemIdentifierCodeQualifier(getItemIdentifierCodeQualifier());
        clone.setProgramStateCode(getProgramStateCode());
        clone.setSource(getSource());
        clone.setInvoiceLineNo(getInvoiceLineNo());
        clone.setAccountNo(getAccountNo());
        clone.setComDivMktBrandProdKey(getComDivMktBrandProdKey());
        clone.setInboundStatus(getInboundStatus());

        return clone;
    }

    @Override
    public int compareTo(ActualsMaster actualsMaster) {
        int primaryKey = actualsMaster.getPrimaryKey();

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

        if (!(obj instanceof ActualsMasterClp)) {
            return false;
        }

        ActualsMasterClp actualsMaster = (ActualsMasterClp) obj;

        int primaryKey = actualsMaster.getPrimaryKey();

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
        StringBundler sb = new StringBundler(97);

        sb.append("{quantityInclusion=");
        sb.append(getQuantityInclusion());
        sb.append(", mandatedDiscountAmount=");
        sb.append(getMandatedDiscountAmount());
        sb.append(", itemNo=");
        sb.append(getItemNo());
        sb.append(", analysisCode=");
        sb.append(getAnalysisCode());
        sb.append(", recordSequence=");
        sb.append(getRecordSequence());
        sb.append(", modifiedBy=");
        sb.append(getModifiedBy());
        sb.append(", settlementMethodNo=");
        sb.append(getSettlementMethodNo());
        sb.append(", quantity=");
        sb.append(getQuantity());
        sb.append(", accountId=");
        sb.append(getAccountId());
        sb.append(", createdDate=");
        sb.append(getCreatedDate());
        sb.append(", provisionClaimIndicator=");
        sb.append(getProvisionClaimIndicator());
        sb.append(", dispensedDate=");
        sb.append(getDispensedDate());
        sb.append(", isActive=");
        sb.append(getIsActive());
        sb.append(", batchId=");
        sb.append(getBatchId());
        sb.append(", accrualActualEndDate=");
        sb.append(getAccrualActualEndDate());
        sb.append(", marketId=");
        sb.append(getMarketId());
        sb.append(", brandId=");
        sb.append(getBrandId());
        sb.append(", accountName=");
        sb.append(getAccountName());
        sb.append(", amount=");
        sb.append(getAmount());
        sb.append(", actualsMasterSid=");
        sb.append(getActualsMasterSid());
        sb.append(", acctIdentifierCodeQualifier=");
        sb.append(getAcctIdentifierCodeQualifier());
        sb.append(", organizationKey=");
        sb.append(getOrganizationKey());
        sb.append(", createdBy=");
        sb.append(getCreatedBy());
        sb.append(", accrualProcessed=");
        sb.append(getAccrualProcessed());
        sb.append(", parentcomDivmktBrandProdkey=");
        sb.append(getParentcomDivmktBrandProdkey());
        sb.append(", cashPaidDate=");
        sb.append(getCashPaidDate());
        sb.append(", salesAmount=");
        sb.append(getSalesAmount());
        sb.append(", accrualActualStartDate=");
        sb.append(getAccrualActualStartDate());
        sb.append(", settlementNo=");
        sb.append(getSettlementNo());
        sb.append(", price=");
        sb.append(getPrice());
        sb.append(", uploadDate=");
        sb.append(getUploadDate());
        sb.append(", claimIndicator=");
        sb.append(getClaimIndicator());
        sb.append(", itemId=");
        sb.append(getItemId());
        sb.append(", priceAdjustmentName=");
        sb.append(getPriceAdjustmentName());
        sb.append(", contractId=");
        sb.append(getContractId());
        sb.append(", modifiedDate=");
        sb.append(getModifiedDate());
        sb.append(", actualId=");
        sb.append(getActualId());
        sb.append(", provisionId=");
        sb.append(getProvisionId());
        sb.append(", sentOut=");
        sb.append(getSentOut());
        sb.append(", recordLockStatus=");
        sb.append(getRecordLockStatus());
        sb.append(", divisionId=");
        sb.append(getDivisionId());
        sb.append(", itemIdentifierCodeQualifier=");
        sb.append(getItemIdentifierCodeQualifier());
        sb.append(", programStateCode=");
        sb.append(getProgramStateCode());
        sb.append(", source=");
        sb.append(getSource());
        sb.append(", invoiceLineNo=");
        sb.append(getInvoiceLineNo());
        sb.append(", accountNo=");
        sb.append(getAccountNo());
        sb.append(", comDivMktBrandProdKey=");
        sb.append(getComDivMktBrandProdKey());
        sb.append(", inboundStatus=");
        sb.append(getInboundStatus());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(148);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.ActualsMaster");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>quantityInclusion</column-name><column-value><![CDATA[");
        sb.append(getQuantityInclusion());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>mandatedDiscountAmount</column-name><column-value><![CDATA[");
        sb.append(getMandatedDiscountAmount());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemNo</column-name><column-value><![CDATA[");
        sb.append(getItemNo());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>analysisCode</column-name><column-value><![CDATA[");
        sb.append(getAnalysisCode());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>recordSequence</column-name><column-value><![CDATA[");
        sb.append(getRecordSequence());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedBy</column-name><column-value><![CDATA[");
        sb.append(getModifiedBy());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>settlementMethodNo</column-name><column-value><![CDATA[");
        sb.append(getSettlementMethodNo());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>quantity</column-name><column-value><![CDATA[");
        sb.append(getQuantity());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>accountId</column-name><column-value><![CDATA[");
        sb.append(getAccountId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>createdDate</column-name><column-value><![CDATA[");
        sb.append(getCreatedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>provisionClaimIndicator</column-name><column-value><![CDATA[");
        sb.append(getProvisionClaimIndicator());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>dispensedDate</column-name><column-value><![CDATA[");
        sb.append(getDispensedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>isActive</column-name><column-value><![CDATA[");
        sb.append(getIsActive());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>batchId</column-name><column-value><![CDATA[");
        sb.append(getBatchId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>accrualActualEndDate</column-name><column-value><![CDATA[");
        sb.append(getAccrualActualEndDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>marketId</column-name><column-value><![CDATA[");
        sb.append(getMarketId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>brandId</column-name><column-value><![CDATA[");
        sb.append(getBrandId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>accountName</column-name><column-value><![CDATA[");
        sb.append(getAccountName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>amount</column-name><column-value><![CDATA[");
        sb.append(getAmount());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>actualsMasterSid</column-name><column-value><![CDATA[");
        sb.append(getActualsMasterSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>acctIdentifierCodeQualifier</column-name><column-value><![CDATA[");
        sb.append(getAcctIdentifierCodeQualifier());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>organizationKey</column-name><column-value><![CDATA[");
        sb.append(getOrganizationKey());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>createdBy</column-name><column-value><![CDATA[");
        sb.append(getCreatedBy());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>accrualProcessed</column-name><column-value><![CDATA[");
        sb.append(getAccrualProcessed());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>parentcomDivmktBrandProdkey</column-name><column-value><![CDATA[");
        sb.append(getParentcomDivmktBrandProdkey());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>cashPaidDate</column-name><column-value><![CDATA[");
        sb.append(getCashPaidDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>salesAmount</column-name><column-value><![CDATA[");
        sb.append(getSalesAmount());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>accrualActualStartDate</column-name><column-value><![CDATA[");
        sb.append(getAccrualActualStartDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>settlementNo</column-name><column-value><![CDATA[");
        sb.append(getSettlementNo());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>price</column-name><column-value><![CDATA[");
        sb.append(getPrice());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>uploadDate</column-name><column-value><![CDATA[");
        sb.append(getUploadDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>claimIndicator</column-name><column-value><![CDATA[");
        sb.append(getClaimIndicator());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemId</column-name><column-value><![CDATA[");
        sb.append(getItemId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>priceAdjustmentName</column-name><column-value><![CDATA[");
        sb.append(getPriceAdjustmentName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>contractId</column-name><column-value><![CDATA[");
        sb.append(getContractId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
        sb.append(getModifiedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>actualId</column-name><column-value><![CDATA[");
        sb.append(getActualId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>provisionId</column-name><column-value><![CDATA[");
        sb.append(getProvisionId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>sentOut</column-name><column-value><![CDATA[");
        sb.append(getSentOut());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>recordLockStatus</column-name><column-value><![CDATA[");
        sb.append(getRecordLockStatus());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>divisionId</column-name><column-value><![CDATA[");
        sb.append(getDivisionId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemIdentifierCodeQualifier</column-name><column-value><![CDATA[");
        sb.append(getItemIdentifierCodeQualifier());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>programStateCode</column-name><column-value><![CDATA[");
        sb.append(getProgramStateCode());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>source</column-name><column-value><![CDATA[");
        sb.append(getSource());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>invoiceLineNo</column-name><column-value><![CDATA[");
        sb.append(getInvoiceLineNo());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>accountNo</column-name><column-value><![CDATA[");
        sb.append(getAccountNo());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>comDivMktBrandProdKey</column-name><column-value><![CDATA[");
        sb.append(getComDivMktBrandProdKey());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>inboundStatus</column-name><column-value><![CDATA[");
        sb.append(getInboundStatus());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
