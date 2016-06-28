package com.stpl.app.model;

import com.stpl.app.service.ClpSerializer;
import com.stpl.app.service.RebatePlanTierLocalServiceUtil;

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


public class RebatePlanTierClp extends BaseModelImpl<RebatePlanTier>
    implements RebatePlanTier {
    private double _tierValue;
    private String _returnRateSid;
    private int _rebatePlanMasterSid;
    private int _rebatePlanTierSid;
    private String _itemPricingQualifierSid;
    private Date _modifiedDate;
    private double _tierTolerance;
    private String _tierFrom;
    private String _tierOperator;
    private boolean _recordLockStatus;
    private Date _createdDate;
    private int _createdBy;
    private String _source;
    private String _tierTo;
    private String _batchId;
    private String _rebatePlanTierId;
    private double _freeAmount;
    private int _modifiedBy;
    private String _inboundStatus;
    private String _tierLevel;
    private String _formulaNo;
    private String _formulaName;
    private String _secondaryRebatePlanNo;
    private String _secondaryRebatePlanName;
    private String _secondaryRebatePlanSid;
    private BaseModel<?> _rebatePlanTierRemoteModel;

    public RebatePlanTierClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return RebatePlanTier.class;
    }

    @Override
    public String getModelClassName() {
        return RebatePlanTier.class.getName();
    }

    @Override
    public int getPrimaryKey() {
        return _rebatePlanTierSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setRebatePlanTierSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _rebatePlanTierSid;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Integer) primaryKeyObj).intValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("tierValue", getTierValue());
        attributes.put("returnRateSid", getReturnRateSid());
        attributes.put("rebatePlanMasterSid", getRebatePlanMasterSid());
        attributes.put("rebatePlanTierSid", getRebatePlanTierSid());
        attributes.put("itemPricingQualifierSid", getItemPricingQualifierSid());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("tierTolerance", getTierTolerance());
        attributes.put("tierFrom", getTierFrom());
        attributes.put("tierOperator", getTierOperator());
        attributes.put("recordLockStatus", getRecordLockStatus());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("source", getSource());
        attributes.put("tierTo", getTierTo());
        attributes.put("batchId", getBatchId());
        attributes.put("rebatePlanTierId", getRebatePlanTierId());
        attributes.put("freeAmount", getFreeAmount());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("inboundStatus", getInboundStatus());
        attributes.put("tierLevel", getTierLevel());
        attributes.put("formulaNo", getFormulaNo());
        attributes.put("formulaName", getFormulaName());
        attributes.put("secondaryRebatePlanNo", getSecondaryRebatePlanNo());
        attributes.put("secondaryRebatePlanName", getSecondaryRebatePlanName());
        attributes.put("secondaryRebatePlanSid", getSecondaryRebatePlanSid());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Double tierValue = (Double) attributes.get("tierValue");

        if (tierValue != null) {
            setTierValue(tierValue);
        }

        String returnRateSid = (String) attributes.get("returnRateSid");

        if (returnRateSid != null) {
            setReturnRateSid(returnRateSid);
        }

        Integer rebatePlanMasterSid = (Integer) attributes.get(
                "rebatePlanMasterSid");

        if (rebatePlanMasterSid != null) {
            setRebatePlanMasterSid(rebatePlanMasterSid);
        }

        Integer rebatePlanTierSid = (Integer) attributes.get(
                "rebatePlanTierSid");

        if (rebatePlanTierSid != null) {
            setRebatePlanTierSid(rebatePlanTierSid);
        }

        String itemPricingQualifierSid = (String) attributes.get(
                "itemPricingQualifierSid");

        if (itemPricingQualifierSid != null) {
            setItemPricingQualifierSid(itemPricingQualifierSid);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        Double tierTolerance = (Double) attributes.get("tierTolerance");

        if (tierTolerance != null) {
            setTierTolerance(tierTolerance);
        }

        String tierFrom = (String) attributes.get("tierFrom");

        if (tierFrom != null) {
            setTierFrom(tierFrom);
        }

        String tierOperator = (String) attributes.get("tierOperator");

        if (tierOperator != null) {
            setTierOperator(tierOperator);
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

        String tierTo = (String) attributes.get("tierTo");

        if (tierTo != null) {
            setTierTo(tierTo);
        }

        String batchId = (String) attributes.get("batchId");

        if (batchId != null) {
            setBatchId(batchId);
        }

        String rebatePlanTierId = (String) attributes.get("rebatePlanTierId");

        if (rebatePlanTierId != null) {
            setRebatePlanTierId(rebatePlanTierId);
        }

        Double freeAmount = (Double) attributes.get("freeAmount");

        if (freeAmount != null) {
            setFreeAmount(freeAmount);
        }

        Integer modifiedBy = (Integer) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        String inboundStatus = (String) attributes.get("inboundStatus");

        if (inboundStatus != null) {
            setInboundStatus(inboundStatus);
        }

        String tierLevel = (String) attributes.get("tierLevel");

        if (tierLevel != null) {
            setTierLevel(tierLevel);
        }

        String formulaNo = (String) attributes.get("formulaNo");

        if (formulaNo != null) {
            setFormulaNo(formulaNo);
        }

        String formulaName = (String) attributes.get("formulaName");

        if (formulaName != null) {
            setFormulaName(formulaName);
        }

        String secondaryRebatePlanNo = (String) attributes.get(
                "secondaryRebatePlanNo");

        if (secondaryRebatePlanNo != null) {
            setSecondaryRebatePlanNo(secondaryRebatePlanNo);
        }

        String secondaryRebatePlanName = (String) attributes.get(
                "secondaryRebatePlanName");

        if (secondaryRebatePlanName != null) {
            setSecondaryRebatePlanName(secondaryRebatePlanName);
        }

        String secondaryRebatePlanSid = (String) attributes.get(
                "secondaryRebatePlanSid");

        if (secondaryRebatePlanSid != null) {
            setSecondaryRebatePlanSid(secondaryRebatePlanSid);
        }
    }

    @Override
    public double getTierValue() {
        return _tierValue;
    }

    @Override
    public void setTierValue(double tierValue) {
        _tierValue = tierValue;

        if (_rebatePlanTierRemoteModel != null) {
            try {
                Class<?> clazz = _rebatePlanTierRemoteModel.getClass();

                Method method = clazz.getMethod("setTierValue", double.class);

                method.invoke(_rebatePlanTierRemoteModel, tierValue);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getReturnRateSid() {
        return _returnRateSid;
    }

    @Override
    public void setReturnRateSid(String returnRateSid) {
        _returnRateSid = returnRateSid;

        if (_rebatePlanTierRemoteModel != null) {
            try {
                Class<?> clazz = _rebatePlanTierRemoteModel.getClass();

                Method method = clazz.getMethod("setReturnRateSid", String.class);

                method.invoke(_rebatePlanTierRemoteModel, returnRateSid);
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

        if (_rebatePlanTierRemoteModel != null) {
            try {
                Class<?> clazz = _rebatePlanTierRemoteModel.getClass();

                Method method = clazz.getMethod("setRebatePlanMasterSid",
                        int.class);

                method.invoke(_rebatePlanTierRemoteModel, rebatePlanMasterSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getRebatePlanTierSid() {
        return _rebatePlanTierSid;
    }

    @Override
    public void setRebatePlanTierSid(int rebatePlanTierSid) {
        _rebatePlanTierSid = rebatePlanTierSid;

        if (_rebatePlanTierRemoteModel != null) {
            try {
                Class<?> clazz = _rebatePlanTierRemoteModel.getClass();

                Method method = clazz.getMethod("setRebatePlanTierSid",
                        int.class);

                method.invoke(_rebatePlanTierRemoteModel, rebatePlanTierSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getItemPricingQualifierSid() {
        return _itemPricingQualifierSid;
    }

    @Override
    public void setItemPricingQualifierSid(String itemPricingQualifierSid) {
        _itemPricingQualifierSid = itemPricingQualifierSid;

        if (_rebatePlanTierRemoteModel != null) {
            try {
                Class<?> clazz = _rebatePlanTierRemoteModel.getClass();

                Method method = clazz.getMethod("setItemPricingQualifierSid",
                        String.class);

                method.invoke(_rebatePlanTierRemoteModel,
                    itemPricingQualifierSid);
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

        if (_rebatePlanTierRemoteModel != null) {
            try {
                Class<?> clazz = _rebatePlanTierRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedDate", Date.class);

                method.invoke(_rebatePlanTierRemoteModel, modifiedDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getTierTolerance() {
        return _tierTolerance;
    }

    @Override
    public void setTierTolerance(double tierTolerance) {
        _tierTolerance = tierTolerance;

        if (_rebatePlanTierRemoteModel != null) {
            try {
                Class<?> clazz = _rebatePlanTierRemoteModel.getClass();

                Method method = clazz.getMethod("setTierTolerance", double.class);

                method.invoke(_rebatePlanTierRemoteModel, tierTolerance);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getTierFrom() {
        return _tierFrom;
    }

    @Override
    public void setTierFrom(String tierFrom) {
        _tierFrom = tierFrom;

        if (_rebatePlanTierRemoteModel != null) {
            try {
                Class<?> clazz = _rebatePlanTierRemoteModel.getClass();

                Method method = clazz.getMethod("setTierFrom", String.class);

                method.invoke(_rebatePlanTierRemoteModel, tierFrom);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getTierOperator() {
        return _tierOperator;
    }

    @Override
    public void setTierOperator(String tierOperator) {
        _tierOperator = tierOperator;

        if (_rebatePlanTierRemoteModel != null) {
            try {
                Class<?> clazz = _rebatePlanTierRemoteModel.getClass();

                Method method = clazz.getMethod("setTierOperator", String.class);

                method.invoke(_rebatePlanTierRemoteModel, tierOperator);
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

        if (_rebatePlanTierRemoteModel != null) {
            try {
                Class<?> clazz = _rebatePlanTierRemoteModel.getClass();

                Method method = clazz.getMethod("setRecordLockStatus",
                        boolean.class);

                method.invoke(_rebatePlanTierRemoteModel, recordLockStatus);
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

        if (_rebatePlanTierRemoteModel != null) {
            try {
                Class<?> clazz = _rebatePlanTierRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedDate", Date.class);

                method.invoke(_rebatePlanTierRemoteModel, createdDate);
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

        if (_rebatePlanTierRemoteModel != null) {
            try {
                Class<?> clazz = _rebatePlanTierRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedBy", int.class);

                method.invoke(_rebatePlanTierRemoteModel, createdBy);
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

        if (_rebatePlanTierRemoteModel != null) {
            try {
                Class<?> clazz = _rebatePlanTierRemoteModel.getClass();

                Method method = clazz.getMethod("setSource", String.class);

                method.invoke(_rebatePlanTierRemoteModel, source);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getTierTo() {
        return _tierTo;
    }

    @Override
    public void setTierTo(String tierTo) {
        _tierTo = tierTo;

        if (_rebatePlanTierRemoteModel != null) {
            try {
                Class<?> clazz = _rebatePlanTierRemoteModel.getClass();

                Method method = clazz.getMethod("setTierTo", String.class);

                method.invoke(_rebatePlanTierRemoteModel, tierTo);
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

        if (_rebatePlanTierRemoteModel != null) {
            try {
                Class<?> clazz = _rebatePlanTierRemoteModel.getClass();

                Method method = clazz.getMethod("setBatchId", String.class);

                method.invoke(_rebatePlanTierRemoteModel, batchId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getRebatePlanTierId() {
        return _rebatePlanTierId;
    }

    @Override
    public void setRebatePlanTierId(String rebatePlanTierId) {
        _rebatePlanTierId = rebatePlanTierId;

        if (_rebatePlanTierRemoteModel != null) {
            try {
                Class<?> clazz = _rebatePlanTierRemoteModel.getClass();

                Method method = clazz.getMethod("setRebatePlanTierId",
                        String.class);

                method.invoke(_rebatePlanTierRemoteModel, rebatePlanTierId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getFreeAmount() {
        return _freeAmount;
    }

    @Override
    public void setFreeAmount(double freeAmount) {
        _freeAmount = freeAmount;

        if (_rebatePlanTierRemoteModel != null) {
            try {
                Class<?> clazz = _rebatePlanTierRemoteModel.getClass();

                Method method = clazz.getMethod("setFreeAmount", double.class);

                method.invoke(_rebatePlanTierRemoteModel, freeAmount);
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

        if (_rebatePlanTierRemoteModel != null) {
            try {
                Class<?> clazz = _rebatePlanTierRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedBy", int.class);

                method.invoke(_rebatePlanTierRemoteModel, modifiedBy);
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

        if (_rebatePlanTierRemoteModel != null) {
            try {
                Class<?> clazz = _rebatePlanTierRemoteModel.getClass();

                Method method = clazz.getMethod("setInboundStatus", String.class);

                method.invoke(_rebatePlanTierRemoteModel, inboundStatus);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getTierLevel() {
        return _tierLevel;
    }

    @Override
    public void setTierLevel(String tierLevel) {
        _tierLevel = tierLevel;

        if (_rebatePlanTierRemoteModel != null) {
            try {
                Class<?> clazz = _rebatePlanTierRemoteModel.getClass();

                Method method = clazz.getMethod("setTierLevel", String.class);

                method.invoke(_rebatePlanTierRemoteModel, tierLevel);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getFormulaNo() {
        return _formulaNo;
    }

    @Override
    public void setFormulaNo(String formulaNo) {
        _formulaNo = formulaNo;

        if (_rebatePlanTierRemoteModel != null) {
            try {
                Class<?> clazz = _rebatePlanTierRemoteModel.getClass();

                Method method = clazz.getMethod("setFormulaNo", String.class);

                method.invoke(_rebatePlanTierRemoteModel, formulaNo);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getFormulaName() {
        return _formulaName;
    }

    @Override
    public void setFormulaName(String formulaName) {
        _formulaName = formulaName;

        if (_rebatePlanTierRemoteModel != null) {
            try {
                Class<?> clazz = _rebatePlanTierRemoteModel.getClass();

                Method method = clazz.getMethod("setFormulaName", String.class);

                method.invoke(_rebatePlanTierRemoteModel, formulaName);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getSecondaryRebatePlanNo() {
        return _secondaryRebatePlanNo;
    }

    @Override
    public void setSecondaryRebatePlanNo(String secondaryRebatePlanNo) {
        _secondaryRebatePlanNo = secondaryRebatePlanNo;

        if (_rebatePlanTierRemoteModel != null) {
            try {
                Class<?> clazz = _rebatePlanTierRemoteModel.getClass();

                Method method = clazz.getMethod("setSecondaryRebatePlanNo",
                        String.class);

                method.invoke(_rebatePlanTierRemoteModel, secondaryRebatePlanNo);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getSecondaryRebatePlanName() {
        return _secondaryRebatePlanName;
    }

    @Override
    public void setSecondaryRebatePlanName(String secondaryRebatePlanName) {
        _secondaryRebatePlanName = secondaryRebatePlanName;

        if (_rebatePlanTierRemoteModel != null) {
            try {
                Class<?> clazz = _rebatePlanTierRemoteModel.getClass();

                Method method = clazz.getMethod("setSecondaryRebatePlanName",
                        String.class);

                method.invoke(_rebatePlanTierRemoteModel,
                    secondaryRebatePlanName);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getSecondaryRebatePlanSid() {
        return _secondaryRebatePlanSid;
    }

    @Override
    public void setSecondaryRebatePlanSid(String secondaryRebatePlanSid) {
        _secondaryRebatePlanSid = secondaryRebatePlanSid;

        if (_rebatePlanTierRemoteModel != null) {
            try {
                Class<?> clazz = _rebatePlanTierRemoteModel.getClass();

                Method method = clazz.getMethod("setSecondaryRebatePlanSid",
                        String.class);

                method.invoke(_rebatePlanTierRemoteModel, secondaryRebatePlanSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getRebatePlanTierRemoteModel() {
        return _rebatePlanTierRemoteModel;
    }

    public void setRebatePlanTierRemoteModel(
        BaseModel<?> rebatePlanTierRemoteModel) {
        _rebatePlanTierRemoteModel = rebatePlanTierRemoteModel;
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

        Class<?> remoteModelClass = _rebatePlanTierRemoteModel.getClass();

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

        Object returnValue = method.invoke(_rebatePlanTierRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            RebatePlanTierLocalServiceUtil.addRebatePlanTier(this);
        } else {
            RebatePlanTierLocalServiceUtil.updateRebatePlanTier(this);
        }
    }

    @Override
    public RebatePlanTier toEscapedModel() {
        return (RebatePlanTier) ProxyUtil.newProxyInstance(RebatePlanTier.class.getClassLoader(),
            new Class[] { RebatePlanTier.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        RebatePlanTierClp clone = new RebatePlanTierClp();

        clone.setTierValue(getTierValue());
        clone.setReturnRateSid(getReturnRateSid());
        clone.setRebatePlanMasterSid(getRebatePlanMasterSid());
        clone.setRebatePlanTierSid(getRebatePlanTierSid());
        clone.setItemPricingQualifierSid(getItemPricingQualifierSid());
        clone.setModifiedDate(getModifiedDate());
        clone.setTierTolerance(getTierTolerance());
        clone.setTierFrom(getTierFrom());
        clone.setTierOperator(getTierOperator());
        clone.setRecordLockStatus(getRecordLockStatus());
        clone.setCreatedDate(getCreatedDate());
        clone.setCreatedBy(getCreatedBy());
        clone.setSource(getSource());
        clone.setTierTo(getTierTo());
        clone.setBatchId(getBatchId());
        clone.setRebatePlanTierId(getRebatePlanTierId());
        clone.setFreeAmount(getFreeAmount());
        clone.setModifiedBy(getModifiedBy());
        clone.setInboundStatus(getInboundStatus());
        clone.setTierLevel(getTierLevel());
        clone.setFormulaNo(getFormulaNo());
        clone.setFormulaName(getFormulaName());
        clone.setSecondaryRebatePlanNo(getSecondaryRebatePlanNo());
        clone.setSecondaryRebatePlanName(getSecondaryRebatePlanName());
        clone.setSecondaryRebatePlanSid(getSecondaryRebatePlanSid());

        return clone;
    }

    @Override
    public int compareTo(RebatePlanTier rebatePlanTier) {
        int primaryKey = rebatePlanTier.getPrimaryKey();

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

        if (!(obj instanceof RebatePlanTierClp)) {
            return false;
        }

        RebatePlanTierClp rebatePlanTier = (RebatePlanTierClp) obj;

        int primaryKey = rebatePlanTier.getPrimaryKey();

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
        StringBundler sb = new StringBundler(51);

        sb.append("{tierValue=");
        sb.append(getTierValue());
        sb.append(", returnRateSid=");
        sb.append(getReturnRateSid());
        sb.append(", rebatePlanMasterSid=");
        sb.append(getRebatePlanMasterSid());
        sb.append(", rebatePlanTierSid=");
        sb.append(getRebatePlanTierSid());
        sb.append(", itemPricingQualifierSid=");
        sb.append(getItemPricingQualifierSid());
        sb.append(", modifiedDate=");
        sb.append(getModifiedDate());
        sb.append(", tierTolerance=");
        sb.append(getTierTolerance());
        sb.append(", tierFrom=");
        sb.append(getTierFrom());
        sb.append(", tierOperator=");
        sb.append(getTierOperator());
        sb.append(", recordLockStatus=");
        sb.append(getRecordLockStatus());
        sb.append(", createdDate=");
        sb.append(getCreatedDate());
        sb.append(", createdBy=");
        sb.append(getCreatedBy());
        sb.append(", source=");
        sb.append(getSource());
        sb.append(", tierTo=");
        sb.append(getTierTo());
        sb.append(", batchId=");
        sb.append(getBatchId());
        sb.append(", rebatePlanTierId=");
        sb.append(getRebatePlanTierId());
        sb.append(", freeAmount=");
        sb.append(getFreeAmount());
        sb.append(", modifiedBy=");
        sb.append(getModifiedBy());
        sb.append(", inboundStatus=");
        sb.append(getInboundStatus());
        sb.append(", tierLevel=");
        sb.append(getTierLevel());
        sb.append(", formulaNo=");
        sb.append(getFormulaNo());
        sb.append(", formulaName=");
        sb.append(getFormulaName());
        sb.append(", secondaryRebatePlanNo=");
        sb.append(getSecondaryRebatePlanNo());
        sb.append(", secondaryRebatePlanName=");
        sb.append(getSecondaryRebatePlanName());
        sb.append(", secondaryRebatePlanSid=");
        sb.append(getSecondaryRebatePlanSid());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(79);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.RebatePlanTier");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>tierValue</column-name><column-value><![CDATA[");
        sb.append(getTierValue());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>returnRateSid</column-name><column-value><![CDATA[");
        sb.append(getReturnRateSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>rebatePlanMasterSid</column-name><column-value><![CDATA[");
        sb.append(getRebatePlanMasterSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>rebatePlanTierSid</column-name><column-value><![CDATA[");
        sb.append(getRebatePlanTierSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemPricingQualifierSid</column-name><column-value><![CDATA[");
        sb.append(getItemPricingQualifierSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
        sb.append(getModifiedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>tierTolerance</column-name><column-value><![CDATA[");
        sb.append(getTierTolerance());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>tierFrom</column-name><column-value><![CDATA[");
        sb.append(getTierFrom());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>tierOperator</column-name><column-value><![CDATA[");
        sb.append(getTierOperator());
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
            "<column><column-name>tierTo</column-name><column-value><![CDATA[");
        sb.append(getTierTo());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>batchId</column-name><column-value><![CDATA[");
        sb.append(getBatchId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>rebatePlanTierId</column-name><column-value><![CDATA[");
        sb.append(getRebatePlanTierId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>freeAmount</column-name><column-value><![CDATA[");
        sb.append(getFreeAmount());
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
            "<column><column-name>tierLevel</column-name><column-value><![CDATA[");
        sb.append(getTierLevel());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>formulaNo</column-name><column-value><![CDATA[");
        sb.append(getFormulaNo());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>formulaName</column-name><column-value><![CDATA[");
        sb.append(getFormulaName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>secondaryRebatePlanNo</column-name><column-value><![CDATA[");
        sb.append(getSecondaryRebatePlanNo());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>secondaryRebatePlanName</column-name><column-value><![CDATA[");
        sb.append(getSecondaryRebatePlanName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>secondaryRebatePlanSid</column-name><column-value><![CDATA[");
        sb.append(getSecondaryRebatePlanSid());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
