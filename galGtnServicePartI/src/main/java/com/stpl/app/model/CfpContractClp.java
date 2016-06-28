package com.stpl.app.model;

import com.stpl.app.service.CfpContractLocalServiceUtil;
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


public class CfpContractClp extends BaseModelImpl<CfpContract>
    implements CfpContract {
    private int _createdBy;
    private int _cfpContractSid;
    private int _cfpType;
    private int _cfpTradeClass;
    private int _modifiedBy;
    private Date _createdDate;
    private int _contractMasterSid;
    private Date _cfpContractAttachedDate;
    private int _cfpModelSid;
    private String _batchId;
    private Date _modifiedDate;
    private boolean _recordLockStatus;
    private String _cfpDesignation;
    private String _cfpName;
    private int _cfpCategory;
    private String _source;
    private int _cfpStatus;
    private int _parentCfpId;
    private int _cfpContractAttachedStatus;
    private Date _cfpStartDate;
    private Date _cfpEndDate;
    private String _parentCfpName;
    private String _inboundStatus;
    private int _salesInclusion;
    private BaseModel<?> _cfpContractRemoteModel;

    public CfpContractClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return CfpContract.class;
    }

    @Override
    public String getModelClassName() {
        return CfpContract.class.getName();
    }

    @Override
    public int getPrimaryKey() {
        return _cfpContractSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setCfpContractSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _cfpContractSid;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Integer) primaryKeyObj).intValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("createdBy", getCreatedBy());
        attributes.put("cfpContractSid", getCfpContractSid());
        attributes.put("cfpType", getCfpType());
        attributes.put("cfpTradeClass", getCfpTradeClass());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("contractMasterSid", getContractMasterSid());
        attributes.put("cfpContractAttachedDate", getCfpContractAttachedDate());
        attributes.put("cfpModelSid", getCfpModelSid());
        attributes.put("batchId", getBatchId());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("recordLockStatus", getRecordLockStatus());
        attributes.put("cfpDesignation", getCfpDesignation());
        attributes.put("cfpName", getCfpName());
        attributes.put("cfpCategory", getCfpCategory());
        attributes.put("source", getSource());
        attributes.put("cfpStatus", getCfpStatus());
        attributes.put("parentCfpId", getParentCfpId());
        attributes.put("cfpContractAttachedStatus",
            getCfpContractAttachedStatus());
        attributes.put("cfpStartDate", getCfpStartDate());
        attributes.put("cfpEndDate", getCfpEndDate());
        attributes.put("parentCfpName", getParentCfpName());
        attributes.put("inboundStatus", getInboundStatus());
        attributes.put("salesInclusion", getSalesInclusion());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Integer createdBy = (Integer) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        Integer cfpContractSid = (Integer) attributes.get("cfpContractSid");

        if (cfpContractSid != null) {
            setCfpContractSid(cfpContractSid);
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

        Integer contractMasterSid = (Integer) attributes.get(
                "contractMasterSid");

        if (contractMasterSid != null) {
            setContractMasterSid(contractMasterSid);
        }

        Date cfpContractAttachedDate = (Date) attributes.get(
                "cfpContractAttachedDate");

        if (cfpContractAttachedDate != null) {
            setCfpContractAttachedDate(cfpContractAttachedDate);
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

        String cfpDesignation = (String) attributes.get("cfpDesignation");

        if (cfpDesignation != null) {
            setCfpDesignation(cfpDesignation);
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

        Integer cfpStatus = (Integer) attributes.get("cfpStatus");

        if (cfpStatus != null) {
            setCfpStatus(cfpStatus);
        }

        Integer parentCfpId = (Integer) attributes.get("parentCfpId");

        if (parentCfpId != null) {
            setParentCfpId(parentCfpId);
        }

        Integer cfpContractAttachedStatus = (Integer) attributes.get(
                "cfpContractAttachedStatus");

        if (cfpContractAttachedStatus != null) {
            setCfpContractAttachedStatus(cfpContractAttachedStatus);
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

        Integer salesInclusion = (Integer) attributes.get("salesInclusion");

        if (salesInclusion != null) {
            setSalesInclusion(salesInclusion);
        }
    }

    @Override
    public int getCreatedBy() {
        return _createdBy;
    }

    @Override
    public void setCreatedBy(int createdBy) {
        _createdBy = createdBy;

        if (_cfpContractRemoteModel != null) {
            try {
                Class<?> clazz = _cfpContractRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedBy", int.class);

                method.invoke(_cfpContractRemoteModel, createdBy);
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

        if (_cfpContractRemoteModel != null) {
            try {
                Class<?> clazz = _cfpContractRemoteModel.getClass();

                Method method = clazz.getMethod("setCfpContractSid", int.class);

                method.invoke(_cfpContractRemoteModel, cfpContractSid);
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

        if (_cfpContractRemoteModel != null) {
            try {
                Class<?> clazz = _cfpContractRemoteModel.getClass();

                Method method = clazz.getMethod("setCfpType", int.class);

                method.invoke(_cfpContractRemoteModel, cfpType);
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

        if (_cfpContractRemoteModel != null) {
            try {
                Class<?> clazz = _cfpContractRemoteModel.getClass();

                Method method = clazz.getMethod("setCfpTradeClass", int.class);

                method.invoke(_cfpContractRemoteModel, cfpTradeClass);
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

        if (_cfpContractRemoteModel != null) {
            try {
                Class<?> clazz = _cfpContractRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedBy", int.class);

                method.invoke(_cfpContractRemoteModel, modifiedBy);
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

        if (_cfpContractRemoteModel != null) {
            try {
                Class<?> clazz = _cfpContractRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedDate", Date.class);

                method.invoke(_cfpContractRemoteModel, createdDate);
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

        if (_cfpContractRemoteModel != null) {
            try {
                Class<?> clazz = _cfpContractRemoteModel.getClass();

                Method method = clazz.getMethod("setContractMasterSid",
                        int.class);

                method.invoke(_cfpContractRemoteModel, contractMasterSid);
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

        if (_cfpContractRemoteModel != null) {
            try {
                Class<?> clazz = _cfpContractRemoteModel.getClass();

                Method method = clazz.getMethod("setCfpContractAttachedDate",
                        Date.class);

                method.invoke(_cfpContractRemoteModel, cfpContractAttachedDate);
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

        if (_cfpContractRemoteModel != null) {
            try {
                Class<?> clazz = _cfpContractRemoteModel.getClass();

                Method method = clazz.getMethod("setCfpModelSid", int.class);

                method.invoke(_cfpContractRemoteModel, cfpModelSid);
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

        if (_cfpContractRemoteModel != null) {
            try {
                Class<?> clazz = _cfpContractRemoteModel.getClass();

                Method method = clazz.getMethod("setBatchId", String.class);

                method.invoke(_cfpContractRemoteModel, batchId);
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

        if (_cfpContractRemoteModel != null) {
            try {
                Class<?> clazz = _cfpContractRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedDate", Date.class);

                method.invoke(_cfpContractRemoteModel, modifiedDate);
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

        if (_cfpContractRemoteModel != null) {
            try {
                Class<?> clazz = _cfpContractRemoteModel.getClass();

                Method method = clazz.getMethod("setRecordLockStatus",
                        boolean.class);

                method.invoke(_cfpContractRemoteModel, recordLockStatus);
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

        if (_cfpContractRemoteModel != null) {
            try {
                Class<?> clazz = _cfpContractRemoteModel.getClass();

                Method method = clazz.getMethod("setCfpDesignation",
                        String.class);

                method.invoke(_cfpContractRemoteModel, cfpDesignation);
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

        if (_cfpContractRemoteModel != null) {
            try {
                Class<?> clazz = _cfpContractRemoteModel.getClass();

                Method method = clazz.getMethod("setCfpName", String.class);

                method.invoke(_cfpContractRemoteModel, cfpName);
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

        if (_cfpContractRemoteModel != null) {
            try {
                Class<?> clazz = _cfpContractRemoteModel.getClass();

                Method method = clazz.getMethod("setCfpCategory", int.class);

                method.invoke(_cfpContractRemoteModel, cfpCategory);
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

        if (_cfpContractRemoteModel != null) {
            try {
                Class<?> clazz = _cfpContractRemoteModel.getClass();

                Method method = clazz.getMethod("setSource", String.class);

                method.invoke(_cfpContractRemoteModel, source);
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

        if (_cfpContractRemoteModel != null) {
            try {
                Class<?> clazz = _cfpContractRemoteModel.getClass();

                Method method = clazz.getMethod("setCfpStatus", int.class);

                method.invoke(_cfpContractRemoteModel, cfpStatus);
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

        if (_cfpContractRemoteModel != null) {
            try {
                Class<?> clazz = _cfpContractRemoteModel.getClass();

                Method method = clazz.getMethod("setParentCfpId", int.class);

                method.invoke(_cfpContractRemoteModel, parentCfpId);
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

        if (_cfpContractRemoteModel != null) {
            try {
                Class<?> clazz = _cfpContractRemoteModel.getClass();

                Method method = clazz.getMethod("setCfpContractAttachedStatus",
                        int.class);

                method.invoke(_cfpContractRemoteModel, cfpContractAttachedStatus);
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

        if (_cfpContractRemoteModel != null) {
            try {
                Class<?> clazz = _cfpContractRemoteModel.getClass();

                Method method = clazz.getMethod("setCfpStartDate", Date.class);

                method.invoke(_cfpContractRemoteModel, cfpStartDate);
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

        if (_cfpContractRemoteModel != null) {
            try {
                Class<?> clazz = _cfpContractRemoteModel.getClass();

                Method method = clazz.getMethod("setCfpEndDate", Date.class);

                method.invoke(_cfpContractRemoteModel, cfpEndDate);
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

        if (_cfpContractRemoteModel != null) {
            try {
                Class<?> clazz = _cfpContractRemoteModel.getClass();

                Method method = clazz.getMethod("setParentCfpName", String.class);

                method.invoke(_cfpContractRemoteModel, parentCfpName);
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

        if (_cfpContractRemoteModel != null) {
            try {
                Class<?> clazz = _cfpContractRemoteModel.getClass();

                Method method = clazz.getMethod("setInboundStatus", String.class);

                method.invoke(_cfpContractRemoteModel, inboundStatus);
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

        if (_cfpContractRemoteModel != null) {
            try {
                Class<?> clazz = _cfpContractRemoteModel.getClass();

                Method method = clazz.getMethod("setSalesInclusion", int.class);

                method.invoke(_cfpContractRemoteModel, salesInclusion);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getCfpContractRemoteModel() {
        return _cfpContractRemoteModel;
    }

    public void setCfpContractRemoteModel(BaseModel<?> cfpContractRemoteModel) {
        _cfpContractRemoteModel = cfpContractRemoteModel;
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

        Class<?> remoteModelClass = _cfpContractRemoteModel.getClass();

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

        Object returnValue = method.invoke(_cfpContractRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            CfpContractLocalServiceUtil.addCfpContract(this);
        } else {
            CfpContractLocalServiceUtil.updateCfpContract(this);
        }
    }

    @Override
    public CfpContract toEscapedModel() {
        return (CfpContract) ProxyUtil.newProxyInstance(CfpContract.class.getClassLoader(),
            new Class[] { CfpContract.class }, new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        CfpContractClp clone = new CfpContractClp();

        clone.setCreatedBy(getCreatedBy());
        clone.setCfpContractSid(getCfpContractSid());
        clone.setCfpType(getCfpType());
        clone.setCfpTradeClass(getCfpTradeClass());
        clone.setModifiedBy(getModifiedBy());
        clone.setCreatedDate(getCreatedDate());
        clone.setContractMasterSid(getContractMasterSid());
        clone.setCfpContractAttachedDate(getCfpContractAttachedDate());
        clone.setCfpModelSid(getCfpModelSid());
        clone.setBatchId(getBatchId());
        clone.setModifiedDate(getModifiedDate());
        clone.setRecordLockStatus(getRecordLockStatus());
        clone.setCfpDesignation(getCfpDesignation());
        clone.setCfpName(getCfpName());
        clone.setCfpCategory(getCfpCategory());
        clone.setSource(getSource());
        clone.setCfpStatus(getCfpStatus());
        clone.setParentCfpId(getParentCfpId());
        clone.setCfpContractAttachedStatus(getCfpContractAttachedStatus());
        clone.setCfpStartDate(getCfpStartDate());
        clone.setCfpEndDate(getCfpEndDate());
        clone.setParentCfpName(getParentCfpName());
        clone.setInboundStatus(getInboundStatus());
        clone.setSalesInclusion(getSalesInclusion());

        return clone;
    }

    @Override
    public int compareTo(CfpContract cfpContract) {
        int primaryKey = cfpContract.getPrimaryKey();

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

        if (!(obj instanceof CfpContractClp)) {
            return false;
        }

        CfpContractClp cfpContract = (CfpContractClp) obj;

        int primaryKey = cfpContract.getPrimaryKey();

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
        StringBundler sb = new StringBundler(49);

        sb.append("{createdBy=");
        sb.append(getCreatedBy());
        sb.append(", cfpContractSid=");
        sb.append(getCfpContractSid());
        sb.append(", cfpType=");
        sb.append(getCfpType());
        sb.append(", cfpTradeClass=");
        sb.append(getCfpTradeClass());
        sb.append(", modifiedBy=");
        sb.append(getModifiedBy());
        sb.append(", createdDate=");
        sb.append(getCreatedDate());
        sb.append(", contractMasterSid=");
        sb.append(getContractMasterSid());
        sb.append(", cfpContractAttachedDate=");
        sb.append(getCfpContractAttachedDate());
        sb.append(", cfpModelSid=");
        sb.append(getCfpModelSid());
        sb.append(", batchId=");
        sb.append(getBatchId());
        sb.append(", modifiedDate=");
        sb.append(getModifiedDate());
        sb.append(", recordLockStatus=");
        sb.append(getRecordLockStatus());
        sb.append(", cfpDesignation=");
        sb.append(getCfpDesignation());
        sb.append(", cfpName=");
        sb.append(getCfpName());
        sb.append(", cfpCategory=");
        sb.append(getCfpCategory());
        sb.append(", source=");
        sb.append(getSource());
        sb.append(", cfpStatus=");
        sb.append(getCfpStatus());
        sb.append(", parentCfpId=");
        sb.append(getParentCfpId());
        sb.append(", cfpContractAttachedStatus=");
        sb.append(getCfpContractAttachedStatus());
        sb.append(", cfpStartDate=");
        sb.append(getCfpStartDate());
        sb.append(", cfpEndDate=");
        sb.append(getCfpEndDate());
        sb.append(", parentCfpName=");
        sb.append(getParentCfpName());
        sb.append(", inboundStatus=");
        sb.append(getInboundStatus());
        sb.append(", salesInclusion=");
        sb.append(getSalesInclusion());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(76);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.CfpContract");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>createdBy</column-name><column-value><![CDATA[");
        sb.append(getCreatedBy());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>cfpContractSid</column-name><column-value><![CDATA[");
        sb.append(getCfpContractSid());
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
            "<column><column-name>contractMasterSid</column-name><column-value><![CDATA[");
        sb.append(getContractMasterSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>cfpContractAttachedDate</column-name><column-value><![CDATA[");
        sb.append(getCfpContractAttachedDate());
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
            "<column><column-name>cfpDesignation</column-name><column-value><![CDATA[");
        sb.append(getCfpDesignation());
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
            "<column><column-name>cfpStatus</column-name><column-value><![CDATA[");
        sb.append(getCfpStatus());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>parentCfpId</column-name><column-value><![CDATA[");
        sb.append(getParentCfpId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>cfpContractAttachedStatus</column-name><column-value><![CDATA[");
        sb.append(getCfpContractAttachedStatus());
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
        sb.append(
            "<column><column-name>salesInclusion</column-name><column-value><![CDATA[");
        sb.append(getSalesInclusion());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
