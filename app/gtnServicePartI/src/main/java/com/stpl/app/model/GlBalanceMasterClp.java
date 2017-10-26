package com.stpl.app.model;

import com.stpl.app.service.ClpSerializer;
import com.stpl.app.service.GlBalanceMasterLocalServiceUtil;

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


public class GlBalanceMasterClp extends BaseModelImpl<GlBalanceMaster>
    implements GlBalanceMaster {
    private int _createdBy;
    private int _modifiedBy;
    private String _accountId;
    private Date _uploadDate;
    private Date _createdDate;
    private int _glBalanceMasterSid;
    private String _isActive;
    private String _batchId;
    private Date _modifiedDate;
    private String _balance;
    private String _closeIndicator;
    private boolean _recordLockStatus;
    private String _year;
    private String _period;
    private String _source;
    private Date _insertedDate;
    private String _accountNo;
    private String _inboundStatus;
    private BaseModel<?> _glBalanceMasterRemoteModel;

    public GlBalanceMasterClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return GlBalanceMaster.class;
    }

    @Override
    public String getModelClassName() {
        return GlBalanceMaster.class.getName();
    }

    @Override
    public int getPrimaryKey() {
        return _glBalanceMasterSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setGlBalanceMasterSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _glBalanceMasterSid;
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
        attributes.put("accountId", getAccountId());
        attributes.put("uploadDate", getUploadDate());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("glBalanceMasterSid", getGlBalanceMasterSid());
        attributes.put("isActive", getIsActive());
        attributes.put("batchId", getBatchId());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("balance", getBalance());
        attributes.put("closeIndicator", getCloseIndicator());
        attributes.put("recordLockStatus", getRecordLockStatus());
        attributes.put("year", getYear());
        attributes.put("period", getPeriod());
        attributes.put("source", getSource());
        attributes.put("insertedDate", getInsertedDate());
        attributes.put("accountNo", getAccountNo());
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

        String accountId = (String) attributes.get("accountId");

        if (accountId != null) {
            setAccountId(accountId);
        }

        Date uploadDate = (Date) attributes.get("uploadDate");

        if (uploadDate != null) {
            setUploadDate(uploadDate);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        Integer glBalanceMasterSid = (Integer) attributes.get(
                "glBalanceMasterSid");

        if (glBalanceMasterSid != null) {
            setGlBalanceMasterSid(glBalanceMasterSid);
        }

        String isActive = (String) attributes.get("isActive");

        if (isActive != null) {
            setIsActive(isActive);
        }

        String batchId = (String) attributes.get("batchId");

        if (batchId != null) {
            setBatchId(batchId);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        String balance = (String) attributes.get("balance");

        if (balance != null) {
            setBalance(balance);
        }

        String closeIndicator = (String) attributes.get("closeIndicator");

        if (closeIndicator != null) {
            setCloseIndicator(closeIndicator);
        }

        Boolean recordLockStatus = (Boolean) attributes.get("recordLockStatus");

        if (recordLockStatus != null) {
            setRecordLockStatus(recordLockStatus);
        }

        String year = (String) attributes.get("year");

        if (year != null) {
            setYear(year);
        }

        String period = (String) attributes.get("period");

        if (period != null) {
            setPeriod(period);
        }

        String source = (String) attributes.get("source");

        if (source != null) {
            setSource(source);
        }

        Date insertedDate = (Date) attributes.get("insertedDate");

        if (insertedDate != null) {
            setInsertedDate(insertedDate);
        }

        String accountNo = (String) attributes.get("accountNo");

        if (accountNo != null) {
            setAccountNo(accountNo);
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

        if (_glBalanceMasterRemoteModel != null) {
            try {
                Class<?> clazz = _glBalanceMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedBy", int.class);

                method.invoke(_glBalanceMasterRemoteModel, createdBy);
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

        if (_glBalanceMasterRemoteModel != null) {
            try {
                Class<?> clazz = _glBalanceMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedBy", int.class);

                method.invoke(_glBalanceMasterRemoteModel, modifiedBy);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getAccountId() {
        return _accountId;
    }

    @Override
    public void setAccountId(String accountId) {
        _accountId = accountId;

        if (_glBalanceMasterRemoteModel != null) {
            try {
                Class<?> clazz = _glBalanceMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setAccountId", String.class);

                method.invoke(_glBalanceMasterRemoteModel, accountId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getUploadDate() {
        return _uploadDate;
    }

    @Override
    public void setUploadDate(Date uploadDate) {
        _uploadDate = uploadDate;

        if (_glBalanceMasterRemoteModel != null) {
            try {
                Class<?> clazz = _glBalanceMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setUploadDate", Date.class);

                method.invoke(_glBalanceMasterRemoteModel, uploadDate);
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

        if (_glBalanceMasterRemoteModel != null) {
            try {
                Class<?> clazz = _glBalanceMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedDate", Date.class);

                method.invoke(_glBalanceMasterRemoteModel, createdDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getGlBalanceMasterSid() {
        return _glBalanceMasterSid;
    }

    @Override
    public void setGlBalanceMasterSid(int glBalanceMasterSid) {
        _glBalanceMasterSid = glBalanceMasterSid;

        if (_glBalanceMasterRemoteModel != null) {
            try {
                Class<?> clazz = _glBalanceMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setGlBalanceMasterSid",
                        int.class);

                method.invoke(_glBalanceMasterRemoteModel, glBalanceMasterSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getIsActive() {
        return _isActive;
    }

    @Override
    public void setIsActive(String isActive) {
        _isActive = isActive;

        if (_glBalanceMasterRemoteModel != null) {
            try {
                Class<?> clazz = _glBalanceMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setIsActive", String.class);

                method.invoke(_glBalanceMasterRemoteModel, isActive);
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

        if (_glBalanceMasterRemoteModel != null) {
            try {
                Class<?> clazz = _glBalanceMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setBatchId", String.class);

                method.invoke(_glBalanceMasterRemoteModel, batchId);
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

        if (_glBalanceMasterRemoteModel != null) {
            try {
                Class<?> clazz = _glBalanceMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedDate", Date.class);

                method.invoke(_glBalanceMasterRemoteModel, modifiedDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getBalance() {
        return _balance;
    }

    @Override
    public void setBalance(String balance) {
        _balance = balance;

        if (_glBalanceMasterRemoteModel != null) {
            try {
                Class<?> clazz = _glBalanceMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setBalance", String.class);

                method.invoke(_glBalanceMasterRemoteModel, balance);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getCloseIndicator() {
        return _closeIndicator;
    }

    @Override
    public void setCloseIndicator(String closeIndicator) {
        _closeIndicator = closeIndicator;

        if (_glBalanceMasterRemoteModel != null) {
            try {
                Class<?> clazz = _glBalanceMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCloseIndicator",
                        String.class);

                method.invoke(_glBalanceMasterRemoteModel, closeIndicator);
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

        if (_glBalanceMasterRemoteModel != null) {
            try {
                Class<?> clazz = _glBalanceMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setRecordLockStatus",
                        boolean.class);

                method.invoke(_glBalanceMasterRemoteModel, recordLockStatus);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getYear() {
        return _year;
    }

    @Override
    public void setYear(String year) {
        _year = year;

        if (_glBalanceMasterRemoteModel != null) {
            try {
                Class<?> clazz = _glBalanceMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setYear", String.class);

                method.invoke(_glBalanceMasterRemoteModel, year);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getPeriod() {
        return _period;
    }

    @Override
    public void setPeriod(String period) {
        _period = period;

        if (_glBalanceMasterRemoteModel != null) {
            try {
                Class<?> clazz = _glBalanceMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setPeriod", String.class);

                method.invoke(_glBalanceMasterRemoteModel, period);
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

        if (_glBalanceMasterRemoteModel != null) {
            try {
                Class<?> clazz = _glBalanceMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setSource", String.class);

                method.invoke(_glBalanceMasterRemoteModel, source);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getInsertedDate() {
        return _insertedDate;
    }

    @Override
    public void setInsertedDate(Date insertedDate) {
        _insertedDate = insertedDate;

        if (_glBalanceMasterRemoteModel != null) {
            try {
                Class<?> clazz = _glBalanceMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setInsertedDate", Date.class);

                method.invoke(_glBalanceMasterRemoteModel, insertedDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getAccountNo() {
        return _accountNo;
    }

    @Override
    public void setAccountNo(String accountNo) {
        _accountNo = accountNo;

        if (_glBalanceMasterRemoteModel != null) {
            try {
                Class<?> clazz = _glBalanceMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setAccountNo", String.class);

                method.invoke(_glBalanceMasterRemoteModel, accountNo);
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

        if (_glBalanceMasterRemoteModel != null) {
            try {
                Class<?> clazz = _glBalanceMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setInboundStatus", String.class);

                method.invoke(_glBalanceMasterRemoteModel, inboundStatus);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getGlBalanceMasterRemoteModel() {
        return _glBalanceMasterRemoteModel;
    }

    public void setGlBalanceMasterRemoteModel(
        BaseModel<?> glBalanceMasterRemoteModel) {
        _glBalanceMasterRemoteModel = glBalanceMasterRemoteModel;
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

        Class<?> remoteModelClass = _glBalanceMasterRemoteModel.getClass();

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

        Object returnValue = method.invoke(_glBalanceMasterRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            GlBalanceMasterLocalServiceUtil.addGlBalanceMaster(this);
        } else {
            GlBalanceMasterLocalServiceUtil.updateGlBalanceMaster(this);
        }
    }

    @Override
    public GlBalanceMaster toEscapedModel() {
        return (GlBalanceMaster) ProxyUtil.newProxyInstance(GlBalanceMaster.class.getClassLoader(),
            new Class[] { GlBalanceMaster.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        GlBalanceMasterClp clone = new GlBalanceMasterClp();

        clone.setCreatedBy(getCreatedBy());
        clone.setModifiedBy(getModifiedBy());
        clone.setAccountId(getAccountId());
        clone.setUploadDate(getUploadDate());
        clone.setCreatedDate(getCreatedDate());
        clone.setGlBalanceMasterSid(getGlBalanceMasterSid());
        clone.setIsActive(getIsActive());
        clone.setBatchId(getBatchId());
        clone.setModifiedDate(getModifiedDate());
        clone.setBalance(getBalance());
        clone.setCloseIndicator(getCloseIndicator());
        clone.setRecordLockStatus(getRecordLockStatus());
        clone.setYear(getYear());
        clone.setPeriod(getPeriod());
        clone.setSource(getSource());
        clone.setInsertedDate(getInsertedDate());
        clone.setAccountNo(getAccountNo());
        clone.setInboundStatus(getInboundStatus());

        return clone;
    }

    @Override
    public int compareTo(GlBalanceMaster glBalanceMaster) {
        int primaryKey = glBalanceMaster.getPrimaryKey();

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

        if (!(obj instanceof GlBalanceMasterClp)) {
            return false;
        }

        GlBalanceMasterClp glBalanceMaster = (GlBalanceMasterClp) obj;

        int primaryKey = glBalanceMaster.getPrimaryKey();

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
        StringBundler sb = new StringBundler(37);

        sb.append("{createdBy=");
        sb.append(getCreatedBy());
        sb.append(", modifiedBy=");
        sb.append(getModifiedBy());
        sb.append(", accountId=");
        sb.append(getAccountId());
        sb.append(", uploadDate=");
        sb.append(getUploadDate());
        sb.append(", createdDate=");
        sb.append(getCreatedDate());
        sb.append(", glBalanceMasterSid=");
        sb.append(getGlBalanceMasterSid());
        sb.append(", isActive=");
        sb.append(getIsActive());
        sb.append(", batchId=");
        sb.append(getBatchId());
        sb.append(", modifiedDate=");
        sb.append(getModifiedDate());
        sb.append(", balance=");
        sb.append(getBalance());
        sb.append(", closeIndicator=");
        sb.append(getCloseIndicator());
        sb.append(", recordLockStatus=");
        sb.append(getRecordLockStatus());
        sb.append(", year=");
        sb.append(getYear());
        sb.append(", period=");
        sb.append(getPeriod());
        sb.append(", source=");
        sb.append(getSource());
        sb.append(", insertedDate=");
        sb.append(getInsertedDate());
        sb.append(", accountNo=");
        sb.append(getAccountNo());
        sb.append(", inboundStatus=");
        sb.append(getInboundStatus());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(58);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.GlBalanceMaster");
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
            "<column><column-name>accountId</column-name><column-value><![CDATA[");
        sb.append(getAccountId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>uploadDate</column-name><column-value><![CDATA[");
        sb.append(getUploadDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>createdDate</column-name><column-value><![CDATA[");
        sb.append(getCreatedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>glBalanceMasterSid</column-name><column-value><![CDATA[");
        sb.append(getGlBalanceMasterSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>isActive</column-name><column-value><![CDATA[");
        sb.append(getIsActive());
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
            "<column><column-name>balance</column-name><column-value><![CDATA[");
        sb.append(getBalance());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>closeIndicator</column-name><column-value><![CDATA[");
        sb.append(getCloseIndicator());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>recordLockStatus</column-name><column-value><![CDATA[");
        sb.append(getRecordLockStatus());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>year</column-name><column-value><![CDATA[");
        sb.append(getYear());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>period</column-name><column-value><![CDATA[");
        sb.append(getPeriod());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>source</column-name><column-value><![CDATA[");
        sb.append(getSource());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>insertedDate</column-name><column-value><![CDATA[");
        sb.append(getInsertedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>accountNo</column-name><column-value><![CDATA[");
        sb.append(getAccountNo());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>inboundStatus</column-name><column-value><![CDATA[");
        sb.append(getInboundStatus());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
