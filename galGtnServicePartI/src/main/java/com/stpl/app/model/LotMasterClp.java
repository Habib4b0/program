package com.stpl.app.model;

import com.stpl.app.service.ClpSerializer;
import com.stpl.app.service.LotMasterLocalServiceUtil;

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


public class LotMasterClp extends BaseModelImpl<LotMaster> implements LotMaster {
    private int _createdBy;
    private int _modifiedBy;
    private Date _createdDate;
    private String _itemId;
    private String _batchId;
    private Date _modifiedDate;
    private boolean _recordLockStatus;
    private Date _lastLotSoldDate;
    private Date _lotExpirationDate;
    private String _source;
    private int _lotMasterSid;
    private String _lotNo;
    private String _inboundStatus;
    private BaseModel<?> _lotMasterRemoteModel;

    public LotMasterClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return LotMaster.class;
    }

    @Override
    public String getModelClassName() {
        return LotMaster.class.getName();
    }

    @Override
    public int getPrimaryKey() {
        return _lotMasterSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setLotMasterSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _lotMasterSid;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Integer) primaryKeyObj).intValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("createdBy", getCreatedBy());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("itemId", getItemId());
        attributes.put("batchId", getBatchId());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("recordLockStatus", getRecordLockStatus());
        attributes.put("lastLotSoldDate", getLastLotSoldDate());
        attributes.put("lotExpirationDate", getLotExpirationDate());
        attributes.put("source", getSource());
        attributes.put("lotMasterSid", getLotMasterSid());
        attributes.put("lotNo", getLotNo());
        attributes.put("inboundStatus", getInboundStatus());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
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

        String itemId = (String) attributes.get("itemId");

        if (itemId != null) {
            setItemId(itemId);
        }

        String batchId = (String) attributes.get("batchId");

        if (batchId != null) {
            setBatchId(batchId);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        Boolean recordLockStatus = (Boolean) attributes.get("recordLockStatus");

        if (recordLockStatus != null) {
            setRecordLockStatus(recordLockStatus);
        }

        Date lastLotSoldDate = (Date) attributes.get("lastLotSoldDate");

        if (lastLotSoldDate != null) {
            setLastLotSoldDate(lastLotSoldDate);
        }

        Date lotExpirationDate = (Date) attributes.get("lotExpirationDate");

        if (lotExpirationDate != null) {
            setLotExpirationDate(lotExpirationDate);
        }

        String source = (String) attributes.get("source");

        if (source != null) {
            setSource(source);
        }

        Integer lotMasterSid = (Integer) attributes.get("lotMasterSid");

        if (lotMasterSid != null) {
            setLotMasterSid(lotMasterSid);
        }

        String lotNo = (String) attributes.get("lotNo");

        if (lotNo != null) {
            setLotNo(lotNo);
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

        if (_lotMasterRemoteModel != null) {
            try {
                Class<?> clazz = _lotMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedBy", int.class);

                method.invoke(_lotMasterRemoteModel, createdBy);
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

        if (_lotMasterRemoteModel != null) {
            try {
                Class<?> clazz = _lotMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedBy", int.class);

                method.invoke(_lotMasterRemoteModel, modifiedBy);
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

        if (_lotMasterRemoteModel != null) {
            try {
                Class<?> clazz = _lotMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedDate", Date.class);

                method.invoke(_lotMasterRemoteModel, createdDate);
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

        if (_lotMasterRemoteModel != null) {
            try {
                Class<?> clazz = _lotMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setItemId", String.class);

                method.invoke(_lotMasterRemoteModel, itemId);
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

        if (_lotMasterRemoteModel != null) {
            try {
                Class<?> clazz = _lotMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setBatchId", String.class);

                method.invoke(_lotMasterRemoteModel, batchId);
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

        if (_lotMasterRemoteModel != null) {
            try {
                Class<?> clazz = _lotMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedDate", Date.class);

                method.invoke(_lotMasterRemoteModel, modifiedDate);
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

        if (_lotMasterRemoteModel != null) {
            try {
                Class<?> clazz = _lotMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setRecordLockStatus",
                        boolean.class);

                method.invoke(_lotMasterRemoteModel, recordLockStatus);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getLastLotSoldDate() {
        return _lastLotSoldDate;
    }

    @Override
    public void setLastLotSoldDate(Date lastLotSoldDate) {
        _lastLotSoldDate = lastLotSoldDate;

        if (_lotMasterRemoteModel != null) {
            try {
                Class<?> clazz = _lotMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setLastLotSoldDate", Date.class);

                method.invoke(_lotMasterRemoteModel, lastLotSoldDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getLotExpirationDate() {
        return _lotExpirationDate;
    }

    @Override
    public void setLotExpirationDate(Date lotExpirationDate) {
        _lotExpirationDate = lotExpirationDate;

        if (_lotMasterRemoteModel != null) {
            try {
                Class<?> clazz = _lotMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setLotExpirationDate",
                        Date.class);

                method.invoke(_lotMasterRemoteModel, lotExpirationDate);
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

        if (_lotMasterRemoteModel != null) {
            try {
                Class<?> clazz = _lotMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setSource", String.class);

                method.invoke(_lotMasterRemoteModel, source);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getLotMasterSid() {
        return _lotMasterSid;
    }

    @Override
    public void setLotMasterSid(int lotMasterSid) {
        _lotMasterSid = lotMasterSid;

        if (_lotMasterRemoteModel != null) {
            try {
                Class<?> clazz = _lotMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setLotMasterSid", int.class);

                method.invoke(_lotMasterRemoteModel, lotMasterSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getLotNo() {
        return _lotNo;
    }

    @Override
    public void setLotNo(String lotNo) {
        _lotNo = lotNo;

        if (_lotMasterRemoteModel != null) {
            try {
                Class<?> clazz = _lotMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setLotNo", String.class);

                method.invoke(_lotMasterRemoteModel, lotNo);
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

        if (_lotMasterRemoteModel != null) {
            try {
                Class<?> clazz = _lotMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setInboundStatus", String.class);

                method.invoke(_lotMasterRemoteModel, inboundStatus);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getLotMasterRemoteModel() {
        return _lotMasterRemoteModel;
    }

    public void setLotMasterRemoteModel(BaseModel<?> lotMasterRemoteModel) {
        _lotMasterRemoteModel = lotMasterRemoteModel;
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

        Class<?> remoteModelClass = _lotMasterRemoteModel.getClass();

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

        Object returnValue = method.invoke(_lotMasterRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            LotMasterLocalServiceUtil.addLotMaster(this);
        } else {
            LotMasterLocalServiceUtil.updateLotMaster(this);
        }
    }

    @Override
    public LotMaster toEscapedModel() {
        return (LotMaster) ProxyUtil.newProxyInstance(LotMaster.class.getClassLoader(),
            new Class[] { LotMaster.class }, new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        LotMasterClp clone = new LotMasterClp();

        clone.setCreatedBy(getCreatedBy());
        clone.setModifiedBy(getModifiedBy());
        clone.setCreatedDate(getCreatedDate());
        clone.setItemId(getItemId());
        clone.setBatchId(getBatchId());
        clone.setModifiedDate(getModifiedDate());
        clone.setRecordLockStatus(getRecordLockStatus());
        clone.setLastLotSoldDate(getLastLotSoldDate());
        clone.setLotExpirationDate(getLotExpirationDate());
        clone.setSource(getSource());
        clone.setLotMasterSid(getLotMasterSid());
        clone.setLotNo(getLotNo());
        clone.setInboundStatus(getInboundStatus());

        return clone;
    }

    @Override
    public int compareTo(LotMaster lotMaster) {
        int primaryKey = lotMaster.getPrimaryKey();

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

        if (!(obj instanceof LotMasterClp)) {
            return false;
        }

        LotMasterClp lotMaster = (LotMasterClp) obj;

        int primaryKey = lotMaster.getPrimaryKey();

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

        sb.append("{createdBy=");
        sb.append(getCreatedBy());
        sb.append(", modifiedBy=");
        sb.append(getModifiedBy());
        sb.append(", createdDate=");
        sb.append(getCreatedDate());
        sb.append(", itemId=");
        sb.append(getItemId());
        sb.append(", batchId=");
        sb.append(getBatchId());
        sb.append(", modifiedDate=");
        sb.append(getModifiedDate());
        sb.append(", recordLockStatus=");
        sb.append(getRecordLockStatus());
        sb.append(", lastLotSoldDate=");
        sb.append(getLastLotSoldDate());
        sb.append(", lotExpirationDate=");
        sb.append(getLotExpirationDate());
        sb.append(", source=");
        sb.append(getSource());
        sb.append(", lotMasterSid=");
        sb.append(getLotMasterSid());
        sb.append(", lotNo=");
        sb.append(getLotNo());
        sb.append(", inboundStatus=");
        sb.append(getInboundStatus());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(43);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.LotMaster");
        sb.append("</model-name>");

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
            "<column><column-name>itemId</column-name><column-value><![CDATA[");
        sb.append(getItemId());
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
            "<column><column-name>recordLockStatus</column-name><column-value><![CDATA[");
        sb.append(getRecordLockStatus());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>lastLotSoldDate</column-name><column-value><![CDATA[");
        sb.append(getLastLotSoldDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>lotExpirationDate</column-name><column-value><![CDATA[");
        sb.append(getLotExpirationDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>source</column-name><column-value><![CDATA[");
        sb.append(getSource());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>lotMasterSid</column-name><column-value><![CDATA[");
        sb.append(getLotMasterSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>lotNo</column-name><column-value><![CDATA[");
        sb.append(getLotNo());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>inboundStatus</column-name><column-value><![CDATA[");
        sb.append(getInboundStatus());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
