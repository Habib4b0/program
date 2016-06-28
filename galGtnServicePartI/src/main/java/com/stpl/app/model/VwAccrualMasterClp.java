package com.stpl.app.model;

import com.stpl.app.service.ClpSerializer;
import com.stpl.app.service.VwAccrualMasterLocalServiceUtil;

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


public class VwAccrualMasterClp extends BaseModelImpl<VwAccrualMaster>
    implements VwAccrualMaster {
    private String _itemQualifier;
    private String _businessUnitQualifier;
    private String _itemNo;
    private String _postingIndicator;
    private Date _createdDate;
    private String _costCenter;
    private String _subLedger;
    private Date _accrualPeriodEd;
    private String _accrualId;
    private String _companyQualifier;
    private String _contractNo;
    private String _batchId;
    private String _itemName;
    private String _brandId;
    private Date _postingDate;
    private String _businessUnitName;
    private String _salesId;
    private double _amount;
    private String _businessUnitNo;
    private String _subLedgerType;
    private String _documentType;
    private String _accuralType;
    private int _createdBy;
    private String _programNo;
    private String _customerId;
    private String _itemId;
    private int _accrualMasterSid;
    private String _contractId;
    private String _contractName;
    private String _glAccount;
    private Date _glDate;
    private String _businessUnitId;
    private String _programType;
    private String _customerName;
    private String _customerNo;
    private String _source;
    private Date _accrualPeriodSd;
    private BaseModel<?> _vwAccrualMasterRemoteModel;

    public VwAccrualMasterClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return VwAccrualMaster.class;
    }

    @Override
    public String getModelClassName() {
        return VwAccrualMaster.class.getName();
    }

    @Override
    public int getPrimaryKey() {
        return _accrualMasterSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setAccrualMasterSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _accrualMasterSid;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Integer) primaryKeyObj).intValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("itemQualifier", getItemQualifier());
        attributes.put("businessUnitQualifier", getBusinessUnitQualifier());
        attributes.put("itemNo", getItemNo());
        attributes.put("postingIndicator", getPostingIndicator());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("costCenter", getCostCenter());
        attributes.put("subLedger", getSubLedger());
        attributes.put("accrualPeriodEd", getAccrualPeriodEd());
        attributes.put("accrualId", getAccrualId());
        attributes.put("companyQualifier", getCompanyQualifier());
        attributes.put("contractNo", getContractNo());
        attributes.put("batchId", getBatchId());
        attributes.put("itemName", getItemName());
        attributes.put("brandId", getBrandId());
        attributes.put("postingDate", getPostingDate());
        attributes.put("businessUnitName", getBusinessUnitName());
        attributes.put("salesId", getSalesId());
        attributes.put("amount", getAmount());
        attributes.put("businessUnitNo", getBusinessUnitNo());
        attributes.put("subLedgerType", getSubLedgerType());
        attributes.put("documentType", getDocumentType());
        attributes.put("accuralType", getAccuralType());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("programNo", getProgramNo());
        attributes.put("customerId", getCustomerId());
        attributes.put("itemId", getItemId());
        attributes.put("accrualMasterSid", getAccrualMasterSid());
        attributes.put("contractId", getContractId());
        attributes.put("contractName", getContractName());
        attributes.put("glAccount", getGlAccount());
        attributes.put("glDate", getGlDate());
        attributes.put("businessUnitId", getBusinessUnitId());
        attributes.put("programType", getProgramType());
        attributes.put("customerName", getCustomerName());
        attributes.put("customerNo", getCustomerNo());
        attributes.put("source", getSource());
        attributes.put("accrualPeriodSd", getAccrualPeriodSd());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        String itemQualifier = (String) attributes.get("itemQualifier");

        if (itemQualifier != null) {
            setItemQualifier(itemQualifier);
        }

        String businessUnitQualifier = (String) attributes.get(
                "businessUnitQualifier");

        if (businessUnitQualifier != null) {
            setBusinessUnitQualifier(businessUnitQualifier);
        }

        String itemNo = (String) attributes.get("itemNo");

        if (itemNo != null) {
            setItemNo(itemNo);
        }

        String postingIndicator = (String) attributes.get("postingIndicator");

        if (postingIndicator != null) {
            setPostingIndicator(postingIndicator);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        String costCenter = (String) attributes.get("costCenter");

        if (costCenter != null) {
            setCostCenter(costCenter);
        }

        String subLedger = (String) attributes.get("subLedger");

        if (subLedger != null) {
            setSubLedger(subLedger);
        }

        Date accrualPeriodEd = (Date) attributes.get("accrualPeriodEd");

        if (accrualPeriodEd != null) {
            setAccrualPeriodEd(accrualPeriodEd);
        }

        String accrualId = (String) attributes.get("accrualId");

        if (accrualId != null) {
            setAccrualId(accrualId);
        }

        String companyQualifier = (String) attributes.get("companyQualifier");

        if (companyQualifier != null) {
            setCompanyQualifier(companyQualifier);
        }

        String contractNo = (String) attributes.get("contractNo");

        if (contractNo != null) {
            setContractNo(contractNo);
        }

        String batchId = (String) attributes.get("batchId");

        if (batchId != null) {
            setBatchId(batchId);
        }

        String itemName = (String) attributes.get("itemName");

        if (itemName != null) {
            setItemName(itemName);
        }

        String brandId = (String) attributes.get("brandId");

        if (brandId != null) {
            setBrandId(brandId);
        }

        Date postingDate = (Date) attributes.get("postingDate");

        if (postingDate != null) {
            setPostingDate(postingDate);
        }

        String businessUnitName = (String) attributes.get("businessUnitName");

        if (businessUnitName != null) {
            setBusinessUnitName(businessUnitName);
        }

        String salesId = (String) attributes.get("salesId");

        if (salesId != null) {
            setSalesId(salesId);
        }

        Double amount = (Double) attributes.get("amount");

        if (amount != null) {
            setAmount(amount);
        }

        String businessUnitNo = (String) attributes.get("businessUnitNo");

        if (businessUnitNo != null) {
            setBusinessUnitNo(businessUnitNo);
        }

        String subLedgerType = (String) attributes.get("subLedgerType");

        if (subLedgerType != null) {
            setSubLedgerType(subLedgerType);
        }

        String documentType = (String) attributes.get("documentType");

        if (documentType != null) {
            setDocumentType(documentType);
        }

        String accuralType = (String) attributes.get("accuralType");

        if (accuralType != null) {
            setAccuralType(accuralType);
        }

        Integer createdBy = (Integer) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        String programNo = (String) attributes.get("programNo");

        if (programNo != null) {
            setProgramNo(programNo);
        }

        String customerId = (String) attributes.get("customerId");

        if (customerId != null) {
            setCustomerId(customerId);
        }

        String itemId = (String) attributes.get("itemId");

        if (itemId != null) {
            setItemId(itemId);
        }

        Integer accrualMasterSid = (Integer) attributes.get("accrualMasterSid");

        if (accrualMasterSid != null) {
            setAccrualMasterSid(accrualMasterSid);
        }

        String contractId = (String) attributes.get("contractId");

        if (contractId != null) {
            setContractId(contractId);
        }

        String contractName = (String) attributes.get("contractName");

        if (contractName != null) {
            setContractName(contractName);
        }

        String glAccount = (String) attributes.get("glAccount");

        if (glAccount != null) {
            setGlAccount(glAccount);
        }

        Date glDate = (Date) attributes.get("glDate");

        if (glDate != null) {
            setGlDate(glDate);
        }

        String businessUnitId = (String) attributes.get("businessUnitId");

        if (businessUnitId != null) {
            setBusinessUnitId(businessUnitId);
        }

        String programType = (String) attributes.get("programType");

        if (programType != null) {
            setProgramType(programType);
        }

        String customerName = (String) attributes.get("customerName");

        if (customerName != null) {
            setCustomerName(customerName);
        }

        String customerNo = (String) attributes.get("customerNo");

        if (customerNo != null) {
            setCustomerNo(customerNo);
        }

        String source = (String) attributes.get("source");

        if (source != null) {
            setSource(source);
        }

        Date accrualPeriodSd = (Date) attributes.get("accrualPeriodSd");

        if (accrualPeriodSd != null) {
            setAccrualPeriodSd(accrualPeriodSd);
        }
    }

    @Override
    public String getItemQualifier() {
        return _itemQualifier;
    }

    @Override
    public void setItemQualifier(String itemQualifier) {
        _itemQualifier = itemQualifier;

        if (_vwAccrualMasterRemoteModel != null) {
            try {
                Class<?> clazz = _vwAccrualMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setItemQualifier", String.class);

                method.invoke(_vwAccrualMasterRemoteModel, itemQualifier);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getBusinessUnitQualifier() {
        return _businessUnitQualifier;
    }

    @Override
    public void setBusinessUnitQualifier(String businessUnitQualifier) {
        _businessUnitQualifier = businessUnitQualifier;

        if (_vwAccrualMasterRemoteModel != null) {
            try {
                Class<?> clazz = _vwAccrualMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setBusinessUnitQualifier",
                        String.class);

                method.invoke(_vwAccrualMasterRemoteModel, businessUnitQualifier);
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

        if (_vwAccrualMasterRemoteModel != null) {
            try {
                Class<?> clazz = _vwAccrualMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setItemNo", String.class);

                method.invoke(_vwAccrualMasterRemoteModel, itemNo);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getPostingIndicator() {
        return _postingIndicator;
    }

    @Override
    public void setPostingIndicator(String postingIndicator) {
        _postingIndicator = postingIndicator;

        if (_vwAccrualMasterRemoteModel != null) {
            try {
                Class<?> clazz = _vwAccrualMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setPostingIndicator",
                        String.class);

                method.invoke(_vwAccrualMasterRemoteModel, postingIndicator);
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

        if (_vwAccrualMasterRemoteModel != null) {
            try {
                Class<?> clazz = _vwAccrualMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedDate", Date.class);

                method.invoke(_vwAccrualMasterRemoteModel, createdDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getCostCenter() {
        return _costCenter;
    }

    @Override
    public void setCostCenter(String costCenter) {
        _costCenter = costCenter;

        if (_vwAccrualMasterRemoteModel != null) {
            try {
                Class<?> clazz = _vwAccrualMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCostCenter", String.class);

                method.invoke(_vwAccrualMasterRemoteModel, costCenter);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getSubLedger() {
        return _subLedger;
    }

    @Override
    public void setSubLedger(String subLedger) {
        _subLedger = subLedger;

        if (_vwAccrualMasterRemoteModel != null) {
            try {
                Class<?> clazz = _vwAccrualMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setSubLedger", String.class);

                method.invoke(_vwAccrualMasterRemoteModel, subLedger);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getAccrualPeriodEd() {
        return _accrualPeriodEd;
    }

    @Override
    public void setAccrualPeriodEd(Date accrualPeriodEd) {
        _accrualPeriodEd = accrualPeriodEd;

        if (_vwAccrualMasterRemoteModel != null) {
            try {
                Class<?> clazz = _vwAccrualMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setAccrualPeriodEd", Date.class);

                method.invoke(_vwAccrualMasterRemoteModel, accrualPeriodEd);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getAccrualId() {
        return _accrualId;
    }

    @Override
    public void setAccrualId(String accrualId) {
        _accrualId = accrualId;

        if (_vwAccrualMasterRemoteModel != null) {
            try {
                Class<?> clazz = _vwAccrualMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setAccrualId", String.class);

                method.invoke(_vwAccrualMasterRemoteModel, accrualId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getCompanyQualifier() {
        return _companyQualifier;
    }

    @Override
    public void setCompanyQualifier(String companyQualifier) {
        _companyQualifier = companyQualifier;

        if (_vwAccrualMasterRemoteModel != null) {
            try {
                Class<?> clazz = _vwAccrualMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCompanyQualifier",
                        String.class);

                method.invoke(_vwAccrualMasterRemoteModel, companyQualifier);
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

        if (_vwAccrualMasterRemoteModel != null) {
            try {
                Class<?> clazz = _vwAccrualMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setContractNo", String.class);

                method.invoke(_vwAccrualMasterRemoteModel, contractNo);
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

        if (_vwAccrualMasterRemoteModel != null) {
            try {
                Class<?> clazz = _vwAccrualMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setBatchId", String.class);

                method.invoke(_vwAccrualMasterRemoteModel, batchId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getItemName() {
        return _itemName;
    }

    @Override
    public void setItemName(String itemName) {
        _itemName = itemName;

        if (_vwAccrualMasterRemoteModel != null) {
            try {
                Class<?> clazz = _vwAccrualMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setItemName", String.class);

                method.invoke(_vwAccrualMasterRemoteModel, itemName);
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

        if (_vwAccrualMasterRemoteModel != null) {
            try {
                Class<?> clazz = _vwAccrualMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setBrandId", String.class);

                method.invoke(_vwAccrualMasterRemoteModel, brandId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getPostingDate() {
        return _postingDate;
    }

    @Override
    public void setPostingDate(Date postingDate) {
        _postingDate = postingDate;

        if (_vwAccrualMasterRemoteModel != null) {
            try {
                Class<?> clazz = _vwAccrualMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setPostingDate", Date.class);

                method.invoke(_vwAccrualMasterRemoteModel, postingDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getBusinessUnitName() {
        return _businessUnitName;
    }

    @Override
    public void setBusinessUnitName(String businessUnitName) {
        _businessUnitName = businessUnitName;

        if (_vwAccrualMasterRemoteModel != null) {
            try {
                Class<?> clazz = _vwAccrualMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setBusinessUnitName",
                        String.class);

                method.invoke(_vwAccrualMasterRemoteModel, businessUnitName);
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

        if (_vwAccrualMasterRemoteModel != null) {
            try {
                Class<?> clazz = _vwAccrualMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setSalesId", String.class);

                method.invoke(_vwAccrualMasterRemoteModel, salesId);
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

        if (_vwAccrualMasterRemoteModel != null) {
            try {
                Class<?> clazz = _vwAccrualMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setAmount", double.class);

                method.invoke(_vwAccrualMasterRemoteModel, amount);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getBusinessUnitNo() {
        return _businessUnitNo;
    }

    @Override
    public void setBusinessUnitNo(String businessUnitNo) {
        _businessUnitNo = businessUnitNo;

        if (_vwAccrualMasterRemoteModel != null) {
            try {
                Class<?> clazz = _vwAccrualMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setBusinessUnitNo",
                        String.class);

                method.invoke(_vwAccrualMasterRemoteModel, businessUnitNo);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getSubLedgerType() {
        return _subLedgerType;
    }

    @Override
    public void setSubLedgerType(String subLedgerType) {
        _subLedgerType = subLedgerType;

        if (_vwAccrualMasterRemoteModel != null) {
            try {
                Class<?> clazz = _vwAccrualMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setSubLedgerType", String.class);

                method.invoke(_vwAccrualMasterRemoteModel, subLedgerType);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getDocumentType() {
        return _documentType;
    }

    @Override
    public void setDocumentType(String documentType) {
        _documentType = documentType;

        if (_vwAccrualMasterRemoteModel != null) {
            try {
                Class<?> clazz = _vwAccrualMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setDocumentType", String.class);

                method.invoke(_vwAccrualMasterRemoteModel, documentType);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getAccuralType() {
        return _accuralType;
    }

    @Override
    public void setAccuralType(String accuralType) {
        _accuralType = accuralType;

        if (_vwAccrualMasterRemoteModel != null) {
            try {
                Class<?> clazz = _vwAccrualMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setAccuralType", String.class);

                method.invoke(_vwAccrualMasterRemoteModel, accuralType);
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

        if (_vwAccrualMasterRemoteModel != null) {
            try {
                Class<?> clazz = _vwAccrualMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedBy", int.class);

                method.invoke(_vwAccrualMasterRemoteModel, createdBy);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getProgramNo() {
        return _programNo;
    }

    @Override
    public void setProgramNo(String programNo) {
        _programNo = programNo;

        if (_vwAccrualMasterRemoteModel != null) {
            try {
                Class<?> clazz = _vwAccrualMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setProgramNo", String.class);

                method.invoke(_vwAccrualMasterRemoteModel, programNo);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getCustomerId() {
        return _customerId;
    }

    @Override
    public void setCustomerId(String customerId) {
        _customerId = customerId;

        if (_vwAccrualMasterRemoteModel != null) {
            try {
                Class<?> clazz = _vwAccrualMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCustomerId", String.class);

                method.invoke(_vwAccrualMasterRemoteModel, customerId);
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

        if (_vwAccrualMasterRemoteModel != null) {
            try {
                Class<?> clazz = _vwAccrualMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setItemId", String.class);

                method.invoke(_vwAccrualMasterRemoteModel, itemId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getAccrualMasterSid() {
        return _accrualMasterSid;
    }

    @Override
    public void setAccrualMasterSid(int accrualMasterSid) {
        _accrualMasterSid = accrualMasterSid;

        if (_vwAccrualMasterRemoteModel != null) {
            try {
                Class<?> clazz = _vwAccrualMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setAccrualMasterSid", int.class);

                method.invoke(_vwAccrualMasterRemoteModel, accrualMasterSid);
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

        if (_vwAccrualMasterRemoteModel != null) {
            try {
                Class<?> clazz = _vwAccrualMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setContractId", String.class);

                method.invoke(_vwAccrualMasterRemoteModel, contractId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getContractName() {
        return _contractName;
    }

    @Override
    public void setContractName(String contractName) {
        _contractName = contractName;

        if (_vwAccrualMasterRemoteModel != null) {
            try {
                Class<?> clazz = _vwAccrualMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setContractName", String.class);

                method.invoke(_vwAccrualMasterRemoteModel, contractName);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getGlAccount() {
        return _glAccount;
    }

    @Override
    public void setGlAccount(String glAccount) {
        _glAccount = glAccount;

        if (_vwAccrualMasterRemoteModel != null) {
            try {
                Class<?> clazz = _vwAccrualMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setGlAccount", String.class);

                method.invoke(_vwAccrualMasterRemoteModel, glAccount);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getGlDate() {
        return _glDate;
    }

    @Override
    public void setGlDate(Date glDate) {
        _glDate = glDate;

        if (_vwAccrualMasterRemoteModel != null) {
            try {
                Class<?> clazz = _vwAccrualMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setGlDate", Date.class);

                method.invoke(_vwAccrualMasterRemoteModel, glDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getBusinessUnitId() {
        return _businessUnitId;
    }

    @Override
    public void setBusinessUnitId(String businessUnitId) {
        _businessUnitId = businessUnitId;

        if (_vwAccrualMasterRemoteModel != null) {
            try {
                Class<?> clazz = _vwAccrualMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setBusinessUnitId",
                        String.class);

                method.invoke(_vwAccrualMasterRemoteModel, businessUnitId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getProgramType() {
        return _programType;
    }

    @Override
    public void setProgramType(String programType) {
        _programType = programType;

        if (_vwAccrualMasterRemoteModel != null) {
            try {
                Class<?> clazz = _vwAccrualMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setProgramType", String.class);

                method.invoke(_vwAccrualMasterRemoteModel, programType);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getCustomerName() {
        return _customerName;
    }

    @Override
    public void setCustomerName(String customerName) {
        _customerName = customerName;

        if (_vwAccrualMasterRemoteModel != null) {
            try {
                Class<?> clazz = _vwAccrualMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCustomerName", String.class);

                method.invoke(_vwAccrualMasterRemoteModel, customerName);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getCustomerNo() {
        return _customerNo;
    }

    @Override
    public void setCustomerNo(String customerNo) {
        _customerNo = customerNo;

        if (_vwAccrualMasterRemoteModel != null) {
            try {
                Class<?> clazz = _vwAccrualMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCustomerNo", String.class);

                method.invoke(_vwAccrualMasterRemoteModel, customerNo);
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

        if (_vwAccrualMasterRemoteModel != null) {
            try {
                Class<?> clazz = _vwAccrualMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setSource", String.class);

                method.invoke(_vwAccrualMasterRemoteModel, source);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getAccrualPeriodSd() {
        return _accrualPeriodSd;
    }

    @Override
    public void setAccrualPeriodSd(Date accrualPeriodSd) {
        _accrualPeriodSd = accrualPeriodSd;

        if (_vwAccrualMasterRemoteModel != null) {
            try {
                Class<?> clazz = _vwAccrualMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setAccrualPeriodSd", Date.class);

                method.invoke(_vwAccrualMasterRemoteModel, accrualPeriodSd);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getVwAccrualMasterRemoteModel() {
        return _vwAccrualMasterRemoteModel;
    }

    public void setVwAccrualMasterRemoteModel(
        BaseModel<?> vwAccrualMasterRemoteModel) {
        _vwAccrualMasterRemoteModel = vwAccrualMasterRemoteModel;
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

        Class<?> remoteModelClass = _vwAccrualMasterRemoteModel.getClass();

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

        Object returnValue = method.invoke(_vwAccrualMasterRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            VwAccrualMasterLocalServiceUtil.addVwAccrualMaster(this);
        } else {
            VwAccrualMasterLocalServiceUtil.updateVwAccrualMaster(this);
        }
    }

    @Override
    public VwAccrualMaster toEscapedModel() {
        return (VwAccrualMaster) ProxyUtil.newProxyInstance(VwAccrualMaster.class.getClassLoader(),
            new Class[] { VwAccrualMaster.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        VwAccrualMasterClp clone = new VwAccrualMasterClp();

        clone.setItemQualifier(getItemQualifier());
        clone.setBusinessUnitQualifier(getBusinessUnitQualifier());
        clone.setItemNo(getItemNo());
        clone.setPostingIndicator(getPostingIndicator());
        clone.setCreatedDate(getCreatedDate());
        clone.setCostCenter(getCostCenter());
        clone.setSubLedger(getSubLedger());
        clone.setAccrualPeriodEd(getAccrualPeriodEd());
        clone.setAccrualId(getAccrualId());
        clone.setCompanyQualifier(getCompanyQualifier());
        clone.setContractNo(getContractNo());
        clone.setBatchId(getBatchId());
        clone.setItemName(getItemName());
        clone.setBrandId(getBrandId());
        clone.setPostingDate(getPostingDate());
        clone.setBusinessUnitName(getBusinessUnitName());
        clone.setSalesId(getSalesId());
        clone.setAmount(getAmount());
        clone.setBusinessUnitNo(getBusinessUnitNo());
        clone.setSubLedgerType(getSubLedgerType());
        clone.setDocumentType(getDocumentType());
        clone.setAccuralType(getAccuralType());
        clone.setCreatedBy(getCreatedBy());
        clone.setProgramNo(getProgramNo());
        clone.setCustomerId(getCustomerId());
        clone.setItemId(getItemId());
        clone.setAccrualMasterSid(getAccrualMasterSid());
        clone.setContractId(getContractId());
        clone.setContractName(getContractName());
        clone.setGlAccount(getGlAccount());
        clone.setGlDate(getGlDate());
        clone.setBusinessUnitId(getBusinessUnitId());
        clone.setProgramType(getProgramType());
        clone.setCustomerName(getCustomerName());
        clone.setCustomerNo(getCustomerNo());
        clone.setSource(getSource());
        clone.setAccrualPeriodSd(getAccrualPeriodSd());

        return clone;
    }

    @Override
    public int compareTo(VwAccrualMaster vwAccrualMaster) {
        int primaryKey = vwAccrualMaster.getPrimaryKey();

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

        if (!(obj instanceof VwAccrualMasterClp)) {
            return false;
        }

        VwAccrualMasterClp vwAccrualMaster = (VwAccrualMasterClp) obj;

        int primaryKey = vwAccrualMaster.getPrimaryKey();

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
        StringBundler sb = new StringBundler(75);

        sb.append("{itemQualifier=");
        sb.append(getItemQualifier());
        sb.append(", businessUnitQualifier=");
        sb.append(getBusinessUnitQualifier());
        sb.append(", itemNo=");
        sb.append(getItemNo());
        sb.append(", postingIndicator=");
        sb.append(getPostingIndicator());
        sb.append(", createdDate=");
        sb.append(getCreatedDate());
        sb.append(", costCenter=");
        sb.append(getCostCenter());
        sb.append(", subLedger=");
        sb.append(getSubLedger());
        sb.append(", accrualPeriodEd=");
        sb.append(getAccrualPeriodEd());
        sb.append(", accrualId=");
        sb.append(getAccrualId());
        sb.append(", companyQualifier=");
        sb.append(getCompanyQualifier());
        sb.append(", contractNo=");
        sb.append(getContractNo());
        sb.append(", batchId=");
        sb.append(getBatchId());
        sb.append(", itemName=");
        sb.append(getItemName());
        sb.append(", brandId=");
        sb.append(getBrandId());
        sb.append(", postingDate=");
        sb.append(getPostingDate());
        sb.append(", businessUnitName=");
        sb.append(getBusinessUnitName());
        sb.append(", salesId=");
        sb.append(getSalesId());
        sb.append(", amount=");
        sb.append(getAmount());
        sb.append(", businessUnitNo=");
        sb.append(getBusinessUnitNo());
        sb.append(", subLedgerType=");
        sb.append(getSubLedgerType());
        sb.append(", documentType=");
        sb.append(getDocumentType());
        sb.append(", accuralType=");
        sb.append(getAccuralType());
        sb.append(", createdBy=");
        sb.append(getCreatedBy());
        sb.append(", programNo=");
        sb.append(getProgramNo());
        sb.append(", customerId=");
        sb.append(getCustomerId());
        sb.append(", itemId=");
        sb.append(getItemId());
        sb.append(", accrualMasterSid=");
        sb.append(getAccrualMasterSid());
        sb.append(", contractId=");
        sb.append(getContractId());
        sb.append(", contractName=");
        sb.append(getContractName());
        sb.append(", glAccount=");
        sb.append(getGlAccount());
        sb.append(", glDate=");
        sb.append(getGlDate());
        sb.append(", businessUnitId=");
        sb.append(getBusinessUnitId());
        sb.append(", programType=");
        sb.append(getProgramType());
        sb.append(", customerName=");
        sb.append(getCustomerName());
        sb.append(", customerNo=");
        sb.append(getCustomerNo());
        sb.append(", source=");
        sb.append(getSource());
        sb.append(", accrualPeriodSd=");
        sb.append(getAccrualPeriodSd());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(115);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.VwAccrualMaster");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>itemQualifier</column-name><column-value><![CDATA[");
        sb.append(getItemQualifier());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>businessUnitQualifier</column-name><column-value><![CDATA[");
        sb.append(getBusinessUnitQualifier());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemNo</column-name><column-value><![CDATA[");
        sb.append(getItemNo());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>postingIndicator</column-name><column-value><![CDATA[");
        sb.append(getPostingIndicator());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>createdDate</column-name><column-value><![CDATA[");
        sb.append(getCreatedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>costCenter</column-name><column-value><![CDATA[");
        sb.append(getCostCenter());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>subLedger</column-name><column-value><![CDATA[");
        sb.append(getSubLedger());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>accrualPeriodEd</column-name><column-value><![CDATA[");
        sb.append(getAccrualPeriodEd());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>accrualId</column-name><column-value><![CDATA[");
        sb.append(getAccrualId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>companyQualifier</column-name><column-value><![CDATA[");
        sb.append(getCompanyQualifier());
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
            "<column><column-name>itemName</column-name><column-value><![CDATA[");
        sb.append(getItemName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>brandId</column-name><column-value><![CDATA[");
        sb.append(getBrandId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>postingDate</column-name><column-value><![CDATA[");
        sb.append(getPostingDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>businessUnitName</column-name><column-value><![CDATA[");
        sb.append(getBusinessUnitName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>salesId</column-name><column-value><![CDATA[");
        sb.append(getSalesId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>amount</column-name><column-value><![CDATA[");
        sb.append(getAmount());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>businessUnitNo</column-name><column-value><![CDATA[");
        sb.append(getBusinessUnitNo());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>subLedgerType</column-name><column-value><![CDATA[");
        sb.append(getSubLedgerType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>documentType</column-name><column-value><![CDATA[");
        sb.append(getDocumentType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>accuralType</column-name><column-value><![CDATA[");
        sb.append(getAccuralType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>createdBy</column-name><column-value><![CDATA[");
        sb.append(getCreatedBy());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>programNo</column-name><column-value><![CDATA[");
        sb.append(getProgramNo());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>customerId</column-name><column-value><![CDATA[");
        sb.append(getCustomerId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemId</column-name><column-value><![CDATA[");
        sb.append(getItemId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>accrualMasterSid</column-name><column-value><![CDATA[");
        sb.append(getAccrualMasterSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>contractId</column-name><column-value><![CDATA[");
        sb.append(getContractId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>contractName</column-name><column-value><![CDATA[");
        sb.append(getContractName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>glAccount</column-name><column-value><![CDATA[");
        sb.append(getGlAccount());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>glDate</column-name><column-value><![CDATA[");
        sb.append(getGlDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>businessUnitId</column-name><column-value><![CDATA[");
        sb.append(getBusinessUnitId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>programType</column-name><column-value><![CDATA[");
        sb.append(getProgramType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>customerName</column-name><column-value><![CDATA[");
        sb.append(getCustomerName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>customerNo</column-name><column-value><![CDATA[");
        sb.append(getCustomerNo());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>source</column-name><column-value><![CDATA[");
        sb.append(getSource());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>accrualPeriodSd</column-name><column-value><![CDATA[");
        sb.append(getAccrualPeriodSd());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}