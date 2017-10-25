package com.stpl.app.parttwo.model;

import com.stpl.app.parttwo.service.ClpSerializer;
import com.stpl.app.parttwo.service.StCffOutboundMasterLocalServiceUtil;
import com.stpl.app.parttwo.service.persistence.StCffOutboundMasterPK;

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


public class StCffOutboundMasterClp extends BaseModelImpl<StCffOutboundMaster>
    implements StCffOutboundMaster {
    private boolean _etlCheckRecord;
    private String _customerName;
    private String _contractHolderId;
    private String _businessUnitNo;
    private String _year;
    private Date _financialForecastApprovalDate;
    private String _deductionId;
    private Date _modifiedDate;
    private double _deductionPerUnit;
    private double _cogsPerUnit;
    private String _contractType;
    private String _source;
    private String _businessUnitName;
    private int _contractMasterSid;
    private String _financialForecastId;
    private String _projectId;
    private String _customerNo;
    private String _modifiedBy;
    private double _salesDollars;
    private int _month;
    private int _cffDetailsSid;
    private String _type;
    private String _deductionType;
    private int _companyMasterSid;
    private boolean _checkRecord;
    private String _contractName;
    private double _deductionRate;
    private String _deductionCategory;
    private double _cogsAmount;
    private String _deductionNo;
    private Date _financialForecastCreationDate;
    private String _companyNo;
    private double _salesUnits;
    private String _sessionId;
    private String _itemName;
    private String _deductionInclusion;
    private int _rsModelSid;
    private String _contractHolderName;
    private int _itemMasterSid;
    private String _companyName;
    private String _customerId;
    private String _itemId;
    private double _netProfitDollars;
    private int _glCompanyMasterSid;
    private Date _createdDate;
    private String _createdBy;
    private String _deductionCategory1;
    private String _deductionCategory2;
    private String _contractHolderNo;
    private String _deductionCategory3;
    private String _itemNo;
    private String _deductionCategory4;
    private String _deductionCategory5;
    private String _deductionCategory6;
    private String _contractId;
    private String _deductionProgram;
    private String _businessUnitId;
    private String _projectionName;
    private String _userId;
    private String _companyId;
    private String _outboundStatus;
    private String _originalBatchId;
    private String _deductionName;
    private double _netProfitPerUnit;
    private int _periodSid;
    private String _salesInclusion;
    private String _batchId;
    private String _financialForecastName;
    private double _netSalesDollar;
    private double _deductionDollars;
    private String _contractNo;
    private BaseModel<?> _stCffOutboundMasterRemoteModel;

    public StCffOutboundMasterClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return StCffOutboundMaster.class;
    }

    @Override
    public String getModelClassName() {
        return StCffOutboundMaster.class.getName();
    }

    @Override
    public StCffOutboundMasterPK getPrimaryKey() {
        return new StCffOutboundMasterPK(_cffDetailsSid, _sessionId,
            _rsModelSid, _userId, _periodSid);
    }

    @Override
    public void setPrimaryKey(StCffOutboundMasterPK primaryKey) {
        setCffDetailsSid(primaryKey.cffDetailsSid);
        setSessionId(primaryKey.sessionId);
        setRsModelSid(primaryKey.rsModelSid);
        setUserId(primaryKey.userId);
        setPeriodSid(primaryKey.periodSid);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return new StCffOutboundMasterPK(_cffDetailsSid, _sessionId,
            _rsModelSid, _userId, _periodSid);
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey((StCffOutboundMasterPK) primaryKeyObj);
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("etlCheckRecord", getEtlCheckRecord());
        attributes.put("customerName", getCustomerName());
        attributes.put("contractHolderId", getContractHolderId());
        attributes.put("businessUnitNo", getBusinessUnitNo());
        attributes.put("year", getYear());
        attributes.put("financialForecastApprovalDate",
            getFinancialForecastApprovalDate());
        attributes.put("deductionId", getDeductionId());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("deductionPerUnit", getDeductionPerUnit());
        attributes.put("cogsPerUnit", getCogsPerUnit());
        attributes.put("contractType", getContractType());
        attributes.put("source", getSource());
        attributes.put("businessUnitName", getBusinessUnitName());
        attributes.put("contractMasterSid", getContractMasterSid());
        attributes.put("financialForecastId", getFinancialForecastId());
        attributes.put("projectId", getProjectId());
        attributes.put("customerNo", getCustomerNo());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("salesDollars", getSalesDollars());
        attributes.put("month", getMonth());
        attributes.put("cffDetailsSid", getCffDetailsSid());
        attributes.put("type", getType());
        attributes.put("deductionType", getDeductionType());
        attributes.put("companyMasterSid", getCompanyMasterSid());
        attributes.put("checkRecord", getCheckRecord());
        attributes.put("contractName", getContractName());
        attributes.put("deductionRate", getDeductionRate());
        attributes.put("deductionCategory", getDeductionCategory());
        attributes.put("cogsAmount", getCogsAmount());
        attributes.put("deductionNo", getDeductionNo());
        attributes.put("financialForecastCreationDate",
            getFinancialForecastCreationDate());
        attributes.put("companyNo", getCompanyNo());
        attributes.put("salesUnits", getSalesUnits());
        attributes.put("sessionId", getSessionId());
        attributes.put("itemName", getItemName());
        attributes.put("deductionInclusion", getDeductionInclusion());
        attributes.put("rsModelSid", getRsModelSid());
        attributes.put("contractHolderName", getContractHolderName());
        attributes.put("itemMasterSid", getItemMasterSid());
        attributes.put("companyName", getCompanyName());
        attributes.put("customerId", getCustomerId());
        attributes.put("itemId", getItemId());
        attributes.put("netProfitDollars", getNetProfitDollars());
        attributes.put("glCompanyMasterSid", getGlCompanyMasterSid());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("deductionCategory1", getDeductionCategory1());
        attributes.put("deductionCategory2", getDeductionCategory2());
        attributes.put("contractHolderNo", getContractHolderNo());
        attributes.put("deductionCategory3", getDeductionCategory3());
        attributes.put("itemNo", getItemNo());
        attributes.put("deductionCategory4", getDeductionCategory4());
        attributes.put("deductionCategory5", getDeductionCategory5());
        attributes.put("deductionCategory6", getDeductionCategory6());
        attributes.put("contractId", getContractId());
        attributes.put("deductionProgram", getDeductionProgram());
        attributes.put("businessUnitId", getBusinessUnitId());
        attributes.put("projectionName", getProjectionName());
        attributes.put("userId", getUserId());
        attributes.put("companyId", getCompanyId());
        attributes.put("outboundStatus", getOutboundStatus());
        attributes.put("originalBatchId", getOriginalBatchId());
        attributes.put("deductionName", getDeductionName());
        attributes.put("netProfitPerUnit", getNetProfitPerUnit());
        attributes.put("periodSid", getPeriodSid());
        attributes.put("salesInclusion", getSalesInclusion());
        attributes.put("batchId", getBatchId());
        attributes.put("financialForecastName", getFinancialForecastName());
        attributes.put("netSalesDollar", getNetSalesDollar());
        attributes.put("deductionDollars", getDeductionDollars());
        attributes.put("contractNo", getContractNo());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Boolean etlCheckRecord = (Boolean) attributes.get("etlCheckRecord");

        if (etlCheckRecord != null) {
            setEtlCheckRecord(etlCheckRecord);
        }

        String customerName = (String) attributes.get("customerName");

        if (customerName != null) {
            setCustomerName(customerName);
        }

        String contractHolderId = (String) attributes.get("contractHolderId");

        if (contractHolderId != null) {
            setContractHolderId(contractHolderId);
        }

        String businessUnitNo = (String) attributes.get("businessUnitNo");

        if (businessUnitNo != null) {
            setBusinessUnitNo(businessUnitNo);
        }

        String year = (String) attributes.get("year");

        if (year != null) {
            setYear(year);
        }

        Date financialForecastApprovalDate = (Date) attributes.get(
                "financialForecastApprovalDate");

        if (financialForecastApprovalDate != null) {
            setFinancialForecastApprovalDate(financialForecastApprovalDate);
        }

        String deductionId = (String) attributes.get("deductionId");

        if (deductionId != null) {
            setDeductionId(deductionId);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        Double deductionPerUnit = (Double) attributes.get("deductionPerUnit");

        if (deductionPerUnit != null) {
            setDeductionPerUnit(deductionPerUnit);
        }

        Double cogsPerUnit = (Double) attributes.get("cogsPerUnit");

        if (cogsPerUnit != null) {
            setCogsPerUnit(cogsPerUnit);
        }

        String contractType = (String) attributes.get("contractType");

        if (contractType != null) {
            setContractType(contractType);
        }

        String source = (String) attributes.get("source");

        if (source != null) {
            setSource(source);
        }

        String businessUnitName = (String) attributes.get("businessUnitName");

        if (businessUnitName != null) {
            setBusinessUnitName(businessUnitName);
        }

        Integer contractMasterSid = (Integer) attributes.get(
                "contractMasterSid");

        if (contractMasterSid != null) {
            setContractMasterSid(contractMasterSid);
        }

        String financialForecastId = (String) attributes.get(
                "financialForecastId");

        if (financialForecastId != null) {
            setFinancialForecastId(financialForecastId);
        }

        String projectId = (String) attributes.get("projectId");

        if (projectId != null) {
            setProjectId(projectId);
        }

        String customerNo = (String) attributes.get("customerNo");

        if (customerNo != null) {
            setCustomerNo(customerNo);
        }

        String modifiedBy = (String) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        Double salesDollars = (Double) attributes.get("salesDollars");

        if (salesDollars != null) {
            setSalesDollars(salesDollars);
        }

        Integer month = (Integer) attributes.get("month");

        if (month != null) {
            setMonth(month);
        }

        Integer cffDetailsSid = (Integer) attributes.get("cffDetailsSid");

        if (cffDetailsSid != null) {
            setCffDetailsSid(cffDetailsSid);
        }

        String type = (String) attributes.get("type");

        if (type != null) {
            setType(type);
        }

        String deductionType = (String) attributes.get("deductionType");

        if (deductionType != null) {
            setDeductionType(deductionType);
        }

        Integer companyMasterSid = (Integer) attributes.get("companyMasterSid");

        if (companyMasterSid != null) {
            setCompanyMasterSid(companyMasterSid);
        }

        Boolean checkRecord = (Boolean) attributes.get("checkRecord");

        if (checkRecord != null) {
            setCheckRecord(checkRecord);
        }

        String contractName = (String) attributes.get("contractName");

        if (contractName != null) {
            setContractName(contractName);
        }

        Double deductionRate = (Double) attributes.get("deductionRate");

        if (deductionRate != null) {
            setDeductionRate(deductionRate);
        }

        String deductionCategory = (String) attributes.get("deductionCategory");

        if (deductionCategory != null) {
            setDeductionCategory(deductionCategory);
        }

        Double cogsAmount = (Double) attributes.get("cogsAmount");

        if (cogsAmount != null) {
            setCogsAmount(cogsAmount);
        }

        String deductionNo = (String) attributes.get("deductionNo");

        if (deductionNo != null) {
            setDeductionNo(deductionNo);
        }

        Date financialForecastCreationDate = (Date) attributes.get(
                "financialForecastCreationDate");

        if (financialForecastCreationDate != null) {
            setFinancialForecastCreationDate(financialForecastCreationDate);
        }

        String companyNo = (String) attributes.get("companyNo");

        if (companyNo != null) {
            setCompanyNo(companyNo);
        }

        Double salesUnits = (Double) attributes.get("salesUnits");

        if (salesUnits != null) {
            setSalesUnits(salesUnits);
        }

        String sessionId = (String) attributes.get("sessionId");

        if (sessionId != null) {
            setSessionId(sessionId);
        }

        String itemName = (String) attributes.get("itemName");

        if (itemName != null) {
            setItemName(itemName);
        }

        String deductionInclusion = (String) attributes.get(
                "deductionInclusion");

        if (deductionInclusion != null) {
            setDeductionInclusion(deductionInclusion);
        }

        Integer rsModelSid = (Integer) attributes.get("rsModelSid");

        if (rsModelSid != null) {
            setRsModelSid(rsModelSid);
        }

        String contractHolderName = (String) attributes.get(
                "contractHolderName");

        if (contractHolderName != null) {
            setContractHolderName(contractHolderName);
        }

        Integer itemMasterSid = (Integer) attributes.get("itemMasterSid");

        if (itemMasterSid != null) {
            setItemMasterSid(itemMasterSid);
        }

        String companyName = (String) attributes.get("companyName");

        if (companyName != null) {
            setCompanyName(companyName);
        }

        String customerId = (String) attributes.get("customerId");

        if (customerId != null) {
            setCustomerId(customerId);
        }

        String itemId = (String) attributes.get("itemId");

        if (itemId != null) {
            setItemId(itemId);
        }

        Double netProfitDollars = (Double) attributes.get("netProfitDollars");

        if (netProfitDollars != null) {
            setNetProfitDollars(netProfitDollars);
        }

        Integer glCompanyMasterSid = (Integer) attributes.get(
                "glCompanyMasterSid");

        if (glCompanyMasterSid != null) {
            setGlCompanyMasterSid(glCompanyMasterSid);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        String createdBy = (String) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        String deductionCategory1 = (String) attributes.get(
                "deductionCategory1");

        if (deductionCategory1 != null) {
            setDeductionCategory1(deductionCategory1);
        }

        String deductionCategory2 = (String) attributes.get(
                "deductionCategory2");

        if (deductionCategory2 != null) {
            setDeductionCategory2(deductionCategory2);
        }

        String contractHolderNo = (String) attributes.get("contractHolderNo");

        if (contractHolderNo != null) {
            setContractHolderNo(contractHolderNo);
        }

        String deductionCategory3 = (String) attributes.get(
                "deductionCategory3");

        if (deductionCategory3 != null) {
            setDeductionCategory3(deductionCategory3);
        }

        String itemNo = (String) attributes.get("itemNo");

        if (itemNo != null) {
            setItemNo(itemNo);
        }

        String deductionCategory4 = (String) attributes.get(
                "deductionCategory4");

        if (deductionCategory4 != null) {
            setDeductionCategory4(deductionCategory4);
        }

        String deductionCategory5 = (String) attributes.get(
                "deductionCategory5");

        if (deductionCategory5 != null) {
            setDeductionCategory5(deductionCategory5);
        }

        String deductionCategory6 = (String) attributes.get(
                "deductionCategory6");

        if (deductionCategory6 != null) {
            setDeductionCategory6(deductionCategory6);
        }

        String contractId = (String) attributes.get("contractId");

        if (contractId != null) {
            setContractId(contractId);
        }

        String deductionProgram = (String) attributes.get("deductionProgram");

        if (deductionProgram != null) {
            setDeductionProgram(deductionProgram);
        }

        String businessUnitId = (String) attributes.get("businessUnitId");

        if (businessUnitId != null) {
            setBusinessUnitId(businessUnitId);
        }

        String projectionName = (String) attributes.get("projectionName");

        if (projectionName != null) {
            setProjectionName(projectionName);
        }

        String userId = (String) attributes.get("userId");

        if (userId != null) {
            setUserId(userId);
        }

        String companyId = (String) attributes.get("companyId");

        if (companyId != null) {
            setCompanyId(companyId);
        }

        String outboundStatus = (String) attributes.get("outboundStatus");

        if (outboundStatus != null) {
            setOutboundStatus(outboundStatus);
        }

        String originalBatchId = (String) attributes.get("originalBatchId");

        if (originalBatchId != null) {
            setOriginalBatchId(originalBatchId);
        }

        String deductionName = (String) attributes.get("deductionName");

        if (deductionName != null) {
            setDeductionName(deductionName);
        }

        Double netProfitPerUnit = (Double) attributes.get("netProfitPerUnit");

        if (netProfitPerUnit != null) {
            setNetProfitPerUnit(netProfitPerUnit);
        }

        Integer periodSid = (Integer) attributes.get("periodSid");

        if (periodSid != null) {
            setPeriodSid(periodSid);
        }

        String salesInclusion = (String) attributes.get("salesInclusion");

        if (salesInclusion != null) {
            setSalesInclusion(salesInclusion);
        }

        String batchId = (String) attributes.get("batchId");

        if (batchId != null) {
            setBatchId(batchId);
        }

        String financialForecastName = (String) attributes.get(
                "financialForecastName");

        if (financialForecastName != null) {
            setFinancialForecastName(financialForecastName);
        }

        Double netSalesDollar = (Double) attributes.get("netSalesDollar");

        if (netSalesDollar != null) {
            setNetSalesDollar(netSalesDollar);
        }

        Double deductionDollars = (Double) attributes.get("deductionDollars");

        if (deductionDollars != null) {
            setDeductionDollars(deductionDollars);
        }

        String contractNo = (String) attributes.get("contractNo");

        if (contractNo != null) {
            setContractNo(contractNo);
        }
    }

    @Override
    public boolean getEtlCheckRecord() {
        return _etlCheckRecord;
    }

    @Override
    public boolean isEtlCheckRecord() {
        return _etlCheckRecord;
    }

    @Override
    public void setEtlCheckRecord(boolean etlCheckRecord) {
        _etlCheckRecord = etlCheckRecord;

        if (_stCffOutboundMasterRemoteModel != null) {
            try {
                Class<?> clazz = _stCffOutboundMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setEtlCheckRecord",
                        boolean.class);

                method.invoke(_stCffOutboundMasterRemoteModel, etlCheckRecord);
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

        if (_stCffOutboundMasterRemoteModel != null) {
            try {
                Class<?> clazz = _stCffOutboundMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCustomerName", String.class);

                method.invoke(_stCffOutboundMasterRemoteModel, customerName);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getContractHolderId() {
        return _contractHolderId;
    }

    @Override
    public void setContractHolderId(String contractHolderId) {
        _contractHolderId = contractHolderId;

        if (_stCffOutboundMasterRemoteModel != null) {
            try {
                Class<?> clazz = _stCffOutboundMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setContractHolderId",
                        String.class);

                method.invoke(_stCffOutboundMasterRemoteModel, contractHolderId);
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

        if (_stCffOutboundMasterRemoteModel != null) {
            try {
                Class<?> clazz = _stCffOutboundMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setBusinessUnitNo",
                        String.class);

                method.invoke(_stCffOutboundMasterRemoteModel, businessUnitNo);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getYear() {
        return _year;
    }

    @Override
    public void setYear(String year) {
        _year = year;

        if (_stCffOutboundMasterRemoteModel != null) {
            try {
                Class<?> clazz = _stCffOutboundMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setYear", String.class);

                method.invoke(_stCffOutboundMasterRemoteModel, year);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getFinancialForecastApprovalDate() {
        return _financialForecastApprovalDate;
    }

    @Override
    public void setFinancialForecastApprovalDate(
        Date financialForecastApprovalDate) {
        _financialForecastApprovalDate = financialForecastApprovalDate;

        if (_stCffOutboundMasterRemoteModel != null) {
            try {
                Class<?> clazz = _stCffOutboundMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setFinancialForecastApprovalDate",
                        Date.class);

                method.invoke(_stCffOutboundMasterRemoteModel,
                    financialForecastApprovalDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getDeductionId() {
        return _deductionId;
    }

    @Override
    public void setDeductionId(String deductionId) {
        _deductionId = deductionId;

        if (_stCffOutboundMasterRemoteModel != null) {
            try {
                Class<?> clazz = _stCffOutboundMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setDeductionId", String.class);

                method.invoke(_stCffOutboundMasterRemoteModel, deductionId);
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

        if (_stCffOutboundMasterRemoteModel != null) {
            try {
                Class<?> clazz = _stCffOutboundMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedDate", Date.class);

                method.invoke(_stCffOutboundMasterRemoteModel, modifiedDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getDeductionPerUnit() {
        return _deductionPerUnit;
    }

    @Override
    public void setDeductionPerUnit(double deductionPerUnit) {
        _deductionPerUnit = deductionPerUnit;

        if (_stCffOutboundMasterRemoteModel != null) {
            try {
                Class<?> clazz = _stCffOutboundMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setDeductionPerUnit",
                        double.class);

                method.invoke(_stCffOutboundMasterRemoteModel, deductionPerUnit);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getCogsPerUnit() {
        return _cogsPerUnit;
    }

    @Override
    public void setCogsPerUnit(double cogsPerUnit) {
        _cogsPerUnit = cogsPerUnit;

        if (_stCffOutboundMasterRemoteModel != null) {
            try {
                Class<?> clazz = _stCffOutboundMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCogsPerUnit", double.class);

                method.invoke(_stCffOutboundMasterRemoteModel, cogsPerUnit);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getContractType() {
        return _contractType;
    }

    @Override
    public void setContractType(String contractType) {
        _contractType = contractType;

        if (_stCffOutboundMasterRemoteModel != null) {
            try {
                Class<?> clazz = _stCffOutboundMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setContractType", String.class);

                method.invoke(_stCffOutboundMasterRemoteModel, contractType);
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

        if (_stCffOutboundMasterRemoteModel != null) {
            try {
                Class<?> clazz = _stCffOutboundMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setSource", String.class);

                method.invoke(_stCffOutboundMasterRemoteModel, source);
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

        if (_stCffOutboundMasterRemoteModel != null) {
            try {
                Class<?> clazz = _stCffOutboundMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setBusinessUnitName",
                        String.class);

                method.invoke(_stCffOutboundMasterRemoteModel, businessUnitName);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getContractMasterSid() {
        return _contractMasterSid;
    }

    @Override
    public void setContractMasterSid(int contractMasterSid) {
        _contractMasterSid = contractMasterSid;

        if (_stCffOutboundMasterRemoteModel != null) {
            try {
                Class<?> clazz = _stCffOutboundMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setContractMasterSid",
                        int.class);

                method.invoke(_stCffOutboundMasterRemoteModel, contractMasterSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getFinancialForecastId() {
        return _financialForecastId;
    }

    @Override
    public void setFinancialForecastId(String financialForecastId) {
        _financialForecastId = financialForecastId;

        if (_stCffOutboundMasterRemoteModel != null) {
            try {
                Class<?> clazz = _stCffOutboundMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setFinancialForecastId",
                        String.class);

                method.invoke(_stCffOutboundMasterRemoteModel,
                    financialForecastId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getProjectId() {
        return _projectId;
    }

    @Override
    public void setProjectId(String projectId) {
        _projectId = projectId;

        if (_stCffOutboundMasterRemoteModel != null) {
            try {
                Class<?> clazz = _stCffOutboundMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setProjectId", String.class);

                method.invoke(_stCffOutboundMasterRemoteModel, projectId);
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

        if (_stCffOutboundMasterRemoteModel != null) {
            try {
                Class<?> clazz = _stCffOutboundMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCustomerNo", String.class);

                method.invoke(_stCffOutboundMasterRemoteModel, customerNo);
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

        if (_stCffOutboundMasterRemoteModel != null) {
            try {
                Class<?> clazz = _stCffOutboundMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedBy", String.class);

                method.invoke(_stCffOutboundMasterRemoteModel, modifiedBy);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getSalesDollars() {
        return _salesDollars;
    }

    @Override
    public void setSalesDollars(double salesDollars) {
        _salesDollars = salesDollars;

        if (_stCffOutboundMasterRemoteModel != null) {
            try {
                Class<?> clazz = _stCffOutboundMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setSalesDollars", double.class);

                method.invoke(_stCffOutboundMasterRemoteModel, salesDollars);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getMonth() {
        return _month;
    }

    @Override
    public void setMonth(int month) {
        _month = month;

        if (_stCffOutboundMasterRemoteModel != null) {
            try {
                Class<?> clazz = _stCffOutboundMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setMonth", int.class);

                method.invoke(_stCffOutboundMasterRemoteModel, month);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getCffDetailsSid() {
        return _cffDetailsSid;
    }

    @Override
    public void setCffDetailsSid(int cffDetailsSid) {
        _cffDetailsSid = cffDetailsSid;

        if (_stCffOutboundMasterRemoteModel != null) {
            try {
                Class<?> clazz = _stCffOutboundMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCffDetailsSid", int.class);

                method.invoke(_stCffOutboundMasterRemoteModel, cffDetailsSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getType() {
        return _type;
    }

    @Override
    public void setType(String type) {
        _type = type;

        if (_stCffOutboundMasterRemoteModel != null) {
            try {
                Class<?> clazz = _stCffOutboundMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setType", String.class);

                method.invoke(_stCffOutboundMasterRemoteModel, type);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getDeductionType() {
        return _deductionType;
    }

    @Override
    public void setDeductionType(String deductionType) {
        _deductionType = deductionType;

        if (_stCffOutboundMasterRemoteModel != null) {
            try {
                Class<?> clazz = _stCffOutboundMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setDeductionType", String.class);

                method.invoke(_stCffOutboundMasterRemoteModel, deductionType);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getCompanyMasterSid() {
        return _companyMasterSid;
    }

    @Override
    public void setCompanyMasterSid(int companyMasterSid) {
        _companyMasterSid = companyMasterSid;

        if (_stCffOutboundMasterRemoteModel != null) {
            try {
                Class<?> clazz = _stCffOutboundMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCompanyMasterSid", int.class);

                method.invoke(_stCffOutboundMasterRemoteModel, companyMasterSid);
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

        if (_stCffOutboundMasterRemoteModel != null) {
            try {
                Class<?> clazz = _stCffOutboundMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCheckRecord", boolean.class);

                method.invoke(_stCffOutboundMasterRemoteModel, checkRecord);
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

        if (_stCffOutboundMasterRemoteModel != null) {
            try {
                Class<?> clazz = _stCffOutboundMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setContractName", String.class);

                method.invoke(_stCffOutboundMasterRemoteModel, contractName);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getDeductionRate() {
        return _deductionRate;
    }

    @Override
    public void setDeductionRate(double deductionRate) {
        _deductionRate = deductionRate;

        if (_stCffOutboundMasterRemoteModel != null) {
            try {
                Class<?> clazz = _stCffOutboundMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setDeductionRate", double.class);

                method.invoke(_stCffOutboundMasterRemoteModel, deductionRate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getDeductionCategory() {
        return _deductionCategory;
    }

    @Override
    public void setDeductionCategory(String deductionCategory) {
        _deductionCategory = deductionCategory;

        if (_stCffOutboundMasterRemoteModel != null) {
            try {
                Class<?> clazz = _stCffOutboundMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setDeductionCategory",
                        String.class);

                method.invoke(_stCffOutboundMasterRemoteModel, deductionCategory);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getCogsAmount() {
        return _cogsAmount;
    }

    @Override
    public void setCogsAmount(double cogsAmount) {
        _cogsAmount = cogsAmount;

        if (_stCffOutboundMasterRemoteModel != null) {
            try {
                Class<?> clazz = _stCffOutboundMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCogsAmount", double.class);

                method.invoke(_stCffOutboundMasterRemoteModel, cogsAmount);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getDeductionNo() {
        return _deductionNo;
    }

    @Override
    public void setDeductionNo(String deductionNo) {
        _deductionNo = deductionNo;

        if (_stCffOutboundMasterRemoteModel != null) {
            try {
                Class<?> clazz = _stCffOutboundMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setDeductionNo", String.class);

                method.invoke(_stCffOutboundMasterRemoteModel, deductionNo);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getFinancialForecastCreationDate() {
        return _financialForecastCreationDate;
    }

    @Override
    public void setFinancialForecastCreationDate(
        Date financialForecastCreationDate) {
        _financialForecastCreationDate = financialForecastCreationDate;

        if (_stCffOutboundMasterRemoteModel != null) {
            try {
                Class<?> clazz = _stCffOutboundMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setFinancialForecastCreationDate",
                        Date.class);

                method.invoke(_stCffOutboundMasterRemoteModel,
                    financialForecastCreationDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getCompanyNo() {
        return _companyNo;
    }

    @Override
    public void setCompanyNo(String companyNo) {
        _companyNo = companyNo;

        if (_stCffOutboundMasterRemoteModel != null) {
            try {
                Class<?> clazz = _stCffOutboundMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCompanyNo", String.class);

                method.invoke(_stCffOutboundMasterRemoteModel, companyNo);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getSalesUnits() {
        return _salesUnits;
    }

    @Override
    public void setSalesUnits(double salesUnits) {
        _salesUnits = salesUnits;

        if (_stCffOutboundMasterRemoteModel != null) {
            try {
                Class<?> clazz = _stCffOutboundMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setSalesUnits", double.class);

                method.invoke(_stCffOutboundMasterRemoteModel, salesUnits);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getSessionId() {
        return _sessionId;
    }

    @Override
    public void setSessionId(String sessionId) {
        _sessionId = sessionId;

        if (_stCffOutboundMasterRemoteModel != null) {
            try {
                Class<?> clazz = _stCffOutboundMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setSessionId", String.class);

                method.invoke(_stCffOutboundMasterRemoteModel, sessionId);
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

        if (_stCffOutboundMasterRemoteModel != null) {
            try {
                Class<?> clazz = _stCffOutboundMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setItemName", String.class);

                method.invoke(_stCffOutboundMasterRemoteModel, itemName);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getDeductionInclusion() {
        return _deductionInclusion;
    }

    @Override
    public void setDeductionInclusion(String deductionInclusion) {
        _deductionInclusion = deductionInclusion;

        if (_stCffOutboundMasterRemoteModel != null) {
            try {
                Class<?> clazz = _stCffOutboundMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setDeductionInclusion",
                        String.class);

                method.invoke(_stCffOutboundMasterRemoteModel,
                    deductionInclusion);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getRsModelSid() {
        return _rsModelSid;
    }

    @Override
    public void setRsModelSid(int rsModelSid) {
        _rsModelSid = rsModelSid;

        if (_stCffOutboundMasterRemoteModel != null) {
            try {
                Class<?> clazz = _stCffOutboundMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setRsModelSid", int.class);

                method.invoke(_stCffOutboundMasterRemoteModel, rsModelSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getContractHolderName() {
        return _contractHolderName;
    }

    @Override
    public void setContractHolderName(String contractHolderName) {
        _contractHolderName = contractHolderName;

        if (_stCffOutboundMasterRemoteModel != null) {
            try {
                Class<?> clazz = _stCffOutboundMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setContractHolderName",
                        String.class);

                method.invoke(_stCffOutboundMasterRemoteModel,
                    contractHolderName);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getItemMasterSid() {
        return _itemMasterSid;
    }

    @Override
    public void setItemMasterSid(int itemMasterSid) {
        _itemMasterSid = itemMasterSid;

        if (_stCffOutboundMasterRemoteModel != null) {
            try {
                Class<?> clazz = _stCffOutboundMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setItemMasterSid", int.class);

                method.invoke(_stCffOutboundMasterRemoteModel, itemMasterSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getCompanyName() {
        return _companyName;
    }

    @Override
    public void setCompanyName(String companyName) {
        _companyName = companyName;

        if (_stCffOutboundMasterRemoteModel != null) {
            try {
                Class<?> clazz = _stCffOutboundMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCompanyName", String.class);

                method.invoke(_stCffOutboundMasterRemoteModel, companyName);
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

        if (_stCffOutboundMasterRemoteModel != null) {
            try {
                Class<?> clazz = _stCffOutboundMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCustomerId", String.class);

                method.invoke(_stCffOutboundMasterRemoteModel, customerId);
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

        if (_stCffOutboundMasterRemoteModel != null) {
            try {
                Class<?> clazz = _stCffOutboundMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setItemId", String.class);

                method.invoke(_stCffOutboundMasterRemoteModel, itemId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getNetProfitDollars() {
        return _netProfitDollars;
    }

    @Override
    public void setNetProfitDollars(double netProfitDollars) {
        _netProfitDollars = netProfitDollars;

        if (_stCffOutboundMasterRemoteModel != null) {
            try {
                Class<?> clazz = _stCffOutboundMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setNetProfitDollars",
                        double.class);

                method.invoke(_stCffOutboundMasterRemoteModel, netProfitDollars);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getGlCompanyMasterSid() {
        return _glCompanyMasterSid;
    }

    @Override
    public void setGlCompanyMasterSid(int glCompanyMasterSid) {
        _glCompanyMasterSid = glCompanyMasterSid;

        if (_stCffOutboundMasterRemoteModel != null) {
            try {
                Class<?> clazz = _stCffOutboundMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setGlCompanyMasterSid",
                        int.class);

                method.invoke(_stCffOutboundMasterRemoteModel,
                    glCompanyMasterSid);
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

        if (_stCffOutboundMasterRemoteModel != null) {
            try {
                Class<?> clazz = _stCffOutboundMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedDate", Date.class);

                method.invoke(_stCffOutboundMasterRemoteModel, createdDate);
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

        if (_stCffOutboundMasterRemoteModel != null) {
            try {
                Class<?> clazz = _stCffOutboundMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedBy", String.class);

                method.invoke(_stCffOutboundMasterRemoteModel, createdBy);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getDeductionCategory1() {
        return _deductionCategory1;
    }

    @Override
    public void setDeductionCategory1(String deductionCategory1) {
        _deductionCategory1 = deductionCategory1;

        if (_stCffOutboundMasterRemoteModel != null) {
            try {
                Class<?> clazz = _stCffOutboundMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setDeductionCategory1",
                        String.class);

                method.invoke(_stCffOutboundMasterRemoteModel,
                    deductionCategory1);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getDeductionCategory2() {
        return _deductionCategory2;
    }

    @Override
    public void setDeductionCategory2(String deductionCategory2) {
        _deductionCategory2 = deductionCategory2;

        if (_stCffOutboundMasterRemoteModel != null) {
            try {
                Class<?> clazz = _stCffOutboundMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setDeductionCategory2",
                        String.class);

                method.invoke(_stCffOutboundMasterRemoteModel,
                    deductionCategory2);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getContractHolderNo() {
        return _contractHolderNo;
    }

    @Override
    public void setContractHolderNo(String contractHolderNo) {
        _contractHolderNo = contractHolderNo;

        if (_stCffOutboundMasterRemoteModel != null) {
            try {
                Class<?> clazz = _stCffOutboundMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setContractHolderNo",
                        String.class);

                method.invoke(_stCffOutboundMasterRemoteModel, contractHolderNo);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getDeductionCategory3() {
        return _deductionCategory3;
    }

    @Override
    public void setDeductionCategory3(String deductionCategory3) {
        _deductionCategory3 = deductionCategory3;

        if (_stCffOutboundMasterRemoteModel != null) {
            try {
                Class<?> clazz = _stCffOutboundMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setDeductionCategory3",
                        String.class);

                method.invoke(_stCffOutboundMasterRemoteModel,
                    deductionCategory3);
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

        if (_stCffOutboundMasterRemoteModel != null) {
            try {
                Class<?> clazz = _stCffOutboundMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setItemNo", String.class);

                method.invoke(_stCffOutboundMasterRemoteModel, itemNo);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getDeductionCategory4() {
        return _deductionCategory4;
    }

    @Override
    public void setDeductionCategory4(String deductionCategory4) {
        _deductionCategory4 = deductionCategory4;

        if (_stCffOutboundMasterRemoteModel != null) {
            try {
                Class<?> clazz = _stCffOutboundMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setDeductionCategory4",
                        String.class);

                method.invoke(_stCffOutboundMasterRemoteModel,
                    deductionCategory4);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getDeductionCategory5() {
        return _deductionCategory5;
    }

    @Override
    public void setDeductionCategory5(String deductionCategory5) {
        _deductionCategory5 = deductionCategory5;

        if (_stCffOutboundMasterRemoteModel != null) {
            try {
                Class<?> clazz = _stCffOutboundMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setDeductionCategory5",
                        String.class);

                method.invoke(_stCffOutboundMasterRemoteModel,
                    deductionCategory5);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getDeductionCategory6() {
        return _deductionCategory6;
    }

    @Override
    public void setDeductionCategory6(String deductionCategory6) {
        _deductionCategory6 = deductionCategory6;

        if (_stCffOutboundMasterRemoteModel != null) {
            try {
                Class<?> clazz = _stCffOutboundMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setDeductionCategory6",
                        String.class);

                method.invoke(_stCffOutboundMasterRemoteModel,
                    deductionCategory6);
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

        if (_stCffOutboundMasterRemoteModel != null) {
            try {
                Class<?> clazz = _stCffOutboundMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setContractId", String.class);

                method.invoke(_stCffOutboundMasterRemoteModel, contractId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getDeductionProgram() {
        return _deductionProgram;
    }

    @Override
    public void setDeductionProgram(String deductionProgram) {
        _deductionProgram = deductionProgram;

        if (_stCffOutboundMasterRemoteModel != null) {
            try {
                Class<?> clazz = _stCffOutboundMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setDeductionProgram",
                        String.class);

                method.invoke(_stCffOutboundMasterRemoteModel, deductionProgram);
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

        if (_stCffOutboundMasterRemoteModel != null) {
            try {
                Class<?> clazz = _stCffOutboundMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setBusinessUnitId",
                        String.class);

                method.invoke(_stCffOutboundMasterRemoteModel, businessUnitId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getProjectionName() {
        return _projectionName;
    }

    @Override
    public void setProjectionName(String projectionName) {
        _projectionName = projectionName;

        if (_stCffOutboundMasterRemoteModel != null) {
            try {
                Class<?> clazz = _stCffOutboundMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setProjectionName",
                        String.class);

                method.invoke(_stCffOutboundMasterRemoteModel, projectionName);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getUserId() {
        return _userId;
    }

    @Override
    public void setUserId(String userId) {
        _userId = userId;

        if (_stCffOutboundMasterRemoteModel != null) {
            try {
                Class<?> clazz = _stCffOutboundMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setUserId", String.class);

                method.invoke(_stCffOutboundMasterRemoteModel, userId);
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

        if (_stCffOutboundMasterRemoteModel != null) {
            try {
                Class<?> clazz = _stCffOutboundMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCompanyId", String.class);

                method.invoke(_stCffOutboundMasterRemoteModel, companyId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getOutboundStatus() {
        return _outboundStatus;
    }

    @Override
    public void setOutboundStatus(String outboundStatus) {
        _outboundStatus = outboundStatus;

        if (_stCffOutboundMasterRemoteModel != null) {
            try {
                Class<?> clazz = _stCffOutboundMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setOutboundStatus",
                        String.class);

                method.invoke(_stCffOutboundMasterRemoteModel, outboundStatus);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getOriginalBatchId() {
        return _originalBatchId;
    }

    @Override
    public void setOriginalBatchId(String originalBatchId) {
        _originalBatchId = originalBatchId;

        if (_stCffOutboundMasterRemoteModel != null) {
            try {
                Class<?> clazz = _stCffOutboundMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setOriginalBatchId",
                        String.class);

                method.invoke(_stCffOutboundMasterRemoteModel, originalBatchId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getDeductionName() {
        return _deductionName;
    }

    @Override
    public void setDeductionName(String deductionName) {
        _deductionName = deductionName;

        if (_stCffOutboundMasterRemoteModel != null) {
            try {
                Class<?> clazz = _stCffOutboundMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setDeductionName", String.class);

                method.invoke(_stCffOutboundMasterRemoteModel, deductionName);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getNetProfitPerUnit() {
        return _netProfitPerUnit;
    }

    @Override
    public void setNetProfitPerUnit(double netProfitPerUnit) {
        _netProfitPerUnit = netProfitPerUnit;

        if (_stCffOutboundMasterRemoteModel != null) {
            try {
                Class<?> clazz = _stCffOutboundMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setNetProfitPerUnit",
                        double.class);

                method.invoke(_stCffOutboundMasterRemoteModel, netProfitPerUnit);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getPeriodSid() {
        return _periodSid;
    }

    @Override
    public void setPeriodSid(int periodSid) {
        _periodSid = periodSid;

        if (_stCffOutboundMasterRemoteModel != null) {
            try {
                Class<?> clazz = _stCffOutboundMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setPeriodSid", int.class);

                method.invoke(_stCffOutboundMasterRemoteModel, periodSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getSalesInclusion() {
        return _salesInclusion;
    }

    @Override
    public void setSalesInclusion(String salesInclusion) {
        _salesInclusion = salesInclusion;

        if (_stCffOutboundMasterRemoteModel != null) {
            try {
                Class<?> clazz = _stCffOutboundMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setSalesInclusion",
                        String.class);

                method.invoke(_stCffOutboundMasterRemoteModel, salesInclusion);
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

        if (_stCffOutboundMasterRemoteModel != null) {
            try {
                Class<?> clazz = _stCffOutboundMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setBatchId", String.class);

                method.invoke(_stCffOutboundMasterRemoteModel, batchId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getFinancialForecastName() {
        return _financialForecastName;
    }

    @Override
    public void setFinancialForecastName(String financialForecastName) {
        _financialForecastName = financialForecastName;

        if (_stCffOutboundMasterRemoteModel != null) {
            try {
                Class<?> clazz = _stCffOutboundMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setFinancialForecastName",
                        String.class);

                method.invoke(_stCffOutboundMasterRemoteModel,
                    financialForecastName);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getNetSalesDollar() {
        return _netSalesDollar;
    }

    @Override
    public void setNetSalesDollar(double netSalesDollar) {
        _netSalesDollar = netSalesDollar;

        if (_stCffOutboundMasterRemoteModel != null) {
            try {
                Class<?> clazz = _stCffOutboundMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setNetSalesDollar",
                        double.class);

                method.invoke(_stCffOutboundMasterRemoteModel, netSalesDollar);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getDeductionDollars() {
        return _deductionDollars;
    }

    @Override
    public void setDeductionDollars(double deductionDollars) {
        _deductionDollars = deductionDollars;

        if (_stCffOutboundMasterRemoteModel != null) {
            try {
                Class<?> clazz = _stCffOutboundMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setDeductionDollars",
                        double.class);

                method.invoke(_stCffOutboundMasterRemoteModel, deductionDollars);
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

        if (_stCffOutboundMasterRemoteModel != null) {
            try {
                Class<?> clazz = _stCffOutboundMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setContractNo", String.class);

                method.invoke(_stCffOutboundMasterRemoteModel, contractNo);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getStCffOutboundMasterRemoteModel() {
        return _stCffOutboundMasterRemoteModel;
    }

    public void setStCffOutboundMasterRemoteModel(
        BaseModel<?> stCffOutboundMasterRemoteModel) {
        _stCffOutboundMasterRemoteModel = stCffOutboundMasterRemoteModel;
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

        Class<?> remoteModelClass = _stCffOutboundMasterRemoteModel.getClass();

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

        Object returnValue = method.invoke(_stCffOutboundMasterRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            StCffOutboundMasterLocalServiceUtil.addStCffOutboundMaster(this);
        } else {
            StCffOutboundMasterLocalServiceUtil.updateStCffOutboundMaster(this);
        }
    }

    @Override
    public StCffOutboundMaster toEscapedModel() {
        return (StCffOutboundMaster) ProxyUtil.newProxyInstance(StCffOutboundMaster.class.getClassLoader(),
            new Class[] { StCffOutboundMaster.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        StCffOutboundMasterClp clone = new StCffOutboundMasterClp();

        clone.setEtlCheckRecord(getEtlCheckRecord());
        clone.setCustomerName(getCustomerName());
        clone.setContractHolderId(getContractHolderId());
        clone.setBusinessUnitNo(getBusinessUnitNo());
        clone.setYear(getYear());
        clone.setFinancialForecastApprovalDate(getFinancialForecastApprovalDate());
        clone.setDeductionId(getDeductionId());
        clone.setModifiedDate(getModifiedDate());
        clone.setDeductionPerUnit(getDeductionPerUnit());
        clone.setCogsPerUnit(getCogsPerUnit());
        clone.setContractType(getContractType());
        clone.setSource(getSource());
        clone.setBusinessUnitName(getBusinessUnitName());
        clone.setContractMasterSid(getContractMasterSid());
        clone.setFinancialForecastId(getFinancialForecastId());
        clone.setProjectId(getProjectId());
        clone.setCustomerNo(getCustomerNo());
        clone.setModifiedBy(getModifiedBy());
        clone.setSalesDollars(getSalesDollars());
        clone.setMonth(getMonth());
        clone.setCffDetailsSid(getCffDetailsSid());
        clone.setType(getType());
        clone.setDeductionType(getDeductionType());
        clone.setCompanyMasterSid(getCompanyMasterSid());
        clone.setCheckRecord(getCheckRecord());
        clone.setContractName(getContractName());
        clone.setDeductionRate(getDeductionRate());
        clone.setDeductionCategory(getDeductionCategory());
        clone.setCogsAmount(getCogsAmount());
        clone.setDeductionNo(getDeductionNo());
        clone.setFinancialForecastCreationDate(getFinancialForecastCreationDate());
        clone.setCompanyNo(getCompanyNo());
        clone.setSalesUnits(getSalesUnits());
        clone.setSessionId(getSessionId());
        clone.setItemName(getItemName());
        clone.setDeductionInclusion(getDeductionInclusion());
        clone.setRsModelSid(getRsModelSid());
        clone.setContractHolderName(getContractHolderName());
        clone.setItemMasterSid(getItemMasterSid());
        clone.setCompanyName(getCompanyName());
        clone.setCustomerId(getCustomerId());
        clone.setItemId(getItemId());
        clone.setNetProfitDollars(getNetProfitDollars());
        clone.setGlCompanyMasterSid(getGlCompanyMasterSid());
        clone.setCreatedDate(getCreatedDate());
        clone.setCreatedBy(getCreatedBy());
        clone.setDeductionCategory1(getDeductionCategory1());
        clone.setDeductionCategory2(getDeductionCategory2());
        clone.setContractHolderNo(getContractHolderNo());
        clone.setDeductionCategory3(getDeductionCategory3());
        clone.setItemNo(getItemNo());
        clone.setDeductionCategory4(getDeductionCategory4());
        clone.setDeductionCategory5(getDeductionCategory5());
        clone.setDeductionCategory6(getDeductionCategory6());
        clone.setContractId(getContractId());
        clone.setDeductionProgram(getDeductionProgram());
        clone.setBusinessUnitId(getBusinessUnitId());
        clone.setProjectionName(getProjectionName());
        clone.setUserId(getUserId());
        clone.setCompanyId(getCompanyId());
        clone.setOutboundStatus(getOutboundStatus());
        clone.setOriginalBatchId(getOriginalBatchId());
        clone.setDeductionName(getDeductionName());
        clone.setNetProfitPerUnit(getNetProfitPerUnit());
        clone.setPeriodSid(getPeriodSid());
        clone.setSalesInclusion(getSalesInclusion());
        clone.setBatchId(getBatchId());
        clone.setFinancialForecastName(getFinancialForecastName());
        clone.setNetSalesDollar(getNetSalesDollar());
        clone.setDeductionDollars(getDeductionDollars());
        clone.setContractNo(getContractNo());

        return clone;
    }

    @Override
    public int compareTo(StCffOutboundMaster stCffOutboundMaster) {
        StCffOutboundMasterPK primaryKey = stCffOutboundMaster.getPrimaryKey();

        return getPrimaryKey().compareTo(primaryKey);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof StCffOutboundMasterClp)) {
            return false;
        }

        StCffOutboundMasterClp stCffOutboundMaster = (StCffOutboundMasterClp) obj;

        StCffOutboundMasterPK primaryKey = stCffOutboundMaster.getPrimaryKey();

        if (getPrimaryKey().equals(primaryKey)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return getPrimaryKey().hashCode();
    }

    @Override
    public String toString() {
        StringBundler sb = new StringBundler(143);

        sb.append("{etlCheckRecord=");
        sb.append(getEtlCheckRecord());
        sb.append(", customerName=");
        sb.append(getCustomerName());
        sb.append(", contractHolderId=");
        sb.append(getContractHolderId());
        sb.append(", businessUnitNo=");
        sb.append(getBusinessUnitNo());
        sb.append(", year=");
        sb.append(getYear());
        sb.append(", financialForecastApprovalDate=");
        sb.append(getFinancialForecastApprovalDate());
        sb.append(", deductionId=");
        sb.append(getDeductionId());
        sb.append(", modifiedDate=");
        sb.append(getModifiedDate());
        sb.append(", deductionPerUnit=");
        sb.append(getDeductionPerUnit());
        sb.append(", cogsPerUnit=");
        sb.append(getCogsPerUnit());
        sb.append(", contractType=");
        sb.append(getContractType());
        sb.append(", source=");
        sb.append(getSource());
        sb.append(", businessUnitName=");
        sb.append(getBusinessUnitName());
        sb.append(", contractMasterSid=");
        sb.append(getContractMasterSid());
        sb.append(", financialForecastId=");
        sb.append(getFinancialForecastId());
        sb.append(", projectId=");
        sb.append(getProjectId());
        sb.append(", customerNo=");
        sb.append(getCustomerNo());
        sb.append(", modifiedBy=");
        sb.append(getModifiedBy());
        sb.append(", salesDollars=");
        sb.append(getSalesDollars());
        sb.append(", month=");
        sb.append(getMonth());
        sb.append(", cffDetailsSid=");
        sb.append(getCffDetailsSid());
        sb.append(", type=");
        sb.append(getType());
        sb.append(", deductionType=");
        sb.append(getDeductionType());
        sb.append(", companyMasterSid=");
        sb.append(getCompanyMasterSid());
        sb.append(", checkRecord=");
        sb.append(getCheckRecord());
        sb.append(", contractName=");
        sb.append(getContractName());
        sb.append(", deductionRate=");
        sb.append(getDeductionRate());
        sb.append(", deductionCategory=");
        sb.append(getDeductionCategory());
        sb.append(", cogsAmount=");
        sb.append(getCogsAmount());
        sb.append(", deductionNo=");
        sb.append(getDeductionNo());
        sb.append(", financialForecastCreationDate=");
        sb.append(getFinancialForecastCreationDate());
        sb.append(", companyNo=");
        sb.append(getCompanyNo());
        sb.append(", salesUnits=");
        sb.append(getSalesUnits());
        sb.append(", sessionId=");
        sb.append(getSessionId());
        sb.append(", itemName=");
        sb.append(getItemName());
        sb.append(", deductionInclusion=");
        sb.append(getDeductionInclusion());
        sb.append(", rsModelSid=");
        sb.append(getRsModelSid());
        sb.append(", contractHolderName=");
        sb.append(getContractHolderName());
        sb.append(", itemMasterSid=");
        sb.append(getItemMasterSid());
        sb.append(", companyName=");
        sb.append(getCompanyName());
        sb.append(", customerId=");
        sb.append(getCustomerId());
        sb.append(", itemId=");
        sb.append(getItemId());
        sb.append(", netProfitDollars=");
        sb.append(getNetProfitDollars());
        sb.append(", glCompanyMasterSid=");
        sb.append(getGlCompanyMasterSid());
        sb.append(", createdDate=");
        sb.append(getCreatedDate());
        sb.append(", createdBy=");
        sb.append(getCreatedBy());
        sb.append(", deductionCategory1=");
        sb.append(getDeductionCategory1());
        sb.append(", deductionCategory2=");
        sb.append(getDeductionCategory2());
        sb.append(", contractHolderNo=");
        sb.append(getContractHolderNo());
        sb.append(", deductionCategory3=");
        sb.append(getDeductionCategory3());
        sb.append(", itemNo=");
        sb.append(getItemNo());
        sb.append(", deductionCategory4=");
        sb.append(getDeductionCategory4());
        sb.append(", deductionCategory5=");
        sb.append(getDeductionCategory5());
        sb.append(", deductionCategory6=");
        sb.append(getDeductionCategory6());
        sb.append(", contractId=");
        sb.append(getContractId());
        sb.append(", deductionProgram=");
        sb.append(getDeductionProgram());
        sb.append(", businessUnitId=");
        sb.append(getBusinessUnitId());
        sb.append(", projectionName=");
        sb.append(getProjectionName());
        sb.append(", userId=");
        sb.append(getUserId());
        sb.append(", companyId=");
        sb.append(getCompanyId());
        sb.append(", outboundStatus=");
        sb.append(getOutboundStatus());
        sb.append(", originalBatchId=");
        sb.append(getOriginalBatchId());
        sb.append(", deductionName=");
        sb.append(getDeductionName());
        sb.append(", netProfitPerUnit=");
        sb.append(getNetProfitPerUnit());
        sb.append(", periodSid=");
        sb.append(getPeriodSid());
        sb.append(", salesInclusion=");
        sb.append(getSalesInclusion());
        sb.append(", batchId=");
        sb.append(getBatchId());
        sb.append(", financialForecastName=");
        sb.append(getFinancialForecastName());
        sb.append(", netSalesDollar=");
        sb.append(getNetSalesDollar());
        sb.append(", deductionDollars=");
        sb.append(getDeductionDollars());
        sb.append(", contractNo=");
        sb.append(getContractNo());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(217);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.parttwo.model.StCffOutboundMaster");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>etlCheckRecord</column-name><column-value><![CDATA[");
        sb.append(getEtlCheckRecord());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>customerName</column-name><column-value><![CDATA[");
        sb.append(getCustomerName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>contractHolderId</column-name><column-value><![CDATA[");
        sb.append(getContractHolderId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>businessUnitNo</column-name><column-value><![CDATA[");
        sb.append(getBusinessUnitNo());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>year</column-name><column-value><![CDATA[");
        sb.append(getYear());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>financialForecastApprovalDate</column-name><column-value><![CDATA[");
        sb.append(getFinancialForecastApprovalDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>deductionId</column-name><column-value><![CDATA[");
        sb.append(getDeductionId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
        sb.append(getModifiedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>deductionPerUnit</column-name><column-value><![CDATA[");
        sb.append(getDeductionPerUnit());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>cogsPerUnit</column-name><column-value><![CDATA[");
        sb.append(getCogsPerUnit());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>contractType</column-name><column-value><![CDATA[");
        sb.append(getContractType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>source</column-name><column-value><![CDATA[");
        sb.append(getSource());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>businessUnitName</column-name><column-value><![CDATA[");
        sb.append(getBusinessUnitName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>contractMasterSid</column-name><column-value><![CDATA[");
        sb.append(getContractMasterSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>financialForecastId</column-name><column-value><![CDATA[");
        sb.append(getFinancialForecastId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>projectId</column-name><column-value><![CDATA[");
        sb.append(getProjectId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>customerNo</column-name><column-value><![CDATA[");
        sb.append(getCustomerNo());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedBy</column-name><column-value><![CDATA[");
        sb.append(getModifiedBy());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>salesDollars</column-name><column-value><![CDATA[");
        sb.append(getSalesDollars());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>month</column-name><column-value><![CDATA[");
        sb.append(getMonth());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>cffDetailsSid</column-name><column-value><![CDATA[");
        sb.append(getCffDetailsSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>type</column-name><column-value><![CDATA[");
        sb.append(getType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>deductionType</column-name><column-value><![CDATA[");
        sb.append(getDeductionType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>companyMasterSid</column-name><column-value><![CDATA[");
        sb.append(getCompanyMasterSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>checkRecord</column-name><column-value><![CDATA[");
        sb.append(getCheckRecord());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>contractName</column-name><column-value><![CDATA[");
        sb.append(getContractName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>deductionRate</column-name><column-value><![CDATA[");
        sb.append(getDeductionRate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>deductionCategory</column-name><column-value><![CDATA[");
        sb.append(getDeductionCategory());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>cogsAmount</column-name><column-value><![CDATA[");
        sb.append(getCogsAmount());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>deductionNo</column-name><column-value><![CDATA[");
        sb.append(getDeductionNo());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>financialForecastCreationDate</column-name><column-value><![CDATA[");
        sb.append(getFinancialForecastCreationDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>companyNo</column-name><column-value><![CDATA[");
        sb.append(getCompanyNo());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>salesUnits</column-name><column-value><![CDATA[");
        sb.append(getSalesUnits());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>sessionId</column-name><column-value><![CDATA[");
        sb.append(getSessionId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemName</column-name><column-value><![CDATA[");
        sb.append(getItemName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>deductionInclusion</column-name><column-value><![CDATA[");
        sb.append(getDeductionInclusion());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>rsModelSid</column-name><column-value><![CDATA[");
        sb.append(getRsModelSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>contractHolderName</column-name><column-value><![CDATA[");
        sb.append(getContractHolderName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemMasterSid</column-name><column-value><![CDATA[");
        sb.append(getItemMasterSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>companyName</column-name><column-value><![CDATA[");
        sb.append(getCompanyName());
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
            "<column><column-name>netProfitDollars</column-name><column-value><![CDATA[");
        sb.append(getNetProfitDollars());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>glCompanyMasterSid</column-name><column-value><![CDATA[");
        sb.append(getGlCompanyMasterSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>createdDate</column-name><column-value><![CDATA[");
        sb.append(getCreatedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>createdBy</column-name><column-value><![CDATA[");
        sb.append(getCreatedBy());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>deductionCategory1</column-name><column-value><![CDATA[");
        sb.append(getDeductionCategory1());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>deductionCategory2</column-name><column-value><![CDATA[");
        sb.append(getDeductionCategory2());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>contractHolderNo</column-name><column-value><![CDATA[");
        sb.append(getContractHolderNo());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>deductionCategory3</column-name><column-value><![CDATA[");
        sb.append(getDeductionCategory3());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemNo</column-name><column-value><![CDATA[");
        sb.append(getItemNo());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>deductionCategory4</column-name><column-value><![CDATA[");
        sb.append(getDeductionCategory4());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>deductionCategory5</column-name><column-value><![CDATA[");
        sb.append(getDeductionCategory5());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>deductionCategory6</column-name><column-value><![CDATA[");
        sb.append(getDeductionCategory6());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>contractId</column-name><column-value><![CDATA[");
        sb.append(getContractId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>deductionProgram</column-name><column-value><![CDATA[");
        sb.append(getDeductionProgram());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>businessUnitId</column-name><column-value><![CDATA[");
        sb.append(getBusinessUnitId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>projectionName</column-name><column-value><![CDATA[");
        sb.append(getProjectionName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>userId</column-name><column-value><![CDATA[");
        sb.append(getUserId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>companyId</column-name><column-value><![CDATA[");
        sb.append(getCompanyId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>outboundStatus</column-name><column-value><![CDATA[");
        sb.append(getOutboundStatus());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>originalBatchId</column-name><column-value><![CDATA[");
        sb.append(getOriginalBatchId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>deductionName</column-name><column-value><![CDATA[");
        sb.append(getDeductionName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>netProfitPerUnit</column-name><column-value><![CDATA[");
        sb.append(getNetProfitPerUnit());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>periodSid</column-name><column-value><![CDATA[");
        sb.append(getPeriodSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>salesInclusion</column-name><column-value><![CDATA[");
        sb.append(getSalesInclusion());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>batchId</column-name><column-value><![CDATA[");
        sb.append(getBatchId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>financialForecastName</column-name><column-value><![CDATA[");
        sb.append(getFinancialForecastName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>netSalesDollar</column-name><column-value><![CDATA[");
        sb.append(getNetSalesDollar());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>deductionDollars</column-name><column-value><![CDATA[");
        sb.append(getDeductionDollars());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>contractNo</column-name><column-value><![CDATA[");
        sb.append(getContractNo());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
