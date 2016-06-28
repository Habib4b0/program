package com.stpl.app.model;

import com.stpl.app.service.ClpSerializer;
import com.stpl.app.service.RsContractDetailsLocalServiceUtil;

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


public class RsContractDetailsClp extends BaseModelImpl<RsContractDetails>
    implements RsContractDetails {
    private double _rebateAmount;
    private String _formulaMethodId;
    private int _itemMasterSid;
    private String _rebatePlanMasterSid;
    private Date _modifiedDate;
    private int _rsContractDetailsSid;
    private String _bundleNo;
    private boolean _recordLockStatus;
    private Date _createdDate;
    private int _createdBy;
    private String _source;
    private int _rsContractSid;
    private Date _itemRebateEndDate;
    private String _batchId;
    private Date _itemRebateStartDate;
    private int _modifiedBy;
    private int _formulaId;
    private String _inboundStatus;
    private String _deductionCalendarMasterSid;
    private String _netSalesFormulaMasterSid;
    private double _formulaType;
    private int _netSalesRule;
    private int _evaluationRule;
    private String _evaluationRuleBundle;
    private int _calculationRule;
    private String _calculationRuleBundle;
    private Date _rsContractAttachedDate;
    private int _rsContractAttachedStatus;
    private BaseModel<?> _rsContractDetailsRemoteModel;

    public RsContractDetailsClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return RsContractDetails.class;
    }

    @Override
    public String getModelClassName() {
        return RsContractDetails.class.getName();
    }

    @Override
    public int getPrimaryKey() {
        return _rsContractDetailsSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setRsContractDetailsSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _rsContractDetailsSid;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Integer) primaryKeyObj).intValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("rebateAmount", getRebateAmount());
        attributes.put("formulaMethodId", getFormulaMethodId());
        attributes.put("itemMasterSid", getItemMasterSid());
        attributes.put("rebatePlanMasterSid", getRebatePlanMasterSid());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("rsContractDetailsSid", getRsContractDetailsSid());
        attributes.put("bundleNo", getBundleNo());
        attributes.put("recordLockStatus", getRecordLockStatus());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("source", getSource());
        attributes.put("rsContractSid", getRsContractSid());
        attributes.put("itemRebateEndDate", getItemRebateEndDate());
        attributes.put("batchId", getBatchId());
        attributes.put("itemRebateStartDate", getItemRebateStartDate());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("formulaId", getFormulaId());
        attributes.put("inboundStatus", getInboundStatus());
        attributes.put("deductionCalendarMasterSid",
            getDeductionCalendarMasterSid());
        attributes.put("netSalesFormulaMasterSid", getNetSalesFormulaMasterSid());
        attributes.put("formulaType", getFormulaType());
        attributes.put("netSalesRule", getNetSalesRule());
        attributes.put("evaluationRule", getEvaluationRule());
        attributes.put("evaluationRuleBundle", getEvaluationRuleBundle());
        attributes.put("calculationRule", getCalculationRule());
        attributes.put("calculationRuleBundle", getCalculationRuleBundle());
        attributes.put("rsContractAttachedDate", getRsContractAttachedDate());
        attributes.put("rsContractAttachedStatus", getRsContractAttachedStatus());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Double rebateAmount = (Double) attributes.get("rebateAmount");

        if (rebateAmount != null) {
            setRebateAmount(rebateAmount);
        }

        String formulaMethodId = (String) attributes.get("formulaMethodId");

        if (formulaMethodId != null) {
            setFormulaMethodId(formulaMethodId);
        }

        Integer itemMasterSid = (Integer) attributes.get("itemMasterSid");

        if (itemMasterSid != null) {
            setItemMasterSid(itemMasterSid);
        }

        String rebatePlanMasterSid = (String) attributes.get(
                "rebatePlanMasterSid");

        if (rebatePlanMasterSid != null) {
            setRebatePlanMasterSid(rebatePlanMasterSid);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        Integer rsContractDetailsSid = (Integer) attributes.get(
                "rsContractDetailsSid");

        if (rsContractDetailsSid != null) {
            setRsContractDetailsSid(rsContractDetailsSid);
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

        Integer rsContractSid = (Integer) attributes.get("rsContractSid");

        if (rsContractSid != null) {
            setRsContractSid(rsContractSid);
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

        Integer formulaId = (Integer) attributes.get("formulaId");

        if (formulaId != null) {
            setFormulaId(formulaId);
        }

        String inboundStatus = (String) attributes.get("inboundStatus");

        if (inboundStatus != null) {
            setInboundStatus(inboundStatus);
        }

        String deductionCalendarMasterSid = (String) attributes.get(
                "deductionCalendarMasterSid");

        if (deductionCalendarMasterSid != null) {
            setDeductionCalendarMasterSid(deductionCalendarMasterSid);
        }

        String netSalesFormulaMasterSid = (String) attributes.get(
                "netSalesFormulaMasterSid");

        if (netSalesFormulaMasterSid != null) {
            setNetSalesFormulaMasterSid(netSalesFormulaMasterSid);
        }

        Double formulaType = (Double) attributes.get("formulaType");

        if (formulaType != null) {
            setFormulaType(formulaType);
        }

        Integer netSalesRule = (Integer) attributes.get("netSalesRule");

        if (netSalesRule != null) {
            setNetSalesRule(netSalesRule);
        }

        Integer evaluationRule = (Integer) attributes.get("evaluationRule");

        if (evaluationRule != null) {
            setEvaluationRule(evaluationRule);
        }

        String evaluationRuleBundle = (String) attributes.get(
                "evaluationRuleBundle");

        if (evaluationRuleBundle != null) {
            setEvaluationRuleBundle(evaluationRuleBundle);
        }

        Integer calculationRule = (Integer) attributes.get("calculationRule");

        if (calculationRule != null) {
            setCalculationRule(calculationRule);
        }

        String calculationRuleBundle = (String) attributes.get(
                "calculationRuleBundle");

        if (calculationRuleBundle != null) {
            setCalculationRuleBundle(calculationRuleBundle);
        }

        Date rsContractAttachedDate = (Date) attributes.get(
                "rsContractAttachedDate");

        if (rsContractAttachedDate != null) {
            setRsContractAttachedDate(rsContractAttachedDate);
        }

        Integer rsContractAttachedStatus = (Integer) attributes.get(
                "rsContractAttachedStatus");

        if (rsContractAttachedStatus != null) {
            setRsContractAttachedStatus(rsContractAttachedStatus);
        }
    }

    @Override
    public double getRebateAmount() {
        return _rebateAmount;
    }

    @Override
    public void setRebateAmount(double rebateAmount) {
        _rebateAmount = rebateAmount;

        if (_rsContractDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _rsContractDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setRebateAmount", double.class);

                method.invoke(_rsContractDetailsRemoteModel, rebateAmount);
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

        if (_rsContractDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _rsContractDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setFormulaMethodId",
                        String.class);

                method.invoke(_rsContractDetailsRemoteModel, formulaMethodId);
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

        if (_rsContractDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _rsContractDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setItemMasterSid", int.class);

                method.invoke(_rsContractDetailsRemoteModel, itemMasterSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getRebatePlanMasterSid() {
        return _rebatePlanMasterSid;
    }

    @Override
    public void setRebatePlanMasterSid(String rebatePlanMasterSid) {
        _rebatePlanMasterSid = rebatePlanMasterSid;

        if (_rsContractDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _rsContractDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setRebatePlanMasterSid",
                        String.class);

                method.invoke(_rsContractDetailsRemoteModel, rebatePlanMasterSid);
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

        if (_rsContractDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _rsContractDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedDate", Date.class);

                method.invoke(_rsContractDetailsRemoteModel, modifiedDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getRsContractDetailsSid() {
        return _rsContractDetailsSid;
    }

    @Override
    public void setRsContractDetailsSid(int rsContractDetailsSid) {
        _rsContractDetailsSid = rsContractDetailsSid;

        if (_rsContractDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _rsContractDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setRsContractDetailsSid",
                        int.class);

                method.invoke(_rsContractDetailsRemoteModel,
                    rsContractDetailsSid);
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

        if (_rsContractDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _rsContractDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setBundleNo", String.class);

                method.invoke(_rsContractDetailsRemoteModel, bundleNo);
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

        if (_rsContractDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _rsContractDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setRecordLockStatus",
                        boolean.class);

                method.invoke(_rsContractDetailsRemoteModel, recordLockStatus);
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

        if (_rsContractDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _rsContractDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedDate", Date.class);

                method.invoke(_rsContractDetailsRemoteModel, createdDate);
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

        if (_rsContractDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _rsContractDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedBy", int.class);

                method.invoke(_rsContractDetailsRemoteModel, createdBy);
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

        if (_rsContractDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _rsContractDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setSource", String.class);

                method.invoke(_rsContractDetailsRemoteModel, source);
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

        if (_rsContractDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _rsContractDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setRsContractSid", int.class);

                method.invoke(_rsContractDetailsRemoteModel, rsContractSid);
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

        if (_rsContractDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _rsContractDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setItemRebateEndDate",
                        Date.class);

                method.invoke(_rsContractDetailsRemoteModel, itemRebateEndDate);
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

        if (_rsContractDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _rsContractDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setBatchId", String.class);

                method.invoke(_rsContractDetailsRemoteModel, batchId);
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

        if (_rsContractDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _rsContractDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setItemRebateStartDate",
                        Date.class);

                method.invoke(_rsContractDetailsRemoteModel, itemRebateStartDate);
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

        if (_rsContractDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _rsContractDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedBy", int.class);

                method.invoke(_rsContractDetailsRemoteModel, modifiedBy);
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

        if (_rsContractDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _rsContractDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setFormulaId", int.class);

                method.invoke(_rsContractDetailsRemoteModel, formulaId);
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

        if (_rsContractDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _rsContractDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setInboundStatus", String.class);

                method.invoke(_rsContractDetailsRemoteModel, inboundStatus);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getDeductionCalendarMasterSid() {
        return _deductionCalendarMasterSid;
    }

    @Override
    public void setDeductionCalendarMasterSid(String deductionCalendarMasterSid) {
        _deductionCalendarMasterSid = deductionCalendarMasterSid;

        if (_rsContractDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _rsContractDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setDeductionCalendarMasterSid",
                        String.class);

                method.invoke(_rsContractDetailsRemoteModel,
                    deductionCalendarMasterSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getNetSalesFormulaMasterSid() {
        return _netSalesFormulaMasterSid;
    }

    @Override
    public void setNetSalesFormulaMasterSid(String netSalesFormulaMasterSid) {
        _netSalesFormulaMasterSid = netSalesFormulaMasterSid;

        if (_rsContractDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _rsContractDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setNetSalesFormulaMasterSid",
                        String.class);

                method.invoke(_rsContractDetailsRemoteModel,
                    netSalesFormulaMasterSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getFormulaType() {
        return _formulaType;
    }

    @Override
    public void setFormulaType(double formulaType) {
        _formulaType = formulaType;

        if (_rsContractDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _rsContractDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setFormulaType", double.class);

                method.invoke(_rsContractDetailsRemoteModel, formulaType);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getNetSalesRule() {
        return _netSalesRule;
    }

    @Override
    public void setNetSalesRule(int netSalesRule) {
        _netSalesRule = netSalesRule;

        if (_rsContractDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _rsContractDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setNetSalesRule", int.class);

                method.invoke(_rsContractDetailsRemoteModel, netSalesRule);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getEvaluationRule() {
        return _evaluationRule;
    }

    @Override
    public void setEvaluationRule(int evaluationRule) {
        _evaluationRule = evaluationRule;

        if (_rsContractDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _rsContractDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setEvaluationRule", int.class);

                method.invoke(_rsContractDetailsRemoteModel, evaluationRule);
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

        if (_rsContractDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _rsContractDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setEvaluationRuleBundle",
                        String.class);

                method.invoke(_rsContractDetailsRemoteModel,
                    evaluationRuleBundle);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getCalculationRule() {
        return _calculationRule;
    }

    @Override
    public void setCalculationRule(int calculationRule) {
        _calculationRule = calculationRule;

        if (_rsContractDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _rsContractDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCalculationRule", int.class);

                method.invoke(_rsContractDetailsRemoteModel, calculationRule);
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

        if (_rsContractDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _rsContractDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCalculationRuleBundle",
                        String.class);

                method.invoke(_rsContractDetailsRemoteModel,
                    calculationRuleBundle);
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

        if (_rsContractDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _rsContractDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setRsContractAttachedDate",
                        Date.class);

                method.invoke(_rsContractDetailsRemoteModel,
                    rsContractAttachedDate);
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

        if (_rsContractDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _rsContractDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setRsContractAttachedStatus",
                        int.class);

                method.invoke(_rsContractDetailsRemoteModel,
                    rsContractAttachedStatus);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getRsContractDetailsRemoteModel() {
        return _rsContractDetailsRemoteModel;
    }

    public void setRsContractDetailsRemoteModel(
        BaseModel<?> rsContractDetailsRemoteModel) {
        _rsContractDetailsRemoteModel = rsContractDetailsRemoteModel;
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

        Class<?> remoteModelClass = _rsContractDetailsRemoteModel.getClass();

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

        Object returnValue = method.invoke(_rsContractDetailsRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            RsContractDetailsLocalServiceUtil.addRsContractDetails(this);
        } else {
            RsContractDetailsLocalServiceUtil.updateRsContractDetails(this);
        }
    }

    @Override
    public RsContractDetails toEscapedModel() {
        return (RsContractDetails) ProxyUtil.newProxyInstance(RsContractDetails.class.getClassLoader(),
            new Class[] { RsContractDetails.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        RsContractDetailsClp clone = new RsContractDetailsClp();

        clone.setRebateAmount(getRebateAmount());
        clone.setFormulaMethodId(getFormulaMethodId());
        clone.setItemMasterSid(getItemMasterSid());
        clone.setRebatePlanMasterSid(getRebatePlanMasterSid());
        clone.setModifiedDate(getModifiedDate());
        clone.setRsContractDetailsSid(getRsContractDetailsSid());
        clone.setBundleNo(getBundleNo());
        clone.setRecordLockStatus(getRecordLockStatus());
        clone.setCreatedDate(getCreatedDate());
        clone.setCreatedBy(getCreatedBy());
        clone.setSource(getSource());
        clone.setRsContractSid(getRsContractSid());
        clone.setItemRebateEndDate(getItemRebateEndDate());
        clone.setBatchId(getBatchId());
        clone.setItemRebateStartDate(getItemRebateStartDate());
        clone.setModifiedBy(getModifiedBy());
        clone.setFormulaId(getFormulaId());
        clone.setInboundStatus(getInboundStatus());
        clone.setDeductionCalendarMasterSid(getDeductionCalendarMasterSid());
        clone.setNetSalesFormulaMasterSid(getNetSalesFormulaMasterSid());
        clone.setFormulaType(getFormulaType());
        clone.setNetSalesRule(getNetSalesRule());
        clone.setEvaluationRule(getEvaluationRule());
        clone.setEvaluationRuleBundle(getEvaluationRuleBundle());
        clone.setCalculationRule(getCalculationRule());
        clone.setCalculationRuleBundle(getCalculationRuleBundle());
        clone.setRsContractAttachedDate(getRsContractAttachedDate());
        clone.setRsContractAttachedStatus(getRsContractAttachedStatus());

        return clone;
    }

    @Override
    public int compareTo(RsContractDetails rsContractDetails) {
        int primaryKey = rsContractDetails.getPrimaryKey();

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

        if (!(obj instanceof RsContractDetailsClp)) {
            return false;
        }

        RsContractDetailsClp rsContractDetails = (RsContractDetailsClp) obj;

        int primaryKey = rsContractDetails.getPrimaryKey();

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
        StringBundler sb = new StringBundler(57);

        sb.append("{rebateAmount=");
        sb.append(getRebateAmount());
        sb.append(", formulaMethodId=");
        sb.append(getFormulaMethodId());
        sb.append(", itemMasterSid=");
        sb.append(getItemMasterSid());
        sb.append(", rebatePlanMasterSid=");
        sb.append(getRebatePlanMasterSid());
        sb.append(", modifiedDate=");
        sb.append(getModifiedDate());
        sb.append(", rsContractDetailsSid=");
        sb.append(getRsContractDetailsSid());
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
        sb.append(", rsContractSid=");
        sb.append(getRsContractSid());
        sb.append(", itemRebateEndDate=");
        sb.append(getItemRebateEndDate());
        sb.append(", batchId=");
        sb.append(getBatchId());
        sb.append(", itemRebateStartDate=");
        sb.append(getItemRebateStartDate());
        sb.append(", modifiedBy=");
        sb.append(getModifiedBy());
        sb.append(", formulaId=");
        sb.append(getFormulaId());
        sb.append(", inboundStatus=");
        sb.append(getInboundStatus());
        sb.append(", deductionCalendarMasterSid=");
        sb.append(getDeductionCalendarMasterSid());
        sb.append(", netSalesFormulaMasterSid=");
        sb.append(getNetSalesFormulaMasterSid());
        sb.append(", formulaType=");
        sb.append(getFormulaType());
        sb.append(", netSalesRule=");
        sb.append(getNetSalesRule());
        sb.append(", evaluationRule=");
        sb.append(getEvaluationRule());
        sb.append(", evaluationRuleBundle=");
        sb.append(getEvaluationRuleBundle());
        sb.append(", calculationRule=");
        sb.append(getCalculationRule());
        sb.append(", calculationRuleBundle=");
        sb.append(getCalculationRuleBundle());
        sb.append(", rsContractAttachedDate=");
        sb.append(getRsContractAttachedDate());
        sb.append(", rsContractAttachedStatus=");
        sb.append(getRsContractAttachedStatus());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(88);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.RsContractDetails");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>rebateAmount</column-name><column-value><![CDATA[");
        sb.append(getRebateAmount());
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
            "<column><column-name>rsContractDetailsSid</column-name><column-value><![CDATA[");
        sb.append(getRsContractDetailsSid());
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
            "<column><column-name>rsContractSid</column-name><column-value><![CDATA[");
        sb.append(getRsContractSid());
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
            "<column><column-name>formulaId</column-name><column-value><![CDATA[");
        sb.append(getFormulaId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>inboundStatus</column-name><column-value><![CDATA[");
        sb.append(getInboundStatus());
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
            "<column><column-name>formulaType</column-name><column-value><![CDATA[");
        sb.append(getFormulaType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>netSalesRule</column-name><column-value><![CDATA[");
        sb.append(getNetSalesRule());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>evaluationRule</column-name><column-value><![CDATA[");
        sb.append(getEvaluationRule());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>evaluationRuleBundle</column-name><column-value><![CDATA[");
        sb.append(getEvaluationRuleBundle());
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
            "<column><column-name>rsContractAttachedDate</column-name><column-value><![CDATA[");
        sb.append(getRsContractAttachedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>rsContractAttachedStatus</column-name><column-value><![CDATA[");
        sb.append(getRsContractAttachedStatus());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
