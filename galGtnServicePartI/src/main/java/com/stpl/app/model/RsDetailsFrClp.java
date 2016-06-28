package com.stpl.app.model;

import com.stpl.app.service.ClpSerializer;
import com.stpl.app.service.RsDetailsFrLocalServiceUtil;

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


public class RsDetailsFrClp extends BaseModelImpl<RsDetailsFr>
    implements RsDetailsFr {
    private boolean _recordLockStatus;
    private Date _createdDate;
    private int _createdBy;
    private String _source;
    private String _formulaMethodId;
    private String _batchId;
    private int _modifiedBy;
    private String _inboundStatus;
    private int _formulaId;
    private int _itemMasterSid;
    private int _rsDetailsSid;
    private Date _modifiedDate;
    private int _rsDetailsFrSid;
    private BaseModel<?> _rsDetailsFrRemoteModel;

    public RsDetailsFrClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return RsDetailsFr.class;
    }

    @Override
    public String getModelClassName() {
        return RsDetailsFr.class.getName();
    }

    @Override
    public int getPrimaryKey() {
        return _rsDetailsFrSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setRsDetailsFrSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _rsDetailsFrSid;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Integer) primaryKeyObj).intValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("recordLockStatus", getRecordLockStatus());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("source", getSource());
        attributes.put("formulaMethodId", getFormulaMethodId());
        attributes.put("batchId", getBatchId());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("inboundStatus", getInboundStatus());
        attributes.put("formulaId", getFormulaId());
        attributes.put("itemMasterSid", getItemMasterSid());
        attributes.put("rsDetailsSid", getRsDetailsSid());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("rsDetailsFrSid", getRsDetailsFrSid());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
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

        String formulaMethodId = (String) attributes.get("formulaMethodId");

        if (formulaMethodId != null) {
            setFormulaMethodId(formulaMethodId);
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

        Integer formulaId = (Integer) attributes.get("formulaId");

        if (formulaId != null) {
            setFormulaId(formulaId);
        }

        Integer itemMasterSid = (Integer) attributes.get("itemMasterSid");

        if (itemMasterSid != null) {
            setItemMasterSid(itemMasterSid);
        }

        Integer rsDetailsSid = (Integer) attributes.get("rsDetailsSid");

        if (rsDetailsSid != null) {
            setRsDetailsSid(rsDetailsSid);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        Integer rsDetailsFrSid = (Integer) attributes.get("rsDetailsFrSid");

        if (rsDetailsFrSid != null) {
            setRsDetailsFrSid(rsDetailsFrSid);
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

        if (_rsDetailsFrRemoteModel != null) {
            try {
                Class<?> clazz = _rsDetailsFrRemoteModel.getClass();

                Method method = clazz.getMethod("setRecordLockStatus",
                        boolean.class);

                method.invoke(_rsDetailsFrRemoteModel, recordLockStatus);
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

        if (_rsDetailsFrRemoteModel != null) {
            try {
                Class<?> clazz = _rsDetailsFrRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedDate", Date.class);

                method.invoke(_rsDetailsFrRemoteModel, createdDate);
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

        if (_rsDetailsFrRemoteModel != null) {
            try {
                Class<?> clazz = _rsDetailsFrRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedBy", int.class);

                method.invoke(_rsDetailsFrRemoteModel, createdBy);
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

        if (_rsDetailsFrRemoteModel != null) {
            try {
                Class<?> clazz = _rsDetailsFrRemoteModel.getClass();

                Method method = clazz.getMethod("setSource", String.class);

                method.invoke(_rsDetailsFrRemoteModel, source);
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

        if (_rsDetailsFrRemoteModel != null) {
            try {
                Class<?> clazz = _rsDetailsFrRemoteModel.getClass();

                Method method = clazz.getMethod("setFormulaMethodId",
                        String.class);

                method.invoke(_rsDetailsFrRemoteModel, formulaMethodId);
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

        if (_rsDetailsFrRemoteModel != null) {
            try {
                Class<?> clazz = _rsDetailsFrRemoteModel.getClass();

                Method method = clazz.getMethod("setBatchId", String.class);

                method.invoke(_rsDetailsFrRemoteModel, batchId);
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

        if (_rsDetailsFrRemoteModel != null) {
            try {
                Class<?> clazz = _rsDetailsFrRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedBy", int.class);

                method.invoke(_rsDetailsFrRemoteModel, modifiedBy);
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

        if (_rsDetailsFrRemoteModel != null) {
            try {
                Class<?> clazz = _rsDetailsFrRemoteModel.getClass();

                Method method = clazz.getMethod("setInboundStatus", String.class);

                method.invoke(_rsDetailsFrRemoteModel, inboundStatus);
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

        if (_rsDetailsFrRemoteModel != null) {
            try {
                Class<?> clazz = _rsDetailsFrRemoteModel.getClass();

                Method method = clazz.getMethod("setFormulaId", int.class);

                method.invoke(_rsDetailsFrRemoteModel, formulaId);
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

        if (_rsDetailsFrRemoteModel != null) {
            try {
                Class<?> clazz = _rsDetailsFrRemoteModel.getClass();

                Method method = clazz.getMethod("setItemMasterSid", int.class);

                method.invoke(_rsDetailsFrRemoteModel, itemMasterSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getRsDetailsSid() {
        return _rsDetailsSid;
    }

    @Override
    public void setRsDetailsSid(int rsDetailsSid) {
        _rsDetailsSid = rsDetailsSid;

        if (_rsDetailsFrRemoteModel != null) {
            try {
                Class<?> clazz = _rsDetailsFrRemoteModel.getClass();

                Method method = clazz.getMethod("setRsDetailsSid", int.class);

                method.invoke(_rsDetailsFrRemoteModel, rsDetailsSid);
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

        if (_rsDetailsFrRemoteModel != null) {
            try {
                Class<?> clazz = _rsDetailsFrRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedDate", Date.class);

                method.invoke(_rsDetailsFrRemoteModel, modifiedDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getRsDetailsFrSid() {
        return _rsDetailsFrSid;
    }

    @Override
    public void setRsDetailsFrSid(int rsDetailsFrSid) {
        _rsDetailsFrSid = rsDetailsFrSid;

        if (_rsDetailsFrRemoteModel != null) {
            try {
                Class<?> clazz = _rsDetailsFrRemoteModel.getClass();

                Method method = clazz.getMethod("setRsDetailsFrSid", int.class);

                method.invoke(_rsDetailsFrRemoteModel, rsDetailsFrSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getRsDetailsFrRemoteModel() {
        return _rsDetailsFrRemoteModel;
    }

    public void setRsDetailsFrRemoteModel(BaseModel<?> rsDetailsFrRemoteModel) {
        _rsDetailsFrRemoteModel = rsDetailsFrRemoteModel;
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

        Class<?> remoteModelClass = _rsDetailsFrRemoteModel.getClass();

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

        Object returnValue = method.invoke(_rsDetailsFrRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            RsDetailsFrLocalServiceUtil.addRsDetailsFr(this);
        } else {
            RsDetailsFrLocalServiceUtil.updateRsDetailsFr(this);
        }
    }

    @Override
    public RsDetailsFr toEscapedModel() {
        return (RsDetailsFr) ProxyUtil.newProxyInstance(RsDetailsFr.class.getClassLoader(),
            new Class[] { RsDetailsFr.class }, new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        RsDetailsFrClp clone = new RsDetailsFrClp();

        clone.setRecordLockStatus(getRecordLockStatus());
        clone.setCreatedDate(getCreatedDate());
        clone.setCreatedBy(getCreatedBy());
        clone.setSource(getSource());
        clone.setFormulaMethodId(getFormulaMethodId());
        clone.setBatchId(getBatchId());
        clone.setModifiedBy(getModifiedBy());
        clone.setInboundStatus(getInboundStatus());
        clone.setFormulaId(getFormulaId());
        clone.setItemMasterSid(getItemMasterSid());
        clone.setRsDetailsSid(getRsDetailsSid());
        clone.setModifiedDate(getModifiedDate());
        clone.setRsDetailsFrSid(getRsDetailsFrSid());

        return clone;
    }

    @Override
    public int compareTo(RsDetailsFr rsDetailsFr) {
        int primaryKey = rsDetailsFr.getPrimaryKey();

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

        if (!(obj instanceof RsDetailsFrClp)) {
            return false;
        }

        RsDetailsFrClp rsDetailsFr = (RsDetailsFrClp) obj;

        int primaryKey = rsDetailsFr.getPrimaryKey();

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
        StringBundler sb = new StringBundler(27);

        sb.append("{recordLockStatus=");
        sb.append(getRecordLockStatus());
        sb.append(", createdDate=");
        sb.append(getCreatedDate());
        sb.append(", createdBy=");
        sb.append(getCreatedBy());
        sb.append(", source=");
        sb.append(getSource());
        sb.append(", formulaMethodId=");
        sb.append(getFormulaMethodId());
        sb.append(", batchId=");
        sb.append(getBatchId());
        sb.append(", modifiedBy=");
        sb.append(getModifiedBy());
        sb.append(", inboundStatus=");
        sb.append(getInboundStatus());
        sb.append(", formulaId=");
        sb.append(getFormulaId());
        sb.append(", itemMasterSid=");
        sb.append(getItemMasterSid());
        sb.append(", rsDetailsSid=");
        sb.append(getRsDetailsSid());
        sb.append(", modifiedDate=");
        sb.append(getModifiedDate());
        sb.append(", rsDetailsFrSid=");
        sb.append(getRsDetailsFrSid());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(43);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.RsDetailsFr");
        sb.append("</model-name>");

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
            "<column><column-name>formulaMethodId</column-name><column-value><![CDATA[");
        sb.append(getFormulaMethodId());
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
            "<column><column-name>formulaId</column-name><column-value><![CDATA[");
        sb.append(getFormulaId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemMasterSid</column-name><column-value><![CDATA[");
        sb.append(getItemMasterSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>rsDetailsSid</column-name><column-value><![CDATA[");
        sb.append(getRsDetailsSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
        sb.append(getModifiedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>rsDetailsFrSid</column-name><column-value><![CDATA[");
        sb.append(getRsDetailsFrSid());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
