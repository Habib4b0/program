package com.stpl.app.parttwo.model;

import com.stpl.app.parttwo.service.ClpSerializer;
import com.stpl.app.parttwo.service.IvldCompanyTradeClassLocalServiceUtil;

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


public class IvldCompanyTradeClassClp extends BaseModelImpl<IvldCompanyTradeClass>
    implements IvldCompanyTradeClass {
    private int _ivldCompanyTradeClassSid;
    private String _priorTradeClass;
    private String _reasonForFailure;
    private String _companyId;
    private String _lastUpdatedDate;
    private String _priorTradeClassStartDate;
    private Date _modifiedDate;
    private String _tradeClassEndDate;
    private String _tradeClassIntfid;
    private String _tradeClassStartDate;
    private String _source;
    private String _createdBy;
    private Date _createdDate;
    private String _addChgDelIndicator;
    private String _batchId;
    private String _errorField;
    private String _errorCode;
    private String _tradeClass;
    private Date _intfInsertedDate;
    private String _modifiedBy;
    private String _reprocessedFlag;
    private boolean _checkRecord;
    private BaseModel<?> _ivldCompanyTradeClassRemoteModel;

    public IvldCompanyTradeClassClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return IvldCompanyTradeClass.class;
    }

    @Override
    public String getModelClassName() {
        return IvldCompanyTradeClass.class.getName();
    }

    @Override
    public int getPrimaryKey() {
        return _ivldCompanyTradeClassSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setIvldCompanyTradeClassSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _ivldCompanyTradeClassSid;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Integer) primaryKeyObj).intValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("ivldCompanyTradeClassSid", getIvldCompanyTradeClassSid());
        attributes.put("priorTradeClass", getPriorTradeClass());
        attributes.put("reasonForFailure", getReasonForFailure());
        attributes.put("companyId", getCompanyId());
        attributes.put("lastUpdatedDate", getLastUpdatedDate());
        attributes.put("priorTradeClassStartDate", getPriorTradeClassStartDate());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("tradeClassEndDate", getTradeClassEndDate());
        attributes.put("tradeClassIntfid", getTradeClassIntfid());
        attributes.put("tradeClassStartDate", getTradeClassStartDate());
        attributes.put("source", getSource());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("addChgDelIndicator", getAddChgDelIndicator());
        attributes.put("batchId", getBatchId());
        attributes.put("errorField", getErrorField());
        attributes.put("errorCode", getErrorCode());
        attributes.put("tradeClass", getTradeClass());
        attributes.put("intfInsertedDate", getIntfInsertedDate());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("reprocessedFlag", getReprocessedFlag());
        attributes.put("checkRecord", getCheckRecord());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Integer ivldCompanyTradeClassSid = (Integer) attributes.get(
                "ivldCompanyTradeClassSid");

        if (ivldCompanyTradeClassSid != null) {
            setIvldCompanyTradeClassSid(ivldCompanyTradeClassSid);
        }

        String priorTradeClass = (String) attributes.get("priorTradeClass");

        if (priorTradeClass != null) {
            setPriorTradeClass(priorTradeClass);
        }

        String reasonForFailure = (String) attributes.get("reasonForFailure");

        if (reasonForFailure != null) {
            setReasonForFailure(reasonForFailure);
        }

        String companyId = (String) attributes.get("companyId");

        if (companyId != null) {
            setCompanyId(companyId);
        }

        String lastUpdatedDate = (String) attributes.get("lastUpdatedDate");

        if (lastUpdatedDate != null) {
            setLastUpdatedDate(lastUpdatedDate);
        }

        String priorTradeClassStartDate = (String) attributes.get(
                "priorTradeClassStartDate");

        if (priorTradeClassStartDate != null) {
            setPriorTradeClassStartDate(priorTradeClassStartDate);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        String tradeClassEndDate = (String) attributes.get("tradeClassEndDate");

        if (tradeClassEndDate != null) {
            setTradeClassEndDate(tradeClassEndDate);
        }

        String tradeClassIntfid = (String) attributes.get("tradeClassIntfid");

        if (tradeClassIntfid != null) {
            setTradeClassIntfid(tradeClassIntfid);
        }

        String tradeClassStartDate = (String) attributes.get(
                "tradeClassStartDate");

        if (tradeClassStartDate != null) {
            setTradeClassStartDate(tradeClassStartDate);
        }

        String source = (String) attributes.get("source");

        if (source != null) {
            setSource(source);
        }

        String createdBy = (String) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        String addChgDelIndicator = (String) attributes.get(
                "addChgDelIndicator");

        if (addChgDelIndicator != null) {
            setAddChgDelIndicator(addChgDelIndicator);
        }

        String batchId = (String) attributes.get("batchId");

        if (batchId != null) {
            setBatchId(batchId);
        }

        String errorField = (String) attributes.get("errorField");

        if (errorField != null) {
            setErrorField(errorField);
        }

        String errorCode = (String) attributes.get("errorCode");

        if (errorCode != null) {
            setErrorCode(errorCode);
        }

        String tradeClass = (String) attributes.get("tradeClass");

        if (tradeClass != null) {
            setTradeClass(tradeClass);
        }

        Date intfInsertedDate = (Date) attributes.get("intfInsertedDate");

        if (intfInsertedDate != null) {
            setIntfInsertedDate(intfInsertedDate);
        }

        String modifiedBy = (String) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        String reprocessedFlag = (String) attributes.get("reprocessedFlag");

        if (reprocessedFlag != null) {
            setReprocessedFlag(reprocessedFlag);
        }

        Boolean checkRecord = (Boolean) attributes.get("checkRecord");

        if (checkRecord != null) {
            setCheckRecord(checkRecord);
        }
    }

    @Override
    public int getIvldCompanyTradeClassSid() {
        return _ivldCompanyTradeClassSid;
    }

    @Override
    public void setIvldCompanyTradeClassSid(int ivldCompanyTradeClassSid) {
        _ivldCompanyTradeClassSid = ivldCompanyTradeClassSid;

        if (_ivldCompanyTradeClassRemoteModel != null) {
            try {
                Class<?> clazz = _ivldCompanyTradeClassRemoteModel.getClass();

                Method method = clazz.getMethod("setIvldCompanyTradeClassSid",
                        int.class);

                method.invoke(_ivldCompanyTradeClassRemoteModel,
                    ivldCompanyTradeClassSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getPriorTradeClass() {
        return _priorTradeClass;
    }

    @Override
    public void setPriorTradeClass(String priorTradeClass) {
        _priorTradeClass = priorTradeClass;

        if (_ivldCompanyTradeClassRemoteModel != null) {
            try {
                Class<?> clazz = _ivldCompanyTradeClassRemoteModel.getClass();

                Method method = clazz.getMethod("setPriorTradeClass",
                        String.class);

                method.invoke(_ivldCompanyTradeClassRemoteModel, priorTradeClass);
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

        if (_ivldCompanyTradeClassRemoteModel != null) {
            try {
                Class<?> clazz = _ivldCompanyTradeClassRemoteModel.getClass();

                Method method = clazz.getMethod("setReasonForFailure",
                        String.class);

                method.invoke(_ivldCompanyTradeClassRemoteModel,
                    reasonForFailure);
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

        if (_ivldCompanyTradeClassRemoteModel != null) {
            try {
                Class<?> clazz = _ivldCompanyTradeClassRemoteModel.getClass();

                Method method = clazz.getMethod("setCompanyId", String.class);

                method.invoke(_ivldCompanyTradeClassRemoteModel, companyId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getLastUpdatedDate() {
        return _lastUpdatedDate;
    }

    @Override
    public void setLastUpdatedDate(String lastUpdatedDate) {
        _lastUpdatedDate = lastUpdatedDate;

        if (_ivldCompanyTradeClassRemoteModel != null) {
            try {
                Class<?> clazz = _ivldCompanyTradeClassRemoteModel.getClass();

                Method method = clazz.getMethod("setLastUpdatedDate",
                        String.class);

                method.invoke(_ivldCompanyTradeClassRemoteModel, lastUpdatedDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getPriorTradeClassStartDate() {
        return _priorTradeClassStartDate;
    }

    @Override
    public void setPriorTradeClassStartDate(String priorTradeClassStartDate) {
        _priorTradeClassStartDate = priorTradeClassStartDate;

        if (_ivldCompanyTradeClassRemoteModel != null) {
            try {
                Class<?> clazz = _ivldCompanyTradeClassRemoteModel.getClass();

                Method method = clazz.getMethod("setPriorTradeClassStartDate",
                        String.class);

                method.invoke(_ivldCompanyTradeClassRemoteModel,
                    priorTradeClassStartDate);
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

        if (_ivldCompanyTradeClassRemoteModel != null) {
            try {
                Class<?> clazz = _ivldCompanyTradeClassRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedDate", Date.class);

                method.invoke(_ivldCompanyTradeClassRemoteModel, modifiedDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getTradeClassEndDate() {
        return _tradeClassEndDate;
    }

    @Override
    public void setTradeClassEndDate(String tradeClassEndDate) {
        _tradeClassEndDate = tradeClassEndDate;

        if (_ivldCompanyTradeClassRemoteModel != null) {
            try {
                Class<?> clazz = _ivldCompanyTradeClassRemoteModel.getClass();

                Method method = clazz.getMethod("setTradeClassEndDate",
                        String.class);

                method.invoke(_ivldCompanyTradeClassRemoteModel,
                    tradeClassEndDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getTradeClassIntfid() {
        return _tradeClassIntfid;
    }

    @Override
    public void setTradeClassIntfid(String tradeClassIntfid) {
        _tradeClassIntfid = tradeClassIntfid;

        if (_ivldCompanyTradeClassRemoteModel != null) {
            try {
                Class<?> clazz = _ivldCompanyTradeClassRemoteModel.getClass();

                Method method = clazz.getMethod("setTradeClassIntfid",
                        String.class);

                method.invoke(_ivldCompanyTradeClassRemoteModel,
                    tradeClassIntfid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getTradeClassStartDate() {
        return _tradeClassStartDate;
    }

    @Override
    public void setTradeClassStartDate(String tradeClassStartDate) {
        _tradeClassStartDate = tradeClassStartDate;

        if (_ivldCompanyTradeClassRemoteModel != null) {
            try {
                Class<?> clazz = _ivldCompanyTradeClassRemoteModel.getClass();

                Method method = clazz.getMethod("setTradeClassStartDate",
                        String.class);

                method.invoke(_ivldCompanyTradeClassRemoteModel,
                    tradeClassStartDate);
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

        if (_ivldCompanyTradeClassRemoteModel != null) {
            try {
                Class<?> clazz = _ivldCompanyTradeClassRemoteModel.getClass();

                Method method = clazz.getMethod("setSource", String.class);

                method.invoke(_ivldCompanyTradeClassRemoteModel, source);
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

        if (_ivldCompanyTradeClassRemoteModel != null) {
            try {
                Class<?> clazz = _ivldCompanyTradeClassRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedBy", String.class);

                method.invoke(_ivldCompanyTradeClassRemoteModel, createdBy);
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

        if (_ivldCompanyTradeClassRemoteModel != null) {
            try {
                Class<?> clazz = _ivldCompanyTradeClassRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedDate", Date.class);

                method.invoke(_ivldCompanyTradeClassRemoteModel, createdDate);
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

        if (_ivldCompanyTradeClassRemoteModel != null) {
            try {
                Class<?> clazz = _ivldCompanyTradeClassRemoteModel.getClass();

                Method method = clazz.getMethod("setAddChgDelIndicator",
                        String.class);

                method.invoke(_ivldCompanyTradeClassRemoteModel,
                    addChgDelIndicator);
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

        if (_ivldCompanyTradeClassRemoteModel != null) {
            try {
                Class<?> clazz = _ivldCompanyTradeClassRemoteModel.getClass();

                Method method = clazz.getMethod("setBatchId", String.class);

                method.invoke(_ivldCompanyTradeClassRemoteModel, batchId);
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

        if (_ivldCompanyTradeClassRemoteModel != null) {
            try {
                Class<?> clazz = _ivldCompanyTradeClassRemoteModel.getClass();

                Method method = clazz.getMethod("setErrorField", String.class);

                method.invoke(_ivldCompanyTradeClassRemoteModel, errorField);
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

        if (_ivldCompanyTradeClassRemoteModel != null) {
            try {
                Class<?> clazz = _ivldCompanyTradeClassRemoteModel.getClass();

                Method method = clazz.getMethod("setErrorCode", String.class);

                method.invoke(_ivldCompanyTradeClassRemoteModel, errorCode);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getTradeClass() {
        return _tradeClass;
    }

    @Override
    public void setTradeClass(String tradeClass) {
        _tradeClass = tradeClass;

        if (_ivldCompanyTradeClassRemoteModel != null) {
            try {
                Class<?> clazz = _ivldCompanyTradeClassRemoteModel.getClass();

                Method method = clazz.getMethod("setTradeClass", String.class);

                method.invoke(_ivldCompanyTradeClassRemoteModel, tradeClass);
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

        if (_ivldCompanyTradeClassRemoteModel != null) {
            try {
                Class<?> clazz = _ivldCompanyTradeClassRemoteModel.getClass();

                Method method = clazz.getMethod("setIntfInsertedDate",
                        Date.class);

                method.invoke(_ivldCompanyTradeClassRemoteModel,
                    intfInsertedDate);
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

        if (_ivldCompanyTradeClassRemoteModel != null) {
            try {
                Class<?> clazz = _ivldCompanyTradeClassRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedBy", String.class);

                method.invoke(_ivldCompanyTradeClassRemoteModel, modifiedBy);
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

        if (_ivldCompanyTradeClassRemoteModel != null) {
            try {
                Class<?> clazz = _ivldCompanyTradeClassRemoteModel.getClass();

                Method method = clazz.getMethod("setReprocessedFlag",
                        String.class);

                method.invoke(_ivldCompanyTradeClassRemoteModel, reprocessedFlag);
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

        if (_ivldCompanyTradeClassRemoteModel != null) {
            try {
                Class<?> clazz = _ivldCompanyTradeClassRemoteModel.getClass();

                Method method = clazz.getMethod("setCheckRecord", boolean.class);

                method.invoke(_ivldCompanyTradeClassRemoteModel, checkRecord);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getIvldCompanyTradeClassRemoteModel() {
        return _ivldCompanyTradeClassRemoteModel;
    }

    public void setIvldCompanyTradeClassRemoteModel(
        BaseModel<?> ivldCompanyTradeClassRemoteModel) {
        _ivldCompanyTradeClassRemoteModel = ivldCompanyTradeClassRemoteModel;
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

        Class<?> remoteModelClass = _ivldCompanyTradeClassRemoteModel.getClass();

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

        Object returnValue = method.invoke(_ivldCompanyTradeClassRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            IvldCompanyTradeClassLocalServiceUtil.addIvldCompanyTradeClass(this);
        } else {
            IvldCompanyTradeClassLocalServiceUtil.updateIvldCompanyTradeClass(this);
        }
    }

    @Override
    public IvldCompanyTradeClass toEscapedModel() {
        return (IvldCompanyTradeClass) ProxyUtil.newProxyInstance(IvldCompanyTradeClass.class.getClassLoader(),
            new Class[] { IvldCompanyTradeClass.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        IvldCompanyTradeClassClp clone = new IvldCompanyTradeClassClp();

        clone.setIvldCompanyTradeClassSid(getIvldCompanyTradeClassSid());
        clone.setPriorTradeClass(getPriorTradeClass());
        clone.setReasonForFailure(getReasonForFailure());
        clone.setCompanyId(getCompanyId());
        clone.setLastUpdatedDate(getLastUpdatedDate());
        clone.setPriorTradeClassStartDate(getPriorTradeClassStartDate());
        clone.setModifiedDate(getModifiedDate());
        clone.setTradeClassEndDate(getTradeClassEndDate());
        clone.setTradeClassIntfid(getTradeClassIntfid());
        clone.setTradeClassStartDate(getTradeClassStartDate());
        clone.setSource(getSource());
        clone.setCreatedBy(getCreatedBy());
        clone.setCreatedDate(getCreatedDate());
        clone.setAddChgDelIndicator(getAddChgDelIndicator());
        clone.setBatchId(getBatchId());
        clone.setErrorField(getErrorField());
        clone.setErrorCode(getErrorCode());
        clone.setTradeClass(getTradeClass());
        clone.setIntfInsertedDate(getIntfInsertedDate());
        clone.setModifiedBy(getModifiedBy());
        clone.setReprocessedFlag(getReprocessedFlag());
        clone.setCheckRecord(getCheckRecord());

        return clone;
    }

    @Override
    public int compareTo(IvldCompanyTradeClass ivldCompanyTradeClass) {
        int primaryKey = ivldCompanyTradeClass.getPrimaryKey();

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

        if (!(obj instanceof IvldCompanyTradeClassClp)) {
            return false;
        }

        IvldCompanyTradeClassClp ivldCompanyTradeClass = (IvldCompanyTradeClassClp) obj;

        int primaryKey = ivldCompanyTradeClass.getPrimaryKey();

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
        StringBundler sb = new StringBundler(45);

        sb.append("{ivldCompanyTradeClassSid=");
        sb.append(getIvldCompanyTradeClassSid());
        sb.append(", priorTradeClass=");
        sb.append(getPriorTradeClass());
        sb.append(", reasonForFailure=");
        sb.append(getReasonForFailure());
        sb.append(", companyId=");
        sb.append(getCompanyId());
        sb.append(", lastUpdatedDate=");
        sb.append(getLastUpdatedDate());
        sb.append(", priorTradeClassStartDate=");
        sb.append(getPriorTradeClassStartDate());
        sb.append(", modifiedDate=");
        sb.append(getModifiedDate());
        sb.append(", tradeClassEndDate=");
        sb.append(getTradeClassEndDate());
        sb.append(", tradeClassIntfid=");
        sb.append(getTradeClassIntfid());
        sb.append(", tradeClassStartDate=");
        sb.append(getTradeClassStartDate());
        sb.append(", source=");
        sb.append(getSource());
        sb.append(", createdBy=");
        sb.append(getCreatedBy());
        sb.append(", createdDate=");
        sb.append(getCreatedDate());
        sb.append(", addChgDelIndicator=");
        sb.append(getAddChgDelIndicator());
        sb.append(", batchId=");
        sb.append(getBatchId());
        sb.append(", errorField=");
        sb.append(getErrorField());
        sb.append(", errorCode=");
        sb.append(getErrorCode());
        sb.append(", tradeClass=");
        sb.append(getTradeClass());
        sb.append(", intfInsertedDate=");
        sb.append(getIntfInsertedDate());
        sb.append(", modifiedBy=");
        sb.append(getModifiedBy());
        sb.append(", reprocessedFlag=");
        sb.append(getReprocessedFlag());
        sb.append(", checkRecord=");
        sb.append(getCheckRecord());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(70);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.parttwo.model.IvldCompanyTradeClass");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>ivldCompanyTradeClassSid</column-name><column-value><![CDATA[");
        sb.append(getIvldCompanyTradeClassSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>priorTradeClass</column-name><column-value><![CDATA[");
        sb.append(getPriorTradeClass());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>reasonForFailure</column-name><column-value><![CDATA[");
        sb.append(getReasonForFailure());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>companyId</column-name><column-value><![CDATA[");
        sb.append(getCompanyId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>lastUpdatedDate</column-name><column-value><![CDATA[");
        sb.append(getLastUpdatedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>priorTradeClassStartDate</column-name><column-value><![CDATA[");
        sb.append(getPriorTradeClassStartDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
        sb.append(getModifiedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>tradeClassEndDate</column-name><column-value><![CDATA[");
        sb.append(getTradeClassEndDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>tradeClassIntfid</column-name><column-value><![CDATA[");
        sb.append(getTradeClassIntfid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>tradeClassStartDate</column-name><column-value><![CDATA[");
        sb.append(getTradeClassStartDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>source</column-name><column-value><![CDATA[");
        sb.append(getSource());
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
            "<column><column-name>addChgDelIndicator</column-name><column-value><![CDATA[");
        sb.append(getAddChgDelIndicator());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>batchId</column-name><column-value><![CDATA[");
        sb.append(getBatchId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>errorField</column-name><column-value><![CDATA[");
        sb.append(getErrorField());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>errorCode</column-name><column-value><![CDATA[");
        sb.append(getErrorCode());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>tradeClass</column-name><column-value><![CDATA[");
        sb.append(getTradeClass());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>intfInsertedDate</column-name><column-value><![CDATA[");
        sb.append(getIntfInsertedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedBy</column-name><column-value><![CDATA[");
        sb.append(getModifiedBy());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>reprocessedFlag</column-name><column-value><![CDATA[");
        sb.append(getReprocessedFlag());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>checkRecord</column-name><column-value><![CDATA[");
        sb.append(getCheckRecord());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
