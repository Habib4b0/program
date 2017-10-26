package com.stpl.app.model;

import com.stpl.app.service.ClpSerializer;
import com.stpl.app.service.SalesMasterLocalServiceUtil;

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


public class SalesMasterClp extends BaseModelImpl<SalesMaster>
    implements SalesMaster {
    private String _itemNo;
    private int _recordSequence;
    private double _quantity;
    private String _accountId;
    private Date _createdDate;
    private String _identifierCodeQualifier;
    private String _isActive;
    private String _marketId;
    private Date _invoiceDate;
    private String _accountName;
    private String _docType;
    private Date _orderReceivedDate;
    private double _amount;
    private int _salesMasterSid;
    private String _orderSubtype;
    private int _createdBy;
    private double _price;
    private Date _uploadDate;
    private String _itemId;
    private String _priceAdjustmentName;
    private String _itemCodeQualifier;
    private String _contractId;
    private String _itemUom;
    private Date _modifiedDate;
    private String _customerSubtype;
    private int _provisionId;
    private String _wholesaleOwnerId;
    private String _source;
    private String _accountNo;
    private String _lotNo;
    private String _parentItemId;
    private String _customerCompanyCode;
    private String _analysisCode;
    private String _accountType;
    private int _modifiedBy;
    private String _contractNo;
    private String _batchId;
    private String _brandId;
    private String _salesId;
    private String _companyId;
    private String _organizationKey;
    private String _itemParentNo;
    private String _invoiceNumber;
    private String _orderType;
    private boolean _recordLockStatus;
    private String _divisionId;
    private String _invoiceLineNumber;
    private String _orderNumber;
    private String _inboundStatus;
    private BaseModel<?> _salesMasterRemoteModel;

    public SalesMasterClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return SalesMaster.class;
    }

    @Override
    public String getModelClassName() {
        return SalesMaster.class.getName();
    }

    @Override
    public int getPrimaryKey() {
        return _salesMasterSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setSalesMasterSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _salesMasterSid;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Integer) primaryKeyObj).intValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("itemNo", getItemNo());
        attributes.put("recordSequence", getRecordSequence());
        attributes.put("quantity", getQuantity());
        attributes.put("accountId", getAccountId());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("identifierCodeQualifier", getIdentifierCodeQualifier());
        attributes.put("isActive", getIsActive());
        attributes.put("marketId", getMarketId());
        attributes.put("invoiceDate", getInvoiceDate());
        attributes.put("accountName", getAccountName());
        attributes.put("docType", getDocType());
        attributes.put("orderReceivedDate", getOrderReceivedDate());
        attributes.put("amount", getAmount());
        attributes.put("salesMasterSid", getSalesMasterSid());
        attributes.put("orderSubtype", getOrderSubtype());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("price", getPrice());
        attributes.put("uploadDate", getUploadDate());
        attributes.put("itemId", getItemId());
        attributes.put("priceAdjustmentName", getPriceAdjustmentName());
        attributes.put("itemCodeQualifier", getItemCodeQualifier());
        attributes.put("contractId", getContractId());
        attributes.put("itemUom", getItemUom());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("customerSubtype", getCustomerSubtype());
        attributes.put("provisionId", getProvisionId());
        attributes.put("wholesaleOwnerId", getWholesaleOwnerId());
        attributes.put("source", getSource());
        attributes.put("accountNo", getAccountNo());
        attributes.put("lotNo", getLotNo());
        attributes.put("parentItemId", getParentItemId());
        attributes.put("customerCompanyCode", getCustomerCompanyCode());
        attributes.put("analysisCode", getAnalysisCode());
        attributes.put("accountType", getAccountType());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("contractNo", getContractNo());
        attributes.put("batchId", getBatchId());
        attributes.put("brandId", getBrandId());
        attributes.put("salesId", getSalesId());
        attributes.put("companyId", getCompanyId());
        attributes.put("organizationKey", getOrganizationKey());
        attributes.put("itemParentNo", getItemParentNo());
        attributes.put("invoiceNumber", getInvoiceNumber());
        attributes.put("orderType", getOrderType());
        attributes.put("recordLockStatus", getRecordLockStatus());
        attributes.put("divisionId", getDivisionId());
        attributes.put("invoiceLineNumber", getInvoiceLineNumber());
        attributes.put("orderNumber", getOrderNumber());
        attributes.put("inboundStatus", getInboundStatus());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        String itemNo = (String) attributes.get("itemNo");

        if (itemNo != null) {
            setItemNo(itemNo);
        }

        Integer recordSequence = (Integer) attributes.get("recordSequence");

        if (recordSequence != null) {
            setRecordSequence(recordSequence);
        }

        Double quantity = (Double) attributes.get("quantity");

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

        String identifierCodeQualifier = (String) attributes.get(
                "identifierCodeQualifier");

        if (identifierCodeQualifier != null) {
            setIdentifierCodeQualifier(identifierCodeQualifier);
        }

        String isActive = (String) attributes.get("isActive");

        if (isActive != null) {
            setIsActive(isActive);
        }

        String marketId = (String) attributes.get("marketId");

        if (marketId != null) {
            setMarketId(marketId);
        }

        Date invoiceDate = (Date) attributes.get("invoiceDate");

        if (invoiceDate != null) {
            setInvoiceDate(invoiceDate);
        }

        String accountName = (String) attributes.get("accountName");

        if (accountName != null) {
            setAccountName(accountName);
        }

        String docType = (String) attributes.get("docType");

        if (docType != null) {
            setDocType(docType);
        }

        Date orderReceivedDate = (Date) attributes.get("orderReceivedDate");

        if (orderReceivedDate != null) {
            setOrderReceivedDate(orderReceivedDate);
        }

        Double amount = (Double) attributes.get("amount");

        if (amount != null) {
            setAmount(amount);
        }

        Integer salesMasterSid = (Integer) attributes.get("salesMasterSid");

        if (salesMasterSid != null) {
            setSalesMasterSid(salesMasterSid);
        }

        String orderSubtype = (String) attributes.get("orderSubtype");

        if (orderSubtype != null) {
            setOrderSubtype(orderSubtype);
        }

        Integer createdBy = (Integer) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        Double price = (Double) attributes.get("price");

        if (price != null) {
            setPrice(price);
        }

        Date uploadDate = (Date) attributes.get("uploadDate");

        if (uploadDate != null) {
            setUploadDate(uploadDate);
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

        String itemCodeQualifier = (String) attributes.get("itemCodeQualifier");

        if (itemCodeQualifier != null) {
            setItemCodeQualifier(itemCodeQualifier);
        }

        String contractId = (String) attributes.get("contractId");

        if (contractId != null) {
            setContractId(contractId);
        }

        String itemUom = (String) attributes.get("itemUom");

        if (itemUom != null) {
            setItemUom(itemUom);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        String customerSubtype = (String) attributes.get("customerSubtype");

        if (customerSubtype != null) {
            setCustomerSubtype(customerSubtype);
        }

        Integer provisionId = (Integer) attributes.get("provisionId");

        if (provisionId != null) {
            setProvisionId(provisionId);
        }

        String wholesaleOwnerId = (String) attributes.get("wholesaleOwnerId");

        if (wholesaleOwnerId != null) {
            setWholesaleOwnerId(wholesaleOwnerId);
        }

        String source = (String) attributes.get("source");

        if (source != null) {
            setSource(source);
        }

        String accountNo = (String) attributes.get("accountNo");

        if (accountNo != null) {
            setAccountNo(accountNo);
        }

        String lotNo = (String) attributes.get("lotNo");

        if (lotNo != null) {
            setLotNo(lotNo);
        }

        String parentItemId = (String) attributes.get("parentItemId");

        if (parentItemId != null) {
            setParentItemId(parentItemId);
        }

        String customerCompanyCode = (String) attributes.get(
                "customerCompanyCode");

        if (customerCompanyCode != null) {
            setCustomerCompanyCode(customerCompanyCode);
        }

        String analysisCode = (String) attributes.get("analysisCode");

        if (analysisCode != null) {
            setAnalysisCode(analysisCode);
        }

        String accountType = (String) attributes.get("accountType");

        if (accountType != null) {
            setAccountType(accountType);
        }

        Integer modifiedBy = (Integer) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        String contractNo = (String) attributes.get("contractNo");

        if (contractNo != null) {
            setContractNo(contractNo);
        }

        String batchId = (String) attributes.get("batchId");

        if (batchId != null) {
            setBatchId(batchId);
        }

        String brandId = (String) attributes.get("brandId");

        if (brandId != null) {
            setBrandId(brandId);
        }

        String salesId = (String) attributes.get("salesId");

        if (salesId != null) {
            setSalesId(salesId);
        }

        String companyId = (String) attributes.get("companyId");

        if (companyId != null) {
            setCompanyId(companyId);
        }

        String organizationKey = (String) attributes.get("organizationKey");

        if (organizationKey != null) {
            setOrganizationKey(organizationKey);
        }

        String itemParentNo = (String) attributes.get("itemParentNo");

        if (itemParentNo != null) {
            setItemParentNo(itemParentNo);
        }

        String invoiceNumber = (String) attributes.get("invoiceNumber");

        if (invoiceNumber != null) {
            setInvoiceNumber(invoiceNumber);
        }

        String orderType = (String) attributes.get("orderType");

        if (orderType != null) {
            setOrderType(orderType);
        }

        Boolean recordLockStatus = (Boolean) attributes.get("recordLockStatus");

        if (recordLockStatus != null) {
            setRecordLockStatus(recordLockStatus);
        }

        String divisionId = (String) attributes.get("divisionId");

        if (divisionId != null) {
            setDivisionId(divisionId);
        }

        String invoiceLineNumber = (String) attributes.get("invoiceLineNumber");

        if (invoiceLineNumber != null) {
            setInvoiceLineNumber(invoiceLineNumber);
        }

        String orderNumber = (String) attributes.get("orderNumber");

        if (orderNumber != null) {
            setOrderNumber(orderNumber);
        }

        String inboundStatus = (String) attributes.get("inboundStatus");

        if (inboundStatus != null) {
            setInboundStatus(inboundStatus);
        }
    }

    @Override
    public String getItemNo() {
        return _itemNo;
    }

    @Override
    public void setItemNo(String itemNo) {
        _itemNo = itemNo;

        if (_salesMasterRemoteModel != null) {
            try {
                Class<?> clazz = _salesMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setItemNo", String.class);

                method.invoke(_salesMasterRemoteModel, itemNo);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getRecordSequence() {
        return _recordSequence;
    }

    @Override
    public void setRecordSequence(int recordSequence) {
        _recordSequence = recordSequence;

        if (_salesMasterRemoteModel != null) {
            try {
                Class<?> clazz = _salesMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setRecordSequence", int.class);

                method.invoke(_salesMasterRemoteModel, recordSequence);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getQuantity() {
        return _quantity;
    }

    @Override
    public void setQuantity(double quantity) {
        _quantity = quantity;

        if (_salesMasterRemoteModel != null) {
            try {
                Class<?> clazz = _salesMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setQuantity", double.class);

                method.invoke(_salesMasterRemoteModel, quantity);
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

        if (_salesMasterRemoteModel != null) {
            try {
                Class<?> clazz = _salesMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setAccountId", String.class);

                method.invoke(_salesMasterRemoteModel, accountId);
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

        if (_salesMasterRemoteModel != null) {
            try {
                Class<?> clazz = _salesMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedDate", Date.class);

                method.invoke(_salesMasterRemoteModel, createdDate);
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

        if (_salesMasterRemoteModel != null) {
            try {
                Class<?> clazz = _salesMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setIdentifierCodeQualifier",
                        String.class);

                method.invoke(_salesMasterRemoteModel, identifierCodeQualifier);
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

        if (_salesMasterRemoteModel != null) {
            try {
                Class<?> clazz = _salesMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setIsActive", String.class);

                method.invoke(_salesMasterRemoteModel, isActive);
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

        if (_salesMasterRemoteModel != null) {
            try {
                Class<?> clazz = _salesMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setMarketId", String.class);

                method.invoke(_salesMasterRemoteModel, marketId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getInvoiceDate() {
        return _invoiceDate;
    }

    @Override
    public void setInvoiceDate(Date invoiceDate) {
        _invoiceDate = invoiceDate;

        if (_salesMasterRemoteModel != null) {
            try {
                Class<?> clazz = _salesMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setInvoiceDate", Date.class);

                method.invoke(_salesMasterRemoteModel, invoiceDate);
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

        if (_salesMasterRemoteModel != null) {
            try {
                Class<?> clazz = _salesMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setAccountName", String.class);

                method.invoke(_salesMasterRemoteModel, accountName);
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

        if (_salesMasterRemoteModel != null) {
            try {
                Class<?> clazz = _salesMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setDocType", String.class);

                method.invoke(_salesMasterRemoteModel, docType);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getOrderReceivedDate() {
        return _orderReceivedDate;
    }

    @Override
    public void setOrderReceivedDate(Date orderReceivedDate) {
        _orderReceivedDate = orderReceivedDate;

        if (_salesMasterRemoteModel != null) {
            try {
                Class<?> clazz = _salesMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setOrderReceivedDate",
                        Date.class);

                method.invoke(_salesMasterRemoteModel, orderReceivedDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getAmount() {
        return _amount;
    }

    @Override
    public void setAmount(double amount) {
        _amount = amount;

        if (_salesMasterRemoteModel != null) {
            try {
                Class<?> clazz = _salesMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setAmount", double.class);

                method.invoke(_salesMasterRemoteModel, amount);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getSalesMasterSid() {
        return _salesMasterSid;
    }

    @Override
    public void setSalesMasterSid(int salesMasterSid) {
        _salesMasterSid = salesMasterSid;

        if (_salesMasterRemoteModel != null) {
            try {
                Class<?> clazz = _salesMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setSalesMasterSid", int.class);

                method.invoke(_salesMasterRemoteModel, salesMasterSid);
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

        if (_salesMasterRemoteModel != null) {
            try {
                Class<?> clazz = _salesMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setOrderSubtype", String.class);

                method.invoke(_salesMasterRemoteModel, orderSubtype);
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

        if (_salesMasterRemoteModel != null) {
            try {
                Class<?> clazz = _salesMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedBy", int.class);

                method.invoke(_salesMasterRemoteModel, createdBy);
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

        if (_salesMasterRemoteModel != null) {
            try {
                Class<?> clazz = _salesMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setPrice", double.class);

                method.invoke(_salesMasterRemoteModel, price);
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

        if (_salesMasterRemoteModel != null) {
            try {
                Class<?> clazz = _salesMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setUploadDate", Date.class);

                method.invoke(_salesMasterRemoteModel, uploadDate);
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

        if (_salesMasterRemoteModel != null) {
            try {
                Class<?> clazz = _salesMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setItemId", String.class);

                method.invoke(_salesMasterRemoteModel, itemId);
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

        if (_salesMasterRemoteModel != null) {
            try {
                Class<?> clazz = _salesMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setPriceAdjustmentName",
                        String.class);

                method.invoke(_salesMasterRemoteModel, priceAdjustmentName);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getItemCodeQualifier() {
        return _itemCodeQualifier;
    }

    @Override
    public void setItemCodeQualifier(String itemCodeQualifier) {
        _itemCodeQualifier = itemCodeQualifier;

        if (_salesMasterRemoteModel != null) {
            try {
                Class<?> clazz = _salesMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setItemCodeQualifier",
                        String.class);

                method.invoke(_salesMasterRemoteModel, itemCodeQualifier);
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

        if (_salesMasterRemoteModel != null) {
            try {
                Class<?> clazz = _salesMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setContractId", String.class);

                method.invoke(_salesMasterRemoteModel, contractId);
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

        if (_salesMasterRemoteModel != null) {
            try {
                Class<?> clazz = _salesMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setItemUom", String.class);

                method.invoke(_salesMasterRemoteModel, itemUom);
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

        if (_salesMasterRemoteModel != null) {
            try {
                Class<?> clazz = _salesMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedDate", Date.class);

                method.invoke(_salesMasterRemoteModel, modifiedDate);
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

        if (_salesMasterRemoteModel != null) {
            try {
                Class<?> clazz = _salesMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCustomerSubtype",
                        String.class);

                method.invoke(_salesMasterRemoteModel, customerSubtype);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getProvisionId() {
        return _provisionId;
    }

    @Override
    public void setProvisionId(int provisionId) {
        _provisionId = provisionId;

        if (_salesMasterRemoteModel != null) {
            try {
                Class<?> clazz = _salesMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setProvisionId", int.class);

                method.invoke(_salesMasterRemoteModel, provisionId);
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

        if (_salesMasterRemoteModel != null) {
            try {
                Class<?> clazz = _salesMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setWholesaleOwnerId",
                        String.class);

                method.invoke(_salesMasterRemoteModel, wholesaleOwnerId);
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

        if (_salesMasterRemoteModel != null) {
            try {
                Class<?> clazz = _salesMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setSource", String.class);

                method.invoke(_salesMasterRemoteModel, source);
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

        if (_salesMasterRemoteModel != null) {
            try {
                Class<?> clazz = _salesMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setAccountNo", String.class);

                method.invoke(_salesMasterRemoteModel, accountNo);
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

        if (_salesMasterRemoteModel != null) {
            try {
                Class<?> clazz = _salesMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setLotNo", String.class);

                method.invoke(_salesMasterRemoteModel, lotNo);
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

        if (_salesMasterRemoteModel != null) {
            try {
                Class<?> clazz = _salesMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setParentItemId", String.class);

                method.invoke(_salesMasterRemoteModel, parentItemId);
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

        if (_salesMasterRemoteModel != null) {
            try {
                Class<?> clazz = _salesMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCustomerCompanyCode",
                        String.class);

                method.invoke(_salesMasterRemoteModel, customerCompanyCode);
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

        if (_salesMasterRemoteModel != null) {
            try {
                Class<?> clazz = _salesMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setAnalysisCode", String.class);

                method.invoke(_salesMasterRemoteModel, analysisCode);
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

        if (_salesMasterRemoteModel != null) {
            try {
                Class<?> clazz = _salesMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setAccountType", String.class);

                method.invoke(_salesMasterRemoteModel, accountType);
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

        if (_salesMasterRemoteModel != null) {
            try {
                Class<?> clazz = _salesMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedBy", int.class);

                method.invoke(_salesMasterRemoteModel, modifiedBy);
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

        if (_salesMasterRemoteModel != null) {
            try {
                Class<?> clazz = _salesMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setContractNo", String.class);

                method.invoke(_salesMasterRemoteModel, contractNo);
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

        if (_salesMasterRemoteModel != null) {
            try {
                Class<?> clazz = _salesMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setBatchId", String.class);

                method.invoke(_salesMasterRemoteModel, batchId);
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

        if (_salesMasterRemoteModel != null) {
            try {
                Class<?> clazz = _salesMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setBrandId", String.class);

                method.invoke(_salesMasterRemoteModel, brandId);
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

        if (_salesMasterRemoteModel != null) {
            try {
                Class<?> clazz = _salesMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setSalesId", String.class);

                method.invoke(_salesMasterRemoteModel, salesId);
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

        if (_salesMasterRemoteModel != null) {
            try {
                Class<?> clazz = _salesMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCompanyId", String.class);

                method.invoke(_salesMasterRemoteModel, companyId);
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

        if (_salesMasterRemoteModel != null) {
            try {
                Class<?> clazz = _salesMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setOrganizationKey",
                        String.class);

                method.invoke(_salesMasterRemoteModel, organizationKey);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getItemParentNo() {
        return _itemParentNo;
    }

    @Override
    public void setItemParentNo(String itemParentNo) {
        _itemParentNo = itemParentNo;

        if (_salesMasterRemoteModel != null) {
            try {
                Class<?> clazz = _salesMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setItemParentNo", String.class);

                method.invoke(_salesMasterRemoteModel, itemParentNo);
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

        if (_salesMasterRemoteModel != null) {
            try {
                Class<?> clazz = _salesMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setInvoiceNumber", String.class);

                method.invoke(_salesMasterRemoteModel, invoiceNumber);
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

        if (_salesMasterRemoteModel != null) {
            try {
                Class<?> clazz = _salesMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setOrderType", String.class);

                method.invoke(_salesMasterRemoteModel, orderType);
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

        if (_salesMasterRemoteModel != null) {
            try {
                Class<?> clazz = _salesMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setRecordLockStatus",
                        boolean.class);

                method.invoke(_salesMasterRemoteModel, recordLockStatus);
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

        if (_salesMasterRemoteModel != null) {
            try {
                Class<?> clazz = _salesMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setDivisionId", String.class);

                method.invoke(_salesMasterRemoteModel, divisionId);
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

        if (_salesMasterRemoteModel != null) {
            try {
                Class<?> clazz = _salesMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setInvoiceLineNumber",
                        String.class);

                method.invoke(_salesMasterRemoteModel, invoiceLineNumber);
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

        if (_salesMasterRemoteModel != null) {
            try {
                Class<?> clazz = _salesMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setOrderNumber", String.class);

                method.invoke(_salesMasterRemoteModel, orderNumber);
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

        if (_salesMasterRemoteModel != null) {
            try {
                Class<?> clazz = _salesMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setInboundStatus", String.class);

                method.invoke(_salesMasterRemoteModel, inboundStatus);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getSalesMasterRemoteModel() {
        return _salesMasterRemoteModel;
    }

    public void setSalesMasterRemoteModel(BaseModel<?> salesMasterRemoteModel) {
        _salesMasterRemoteModel = salesMasterRemoteModel;
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

        Class<?> remoteModelClass = _salesMasterRemoteModel.getClass();

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

        Object returnValue = method.invoke(_salesMasterRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            SalesMasterLocalServiceUtil.addSalesMaster(this);
        } else {
            SalesMasterLocalServiceUtil.updateSalesMaster(this);
        }
    }

    @Override
    public SalesMaster toEscapedModel() {
        return (SalesMaster) ProxyUtil.newProxyInstance(SalesMaster.class.getClassLoader(),
            new Class[] { SalesMaster.class }, new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        SalesMasterClp clone = new SalesMasterClp();

        clone.setItemNo(getItemNo());
        clone.setRecordSequence(getRecordSequence());
        clone.setQuantity(getQuantity());
        clone.setAccountId(getAccountId());
        clone.setCreatedDate(getCreatedDate());
        clone.setIdentifierCodeQualifier(getIdentifierCodeQualifier());
        clone.setIsActive(getIsActive());
        clone.setMarketId(getMarketId());
        clone.setInvoiceDate(getInvoiceDate());
        clone.setAccountName(getAccountName());
        clone.setDocType(getDocType());
        clone.setOrderReceivedDate(getOrderReceivedDate());
        clone.setAmount(getAmount());
        clone.setSalesMasterSid(getSalesMasterSid());
        clone.setOrderSubtype(getOrderSubtype());
        clone.setCreatedBy(getCreatedBy());
        clone.setPrice(getPrice());
        clone.setUploadDate(getUploadDate());
        clone.setItemId(getItemId());
        clone.setPriceAdjustmentName(getPriceAdjustmentName());
        clone.setItemCodeQualifier(getItemCodeQualifier());
        clone.setContractId(getContractId());
        clone.setItemUom(getItemUom());
        clone.setModifiedDate(getModifiedDate());
        clone.setCustomerSubtype(getCustomerSubtype());
        clone.setProvisionId(getProvisionId());
        clone.setWholesaleOwnerId(getWholesaleOwnerId());
        clone.setSource(getSource());
        clone.setAccountNo(getAccountNo());
        clone.setLotNo(getLotNo());
        clone.setParentItemId(getParentItemId());
        clone.setCustomerCompanyCode(getCustomerCompanyCode());
        clone.setAnalysisCode(getAnalysisCode());
        clone.setAccountType(getAccountType());
        clone.setModifiedBy(getModifiedBy());
        clone.setContractNo(getContractNo());
        clone.setBatchId(getBatchId());
        clone.setBrandId(getBrandId());
        clone.setSalesId(getSalesId());
        clone.setCompanyId(getCompanyId());
        clone.setOrganizationKey(getOrganizationKey());
        clone.setItemParentNo(getItemParentNo());
        clone.setInvoiceNumber(getInvoiceNumber());
        clone.setOrderType(getOrderType());
        clone.setRecordLockStatus(getRecordLockStatus());
        clone.setDivisionId(getDivisionId());
        clone.setInvoiceLineNumber(getInvoiceLineNumber());
        clone.setOrderNumber(getOrderNumber());
        clone.setInboundStatus(getInboundStatus());

        return clone;
    }

    @Override
    public int compareTo(SalesMaster salesMaster) {
        int primaryKey = salesMaster.getPrimaryKey();

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

        if (!(obj instanceof SalesMasterClp)) {
            return false;
        }

        SalesMasterClp salesMaster = (SalesMasterClp) obj;

        int primaryKey = salesMaster.getPrimaryKey();

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
        StringBundler sb = new StringBundler(99);

        sb.append("{itemNo=");
        sb.append(getItemNo());
        sb.append(", recordSequence=");
        sb.append(getRecordSequence());
        sb.append(", quantity=");
        sb.append(getQuantity());
        sb.append(", accountId=");
        sb.append(getAccountId());
        sb.append(", createdDate=");
        sb.append(getCreatedDate());
        sb.append(", identifierCodeQualifier=");
        sb.append(getIdentifierCodeQualifier());
        sb.append(", isActive=");
        sb.append(getIsActive());
        sb.append(", marketId=");
        sb.append(getMarketId());
        sb.append(", invoiceDate=");
        sb.append(getInvoiceDate());
        sb.append(", accountName=");
        sb.append(getAccountName());
        sb.append(", docType=");
        sb.append(getDocType());
        sb.append(", orderReceivedDate=");
        sb.append(getOrderReceivedDate());
        sb.append(", amount=");
        sb.append(getAmount());
        sb.append(", salesMasterSid=");
        sb.append(getSalesMasterSid());
        sb.append(", orderSubtype=");
        sb.append(getOrderSubtype());
        sb.append(", createdBy=");
        sb.append(getCreatedBy());
        sb.append(", price=");
        sb.append(getPrice());
        sb.append(", uploadDate=");
        sb.append(getUploadDate());
        sb.append(", itemId=");
        sb.append(getItemId());
        sb.append(", priceAdjustmentName=");
        sb.append(getPriceAdjustmentName());
        sb.append(", itemCodeQualifier=");
        sb.append(getItemCodeQualifier());
        sb.append(", contractId=");
        sb.append(getContractId());
        sb.append(", itemUom=");
        sb.append(getItemUom());
        sb.append(", modifiedDate=");
        sb.append(getModifiedDate());
        sb.append(", customerSubtype=");
        sb.append(getCustomerSubtype());
        sb.append(", provisionId=");
        sb.append(getProvisionId());
        sb.append(", wholesaleOwnerId=");
        sb.append(getWholesaleOwnerId());
        sb.append(", source=");
        sb.append(getSource());
        sb.append(", accountNo=");
        sb.append(getAccountNo());
        sb.append(", lotNo=");
        sb.append(getLotNo());
        sb.append(", parentItemId=");
        sb.append(getParentItemId());
        sb.append(", customerCompanyCode=");
        sb.append(getCustomerCompanyCode());
        sb.append(", analysisCode=");
        sb.append(getAnalysisCode());
        sb.append(", accountType=");
        sb.append(getAccountType());
        sb.append(", modifiedBy=");
        sb.append(getModifiedBy());
        sb.append(", contractNo=");
        sb.append(getContractNo());
        sb.append(", batchId=");
        sb.append(getBatchId());
        sb.append(", brandId=");
        sb.append(getBrandId());
        sb.append(", salesId=");
        sb.append(getSalesId());
        sb.append(", companyId=");
        sb.append(getCompanyId());
        sb.append(", organizationKey=");
        sb.append(getOrganizationKey());
        sb.append(", itemParentNo=");
        sb.append(getItemParentNo());
        sb.append(", invoiceNumber=");
        sb.append(getInvoiceNumber());
        sb.append(", orderType=");
        sb.append(getOrderType());
        sb.append(", recordLockStatus=");
        sb.append(getRecordLockStatus());
        sb.append(", divisionId=");
        sb.append(getDivisionId());
        sb.append(", invoiceLineNumber=");
        sb.append(getInvoiceLineNumber());
        sb.append(", orderNumber=");
        sb.append(getOrderNumber());
        sb.append(", inboundStatus=");
        sb.append(getInboundStatus());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(151);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.SalesMaster");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>itemNo</column-name><column-value><![CDATA[");
        sb.append(getItemNo());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>recordSequence</column-name><column-value><![CDATA[");
        sb.append(getRecordSequence());
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
            "<column><column-name>identifierCodeQualifier</column-name><column-value><![CDATA[");
        sb.append(getIdentifierCodeQualifier());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>isActive</column-name><column-value><![CDATA[");
        sb.append(getIsActive());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>marketId</column-name><column-value><![CDATA[");
        sb.append(getMarketId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>invoiceDate</column-name><column-value><![CDATA[");
        sb.append(getInvoiceDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>accountName</column-name><column-value><![CDATA[");
        sb.append(getAccountName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>docType</column-name><column-value><![CDATA[");
        sb.append(getDocType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>orderReceivedDate</column-name><column-value><![CDATA[");
        sb.append(getOrderReceivedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>amount</column-name><column-value><![CDATA[");
        sb.append(getAmount());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>salesMasterSid</column-name><column-value><![CDATA[");
        sb.append(getSalesMasterSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>orderSubtype</column-name><column-value><![CDATA[");
        sb.append(getOrderSubtype());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>createdBy</column-name><column-value><![CDATA[");
        sb.append(getCreatedBy());
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
            "<column><column-name>itemId</column-name><column-value><![CDATA[");
        sb.append(getItemId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>priceAdjustmentName</column-name><column-value><![CDATA[");
        sb.append(getPriceAdjustmentName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemCodeQualifier</column-name><column-value><![CDATA[");
        sb.append(getItemCodeQualifier());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>contractId</column-name><column-value><![CDATA[");
        sb.append(getContractId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemUom</column-name><column-value><![CDATA[");
        sb.append(getItemUom());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
        sb.append(getModifiedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>customerSubtype</column-name><column-value><![CDATA[");
        sb.append(getCustomerSubtype());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>provisionId</column-name><column-value><![CDATA[");
        sb.append(getProvisionId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>wholesaleOwnerId</column-name><column-value><![CDATA[");
        sb.append(getWholesaleOwnerId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>source</column-name><column-value><![CDATA[");
        sb.append(getSource());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>accountNo</column-name><column-value><![CDATA[");
        sb.append(getAccountNo());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>lotNo</column-name><column-value><![CDATA[");
        sb.append(getLotNo());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>parentItemId</column-name><column-value><![CDATA[");
        sb.append(getParentItemId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>customerCompanyCode</column-name><column-value><![CDATA[");
        sb.append(getCustomerCompanyCode());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>analysisCode</column-name><column-value><![CDATA[");
        sb.append(getAnalysisCode());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>accountType</column-name><column-value><![CDATA[");
        sb.append(getAccountType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedBy</column-name><column-value><![CDATA[");
        sb.append(getModifiedBy());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>contractNo</column-name><column-value><![CDATA[");
        sb.append(getContractNo());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>batchId</column-name><column-value><![CDATA[");
        sb.append(getBatchId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>brandId</column-name><column-value><![CDATA[");
        sb.append(getBrandId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>salesId</column-name><column-value><![CDATA[");
        sb.append(getSalesId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>companyId</column-name><column-value><![CDATA[");
        sb.append(getCompanyId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>organizationKey</column-name><column-value><![CDATA[");
        sb.append(getOrganizationKey());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemParentNo</column-name><column-value><![CDATA[");
        sb.append(getItemParentNo());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>invoiceNumber</column-name><column-value><![CDATA[");
        sb.append(getInvoiceNumber());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>orderType</column-name><column-value><![CDATA[");
        sb.append(getOrderType());
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
            "<column><column-name>invoiceLineNumber</column-name><column-value><![CDATA[");
        sb.append(getInvoiceLineNumber());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>orderNumber</column-name><column-value><![CDATA[");
        sb.append(getOrderNumber());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>inboundStatus</column-name><column-value><![CDATA[");
        sb.append(getInboundStatus());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
