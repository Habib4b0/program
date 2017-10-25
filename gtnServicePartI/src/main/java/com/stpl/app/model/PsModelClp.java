package com.stpl.app.model;

import com.stpl.app.service.ClpSerializer;
import com.stpl.app.service.PsModelLocalServiceUtil;

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


public class PsModelClp extends BaseModelImpl<PsModel> implements PsModel {
    private String _psId;
    private String _psName;
    private int _psType;
    private Date _modifiedDate;
    private int _psCategory;
    private boolean _recordLockStatus;
    private int _psStatus;
    private Date _createdDate;
    private int _createdBy;
    private String _source;
    private String _psNo;
    private String _psDesignation;
    private String _parentPsId;
    private String _batchId;
    private int _psModelSid;
    private Date _psEndDate;
    private int _psTradeClass;
    private int _modifiedBy;
    private String _inboundStatus;
    private Date _psStartDate;
    private String _parentPsName;
    private String _internalNotes;
    private BaseModel<?> _psModelRemoteModel;

    public PsModelClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return PsModel.class;
    }

    @Override
    public String getModelClassName() {
        return PsModel.class.getName();
    }

    @Override
    public int getPrimaryKey() {
        return _psModelSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setPsModelSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _psModelSid;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Integer) primaryKeyObj).intValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("psId", getPsId());
        attributes.put("psName", getPsName());
        attributes.put("psType", getPsType());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("psCategory", getPsCategory());
        attributes.put("recordLockStatus", getRecordLockStatus());
        attributes.put("psStatus", getPsStatus());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("source", getSource());
        attributes.put("psNo", getPsNo());
        attributes.put("psDesignation", getPsDesignation());
        attributes.put("parentPsId", getParentPsId());
        attributes.put("batchId", getBatchId());
        attributes.put("psModelSid", getPsModelSid());
        attributes.put("psEndDate", getPsEndDate());
        attributes.put("psTradeClass", getPsTradeClass());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("inboundStatus", getInboundStatus());
        attributes.put("psStartDate", getPsStartDate());
        attributes.put("parentPsName", getParentPsName());
        attributes.put("internalNotes", getInternalNotes());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        String psId = (String) attributes.get("psId");

        if (psId != null) {
            setPsId(psId);
        }

        String psName = (String) attributes.get("psName");

        if (psName != null) {
            setPsName(psName);
        }

        Integer psType = (Integer) attributes.get("psType");

        if (psType != null) {
            setPsType(psType);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        Integer psCategory = (Integer) attributes.get("psCategory");

        if (psCategory != null) {
            setPsCategory(psCategory);
        }

        Boolean recordLockStatus = (Boolean) attributes.get("recordLockStatus");

        if (recordLockStatus != null) {
            setRecordLockStatus(recordLockStatus);
        }

        Integer psStatus = (Integer) attributes.get("psStatus");

        if (psStatus != null) {
            setPsStatus(psStatus);
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

        String psNo = (String) attributes.get("psNo");

        if (psNo != null) {
            setPsNo(psNo);
        }

        String psDesignation = (String) attributes.get("psDesignation");

        if (psDesignation != null) {
            setPsDesignation(psDesignation);
        }

        String parentPsId = (String) attributes.get("parentPsId");

        if (parentPsId != null) {
            setParentPsId(parentPsId);
        }

        String batchId = (String) attributes.get("batchId");

        if (batchId != null) {
            setBatchId(batchId);
        }

        Integer psModelSid = (Integer) attributes.get("psModelSid");

        if (psModelSid != null) {
            setPsModelSid(psModelSid);
        }

        Date psEndDate = (Date) attributes.get("psEndDate");

        if (psEndDate != null) {
            setPsEndDate(psEndDate);
        }

        Integer psTradeClass = (Integer) attributes.get("psTradeClass");

        if (psTradeClass != null) {
            setPsTradeClass(psTradeClass);
        }

        Integer modifiedBy = (Integer) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        String inboundStatus = (String) attributes.get("inboundStatus");

        if (inboundStatus != null) {
            setInboundStatus(inboundStatus);
        }

        Date psStartDate = (Date) attributes.get("psStartDate");

        if (psStartDate != null) {
            setPsStartDate(psStartDate);
        }

        String parentPsName = (String) attributes.get("parentPsName");

        if (parentPsName != null) {
            setParentPsName(parentPsName);
        }

        String internalNotes = (String) attributes.get("internalNotes");

        if (internalNotes != null) {
            setInternalNotes(internalNotes);
        }
    }

    @Override
    public String getPsId() {
        return _psId;
    }

    @Override
    public void setPsId(String psId) {
        _psId = psId;

        if (_psModelRemoteModel != null) {
            try {
                Class<?> clazz = _psModelRemoteModel.getClass();

                Method method = clazz.getMethod("setPsId", String.class);

                method.invoke(_psModelRemoteModel, psId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getPsName() {
        return _psName;
    }

    @Override
    public void setPsName(String psName) {
        _psName = psName;

        if (_psModelRemoteModel != null) {
            try {
                Class<?> clazz = _psModelRemoteModel.getClass();

                Method method = clazz.getMethod("setPsName", String.class);

                method.invoke(_psModelRemoteModel, psName);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getPsType() {
        return _psType;
    }

    @Override
    public void setPsType(int psType) {
        _psType = psType;

        if (_psModelRemoteModel != null) {
            try {
                Class<?> clazz = _psModelRemoteModel.getClass();

                Method method = clazz.getMethod("setPsType", int.class);

                method.invoke(_psModelRemoteModel, psType);
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

        if (_psModelRemoteModel != null) {
            try {
                Class<?> clazz = _psModelRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedDate", Date.class);

                method.invoke(_psModelRemoteModel, modifiedDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getPsCategory() {
        return _psCategory;
    }

    @Override
    public void setPsCategory(int psCategory) {
        _psCategory = psCategory;

        if (_psModelRemoteModel != null) {
            try {
                Class<?> clazz = _psModelRemoteModel.getClass();

                Method method = clazz.getMethod("setPsCategory", int.class);

                method.invoke(_psModelRemoteModel, psCategory);
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

        if (_psModelRemoteModel != null) {
            try {
                Class<?> clazz = _psModelRemoteModel.getClass();

                Method method = clazz.getMethod("setRecordLockStatus",
                        boolean.class);

                method.invoke(_psModelRemoteModel, recordLockStatus);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getPsStatus() {
        return _psStatus;
    }

    @Override
    public void setPsStatus(int psStatus) {
        _psStatus = psStatus;

        if (_psModelRemoteModel != null) {
            try {
                Class<?> clazz = _psModelRemoteModel.getClass();

                Method method = clazz.getMethod("setPsStatus", int.class);

                method.invoke(_psModelRemoteModel, psStatus);
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

        if (_psModelRemoteModel != null) {
            try {
                Class<?> clazz = _psModelRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedDate", Date.class);

                method.invoke(_psModelRemoteModel, createdDate);
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

        if (_psModelRemoteModel != null) {
            try {
                Class<?> clazz = _psModelRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedBy", int.class);

                method.invoke(_psModelRemoteModel, createdBy);
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

        if (_psModelRemoteModel != null) {
            try {
                Class<?> clazz = _psModelRemoteModel.getClass();

                Method method = clazz.getMethod("setSource", String.class);

                method.invoke(_psModelRemoteModel, source);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getPsNo() {
        return _psNo;
    }

    @Override
    public void setPsNo(String psNo) {
        _psNo = psNo;

        if (_psModelRemoteModel != null) {
            try {
                Class<?> clazz = _psModelRemoteModel.getClass();

                Method method = clazz.getMethod("setPsNo", String.class);

                method.invoke(_psModelRemoteModel, psNo);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getPsDesignation() {
        return _psDesignation;
    }

    @Override
    public void setPsDesignation(String psDesignation) {
        _psDesignation = psDesignation;

        if (_psModelRemoteModel != null) {
            try {
                Class<?> clazz = _psModelRemoteModel.getClass();

                Method method = clazz.getMethod("setPsDesignation", String.class);

                method.invoke(_psModelRemoteModel, psDesignation);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getParentPsId() {
        return _parentPsId;
    }

    @Override
    public void setParentPsId(String parentPsId) {
        _parentPsId = parentPsId;

        if (_psModelRemoteModel != null) {
            try {
                Class<?> clazz = _psModelRemoteModel.getClass();

                Method method = clazz.getMethod("setParentPsId", String.class);

                method.invoke(_psModelRemoteModel, parentPsId);
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

        if (_psModelRemoteModel != null) {
            try {
                Class<?> clazz = _psModelRemoteModel.getClass();

                Method method = clazz.getMethod("setBatchId", String.class);

                method.invoke(_psModelRemoteModel, batchId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getPsModelSid() {
        return _psModelSid;
    }

    @Override
    public void setPsModelSid(int psModelSid) {
        _psModelSid = psModelSid;

        if (_psModelRemoteModel != null) {
            try {
                Class<?> clazz = _psModelRemoteModel.getClass();

                Method method = clazz.getMethod("setPsModelSid", int.class);

                method.invoke(_psModelRemoteModel, psModelSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getPsEndDate() {
        return _psEndDate;
    }

    @Override
    public void setPsEndDate(Date psEndDate) {
        _psEndDate = psEndDate;

        if (_psModelRemoteModel != null) {
            try {
                Class<?> clazz = _psModelRemoteModel.getClass();

                Method method = clazz.getMethod("setPsEndDate", Date.class);

                method.invoke(_psModelRemoteModel, psEndDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getPsTradeClass() {
        return _psTradeClass;
    }

    @Override
    public void setPsTradeClass(int psTradeClass) {
        _psTradeClass = psTradeClass;

        if (_psModelRemoteModel != null) {
            try {
                Class<?> clazz = _psModelRemoteModel.getClass();

                Method method = clazz.getMethod("setPsTradeClass", int.class);

                method.invoke(_psModelRemoteModel, psTradeClass);
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

        if (_psModelRemoteModel != null) {
            try {
                Class<?> clazz = _psModelRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedBy", int.class);

                method.invoke(_psModelRemoteModel, modifiedBy);
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

        if (_psModelRemoteModel != null) {
            try {
                Class<?> clazz = _psModelRemoteModel.getClass();

                Method method = clazz.getMethod("setInboundStatus", String.class);

                method.invoke(_psModelRemoteModel, inboundStatus);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getPsStartDate() {
        return _psStartDate;
    }

    @Override
    public void setPsStartDate(Date psStartDate) {
        _psStartDate = psStartDate;

        if (_psModelRemoteModel != null) {
            try {
                Class<?> clazz = _psModelRemoteModel.getClass();

                Method method = clazz.getMethod("setPsStartDate", Date.class);

                method.invoke(_psModelRemoteModel, psStartDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getParentPsName() {
        return _parentPsName;
    }

    @Override
    public void setParentPsName(String parentPsName) {
        _parentPsName = parentPsName;

        if (_psModelRemoteModel != null) {
            try {
                Class<?> clazz = _psModelRemoteModel.getClass();

                Method method = clazz.getMethod("setParentPsName", String.class);

                method.invoke(_psModelRemoteModel, parentPsName);
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

        if (_psModelRemoteModel != null) {
            try {
                Class<?> clazz = _psModelRemoteModel.getClass();

                Method method = clazz.getMethod("setInternalNotes", String.class);

                method.invoke(_psModelRemoteModel, internalNotes);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getPsModelRemoteModel() {
        return _psModelRemoteModel;
    }

    public void setPsModelRemoteModel(BaseModel<?> psModelRemoteModel) {
        _psModelRemoteModel = psModelRemoteModel;
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

        Class<?> remoteModelClass = _psModelRemoteModel.getClass();

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

        Object returnValue = method.invoke(_psModelRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            PsModelLocalServiceUtil.addPsModel(this);
        } else {
            PsModelLocalServiceUtil.updatePsModel(this);
        }
    }

    @Override
    public PsModel toEscapedModel() {
        return (PsModel) ProxyUtil.newProxyInstance(PsModel.class.getClassLoader(),
            new Class[] { PsModel.class }, new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        PsModelClp clone = new PsModelClp();

        clone.setPsId(getPsId());
        clone.setPsName(getPsName());
        clone.setPsType(getPsType());
        clone.setModifiedDate(getModifiedDate());
        clone.setPsCategory(getPsCategory());
        clone.setRecordLockStatus(getRecordLockStatus());
        clone.setPsStatus(getPsStatus());
        clone.setCreatedDate(getCreatedDate());
        clone.setCreatedBy(getCreatedBy());
        clone.setSource(getSource());
        clone.setPsNo(getPsNo());
        clone.setPsDesignation(getPsDesignation());
        clone.setParentPsId(getParentPsId());
        clone.setBatchId(getBatchId());
        clone.setPsModelSid(getPsModelSid());
        clone.setPsEndDate(getPsEndDate());
        clone.setPsTradeClass(getPsTradeClass());
        clone.setModifiedBy(getModifiedBy());
        clone.setInboundStatus(getInboundStatus());
        clone.setPsStartDate(getPsStartDate());
        clone.setParentPsName(getParentPsName());
        clone.setInternalNotes(getInternalNotes());

        return clone;
    }

    @Override
    public int compareTo(PsModel psModel) {
        int primaryKey = psModel.getPrimaryKey();

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

        if (!(obj instanceof PsModelClp)) {
            return false;
        }

        PsModelClp psModel = (PsModelClp) obj;

        int primaryKey = psModel.getPrimaryKey();

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
        StringBundler sb = new StringBundler(45);

        sb.append("{psId=");
        sb.append(getPsId());
        sb.append(", psName=");
        sb.append(getPsName());
        sb.append(", psType=");
        sb.append(getPsType());
        sb.append(", modifiedDate=");
        sb.append(getModifiedDate());
        sb.append(", psCategory=");
        sb.append(getPsCategory());
        sb.append(", recordLockStatus=");
        sb.append(getRecordLockStatus());
        sb.append(", psStatus=");
        sb.append(getPsStatus());
        sb.append(", createdDate=");
        sb.append(getCreatedDate());
        sb.append(", createdBy=");
        sb.append(getCreatedBy());
        sb.append(", source=");
        sb.append(getSource());
        sb.append(", psNo=");
        sb.append(getPsNo());
        sb.append(", psDesignation=");
        sb.append(getPsDesignation());
        sb.append(", parentPsId=");
        sb.append(getParentPsId());
        sb.append(", batchId=");
        sb.append(getBatchId());
        sb.append(", psModelSid=");
        sb.append(getPsModelSid());
        sb.append(", psEndDate=");
        sb.append(getPsEndDate());
        sb.append(", psTradeClass=");
        sb.append(getPsTradeClass());
        sb.append(", modifiedBy=");
        sb.append(getModifiedBy());
        sb.append(", inboundStatus=");
        sb.append(getInboundStatus());
        sb.append(", psStartDate=");
        sb.append(getPsStartDate());
        sb.append(", parentPsName=");
        sb.append(getParentPsName());
        sb.append(", internalNotes=");
        sb.append(getInternalNotes());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(70);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.PsModel");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>psId</column-name><column-value><![CDATA[");
        sb.append(getPsId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>psName</column-name><column-value><![CDATA[");
        sb.append(getPsName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>psType</column-name><column-value><![CDATA[");
        sb.append(getPsType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
        sb.append(getModifiedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>psCategory</column-name><column-value><![CDATA[");
        sb.append(getPsCategory());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>recordLockStatus</column-name><column-value><![CDATA[");
        sb.append(getRecordLockStatus());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>psStatus</column-name><column-value><![CDATA[");
        sb.append(getPsStatus());
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
            "<column><column-name>psNo</column-name><column-value><![CDATA[");
        sb.append(getPsNo());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>psDesignation</column-name><column-value><![CDATA[");
        sb.append(getPsDesignation());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>parentPsId</column-name><column-value><![CDATA[");
        sb.append(getParentPsId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>batchId</column-name><column-value><![CDATA[");
        sb.append(getBatchId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>psModelSid</column-name><column-value><![CDATA[");
        sb.append(getPsModelSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>psEndDate</column-name><column-value><![CDATA[");
        sb.append(getPsEndDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>psTradeClass</column-name><column-value><![CDATA[");
        sb.append(getPsTradeClass());
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
            "<column><column-name>psStartDate</column-name><column-value><![CDATA[");
        sb.append(getPsStartDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>parentPsName</column-name><column-value><![CDATA[");
        sb.append(getParentPsName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>internalNotes</column-name><column-value><![CDATA[");
        sb.append(getInternalNotes());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
