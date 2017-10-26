package com.stpl.app.model;

import com.stpl.app.service.ClpSerializer;
import com.stpl.app.service.ImtdRsContractDetailsFrLocalServiceUtil;

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


public class ImtdRsContractDetailsFrClp extends BaseModelImpl<ImtdRsContractDetailsFr>
    implements ImtdRsContractDetailsFr {
    private int _imtdRsContractDetailsFrSid;
    private String _formulaMethodId;
    private int _itemMasterSid;
    private int _rsContractDetailsFrSid;
    private Date _modifiedDate;
    private int _rsContractDetailsSid;
    private int _imtdItemPriceRebateDetailsSid;
    private boolean _recordLockStatus;
    private Date _createdDate;
    private int _createdBy;
    private String _source;
    private String _batchId;
    private Date _imtdCreatedDate;
    private String _sessionId;
    private String _usersId;
    private String _operation;
    private int _modifiedBy;
    private int _formulaId;
    private String _inboundStatus;
    private BaseModel<?> _imtdRsContractDetailsFrRemoteModel;

    public ImtdRsContractDetailsFrClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return ImtdRsContractDetailsFr.class;
    }

    @Override
    public String getModelClassName() {
        return ImtdRsContractDetailsFr.class.getName();
    }

    @Override
    public int getPrimaryKey() {
        return _imtdRsContractDetailsFrSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setImtdRsContractDetailsFrSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _imtdRsContractDetailsFrSid;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Integer) primaryKeyObj).intValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("imtdRsContractDetailsFrSid",
            getImtdRsContractDetailsFrSid());
        attributes.put("formulaMethodId", getFormulaMethodId());
        attributes.put("itemMasterSid", getItemMasterSid());
        attributes.put("rsContractDetailsFrSid", getRsContractDetailsFrSid());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("rsContractDetailsSid", getRsContractDetailsSid());
        attributes.put("imtdItemPriceRebateDetailsSid",
            getImtdItemPriceRebateDetailsSid());
        attributes.put("recordLockStatus", getRecordLockStatus());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("source", getSource());
        attributes.put("batchId", getBatchId());
        attributes.put("imtdCreatedDate", getImtdCreatedDate());
        attributes.put("sessionId", getSessionId());
        attributes.put("usersId", getUsersId());
        attributes.put("operation", getOperation());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("formulaId", getFormulaId());
        attributes.put("inboundStatus", getInboundStatus());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Integer imtdRsContractDetailsFrSid = (Integer) attributes.get(
                "imtdRsContractDetailsFrSid");

        if (imtdRsContractDetailsFrSid != null) {
            setImtdRsContractDetailsFrSid(imtdRsContractDetailsFrSid);
        }

        String formulaMethodId = (String) attributes.get("formulaMethodId");

        if (formulaMethodId != null) {
            setFormulaMethodId(formulaMethodId);
        }

        Integer itemMasterSid = (Integer) attributes.get("itemMasterSid");

        if (itemMasterSid != null) {
            setItemMasterSid(itemMasterSid);
        }

        Integer rsContractDetailsFrSid = (Integer) attributes.get(
                "rsContractDetailsFrSid");

        if (rsContractDetailsFrSid != null) {
            setRsContractDetailsFrSid(rsContractDetailsFrSid);
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

        Integer imtdItemPriceRebateDetailsSid = (Integer) attributes.get(
                "imtdItemPriceRebateDetailsSid");

        if (imtdItemPriceRebateDetailsSid != null) {
            setImtdItemPriceRebateDetailsSid(imtdItemPriceRebateDetailsSid);
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
    public int getImtdRsContractDetailsFrSid() {
        return _imtdRsContractDetailsFrSid;
    }

    @Override
    public void setImtdRsContractDetailsFrSid(int imtdRsContractDetailsFrSid) {
        _imtdRsContractDetailsFrSid = imtdRsContractDetailsFrSid;

        if (_imtdRsContractDetailsFrRemoteModel != null) {
            try {
                Class<?> clazz = _imtdRsContractDetailsFrRemoteModel.getClass();

                Method method = clazz.getMethod("setImtdRsContractDetailsFrSid",
                        int.class);

                method.invoke(_imtdRsContractDetailsFrRemoteModel,
                    imtdRsContractDetailsFrSid);
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

        if (_imtdRsContractDetailsFrRemoteModel != null) {
            try {
                Class<?> clazz = _imtdRsContractDetailsFrRemoteModel.getClass();

                Method method = clazz.getMethod("setFormulaMethodId",
                        String.class);

                method.invoke(_imtdRsContractDetailsFrRemoteModel,
                    formulaMethodId);
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

        if (_imtdRsContractDetailsFrRemoteModel != null) {
            try {
                Class<?> clazz = _imtdRsContractDetailsFrRemoteModel.getClass();

                Method method = clazz.getMethod("setItemMasterSid", int.class);

                method.invoke(_imtdRsContractDetailsFrRemoteModel, itemMasterSid);
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

        if (_imtdRsContractDetailsFrRemoteModel != null) {
            try {
                Class<?> clazz = _imtdRsContractDetailsFrRemoteModel.getClass();

                Method method = clazz.getMethod("setRsContractDetailsFrSid",
                        int.class);

                method.invoke(_imtdRsContractDetailsFrRemoteModel,
                    rsContractDetailsFrSid);
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

        if (_imtdRsContractDetailsFrRemoteModel != null) {
            try {
                Class<?> clazz = _imtdRsContractDetailsFrRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedDate", Date.class);

                method.invoke(_imtdRsContractDetailsFrRemoteModel, modifiedDate);
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

        if (_imtdRsContractDetailsFrRemoteModel != null) {
            try {
                Class<?> clazz = _imtdRsContractDetailsFrRemoteModel.getClass();

                Method method = clazz.getMethod("setRsContractDetailsSid",
                        int.class);

                method.invoke(_imtdRsContractDetailsFrRemoteModel,
                    rsContractDetailsSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getImtdItemPriceRebateDetailsSid() {
        return _imtdItemPriceRebateDetailsSid;
    }

    @Override
    public void setImtdItemPriceRebateDetailsSid(
        int imtdItemPriceRebateDetailsSid) {
        _imtdItemPriceRebateDetailsSid = imtdItemPriceRebateDetailsSid;

        if (_imtdRsContractDetailsFrRemoteModel != null) {
            try {
                Class<?> clazz = _imtdRsContractDetailsFrRemoteModel.getClass();

                Method method = clazz.getMethod("setImtdItemPriceRebateDetailsSid",
                        int.class);

                method.invoke(_imtdRsContractDetailsFrRemoteModel,
                    imtdItemPriceRebateDetailsSid);
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

        if (_imtdRsContractDetailsFrRemoteModel != null) {
            try {
                Class<?> clazz = _imtdRsContractDetailsFrRemoteModel.getClass();

                Method method = clazz.getMethod("setRecordLockStatus",
                        boolean.class);

                method.invoke(_imtdRsContractDetailsFrRemoteModel,
                    recordLockStatus);
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

        if (_imtdRsContractDetailsFrRemoteModel != null) {
            try {
                Class<?> clazz = _imtdRsContractDetailsFrRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedDate", Date.class);

                method.invoke(_imtdRsContractDetailsFrRemoteModel, createdDate);
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

        if (_imtdRsContractDetailsFrRemoteModel != null) {
            try {
                Class<?> clazz = _imtdRsContractDetailsFrRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedBy", int.class);

                method.invoke(_imtdRsContractDetailsFrRemoteModel, createdBy);
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

        if (_imtdRsContractDetailsFrRemoteModel != null) {
            try {
                Class<?> clazz = _imtdRsContractDetailsFrRemoteModel.getClass();

                Method method = clazz.getMethod("setSource", String.class);

                method.invoke(_imtdRsContractDetailsFrRemoteModel, source);
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

        if (_imtdRsContractDetailsFrRemoteModel != null) {
            try {
                Class<?> clazz = _imtdRsContractDetailsFrRemoteModel.getClass();

                Method method = clazz.getMethod("setBatchId", String.class);

                method.invoke(_imtdRsContractDetailsFrRemoteModel, batchId);
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

        if (_imtdRsContractDetailsFrRemoteModel != null) {
            try {
                Class<?> clazz = _imtdRsContractDetailsFrRemoteModel.getClass();

                Method method = clazz.getMethod("setImtdCreatedDate", Date.class);

                method.invoke(_imtdRsContractDetailsFrRemoteModel,
                    imtdCreatedDate);
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

        if (_imtdRsContractDetailsFrRemoteModel != null) {
            try {
                Class<?> clazz = _imtdRsContractDetailsFrRemoteModel.getClass();

                Method method = clazz.getMethod("setSessionId", String.class);

                method.invoke(_imtdRsContractDetailsFrRemoteModel, sessionId);
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

        if (_imtdRsContractDetailsFrRemoteModel != null) {
            try {
                Class<?> clazz = _imtdRsContractDetailsFrRemoteModel.getClass();

                Method method = clazz.getMethod("setUsersId", String.class);

                method.invoke(_imtdRsContractDetailsFrRemoteModel, usersId);
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

        if (_imtdRsContractDetailsFrRemoteModel != null) {
            try {
                Class<?> clazz = _imtdRsContractDetailsFrRemoteModel.getClass();

                Method method = clazz.getMethod("setOperation", String.class);

                method.invoke(_imtdRsContractDetailsFrRemoteModel, operation);
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

        if (_imtdRsContractDetailsFrRemoteModel != null) {
            try {
                Class<?> clazz = _imtdRsContractDetailsFrRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedBy", int.class);

                method.invoke(_imtdRsContractDetailsFrRemoteModel, modifiedBy);
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

        if (_imtdRsContractDetailsFrRemoteModel != null) {
            try {
                Class<?> clazz = _imtdRsContractDetailsFrRemoteModel.getClass();

                Method method = clazz.getMethod("setFormulaId", int.class);

                method.invoke(_imtdRsContractDetailsFrRemoteModel, formulaId);
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

        if (_imtdRsContractDetailsFrRemoteModel != null) {
            try {
                Class<?> clazz = _imtdRsContractDetailsFrRemoteModel.getClass();

                Method method = clazz.getMethod("setInboundStatus", String.class);

                method.invoke(_imtdRsContractDetailsFrRemoteModel, inboundStatus);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getImtdRsContractDetailsFrRemoteModel() {
        return _imtdRsContractDetailsFrRemoteModel;
    }

    public void setImtdRsContractDetailsFrRemoteModel(
        BaseModel<?> imtdRsContractDetailsFrRemoteModel) {
        _imtdRsContractDetailsFrRemoteModel = imtdRsContractDetailsFrRemoteModel;
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

        Class<?> remoteModelClass = _imtdRsContractDetailsFrRemoteModel.getClass();

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

        Object returnValue = method.invoke(_imtdRsContractDetailsFrRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            ImtdRsContractDetailsFrLocalServiceUtil.addImtdRsContractDetailsFr(this);
        } else {
            ImtdRsContractDetailsFrLocalServiceUtil.updateImtdRsContractDetailsFr(this);
        }
    }

    @Override
    public ImtdRsContractDetailsFr toEscapedModel() {
        return (ImtdRsContractDetailsFr) ProxyUtil.newProxyInstance(ImtdRsContractDetailsFr.class.getClassLoader(),
            new Class[] { ImtdRsContractDetailsFr.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        ImtdRsContractDetailsFrClp clone = new ImtdRsContractDetailsFrClp();

        clone.setImtdRsContractDetailsFrSid(getImtdRsContractDetailsFrSid());
        clone.setFormulaMethodId(getFormulaMethodId());
        clone.setItemMasterSid(getItemMasterSid());
        clone.setRsContractDetailsFrSid(getRsContractDetailsFrSid());
        clone.setModifiedDate(getModifiedDate());
        clone.setRsContractDetailsSid(getRsContractDetailsSid());
        clone.setImtdItemPriceRebateDetailsSid(getImtdItemPriceRebateDetailsSid());
        clone.setRecordLockStatus(getRecordLockStatus());
        clone.setCreatedDate(getCreatedDate());
        clone.setCreatedBy(getCreatedBy());
        clone.setSource(getSource());
        clone.setBatchId(getBatchId());
        clone.setImtdCreatedDate(getImtdCreatedDate());
        clone.setSessionId(getSessionId());
        clone.setUsersId(getUsersId());
        clone.setOperation(getOperation());
        clone.setModifiedBy(getModifiedBy());
        clone.setFormulaId(getFormulaId());
        clone.setInboundStatus(getInboundStatus());

        return clone;
    }

    @Override
    public int compareTo(ImtdRsContractDetailsFr imtdRsContractDetailsFr) {
        int primaryKey = imtdRsContractDetailsFr.getPrimaryKey();

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

        if (!(obj instanceof ImtdRsContractDetailsFrClp)) {
            return false;
        }

        ImtdRsContractDetailsFrClp imtdRsContractDetailsFr = (ImtdRsContractDetailsFrClp) obj;

        int primaryKey = imtdRsContractDetailsFr.getPrimaryKey();

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

        sb.append("{imtdRsContractDetailsFrSid=");
        sb.append(getImtdRsContractDetailsFrSid());
        sb.append(", formulaMethodId=");
        sb.append(getFormulaMethodId());
        sb.append(", itemMasterSid=");
        sb.append(getItemMasterSid());
        sb.append(", rsContractDetailsFrSid=");
        sb.append(getRsContractDetailsFrSid());
        sb.append(", modifiedDate=");
        sb.append(getModifiedDate());
        sb.append(", rsContractDetailsSid=");
        sb.append(getRsContractDetailsSid());
        sb.append(", imtdItemPriceRebateDetailsSid=");
        sb.append(getImtdItemPriceRebateDetailsSid());
        sb.append(", recordLockStatus=");
        sb.append(getRecordLockStatus());
        sb.append(", createdDate=");
        sb.append(getCreatedDate());
        sb.append(", createdBy=");
        sb.append(getCreatedBy());
        sb.append(", source=");
        sb.append(getSource());
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
        sb.append("com.stpl.app.model.ImtdRsContractDetailsFr");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>imtdRsContractDetailsFrSid</column-name><column-value><![CDATA[");
        sb.append(getImtdRsContractDetailsFrSid());
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
            "<column><column-name>rsContractDetailsFrSid</column-name><column-value><![CDATA[");
        sb.append(getRsContractDetailsFrSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
        sb.append(getModifiedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>rsContractDetailsSid</column-name><column-value><![CDATA[");
        sb.append(getRsContractDetailsSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>imtdItemPriceRebateDetailsSid</column-name><column-value><![CDATA[");
        sb.append(getImtdItemPriceRebateDetailsSid());
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
