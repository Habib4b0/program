package com.stpl.app.model;

import com.stpl.app.service.ClpSerializer;
import com.stpl.app.service.IfpContractLocalServiceUtil;

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


public class IfpContractClp extends BaseModelImpl<IfpContract>
    implements IfpContract {
    private String _cfpContractSid;
    private String _parentIfpName;
    private Date _ifpContractAttachedDate;
    private int _ifpStatus;
    private Date _ifpStartDate;
    private int _ifpContractAttachedStatus;
    private Date _modifiedDate;
    private int _ifpCategory;
    private boolean _recordLockStatus;
    private Date _ifpEndDate;
    private Date _createdDate;
    private int _createdBy;
    private String _source;
    private String _ifpDesignation;
    private String _parentIfpId;
    private String _batchId;
    private int _contractMasterSid;
    private int _ifpType;
    private String _ifpName;
    private int _modifiedBy;
    private String _inboundStatus;
    private int _ifpContractSid;
    private int _ifpModelSid;
    private BaseModel<?> _ifpContractRemoteModel;

    public IfpContractClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return IfpContract.class;
    }

    @Override
    public String getModelClassName() {
        return IfpContract.class.getName();
    }

    @Override
    public int getPrimaryKey() {
        return _ifpContractSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setIfpContractSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _ifpContractSid;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Integer) primaryKeyObj).intValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("cfpContractSid", getCfpContractSid());
        attributes.put("parentIfpName", getParentIfpName());
        attributes.put("ifpContractAttachedDate", getIfpContractAttachedDate());
        attributes.put("ifpStatus", getIfpStatus());
        attributes.put("ifpStartDate", getIfpStartDate());
        attributes.put("ifpContractAttachedStatus",
            getIfpContractAttachedStatus());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("ifpCategory", getIfpCategory());
        attributes.put("recordLockStatus", getRecordLockStatus());
        attributes.put("ifpEndDate", getIfpEndDate());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("source", getSource());
        attributes.put("ifpDesignation", getIfpDesignation());
        attributes.put("parentIfpId", getParentIfpId());
        attributes.put("batchId", getBatchId());
        attributes.put("contractMasterSid", getContractMasterSid());
        attributes.put("ifpType", getIfpType());
        attributes.put("ifpName", getIfpName());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("inboundStatus", getInboundStatus());
        attributes.put("ifpContractSid", getIfpContractSid());
        attributes.put("ifpModelSid", getIfpModelSid());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        String cfpContractSid = (String) attributes.get("cfpContractSid");

        if (cfpContractSid != null) {
            setCfpContractSid(cfpContractSid);
        }

        String parentIfpName = (String) attributes.get("parentIfpName");

        if (parentIfpName != null) {
            setParentIfpName(parentIfpName);
        }

        Date ifpContractAttachedDate = (Date) attributes.get(
                "ifpContractAttachedDate");

        if (ifpContractAttachedDate != null) {
            setIfpContractAttachedDate(ifpContractAttachedDate);
        }

        Integer ifpStatus = (Integer) attributes.get("ifpStatus");

        if (ifpStatus != null) {
            setIfpStatus(ifpStatus);
        }

        Date ifpStartDate = (Date) attributes.get("ifpStartDate");

        if (ifpStartDate != null) {
            setIfpStartDate(ifpStartDate);
        }

        Integer ifpContractAttachedStatus = (Integer) attributes.get(
                "ifpContractAttachedStatus");

        if (ifpContractAttachedStatus != null) {
            setIfpContractAttachedStatus(ifpContractAttachedStatus);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        Integer ifpCategory = (Integer) attributes.get("ifpCategory");

        if (ifpCategory != null) {
            setIfpCategory(ifpCategory);
        }

        Boolean recordLockStatus = (Boolean) attributes.get("recordLockStatus");

        if (recordLockStatus != null) {
            setRecordLockStatus(recordLockStatus);
        }

        Date ifpEndDate = (Date) attributes.get("ifpEndDate");

        if (ifpEndDate != null) {
            setIfpEndDate(ifpEndDate);
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

        String ifpDesignation = (String) attributes.get("ifpDesignation");

        if (ifpDesignation != null) {
            setIfpDesignation(ifpDesignation);
        }

        String parentIfpId = (String) attributes.get("parentIfpId");

        if (parentIfpId != null) {
            setParentIfpId(parentIfpId);
        }

        String batchId = (String) attributes.get("batchId");

        if (batchId != null) {
            setBatchId(batchId);
        }

        Integer contractMasterSid = (Integer) attributes.get(
                "contractMasterSid");

        if (contractMasterSid != null) {
            setContractMasterSid(contractMasterSid);
        }

        Integer ifpType = (Integer) attributes.get("ifpType");

        if (ifpType != null) {
            setIfpType(ifpType);
        }

        String ifpName = (String) attributes.get("ifpName");

        if (ifpName != null) {
            setIfpName(ifpName);
        }

        Integer modifiedBy = (Integer) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        String inboundStatus = (String) attributes.get("inboundStatus");

        if (inboundStatus != null) {
            setInboundStatus(inboundStatus);
        }

        Integer ifpContractSid = (Integer) attributes.get("ifpContractSid");

        if (ifpContractSid != null) {
            setIfpContractSid(ifpContractSid);
        }

        Integer ifpModelSid = (Integer) attributes.get("ifpModelSid");

        if (ifpModelSid != null) {
            setIfpModelSid(ifpModelSid);
        }
    }

    @Override
    public String getCfpContractSid() {
        return _cfpContractSid;
    }

    @Override
    public void setCfpContractSid(String cfpContractSid) {
        _cfpContractSid = cfpContractSid;

        if (_ifpContractRemoteModel != null) {
            try {
                Class<?> clazz = _ifpContractRemoteModel.getClass();

                Method method = clazz.getMethod("setCfpContractSid",
                        String.class);

                method.invoke(_ifpContractRemoteModel, cfpContractSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getParentIfpName() {
        return _parentIfpName;
    }

    @Override
    public void setParentIfpName(String parentIfpName) {
        _parentIfpName = parentIfpName;

        if (_ifpContractRemoteModel != null) {
            try {
                Class<?> clazz = _ifpContractRemoteModel.getClass();

                Method method = clazz.getMethod("setParentIfpName", String.class);

                method.invoke(_ifpContractRemoteModel, parentIfpName);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getIfpContractAttachedDate() {
        return _ifpContractAttachedDate;
    }

    @Override
    public void setIfpContractAttachedDate(Date ifpContractAttachedDate) {
        _ifpContractAttachedDate = ifpContractAttachedDate;

        if (_ifpContractRemoteModel != null) {
            try {
                Class<?> clazz = _ifpContractRemoteModel.getClass();

                Method method = clazz.getMethod("setIfpContractAttachedDate",
                        Date.class);

                method.invoke(_ifpContractRemoteModel, ifpContractAttachedDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getIfpStatus() {
        return _ifpStatus;
    }

    @Override
    public void setIfpStatus(int ifpStatus) {
        _ifpStatus = ifpStatus;

        if (_ifpContractRemoteModel != null) {
            try {
                Class<?> clazz = _ifpContractRemoteModel.getClass();

                Method method = clazz.getMethod("setIfpStatus", int.class);

                method.invoke(_ifpContractRemoteModel, ifpStatus);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getIfpStartDate() {
        return _ifpStartDate;
    }

    @Override
    public void setIfpStartDate(Date ifpStartDate) {
        _ifpStartDate = ifpStartDate;

        if (_ifpContractRemoteModel != null) {
            try {
                Class<?> clazz = _ifpContractRemoteModel.getClass();

                Method method = clazz.getMethod("setIfpStartDate", Date.class);

                method.invoke(_ifpContractRemoteModel, ifpStartDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getIfpContractAttachedStatus() {
        return _ifpContractAttachedStatus;
    }

    @Override
    public void setIfpContractAttachedStatus(int ifpContractAttachedStatus) {
        _ifpContractAttachedStatus = ifpContractAttachedStatus;

        if (_ifpContractRemoteModel != null) {
            try {
                Class<?> clazz = _ifpContractRemoteModel.getClass();

                Method method = clazz.getMethod("setIfpContractAttachedStatus",
                        int.class);

                method.invoke(_ifpContractRemoteModel, ifpContractAttachedStatus);
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

        if (_ifpContractRemoteModel != null) {
            try {
                Class<?> clazz = _ifpContractRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedDate", Date.class);

                method.invoke(_ifpContractRemoteModel, modifiedDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getIfpCategory() {
        return _ifpCategory;
    }

    @Override
    public void setIfpCategory(int ifpCategory) {
        _ifpCategory = ifpCategory;

        if (_ifpContractRemoteModel != null) {
            try {
                Class<?> clazz = _ifpContractRemoteModel.getClass();

                Method method = clazz.getMethod("setIfpCategory", int.class);

                method.invoke(_ifpContractRemoteModel, ifpCategory);
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

        if (_ifpContractRemoteModel != null) {
            try {
                Class<?> clazz = _ifpContractRemoteModel.getClass();

                Method method = clazz.getMethod("setRecordLockStatus",
                        boolean.class);

                method.invoke(_ifpContractRemoteModel, recordLockStatus);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getIfpEndDate() {
        return _ifpEndDate;
    }

    @Override
    public void setIfpEndDate(Date ifpEndDate) {
        _ifpEndDate = ifpEndDate;

        if (_ifpContractRemoteModel != null) {
            try {
                Class<?> clazz = _ifpContractRemoteModel.getClass();

                Method method = clazz.getMethod("setIfpEndDate", Date.class);

                method.invoke(_ifpContractRemoteModel, ifpEndDate);
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

        if (_ifpContractRemoteModel != null) {
            try {
                Class<?> clazz = _ifpContractRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedDate", Date.class);

                method.invoke(_ifpContractRemoteModel, createdDate);
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

        if (_ifpContractRemoteModel != null) {
            try {
                Class<?> clazz = _ifpContractRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedBy", int.class);

                method.invoke(_ifpContractRemoteModel, createdBy);
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

        if (_ifpContractRemoteModel != null) {
            try {
                Class<?> clazz = _ifpContractRemoteModel.getClass();

                Method method = clazz.getMethod("setSource", String.class);

                method.invoke(_ifpContractRemoteModel, source);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getIfpDesignation() {
        return _ifpDesignation;
    }

    @Override
    public void setIfpDesignation(String ifpDesignation) {
        _ifpDesignation = ifpDesignation;

        if (_ifpContractRemoteModel != null) {
            try {
                Class<?> clazz = _ifpContractRemoteModel.getClass();

                Method method = clazz.getMethod("setIfpDesignation",
                        String.class);

                method.invoke(_ifpContractRemoteModel, ifpDesignation);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getParentIfpId() {
        return _parentIfpId;
    }

    @Override
    public void setParentIfpId(String parentIfpId) {
        _parentIfpId = parentIfpId;

        if (_ifpContractRemoteModel != null) {
            try {
                Class<?> clazz = _ifpContractRemoteModel.getClass();

                Method method = clazz.getMethod("setParentIfpId", String.class);

                method.invoke(_ifpContractRemoteModel, parentIfpId);
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

        if (_ifpContractRemoteModel != null) {
            try {
                Class<?> clazz = _ifpContractRemoteModel.getClass();

                Method method = clazz.getMethod("setBatchId", String.class);

                method.invoke(_ifpContractRemoteModel, batchId);
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

        if (_ifpContractRemoteModel != null) {
            try {
                Class<?> clazz = _ifpContractRemoteModel.getClass();

                Method method = clazz.getMethod("setContractMasterSid",
                        int.class);

                method.invoke(_ifpContractRemoteModel, contractMasterSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getIfpType() {
        return _ifpType;
    }

    @Override
    public void setIfpType(int ifpType) {
        _ifpType = ifpType;

        if (_ifpContractRemoteModel != null) {
            try {
                Class<?> clazz = _ifpContractRemoteModel.getClass();

                Method method = clazz.getMethod("setIfpType", int.class);

                method.invoke(_ifpContractRemoteModel, ifpType);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getIfpName() {
        return _ifpName;
    }

    @Override
    public void setIfpName(String ifpName) {
        _ifpName = ifpName;

        if (_ifpContractRemoteModel != null) {
            try {
                Class<?> clazz = _ifpContractRemoteModel.getClass();

                Method method = clazz.getMethod("setIfpName", String.class);

                method.invoke(_ifpContractRemoteModel, ifpName);
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

        if (_ifpContractRemoteModel != null) {
            try {
                Class<?> clazz = _ifpContractRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedBy", int.class);

                method.invoke(_ifpContractRemoteModel, modifiedBy);
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

        if (_ifpContractRemoteModel != null) {
            try {
                Class<?> clazz = _ifpContractRemoteModel.getClass();

                Method method = clazz.getMethod("setInboundStatus", String.class);

                method.invoke(_ifpContractRemoteModel, inboundStatus);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getIfpContractSid() {
        return _ifpContractSid;
    }

    @Override
    public void setIfpContractSid(int ifpContractSid) {
        _ifpContractSid = ifpContractSid;

        if (_ifpContractRemoteModel != null) {
            try {
                Class<?> clazz = _ifpContractRemoteModel.getClass();

                Method method = clazz.getMethod("setIfpContractSid", int.class);

                method.invoke(_ifpContractRemoteModel, ifpContractSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getIfpModelSid() {
        return _ifpModelSid;
    }

    @Override
    public void setIfpModelSid(int ifpModelSid) {
        _ifpModelSid = ifpModelSid;

        if (_ifpContractRemoteModel != null) {
            try {
                Class<?> clazz = _ifpContractRemoteModel.getClass();

                Method method = clazz.getMethod("setIfpModelSid", int.class);

                method.invoke(_ifpContractRemoteModel, ifpModelSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getIfpContractRemoteModel() {
        return _ifpContractRemoteModel;
    }

    public void setIfpContractRemoteModel(BaseModel<?> ifpContractRemoteModel) {
        _ifpContractRemoteModel = ifpContractRemoteModel;
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

        Class<?> remoteModelClass = _ifpContractRemoteModel.getClass();

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

        Object returnValue = method.invoke(_ifpContractRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            IfpContractLocalServiceUtil.addIfpContract(this);
        } else {
            IfpContractLocalServiceUtil.updateIfpContract(this);
        }
    }

    @Override
    public IfpContract toEscapedModel() {
        return (IfpContract) ProxyUtil.newProxyInstance(IfpContract.class.getClassLoader(),
            new Class[] { IfpContract.class }, new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        IfpContractClp clone = new IfpContractClp();

        clone.setCfpContractSid(getCfpContractSid());
        clone.setParentIfpName(getParentIfpName());
        clone.setIfpContractAttachedDate(getIfpContractAttachedDate());
        clone.setIfpStatus(getIfpStatus());
        clone.setIfpStartDate(getIfpStartDate());
        clone.setIfpContractAttachedStatus(getIfpContractAttachedStatus());
        clone.setModifiedDate(getModifiedDate());
        clone.setIfpCategory(getIfpCategory());
        clone.setRecordLockStatus(getRecordLockStatus());
        clone.setIfpEndDate(getIfpEndDate());
        clone.setCreatedDate(getCreatedDate());
        clone.setCreatedBy(getCreatedBy());
        clone.setSource(getSource());
        clone.setIfpDesignation(getIfpDesignation());
        clone.setParentIfpId(getParentIfpId());
        clone.setBatchId(getBatchId());
        clone.setContractMasterSid(getContractMasterSid());
        clone.setIfpType(getIfpType());
        clone.setIfpName(getIfpName());
        clone.setModifiedBy(getModifiedBy());
        clone.setInboundStatus(getInboundStatus());
        clone.setIfpContractSid(getIfpContractSid());
        clone.setIfpModelSid(getIfpModelSid());

        return clone;
    }

    @Override
    public int compareTo(IfpContract ifpContract) {
        int primaryKey = ifpContract.getPrimaryKey();

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

        if (!(obj instanceof IfpContractClp)) {
            return false;
        }

        IfpContractClp ifpContract = (IfpContractClp) obj;

        int primaryKey = ifpContract.getPrimaryKey();

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

        sb.append("{cfpContractSid=");
        sb.append(getCfpContractSid());
        sb.append(", parentIfpName=");
        sb.append(getParentIfpName());
        sb.append(", ifpContractAttachedDate=");
        sb.append(getIfpContractAttachedDate());
        sb.append(", ifpStatus=");
        sb.append(getIfpStatus());
        sb.append(", ifpStartDate=");
        sb.append(getIfpStartDate());
        sb.append(", ifpContractAttachedStatus=");
        sb.append(getIfpContractAttachedStatus());
        sb.append(", modifiedDate=");
        sb.append(getModifiedDate());
        sb.append(", ifpCategory=");
        sb.append(getIfpCategory());
        sb.append(", recordLockStatus=");
        sb.append(getRecordLockStatus());
        sb.append(", ifpEndDate=");
        sb.append(getIfpEndDate());
        sb.append(", createdDate=");
        sb.append(getCreatedDate());
        sb.append(", createdBy=");
        sb.append(getCreatedBy());
        sb.append(", source=");
        sb.append(getSource());
        sb.append(", ifpDesignation=");
        sb.append(getIfpDesignation());
        sb.append(", parentIfpId=");
        sb.append(getParentIfpId());
        sb.append(", batchId=");
        sb.append(getBatchId());
        sb.append(", contractMasterSid=");
        sb.append(getContractMasterSid());
        sb.append(", ifpType=");
        sb.append(getIfpType());
        sb.append(", ifpName=");
        sb.append(getIfpName());
        sb.append(", modifiedBy=");
        sb.append(getModifiedBy());
        sb.append(", inboundStatus=");
        sb.append(getInboundStatus());
        sb.append(", ifpContractSid=");
        sb.append(getIfpContractSid());
        sb.append(", ifpModelSid=");
        sb.append(getIfpModelSid());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(73);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.IfpContract");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>cfpContractSid</column-name><column-value><![CDATA[");
        sb.append(getCfpContractSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>parentIfpName</column-name><column-value><![CDATA[");
        sb.append(getParentIfpName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>ifpContractAttachedDate</column-name><column-value><![CDATA[");
        sb.append(getIfpContractAttachedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>ifpStatus</column-name><column-value><![CDATA[");
        sb.append(getIfpStatus());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>ifpStartDate</column-name><column-value><![CDATA[");
        sb.append(getIfpStartDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>ifpContractAttachedStatus</column-name><column-value><![CDATA[");
        sb.append(getIfpContractAttachedStatus());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
        sb.append(getModifiedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>ifpCategory</column-name><column-value><![CDATA[");
        sb.append(getIfpCategory());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>recordLockStatus</column-name><column-value><![CDATA[");
        sb.append(getRecordLockStatus());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>ifpEndDate</column-name><column-value><![CDATA[");
        sb.append(getIfpEndDate());
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
            "<column><column-name>ifpDesignation</column-name><column-value><![CDATA[");
        sb.append(getIfpDesignation());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>parentIfpId</column-name><column-value><![CDATA[");
        sb.append(getParentIfpId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>batchId</column-name><column-value><![CDATA[");
        sb.append(getBatchId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>contractMasterSid</column-name><column-value><![CDATA[");
        sb.append(getContractMasterSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>ifpType</column-name><column-value><![CDATA[");
        sb.append(getIfpType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>ifpName</column-name><column-value><![CDATA[");
        sb.append(getIfpName());
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
            "<column><column-name>ifpContractSid</column-name><column-value><![CDATA[");
        sb.append(getIfpContractSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>ifpModelSid</column-name><column-value><![CDATA[");
        sb.append(getIfpModelSid());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
