package com.stpl.app.model;

import com.stpl.app.service.ClpSerializer;
import com.stpl.app.service.RsContractDetailsFrLocalServiceUtil;

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


public class RsContractDetailsFrClp extends BaseModelImpl<RsContractDetailsFr>
    implements RsContractDetailsFr {
    private boolean _recordLockStatus;
    private Date _createdDate;
    private int _createdBy;
    private String _source;
    private String _formulaMethodId;
    private int _itemMasterSid;
    private String _batchId;
    private int _rsContractDetailsFrSid;
    private int _modifiedBy;
    private String _inboundStatus;
    private int _formulaId;
    private Date _modifiedDate;
    private int _rsContractDetailsSid;
    private BaseModel<?> _rsContractDetailsFrRemoteModel;

    public RsContractDetailsFrClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return RsContractDetailsFr.class;
    }

    @Override
    public String getModelClassName() {
        return RsContractDetailsFr.class.getName();
    }

    @Override
    public int getPrimaryKey() {
        return _rsContractDetailsFrSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setRsContractDetailsFrSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _rsContractDetailsFrSid;
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
        attributes.put("itemMasterSid", getItemMasterSid());
        attributes.put("batchId", getBatchId());
        attributes.put("rsContractDetailsFrSid", getRsContractDetailsFrSid());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("inboundStatus", getInboundStatus());
        attributes.put("formulaId", getFormulaId());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("rsContractDetailsSid", getRsContractDetailsSid());

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

        Integer itemMasterSid = (Integer) attributes.get("itemMasterSid");

        if (itemMasterSid != null) {
            setItemMasterSid(itemMasterSid);
        }

        String batchId = (String) attributes.get("batchId");

        if (batchId != null) {
            setBatchId(batchId);
        }

        Integer rsContractDetailsFrSid = (Integer) attributes.get(
                "rsContractDetailsFrSid");

        if (rsContractDetailsFrSid != null) {
            setRsContractDetailsFrSid(rsContractDetailsFrSid);
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

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        Integer rsContractDetailsSid = (Integer) attributes.get(
                "rsContractDetailsSid");

        if (rsContractDetailsSid != null) {
            setRsContractDetailsSid(rsContractDetailsSid);
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

        if (_rsContractDetailsFrRemoteModel != null) {
            try {
                Class<?> clazz = _rsContractDetailsFrRemoteModel.getClass();

                Method method = clazz.getMethod("setRecordLockStatus",
                        boolean.class);

                method.invoke(_rsContractDetailsFrRemoteModel, recordLockStatus);
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

        if (_rsContractDetailsFrRemoteModel != null) {
            try {
                Class<?> clazz = _rsContractDetailsFrRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedDate", Date.class);

                method.invoke(_rsContractDetailsFrRemoteModel, createdDate);
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

        if (_rsContractDetailsFrRemoteModel != null) {
            try {
                Class<?> clazz = _rsContractDetailsFrRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedBy", int.class);

                method.invoke(_rsContractDetailsFrRemoteModel, createdBy);
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

        if (_rsContractDetailsFrRemoteModel != null) {
            try {
                Class<?> clazz = _rsContractDetailsFrRemoteModel.getClass();

                Method method = clazz.getMethod("setSource", String.class);

                method.invoke(_rsContractDetailsFrRemoteModel, source);
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

        if (_rsContractDetailsFrRemoteModel != null) {
            try {
                Class<?> clazz = _rsContractDetailsFrRemoteModel.getClass();

                Method method = clazz.getMethod("setFormulaMethodId",
                        String.class);

                method.invoke(_rsContractDetailsFrRemoteModel, formulaMethodId);
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

        if (_rsContractDetailsFrRemoteModel != null) {
            try {
                Class<?> clazz = _rsContractDetailsFrRemoteModel.getClass();

                Method method = clazz.getMethod("setItemMasterSid", int.class);

                method.invoke(_rsContractDetailsFrRemoteModel, itemMasterSid);
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

        if (_rsContractDetailsFrRemoteModel != null) {
            try {
                Class<?> clazz = _rsContractDetailsFrRemoteModel.getClass();

                Method method = clazz.getMethod("setBatchId", String.class);

                method.invoke(_rsContractDetailsFrRemoteModel, batchId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getRsContractDetailsFrSid() {
        return _rsContractDetailsFrSid;
    }

    @Override
    public void setRsContractDetailsFrSid(int rsContractDetailsFrSid) {
        _rsContractDetailsFrSid = rsContractDetailsFrSid;

        if (_rsContractDetailsFrRemoteModel != null) {
            try {
                Class<?> clazz = _rsContractDetailsFrRemoteModel.getClass();

                Method method = clazz.getMethod("setRsContractDetailsFrSid",
                        int.class);

                method.invoke(_rsContractDetailsFrRemoteModel,
                    rsContractDetailsFrSid);
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

        if (_rsContractDetailsFrRemoteModel != null) {
            try {
                Class<?> clazz = _rsContractDetailsFrRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedBy", int.class);

                method.invoke(_rsContractDetailsFrRemoteModel, modifiedBy);
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

        if (_rsContractDetailsFrRemoteModel != null) {
            try {
                Class<?> clazz = _rsContractDetailsFrRemoteModel.getClass();

                Method method = clazz.getMethod("setInboundStatus", String.class);

                method.invoke(_rsContractDetailsFrRemoteModel, inboundStatus);
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

        if (_rsContractDetailsFrRemoteModel != null) {
            try {
                Class<?> clazz = _rsContractDetailsFrRemoteModel.getClass();

                Method method = clazz.getMethod("setFormulaId", int.class);

                method.invoke(_rsContractDetailsFrRemoteModel, formulaId);
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

        if (_rsContractDetailsFrRemoteModel != null) {
            try {
                Class<?> clazz = _rsContractDetailsFrRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedDate", Date.class);

                method.invoke(_rsContractDetailsFrRemoteModel, modifiedDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getRsContractDetailsSid() {
        return _rsContractDetailsSid;
    }

    @Override
    public void setRsContractDetailsSid(int rsContractDetailsSid) {
        _rsContractDetailsSid = rsContractDetailsSid;

        if (_rsContractDetailsFrRemoteModel != null) {
            try {
                Class<?> clazz = _rsContractDetailsFrRemoteModel.getClass();

                Method method = clazz.getMethod("setRsContractDetailsSid",
                        int.class);

                method.invoke(_rsContractDetailsFrRemoteModel,
                    rsContractDetailsSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getRsContractDetailsFrRemoteModel() {
        return _rsContractDetailsFrRemoteModel;
    }

    public void setRsContractDetailsFrRemoteModel(
        BaseModel<?> rsContractDetailsFrRemoteModel) {
        _rsContractDetailsFrRemoteModel = rsContractDetailsFrRemoteModel;
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

        Class<?> remoteModelClass = _rsContractDetailsFrRemoteModel.getClass();

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

        Object returnValue = method.invoke(_rsContractDetailsFrRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            RsContractDetailsFrLocalServiceUtil.addRsContractDetailsFr(this);
        } else {
            RsContractDetailsFrLocalServiceUtil.updateRsContractDetailsFr(this);
        }
    }

    @Override
    public RsContractDetailsFr toEscapedModel() {
        return (RsContractDetailsFr) ProxyUtil.newProxyInstance(RsContractDetailsFr.class.getClassLoader(),
            new Class[] { RsContractDetailsFr.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        RsContractDetailsFrClp clone = new RsContractDetailsFrClp();

        clone.setRecordLockStatus(getRecordLockStatus());
        clone.setCreatedDate(getCreatedDate());
        clone.setCreatedBy(getCreatedBy());
        clone.setSource(getSource());
        clone.setFormulaMethodId(getFormulaMethodId());
        clone.setItemMasterSid(getItemMasterSid());
        clone.setBatchId(getBatchId());
        clone.setRsContractDetailsFrSid(getRsContractDetailsFrSid());
        clone.setModifiedBy(getModifiedBy());
        clone.setInboundStatus(getInboundStatus());
        clone.setFormulaId(getFormulaId());
        clone.setModifiedDate(getModifiedDate());
        clone.setRsContractDetailsSid(getRsContractDetailsSid());

        return clone;
    }

    @Override
    public int compareTo(RsContractDetailsFr rsContractDetailsFr) {
        int primaryKey = rsContractDetailsFr.getPrimaryKey();

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

        if (!(obj instanceof RsContractDetailsFrClp)) {
            return false;
        }

        RsContractDetailsFrClp rsContractDetailsFr = (RsContractDetailsFrClp) obj;

        int primaryKey = rsContractDetailsFr.getPrimaryKey();

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
        sb.append(", itemMasterSid=");
        sb.append(getItemMasterSid());
        sb.append(", batchId=");
        sb.append(getBatchId());
        sb.append(", rsContractDetailsFrSid=");
        sb.append(getRsContractDetailsFrSid());
        sb.append(", modifiedBy=");
        sb.append(getModifiedBy());
        sb.append(", inboundStatus=");
        sb.append(getInboundStatus());
        sb.append(", formulaId=");
        sb.append(getFormulaId());
        sb.append(", modifiedDate=");
        sb.append(getModifiedDate());
        sb.append(", rsContractDetailsSid=");
        sb.append(getRsContractDetailsSid());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(43);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.RsContractDetailsFr");
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
            "<column><column-name>itemMasterSid</column-name><column-value><![CDATA[");
        sb.append(getItemMasterSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>batchId</column-name><column-value><![CDATA[");
        sb.append(getBatchId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>rsContractDetailsFrSid</column-name><column-value><![CDATA[");
        sb.append(getRsContractDetailsFrSid());
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
            "<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
        sb.append(getModifiedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>rsContractDetailsSid</column-name><column-value><![CDATA[");
        sb.append(getRsContractDetailsSid());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
