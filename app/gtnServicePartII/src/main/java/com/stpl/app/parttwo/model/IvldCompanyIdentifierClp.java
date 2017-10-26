package com.stpl.app.parttwo.model;

import com.stpl.app.parttwo.service.ClpSerializer;
import com.stpl.app.parttwo.service.IvldCompanyIdentifierLocalServiceUtil;

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


public class IvldCompanyIdentifierClp extends BaseModelImpl<IvldCompanyIdentifier>
    implements IvldCompanyIdentifier {
    private String _reasonForFailure;
    private String _companyId;
    private String _companyName;
    private String _endDate;
    private Date _modifiedDate;
    private String _identifierStatus;
    private String _companyIdentifier;
    private String _entityCode;
    private String _companyIdentifierIntfid;
    private String _startDate;
    private String _source;
    private Date _createdDate;
    private String _createdBy;
    private String _companyNo;
    private String _addChgDelIndicator;
    private String _batchId;
    private String _errorField;
    private String _errorCode;
    private String _identifierCodeQualifierName;
    private Date _intfInsertedDate;
    private String _modifiedBy;
    private int _ivldCompanyIdentifierSid;
    private String _reprocessedFlag;
    private String _identifierCodeQualifier;
    private boolean _checkRecord;
    private BaseModel<?> _ivldCompanyIdentifierRemoteModel;

    public IvldCompanyIdentifierClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return IvldCompanyIdentifier.class;
    }

    @Override
    public String getModelClassName() {
        return IvldCompanyIdentifier.class.getName();
    }

    @Override
    public int getPrimaryKey() {
        return _ivldCompanyIdentifierSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setIvldCompanyIdentifierSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _ivldCompanyIdentifierSid;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Integer) primaryKeyObj).intValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("reasonForFailure", getReasonForFailure());
        attributes.put("companyId", getCompanyId());
        attributes.put("companyName", getCompanyName());
        attributes.put("endDate", getEndDate());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("identifierStatus", getIdentifierStatus());
        attributes.put("companyIdentifier", getCompanyIdentifier());
        attributes.put("entityCode", getEntityCode());
        attributes.put("companyIdentifierIntfid", getCompanyIdentifierIntfid());
        attributes.put("startDate", getStartDate());
        attributes.put("source", getSource());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("companyNo", getCompanyNo());
        attributes.put("addChgDelIndicator", getAddChgDelIndicator());
        attributes.put("batchId", getBatchId());
        attributes.put("errorField", getErrorField());
        attributes.put("errorCode", getErrorCode());
        attributes.put("identifierCodeQualifierName",
            getIdentifierCodeQualifierName());
        attributes.put("intfInsertedDate", getIntfInsertedDate());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("ivldCompanyIdentifierSid", getIvldCompanyIdentifierSid());
        attributes.put("reprocessedFlag", getReprocessedFlag());
        attributes.put("identifierCodeQualifier", getIdentifierCodeQualifier());
        attributes.put("checkRecord", getCheckRecord());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        String reasonForFailure = (String) attributes.get("reasonForFailure");

        if (reasonForFailure != null) {
            setReasonForFailure(reasonForFailure);
        }

        String companyId = (String) attributes.get("companyId");

        if (companyId != null) {
            setCompanyId(companyId);
        }

        String companyName = (String) attributes.get("companyName");

        if (companyName != null) {
            setCompanyName(companyName);
        }

        String endDate = (String) attributes.get("endDate");

        if (endDate != null) {
            setEndDate(endDate);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        String identifierStatus = (String) attributes.get("identifierStatus");

        if (identifierStatus != null) {
            setIdentifierStatus(identifierStatus);
        }

        String companyIdentifier = (String) attributes.get("companyIdentifier");

        if (companyIdentifier != null) {
            setCompanyIdentifier(companyIdentifier);
        }

        String entityCode = (String) attributes.get("entityCode");

        if (entityCode != null) {
            setEntityCode(entityCode);
        }

        String companyIdentifierIntfid = (String) attributes.get(
                "companyIdentifierIntfid");

        if (companyIdentifierIntfid != null) {
            setCompanyIdentifierIntfid(companyIdentifierIntfid);
        }

        String startDate = (String) attributes.get("startDate");

        if (startDate != null) {
            setStartDate(startDate);
        }

        String source = (String) attributes.get("source");

        if (source != null) {
            setSource(source);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        String createdBy = (String) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        String companyNo = (String) attributes.get("companyNo");

        if (companyNo != null) {
            setCompanyNo(companyNo);
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

        String identifierCodeQualifierName = (String) attributes.get(
                "identifierCodeQualifierName");

        if (identifierCodeQualifierName != null) {
            setIdentifierCodeQualifierName(identifierCodeQualifierName);
        }

        Date intfInsertedDate = (Date) attributes.get("intfInsertedDate");

        if (intfInsertedDate != null) {
            setIntfInsertedDate(intfInsertedDate);
        }

        String modifiedBy = (String) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        Integer ivldCompanyIdentifierSid = (Integer) attributes.get(
                "ivldCompanyIdentifierSid");

        if (ivldCompanyIdentifierSid != null) {
            setIvldCompanyIdentifierSid(ivldCompanyIdentifierSid);
        }

        String reprocessedFlag = (String) attributes.get("reprocessedFlag");

        if (reprocessedFlag != null) {
            setReprocessedFlag(reprocessedFlag);
        }

        String identifierCodeQualifier = (String) attributes.get(
                "identifierCodeQualifier");

        if (identifierCodeQualifier != null) {
            setIdentifierCodeQualifier(identifierCodeQualifier);
        }

        Boolean checkRecord = (Boolean) attributes.get("checkRecord");

        if (checkRecord != null) {
            setCheckRecord(checkRecord);
        }
    }

    @Override
    public String getReasonForFailure() {
        return _reasonForFailure;
    }

    @Override
    public void setReasonForFailure(String reasonForFailure) {
        _reasonForFailure = reasonForFailure;

        if (_ivldCompanyIdentifierRemoteModel != null) {
            try {
                Class<?> clazz = _ivldCompanyIdentifierRemoteModel.getClass();

                Method method = clazz.getMethod("setReasonForFailure",
                        String.class);

                method.invoke(_ivldCompanyIdentifierRemoteModel,
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

        if (_ivldCompanyIdentifierRemoteModel != null) {
            try {
                Class<?> clazz = _ivldCompanyIdentifierRemoteModel.getClass();

                Method method = clazz.getMethod("setCompanyId", String.class);

                method.invoke(_ivldCompanyIdentifierRemoteModel, companyId);
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

        if (_ivldCompanyIdentifierRemoteModel != null) {
            try {
                Class<?> clazz = _ivldCompanyIdentifierRemoteModel.getClass();

                Method method = clazz.getMethod("setCompanyName", String.class);

                method.invoke(_ivldCompanyIdentifierRemoteModel, companyName);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getEndDate() {
        return _endDate;
    }

    @Override
    public void setEndDate(String endDate) {
        _endDate = endDate;

        if (_ivldCompanyIdentifierRemoteModel != null) {
            try {
                Class<?> clazz = _ivldCompanyIdentifierRemoteModel.getClass();

                Method method = clazz.getMethod("setEndDate", String.class);

                method.invoke(_ivldCompanyIdentifierRemoteModel, endDate);
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

        if (_ivldCompanyIdentifierRemoteModel != null) {
            try {
                Class<?> clazz = _ivldCompanyIdentifierRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedDate", Date.class);

                method.invoke(_ivldCompanyIdentifierRemoteModel, modifiedDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getIdentifierStatus() {
        return _identifierStatus;
    }

    @Override
    public void setIdentifierStatus(String identifierStatus) {
        _identifierStatus = identifierStatus;

        if (_ivldCompanyIdentifierRemoteModel != null) {
            try {
                Class<?> clazz = _ivldCompanyIdentifierRemoteModel.getClass();

                Method method = clazz.getMethod("setIdentifierStatus",
                        String.class);

                method.invoke(_ivldCompanyIdentifierRemoteModel,
                    identifierStatus);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getCompanyIdentifier() {
        return _companyIdentifier;
    }

    @Override
    public void setCompanyIdentifier(String companyIdentifier) {
        _companyIdentifier = companyIdentifier;

        if (_ivldCompanyIdentifierRemoteModel != null) {
            try {
                Class<?> clazz = _ivldCompanyIdentifierRemoteModel.getClass();

                Method method = clazz.getMethod("setCompanyIdentifier",
                        String.class);

                method.invoke(_ivldCompanyIdentifierRemoteModel,
                    companyIdentifier);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getEntityCode() {
        return _entityCode;
    }

    @Override
    public void setEntityCode(String entityCode) {
        _entityCode = entityCode;

        if (_ivldCompanyIdentifierRemoteModel != null) {
            try {
                Class<?> clazz = _ivldCompanyIdentifierRemoteModel.getClass();

                Method method = clazz.getMethod("setEntityCode", String.class);

                method.invoke(_ivldCompanyIdentifierRemoteModel, entityCode);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getCompanyIdentifierIntfid() {
        return _companyIdentifierIntfid;
    }

    @Override
    public void setCompanyIdentifierIntfid(String companyIdentifierIntfid) {
        _companyIdentifierIntfid = companyIdentifierIntfid;

        if (_ivldCompanyIdentifierRemoteModel != null) {
            try {
                Class<?> clazz = _ivldCompanyIdentifierRemoteModel.getClass();

                Method method = clazz.getMethod("setCompanyIdentifierIntfid",
                        String.class);

                method.invoke(_ivldCompanyIdentifierRemoteModel,
                    companyIdentifierIntfid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getStartDate() {
        return _startDate;
    }

    @Override
    public void setStartDate(String startDate) {
        _startDate = startDate;

        if (_ivldCompanyIdentifierRemoteModel != null) {
            try {
                Class<?> clazz = _ivldCompanyIdentifierRemoteModel.getClass();

                Method method = clazz.getMethod("setStartDate", String.class);

                method.invoke(_ivldCompanyIdentifierRemoteModel, startDate);
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

        if (_ivldCompanyIdentifierRemoteModel != null) {
            try {
                Class<?> clazz = _ivldCompanyIdentifierRemoteModel.getClass();

                Method method = clazz.getMethod("setSource", String.class);

                method.invoke(_ivldCompanyIdentifierRemoteModel, source);
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

        if (_ivldCompanyIdentifierRemoteModel != null) {
            try {
                Class<?> clazz = _ivldCompanyIdentifierRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedDate", Date.class);

                method.invoke(_ivldCompanyIdentifierRemoteModel, createdDate);
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

        if (_ivldCompanyIdentifierRemoteModel != null) {
            try {
                Class<?> clazz = _ivldCompanyIdentifierRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedBy", String.class);

                method.invoke(_ivldCompanyIdentifierRemoteModel, createdBy);
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

        if (_ivldCompanyIdentifierRemoteModel != null) {
            try {
                Class<?> clazz = _ivldCompanyIdentifierRemoteModel.getClass();

                Method method = clazz.getMethod("setCompanyNo", String.class);

                method.invoke(_ivldCompanyIdentifierRemoteModel, companyNo);
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

        if (_ivldCompanyIdentifierRemoteModel != null) {
            try {
                Class<?> clazz = _ivldCompanyIdentifierRemoteModel.getClass();

                Method method = clazz.getMethod("setAddChgDelIndicator",
                        String.class);

                method.invoke(_ivldCompanyIdentifierRemoteModel,
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

        if (_ivldCompanyIdentifierRemoteModel != null) {
            try {
                Class<?> clazz = _ivldCompanyIdentifierRemoteModel.getClass();

                Method method = clazz.getMethod("setBatchId", String.class);

                method.invoke(_ivldCompanyIdentifierRemoteModel, batchId);
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

        if (_ivldCompanyIdentifierRemoteModel != null) {
            try {
                Class<?> clazz = _ivldCompanyIdentifierRemoteModel.getClass();

                Method method = clazz.getMethod("setErrorField", String.class);

                method.invoke(_ivldCompanyIdentifierRemoteModel, errorField);
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

        if (_ivldCompanyIdentifierRemoteModel != null) {
            try {
                Class<?> clazz = _ivldCompanyIdentifierRemoteModel.getClass();

                Method method = clazz.getMethod("setErrorCode", String.class);

                method.invoke(_ivldCompanyIdentifierRemoteModel, errorCode);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getIdentifierCodeQualifierName() {
        return _identifierCodeQualifierName;
    }

    @Override
    public void setIdentifierCodeQualifierName(
        String identifierCodeQualifierName) {
        _identifierCodeQualifierName = identifierCodeQualifierName;

        if (_ivldCompanyIdentifierRemoteModel != null) {
            try {
                Class<?> clazz = _ivldCompanyIdentifierRemoteModel.getClass();

                Method method = clazz.getMethod("setIdentifierCodeQualifierName",
                        String.class);

                method.invoke(_ivldCompanyIdentifierRemoteModel,
                    identifierCodeQualifierName);
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

        if (_ivldCompanyIdentifierRemoteModel != null) {
            try {
                Class<?> clazz = _ivldCompanyIdentifierRemoteModel.getClass();

                Method method = clazz.getMethod("setIntfInsertedDate",
                        Date.class);

                method.invoke(_ivldCompanyIdentifierRemoteModel,
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

        if (_ivldCompanyIdentifierRemoteModel != null) {
            try {
                Class<?> clazz = _ivldCompanyIdentifierRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedBy", String.class);

                method.invoke(_ivldCompanyIdentifierRemoteModel, modifiedBy);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getIvldCompanyIdentifierSid() {
        return _ivldCompanyIdentifierSid;
    }

    @Override
    public void setIvldCompanyIdentifierSid(int ivldCompanyIdentifierSid) {
        _ivldCompanyIdentifierSid = ivldCompanyIdentifierSid;

        if (_ivldCompanyIdentifierRemoteModel != null) {
            try {
                Class<?> clazz = _ivldCompanyIdentifierRemoteModel.getClass();

                Method method = clazz.getMethod("setIvldCompanyIdentifierSid",
                        int.class);

                method.invoke(_ivldCompanyIdentifierRemoteModel,
                    ivldCompanyIdentifierSid);
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

        if (_ivldCompanyIdentifierRemoteModel != null) {
            try {
                Class<?> clazz = _ivldCompanyIdentifierRemoteModel.getClass();

                Method method = clazz.getMethod("setReprocessedFlag",
                        String.class);

                method.invoke(_ivldCompanyIdentifierRemoteModel, reprocessedFlag);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getIdentifierCodeQualifier() {
        return _identifierCodeQualifier;
    }

    @Override
    public void setIdentifierCodeQualifier(String identifierCodeQualifier) {
        _identifierCodeQualifier = identifierCodeQualifier;

        if (_ivldCompanyIdentifierRemoteModel != null) {
            try {
                Class<?> clazz = _ivldCompanyIdentifierRemoteModel.getClass();

                Method method = clazz.getMethod("setIdentifierCodeQualifier",
                        String.class);

                method.invoke(_ivldCompanyIdentifierRemoteModel,
                    identifierCodeQualifier);
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

        if (_ivldCompanyIdentifierRemoteModel != null) {
            try {
                Class<?> clazz = _ivldCompanyIdentifierRemoteModel.getClass();

                Method method = clazz.getMethod("setCheckRecord", boolean.class);

                method.invoke(_ivldCompanyIdentifierRemoteModel, checkRecord);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getIvldCompanyIdentifierRemoteModel() {
        return _ivldCompanyIdentifierRemoteModel;
    }

    public void setIvldCompanyIdentifierRemoteModel(
        BaseModel<?> ivldCompanyIdentifierRemoteModel) {
        _ivldCompanyIdentifierRemoteModel = ivldCompanyIdentifierRemoteModel;
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

        Class<?> remoteModelClass = _ivldCompanyIdentifierRemoteModel.getClass();

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

        Object returnValue = method.invoke(_ivldCompanyIdentifierRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            IvldCompanyIdentifierLocalServiceUtil.addIvldCompanyIdentifier(this);
        } else {
            IvldCompanyIdentifierLocalServiceUtil.updateIvldCompanyIdentifier(this);
        }
    }

    @Override
    public IvldCompanyIdentifier toEscapedModel() {
        return (IvldCompanyIdentifier) ProxyUtil.newProxyInstance(IvldCompanyIdentifier.class.getClassLoader(),
            new Class[] { IvldCompanyIdentifier.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        IvldCompanyIdentifierClp clone = new IvldCompanyIdentifierClp();

        clone.setReasonForFailure(getReasonForFailure());
        clone.setCompanyId(getCompanyId());
        clone.setCompanyName(getCompanyName());
        clone.setEndDate(getEndDate());
        clone.setModifiedDate(getModifiedDate());
        clone.setIdentifierStatus(getIdentifierStatus());
        clone.setCompanyIdentifier(getCompanyIdentifier());
        clone.setEntityCode(getEntityCode());
        clone.setCompanyIdentifierIntfid(getCompanyIdentifierIntfid());
        clone.setStartDate(getStartDate());
        clone.setSource(getSource());
        clone.setCreatedDate(getCreatedDate());
        clone.setCreatedBy(getCreatedBy());
        clone.setCompanyNo(getCompanyNo());
        clone.setAddChgDelIndicator(getAddChgDelIndicator());
        clone.setBatchId(getBatchId());
        clone.setErrorField(getErrorField());
        clone.setErrorCode(getErrorCode());
        clone.setIdentifierCodeQualifierName(getIdentifierCodeQualifierName());
        clone.setIntfInsertedDate(getIntfInsertedDate());
        clone.setModifiedBy(getModifiedBy());
        clone.setIvldCompanyIdentifierSid(getIvldCompanyIdentifierSid());
        clone.setReprocessedFlag(getReprocessedFlag());
        clone.setIdentifierCodeQualifier(getIdentifierCodeQualifier());
        clone.setCheckRecord(getCheckRecord());

        return clone;
    }

    @Override
    public int compareTo(IvldCompanyIdentifier ivldCompanyIdentifier) {
        int primaryKey = ivldCompanyIdentifier.getPrimaryKey();

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

        if (!(obj instanceof IvldCompanyIdentifierClp)) {
            return false;
        }

        IvldCompanyIdentifierClp ivldCompanyIdentifier = (IvldCompanyIdentifierClp) obj;

        int primaryKey = ivldCompanyIdentifier.getPrimaryKey();

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

        sb.append("{reasonForFailure=");
        sb.append(getReasonForFailure());
        sb.append(", companyId=");
        sb.append(getCompanyId());
        sb.append(", companyName=");
        sb.append(getCompanyName());
        sb.append(", endDate=");
        sb.append(getEndDate());
        sb.append(", modifiedDate=");
        sb.append(getModifiedDate());
        sb.append(", identifierStatus=");
        sb.append(getIdentifierStatus());
        sb.append(", companyIdentifier=");
        sb.append(getCompanyIdentifier());
        sb.append(", entityCode=");
        sb.append(getEntityCode());
        sb.append(", companyIdentifierIntfid=");
        sb.append(getCompanyIdentifierIntfid());
        sb.append(", startDate=");
        sb.append(getStartDate());
        sb.append(", source=");
        sb.append(getSource());
        sb.append(", createdDate=");
        sb.append(getCreatedDate());
        sb.append(", createdBy=");
        sb.append(getCreatedBy());
        sb.append(", companyNo=");
        sb.append(getCompanyNo());
        sb.append(", addChgDelIndicator=");
        sb.append(getAddChgDelIndicator());
        sb.append(", batchId=");
        sb.append(getBatchId());
        sb.append(", errorField=");
        sb.append(getErrorField());
        sb.append(", errorCode=");
        sb.append(getErrorCode());
        sb.append(", identifierCodeQualifierName=");
        sb.append(getIdentifierCodeQualifierName());
        sb.append(", intfInsertedDate=");
        sb.append(getIntfInsertedDate());
        sb.append(", modifiedBy=");
        sb.append(getModifiedBy());
        sb.append(", ivldCompanyIdentifierSid=");
        sb.append(getIvldCompanyIdentifierSid());
        sb.append(", reprocessedFlag=");
        sb.append(getReprocessedFlag());
        sb.append(", identifierCodeQualifier=");
        sb.append(getIdentifierCodeQualifier());
        sb.append(", checkRecord=");
        sb.append(getCheckRecord());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(79);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.parttwo.model.IvldCompanyIdentifier");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>reasonForFailure</column-name><column-value><![CDATA[");
        sb.append(getReasonForFailure());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>companyId</column-name><column-value><![CDATA[");
        sb.append(getCompanyId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>companyName</column-name><column-value><![CDATA[");
        sb.append(getCompanyName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>endDate</column-name><column-value><![CDATA[");
        sb.append(getEndDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
        sb.append(getModifiedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>identifierStatus</column-name><column-value><![CDATA[");
        sb.append(getIdentifierStatus());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>companyIdentifier</column-name><column-value><![CDATA[");
        sb.append(getCompanyIdentifier());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>entityCode</column-name><column-value><![CDATA[");
        sb.append(getEntityCode());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>companyIdentifierIntfid</column-name><column-value><![CDATA[");
        sb.append(getCompanyIdentifierIntfid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>startDate</column-name><column-value><![CDATA[");
        sb.append(getStartDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>source</column-name><column-value><![CDATA[");
        sb.append(getSource());
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
            "<column><column-name>companyNo</column-name><column-value><![CDATA[");
        sb.append(getCompanyNo());
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
            "<column><column-name>identifierCodeQualifierName</column-name><column-value><![CDATA[");
        sb.append(getIdentifierCodeQualifierName());
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
            "<column><column-name>ivldCompanyIdentifierSid</column-name><column-value><![CDATA[");
        sb.append(getIvldCompanyIdentifierSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>reprocessedFlag</column-name><column-value><![CDATA[");
        sb.append(getReprocessedFlag());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>identifierCodeQualifier</column-name><column-value><![CDATA[");
        sb.append(getIdentifierCodeQualifier());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>checkRecord</column-name><column-value><![CDATA[");
        sb.append(getCheckRecord());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
