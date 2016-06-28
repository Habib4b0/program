package com.stpl.app.model;

import com.stpl.app.service.ClpSerializer;
import com.stpl.app.service.RsDetailsLocalServiceUtil;

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


public class RsDetailsClp extends BaseModelImpl<RsDetails> implements RsDetails {
    private double _rebateAmount;
    private int _itemRsAttachedStatus;
    private String _formulaMethodId;
    private int _itemMasterSid;
    private int _rebatePlanMasterSid;
    private Date _modifiedDate;
    private String _bundleNo;
    private boolean _recordLockStatus;
    private Date _createdDate;
    private int _createdBy;
    private String _source;
    private Date _itemRebateEndDate;
    private String _batchId;
    private Date _itemRebateStartDate;
    private int _modifiedBy;
    private String _inboundStatus;
    private int _rsDetailsSid;
    private int _rsModelSid;
    private int _formulaId;
    private Date _itemRsAttachedDate;
    private int _ifpModelSid;
    private int _deductionCalendarMasterSid;
    private int _netSalesFormulaMasterSid;
    private String _evaluationRule;
    private String _netSalesRule;
    private String _formulaType;
    private String _calculationRule;
    private String _calculationRuleBundle;
    private String _evaluationRuleBundle;
    private BaseModel<?> _rsDetailsRemoteModel;

