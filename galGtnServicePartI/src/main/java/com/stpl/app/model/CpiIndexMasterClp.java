package com.stpl.app.model;

import com.stpl.app.service.ClpSerializer;
import com.stpl.app.service.CpiIndexMasterLocalServiceUtil;

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


public class CpiIndexMasterClp extends BaseModelImpl<CpiIndexMaster>
    implements CpiIndexMaster {
    private Date _effectiveDate;
    private int _createdBy;
    private int _modifiedBy;
    private Date _createdDate;
    private int _cpiIndexMasterSid;
    private String _batchId;
    private Date _modifiedDate;
    private String _status;
    private String _indexType;
    private String _indexId;
    private boolean _recordLockStatus;
    private String _indexValue;
    private String _source;
    private String _inboundStatus;
    private BaseModel<?> _cpiIndexMasterRemoteModel;

    public CpiIndexMasterClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return CpiIndexMaster.class;
    }

    @Override
    public String getModelClassName() {
        return CpiIndexMaster.class.getName();
    }

    @Override
    public int getPrimaryKey() {
        return _cpiIndexMasterSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setCpiIndexMasterSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _cpiIndexMasterSid;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Integer) primaryKeyObj).intValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("effectiveDate", getEffectiveDate());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("cpiIndexMasterSid", getCpiIndexMasterSid());
        attributes.put("batchId", getBatchId());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("status", getStatus());
        attributes.put("indexType", getIndexType());
        attributes.put("indexId", getIndexId());
        attributes.put("recordLockStatus", getRecordLockStatus());
        attributes.put("indexValue", getIndexValue());
        attributes.put("source", getSource());
        attributes.put("inboundStatus", getInboundStatus());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Date effectiveDate = (Date) attributes.get("effectiveDate");

        if (effectiveDate != null) {
            setEffectiveDate(effectiveDate);
        }

        Integer createdBy = (Integer) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        Integer modifiedBy = (Integer) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        Integer cpiIndexMasterSid = (Integer) attributes.get(
                "cpiIndexMasterSid");

        if (cpiIndexMasterSid != null) {
            setCpiIndexMasterSid(cpiIndexMasterSid);
        }

        String batchId = (String) attributes.get("batchId");

        if (batchId != null) {
            setBatchId(batchId);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        String status = (String) attributes.get("status");

        if (status != null) {
            setStatus(status);
        }

        String indexType = (String) attributes.get("indexType");

        if (indexType != null) {
            setIndexType(indexType);
        }

        String indexId = (String) attributes.get("indexId");

        if (indexId != null) {
            setIndexId(indexId);
        }

        Boolean recordLockStatus = (Boolean) attributes.get("recordLockStatus");

        if (recordLockStatus != null) {
            setRecordLockStatus(recordLockStatus);
        }

        String indexValue = (String) attributes.get("indexValue");

        if (indexValue != null) {
            setIndexValue(indexValue);
        }

        String source = (String) attributes.get("source");

        if (source != null) {
            setSource(source);
        }

        String inboundStatus = (String) attributes.get("inboundStatus");

        if (inboundStatus != null) {
            setInboundStatus(inboundStatus);
        }
    }

    @Override
    public Date getEffectiveDate() {
        return _effectiveDate;
    }

    @Override
    public void setEffectiveDate(Date effectiveDate) {
        _effectiveDate = effectiveDate;

        if (_cpiIndexMasterRemoteModel != null) {
            try {
                Class<?> clazz = _cpiIndexMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setEffectiveDate", Date.class);

                method.invoke(_cpiIndexMasterRemoteModel, effectiveDate);
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

        if (_cpiIndexMasterRemoteModel != null) {
            try {
                Class<?> clazz = _cpiIndexMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedBy", int.class);

                method.invoke(_cpiIndexMasterRemoteModel, createdBy);
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

        if (_cpiIndexMasterRemoteModel != null) {
            try {
                Class<?> clazz = _cpiIndexMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedBy", int.class);

                method.invoke(_cpiIndexMasterRemoteModel, modifiedBy);
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

        if (_cpiIndexMasterRemoteModel != null) {
            try {
                Class<?> clazz = _cpiIndexMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedDate", Date.class);

                method.invoke(_cpiIndexMasterRemoteModel, createdDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getCpiIndexMasterSid() {
        return _cpiIndexMasterSid;
    }

    @Override
    public void setCpiIndexMasterSid(int cpiIndexMasterSid) {
        _cpiIndexMasterSid = cpiIndexMasterSid;

        if (_cpiIndexMasterRemoteModel != null) {
            try {
                Class<?> clazz = _cpiIndexMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCpiIndexMasterSid",
                        int.class);

                method.invoke(_cpiIndexMasterRemoteModel, cpiIndexMasterSid);
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

        if (_cpiIndexMasterRemoteModel != null) {
            try {
                Class<?> clazz = _cpiIndexMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setBatchId", String.class);

                method.invoke(_cpiIndexMasterRemoteModel, batchId);
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

        if (_cpiIndexMasterRemoteModel != null) {
            try {
                Class<?> clazz = _cpiIndexMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedDate", Date.class);

                method.invoke(_cpiIndexMasterRemoteModel, modifiedDate);
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

        if (_cpiIndexMasterRemoteModel != null) {
            try {
                Class<?> clazz = _cpiIndexMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setStatus", String.class);

                method.invoke(_cpiIndexMasterRemoteModel, status);
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

        if (_cpiIndexMasterRemoteModel != null) {
            try {
                Class<?> clazz = _cpiIndexMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setIndexType", String.class);

                method.invoke(_cpiIndexMasterRemoteModel, indexType);
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

        if (_cpiIndexMasterRemoteModel != null) {
            try {
                Class<?> clazz = _cpiIndexMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setIndexId", String.class);

                method.invoke(_cpiIndexMasterRemoteModel, indexId);
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

        if (_cpiIndexMasterRemoteModel != null) {
            try {
                Class<?> clazz = _cpiIndexMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setRecordLockStatus",
                        boolean.class);

                method.invoke(_cpiIndexMasterRemoteModel, recordLockStatus);
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

        if (_cpiIndexMasterRemoteModel != null) {
            try {
                Class<?> clazz = _cpiIndexMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setIndexValue", String.class);

                method.invoke(_cpiIndexMasterRemoteModel, indexValue);
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

        if (_cpiIndexMasterRemoteModel != null) {
            try {
                Class<?> clazz = _cpiIndexMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setSource", String.class);

                method.invoke(_cpiIndexMasterRemoteModel, source);
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

        if (_cpiIndexMasterRemoteModel != null) {
            try {
                Class<?> clazz = _cpiIndexMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setInboundStatus", String.class);

                method.invoke(_cpiIndexMasterRemoteModel, inboundStatus);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getCpiIndexMasterRemoteModel() {
        return _cpiIndexMasterRemoteModel;
    }

    public void setCpiIndexMasterRemoteModel(
        BaseModel<?> cpiIndexMasterRemoteModel) {
        _cpiIndexMasterRemoteModel = cpiIndexMasterRemoteModel;
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

        Class<?> remoteModelClass = _cpiIndexMasterRemoteModel.getClass();

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

        Object returnValue = method.invoke(_cpiIndexMasterRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            CpiIndexMasterLocalServiceUtil.addCpiIndexMaster(this);
        } else {
            CpiIndexMasterLocalServiceUtil.updateCpiIndexMaster(this);
        }
    }

    @Override
    public CpiIndexMaster toEscapedModel() {
        return (CpiIndexMaster) ProxyUtil.newProxyInstance(CpiIndexMaster.class.getClassLoader(),
            new Class[] { CpiIndexMaster.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        CpiIndexMasterClp clone = new CpiIndexMasterClp();

        clone.setEffectiveDate(getEffectiveDate());
        clone.setCreatedBy(getCreatedBy());
        clone.setModifiedBy(getModifiedBy());
        clone.setCreatedDate(getCreatedDate());
        clone.setCpiIndexMasterSid(getCpiIndexMasterSid());
        clone.setBatchId(getBatchId());
        clone.setModifiedDate(getModifiedDate());
        clone.setStatus(getStatus());
        clone.setIndexType(getIndexType());
        clone.setIndexId(getIndexId());
        clone.setRecordLockStatus(getRecordLockStatus());
        clone.setIndexValue(getIndexValue());
        clone.setSource(getSource());
        clone.setInboundStatus(getInboundStatus());

        return clone;
    }

    @Override
    public int compareTo(CpiIndexMaster cpiIndexMaster) {
        int primaryKey = cpiIndexMaster.getPrimaryKey();

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

        if (!(obj instanceof CpiIndexMasterClp)) {
            return false;
        }

        CpiIndexMasterClp cpiIndexMaster = (CpiIndexMasterClp) obj;

        int primaryKey = cpiIndexMaster.getPrimaryKey();

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
        StringBundler sb = new StringBundler(29);

        sb.append("{effectiveDate=");
        sb.append(getEffectiveDate());
        sb.append(", createdBy=");
        sb.append(getCreatedBy());
        sb.append(", modifiedBy=");
        sb.append(getModifiedBy());
        sb.append(", createdDate=");
        sb.append(getCreatedDate());
        sb.append(", cpiIndexMasterSid=");
        sb.append(getCpiIndexMasterSid());
        sb.append(", batchId=");
        sb.append(getBatchId());
        sb.append(", modifiedDate=");
        sb.append(getModifiedDate());
        sb.append(", status=");
        sb.append(getStatus());
        sb.append(", indexType=");
        sb.append(getIndexType());
        sb.append(", indexId=");
        sb.append(getIndexId());
        sb.append(", recordLockStatus=");
        sb.append(getRecordLockStatus());
        sb.append(", indexValue=");
        sb.append(getIndexValue());
        sb.append(", source=");
        sb.append(getSource());
        sb.append(", inboundStatus=");
        sb.append(getInboundStatus());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(46);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.CpiIndexMaster");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>effectiveDate</column-name><column-value><![CDATA[");
        sb.append(getEffectiveDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>createdBy</column-name><column-value><![CDATA[");
        sb.append(getCreatedBy());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedBy</column-name><column-value><![CDATA[");
        sb.append(getModifiedBy());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>createdDate</column-name><column-value><![CDATA[");
        sb.append(getCreatedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>cpiIndexMasterSid</column-name><column-value><![CDATA[");
        sb.append(getCpiIndexMasterSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>batchId</column-name><column-value><![CDATA[");
        sb.append(getBatchId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
        sb.append(getModifiedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>status</column-name><column-value><![CDATA[");
        sb.append(getStatus());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>indexType</column-name><column-value><![CDATA[");
        sb.append(getIndexType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>indexId</column-name><column-value><![CDATA[");
        sb.append(getIndexId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>recordLockStatus</column-name><column-value><![CDATA[");
        sb.append(getRecordLockStatus());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>indexValue</column-name><column-value><![CDATA[");
        sb.append(getIndexValue());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>source</column-name><column-value><![CDATA[");
        sb.append(getSource());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>inboundStatus</column-name><column-value><![CDATA[");
        sb.append(getInboundStatus());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
