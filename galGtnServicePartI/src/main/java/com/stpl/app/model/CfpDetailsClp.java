package com.stpl.app.model;

import com.stpl.app.service.CfpDetailsLocalServiceUtil;
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


public class CfpDetailsClp extends BaseModelImpl<CfpDetails>
    implements CfpDetails {
    private int _createdBy;
    private int _companyCfpAttachedStatus;
    private int _tradeClass;
    private Date _tradeClassEndDate;
    private int _modifiedBy;
    private Date _companyStartDate;
    private Date _tradeClassStartDate;
    private Date _createdDate;
    private Date _companyEndDate;
    private int _companyMasterSid;
    private Date _companyCfpAttachedDate;
    private int _cfpModelSid;
    private String _batchId;
    private int _cfpDetailsSid;
    private Date _modifiedDate;
    private boolean _recordLockStatus;
    private String _source;
    private String _inboundStatus;
    private BaseModel<?> _cfpDetailsRemoteModel;

    public CfpDetailsClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return CfpDetails.class;
    }

    @Override
    public String getModelClassName() {
        return CfpDetails.class.getName();
    }

    @Override
    public int getPrimaryKey() {
        return _cfpDetailsSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setCfpDetailsSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _cfpDetailsSid;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Integer) primaryKeyObj).intValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("createdBy", getCreatedBy());
        attributes.put("companyCfpAttachedStatus", getCompanyCfpAttachedStatus());
        attributes.put("tradeClass", getTradeClass());
        attributes.put("tradeClassEndDate", getTradeClassEndDate());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("companyStartDate", getCompanyStartDate());
        attributes.put("tradeClassStartDate", getTradeClassStartDate());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("companyEndDate", getCompanyEndDate());
        attributes.put("companyMasterSid", getCompanyMasterSid());
        attributes.put("companyCfpAttachedDate", getCompanyCfpAttachedDate());
        attributes.put("cfpModelSid", getCfpModelSid());
        attributes.put("batchId", getBatchId());
        attributes.put("cfpDetailsSid", getCfpDetailsSid());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("recordLockStatus", getRecordLockStatus());
        attributes.put("source", getSource());
        attributes.put("inboundStatus", getInboundStatus());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Integer createdBy = (Integer) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        Integer companyCfpAttachedStatus = (Integer) attributes.get(
                "companyCfpAttachedStatus");

        if (companyCfpAttachedStatus != null) {
            setCompanyCfpAttachedStatus(companyCfpAttachedStatus);
        }

        Integer tradeClass = (Integer) attributes.get("tradeClass");

        if (tradeClass != null) {
            setTradeClass(tradeClass);
        }

        Date tradeClassEndDate = (Date) attributes.get("tradeClassEndDate");

        if (tradeClassEndDate != null) {
            setTradeClassEndDate(tradeClassEndDate);
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

        Date companyEndDate = (Date) attributes.get("companyEndDate");

        if (companyEndDate != null) {
            setCompanyEndDate(companyEndDate);
        }

        Integer companyMasterSid = (Integer) attributes.get("companyMasterSid");

        if (companyMasterSid != null) {
            setCompanyMasterSid(companyMasterSid);
        }

        Date companyCfpAttachedDate = (Date) attributes.get(
                "companyCfpAttachedDate");

        if (companyCfpAttachedDate != null) {
            setCompanyCfpAttachedDate(companyCfpAttachedDate);
        }

        Integer cfpModelSid = (Integer) attributes.get("cfpModelSid");

        if (cfpModelSid != null) {
            setCfpModelSid(cfpModelSid);
        }

        String batchId = (String) attributes.get("batchId");

        if (batchId != null) {
            setBatchId(batchId);
        }

        Integer cfpDetailsSid = (Integer) attributes.get("cfpDetailsSid");

        if (cfpDetailsSid != null) {
            setCfpDetailsSid(cfpDetailsSid);
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

        if (_cfpDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _cfpDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedBy", int.class);

                method.invoke(_cfpDetailsRemoteModel, createdBy);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getCompanyCfpAttachedStatus() {
        return _companyCfpAttachedStatus;
    }

    @Override
    public void setCompanyCfpAttachedStatus(int companyCfpAttachedStatus) {
        _companyCfpAttachedStatus = companyCfpAttachedStatus;

        if (_cfpDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _cfpDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCompanyCfpAttachedStatus",
                        int.class);

                method.invoke(_cfpDetailsRemoteModel, companyCfpAttachedStatus);
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

        if (_cfpDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _cfpDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setTradeClass", int.class);

                method.invoke(_cfpDetailsRemoteModel, tradeClass);
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

        if (_cfpDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _cfpDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setTradeClassEndDate",
                        Date.class);

                method.invoke(_cfpDetailsRemoteModel, tradeClassEndDate);
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

        if (_cfpDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _cfpDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedBy", int.class);

                method.invoke(_cfpDetailsRemoteModel, modifiedBy);
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

        if (_cfpDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _cfpDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCompanyStartDate",
                        Date.class);

                method.invoke(_cfpDetailsRemoteModel, companyStartDate);
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

        if (_cfpDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _cfpDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setTradeClassStartDate",
                        Date.class);

                method.invoke(_cfpDetailsRemoteModel, tradeClassStartDate);
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

        if (_cfpDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _cfpDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedDate", Date.class);

                method.invoke(_cfpDetailsRemoteModel, createdDate);
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

        if (_cfpDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _cfpDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCompanyEndDate", Date.class);

                method.invoke(_cfpDetailsRemoteModel, companyEndDate);
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

        if (_cfpDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _cfpDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCompanyMasterSid", int.class);

                method.invoke(_cfpDetailsRemoteModel, companyMasterSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getCompanyCfpAttachedDate() {
        return _companyCfpAttachedDate;
    }

    @Override
    public void setCompanyCfpAttachedDate(Date companyCfpAttachedDate) {
        _companyCfpAttachedDate = companyCfpAttachedDate;

        if (_cfpDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _cfpDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCompanyCfpAttachedDate",
                        Date.class);

                method.invoke(_cfpDetailsRemoteModel, companyCfpAttachedDate);
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

        if (_cfpDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _cfpDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCfpModelSid", int.class);

                method.invoke(_cfpDetailsRemoteModel, cfpModelSid);
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

        if (_cfpDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _cfpDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setBatchId", String.class);

                method.invoke(_cfpDetailsRemoteModel, batchId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getCfpDetailsSid() {
        return _cfpDetailsSid;
    }

    @Override
    public void setCfpDetailsSid(int cfpDetailsSid) {
        _cfpDetailsSid = cfpDetailsSid;

        if (_cfpDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _cfpDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCfpDetailsSid", int.class);

                method.invoke(_cfpDetailsRemoteModel, cfpDetailsSid);
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

        if (_cfpDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _cfpDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedDate", Date.class);

                method.invoke(_cfpDetailsRemoteModel, modifiedDate);
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

        if (_cfpDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _cfpDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setRecordLockStatus",
                        boolean.class);

                method.invoke(_cfpDetailsRemoteModel, recordLockStatus);
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

        if (_cfpDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _cfpDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setSource", String.class);

                method.invoke(_cfpDetailsRemoteModel, source);
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

        if (_cfpDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _cfpDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setInboundStatus", String.class);

                method.invoke(_cfpDetailsRemoteModel, inboundStatus);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getCfpDetailsRemoteModel() {
        return _cfpDetailsRemoteModel;
    }

    public void setCfpDetailsRemoteModel(BaseModel<?> cfpDetailsRemoteModel) {
        _cfpDetailsRemoteModel = cfpDetailsRemoteModel;
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

        Class<?> remoteModelClass = _cfpDetailsRemoteModel.getClass();

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

        Object returnValue = method.invoke(_cfpDetailsRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            CfpDetailsLocalServiceUtil.addCfpDetails(this);
        } else {
            CfpDetailsLocalServiceUtil.updateCfpDetails(this);
        }
    }

    @Override
    public CfpDetails toEscapedModel() {
        return (CfpDetails) ProxyUtil.newProxyInstance(CfpDetails.class.getClassLoader(),
            new Class[] { CfpDetails.class }, new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        CfpDetailsClp clone = new CfpDetailsClp();

        clone.setCreatedBy(getCreatedBy());
        clone.setCompanyCfpAttachedStatus(getCompanyCfpAttachedStatus());
        clone.setTradeClass(getTradeClass());
        clone.setTradeClassEndDate(getTradeClassEndDate());
        clone.setModifiedBy(getModifiedBy());
        clone.setCompanyStartDate(getCompanyStartDate());
        clone.setTradeClassStartDate(getTradeClassStartDate());
        clone.setCreatedDate(getCreatedDate());
        clone.setCompanyEndDate(getCompanyEndDate());
        clone.setCompanyMasterSid(getCompanyMasterSid());
        clone.setCompanyCfpAttachedDate(getCompanyCfpAttachedDate());
        clone.setCfpModelSid(getCfpModelSid());
        clone.setBatchId(getBatchId());
        clone.setCfpDetailsSid(getCfpDetailsSid());
        clone.setModifiedDate(getModifiedDate());
        clone.setRecordLockStatus(getRecordLockStatus());
        clone.setSource(getSource());
        clone.setInboundStatus(getInboundStatus());

        return clone;
    }

    @Override
    public int compareTo(CfpDetails cfpDetails) {
        int primaryKey = cfpDetails.getPrimaryKey();

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

        if (!(obj instanceof CfpDetailsClp)) {
            return false;
        }

        CfpDetailsClp cfpDetails = (CfpDetailsClp) obj;

        int primaryKey = cfpDetails.getPrimaryKey();

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
        sb.append(", companyCfpAttachedStatus=");
        sb.append(getCompanyCfpAttachedStatus());
        sb.append(", tradeClass=");
        sb.append(getTradeClass());
        sb.append(", tradeClassEndDate=");
        sb.append(getTradeClassEndDate());
        sb.append(", modifiedBy=");
        sb.append(getModifiedBy());
        sb.append(", companyStartDate=");
        sb.append(getCompanyStartDate());
        sb.append(", tradeClassStartDate=");
        sb.append(getTradeClassStartDate());
        sb.append(", createdDate=");
        sb.append(getCreatedDate());
        sb.append(", companyEndDate=");
        sb.append(getCompanyEndDate());
        sb.append(", companyMasterSid=");
        sb.append(getCompanyMasterSid());
        sb.append(", companyCfpAttachedDate=");
        sb.append(getCompanyCfpAttachedDate());
        sb.append(", cfpModelSid=");
        sb.append(getCfpModelSid());
        sb.append(", batchId=");
        sb.append(getBatchId());
        sb.append(", cfpDetailsSid=");
        sb.append(getCfpDetailsSid());
        sb.append(", modifiedDate=");
        sb.append(getModifiedDate());
        sb.append(", recordLockStatus=");
        sb.append(getRecordLockStatus());
        sb.append(", source=");
        sb.append(getSource());
        sb.append(", inboundStatus=");
        sb.append(getInboundStatus());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(58);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.CfpDetails");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>createdBy</column-name><column-value><![CDATA[");
        sb.append(getCreatedBy());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>companyCfpAttachedStatus</column-name><column-value><![CDATA[");
        sb.append(getCompanyCfpAttachedStatus());
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
            "<column><column-name>companyEndDate</column-name><column-value><![CDATA[");
        sb.append(getCompanyEndDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>companyMasterSid</column-name><column-value><![CDATA[");
        sb.append(getCompanyMasterSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>companyCfpAttachedDate</column-name><column-value><![CDATA[");
        sb.append(getCompanyCfpAttachedDate());
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
            "<column><column-name>cfpDetailsSid</column-name><column-value><![CDATA[");
        sb.append(getCfpDetailsSid());
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
            "<column><column-name>inboundStatus</column-name><column-value><![CDATA[");
        sb.append(getInboundStatus());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
