package com.stpl.app.parttwo.model;

import com.stpl.app.parttwo.service.ClpSerializer;
import com.stpl.app.parttwo.service.VwCompanyIdentifierLocalServiceUtil;

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


public class VwCompanyIdentifierClp extends BaseModelImpl<VwCompanyIdentifier>
    implements VwCompanyIdentifier {
    private String _companyId;
    private String _companyName;
    private Date _endDate;
    private int _companyIdentifierSid;
    private Date _modifiedDate;
    private String _identifierStatus;
    private String _companyIdentifier;
    private String _entityCode;
    private Date _startDate;
    private Date _createdDate;
    private String _createdBy;
    private String _source;
    private String _companyNo;
    private String _batchId;
    private String _addChgDelIndicator;
    private String _identifierCodeQualifierName;
    private String _modifiedBy;
    private String _identifierCodeQualifier;
    private BaseModel<?> _vwCompanyIdentifierRemoteModel;

    public VwCompanyIdentifierClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return VwCompanyIdentifier.class;
    }

    @Override
    public String getModelClassName() {
        return VwCompanyIdentifier.class.getName();
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

        attributes.put("companyId", getCompanyId());
        attributes.put("companyName", getCompanyName());
        attributes.put("endDate", getEndDate());
        attributes.put("companyIdentifierSid", getCompanyIdentifierSid());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("identifierStatus", getIdentifierStatus());
        attributes.put("companyIdentifier", getCompanyIdentifier());
        attributes.put("entityCode", getEntityCode());
        attributes.put("startDate", getStartDate());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("source", getSource());
        attributes.put("companyNo", getCompanyNo());
        attributes.put("batchId", getBatchId());
        attributes.put("addChgDelIndicator", getAddChgDelIndicator());
        attributes.put("identifierCodeQualifierName",
            getIdentifierCodeQualifierName());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("identifierCodeQualifier", getIdentifierCodeQualifier());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        String companyId = (String) attributes.get("companyId");

        if (companyId != null) {
            setCompanyId(companyId);
        }

        String companyName = (String) attributes.get("companyName");

        if (companyName != null) {
            setCompanyName(companyName);
        }

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

        String identifierStatus = (String) attributes.get("identifierStatus");

        if (identifierStatus != null) {
            setIdentifierStatus(identifierStatus);
        }

        String companyIdentifier = (String) attributes.get("companyIdentifier");

        if (companyIdentifier != null) {
            setCompanyIdentifier(companyIdentifier);
        }

        String entityCode = (String) attributes.get("entityCode");

        if (entityCode != null) {
            setEntityCode(entityCode);
        }

        Date startDate = (Date) attributes.get("startDate");

        if (startDate != null) {
            setStartDate(startDate);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        String createdBy = (String) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        String source = (String) attributes.get("source");

        if (source != null) {
            setSource(source);
        }

        String companyNo = (String) attributes.get("companyNo");

        if (companyNo != null) {
            setCompanyNo(companyNo);
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

        String identifierCodeQualifierName = (String) attributes.get(
                "identifierCodeQualifierName");

        if (identifierCodeQualifierName != null) {
            setIdentifierCodeQualifierName(identifierCodeQualifierName);
        }

        String modifiedBy = (String) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        String identifierCodeQualifier = (String) attributes.get(
                "identifierCodeQualifier");

        if (identifierCodeQualifier != null) {
            setIdentifierCodeQualifier(identifierCodeQualifier);
        }
    }

    @Override
    public String getCompanyId() {
        return _companyId;
    }

    @Override
    public void setCompanyId(String companyId) {
        _companyId = companyId;

        if (_vwCompanyIdentifierRemoteModel != null) {
            try {
                Class<?> clazz = _vwCompanyIdentifierRemoteModel.getClass();

                Method method = clazz.getMethod("setCompanyId", String.class);

                method.invoke(_vwCompanyIdentifierRemoteModel, companyId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getCompanyName() {
        return _companyName;
    }

    @Override
    public void setCompanyName(String companyName) {
        _companyName = companyName;

        if (_vwCompanyIdentifierRemoteModel != null) {
            try {
                Class<?> clazz = _vwCompanyIdentifierRemoteModel.getClass();

                Method method = clazz.getMethod("setCompanyName", String.class);

                method.invoke(_vwCompanyIdentifierRemoteModel, companyName);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getEndDate() {
        return _endDate;
    }

    @Override
    public void setEndDate(Date endDate) {
        _endDate = endDate;

        if (_vwCompanyIdentifierRemoteModel != null) {
            try {
                Class<?> clazz = _vwCompanyIdentifierRemoteModel.getClass();

                Method method = clazz.getMethod("setEndDate", Date.class);

                method.invoke(_vwCompanyIdentifierRemoteModel, endDate);
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

        if (_vwCompanyIdentifierRemoteModel != null) {
            try {
                Class<?> clazz = _vwCompanyIdentifierRemoteModel.getClass();

                Method method = clazz.getMethod("setCompanyIdentifierSid",
                        int.class);

                method.invoke(_vwCompanyIdentifierRemoteModel,
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

        if (_vwCompanyIdentifierRemoteModel != null) {
            try {
                Class<?> clazz = _vwCompanyIdentifierRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedDate", Date.class);

                method.invoke(_vwCompanyIdentifierRemoteModel, modifiedDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getIdentifierStatus() {
        return _identifierStatus;
    }

    @Override
    public void setIdentifierStatus(String identifierStatus) {
        _identifierStatus = identifierStatus;

        if (_vwCompanyIdentifierRemoteModel != null) {
            try {
                Class<?> clazz = _vwCompanyIdentifierRemoteModel.getClass();

                Method method = clazz.getMethod("setIdentifierStatus",
                        String.class);

                method.invoke(_vwCompanyIdentifierRemoteModel, identifierStatus);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getCompanyIdentifier() {
        return _companyIdentifier;
    }

    @Override
    public void setCompanyIdentifier(String companyIdentifier) {
        _companyIdentifier = companyIdentifier;

        if (_vwCompanyIdentifierRemoteModel != null) {
            try {
                Class<?> clazz = _vwCompanyIdentifierRemoteModel.getClass();

                Method method = clazz.getMethod("setCompanyIdentifier",
                        String.class);

                method.invoke(_vwCompanyIdentifierRemoteModel, companyIdentifier);
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

        if (_vwCompanyIdentifierRemoteModel != null) {
            try {
                Class<?> clazz = _vwCompanyIdentifierRemoteModel.getClass();

                Method method = clazz.getMethod("setEntityCode", String.class);

                method.invoke(_vwCompanyIdentifierRemoteModel, entityCode);
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

        if (_vwCompanyIdentifierRemoteModel != null) {
            try {
                Class<?> clazz = _vwCompanyIdentifierRemoteModel.getClass();

                Method method = clazz.getMethod("setStartDate", Date.class);

                method.invoke(_vwCompanyIdentifierRemoteModel, startDate);
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

        if (_vwCompanyIdentifierRemoteModel != null) {
            try {
                Class<?> clazz = _vwCompanyIdentifierRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedDate", Date.class);

                method.invoke(_vwCompanyIdentifierRemoteModel, createdDate);
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

        if (_vwCompanyIdentifierRemoteModel != null) {
            try {
                Class<?> clazz = _vwCompanyIdentifierRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedBy", String.class);

                method.invoke(_vwCompanyIdentifierRemoteModel, createdBy);
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

        if (_vwCompanyIdentifierRemoteModel != null) {
            try {
                Class<?> clazz = _vwCompanyIdentifierRemoteModel.getClass();

                Method method = clazz.getMethod("setSource", String.class);

                method.invoke(_vwCompanyIdentifierRemoteModel, source);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getCompanyNo() {
        return _companyNo;
    }

    @Override
    public void setCompanyNo(String companyNo) {
        _companyNo = companyNo;

        if (_vwCompanyIdentifierRemoteModel != null) {
            try {
                Class<?> clazz = _vwCompanyIdentifierRemoteModel.getClass();

                Method method = clazz.getMethod("setCompanyNo", String.class);

                method.invoke(_vwCompanyIdentifierRemoteModel, companyNo);
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

        if (_vwCompanyIdentifierRemoteModel != null) {
            try {
                Class<?> clazz = _vwCompanyIdentifierRemoteModel.getClass();

                Method method = clazz.getMethod("setBatchId", String.class);

                method.invoke(_vwCompanyIdentifierRemoteModel, batchId);
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

        if (_vwCompanyIdentifierRemoteModel != null) {
            try {
                Class<?> clazz = _vwCompanyIdentifierRemoteModel.getClass();

                Method method = clazz.getMethod("setAddChgDelIndicator",
                        String.class);

                method.invoke(_vwCompanyIdentifierRemoteModel,
                    addChgDelIndicator);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getIdentifierCodeQualifierName() {
        return _identifierCodeQualifierName;
    }

    @Override
    public void setIdentifierCodeQualifierName(
        String identifierCodeQualifierName) {
        _identifierCodeQualifierName = identifierCodeQualifierName;

        if (_vwCompanyIdentifierRemoteModel != null) {
            try {
                Class<?> clazz = _vwCompanyIdentifierRemoteModel.getClass();

                Method method = clazz.getMethod("setIdentifierCodeQualifierName",
                        String.class);

                method.invoke(_vwCompanyIdentifierRemoteModel,
                    identifierCodeQualifierName);
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

        if (_vwCompanyIdentifierRemoteModel != null) {
            try {
                Class<?> clazz = _vwCompanyIdentifierRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedBy", String.class);

                method.invoke(_vwCompanyIdentifierRemoteModel, modifiedBy);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getIdentifierCodeQualifier() {
        return _identifierCodeQualifier;
    }

    @Override
    public void setIdentifierCodeQualifier(String identifierCodeQualifier) {
        _identifierCodeQualifier = identifierCodeQualifier;

        if (_vwCompanyIdentifierRemoteModel != null) {
            try {
                Class<?> clazz = _vwCompanyIdentifierRemoteModel.getClass();

                Method method = clazz.getMethod("setIdentifierCodeQualifier",
                        String.class);

                method.invoke(_vwCompanyIdentifierRemoteModel,
                    identifierCodeQualifier);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getVwCompanyIdentifierRemoteModel() {
        return _vwCompanyIdentifierRemoteModel;
    }

    public void setVwCompanyIdentifierRemoteModel(
        BaseModel<?> vwCompanyIdentifierRemoteModel) {
        _vwCompanyIdentifierRemoteModel = vwCompanyIdentifierRemoteModel;
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

        Class<?> remoteModelClass = _vwCompanyIdentifierRemoteModel.getClass();

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

        Object returnValue = method.invoke(_vwCompanyIdentifierRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            VwCompanyIdentifierLocalServiceUtil.addVwCompanyIdentifier(this);
        } else {
            VwCompanyIdentifierLocalServiceUtil.updateVwCompanyIdentifier(this);
        }
    }

    @Override
    public VwCompanyIdentifier toEscapedModel() {
        return (VwCompanyIdentifier) ProxyUtil.newProxyInstance(VwCompanyIdentifier.class.getClassLoader(),
            new Class[] { VwCompanyIdentifier.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        VwCompanyIdentifierClp clone = new VwCompanyIdentifierClp();

        clone.setCompanyId(getCompanyId());
        clone.setCompanyName(getCompanyName());
        clone.setEndDate(getEndDate());
        clone.setCompanyIdentifierSid(getCompanyIdentifierSid());
        clone.setModifiedDate(getModifiedDate());
        clone.setIdentifierStatus(getIdentifierStatus());
        clone.setCompanyIdentifier(getCompanyIdentifier());
        clone.setEntityCode(getEntityCode());
        clone.setStartDate(getStartDate());
        clone.setCreatedDate(getCreatedDate());
        clone.setCreatedBy(getCreatedBy());
        clone.setSource(getSource());
        clone.setCompanyNo(getCompanyNo());
        clone.setBatchId(getBatchId());
        clone.setAddChgDelIndicator(getAddChgDelIndicator());
        clone.setIdentifierCodeQualifierName(getIdentifierCodeQualifierName());
        clone.setModifiedBy(getModifiedBy());
        clone.setIdentifierCodeQualifier(getIdentifierCodeQualifier());

        return clone;
    }

    @Override
    public int compareTo(VwCompanyIdentifier vwCompanyIdentifier) {
        int primaryKey = vwCompanyIdentifier.getPrimaryKey();

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

        if (!(obj instanceof VwCompanyIdentifierClp)) {
            return false;
        }

        VwCompanyIdentifierClp vwCompanyIdentifier = (VwCompanyIdentifierClp) obj;

        int primaryKey = vwCompanyIdentifier.getPrimaryKey();

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

        sb.append("{companyId=");
        sb.append(getCompanyId());
        sb.append(", companyName=");
        sb.append(getCompanyName());
        sb.append(", endDate=");
        sb.append(getEndDate());
        sb.append(", companyIdentifierSid=");
        sb.append(getCompanyIdentifierSid());
        sb.append(", modifiedDate=");
        sb.append(getModifiedDate());
        sb.append(", identifierStatus=");
        sb.append(getIdentifierStatus());
        sb.append(", companyIdentifier=");
        sb.append(getCompanyIdentifier());
        sb.append(", entityCode=");
        sb.append(getEntityCode());
        sb.append(", startDate=");
        sb.append(getStartDate());
        sb.append(", createdDate=");
        sb.append(getCreatedDate());
        sb.append(", createdBy=");
        sb.append(getCreatedBy());
        sb.append(", source=");
        sb.append(getSource());
        sb.append(", companyNo=");
        sb.append(getCompanyNo());
        sb.append(", batchId=");
        sb.append(getBatchId());
        sb.append(", addChgDelIndicator=");
        sb.append(getAddChgDelIndicator());
        sb.append(", identifierCodeQualifierName=");
        sb.append(getIdentifierCodeQualifierName());
        sb.append(", modifiedBy=");
        sb.append(getModifiedBy());
        sb.append(", identifierCodeQualifier=");
        sb.append(getIdentifierCodeQualifier());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(58);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.parttwo.model.VwCompanyIdentifier");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>companyId</column-name><column-value><![CDATA[");
        sb.append(getCompanyId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>companyName</column-name><column-value><![CDATA[");
        sb.append(getCompanyName());
        sb.append("]]></column-value></column>");
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
            "<column><column-name>companyIdentifier</column-name><column-value><![CDATA[");
        sb.append(getCompanyIdentifier());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>entityCode</column-name><column-value><![CDATA[");
        sb.append(getEntityCode());
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
            "<column><column-name>createdBy</column-name><column-value><![CDATA[");
        sb.append(getCreatedBy());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>source</column-name><column-value><![CDATA[");
        sb.append(getSource());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>companyNo</column-name><column-value><![CDATA[");
        sb.append(getCompanyNo());
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
            "<column><column-name>identifierCodeQualifierName</column-name><column-value><![CDATA[");
        sb.append(getIdentifierCodeQualifierName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedBy</column-name><column-value><![CDATA[");
        sb.append(getModifiedBy());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>identifierCodeQualifier</column-name><column-value><![CDATA[");
        sb.append(getIdentifierCodeQualifier());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
