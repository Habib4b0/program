package com.stpl.app.model;

import com.stpl.app.service.ClpSerializer;
import com.stpl.app.service.CompanyIdentifierLocalServiceUtil;

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


public class CompanyIdentifierClp extends BaseModelImpl<CompanyIdentifier>
    implements CompanyIdentifier {
    private Date _endDate;
    private int _companyIdentifierSid;
    private Date _modifiedDate;
    private int _identifierStatus;
    private String _entityCode;
    private boolean _recordLockStatus;
    private Date _startDate;
    private Date _createdDate;
    private String _source;
    private int _createdBy;
    private String _companyIdentifierValue;
    private String _batchId;
    private int _companyQualifierSid;
    private int _modifiedBy;
    private String _inboundStatus;
    private int _companyMasterSid;
    private BaseModel<?> _companyIdentifierRemoteModel;

    public CompanyIdentifierClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return CompanyIdentifier.class;
    }

    @Override
    public String getModelClassName() {
        return CompanyIdentifier.class.getName();
    }

    @Override
    public int getPrimaryKey() {
        return _companyIdentifierSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setCompanyIdentifierSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _companyIdentifierSid;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Integer) primaryKeyObj).intValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("endDate", getEndDate());
        attributes.put("companyIdentifierSid", getCompanyIdentifierSid());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("identifierStatus", getIdentifierStatus());
        attributes.put("entityCode", getEntityCode());
        attributes.put("recordLockStatus", getRecordLockStatus());
        attributes.put("startDate", getStartDate());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("source", getSource());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("companyIdentifierValue", getCompanyIdentifierValue());
        attributes.put("batchId", getBatchId());
        attributes.put("companyQualifierSid", getCompanyQualifierSid());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("inboundStatus", getInboundStatus());
        attributes.put("companyMasterSid", getCompanyMasterSid());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Date endDate = (Date) attributes.get("endDate");

        if (endDate != null) {
            setEndDate(endDate);
        }

        Integer companyIdentifierSid = (Integer) attributes.get(
                "companyIdentifierSid");

        if (companyIdentifierSid != null) {
            setCompanyIdentifierSid(companyIdentifierSid);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        Integer identifierStatus = (Integer) attributes.get("identifierStatus");

        if (identifierStatus != null) {
            setIdentifierStatus(identifierStatus);
        }

        String entityCode = (String) attributes.get("entityCode");

        if (entityCode != null) {
            setEntityCode(entityCode);
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

        String companyIdentifierValue = (String) attributes.get(
                "companyIdentifierValue");

        if (companyIdentifierValue != null) {
            setCompanyIdentifierValue(companyIdentifierValue);
        }

        String batchId = (String) attributes.get("batchId");

        if (batchId != null) {
            setBatchId(batchId);
        }

        Integer companyQualifierSid = (Integer) attributes.get(
                "companyQualifierSid");

        if (companyQualifierSid != null) {
            setCompanyQualifierSid(companyQualifierSid);
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
    public Date getEndDate() {
        return _endDate;
    }

    @Override
    public void setEndDate(Date endDate) {
        _endDate = endDate;

        if (_companyIdentifierRemoteModel != null) {
            try {
                Class<?> clazz = _companyIdentifierRemoteModel.getClass();

                Method method = clazz.getMethod("setEndDate", Date.class);

                method.invoke(_companyIdentifierRemoteModel, endDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getCompanyIdentifierSid() {
        return _companyIdentifierSid;
    }

    @Override
    public void setCompanyIdentifierSid(int companyIdentifierSid) {
        _companyIdentifierSid = companyIdentifierSid;

        if (_companyIdentifierRemoteModel != null) {
            try {
                Class<?> clazz = _companyIdentifierRemoteModel.getClass();

                Method method = clazz.getMethod("setCompanyIdentifierSid",
                        int.class);

                method.invoke(_companyIdentifierRemoteModel,
                    companyIdentifierSid);
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

        if (_companyIdentifierRemoteModel != null) {
            try {
                Class<?> clazz = _companyIdentifierRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedDate", Date.class);

                method.invoke(_companyIdentifierRemoteModel, modifiedDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getIdentifierStatus() {
        return _identifierStatus;
    }

    @Override
    public void setIdentifierStatus(int identifierStatus) {
        _identifierStatus = identifierStatus;

        if (_companyIdentifierRemoteModel != null) {
            try {
                Class<?> clazz = _companyIdentifierRemoteModel.getClass();

                Method method = clazz.getMethod("setIdentifierStatus", int.class);

                method.invoke(_companyIdentifierRemoteModel, identifierStatus);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getEntityCode() {
        return _entityCode;
    }

    @Override
    public void setEntityCode(String entityCode) {
        _entityCode = entityCode;

        if (_companyIdentifierRemoteModel != null) {
            try {
                Class<?> clazz = _companyIdentifierRemoteModel.getClass();

                Method method = clazz.getMethod("setEntityCode", String.class);

                method.invoke(_companyIdentifierRemoteModel, entityCode);
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

        if (_companyIdentifierRemoteModel != null) {
            try {
                Class<?> clazz = _companyIdentifierRemoteModel.getClass();

                Method method = clazz.getMethod("setRecordLockStatus",
                        boolean.class);

                method.invoke(_companyIdentifierRemoteModel, recordLockStatus);
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

        if (_companyIdentifierRemoteModel != null) {
            try {
                Class<?> clazz = _companyIdentifierRemoteModel.getClass();

                Method method = clazz.getMethod("setStartDate", Date.class);

                method.invoke(_companyIdentifierRemoteModel, startDate);
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

        if (_companyIdentifierRemoteModel != null) {
            try {
                Class<?> clazz = _companyIdentifierRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedDate", Date.class);

                method.invoke(_companyIdentifierRemoteModel, createdDate);
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

        if (_companyIdentifierRemoteModel != null) {
            try {
                Class<?> clazz = _companyIdentifierRemoteModel.getClass();

                Method method = clazz.getMethod("setSource", String.class);

                method.invoke(_companyIdentifierRemoteModel, source);
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

        if (_companyIdentifierRemoteModel != null) {
            try {
                Class<?> clazz = _companyIdentifierRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedBy", int.class);

                method.invoke(_companyIdentifierRemoteModel, createdBy);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getCompanyIdentifierValue() {
        return _companyIdentifierValue;
    }

    @Override
    public void setCompanyIdentifierValue(String companyIdentifierValue) {
        _companyIdentifierValue = companyIdentifierValue;

        if (_companyIdentifierRemoteModel != null) {
            try {
                Class<?> clazz = _companyIdentifierRemoteModel.getClass();

                Method method = clazz.getMethod("setCompanyIdentifierValue",
                        String.class);

                method.invoke(_companyIdentifierRemoteModel,
                    companyIdentifierValue);
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

        if (_companyIdentifierRemoteModel != null) {
            try {
                Class<?> clazz = _companyIdentifierRemoteModel.getClass();

                Method method = clazz.getMethod("setBatchId", String.class);

                method.invoke(_companyIdentifierRemoteModel, batchId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getCompanyQualifierSid() {
        return _companyQualifierSid;
    }

    @Override
    public void setCompanyQualifierSid(int companyQualifierSid) {
        _companyQualifierSid = companyQualifierSid;

        if (_companyIdentifierRemoteModel != null) {
            try {
                Class<?> clazz = _companyIdentifierRemoteModel.getClass();

                Method method = clazz.getMethod("setCompanyQualifierSid",
                        int.class);

                method.invoke(_companyIdentifierRemoteModel, companyQualifierSid);
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

        if (_companyIdentifierRemoteModel != null) {
            try {
                Class<?> clazz = _companyIdentifierRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedBy", int.class);

                method.invoke(_companyIdentifierRemoteModel, modifiedBy);
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

        if (_companyIdentifierRemoteModel != null) {
            try {
                Class<?> clazz = _companyIdentifierRemoteModel.getClass();

                Method method = clazz.getMethod("setInboundStatus", String.class);

                method.invoke(_companyIdentifierRemoteModel, inboundStatus);
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

        if (_companyIdentifierRemoteModel != null) {
            try {
                Class<?> clazz = _companyIdentifierRemoteModel.getClass();

                Method method = clazz.getMethod("setCompanyMasterSid", int.class);

                method.invoke(_companyIdentifierRemoteModel, companyMasterSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getCompanyIdentifierRemoteModel() {
        return _companyIdentifierRemoteModel;
    }

    public void setCompanyIdentifierRemoteModel(
        BaseModel<?> companyIdentifierRemoteModel) {
        _companyIdentifierRemoteModel = companyIdentifierRemoteModel;
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

        Class<?> remoteModelClass = _companyIdentifierRemoteModel.getClass();

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

        Object returnValue = method.invoke(_companyIdentifierRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            CompanyIdentifierLocalServiceUtil.addCompanyIdentifier(this);
        } else {
            CompanyIdentifierLocalServiceUtil.updateCompanyIdentifier(this);
        }
    }

    @Override
    public CompanyIdentifier toEscapedModel() {
        return (CompanyIdentifier) ProxyUtil.newProxyInstance(CompanyIdentifier.class.getClassLoader(),
            new Class[] { CompanyIdentifier.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        CompanyIdentifierClp clone = new CompanyIdentifierClp();

        clone.setEndDate(getEndDate());
        clone.setCompanyIdentifierSid(getCompanyIdentifierSid());
        clone.setModifiedDate(getModifiedDate());
        clone.setIdentifierStatus(getIdentifierStatus());
        clone.setEntityCode(getEntityCode());
        clone.setRecordLockStatus(getRecordLockStatus());
        clone.setStartDate(getStartDate());
        clone.setCreatedDate(getCreatedDate());
        clone.setSource(getSource());
        clone.setCreatedBy(getCreatedBy());
        clone.setCompanyIdentifierValue(getCompanyIdentifierValue());
        clone.setBatchId(getBatchId());
        clone.setCompanyQualifierSid(getCompanyQualifierSid());
        clone.setModifiedBy(getModifiedBy());
        clone.setInboundStatus(getInboundStatus());
        clone.setCompanyMasterSid(getCompanyMasterSid());

        return clone;
    }

    @Override
    public int compareTo(CompanyIdentifier companyIdentifier) {
        int primaryKey = companyIdentifier.getPrimaryKey();

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

        if (!(obj instanceof CompanyIdentifierClp)) {
            return false;
        }

        CompanyIdentifierClp companyIdentifier = (CompanyIdentifierClp) obj;

        int primaryKey = companyIdentifier.getPrimaryKey();

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

        sb.append("{endDate=");
        sb.append(getEndDate());
        sb.append(", companyIdentifierSid=");
        sb.append(getCompanyIdentifierSid());
        sb.append(", modifiedDate=");
        sb.append(getModifiedDate());
        sb.append(", identifierStatus=");
        sb.append(getIdentifierStatus());
        sb.append(", entityCode=");
        sb.append(getEntityCode());
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
        sb.append(", companyIdentifierValue=");
        sb.append(getCompanyIdentifierValue());
        sb.append(", batchId=");
        sb.append(getBatchId());
        sb.append(", companyQualifierSid=");
        sb.append(getCompanyQualifierSid());
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
        sb.append("com.stpl.app.model.CompanyIdentifier");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>endDate</column-name><column-value><![CDATA[");
        sb.append(getEndDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>companyIdentifierSid</column-name><column-value><![CDATA[");
        sb.append(getCompanyIdentifierSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
        sb.append(getModifiedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>identifierStatus</column-name><column-value><![CDATA[");
        sb.append(getIdentifierStatus());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>entityCode</column-name><column-value><![CDATA[");
        sb.append(getEntityCode());
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
            "<column><column-name>companyIdentifierValue</column-name><column-value><![CDATA[");
        sb.append(getCompanyIdentifierValue());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>batchId</column-name><column-value><![CDATA[");
        sb.append(getBatchId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>companyQualifierSid</column-name><column-value><![CDATA[");
        sb.append(getCompanyQualifierSid());
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
