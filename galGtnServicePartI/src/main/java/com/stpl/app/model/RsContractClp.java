package com.stpl.app.model;

import com.stpl.app.service.ClpSerializer;
import com.stpl.app.service.RsContractLocalServiceUtil;

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


public class RsContractClp extends BaseModelImpl<RsContract>
    implements RsContract {
    private String _cfpContractSid;
    private Date _createdDate;
    private String _psContractSid;
    private String _rsName;
    private int _rsStatus;
    private Date _rsStartDate;
    private String _rsTransRefId;
    private String _makePayableTo;
    private int _createdBy;
    private int _rsCategory;
    private int _rsTradeClass;
    private int _contractMasterSid;
    private int _rebateRuleType;
    private int _paymentMethod;
    private Date _rsContractAttachedDate;
    private String _rsAlias;
    private String _formulaMethodId;
    private int _rebateProcessingType;
    private int _rsContractAttachedStatus;
    private int _interestBearingBasis;
    private Date _modifiedDate;
    private String _rsTransRefName;
    private int _rebateProgramType;
    private String _rebatePlanLevel;
    private String _source;
    private String _rsCalendar;
    private int _rsType;
    private String _address1;
    private String _address2;
    private Date _rsEndDate;
    private int _modifiedBy;
    private String _rsTransRefNo;
    private String _zipCode;
    private String _rebateRuleAssociation;
    private int _state;
    private int _rebateFrequency;
    private String _rsDesignation;
    private String _batchId;
    private String _ifpContractSid;
    private int _rsContractSid;
    private int _paymentTerms;
    private String _rsNo;
    private int _rsModelSid;
    private int _rsValidationProfile;
    private String _paymentGracePeriod;
    private int _paymentFrequency;
    private boolean _recordLockStatus;
    private String _rsId;
    private String _city;
    private String _parentRsName;
    private int _interestBearingIndicator;
    private String _parentRsId;
    private String _inboundStatus;
    private int _calculationType;
    private int _calculationLevel;
    private String _calculationRule;
    private String _calculationRuleLevel;
    private String _evaluationRuleType;
    private String _evaluationRuleLevel;
    private String _evaluationRuleOrAssociation;
    private String _deductionInclusion;
    private BaseModel<?> _rsContractRemoteModel;

    public RsContractClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return RsContract.class;
    }

    @Override
    public String getModelClassName() {
        return RsContract.class.getName();
    }

    @Override
    public int getPrimaryKey() {
        return _rsContractSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setRsContractSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _rsContractSid;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Integer) primaryKeyObj).intValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("cfpContractSid", getCfpContractSid());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("psContractSid", getPsContractSid());
        attributes.put("rsName", getRsName());
        attributes.put("rsStatus", getRsStatus());
        attributes.put("rsStartDate", getRsStartDate());
        attributes.put("rsTransRefId", getRsTransRefId());
        attributes.put("makePayableTo", getMakePayableTo());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("rsCategory", getRsCategory());
        attributes.put("rsTradeClass", getRsTradeClass());
        attributes.put("contractMasterSid", getContractMasterSid());
        attributes.put("rebateRuleType", getRebateRuleType());
        attributes.put("paymentMethod", getPaymentMethod());
        attributes.put("rsContractAttachedDate", getRsContractAttachedDate());
        attributes.put("rsAlias", getRsAlias());
        attributes.put("formulaMethodId", getFormulaMethodId());
        attributes.put("rebateProcessingType", getRebateProcessingType());
        attributes.put("rsContractAttachedStatus", getRsContractAttachedStatus());
        attributes.put("interestBearingBasis", getInterestBearingBasis());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("rsTransRefName", getRsTransRefName());
        attributes.put("rebateProgramType", getRebateProgramType());
        attributes.put("rebatePlanLevel", getRebatePlanLevel());
        attributes.put("source", getSource());
        attributes.put("rsCalendar", getRsCalendar());
        attributes.put("rsType", getRsType());
        attributes.put("address1", getAddress1());
        attributes.put("address2", getAddress2());
        attributes.put("rsEndDate", getRsEndDate());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("rsTransRefNo", getRsTransRefNo());
        attributes.put("zipCode", getZipCode());
        attributes.put("rebateRuleAssociation", getRebateRuleAssociation());
        attributes.put("state", getState());
        attributes.put("rebateFrequency", getRebateFrequency());
        attributes.put("rsDesignation", getRsDesignation());
        attributes.put("batchId", getBatchId());
        attributes.put("ifpContractSid", getIfpContractSid());
        attributes.put("rsContractSid", getRsContractSid());
        attributes.put("paymentTerms", getPaymentTerms());
        attributes.put("rsNo", getRsNo());
        attributes.put("rsModelSid", getRsModelSid());
        attributes.put("rsValidationProfile", getRsValidationProfile());
        attributes.put("paymentGracePeriod", getPaymentGracePeriod());
        attributes.put("paymentFrequency", getPaymentFrequency());
        attributes.put("recordLockStatus", getRecordLockStatus());
        attributes.put("rsId", getRsId());
        attributes.put("city", getCity());
        attributes.put("parentRsName", getParentRsName());
        attributes.put("interestBearingIndicator", getInterestBearingIndicator());
        attributes.put("parentRsId", getParentRsId());
        attributes.put("inboundStatus", getInboundStatus());
        attributes.put("calculationType", getCalculationType());
        attributes.put("calculationLevel", getCalculationLevel());
        attributes.put("calculationRule", getCalculationRule());
        attributes.put("calculationRuleLevel", getCalculationRuleLevel());
        attributes.put("evaluationRuleType", getEvaluationRuleType());
        attributes.put("evaluationRuleLevel", getEvaluationRuleLevel());
        attributes.put("evaluationRuleOrAssociation",
            getEvaluationRuleOrAssociation());
        attributes.put("deductionInclusion", getDeductionInclusion());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        String cfpContractSid = (String) attributes.get("cfpContractSid");

        if (cfpContractSid != null) {
            setCfpContractSid(cfpContractSid);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        String psContractSid = (String) attributes.get("psContractSid");

        if (psContractSid != null) {
            setPsContractSid(psContractSid);
        }

        String rsName = (String) attributes.get("rsName");

        if (rsName != null) {
            setRsName(rsName);
        }

        Integer rsStatus = (Integer) attributes.get("rsStatus");

        if (rsStatus != null) {
            setRsStatus(rsStatus);
        }

        Date rsStartDate = (Date) attributes.get("rsStartDate");

        if (rsStartDate != null) {
            setRsStartDate(rsStartDate);
        }

        String rsTransRefId = (String) attributes.get("rsTransRefId");

        if (rsTransRefId != null) {
            setRsTransRefId(rsTransRefId);
        }

        String makePayableTo = (String) attributes.get("makePayableTo");

        if (makePayableTo != null) {
            setMakePayableTo(makePayableTo);
        }

        Integer createdBy = (Integer) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        Integer rsCategory = (Integer) attributes.get("rsCategory");

        if (rsCategory != null) {
            setRsCategory(rsCategory);
        }

        Integer rsTradeClass = (Integer) attributes.get("rsTradeClass");

        if (rsTradeClass != null) {
            setRsTradeClass(rsTradeClass);
        }

        Integer contractMasterSid = (Integer) attributes.get(
                "contractMasterSid");

        if (contractMasterSid != null) {
            setContractMasterSid(contractMasterSid);
        }

        Integer rebateRuleType = (Integer) attributes.get("rebateRuleType");

        if (rebateRuleType != null) {
            setRebateRuleType(rebateRuleType);
        }

        Integer paymentMethod = (Integer) attributes.get("paymentMethod");

        if (paymentMethod != null) {
            setPaymentMethod(paymentMethod);
        }

        Date rsContractAttachedDate = (Date) attributes.get(
                "rsContractAttachedDate");

        if (rsContractAttachedDate != null) {
            setRsContractAttachedDate(rsContractAttachedDate);
        }

        String rsAlias = (String) attributes.get("rsAlias");

        if (rsAlias != null) {
            setRsAlias(rsAlias);
        }

        String formulaMethodId = (String) attributes.get("formulaMethodId");

        if (formulaMethodId != null) {
            setFormulaMethodId(formulaMethodId);
        }

        Integer rebateProcessingType = (Integer) attributes.get(
                "rebateProcessingType");

        if (rebateProcessingType != null) {
            setRebateProcessingType(rebateProcessingType);
        }

        Integer rsContractAttachedStatus = (Integer) attributes.get(
                "rsContractAttachedStatus");

        if (rsContractAttachedStatus != null) {
            setRsContractAttachedStatus(rsContractAttachedStatus);
        }

        Integer interestBearingBasis = (Integer) attributes.get(
                "interestBearingBasis");

        if (interestBearingBasis != null) {
            setInterestBearingBasis(interestBearingBasis);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        String rsTransRefName = (String) attributes.get("rsTransRefName");

        if (rsTransRefName != null) {
            setRsTransRefName(rsTransRefName);
        }

        Integer rebateProgramType = (Integer) attributes.get(
                "rebateProgramType");

        if (rebateProgramType != null) {
            setRebateProgramType(rebateProgramType);
        }

        String rebatePlanLevel = (String) attributes.get("rebatePlanLevel");

        if (rebatePlanLevel != null) {
            setRebatePlanLevel(rebatePlanLevel);
        }

        String source = (String) attributes.get("source");

        if (source != null) {
            setSource(source);
        }

        String rsCalendar = (String) attributes.get("rsCalendar");

        if (rsCalendar != null) {
            setRsCalendar(rsCalendar);
        }

        Integer rsType = (Integer) attributes.get("rsType");

        if (rsType != null) {
            setRsType(rsType);
        }

        String address1 = (String) attributes.get("address1");

        if (address1 != null) {
            setAddress1(address1);
        }

        String address2 = (String) attributes.get("address2");

        if (address2 != null) {
            setAddress2(address2);
        }

        Date rsEndDate = (Date) attributes.get("rsEndDate");

        if (rsEndDate != null) {
            setRsEndDate(rsEndDate);
        }

        Integer modifiedBy = (Integer) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        String rsTransRefNo = (String) attributes.get("rsTransRefNo");

        if (rsTransRefNo != null) {
            setRsTransRefNo(rsTransRefNo);
        }

        String zipCode = (String) attributes.get("zipCode");

        if (zipCode != null) {
            setZipCode(zipCode);
        }

        String rebateRuleAssociation = (String) attributes.get(
                "rebateRuleAssociation");

        if (rebateRuleAssociation != null) {
            setRebateRuleAssociation(rebateRuleAssociation);
        }

        Integer state = (Integer) attributes.get("state");

        if (state != null) {
            setState(state);
        }

        Integer rebateFrequency = (Integer) attributes.get("rebateFrequency");

        if (rebateFrequency != null) {
            setRebateFrequency(rebateFrequency);
        }

        String rsDesignation = (String) attributes.get("rsDesignation");

        if (rsDesignation != null) {
            setRsDesignation(rsDesignation);
        }

        String batchId = (String) attributes.get("batchId");

        if (batchId != null) {
            setBatchId(batchId);
        }

        String ifpContractSid = (String) attributes.get("ifpContractSid");

        if (ifpContractSid != null) {
            setIfpContractSid(ifpContractSid);
        }

        Integer rsContractSid = (Integer) attributes.get("rsContractSid");

        if (rsContractSid != null) {
            setRsContractSid(rsContractSid);
        }

        Integer paymentTerms = (Integer) attributes.get("paymentTerms");

        if (paymentTerms != null) {
            setPaymentTerms(paymentTerms);
        }

        String rsNo = (String) attributes.get("rsNo");

        if (rsNo != null) {
            setRsNo(rsNo);
        }

        Integer rsModelSid = (Integer) attributes.get("rsModelSid");

        if (rsModelSid != null) {
            setRsModelSid(rsModelSid);
        }

        Integer rsValidationProfile = (Integer) attributes.get(
                "rsValidationProfile");

        if (rsValidationProfile != null) {
            setRsValidationProfile(rsValidationProfile);
        }

        String paymentGracePeriod = (String) attributes.get(
                "paymentGracePeriod");

        if (paymentGracePeriod != null) {
            setPaymentGracePeriod(paymentGracePeriod);
        }

        Integer paymentFrequency = (Integer) attributes.get("paymentFrequency");

        if (paymentFrequency != null) {
            setPaymentFrequency(paymentFrequency);
        }

        Boolean recordLockStatus = (Boolean) attributes.get("recordLockStatus");

        if (recordLockStatus != null) {
            setRecordLockStatus(recordLockStatus);
        }

        String rsId = (String) attributes.get("rsId");

        if (rsId != null) {
            setRsId(rsId);
        }

        String city = (String) attributes.get("city");

        if (city != null) {
            setCity(city);
        }

        String parentRsName = (String) attributes.get("parentRsName");

        if (parentRsName != null) {
            setParentRsName(parentRsName);
        }

        Integer interestBearingIndicator = (Integer) attributes.get(
                "interestBearingIndicator");

        if (interestBearingIndicator != null) {
            setInterestBearingIndicator(interestBearingIndicator);
        }

        String parentRsId = (String) attributes.get("parentRsId");

        if (parentRsId != null) {
            setParentRsId(parentRsId);
        }

        String inboundStatus = (String) attributes.get("inboundStatus");

        if (inboundStatus != null) {
            setInboundStatus(inboundStatus);
        }

        Integer calculationType = (Integer) attributes.get("calculationType");

        if (calculationType != null) {
            setCalculationType(calculationType);
        }

        Integer calculationLevel = (Integer) attributes.get("calculationLevel");

        if (calculationLevel != null) {
            setCalculationLevel(calculationLevel);
        }

        String calculationRule = (String) attributes.get("calculationRule");

        if (calculationRule != null) {
            setCalculationRule(calculationRule);
        }

        String calculationRuleLevel = (String) attributes.get(
                "calculationRuleLevel");

        if (calculationRuleLevel != null) {
            setCalculationRuleLevel(calculationRuleLevel);
        }

        String evaluationRuleType = (String) attributes.get(
                "evaluationRuleType");

        if (evaluationRuleType != null) {
            setEvaluationRuleType(evaluationRuleType);
        }

        String evaluationRuleLevel = (String) attributes.get(
                "evaluationRuleLevel");

        if (evaluationRuleLevel != null) {
            setEvaluationRuleLevel(evaluationRuleLevel);
        }

        String evaluationRuleOrAssociation = (String) attributes.get(
                "evaluationRuleOrAssociation");

        if (evaluationRuleOrAssociation != null) {
            setEvaluationRuleOrAssociation(evaluationRuleOrAssociation);
        }

        String deductionInclusion = (String) attributes.get(
                "deductionInclusion");

        if (deductionInclusion != null) {
            setDeductionInclusion(deductionInclusion);
        }
    }

    @Override
    public String getCfpContractSid() {
        return _cfpContractSid;
    }

    @Override
    public void setCfpContractSid(String cfpContractSid) {
        _cfpContractSid = cfpContractSid;

        if (_rsContractRemoteModel != null) {
            try {
                Class<?> clazz = _rsContractRemoteModel.getClass();

                Method method = clazz.getMethod("setCfpContractSid",
                        String.class);

                method.invoke(_rsContractRemoteModel, cfpContractSid);
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

        if (_rsContractRemoteModel != null) {
            try {
                Class<?> clazz = _rsContractRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedDate", Date.class);

                method.invoke(_rsContractRemoteModel, createdDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getPsContractSid() {
        return _psContractSid;
    }

    @Override
    public void setPsContractSid(String psContractSid) {
        _psContractSid = psContractSid;

        if (_rsContractRemoteModel != null) {
            try {
                Class<?> clazz = _rsContractRemoteModel.getClass();

                Method method = clazz.getMethod("setPsContractSid", String.class);

                method.invoke(_rsContractRemoteModel, psContractSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getRsName() {
        return _rsName;
    }

    @Override
    public void setRsName(String rsName) {
        _rsName = rsName;

        if (_rsContractRemoteModel != null) {
            try {
                Class<?> clazz = _rsContractRemoteModel.getClass();

                Method method = clazz.getMethod("setRsName", String.class);

                method.invoke(_rsContractRemoteModel, rsName);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getRsStatus() {
        return _rsStatus;
    }

    @Override
    public void setRsStatus(int rsStatus) {
        _rsStatus = rsStatus;

        if (_rsContractRemoteModel != null) {
            try {
                Class<?> clazz = _rsContractRemoteModel.getClass();

                Method method = clazz.getMethod("setRsStatus", int.class);

                method.invoke(_rsContractRemoteModel, rsStatus);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getRsStartDate() {
        return _rsStartDate;
    }

    @Override
    public void setRsStartDate(Date rsStartDate) {
        _rsStartDate = rsStartDate;

        if (_rsContractRemoteModel != null) {
            try {
                Class<?> clazz = _rsContractRemoteModel.getClass();

                Method method = clazz.getMethod("setRsStartDate", Date.class);

                method.invoke(_rsContractRemoteModel, rsStartDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getRsTransRefId() {
        return _rsTransRefId;
    }

    @Override
    public void setRsTransRefId(String rsTransRefId) {
        _rsTransRefId = rsTransRefId;

        if (_rsContractRemoteModel != null) {
            try {
                Class<?> clazz = _rsContractRemoteModel.getClass();

                Method method = clazz.getMethod("setRsTransRefId", String.class);

                method.invoke(_rsContractRemoteModel, rsTransRefId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getMakePayableTo() {
        return _makePayableTo;
    }

    @Override
    public void setMakePayableTo(String makePayableTo) {
        _makePayableTo = makePayableTo;

        if (_rsContractRemoteModel != null) {
            try {
                Class<?> clazz = _rsContractRemoteModel.getClass();

                Method method = clazz.getMethod("setMakePayableTo", String.class);

                method.invoke(_rsContractRemoteModel, makePayableTo);
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

        if (_rsContractRemoteModel != null) {
            try {
                Class<?> clazz = _rsContractRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedBy", int.class);

                method.invoke(_rsContractRemoteModel, createdBy);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getRsCategory() {
        return _rsCategory;
    }

    @Override
    public void setRsCategory(int rsCategory) {
        _rsCategory = rsCategory;

        if (_rsContractRemoteModel != null) {
            try {
                Class<?> clazz = _rsContractRemoteModel.getClass();

                Method method = clazz.getMethod("setRsCategory", int.class);

                method.invoke(_rsContractRemoteModel, rsCategory);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getRsTradeClass() {
        return _rsTradeClass;
    }

    @Override
    public void setRsTradeClass(int rsTradeClass) {
        _rsTradeClass = rsTradeClass;

        if (_rsContractRemoteModel != null) {
            try {
                Class<?> clazz = _rsContractRemoteModel.getClass();

                Method method = clazz.getMethod("setRsTradeClass", int.class);

                method.invoke(_rsContractRemoteModel, rsTradeClass);
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

        if (_rsContractRemoteModel != null) {
            try {
                Class<?> clazz = _rsContractRemoteModel.getClass();

                Method method = clazz.getMethod("setContractMasterSid",
                        int.class);

                method.invoke(_rsContractRemoteModel, contractMasterSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getRebateRuleType() {
        return _rebateRuleType;
    }

    @Override
    public void setRebateRuleType(int rebateRuleType) {
        _rebateRuleType = rebateRuleType;

        if (_rsContractRemoteModel != null) {
            try {
                Class<?> clazz = _rsContractRemoteModel.getClass();

                Method method = clazz.getMethod("setRebateRuleType", int.class);

                method.invoke(_rsContractRemoteModel, rebateRuleType);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getPaymentMethod() {
        return _paymentMethod;
    }

    @Override
    public void setPaymentMethod(int paymentMethod) {
        _paymentMethod = paymentMethod;

        if (_rsContractRemoteModel != null) {
            try {
                Class<?> clazz = _rsContractRemoteModel.getClass();

                Method method = clazz.getMethod("setPaymentMethod", int.class);

                method.invoke(_rsContractRemoteModel, paymentMethod);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getRsContractAttachedDate() {
        return _rsContractAttachedDate;
    }

    @Override
    public void setRsContractAttachedDate(Date rsContractAttachedDate) {
        _rsContractAttachedDate = rsContractAttachedDate;

        if (_rsContractRemoteModel != null) {
            try {
                Class<?> clazz = _rsContractRemoteModel.getClass();

                Method method = clazz.getMethod("setRsContractAttachedDate",
                        Date.class);

                method.invoke(_rsContractRemoteModel, rsContractAttachedDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getRsAlias() {
        return _rsAlias;
    }

    @Override
    public void setRsAlias(String rsAlias) {
        _rsAlias = rsAlias;

        if (_rsContractRemoteModel != null) {
            try {
                Class<?> clazz = _rsContractRemoteModel.getClass();

                Method method = clazz.getMethod("setRsAlias", String.class);

                method.invoke(_rsContractRemoteModel, rsAlias);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getFormulaMethodId() {
        return _formulaMethodId;
    }

    @Override
    public void setFormulaMethodId(String formulaMethodId) {
        _formulaMethodId = formulaMethodId;

        if (_rsContractRemoteModel != null) {
            try {
                Class<?> clazz = _rsContractRemoteModel.getClass();

                Method method = clazz.getMethod("setFormulaMethodId",
                        String.class);

                method.invoke(_rsContractRemoteModel, formulaMethodId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getRebateProcessingType() {
        return _rebateProcessingType;
    }

    @Override
    public void setRebateProcessingType(int rebateProcessingType) {
        _rebateProcessingType = rebateProcessingType;

        if (_rsContractRemoteModel != null) {
            try {
                Class<?> clazz = _rsContractRemoteModel.getClass();

                Method method = clazz.getMethod("setRebateProcessingType",
                        int.class);

                method.invoke(_rsContractRemoteModel, rebateProcessingType);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getRsContractAttachedStatus() {
        return _rsContractAttachedStatus;
    }

    @Override
    public void setRsContractAttachedStatus(int rsContractAttachedStatus) {
        _rsContractAttachedStatus = rsContractAttachedStatus;

        if (_rsContractRemoteModel != null) {
            try {
                Class<?> clazz = _rsContractRemoteModel.getClass();

                Method method = clazz.getMethod("setRsContractAttachedStatus",
                        int.class);

                method.invoke(_rsContractRemoteModel, rsContractAttachedStatus);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getInterestBearingBasis() {
        return _interestBearingBasis;
    }

    @Override
    public void setInterestBearingBasis(int interestBearingBasis) {
        _interestBearingBasis = interestBearingBasis;

        if (_rsContractRemoteModel != null) {
            try {
                Class<?> clazz = _rsContractRemoteModel.getClass();

                Method method = clazz.getMethod("setInterestBearingBasis",
                        int.class);

                method.invoke(_rsContractRemoteModel, interestBearingBasis);
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

        if (_rsContractRemoteModel != null) {
            try {
                Class<?> clazz = _rsContractRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedDate", Date.class);

                method.invoke(_rsContractRemoteModel, modifiedDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getRsTransRefName() {
        return _rsTransRefName;
    }

    @Override
    public void setRsTransRefName(String rsTransRefName) {
        _rsTransRefName = rsTransRefName;

        if (_rsContractRemoteModel != null) {
            try {
                Class<?> clazz = _rsContractRemoteModel.getClass();

                Method method = clazz.getMethod("setRsTransRefName",
                        String.class);

                method.invoke(_rsContractRemoteModel, rsTransRefName);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getRebateProgramType() {
        return _rebateProgramType;
    }

    @Override
    public void setRebateProgramType(int rebateProgramType) {
        _rebateProgramType = rebateProgramType;

        if (_rsContractRemoteModel != null) {
            try {
                Class<?> clazz = _rsContractRemoteModel.getClass();

                Method method = clazz.getMethod("setRebateProgramType",
                        int.class);

                method.invoke(_rsContractRemoteModel, rebateProgramType);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getRebatePlanLevel() {
        return _rebatePlanLevel;
    }

    @Override
    public void setRebatePlanLevel(String rebatePlanLevel) {
        _rebatePlanLevel = rebatePlanLevel;

        if (_rsContractRemoteModel != null) {
            try {
                Class<?> clazz = _rsContractRemoteModel.getClass();

                Method method = clazz.getMethod("setRebatePlanLevel",
                        String.class);

                method.invoke(_rsContractRemoteModel, rebatePlanLevel);
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

        if (_rsContractRemoteModel != null) {
            try {
                Class<?> clazz = _rsContractRemoteModel.getClass();

                Method method = clazz.getMethod("setSource", String.class);

                method.invoke(_rsContractRemoteModel, source);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getRsCalendar() {
        return _rsCalendar;
    }

    @Override
    public void setRsCalendar(String rsCalendar) {
        _rsCalendar = rsCalendar;

        if (_rsContractRemoteModel != null) {
            try {
                Class<?> clazz = _rsContractRemoteModel.getClass();

                Method method = clazz.getMethod("setRsCalendar", String.class);

                method.invoke(_rsContractRemoteModel, rsCalendar);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getRsType() {
        return _rsType;
    }

    @Override
    public void setRsType(int rsType) {
        _rsType = rsType;

        if (_rsContractRemoteModel != null) {
            try {
                Class<?> clazz = _rsContractRemoteModel.getClass();

                Method method = clazz.getMethod("setRsType", int.class);

                method.invoke(_rsContractRemoteModel, rsType);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getAddress1() {
        return _address1;
    }

    @Override
    public void setAddress1(String address1) {
        _address1 = address1;

        if (_rsContractRemoteModel != null) {
            try {
                Class<?> clazz = _rsContractRemoteModel.getClass();

                Method method = clazz.getMethod("setAddress1", String.class);

                method.invoke(_rsContractRemoteModel, address1);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getAddress2() {
        return _address2;
    }

    @Override
    public void setAddress2(String address2) {
        _address2 = address2;

        if (_rsContractRemoteModel != null) {
            try {
                Class<?> clazz = _rsContractRemoteModel.getClass();

                Method method = clazz.getMethod("setAddress2", String.class);

                method.invoke(_rsContractRemoteModel, address2);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getRsEndDate() {
        return _rsEndDate;
    }

    @Override
    public void setRsEndDate(Date rsEndDate) {
        _rsEndDate = rsEndDate;

        if (_rsContractRemoteModel != null) {
            try {
                Class<?> clazz = _rsContractRemoteModel.getClass();

                Method method = clazz.getMethod("setRsEndDate", Date.class);

                method.invoke(_rsContractRemoteModel, rsEndDate);
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

        if (_rsContractRemoteModel != null) {
            try {
                Class<?> clazz = _rsContractRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedBy", int.class);

                method.invoke(_rsContractRemoteModel, modifiedBy);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getRsTransRefNo() {
        return _rsTransRefNo;
    }

    @Override
    public void setRsTransRefNo(String rsTransRefNo) {
        _rsTransRefNo = rsTransRefNo;

        if (_rsContractRemoteModel != null) {
            try {
                Class<?> clazz = _rsContractRemoteModel.getClass();

                Method method = clazz.getMethod("setRsTransRefNo", String.class);

                method.invoke(_rsContractRemoteModel, rsTransRefNo);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getZipCode() {
        return _zipCode;
    }

    @Override
    public void setZipCode(String zipCode) {
        _zipCode = zipCode;

        if (_rsContractRemoteModel != null) {
            try {
                Class<?> clazz = _rsContractRemoteModel.getClass();

                Method method = clazz.getMethod("setZipCode", String.class);

                method.invoke(_rsContractRemoteModel, zipCode);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getRebateRuleAssociation() {
        return _rebateRuleAssociation;
    }

    @Override
    public void setRebateRuleAssociation(String rebateRuleAssociation) {
        _rebateRuleAssociation = rebateRuleAssociation;

        if (_rsContractRemoteModel != null) {
            try {
                Class<?> clazz = _rsContractRemoteModel.getClass();

                Method method = clazz.getMethod("setRebateRuleAssociation",
                        String.class);

                method.invoke(_rsContractRemoteModel, rebateRuleAssociation);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getState() {
        return _state;
    }

    @Override
    public void setState(int state) {
        _state = state;

        if (_rsContractRemoteModel != null) {
            try {
                Class<?> clazz = _rsContractRemoteModel.getClass();

                Method method = clazz.getMethod("setState", int.class);

                method.invoke(_rsContractRemoteModel, state);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getRebateFrequency() {
        return _rebateFrequency;
    }

    @Override
    public void setRebateFrequency(int rebateFrequency) {
        _rebateFrequency = rebateFrequency;

        if (_rsContractRemoteModel != null) {
            try {
                Class<?> clazz = _rsContractRemoteModel.getClass();

                Method method = clazz.getMethod("setRebateFrequency", int.class);

                method.invoke(_rsContractRemoteModel, rebateFrequency);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getRsDesignation() {
        return _rsDesignation;
    }

    @Override
    public void setRsDesignation(String rsDesignation) {
        _rsDesignation = rsDesignation;

        if (_rsContractRemoteModel != null) {
            try {
                Class<?> clazz = _rsContractRemoteModel.getClass();

                Method method = clazz.getMethod("setRsDesignation", String.class);

                method.invoke(_rsContractRemoteModel, rsDesignation);
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

        if (_rsContractRemoteModel != null) {
            try {
                Class<?> clazz = _rsContractRemoteModel.getClass();

                Method method = clazz.getMethod("setBatchId", String.class);

                method.invoke(_rsContractRemoteModel, batchId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getIfpContractSid() {
        return _ifpContractSid;
    }

    @Override
    public void setIfpContractSid(String ifpContractSid) {
        _ifpContractSid = ifpContractSid;

        if (_rsContractRemoteModel != null) {
            try {
                Class<?> clazz = _rsContractRemoteModel.getClass();

                Method method = clazz.getMethod("setIfpContractSid",
                        String.class);

                method.invoke(_rsContractRemoteModel, ifpContractSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getRsContractSid() {
        return _rsContractSid;
    }

    @Override
    public void setRsContractSid(int rsContractSid) {
        _rsContractSid = rsContractSid;

        if (_rsContractRemoteModel != null) {
            try {
                Class<?> clazz = _rsContractRemoteModel.getClass();

                Method method = clazz.getMethod("setRsContractSid", int.class);

                method.invoke(_rsContractRemoteModel, rsContractSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getPaymentTerms() {
        return _paymentTerms;
    }

    @Override
    public void setPaymentTerms(int paymentTerms) {
        _paymentTerms = paymentTerms;

        if (_rsContractRemoteModel != null) {
            try {
                Class<?> clazz = _rsContractRemoteModel.getClass();

                Method method = clazz.getMethod("setPaymentTerms", int.class);

                method.invoke(_rsContractRemoteModel, paymentTerms);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getRsNo() {
        return _rsNo;
    }

    @Override
    public void setRsNo(String rsNo) {
        _rsNo = rsNo;

        if (_rsContractRemoteModel != null) {
            try {
                Class<?> clazz = _rsContractRemoteModel.getClass();

                Method method = clazz.getMethod("setRsNo", String.class);

                method.invoke(_rsContractRemoteModel, rsNo);
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

        if (_rsContractRemoteModel != null) {
            try {
                Class<?> clazz = _rsContractRemoteModel.getClass();

                Method method = clazz.getMethod("setRsModelSid", int.class);

                method.invoke(_rsContractRemoteModel, rsModelSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getRsValidationProfile() {
        return _rsValidationProfile;
    }

    @Override
    public void setRsValidationProfile(int rsValidationProfile) {
        _rsValidationProfile = rsValidationProfile;

        if (_rsContractRemoteModel != null) {
            try {
                Class<?> clazz = _rsContractRemoteModel.getClass();

                Method method = clazz.getMethod("setRsValidationProfile",
                        int.class);

                method.invoke(_rsContractRemoteModel, rsValidationProfile);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getPaymentGracePeriod() {
        return _paymentGracePeriod;
    }

    @Override
    public void setPaymentGracePeriod(String paymentGracePeriod) {
        _paymentGracePeriod = paymentGracePeriod;

        if (_rsContractRemoteModel != null) {
            try {
                Class<?> clazz = _rsContractRemoteModel.getClass();

                Method method = clazz.getMethod("setPaymentGracePeriod",
                        String.class);

                method.invoke(_rsContractRemoteModel, paymentGracePeriod);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getPaymentFrequency() {
        return _paymentFrequency;
    }

    @Override
    public void setPaymentFrequency(int paymentFrequency) {
        _paymentFrequency = paymentFrequency;

        if (_rsContractRemoteModel != null) {
            try {
                Class<?> clazz = _rsContractRemoteModel.getClass();

                Method method = clazz.getMethod("setPaymentFrequency", int.class);

                method.invoke(_rsContractRemoteModel, paymentFrequency);
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

        if (_rsContractRemoteModel != null) {
            try {
                Class<?> clazz = _rsContractRemoteModel.getClass();

                Method method = clazz.getMethod("setRecordLockStatus",
                        boolean.class);

                method.invoke(_rsContractRemoteModel, recordLockStatus);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getRsId() {
        return _rsId;
    }

    @Override
    public void setRsId(String rsId) {
        _rsId = rsId;

        if (_rsContractRemoteModel != null) {
            try {
                Class<?> clazz = _rsContractRemoteModel.getClass();

                Method method = clazz.getMethod("setRsId", String.class);

                method.invoke(_rsContractRemoteModel, rsId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getCity() {
        return _city;
    }

    @Override
    public void setCity(String city) {
        _city = city;

        if (_rsContractRemoteModel != null) {
            try {
                Class<?> clazz = _rsContractRemoteModel.getClass();

                Method method = clazz.getMethod("setCity", String.class);

                method.invoke(_rsContractRemoteModel, city);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getParentRsName() {
        return _parentRsName;
    }

    @Override
    public void setParentRsName(String parentRsName) {
        _parentRsName = parentRsName;

        if (_rsContractRemoteModel != null) {
            try {
                Class<?> clazz = _rsContractRemoteModel.getClass();

                Method method = clazz.getMethod("setParentRsName", String.class);

                method.invoke(_rsContractRemoteModel, parentRsName);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getInterestBearingIndicator() {
        return _interestBearingIndicator;
    }

    @Override
    public void setInterestBearingIndicator(int interestBearingIndicator) {
        _interestBearingIndicator = interestBearingIndicator;

        if (_rsContractRemoteModel != null) {
            try {
                Class<?> clazz = _rsContractRemoteModel.getClass();

                Method method = clazz.getMethod("setInterestBearingIndicator",
                        int.class);

                method.invoke(_rsContractRemoteModel, interestBearingIndicator);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getParentRsId() {
        return _parentRsId;
    }

    @Override
    public void setParentRsId(String parentRsId) {
        _parentRsId = parentRsId;

        if (_rsContractRemoteModel != null) {
            try {
                Class<?> clazz = _rsContractRemoteModel.getClass();

                Method method = clazz.getMethod("setParentRsId", String.class);

                method.invoke(_rsContractRemoteModel, parentRsId);
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

        if (_rsContractRemoteModel != null) {
            try {
                Class<?> clazz = _rsContractRemoteModel.getClass();

                Method method = clazz.getMethod("setInboundStatus", String.class);

                method.invoke(_rsContractRemoteModel, inboundStatus);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getCalculationType() {
        return _calculationType;
    }

    @Override
    public void setCalculationType(int calculationType) {
        _calculationType = calculationType;

        if (_rsContractRemoteModel != null) {
            try {
                Class<?> clazz = _rsContractRemoteModel.getClass();

                Method method = clazz.getMethod("setCalculationType", int.class);

                method.invoke(_rsContractRemoteModel, calculationType);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getCalculationLevel() {
        return _calculationLevel;
    }

    @Override
    public void setCalculationLevel(int calculationLevel) {
        _calculationLevel = calculationLevel;

        if (_rsContractRemoteModel != null) {
            try {
                Class<?> clazz = _rsContractRemoteModel.getClass();

                Method method = clazz.getMethod("setCalculationLevel", int.class);

                method.invoke(_rsContractRemoteModel, calculationLevel);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getCalculationRule() {
        return _calculationRule;
    }

    @Override
    public void setCalculationRule(String calculationRule) {
        _calculationRule = calculationRule;

        if (_rsContractRemoteModel != null) {
            try {
                Class<?> clazz = _rsContractRemoteModel.getClass();

                Method method = clazz.getMethod("setCalculationRule",
                        String.class);

                method.invoke(_rsContractRemoteModel, calculationRule);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getCalculationRuleLevel() {
        return _calculationRuleLevel;
    }

    @Override
    public void setCalculationRuleLevel(String calculationRuleLevel) {
        _calculationRuleLevel = calculationRuleLevel;

        if (_rsContractRemoteModel != null) {
            try {
                Class<?> clazz = _rsContractRemoteModel.getClass();

                Method method = clazz.getMethod("setCalculationRuleLevel",
                        String.class);

                method.invoke(_rsContractRemoteModel, calculationRuleLevel);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getEvaluationRuleType() {
        return _evaluationRuleType;
    }

    @Override
    public void setEvaluationRuleType(String evaluationRuleType) {
        _evaluationRuleType = evaluationRuleType;

        if (_rsContractRemoteModel != null) {
            try {
                Class<?> clazz = _rsContractRemoteModel.getClass();

                Method method = clazz.getMethod("setEvaluationRuleType",
                        String.class);

                method.invoke(_rsContractRemoteModel, evaluationRuleType);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getEvaluationRuleLevel() {
        return _evaluationRuleLevel;
    }

    @Override
    public void setEvaluationRuleLevel(String evaluationRuleLevel) {
        _evaluationRuleLevel = evaluationRuleLevel;

        if (_rsContractRemoteModel != null) {
            try {
                Class<?> clazz = _rsContractRemoteModel.getClass();

                Method method = clazz.getMethod("setEvaluationRuleLevel",
                        String.class);

                method.invoke(_rsContractRemoteModel, evaluationRuleLevel);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getEvaluationRuleOrAssociation() {
        return _evaluationRuleOrAssociation;
    }

    @Override
    public void setEvaluationRuleOrAssociation(
        String evaluationRuleOrAssociation) {
        _evaluationRuleOrAssociation = evaluationRuleOrAssociation;

        if (_rsContractRemoteModel != null) {
            try {
                Class<?> clazz = _rsContractRemoteModel.getClass();

                Method method = clazz.getMethod("setEvaluationRuleOrAssociation",
                        String.class);

                method.invoke(_rsContractRemoteModel,
                    evaluationRuleOrAssociation);
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

        if (_rsContractRemoteModel != null) {
            try {
                Class<?> clazz = _rsContractRemoteModel.getClass();

                Method method = clazz.getMethod("setDeductionInclusion",
                        String.class);

                method.invoke(_rsContractRemoteModel, deductionInclusion);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getRsContractRemoteModel() {
        return _rsContractRemoteModel;
    }

    public void setRsContractRemoteModel(BaseModel<?> rsContractRemoteModel) {
        _rsContractRemoteModel = rsContractRemoteModel;
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

        Class<?> remoteModelClass = _rsContractRemoteModel.getClass();

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

        Object returnValue = method.invoke(_rsContractRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            RsContractLocalServiceUtil.addRsContract(this);
        } else {
            RsContractLocalServiceUtil.updateRsContract(this);
        }
    }

    @Override
    public RsContract toEscapedModel() {
        return (RsContract) ProxyUtil.newProxyInstance(RsContract.class.getClassLoader(),
            new Class[] { RsContract.class }, new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        RsContractClp clone = new RsContractClp();

        clone.setCfpContractSid(getCfpContractSid());
        clone.setCreatedDate(getCreatedDate());
        clone.setPsContractSid(getPsContractSid());
        clone.setRsName(getRsName());
        clone.setRsStatus(getRsStatus());
        clone.setRsStartDate(getRsStartDate());
        clone.setRsTransRefId(getRsTransRefId());
        clone.setMakePayableTo(getMakePayableTo());
        clone.setCreatedBy(getCreatedBy());
        clone.setRsCategory(getRsCategory());
        clone.setRsTradeClass(getRsTradeClass());
        clone.setContractMasterSid(getContractMasterSid());
        clone.setRebateRuleType(getRebateRuleType());
        clone.setPaymentMethod(getPaymentMethod());
        clone.setRsContractAttachedDate(getRsContractAttachedDate());
        clone.setRsAlias(getRsAlias());
        clone.setFormulaMethodId(getFormulaMethodId());
        clone.setRebateProcessingType(getRebateProcessingType());
        clone.setRsContractAttachedStatus(getRsContractAttachedStatus());
        clone.setInterestBearingBasis(getInterestBearingBasis());
        clone.setModifiedDate(getModifiedDate());
        clone.setRsTransRefName(getRsTransRefName());
        clone.setRebateProgramType(getRebateProgramType());
        clone.setRebatePlanLevel(getRebatePlanLevel());
        clone.setSource(getSource());
        clone.setRsCalendar(getRsCalendar());
        clone.setRsType(getRsType());
        clone.setAddress1(getAddress1());
        clone.setAddress2(getAddress2());
        clone.setRsEndDate(getRsEndDate());
        clone.setModifiedBy(getModifiedBy());
        clone.setRsTransRefNo(getRsTransRefNo());
        clone.setZipCode(getZipCode());
        clone.setRebateRuleAssociation(getRebateRuleAssociation());
        clone.setState(getState());
        clone.setRebateFrequency(getRebateFrequency());
        clone.setRsDesignation(getRsDesignation());
        clone.setBatchId(getBatchId());
        clone.setIfpContractSid(getIfpContractSid());
        clone.setRsContractSid(getRsContractSid());
        clone.setPaymentTerms(getPaymentTerms());
        clone.setRsNo(getRsNo());
        clone.setRsModelSid(getRsModelSid());
        clone.setRsValidationProfile(getRsValidationProfile());
        clone.setPaymentGracePeriod(getPaymentGracePeriod());
        clone.setPaymentFrequency(getPaymentFrequency());
        clone.setRecordLockStatus(getRecordLockStatus());
        clone.setRsId(getRsId());
        clone.setCity(getCity());
        clone.setParentRsName(getParentRsName());
        clone.setInterestBearingIndicator(getInterestBearingIndicator());
        clone.setParentRsId(getParentRsId());
        clone.setInboundStatus(getInboundStatus());
        clone.setCalculationType(getCalculationType());
        clone.setCalculationLevel(getCalculationLevel());
        clone.setCalculationRule(getCalculationRule());
        clone.setCalculationRuleLevel(getCalculationRuleLevel());
        clone.setEvaluationRuleType(getEvaluationRuleType());
        clone.setEvaluationRuleLevel(getEvaluationRuleLevel());
        clone.setEvaluationRuleOrAssociation(getEvaluationRuleOrAssociation());
        clone.setDeductionInclusion(getDeductionInclusion());

        return clone;
    }

    @Override
    public int compareTo(RsContract rsContract) {
        int primaryKey = rsContract.getPrimaryKey();

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

        if (!(obj instanceof RsContractClp)) {
            return false;
        }

        RsContractClp rsContract = (RsContractClp) obj;

        int primaryKey = rsContract.getPrimaryKey();

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
        StringBundler sb = new StringBundler(123);

        sb.append("{cfpContractSid=");
        sb.append(getCfpContractSid());
        sb.append(", createdDate=");
        sb.append(getCreatedDate());
        sb.append(", psContractSid=");
        sb.append(getPsContractSid());
        sb.append(", rsName=");
        sb.append(getRsName());
        sb.append(", rsStatus=");
        sb.append(getRsStatus());
        sb.append(", rsStartDate=");
        sb.append(getRsStartDate());
        sb.append(", rsTransRefId=");
        sb.append(getRsTransRefId());
        sb.append(", makePayableTo=");
        sb.append(getMakePayableTo());
        sb.append(", createdBy=");
        sb.append(getCreatedBy());
        sb.append(", rsCategory=");
        sb.append(getRsCategory());
        sb.append(", rsTradeClass=");
        sb.append(getRsTradeClass());
        sb.append(", contractMasterSid=");
        sb.append(getContractMasterSid());
        sb.append(", rebateRuleType=");
        sb.append(getRebateRuleType());
        sb.append(", paymentMethod=");
        sb.append(getPaymentMethod());
        sb.append(", rsContractAttachedDate=");
        sb.append(getRsContractAttachedDate());
        sb.append(", rsAlias=");
        sb.append(getRsAlias());
        sb.append(", formulaMethodId=");
        sb.append(getFormulaMethodId());
        sb.append(", rebateProcessingType=");
        sb.append(getRebateProcessingType());
        sb.append(", rsContractAttachedStatus=");
        sb.append(getRsContractAttachedStatus());
        sb.append(", interestBearingBasis=");
        sb.append(getInterestBearingBasis());
        sb.append(", modifiedDate=");
        sb.append(getModifiedDate());
        sb.append(", rsTransRefName=");
        sb.append(getRsTransRefName());
        sb.append(", rebateProgramType=");
        sb.append(getRebateProgramType());
        sb.append(", rebatePlanLevel=");
        sb.append(getRebatePlanLevel());
        sb.append(", source=");
        sb.append(getSource());
        sb.append(", rsCalendar=");
        sb.append(getRsCalendar());
        sb.append(", rsType=");
        sb.append(getRsType());
        sb.append(", address1=");
        sb.append(getAddress1());
        sb.append(", address2=");
        sb.append(getAddress2());
        sb.append(", rsEndDate=");
        sb.append(getRsEndDate());
        sb.append(", modifiedBy=");
        sb.append(getModifiedBy());
        sb.append(", rsTransRefNo=");
        sb.append(getRsTransRefNo());
        sb.append(", zipCode=");
        sb.append(getZipCode());
        sb.append(", rebateRuleAssociation=");
        sb.append(getRebateRuleAssociation());
        sb.append(", state=");
        sb.append(getState());
        sb.append(", rebateFrequency=");
        sb.append(getRebateFrequency());
        sb.append(", rsDesignation=");
        sb.append(getRsDesignation());
        sb.append(", batchId=");
        sb.append(getBatchId());
        sb.append(", ifpContractSid=");
        sb.append(getIfpContractSid());
        sb.append(", rsContractSid=");
        sb.append(getRsContractSid());
        sb.append(", paymentTerms=");
        sb.append(getPaymentTerms());
        sb.append(", rsNo=");
        sb.append(getRsNo());
        sb.append(", rsModelSid=");
        sb.append(getRsModelSid());
        sb.append(", rsValidationProfile=");
        sb.append(getRsValidationProfile());
        sb.append(", paymentGracePeriod=");
        sb.append(getPaymentGracePeriod());
        sb.append(", paymentFrequency=");
        sb.append(getPaymentFrequency());
        sb.append(", recordLockStatus=");
        sb.append(getRecordLockStatus());
        sb.append(", rsId=");
        sb.append(getRsId());
        sb.append(", city=");
        sb.append(getCity());
        sb.append(", parentRsName=");
        sb.append(getParentRsName());
        sb.append(", interestBearingIndicator=");
        sb.append(getInterestBearingIndicator());
        sb.append(", parentRsId=");
        sb.append(getParentRsId());
        sb.append(", inboundStatus=");
        sb.append(getInboundStatus());
        sb.append(", calculationType=");
        sb.append(getCalculationType());
        sb.append(", calculationLevel=");
        sb.append(getCalculationLevel());
        sb.append(", calculationRule=");
        sb.append(getCalculationRule());
        sb.append(", calculationRuleLevel=");
        sb.append(getCalculationRuleLevel());
        sb.append(", evaluationRuleType=");
        sb.append(getEvaluationRuleType());
        sb.append(", evaluationRuleLevel=");
        sb.append(getEvaluationRuleLevel());
        sb.append(", evaluationRuleOrAssociation=");
        sb.append(getEvaluationRuleOrAssociation());
        sb.append(", deductionInclusion=");
        sb.append(getDeductionInclusion());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(187);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.RsContract");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>cfpContractSid</column-name><column-value><![CDATA[");
        sb.append(getCfpContractSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>createdDate</column-name><column-value><![CDATA[");
        sb.append(getCreatedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>psContractSid</column-name><column-value><![CDATA[");
        sb.append(getPsContractSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>rsName</column-name><column-value><![CDATA[");
        sb.append(getRsName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>rsStatus</column-name><column-value><![CDATA[");
        sb.append(getRsStatus());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>rsStartDate</column-name><column-value><![CDATA[");
        sb.append(getRsStartDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>rsTransRefId</column-name><column-value><![CDATA[");
        sb.append(getRsTransRefId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>makePayableTo</column-name><column-value><![CDATA[");
        sb.append(getMakePayableTo());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>createdBy</column-name><column-value><![CDATA[");
        sb.append(getCreatedBy());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>rsCategory</column-name><column-value><![CDATA[");
        sb.append(getRsCategory());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>rsTradeClass</column-name><column-value><![CDATA[");
        sb.append(getRsTradeClass());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>contractMasterSid</column-name><column-value><![CDATA[");
        sb.append(getContractMasterSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>rebateRuleType</column-name><column-value><![CDATA[");
        sb.append(getRebateRuleType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>paymentMethod</column-name><column-value><![CDATA[");
        sb.append(getPaymentMethod());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>rsContractAttachedDate</column-name><column-value><![CDATA[");
        sb.append(getRsContractAttachedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>rsAlias</column-name><column-value><![CDATA[");
        sb.append(getRsAlias());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>formulaMethodId</column-name><column-value><![CDATA[");
        sb.append(getFormulaMethodId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>rebateProcessingType</column-name><column-value><![CDATA[");
        sb.append(getRebateProcessingType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>rsContractAttachedStatus</column-name><column-value><![CDATA[");
        sb.append(getRsContractAttachedStatus());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>interestBearingBasis</column-name><column-value><![CDATA[");
        sb.append(getInterestBearingBasis());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
        sb.append(getModifiedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>rsTransRefName</column-name><column-value><![CDATA[");
        sb.append(getRsTransRefName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>rebateProgramType</column-name><column-value><![CDATA[");
        sb.append(getRebateProgramType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>rebatePlanLevel</column-name><column-value><![CDATA[");
        sb.append(getRebatePlanLevel());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>source</column-name><column-value><![CDATA[");
        sb.append(getSource());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>rsCalendar</column-name><column-value><![CDATA[");
        sb.append(getRsCalendar());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>rsType</column-name><column-value><![CDATA[");
        sb.append(getRsType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>address1</column-name><column-value><![CDATA[");
        sb.append(getAddress1());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>address2</column-name><column-value><![CDATA[");
        sb.append(getAddress2());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>rsEndDate</column-name><column-value><![CDATA[");
        sb.append(getRsEndDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedBy</column-name><column-value><![CDATA[");
        sb.append(getModifiedBy());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>rsTransRefNo</column-name><column-value><![CDATA[");
        sb.append(getRsTransRefNo());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>zipCode</column-name><column-value><![CDATA[");
        sb.append(getZipCode());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>rebateRuleAssociation</column-name><column-value><![CDATA[");
        sb.append(getRebateRuleAssociation());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>state</column-name><column-value><![CDATA[");
        sb.append(getState());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>rebateFrequency</column-name><column-value><![CDATA[");
        sb.append(getRebateFrequency());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>rsDesignation</column-name><column-value><![CDATA[");
        sb.append(getRsDesignation());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>batchId</column-name><column-value><![CDATA[");
        sb.append(getBatchId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>ifpContractSid</column-name><column-value><![CDATA[");
        sb.append(getIfpContractSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>rsContractSid</column-name><column-value><![CDATA[");
        sb.append(getRsContractSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>paymentTerms</column-name><column-value><![CDATA[");
        sb.append(getPaymentTerms());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>rsNo</column-name><column-value><![CDATA[");
        sb.append(getRsNo());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>rsModelSid</column-name><column-value><![CDATA[");
        sb.append(getRsModelSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>rsValidationProfile</column-name><column-value><![CDATA[");
        sb.append(getRsValidationProfile());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>paymentGracePeriod</column-name><column-value><![CDATA[");
        sb.append(getPaymentGracePeriod());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>paymentFrequency</column-name><column-value><![CDATA[");
        sb.append(getPaymentFrequency());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>recordLockStatus</column-name><column-value><![CDATA[");
        sb.append(getRecordLockStatus());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>rsId</column-name><column-value><![CDATA[");
        sb.append(getRsId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>city</column-name><column-value><![CDATA[");
        sb.append(getCity());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>parentRsName</column-name><column-value><![CDATA[");
        sb.append(getParentRsName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>interestBearingIndicator</column-name><column-value><![CDATA[");
        sb.append(getInterestBearingIndicator());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>parentRsId</column-name><column-value><![CDATA[");
        sb.append(getParentRsId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>inboundStatus</column-name><column-value><![CDATA[");
        sb.append(getInboundStatus());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>calculationType</column-name><column-value><![CDATA[");
        sb.append(getCalculationType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>calculationLevel</column-name><column-value><![CDATA[");
        sb.append(getCalculationLevel());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>calculationRule</column-name><column-value><![CDATA[");
        sb.append(getCalculationRule());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>calculationRuleLevel</column-name><column-value><![CDATA[");
        sb.append(getCalculationRuleLevel());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>evaluationRuleType</column-name><column-value><![CDATA[");
        sb.append(getEvaluationRuleType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>evaluationRuleLevel</column-name><column-value><![CDATA[");
        sb.append(getEvaluationRuleLevel());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>evaluationRuleOrAssociation</column-name><column-value><![CDATA[");
        sb.append(getEvaluationRuleOrAssociation());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>deductionInclusion</column-name><column-value><![CDATA[");
        sb.append(getDeductionInclusion());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
