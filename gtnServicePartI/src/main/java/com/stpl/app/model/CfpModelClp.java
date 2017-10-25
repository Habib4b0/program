package com.stpl.app.model;

import com.stpl.app.service.CfpModelLocalServiceUtil;
import com.stpl.app.service.ClpSerializer;

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


public class CfpModelClp extends BaseModelImpl<CfpModel> implements CfpModel {
    private int _createdBy;
    private int _cfpType;
    private int _cfpTradeClass;
    private int _modifiedBy;
    private Date _createdDate;
    private String _cfpNo;
    private int _cfpModelSid;
    private String _batchId;
    private Date _modifiedDate;
    private boolean _recordLockStatus;
    private String _internalNotes;
    private String _cfpDesignation;
    private int _salesInclusion;
    private String _cfpName;
    private int _cfpCategory;
    private String _source;
    private String _cfpId;
    private int _cfpStatus;
    private int _parentCfpId;
    private Date _cfpStartDate;
    private Date _cfpEndDate;
    private String _parentCfpName;
    private String _inboundStatus;
    private BaseModel<?> _cfpModelRemoteModel;

    public CfpModelClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return CfpModel.class;
    }

    @Override
    public String getModelClassName() {
        return CfpModel.class.getName();
    }

    @Override
    public int getPrimaryKey() {
        return _cfpModelSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setCfpModelSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _cfpModelSid;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Integer) primaryKeyObj).intValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("createdBy", getCreatedBy());
        attributes.put("cfpType", getCfpType());
        attributes.put("cfpTradeClass", getCfpTradeClass());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("cfpNo", getCfpNo());
        attributes.put("cfpModelSid", getCfpModelSid());
        attributes.put("batchId", getBatchId());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("recordLockStatus", getRecordLockStatus());
        attributes.put("internalNotes", getInternalNotes());
        attributes.put("cfpDesignation", getCfpDesignation());
        attributes.put("salesInclusion", getSalesInclusion());
        attributes.put("cfpName", getCfpName());
        attributes.put("cfpCategory", getCfpCategory());
        attributes.put("source", getSource());
        attributes.put("cfpId", getCfpId());
        attributes.put("cfpStatus", getCfpStatus());
        attributes.put("parentCfpId", getParentCfpId());
        attributes.put("cfpStartDate", getCfpStartDate());
        attributes.put("cfpEndDate", getCfpEndDate());
        attributes.put("parentCfpName", getParentCfpName());
        attributes.put("inboundStatus", getInboundStatus());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Integer createdBy = (Integer) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        Integer cfpType = (Integer) attributes.get("cfpType");

        if (cfpType != null) {
            setCfpType(cfpType);
        }

        Integer cfpTradeClass = (Integer) attributes.get("cfpTradeClass");

        if (cfpTradeClass != null) {
            setCfpTradeClass(cfpTradeClass);
        }

        Integer modifiedBy = (Integer) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        String cfpNo = (String) attributes.get("cfpNo");

        if (cfpNo != null) {
            setCfpNo(cfpNo);
        }

        Integer cfpModelSid = (Integer) attributes.get("cfpModelSid");

        if (cfpModelSid != null) {
            setCfpModelSid(cfpModelSid);
        }

        String batchId = (String) attributes.get("batchId");

        if (batchId != null) {
            setBatchId(batchId);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        Boolean recordLockStatus = (Boolean) attributes.get("recordLockStatus");

        if (recordLockStatus != null) {
            setRecordLockStatus(recordLockStatus);
        }

        String internalNotes = (String) attributes.get("internalNotes");

        if (internalNotes != null) {
            setInternalNotes(internalNotes);
        }

        String cfpDesignation = (String) attributes.get("cfpDesignation");

        if (cfpDesignation != null) {
            setCfpDesignation(cfpDesignation);
        }

        Integer salesInclusion = (Integer) attributes.get("salesInclusion");

        if (salesInclusion != null) {
            setSalesInclusion(salesInclusion);
        }

        String cfpName = (String) attributes.get("cfpName");

        if (cfpName != null) {
            setCfpName(cfpName);
        }

        Integer cfpCategory = (Integer) attributes.get("cfpCategory");

        if (cfpCategory != null) {
            setCfpCategory(cfpCategory);
        }

        String source = (String) attributes.get("source");

        if (source != null) {
            setSource(source);
        }

        String cfpId = (String) attributes.get("cfpId");

        if (cfpId != null) {
            setCfpId(cfpId);
        }

        Integer cfpStatus = (Integer) attributes.get("cfpStatus");

        if (cfpStatus != null) {
            setCfpStatus(cfpStatus);
        }

        Integer parentCfpId = (Integer) attributes.get("parentCfpId");

        if (parentCfpId != null) {
            setParentCfpId(parentCfpId);
        }

        Date cfpStartDate = (Date) attributes.get("cfpStartDate");

        if (cfpStartDate != null) {
            setCfpStartDate(cfpStartDate);
        }

        Date cfpEndDate = (Date) attributes.get("cfpEndDate");

        if (cfpEndDate != null) {
            setCfpEndDate(cfpEndDate);
        }

        String parentCfpName = (String) attributes.get("parentCfpName");

        if (parentCfpName != null) {
            setParentCfpName(parentCfpName);
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

        if (_cfpModelRemoteModel != null) {
            try {
                Class<?> clazz = _cfpModelRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedBy", int.class);

                method.invoke(_cfpModelRemoteModel, createdBy);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getCfpType() {
        return _cfpType;
    }

    @Override
    public void setCfpType(int cfpType) {
        _cfpType = cfpType;

        if (_cfpModelRemoteModel != null) {
            try {
                Class<?> clazz = _cfpModelRemoteModel.getClass();

                Method method = clazz.getMethod("setCfpType", int.class);

                method.invoke(_cfpModelRemoteModel, cfpType);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getCfpTradeClass() {
        return _cfpTradeClass;
    }

    @Override
    public void setCfpTradeClass(int cfpTradeClass) {
        _cfpTradeClass = cfpTradeClass;

        if (_cfpModelRemoteModel != null) {
            try {
                Class<?> clazz = _cfpModelRemoteModel.getClass();

                Method method = clazz.getMethod("setCfpTradeClass", int.class);

                method.invoke(_cfpModelRemoteModel, cfpTradeClass);
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

        if (_cfpModelRemoteModel != null) {
            try {
                Class<?> clazz = _cfpModelRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedBy", int.class);

                method.invoke(_cfpModelRemoteModel, modifiedBy);
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

        if (_cfpModelRemoteModel != null) {
            try {
                Class<?> clazz = _cfpModelRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedDate", Date.class);

                method.invoke(_cfpModelRemoteModel, createdDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getCfpNo() {
        return _cfpNo;
    }

    @Override
    public void setCfpNo(String cfpNo) {
        _cfpNo = cfpNo;

        if (_cfpModelRemoteModel != null) {
            try {
                Class<?> clazz = _cfpModelRemoteModel.getClass();

                Method method = clazz.getMethod("setCfpNo", String.class);

                method.invoke(_cfpModelRemoteModel, cfpNo);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getCfpModelSid() {
        return _cfpModelSid;
    }

    @Override
    public void setCfpModelSid(int cfpModelSid) {
        _cfpModelSid = cfpModelSid;

        if (_cfpModelRemoteModel != null) {
            try {
                Class<?> clazz = _cfpModelRemoteModel.getClass();

                Method method = clazz.getMethod("setCfpModelSid", int.class);

                method.invoke(_cfpModelRemoteModel, cfpModelSid);
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

        if (_cfpModelRemoteModel != null) {
            try {
                Class<?> clazz = _cfpModelRemoteModel.getClass();

                Method method = clazz.getMethod("setBatchId", String.class);

                method.invoke(_cfpModelRemoteModel, batchId);
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

        if (_cfpModelRemoteModel != null) {
            try {
                Class<?> clazz = _cfpModelRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedDate", Date.class);

                method.invoke(_cfpModelRemoteModel, modifiedDate);
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

        if (_cfpModelRemoteModel != null) {
            try {
                Class<?> clazz = _cfpModelRemoteModel.getClass();

                Method method = clazz.getMethod("setRecordLockStatus",
                        boolean.class);

                method.invoke(_cfpModelRemoteModel, recordLockStatus);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getInternalNotes() {
        return _internalNotes;
    }

    @Override
    public void setInternalNotes(String internalNotes) {
        _internalNotes = internalNotes;

        if (_cfpModelRemoteModel != null) {
            try {
                Class<?> clazz = _cfpModelRemoteModel.getClass();

                Method method = clazz.getMethod("setInternalNotes", String.class);

                method.invoke(_cfpModelRemoteModel, internalNotes);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getCfpDesignation() {
        return _cfpDesignation;
    }

    @Override
    public void setCfpDesignation(String cfpDesignation) {
        _cfpDesignation = cfpDesignation;

        if (_cfpModelRemoteModel != null) {
            try {
                Class<?> clazz = _cfpModelRemoteModel.getClass();

                Method method = clazz.getMethod("setCfpDesignation",
                        String.class);

                method.invoke(_cfpModelRemoteModel, cfpDesignation);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getSalesInclusion() {
        return _salesInclusion;
    }

    @Override
    public void setSalesInclusion(int salesInclusion) {
        _salesInclusion = salesInclusion;

        if (_cfpModelRemoteModel != null) {
            try {
                Class<?> clazz = _cfpModelRemoteModel.getClass();

                Method method = clazz.getMethod("setSalesInclusion", int.class);

                method.invoke(_cfpModelRemoteModel, salesInclusion);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getCfpName() {
        return _cfpName;
    }

    @Override
    public void setCfpName(String cfpName) {
        _cfpName = cfpName;

        if (_cfpModelRemoteModel != null) {
            try {
                Class<?> clazz = _cfpModelRemoteModel.getClass();

                Method method = clazz.getMethod("setCfpName", String.class);

                method.invoke(_cfpModelRemoteModel, cfpName);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getCfpCategory() {
        return _cfpCategory;
    }

    @Override
    public void setCfpCategory(int cfpCategory) {
        _cfpCategory = cfpCategory;

        if (_cfpModelRemoteModel != null) {
            try {
                Class<?> clazz = _cfpModelRemoteModel.getClass();

                Method method = clazz.getMethod("setCfpCategory", int.class);

                method.invoke(_cfpModelRemoteModel, cfpCategory);
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

        if (_cfpModelRemoteModel != null) {
            try {
                Class<?> clazz = _cfpModelRemoteModel.getClass();

                Method method = clazz.getMethod("setSource", String.class);

                method.invoke(_cfpModelRemoteModel, source);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getCfpId() {
        return _cfpId;
    }

    @Override
    public void setCfpId(String cfpId) {
        _cfpId = cfpId;

        if (_cfpModelRemoteModel != null) {
            try {
                Class<?> clazz = _cfpModelRemoteModel.getClass();

                Method method = clazz.getMethod("setCfpId", String.class);

                method.invoke(_cfpModelRemoteModel, cfpId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getCfpStatus() {
        return _cfpStatus;
    }

    @Override
    public void setCfpStatus(int cfpStatus) {
        _cfpStatus = cfpStatus;

        if (_cfpModelRemoteModel != null) {
            try {
                Class<?> clazz = _cfpModelRemoteModel.getClass();

                Method method = clazz.getMethod("setCfpStatus", int.class);

                method.invoke(_cfpModelRemoteModel, cfpStatus);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getParentCfpId() {
        return _parentCfpId;
    }

    @Override
    public void setParentCfpId(int parentCfpId) {
        _parentCfpId = parentCfpId;

        if (_cfpModelRemoteModel != null) {
            try {
                Class<?> clazz = _cfpModelRemoteModel.getClass();

                Method method = clazz.getMethod("setParentCfpId", int.class);

                method.invoke(_cfpModelRemoteModel, parentCfpId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getCfpStartDate() {
        return _cfpStartDate;
    }

    @Override
    public void setCfpStartDate(Date cfpStartDate) {
        _cfpStartDate = cfpStartDate;

        if (_cfpModelRemoteModel != null) {
            try {
                Class<?> clazz = _cfpModelRemoteModel.getClass();

                Method method = clazz.getMethod("setCfpStartDate", Date.class);

                method.invoke(_cfpModelRemoteModel, cfpStartDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getCfpEndDate() {
        return _cfpEndDate;
    }

    @Override
    public void setCfpEndDate(Date cfpEndDate) {
        _cfpEndDate = cfpEndDate;

        if (_cfpModelRemoteModel != null) {
            try {
                Class<?> clazz = _cfpModelRemoteModel.getClass();

                Method method = clazz.getMethod("setCfpEndDate", Date.class);

                method.invoke(_cfpModelRemoteModel, cfpEndDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getParentCfpName() {
        return _parentCfpName;
    }

    @Override
    public void setParentCfpName(String parentCfpName) {
        _parentCfpName = parentCfpName;

        if (_cfpModelRemoteModel != null) {
            try {
                Class<?> clazz = _cfpModelRemoteModel.getClass();

                Method method = clazz.getMethod("setParentCfpName", String.class);

                method.invoke(_cfpModelRemoteModel, parentCfpName);
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

        if (_cfpModelRemoteModel != null) {
            try {
                Class<?> clazz = _cfpModelRemoteModel.getClass();

                Method method = clazz.getMethod("setInboundStatus", String.class);

                method.invoke(_cfpModelRemoteModel, inboundStatus);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getCfpModelRemoteModel() {
        return _cfpModelRemoteModel;
    }

    public void setCfpModelRemoteModel(BaseModel<?> cfpModelRemoteModel) {
        _cfpModelRemoteModel = cfpModelRemoteModel;
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

        Class<?> remoteModelClass = _cfpModelRemoteModel.getClass();

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

        Object returnValue = method.invoke(_cfpModelRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            CfpModelLocalServiceUtil.addCfpModel(this);
        } else {
            CfpModelLocalServiceUtil.updateCfpModel(this);
        }
    }

    @Override
    public CfpModel toEscapedModel() {
        return (CfpModel) ProxyUtil.newProxyInstance(CfpModel.class.getClassLoader(),
            new Class[] { CfpModel.class }, new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        CfpModelClp clone = new CfpModelClp();

        clone.setCreatedBy(getCreatedBy());
        clone.setCfpType(getCfpType());
        clone.setCfpTradeClass(getCfpTradeClass());
        clone.setModifiedBy(getModifiedBy());
        clone.setCreatedDate(getCreatedDate());
        clone.setCfpNo(getCfpNo());
        clone.setCfpModelSid(getCfpModelSid());
        clone.setBatchId(getBatchId());
        clone.setModifiedDate(getModifiedDate());
        clone.setRecordLockStatus(getRecordLockStatus());
        clone.setInternalNotes(getInternalNotes());
        clone.setCfpDesignation(getCfpDesignation());
        clone.setSalesInclusion(getSalesInclusion());
        clone.setCfpName(getCfpName());
        clone.setCfpCategory(getCfpCategory());
        clone.setSource(getSource());
        clone.setCfpId(getCfpId());
        clone.setCfpStatus(getCfpStatus());
        clone.setParentCfpId(getParentCfpId());
        clone.setCfpStartDate(getCfpStartDate());
        clone.setCfpEndDate(getCfpEndDate());
        clone.setParentCfpName(getParentCfpName());
        clone.setInboundStatus(getInboundStatus());

        return clone;
    }

    @Override
    public int compareTo(CfpModel cfpModel) {
        int primaryKey = cfpModel.getPrimaryKey();

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

        if (!(obj instanceof CfpModelClp)) {
            return false;
        }

        CfpModelClp cfpModel = (CfpModelClp) obj;

        int primaryKey = cfpModel.getPrimaryKey();

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
        StringBundler sb = new StringBundler(47);

        sb.append("{createdBy=");
        sb.append(getCreatedBy());
        sb.append(", cfpType=");
        sb.append(getCfpType());
        sb.append(", cfpTradeClass=");
        sb.append(getCfpTradeClass());
        sb.append(", modifiedBy=");
        sb.append(getModifiedBy());
        sb.append(", createdDate=");
        sb.append(getCreatedDate());
        sb.append(", cfpNo=");
        sb.append(getCfpNo());
        sb.append(", cfpModelSid=");
        sb.append(getCfpModelSid());
        sb.append(", batchId=");
        sb.append(getBatchId());
        sb.append(", modifiedDate=");
        sb.append(getModifiedDate());
        sb.append(", recordLockStatus=");
        sb.append(getRecordLockStatus());
        sb.append(", internalNotes=");
        sb.append(getInternalNotes());
        sb.append(", cfpDesignation=");
        sb.append(getCfpDesignation());
        sb.append(", salesInclusion=");
        sb.append(getSalesInclusion());
        sb.append(", cfpName=");
        sb.append(getCfpName());
        sb.append(", cfpCategory=");
        sb.append(getCfpCategory());
        sb.append(", source=");
        sb.append(getSource());
        sb.append(", cfpId=");
        sb.append(getCfpId());
        sb.append(", cfpStatus=");
        sb.append(getCfpStatus());
        sb.append(", parentCfpId=");
        sb.append(getParentCfpId());
        sb.append(", cfpStartDate=");
        sb.append(getCfpStartDate());
        sb.append(", cfpEndDate=");
        sb.append(getCfpEndDate());
        sb.append(", parentCfpName=");
        sb.append(getParentCfpName());
        sb.append(", inboundStatus=");
        sb.append(getInboundStatus());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(73);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.CfpModel");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>createdBy</column-name><column-value><![CDATA[");
        sb.append(getCreatedBy());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>cfpType</column-name><column-value><![CDATA[");
        sb.append(getCfpType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>cfpTradeClass</column-name><column-value><![CDATA[");
        sb.append(getCfpTradeClass());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedBy</column-name><column-value><![CDATA[");
        sb.append(getModifiedBy());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>createdDate</column-name><column-value><![CDATA[");
        sb.append(getCreatedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>cfpNo</column-name><column-value><![CDATA[");
        sb.append(getCfpNo());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>cfpModelSid</column-name><column-value><![CDATA[");
        sb.append(getCfpModelSid());
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
            "<column><column-name>recordLockStatus</column-name><column-value><![CDATA[");
        sb.append(getRecordLockStatus());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>internalNotes</column-name><column-value><![CDATA[");
        sb.append(getInternalNotes());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>cfpDesignation</column-name><column-value><![CDATA[");
        sb.append(getCfpDesignation());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>salesInclusion</column-name><column-value><![CDATA[");
        sb.append(getSalesInclusion());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>cfpName</column-name><column-value><![CDATA[");
        sb.append(getCfpName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>cfpCategory</column-name><column-value><![CDATA[");
        sb.append(getCfpCategory());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>source</column-name><column-value><![CDATA[");
        sb.append(getSource());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>cfpId</column-name><column-value><![CDATA[");
        sb.append(getCfpId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>cfpStatus</column-name><column-value><![CDATA[");
        sb.append(getCfpStatus());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>parentCfpId</column-name><column-value><![CDATA[");
        sb.append(getParentCfpId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>cfpStartDate</column-name><column-value><![CDATA[");
        sb.append(getCfpStartDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>cfpEndDate</column-name><column-value><![CDATA[");
        sb.append(getCfpEndDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>parentCfpName</column-name><column-value><![CDATA[");
        sb.append(getParentCfpName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>inboundStatus</column-name><column-value><![CDATA[");
        sb.append(getInboundStatus());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
