package com.stpl.app.model;

import com.stpl.app.service.ClpSerializer;
import com.stpl.app.service.IvldCpiLocalServiceUtil;

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


public class IvldCpiClp extends BaseModelImpl<IvldCpi> implements IvldCpi {
    private String _effectiveDate;
    private String _reasonForFailure;
    private String _indexType;
    private String _status;
    private Date _modifiedDate;
    private String _cpiIntfid;
    private String _createdBy;
    private Date _createdDate;
    private String _source;
    private String _indexValue;
    private String _addChgDelIndicator;
    private String _batchId;
    private int _ivldCpiSid;
    private String _errorField;
    private String _errorCode;
    private Date _intfInsertedDate;
    private String _modifiedBy;
    private String _reprocessedFlag;
    private String _indexId;
    private BaseModel<?> _ivldCpiRemoteModel;

    public IvldCpiClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return IvldCpi.class;
    }

    @Override
    public String getModelClassName() {
        return IvldCpi.class.getName();
    }

    @Override
    public int getPrimaryKey() {
        return _ivldCpiSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setIvldCpiSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _ivldCpiSid;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Integer) primaryKeyObj).intValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("effectiveDate", getEffectiveDate());
        attributes.put("reasonForFailure", getReasonForFailure());
        attributes.put("indexType", getIndexType());
        attributes.put("status", getStatus());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("cpiIntfid", getCpiIntfid());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("source", getSource());
        attributes.put("indexValue", getIndexValue());
        attributes.put("addChgDelIndicator", getAddChgDelIndicator());
        attributes.put("batchId", getBatchId());
        attributes.put("ivldCpiSid", getIvldCpiSid());
        attributes.put("errorField", getErrorField());
        attributes.put("errorCode", getErrorCode());
        attributes.put("intfInsertedDate", getIntfInsertedDate());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("reprocessedFlag", getReprocessedFlag());
        attributes.put("indexId", getIndexId());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        String effectiveDate = (String) attributes.get("effectiveDate");

        if (effectiveDate != null) {
            setEffectiveDate(effectiveDate);
        }

        String reasonForFailure = (String) attributes.get("reasonForFailure");

        if (reasonForFailure != null) {
            setReasonForFailure(reasonForFailure);
        }

        String indexType = (String) attributes.get("indexType");

        if (indexType != null) {
            setIndexType(indexType);
        }

        String status = (String) attributes.get("status");

        if (status != null) {
            setStatus(status);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        String cpiIntfid = (String) attributes.get("cpiIntfid");

        if (cpiIntfid != null) {
            setCpiIntfid(cpiIntfid);
        }

        String createdBy = (String) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        String source = (String) attributes.get("source");

        if (source != null) {
            setSource(source);
        }

        String indexValue = (String) attributes.get("indexValue");

        if (indexValue != null) {
            setIndexValue(indexValue);
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

        Integer ivldCpiSid = (Integer) attributes.get("ivldCpiSid");

        if (ivldCpiSid != null) {
            setIvldCpiSid(ivldCpiSid);
        }

        String errorField = (String) attributes.get("errorField");

        if (errorField != null) {
            setErrorField(errorField);
        }

        String errorCode = (String) attributes.get("errorCode");

        if (errorCode != null) {
            setErrorCode(errorCode);
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

        String indexId = (String) attributes.get("indexId");

        if (indexId != null) {
            setIndexId(indexId);
        }
    }

    @Override
    public String getEffectiveDate() {
        return _effectiveDate;
    }

    @Override
    public void setEffectiveDate(String effectiveDate) {
        _effectiveDate = effectiveDate;

        if (_ivldCpiRemoteModel != null) {
            try {
                Class<?> clazz = _ivldCpiRemoteModel.getClass();

                Method method = clazz.getMethod("setEffectiveDate", String.class);

                method.invoke(_ivldCpiRemoteModel, effectiveDate);
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

        if (_ivldCpiRemoteModel != null) {
            try {
                Class<?> clazz = _ivldCpiRemoteModel.getClass();

                Method method = clazz.getMethod("setReasonForFailure",
                        String.class);

                method.invoke(_ivldCpiRemoteModel, reasonForFailure);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getIndexType() {
        return _indexType;
    }

    @Override
    public void setIndexType(String indexType) {
        _indexType = indexType;

        if (_ivldCpiRemoteModel != null) {
            try {
                Class<?> clazz = _ivldCpiRemoteModel.getClass();

                Method method = clazz.getMethod("setIndexType", String.class);

                method.invoke(_ivldCpiRemoteModel, indexType);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getStatus() {
        return _status;
    }

    @Override
    public void setStatus(String status) {
        _status = status;

        if (_ivldCpiRemoteModel != null) {
            try {
                Class<?> clazz = _ivldCpiRemoteModel.getClass();

                Method method = clazz.getMethod("setStatus", String.class);

                method.invoke(_ivldCpiRemoteModel, status);
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

        if (_ivldCpiRemoteModel != null) {
            try {
                Class<?> clazz = _ivldCpiRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedDate", Date.class);

                method.invoke(_ivldCpiRemoteModel, modifiedDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getCpiIntfid() {
        return _cpiIntfid;
    }

    @Override
    public void setCpiIntfid(String cpiIntfid) {
        _cpiIntfid = cpiIntfid;

        if (_ivldCpiRemoteModel != null) {
            try {
                Class<?> clazz = _ivldCpiRemoteModel.getClass();

                Method method = clazz.getMethod("setCpiIntfid", String.class);

                method.invoke(_ivldCpiRemoteModel, cpiIntfid);
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

        if (_ivldCpiRemoteModel != null) {
            try {
                Class<?> clazz = _ivldCpiRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedBy", String.class);

                method.invoke(_ivldCpiRemoteModel, createdBy);
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

        if (_ivldCpiRemoteModel != null) {
            try {
                Class<?> clazz = _ivldCpiRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedDate", Date.class);

                method.invoke(_ivldCpiRemoteModel, createdDate);
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

        if (_ivldCpiRemoteModel != null) {
            try {
                Class<?> clazz = _ivldCpiRemoteModel.getClass();

                Method method = clazz.getMethod("setSource", String.class);

                method.invoke(_ivldCpiRemoteModel, source);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getIndexValue() {
        return _indexValue;
    }

    @Override
    public void setIndexValue(String indexValue) {
        _indexValue = indexValue;

        if (_ivldCpiRemoteModel != null) {
            try {
                Class<?> clazz = _ivldCpiRemoteModel.getClass();

                Method method = clazz.getMethod("setIndexValue", String.class);

                method.invoke(_ivldCpiRemoteModel, indexValue);
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

        if (_ivldCpiRemoteModel != null) {
            try {
                Class<?> clazz = _ivldCpiRemoteModel.getClass();

                Method method = clazz.getMethod("setAddChgDelIndicator",
                        String.class);

                method.invoke(_ivldCpiRemoteModel, addChgDelIndicator);
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

        if (_ivldCpiRemoteModel != null) {
            try {
                Class<?> clazz = _ivldCpiRemoteModel.getClass();

                Method method = clazz.getMethod("setBatchId", String.class);

                method.invoke(_ivldCpiRemoteModel, batchId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getIvldCpiSid() {
        return _ivldCpiSid;
    }

    @Override
    public void setIvldCpiSid(int ivldCpiSid) {
        _ivldCpiSid = ivldCpiSid;

        if (_ivldCpiRemoteModel != null) {
            try {
                Class<?> clazz = _ivldCpiRemoteModel.getClass();

                Method method = clazz.getMethod("setIvldCpiSid", int.class);

                method.invoke(_ivldCpiRemoteModel, ivldCpiSid);
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

        if (_ivldCpiRemoteModel != null) {
            try {
                Class<?> clazz = _ivldCpiRemoteModel.getClass();

                Method method = clazz.getMethod("setErrorField", String.class);

                method.invoke(_ivldCpiRemoteModel, errorField);
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

        if (_ivldCpiRemoteModel != null) {
            try {
                Class<?> clazz = _ivldCpiRemoteModel.getClass();

                Method method = clazz.getMethod("setErrorCode", String.class);

                method.invoke(_ivldCpiRemoteModel, errorCode);
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

        if (_ivldCpiRemoteModel != null) {
            try {
                Class<?> clazz = _ivldCpiRemoteModel.getClass();

                Method method = clazz.getMethod("setIntfInsertedDate",
                        Date.class);

                method.invoke(_ivldCpiRemoteModel, intfInsertedDate);
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

        if (_ivldCpiRemoteModel != null) {
            try {
                Class<?> clazz = _ivldCpiRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedBy", String.class);

                method.invoke(_ivldCpiRemoteModel, modifiedBy);
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

        if (_ivldCpiRemoteModel != null) {
            try {
                Class<?> clazz = _ivldCpiRemoteModel.getClass();

                Method method = clazz.getMethod("setReprocessedFlag",
                        String.class);

                method.invoke(_ivldCpiRemoteModel, reprocessedFlag);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getIndexId() {
        return _indexId;
    }

    @Override
    public void setIndexId(String indexId) {
        _indexId = indexId;

        if (_ivldCpiRemoteModel != null) {
            try {
                Class<?> clazz = _ivldCpiRemoteModel.getClass();

                Method method = clazz.getMethod("setIndexId", String.class);

                method.invoke(_ivldCpiRemoteModel, indexId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getIvldCpiRemoteModel() {
        return _ivldCpiRemoteModel;
    }

    public void setIvldCpiRemoteModel(BaseModel<?> ivldCpiRemoteModel) {
        _ivldCpiRemoteModel = ivldCpiRemoteModel;
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

        Class<?> remoteModelClass = _ivldCpiRemoteModel.getClass();

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

        Object returnValue = method.invoke(_ivldCpiRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            IvldCpiLocalServiceUtil.addIvldCpi(this);
        } else {
            IvldCpiLocalServiceUtil.updateIvldCpi(this);
        }
    }

    @Override
    public IvldCpi toEscapedModel() {
        return (IvldCpi) ProxyUtil.newProxyInstance(IvldCpi.class.getClassLoader(),
            new Class[] { IvldCpi.class }, new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        IvldCpiClp clone = new IvldCpiClp();

        clone.setEffectiveDate(getEffectiveDate());
        clone.setReasonForFailure(getReasonForFailure());
        clone.setIndexType(getIndexType());
        clone.setStatus(getStatus());
        clone.setModifiedDate(getModifiedDate());
        clone.setCpiIntfid(getCpiIntfid());
        clone.setCreatedBy(getCreatedBy());
        clone.setCreatedDate(getCreatedDate());
        clone.setSource(getSource());
        clone.setIndexValue(getIndexValue());
        clone.setAddChgDelIndicator(getAddChgDelIndicator());
        clone.setBatchId(getBatchId());
        clone.setIvldCpiSid(getIvldCpiSid());
        clone.setErrorField(getErrorField());
        clone.setErrorCode(getErrorCode());
        clone.setIntfInsertedDate(getIntfInsertedDate());
        clone.setModifiedBy(getModifiedBy());
        clone.setReprocessedFlag(getReprocessedFlag());
        clone.setIndexId(getIndexId());

        return clone;
    }

    @Override
    public int compareTo(IvldCpi ivldCpi) {
        int primaryKey = ivldCpi.getPrimaryKey();

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

        if (!(obj instanceof IvldCpiClp)) {
            return false;
        }

        IvldCpiClp ivldCpi = (IvldCpiClp) obj;

        int primaryKey = ivldCpi.getPrimaryKey();

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
        StringBundler sb = new StringBundler(39);

        sb.append("{effectiveDate=");
        sb.append(getEffectiveDate());
        sb.append(", reasonForFailure=");
        sb.append(getReasonForFailure());
        sb.append(", indexType=");
        sb.append(getIndexType());
        sb.append(", status=");
        sb.append(getStatus());
        sb.append(", modifiedDate=");
        sb.append(getModifiedDate());
        sb.append(", cpiIntfid=");
        sb.append(getCpiIntfid());
        sb.append(", createdBy=");
        sb.append(getCreatedBy());
        sb.append(", createdDate=");
        sb.append(getCreatedDate());
        sb.append(", source=");
        sb.append(getSource());
        sb.append(", indexValue=");
        sb.append(getIndexValue());
        sb.append(", addChgDelIndicator=");
        sb.append(getAddChgDelIndicator());
        sb.append(", batchId=");
        sb.append(getBatchId());
        sb.append(", ivldCpiSid=");
        sb.append(getIvldCpiSid());
        sb.append(", errorField=");
        sb.append(getErrorField());
        sb.append(", errorCode=");
        sb.append(getErrorCode());
        sb.append(", intfInsertedDate=");
        sb.append(getIntfInsertedDate());
        sb.append(", modifiedBy=");
        sb.append(getModifiedBy());
        sb.append(", reprocessedFlag=");
        sb.append(getReprocessedFlag());
        sb.append(", indexId=");
        sb.append(getIndexId());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(61);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.IvldCpi");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>effectiveDate</column-name><column-value><![CDATA[");
        sb.append(getEffectiveDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>reasonForFailure</column-name><column-value><![CDATA[");
        sb.append(getReasonForFailure());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>indexType</column-name><column-value><![CDATA[");
        sb.append(getIndexType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>status</column-name><column-value><![CDATA[");
        sb.append(getStatus());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
        sb.append(getModifiedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>cpiIntfid</column-name><column-value><![CDATA[");
        sb.append(getCpiIntfid());
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
            "<column><column-name>source</column-name><column-value><![CDATA[");
        sb.append(getSource());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>indexValue</column-name><column-value><![CDATA[");
        sb.append(getIndexValue());
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
            "<column><column-name>ivldCpiSid</column-name><column-value><![CDATA[");
        sb.append(getIvldCpiSid());
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
            "<column><column-name>indexId</column-name><column-value><![CDATA[");
        sb.append(getIndexId());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
