package com.stpl.app.model;

import com.stpl.app.service.ClpSerializer;
import com.stpl.app.service.CompanyQualifierLocalServiceUtil;

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


public class CompanyQualifierClp extends BaseModelImpl<CompanyQualifier>
    implements CompanyQualifier {
    private boolean _recordLockStatus;
    private Date _createdDate;
    private int _createdBy;
    private String _source;
    private String _companyQualifierValue;
    private String _batchId;
    private int _companyQualifierSid;
    private String _companyQualifierName;
    private String _effectiveDates;
    private String _notes;
    private int _modifiedBy;
    private String _inboundStatus;
    private Date _modifiedDate;
    private BaseModel<?> _companyQualifierRemoteModel;

    public CompanyQualifierClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return CompanyQualifier.class;
    }

    @Override
    public String getModelClassName() {
        return CompanyQualifier.class.getName();
    }

    @Override
    public int getPrimaryKey() {
        return _companyQualifierSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setCompanyQualifierSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _companyQualifierSid;
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
        attributes.put("companyQualifierValue", getCompanyQualifierValue());
        attributes.put("batchId", getBatchId());
        attributes.put("companyQualifierSid", getCompanyQualifierSid());
        attributes.put("companyQualifierName", getCompanyQualifierName());
        attributes.put("effectiveDates", getEffectiveDates());
        attributes.put("notes", getNotes());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("inboundStatus", getInboundStatus());
        attributes.put("modifiedDate", getModifiedDate());

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

        String companyQualifierValue = (String) attributes.get(
                "companyQualifierValue");

        if (companyQualifierValue != null) {
            setCompanyQualifierValue(companyQualifierValue);
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

        String companyQualifierName = (String) attributes.get(
                "companyQualifierName");

        if (companyQualifierName != null) {
            setCompanyQualifierName(companyQualifierName);
        }

        String effectiveDates = (String) attributes.get("effectiveDates");

        if (effectiveDates != null) {
            setEffectiveDates(effectiveDates);
        }

        String notes = (String) attributes.get("notes");

        if (notes != null) {
            setNotes(notes);
        }

        Integer modifiedBy = (Integer) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        String inboundStatus = (String) attributes.get("inboundStatus");

        if (inboundStatus != null) {
            setInboundStatus(inboundStatus);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
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

        if (_companyQualifierRemoteModel != null) {
            try {
                Class<?> clazz = _companyQualifierRemoteModel.getClass();

                Method method = clazz.getMethod("setRecordLockStatus",
                        boolean.class);

                method.invoke(_companyQualifierRemoteModel, recordLockStatus);
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

        if (_companyQualifierRemoteModel != null) {
            try {
                Class<?> clazz = _companyQualifierRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedDate", Date.class);

                method.invoke(_companyQualifierRemoteModel, createdDate);
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

        if (_companyQualifierRemoteModel != null) {
            try {
                Class<?> clazz = _companyQualifierRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedBy", int.class);

                method.invoke(_companyQualifierRemoteModel, createdBy);
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

        if (_companyQualifierRemoteModel != null) {
            try {
                Class<?> clazz = _companyQualifierRemoteModel.getClass();

                Method method = clazz.getMethod("setSource", String.class);

                method.invoke(_companyQualifierRemoteModel, source);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getCompanyQualifierValue() {
        return _companyQualifierValue;
    }

    @Override
    public void setCompanyQualifierValue(String companyQualifierValue) {
        _companyQualifierValue = companyQualifierValue;

        if (_companyQualifierRemoteModel != null) {
            try {
                Class<?> clazz = _companyQualifierRemoteModel.getClass();

                Method method = clazz.getMethod("setCompanyQualifierValue",
                        String.class);

                method.invoke(_companyQualifierRemoteModel,
                    companyQualifierValue);
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

        if (_companyQualifierRemoteModel != null) {
            try {
                Class<?> clazz = _companyQualifierRemoteModel.getClass();

                Method method = clazz.getMethod("setBatchId", String.class);

                method.invoke(_companyQualifierRemoteModel, batchId);
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

        if (_companyQualifierRemoteModel != null) {
            try {
                Class<?> clazz = _companyQualifierRemoteModel.getClass();

                Method method = clazz.getMethod("setCompanyQualifierSid",
                        int.class);

                method.invoke(_companyQualifierRemoteModel, companyQualifierSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getCompanyQualifierName() {
        return _companyQualifierName;
    }

    @Override
    public void setCompanyQualifierName(String companyQualifierName) {
        _companyQualifierName = companyQualifierName;

        if (_companyQualifierRemoteModel != null) {
            try {
                Class<?> clazz = _companyQualifierRemoteModel.getClass();

                Method method = clazz.getMethod("setCompanyQualifierName",
                        String.class);

                method.invoke(_companyQualifierRemoteModel, companyQualifierName);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getEffectiveDates() {
        return _effectiveDates;
    }

    @Override
    public void setEffectiveDates(String effectiveDates) {
        _effectiveDates = effectiveDates;

        if (_companyQualifierRemoteModel != null) {
            try {
                Class<?> clazz = _companyQualifierRemoteModel.getClass();

                Method method = clazz.getMethod("setEffectiveDates",
                        String.class);

                method.invoke(_companyQualifierRemoteModel, effectiveDates);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getNotes() {
        return _notes;
    }

    @Override
    public void setNotes(String notes) {
        _notes = notes;

        if (_companyQualifierRemoteModel != null) {
            try {
                Class<?> clazz = _companyQualifierRemoteModel.getClass();

                Method method = clazz.getMethod("setNotes", String.class);

                method.invoke(_companyQualifierRemoteModel, notes);
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

        if (_companyQualifierRemoteModel != null) {
            try {
                Class<?> clazz = _companyQualifierRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedBy", int.class);

                method.invoke(_companyQualifierRemoteModel, modifiedBy);
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

        if (_companyQualifierRemoteModel != null) {
            try {
                Class<?> clazz = _companyQualifierRemoteModel.getClass();

                Method method = clazz.getMethod("setInboundStatus", String.class);

                method.invoke(_companyQualifierRemoteModel, inboundStatus);
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

        if (_companyQualifierRemoteModel != null) {
            try {
                Class<?> clazz = _companyQualifierRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedDate", Date.class);

                method.invoke(_companyQualifierRemoteModel, modifiedDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getCompanyQualifierRemoteModel() {
        return _companyQualifierRemoteModel;
    }

    public void setCompanyQualifierRemoteModel(
        BaseModel<?> companyQualifierRemoteModel) {
        _companyQualifierRemoteModel = companyQualifierRemoteModel;
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

        Class<?> remoteModelClass = _companyQualifierRemoteModel.getClass();

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

        Object returnValue = method.invoke(_companyQualifierRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            CompanyQualifierLocalServiceUtil.addCompanyQualifier(this);
        } else {
            CompanyQualifierLocalServiceUtil.updateCompanyQualifier(this);
        }
    }

    @Override
    public CompanyQualifier toEscapedModel() {
        return (CompanyQualifier) ProxyUtil.newProxyInstance(CompanyQualifier.class.getClassLoader(),
            new Class[] { CompanyQualifier.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        CompanyQualifierClp clone = new CompanyQualifierClp();

        clone.setRecordLockStatus(getRecordLockStatus());
        clone.setCreatedDate(getCreatedDate());
        clone.setCreatedBy(getCreatedBy());
        clone.setSource(getSource());
        clone.setCompanyQualifierValue(getCompanyQualifierValue());
        clone.setBatchId(getBatchId());
        clone.setCompanyQualifierSid(getCompanyQualifierSid());
        clone.setCompanyQualifierName(getCompanyQualifierName());
        clone.setEffectiveDates(getEffectiveDates());
        clone.setNotes(getNotes());
        clone.setModifiedBy(getModifiedBy());
        clone.setInboundStatus(getInboundStatus());
        clone.setModifiedDate(getModifiedDate());

        return clone;
    }

    @Override
    public int compareTo(CompanyQualifier companyQualifier) {
        int primaryKey = companyQualifier.getPrimaryKey();

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

        if (!(obj instanceof CompanyQualifierClp)) {
            return false;
        }

        CompanyQualifierClp companyQualifier = (CompanyQualifierClp) obj;

        int primaryKey = companyQualifier.getPrimaryKey();

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
        sb.append(", companyQualifierValue=");
        sb.append(getCompanyQualifierValue());
        sb.append(", batchId=");
        sb.append(getBatchId());
        sb.append(", companyQualifierSid=");
        sb.append(getCompanyQualifierSid());
        sb.append(", companyQualifierName=");
        sb.append(getCompanyQualifierName());
        sb.append(", effectiveDates=");
        sb.append(getEffectiveDates());
        sb.append(", notes=");
        sb.append(getNotes());
        sb.append(", modifiedBy=");
        sb.append(getModifiedBy());
        sb.append(", inboundStatus=");
        sb.append(getInboundStatus());
        sb.append(", modifiedDate=");
        sb.append(getModifiedDate());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(43);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.CompanyQualifier");
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
            "<column><column-name>companyQualifierValue</column-name><column-value><![CDATA[");
        sb.append(getCompanyQualifierValue());
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
            "<column><column-name>companyQualifierName</column-name><column-value><![CDATA[");
        sb.append(getCompanyQualifierName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>effectiveDates</column-name><column-value><![CDATA[");
        sb.append(getEffectiveDates());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>notes</column-name><column-value><![CDATA[");
        sb.append(getNotes());
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
            "<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
        sb.append(getModifiedDate());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
