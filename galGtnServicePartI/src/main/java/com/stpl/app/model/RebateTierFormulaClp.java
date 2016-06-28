package com.stpl.app.model;

import com.stpl.app.service.ClpSerializer;
import com.stpl.app.service.RebateTierFormulaLocalServiceUtil;

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


public class RebateTierFormulaClp extends BaseModelImpl<RebateTierFormula>
    implements RebateTierFormula {
    private String _rebateTierFormulaNo;
    private String _rebateTierFormulaName;
    private int _rebatePlanMasterSid;
    private Date _modifiedDate;
    private boolean _recordLockStatus;
    private String _source;
    private int _createdBy;
    private Date _createdDate;
    private String _batchId;
    private String _rebateTierFormulaId;
    private String _inboundStatus;
    private int _modifiedBy;
    private int _rebateTierFormulaSid;
    private BaseModel<?> _rebateTierFormulaRemoteModel;

    public RebateTierFormulaClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return RebateTierFormula.class;
    }

    @Override
    public String getModelClassName() {
        return RebateTierFormula.class.getName();
    }

    @Override
    public int getPrimaryKey() {
        return _rebateTierFormulaSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setRebateTierFormulaSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _rebateTierFormulaSid;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Integer) primaryKeyObj).intValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("rebateTierFormulaNo", getRebateTierFormulaNo());
        attributes.put("rebateTierFormulaName", getRebateTierFormulaName());
        attributes.put("rebatePlanMasterSid", getRebatePlanMasterSid());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("recordLockStatus", getRecordLockStatus());
        attributes.put("source", getSource());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("batchId", getBatchId());
        attributes.put("rebateTierFormulaId", getRebateTierFormulaId());
        attributes.put("inboundStatus", getInboundStatus());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("rebateTierFormulaSid", getRebateTierFormulaSid());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        String rebateTierFormulaNo = (String) attributes.get(
                "rebateTierFormulaNo");

        if (rebateTierFormulaNo != null) {
            setRebateTierFormulaNo(rebateTierFormulaNo);
        }

        String rebateTierFormulaName = (String) attributes.get(
                "rebateTierFormulaName");

        if (rebateTierFormulaName != null) {
            setRebateTierFormulaName(rebateTierFormulaName);
        }

        Integer rebatePlanMasterSid = (Integer) attributes.get(
                "rebatePlanMasterSid");

        if (rebatePlanMasterSid != null) {
            setRebatePlanMasterSid(rebatePlanMasterSid);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        Boolean recordLockStatus = (Boolean) attributes.get("recordLockStatus");

        if (recordLockStatus != null) {
            setRecordLockStatus(recordLockStatus);
        }

        String source = (String) attributes.get("source");

        if (source != null) {
            setSource(source);
        }

        Integer createdBy = (Integer) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        String batchId = (String) attributes.get("batchId");

        if (batchId != null) {
            setBatchId(batchId);
        }

        String rebateTierFormulaId = (String) attributes.get(
                "rebateTierFormulaId");

        if (rebateTierFormulaId != null) {
            setRebateTierFormulaId(rebateTierFormulaId);
        }

        String inboundStatus = (String) attributes.get("inboundStatus");

        if (inboundStatus != null) {
            setInboundStatus(inboundStatus);
        }

        Integer modifiedBy = (Integer) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        Integer rebateTierFormulaSid = (Integer) attributes.get(
                "rebateTierFormulaSid");

        if (rebateTierFormulaSid != null) {
            setRebateTierFormulaSid(rebateTierFormulaSid);
        }
    }

    @Override
    public String getRebateTierFormulaNo() {
        return _rebateTierFormulaNo;
    }

    @Override
    public void setRebateTierFormulaNo(String rebateTierFormulaNo) {
        _rebateTierFormulaNo = rebateTierFormulaNo;

        if (_rebateTierFormulaRemoteModel != null) {
            try {
                Class<?> clazz = _rebateTierFormulaRemoteModel.getClass();

                Method method = clazz.getMethod("setRebateTierFormulaNo",
                        String.class);

                method.invoke(_rebateTierFormulaRemoteModel, rebateTierFormulaNo);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getRebateTierFormulaName() {
        return _rebateTierFormulaName;
    }

    @Override
    public void setRebateTierFormulaName(String rebateTierFormulaName) {
        _rebateTierFormulaName = rebateTierFormulaName;

        if (_rebateTierFormulaRemoteModel != null) {
            try {
                Class<?> clazz = _rebateTierFormulaRemoteModel.getClass();

                Method method = clazz.getMethod("setRebateTierFormulaName",
                        String.class);

                method.invoke(_rebateTierFormulaRemoteModel,
                    rebateTierFormulaName);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getRebatePlanMasterSid() {
        return _rebatePlanMasterSid;
    }

    @Override
    public void setRebatePlanMasterSid(int rebatePlanMasterSid) {
        _rebatePlanMasterSid = rebatePlanMasterSid;

        if (_rebateTierFormulaRemoteModel != null) {
            try {
                Class<?> clazz = _rebateTierFormulaRemoteModel.getClass();

                Method method = clazz.getMethod("setRebatePlanMasterSid",
                        int.class);

                method.invoke(_rebateTierFormulaRemoteModel, rebatePlanMasterSid);
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

        if (_rebateTierFormulaRemoteModel != null) {
            try {
                Class<?> clazz = _rebateTierFormulaRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedDate", Date.class);

                method.invoke(_rebateTierFormulaRemoteModel, modifiedDate);
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

        if (_rebateTierFormulaRemoteModel != null) {
            try {
                Class<?> clazz = _rebateTierFormulaRemoteModel.getClass();

                Method method = clazz.getMethod("setRecordLockStatus",
                        boolean.class);

                method.invoke(_rebateTierFormulaRemoteModel, recordLockStatus);
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

        if (_rebateTierFormulaRemoteModel != null) {
            try {
                Class<?> clazz = _rebateTierFormulaRemoteModel.getClass();

                Method method = clazz.getMethod("setSource", String.class);

                method.invoke(_rebateTierFormulaRemoteModel, source);
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

        if (_rebateTierFormulaRemoteModel != null) {
            try {
                Class<?> clazz = _rebateTierFormulaRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedBy", int.class);

                method.invoke(_rebateTierFormulaRemoteModel, createdBy);
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

        if (_rebateTierFormulaRemoteModel != null) {
            try {
                Class<?> clazz = _rebateTierFormulaRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedDate", Date.class);

                method.invoke(_rebateTierFormulaRemoteModel, createdDate);
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

        if (_rebateTierFormulaRemoteModel != null) {
            try {
                Class<?> clazz = _rebateTierFormulaRemoteModel.getClass();

                Method method = clazz.getMethod("setBatchId", String.class);

                method.invoke(_rebateTierFormulaRemoteModel, batchId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getRebateTierFormulaId() {
        return _rebateTierFormulaId;
    }

    @Override
    public void setRebateTierFormulaId(String rebateTierFormulaId) {
        _rebateTierFormulaId = rebateTierFormulaId;

        if (_rebateTierFormulaRemoteModel != null) {
            try {
                Class<?> clazz = _rebateTierFormulaRemoteModel.getClass();

                Method method = clazz.getMethod("setRebateTierFormulaId",
                        String.class);

                method.invoke(_rebateTierFormulaRemoteModel, rebateTierFormulaId);
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

        if (_rebateTierFormulaRemoteModel != null) {
            try {
                Class<?> clazz = _rebateTierFormulaRemoteModel.getClass();

                Method method = clazz.getMethod("setInboundStatus", String.class);

                method.invoke(_rebateTierFormulaRemoteModel, inboundStatus);
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

        if (_rebateTierFormulaRemoteModel != null) {
            try {
                Class<?> clazz = _rebateTierFormulaRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedBy", int.class);

                method.invoke(_rebateTierFormulaRemoteModel, modifiedBy);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getRebateTierFormulaSid() {
        return _rebateTierFormulaSid;
    }

    @Override
    public void setRebateTierFormulaSid(int rebateTierFormulaSid) {
        _rebateTierFormulaSid = rebateTierFormulaSid;

        if (_rebateTierFormulaRemoteModel != null) {
            try {
                Class<?> clazz = _rebateTierFormulaRemoteModel.getClass();

                Method method = clazz.getMethod("setRebateTierFormulaSid",
                        int.class);

                method.invoke(_rebateTierFormulaRemoteModel,
                    rebateTierFormulaSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getRebateTierFormulaRemoteModel() {
        return _rebateTierFormulaRemoteModel;
    }

    public void setRebateTierFormulaRemoteModel(
        BaseModel<?> rebateTierFormulaRemoteModel) {
        _rebateTierFormulaRemoteModel = rebateTierFormulaRemoteModel;
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

        Class<?> remoteModelClass = _rebateTierFormulaRemoteModel.getClass();

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

        Object returnValue = method.invoke(_rebateTierFormulaRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            RebateTierFormulaLocalServiceUtil.addRebateTierFormula(this);
        } else {
            RebateTierFormulaLocalServiceUtil.updateRebateTierFormula(this);
        }
    }

    @Override
    public RebateTierFormula toEscapedModel() {
        return (RebateTierFormula) ProxyUtil.newProxyInstance(RebateTierFormula.class.getClassLoader(),
            new Class[] { RebateTierFormula.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        RebateTierFormulaClp clone = new RebateTierFormulaClp();

        clone.setRebateTierFormulaNo(getRebateTierFormulaNo());
        clone.setRebateTierFormulaName(getRebateTierFormulaName());
        clone.setRebatePlanMasterSid(getRebatePlanMasterSid());
        clone.setModifiedDate(getModifiedDate());
        clone.setRecordLockStatus(getRecordLockStatus());
        clone.setSource(getSource());
        clone.setCreatedBy(getCreatedBy());
        clone.setCreatedDate(getCreatedDate());
        clone.setBatchId(getBatchId());
        clone.setRebateTierFormulaId(getRebateTierFormulaId());
        clone.setInboundStatus(getInboundStatus());
        clone.setModifiedBy(getModifiedBy());
        clone.setRebateTierFormulaSid(getRebateTierFormulaSid());

        return clone;
    }

    @Override
    public int compareTo(RebateTierFormula rebateTierFormula) {
        int primaryKey = rebateTierFormula.getPrimaryKey();

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

        if (!(obj instanceof RebateTierFormulaClp)) {
            return false;
        }

        RebateTierFormulaClp rebateTierFormula = (RebateTierFormulaClp) obj;

        int primaryKey = rebateTierFormula.getPrimaryKey();

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

        sb.append("{rebateTierFormulaNo=");
        sb.append(getRebateTierFormulaNo());
        sb.append(", rebateTierFormulaName=");
        sb.append(getRebateTierFormulaName());
        sb.append(", rebatePlanMasterSid=");
        sb.append(getRebatePlanMasterSid());
        sb.append(", modifiedDate=");
        sb.append(getModifiedDate());
        sb.append(", recordLockStatus=");
        sb.append(getRecordLockStatus());
        sb.append(", source=");
        sb.append(getSource());
        sb.append(", createdBy=");
        sb.append(getCreatedBy());
        sb.append(", createdDate=");
        sb.append(getCreatedDate());
        sb.append(", batchId=");
        sb.append(getBatchId());
        sb.append(", rebateTierFormulaId=");
        sb.append(getRebateTierFormulaId());
        sb.append(", inboundStatus=");
        sb.append(getInboundStatus());
        sb.append(", modifiedBy=");
        sb.append(getModifiedBy());
        sb.append(", rebateTierFormulaSid=");
        sb.append(getRebateTierFormulaSid());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(43);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.RebateTierFormula");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>rebateTierFormulaNo</column-name><column-value><![CDATA[");
        sb.append(getRebateTierFormulaNo());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>rebateTierFormulaName</column-name><column-value><![CDATA[");
        sb.append(getRebateTierFormulaName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>rebatePlanMasterSid</column-name><column-value><![CDATA[");
        sb.append(getRebatePlanMasterSid());
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
            "<column><column-name>source</column-name><column-value><![CDATA[");
        sb.append(getSource());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>createdBy</column-name><column-value><![CDATA[");
        sb.append(getCreatedBy());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>createdDate</column-name><column-value><![CDATA[");
        sb.append(getCreatedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>batchId</column-name><column-value><![CDATA[");
        sb.append(getBatchId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>rebateTierFormulaId</column-name><column-value><![CDATA[");
        sb.append(getRebateTierFormulaId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>inboundStatus</column-name><column-value><![CDATA[");
        sb.append(getInboundStatus());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedBy</column-name><column-value><![CDATA[");
        sb.append(getModifiedBy());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>rebateTierFormulaSid</column-name><column-value><![CDATA[");
        sb.append(getRebateTierFormulaSid());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
