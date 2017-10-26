package com.stpl.app.model;

import com.stpl.app.service.ClpSerializer;
import com.stpl.app.service.RsModelLocalServiceUtil;

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


public class RsModelClp extends BaseModelImpl<RsModel> implements RsModel {
    private String _formulaMethodId;
    private int _calculationType;
    private int _rsStatus;
    private Date _rsEndDate;
    private String _rsTransRefNo;
    private int _paymentFrequency;
    private Date _modifiedDate;
    private int _calculationLevel;
    private String _rsName;
    private String _source;
    private String _rebatePlanLevel;
    private int _rebateRuleType;
    private String _inboundStatus;
    private int _modifiedBy;
    private String _rsAlias;
    private String _rsId;
    private int _paymentMethod;
    private String _zipCode;
    private String _rebateRuleAssociation;
    private String _parentRsName;
    private String _internalNotes;
    private int _paymentLevel;
    private boolean _recordLockStatus;
    private int _rsCalendar;
    private int _rebateProgramType;
    private int _interestBearingBasis;
    private int _rsModelSid;
    private String _city;
    private int _rebateProcessingType;
    private String _rsNo;
    private int _state;
    private int _rebateFrequency;
    private String _parentRsId;
    private int _rsType;
    private Date _rsStartDate;
    private String _makePayableTo;
    private int _rsDesignation;
    private String _rsTransRefName;
    private int _createdBy;
    private Date _createdDate;
    private String _rsTransRefId;
    private int _rsCategory;
    private int _rsTradeClass;
    private int _interestBearingIndicator;
    private int _manfCompanyMasterSid;
    private int _paymentTerms;
    private String _address1;
    private String _address2;
    private int _rsValidationProfile;
    private String _paymentGracePeriod;
    private String _batchId;
    private String _evaluationRuleType;
    private String _evaluationRuleLevel;
    private String _evaluationRuleOrAssociation;
    private String _calculationRuleLevel;
    private String _calculationRule;
    private String _deductionInclusion;
    private BaseModel<?> _rsModelRemoteModel;

