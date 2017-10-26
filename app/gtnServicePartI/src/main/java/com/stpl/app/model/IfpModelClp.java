package com.stpl.app.model;

import com.stpl.app.service.ClpSerializer;
import com.stpl.app.service.IfpModelLocalServiceUtil;

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


public class IfpModelClp extends BaseModelImpl<IfpModel> implements IfpModel {
    private int _modifiedBy;
    private String _totalDollarCommitment;
    private Date _createdDate;
    private int _ifpStatus;
    private String _totalVolumeCommitment;
    private String _batchId;
    private String _internalNotes;
    private String _ifpId;
    private String _totalMarketshareCommitment;
    private int _ifpCategory;
    private String _parentIfpName;
    private Date _ifpEndDate;
    private String _ifpDesignation;
    private int _createdBy;
    private Date _ifpStartDate;
    private String _parentIfpId;
    private String _commitmentPeriod;
    private int _ifpType;
    private Date _modifiedDate;
    private int _ifpModelSid;
    private boolean _recordLockStatus;
    private String _source;
    private String _ifpName;
    private String _ifpNo;
    private String _inboundStatus;
    private BaseModel<?> _ifpModelRemoteModel;

    public IfpModelClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return IfpModel.class;
    }

    @Override
    public String getModelClassName() {
        return IfpModel.class.getName();
    }

    @Override
    public int getPrimaryKey() {
        return _ifpModelSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setIfpModelSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _ifpModelSid;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Integer) primaryKeyObj).intValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("totalDollarCommitment", getTotalDollarCommitment());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("ifpStatus", getIfpStatus());
        attributes.put("totalVolumeCommitment", getTotalVolumeCommitment());
        attributes.put("batchId", getBatchId());
        attributes.put("internalNotes", getInternalNotes());
        attributes.put("ifpId", getIfpId());
        attributes.put("totalMarketshareCommitment",
            getTotalMarketshareCommitment());
        attributes.put("ifpCategory", getIfpCategory());
        attributes.put("parentIfpName", getParentIfpName());
        attributes.put("ifpEndDate", getIfpEndDate());
        attributes.put("ifpDesignation", getIfpDesignation());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("ifpStartDate", getIfpStartDate());
        attributes.put("parentIfpId", getParentIfpId());
        attributes.put("commitmentPeriod", getCommitmentPeriod());
        attributes.put("ifpType", getIfpType());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("ifpModelSid", getIfpModelSid());
        attributes.put("recordLockStatus", getRecordLockStatus());
        attributes.put("source", getSource());
        attributes.put("ifpName", getIfpName());
        attributes.put("ifpNo", getIfpNo());
        attributes.put("inboundStatus", getInboundStatus());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Integer modifiedBy = (Integer) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        String totalDollarCommitment = (String) attributes.get(
                "totalDollarCommitment");

        if (totalDollarCommitment != null) {
            setTotalDollarCommitment(totalDollarCommitment);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        Integer ifpStatus = (Integer) attributes.get("ifpStatus");

        if (ifpStatus != null) {
            setIfpStatus(ifpStatus);
        }

        String totalVolumeCommitment = (String) attributes.get(
                "totalVolumeCommitment");

        if (totalVolumeCommitment != null) {
            setTotalVolumeCommitment(totalVolumeCommitment);
        }

        String batchId = (String) attributes.get("batchId");

        if (batchId != null) {
            setBatchId(batchId);
        }

        String internalNotes = (String) attributes.get("internalNotes");

        if (internalNotes != null) {
            setInternalNotes(internalNotes);
        }

        String ifpId = (String) attributes.get("ifpId");

        if (ifpId != null) {
            setIfpId(ifpId);
        }

        String totalMarketshareCommitment = (String) attributes.get(
                "totalMarketshareCommitment");

        if (totalMarketshareCommitment != null) {
            setTotalMarketshareCommitment(totalMarketshareCommitment);
        }

        Integer ifpCategory = (Integer) attributes.get("ifpCategory");

        if (ifpCategory != null) {
            setIfpCategory(ifpCategory);
        }

        String parentIfpName = (String) attributes.get("parentIfpName");

        if (parentIfpName != null) {
            setParentIfpName(parentIfpName);
        }

        Date ifpEndDate = (Date) attributes.get("ifpEndDate");

        if (ifpEndDate != null) {
            setIfpEndDate(ifpEndDate);
        }

        String ifpDesignation = (String) attributes.get("ifpDesignation");

        if (ifpDesignation != null) {
            setIfpDesignation(ifpDesignation);
        }

        Integer createdBy = (Integer) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        Date ifpStartDate = (Date) attributes.get("ifpStartDate");

        if (ifpStartDate != null) {
            setIfpStartDate(ifpStartDate);
        }

        String parentIfpId = (String) attributes.get("parentIfpId");

        if (parentIfpId != null) {
            setParentIfpId(parentIfpId);
        }

        String commitmentPeriod = (String) attributes.get("commitmentPeriod");

        if (commitmentPeriod != null) {
            setCommitmentPeriod(commitmentPeriod);
        }

        Integer ifpType = (Integer) attributes.get("ifpType");

        if (ifpType != null) {
            setIfpType(ifpType);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        Integer ifpModelSid = (Integer) attributes.get("ifpModelSid");

        if (ifpModelSid != null) {
            setIfpModelSid(ifpModelSid);
        }

        Boolean recordLockStatus = (Boolean) attributes.get("recordLockStatus");

        if (recordLockStatus != null) {
            setRecordLockStatus(recordLockStatus);
        }

        String source = (String) attributes.get("source");

        if (source != null) {
            setSource(source);
        }

        String ifpName = (String) attributes.get("ifpName");

        if (ifpName != null) {
            setIfpName(ifpName);
        }

        String ifpNo = (String) attributes.get("ifpNo");

        if (ifpNo != null) {
            setIfpNo(ifpNo);
        }

        String inboundStatus = (String) attributes.get("inboundStatus");

        if (inboundStatus != null) {
            setInboundStatus(inboundStatus);
        }
    }

    @Override
    public int getModifiedBy() {
        return _modifiedBy;
    }

    @Override
    public void setModifiedBy(int modifiedBy) {
        _modifiedBy = modifiedBy;

        if (_ifpModelRemoteModel != null) {
            try {
                Class<?> clazz = _ifpModelRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedBy", int.class);

                method.invoke(_ifpModelRemoteModel, modifiedBy);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getTotalDollarCommitment() {
        return _totalDollarCommitment;
    }

    @Override
    public void setTotalDollarCommitment(String totalDollarCommitment) {
        _totalDollarCommitment = totalDollarCommitment;

        if (_ifpModelRemoteModel != null) {
            try {
                Class<?> clazz = _ifpModelRemoteModel.getClass();

                Method method = clazz.getMethod("setTotalDollarCommitment",
                        String.class);

                method.invoke(_ifpModelRemoteModel, totalDollarCommitment);
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

        if (_ifpModelRemoteModel != null) {
            try {
                Class<?> clazz = _ifpModelRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedDate", Date.class);

                method.invoke(_ifpModelRemoteModel, createdDate);
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

        if (_ifpModelRemoteModel != null) {
            try {
                Class<?> clazz = _ifpModelRemoteModel.getClass();

                Method method = clazz.getMethod("setIfpStatus", int.class);

                method.invoke(_ifpModelRemoteModel, ifpStatus);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getTotalVolumeCommitment() {
        return _totalVolumeCommitment;
    }

    @Override
    public void setTotalVolumeCommitment(String totalVolumeCommitment) {
        _totalVolumeCommitment = totalVolumeCommitment;

        if (_ifpModelRemoteModel != null) {
            try {
                Class<?> clazz = _ifpModelRemoteModel.getClass();

                Method method = clazz.getMethod("setTotalVolumeCommitment",
                        String.class);

                method.invoke(_ifpModelRemoteModel, totalVolumeCommitment);
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

        if (_ifpModelRemoteModel != null) {
            try {
                Class<?> clazz = _ifpModelRemoteModel.getClass();

                Method method = clazz.getMethod("setBatchId", String.class);

                method.invoke(_ifpModelRemoteModel, batchId);
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

        if (_ifpModelRemoteModel != null) {
            try {
                Class<?> clazz = _ifpModelRemoteModel.getClass();

                Method method = clazz.getMethod("setInternalNotes", String.class);

                method.invoke(_ifpModelRemoteModel, internalNotes);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getIfpId() {
        return _ifpId;
    }

    @Override
    public void setIfpId(String ifpId) {
        _ifpId = ifpId;

        if (_ifpModelRemoteModel != null) {
            try {
                Class<?> clazz = _ifpModelRemoteModel.getClass();

                Method method = clazz.getMethod("setIfpId", String.class);

                method.invoke(_ifpModelRemoteModel, ifpId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getTotalMarketshareCommitment() {
        return _totalMarketshareCommitment;
    }

    @Override
    public void setTotalMarketshareCommitment(String totalMarketshareCommitment) {
        _totalMarketshareCommitment = totalMarketshareCommitment;

        if (_ifpModelRemoteModel != null) {
            try {
                Class<?> clazz = _ifpModelRemoteModel.getClass();

                Method method = clazz.getMethod("setTotalMarketshareCommitment",
                        String.class);

                method.invoke(_ifpModelRemoteModel, totalMarketshareCommitment);
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

        if (_ifpModelRemoteModel != null) {
            try {
                Class<?> clazz = _ifpModelRemoteModel.getClass();

                Method method = clazz.getMethod("setIfpCategory", int.class);

                method.invoke(_ifpModelRemoteModel, ifpCategory);
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

        if (_ifpModelRemoteModel != null) {
            try {
                Class<?> clazz = _ifpModelRemoteModel.getClass();

                Method method = clazz.getMethod("setParentIfpName", String.class);

                method.invoke(_ifpModelRemoteModel, parentIfpName);
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

        if (_ifpModelRemoteModel != null) {
            try {
                Class<?> clazz = _ifpModelRemoteModel.getClass();

                Method method = clazz.getMethod("setIfpEndDate", Date.class);

                method.invoke(_ifpModelRemoteModel, ifpEndDate);
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

        if (_ifpModelRemoteModel != null) {
            try {
                Class<?> clazz = _ifpModelRemoteModel.getClass();

                Method method = clazz.getMethod("setIfpDesignation",
                        String.class);

                method.invoke(_ifpModelRemoteModel, ifpDesignation);
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

        if (_ifpModelRemoteModel != null) {
            try {
                Class<?> clazz = _ifpModelRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedBy", int.class);

                method.invoke(_ifpModelRemoteModel, createdBy);
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

        if (_ifpModelRemoteModel != null) {
            try {
                Class<?> clazz = _ifpModelRemoteModel.getClass();

                Method method = clazz.getMethod("setIfpStartDate", Date.class);

                method.invoke(_ifpModelRemoteModel, ifpStartDate);
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

        if (_ifpModelRemoteModel != null) {
            try {
                Class<?> clazz = _ifpModelRemoteModel.getClass();

                Method method = clazz.getMethod("setParentIfpId", String.class);

                method.invoke(_ifpModelRemoteModel, parentIfpId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getCommitmentPeriod() {
        return _commitmentPeriod;
    }

    @Override
    public void setCommitmentPeriod(String commitmentPeriod) {
        _commitmentPeriod = commitmentPeriod;

        if (_ifpModelRemoteModel != null) {
            try {
                Class<?> clazz = _ifpModelRemoteModel.getClass();

                Method method = clazz.getMethod("setCommitmentPeriod",
                        String.class);

                method.invoke(_ifpModelRemoteModel, commitmentPeriod);
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

        if (_ifpModelRemoteModel != null) {
            try {
                Class<?> clazz = _ifpModelRemoteModel.getClass();

                Method method = clazz.getMethod("setIfpType", int.class);

                method.invoke(_ifpModelRemoteModel, ifpType);
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

        if (_ifpModelRemoteModel != null) {
            try {
                Class<?> clazz = _ifpModelRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedDate", Date.class);

                method.invoke(_ifpModelRemoteModel, modifiedDate);
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

        if (_ifpModelRemoteModel != null) {
            try {
                Class<?> clazz = _ifpModelRemoteModel.getClass();

                Method method = clazz.getMethod("setIfpModelSid", int.class);

                method.invoke(_ifpModelRemoteModel, ifpModelSid);
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

        if (_ifpModelRemoteModel != null) {
            try {
                Class<?> clazz = _ifpModelRemoteModel.getClass();

                Method method = clazz.getMethod("setRecordLockStatus",
                        boolean.class);

                method.invoke(_ifpModelRemoteModel, recordLockStatus);
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

        if (_ifpModelRemoteModel != null) {
            try {
                Class<?> clazz = _ifpModelRemoteModel.getClass();

                Method method = clazz.getMethod("setSource", String.class);

                method.invoke(_ifpModelRemoteModel, source);
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

        if (_ifpModelRemoteModel != null) {
            try {
                Class<?> clazz = _ifpModelRemoteModel.getClass();

                Method method = clazz.getMethod("setIfpName", String.class);

                method.invoke(_ifpModelRemoteModel, ifpName);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getIfpNo() {
        return _ifpNo;
    }

    @Override
    public void setIfpNo(String ifpNo) {
        _ifpNo = ifpNo;

        if (_ifpModelRemoteModel != null) {
            try {
                Class<?> clazz = _ifpModelRemoteModel.getClass();

                Method method = clazz.getMethod("setIfpNo", String.class);

                method.invoke(_ifpModelRemoteModel, ifpNo);
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

        if (_ifpModelRemoteModel != null) {
            try {
                Class<?> clazz = _ifpModelRemoteModel.getClass();

                Method method = clazz.getMethod("setInboundStatus", String.class);

                method.invoke(_ifpModelRemoteModel, inboundStatus);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getIfpModelRemoteModel() {
        return _ifpModelRemoteModel;
    }

    public void setIfpModelRemoteModel(BaseModel<?> ifpModelRemoteModel) {
        _ifpModelRemoteModel = ifpModelRemoteModel;
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

        Class<?> remoteModelClass = _ifpModelRemoteModel.getClass();

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

        Object returnValue = method.invoke(_ifpModelRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            IfpModelLocalServiceUtil.addIfpModel(this);
        } else {
            IfpModelLocalServiceUtil.updateIfpModel(this);
        }
    }

    @Override
    public IfpModel toEscapedModel() {
        return (IfpModel) ProxyUtil.newProxyInstance(IfpModel.class.getClassLoader(),
            new Class[] { IfpModel.class }, new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        IfpModelClp clone = new IfpModelClp();

        clone.setModifiedBy(getModifiedBy());
        clone.setTotalDollarCommitment(getTotalDollarCommitment());
        clone.setCreatedDate(getCreatedDate());
        clone.setIfpStatus(getIfpStatus());
        clone.setTotalVolumeCommitment(getTotalVolumeCommitment());
        clone.setBatchId(getBatchId());
        clone.setInternalNotes(getInternalNotes());
        clone.setIfpId(getIfpId());
        clone.setTotalMarketshareCommitment(getTotalMarketshareCommitment());
        clone.setIfpCategory(getIfpCategory());
        clone.setParentIfpName(getParentIfpName());
        clone.setIfpEndDate(getIfpEndDate());
        clone.setIfpDesignation(getIfpDesignation());
        clone.setCreatedBy(getCreatedBy());
        clone.setIfpStartDate(getIfpStartDate());
        clone.setParentIfpId(getParentIfpId());
        clone.setCommitmentPeriod(getCommitmentPeriod());
        clone.setIfpType(getIfpType());
        clone.setModifiedDate(getModifiedDate());
        clone.setIfpModelSid(getIfpModelSid());
        clone.setRecordLockStatus(getRecordLockStatus());
        clone.setSource(getSource());
        clone.setIfpName(getIfpName());
        clone.setIfpNo(getIfpNo());
        clone.setInboundStatus(getInboundStatus());

        return clone;
    }

    @Override
    public int compareTo(IfpModel ifpModel) {
        int primaryKey = ifpModel.getPrimaryKey();

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

        if (!(obj instanceof IfpModelClp)) {
            return false;
        }

        IfpModelClp ifpModel = (IfpModelClp) obj;

        int primaryKey = ifpModel.getPrimaryKey();

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
        StringBundler sb = new StringBundler(51);

        sb.append("{modifiedBy=");
        sb.append(getModifiedBy());
        sb.append(", totalDollarCommitment=");
        sb.append(getTotalDollarCommitment());
        sb.append(", createdDate=");
        sb.append(getCreatedDate());
        sb.append(", ifpStatus=");
        sb.append(getIfpStatus());
        sb.append(", totalVolumeCommitment=");
        sb.append(getTotalVolumeCommitment());
        sb.append(", batchId=");
        sb.append(getBatchId());
        sb.append(", internalNotes=");
        sb.append(getInternalNotes());
        sb.append(", ifpId=");
        sb.append(getIfpId());
        sb.append(", totalMarketshareCommitment=");
        sb.append(getTotalMarketshareCommitment());
        sb.append(", ifpCategory=");
        sb.append(getIfpCategory());
        sb.append(", parentIfpName=");
        sb.append(getParentIfpName());
        sb.append(", ifpEndDate=");
        sb.append(getIfpEndDate());
        sb.append(", ifpDesignation=");
        sb.append(getIfpDesignation());
        sb.append(", createdBy=");
        sb.append(getCreatedBy());
        sb.append(", ifpStartDate=");
        sb.append(getIfpStartDate());
        sb.append(", parentIfpId=");
        sb.append(getParentIfpId());
        sb.append(", commitmentPeriod=");
        sb.append(getCommitmentPeriod());
        sb.append(", ifpType=");
        sb.append(getIfpType());
        sb.append(", modifiedDate=");
        sb.append(getModifiedDate());
        sb.append(", ifpModelSid=");
        sb.append(getIfpModelSid());
        sb.append(", recordLockStatus=");
        sb.append(getRecordLockStatus());
        sb.append(", source=");
        sb.append(getSource());
        sb.append(", ifpName=");
        sb.append(getIfpName());
        sb.append(", ifpNo=");
        sb.append(getIfpNo());
        sb.append(", inboundStatus=");
        sb.append(getInboundStatus());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(79);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.IfpModel");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>modifiedBy</column-name><column-value><![CDATA[");
        sb.append(getModifiedBy());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>totalDollarCommitment</column-name><column-value><![CDATA[");
        sb.append(getTotalDollarCommitment());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>createdDate</column-name><column-value><![CDATA[");
        sb.append(getCreatedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>ifpStatus</column-name><column-value><![CDATA[");
        sb.append(getIfpStatus());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>totalVolumeCommitment</column-name><column-value><![CDATA[");
        sb.append(getTotalVolumeCommitment());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>batchId</column-name><column-value><![CDATA[");
        sb.append(getBatchId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>internalNotes</column-name><column-value><![CDATA[");
        sb.append(getInternalNotes());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>ifpId</column-name><column-value><![CDATA[");
        sb.append(getIfpId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>totalMarketshareCommitment</column-name><column-value><![CDATA[");
        sb.append(getTotalMarketshareCommitment());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>ifpCategory</column-name><column-value><![CDATA[");
        sb.append(getIfpCategory());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>parentIfpName</column-name><column-value><![CDATA[");
        sb.append(getParentIfpName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>ifpEndDate</column-name><column-value><![CDATA[");
        sb.append(getIfpEndDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>ifpDesignation</column-name><column-value><![CDATA[");
        sb.append(getIfpDesignation());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>createdBy</column-name><column-value><![CDATA[");
        sb.append(getCreatedBy());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>ifpStartDate</column-name><column-value><![CDATA[");
        sb.append(getIfpStartDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>parentIfpId</column-name><column-value><![CDATA[");
        sb.append(getParentIfpId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>commitmentPeriod</column-name><column-value><![CDATA[");
        sb.append(getCommitmentPeriod());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>ifpType</column-name><column-value><![CDATA[");
        sb.append(getIfpType());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
        sb.append(getModifiedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>ifpModelSid</column-name><column-value><![CDATA[");
        sb.append(getIfpModelSid());
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
            "<column><column-name>ifpName</column-name><column-value><![CDATA[");
        sb.append(getIfpName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>ifpNo</column-name><column-value><![CDATA[");
        sb.append(getIfpNo());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>inboundStatus</column-name><column-value><![CDATA[");
        sb.append(getInboundStatus());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
