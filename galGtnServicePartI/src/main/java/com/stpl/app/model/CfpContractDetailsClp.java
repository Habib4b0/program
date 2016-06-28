package com.stpl.app.model;

import com.stpl.app.service.CfpContractDetailsLocalServiceUtil;
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


public class CfpContractDetailsClp extends BaseModelImpl<CfpContractDetails>
    implements CfpContractDetails {
    private int _createdBy;
    private int _tradeClass;
    private Date _tradeClassEndDate;
    private int _cfpContractSid;
    private int _modifiedBy;
    private Date _companyStartDate;
    private Date _tradeClassStartDate;
    private Date _createdDate;
    private Date _cfpContractAttachedDate;
    private Date _companyEndDate;
    private int _companyMasterSid;
    private String _batchId;
    private Date _modifiedDate;
    private boolean _recordLockStatus;
    private String _source;
    private int _cfpContractDetailsSid;
    private int _cfpContractAttachedStatus;
    private String _inboundStatus;
    private BaseModel<?> _cfpContractDetailsRemoteModel;

    public CfpContractDetailsClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return CfpContractDetails.class;
    }

    @Override
    public String getModelClassName() {
        return CfpContractDetails.class.getName();
    }

    @Override
    public int getPrimaryKey() {
        return _cfpContractDetailsSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setCfpContractDetailsSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _cfpContractDetailsSid;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Integer) primaryKeyObj).intValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("createdBy", getCreatedBy());
        attributes.put("tradeClass", getTradeClass());
        attributes.put("tradeClassEndDate", getTradeClassEndDate());
        attributes.put("cfpContractSid", getCfpContractSid());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("companyStartDate", getCompanyStartDate());
        attributes.put("tradeClassStartDate", getTradeClassStartDate());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("cfpContractAttachedDate", getCfpContractAttachedDate());
        attributes.put("companyEndDate", getCompanyEndDate());
        attributes.put("companyMasterSid", getCompanyMasterSid());
        attributes.put("batchId", getBatchId());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("recordLockStatus", getRecordLockStatus());
        attributes.put("source", getSource());
        attributes.put("cfpContractDetailsSid", getCfpContractDetailsSid());
        attributes.put("cfpContractAttachedStatus",
            getCfpContractAttachedStatus());
        attributes.put("inboundStatus", getInboundStatus());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Integer createdBy = (Integer) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        Integer tradeClass = (Integer) attributes.get("tradeClass");

        if (tradeClass != null) {
            setTradeClass(tradeClass);
        }

        Date tradeClassEndDate = (Date) attributes.get("tradeClassEndDate");

        if (tradeClassEndDate != null) {
            setTradeClassEndDate(tradeClassEndDate);
        }

        Integer cfpContractSid = (Integer) attributes.get("cfpContractSid");

        if (cfpContractSid != null) {
            setCfpContractSid(cfpContractSid);
        }

        Integer modifiedBy = (Integer) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        Date companyStartDate = (Date) attributes.get("companyStartDate");

        if (companyStartDate != null) {
            setCompanyStartDate(companyStartDate);
        }

        Date tradeClassStartDate = (Date) attributes.get("tradeClassStartDate");

        if (tradeClassStartDate != null) {
            setTradeClassStartDate(tradeClassStartDate);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        Date cfpContractAttachedDate = (Date) attributes.get(
                "cfpContractAttachedDate");

        if (cfpContractAttachedDate != null) {
            setCfpContractAttachedDate(cfpContractAttachedDate);
        }

        Date companyEndDate = (Date) attributes.get("companyEndDate");

        if (companyEndDate != null) {
            setCompanyEndDate(companyEndDate);
        }

        Integer companyMasterSid = (Integer) attributes.get("companyMasterSid");

        if (companyMasterSid != null) {
            setCompanyMasterSid(companyMasterSid);
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

        String source = (String) attributes.get("source");

        if (source != null) {
            setSource(source);
        }

        Integer cfpContractDetailsSid = (Integer) attributes.get(
                "cfpContractDetailsSid");

        if (cfpContractDetailsSid != null) {
            setCfpContractDetailsSid(cfpContractDetailsSid);
        }

        Integer cfpContractAttachedStatus = (Integer) attributes.get(
                "cfpContractAttachedStatus");

        if (cfpContractAttachedStatus != null) {
            setCfpContractAttachedStatus(cfpContractAttachedStatus);
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

        if (_cfpContractDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _cfpContractDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedBy", int.class);

                method.invoke(_cfpContractDetailsRemoteModel, createdBy);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getTradeClass() {
        return _tradeClass;
    }

    @Override
    public void setTradeClass(int tradeClass) {
        _tradeClass = tradeClass;

        if (_cfpContractDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _cfpContractDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setTradeClass", int.class);

                method.invoke(_cfpContractDetailsRemoteModel, tradeClass);
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

        if (_cfpContractDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _cfpContractDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setTradeClassEndDate",
                        Date.class);

                method.invoke(_cfpContractDetailsRemoteModel, tradeClassEndDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getCfpContractSid() {
        return _cfpContractSid;
    }

    @Override
    public void setCfpContractSid(int cfpContractSid) {
        _cfpContractSid = cfpContractSid;

        if (_cfpContractDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _cfpContractDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCfpContractSid", int.class);

                method.invoke(_cfpContractDetailsRemoteModel, cfpContractSid);
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

        if (_cfpContractDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _cfpContractDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedBy", int.class);

                method.invoke(_cfpContractDetailsRemoteModel, modifiedBy);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getCompanyStartDate() {
        return _companyStartDate;
    }

    @Override
    public void setCompanyStartDate(Date companyStartDate) {
        _companyStartDate = companyStartDate;

        if (_cfpContractDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _cfpContractDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCompanyStartDate",
                        Date.class);

                method.invoke(_cfpContractDetailsRemoteModel, companyStartDate);
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

        if (_cfpContractDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _cfpContractDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setTradeClassStartDate",
                        Date.class);

                method.invoke(_cfpContractDetailsRemoteModel,
                    tradeClassStartDate);
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

        if (_cfpContractDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _cfpContractDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedDate", Date.class);

                method.invoke(_cfpContractDetailsRemoteModel, createdDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getCfpContractAttachedDate() {
        return _cfpContractAttachedDate;
    }

    @Override
    public void setCfpContractAttachedDate(Date cfpContractAttachedDate) {
        _cfpContractAttachedDate = cfpContractAttachedDate;

        if (_cfpContractDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _cfpContractDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCfpContractAttachedDate",
                        Date.class);

                method.invoke(_cfpContractDetailsRemoteModel,
                    cfpContractAttachedDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getCompanyEndDate() {
        return _companyEndDate;
    }

    @Override
    public void setCompanyEndDate(Date companyEndDate) {
        _companyEndDate = companyEndDate;

        if (_cfpContractDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _cfpContractDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCompanyEndDate", Date.class);

                method.invoke(_cfpContractDetailsRemoteModel, companyEndDate);
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

        if (_cfpContractDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _cfpContractDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCompanyMasterSid", int.class);

                method.invoke(_cfpContractDetailsRemoteModel, companyMasterSid);
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

        if (_cfpContractDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _cfpContractDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setBatchId", String.class);

                method.invoke(_cfpContractDetailsRemoteModel, batchId);
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

        if (_cfpContractDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _cfpContractDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedDate", Date.class);

                method.invoke(_cfpContractDetailsRemoteModel, modifiedDate);
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

        if (_cfpContractDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _cfpContractDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setRecordLockStatus",
                        boolean.class);

                method.invoke(_cfpContractDetailsRemoteModel, recordLockStatus);
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

        if (_cfpContractDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _cfpContractDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setSource", String.class);

                method.invoke(_cfpContractDetailsRemoteModel, source);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getCfpContractDetailsSid() {
        return _cfpContractDetailsSid;
    }

    @Override
    public void setCfpContractDetailsSid(int cfpContractDetailsSid) {
        _cfpContractDetailsSid = cfpContractDetailsSid;

        if (_cfpContractDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _cfpContractDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCfpContractDetailsSid",
                        int.class);

                method.invoke(_cfpContractDetailsRemoteModel,
                    cfpContractDetailsSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getCfpContractAttachedStatus() {
        return _cfpContractAttachedStatus;
    }

    @Override
    public void setCfpContractAttachedStatus(int cfpContractAttachedStatus) {
        _cfpContractAttachedStatus = cfpContractAttachedStatus;

        if (_cfpContractDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _cfpContractDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCfpContractAttachedStatus",
                        int.class);

                method.invoke(_cfpContractDetailsRemoteModel,
                    cfpContractAttachedStatus);
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

        if (_cfpContractDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _cfpContractDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setInboundStatus", String.class);

                method.invoke(_cfpContractDetailsRemoteModel, inboundStatus);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getCfpContractDetailsRemoteModel() {
        return _cfpContractDetailsRemoteModel;
    }

    public void setCfpContractDetailsRemoteModel(
        BaseModel<?> cfpContractDetailsRemoteModel) {
        _cfpContractDetailsRemoteModel = cfpContractDetailsRemoteModel;
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

        Class<?> remoteModelClass = _cfpContractDetailsRemoteModel.getClass();

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

        Object returnValue = method.invoke(_cfpContractDetailsRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            CfpContractDetailsLocalServiceUtil.addCfpContractDetails(this);
        } else {
            CfpContractDetailsLocalServiceUtil.updateCfpContractDetails(this);
        }
    }

    @Override
    public CfpContractDetails toEscapedModel() {
        return (CfpContractDetails) ProxyUtil.newProxyInstance(CfpContractDetails.class.getClassLoader(),
            new Class[] { CfpContractDetails.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        CfpContractDetailsClp clone = new CfpContractDetailsClp();

        clone.setCreatedBy(getCreatedBy());
        clone.setTradeClass(getTradeClass());
        clone.setTradeClassEndDate(getTradeClassEndDate());
        clone.setCfpContractSid(getCfpContractSid());
        clone.setModifiedBy(getModifiedBy());
        clone.setCompanyStartDate(getCompanyStartDate());
        clone.setTradeClassStartDate(getTradeClassStartDate());
        clone.setCreatedDate(getCreatedDate());
        clone.setCfpContractAttachedDate(getCfpContractAttachedDate());
        clone.setCompanyEndDate(getCompanyEndDate());
        clone.setCompanyMasterSid(getCompanyMasterSid());
        clone.setBatchId(getBatchId());
        clone.setModifiedDate(getModifiedDate());
        clone.setRecordLockStatus(getRecordLockStatus());
        clone.setSource(getSource());
        clone.setCfpContractDetailsSid(getCfpContractDetailsSid());
        clone.setCfpContractAttachedStatus(getCfpContractAttachedStatus());
        clone.setInboundStatus(getInboundStatus());

        return clone;
    }

    @Override
    public int compareTo(CfpContractDetails cfpContractDetails) {
        int primaryKey = cfpContractDetails.getPrimaryKey();

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

        if (!(obj instanceof CfpContractDetailsClp)) {
            return false;
        }

        CfpContractDetailsClp cfpContractDetails = (CfpContractDetailsClp) obj;

        int primaryKey = cfpContractDetails.getPrimaryKey();

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
        sb.append(", tradeClass=");
        sb.append(getTradeClass());
        sb.append(", tradeClassEndDate=");
        sb.append(getTradeClassEndDate());
        sb.append(", cfpContractSid=");
        sb.append(getCfpContractSid());
        sb.append(", modifiedBy=");
        sb.append(getModifiedBy());
        sb.append(", companyStartDate=");
        sb.append(getCompanyStartDate());
        sb.append(", tradeClassStartDate=");
        sb.append(getTradeClassStartDate());
        sb.append(", createdDate=");
        sb.append(getCreatedDate());
        sb.append(", cfpContractAttachedDate=");
        sb.append(getCfpContractAttachedDate());
        sb.append(", companyEndDate=");
        sb.append(getCompanyEndDate());
        sb.append(", companyMasterSid=");
        sb.append(getCompanyMasterSid());
        sb.append(", batchId=");
        sb.append(getBatchId());
        sb.append(", modifiedDate=");
        sb.append(getModifiedDate());
        sb.append(", recordLockStatus=");
        sb.append(getRecordLockStatus());
        sb.append(", source=");
        sb.append(getSource());
        sb.append(", cfpContractDetailsSid=");
        sb.append(getCfpContractDetailsSid());
        sb.append(", cfpContractAttachedStatus=");
        sb.append(getCfpContractAttachedStatus());
        sb.append(", inboundStatus=");
        sb.append(getInboundStatus());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(58);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.CfpContractDetails");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>createdBy</column-name><column-value><![CDATA[");
        sb.append(getCreatedBy());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>tradeClass</column-name><column-value><![CDATA[");
        sb.append(getTradeClass());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>tradeClassEndDate</column-name><column-value><![CDATA[");
        sb.append(getTradeClassEndDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>cfpContractSid</column-name><column-value><![CDATA[");
        sb.append(getCfpContractSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedBy</column-name><column-value><![CDATA[");
        sb.append(getModifiedBy());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>companyStartDate</column-name><column-value><![CDATA[");
        sb.append(getCompanyStartDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>tradeClassStartDate</column-name><column-value><![CDATA[");
        sb.append(getTradeClassStartDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>createdDate</column-name><column-value><![CDATA[");
        sb.append(getCreatedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>cfpContractAttachedDate</column-name><column-value><![CDATA[");
        sb.append(getCfpContractAttachedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>companyEndDate</column-name><column-value><![CDATA[");
        sb.append(getCompanyEndDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>companyMasterSid</column-name><column-value><![CDATA[");
        sb.append(getCompanyMasterSid());
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
            "<column><column-name>source</column-name><column-value><![CDATA[");
        sb.append(getSource());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>cfpContractDetailsSid</column-name><column-value><![CDATA[");
        sb.append(getCfpContractDetailsSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>cfpContractAttachedStatus</column-name><column-value><![CDATA[");
        sb.append(getCfpContractAttachedStatus());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>inboundStatus</column-name><column-value><![CDATA[");
        sb.append(getInboundStatus());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
