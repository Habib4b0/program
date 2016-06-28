package com.stpl.app.model;

import com.stpl.app.service.ClpSerializer;
import com.stpl.app.service.GlCostCenterMasterLocalServiceUtil;

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


public class GlCostCenterMasterClp extends BaseModelImpl<GlCostCenterMaster>
    implements GlCostCenterMaster {
    private int _createdBy;
    private int _modifiedBy;
    private Date _uploadDate;
    private Date _createdDate;
    private int _glCostCenterMasterSid;
    private String _batchId;
    private Date _modifiedDate;
    private String _ndc8;
    private boolean _recordLockStatus;
    private String _companyCode;
    private String _source;
    private String _companyCostCenter;
    private String _inboundStatus;
    private BaseModel<?> _glCostCenterMasterRemoteModel;

    public GlCostCenterMasterClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return GlCostCenterMaster.class;
    }

    @Override
    public String getModelClassName() {
        return GlCostCenterMaster.class.getName();
    }

    @Override
    public int getPrimaryKey() {
        return _glCostCenterMasterSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setGlCostCenterMasterSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _glCostCenterMasterSid;
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
        attributes.put("uploadDate", getUploadDate());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("glCostCenterMasterSid", getGlCostCenterMasterSid());
        attributes.put("batchId", getBatchId());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("ndc8", getNdc8());
        attributes.put("recordLockStatus", getRecordLockStatus());
        attributes.put("companyCode", getCompanyCode());
        attributes.put("source", getSource());
        attributes.put("companyCostCenter", getCompanyCostCenter());
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

        Date uploadDate = (Date) attributes.get("uploadDate");

        if (uploadDate != null) {
            setUploadDate(uploadDate);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        Integer glCostCenterMasterSid = (Integer) attributes.get(
                "glCostCenterMasterSid");

        if (glCostCenterMasterSid != null) {
            setGlCostCenterMasterSid(glCostCenterMasterSid);
        }

        String batchId = (String) attributes.get("batchId");

        if (batchId != null) {
            setBatchId(batchId);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        String ndc8 = (String) attributes.get("ndc8");

        if (ndc8 != null) {
            setNdc8(ndc8);
        }

        Boolean recordLockStatus = (Boolean) attributes.get("recordLockStatus");

        if (recordLockStatus != null) {
            setRecordLockStatus(recordLockStatus);
        }

        String companyCode = (String) attributes.get("companyCode");

        if (companyCode != null) {
            setCompanyCode(companyCode);
        }

        String source = (String) attributes.get("source");

        if (source != null) {
            setSource(source);
        }

        String companyCostCenter = (String) attributes.get("companyCostCenter");

        if (companyCostCenter != null) {
            setCompanyCostCenter(companyCostCenter);
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

        if (_glCostCenterMasterRemoteModel != null) {
            try {
                Class<?> clazz = _glCostCenterMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedBy", int.class);

                method.invoke(_glCostCenterMasterRemoteModel, createdBy);
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

        if (_glCostCenterMasterRemoteModel != null) {
            try {
                Class<?> clazz = _glCostCenterMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedBy", int.class);

                method.invoke(_glCostCenterMasterRemoteModel, modifiedBy);
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

        if (_glCostCenterMasterRemoteModel != null) {
            try {
                Class<?> clazz = _glCostCenterMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setUploadDate", Date.class);

                method.invoke(_glCostCenterMasterRemoteModel, uploadDate);
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

        if (_glCostCenterMasterRemoteModel != null) {
            try {
                Class<?> clazz = _glCostCenterMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedDate", Date.class);

                method.invoke(_glCostCenterMasterRemoteModel, createdDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getGlCostCenterMasterSid() {
        return _glCostCenterMasterSid;
    }

    @Override
    public void setGlCostCenterMasterSid(int glCostCenterMasterSid) {
        _glCostCenterMasterSid = glCostCenterMasterSid;

        if (_glCostCenterMasterRemoteModel != null) {
            try {
                Class<?> clazz = _glCostCenterMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setGlCostCenterMasterSid",
                        int.class);

                method.invoke(_glCostCenterMasterRemoteModel,
                    glCostCenterMasterSid);
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

        if (_glCostCenterMasterRemoteModel != null) {
            try {
                Class<?> clazz = _glCostCenterMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setBatchId", String.class);

                method.invoke(_glCostCenterMasterRemoteModel, batchId);
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

        if (_glCostCenterMasterRemoteModel != null) {
            try {
                Class<?> clazz = _glCostCenterMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedDate", Date.class);

                method.invoke(_glCostCenterMasterRemoteModel, modifiedDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getNdc8() {
        return _ndc8;
    }

    @Override
    public void setNdc8(String ndc8) {
        _ndc8 = ndc8;

        if (_glCostCenterMasterRemoteModel != null) {
            try {
                Class<?> clazz = _glCostCenterMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setNdc8", String.class);

                method.invoke(_glCostCenterMasterRemoteModel, ndc8);
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

        if (_glCostCenterMasterRemoteModel != null) {
            try {
                Class<?> clazz = _glCostCenterMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setRecordLockStatus",
                        boolean.class);

                method.invoke(_glCostCenterMasterRemoteModel, recordLockStatus);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getCompanyCode() {
        return _companyCode;
    }

    @Override
    public void setCompanyCode(String companyCode) {
        _companyCode = companyCode;

        if (_glCostCenterMasterRemoteModel != null) {
            try {
                Class<?> clazz = _glCostCenterMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCompanyCode", String.class);

                method.invoke(_glCostCenterMasterRemoteModel, companyCode);
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

        if (_glCostCenterMasterRemoteModel != null) {
            try {
                Class<?> clazz = _glCostCenterMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setSource", String.class);

                method.invoke(_glCostCenterMasterRemoteModel, source);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getCompanyCostCenter() {
        return _companyCostCenter;
    }

    @Override
    public void setCompanyCostCenter(String companyCostCenter) {
        _companyCostCenter = companyCostCenter;

        if (_glCostCenterMasterRemoteModel != null) {
            try {
                Class<?> clazz = _glCostCenterMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCompanyCostCenter",
                        String.class);

                method.invoke(_glCostCenterMasterRemoteModel, companyCostCenter);
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

        if (_glCostCenterMasterRemoteModel != null) {
            try {
                Class<?> clazz = _glCostCenterMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setInboundStatus", String.class);

                method.invoke(_glCostCenterMasterRemoteModel, inboundStatus);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getGlCostCenterMasterRemoteModel() {
        return _glCostCenterMasterRemoteModel;
    }

    public void setGlCostCenterMasterRemoteModel(
        BaseModel<?> glCostCenterMasterRemoteModel) {
        _glCostCenterMasterRemoteModel = glCostCenterMasterRemoteModel;
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

        Class<?> remoteModelClass = _glCostCenterMasterRemoteModel.getClass();

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

        Object returnValue = method.invoke(_glCostCenterMasterRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            GlCostCenterMasterLocalServiceUtil.addGlCostCenterMaster(this);
        } else {
            GlCostCenterMasterLocalServiceUtil.updateGlCostCenterMaster(this);
        }
    }

    @Override
    public GlCostCenterMaster toEscapedModel() {
        return (GlCostCenterMaster) ProxyUtil.newProxyInstance(GlCostCenterMaster.class.getClassLoader(),
            new Class[] { GlCostCenterMaster.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        GlCostCenterMasterClp clone = new GlCostCenterMasterClp();

        clone.setCreatedBy(getCreatedBy());
        clone.setModifiedBy(getModifiedBy());
        clone.setUploadDate(getUploadDate());
        clone.setCreatedDate(getCreatedDate());
        clone.setGlCostCenterMasterSid(getGlCostCenterMasterSid());
        clone.setBatchId(getBatchId());
        clone.setModifiedDate(getModifiedDate());
        clone.setNdc8(getNdc8());
        clone.setRecordLockStatus(getRecordLockStatus());
        clone.setCompanyCode(getCompanyCode());
        clone.setSource(getSource());
        clone.setCompanyCostCenter(getCompanyCostCenter());
        clone.setInboundStatus(getInboundStatus());

        return clone;
    }

    @Override
    public int compareTo(GlCostCenterMaster glCostCenterMaster) {
        int primaryKey = glCostCenterMaster.getPrimaryKey();

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

        if (!(obj instanceof GlCostCenterMasterClp)) {
            return false;
        }

        GlCostCenterMasterClp glCostCenterMaster = (GlCostCenterMasterClp) obj;

        int primaryKey = glCostCenterMaster.getPrimaryKey();

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
        sb.append(", uploadDate=");
        sb.append(getUploadDate());
        sb.append(", createdDate=");
        sb.append(getCreatedDate());
        sb.append(", glCostCenterMasterSid=");
        sb.append(getGlCostCenterMasterSid());
        sb.append(", batchId=");
        sb.append(getBatchId());
        sb.append(", modifiedDate=");
        sb.append(getModifiedDate());
        sb.append(", ndc8=");
        sb.append(getNdc8());
        sb.append(", recordLockStatus=");
        sb.append(getRecordLockStatus());
        sb.append(", companyCode=");
        sb.append(getCompanyCode());
        sb.append(", source=");
        sb.append(getSource());
        sb.append(", companyCostCenter=");
        sb.append(getCompanyCostCenter());
        sb.append(", inboundStatus=");
        sb.append(getInboundStatus());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(43);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.GlCostCenterMaster");
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
            "<column><column-name>uploadDate</column-name><column-value><![CDATA[");
        sb.append(getUploadDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>createdDate</column-name><column-value><![CDATA[");
        sb.append(getCreatedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>glCostCenterMasterSid</column-name><column-value><![CDATA[");
        sb.append(getGlCostCenterMasterSid());
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
            "<column><column-name>ndc8</column-name><column-value><![CDATA[");
        sb.append(getNdc8());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>recordLockStatus</column-name><column-value><![CDATA[");
        sb.append(getRecordLockStatus());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>companyCode</column-name><column-value><![CDATA[");
        sb.append(getCompanyCode());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>source</column-name><column-value><![CDATA[");
        sb.append(getSource());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>companyCostCenter</column-name><column-value><![CDATA[");
        sb.append(getCompanyCostCenter());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>inboundStatus</column-name><column-value><![CDATA[");
        sb.append(getInboundStatus());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
