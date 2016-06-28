package com.stpl.app.model;

import com.stpl.app.service.ClpSerializer;
import com.stpl.app.service.IvldAverageShelfLifeLocalServiceUtil;

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


public class IvldAverageShelfLifeClp extends BaseModelImpl<IvldAverageShelfLife>
    implements IvldAverageShelfLife {
    private String _reasonForFailure;
    private int _ivldAverageShelfLifeSid;
    private String _itemId;
    private String _avgShelfLife;
    private Date _modifiedDate;
    private String _createdBy;
    private Date _createdDate;
    private String _source;
    private String _itemIdType;
    private String _batchId;
    private String _addChgDelIndicator;
    private String _averageShelfLifeIntfid;
    private String _errorField;
    private String _errorCode;
    private Date _intfInsertedDate;
    private String _modifiedBy;
    private String _reprocessedFlag;
    private BaseModel<?> _ivldAverageShelfLifeRemoteModel;

    public IvldAverageShelfLifeClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return IvldAverageShelfLife.class;
    }

    @Override
    public String getModelClassName() {
        return IvldAverageShelfLife.class.getName();
    }

    @Override
    public int getPrimaryKey() {
        return _ivldAverageShelfLifeSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setIvldAverageShelfLifeSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _ivldAverageShelfLifeSid;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Integer) primaryKeyObj).intValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("reasonForFailure", getReasonForFailure());
        attributes.put("ivldAverageShelfLifeSid", getIvldAverageShelfLifeSid());
        attributes.put("itemId", getItemId());
        attributes.put("avgShelfLife", getAvgShelfLife());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("source", getSource());
        attributes.put("itemIdType", getItemIdType());
        attributes.put("batchId", getBatchId());
        attributes.put("addChgDelIndicator", getAddChgDelIndicator());
        attributes.put("averageShelfLifeIntfid", getAverageShelfLifeIntfid());
        attributes.put("errorField", getErrorField());
        attributes.put("errorCode", getErrorCode());
        attributes.put("intfInsertedDate", getIntfInsertedDate());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("reprocessedFlag", getReprocessedFlag());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        String reasonForFailure = (String) attributes.get("reasonForFailure");

        if (reasonForFailure != null) {
            setReasonForFailure(reasonForFailure);
        }

        Integer ivldAverageShelfLifeSid = (Integer) attributes.get(
                "ivldAverageShelfLifeSid");

        if (ivldAverageShelfLifeSid != null) {
            setIvldAverageShelfLifeSid(ivldAverageShelfLifeSid);
        }

        String itemId = (String) attributes.get("itemId");

        if (itemId != null) {
            setItemId(itemId);
        }

        String avgShelfLife = (String) attributes.get("avgShelfLife");

        if (avgShelfLife != null) {
            setAvgShelfLife(avgShelfLife);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
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

        String itemIdType = (String) attributes.get("itemIdType");

        if (itemIdType != null) {
            setItemIdType(itemIdType);
        }

        String batchId = (String) attributes.get("batchId");

        if (batchId != null) {
            setBatchId(batchId);
        }

        String addChgDelIndicator = (String) attributes.get(
                "addChgDelIndicator");

        if (addChgDelIndicator != null) {
            setAddChgDelIndicator(addChgDelIndicator);
        }

        String averageShelfLifeIntfid = (String) attributes.get(
                "averageShelfLifeIntfid");

        if (averageShelfLifeIntfid != null) {
            setAverageShelfLifeIntfid(averageShelfLifeIntfid);
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
    }

    @Override
    public String getReasonForFailure() {
        return _reasonForFailure;
    }

    @Override
    public void setReasonForFailure(String reasonForFailure) {
        _reasonForFailure = reasonForFailure;

        if (_ivldAverageShelfLifeRemoteModel != null) {
            try {
                Class<?> clazz = _ivldAverageShelfLifeRemoteModel.getClass();

                Method method = clazz.getMethod("setReasonForFailure",
                        String.class);

                method.invoke(_ivldAverageShelfLifeRemoteModel, reasonForFailure);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getIvldAverageShelfLifeSid() {
        return _ivldAverageShelfLifeSid;
    }

    @Override
    public void setIvldAverageShelfLifeSid(int ivldAverageShelfLifeSid) {
        _ivldAverageShelfLifeSid = ivldAverageShelfLifeSid;

        if (_ivldAverageShelfLifeRemoteModel != null) {
            try {
                Class<?> clazz = _ivldAverageShelfLifeRemoteModel.getClass();

                Method method = clazz.getMethod("setIvldAverageShelfLifeSid",
                        int.class);

                method.invoke(_ivldAverageShelfLifeRemoteModel,
                    ivldAverageShelfLifeSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getItemId() {
        return _itemId;
    }

    @Override
    public void setItemId(String itemId) {
        _itemId = itemId;

        if (_ivldAverageShelfLifeRemoteModel != null) {
            try {
                Class<?> clazz = _ivldAverageShelfLifeRemoteModel.getClass();

                Method method = clazz.getMethod("setItemId", String.class);

                method.invoke(_ivldAverageShelfLifeRemoteModel, itemId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getAvgShelfLife() {
        return _avgShelfLife;
    }

    @Override
    public void setAvgShelfLife(String avgShelfLife) {
        _avgShelfLife = avgShelfLife;

        if (_ivldAverageShelfLifeRemoteModel != null) {
            try {
                Class<?> clazz = _ivldAverageShelfLifeRemoteModel.getClass();

                Method method = clazz.getMethod("setAvgShelfLife", String.class);

                method.invoke(_ivldAverageShelfLifeRemoteModel, avgShelfLife);
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

        if (_ivldAverageShelfLifeRemoteModel != null) {
            try {
                Class<?> clazz = _ivldAverageShelfLifeRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedDate", Date.class);

                method.invoke(_ivldAverageShelfLifeRemoteModel, modifiedDate);
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

        if (_ivldAverageShelfLifeRemoteModel != null) {
            try {
                Class<?> clazz = _ivldAverageShelfLifeRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedBy", String.class);

                method.invoke(_ivldAverageShelfLifeRemoteModel, createdBy);
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

        if (_ivldAverageShelfLifeRemoteModel != null) {
            try {
                Class<?> clazz = _ivldAverageShelfLifeRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedDate", Date.class);

                method.invoke(_ivldAverageShelfLifeRemoteModel, createdDate);
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

        if (_ivldAverageShelfLifeRemoteModel != null) {
            try {
                Class<?> clazz = _ivldAverageShelfLifeRemoteModel.getClass();

                Method method = clazz.getMethod("setSource", String.class);

                method.invoke(_ivldAverageShelfLifeRemoteModel, source);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getItemIdType() {
        return _itemIdType;
    }

    @Override
    public void setItemIdType(String itemIdType) {
        _itemIdType = itemIdType;

        if (_ivldAverageShelfLifeRemoteModel != null) {
            try {
                Class<?> clazz = _ivldAverageShelfLifeRemoteModel.getClass();

                Method method = clazz.getMethod("setItemIdType", String.class);

                method.invoke(_ivldAverageShelfLifeRemoteModel, itemIdType);
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

        if (_ivldAverageShelfLifeRemoteModel != null) {
            try {
                Class<?> clazz = _ivldAverageShelfLifeRemoteModel.getClass();

                Method method = clazz.getMethod("setBatchId", String.class);

                method.invoke(_ivldAverageShelfLifeRemoteModel, batchId);
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

        if (_ivldAverageShelfLifeRemoteModel != null) {
            try {
                Class<?> clazz = _ivldAverageShelfLifeRemoteModel.getClass();

                Method method = clazz.getMethod("setAddChgDelIndicator",
                        String.class);

                method.invoke(_ivldAverageShelfLifeRemoteModel,
                    addChgDelIndicator);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getAverageShelfLifeIntfid() {
        return _averageShelfLifeIntfid;
    }

    @Override
    public void setAverageShelfLifeIntfid(String averageShelfLifeIntfid) {
        _averageShelfLifeIntfid = averageShelfLifeIntfid;

        if (_ivldAverageShelfLifeRemoteModel != null) {
            try {
                Class<?> clazz = _ivldAverageShelfLifeRemoteModel.getClass();

                Method method = clazz.getMethod("setAverageShelfLifeIntfid",
                        String.class);

                method.invoke(_ivldAverageShelfLifeRemoteModel,
                    averageShelfLifeIntfid);
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

        if (_ivldAverageShelfLifeRemoteModel != null) {
            try {
                Class<?> clazz = _ivldAverageShelfLifeRemoteModel.getClass();

                Method method = clazz.getMethod("setErrorField", String.class);

                method.invoke(_ivldAverageShelfLifeRemoteModel, errorField);
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

        if (_ivldAverageShelfLifeRemoteModel != null) {
            try {
                Class<?> clazz = _ivldAverageShelfLifeRemoteModel.getClass();

                Method method = clazz.getMethod("setErrorCode", String.class);

                method.invoke(_ivldAverageShelfLifeRemoteModel, errorCode);
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

        if (_ivldAverageShelfLifeRemoteModel != null) {
            try {
                Class<?> clazz = _ivldAverageShelfLifeRemoteModel.getClass();

                Method method = clazz.getMethod("setIntfInsertedDate",
                        Date.class);

                method.invoke(_ivldAverageShelfLifeRemoteModel, intfInsertedDate);
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

        if (_ivldAverageShelfLifeRemoteModel != null) {
            try {
                Class<?> clazz = _ivldAverageShelfLifeRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedBy", String.class);

                method.invoke(_ivldAverageShelfLifeRemoteModel, modifiedBy);
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

        if (_ivldAverageShelfLifeRemoteModel != null) {
            try {
                Class<?> clazz = _ivldAverageShelfLifeRemoteModel.getClass();

                Method method = clazz.getMethod("setReprocessedFlag",
                        String.class);

                method.invoke(_ivldAverageShelfLifeRemoteModel, reprocessedFlag);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getIvldAverageShelfLifeRemoteModel() {
        return _ivldAverageShelfLifeRemoteModel;
    }

    public void setIvldAverageShelfLifeRemoteModel(
        BaseModel<?> ivldAverageShelfLifeRemoteModel) {
        _ivldAverageShelfLifeRemoteModel = ivldAverageShelfLifeRemoteModel;
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

        Class<?> remoteModelClass = _ivldAverageShelfLifeRemoteModel.getClass();

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

        Object returnValue = method.invoke(_ivldAverageShelfLifeRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            IvldAverageShelfLifeLocalServiceUtil.addIvldAverageShelfLife(this);
        } else {
            IvldAverageShelfLifeLocalServiceUtil.updateIvldAverageShelfLife(this);
        }
    }

    @Override
    public IvldAverageShelfLife toEscapedModel() {
        return (IvldAverageShelfLife) ProxyUtil.newProxyInstance(IvldAverageShelfLife.class.getClassLoader(),
            new Class[] { IvldAverageShelfLife.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        IvldAverageShelfLifeClp clone = new IvldAverageShelfLifeClp();

        clone.setReasonForFailure(getReasonForFailure());
        clone.setIvldAverageShelfLifeSid(getIvldAverageShelfLifeSid());
        clone.setItemId(getItemId());
        clone.setAvgShelfLife(getAvgShelfLife());
        clone.setModifiedDate(getModifiedDate());
        clone.setCreatedBy(getCreatedBy());
        clone.setCreatedDate(getCreatedDate());
        clone.setSource(getSource());
        clone.setItemIdType(getItemIdType());
        clone.setBatchId(getBatchId());
        clone.setAddChgDelIndicator(getAddChgDelIndicator());
        clone.setAverageShelfLifeIntfid(getAverageShelfLifeIntfid());
        clone.setErrorField(getErrorField());
        clone.setErrorCode(getErrorCode());
        clone.setIntfInsertedDate(getIntfInsertedDate());
        clone.setModifiedBy(getModifiedBy());
        clone.setReprocessedFlag(getReprocessedFlag());

        return clone;
    }

    @Override
    public int compareTo(IvldAverageShelfLife ivldAverageShelfLife) {
        int primaryKey = ivldAverageShelfLife.getPrimaryKey();

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

        if (!(obj instanceof IvldAverageShelfLifeClp)) {
            return false;
        }

        IvldAverageShelfLifeClp ivldAverageShelfLife = (IvldAverageShelfLifeClp) obj;

        int primaryKey = ivldAverageShelfLife.getPrimaryKey();

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
        StringBundler sb = new StringBundler(35);

        sb.append("{reasonForFailure=");
        sb.append(getReasonForFailure());
        sb.append(", ivldAverageShelfLifeSid=");
        sb.append(getIvldAverageShelfLifeSid());
        sb.append(", itemId=");
        sb.append(getItemId());
        sb.append(", avgShelfLife=");
        sb.append(getAvgShelfLife());
        sb.append(", modifiedDate=");
        sb.append(getModifiedDate());
        sb.append(", createdBy=");
        sb.append(getCreatedBy());
        sb.append(", createdDate=");
        sb.append(getCreatedDate());
        sb.append(", source=");
        sb.append(getSource());
        sb.append(", itemIdType=");
        sb.append(getItemIdType());
        sb.append(", batchId=");
        sb.append(getBatchId());
        sb.append(", addChgDelIndicator=");
        sb.append(getAddChgDelIndicator());
        sb.append(", averageShelfLifeIntfid=");
        sb.append(getAverageShelfLifeIntfid());
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
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(55);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.IvldAverageShelfLife");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>reasonForFailure</column-name><column-value><![CDATA[");
        sb.append(getReasonForFailure());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>ivldAverageShelfLifeSid</column-name><column-value><![CDATA[");
        sb.append(getIvldAverageShelfLifeSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemId</column-name><column-value><![CDATA[");
        sb.append(getItemId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>avgShelfLife</column-name><column-value><![CDATA[");
        sb.append(getAvgShelfLife());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
        sb.append(getModifiedDate());
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
            "<column><column-name>itemIdType</column-name><column-value><![CDATA[");
        sb.append(getItemIdType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>batchId</column-name><column-value><![CDATA[");
        sb.append(getBatchId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>addChgDelIndicator</column-name><column-value><![CDATA[");
        sb.append(getAddChgDelIndicator());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>averageShelfLifeIntfid</column-name><column-value><![CDATA[");
        sb.append(getAverageShelfLifeIntfid());
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

        sb.append("</model>");

        return sb.toString();
    }
}
