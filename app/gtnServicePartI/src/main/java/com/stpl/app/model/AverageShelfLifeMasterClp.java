package com.stpl.app.model;

import com.stpl.app.service.AverageShelfLifeMasterLocalServiceUtil;
import com.stpl.app.service.ClpSerializer;

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


public class AverageShelfLifeMasterClp extends BaseModelImpl<AverageShelfLifeMaster>
    implements AverageShelfLifeMaster {
    private int _createdBy;
    private int _averageShelfLifeMasterSid;
    private boolean _recordLockStatus;
    private String _itemIdType;
    private int _modifiedBy;
    private Date _createdDate;
    private String _source;
    private String _itemId;
    private String _avgShelfLife;
    private String _batchId;
    private Date _modifiedDate;
    private String _inboundStatus;
    private BaseModel<?> _averageShelfLifeMasterRemoteModel;

    public AverageShelfLifeMasterClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return AverageShelfLifeMaster.class;
    }

    @Override
    public String getModelClassName() {
        return AverageShelfLifeMaster.class.getName();
    }

    @Override
    public int getPrimaryKey() {
        return _averageShelfLifeMasterSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setAverageShelfLifeMasterSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _averageShelfLifeMasterSid;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Integer) primaryKeyObj).intValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("createdBy", getCreatedBy());
        attributes.put("averageShelfLifeMasterSid",
            getAverageShelfLifeMasterSid());
        attributes.put("recordLockStatus", getRecordLockStatus());
        attributes.put("itemIdType", getItemIdType());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("source", getSource());
        attributes.put("itemId", getItemId());
        attributes.put("avgShelfLife", getAvgShelfLife());
        attributes.put("batchId", getBatchId());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("inboundStatus", getInboundStatus());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Integer createdBy = (Integer) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        Integer averageShelfLifeMasterSid = (Integer) attributes.get(
                "averageShelfLifeMasterSid");

        if (averageShelfLifeMasterSid != null) {
            setAverageShelfLifeMasterSid(averageShelfLifeMasterSid);
        }

        Boolean recordLockStatus = (Boolean) attributes.get("recordLockStatus");

        if (recordLockStatus != null) {
            setRecordLockStatus(recordLockStatus);
        }

        String itemIdType = (String) attributes.get("itemIdType");

        if (itemIdType != null) {
            setItemIdType(itemIdType);
        }

        Integer modifiedBy = (Integer) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        String source = (String) attributes.get("source");

        if (source != null) {
            setSource(source);
        }

        String itemId = (String) attributes.get("itemId");

        if (itemId != null) {
            setItemId(itemId);
        }

        String avgShelfLife = (String) attributes.get("avgShelfLife");

        if (avgShelfLife != null) {
            setAvgShelfLife(avgShelfLife);
        }

        String batchId = (String) attributes.get("batchId");

        if (batchId != null) {
            setBatchId(batchId);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        String inboundStatus = (String) attributes.get("inboundStatus");

        if (inboundStatus != null) {
            setInboundStatus(inboundStatus);
        }
    }

    @Override
    public int getCreatedBy() {
        return _createdBy;
    }

    @Override
    public void setCreatedBy(int createdBy) {
        _createdBy = createdBy;

        if (_averageShelfLifeMasterRemoteModel != null) {
            try {
                Class<?> clazz = _averageShelfLifeMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedBy", int.class);

                method.invoke(_averageShelfLifeMasterRemoteModel, createdBy);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getAverageShelfLifeMasterSid() {
        return _averageShelfLifeMasterSid;
    }

    @Override
    public void setAverageShelfLifeMasterSid(int averageShelfLifeMasterSid) {
        _averageShelfLifeMasterSid = averageShelfLifeMasterSid;

        if (_averageShelfLifeMasterRemoteModel != null) {
            try {
                Class<?> clazz = _averageShelfLifeMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setAverageShelfLifeMasterSid",
                        int.class);

                method.invoke(_averageShelfLifeMasterRemoteModel,
                    averageShelfLifeMasterSid);
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

        if (_averageShelfLifeMasterRemoteModel != null) {
            try {
                Class<?> clazz = _averageShelfLifeMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setRecordLockStatus",
                        boolean.class);

                method.invoke(_averageShelfLifeMasterRemoteModel,
                    recordLockStatus);
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

        if (_averageShelfLifeMasterRemoteModel != null) {
            try {
                Class<?> clazz = _averageShelfLifeMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setItemIdType", String.class);

                method.invoke(_averageShelfLifeMasterRemoteModel, itemIdType);
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

        if (_averageShelfLifeMasterRemoteModel != null) {
            try {
                Class<?> clazz = _averageShelfLifeMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedBy", int.class);

                method.invoke(_averageShelfLifeMasterRemoteModel, modifiedBy);
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

        if (_averageShelfLifeMasterRemoteModel != null) {
            try {
                Class<?> clazz = _averageShelfLifeMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedDate", Date.class);

                method.invoke(_averageShelfLifeMasterRemoteModel, createdDate);
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

        if (_averageShelfLifeMasterRemoteModel != null) {
            try {
                Class<?> clazz = _averageShelfLifeMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setSource", String.class);

                method.invoke(_averageShelfLifeMasterRemoteModel, source);
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

        if (_averageShelfLifeMasterRemoteModel != null) {
            try {
                Class<?> clazz = _averageShelfLifeMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setItemId", String.class);

                method.invoke(_averageShelfLifeMasterRemoteModel, itemId);
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

        if (_averageShelfLifeMasterRemoteModel != null) {
            try {
                Class<?> clazz = _averageShelfLifeMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setAvgShelfLife", String.class);

                method.invoke(_averageShelfLifeMasterRemoteModel, avgShelfLife);
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

        if (_averageShelfLifeMasterRemoteModel != null) {
            try {
                Class<?> clazz = _averageShelfLifeMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setBatchId", String.class);

                method.invoke(_averageShelfLifeMasterRemoteModel, batchId);
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

        if (_averageShelfLifeMasterRemoteModel != null) {
            try {
                Class<?> clazz = _averageShelfLifeMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedDate", Date.class);

                method.invoke(_averageShelfLifeMasterRemoteModel, modifiedDate);
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

        if (_averageShelfLifeMasterRemoteModel != null) {
            try {
                Class<?> clazz = _averageShelfLifeMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setInboundStatus", String.class);

                method.invoke(_averageShelfLifeMasterRemoteModel, inboundStatus);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getAverageShelfLifeMasterRemoteModel() {
        return _averageShelfLifeMasterRemoteModel;
    }

    public void setAverageShelfLifeMasterRemoteModel(
        BaseModel<?> averageShelfLifeMasterRemoteModel) {
        _averageShelfLifeMasterRemoteModel = averageShelfLifeMasterRemoteModel;
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

        Class<?> remoteModelClass = _averageShelfLifeMasterRemoteModel.getClass();

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

        Object returnValue = method.invoke(_averageShelfLifeMasterRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            AverageShelfLifeMasterLocalServiceUtil.addAverageShelfLifeMaster(this);
        } else {
            AverageShelfLifeMasterLocalServiceUtil.updateAverageShelfLifeMaster(this);
        }
    }

    @Override
    public AverageShelfLifeMaster toEscapedModel() {
        return (AverageShelfLifeMaster) ProxyUtil.newProxyInstance(AverageShelfLifeMaster.class.getClassLoader(),
            new Class[] { AverageShelfLifeMaster.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        AverageShelfLifeMasterClp clone = new AverageShelfLifeMasterClp();

        clone.setCreatedBy(getCreatedBy());
        clone.setAverageShelfLifeMasterSid(getAverageShelfLifeMasterSid());
        clone.setRecordLockStatus(getRecordLockStatus());
        clone.setItemIdType(getItemIdType());
        clone.setModifiedBy(getModifiedBy());
        clone.setCreatedDate(getCreatedDate());
        clone.setSource(getSource());
        clone.setItemId(getItemId());
        clone.setAvgShelfLife(getAvgShelfLife());
        clone.setBatchId(getBatchId());
        clone.setModifiedDate(getModifiedDate());
        clone.setInboundStatus(getInboundStatus());

        return clone;
    }

    @Override
    public int compareTo(AverageShelfLifeMaster averageShelfLifeMaster) {
        int primaryKey = averageShelfLifeMaster.getPrimaryKey();

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

        if (!(obj instanceof AverageShelfLifeMasterClp)) {
            return false;
        }

        AverageShelfLifeMasterClp averageShelfLifeMaster = (AverageShelfLifeMasterClp) obj;

        int primaryKey = averageShelfLifeMaster.getPrimaryKey();

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
        StringBundler sb = new StringBundler(25);

        sb.append("{createdBy=");
        sb.append(getCreatedBy());
        sb.append(", averageShelfLifeMasterSid=");
        sb.append(getAverageShelfLifeMasterSid());
        sb.append(", recordLockStatus=");
        sb.append(getRecordLockStatus());
        sb.append(", itemIdType=");
        sb.append(getItemIdType());
        sb.append(", modifiedBy=");
        sb.append(getModifiedBy());
        sb.append(", createdDate=");
        sb.append(getCreatedDate());
        sb.append(", source=");
        sb.append(getSource());
        sb.append(", itemId=");
        sb.append(getItemId());
        sb.append(", avgShelfLife=");
        sb.append(getAvgShelfLife());
        sb.append(", batchId=");
        sb.append(getBatchId());
        sb.append(", modifiedDate=");
        sb.append(getModifiedDate());
        sb.append(", inboundStatus=");
        sb.append(getInboundStatus());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(40);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.AverageShelfLifeMaster");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>createdBy</column-name><column-value><![CDATA[");
        sb.append(getCreatedBy());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>averageShelfLifeMasterSid</column-name><column-value><![CDATA[");
        sb.append(getAverageShelfLifeMasterSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>recordLockStatus</column-name><column-value><![CDATA[");
        sb.append(getRecordLockStatus());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemIdType</column-name><column-value><![CDATA[");
        sb.append(getItemIdType());
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
            "<column><column-name>source</column-name><column-value><![CDATA[");
        sb.append(getSource());
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
            "<column><column-name>batchId</column-name><column-value><![CDATA[");
        sb.append(getBatchId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
        sb.append(getModifiedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>inboundStatus</column-name><column-value><![CDATA[");
        sb.append(getInboundStatus());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