    public RsModelClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return RsModel.class;
    }

    @Override
    public String getModelClassName() {
        return RsModel.class.getName();
    }

    @Override
    public int getPrimaryKey() {
        return _rsModelSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setRsModelSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _rsModelSid;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Integer) primaryKeyObj).intValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("formulaMethodId", getFormulaMethodId());
        attributes.put("calculationType", getCalculationType());
        attributes.put("rsStatus", getRsStatus());
        attributes.put("rsEndDate", getRsEndDate());
        attributes.put("rsTransRefNo", getRsTransRefNo());
        attributes.put("paymentFrequency", getPaymentFrequency());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("calculationLevel", getCalculationLevel());
        attributes.put("rsName", getRsName());
        attributes.put("source", getSource());
        attributes.put("rebatePlanLevel", getRebatePlanLevel());
        attributes.put("rebateRuleType", getRebateRuleType());
        attributes.put("inboundStatus", getInboundStatus());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("rsAlias", getRsAlias());
        attributes.put("rsId", getRsId());
        attributes.put("paymentMethod", getPaymentMethod());
        attributes.put("zipCode", getZipCode());
        attributes.put("rebateRuleAssociation", getRebateRuleAssociation());
        attributes.put("parentRsName", getParentRsName());
        attributes.put("internalNotes", getInternalNotes());
        attributes.put("paymentLevel", getPaymentLevel());
        attributes.put("recordLockStatus", getRecordLockStatus());
        attributes.put("rsCalendar", getRsCalendar());
        attributes.put("rebateProgramType", getRebateProgramType());
        attributes.put("interestBearingBasis", getInterestBearingBasis());
        attributes.put("rsModelSid", getRsModelSid());
        attributes.put("city", getCity());
        attributes.put("rebateProcessingType", getRebateProcessingType());
        attributes.put("rsNo", getRsNo());
        attributes.put("state", getState());
        attributes.put("rebateFrequency", getRebateFrequency());
        attributes.put("parentRsId", getParentRsId());
        attributes.put("rsType", getRsType());
        attributes.put("rsStartDate", getRsStartDate());
        attributes.put("makePayableTo", getMakePayableTo());
        attributes.put("rsDesignation", getRsDesignation());
        attributes.put("rsTransRefName", getRsTransRefName());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("rsTransRefId", getRsTransRefId());
        attributes.put("rsCategory", getRsCategory());
        attributes.put("rsTradeClass", getRsTradeClass());
        attributes.put("interestBearingIndicator", getInterestBearingIndicator());
        attributes.put("manfCompanyMasterSid", getManfCompanyMasterSid());
        attributes.put("paymentTerms", getPaymentTerms());
        attributes.put("address1", getAddress1());
        attributes.put("address2", getAddress2());
        attributes.put("rsValidationProfile", getRsValidationProfile());
        attributes.put("paymentGracePeriod", getPaymentGracePeriod());
        attributes.put("batchId", getBatchId());
        attributes.put("evaluationRuleType", getEvaluationRuleType());
        attributes.put("evaluationRuleLevel", getEvaluationRuleLevel());
        attributes.put("evaluationRuleOrAssociation",
            getEvaluationRuleOrAssociation());
        attributes.put("calculationRuleLevel", getCalculationRuleLevel());
        attributes.put("calculationRule", getCalculationRule());
        attributes.put("deductionInclusion", getDeductionInclusion());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        String formulaMethodId = (String) attributes.get("formulaMethodId");

        if (formulaMethodId != null) {
            setFormulaMethodId(formulaMethodId);
        }

        Integer calculationType = (Integer) attributes.get("calculationType");

        if (calculationType != null) {
            setCalculationType(calculationType);
        }

        Integer rsStatus = (Integer) attributes.get("rsStatus");

        if (rsStatus != null) {
            setRsStatus(rsStatus);
        }

        Date rsEndDate = (Date) attributes.get("rsEndDate");

        if (rsEndDate != null) {
            setRsEndDate(rsEndDate);
        }

        String rsTransRefNo = (String) attributes.get("rsTransRefNo");

        if (rsTransRefNo != null) {
            setRsTransRefNo(rsTransRefNo);
        }

        Integer paymentFrequency = (Integer) attributes.get("paymentFrequency");

        if (paymentFrequency != null) {
            setPaymentFrequency(paymentFrequency);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        Integer calculationLevel = (Integer) attributes.get("calculationLevel");

        if (calculationLevel != null) {
            setCalculationLevel(calculationLevel);
        }

        String rsName = (String) attributes.get("rsName");

        if (rsName != null) {
            setRsName(rsName);
        }

        String source = (String) attributes.get("source");

        if (source != null) {
            setSource(source);
        }

        String rebatePlanLevel = (String) attributes.get("rebatePlanLevel");

        if (rebatePlanLevel != null) {
            setRebatePlanLevel(rebatePlanLevel);
        }

        Integer rebateRuleType = (Integer) attributes.get("rebateRuleType");

        if (rebateRuleType != null) {
            setRebateRuleType(rebateRuleType);
        }

        String inboundStatus = (String) attributes.get("inboundStatus");

        if (inboundStatus != null) {
            setInboundStatus(inboundStatus);
        }

        Integer modifiedBy = (Integer) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        String rsAlias = (String) attributes.get("rsAlias");

        if (rsAlias != null) {
            setRsAlias(rsAlias);
        }

        String rsId = (String) attributes.get("rsId");

        if (rsId != null) {
            setRsId(rsId);
        }

        Integer paymentMethod = (Integer) attributes.get("paymentMethod");

        if (paymentMethod != null) {
            setPaymentMethod(paymentMethod);
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

        String parentRsName = (String) attributes.get("parentRsName");

        if (parentRsName != null) {
            setParentRsName(parentRsName);
        }

        String internalNotes = (String) attributes.get("internalNotes");

        if (internalNotes != null) {
            setInternalNotes(internalNotes);
        }

        Integer paymentLevel = (Integer) attributes.get("paymentLevel");

        if (paymentLevel != null) {
            setPaymentLevel(paymentLevel);
        }

        Boolean recordLockStatus = (Boolean) attributes.get("recordLockStatus");

        if (recordLockStatus != null) {
            setRecordLockStatus(recordLockStatus);
        }

        Integer rsCalendar = (Integer) attributes.get("rsCalendar");

        if (rsCalendar != null) {
            setRsCalendar(rsCalendar);
        }

        Integer rebateProgramType = (Integer) attributes.get(
                "rebateProgramType");

        if (rebateProgramType != null) {
            setRebateProgramType(rebateProgramType);
        }

        Integer interestBearingBasis = (Integer) attributes.get(
                "interestBearingBasis");

        if (interestBearingBasis != null) {
            setInterestBearingBasis(interestBearingBasis);
        }

        Integer rsModelSid = (Integer) attributes.get("rsModelSid");

        if (rsModelSid != null) {
            setRsModelSid(rsModelSid);
        }

        String city = (String) attributes.get("city");

        if (city != null) {
            setCity(city);
        }

        Integer rebateProcessingType = (Integer) attributes.get(
                "rebateProcessingType");

        if (rebateProcessingType != null) {
            setRebateProcessingType(rebateProcessingType);
        }

        String rsNo = (String) attributes.get("rsNo");

        if (rsNo != null) {
            setRsNo(rsNo);
        }

        Integer state = (Integer) attributes.get("state");

        if (state != null) {
            setState(state);
        }

        Integer rebateFrequency = (Integer) attributes.get("rebateFrequency");

        if (rebateFrequency != null) {
            setRebateFrequency(rebateFrequency);
        }

        String parentRsId = (String) attributes.get("parentRsId");

        if (parentRsId != null) {
            setParentRsId(parentRsId);
        }

        Integer rsType = (Integer) attributes.get("rsType");

        if (rsType != null) {
            setRsType(rsType);
        }

        Date rsStartDate = (Date) attributes.get("rsStartDate");

        if (rsStartDate != null) {
            setRsStartDate(rsStartDate);
        }

        String makePayableTo = (String) attributes.get("makePayableTo");

        if (makePayableTo != null) {
            setMakePayableTo(makePayableTo);
        }

        Integer rsDesignation = (Integer) attributes.get("rsDesignation");

        if (rsDesignation != null) {
            setRsDesignation(rsDesignation);
        }

        String rsTransRefName = (String) attributes.get("rsTransRefName");

        if (rsTransRefName != null) {
            setRsTransRefName(rsTransRefName);
        }

        Integer createdBy = (Integer) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        String rsTransRefId = (String) attributes.get("rsTransRefId");

        if (rsTransRefId != null) {
            setRsTransRefId(rsTransRefId);
        }

        Integer rsCategory = (Integer) attributes.get("rsCategory");

        if (rsCategory != null) {
            setRsCategory(rsCategory);
        }

        Integer rsTradeClass = (Integer) attributes.get("rsTradeClass");

        if (rsTradeClass != null) {
            setRsTradeClass(rsTradeClass);
        }

        Integer interestBearingIndicator = (Integer) attributes.get(
                "interestBearingIndicator");

        if (interestBearingIndicator != null) {
            setInterestBearingIndicator(interestBearingIndicator);
        }

        Integer manfCompanyMasterSid = (Integer) attributes.get(
                "manfCompanyMasterSid");

        if (manfCompanyMasterSid != null) {
            setManfCompanyMasterSid(manfCompanyMasterSid);
        }

        Integer paymentTerms = (Integer) attributes.get("paymentTerms");

        if (paymentTerms != null) {
            setPaymentTerms(paymentTerms);
        }

        String address1 = (String) attributes.get("address1");

        if (address1 != null) {
            setAddress1(address1);
        }

        String address2 = (String) attributes.get("address2");

        if (address2 != null) {
            setAddress2(address2);
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

        String batchId = (String) attributes.get("batchId");

        if (batchId != null) {
            setBatchId(batchId);
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

        String calculationRuleLevel = (String) attributes.get(
                "calculationRuleLevel");

        if (calculationRuleLevel != null) {
            setCalculationRuleLevel(calculationRuleLevel);
        }

        String calculationRule = (String) attributes.get("calculationRule");

        if (calculationRule != null) {
            setCalculationRule(calculationRule);
        }

        String deductionInclusion = (String) attributes.get(
                "deductionInclusion");

        if (deductionInclusion != null) {
            setDeductionInclusion(deductionInclusion);
        }
    }

    @Override
    public String getFormulaMethodId() {
        return _formulaMethodId;
    }

    @Override
    public void setFormulaMethodId(String formulaMethodId) {
        _formulaMethodId = formulaMethodId;

        if (_rsModelRemoteModel != null) {
            try {
                Class<?> clazz = _rsModelRemoteModel.getClass();

                Method method = clazz.getMethod("setFormulaMethodId",
                        String.class);

                method.invoke(_rsModelRemoteModel, formulaMethodId);
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

        if (_rsModelRemoteModel != null) {
            try {
                Class<?> clazz = _rsModelRemoteModel.getClass();

                Method method = clazz.getMethod("setCalculationType", int.class);

                method.invoke(_rsModelRemoteModel, calculationType);
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

        if (_rsModelRemoteModel != null) {
            try {
                Class<?> clazz = _rsModelRemoteModel.getClass();

                Method method = clazz.getMethod("setRsStatus", int.class);

                method.invoke(_rsModelRemoteModel, rsStatus);
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

        if (_rsModelRemoteModel != null) {
            try {
                Class<?> clazz = _rsModelRemoteModel.getClass();

                Method method = clazz.getMethod("setRsEndDate", Date.class);

                method.invoke(_rsModelRemoteModel, rsEndDate);
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

        if (_rsModelRemoteModel != null) {
            try {
                Class<?> clazz = _rsModelRemoteModel.getClass();

                Method method = clazz.getMethod("setRsTransRefNo", String.class);

                method.invoke(_rsModelRemoteModel, rsTransRefNo);
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

        if (_rsModelRemoteModel != null) {
            try {
                Class<?> clazz = _rsModelRemoteModel.getClass();

                Method method = clazz.getMethod("setPaymentFrequency", int.class);

                method.invoke(_rsModelRemoteModel, paymentFrequency);
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

        if (_rsModelRemoteModel != null) {
            try {
                Class<?> clazz = _rsModelRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedDate", Date.class);

                method.invoke(_rsModelRemoteModel, modifiedDate);
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

        if (_rsModelRemoteModel != null) {
            try {
                Class<?> clazz = _rsModelRemoteModel.getClass();

                Method method = clazz.getMethod("setCalculationLevel", int.class);

                method.invoke(_rsModelRemoteModel, calculationLevel);
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

        if (_rsModelRemoteModel != null) {
            try {
                Class<?> clazz = _rsModelRemoteModel.getClass();

                Method method = clazz.getMethod("setRsName", String.class);

                method.invoke(_rsModelRemoteModel, rsName);
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

        if (_rsModelRemoteModel != null) {
            try {
                Class<?> clazz = _rsModelRemoteModel.getClass();

                Method method = clazz.getMethod("setSource", String.class);

                method.invoke(_rsModelRemoteModel, source);
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

        if (_rsModelRemoteModel != null) {
            try {
                Class<?> clazz = _rsModelRemoteModel.getClass();

                Method method = clazz.getMethod("setRebatePlanLevel",
                        String.class);

                method.invoke(_rsModelRemoteModel, rebatePlanLevel);
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

        if (_rsModelRemoteModel != null) {
            try {
                Class<?> clazz = _rsModelRemoteModel.getClass();

                Method method = clazz.getMethod("setRebateRuleType", int.class);

                method.invoke(_rsModelRemoteModel, rebateRuleType);
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

        if (_rsModelRemoteModel != null) {
            try {
                Class<?> clazz = _rsModelRemoteModel.getClass();

                Method method = clazz.getMethod("setInboundStatus", String.class);

                method.invoke(_rsModelRemoteModel, inboundStatus);
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

        if (_rsModelRemoteModel != null) {
            try {
                Class<?> clazz = _rsModelRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedBy", int.class);

                method.invoke(_rsModelRemoteModel, modifiedBy);
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

        if (_rsModelRemoteModel != null) {
            try {
                Class<?> clazz = _rsModelRemoteModel.getClass();

                Method method = clazz.getMethod("setRsAlias", String.class);

                method.invoke(_rsModelRemoteModel, rsAlias);
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

        if (_rsModelRemoteModel != null) {
            try {
                Class<?> clazz = _rsModelRemoteModel.getClass();

                Method method = clazz.getMethod("setRsId", String.class);

                method.invoke(_rsModelRemoteModel, rsId);
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

        if (_rsModelRemoteModel != null) {
            try {
                Class<?> clazz = _rsModelRemoteModel.getClass();

                Method method = clazz.getMethod("setPaymentMethod", int.class);

                method.invoke(_rsModelRemoteModel, paymentMethod);
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

        if (_rsModelRemoteModel != null) {
            try {
                Class<?> clazz = _rsModelRemoteModel.getClass();

                Method method = clazz.getMethod("setZipCode", String.class);

                method.invoke(_rsModelRemoteModel, zipCode);
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

        if (_rsModelRemoteModel != null) {
            try {
                Class<?> clazz = _rsModelRemoteModel.getClass();

                Method method = clazz.getMethod("setRebateRuleAssociation",
                        String.class);

                method.invoke(_rsModelRemoteModel, rebateRuleAssociation);
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

        if (_rsModelRemoteModel != null) {
            try {
                Class<?> clazz = _rsModelRemoteModel.getClass();

                Method method = clazz.getMethod("setParentRsName", String.class);

                method.invoke(_rsModelRemoteModel, parentRsName);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getInternalNotes() {
        return _internalNotes;
    }

    @Override
    public void setInternalNotes(String internalNotes) {
        _internalNotes = internalNotes;

        if (_rsModelRemoteModel != null) {
            try {
                Class<?> clazz = _rsModelRemoteModel.getClass();

                Method method = clazz.getMethod("setInternalNotes", String.class);

                method.invoke(_rsModelRemoteModel, internalNotes);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getPaymentLevel() {
        return _paymentLevel;
    }

    @Override
    public void setPaymentLevel(int paymentLevel) {
        _paymentLevel = paymentLevel;

        if (_rsModelRemoteModel != null) {
            try {
                Class<?> clazz = _rsModelRemoteModel.getClass();

                Method method = clazz.getMethod("setPaymentLevel", int.class);

                method.invoke(_rsModelRemoteModel, paymentLevel);
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

        if (_rsModelRemoteModel != null) {
            try {
                Class<?> clazz = _rsModelRemoteModel.getClass();

                Method method = clazz.getMethod("setRecordLockStatus",
                        boolean.class);

                method.invoke(_rsModelRemoteModel, recordLockStatus);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getRsCalendar() {
        return _rsCalendar;
    }

    @Override
    public void setRsCalendar(int rsCalendar) {
        _rsCalendar = rsCalendar;

        if (_rsModelRemoteModel != null) {
            try {
                Class<?> clazz = _rsModelRemoteModel.getClass();

                Method method = clazz.getMethod("setRsCalendar", int.class);

                method.invoke(_rsModelRemoteModel, rsCalendar);
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

        if (_rsModelRemoteModel != null) {
            try {
                Class<?> clazz = _rsModelRemoteModel.getClass();

                Method method = clazz.getMethod("setRebateProgramType",
                        int.class);

                method.invoke(_rsModelRemoteModel, rebateProgramType);
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

        if (_rsModelRemoteModel != null) {
            try {
                Class<?> clazz = _rsModelRemoteModel.getClass();

                Method method = clazz.getMethod("setInterestBearingBasis",
                        int.class);

                method.invoke(_rsModelRemoteModel, interestBearingBasis);
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

        if (_rsModelRemoteModel != null) {
            try {
                Class<?> clazz = _rsModelRemoteModel.getClass();

                Method method = clazz.getMethod("setRsModelSid", int.class);

                method.invoke(_rsModelRemoteModel, rsModelSid);
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

        if (_rsModelRemoteModel != null) {
            try {
                Class<?> clazz = _rsModelRemoteModel.getClass();

                Method method = clazz.getMethod("setCity", String.class);

                method.invoke(_rsModelRemoteModel, city);
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

        if (_rsModelRemoteModel != null) {
            try {
                Class<?> clazz = _rsModelRemoteModel.getClass();

                Method method = clazz.getMethod("setRebateProcessingType",
                        int.class);

                method.invoke(_rsModelRemoteModel, rebateProcessingType);
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

        if (_rsModelRemoteModel != null) {
            try {
                Class<?> clazz = _rsModelRemoteModel.getClass();

                Method method = clazz.getMethod("setRsNo", String.class);

                method.invoke(_rsModelRemoteModel, rsNo);
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

        if (_rsModelRemoteModel != null) {
            try {
                Class<?> clazz = _rsModelRemoteModel.getClass();

                Method method = clazz.getMethod("setState", int.class);

                method.invoke(_rsModelRemoteModel, state);
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

        if (_rsModelRemoteModel != null) {
            try {
                Class<?> clazz = _rsModelRemoteModel.getClass();

                Method method = clazz.getMethod("setRebateFrequency", int.class);

                method.invoke(_rsModelRemoteModel, rebateFrequency);
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

        if (_rsModelRemoteModel != null) {
            try {
                Class<?> clazz = _rsModelRemoteModel.getClass();

                Method method = clazz.getMethod("setParentRsId", String.class);

                method.invoke(_rsModelRemoteModel, parentRsId);
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

        if (_rsModelRemoteModel != null) {
            try {
                Class<?> clazz = _rsModelRemoteModel.getClass();

                Method method = clazz.getMethod("setRsType", int.class);

                method.invoke(_rsModelRemoteModel, rsType);
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

        if (_rsModelRemoteModel != null) {
            try {
                Class<?> clazz = _rsModelRemoteModel.getClass();

                Method method = clazz.getMethod("setRsStartDate", Date.class);

                method.invoke(_rsModelRemoteModel, rsStartDate);
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

        if (_rsModelRemoteModel != null) {
            try {
                Class<?> clazz = _rsModelRemoteModel.getClass();

                Method method = clazz.getMethod("setMakePayableTo", String.class);

                method.invoke(_rsModelRemoteModel, makePayableTo);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getRsDesignation() {
        return _rsDesignation;
    }

    @Override
    public void setRsDesignation(int rsDesignation) {
        _rsDesignation = rsDesignation;

        if (_rsModelRemoteModel != null) {
            try {
                Class<?> clazz = _rsModelRemoteModel.getClass();

                Method method = clazz.getMethod("setRsDesignation", int.class);

                method.invoke(_rsModelRemoteModel, rsDesignation);
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

        if (_rsModelRemoteModel != null) {
            try {
                Class<?> clazz = _rsModelRemoteModel.getClass();

                Method method = clazz.getMethod("setRsTransRefName",
                        String.class);

                method.invoke(_rsModelRemoteModel, rsTransRefName);
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

        if (_rsModelRemoteModel != null) {
            try {
                Class<?> clazz = _rsModelRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedBy", int.class);

                method.invoke(_rsModelRemoteModel, createdBy);
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

        if (_rsModelRemoteModel != null) {
            try {
                Class<?> clazz = _rsModelRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedDate", Date.class);

                method.invoke(_rsModelRemoteModel, createdDate);
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

        if (_rsModelRemoteModel != null) {
            try {
                Class<?> clazz = _rsModelRemoteModel.getClass();

                Method method = clazz.getMethod("setRsTransRefId", String.class);

                method.invoke(_rsModelRemoteModel, rsTransRefId);
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

        if (_rsModelRemoteModel != null) {
            try {
                Class<?> clazz = _rsModelRemoteModel.getClass();

                Method method = clazz.getMethod("setRsCategory", int.class);

                method.invoke(_rsModelRemoteModel, rsCategory);
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

        if (_rsModelRemoteModel != null) {
            try {
                Class<?> clazz = _rsModelRemoteModel.getClass();

                Method method = clazz.getMethod("setRsTradeClass", int.class);

                method.invoke(_rsModelRemoteModel, rsTradeClass);
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

        if (_rsModelRemoteModel != null) {
            try {
                Class<?> clazz = _rsModelRemoteModel.getClass();

                Method method = clazz.getMethod("setInterestBearingIndicator",
                        int.class);

                method.invoke(_rsModelRemoteModel, interestBearingIndicator);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getManfCompanyMasterSid() {
        return _manfCompanyMasterSid;
    }

    @Override
    public void setManfCompanyMasterSid(int manfCompanyMasterSid) {
        _manfCompanyMasterSid = manfCompanyMasterSid;

        if (_rsModelRemoteModel != null) {
            try {
                Class<?> clazz = _rsModelRemoteModel.getClass();

                Method method = clazz.getMethod("setManfCompanyMasterSid",
                        int.class);

                method.invoke(_rsModelRemoteModel, manfCompanyMasterSid);
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

        if (_rsModelRemoteModel != null) {
            try {
                Class<?> clazz = _rsModelRemoteModel.getClass();

                Method method = clazz.getMethod("setPaymentTerms", int.class);

                method.invoke(_rsModelRemoteModel, paymentTerms);
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

        if (_rsModelRemoteModel != null) {
            try {
                Class<?> clazz = _rsModelRemoteModel.getClass();

                Method method = clazz.getMethod("setAddress1", String.class);

                method.invoke(_rsModelRemoteModel, address1);
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

        if (_rsModelRemoteModel != null) {
            try {
                Class<?> clazz = _rsModelRemoteModel.getClass();

                Method method = clazz.getMethod("setAddress2", String.class);

                method.invoke(_rsModelRemoteModel, address2);
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

        if (_rsModelRemoteModel != null) {
            try {
                Class<?> clazz = _rsModelRemoteModel.getClass();

                Method method = clazz.getMethod("setRsValidationProfile",
                        int.class);

                method.invoke(_rsModelRemoteModel, rsValidationProfile);
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

        if (_rsModelRemoteModel != null) {
            try {
                Class<?> clazz = _rsModelRemoteModel.getClass();

                Method method = clazz.getMethod("setPaymentGracePeriod",
                        String.class);

                method.invoke(_rsModelRemoteModel, paymentGracePeriod);
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

        if (_rsModelRemoteModel != null) {
            try {
                Class<?> clazz = _rsModelRemoteModel.getClass();

                Method method = clazz.getMethod("setBatchId", String.class);

                method.invoke(_rsModelRemoteModel, batchId);
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

        if (_rsModelRemoteModel != null) {
            try {
                Class<?> clazz = _rsModelRemoteModel.getClass();

                Method method = clazz.getMethod("setEvaluationRuleType",
                        String.class);

                method.invoke(_rsModelRemoteModel, evaluationRuleType);
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

        if (_rsModelRemoteModel != null) {
            try {
                Class<?> clazz = _rsModelRemoteModel.getClass();

                Method method = clazz.getMethod("setEvaluationRuleLevel",
                        String.class);

                method.invoke(_rsModelRemoteModel, evaluationRuleLevel);
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

        if (_rsModelRemoteModel != null) {
            try {
                Class<?> clazz = _rsModelRemoteModel.getClass();

                Method method = clazz.getMethod("setEvaluationRuleOrAssociation",
                        String.class);

                method.invoke(_rsModelRemoteModel, evaluationRuleOrAssociation);
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

        if (_rsModelRemoteModel != null) {
            try {
                Class<?> clazz = _rsModelRemoteModel.getClass();

                Method method = clazz.getMethod("setCalculationRuleLevel",
                        String.class);

                method.invoke(_rsModelRemoteModel, calculationRuleLevel);
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

        if (_rsModelRemoteModel != null) {
            try {
                Class<?> clazz = _rsModelRemoteModel.getClass();

                Method method = clazz.getMethod("setCalculationRule",
                        String.class);

                method.invoke(_rsModelRemoteModel, calculationRule);
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

        if (_rsModelRemoteModel != null) {
            try {
                Class<?> clazz = _rsModelRemoteModel.getClass();

                Method method = clazz.getMethod("setDeductionInclusion",
                        String.class);

                method.invoke(_rsModelRemoteModel, deductionInclusion);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getRsModelRemoteModel() {
        return _rsModelRemoteModel;
    }

    public void setRsModelRemoteModel(BaseModel<?> rsModelRemoteModel) {
        _rsModelRemoteModel = rsModelRemoteModel;
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

        Class<?> remoteModelClass = _rsModelRemoteModel.getClass();

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

        Object returnValue = method.invoke(_rsModelRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            RsModelLocalServiceUtil.addRsModel(this);
        } else {
            RsModelLocalServiceUtil.updateRsModel(this);
        }
    }

    @Override
    public RsModel toEscapedModel() {
        return (RsModel) ProxyUtil.newProxyInstance(RsModel.class.getClassLoader(),
            new Class[] { RsModel.class }, new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        RsModelClp clone = new RsModelClp();

        clone.setFormulaMethodId(getFormulaMethodId());
        clone.setCalculationType(getCalculationType());
        clone.setRsStatus(getRsStatus());
        clone.setRsEndDate(getRsEndDate());
        clone.setRsTransRefNo(getRsTransRefNo());
        clone.setPaymentFrequency(getPaymentFrequency());
        clone.setModifiedDate(getModifiedDate());
        clone.setCalculationLevel(getCalculationLevel());
        clone.setRsName(getRsName());
        clone.setSource(getSource());
        clone.setRebatePlanLevel(getRebatePlanLevel());
        clone.setRebateRuleType(getRebateRuleType());
        clone.setInboundStatus(getInboundStatus());
        clone.setModifiedBy(getModifiedBy());
        clone.setRsAlias(getRsAlias());
        clone.setRsId(getRsId());
        clone.setPaymentMethod(getPaymentMethod());
        clone.setZipCode(getZipCode());
        clone.setRebateRuleAssociation(getRebateRuleAssociation());
        clone.setParentRsName(getParentRsName());
        clone.setInternalNotes(getInternalNotes());
        clone.setPaymentLevel(getPaymentLevel());
        clone.setRecordLockStatus(getRecordLockStatus());
        clone.setRsCalendar(getRsCalendar());
        clone.setRebateProgramType(getRebateProgramType());
        clone.setInterestBearingBasis(getInterestBearingBasis());
        clone.setRsModelSid(getRsModelSid());
        clone.setCity(getCity());
        clone.setRebateProcessingType(getRebateProcessingType());
        clone.setRsNo(getRsNo());
        clone.setState(getState());
        clone.setRebateFrequency(getRebateFrequency());
        clone.setParentRsId(getParentRsId());
        clone.setRsType(getRsType());
        clone.setRsStartDate(getRsStartDate());
        clone.setMakePayableTo(getMakePayableTo());
        clone.setRsDesignation(getRsDesignation());
        clone.setRsTransRefName(getRsTransRefName());
        clone.setCreatedBy(getCreatedBy());
        clone.setCreatedDate(getCreatedDate());
        clone.setRsTransRefId(getRsTransRefId());
        clone.setRsCategory(getRsCategory());
        clone.setRsTradeClass(getRsTradeClass());
        clone.setInterestBearingIndicator(getInterestBearingIndicator());
        clone.setManfCompanyMasterSid(getManfCompanyMasterSid());
        clone.setPaymentTerms(getPaymentTerms());
        clone.setAddress1(getAddress1());
        clone.setAddress2(getAddress2());
        clone.setRsValidationProfile(getRsValidationProfile());
        clone.setPaymentGracePeriod(getPaymentGracePeriod());
        clone.setBatchId(getBatchId());
        clone.setEvaluationRuleType(getEvaluationRuleType());
        clone.setEvaluationRuleLevel(getEvaluationRuleLevel());
        clone.setEvaluationRuleOrAssociation(getEvaluationRuleOrAssociation());
        clone.setCalculationRuleLevel(getCalculationRuleLevel());
        clone.setCalculationRule(getCalculationRule());
        clone.setDeductionInclusion(getDeductionInclusion());

        return clone;
    }

    @Override
    public int compareTo(RsModel rsModel) {
        int primaryKey = rsModel.getPrimaryKey();

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

        if (!(obj instanceof RsModelClp)) {
            return false;
        }

        RsModelClp rsModel = (RsModelClp) obj;

        int primaryKey = rsModel.getPrimaryKey();

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
        StringBundler sb = new StringBundler(115);

        sb.append("{formulaMethodId=");
        sb.append(getFormulaMethodId());
        sb.append(", calculationType=");
        sb.append(getCalculationType());
        sb.append(", rsStatus=");
        sb.append(getRsStatus());
        sb.append(", rsEndDate=");
        sb.append(getRsEndDate());
        sb.append(", rsTransRefNo=");
        sb.append(getRsTransRefNo());
        sb.append(", paymentFrequency=");
        sb.append(getPaymentFrequency());
        sb.append(", modifiedDate=");
        sb.append(getModifiedDate());
        sb.append(", calculationLevel=");
        sb.append(getCalculationLevel());
        sb.append(", rsName=");
        sb.append(getRsName());
        sb.append(", source=");
        sb.append(getSource());
        sb.append(", rebatePlanLevel=");
        sb.append(getRebatePlanLevel());
        sb.append(", rebateRuleType=");
        sb.append(getRebateRuleType());
        sb.append(", inboundStatus=");
        sb.append(getInboundStatus());
        sb.append(", modifiedBy=");
        sb.append(getModifiedBy());
        sb.append(", rsAlias=");
        sb.append(getRsAlias());
        sb.append(", rsId=");
        sb.append(getRsId());
        sb.append(", paymentMethod=");
        sb.append(getPaymentMethod());
        sb.append(", zipCode=");
        sb.append(getZipCode());
        sb.append(", rebateRuleAssociation=");
        sb.append(getRebateRuleAssociation());
        sb.append(", parentRsName=");
        sb.append(getParentRsName());
        sb.append(", internalNotes=");
        sb.append(getInternalNotes());
        sb.append(", paymentLevel=");
        sb.append(getPaymentLevel());
        sb.append(", recordLockStatus=");
        sb.append(getRecordLockStatus());
        sb.append(", rsCalendar=");
        sb.append(getRsCalendar());
        sb.append(", rebateProgramType=");
        sb.append(getRebateProgramType());
        sb.append(", interestBearingBasis=");
        sb.append(getInterestBearingBasis());
        sb.append(", rsModelSid=");
        sb.append(getRsModelSid());
        sb.append(", city=");
        sb.append(getCity());
        sb.append(", rebateProcessingType=");
        sb.append(getRebateProcessingType());
        sb.append(", rsNo=");
        sb.append(getRsNo());
        sb.append(", state=");
        sb.append(getState());
        sb.append(", rebateFrequency=");
        sb.append(getRebateFrequency());
        sb.append(", parentRsId=");
        sb.append(getParentRsId());
        sb.append(", rsType=");
        sb.append(getRsType());
        sb.append(", rsStartDate=");
        sb.append(getRsStartDate());
        sb.append(", makePayableTo=");
        sb.append(getMakePayableTo());
        sb.append(", rsDesignation=");
        sb.append(getRsDesignation());
        sb.append(", rsTransRefName=");
        sb.append(getRsTransRefName());
        sb.append(", createdBy=");
        sb.append(getCreatedBy());
        sb.append(", createdDate=");
        sb.append(getCreatedDate());
        sb.append(", rsTransRefId=");
        sb.append(getRsTransRefId());
        sb.append(", rsCategory=");
        sb.append(getRsCategory());
        sb.append(", rsTradeClass=");
        sb.append(getRsTradeClass());
        sb.append(", interestBearingIndicator=");
        sb.append(getInterestBearingIndicator());
        sb.append(", manfCompanyMasterSid=");
        sb.append(getManfCompanyMasterSid());
        sb.append(", paymentTerms=");
        sb.append(getPaymentTerms());
        sb.append(", address1=");
        sb.append(getAddress1());
        sb.append(", address2=");
        sb.append(getAddress2());
        sb.append(", rsValidationProfile=");
        sb.append(getRsValidationProfile());
        sb.append(", paymentGracePeriod=");
        sb.append(getPaymentGracePeriod());
        sb.append(", batchId=");
        sb.append(getBatchId());
        sb.append(", evaluationRuleType=");
        sb.append(getEvaluationRuleType());
        sb.append(", evaluationRuleLevel=");
        sb.append(getEvaluationRuleLevel());
        sb.append(", evaluationRuleOrAssociation=");
        sb.append(getEvaluationRuleOrAssociation());
        sb.append(", calculationRuleLevel=");
        sb.append(getCalculationRuleLevel());
        sb.append(", calculationRule=");
        sb.append(getCalculationRule());
        sb.append(", deductionInclusion=");
        sb.append(getDeductionInclusion());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(175);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.RsModel");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>formulaMethodId</column-name><column-value><![CDATA[");
        sb.append(getFormulaMethodId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>calculationType</column-name><column-value><![CDATA[");
        sb.append(getCalculationType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>rsStatus</column-name><column-value><![CDATA[");
        sb.append(getRsStatus());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>rsEndDate</column-name><column-value><![CDATA[");
        sb.append(getRsEndDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>rsTransRefNo</column-name><column-value><![CDATA[");
        sb.append(getRsTransRefNo());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>paymentFrequency</column-name><column-value><![CDATA[");
        sb.append(getPaymentFrequency());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
        sb.append(getModifiedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>calculationLevel</column-name><column-value><![CDATA[");
        sb.append(getCalculationLevel());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>rsName</column-name><column-value><![CDATA[");
        sb.append(getRsName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>source</column-name><column-value><![CDATA[");
        sb.append(getSource());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>rebatePlanLevel</column-name><column-value><![CDATA[");
        sb.append(getRebatePlanLevel());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>rebateRuleType</column-name><column-value><![CDATA[");
        sb.append(getRebateRuleType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>inboundStatus</column-name><column-value><![CDATA[");
        sb.append(getInboundStatus());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedBy</column-name><column-value><![CDATA[");
        sb.append(getModifiedBy());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>rsAlias</column-name><column-value><![CDATA[");
        sb.append(getRsAlias());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>rsId</column-name><column-value><![CDATA[");
        sb.append(getRsId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>paymentMethod</column-name><column-value><![CDATA[");
        sb.append(getPaymentMethod());
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
            "<column><column-name>parentRsName</column-name><column-value><![CDATA[");
        sb.append(getParentRsName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>internalNotes</column-name><column-value><![CDATA[");
        sb.append(getInternalNotes());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>paymentLevel</column-name><column-value><![CDATA[");
        sb.append(getPaymentLevel());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>recordLockStatus</column-name><column-value><![CDATA[");
        sb.append(getRecordLockStatus());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>rsCalendar</column-name><column-value><![CDATA[");
        sb.append(getRsCalendar());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>rebateProgramType</column-name><column-value><![CDATA[");
        sb.append(getRebateProgramType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>interestBearingBasis</column-name><column-value><![CDATA[");
        sb.append(getInterestBearingBasis());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>rsModelSid</column-name><column-value><![CDATA[");
        sb.append(getRsModelSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>city</column-name><column-value><![CDATA[");
        sb.append(getCity());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>rebateProcessingType</column-name><column-value><![CDATA[");
        sb.append(getRebateProcessingType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>rsNo</column-name><column-value><![CDATA[");
        sb.append(getRsNo());
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
            "<column><column-name>parentRsId</column-name><column-value><![CDATA[");
        sb.append(getParentRsId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>rsType</column-name><column-value><![CDATA[");
        sb.append(getRsType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>rsStartDate</column-name><column-value><![CDATA[");
        sb.append(getRsStartDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>makePayableTo</column-name><column-value><![CDATA[");
        sb.append(getMakePayableTo());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>rsDesignation</column-name><column-value><![CDATA[");
        sb.append(getRsDesignation());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>rsTransRefName</column-name><column-value><![CDATA[");
        sb.append(getRsTransRefName());
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
            "<column><column-name>rsTransRefId</column-name><column-value><![CDATA[");
        sb.append(getRsTransRefId());
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
            "<column><column-name>interestBearingIndicator</column-name><column-value><![CDATA[");
        sb.append(getInterestBearingIndicator());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>manfCompanyMasterSid</column-name><column-value><![CDATA[");
        sb.append(getManfCompanyMasterSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>paymentTerms</column-name><column-value><![CDATA[");
        sb.append(getPaymentTerms());
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
            "<column><column-name>rsValidationProfile</column-name><column-value><![CDATA[");
        sb.append(getRsValidationProfile());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>paymentGracePeriod</column-name><column-value><![CDATA[");
        sb.append(getPaymentGracePeriod());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>batchId</column-name><column-value><![CDATA[");
        sb.append(getBatchId());
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
            "<column><column-name>calculationRuleLevel</column-name><column-value><![CDATA[");
        sb.append(getCalculationRuleLevel());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>calculationRule</column-name><column-value><![CDATA[");
        sb.append(getCalculationRule());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>deductionInclusion</column-name><column-value><![CDATA[");
        sb.append(getDeductionInclusion());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