    public RsDetailsClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return RsDetails.class;
    }

    @Override
    public String getModelClassName() {
        return RsDetails.class.getName();
    }

    @Override
    public int getPrimaryKey() {
        return _rsDetailsSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setRsDetailsSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _rsDetailsSid;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Integer) primaryKeyObj).intValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("rebateAmount", getRebateAmount());
        attributes.put("itemRsAttachedStatus", getItemRsAttachedStatus());
        attributes.put("formulaMethodId", getFormulaMethodId());
        attributes.put("itemMasterSid", getItemMasterSid());
        attributes.put("rebatePlanMasterSid", getRebatePlanMasterSid());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("bundleNo", getBundleNo());
        attributes.put("recordLockStatus", getRecordLockStatus());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("source", getSource());
        attributes.put("itemRebateEndDate", getItemRebateEndDate());
        attributes.put("batchId", getBatchId());
        attributes.put("itemRebateStartDate", getItemRebateStartDate());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("inboundStatus", getInboundStatus());
        attributes.put("rsDetailsSid", getRsDetailsSid());
        attributes.put("rsModelSid", getRsModelSid());
        attributes.put("formulaId", getFormulaId());
        attributes.put("itemRsAttachedDate", getItemRsAttachedDate());
        attributes.put("ifpModelSid", getIfpModelSid());
        attributes.put("deductionCalendarMasterSid",
            getDeductionCalendarMasterSid());
        attributes.put("netSalesFormulaMasterSid", getNetSalesFormulaMasterSid());
        attributes.put("evaluationRule", getEvaluationRule());
        attributes.put("netSalesRule", getNetSalesRule());
        attributes.put("formulaType", getFormulaType());
        attributes.put("calculationRule", getCalculationRule());
        attributes.put("calculationRuleBundle", getCalculationRuleBundle());
        attributes.put("evaluationRuleBundle", getEvaluationRuleBundle());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Double rebateAmount = (Double) attributes.get("rebateAmount");

        if (rebateAmount != null) {
            setRebateAmount(rebateAmount);
        }

        Integer itemRsAttachedStatus = (Integer) attributes.get(
                "itemRsAttachedStatus");

        if (itemRsAttachedStatus != null) {
            setItemRsAttachedStatus(itemRsAttachedStatus);
        }

        String formulaMethodId = (String) attributes.get("formulaMethodId");

        if (formulaMethodId != null) {
            setFormulaMethodId(formulaMethodId);
        }

        Integer itemMasterSid = (Integer) attributes.get("itemMasterSid");

        if (itemMasterSid != null) {
            setItemMasterSid(itemMasterSid);
        }

        Integer rebatePlanMasterSid = (Integer) attributes.get(
                "rebatePlanMasterSid");

        if (rebatePlanMasterSid != null) {
            setRebatePlanMasterSid(rebatePlanMasterSid);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        String bundleNo = (String) attributes.get("bundleNo");

        if (bundleNo != null) {
            setBundleNo(bundleNo);
        }

        Boolean recordLockStatus = (Boolean) attributes.get("recordLockStatus");

        if (recordLockStatus != null) {
            setRecordLockStatus(recordLockStatus);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        Integer createdBy = (Integer) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        String source = (String) attributes.get("source");

        if (source != null) {
            setSource(source);
        }

        Date itemRebateEndDate = (Date) attributes.get("itemRebateEndDate");

        if (itemRebateEndDate != null) {
            setItemRebateEndDate(itemRebateEndDate);
        }

        String batchId = (String) attributes.get("batchId");

        if (batchId != null) {
            setBatchId(batchId);
        }

        Date itemRebateStartDate = (Date) attributes.get("itemRebateStartDate");

        if (itemRebateStartDate != null) {
            setItemRebateStartDate(itemRebateStartDate);
        }

        Integer modifiedBy = (Integer) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        String inboundStatus = (String) attributes.get("inboundStatus");

        if (inboundStatus != null) {
            setInboundStatus(inboundStatus);
        }

        Integer rsDetailsSid = (Integer) attributes.get("rsDetailsSid");

        if (rsDetailsSid != null) {
            setRsDetailsSid(rsDetailsSid);
        }

        Integer rsModelSid = (Integer) attributes.get("rsModelSid");

        if (rsModelSid != null) {
            setRsModelSid(rsModelSid);
        }

        Integer formulaId = (Integer) attributes.get("formulaId");

        if (formulaId != null) {
            setFormulaId(formulaId);
        }

        Date itemRsAttachedDate = (Date) attributes.get("itemRsAttachedDate");

        if (itemRsAttachedDate != null) {
            setItemRsAttachedDate(itemRsAttachedDate);
        }

        Integer ifpModelSid = (Integer) attributes.get("ifpModelSid");

        if (ifpModelSid != null) {
            setIfpModelSid(ifpModelSid);
        }

        Integer deductionCalendarMasterSid = (Integer) attributes.get(
                "deductionCalendarMasterSid");

        if (deductionCalendarMasterSid != null) {
            setDeductionCalendarMasterSid(deductionCalendarMasterSid);
        }

        Integer netSalesFormulaMasterSid = (Integer) attributes.get(
                "netSalesFormulaMasterSid");

        if (netSalesFormulaMasterSid != null) {
            setNetSalesFormulaMasterSid(netSalesFormulaMasterSid);
        }

        String evaluationRule = (String) attributes.get("evaluationRule");

        if (evaluationRule != null) {
            setEvaluationRule(evaluationRule);
        }

        String netSalesRule = (String) attributes.get("netSalesRule");

        if (netSalesRule != null) {
            setNetSalesRule(netSalesRule);
        }

        String formulaType = (String) attributes.get("formulaType");

        if (formulaType != null) {
            setFormulaType(formulaType);
        }

        String calculationRule = (String) attributes.get("calculationRule");

        if (calculationRule != null) {
            setCalculationRule(calculationRule);
        }

        String calculationRuleBundle = (String) attributes.get(
                "calculationRuleBundle");

        if (calculationRuleBundle != null) {
            setCalculationRuleBundle(calculationRuleBundle);
        }

        String evaluationRuleBundle = (String) attributes.get(
                "evaluationRuleBundle");

        if (evaluationRuleBundle != null) {
            setEvaluationRuleBundle(evaluationRuleBundle);
        }
    }

    @Override
    public double getRebateAmount() {
        return _rebateAmount;
    }

    @Override
    public void setRebateAmount(double rebateAmount) {
        _rebateAmount = rebateAmount;

        if (_rsDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _rsDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setRebateAmount", double.class);

                method.invoke(_rsDetailsRemoteModel, rebateAmount);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getItemRsAttachedStatus() {
        return _itemRsAttachedStatus;
    }

    @Override
    public void setItemRsAttachedStatus(int itemRsAttachedStatus) {
        _itemRsAttachedStatus = itemRsAttachedStatus;

        if (_rsDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _rsDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setItemRsAttachedStatus",
                        int.class);

                method.invoke(_rsDetailsRemoteModel, itemRsAttachedStatus);
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

        if (_rsDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _rsDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setFormulaMethodId",
                        String.class);

                method.invoke(_rsDetailsRemoteModel, formulaMethodId);
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

        if (_rsDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _rsDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setItemMasterSid", int.class);

                method.invoke(_rsDetailsRemoteModel, itemMasterSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getRebatePlanMasterSid() {
        return _rebatePlanMasterSid;
    }

    @Override
    public void setRebatePlanMasterSid(int rebatePlanMasterSid) {
        _rebatePlanMasterSid = rebatePlanMasterSid;

        if (_rsDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _rsDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setRebatePlanMasterSid",
                        int.class);

                method.invoke(_rsDetailsRemoteModel, rebatePlanMasterSid);
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

        if (_rsDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _rsDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedDate", Date.class);

                method.invoke(_rsDetailsRemoteModel, modifiedDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getBundleNo() {
        return _bundleNo;
    }

    @Override
    public void setBundleNo(String bundleNo) {
        _bundleNo = bundleNo;

        if (_rsDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _rsDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setBundleNo", String.class);

                method.invoke(_rsDetailsRemoteModel, bundleNo);
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

        if (_rsDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _rsDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setRecordLockStatus",
                        boolean.class);

                method.invoke(_rsDetailsRemoteModel, recordLockStatus);
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

        if (_rsDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _rsDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedDate", Date.class);

                method.invoke(_rsDetailsRemoteModel, createdDate);
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

        if (_rsDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _rsDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedBy", int.class);

                method.invoke(_rsDetailsRemoteModel, createdBy);
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

        if (_rsDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _rsDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setSource", String.class);

                method.invoke(_rsDetailsRemoteModel, source);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getItemRebateEndDate() {
        return _itemRebateEndDate;
    }

    @Override
    public void setItemRebateEndDate(Date itemRebateEndDate) {
        _itemRebateEndDate = itemRebateEndDate;

        if (_rsDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _rsDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setItemRebateEndDate",
                        Date.class);

                method.invoke(_rsDetailsRemoteModel, itemRebateEndDate);
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

        if (_rsDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _rsDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setBatchId", String.class);

                method.invoke(_rsDetailsRemoteModel, batchId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getItemRebateStartDate() {
        return _itemRebateStartDate;
    }

    @Override
    public void setItemRebateStartDate(Date itemRebateStartDate) {
        _itemRebateStartDate = itemRebateStartDate;

        if (_rsDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _rsDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setItemRebateStartDate",
                        Date.class);

                method.invoke(_rsDetailsRemoteModel, itemRebateStartDate);
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

        if (_rsDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _rsDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedBy", int.class);

                method.invoke(_rsDetailsRemoteModel, modifiedBy);
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

        if (_rsDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _rsDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setInboundStatus", String.class);

                method.invoke(_rsDetailsRemoteModel, inboundStatus);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getRsDetailsSid() {
        return _rsDetailsSid;
    }

    @Override
    public void setRsDetailsSid(int rsDetailsSid) {
        _rsDetailsSid = rsDetailsSid;

        if (_rsDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _rsDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setRsDetailsSid", int.class);

                method.invoke(_rsDetailsRemoteModel, rsDetailsSid);
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

        if (_rsDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _rsDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setRsModelSid", int.class);

                method.invoke(_rsDetailsRemoteModel, rsModelSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getFormulaId() {
        return _formulaId;
    }

    @Override
    public void setFormulaId(int formulaId) {
        _formulaId = formulaId;

        if (_rsDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _rsDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setFormulaId", int.class);

                method.invoke(_rsDetailsRemoteModel, formulaId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getItemRsAttachedDate() {
        return _itemRsAttachedDate;
    }

    @Override
    public void setItemRsAttachedDate(Date itemRsAttachedDate) {
        _itemRsAttachedDate = itemRsAttachedDate;

        if (_rsDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _rsDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setItemRsAttachedDate",
                        Date.class);

                method.invoke(_rsDetailsRemoteModel, itemRsAttachedDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getIfpModelSid() {
        return _ifpModelSid;
    }

    @Override
    public void setIfpModelSid(int ifpModelSid) {
        _ifpModelSid = ifpModelSid;

        if (_rsDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _rsDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setIfpModelSid", int.class);

                method.invoke(_rsDetailsRemoteModel, ifpModelSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getDeductionCalendarMasterSid() {
        return _deductionCalendarMasterSid;
    }

    @Override
    public void setDeductionCalendarMasterSid(int deductionCalendarMasterSid) {
        _deductionCalendarMasterSid = deductionCalendarMasterSid;

        if (_rsDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _rsDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setDeductionCalendarMasterSid",
                        int.class);

                method.invoke(_rsDetailsRemoteModel, deductionCalendarMasterSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getNetSalesFormulaMasterSid() {
        return _netSalesFormulaMasterSid;
    }

    @Override
    public void setNetSalesFormulaMasterSid(int netSalesFormulaMasterSid) {
        _netSalesFormulaMasterSid = netSalesFormulaMasterSid;

        if (_rsDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _rsDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setNetSalesFormulaMasterSid",
                        int.class);

                method.invoke(_rsDetailsRemoteModel, netSalesFormulaMasterSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getEvaluationRule() {
        return _evaluationRule;
    }

    @Override
    public void setEvaluationRule(String evaluationRule) {
        _evaluationRule = evaluationRule;

        if (_rsDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _rsDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setEvaluationRule",
                        String.class);

                method.invoke(_rsDetailsRemoteModel, evaluationRule);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getNetSalesRule() {
        return _netSalesRule;
    }

    @Override
    public void setNetSalesRule(String netSalesRule) {
        _netSalesRule = netSalesRule;

        if (_rsDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _rsDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setNetSalesRule", String.class);

                method.invoke(_rsDetailsRemoteModel, netSalesRule);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getFormulaType() {
        return _formulaType;
    }

    @Override
    public void setFormulaType(String formulaType) {
        _formulaType = formulaType;

        if (_rsDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _rsDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setFormulaType", String.class);

                method.invoke(_rsDetailsRemoteModel, formulaType);
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

        if (_rsDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _rsDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCalculationRule",
                        String.class);

                method.invoke(_rsDetailsRemoteModel, calculationRule);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getCalculationRuleBundle() {
        return _calculationRuleBundle;
    }

    @Override
    public void setCalculationRuleBundle(String calculationRuleBundle) {
        _calculationRuleBundle = calculationRuleBundle;

        if (_rsDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _rsDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCalculationRuleBundle",
                        String.class);

                method.invoke(_rsDetailsRemoteModel, calculationRuleBundle);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getEvaluationRuleBundle() {
        return _evaluationRuleBundle;
    }

    @Override
    public void setEvaluationRuleBundle(String evaluationRuleBundle) {
        _evaluationRuleBundle = evaluationRuleBundle;

        if (_rsDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _rsDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setEvaluationRuleBundle",
                        String.class);

                method.invoke(_rsDetailsRemoteModel, evaluationRuleBundle);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getRsDetailsRemoteModel() {
        return _rsDetailsRemoteModel;
    }

    public void setRsDetailsRemoteModel(BaseModel<?> rsDetailsRemoteModel) {
        _rsDetailsRemoteModel = rsDetailsRemoteModel;
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

        Class<?> remoteModelClass = _rsDetailsRemoteModel.getClass();

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

        Object returnValue = method.invoke(_rsDetailsRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            RsDetailsLocalServiceUtil.addRsDetails(this);
        } else {
            RsDetailsLocalServiceUtil.updateRsDetails(this);
        }
    }

    @Override
    public RsDetails toEscapedModel() {
        return (RsDetails) ProxyUtil.newProxyInstance(RsDetails.class.getClassLoader(),
            new Class[] { RsDetails.class }, new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        RsDetailsClp clone = new RsDetailsClp();

        clone.setRebateAmount(getRebateAmount());
        clone.setItemRsAttachedStatus(getItemRsAttachedStatus());
        clone.setFormulaMethodId(getFormulaMethodId());
        clone.setItemMasterSid(getItemMasterSid());
        clone.setRebatePlanMasterSid(getRebatePlanMasterSid());
        clone.setModifiedDate(getModifiedDate());
        clone.setBundleNo(getBundleNo());
        clone.setRecordLockStatus(getRecordLockStatus());
        clone.setCreatedDate(getCreatedDate());
        clone.setCreatedBy(getCreatedBy());
        clone.setSource(getSource());
        clone.setItemRebateEndDate(getItemRebateEndDate());
        clone.setBatchId(getBatchId());
        clone.setItemRebateStartDate(getItemRebateStartDate());
        clone.setModifiedBy(getModifiedBy());
        clone.setInboundStatus(getInboundStatus());
        clone.setRsDetailsSid(getRsDetailsSid());
        clone.setRsModelSid(getRsModelSid());
        clone.setFormulaId(getFormulaId());
        clone.setItemRsAttachedDate(getItemRsAttachedDate());
        clone.setIfpModelSid(getIfpModelSid());
        clone.setDeductionCalendarMasterSid(getDeductionCalendarMasterSid());
        clone.setNetSalesFormulaMasterSid(getNetSalesFormulaMasterSid());
        clone.setEvaluationRule(getEvaluationRule());
        clone.setNetSalesRule(getNetSalesRule());
        clone.setFormulaType(getFormulaType());
        clone.setCalculationRule(getCalculationRule());
        clone.setCalculationRuleBundle(getCalculationRuleBundle());
        clone.setEvaluationRuleBundle(getEvaluationRuleBundle());

        return clone;
    }

    @Override
    public int compareTo(RsDetails rsDetails) {
        int primaryKey = rsDetails.getPrimaryKey();

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

        if (!(obj instanceof RsDetailsClp)) {
            return false;
        }

        RsDetailsClp rsDetails = (RsDetailsClp) obj;

        int primaryKey = rsDetails.getPrimaryKey();

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
        StringBundler sb = new StringBundler(59);

        sb.append("{rebateAmount=");
        sb.append(getRebateAmount());
        sb.append(", itemRsAttachedStatus=");
        sb.append(getItemRsAttachedStatus());
        sb.append(", formulaMethodId=");
        sb.append(getFormulaMethodId());
        sb.append(", itemMasterSid=");
        sb.append(getItemMasterSid());
        sb.append(", rebatePlanMasterSid=");
        sb.append(getRebatePlanMasterSid());
        sb.append(", modifiedDate=");
        sb.append(getModifiedDate());
        sb.append(", bundleNo=");
        sb.append(getBundleNo());
        sb.append(", recordLockStatus=");
        sb.append(getRecordLockStatus());
        sb.append(", createdDate=");
        sb.append(getCreatedDate());
        sb.append(", createdBy=");
        sb.append(getCreatedBy());
        sb.append(", source=");
        sb.append(getSource());
        sb.append(", itemRebateEndDate=");
        sb.append(getItemRebateEndDate());
        sb.append(", batchId=");
        sb.append(getBatchId());
        sb.append(", itemRebateStartDate=");
        sb.append(getItemRebateStartDate());
        sb.append(", modifiedBy=");
        sb.append(getModifiedBy());
        sb.append(", inboundStatus=");
        sb.append(getInboundStatus());
        sb.append(", rsDetailsSid=");
        sb.append(getRsDetailsSid());
        sb.append(", rsModelSid=");
        sb.append(getRsModelSid());
        sb.append(", formulaId=");
        sb.append(getFormulaId());
        sb.append(", itemRsAttachedDate=");
        sb.append(getItemRsAttachedDate());
        sb.append(", ifpModelSid=");
        sb.append(getIfpModelSid());
        sb.append(", deductionCalendarMasterSid=");
        sb.append(getDeductionCalendarMasterSid());
        sb.append(", netSalesFormulaMasterSid=");
        sb.append(getNetSalesFormulaMasterSid());
        sb.append(", evaluationRule=");
        sb.append(getEvaluationRule());
        sb.append(", netSalesRule=");
        sb.append(getNetSalesRule());
        sb.append(", formulaType=");
        sb.append(getFormulaType());
        sb.append(", calculationRule=");
        sb.append(getCalculationRule());
        sb.append(", calculationRuleBundle=");
        sb.append(getCalculationRuleBundle());
        sb.append(", evaluationRuleBundle=");
        sb.append(getEvaluationRuleBundle());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(91);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.RsDetails");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>rebateAmount</column-name><column-value><![CDATA[");
        sb.append(getRebateAmount());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemRsAttachedStatus</column-name><column-value><![CDATA[");
        sb.append(getItemRsAttachedStatus());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>formulaMethodId</column-name><column-value><![CDATA[");
        sb.append(getFormulaMethodId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemMasterSid</column-name><column-value><![CDATA[");
        sb.append(getItemMasterSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>rebatePlanMasterSid</column-name><column-value><![CDATA[");
        sb.append(getRebatePlanMasterSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
        sb.append(getModifiedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>bundleNo</column-name><column-value><![CDATA[");
        sb.append(getBundleNo());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>recordLockStatus</column-name><column-value><![CDATA[");
        sb.append(getRecordLockStatus());
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
            "<column><column-name>source</column-name><column-value><![CDATA[");
        sb.append(getSource());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemRebateEndDate</column-name><column-value><![CDATA[");
        sb.append(getItemRebateEndDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>batchId</column-name><column-value><![CDATA[");
        sb.append(getBatchId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemRebateStartDate</column-name><column-value><![CDATA[");
        sb.append(getItemRebateStartDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedBy</column-name><column-value><![CDATA[");
        sb.append(getModifiedBy());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>inboundStatus</column-name><column-value><![CDATA[");
        sb.append(getInboundStatus());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>rsDetailsSid</column-name><column-value><![CDATA[");
        sb.append(getRsDetailsSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>rsModelSid</column-name><column-value><![CDATA[");
        sb.append(getRsModelSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>formulaId</column-name><column-value><![CDATA[");
        sb.append(getFormulaId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemRsAttachedDate</column-name><column-value><![CDATA[");
        sb.append(getItemRsAttachedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>ifpModelSid</column-name><column-value><![CDATA[");
        sb.append(getIfpModelSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>deductionCalendarMasterSid</column-name><column-value><![CDATA[");
        sb.append(getDeductionCalendarMasterSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>netSalesFormulaMasterSid</column-name><column-value><![CDATA[");
        sb.append(getNetSalesFormulaMasterSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>evaluationRule</column-name><column-value><![CDATA[");
        sb.append(getEvaluationRule());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>netSalesRule</column-name><column-value><![CDATA[");
        sb.append(getNetSalesRule());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>formulaType</column-name><column-value><![CDATA[");
        sb.append(getFormulaType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>calculationRule</column-name><column-value><![CDATA[");
        sb.append(getCalculationRule());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>calculationRuleBundle</column-name><column-value><![CDATA[");
        sb.append(getCalculationRuleBundle());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>evaluationRuleBundle</column-name><column-value><![CDATA[");
        sb.append(getEvaluationRuleBundle());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
