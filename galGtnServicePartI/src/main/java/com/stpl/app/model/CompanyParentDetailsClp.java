package com.stpl.app.model;

import com.stpl.app.service.ClpSerializer;
import com.stpl.app.service.CompanyParentDetailsLocalServiceUtil;

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


public class CompanyParentDetailsClp extends BaseModelImpl<CompanyParentDetails>
    implements CompanyParentDetails {
    private Date _lastUpdatedDate;
    private Date _parentEndDate;
    private Date _modifiedDate;
    private int _parentCompanyMasterSid;
    private boolean _recordLockStatus;
    private Date _priorParentStartDate;
    private Date _createdDate;
    private String _source;
    private int _createdBy;
    private int _companyParentDetailsSid;
    private String _priorParentCmpyMasterSid;
    private String _batchId;
    private int _modifiedBy;
    private String _inboundStatus;
    private int _companyMasterSid;
    private Date _parentStartDate;
    private BaseModel<?> _companyParentDetailsRemoteModel;

    public CompanyParentDetailsClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return CompanyParentDetails.class;
    }

    @Override
    public String getModelClassName() {
        return CompanyParentDetails.class.getName();
    }

    @Override
    public int getPrimaryKey() {
        return _companyParentDetailsSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setCompanyParentDetailsSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _companyParentDetailsSid;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Integer) primaryKeyObj).intValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("lastUpdatedDate", getLastUpdatedDate());
        attributes.put("parentEndDate", getParentEndDate());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("parentCompanyMasterSid", getParentCompanyMasterSid());
        attributes.put("recordLockStatus", getRecordLockStatus());
        attributes.put("priorParentStartDate", getPriorParentStartDate());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("source", getSource());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("companyParentDetailsSid", getCompanyParentDetailsSid());
        attributes.put("priorParentCmpyMasterSid", getPriorParentCmpyMasterSid());
        attributes.put("batchId", getBatchId());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("inboundStatus", getInboundStatus());
        attributes.put("companyMasterSid", getCompanyMasterSid());
        attributes.put("parentStartDate", getParentStartDate());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Date lastUpdatedDate = (Date) attributes.get("lastUpdatedDate");

        if (lastUpdatedDate != null) {
            setLastUpdatedDate(lastUpdatedDate);
        }

        Date parentEndDate = (Date) attributes.get("parentEndDate");

        if (parentEndDate != null) {
            setParentEndDate(parentEndDate);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        Integer parentCompanyMasterSid = (Integer) attributes.get(
                "parentCompanyMasterSid");

        if (parentCompanyMasterSid != null) {
            setParentCompanyMasterSid(parentCompanyMasterSid);
        }

        Boolean recordLockStatus = (Boolean) attributes.get("recordLockStatus");

        if (recordLockStatus != null) {
            setRecordLockStatus(recordLockStatus);
        }

        Date priorParentStartDate = (Date) attributes.get(
                "priorParentStartDate");

        if (priorParentStartDate != null) {
            setPriorParentStartDate(priorParentStartDate);
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

        Integer companyParentDetailsSid = (Integer) attributes.get(
                "companyParentDetailsSid");

        if (companyParentDetailsSid != null) {
            setCompanyParentDetailsSid(companyParentDetailsSid);
        }

        String priorParentCmpyMasterSid = (String) attributes.get(
                "priorParentCmpyMasterSid");

        if (priorParentCmpyMasterSid != null) {
            setPriorParentCmpyMasterSid(priorParentCmpyMasterSid);
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

        Integer companyMasterSid = (Integer) attributes.get("companyMasterSid");

        if (companyMasterSid != null) {
            setCompanyMasterSid(companyMasterSid);
        }

        Date parentStartDate = (Date) attributes.get("parentStartDate");

        if (parentStartDate != null) {
            setParentStartDate(parentStartDate);
        }
    }

    @Override
    public Date getLastUpdatedDate() {
        return _lastUpdatedDate;
    }

    @Override
    public void setLastUpdatedDate(Date lastUpdatedDate) {
        _lastUpdatedDate = lastUpdatedDate;

        if (_companyParentDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _companyParentDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setLastUpdatedDate", Date.class);

                method.invoke(_companyParentDetailsRemoteModel, lastUpdatedDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getParentEndDate() {
        return _parentEndDate;
    }

    @Override
    public void setParentEndDate(Date parentEndDate) {
        _parentEndDate = parentEndDate;

        if (_companyParentDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _companyParentDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setParentEndDate", Date.class);

                method.invoke(_companyParentDetailsRemoteModel, parentEndDate);
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

        if (_companyParentDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _companyParentDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedDate", Date.class);

                method.invoke(_companyParentDetailsRemoteModel, modifiedDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getParentCompanyMasterSid() {
        return _parentCompanyMasterSid;
    }

    @Override
    public void setParentCompanyMasterSid(int parentCompanyMasterSid) {
        _parentCompanyMasterSid = parentCompanyMasterSid;

        if (_companyParentDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _companyParentDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setParentCompanyMasterSid",
                        int.class);

                method.invoke(_companyParentDetailsRemoteModel,
                    parentCompanyMasterSid);
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

        if (_companyParentDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _companyParentDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setRecordLockStatus",
                        boolean.class);

                method.invoke(_companyParentDetailsRemoteModel, recordLockStatus);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getPriorParentStartDate() {
        return _priorParentStartDate;
    }

    @Override
    public void setPriorParentStartDate(Date priorParentStartDate) {
        _priorParentStartDate = priorParentStartDate;

        if (_companyParentDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _companyParentDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setPriorParentStartDate",
                        Date.class);

                method.invoke(_companyParentDetailsRemoteModel,
                    priorParentStartDate);
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

        if (_companyParentDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _companyParentDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedDate", Date.class);

                method.invoke(_companyParentDetailsRemoteModel, createdDate);
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

        if (_companyParentDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _companyParentDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setSource", String.class);

                method.invoke(_companyParentDetailsRemoteModel, source);
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

        if (_companyParentDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _companyParentDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedBy", int.class);

                method.invoke(_companyParentDetailsRemoteModel, createdBy);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getCompanyParentDetailsSid() {
        return _companyParentDetailsSid;
    }

    @Override
    public void setCompanyParentDetailsSid(int companyParentDetailsSid) {
        _companyParentDetailsSid = companyParentDetailsSid;

        if (_companyParentDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _companyParentDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCompanyParentDetailsSid",
                        int.class);

                method.invoke(_companyParentDetailsRemoteModel,
                    companyParentDetailsSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getPriorParentCmpyMasterSid() {
        return _priorParentCmpyMasterSid;
    }

    @Override
    public void setPriorParentCmpyMasterSid(String priorParentCmpyMasterSid) {
        _priorParentCmpyMasterSid = priorParentCmpyMasterSid;

        if (_companyParentDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _companyParentDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setPriorParentCmpyMasterSid",
                        String.class);

                method.invoke(_companyParentDetailsRemoteModel,
                    priorParentCmpyMasterSid);
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

        if (_companyParentDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _companyParentDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setBatchId", String.class);

                method.invoke(_companyParentDetailsRemoteModel, batchId);
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

        if (_companyParentDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _companyParentDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedBy", int.class);

                method.invoke(_companyParentDetailsRemoteModel, modifiedBy);
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

        if (_companyParentDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _companyParentDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setInboundStatus", String.class);

                method.invoke(_companyParentDetailsRemoteModel, inboundStatus);
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

        if (_companyParentDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _companyParentDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCompanyMasterSid", int.class);

                method.invoke(_companyParentDetailsRemoteModel, companyMasterSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getParentStartDate() {
        return _parentStartDate;
    }

    @Override
    public void setParentStartDate(Date parentStartDate) {
        _parentStartDate = parentStartDate;

        if (_companyParentDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _companyParentDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setParentStartDate", Date.class);

                method.invoke(_companyParentDetailsRemoteModel, parentStartDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getCompanyParentDetailsRemoteModel() {
        return _companyParentDetailsRemoteModel;
    }

    public void setCompanyParentDetailsRemoteModel(
        BaseModel<?> companyParentDetailsRemoteModel) {
        _companyParentDetailsRemoteModel = companyParentDetailsRemoteModel;
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

        Class<?> remoteModelClass = _companyParentDetailsRemoteModel.getClass();

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

        Object returnValue = method.invoke(_companyParentDetailsRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            CompanyParentDetailsLocalServiceUtil.addCompanyParentDetails(this);
        } else {
            CompanyParentDetailsLocalServiceUtil.updateCompanyParentDetails(this);
        }
    }

    @Override
    public CompanyParentDetails toEscapedModel() {
        return (CompanyParentDetails) ProxyUtil.newProxyInstance(CompanyParentDetails.class.getClassLoader(),
            new Class[] { CompanyParentDetails.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        CompanyParentDetailsClp clone = new CompanyParentDetailsClp();

        clone.setLastUpdatedDate(getLastUpdatedDate());
        clone.setParentEndDate(getParentEndDate());
        clone.setModifiedDate(getModifiedDate());
        clone.setParentCompanyMasterSid(getParentCompanyMasterSid());
        clone.setRecordLockStatus(getRecordLockStatus());
        clone.setPriorParentStartDate(getPriorParentStartDate());
        clone.setCreatedDate(getCreatedDate());
        clone.setSource(getSource());
        clone.setCreatedBy(getCreatedBy());
        clone.setCompanyParentDetailsSid(getCompanyParentDetailsSid());
        clone.setPriorParentCmpyMasterSid(getPriorParentCmpyMasterSid());
        clone.setBatchId(getBatchId());
        clone.setModifiedBy(getModifiedBy());
        clone.setInboundStatus(getInboundStatus());
        clone.setCompanyMasterSid(getCompanyMasterSid());
        clone.setParentStartDate(getParentStartDate());

        return clone;
    }

    @Override
    public int compareTo(CompanyParentDetails companyParentDetails) {
        int primaryKey = companyParentDetails.getPrimaryKey();

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

        if (!(obj instanceof CompanyParentDetailsClp)) {
            return false;
        }

        CompanyParentDetailsClp companyParentDetails = (CompanyParentDetailsClp) obj;

        int primaryKey = companyParentDetails.getPrimaryKey();

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

        sb.append("{lastUpdatedDate=");
        sb.append(getLastUpdatedDate());
        sb.append(", parentEndDate=");
        sb.append(getParentEndDate());
        sb.append(", modifiedDate=");
        sb.append(getModifiedDate());
        sb.append(", parentCompanyMasterSid=");
        sb.append(getParentCompanyMasterSid());
        sb.append(", recordLockStatus=");
        sb.append(getRecordLockStatus());
        sb.append(", priorParentStartDate=");
        sb.append(getPriorParentStartDate());
        sb.append(", createdDate=");
        sb.append(getCreatedDate());
        sb.append(", source=");
        sb.append(getSource());
        sb.append(", createdBy=");
        sb.append(getCreatedBy());
        sb.append(", companyParentDetailsSid=");
        sb.append(getCompanyParentDetailsSid());
        sb.append(", priorParentCmpyMasterSid=");
        sb.append(getPriorParentCmpyMasterSid());
        sb.append(", batchId=");
        sb.append(getBatchId());
        sb.append(", modifiedBy=");
        sb.append(getModifiedBy());
        sb.append(", inboundStatus=");
        sb.append(getInboundStatus());
        sb.append(", companyMasterSid=");
        sb.append(getCompanyMasterSid());
        sb.append(", parentStartDate=");
        sb.append(getParentStartDate());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(52);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.CompanyParentDetails");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>lastUpdatedDate</column-name><column-value><![CDATA[");
        sb.append(getLastUpdatedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>parentEndDate</column-name><column-value><![CDATA[");
        sb.append(getParentEndDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
        sb.append(getModifiedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>parentCompanyMasterSid</column-name><column-value><![CDATA[");
        sb.append(getParentCompanyMasterSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>recordLockStatus</column-name><column-value><![CDATA[");
        sb.append(getRecordLockStatus());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>priorParentStartDate</column-name><column-value><![CDATA[");
        sb.append(getPriorParentStartDate());
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
            "<column><column-name>companyParentDetailsSid</column-name><column-value><![CDATA[");
        sb.append(getCompanyParentDetailsSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>priorParentCmpyMasterSid</column-name><column-value><![CDATA[");
        sb.append(getPriorParentCmpyMasterSid());
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
            "<column><column-name>companyMasterSid</column-name><column-value><![CDATA[");
        sb.append(getCompanyMasterSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>parentStartDate</column-name><column-value><![CDATA[");
        sb.append(getParentStartDate());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
