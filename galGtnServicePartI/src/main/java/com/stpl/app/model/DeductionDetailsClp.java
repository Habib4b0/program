package com.stpl.app.model;

import com.stpl.app.service.ClpSerializer;
import com.stpl.app.service.DeductionDetailsLocalServiceUtil;

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


public class DeductionDetailsClp extends BaseModelImpl<DeductionDetails>
    implements DeductionDetails {
    private int _createdBy;
    private int _netSalesFormulaMasterSid;
    private int _modifiedBy;
    private int _rsContractSid;
    private Date _createdDate;
    private int _contractMasterSid;
    private int _deductionDetailsSid;
    private String _indicator;
    private Date _modifiedDate;
    private boolean _recordLockStatus;
    private String _source;
    private int _cdrModelSid;
    private String _inboundStatus;
    private String _deductionSubType;
    private String _deductionType;
    private String _deductionCategory;
    private BaseModel<?> _deductionDetailsRemoteModel;

    public DeductionDetailsClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return DeductionDetails.class;
    }

    @Override
    public String getModelClassName() {
        return DeductionDetails.class.getName();
    }

    @Override
    public int getPrimaryKey() {
        return _deductionDetailsSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setDeductionDetailsSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _deductionDetailsSid;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Integer) primaryKeyObj).intValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("createdBy", getCreatedBy());
        attributes.put("netSalesFormulaMasterSid", getNetSalesFormulaMasterSid());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("rsContractSid", getRsContractSid());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("contractMasterSid", getContractMasterSid());
        attributes.put("deductionDetailsSid", getDeductionDetailsSid());
        attributes.put("indicator", getIndicator());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("recordLockStatus", getRecordLockStatus());
        attributes.put("source", getSource());
        attributes.put("cdrModelSid", getCdrModelSid());
        attributes.put("inboundStatus", getInboundStatus());
        attributes.put("deductionSubType", getDeductionSubType());
        attributes.put("deductionType", getDeductionType());
        attributes.put("deductionCategory", getDeductionCategory());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Integer createdBy = (Integer) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        Integer netSalesFormulaMasterSid = (Integer) attributes.get(
                "netSalesFormulaMasterSid");

        if (netSalesFormulaMasterSid != null) {
            setNetSalesFormulaMasterSid(netSalesFormulaMasterSid);
        }

        Integer modifiedBy = (Integer) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        Integer rsContractSid = (Integer) attributes.get("rsContractSid");

        if (rsContractSid != null) {
            setRsContractSid(rsContractSid);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        Integer contractMasterSid = (Integer) attributes.get(
                "contractMasterSid");

        if (contractMasterSid != null) {
            setContractMasterSid(contractMasterSid);
        }

        Integer deductionDetailsSid = (Integer) attributes.get(
                "deductionDetailsSid");

        if (deductionDetailsSid != null) {
            setDeductionDetailsSid(deductionDetailsSid);
        }

        String indicator = (String) attributes.get("indicator");

        if (indicator != null) {
            setIndicator(indicator);
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

        Integer cdrModelSid = (Integer) attributes.get("cdrModelSid");

        if (cdrModelSid != null) {
            setCdrModelSid(cdrModelSid);
        }

        String inboundStatus = (String) attributes.get("inboundStatus");

        if (inboundStatus != null) {
            setInboundStatus(inboundStatus);
        }

        String deductionSubType = (String) attributes.get("deductionSubType");

        if (deductionSubType != null) {
            setDeductionSubType(deductionSubType);
        }

        String deductionType = (String) attributes.get("deductionType");

        if (deductionType != null) {
            setDeductionType(deductionType);
        }

        String deductionCategory = (String) attributes.get("deductionCategory");

        if (deductionCategory != null) {
            setDeductionCategory(deductionCategory);
        }
    }

    @Override
    public int getCreatedBy() {
        return _createdBy;
    }

    @Override
    public void setCreatedBy(int createdBy) {
        _createdBy = createdBy;

        if (_deductionDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _deductionDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedBy", int.class);

                method.invoke(_deductionDetailsRemoteModel, createdBy);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getNetSalesFormulaMasterSid() {
        return _netSalesFormulaMasterSid;
    }

    @Override
    public void setNetSalesFormulaMasterSid(int netSalesFormulaMasterSid) {
        _netSalesFormulaMasterSid = netSalesFormulaMasterSid;

        if (_deductionDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _deductionDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setNetSalesFormulaMasterSid",
                        int.class);

                method.invoke(_deductionDetailsRemoteModel,
                    netSalesFormulaMasterSid);
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

        if (_deductionDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _deductionDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedBy", int.class);

                method.invoke(_deductionDetailsRemoteModel, modifiedBy);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getRsContractSid() {
        return _rsContractSid;
    }

    @Override
    public void setRsContractSid(int rsContractSid) {
        _rsContractSid = rsContractSid;

        if (_deductionDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _deductionDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setRsContractSid", int.class);

                method.invoke(_deductionDetailsRemoteModel, rsContractSid);
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

        if (_deductionDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _deductionDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedDate", Date.class);

                method.invoke(_deductionDetailsRemoteModel, createdDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getContractMasterSid() {
        return _contractMasterSid;
    }

    @Override
    public void setContractMasterSid(int contractMasterSid) {
        _contractMasterSid = contractMasterSid;

        if (_deductionDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _deductionDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setContractMasterSid",
                        int.class);

                method.invoke(_deductionDetailsRemoteModel, contractMasterSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getDeductionDetailsSid() {
        return _deductionDetailsSid;
    }

    @Override
    public void setDeductionDetailsSid(int deductionDetailsSid) {
        _deductionDetailsSid = deductionDetailsSid;

        if (_deductionDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _deductionDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setDeductionDetailsSid",
                        int.class);

                method.invoke(_deductionDetailsRemoteModel, deductionDetailsSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getIndicator() {
        return _indicator;
    }

    @Override
    public void setIndicator(String indicator) {
        _indicator = indicator;

        if (_deductionDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _deductionDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setIndicator", String.class);

                method.invoke(_deductionDetailsRemoteModel, indicator);
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

        if (_deductionDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _deductionDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedDate", Date.class);

                method.invoke(_deductionDetailsRemoteModel, modifiedDate);
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

        if (_deductionDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _deductionDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setRecordLockStatus",
                        boolean.class);

                method.invoke(_deductionDetailsRemoteModel, recordLockStatus);
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

        if (_deductionDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _deductionDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setSource", String.class);

                method.invoke(_deductionDetailsRemoteModel, source);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getCdrModelSid() {
        return _cdrModelSid;
    }

    @Override
    public void setCdrModelSid(int cdrModelSid) {
        _cdrModelSid = cdrModelSid;

        if (_deductionDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _deductionDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCdrModelSid", int.class);

                method.invoke(_deductionDetailsRemoteModel, cdrModelSid);
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

        if (_deductionDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _deductionDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setInboundStatus", String.class);

                method.invoke(_deductionDetailsRemoteModel, inboundStatus);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getDeductionSubType() {
        return _deductionSubType;
    }

    @Override
    public void setDeductionSubType(String deductionSubType) {
        _deductionSubType = deductionSubType;

        if (_deductionDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _deductionDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setDeductionSubType",
                        String.class);

                method.invoke(_deductionDetailsRemoteModel, deductionSubType);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getDeductionType() {
        return _deductionType;
    }

    @Override
    public void setDeductionType(String deductionType) {
        _deductionType = deductionType;

        if (_deductionDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _deductionDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setDeductionType", String.class);

                method.invoke(_deductionDetailsRemoteModel, deductionType);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getDeductionCategory() {
        return _deductionCategory;
    }

    @Override
    public void setDeductionCategory(String deductionCategory) {
        _deductionCategory = deductionCategory;

        if (_deductionDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _deductionDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setDeductionCategory",
                        String.class);

                method.invoke(_deductionDetailsRemoteModel, deductionCategory);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getDeductionDetailsRemoteModel() {
        return _deductionDetailsRemoteModel;
    }

    public void setDeductionDetailsRemoteModel(
        BaseModel<?> deductionDetailsRemoteModel) {
        _deductionDetailsRemoteModel = deductionDetailsRemoteModel;
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

        Class<?> remoteModelClass = _deductionDetailsRemoteModel.getClass();

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

        Object returnValue = method.invoke(_deductionDetailsRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            DeductionDetailsLocalServiceUtil.addDeductionDetails(this);
        } else {
            DeductionDetailsLocalServiceUtil.updateDeductionDetails(this);
        }
    }

    @Override
    public DeductionDetails toEscapedModel() {
        return (DeductionDetails) ProxyUtil.newProxyInstance(DeductionDetails.class.getClassLoader(),
            new Class[] { DeductionDetails.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        DeductionDetailsClp clone = new DeductionDetailsClp();

        clone.setCreatedBy(getCreatedBy());
        clone.setNetSalesFormulaMasterSid(getNetSalesFormulaMasterSid());
        clone.setModifiedBy(getModifiedBy());
        clone.setRsContractSid(getRsContractSid());
        clone.setCreatedDate(getCreatedDate());
        clone.setContractMasterSid(getContractMasterSid());
        clone.setDeductionDetailsSid(getDeductionDetailsSid());
        clone.setIndicator(getIndicator());
        clone.setModifiedDate(getModifiedDate());
        clone.setRecordLockStatus(getRecordLockStatus());
        clone.setSource(getSource());
        clone.setCdrModelSid(getCdrModelSid());
        clone.setInboundStatus(getInboundStatus());
        clone.setDeductionSubType(getDeductionSubType());
        clone.setDeductionType(getDeductionType());
        clone.setDeductionCategory(getDeductionCategory());

        return clone;
    }

    @Override
    public int compareTo(DeductionDetails deductionDetails) {
        int primaryKey = deductionDetails.getPrimaryKey();

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

        if (!(obj instanceof DeductionDetailsClp)) {
            return false;
        }

        DeductionDetailsClp deductionDetails = (DeductionDetailsClp) obj;

        int primaryKey = deductionDetails.getPrimaryKey();

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

        sb.append("{createdBy=");
        sb.append(getCreatedBy());
        sb.append(", netSalesFormulaMasterSid=");
        sb.append(getNetSalesFormulaMasterSid());
        sb.append(", modifiedBy=");
        sb.append(getModifiedBy());
        sb.append(", rsContractSid=");
        sb.append(getRsContractSid());
        sb.append(", createdDate=");
        sb.append(getCreatedDate());
        sb.append(", contractMasterSid=");
        sb.append(getContractMasterSid());
        sb.append(", deductionDetailsSid=");
        sb.append(getDeductionDetailsSid());
        sb.append(", indicator=");
        sb.append(getIndicator());
        sb.append(", modifiedDate=");
        sb.append(getModifiedDate());
        sb.append(", recordLockStatus=");
        sb.append(getRecordLockStatus());
        sb.append(", source=");
        sb.append(getSource());
        sb.append(", cdrModelSid=");
        sb.append(getCdrModelSid());
        sb.append(", inboundStatus=");
        sb.append(getInboundStatus());
        sb.append(", deductionSubType=");
        sb.append(getDeductionSubType());
        sb.append(", deductionType=");
        sb.append(getDeductionType());
        sb.append(", deductionCategory=");
        sb.append(getDeductionCategory());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(52);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.DeductionDetails");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>createdBy</column-name><column-value><![CDATA[");
        sb.append(getCreatedBy());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>netSalesFormulaMasterSid</column-name><column-value><![CDATA[");
        sb.append(getNetSalesFormulaMasterSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedBy</column-name><column-value><![CDATA[");
        sb.append(getModifiedBy());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>rsContractSid</column-name><column-value><![CDATA[");
        sb.append(getRsContractSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>createdDate</column-name><column-value><![CDATA[");
        sb.append(getCreatedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>contractMasterSid</column-name><column-value><![CDATA[");
        sb.append(getContractMasterSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>deductionDetailsSid</column-name><column-value><![CDATA[");
        sb.append(getDeductionDetailsSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>indicator</column-name><column-value><![CDATA[");
        sb.append(getIndicator());
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
            "<column><column-name>cdrModelSid</column-name><column-value><![CDATA[");
        sb.append(getCdrModelSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>inboundStatus</column-name><column-value><![CDATA[");
        sb.append(getInboundStatus());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>deductionSubType</column-name><column-value><![CDATA[");
        sb.append(getDeductionSubType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>deductionType</column-name><column-value><![CDATA[");
        sb.append(getDeductionType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>deductionCategory</column-name><column-value><![CDATA[");
        sb.append(getDeductionCategory());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
