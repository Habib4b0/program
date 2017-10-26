package com.stpl.app.model;

import com.stpl.app.service.ClpSerializer;
import com.stpl.app.service.IvldLotMasterLocalServiceUtil;

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


public class IvldLotMasterClp extends BaseModelImpl<IvldLotMaster>
    implements IvldLotMaster {
    private String _reasonForFailure;
    private String _itemId;
    private int _ivldLotMasterSid;
    private Date _modifiedDate;
    private String _createdBy;
    private Date _createdDate;
    private String _source;
    private String _lastLotSoldDate;
    private String _lotExpirationDate;
    private String _batchId;
    private String _addChgDelIndicator;
    private String _errorField;
    private String _errorCode;
    private Date _intfInsertedDate;
    private String _modifiedBy;
    private String _lotNo;
    private String _reprocessedFlag;
    private String _lotMasterIntfid;
    private boolean _checkRecord;
    private BaseModel<?> _ivldLotMasterRemoteModel;

    public IvldLotMasterClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return IvldLotMaster.class;
    }

    @Override
    public String getModelClassName() {
        return IvldLotMaster.class.getName();
    }

    @Override
    public int getPrimaryKey() {
        return _ivldLotMasterSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setIvldLotMasterSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _ivldLotMasterSid;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Integer) primaryKeyObj).intValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("reasonForFailure", getReasonForFailure());
        attributes.put("itemId", getItemId());
        attributes.put("ivldLotMasterSid", getIvldLotMasterSid());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("source", getSource());
        attributes.put("lastLotSoldDate", getLastLotSoldDate());
        attributes.put("lotExpirationDate", getLotExpirationDate());
        attributes.put("batchId", getBatchId());
        attributes.put("addChgDelIndicator", getAddChgDelIndicator());
        attributes.put("errorField", getErrorField());
        attributes.put("errorCode", getErrorCode());
        attributes.put("intfInsertedDate", getIntfInsertedDate());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("lotNo", getLotNo());
        attributes.put("reprocessedFlag", getReprocessedFlag());
        attributes.put("lotMasterIntfid", getLotMasterIntfid());
        attributes.put("checkRecord", getCheckRecord());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        String reasonForFailure = (String) attributes.get("reasonForFailure");

        if (reasonForFailure != null) {
            setReasonForFailure(reasonForFailure);
        }

        String itemId = (String) attributes.get("itemId");

        if (itemId != null) {
            setItemId(itemId);
        }

        Integer ivldLotMasterSid = (Integer) attributes.get("ivldLotMasterSid");

        if (ivldLotMasterSid != null) {
            setIvldLotMasterSid(ivldLotMasterSid);
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

        String lastLotSoldDate = (String) attributes.get("lastLotSoldDate");

        if (lastLotSoldDate != null) {
            setLastLotSoldDate(lastLotSoldDate);
        }

        String lotExpirationDate = (String) attributes.get("lotExpirationDate");

        if (lotExpirationDate != null) {
            setLotExpirationDate(lotExpirationDate);
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

        String lotNo = (String) attributes.get("lotNo");

        if (lotNo != null) {
            setLotNo(lotNo);
        }

        String reprocessedFlag = (String) attributes.get("reprocessedFlag");

        if (reprocessedFlag != null) {
            setReprocessedFlag(reprocessedFlag);
        }

        String lotMasterIntfid = (String) attributes.get("lotMasterIntfid");

        if (lotMasterIntfid != null) {
            setLotMasterIntfid(lotMasterIntfid);
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

        if (_ivldLotMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldLotMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setReasonForFailure",
                        String.class);

                method.invoke(_ivldLotMasterRemoteModel, reasonForFailure);
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

        if (_ivldLotMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldLotMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setItemId", String.class);

                method.invoke(_ivldLotMasterRemoteModel, itemId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getIvldLotMasterSid() {
        return _ivldLotMasterSid;
    }

    @Override
    public void setIvldLotMasterSid(int ivldLotMasterSid) {
        _ivldLotMasterSid = ivldLotMasterSid;

        if (_ivldLotMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldLotMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setIvldLotMasterSid", int.class);

                method.invoke(_ivldLotMasterRemoteModel, ivldLotMasterSid);
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

        if (_ivldLotMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldLotMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedDate", Date.class);

                method.invoke(_ivldLotMasterRemoteModel, modifiedDate);
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

        if (_ivldLotMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldLotMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedBy", String.class);

                method.invoke(_ivldLotMasterRemoteModel, createdBy);
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

        if (_ivldLotMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldLotMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedDate", Date.class);

                method.invoke(_ivldLotMasterRemoteModel, createdDate);
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

        if (_ivldLotMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldLotMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setSource", String.class);

                method.invoke(_ivldLotMasterRemoteModel, source);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getLastLotSoldDate() {
        return _lastLotSoldDate;
    }

    @Override
    public void setLastLotSoldDate(String lastLotSoldDate) {
        _lastLotSoldDate = lastLotSoldDate;

        if (_ivldLotMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldLotMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setLastLotSoldDate",
                        String.class);

                method.invoke(_ivldLotMasterRemoteModel, lastLotSoldDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getLotExpirationDate() {
        return _lotExpirationDate;
    }

    @Override
    public void setLotExpirationDate(String lotExpirationDate) {
        _lotExpirationDate = lotExpirationDate;

        if (_ivldLotMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldLotMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setLotExpirationDate",
                        String.class);

                method.invoke(_ivldLotMasterRemoteModel, lotExpirationDate);
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

        if (_ivldLotMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldLotMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setBatchId", String.class);

                method.invoke(_ivldLotMasterRemoteModel, batchId);
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

        if (_ivldLotMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldLotMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setAddChgDelIndicator",
                        String.class);

                method.invoke(_ivldLotMasterRemoteModel, addChgDelIndicator);
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

        if (_ivldLotMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldLotMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setErrorField", String.class);

                method.invoke(_ivldLotMasterRemoteModel, errorField);
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

        if (_ivldLotMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldLotMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setErrorCode", String.class);

                method.invoke(_ivldLotMasterRemoteModel, errorCode);
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

        if (_ivldLotMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldLotMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setIntfInsertedDate",
                        Date.class);

                method.invoke(_ivldLotMasterRemoteModel, intfInsertedDate);
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

        if (_ivldLotMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldLotMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedBy", String.class);

                method.invoke(_ivldLotMasterRemoteModel, modifiedBy);
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

        if (_ivldLotMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldLotMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setLotNo", String.class);

                method.invoke(_ivldLotMasterRemoteModel, lotNo);
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

        if (_ivldLotMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldLotMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setReprocessedFlag",
                        String.class);

                method.invoke(_ivldLotMasterRemoteModel, reprocessedFlag);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getLotMasterIntfid() {
        return _lotMasterIntfid;
    }

    @Override
    public void setLotMasterIntfid(String lotMasterIntfid) {
        _lotMasterIntfid = lotMasterIntfid;

        if (_ivldLotMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldLotMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setLotMasterIntfid",
                        String.class);

                method.invoke(_ivldLotMasterRemoteModel, lotMasterIntfid);
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

        if (_ivldLotMasterRemoteModel != null) {
            try {
                Class<?> clazz = _ivldLotMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCheckRecord", boolean.class);

                method.invoke(_ivldLotMasterRemoteModel, checkRecord);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getIvldLotMasterRemoteModel() {
        return _ivldLotMasterRemoteModel;
    }

    public void setIvldLotMasterRemoteModel(
        BaseModel<?> ivldLotMasterRemoteModel) {
        _ivldLotMasterRemoteModel = ivldLotMasterRemoteModel;
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

        Class<?> remoteModelClass = _ivldLotMasterRemoteModel.getClass();

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

        Object returnValue = method.invoke(_ivldLotMasterRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            IvldLotMasterLocalServiceUtil.addIvldLotMaster(this);
        } else {
            IvldLotMasterLocalServiceUtil.updateIvldLotMaster(this);
        }
    }

    @Override
    public IvldLotMaster toEscapedModel() {
        return (IvldLotMaster) ProxyUtil.newProxyInstance(IvldLotMaster.class.getClassLoader(),
            new Class[] { IvldLotMaster.class }, new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        IvldLotMasterClp clone = new IvldLotMasterClp();

        clone.setReasonForFailure(getReasonForFailure());
        clone.setItemId(getItemId());
        clone.setIvldLotMasterSid(getIvldLotMasterSid());
        clone.setModifiedDate(getModifiedDate());
        clone.setCreatedBy(getCreatedBy());
        clone.setCreatedDate(getCreatedDate());
        clone.setSource(getSource());
        clone.setLastLotSoldDate(getLastLotSoldDate());
        clone.setLotExpirationDate(getLotExpirationDate());
        clone.setBatchId(getBatchId());
        clone.setAddChgDelIndicator(getAddChgDelIndicator());
        clone.setErrorField(getErrorField());
        clone.setErrorCode(getErrorCode());
        clone.setIntfInsertedDate(getIntfInsertedDate());
        clone.setModifiedBy(getModifiedBy());
        clone.setLotNo(getLotNo());
        clone.setReprocessedFlag(getReprocessedFlag());
        clone.setLotMasterIntfid(getLotMasterIntfid());
        clone.setCheckRecord(getCheckRecord());

        return clone;
    }

    @Override
    public int compareTo(IvldLotMaster ivldLotMaster) {
        int primaryKey = ivldLotMaster.getPrimaryKey();

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

        if (!(obj instanceof IvldLotMasterClp)) {
            return false;
        }

        IvldLotMasterClp ivldLotMaster = (IvldLotMasterClp) obj;

        int primaryKey = ivldLotMaster.getPrimaryKey();

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

        sb.append("{reasonForFailure=");
        sb.append(getReasonForFailure());
        sb.append(", itemId=");
        sb.append(getItemId());
        sb.append(", ivldLotMasterSid=");
        sb.append(getIvldLotMasterSid());
        sb.append(", modifiedDate=");
        sb.append(getModifiedDate());
        sb.append(", createdBy=");
        sb.append(getCreatedBy());
        sb.append(", createdDate=");
        sb.append(getCreatedDate());
        sb.append(", source=");
        sb.append(getSource());
        sb.append(", lastLotSoldDate=");
        sb.append(getLastLotSoldDate());
        sb.append(", lotExpirationDate=");
        sb.append(getLotExpirationDate());
        sb.append(", batchId=");
        sb.append(getBatchId());
        sb.append(", addChgDelIndicator=");
        sb.append(getAddChgDelIndicator());
        sb.append(", errorField=");
        sb.append(getErrorField());
        sb.append(", errorCode=");
        sb.append(getErrorCode());
        sb.append(", intfInsertedDate=");
        sb.append(getIntfInsertedDate());
        sb.append(", modifiedBy=");
        sb.append(getModifiedBy());
        sb.append(", lotNo=");
        sb.append(getLotNo());
        sb.append(", reprocessedFlag=");
        sb.append(getReprocessedFlag());
        sb.append(", lotMasterIntfid=");
        sb.append(getLotMasterIntfid());
        sb.append(", checkRecord=");
        sb.append(getCheckRecord());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(61);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.IvldLotMaster");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>reasonForFailure</column-name><column-value><![CDATA[");
        sb.append(getReasonForFailure());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemId</column-name><column-value><![CDATA[");
        sb.append(getItemId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>ivldLotMasterSid</column-name><column-value><![CDATA[");
        sb.append(getIvldLotMasterSid());
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
            "<column><column-name>lastLotSoldDate</column-name><column-value><![CDATA[");
        sb.append(getLastLotSoldDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>lotExpirationDate</column-name><column-value><![CDATA[");
        sb.append(getLotExpirationDate());
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
            "<column><column-name>lotNo</column-name><column-value><![CDATA[");
        sb.append(getLotNo());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>reprocessedFlag</column-name><column-value><![CDATA[");
        sb.append(getReprocessedFlag());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>lotMasterIntfid</column-name><column-value><![CDATA[");
        sb.append(getLotMasterIntfid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>checkRecord</column-name><column-value><![CDATA[");
        sb.append(getCheckRecord());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
