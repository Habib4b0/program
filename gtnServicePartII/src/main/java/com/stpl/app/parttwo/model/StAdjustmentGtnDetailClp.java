package com.stpl.app.parttwo.model;

import com.stpl.app.parttwo.service.ClpSerializer;
import com.stpl.app.parttwo.service.StAdjustmentGtnDetailLocalServiceUtil;
import com.stpl.app.parttwo.service.persistence.StAdjustmentGtnDetailPK;

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


public class StAdjustmentGtnDetailClp extends BaseModelImpl<StAdjustmentGtnDetail>
    implements StAdjustmentGtnDetail {
    private Date _adjustmentCreatedDate;
    private boolean _etlCheckRecord;
    private String _businessUnitNo;
    private Date _redemptionPeriod;
    private String _deductionId;
    private int _glYear;
    private String _brandName;
    private Date _modifiedDate;
    private String _account;
    private String _source;
    private Date _workflowApprovedDate;
    private String _udc6;
    private String _businessUnitName;
    private String _udc5;
    private Date _workflowCreatedDate;
    private String _udc4;
    private String _udc3;
    private String _udc2;
    private String _udc1;
    private String _adjustmentType;
    private String _modifiedBy;
    private String _deductionType;
    private boolean _checkRecord;
    private String _contractName;
    private String _deductionRate;
    private String _deductionCategory;
    private String _deductionNo;
    private String _companyNo;
    private String _sessionId;
    private String _glCompanyId;
    private String _itemName;
    private String _deductionInclusion;
    private String _deductionAmount;
    private String _companyName;
    private String _project;
    private String _deductionUdc3;
    private String _deductionUdc4;
    private String _deductionUdc1;
    private String _itemId;
    private String _deductionUdc2;
    private String _accountType;
    private String _glString;
    private Date _createdDate;
    private String _createdBy;
    private String _deductionUdc6;
    private String _deductionUdc5;
    private String _glCompanyName;
    private String _workflowId;
    private String _itemNo;
    private String _contractId;
    private String _deductionProgram;
    private String _businessUnitId;
    private String _userId;
    private String _costCenter;
    private String _companyId;
    private String _outboundStatus;
    private String _future1;
    private String _brandId;
    private String _deductionName;
    private String _future2;
    private String _workflowName;
    private Date _glDate;
    private String _workflowCreatedBy;
    private int _glMonth;
    private String _batchId;
    private String _accountCategory;
    private String _glCompanyNo;
    private String _workflowApprovedBy;
    private String _contractNo;
    private String _originalBatchId;
    private String _adjustmentLevel;
    private BaseModel<?> _stAdjustmentGtnDetailRemoteModel;

    public StAdjustmentGtnDetailClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return StAdjustmentGtnDetail.class;
    }

    @Override
    public String getModelClassName() {
        return StAdjustmentGtnDetail.class.getName();
    }

    @Override
    public StAdjustmentGtnDetailPK getPrimaryKey() {
        return new StAdjustmentGtnDetailPK(_redemptionPeriod, _deductionId,
            _account, _adjustmentType, _sessionId, _glCompanyId, _itemId,
            _accountType, _workflowId, _contractId, _businessUnitId, _userId,
            _companyId, _accountCategory);
    }

    @Override
    public void setPrimaryKey(StAdjustmentGtnDetailPK primaryKey) {
        setRedemptionPeriod(primaryKey.redemptionPeriod);
        setDeductionId(primaryKey.deductionId);
        setAccount(primaryKey.account);
        setAdjustmentType(primaryKey.adjustmentType);
        setSessionId(primaryKey.sessionId);
        setGlCompanyId(primaryKey.glCompanyId);
        setItemId(primaryKey.itemId);
        setAccountType(primaryKey.accountType);
        setWorkflowId(primaryKey.workflowId);
        setContractId(primaryKey.contractId);
        setBusinessUnitId(primaryKey.businessUnitId);
        setUserId(primaryKey.userId);
        setCompanyId(primaryKey.companyId);
        setAccountCategory(primaryKey.accountCategory);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return new StAdjustmentGtnDetailPK(_redemptionPeriod, _deductionId,
            _account, _adjustmentType, _sessionId, _glCompanyId, _itemId,
            _accountType, _workflowId, _contractId, _businessUnitId, _userId,
            _companyId, _accountCategory);
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey((StAdjustmentGtnDetailPK) primaryKeyObj);
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("adjustmentCreatedDate", getAdjustmentCreatedDate());
        attributes.put("etlCheckRecord", getEtlCheckRecord());
        attributes.put("businessUnitNo", getBusinessUnitNo());
        attributes.put("redemptionPeriod", getRedemptionPeriod());
        attributes.put("deductionId", getDeductionId());
        attributes.put("glYear", getGlYear());
        attributes.put("brandName", getBrandName());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("account", getAccount());
        attributes.put("source", getSource());
        attributes.put("workflowApprovedDate", getWorkflowApprovedDate());
        attributes.put("udc6", getUdc6());
        attributes.put("businessUnitName", getBusinessUnitName());
        attributes.put("udc5", getUdc5());
        attributes.put("workflowCreatedDate", getWorkflowCreatedDate());
        attributes.put("udc4", getUdc4());
        attributes.put("udc3", getUdc3());
        attributes.put("udc2", getUdc2());
        attributes.put("udc1", getUdc1());
        attributes.put("adjustmentType", getAdjustmentType());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("deductionType", getDeductionType());
        attributes.put("checkRecord", getCheckRecord());
        attributes.put("contractName", getContractName());
        attributes.put("deductionRate", getDeductionRate());
        attributes.put("deductionCategory", getDeductionCategory());
        attributes.put("deductionNo", getDeductionNo());
        attributes.put("companyNo", getCompanyNo());
        attributes.put("sessionId", getSessionId());
        attributes.put("glCompanyId", getGlCompanyId());
        attributes.put("itemName", getItemName());
        attributes.put("deductionInclusion", getDeductionInclusion());
        attributes.put("deductionAmount", getDeductionAmount());
        attributes.put("companyName", getCompanyName());
        attributes.put("project", getProject());
        attributes.put("deductionUdc3", getDeductionUdc3());
        attributes.put("deductionUdc4", getDeductionUdc4());
        attributes.put("deductionUdc1", getDeductionUdc1());
        attributes.put("itemId", getItemId());
        attributes.put("deductionUdc2", getDeductionUdc2());
        attributes.put("accountType", getAccountType());
        attributes.put("glString", getGlString());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("deductionUdc6", getDeductionUdc6());
        attributes.put("deductionUdc5", getDeductionUdc5());
        attributes.put("glCompanyName", getGlCompanyName());
        attributes.put("workflowId", getWorkflowId());
        attributes.put("itemNo", getItemNo());
        attributes.put("contractId", getContractId());
        attributes.put("deductionProgram", getDeductionProgram());
        attributes.put("businessUnitId", getBusinessUnitId());
        attributes.put("userId", getUserId());
        attributes.put("costCenter", getCostCenter());
        attributes.put("companyId", getCompanyId());
        attributes.put("outboundStatus", getOutboundStatus());
        attributes.put("future1", getFuture1());
        attributes.put("brandId", getBrandId());
        attributes.put("deductionName", getDeductionName());
        attributes.put("future2", getFuture2());
        attributes.put("workflowName", getWorkflowName());
        attributes.put("glDate", getGlDate());
        attributes.put("workflowCreatedBy", getWorkflowCreatedBy());
        attributes.put("glMonth", getGlMonth());
        attributes.put("batchId", getBatchId());
        attributes.put("accountCategory", getAccountCategory());
        attributes.put("glCompanyNo", getGlCompanyNo());
        attributes.put("workflowApprovedBy", getWorkflowApprovedBy());
        attributes.put("contractNo", getContractNo());
        attributes.put("originalBatchId", getOriginalBatchId());
        attributes.put("adjustmentLevel", getAdjustmentLevel());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Date adjustmentCreatedDate = (Date) attributes.get(
                "adjustmentCreatedDate");

        if (adjustmentCreatedDate != null) {
            setAdjustmentCreatedDate(adjustmentCreatedDate);
        }

        Boolean etlCheckRecord = (Boolean) attributes.get("etlCheckRecord");

        if (etlCheckRecord != null) {
            setEtlCheckRecord(etlCheckRecord);
        }

        String businessUnitNo = (String) attributes.get("businessUnitNo");

        if (businessUnitNo != null) {
            setBusinessUnitNo(businessUnitNo);
        }

        Date redemptionPeriod = (Date) attributes.get("redemptionPeriod");

        if (redemptionPeriod != null) {
            setRedemptionPeriod(redemptionPeriod);
        }

        String deductionId = (String) attributes.get("deductionId");

        if (deductionId != null) {
            setDeductionId(deductionId);
        }

        Integer glYear = (Integer) attributes.get("glYear");

        if (glYear != null) {
            setGlYear(glYear);
        }

        String brandName = (String) attributes.get("brandName");

        if (brandName != null) {
            setBrandName(brandName);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        String account = (String) attributes.get("account");

        if (account != null) {
            setAccount(account);
        }

        String source = (String) attributes.get("source");

        if (source != null) {
            setSource(source);
        }

        Date workflowApprovedDate = (Date) attributes.get(
                "workflowApprovedDate");

        if (workflowApprovedDate != null) {
            setWorkflowApprovedDate(workflowApprovedDate);
        }

        String udc6 = (String) attributes.get("udc6");

        if (udc6 != null) {
            setUdc6(udc6);
        }

        String businessUnitName = (String) attributes.get("businessUnitName");

        if (businessUnitName != null) {
            setBusinessUnitName(businessUnitName);
        }

        String udc5 = (String) attributes.get("udc5");

        if (udc5 != null) {
            setUdc5(udc5);
        }

        Date workflowCreatedDate = (Date) attributes.get("workflowCreatedDate");

        if (workflowCreatedDate != null) {
            setWorkflowCreatedDate(workflowCreatedDate);
        }

        String udc4 = (String) attributes.get("udc4");

        if (udc4 != null) {
            setUdc4(udc4);
        }

        String udc3 = (String) attributes.get("udc3");

        if (udc3 != null) {
            setUdc3(udc3);
        }

        String udc2 = (String) attributes.get("udc2");

        if (udc2 != null) {
            setUdc2(udc2);
        }

        String udc1 = (String) attributes.get("udc1");

        if (udc1 != null) {
            setUdc1(udc1);
        }

        String adjustmentType = (String) attributes.get("adjustmentType");

        if (adjustmentType != null) {
            setAdjustmentType(adjustmentType);
        }

        String modifiedBy = (String) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        String deductionType = (String) attributes.get("deductionType");

        if (deductionType != null) {
            setDeductionType(deductionType);
        }

        Boolean checkRecord = (Boolean) attributes.get("checkRecord");

        if (checkRecord != null) {
            setCheckRecord(checkRecord);
        }

        String contractName = (String) attributes.get("contractName");

        if (contractName != null) {
            setContractName(contractName);
        }

        String deductionRate = (String) attributes.get("deductionRate");

        if (deductionRate != null) {
            setDeductionRate(deductionRate);
        }

        String deductionCategory = (String) attributes.get("deductionCategory");

        if (deductionCategory != null) {
            setDeductionCategory(deductionCategory);
        }

        String deductionNo = (String) attributes.get("deductionNo");

        if (deductionNo != null) {
            setDeductionNo(deductionNo);
        }

        String companyNo = (String) attributes.get("companyNo");

        if (companyNo != null) {
            setCompanyNo(companyNo);
        }

        String sessionId = (String) attributes.get("sessionId");

        if (sessionId != null) {
            setSessionId(sessionId);
        }

        String glCompanyId = (String) attributes.get("glCompanyId");

        if (glCompanyId != null) {
            setGlCompanyId(glCompanyId);
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

        String deductionAmount = (String) attributes.get("deductionAmount");

        if (deductionAmount != null) {
            setDeductionAmount(deductionAmount);
        }

        String companyName = (String) attributes.get("companyName");

        if (companyName != null) {
            setCompanyName(companyName);
        }

        String project = (String) attributes.get("project");

        if (project != null) {
            setProject(project);
        }

        String deductionUdc3 = (String) attributes.get("deductionUdc3");

        if (deductionUdc3 != null) {
            setDeductionUdc3(deductionUdc3);
        }

        String deductionUdc4 = (String) attributes.get("deductionUdc4");

        if (deductionUdc4 != null) {
            setDeductionUdc4(deductionUdc4);
        }

        String deductionUdc1 = (String) attributes.get("deductionUdc1");

        if (deductionUdc1 != null) {
            setDeductionUdc1(deductionUdc1);
        }

        String itemId = (String) attributes.get("itemId");

        if (itemId != null) {
            setItemId(itemId);
        }

        String deductionUdc2 = (String) attributes.get("deductionUdc2");

        if (deductionUdc2 != null) {
            setDeductionUdc2(deductionUdc2);
        }

        String accountType = (String) attributes.get("accountType");

        if (accountType != null) {
            setAccountType(accountType);
        }

        String glString = (String) attributes.get("glString");

        if (glString != null) {
            setGlString(glString);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        String createdBy = (String) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        String deductionUdc6 = (String) attributes.get("deductionUdc6");

        if (deductionUdc6 != null) {
            setDeductionUdc6(deductionUdc6);
        }

        String deductionUdc5 = (String) attributes.get("deductionUdc5");

        if (deductionUdc5 != null) {
            setDeductionUdc5(deductionUdc5);
        }

        String glCompanyName = (String) attributes.get("glCompanyName");

        if (glCompanyName != null) {
            setGlCompanyName(glCompanyName);
        }

        String workflowId = (String) attributes.get("workflowId");

        if (workflowId != null) {
            setWorkflowId(workflowId);
        }

        String itemNo = (String) attributes.get("itemNo");

        if (itemNo != null) {
            setItemNo(itemNo);
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

        String userId = (String) attributes.get("userId");

        if (userId != null) {
            setUserId(userId);
        }

        String costCenter = (String) attributes.get("costCenter");

        if (costCenter != null) {
            setCostCenter(costCenter);
        }

        String companyId = (String) attributes.get("companyId");

        if (companyId != null) {
            setCompanyId(companyId);
        }

        String outboundStatus = (String) attributes.get("outboundStatus");

        if (outboundStatus != null) {
            setOutboundStatus(outboundStatus);
        }

        String future1 = (String) attributes.get("future1");

        if (future1 != null) {
            setFuture1(future1);
        }

        String brandId = (String) attributes.get("brandId");

        if (brandId != null) {
            setBrandId(brandId);
        }

        String deductionName = (String) attributes.get("deductionName");

        if (deductionName != null) {
            setDeductionName(deductionName);
        }

        String future2 = (String) attributes.get("future2");

        if (future2 != null) {
            setFuture2(future2);
        }

        String workflowName = (String) attributes.get("workflowName");

        if (workflowName != null) {
            setWorkflowName(workflowName);
        }

        Date glDate = (Date) attributes.get("glDate");

        if (glDate != null) {
            setGlDate(glDate);
        }

        String workflowCreatedBy = (String) attributes.get("workflowCreatedBy");

        if (workflowCreatedBy != null) {
            setWorkflowCreatedBy(workflowCreatedBy);
        }

        Integer glMonth = (Integer) attributes.get("glMonth");

        if (glMonth != null) {
            setGlMonth(glMonth);
        }

        String batchId = (String) attributes.get("batchId");

        if (batchId != null) {
            setBatchId(batchId);
        }

        String accountCategory = (String) attributes.get("accountCategory");

        if (accountCategory != null) {
            setAccountCategory(accountCategory);
        }

        String glCompanyNo = (String) attributes.get("glCompanyNo");

        if (glCompanyNo != null) {
            setGlCompanyNo(glCompanyNo);
        }

        String workflowApprovedBy = (String) attributes.get(
                "workflowApprovedBy");

        if (workflowApprovedBy != null) {
            setWorkflowApprovedBy(workflowApprovedBy);
        }

        String contractNo = (String) attributes.get("contractNo");

        if (contractNo != null) {
            setContractNo(contractNo);
        }

        String originalBatchId = (String) attributes.get("originalBatchId");

        if (originalBatchId != null) {
            setOriginalBatchId(originalBatchId);
        }

        String adjustmentLevel = (String) attributes.get("adjustmentLevel");

        if (adjustmentLevel != null) {
            setAdjustmentLevel(adjustmentLevel);
        }
    }

    @Override
    public Date getAdjustmentCreatedDate() {
        return _adjustmentCreatedDate;
    }

    @Override
    public void setAdjustmentCreatedDate(Date adjustmentCreatedDate) {
        _adjustmentCreatedDate = adjustmentCreatedDate;

        if (_stAdjustmentGtnDetailRemoteModel != null) {
            try {
                Class<?> clazz = _stAdjustmentGtnDetailRemoteModel.getClass();

                Method method = clazz.getMethod("setAdjustmentCreatedDate",
                        Date.class);

                method.invoke(_stAdjustmentGtnDetailRemoteModel,
                    adjustmentCreatedDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
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

        if (_stAdjustmentGtnDetailRemoteModel != null) {
            try {
                Class<?> clazz = _stAdjustmentGtnDetailRemoteModel.getClass();

                Method method = clazz.getMethod("setEtlCheckRecord",
                        boolean.class);

                method.invoke(_stAdjustmentGtnDetailRemoteModel, etlCheckRecord);
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

        if (_stAdjustmentGtnDetailRemoteModel != null) {
            try {
                Class<?> clazz = _stAdjustmentGtnDetailRemoteModel.getClass();

                Method method = clazz.getMethod("setBusinessUnitNo",
                        String.class);

                method.invoke(_stAdjustmentGtnDetailRemoteModel, businessUnitNo);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getRedemptionPeriod() {
        return _redemptionPeriod;
    }

    @Override
    public void setRedemptionPeriod(Date redemptionPeriod) {
        _redemptionPeriod = redemptionPeriod;

        if (_stAdjustmentGtnDetailRemoteModel != null) {
            try {
                Class<?> clazz = _stAdjustmentGtnDetailRemoteModel.getClass();

                Method method = clazz.getMethod("setRedemptionPeriod",
                        Date.class);

                method.invoke(_stAdjustmentGtnDetailRemoteModel,
                    redemptionPeriod);
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

        if (_stAdjustmentGtnDetailRemoteModel != null) {
            try {
                Class<?> clazz = _stAdjustmentGtnDetailRemoteModel.getClass();

                Method method = clazz.getMethod("setDeductionId", String.class);

                method.invoke(_stAdjustmentGtnDetailRemoteModel, deductionId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getGlYear() {
        return _glYear;
    }

    @Override
    public void setGlYear(int glYear) {
        _glYear = glYear;

        if (_stAdjustmentGtnDetailRemoteModel != null) {
            try {
                Class<?> clazz = _stAdjustmentGtnDetailRemoteModel.getClass();

                Method method = clazz.getMethod("setGlYear", int.class);

                method.invoke(_stAdjustmentGtnDetailRemoteModel, glYear);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getBrandName() {
        return _brandName;
    }

    @Override
    public void setBrandName(String brandName) {
        _brandName = brandName;

        if (_stAdjustmentGtnDetailRemoteModel != null) {
            try {
                Class<?> clazz = _stAdjustmentGtnDetailRemoteModel.getClass();

                Method method = clazz.getMethod("setBrandName", String.class);

                method.invoke(_stAdjustmentGtnDetailRemoteModel, brandName);
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

        if (_stAdjustmentGtnDetailRemoteModel != null) {
            try {
                Class<?> clazz = _stAdjustmentGtnDetailRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedDate", Date.class);

                method.invoke(_stAdjustmentGtnDetailRemoteModel, modifiedDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getAccount() {
        return _account;
    }

    @Override
    public void setAccount(String account) {
        _account = account;

        if (_stAdjustmentGtnDetailRemoteModel != null) {
            try {
                Class<?> clazz = _stAdjustmentGtnDetailRemoteModel.getClass();

                Method method = clazz.getMethod("setAccount", String.class);

                method.invoke(_stAdjustmentGtnDetailRemoteModel, account);
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

        if (_stAdjustmentGtnDetailRemoteModel != null) {
            try {
                Class<?> clazz = _stAdjustmentGtnDetailRemoteModel.getClass();

                Method method = clazz.getMethod("setSource", String.class);

                method.invoke(_stAdjustmentGtnDetailRemoteModel, source);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getWorkflowApprovedDate() {
        return _workflowApprovedDate;
    }

    @Override
    public void setWorkflowApprovedDate(Date workflowApprovedDate) {
        _workflowApprovedDate = workflowApprovedDate;

        if (_stAdjustmentGtnDetailRemoteModel != null) {
            try {
                Class<?> clazz = _stAdjustmentGtnDetailRemoteModel.getClass();

                Method method = clazz.getMethod("setWorkflowApprovedDate",
                        Date.class);

                method.invoke(_stAdjustmentGtnDetailRemoteModel,
                    workflowApprovedDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getUdc6() {
        return _udc6;
    }

    @Override
    public void setUdc6(String udc6) {
        _udc6 = udc6;

        if (_stAdjustmentGtnDetailRemoteModel != null) {
            try {
                Class<?> clazz = _stAdjustmentGtnDetailRemoteModel.getClass();

                Method method = clazz.getMethod("setUdc6", String.class);

                method.invoke(_stAdjustmentGtnDetailRemoteModel, udc6);
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

        if (_stAdjustmentGtnDetailRemoteModel != null) {
            try {
                Class<?> clazz = _stAdjustmentGtnDetailRemoteModel.getClass();

                Method method = clazz.getMethod("setBusinessUnitName",
                        String.class);

                method.invoke(_stAdjustmentGtnDetailRemoteModel,
                    businessUnitName);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getUdc5() {
        return _udc5;
    }

    @Override
    public void setUdc5(String udc5) {
        _udc5 = udc5;

        if (_stAdjustmentGtnDetailRemoteModel != null) {
            try {
                Class<?> clazz = _stAdjustmentGtnDetailRemoteModel.getClass();

                Method method = clazz.getMethod("setUdc5", String.class);

                method.invoke(_stAdjustmentGtnDetailRemoteModel, udc5);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getWorkflowCreatedDate() {
        return _workflowCreatedDate;
    }

    @Override
    public void setWorkflowCreatedDate(Date workflowCreatedDate) {
        _workflowCreatedDate = workflowCreatedDate;

        if (_stAdjustmentGtnDetailRemoteModel != null) {
            try {
                Class<?> clazz = _stAdjustmentGtnDetailRemoteModel.getClass();

                Method method = clazz.getMethod("setWorkflowCreatedDate",
                        Date.class);

                method.invoke(_stAdjustmentGtnDetailRemoteModel,
                    workflowCreatedDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getUdc4() {
        return _udc4;
    }

    @Override
    public void setUdc4(String udc4) {
        _udc4 = udc4;

        if (_stAdjustmentGtnDetailRemoteModel != null) {
            try {
                Class<?> clazz = _stAdjustmentGtnDetailRemoteModel.getClass();

                Method method = clazz.getMethod("setUdc4", String.class);

                method.invoke(_stAdjustmentGtnDetailRemoteModel, udc4);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getUdc3() {
        return _udc3;
    }

    @Override
    public void setUdc3(String udc3) {
        _udc3 = udc3;

        if (_stAdjustmentGtnDetailRemoteModel != null) {
            try {
                Class<?> clazz = _stAdjustmentGtnDetailRemoteModel.getClass();

                Method method = clazz.getMethod("setUdc3", String.class);

                method.invoke(_stAdjustmentGtnDetailRemoteModel, udc3);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getUdc2() {
        return _udc2;
    }

    @Override
    public void setUdc2(String udc2) {
        _udc2 = udc2;

        if (_stAdjustmentGtnDetailRemoteModel != null) {
            try {
                Class<?> clazz = _stAdjustmentGtnDetailRemoteModel.getClass();

                Method method = clazz.getMethod("setUdc2", String.class);

                method.invoke(_stAdjustmentGtnDetailRemoteModel, udc2);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getUdc1() {
        return _udc1;
    }

    @Override
    public void setUdc1(String udc1) {
        _udc1 = udc1;

        if (_stAdjustmentGtnDetailRemoteModel != null) {
            try {
                Class<?> clazz = _stAdjustmentGtnDetailRemoteModel.getClass();

                Method method = clazz.getMethod("setUdc1", String.class);

                method.invoke(_stAdjustmentGtnDetailRemoteModel, udc1);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getAdjustmentType() {
        return _adjustmentType;
    }

    @Override
    public void setAdjustmentType(String adjustmentType) {
        _adjustmentType = adjustmentType;

        if (_stAdjustmentGtnDetailRemoteModel != null) {
            try {
                Class<?> clazz = _stAdjustmentGtnDetailRemoteModel.getClass();

                Method method = clazz.getMethod("setAdjustmentType",
                        String.class);

                method.invoke(_stAdjustmentGtnDetailRemoteModel, adjustmentType);
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

        if (_stAdjustmentGtnDetailRemoteModel != null) {
            try {
                Class<?> clazz = _stAdjustmentGtnDetailRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedBy", String.class);

                method.invoke(_stAdjustmentGtnDetailRemoteModel, modifiedBy);
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

        if (_stAdjustmentGtnDetailRemoteModel != null) {
            try {
                Class<?> clazz = _stAdjustmentGtnDetailRemoteModel.getClass();

                Method method = clazz.getMethod("setDeductionType", String.class);

                method.invoke(_stAdjustmentGtnDetailRemoteModel, deductionType);
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

        if (_stAdjustmentGtnDetailRemoteModel != null) {
            try {
                Class<?> clazz = _stAdjustmentGtnDetailRemoteModel.getClass();

                Method method = clazz.getMethod("setCheckRecord", boolean.class);

                method.invoke(_stAdjustmentGtnDetailRemoteModel, checkRecord);
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

        if (_stAdjustmentGtnDetailRemoteModel != null) {
            try {
                Class<?> clazz = _stAdjustmentGtnDetailRemoteModel.getClass();

                Method method = clazz.getMethod("setContractName", String.class);

                method.invoke(_stAdjustmentGtnDetailRemoteModel, contractName);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getDeductionRate() {
        return _deductionRate;
    }

    @Override
    public void setDeductionRate(String deductionRate) {
        _deductionRate = deductionRate;

        if (_stAdjustmentGtnDetailRemoteModel != null) {
            try {
                Class<?> clazz = _stAdjustmentGtnDetailRemoteModel.getClass();

                Method method = clazz.getMethod("setDeductionRate", String.class);

                method.invoke(_stAdjustmentGtnDetailRemoteModel, deductionRate);
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

        if (_stAdjustmentGtnDetailRemoteModel != null) {
            try {
                Class<?> clazz = _stAdjustmentGtnDetailRemoteModel.getClass();

                Method method = clazz.getMethod("setDeductionCategory",
                        String.class);

                method.invoke(_stAdjustmentGtnDetailRemoteModel,
                    deductionCategory);
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

        if (_stAdjustmentGtnDetailRemoteModel != null) {
            try {
                Class<?> clazz = _stAdjustmentGtnDetailRemoteModel.getClass();

                Method method = clazz.getMethod("setDeductionNo", String.class);

                method.invoke(_stAdjustmentGtnDetailRemoteModel, deductionNo);
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

        if (_stAdjustmentGtnDetailRemoteModel != null) {
            try {
                Class<?> clazz = _stAdjustmentGtnDetailRemoteModel.getClass();

                Method method = clazz.getMethod("setCompanyNo", String.class);

                method.invoke(_stAdjustmentGtnDetailRemoteModel, companyNo);
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

        if (_stAdjustmentGtnDetailRemoteModel != null) {
            try {
                Class<?> clazz = _stAdjustmentGtnDetailRemoteModel.getClass();

                Method method = clazz.getMethod("setSessionId", String.class);

                method.invoke(_stAdjustmentGtnDetailRemoteModel, sessionId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getGlCompanyId() {
        return _glCompanyId;
    }

    @Override
    public void setGlCompanyId(String glCompanyId) {
        _glCompanyId = glCompanyId;

        if (_stAdjustmentGtnDetailRemoteModel != null) {
            try {
                Class<?> clazz = _stAdjustmentGtnDetailRemoteModel.getClass();

                Method method = clazz.getMethod("setGlCompanyId", String.class);

                method.invoke(_stAdjustmentGtnDetailRemoteModel, glCompanyId);
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

        if (_stAdjustmentGtnDetailRemoteModel != null) {
            try {
                Class<?> clazz = _stAdjustmentGtnDetailRemoteModel.getClass();

                Method method = clazz.getMethod("setItemName", String.class);

                method.invoke(_stAdjustmentGtnDetailRemoteModel, itemName);
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

        if (_stAdjustmentGtnDetailRemoteModel != null) {
            try {
                Class<?> clazz = _stAdjustmentGtnDetailRemoteModel.getClass();

                Method method = clazz.getMethod("setDeductionInclusion",
                        String.class);

                method.invoke(_stAdjustmentGtnDetailRemoteModel,
                    deductionInclusion);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getDeductionAmount() {
        return _deductionAmount;
    }

    @Override
    public void setDeductionAmount(String deductionAmount) {
        _deductionAmount = deductionAmount;

        if (_stAdjustmentGtnDetailRemoteModel != null) {
            try {
                Class<?> clazz = _stAdjustmentGtnDetailRemoteModel.getClass();

                Method method = clazz.getMethod("setDeductionAmount",
                        String.class);

                method.invoke(_stAdjustmentGtnDetailRemoteModel, deductionAmount);
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

        if (_stAdjustmentGtnDetailRemoteModel != null) {
            try {
                Class<?> clazz = _stAdjustmentGtnDetailRemoteModel.getClass();

                Method method = clazz.getMethod("setCompanyName", String.class);

                method.invoke(_stAdjustmentGtnDetailRemoteModel, companyName);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getProject() {
        return _project;
    }

    @Override
    public void setProject(String project) {
        _project = project;

        if (_stAdjustmentGtnDetailRemoteModel != null) {
            try {
                Class<?> clazz = _stAdjustmentGtnDetailRemoteModel.getClass();

                Method method = clazz.getMethod("setProject", String.class);

                method.invoke(_stAdjustmentGtnDetailRemoteModel, project);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getDeductionUdc3() {
        return _deductionUdc3;
    }

    @Override
    public void setDeductionUdc3(String deductionUdc3) {
        _deductionUdc3 = deductionUdc3;

        if (_stAdjustmentGtnDetailRemoteModel != null) {
            try {
                Class<?> clazz = _stAdjustmentGtnDetailRemoteModel.getClass();

                Method method = clazz.getMethod("setDeductionUdc3", String.class);

                method.invoke(_stAdjustmentGtnDetailRemoteModel, deductionUdc3);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getDeductionUdc4() {
        return _deductionUdc4;
    }

    @Override
    public void setDeductionUdc4(String deductionUdc4) {
        _deductionUdc4 = deductionUdc4;

        if (_stAdjustmentGtnDetailRemoteModel != null) {
            try {
                Class<?> clazz = _stAdjustmentGtnDetailRemoteModel.getClass();

                Method method = clazz.getMethod("setDeductionUdc4", String.class);

                method.invoke(_stAdjustmentGtnDetailRemoteModel, deductionUdc4);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getDeductionUdc1() {
        return _deductionUdc1;
    }

    @Override
    public void setDeductionUdc1(String deductionUdc1) {
        _deductionUdc1 = deductionUdc1;

        if (_stAdjustmentGtnDetailRemoteModel != null) {
            try {
                Class<?> clazz = _stAdjustmentGtnDetailRemoteModel.getClass();

                Method method = clazz.getMethod("setDeductionUdc1", String.class);

                method.invoke(_stAdjustmentGtnDetailRemoteModel, deductionUdc1);
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

        if (_stAdjustmentGtnDetailRemoteModel != null) {
            try {
                Class<?> clazz = _stAdjustmentGtnDetailRemoteModel.getClass();

                Method method = clazz.getMethod("setItemId", String.class);

                method.invoke(_stAdjustmentGtnDetailRemoteModel, itemId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getDeductionUdc2() {
        return _deductionUdc2;
    }

    @Override
    public void setDeductionUdc2(String deductionUdc2) {
        _deductionUdc2 = deductionUdc2;

        if (_stAdjustmentGtnDetailRemoteModel != null) {
            try {
                Class<?> clazz = _stAdjustmentGtnDetailRemoteModel.getClass();

                Method method = clazz.getMethod("setDeductionUdc2", String.class);

                method.invoke(_stAdjustmentGtnDetailRemoteModel, deductionUdc2);
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

        if (_stAdjustmentGtnDetailRemoteModel != null) {
            try {
                Class<?> clazz = _stAdjustmentGtnDetailRemoteModel.getClass();

                Method method = clazz.getMethod("setAccountType", String.class);

                method.invoke(_stAdjustmentGtnDetailRemoteModel, accountType);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getGlString() {
        return _glString;
    }

    @Override
    public void setGlString(String glString) {
        _glString = glString;

        if (_stAdjustmentGtnDetailRemoteModel != null) {
            try {
                Class<?> clazz = _stAdjustmentGtnDetailRemoteModel.getClass();

                Method method = clazz.getMethod("setGlString", String.class);

                method.invoke(_stAdjustmentGtnDetailRemoteModel, glString);
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

        if (_stAdjustmentGtnDetailRemoteModel != null) {
            try {
                Class<?> clazz = _stAdjustmentGtnDetailRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedDate", Date.class);

                method.invoke(_stAdjustmentGtnDetailRemoteModel, createdDate);
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

        if (_stAdjustmentGtnDetailRemoteModel != null) {
            try {
                Class<?> clazz = _stAdjustmentGtnDetailRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedBy", String.class);

                method.invoke(_stAdjustmentGtnDetailRemoteModel, createdBy);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getDeductionUdc6() {
        return _deductionUdc6;
    }

    @Override
    public void setDeductionUdc6(String deductionUdc6) {
        _deductionUdc6 = deductionUdc6;

        if (_stAdjustmentGtnDetailRemoteModel != null) {
            try {
                Class<?> clazz = _stAdjustmentGtnDetailRemoteModel.getClass();

                Method method = clazz.getMethod("setDeductionUdc6", String.class);

                method.invoke(_stAdjustmentGtnDetailRemoteModel, deductionUdc6);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getDeductionUdc5() {
        return _deductionUdc5;
    }

    @Override
    public void setDeductionUdc5(String deductionUdc5) {
        _deductionUdc5 = deductionUdc5;

        if (_stAdjustmentGtnDetailRemoteModel != null) {
            try {
                Class<?> clazz = _stAdjustmentGtnDetailRemoteModel.getClass();

                Method method = clazz.getMethod("setDeductionUdc5", String.class);

                method.invoke(_stAdjustmentGtnDetailRemoteModel, deductionUdc5);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getGlCompanyName() {
        return _glCompanyName;
    }

    @Override
    public void setGlCompanyName(String glCompanyName) {
        _glCompanyName = glCompanyName;

        if (_stAdjustmentGtnDetailRemoteModel != null) {
            try {
                Class<?> clazz = _stAdjustmentGtnDetailRemoteModel.getClass();

                Method method = clazz.getMethod("setGlCompanyName", String.class);

                method.invoke(_stAdjustmentGtnDetailRemoteModel, glCompanyName);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getWorkflowId() {
        return _workflowId;
    }

    @Override
    public void setWorkflowId(String workflowId) {
        _workflowId = workflowId;

        if (_stAdjustmentGtnDetailRemoteModel != null) {
            try {
                Class<?> clazz = _stAdjustmentGtnDetailRemoteModel.getClass();

                Method method = clazz.getMethod("setWorkflowId", String.class);

                method.invoke(_stAdjustmentGtnDetailRemoteModel, workflowId);
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

        if (_stAdjustmentGtnDetailRemoteModel != null) {
            try {
                Class<?> clazz = _stAdjustmentGtnDetailRemoteModel.getClass();

                Method method = clazz.getMethod("setItemNo", String.class);

                method.invoke(_stAdjustmentGtnDetailRemoteModel, itemNo);
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

        if (_stAdjustmentGtnDetailRemoteModel != null) {
            try {
                Class<?> clazz = _stAdjustmentGtnDetailRemoteModel.getClass();

                Method method = clazz.getMethod("setContractId", String.class);

                method.invoke(_stAdjustmentGtnDetailRemoteModel, contractId);
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

        if (_stAdjustmentGtnDetailRemoteModel != null) {
            try {
                Class<?> clazz = _stAdjustmentGtnDetailRemoteModel.getClass();

                Method method = clazz.getMethod("setDeductionProgram",
                        String.class);

                method.invoke(_stAdjustmentGtnDetailRemoteModel,
                    deductionProgram);
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

        if (_stAdjustmentGtnDetailRemoteModel != null) {
            try {
                Class<?> clazz = _stAdjustmentGtnDetailRemoteModel.getClass();

                Method method = clazz.getMethod("setBusinessUnitId",
                        String.class);

                method.invoke(_stAdjustmentGtnDetailRemoteModel, businessUnitId);
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

        if (_stAdjustmentGtnDetailRemoteModel != null) {
            try {
                Class<?> clazz = _stAdjustmentGtnDetailRemoteModel.getClass();

                Method method = clazz.getMethod("setUserId", String.class);

                method.invoke(_stAdjustmentGtnDetailRemoteModel, userId);
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

        if (_stAdjustmentGtnDetailRemoteModel != null) {
            try {
                Class<?> clazz = _stAdjustmentGtnDetailRemoteModel.getClass();

                Method method = clazz.getMethod("setCostCenter", String.class);

                method.invoke(_stAdjustmentGtnDetailRemoteModel, costCenter);
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

        if (_stAdjustmentGtnDetailRemoteModel != null) {
            try {
                Class<?> clazz = _stAdjustmentGtnDetailRemoteModel.getClass();

                Method method = clazz.getMethod("setCompanyId", String.class);

                method.invoke(_stAdjustmentGtnDetailRemoteModel, companyId);
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

        if (_stAdjustmentGtnDetailRemoteModel != null) {
            try {
                Class<?> clazz = _stAdjustmentGtnDetailRemoteModel.getClass();

                Method method = clazz.getMethod("setOutboundStatus",
                        String.class);

                method.invoke(_stAdjustmentGtnDetailRemoteModel, outboundStatus);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getFuture1() {
        return _future1;
    }

    @Override
    public void setFuture1(String future1) {
        _future1 = future1;

        if (_stAdjustmentGtnDetailRemoteModel != null) {
            try {
                Class<?> clazz = _stAdjustmentGtnDetailRemoteModel.getClass();

                Method method = clazz.getMethod("setFuture1", String.class);

                method.invoke(_stAdjustmentGtnDetailRemoteModel, future1);
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

        if (_stAdjustmentGtnDetailRemoteModel != null) {
            try {
                Class<?> clazz = _stAdjustmentGtnDetailRemoteModel.getClass();

                Method method = clazz.getMethod("setBrandId", String.class);

                method.invoke(_stAdjustmentGtnDetailRemoteModel, brandId);
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

        if (_stAdjustmentGtnDetailRemoteModel != null) {
            try {
                Class<?> clazz = _stAdjustmentGtnDetailRemoteModel.getClass();

                Method method = clazz.getMethod("setDeductionName", String.class);

                method.invoke(_stAdjustmentGtnDetailRemoteModel, deductionName);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getFuture2() {
        return _future2;
    }

    @Override
    public void setFuture2(String future2) {
        _future2 = future2;

        if (_stAdjustmentGtnDetailRemoteModel != null) {
            try {
                Class<?> clazz = _stAdjustmentGtnDetailRemoteModel.getClass();

                Method method = clazz.getMethod("setFuture2", String.class);

                method.invoke(_stAdjustmentGtnDetailRemoteModel, future2);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getWorkflowName() {
        return _workflowName;
    }

    @Override
    public void setWorkflowName(String workflowName) {
        _workflowName = workflowName;

        if (_stAdjustmentGtnDetailRemoteModel != null) {
            try {
                Class<?> clazz = _stAdjustmentGtnDetailRemoteModel.getClass();

                Method method = clazz.getMethod("setWorkflowName", String.class);

                method.invoke(_stAdjustmentGtnDetailRemoteModel, workflowName);
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

        if (_stAdjustmentGtnDetailRemoteModel != null) {
            try {
                Class<?> clazz = _stAdjustmentGtnDetailRemoteModel.getClass();

                Method method = clazz.getMethod("setGlDate", Date.class);

                method.invoke(_stAdjustmentGtnDetailRemoteModel, glDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getWorkflowCreatedBy() {
        return _workflowCreatedBy;
    }

    @Override
    public void setWorkflowCreatedBy(String workflowCreatedBy) {
        _workflowCreatedBy = workflowCreatedBy;

        if (_stAdjustmentGtnDetailRemoteModel != null) {
            try {
                Class<?> clazz = _stAdjustmentGtnDetailRemoteModel.getClass();

                Method method = clazz.getMethod("setWorkflowCreatedBy",
                        String.class);

                method.invoke(_stAdjustmentGtnDetailRemoteModel,
                    workflowCreatedBy);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getGlMonth() {
        return _glMonth;
    }

    @Override
    public void setGlMonth(int glMonth) {
        _glMonth = glMonth;

        if (_stAdjustmentGtnDetailRemoteModel != null) {
            try {
                Class<?> clazz = _stAdjustmentGtnDetailRemoteModel.getClass();

                Method method = clazz.getMethod("setGlMonth", int.class);

                method.invoke(_stAdjustmentGtnDetailRemoteModel, glMonth);
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

        if (_stAdjustmentGtnDetailRemoteModel != null) {
            try {
                Class<?> clazz = _stAdjustmentGtnDetailRemoteModel.getClass();

                Method method = clazz.getMethod("setBatchId", String.class);

                method.invoke(_stAdjustmentGtnDetailRemoteModel, batchId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getAccountCategory() {
        return _accountCategory;
    }

    @Override
    public void setAccountCategory(String accountCategory) {
        _accountCategory = accountCategory;

        if (_stAdjustmentGtnDetailRemoteModel != null) {
            try {
                Class<?> clazz = _stAdjustmentGtnDetailRemoteModel.getClass();

                Method method = clazz.getMethod("setAccountCategory",
                        String.class);

                method.invoke(_stAdjustmentGtnDetailRemoteModel, accountCategory);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getGlCompanyNo() {
        return _glCompanyNo;
    }

    @Override
    public void setGlCompanyNo(String glCompanyNo) {
        _glCompanyNo = glCompanyNo;

        if (_stAdjustmentGtnDetailRemoteModel != null) {
            try {
                Class<?> clazz = _stAdjustmentGtnDetailRemoteModel.getClass();

                Method method = clazz.getMethod("setGlCompanyNo", String.class);

                method.invoke(_stAdjustmentGtnDetailRemoteModel, glCompanyNo);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getWorkflowApprovedBy() {
        return _workflowApprovedBy;
    }

    @Override
    public void setWorkflowApprovedBy(String workflowApprovedBy) {
        _workflowApprovedBy = workflowApprovedBy;

        if (_stAdjustmentGtnDetailRemoteModel != null) {
            try {
                Class<?> clazz = _stAdjustmentGtnDetailRemoteModel.getClass();

                Method method = clazz.getMethod("setWorkflowApprovedBy",
                        String.class);

                method.invoke(_stAdjustmentGtnDetailRemoteModel,
                    workflowApprovedBy);
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

        if (_stAdjustmentGtnDetailRemoteModel != null) {
            try {
                Class<?> clazz = _stAdjustmentGtnDetailRemoteModel.getClass();

                Method method = clazz.getMethod("setContractNo", String.class);

                method.invoke(_stAdjustmentGtnDetailRemoteModel, contractNo);
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

        if (_stAdjustmentGtnDetailRemoteModel != null) {
            try {
                Class<?> clazz = _stAdjustmentGtnDetailRemoteModel.getClass();

                Method method = clazz.getMethod("setOriginalBatchId",
                        String.class);

                method.invoke(_stAdjustmentGtnDetailRemoteModel, originalBatchId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getAdjustmentLevel() {
        return _adjustmentLevel;
    }

    @Override
    public void setAdjustmentLevel(String adjustmentLevel) {
        _adjustmentLevel = adjustmentLevel;

        if (_stAdjustmentGtnDetailRemoteModel != null) {
            try {
                Class<?> clazz = _stAdjustmentGtnDetailRemoteModel.getClass();

                Method method = clazz.getMethod("setAdjustmentLevel",
                        String.class);

                method.invoke(_stAdjustmentGtnDetailRemoteModel, adjustmentLevel);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getStAdjustmentGtnDetailRemoteModel() {
        return _stAdjustmentGtnDetailRemoteModel;
    }

    public void setStAdjustmentGtnDetailRemoteModel(
        BaseModel<?> stAdjustmentGtnDetailRemoteModel) {
        _stAdjustmentGtnDetailRemoteModel = stAdjustmentGtnDetailRemoteModel;
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

        Class<?> remoteModelClass = _stAdjustmentGtnDetailRemoteModel.getClass();

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

        Object returnValue = method.invoke(_stAdjustmentGtnDetailRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            StAdjustmentGtnDetailLocalServiceUtil.addStAdjustmentGtnDetail(this);
        } else {
            StAdjustmentGtnDetailLocalServiceUtil.updateStAdjustmentGtnDetail(this);
        }
    }

    @Override
    public StAdjustmentGtnDetail toEscapedModel() {
        return (StAdjustmentGtnDetail) ProxyUtil.newProxyInstance(StAdjustmentGtnDetail.class.getClassLoader(),
            new Class[] { StAdjustmentGtnDetail.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        StAdjustmentGtnDetailClp clone = new StAdjustmentGtnDetailClp();

        clone.setAdjustmentCreatedDate(getAdjustmentCreatedDate());
        clone.setEtlCheckRecord(getEtlCheckRecord());
        clone.setBusinessUnitNo(getBusinessUnitNo());
        clone.setRedemptionPeriod(getRedemptionPeriod());
        clone.setDeductionId(getDeductionId());
        clone.setGlYear(getGlYear());
        clone.setBrandName(getBrandName());
        clone.setModifiedDate(getModifiedDate());
        clone.setAccount(getAccount());
        clone.setSource(getSource());
        clone.setWorkflowApprovedDate(getWorkflowApprovedDate());
        clone.setUdc6(getUdc6());
        clone.setBusinessUnitName(getBusinessUnitName());
        clone.setUdc5(getUdc5());
        clone.setWorkflowCreatedDate(getWorkflowCreatedDate());
        clone.setUdc4(getUdc4());
        clone.setUdc3(getUdc3());
        clone.setUdc2(getUdc2());
        clone.setUdc1(getUdc1());
        clone.setAdjustmentType(getAdjustmentType());
        clone.setModifiedBy(getModifiedBy());
        clone.setDeductionType(getDeductionType());
        clone.setCheckRecord(getCheckRecord());
        clone.setContractName(getContractName());
        clone.setDeductionRate(getDeductionRate());
        clone.setDeductionCategory(getDeductionCategory());
        clone.setDeductionNo(getDeductionNo());
        clone.setCompanyNo(getCompanyNo());
        clone.setSessionId(getSessionId());
        clone.setGlCompanyId(getGlCompanyId());
        clone.setItemName(getItemName());
        clone.setDeductionInclusion(getDeductionInclusion());
        clone.setDeductionAmount(getDeductionAmount());
        clone.setCompanyName(getCompanyName());
        clone.setProject(getProject());
        clone.setDeductionUdc3(getDeductionUdc3());
        clone.setDeductionUdc4(getDeductionUdc4());
        clone.setDeductionUdc1(getDeductionUdc1());
        clone.setItemId(getItemId());
        clone.setDeductionUdc2(getDeductionUdc2());
        clone.setAccountType(getAccountType());
        clone.setGlString(getGlString());
        clone.setCreatedDate(getCreatedDate());
        clone.setCreatedBy(getCreatedBy());
        clone.setDeductionUdc6(getDeductionUdc6());
        clone.setDeductionUdc5(getDeductionUdc5());
        clone.setGlCompanyName(getGlCompanyName());
        clone.setWorkflowId(getWorkflowId());
        clone.setItemNo(getItemNo());
        clone.setContractId(getContractId());
        clone.setDeductionProgram(getDeductionProgram());
        clone.setBusinessUnitId(getBusinessUnitId());
        clone.setUserId(getUserId());
        clone.setCostCenter(getCostCenter());
        clone.setCompanyId(getCompanyId());
        clone.setOutboundStatus(getOutboundStatus());
        clone.setFuture1(getFuture1());
        clone.setBrandId(getBrandId());
        clone.setDeductionName(getDeductionName());
        clone.setFuture2(getFuture2());
        clone.setWorkflowName(getWorkflowName());
        clone.setGlDate(getGlDate());
        clone.setWorkflowCreatedBy(getWorkflowCreatedBy());
        clone.setGlMonth(getGlMonth());
        clone.setBatchId(getBatchId());
        clone.setAccountCategory(getAccountCategory());
        clone.setGlCompanyNo(getGlCompanyNo());
        clone.setWorkflowApprovedBy(getWorkflowApprovedBy());
        clone.setContractNo(getContractNo());
        clone.setOriginalBatchId(getOriginalBatchId());
        clone.setAdjustmentLevel(getAdjustmentLevel());

        return clone;
    }

    @Override
    public int compareTo(StAdjustmentGtnDetail stAdjustmentGtnDetail) {
        StAdjustmentGtnDetailPK primaryKey = stAdjustmentGtnDetail.getPrimaryKey();

        return getPrimaryKey().compareTo(primaryKey);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof StAdjustmentGtnDetailClp)) {
            return false;
        }

        StAdjustmentGtnDetailClp stAdjustmentGtnDetail = (StAdjustmentGtnDetailClp) obj;

        StAdjustmentGtnDetailPK primaryKey = stAdjustmentGtnDetail.getPrimaryKey();

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

        sb.append("{adjustmentCreatedDate=");
        sb.append(getAdjustmentCreatedDate());
        sb.append(", etlCheckRecord=");
        sb.append(getEtlCheckRecord());
        sb.append(", businessUnitNo=");
        sb.append(getBusinessUnitNo());
        sb.append(", redemptionPeriod=");
        sb.append(getRedemptionPeriod());
        sb.append(", deductionId=");
        sb.append(getDeductionId());
        sb.append(", glYear=");
        sb.append(getGlYear());
        sb.append(", brandName=");
        sb.append(getBrandName());
        sb.append(", modifiedDate=");
        sb.append(getModifiedDate());
        sb.append(", account=");
        sb.append(getAccount());
        sb.append(", source=");
        sb.append(getSource());
        sb.append(", workflowApprovedDate=");
        sb.append(getWorkflowApprovedDate());
        sb.append(", udc6=");
        sb.append(getUdc6());
        sb.append(", businessUnitName=");
        sb.append(getBusinessUnitName());
        sb.append(", udc5=");
        sb.append(getUdc5());
        sb.append(", workflowCreatedDate=");
        sb.append(getWorkflowCreatedDate());
        sb.append(", udc4=");
        sb.append(getUdc4());
        sb.append(", udc3=");
        sb.append(getUdc3());
        sb.append(", udc2=");
        sb.append(getUdc2());
        sb.append(", udc1=");
        sb.append(getUdc1());
        sb.append(", adjustmentType=");
        sb.append(getAdjustmentType());
        sb.append(", modifiedBy=");
        sb.append(getModifiedBy());
        sb.append(", deductionType=");
        sb.append(getDeductionType());
        sb.append(", checkRecord=");
        sb.append(getCheckRecord());
        sb.append(", contractName=");
        sb.append(getContractName());
        sb.append(", deductionRate=");
        sb.append(getDeductionRate());
        sb.append(", deductionCategory=");
        sb.append(getDeductionCategory());
        sb.append(", deductionNo=");
        sb.append(getDeductionNo());
        sb.append(", companyNo=");
        sb.append(getCompanyNo());
        sb.append(", sessionId=");
        sb.append(getSessionId());
        sb.append(", glCompanyId=");
        sb.append(getGlCompanyId());
        sb.append(", itemName=");
        sb.append(getItemName());
        sb.append(", deductionInclusion=");
        sb.append(getDeductionInclusion());
        sb.append(", deductionAmount=");
        sb.append(getDeductionAmount());
        sb.append(", companyName=");
        sb.append(getCompanyName());
        sb.append(", project=");
        sb.append(getProject());
        sb.append(", deductionUdc3=");
        sb.append(getDeductionUdc3());
        sb.append(", deductionUdc4=");
        sb.append(getDeductionUdc4());
        sb.append(", deductionUdc1=");
        sb.append(getDeductionUdc1());
        sb.append(", itemId=");
        sb.append(getItemId());
        sb.append(", deductionUdc2=");
        sb.append(getDeductionUdc2());
        sb.append(", accountType=");
        sb.append(getAccountType());
        sb.append(", glString=");
        sb.append(getGlString());
        sb.append(", createdDate=");
        sb.append(getCreatedDate());
        sb.append(", createdBy=");
        sb.append(getCreatedBy());
        sb.append(", deductionUdc6=");
        sb.append(getDeductionUdc6());
        sb.append(", deductionUdc5=");
        sb.append(getDeductionUdc5());
        sb.append(", glCompanyName=");
        sb.append(getGlCompanyName());
        sb.append(", workflowId=");
        sb.append(getWorkflowId());
        sb.append(", itemNo=");
        sb.append(getItemNo());
        sb.append(", contractId=");
        sb.append(getContractId());
        sb.append(", deductionProgram=");
        sb.append(getDeductionProgram());
        sb.append(", businessUnitId=");
        sb.append(getBusinessUnitId());
        sb.append(", userId=");
        sb.append(getUserId());
        sb.append(", costCenter=");
        sb.append(getCostCenter());
        sb.append(", companyId=");
        sb.append(getCompanyId());
        sb.append(", outboundStatus=");
        sb.append(getOutboundStatus());
        sb.append(", future1=");
        sb.append(getFuture1());
        sb.append(", brandId=");
        sb.append(getBrandId());
        sb.append(", deductionName=");
        sb.append(getDeductionName());
        sb.append(", future2=");
        sb.append(getFuture2());
        sb.append(", workflowName=");
        sb.append(getWorkflowName());
        sb.append(", glDate=");
        sb.append(getGlDate());
        sb.append(", workflowCreatedBy=");
        sb.append(getWorkflowCreatedBy());
        sb.append(", glMonth=");
        sb.append(getGlMonth());
        sb.append(", batchId=");
        sb.append(getBatchId());
        sb.append(", accountCategory=");
        sb.append(getAccountCategory());
        sb.append(", glCompanyNo=");
        sb.append(getGlCompanyNo());
        sb.append(", workflowApprovedBy=");
        sb.append(getWorkflowApprovedBy());
        sb.append(", contractNo=");
        sb.append(getContractNo());
        sb.append(", originalBatchId=");
        sb.append(getOriginalBatchId());
        sb.append(", adjustmentLevel=");
        sb.append(getAdjustmentLevel());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(217);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.parttwo.model.StAdjustmentGtnDetail");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>adjustmentCreatedDate</column-name><column-value><![CDATA[");
        sb.append(getAdjustmentCreatedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>etlCheckRecord</column-name><column-value><![CDATA[");
        sb.append(getEtlCheckRecord());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>businessUnitNo</column-name><column-value><![CDATA[");
        sb.append(getBusinessUnitNo());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>redemptionPeriod</column-name><column-value><![CDATA[");
        sb.append(getRedemptionPeriod());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>deductionId</column-name><column-value><![CDATA[");
        sb.append(getDeductionId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>glYear</column-name><column-value><![CDATA[");
        sb.append(getGlYear());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>brandName</column-name><column-value><![CDATA[");
        sb.append(getBrandName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
        sb.append(getModifiedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>account</column-name><column-value><![CDATA[");
        sb.append(getAccount());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>source</column-name><column-value><![CDATA[");
        sb.append(getSource());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>workflowApprovedDate</column-name><column-value><![CDATA[");
        sb.append(getWorkflowApprovedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>udc6</column-name><column-value><![CDATA[");
        sb.append(getUdc6());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>businessUnitName</column-name><column-value><![CDATA[");
        sb.append(getBusinessUnitName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>udc5</column-name><column-value><![CDATA[");
        sb.append(getUdc5());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>workflowCreatedDate</column-name><column-value><![CDATA[");
        sb.append(getWorkflowCreatedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>udc4</column-name><column-value><![CDATA[");
        sb.append(getUdc4());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>udc3</column-name><column-value><![CDATA[");
        sb.append(getUdc3());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>udc2</column-name><column-value><![CDATA[");
        sb.append(getUdc2());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>udc1</column-name><column-value><![CDATA[");
        sb.append(getUdc1());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>adjustmentType</column-name><column-value><![CDATA[");
        sb.append(getAdjustmentType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedBy</column-name><column-value><![CDATA[");
        sb.append(getModifiedBy());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>deductionType</column-name><column-value><![CDATA[");
        sb.append(getDeductionType());
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
            "<column><column-name>deductionNo</column-name><column-value><![CDATA[");
        sb.append(getDeductionNo());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>companyNo</column-name><column-value><![CDATA[");
        sb.append(getCompanyNo());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>sessionId</column-name><column-value><![CDATA[");
        sb.append(getSessionId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>glCompanyId</column-name><column-value><![CDATA[");
        sb.append(getGlCompanyId());
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
            "<column><column-name>deductionAmount</column-name><column-value><![CDATA[");
        sb.append(getDeductionAmount());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>companyName</column-name><column-value><![CDATA[");
        sb.append(getCompanyName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>project</column-name><column-value><![CDATA[");
        sb.append(getProject());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>deductionUdc3</column-name><column-value><![CDATA[");
        sb.append(getDeductionUdc3());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>deductionUdc4</column-name><column-value><![CDATA[");
        sb.append(getDeductionUdc4());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>deductionUdc1</column-name><column-value><![CDATA[");
        sb.append(getDeductionUdc1());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemId</column-name><column-value><![CDATA[");
        sb.append(getItemId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>deductionUdc2</column-name><column-value><![CDATA[");
        sb.append(getDeductionUdc2());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>accountType</column-name><column-value><![CDATA[");
        sb.append(getAccountType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>glString</column-name><column-value><![CDATA[");
        sb.append(getGlString());
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
            "<column><column-name>deductionUdc6</column-name><column-value><![CDATA[");
        sb.append(getDeductionUdc6());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>deductionUdc5</column-name><column-value><![CDATA[");
        sb.append(getDeductionUdc5());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>glCompanyName</column-name><column-value><![CDATA[");
        sb.append(getGlCompanyName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>workflowId</column-name><column-value><![CDATA[");
        sb.append(getWorkflowId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemNo</column-name><column-value><![CDATA[");
        sb.append(getItemNo());
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
            "<column><column-name>userId</column-name><column-value><![CDATA[");
        sb.append(getUserId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>costCenter</column-name><column-value><![CDATA[");
        sb.append(getCostCenter());
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
            "<column><column-name>future1</column-name><column-value><![CDATA[");
        sb.append(getFuture1());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>brandId</column-name><column-value><![CDATA[");
        sb.append(getBrandId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>deductionName</column-name><column-value><![CDATA[");
        sb.append(getDeductionName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>future2</column-name><column-value><![CDATA[");
        sb.append(getFuture2());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>workflowName</column-name><column-value><![CDATA[");
        sb.append(getWorkflowName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>glDate</column-name><column-value><![CDATA[");
        sb.append(getGlDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>workflowCreatedBy</column-name><column-value><![CDATA[");
        sb.append(getWorkflowCreatedBy());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>glMonth</column-name><column-value><![CDATA[");
        sb.append(getGlMonth());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>batchId</column-name><column-value><![CDATA[");
        sb.append(getBatchId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>accountCategory</column-name><column-value><![CDATA[");
        sb.append(getAccountCategory());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>glCompanyNo</column-name><column-value><![CDATA[");
        sb.append(getGlCompanyNo());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>workflowApprovedBy</column-name><column-value><![CDATA[");
        sb.append(getWorkflowApprovedBy());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>contractNo</column-name><column-value><![CDATA[");
        sb.append(getContractNo());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>originalBatchId</column-name><column-value><![CDATA[");
        sb.append(getOriginalBatchId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>adjustmentLevel</column-name><column-value><![CDATA[");
        sb.append(getAdjustmentLevel());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
