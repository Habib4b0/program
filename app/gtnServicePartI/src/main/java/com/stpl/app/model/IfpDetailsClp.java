package com.stpl.app.model;

import com.stpl.app.service.ClpSerializer;
import com.stpl.app.service.IfpDetailsLocalServiceUtil;

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


public class IfpDetailsClp extends BaseModelImpl<IfpDetails>
    implements IfpDetails {
    private int _itemMasterSid;
    private Date _endDate;
    private Date _modifiedDate;
    private boolean _recordLockStatus;
    private Date _startDate;
    private Date _createdDate;
    private String _source;
    private int _createdBy;
    private Date _itemIfpAttachedDate;
    private String _batchId;
    private int _modifiedBy;
    private String _inboundStatus;
    private int _ifpDetailsSid;
    private int _ifpModelSid;
    private int _itemIfpAttachedStatus;
    private BaseModel<?> _ifpDetailsRemoteModel;

    public IfpDetailsClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return IfpDetails.class;
    }

    @Override
    public String getModelClassName() {
        return IfpDetails.class.getName();
    }

    @Override
    public int getPrimaryKey() {
        return _ifpDetailsSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setIfpDetailsSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _ifpDetailsSid;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Integer) primaryKeyObj).intValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("itemMasterSid", getItemMasterSid());
        attributes.put("endDate", getEndDate());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("recordLockStatus", getRecordLockStatus());
        attributes.put("startDate", getStartDate());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("source", getSource());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("itemIfpAttachedDate", getItemIfpAttachedDate());
        attributes.put("batchId", getBatchId());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("inboundStatus", getInboundStatus());
        attributes.put("ifpDetailsSid", getIfpDetailsSid());
        attributes.put("ifpModelSid", getIfpModelSid());
        attributes.put("itemIfpAttachedStatus", getItemIfpAttachedStatus());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Integer itemMasterSid = (Integer) attributes.get("itemMasterSid");

        if (itemMasterSid != null) {
            setItemMasterSid(itemMasterSid);
        }

        Date endDate = (Date) attributes.get("endDate");

        if (endDate != null) {
            setEndDate(endDate);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        Boolean recordLockStatus = (Boolean) attributes.get("recordLockStatus");

        if (recordLockStatus != null) {
            setRecordLockStatus(recordLockStatus);
        }

        Date startDate = (Date) attributes.get("startDate");

        if (startDate != null) {
            setStartDate(startDate);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        String source = (String) attributes.get("source");

        if (source != null) {
            setSource(source);
        }

        Integer createdBy = (Integer) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        Date itemIfpAttachedDate = (Date) attributes.get("itemIfpAttachedDate");

        if (itemIfpAttachedDate != null) {
            setItemIfpAttachedDate(itemIfpAttachedDate);
        }

        String batchId = (String) attributes.get("batchId");

        if (batchId != null) {
            setBatchId(batchId);
        }

        Integer modifiedBy = (Integer) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        String inboundStatus = (String) attributes.get("inboundStatus");

        if (inboundStatus != null) {
            setInboundStatus(inboundStatus);
        }

        Integer ifpDetailsSid = (Integer) attributes.get("ifpDetailsSid");

        if (ifpDetailsSid != null) {
            setIfpDetailsSid(ifpDetailsSid);
        }

        Integer ifpModelSid = (Integer) attributes.get("ifpModelSid");

        if (ifpModelSid != null) {
            setIfpModelSid(ifpModelSid);
        }

        Integer itemIfpAttachedStatus = (Integer) attributes.get(
                "itemIfpAttachedStatus");

        if (itemIfpAttachedStatus != null) {
            setItemIfpAttachedStatus(itemIfpAttachedStatus);
        }
    }

    @Override
    public int getItemMasterSid() {
        return _itemMasterSid;
    }

    @Override
    public void setItemMasterSid(int itemMasterSid) {
        _itemMasterSid = itemMasterSid;

        if (_ifpDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _ifpDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setItemMasterSid", int.class);

                method.invoke(_ifpDetailsRemoteModel, itemMasterSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getEndDate() {
        return _endDate;
    }

    @Override
    public void setEndDate(Date endDate) {
        _endDate = endDate;

        if (_ifpDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _ifpDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setEndDate", Date.class);

                method.invoke(_ifpDetailsRemoteModel, endDate);
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

        if (_ifpDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _ifpDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedDate", Date.class);

                method.invoke(_ifpDetailsRemoteModel, modifiedDate);
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

        if (_ifpDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _ifpDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setRecordLockStatus",
                        boolean.class);

                method.invoke(_ifpDetailsRemoteModel, recordLockStatus);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getStartDate() {
        return _startDate;
    }

    @Override
    public void setStartDate(Date startDate) {
        _startDate = startDate;

        if (_ifpDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _ifpDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setStartDate", Date.class);

                method.invoke(_ifpDetailsRemoteModel, startDate);
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

        if (_ifpDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _ifpDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedDate", Date.class);

                method.invoke(_ifpDetailsRemoteModel, createdDate);
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

        if (_ifpDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _ifpDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setSource", String.class);

                method.invoke(_ifpDetailsRemoteModel, source);
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

        if (_ifpDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _ifpDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedBy", int.class);

                method.invoke(_ifpDetailsRemoteModel, createdBy);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getItemIfpAttachedDate() {
        return _itemIfpAttachedDate;
    }

    @Override
    public void setItemIfpAttachedDate(Date itemIfpAttachedDate) {
        _itemIfpAttachedDate = itemIfpAttachedDate;

        if (_ifpDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _ifpDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setItemIfpAttachedDate",
                        Date.class);

                method.invoke(_ifpDetailsRemoteModel, itemIfpAttachedDate);
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

        if (_ifpDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _ifpDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setBatchId", String.class);

                method.invoke(_ifpDetailsRemoteModel, batchId);
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

        if (_ifpDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _ifpDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedBy", int.class);

                method.invoke(_ifpDetailsRemoteModel, modifiedBy);
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

        if (_ifpDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _ifpDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setInboundStatus", String.class);

                method.invoke(_ifpDetailsRemoteModel, inboundStatus);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getIfpDetailsSid() {
        return _ifpDetailsSid;
    }

    @Override
    public void setIfpDetailsSid(int ifpDetailsSid) {
        _ifpDetailsSid = ifpDetailsSid;

        if (_ifpDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _ifpDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setIfpDetailsSid", int.class);

                method.invoke(_ifpDetailsRemoteModel, ifpDetailsSid);
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

        if (_ifpDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _ifpDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setIfpModelSid", int.class);

                method.invoke(_ifpDetailsRemoteModel, ifpModelSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getItemIfpAttachedStatus() {
        return _itemIfpAttachedStatus;
    }

    @Override
    public void setItemIfpAttachedStatus(int itemIfpAttachedStatus) {
        _itemIfpAttachedStatus = itemIfpAttachedStatus;

        if (_ifpDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _ifpDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setItemIfpAttachedStatus",
                        int.class);

                method.invoke(_ifpDetailsRemoteModel, itemIfpAttachedStatus);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getIfpDetailsRemoteModel() {
        return _ifpDetailsRemoteModel;
    }

    public void setIfpDetailsRemoteModel(BaseModel<?> ifpDetailsRemoteModel) {
        _ifpDetailsRemoteModel = ifpDetailsRemoteModel;
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

        Class<?> remoteModelClass = _ifpDetailsRemoteModel.getClass();

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

        Object returnValue = method.invoke(_ifpDetailsRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            IfpDetailsLocalServiceUtil.addIfpDetails(this);
        } else {
            IfpDetailsLocalServiceUtil.updateIfpDetails(this);
        }
    }

    @Override
    public IfpDetails toEscapedModel() {
        return (IfpDetails) ProxyUtil.newProxyInstance(IfpDetails.class.getClassLoader(),
            new Class[] { IfpDetails.class }, new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        IfpDetailsClp clone = new IfpDetailsClp();

        clone.setItemMasterSid(getItemMasterSid());
        clone.setEndDate(getEndDate());
        clone.setModifiedDate(getModifiedDate());
        clone.setRecordLockStatus(getRecordLockStatus());
        clone.setStartDate(getStartDate());
        clone.setCreatedDate(getCreatedDate());
        clone.setSource(getSource());
        clone.setCreatedBy(getCreatedBy());
        clone.setItemIfpAttachedDate(getItemIfpAttachedDate());
        clone.setBatchId(getBatchId());
        clone.setModifiedBy(getModifiedBy());
        clone.setInboundStatus(getInboundStatus());
        clone.setIfpDetailsSid(getIfpDetailsSid());
        clone.setIfpModelSid(getIfpModelSid());
        clone.setItemIfpAttachedStatus(getItemIfpAttachedStatus());

        return clone;
    }

    @Override
    public int compareTo(IfpDetails ifpDetails) {
        int primaryKey = ifpDetails.getPrimaryKey();

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

        if (!(obj instanceof IfpDetailsClp)) {
            return false;
        }

        IfpDetailsClp ifpDetails = (IfpDetailsClp) obj;

        int primaryKey = ifpDetails.getPrimaryKey();

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
        StringBundler sb = new StringBundler(31);

        sb.append("{itemMasterSid=");
        sb.append(getItemMasterSid());
        sb.append(", endDate=");
        sb.append(getEndDate());
        sb.append(", modifiedDate=");
        sb.append(getModifiedDate());
        sb.append(", recordLockStatus=");
        sb.append(getRecordLockStatus());
        sb.append(", startDate=");
        sb.append(getStartDate());
        sb.append(", createdDate=");
        sb.append(getCreatedDate());
        sb.append(", source=");
        sb.append(getSource());
        sb.append(", createdBy=");
        sb.append(getCreatedBy());
        sb.append(", itemIfpAttachedDate=");
        sb.append(getItemIfpAttachedDate());
        sb.append(", batchId=");
        sb.append(getBatchId());
        sb.append(", modifiedBy=");
        sb.append(getModifiedBy());
        sb.append(", inboundStatus=");
        sb.append(getInboundStatus());
        sb.append(", ifpDetailsSid=");
        sb.append(getIfpDetailsSid());
        sb.append(", ifpModelSid=");
        sb.append(getIfpModelSid());
        sb.append(", itemIfpAttachedStatus=");
        sb.append(getItemIfpAttachedStatus());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(49);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.IfpDetails");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>itemMasterSid</column-name><column-value><![CDATA[");
        sb.append(getItemMasterSid());
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
            "<column><column-name>recordLockStatus</column-name><column-value><![CDATA[");
        sb.append(getRecordLockStatus());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>startDate</column-name><column-value><![CDATA[");
        sb.append(getStartDate());
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
            "<column><column-name>createdBy</column-name><column-value><![CDATA[");
        sb.append(getCreatedBy());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemIfpAttachedDate</column-name><column-value><![CDATA[");
        sb.append(getItemIfpAttachedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>batchId</column-name><column-value><![CDATA[");
        sb.append(getBatchId());
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
            "<column><column-name>ifpDetailsSid</column-name><column-value><![CDATA[");
        sb.append(getIfpDetailsSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>ifpModelSid</column-name><column-value><![CDATA[");
        sb.append(getIfpModelSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemIfpAttachedStatus</column-name><column-value><![CDATA[");
        sb.append(getItemIfpAttachedStatus());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
