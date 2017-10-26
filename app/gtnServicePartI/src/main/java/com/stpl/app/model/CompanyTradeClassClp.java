package com.stpl.app.model;

import com.stpl.app.service.ClpSerializer;
import com.stpl.app.service.CompanyTradeClassLocalServiceUtil;

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


public class CompanyTradeClassClp extends BaseModelImpl<CompanyTradeClass>
    implements CompanyTradeClass {
    private int _priorTradeClass;
    private int _companyTradeClassSid;
    private Date _lastUpdatedDate;
    private Date _priorTradeClassStartDate;
    private Date _modifiedDate;
    private Date _tradeClassEndDate;
    private Date _tradeClassStartDate;
    private boolean _recordLockStatus;
    private Date _createdDate;
    private String _source;
    private int _createdBy;
    private String _batchId;
    private int _companyTradeClass;
    private int _modifiedBy;
    private String _inboundStatus;
    private int _companyMasterSid;
    private BaseModel<?> _companyTradeClassRemoteModel;

    public CompanyTradeClassClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return CompanyTradeClass.class;
    }

    @Override
    public String getModelClassName() {
        return CompanyTradeClass.class.getName();
    }

    @Override
    public int getPrimaryKey() {
        return _companyTradeClassSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setCompanyTradeClassSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _companyTradeClassSid;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Integer) primaryKeyObj).intValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("priorTradeClass", getPriorTradeClass());
        attributes.put("companyTradeClassSid", getCompanyTradeClassSid());
        attributes.put("lastUpdatedDate", getLastUpdatedDate());
        attributes.put("priorTradeClassStartDate", getPriorTradeClassStartDate());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("tradeClassEndDate", getTradeClassEndDate());
        attributes.put("tradeClassStartDate", getTradeClassStartDate());
        attributes.put("recordLockStatus", getRecordLockStatus());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("source", getSource());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("batchId", getBatchId());
        attributes.put("companyTradeClass", getCompanyTradeClass());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("inboundStatus", getInboundStatus());
        attributes.put("companyMasterSid", getCompanyMasterSid());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Integer priorTradeClass = (Integer) attributes.get("priorTradeClass");

        if (priorTradeClass != null) {
            setPriorTradeClass(priorTradeClass);
        }

        Integer companyTradeClassSid = (Integer) attributes.get(
                "companyTradeClassSid");

        if (companyTradeClassSid != null) {
            setCompanyTradeClassSid(companyTradeClassSid);
        }

        Date lastUpdatedDate = (Date) attributes.get("lastUpdatedDate");

        if (lastUpdatedDate != null) {
            setLastUpdatedDate(lastUpdatedDate);
        }

        Date priorTradeClassStartDate = (Date) attributes.get(
                "priorTradeClassStartDate");

        if (priorTradeClassStartDate != null) {
            setPriorTradeClassStartDate(priorTradeClassStartDate);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        Date tradeClassEndDate = (Date) attributes.get("tradeClassEndDate");

        if (tradeClassEndDate != null) {
            setTradeClassEndDate(tradeClassEndDate);
        }

        Date tradeClassStartDate = (Date) attributes.get("tradeClassStartDate");

        if (tradeClassStartDate != null) {
            setTradeClassStartDate(tradeClassStartDate);
        }

        Boolean recordLockStatus = (Boolean) attributes.get("recordLockStatus");

        if (recordLockStatus != null) {
            setRecordLockStatus(recordLockStatus);
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

        String batchId = (String) attributes.get("batchId");

        if (batchId != null) {
            setBatchId(batchId);
        }

        Integer companyTradeClass = (Integer) attributes.get(
                "companyTradeClass");

        if (companyTradeClass != null) {
            setCompanyTradeClass(companyTradeClass);
        }

        Integer modifiedBy = (Integer) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        String inboundStatus = (String) attributes.get("inboundStatus");

        if (inboundStatus != null) {
            setInboundStatus(inboundStatus);
        }

        Integer companyMasterSid = (Integer) attributes.get("companyMasterSid");

        if (companyMasterSid != null) {
            setCompanyMasterSid(companyMasterSid);
        }
    }

    @Override
    public int getPriorTradeClass() {
        return _priorTradeClass;
    }

    @Override
    public void setPriorTradeClass(int priorTradeClass) {
        _priorTradeClass = priorTradeClass;

        if (_companyTradeClassRemoteModel != null) {
            try {
                Class<?> clazz = _companyTradeClassRemoteModel.getClass();

                Method method = clazz.getMethod("setPriorTradeClass", int.class);

                method.invoke(_companyTradeClassRemoteModel, priorTradeClass);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getCompanyTradeClassSid() {
        return _companyTradeClassSid;
    }

    @Override
    public void setCompanyTradeClassSid(int companyTradeClassSid) {
        _companyTradeClassSid = companyTradeClassSid;

        if (_companyTradeClassRemoteModel != null) {
            try {
                Class<?> clazz = _companyTradeClassRemoteModel.getClass();

                Method method = clazz.getMethod("setCompanyTradeClassSid",
                        int.class);

                method.invoke(_companyTradeClassRemoteModel,
                    companyTradeClassSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getLastUpdatedDate() {
        return _lastUpdatedDate;
    }

    @Override
    public void setLastUpdatedDate(Date lastUpdatedDate) {
        _lastUpdatedDate = lastUpdatedDate;

        if (_companyTradeClassRemoteModel != null) {
            try {
                Class<?> clazz = _companyTradeClassRemoteModel.getClass();

                Method method = clazz.getMethod("setLastUpdatedDate", Date.class);

                method.invoke(_companyTradeClassRemoteModel, lastUpdatedDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getPriorTradeClassStartDate() {
        return _priorTradeClassStartDate;
    }

    @Override
    public void setPriorTradeClassStartDate(Date priorTradeClassStartDate) {
        _priorTradeClassStartDate = priorTradeClassStartDate;

        if (_companyTradeClassRemoteModel != null) {
            try {
                Class<?> clazz = _companyTradeClassRemoteModel.getClass();

                Method method = clazz.getMethod("setPriorTradeClassStartDate",
                        Date.class);

                method.invoke(_companyTradeClassRemoteModel,
                    priorTradeClassStartDate);
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

        if (_companyTradeClassRemoteModel != null) {
            try {
                Class<?> clazz = _companyTradeClassRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedDate", Date.class);

                method.invoke(_companyTradeClassRemoteModel, modifiedDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getTradeClassEndDate() {
        return _tradeClassEndDate;
    }

    @Override
    public void setTradeClassEndDate(Date tradeClassEndDate) {
        _tradeClassEndDate = tradeClassEndDate;

        if (_companyTradeClassRemoteModel != null) {
            try {
                Class<?> clazz = _companyTradeClassRemoteModel.getClass();

                Method method = clazz.getMethod("setTradeClassEndDate",
                        Date.class);

                method.invoke(_companyTradeClassRemoteModel, tradeClassEndDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getTradeClassStartDate() {
        return _tradeClassStartDate;
    }

    @Override
    public void setTradeClassStartDate(Date tradeClassStartDate) {
        _tradeClassStartDate = tradeClassStartDate;

        if (_companyTradeClassRemoteModel != null) {
            try {
                Class<?> clazz = _companyTradeClassRemoteModel.getClass();

                Method method = clazz.getMethod("setTradeClassStartDate",
                        Date.class);

                method.invoke(_companyTradeClassRemoteModel, tradeClassStartDate);
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

        if (_companyTradeClassRemoteModel != null) {
            try {
                Class<?> clazz = _companyTradeClassRemoteModel.getClass();

                Method method = clazz.getMethod("setRecordLockStatus",
                        boolean.class);

                method.invoke(_companyTradeClassRemoteModel, recordLockStatus);
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

        if (_companyTradeClassRemoteModel != null) {
            try {
                Class<?> clazz = _companyTradeClassRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedDate", Date.class);

                method.invoke(_companyTradeClassRemoteModel, createdDate);
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

        if (_companyTradeClassRemoteModel != null) {
            try {
                Class<?> clazz = _companyTradeClassRemoteModel.getClass();

                Method method = clazz.getMethod("setSource", String.class);

                method.invoke(_companyTradeClassRemoteModel, source);
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

        if (_companyTradeClassRemoteModel != null) {
            try {
                Class<?> clazz = _companyTradeClassRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedBy", int.class);

                method.invoke(_companyTradeClassRemoteModel, createdBy);
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

        if (_companyTradeClassRemoteModel != null) {
            try {
                Class<?> clazz = _companyTradeClassRemoteModel.getClass();

                Method method = clazz.getMethod("setBatchId", String.class);

                method.invoke(_companyTradeClassRemoteModel, batchId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getCompanyTradeClass() {
        return _companyTradeClass;
    }

    @Override
    public void setCompanyTradeClass(int companyTradeClass) {
        _companyTradeClass = companyTradeClass;

        if (_companyTradeClassRemoteModel != null) {
            try {
                Class<?> clazz = _companyTradeClassRemoteModel.getClass();

                Method method = clazz.getMethod("setCompanyTradeClass",
                        int.class);

                method.invoke(_companyTradeClassRemoteModel, companyTradeClass);
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

        if (_companyTradeClassRemoteModel != null) {
            try {
                Class<?> clazz = _companyTradeClassRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedBy", int.class);

                method.invoke(_companyTradeClassRemoteModel, modifiedBy);
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

        if (_companyTradeClassRemoteModel != null) {
            try {
                Class<?> clazz = _companyTradeClassRemoteModel.getClass();

                Method method = clazz.getMethod("setInboundStatus", String.class);

                method.invoke(_companyTradeClassRemoteModel, inboundStatus);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getCompanyMasterSid() {
        return _companyMasterSid;
    }

    @Override
    public void setCompanyMasterSid(int companyMasterSid) {
        _companyMasterSid = companyMasterSid;

        if (_companyTradeClassRemoteModel != null) {
            try {
                Class<?> clazz = _companyTradeClassRemoteModel.getClass();

                Method method = clazz.getMethod("setCompanyMasterSid", int.class);

                method.invoke(_companyTradeClassRemoteModel, companyMasterSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getCompanyTradeClassRemoteModel() {
        return _companyTradeClassRemoteModel;
    }

    public void setCompanyTradeClassRemoteModel(
        BaseModel<?> companyTradeClassRemoteModel) {
        _companyTradeClassRemoteModel = companyTradeClassRemoteModel;
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

        Class<?> remoteModelClass = _companyTradeClassRemoteModel.getClass();

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

        Object returnValue = method.invoke(_companyTradeClassRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            CompanyTradeClassLocalServiceUtil.addCompanyTradeClass(this);
        } else {
            CompanyTradeClassLocalServiceUtil.updateCompanyTradeClass(this);
        }
    }

    @Override
    public CompanyTradeClass toEscapedModel() {
        return (CompanyTradeClass) ProxyUtil.newProxyInstance(CompanyTradeClass.class.getClassLoader(),
            new Class[] { CompanyTradeClass.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        CompanyTradeClassClp clone = new CompanyTradeClassClp();

        clone.setPriorTradeClass(getPriorTradeClass());
        clone.setCompanyTradeClassSid(getCompanyTradeClassSid());
        clone.setLastUpdatedDate(getLastUpdatedDate());
        clone.setPriorTradeClassStartDate(getPriorTradeClassStartDate());
        clone.setModifiedDate(getModifiedDate());
        clone.setTradeClassEndDate(getTradeClassEndDate());
        clone.setTradeClassStartDate(getTradeClassStartDate());
        clone.setRecordLockStatus(getRecordLockStatus());
        clone.setCreatedDate(getCreatedDate());
        clone.setSource(getSource());
        clone.setCreatedBy(getCreatedBy());
        clone.setBatchId(getBatchId());
        clone.setCompanyTradeClass(getCompanyTradeClass());
        clone.setModifiedBy(getModifiedBy());
        clone.setInboundStatus(getInboundStatus());
        clone.setCompanyMasterSid(getCompanyMasterSid());

        return clone;
    }

    @Override
    public int compareTo(CompanyTradeClass companyTradeClass) {
        int primaryKey = companyTradeClass.getPrimaryKey();

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

        if (!(obj instanceof CompanyTradeClassClp)) {
            return false;
        }

        CompanyTradeClassClp companyTradeClass = (CompanyTradeClassClp) obj;

        int primaryKey = companyTradeClass.getPrimaryKey();

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
        StringBundler sb = new StringBundler(33);

        sb.append("{priorTradeClass=");
        sb.append(getPriorTradeClass());
        sb.append(", companyTradeClassSid=");
        sb.append(getCompanyTradeClassSid());
        sb.append(", lastUpdatedDate=");
        sb.append(getLastUpdatedDate());
        sb.append(", priorTradeClassStartDate=");
        sb.append(getPriorTradeClassStartDate());
        sb.append(", modifiedDate=");
        sb.append(getModifiedDate());
        sb.append(", tradeClassEndDate=");
        sb.append(getTradeClassEndDate());
        sb.append(", tradeClassStartDate=");
        sb.append(getTradeClassStartDate());
        sb.append(", recordLockStatus=");
        sb.append(getRecordLockStatus());
        sb.append(", createdDate=");
        sb.append(getCreatedDate());
        sb.append(", source=");
        sb.append(getSource());
        sb.append(", createdBy=");
        sb.append(getCreatedBy());
        sb.append(", batchId=");
        sb.append(getBatchId());
        sb.append(", companyTradeClass=");
        sb.append(getCompanyTradeClass());
        sb.append(", modifiedBy=");
        sb.append(getModifiedBy());
        sb.append(", inboundStatus=");
        sb.append(getInboundStatus());
        sb.append(", companyMasterSid=");
        sb.append(getCompanyMasterSid());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(52);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.CompanyTradeClass");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>priorTradeClass</column-name><column-value><![CDATA[");
        sb.append(getPriorTradeClass());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>companyTradeClassSid</column-name><column-value><![CDATA[");
        sb.append(getCompanyTradeClassSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>lastUpdatedDate</column-name><column-value><![CDATA[");
        sb.append(getLastUpdatedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>priorTradeClassStartDate</column-name><column-value><![CDATA[");
        sb.append(getPriorTradeClassStartDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
        sb.append(getModifiedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>tradeClassEndDate</column-name><column-value><![CDATA[");
        sb.append(getTradeClassEndDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>tradeClassStartDate</column-name><column-value><![CDATA[");
        sb.append(getTradeClassStartDate());
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
            "<column><column-name>source</column-name><column-value><![CDATA[");
        sb.append(getSource());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>createdBy</column-name><column-value><![CDATA[");
        sb.append(getCreatedBy());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>batchId</column-name><column-value><![CDATA[");
        sb.append(getBatchId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>companyTradeClass</column-name><column-value><![CDATA[");
        sb.append(getCompanyTradeClass());
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
            "<column><column-name>companyMasterSid</column-name><column-value><![CDATA[");
        sb.append(getCompanyMasterSid());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
