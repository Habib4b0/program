package com.stpl.app.model;

import com.stpl.app.service.ClpSerializer;
import com.stpl.app.service.ContractAliasMasterLocalServiceUtil;

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


public class ContractAliasMasterClp extends BaseModelImpl<ContractAliasMaster>
    implements ContractAliasMaster {
    private int _contractAliasType;
    private int _tpCompanyMasterSid;
    private Date _endDate;
    private int _contractAliasMasterSid;
    private Date _modifiedDate;
    private String _contractAliasNo;
    private boolean _recordLockStatus;
    private Date _startDate;
    private Date _createdDate;
    private String _source;
    private int _createdBy;
    private int _contractMasterSid;
    private String _batchId;
    private String _contractAliasName;
    private int _modifiedBy;
    private String _inboundStatus;
    private BaseModel<?> _contractAliasMasterRemoteModel;

    public ContractAliasMasterClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return ContractAliasMaster.class;
    }

    @Override
    public String getModelClassName() {
        return ContractAliasMaster.class.getName();
    }

    @Override
    public int getPrimaryKey() {
        return _contractAliasMasterSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setContractAliasMasterSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _contractAliasMasterSid;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Integer) primaryKeyObj).intValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("contractAliasType", getContractAliasType());
        attributes.put("tpCompanyMasterSid", getTpCompanyMasterSid());
        attributes.put("endDate", getEndDate());
        attributes.put("contractAliasMasterSid", getContractAliasMasterSid());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("contractAliasNo", getContractAliasNo());
        attributes.put("recordLockStatus", getRecordLockStatus());
        attributes.put("startDate", getStartDate());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("source", getSource());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("contractMasterSid", getContractMasterSid());
        attributes.put("batchId", getBatchId());
        attributes.put("contractAliasName", getContractAliasName());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("inboundStatus", getInboundStatus());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Integer contractAliasType = (Integer) attributes.get(
                "contractAliasType");

        if (contractAliasType != null) {
            setContractAliasType(contractAliasType);
        }

        Integer tpCompanyMasterSid = (Integer) attributes.get(
                "tpCompanyMasterSid");

        if (tpCompanyMasterSid != null) {
            setTpCompanyMasterSid(tpCompanyMasterSid);
        }

        Date endDate = (Date) attributes.get("endDate");

        if (endDate != null) {
            setEndDate(endDate);
        }

        Integer contractAliasMasterSid = (Integer) attributes.get(
                "contractAliasMasterSid");

        if (contractAliasMasterSid != null) {
            setContractAliasMasterSid(contractAliasMasterSid);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        String contractAliasNo = (String) attributes.get("contractAliasNo");

        if (contractAliasNo != null) {
            setContractAliasNo(contractAliasNo);
        }

        Boolean recordLockStatus = (Boolean) attributes.get("recordLockStatus");

        if (recordLockStatus != null) {
            setRecordLockStatus(recordLockStatus);
        }

        Date startDate = (Date) attributes.get("startDate");

        if (startDate != null) {
            setStartDate(startDate);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        String source = (String) attributes.get("source");

        if (source != null) {
            setSource(source);
        }

        Integer createdBy = (Integer) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        Integer contractMasterSid = (Integer) attributes.get(
                "contractMasterSid");

        if (contractMasterSid != null) {
            setContractMasterSid(contractMasterSid);
        }

        String batchId = (String) attributes.get("batchId");

        if (batchId != null) {
            setBatchId(batchId);
        }

        String contractAliasName = (String) attributes.get("contractAliasName");

        if (contractAliasName != null) {
            setContractAliasName(contractAliasName);
        }

        Integer modifiedBy = (Integer) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        String inboundStatus = (String) attributes.get("inboundStatus");

        if (inboundStatus != null) {
            setInboundStatus(inboundStatus);
        }
    }

    @Override
    public int getContractAliasType() {
        return _contractAliasType;
    }

    @Override
    public void setContractAliasType(int contractAliasType) {
        _contractAliasType = contractAliasType;

        if (_contractAliasMasterRemoteModel != null) {
            try {
                Class<?> clazz = _contractAliasMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setContractAliasType",
                        int.class);

                method.invoke(_contractAliasMasterRemoteModel, contractAliasType);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getTpCompanyMasterSid() {
        return _tpCompanyMasterSid;
    }

    @Override
    public void setTpCompanyMasterSid(int tpCompanyMasterSid) {
        _tpCompanyMasterSid = tpCompanyMasterSid;

        if (_contractAliasMasterRemoteModel != null) {
            try {
                Class<?> clazz = _contractAliasMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setTpCompanyMasterSid",
                        int.class);

                method.invoke(_contractAliasMasterRemoteModel,
                    tpCompanyMasterSid);
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

        if (_contractAliasMasterRemoteModel != null) {
            try {
                Class<?> clazz = _contractAliasMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setEndDate", Date.class);

                method.invoke(_contractAliasMasterRemoteModel, endDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getContractAliasMasterSid() {
        return _contractAliasMasterSid;
    }

    @Override
    public void setContractAliasMasterSid(int contractAliasMasterSid) {
        _contractAliasMasterSid = contractAliasMasterSid;

        if (_contractAliasMasterRemoteModel != null) {
            try {
                Class<?> clazz = _contractAliasMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setContractAliasMasterSid",
                        int.class);

                method.invoke(_contractAliasMasterRemoteModel,
                    contractAliasMasterSid);
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

        if (_contractAliasMasterRemoteModel != null) {
            try {
                Class<?> clazz = _contractAliasMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedDate", Date.class);

                method.invoke(_contractAliasMasterRemoteModel, modifiedDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getContractAliasNo() {
        return _contractAliasNo;
    }

    @Override
    public void setContractAliasNo(String contractAliasNo) {
        _contractAliasNo = contractAliasNo;

        if (_contractAliasMasterRemoteModel != null) {
            try {
                Class<?> clazz = _contractAliasMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setContractAliasNo",
                        String.class);

                method.invoke(_contractAliasMasterRemoteModel, contractAliasNo);
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

        if (_contractAliasMasterRemoteModel != null) {
            try {
                Class<?> clazz = _contractAliasMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setRecordLockStatus",
                        boolean.class);

                method.invoke(_contractAliasMasterRemoteModel, recordLockStatus);
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

        if (_contractAliasMasterRemoteModel != null) {
            try {
                Class<?> clazz = _contractAliasMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setStartDate", Date.class);

                method.invoke(_contractAliasMasterRemoteModel, startDate);
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

        if (_contractAliasMasterRemoteModel != null) {
            try {
                Class<?> clazz = _contractAliasMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedDate", Date.class);

                method.invoke(_contractAliasMasterRemoteModel, createdDate);
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

        if (_contractAliasMasterRemoteModel != null) {
            try {
                Class<?> clazz = _contractAliasMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setSource", String.class);

                method.invoke(_contractAliasMasterRemoteModel, source);
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

        if (_contractAliasMasterRemoteModel != null) {
            try {
                Class<?> clazz = _contractAliasMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedBy", int.class);

                method.invoke(_contractAliasMasterRemoteModel, createdBy);
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

        if (_contractAliasMasterRemoteModel != null) {
            try {
                Class<?> clazz = _contractAliasMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setContractMasterSid",
                        int.class);

                method.invoke(_contractAliasMasterRemoteModel, contractMasterSid);
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

        if (_contractAliasMasterRemoteModel != null) {
            try {
                Class<?> clazz = _contractAliasMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setBatchId", String.class);

                method.invoke(_contractAliasMasterRemoteModel, batchId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getContractAliasName() {
        return _contractAliasName;
    }

    @Override
    public void setContractAliasName(String contractAliasName) {
        _contractAliasName = contractAliasName;

        if (_contractAliasMasterRemoteModel != null) {
            try {
                Class<?> clazz = _contractAliasMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setContractAliasName",
                        String.class);

                method.invoke(_contractAliasMasterRemoteModel, contractAliasName);
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

        if (_contractAliasMasterRemoteModel != null) {
            try {
                Class<?> clazz = _contractAliasMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedBy", int.class);

                method.invoke(_contractAliasMasterRemoteModel, modifiedBy);
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

        if (_contractAliasMasterRemoteModel != null) {
            try {
                Class<?> clazz = _contractAliasMasterRemoteModel.getClass();

                Method method = clazz.getMethod("setInboundStatus", String.class);

                method.invoke(_contractAliasMasterRemoteModel, inboundStatus);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getContractAliasMasterRemoteModel() {
        return _contractAliasMasterRemoteModel;
    }

    public void setContractAliasMasterRemoteModel(
        BaseModel<?> contractAliasMasterRemoteModel) {
        _contractAliasMasterRemoteModel = contractAliasMasterRemoteModel;
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

        Class<?> remoteModelClass = _contractAliasMasterRemoteModel.getClass();

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

        Object returnValue = method.invoke(_contractAliasMasterRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            ContractAliasMasterLocalServiceUtil.addContractAliasMaster(this);
        } else {
            ContractAliasMasterLocalServiceUtil.updateContractAliasMaster(this);
        }
    }

    @Override
    public ContractAliasMaster toEscapedModel() {
        return (ContractAliasMaster) ProxyUtil.newProxyInstance(ContractAliasMaster.class.getClassLoader(),
            new Class[] { ContractAliasMaster.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        ContractAliasMasterClp clone = new ContractAliasMasterClp();

        clone.setContractAliasType(getContractAliasType());
        clone.setTpCompanyMasterSid(getTpCompanyMasterSid());
        clone.setEndDate(getEndDate());
        clone.setContractAliasMasterSid(getContractAliasMasterSid());
        clone.setModifiedDate(getModifiedDate());
        clone.setContractAliasNo(getContractAliasNo());
        clone.setRecordLockStatus(getRecordLockStatus());
        clone.setStartDate(getStartDate());
        clone.setCreatedDate(getCreatedDate());
        clone.setSource(getSource());
        clone.setCreatedBy(getCreatedBy());
        clone.setContractMasterSid(getContractMasterSid());
        clone.setBatchId(getBatchId());
        clone.setContractAliasName(getContractAliasName());
        clone.setModifiedBy(getModifiedBy());
        clone.setInboundStatus(getInboundStatus());

        return clone;
    }

    @Override
    public int compareTo(ContractAliasMaster contractAliasMaster) {
        int primaryKey = contractAliasMaster.getPrimaryKey();

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

        if (!(obj instanceof ContractAliasMasterClp)) {
            return false;
        }

        ContractAliasMasterClp contractAliasMaster = (ContractAliasMasterClp) obj;

        int primaryKey = contractAliasMaster.getPrimaryKey();

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

        sb.append("{contractAliasType=");
        sb.append(getContractAliasType());
        sb.append(", tpCompanyMasterSid=");
        sb.append(getTpCompanyMasterSid());
        sb.append(", endDate=");
        sb.append(getEndDate());
        sb.append(", contractAliasMasterSid=");
        sb.append(getContractAliasMasterSid());
        sb.append(", modifiedDate=");
        sb.append(getModifiedDate());
        sb.append(", contractAliasNo=");
        sb.append(getContractAliasNo());
        sb.append(", recordLockStatus=");
        sb.append(getRecordLockStatus());
        sb.append(", startDate=");
        sb.append(getStartDate());
        sb.append(", createdDate=");
        sb.append(getCreatedDate());
        sb.append(", source=");
        sb.append(getSource());
        sb.append(", createdBy=");
        sb.append(getCreatedBy());
        sb.append(", contractMasterSid=");
        sb.append(getContractMasterSid());
        sb.append(", batchId=");
        sb.append(getBatchId());
        sb.append(", contractAliasName=");
        sb.append(getContractAliasName());
        sb.append(", modifiedBy=");
        sb.append(getModifiedBy());
        sb.append(", inboundStatus=");
        sb.append(getInboundStatus());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(52);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.ContractAliasMaster");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>contractAliasType</column-name><column-value><![CDATA[");
        sb.append(getContractAliasType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>tpCompanyMasterSid</column-name><column-value><![CDATA[");
        sb.append(getTpCompanyMasterSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>endDate</column-name><column-value><![CDATA[");
        sb.append(getEndDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>contractAliasMasterSid</column-name><column-value><![CDATA[");
        sb.append(getContractAliasMasterSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
        sb.append(getModifiedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>contractAliasNo</column-name><column-value><![CDATA[");
        sb.append(getContractAliasNo());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>recordLockStatus</column-name><column-value><![CDATA[");
        sb.append(getRecordLockStatus());
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
            "<column><column-name>source</column-name><column-value><![CDATA[");
        sb.append(getSource());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>createdBy</column-name><column-value><![CDATA[");
        sb.append(getCreatedBy());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>contractMasterSid</column-name><column-value><![CDATA[");
        sb.append(getContractMasterSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>batchId</column-name><column-value><![CDATA[");
        sb.append(getBatchId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>contractAliasName</column-name><column-value><![CDATA[");
        sb.append(getContractAliasName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedBy</column-name><column-value><![CDATA[");
        sb.append(getModifiedBy());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>inboundStatus</column-name><column-value><![CDATA[");
        sb.append(getInboundStatus());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
