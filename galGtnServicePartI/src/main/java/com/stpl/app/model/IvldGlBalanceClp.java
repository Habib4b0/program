package com.stpl.app.model;

import com.stpl.app.service.ClpSerializer;
import com.stpl.app.service.IvldGlBalanceLocalServiceUtil;

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


public class IvldGlBalanceClp extends BaseModelImpl<IvldGlBalance>
    implements IvldGlBalance {
    private String _balance;
    private String _accountNo;
    private String _reasonForFailure;
    private String _accountId;
    private String _year;
    private String _period;
    private Date _modifiedDate;
    private String _isActive;
    private String _source;
    private String _uploadDate;
    private String _createdBy;
    private Date _createdDate;
    private String _addChgDelIndicator;
    private String _batchId;
    private String _closeIndicator;
    private String _insertedDate;
    private String _errorField;
    private int _ivldGlBalanceSid;
    private String _errorCode;
    private String _glBalanceIntfid;
    private Date _intfInsertedDate;
    private String _modifiedBy;
    private String _reprocessedFlag;
    private BaseModel<?> _ivldGlBalanceRemoteModel;

    public IvldGlBalanceClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return IvldGlBalance.class;
    }

    @Override
    public String getModelClassName() {
        return IvldGlBalance.class.getName();
    }

    @Override
    public int getPrimaryKey() {
        return _ivldGlBalanceSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setIvldGlBalanceSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _ivldGlBalanceSid;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Integer) primaryKeyObj).intValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("balance", getBalance());
        attributes.put("accountNo", getAccountNo());
        attributes.put("reasonForFailure", getReasonForFailure());
        attributes.put("accountId", getAccountId());
        attributes.put("year", getYear());
        attributes.put("period", getPeriod());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("isActive", getIsActive());
        attributes.put("source", getSource());
        attributes.put("uploadDate", getUploadDate());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("addChgDelIndicator", getAddChgDelIndicator());
        attributes.put("batchId", getBatchId());
        attributes.put("closeIndicator", getCloseIndicator());
        attributes.put("insertedDate", getInsertedDate());
        attributes.put("errorField", getErrorField());
        attributes.put("ivldGlBalanceSid", getIvldGlBalanceSid());
        attributes.put("errorCode", getErrorCode());
        attributes.put("glBalanceIntfid", getGlBalanceIntfid());
        attributes.put("intfInsertedDate", getIntfInsertedDate());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("reprocessedFlag", getReprocessedFlag());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        String balance = (String) attributes.get("balance");

        if (balance != null) {
            setBalance(balance);
        }

        String accountNo = (String) attributes.get("accountNo");

        if (accountNo != null) {
            setAccountNo(accountNo);
        }

        String reasonForFailure = (String) attributes.get("reasonForFailure");

        if (reasonForFailure != null) {
            setReasonForFailure(reasonForFailure);
        }

        String accountId = (String) attributes.get("accountId");

        if (accountId != null) {
            setAccountId(accountId);
        }

        String year = (String) attributes.get("year");

        if (year != null) {
            setYear(year);
        }

        String period = (String) attributes.get("period");

        if (period != null) {
            setPeriod(period);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        String isActive = (String) attributes.get("isActive");

        if (isActive != null) {
            setIsActive(isActive);
        }

        String source = (String) attributes.get("source");

        if (source != null) {
            setSource(source);
        }

        String uploadDate = (String) attributes.get("uploadDate");

        if (uploadDate != null) {
            setUploadDate(uploadDate);
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

        String closeIndicator = (String) attributes.get("closeIndicator");

        if (closeIndicator != null) {
            setCloseIndicator(closeIndicator);
        }

        String insertedDate = (String) attributes.get("insertedDate");

        if (insertedDate != null) {
            setInsertedDate(insertedDate);
        }

        String errorField = (String) attributes.get("errorField");

        if (errorField != null) {
            setErrorField(errorField);
        }

        Integer ivldGlBalanceSid = (Integer) attributes.get("ivldGlBalanceSid");

        if (ivldGlBalanceSid != null) {
            setIvldGlBalanceSid(ivldGlBalanceSid);
        }

        String errorCode = (String) attributes.get("errorCode");

        if (errorCode != null) {
            setErrorCode(errorCode);
        }

        String glBalanceIntfid = (String) attributes.get("glBalanceIntfid");

        if (glBalanceIntfid != null) {
            setGlBalanceIntfid(glBalanceIntfid);
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
    }

    @Override
    public String getBalance() {
        return _balance;
    }

    @Override
    public void setBalance(String balance) {
        _balance = balance;

        if (_ivldGlBalanceRemoteModel != null) {
            try {
                Class<?> clazz = _ivldGlBalanceRemoteModel.getClass();

                Method method = clazz.getMethod("setBalance", String.class);

                method.invoke(_ivldGlBalanceRemoteModel, balance);
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

        if (_ivldGlBalanceRemoteModel != null) {
            try {
                Class<?> clazz = _ivldGlBalanceRemoteModel.getClass();

                Method method = clazz.getMethod("setAccountNo", String.class);

                method.invoke(_ivldGlBalanceRemoteModel, accountNo);
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

        if (_ivldGlBalanceRemoteModel != null) {
            try {
                Class<?> clazz = _ivldGlBalanceRemoteModel.getClass();

                Method method = clazz.getMethod("setReasonForFailure",
                        String.class);

                method.invoke(_ivldGlBalanceRemoteModel, reasonForFailure);
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

        if (_ivldGlBalanceRemoteModel != null) {
            try {
                Class<?> clazz = _ivldGlBalanceRemoteModel.getClass();

                Method method = clazz.getMethod("setAccountId", String.class);

                method.invoke(_ivldGlBalanceRemoteModel, accountId);
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

        if (_ivldGlBalanceRemoteModel != null) {
            try {
                Class<?> clazz = _ivldGlBalanceRemoteModel.getClass();

                Method method = clazz.getMethod("setYear", String.class);

                method.invoke(_ivldGlBalanceRemoteModel, year);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getPeriod() {
        return _period;
    }

    @Override
    public void setPeriod(String period) {
        _period = period;

        if (_ivldGlBalanceRemoteModel != null) {
            try {
                Class<?> clazz = _ivldGlBalanceRemoteModel.getClass();

                Method method = clazz.getMethod("setPeriod", String.class);

                method.invoke(_ivldGlBalanceRemoteModel, period);
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

        if (_ivldGlBalanceRemoteModel != null) {
            try {
                Class<?> clazz = _ivldGlBalanceRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedDate", Date.class);

                method.invoke(_ivldGlBalanceRemoteModel, modifiedDate);
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

        if (_ivldGlBalanceRemoteModel != null) {
            try {
                Class<?> clazz = _ivldGlBalanceRemoteModel.getClass();

                Method method = clazz.getMethod("setIsActive", String.class);

                method.invoke(_ivldGlBalanceRemoteModel, isActive);
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

        if (_ivldGlBalanceRemoteModel != null) {
            try {
                Class<?> clazz = _ivldGlBalanceRemoteModel.getClass();

                Method method = clazz.getMethod("setSource", String.class);

                method.invoke(_ivldGlBalanceRemoteModel, source);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getUploadDate() {
        return _uploadDate;
    }

    @Override
    public void setUploadDate(String uploadDate) {
        _uploadDate = uploadDate;

        if (_ivldGlBalanceRemoteModel != null) {
            try {
                Class<?> clazz = _ivldGlBalanceRemoteModel.getClass();

                Method method = clazz.getMethod("setUploadDate", String.class);

                method.invoke(_ivldGlBalanceRemoteModel, uploadDate);
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

        if (_ivldGlBalanceRemoteModel != null) {
            try {
                Class<?> clazz = _ivldGlBalanceRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedBy", String.class);

                method.invoke(_ivldGlBalanceRemoteModel, createdBy);
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

        if (_ivldGlBalanceRemoteModel != null) {
            try {
                Class<?> clazz = _ivldGlBalanceRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedDate", Date.class);

                method.invoke(_ivldGlBalanceRemoteModel, createdDate);
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

        if (_ivldGlBalanceRemoteModel != null) {
            try {
                Class<?> clazz = _ivldGlBalanceRemoteModel.getClass();

                Method method = clazz.getMethod("setAddChgDelIndicator",
                        String.class);

                method.invoke(_ivldGlBalanceRemoteModel, addChgDelIndicator);
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

        if (_ivldGlBalanceRemoteModel != null) {
            try {
                Class<?> clazz = _ivldGlBalanceRemoteModel.getClass();

                Method method = clazz.getMethod("setBatchId", String.class);

                method.invoke(_ivldGlBalanceRemoteModel, batchId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getCloseIndicator() {
        return _closeIndicator;
    }

    @Override
    public void setCloseIndicator(String closeIndicator) {
        _closeIndicator = closeIndicator;

        if (_ivldGlBalanceRemoteModel != null) {
            try {
                Class<?> clazz = _ivldGlBalanceRemoteModel.getClass();

                Method method = clazz.getMethod("setCloseIndicator",
                        String.class);

                method.invoke(_ivldGlBalanceRemoteModel, closeIndicator);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getInsertedDate() {
        return _insertedDate;
    }

    @Override
    public void setInsertedDate(String insertedDate) {
        _insertedDate = insertedDate;

        if (_ivldGlBalanceRemoteModel != null) {
            try {
                Class<?> clazz = _ivldGlBalanceRemoteModel.getClass();

                Method method = clazz.getMethod("setInsertedDate", String.class);

                method.invoke(_ivldGlBalanceRemoteModel, insertedDate);
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

        if (_ivldGlBalanceRemoteModel != null) {
            try {
                Class<?> clazz = _ivldGlBalanceRemoteModel.getClass();

                Method method = clazz.getMethod("setErrorField", String.class);

                method.invoke(_ivldGlBalanceRemoteModel, errorField);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getIvldGlBalanceSid() {
        return _ivldGlBalanceSid;
    }

    @Override
    public void setIvldGlBalanceSid(int ivldGlBalanceSid) {
        _ivldGlBalanceSid = ivldGlBalanceSid;

        if (_ivldGlBalanceRemoteModel != null) {
            try {
                Class<?> clazz = _ivldGlBalanceRemoteModel.getClass();

                Method method = clazz.getMethod("setIvldGlBalanceSid", int.class);

                method.invoke(_ivldGlBalanceRemoteModel, ivldGlBalanceSid);
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

        if (_ivldGlBalanceRemoteModel != null) {
            try {
                Class<?> clazz = _ivldGlBalanceRemoteModel.getClass();

                Method method = clazz.getMethod("setErrorCode", String.class);

                method.invoke(_ivldGlBalanceRemoteModel, errorCode);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getGlBalanceIntfid() {
        return _glBalanceIntfid;
    }

    @Override
    public void setGlBalanceIntfid(String glBalanceIntfid) {
        _glBalanceIntfid = glBalanceIntfid;

        if (_ivldGlBalanceRemoteModel != null) {
            try {
                Class<?> clazz = _ivldGlBalanceRemoteModel.getClass();

                Method method = clazz.getMethod("setGlBalanceIntfid",
                        String.class);

                method.invoke(_ivldGlBalanceRemoteModel, glBalanceIntfid);
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

        if (_ivldGlBalanceRemoteModel != null) {
            try {
                Class<?> clazz = _ivldGlBalanceRemoteModel.getClass();

                Method method = clazz.getMethod("setIntfInsertedDate",
                        Date.class);

                method.invoke(_ivldGlBalanceRemoteModel, intfInsertedDate);
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

        if (_ivldGlBalanceRemoteModel != null) {
            try {
                Class<?> clazz = _ivldGlBalanceRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedBy", String.class);

                method.invoke(_ivldGlBalanceRemoteModel, modifiedBy);
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

        if (_ivldGlBalanceRemoteModel != null) {
            try {
                Class<?> clazz = _ivldGlBalanceRemoteModel.getClass();

                Method method = clazz.getMethod("setReprocessedFlag",
                        String.class);

                method.invoke(_ivldGlBalanceRemoteModel, reprocessedFlag);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getIvldGlBalanceRemoteModel() {
        return _ivldGlBalanceRemoteModel;
    }

    public void setIvldGlBalanceRemoteModel(
        BaseModel<?> ivldGlBalanceRemoteModel) {
        _ivldGlBalanceRemoteModel = ivldGlBalanceRemoteModel;
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

        Class<?> remoteModelClass = _ivldGlBalanceRemoteModel.getClass();

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

        Object returnValue = method.invoke(_ivldGlBalanceRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            IvldGlBalanceLocalServiceUtil.addIvldGlBalance(this);
        } else {
            IvldGlBalanceLocalServiceUtil.updateIvldGlBalance(this);
        }
    }

    @Override
    public IvldGlBalance toEscapedModel() {
        return (IvldGlBalance) ProxyUtil.newProxyInstance(IvldGlBalance.class.getClassLoader(),
            new Class[] { IvldGlBalance.class }, new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        IvldGlBalanceClp clone = new IvldGlBalanceClp();

        clone.setBalance(getBalance());
        clone.setAccountNo(getAccountNo());
        clone.setReasonForFailure(getReasonForFailure());
        clone.setAccountId(getAccountId());
        clone.setYear(getYear());
        clone.setPeriod(getPeriod());
        clone.setModifiedDate(getModifiedDate());
        clone.setIsActive(getIsActive());
        clone.setSource(getSource());
        clone.setUploadDate(getUploadDate());
        clone.setCreatedBy(getCreatedBy());
        clone.setCreatedDate(getCreatedDate());
        clone.setAddChgDelIndicator(getAddChgDelIndicator());
        clone.setBatchId(getBatchId());
        clone.setCloseIndicator(getCloseIndicator());
        clone.setInsertedDate(getInsertedDate());
        clone.setErrorField(getErrorField());
        clone.setIvldGlBalanceSid(getIvldGlBalanceSid());
        clone.setErrorCode(getErrorCode());
        clone.setGlBalanceIntfid(getGlBalanceIntfid());
        clone.setIntfInsertedDate(getIntfInsertedDate());
        clone.setModifiedBy(getModifiedBy());
        clone.setReprocessedFlag(getReprocessedFlag());

        return clone;
    }

    @Override
    public int compareTo(IvldGlBalance ivldGlBalance) {
        int primaryKey = ivldGlBalance.getPrimaryKey();

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

        if (!(obj instanceof IvldGlBalanceClp)) {
            return false;
        }

        IvldGlBalanceClp ivldGlBalance = (IvldGlBalanceClp) obj;

        int primaryKey = ivldGlBalance.getPrimaryKey();

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
        StringBundler sb = new StringBundler(47);

        sb.append("{balance=");
        sb.append(getBalance());
        sb.append(", accountNo=");
        sb.append(getAccountNo());
        sb.append(", reasonForFailure=");
        sb.append(getReasonForFailure());
        sb.append(", accountId=");
        sb.append(getAccountId());
        sb.append(", year=");
        sb.append(getYear());
        sb.append(", period=");
        sb.append(getPeriod());
        sb.append(", modifiedDate=");
        sb.append(getModifiedDate());
        sb.append(", isActive=");
        sb.append(getIsActive());
        sb.append(", source=");
        sb.append(getSource());
        sb.append(", uploadDate=");
        sb.append(getUploadDate());
        sb.append(", createdBy=");
        sb.append(getCreatedBy());
        sb.append(", createdDate=");
        sb.append(getCreatedDate());
        sb.append(", addChgDelIndicator=");
        sb.append(getAddChgDelIndicator());
        sb.append(", batchId=");
        sb.append(getBatchId());
        sb.append(", closeIndicator=");
        sb.append(getCloseIndicator());
        sb.append(", insertedDate=");
        sb.append(getInsertedDate());
        sb.append(", errorField=");
        sb.append(getErrorField());
        sb.append(", ivldGlBalanceSid=");
        sb.append(getIvldGlBalanceSid());
        sb.append(", errorCode=");
        sb.append(getErrorCode());
        sb.append(", glBalanceIntfid=");
        sb.append(getGlBalanceIntfid());
        sb.append(", intfInsertedDate=");
        sb.append(getIntfInsertedDate());
        sb.append(", modifiedBy=");
        sb.append(getModifiedBy());
        sb.append(", reprocessedFlag=");
        sb.append(getReprocessedFlag());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(73);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.IvldGlBalance");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>balance</column-name><column-value><![CDATA[");
        sb.append(getBalance());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>accountNo</column-name><column-value><![CDATA[");
        sb.append(getAccountNo());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>reasonForFailure</column-name><column-value><![CDATA[");
        sb.append(getReasonForFailure());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>accountId</column-name><column-value><![CDATA[");
        sb.append(getAccountId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>year</column-name><column-value><![CDATA[");
        sb.append(getYear());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>period</column-name><column-value><![CDATA[");
        sb.append(getPeriod());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
        sb.append(getModifiedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>isActive</column-name><column-value><![CDATA[");
        sb.append(getIsActive());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>source</column-name><column-value><![CDATA[");
        sb.append(getSource());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>uploadDate</column-name><column-value><![CDATA[");
        sb.append(getUploadDate());
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
            "<column><column-name>closeIndicator</column-name><column-value><![CDATA[");
        sb.append(getCloseIndicator());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>insertedDate</column-name><column-value><![CDATA[");
        sb.append(getInsertedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>errorField</column-name><column-value><![CDATA[");
        sb.append(getErrorField());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>ivldGlBalanceSid</column-name><column-value><![CDATA[");
        sb.append(getIvldGlBalanceSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>errorCode</column-name><column-value><![CDATA[");
        sb.append(getErrorCode());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>glBalanceIntfid</column-name><column-value><![CDATA[");
        sb.append(getGlBalanceIntfid());
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

        sb.append("</model>");

        return sb.toString();
    }
}
