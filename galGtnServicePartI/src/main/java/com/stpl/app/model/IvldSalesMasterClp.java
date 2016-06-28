package com.stpl.app.model;

import com.stpl.app.service.ClpSerializer;
import com.stpl.app.service.IvldSalesMasterLocalServiceUtil;

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


public class IvldSalesMasterClp extends BaseModelImpl<IvldSalesMaster>
    implements IvldSalesMaster {
    private String _accountId;
    private String _salesIntfid;
    private Date _modifiedDate;
    private String _organizationKey;
    private String _divisionId;
    private String _source;
    private String _addChgDelIndicator;
    private String _analysisCode;
    private int _ivldSalesMasterSid;
    private String _docType;
    private String _modifiedBy;
    private String _lotNo;
    private String _quantity;
    private String _invoiceLineNumber;
    private String _identifierCodeQualifier;
    private String _accountCodeQualifier;
    private String _parentItemId;
    private String _accountNo;
    private String _reasonForFailure;
    private String _accountName;
    private String _provisionId;
    private String _amount;
    private String _marketId;
    private String _isActive;
    private String _wholesaleOwnerId;
    private String _priceAdjustmentName;
    private String _salesId;
    private String _errorField;
    private String _recordSequence;
    private String _price;
    private String _customerSubtype;
    private String _parentItemNo;
    private String _itemId;
    private String _orderReceivedDate;
    private String _orderNumber;
    private String _accountType;
    private String _uploadDate;
    private String _createdBy;
    private Date _createdDate;
    private String _errorCode;
    private String _itemUom;
    private Date _intfInsertedDate;
    private String _invoiceNumber;
    private String _orderSubtype;
    private String _itemNo;
    private String _reprocessedFlag;
    private String _contractId;
    private String _customerCompanyCode;
    private String _orderType;
    private String _companyId;
    private String _brandId;
    private String _invoiceDate;
    private String _batchId;
    private String _contractNo;
    private BaseModel<?> _ivldSalesMasterRemoteModel;

    public IvldSalesMasterClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return IvldSalesMaster.class;
    }

    @Override
    public String getModelClassName() {
        return IvldSalesMaster.class.getName();
    }

    @Override
    public int getPrimaryKey() {
        return _ivldSalesMasterSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setIvldSalesMasterSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _ivldSalesMasterSid;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Integer) primaryKeyObj).intValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("accountId", getAccountId());
        attributes.put("salesIntfid", getSalesIntfid());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("organizationKey", getOrganizationKey());
        attributes.put("divisionId", getDivisionId());
        attributes.put("source", getSource());
        attributes.put("addChgDelIndicator", getAddChgDelIndicator());
        attributes.put("analysisCode", getAnalysisCode());
        attributes.put("ivldSalesMasterSid", getIvldSalesMasterSid());
        attributes.put("docType", getDocType());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("lotNo", getLotNo());
        attributes.put("quantity", getQuantity());
        attributes.put("invoiceLineNumber", getInvoiceLineNumber());
        attributes.put("identifierCodeQualifier", getIdentifierCodeQualifier());
        attributes.put("accountCodeQualifier", getAccountCodeQualifier());
        attributes.put("parentItemId", getParentItemId());
        attributes.put("accountNo", getAccountNo());
        attributes.put("reasonForFailure", getReasonForFailure());
        attributes.put("accountName", getAccountName());
        attributes.put("provisionId", getProvisionId());
        attributes.put("amount", getAmount());
        attributes.put("marketId", getMarketId());
        attributes.put("isActive", getIsActive());
        attributes.put("wholesaleOwnerId", getWholesaleOwnerId());
        attributes.put("priceAdjustmentName", getPriceAdjustmentName());
        attributes.put("salesId", getSalesId());
        attributes.put("errorField", getErrorField());
        attributes.put("recordSequence", getRecordSequence());
        attributes.put("price", getPrice());
        attributes.put("customerSubtype", getCustomerSubtype());
        attributes.put("parentItemNo", getParentItemNo());
        attributes.put("itemId", getItemId());
        attributes.put("orderReceivedDate", getOrderReceivedDate());
        attributes.put("orderNumber", getOrderNumber());
        attributes.put("accountType", getAccountType());
        attributes.put("uploadDate", getUploadDate());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("errorCode", getErrorCode());
        attributes.put("itemUom", getItemUom());
        attributes.put("intfInsertedDate", getIntfInsertedDate());
        attributes.put("invoiceNumber", getInvoiceNumber());
        attributes.put("orderSubtype", getOrderSubtype());
        attributes.put("itemNo", getItemNo());
        attributes.put("reprocessedFlag", getReprocessedFlag());
        attributes.put("contractId", getContractId());
        attributes.put("customerCompanyCode", getCustomerCompanyCode());
        attributes.put("orderType", getOrderType());
        attributes.put("companyId", getCompanyId());
        attributes.put("brandId", getBrandId());
        attributes.put("invoiceDate", getInvoiceDate());
        attributes.put("batchId", getBatchId());
        attributes.put("contractNo", getContractNo());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        String accountId = (String) attributes.get("accountId");

        if (accountId != null) {
            setAccountId(accountId);
        }

        String salesIntfid = (String) attributes.get("salesIntfid");

        if (salesIntfid != null) {
            setSalesIntfid(salesIntfid);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        String organizationKey = (String) attributes.get("organizationKey");

        if (organizationKey != null) {
            setOrganizationKey(organizationKey);
        }

        String divisionId = (String) attributes.get("divisionId");

        if (divisionId != null) {
            setDivisionId(divisionId);
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

        Integer ivldSalesMasterSid = (Integer) attributes.get(
                "ivldSalesMasterSid");

        if (ivldSalesMasterSid != null) {
            setIvldSalesMasterSid(ivldSalesMasterSid);
        }

        String docType = (String) attributes.get("docType");

        if (docType != null) {
            setDocType(docType);
        }

        String modifiedBy = (String) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        String lotNo = (String) attributes.get("lotNo");

        if (lotNo != null) {
            setLotNo(lotNo);
        }

        String quantity = (String) attributes.get("quantity");

        if (quantity != null) {
            setQuantity(quantity);
        }

        String invoiceLineNumber = (String) attributes.get("invoiceLineNumber");

        if (invoiceLineNumber != null) {
            setInvoiceLineNumber(invoiceLineNumber);
        }

        String identifierCodeQualifier = (String) attributes.get(
                "identifierCodeQualifier");

        if (identifierCodeQualifier != null) {
            setIdentifierCodeQualifier(identifierCodeQualifier);
        }

        String accountCodeQualifier = (String) attributes.get(
                "accountCodeQualifier");

        if (accountCodeQualifier != null) {
            setAccountCodeQualifier(accountCodeQualifier);
        }

        String parentItemId = (String) attributes.get("parentItemId");

        if (parentItemId != null) {
            setParentItemId(parentItemId);
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

        String wholesaleOwnerId = (String) attributes.get("wholesaleOwnerId");

        if (wholesaleOwnerId != null) {
            setWholesaleOwnerId(wholesaleOwnerId);
        }

        String priceAdjustmentName = (String) attributes.get(
                "priceAdjustmentName");

        if (priceAdjustmentName != null) {
            setPriceAdjustmentName(priceAdjustmentName);
        }

        String salesId = (String) attributes.get("salesId");

        if (salesId != null) {
            setSalesId(salesId);
        }

        String errorField = (String) attributes.get("errorField");

        if (errorField != null) {
            setErrorField(errorField);
        }

        String recordSequence = (String) attributes.get("recordSequence");

        if (recordSequence != null) {
            setRecordSequence(recordSequence);
        }

        String price = (String) attributes.get("price");

        if (price != null) {
            setPrice(price);
        }

        String customerSubtype = (String) attributes.get("customerSubtype");

        if (customerSubtype != null) {
            setCustomerSubtype(customerSubtype);
        }

        String parentItemNo = (String) attributes.get("parentItemNo");

        if (parentItemNo != null) {
            setParentItemNo(parentItemNo);
        }

        String itemId = (String) attributes.get("itemId");

        if (itemId != null) {
            setItemId(itemId);
        }

        String orderReceivedDate = (String) attributes.get("orderReceivedDate");

        if (orderReceivedDate != null) {
            setOrderReceivedDate(orderReceivedDate);
        }

        String orderNumber = (String) attributes.get("orderNumber");

        if (orderNumber != null) {
            setOrderNumber(orderNumber);
        }

        String accountType = (String) attributes.get("accountType");

        if (accountType != null) {
            setAccountType(accountType);
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

        String errorCode = (String) attributes.get("errorCode");

        if (errorCode != null) {
            setErrorCode(errorCode);
        }

        String itemUom = (String) attributes.get("itemUom");

        if (itemUom != null) {
            setItemUom(itemUom);
        }

        Date intfInsertedDate = (Date) attributes.get("intfInsertedDate");

        if (intfInsertedDate != null) {
            setIntfInsertedDate(intfInsertedDate);
        }

        String invoiceNumber = (String) attributes.get("invoiceNumber");

        if (invoiceNumber != null) {
            setInvoiceNumber(invoiceNumber);
        }

        String orderSubtype = (String) attributes.get("orderSubtype");

        if (orderSubtype != null) {
            setOrderSubtype(orderSubtype);
        }

        String itemNo = (String) attributes.get("itemNo");

        if (itemNo != null) {
            setItemNo(itemNo);
        }

        String reprocessedFlag = (String) attributes.get("reprocessedFlag");

        if (reprocessedFlag != null) {
            setReprocessedFlag(reprocessedFlag);
        }

        String contractId = (String) attributes.get("contractId");

        if (contractId != null) {
            setContractId(contractId);
        }

        String customerCompanyCode = (String) attributes.get(
                "customerCompanyCode");

        if (customerCompanyCode != null) {
            setCustomerCompanyCode(customerCompanyCode);
        }

        String orderType = (String) attributes.get("orderType");

        if (orderType != null) {
            setOrderType(orderType);
        }

        String companyId = (String) attributes.get("companyId");

        if (companyId != null) {
            setCompanyId(companyId);
        }

        String brandId = (String) attributes.get("brandId");

        if (brandId != null) {
            setBrandId(brandId);
        }

        String invoiceDate = (String) attributes.get("invoiceDate");

        if (invoiceDate != null) {
            setInvoiceDate(invoiceDate);
        }

        String batchId = (String) attributes.get("batchId");

        if (batchId != null) {
            setBatchId(batchId);
        }

        String contractNo = (String) attributes.get("contractNo");

        if (contractNo != null) {
            setContractNo(contractNo);
        }
    }

    @Override
    public String getAccountId() {
        return _accountId;
    }

    @Override
    public void setAccountId(String accountId) {
        _accountId = accountId;

        if (_ivldSalesMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldSalesMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setAccountId", String.class);

                method.invoke(_ivldSalesMasterRemoteModel, accountId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getSalesIntfid() {
        return _salesIntfid;
    }

    @Override
    public void setSalesIntfid(String salesIntfid) {
        _salesIntfid = salesIntfid;

        if (_ivldSalesMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldSalesMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setSalesIntfid", String.class);

                method.invoke(_ivldSalesMasterRemoteModel, salesIntfid);
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

        if (_ivldSalesMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldSalesMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedDate", Date.class);

                method.invoke(_ivldSalesMasterRemoteModel, modifiedDate);
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

        if (_ivldSalesMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldSalesMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setOrganizationKey",
                        String.class);

                method.invoke(_ivldSalesMasterRemoteModel, organizationKey);
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

        if (_ivldSalesMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldSalesMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setDivisionId", String.class);

                method.invoke(_ivldSalesMasterRemoteModel, divisionId);
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

        if (_ivldSalesMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldSalesMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setSource", String.class);

                method.invoke(_ivldSalesMasterRemoteModel, source);
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

        if (_ivldSalesMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldSalesMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setAddChgDelIndicator",
                        String.class);

                method.invoke(_ivldSalesMasterRemoteModel, addChgDelIndicator);
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

        if (_ivldSalesMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldSalesMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setAnalysisCode", String.class);

                method.invoke(_ivldSalesMasterRemoteModel, analysisCode);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getIvldSalesMasterSid() {
        return _ivldSalesMasterSid;
    }

    @Override
    public void setIvldSalesMasterSid(int ivldSalesMasterSid) {
        _ivldSalesMasterSid = ivldSalesMasterSid;

        if (_ivldSalesMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldSalesMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setIvldSalesMasterSid",
                        int.class);

                method.invoke(_ivldSalesMasterRemoteModel, ivldSalesMasterSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getDocType() {
        return _docType;
    }

    @Override
    public void setDocType(String docType) {
        _docType = docType;

        if (_ivldSalesMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldSalesMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setDocType", String.class);

                method.invoke(_ivldSalesMasterRemoteModel, docType);
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

        if (_ivldSalesMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldSalesMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedBy", String.class);

                method.invoke(_ivldSalesMasterRemoteModel, modifiedBy);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getLotNo() {
        return _lotNo;
    }

    @Override
    public void setLotNo(String lotNo) {
        _lotNo = lotNo;

        if (_ivldSalesMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldSalesMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setLotNo", String.class);

                method.invoke(_ivldSalesMasterRemoteModel, lotNo);
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

        if (_ivldSalesMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldSalesMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setQuantity", String.class);

                method.invoke(_ivldSalesMasterRemoteModel, quantity);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getInvoiceLineNumber() {
        return _invoiceLineNumber;
    }

    @Override
    public void setInvoiceLineNumber(String invoiceLineNumber) {
        _invoiceLineNumber = invoiceLineNumber;

        if (_ivldSalesMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldSalesMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setInvoiceLineNumber",
                        String.class);

                method.invoke(_ivldSalesMasterRemoteModel, invoiceLineNumber);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getIdentifierCodeQualifier() {
        return _identifierCodeQualifier;
    }

    @Override
    public void setIdentifierCodeQualifier(String identifierCodeQualifier) {
        _identifierCodeQualifier = identifierCodeQualifier;

        if (_ivldSalesMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldSalesMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setIdentifierCodeQualifier",
                        String.class);

                method.invoke(_ivldSalesMasterRemoteModel,
                    identifierCodeQualifier);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getAccountCodeQualifier() {
        return _accountCodeQualifier;
    }

    @Override
    public void setAccountCodeQualifier(String accountCodeQualifier) {
        _accountCodeQualifier = accountCodeQualifier;

        if (_ivldSalesMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldSalesMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setAccountCodeQualifier",
                        String.class);

                method.invoke(_ivldSalesMasterRemoteModel, accountCodeQualifier);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getParentItemId() {
        return _parentItemId;
    }

    @Override
    public void setParentItemId(String parentItemId) {
        _parentItemId = parentItemId;

        if (_ivldSalesMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldSalesMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setParentItemId", String.class);

                method.invoke(_ivldSalesMasterRemoteModel, parentItemId);
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

        if (_ivldSalesMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldSalesMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setAccountNo", String.class);

                method.invoke(_ivldSalesMasterRemoteModel, accountNo);
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

        if (_ivldSalesMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldSalesMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setReasonForFailure",
                        String.class);

                method.invoke(_ivldSalesMasterRemoteModel, reasonForFailure);
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

        if (_ivldSalesMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldSalesMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setAccountName", String.class);

                method.invoke(_ivldSalesMasterRemoteModel, accountName);
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

        if (_ivldSalesMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldSalesMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setProvisionId", String.class);

                method.invoke(_ivldSalesMasterRemoteModel, provisionId);
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

        if (_ivldSalesMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldSalesMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setAmount", String.class);

                method.invoke(_ivldSalesMasterRemoteModel, amount);
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

        if (_ivldSalesMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldSalesMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setMarketId", String.class);

                method.invoke(_ivldSalesMasterRemoteModel, marketId);
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

        if (_ivldSalesMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldSalesMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setIsActive", String.class);

                method.invoke(_ivldSalesMasterRemoteModel, isActive);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getWholesaleOwnerId() {
        return _wholesaleOwnerId;
    }

    @Override
    public void setWholesaleOwnerId(String wholesaleOwnerId) {
        _wholesaleOwnerId = wholesaleOwnerId;

        if (_ivldSalesMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldSalesMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setWholesaleOwnerId",
                        String.class);

                method.invoke(_ivldSalesMasterRemoteModel, wholesaleOwnerId);
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

        if (_ivldSalesMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldSalesMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setPriceAdjustmentName",
                        String.class);

                method.invoke(_ivldSalesMasterRemoteModel, priceAdjustmentName);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getSalesId() {
        return _salesId;
    }

    @Override
    public void setSalesId(String salesId) {
        _salesId = salesId;

        if (_ivldSalesMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldSalesMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setSalesId", String.class);

                method.invoke(_ivldSalesMasterRemoteModel, salesId);
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

        if (_ivldSalesMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldSalesMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setErrorField", String.class);

                method.invoke(_ivldSalesMasterRemoteModel, errorField);
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

        if (_ivldSalesMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldSalesMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setRecordSequence",
                        String.class);

                method.invoke(_ivldSalesMasterRemoteModel, recordSequence);
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

        if (_ivldSalesMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldSalesMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setPrice", String.class);

                method.invoke(_ivldSalesMasterRemoteModel, price);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getCustomerSubtype() {
        return _customerSubtype;
    }

    @Override
    public void setCustomerSubtype(String customerSubtype) {
        _customerSubtype = customerSubtype;

        if (_ivldSalesMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldSalesMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCustomerSubtype",
                        String.class);

                method.invoke(_ivldSalesMasterRemoteModel, customerSubtype);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getParentItemNo() {
        return _parentItemNo;
    }

    @Override
    public void setParentItemNo(String parentItemNo) {
        _parentItemNo = parentItemNo;

        if (_ivldSalesMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldSalesMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setParentItemNo", String.class);

                method.invoke(_ivldSalesMasterRemoteModel, parentItemNo);
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

        if (_ivldSalesMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldSalesMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setItemId", String.class);

                method.invoke(_ivldSalesMasterRemoteModel, itemId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getOrderReceivedDate() {
        return _orderReceivedDate;
    }

    @Override
    public void setOrderReceivedDate(String orderReceivedDate) {
        _orderReceivedDate = orderReceivedDate;

        if (_ivldSalesMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldSalesMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setOrderReceivedDate",
                        String.class);

                method.invoke(_ivldSalesMasterRemoteModel, orderReceivedDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getOrderNumber() {
        return _orderNumber;
    }

    @Override
    public void setOrderNumber(String orderNumber) {
        _orderNumber = orderNumber;

        if (_ivldSalesMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldSalesMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setOrderNumber", String.class);

                method.invoke(_ivldSalesMasterRemoteModel, orderNumber);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getAccountType() {
        return _accountType;
    }

    @Override
    public void setAccountType(String accountType) {
        _accountType = accountType;

        if (_ivldSalesMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldSalesMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setAccountType", String.class);

                method.invoke(_ivldSalesMasterRemoteModel, accountType);
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

        if (_ivldSalesMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldSalesMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setUploadDate", String.class);

                method.invoke(_ivldSalesMasterRemoteModel, uploadDate);
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

        if (_ivldSalesMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldSalesMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedBy", String.class);

                method.invoke(_ivldSalesMasterRemoteModel, createdBy);
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

        if (_ivldSalesMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldSalesMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedDate", Date.class);

                method.invoke(_ivldSalesMasterRemoteModel, createdDate);
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

        if (_ivldSalesMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldSalesMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setErrorCode", String.class);

                method.invoke(_ivldSalesMasterRemoteModel, errorCode);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getItemUom() {
        return _itemUom;
    }

    @Override
    public void setItemUom(String itemUom) {
        _itemUom = itemUom;

        if (_ivldSalesMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldSalesMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setItemUom", String.class);

                method.invoke(_ivldSalesMasterRemoteModel, itemUom);
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

        if (_ivldSalesMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldSalesMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setIntfInsertedDate",
                        Date.class);

                method.invoke(_ivldSalesMasterRemoteModel, intfInsertedDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getInvoiceNumber() {
        return _invoiceNumber;
    }

    @Override
    public void setInvoiceNumber(String invoiceNumber) {
        _invoiceNumber = invoiceNumber;

        if (_ivldSalesMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldSalesMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setInvoiceNumber", String.class);

                method.invoke(_ivldSalesMasterRemoteModel, invoiceNumber);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getOrderSubtype() {
        return _orderSubtype;
    }

    @Override
    public void setOrderSubtype(String orderSubtype) {
        _orderSubtype = orderSubtype;

        if (_ivldSalesMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldSalesMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setOrderSubtype", String.class);

                method.invoke(_ivldSalesMasterRemoteModel, orderSubtype);
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

        if (_ivldSalesMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldSalesMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setItemNo", String.class);

                method.invoke(_ivldSalesMasterRemoteModel, itemNo);
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

        if (_ivldSalesMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldSalesMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setReprocessedFlag",
                        String.class);

                method.invoke(_ivldSalesMasterRemoteModel, reprocessedFlag);
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

        if (_ivldSalesMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldSalesMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setContractId", String.class);

                method.invoke(_ivldSalesMasterRemoteModel, contractId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getCustomerCompanyCode() {
        return _customerCompanyCode;
    }

    @Override
    public void setCustomerCompanyCode(String customerCompanyCode) {
        _customerCompanyCode = customerCompanyCode;

        if (_ivldSalesMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldSalesMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCustomerCompanyCode",
                        String.class);

                method.invoke(_ivldSalesMasterRemoteModel, customerCompanyCode);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getOrderType() {
        return _orderType;
    }

    @Override
    public void setOrderType(String orderType) {
        _orderType = orderType;

        if (_ivldSalesMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldSalesMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setOrderType", String.class);

                method.invoke(_ivldSalesMasterRemoteModel, orderType);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getCompanyId() {
        return _companyId;
    }

    @Override
    public void setCompanyId(String companyId) {
        _companyId = companyId;

        if (_ivldSalesMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldSalesMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCompanyId", String.class);

                method.invoke(_ivldSalesMasterRemoteModel, companyId);
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

        if (_ivldSalesMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldSalesMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setBrandId", String.class);

                method.invoke(_ivldSalesMasterRemoteModel, brandId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getInvoiceDate() {
        return _invoiceDate;
    }

    @Override
    public void setInvoiceDate(String invoiceDate) {
        _invoiceDate = invoiceDate;

        if (_ivldSalesMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldSalesMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setInvoiceDate", String.class);

                method.invoke(_ivldSalesMasterRemoteModel, invoiceDate);
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

        if (_ivldSalesMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldSalesMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setBatchId", String.class);

                method.invoke(_ivldSalesMasterRemoteModel, batchId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getContractNo() {
        return _contractNo;
    }

    @Override
    public void setContractNo(String contractNo) {
        _contractNo = contractNo;

        if (_ivldSalesMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldSalesMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setContractNo", String.class);

                method.invoke(_ivldSalesMasterRemoteModel, contractNo);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getIvldSalesMasterRemoteModel() {
        return _ivldSalesMasterRemoteModel;
    }

    public void setIvldSalesMasterRemoteModel(
        BaseModel<?> ivldSalesMasterRemoteModel) {
        _ivldSalesMasterRemoteModel = ivldSalesMasterRemoteModel;
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

        Class<?> remoteModelClass = _ivldSalesMasterRemoteModel.getClass();

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

        Object returnValue = method.invoke(_ivldSalesMasterRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            IvldSalesMasterLocalServiceUtil.addIvldSalesMaster(this);
        } else {
            IvldSalesMasterLocalServiceUtil.updateIvldSalesMaster(this);
        }
    }

    @Override
    public IvldSalesMaster toEscapedModel() {
        return (IvldSalesMaster) ProxyUtil.newProxyInstance(IvldSalesMaster.class.getClassLoader(),
            new Class[] { IvldSalesMaster.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        IvldSalesMasterClp clone = new IvldSalesMasterClp();

        clone.setAccountId(getAccountId());
        clone.setSalesIntfid(getSalesIntfid());
        clone.setModifiedDate(getModifiedDate());
        clone.setOrganizationKey(getOrganizationKey());
        clone.setDivisionId(getDivisionId());
        clone.setSource(getSource());
        clone.setAddChgDelIndicator(getAddChgDelIndicator());
        clone.setAnalysisCode(getAnalysisCode());
        clone.setIvldSalesMasterSid(getIvldSalesMasterSid());
        clone.setDocType(getDocType());
        clone.setModifiedBy(getModifiedBy());
        clone.setLotNo(getLotNo());
        clone.setQuantity(getQuantity());
        clone.setInvoiceLineNumber(getInvoiceLineNumber());
        clone.setIdentifierCodeQualifier(getIdentifierCodeQualifier());
        clone.setAccountCodeQualifier(getAccountCodeQualifier());
        clone.setParentItemId(getParentItemId());
        clone.setAccountNo(getAccountNo());
        clone.setReasonForFailure(getReasonForFailure());
        clone.setAccountName(getAccountName());
        clone.setProvisionId(getProvisionId());
        clone.setAmount(getAmount());
        clone.setMarketId(getMarketId());
        clone.setIsActive(getIsActive());
        clone.setWholesaleOwnerId(getWholesaleOwnerId());
        clone.setPriceAdjustmentName(getPriceAdjustmentName());
        clone.setSalesId(getSalesId());
        clone.setErrorField(getErrorField());
        clone.setRecordSequence(getRecordSequence());
        clone.setPrice(getPrice());
        clone.setCustomerSubtype(getCustomerSubtype());
        clone.setParentItemNo(getParentItemNo());
        clone.setItemId(getItemId());
        clone.setOrderReceivedDate(getOrderReceivedDate());
        clone.setOrderNumber(getOrderNumber());
        clone.setAccountType(getAccountType());
        clone.setUploadDate(getUploadDate());
        clone.setCreatedBy(getCreatedBy());
        clone.setCreatedDate(getCreatedDate());
        clone.setErrorCode(getErrorCode());
        clone.setItemUom(getItemUom());
        clone.setIntfInsertedDate(getIntfInsertedDate());
        clone.setInvoiceNumber(getInvoiceNumber());
        clone.setOrderSubtype(getOrderSubtype());
        clone.setItemNo(getItemNo());
        clone.setReprocessedFlag(getReprocessedFlag());
        clone.setContractId(getContractId());
        clone.setCustomerCompanyCode(getCustomerCompanyCode());
        clone.setOrderType(getOrderType());
        clone.setCompanyId(getCompanyId());
        clone.setBrandId(getBrandId());
        clone.setInvoiceDate(getInvoiceDate());
        clone.setBatchId(getBatchId());
        clone.setContractNo(getContractNo());

        return clone;
    }

    @Override
    public int compareTo(IvldSalesMaster ivldSalesMaster) {
        int primaryKey = ivldSalesMaster.getPrimaryKey();

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

        if (!(obj instanceof IvldSalesMasterClp)) {
            return false;
        }

        IvldSalesMasterClp ivldSalesMaster = (IvldSalesMasterClp) obj;

        int primaryKey = ivldSalesMaster.getPrimaryKey();

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

        sb.append("{accountId=");
        sb.append(getAccountId());
        sb.append(", salesIntfid=");
        sb.append(getSalesIntfid());
        sb.append(", modifiedDate=");
        sb.append(getModifiedDate());
        sb.append(", organizationKey=");
        sb.append(getOrganizationKey());
        sb.append(", divisionId=");
        sb.append(getDivisionId());
        sb.append(", source=");
        sb.append(getSource());
        sb.append(", addChgDelIndicator=");
        sb.append(getAddChgDelIndicator());
        sb.append(", analysisCode=");
        sb.append(getAnalysisCode());
        sb.append(", ivldSalesMasterSid=");
        sb.append(getIvldSalesMasterSid());
        sb.append(", docType=");
        sb.append(getDocType());
        sb.append(", modifiedBy=");
        sb.append(getModifiedBy());
        sb.append(", lotNo=");
        sb.append(getLotNo());
        sb.append(", quantity=");
        sb.append(getQuantity());
        sb.append(", invoiceLineNumber=");
        sb.append(getInvoiceLineNumber());
        sb.append(", identifierCodeQualifier=");
        sb.append(getIdentifierCodeQualifier());
        sb.append(", accountCodeQualifier=");
        sb.append(getAccountCodeQualifier());
        sb.append(", parentItemId=");
        sb.append(getParentItemId());
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
        sb.append(", wholesaleOwnerId=");
        sb.append(getWholesaleOwnerId());
        sb.append(", priceAdjustmentName=");
        sb.append(getPriceAdjustmentName());
        sb.append(", salesId=");
        sb.append(getSalesId());
        sb.append(", errorField=");
        sb.append(getErrorField());
        sb.append(", recordSequence=");
        sb.append(getRecordSequence());
        sb.append(", price=");
        sb.append(getPrice());
        sb.append(", customerSubtype=");
        sb.append(getCustomerSubtype());
        sb.append(", parentItemNo=");
        sb.append(getParentItemNo());
        sb.append(", itemId=");
        sb.append(getItemId());
        sb.append(", orderReceivedDate=");
        sb.append(getOrderReceivedDate());
        sb.append(", orderNumber=");
        sb.append(getOrderNumber());
        sb.append(", accountType=");
        sb.append(getAccountType());
        sb.append(", uploadDate=");
        sb.append(getUploadDate());
        sb.append(", createdBy=");
        sb.append(getCreatedBy());
        sb.append(", createdDate=");
        sb.append(getCreatedDate());
        sb.append(", errorCode=");
        sb.append(getErrorCode());
        sb.append(", itemUom=");
        sb.append(getItemUom());
        sb.append(", intfInsertedDate=");
        sb.append(getIntfInsertedDate());
        sb.append(", invoiceNumber=");
        sb.append(getInvoiceNumber());
        sb.append(", orderSubtype=");
        sb.append(getOrderSubtype());
        sb.append(", itemNo=");
        sb.append(getItemNo());
        sb.append(", reprocessedFlag=");
        sb.append(getReprocessedFlag());
        sb.append(", contractId=");
        sb.append(getContractId());
        sb.append(", customerCompanyCode=");
        sb.append(getCustomerCompanyCode());
        sb.append(", orderType=");
        sb.append(getOrderType());
        sb.append(", companyId=");
        sb.append(getCompanyId());
        sb.append(", brandId=");
        sb.append(getBrandId());
        sb.append(", invoiceDate=");
        sb.append(getInvoiceDate());
        sb.append(", batchId=");
        sb.append(getBatchId());
        sb.append(", contractNo=");
        sb.append(getContractNo());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(166);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.IvldSalesMaster");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>accountId</column-name><column-value><![CDATA[");
        sb.append(getAccountId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>salesIntfid</column-name><column-value><![CDATA[");
        sb.append(getSalesIntfid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
        sb.append(getModifiedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>organizationKey</column-name><column-value><![CDATA[");
        sb.append(getOrganizationKey());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>divisionId</column-name><column-value><![CDATA[");
        sb.append(getDivisionId());
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
            "<column><column-name>ivldSalesMasterSid</column-name><column-value><![CDATA[");
        sb.append(getIvldSalesMasterSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>docType</column-name><column-value><![CDATA[");
        sb.append(getDocType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedBy</column-name><column-value><![CDATA[");
        sb.append(getModifiedBy());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>lotNo</column-name><column-value><![CDATA[");
        sb.append(getLotNo());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>quantity</column-name><column-value><![CDATA[");
        sb.append(getQuantity());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>invoiceLineNumber</column-name><column-value><![CDATA[");
        sb.append(getInvoiceLineNumber());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>identifierCodeQualifier</column-name><column-value><![CDATA[");
        sb.append(getIdentifierCodeQualifier());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>accountCodeQualifier</column-name><column-value><![CDATA[");
        sb.append(getAccountCodeQualifier());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>parentItemId</column-name><column-value><![CDATA[");
        sb.append(getParentItemId());
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
            "<column><column-name>wholesaleOwnerId</column-name><column-value><![CDATA[");
        sb.append(getWholesaleOwnerId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>priceAdjustmentName</column-name><column-value><![CDATA[");
        sb.append(getPriceAdjustmentName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>salesId</column-name><column-value><![CDATA[");
        sb.append(getSalesId());
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
            "<column><column-name>price</column-name><column-value><![CDATA[");
        sb.append(getPrice());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>customerSubtype</column-name><column-value><![CDATA[");
        sb.append(getCustomerSubtype());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>parentItemNo</column-name><column-value><![CDATA[");
        sb.append(getParentItemNo());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemId</column-name><column-value><![CDATA[");
        sb.append(getItemId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>orderReceivedDate</column-name><column-value><![CDATA[");
        sb.append(getOrderReceivedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>orderNumber</column-name><column-value><![CDATA[");
        sb.append(getOrderNumber());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>accountType</column-name><column-value><![CDATA[");
        sb.append(getAccountType());
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
            "<column><column-name>errorCode</column-name><column-value><![CDATA[");
        sb.append(getErrorCode());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemUom</column-name><column-value><![CDATA[");
        sb.append(getItemUom());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>intfInsertedDate</column-name><column-value><![CDATA[");
        sb.append(getIntfInsertedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>invoiceNumber</column-name><column-value><![CDATA[");
        sb.append(getInvoiceNumber());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>orderSubtype</column-name><column-value><![CDATA[");
        sb.append(getOrderSubtype());
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
            "<column><column-name>contractId</column-name><column-value><![CDATA[");
        sb.append(getContractId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>customerCompanyCode</column-name><column-value><![CDATA[");
        sb.append(getCustomerCompanyCode());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>orderType</column-name><column-value><![CDATA[");
        sb.append(getOrderType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>companyId</column-name><column-value><![CDATA[");
        sb.append(getCompanyId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>brandId</column-name><column-value><![CDATA[");
        sb.append(getBrandId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>invoiceDate</column-name><column-value><![CDATA[");
        sb.append(getInvoiceDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>batchId</column-name><column-value><![CDATA[");
        sb.append(getBatchId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>contractNo</column-name><column-value><![CDATA[");
        sb.append(getContractNo());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
