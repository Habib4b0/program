package com.stpl.app.model;

import com.stpl.app.service.ClpSerializer;
import com.stpl.app.service.ImtdRsDetailsFrLocalServiceUtil;

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


public class ImtdRsDetailsFrClp extends BaseModelImpl<ImtdRsDetailsFr>
    implements ImtdRsDetailsFr {
    private String _formulaMethodId;
    private int _itemMasterSid;
    private int _imtdRsDetailsSid;
    private Date _modifiedDate;
    private int _rsDetailsFrSid;
    private boolean _recordLockStatus;
    private Date _createdDate;
    private int _createdBy;
    private String _source;
    private int _imtdRsDetailsFrSid;
    private String _batchId;
    private Date _imtdCreatedDate;
    private String _sessionId;
    private String _usersId;
    private String _operation;
    private int _modifiedBy;
    private int _rsDetailsSid;
    private int _formulaId;
    private String _inboundStatus;
    private BaseModel<?> _imtdRsDetailsFrRemoteModel;

    public ImtdRsDetailsFrClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return ImtdRsDetailsFr.class;
    }

    @Override
    public String getModelClassName() {
        return ImtdRsDetailsFr.class.getName();
    }

    @Override
    public int getPrimaryKey() {
        return _imtdRsDetailsFrSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setImtdRsDetailsFrSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _imtdRsDetailsFrSid;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Integer) primaryKeyObj).intValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("formulaMethodId", getFormulaMethodId());
        attributes.put("itemMasterSid", getItemMasterSid());
        attributes.put("imtdRsDetailsSid", getImtdRsDetailsSid());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("rsDetailsFrSid", getRsDetailsFrSid());
        attributes.put("recordLockStatus", getRecordLockStatus());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("source", getSource());
        attributes.put("imtdRsDetailsFrSid", getImtdRsDetailsFrSid());
        attributes.put("batchId", getBatchId());
        attributes.put("imtdCreatedDate", getImtdCreatedDate());
        attributes.put("sessionId", getSessionId());
        attributes.put("usersId", getUsersId());
        attributes.put("operation", getOperation());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("rsDetailsSid", getRsDetailsSid());
        attributes.put("formulaId", getFormulaId());
        attributes.put("inboundStatus", getInboundStatus());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        String formulaMethodId = (String) attributes.get("formulaMethodId");

        if (formulaMethodId != null) {
            setFormulaMethodId(formulaMethodId);
        }

        Integer itemMasterSid = (Integer) attributes.get("itemMasterSid");

        if (itemMasterSid != null) {
            setItemMasterSid(itemMasterSid);
        }

        Integer imtdRsDetailsSid = (Integer) attributes.get("imtdRsDetailsSid");

        if (imtdRsDetailsSid != null) {
            setImtdRsDetailsSid(imtdRsDetailsSid);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        Integer rsDetailsFrSid = (Integer) attributes.get("rsDetailsFrSid");

        if (rsDetailsFrSid != null) {
            setRsDetailsFrSid(rsDetailsFrSid);
        }

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

        Integer imtdRsDetailsFrSid = (Integer) attributes.get(
                "imtdRsDetailsFrSid");

        if (imtdRsDetailsFrSid != null) {
            setImtdRsDetailsFrSid(imtdRsDetailsFrSid);
        }

        String batchId = (String) attributes.get("batchId");

        if (batchId != null) {
            setBatchId(batchId);
        }

        Date imtdCreatedDate = (Date) attributes.get("imtdCreatedDate");

        if (imtdCreatedDate != null) {
            setImtdCreatedDate(imtdCreatedDate);
        }

        String sessionId = (String) attributes.get("sessionId");

        if (sessionId != null) {
            setSessionId(sessionId);
        }

        String usersId = (String) attributes.get("usersId");

        if (usersId != null) {
            setUsersId(usersId);
        }

        String operation = (String) attributes.get("operation");

        if (operation != null) {
            setOperation(operation);
        }

        Integer modifiedBy = (Integer) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        Integer rsDetailsSid = (Integer) attributes.get("rsDetailsSid");

        if (rsDetailsSid != null) {
            setRsDetailsSid(rsDetailsSid);
        }

        Integer formulaId = (Integer) attributes.get("formulaId");

        if (formulaId != null) {
            setFormulaId(formulaId);
        }

        String inboundStatus = (String) attributes.get("inboundStatus");

        if (inboundStatus != null) {
            setInboundStatus(inboundStatus);
        }
    }

    @Override
    public String getFormulaMethodId() {
        return _formulaMethodId;
    }

    @Override
    public void setFormulaMethodId(String formulaMethodId) {
        _formulaMethodId = formulaMethodId;

        if (_imtdRsDetailsFrRemoteModel != null) {
            try {
                Class<?> clazz = _imtdRsDetailsFrRemoteModel.getClass();

                Method method = clazz.getMethod("setFormulaMethodId",
                        String.class);

                method.invoke(_imtdRsDetailsFrRemoteModel, formulaMethodId);
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

        if (_imtdRsDetailsFrRemoteModel != null) {
            try {
                Class<?> clazz = _imtdRsDetailsFrRemoteModel.getClass();

                Method method = clazz.getMethod("setItemMasterSid", int.class);

                method.invoke(_imtdRsDetailsFrRemoteModel, itemMasterSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getImtdRsDetailsSid() {
        return _imtdRsDetailsSid;
    }

    @Override
    public void setImtdRsDetailsSid(int imtdRsDetailsSid) {
        _imtdRsDetailsSid = imtdRsDetailsSid;

        if (_imtdRsDetailsFrRemoteModel != null) {
            try {
                Class<?> clazz = _imtdRsDetailsFrRemoteModel.getClass();

                Method method = clazz.getMethod("setImtdRsDetailsSid", int.class);

                method.invoke(_imtdRsDetailsFrRemoteModel, imtdRsDetailsSid);
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

        if (_imtdRsDetailsFrRemoteModel != null) {
            try {
                Class<?> clazz = _imtdRsDetailsFrRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedDate", Date.class);

                method.invoke(_imtdRsDetailsFrRemoteModel, modifiedDate);
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

        if (_imtdRsDetailsFrRemoteModel != null) {
            try {
                Class<?> clazz = _imtdRsDetailsFrRemoteModel.getClass();

                Method method = clazz.getMethod("setRsDetailsFrSid", int.class);

                method.invoke(_imtdRsDetailsFrRemoteModel, rsDetailsFrSid);
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

        if (_imtdRsDetailsFrRemoteModel != null) {
            try {
                Class<?> clazz = _imtdRsDetailsFrRemoteModel.getClass();

                Method method = clazz.getMethod("setRecordLockStatus",
                        boolean.class);

                method.invoke(_imtdRsDetailsFrRemoteModel, recordLockStatus);
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

        if (_imtdRsDetailsFrRemoteModel != null) {
            try {
                Class<?> clazz = _imtdRsDetailsFrRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedDate", Date.class);

                method.invoke(_imtdRsDetailsFrRemoteModel, createdDate);
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

        if (_imtdRsDetailsFrRemoteModel != null) {
            try {
                Class<?> clazz = _imtdRsDetailsFrRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedBy", int.class);

                method.invoke(_imtdRsDetailsFrRemoteModel, createdBy);
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

        if (_imtdRsDetailsFrRemoteModel != null) {
            try {
                Class<?> clazz = _imtdRsDetailsFrRemoteModel.getClass();

                Method method = clazz.getMethod("setSource", String.class);

                method.invoke(_imtdRsDetailsFrRemoteModel, source);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getImtdRsDetailsFrSid() {
        return _imtdRsDetailsFrSid;
    }

    @Override
    public void setImtdRsDetailsFrSid(int imtdRsDetailsFrSid) {
        _imtdRsDetailsFrSid = imtdRsDetailsFrSid;

        if (_imtdRsDetailsFrRemoteModel != null) {
            try {
                Class<?> clazz = _imtdRsDetailsFrRemoteModel.getClass();

                Method method = clazz.getMethod("setImtdRsDetailsFrSid",
                        int.class);

                method.invoke(_imtdRsDetailsFrRemoteModel, imtdRsDetailsFrSid);
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

        if (_imtdRsDetailsFrRemoteModel != null) {
            try {
                Class<?> clazz = _imtdRsDetailsFrRemoteModel.getClass();

                Method method = clazz.getMethod("setBatchId", String.class);

                method.invoke(_imtdRsDetailsFrRemoteModel, batchId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getImtdCreatedDate() {
        return _imtdCreatedDate;
    }

    @Override
    public void setImtdCreatedDate(Date imtdCreatedDate) {
        _imtdCreatedDate = imtdCreatedDate;

        if (_imtdRsDetailsFrRemoteModel != null) {
            try {
                Class<?> clazz = _imtdRsDetailsFrRemoteModel.getClass();

                Method method = clazz.getMethod("setImtdCreatedDate", Date.class);

                method.invoke(_imtdRsDetailsFrRemoteModel, imtdCreatedDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getSessionId() {
        return _sessionId;
    }

    @Override
    public void setSessionId(String sessionId) {
        _sessionId = sessionId;

        if (_imtdRsDetailsFrRemoteModel != null) {
            try {
                Class<?> clazz = _imtdRsDetailsFrRemoteModel.getClass();

                Method method = clazz.getMethod("setSessionId", String.class);

                method.invoke(_imtdRsDetailsFrRemoteModel, sessionId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getUsersId() {
        return _usersId;
    }

    @Override
    public void setUsersId(String usersId) {
        _usersId = usersId;

        if (_imtdRsDetailsFrRemoteModel != null) {
            try {
                Class<?> clazz = _imtdRsDetailsFrRemoteModel.getClass();

                Method method = clazz.getMethod("setUsersId", String.class);

                method.invoke(_imtdRsDetailsFrRemoteModel, usersId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getOperation() {
        return _operation;
    }

    @Override
    public void setOperation(String operation) {
        _operation = operation;

        if (_imtdRsDetailsFrRemoteModel != null) {
            try {
                Class<?> clazz = _imtdRsDetailsFrRemoteModel.getClass();

                Method method = clazz.getMethod("setOperation", String.class);

                method.invoke(_imtdRsDetailsFrRemoteModel, operation);
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

        if (_imtdRsDetailsFrRemoteModel != null) {
            try {
                Class<?> clazz = _imtdRsDetailsFrRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedBy", int.class);

                method.invoke(_imtdRsDetailsFrRemoteModel, modifiedBy);
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

        if (_imtdRsDetailsFrRemoteModel != null) {
            try {
                Class<?> clazz = _imtdRsDetailsFrRemoteModel.getClass();

                Method method = clazz.getMethod("setRsDetailsSid", int.class);

                method.invoke(_imtdRsDetailsFrRemoteModel, rsDetailsSid);
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

        if (_imtdRsDetailsFrRemoteModel != null) {
            try {
                Class<?> clazz = _imtdRsDetailsFrRemoteModel.getClass();

                Method method = clazz.getMethod("setFormulaId", int.class);

                method.invoke(_imtdRsDetailsFrRemoteModel, formulaId);
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

        if (_imtdRsDetailsFrRemoteModel != null) {
            try {
                Class<?> clazz = _imtdRsDetailsFrRemoteModel.getClass();

                Method method = clazz.getMethod("setInboundStatus", String.class);

                method.invoke(_imtdRsDetailsFrRemoteModel, inboundStatus);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getImtdRsDetailsFrRemoteModel() {
        return _imtdRsDetailsFrRemoteModel;
    }

    public void setImtdRsDetailsFrRemoteModel(
        BaseModel<?> imtdRsDetailsFrRemoteModel) {
        _imtdRsDetailsFrRemoteModel = imtdRsDetailsFrRemoteModel;
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

        Class<?> remoteModelClass = _imtdRsDetailsFrRemoteModel.getClass();

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

        Object returnValue = method.invoke(_imtdRsDetailsFrRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            ImtdRsDetailsFrLocalServiceUtil.addImtdRsDetailsFr(this);
        } else {
            ImtdRsDetailsFrLocalServiceUtil.updateImtdRsDetailsFr(this);
        }
    }

    @Override
    public ImtdRsDetailsFr toEscapedModel() {
        return (ImtdRsDetailsFr) ProxyUtil.newProxyInstance(ImtdRsDetailsFr.class.getClassLoader(),
            new Class[] { ImtdRsDetailsFr.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        ImtdRsDetailsFrClp clone = new ImtdRsDetailsFrClp();

        clone.setFormulaMethodId(getFormulaMethodId());
        clone.setItemMasterSid(getItemMasterSid());
        clone.setImtdRsDetailsSid(getImtdRsDetailsSid());
        clone.setModifiedDate(getModifiedDate());
        clone.setRsDetailsFrSid(getRsDetailsFrSid());
        clone.setRecordLockStatus(getRecordLockStatus());
        clone.setCreatedDate(getCreatedDate());
        clone.setCreatedBy(getCreatedBy());
        clone.setSource(getSource());
        clone.setImtdRsDetailsFrSid(getImtdRsDetailsFrSid());
        clone.setBatchId(getBatchId());
        clone.setImtdCreatedDate(getImtdCreatedDate());
        clone.setSessionId(getSessionId());
        clone.setUsersId(getUsersId());
        clone.setOperation(getOperation());
        clone.setModifiedBy(getModifiedBy());
        clone.setRsDetailsSid(getRsDetailsSid());
        clone.setFormulaId(getFormulaId());
        clone.setInboundStatus(getInboundStatus());

        return clone;
    }

    @Override
    public int compareTo(ImtdRsDetailsFr imtdRsDetailsFr) {
        int primaryKey = imtdRsDetailsFr.getPrimaryKey();

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

        if (!(obj instanceof ImtdRsDetailsFrClp)) {
            return false;
        }

        ImtdRsDetailsFrClp imtdRsDetailsFr = (ImtdRsDetailsFrClp) obj;

        int primaryKey = imtdRsDetailsFr.getPrimaryKey();

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

        sb.append("{formulaMethodId=");
        sb.append(getFormulaMethodId());
        sb.append(", itemMasterSid=");
        sb.append(getItemMasterSid());
        sb.append(", imtdRsDetailsSid=");
        sb.append(getImtdRsDetailsSid());
        sb.append(", modifiedDate=");
        sb.append(getModifiedDate());
        sb.append(", rsDetailsFrSid=");
        sb.append(getRsDetailsFrSid());
        sb.append(", recordLockStatus=");
        sb.append(getRecordLockStatus());
        sb.append(", createdDate=");
        sb.append(getCreatedDate());
        sb.append(", createdBy=");
        sb.append(getCreatedBy());
        sb.append(", source=");
        sb.append(getSource());
        sb.append(", imtdRsDetailsFrSid=");
        sb.append(getImtdRsDetailsFrSid());
        sb.append(", batchId=");
        sb.append(getBatchId());
        sb.append(", imtdCreatedDate=");
        sb.append(getImtdCreatedDate());
        sb.append(", sessionId=");
        sb.append(getSessionId());
        sb.append(", usersId=");
        sb.append(getUsersId());
        sb.append(", operation=");
        sb.append(getOperation());
        sb.append(", modifiedBy=");
        sb.append(getModifiedBy());
        sb.append(", rsDetailsSid=");
        sb.append(getRsDetailsSid());
        sb.append(", formulaId=");
        sb.append(getFormulaId());
        sb.append(", inboundStatus=");
        sb.append(getInboundStatus());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(61);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.ImtdRsDetailsFr");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>formulaMethodId</column-name><column-value><![CDATA[");
        sb.append(getFormulaMethodId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemMasterSid</column-name><column-value><![CDATA[");
        sb.append(getItemMasterSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>imtdRsDetailsSid</column-name><column-value><![CDATA[");
        sb.append(getImtdRsDetailsSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
        sb.append(getModifiedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>rsDetailsFrSid</column-name><column-value><![CDATA[");
        sb.append(getRsDetailsFrSid());
        sb.append("]]></column-value></column>");
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
            "<column><column-name>imtdRsDetailsFrSid</column-name><column-value><![CDATA[");
        sb.append(getImtdRsDetailsFrSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>batchId</column-name><column-value><![CDATA[");
        sb.append(getBatchId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>imtdCreatedDate</column-name><column-value><![CDATA[");
        sb.append(getImtdCreatedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>sessionId</column-name><column-value><![CDATA[");
        sb.append(getSessionId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>usersId</column-name><column-value><![CDATA[");
        sb.append(getUsersId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>operation</column-name><column-value><![CDATA[");
        sb.append(getOperation());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedBy</column-name><column-value><![CDATA[");
        sb.append(getModifiedBy());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>rsDetailsSid</column-name><column-value><![CDATA[");
        sb.append(getRsDetailsSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>formulaId</column-name><column-value><![CDATA[");
        sb.append(getFormulaId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>inboundStatus</column-name><column-value><![CDATA[");
        sb.append(getInboundStatus());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
