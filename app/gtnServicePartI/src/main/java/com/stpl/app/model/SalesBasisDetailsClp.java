package com.stpl.app.model;

import com.stpl.app.service.ClpSerializer;
import com.stpl.app.service.SalesBasisDetailsLocalServiceUtil;

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


public class SalesBasisDetailsClp extends BaseModelImpl<SalesBasisDetails>
    implements SalesBasisDetails {
    private int _createdBy;
    private int _netSalesFormulaMasterSid;
    private boolean _recordLockStatus;
    private int _modifiedBy;
    private Date _createdDate;
    private int _contractMasterSid;
    private String _source;
    private int _cdrModelSid;
    private int _salesBasisDetailsSid;
    private int _cfpContractDetailsSid;
    private Date _modifiedDate;
    private String _inboundStatus;
    private BaseModel<?> _salesBasisDetailsRemoteModel;

    public SalesBasisDetailsClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return SalesBasisDetails.class;
    }

    @Override
    public String getModelClassName() {
        return SalesBasisDetails.class.getName();
    }

    @Override
    public int getPrimaryKey() {
        return _salesBasisDetailsSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setSalesBasisDetailsSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _salesBasisDetailsSid;
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
        attributes.put("recordLockStatus", getRecordLockStatus());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("contractMasterSid", getContractMasterSid());
        attributes.put("source", getSource());
        attributes.put("cdrModelSid", getCdrModelSid());
        attributes.put("salesBasisDetailsSid", getSalesBasisDetailsSid());
        attributes.put("cfpContractDetailsSid", getCfpContractDetailsSid());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("inboundStatus", getInboundStatus());

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

        Boolean recordLockStatus = (Boolean) attributes.get("recordLockStatus");

        if (recordLockStatus != null) {
            setRecordLockStatus(recordLockStatus);
        }

        Integer modifiedBy = (Integer) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
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

        String source = (String) attributes.get("source");

        if (source != null) {
            setSource(source);
        }

        Integer cdrModelSid = (Integer) attributes.get("cdrModelSid");

        if (cdrModelSid != null) {
            setCdrModelSid(cdrModelSid);
        }

        Integer salesBasisDetailsSid = (Integer) attributes.get(
                "salesBasisDetailsSid");

        if (salesBasisDetailsSid != null) {
            setSalesBasisDetailsSid(salesBasisDetailsSid);
        }

        Integer cfpContractDetailsSid = (Integer) attributes.get(
                "cfpContractDetailsSid");

        if (cfpContractDetailsSid != null) {
            setCfpContractDetailsSid(cfpContractDetailsSid);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
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

        if (_salesBasisDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _salesBasisDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedBy", int.class);

                method.invoke(_salesBasisDetailsRemoteModel, createdBy);
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

        if (_salesBasisDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _salesBasisDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setNetSalesFormulaMasterSid",
                        int.class);

                method.invoke(_salesBasisDetailsRemoteModel,
                    netSalesFormulaMasterSid);
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

        if (_salesBasisDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _salesBasisDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setRecordLockStatus",
                        boolean.class);

                method.invoke(_salesBasisDetailsRemoteModel, recordLockStatus);
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

        if (_salesBasisDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _salesBasisDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedBy", int.class);

                method.invoke(_salesBasisDetailsRemoteModel, modifiedBy);
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

        if (_salesBasisDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _salesBasisDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedDate", Date.class);

                method.invoke(_salesBasisDetailsRemoteModel, createdDate);
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

        if (_salesBasisDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _salesBasisDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setContractMasterSid",
                        int.class);

                method.invoke(_salesBasisDetailsRemoteModel, contractMasterSid);
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

        if (_salesBasisDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _salesBasisDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setSource", String.class);

                method.invoke(_salesBasisDetailsRemoteModel, source);
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

        if (_salesBasisDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _salesBasisDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCdrModelSid", int.class);

                method.invoke(_salesBasisDetailsRemoteModel, cdrModelSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getSalesBasisDetailsSid() {
        return _salesBasisDetailsSid;
    }

    @Override
    public void setSalesBasisDetailsSid(int salesBasisDetailsSid) {
        _salesBasisDetailsSid = salesBasisDetailsSid;

        if (_salesBasisDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _salesBasisDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setSalesBasisDetailsSid",
                        int.class);

                method.invoke(_salesBasisDetailsRemoteModel,
                    salesBasisDetailsSid);
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

        if (_salesBasisDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _salesBasisDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCfpContractDetailsSid",
                        int.class);

                method.invoke(_salesBasisDetailsRemoteModel,
                    cfpContractDetailsSid);
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

        if (_salesBasisDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _salesBasisDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedDate", Date.class);

                method.invoke(_salesBasisDetailsRemoteModel, modifiedDate);
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

        if (_salesBasisDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _salesBasisDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setInboundStatus", String.class);

                method.invoke(_salesBasisDetailsRemoteModel, inboundStatus);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getSalesBasisDetailsRemoteModel() {
        return _salesBasisDetailsRemoteModel;
    }

    public void setSalesBasisDetailsRemoteModel(
        BaseModel<?> salesBasisDetailsRemoteModel) {
        _salesBasisDetailsRemoteModel = salesBasisDetailsRemoteModel;
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

        Class<?> remoteModelClass = _salesBasisDetailsRemoteModel.getClass();

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

        Object returnValue = method.invoke(_salesBasisDetailsRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            SalesBasisDetailsLocalServiceUtil.addSalesBasisDetails(this);
        } else {
            SalesBasisDetailsLocalServiceUtil.updateSalesBasisDetails(this);
        }
    }

    @Override
    public SalesBasisDetails toEscapedModel() {
        return (SalesBasisDetails) ProxyUtil.newProxyInstance(SalesBasisDetails.class.getClassLoader(),
            new Class[] { SalesBasisDetails.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        SalesBasisDetailsClp clone = new SalesBasisDetailsClp();

        clone.setCreatedBy(getCreatedBy());
        clone.setNetSalesFormulaMasterSid(getNetSalesFormulaMasterSid());
        clone.setRecordLockStatus(getRecordLockStatus());
        clone.setModifiedBy(getModifiedBy());
        clone.setCreatedDate(getCreatedDate());
        clone.setContractMasterSid(getContractMasterSid());
        clone.setSource(getSource());
        clone.setCdrModelSid(getCdrModelSid());
        clone.setSalesBasisDetailsSid(getSalesBasisDetailsSid());
        clone.setCfpContractDetailsSid(getCfpContractDetailsSid());
        clone.setModifiedDate(getModifiedDate());
        clone.setInboundStatus(getInboundStatus());

        return clone;
    }

    @Override
    public int compareTo(SalesBasisDetails salesBasisDetails) {
        int primaryKey = salesBasisDetails.getPrimaryKey();

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

        if (!(obj instanceof SalesBasisDetailsClp)) {
            return false;
        }

        SalesBasisDetailsClp salesBasisDetails = (SalesBasisDetailsClp) obj;

        int primaryKey = salesBasisDetails.getPrimaryKey();

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
        StringBundler sb = new StringBundler(25);

        sb.append("{createdBy=");
        sb.append(getCreatedBy());
        sb.append(", netSalesFormulaMasterSid=");
        sb.append(getNetSalesFormulaMasterSid());
        sb.append(", recordLockStatus=");
        sb.append(getRecordLockStatus());
        sb.append(", modifiedBy=");
        sb.append(getModifiedBy());
        sb.append(", createdDate=");
        sb.append(getCreatedDate());
        sb.append(", contractMasterSid=");
        sb.append(getContractMasterSid());
        sb.append(", source=");
        sb.append(getSource());
        sb.append(", cdrModelSid=");
        sb.append(getCdrModelSid());
        sb.append(", salesBasisDetailsSid=");
        sb.append(getSalesBasisDetailsSid());
        sb.append(", cfpContractDetailsSid=");
        sb.append(getCfpContractDetailsSid());
        sb.append(", modifiedDate=");
        sb.append(getModifiedDate());
        sb.append(", inboundStatus=");
        sb.append(getInboundStatus());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(40);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.SalesBasisDetails");
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
            "<column><column-name>recordLockStatus</column-name><column-value><![CDATA[");
        sb.append(getRecordLockStatus());
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
            "<column><column-name>contractMasterSid</column-name><column-value><![CDATA[");
        sb.append(getContractMasterSid());
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
            "<column><column-name>salesBasisDetailsSid</column-name><column-value><![CDATA[");
        sb.append(getSalesBasisDetailsSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>cfpContractDetailsSid</column-name><column-value><![CDATA[");
        sb.append(getCfpContractDetailsSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
        sb.append(getModifiedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>inboundStatus</column-name><column-value><![CDATA[");
        sb.append(getInboundStatus());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
