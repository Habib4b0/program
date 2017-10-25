package com.stpl.app.model;

import com.stpl.app.service.ClpSerializer;
import com.stpl.app.service.IfpContractDetailsLocalServiceUtil;

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


public class IfpContractDetailsClp extends BaseModelImpl<IfpContractDetails>
    implements IfpContractDetails {
    private int _itemStatus;
    private int _itemMasterSid;
    private Date _ifpContractAttachedDate;
    private Date _itemEndDate;
    private String _totalVolumeCommitment;
    private String _totalDollarCommitment;
    private int _ifpContractAttachedStatus;
    private Date _modifiedDate;
    private String _totalMarketshareCommitment;
    private boolean _recordLockStatus;
    private Date _createdDate;
    private int _createdBy;
    private String _source;
    private Date _itemStartDate;
    private String _batchId;
    private int _ifpContractDetailsSid;
    private int _modifiedBy;
    private String _inboundStatus;
    private int _ifpContractSid;
    private String _commitmentPeriod;
    private BaseModel<?> _ifpContractDetailsRemoteModel;

    public IfpContractDetailsClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return IfpContractDetails.class;
    }

    @Override
    public String getModelClassName() {
        return IfpContractDetails.class.getName();
    }

    @Override
    public int getPrimaryKey() {
        return _ifpContractDetailsSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setIfpContractDetailsSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _ifpContractDetailsSid;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Integer) primaryKeyObj).intValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("itemStatus", getItemStatus());
        attributes.put("itemMasterSid", getItemMasterSid());
        attributes.put("ifpContractAttachedDate", getIfpContractAttachedDate());
        attributes.put("itemEndDate", getItemEndDate());
        attributes.put("totalVolumeCommitment", getTotalVolumeCommitment());
        attributes.put("totalDollarCommitment", getTotalDollarCommitment());
        attributes.put("ifpContractAttachedStatus",
            getIfpContractAttachedStatus());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("totalMarketshareCommitment",
            getTotalMarketshareCommitment());
        attributes.put("recordLockStatus", getRecordLockStatus());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("source", getSource());
        attributes.put("itemStartDate", getItemStartDate());
        attributes.put("batchId", getBatchId());
        attributes.put("ifpContractDetailsSid", getIfpContractDetailsSid());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("inboundStatus", getInboundStatus());
        attributes.put("ifpContractSid", getIfpContractSid());
        attributes.put("commitmentPeriod", getCommitmentPeriod());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Integer itemStatus = (Integer) attributes.get("itemStatus");

        if (itemStatus != null) {
            setItemStatus(itemStatus);
        }

        Integer itemMasterSid = (Integer) attributes.get("itemMasterSid");

        if (itemMasterSid != null) {
            setItemMasterSid(itemMasterSid);
        }

        Date ifpContractAttachedDate = (Date) attributes.get(
                "ifpContractAttachedDate");

        if (ifpContractAttachedDate != null) {
            setIfpContractAttachedDate(ifpContractAttachedDate);
        }

        Date itemEndDate = (Date) attributes.get("itemEndDate");

        if (itemEndDate != null) {
            setItemEndDate(itemEndDate);
        }

        String totalVolumeCommitment = (String) attributes.get(
                "totalVolumeCommitment");

        if (totalVolumeCommitment != null) {
            setTotalVolumeCommitment(totalVolumeCommitment);
        }

        String totalDollarCommitment = (String) attributes.get(
                "totalDollarCommitment");

        if (totalDollarCommitment != null) {
            setTotalDollarCommitment(totalDollarCommitment);
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

        String totalMarketshareCommitment = (String) attributes.get(
                "totalMarketshareCommitment");

        if (totalMarketshareCommitment != null) {
            setTotalMarketshareCommitment(totalMarketshareCommitment);
        }

        Boolean recordLockStatus = (Boolean) attributes.get("recordLockStatus");

        if (recordLockStatus != null) {
            setRecordLockStatus(recordLockStatus);
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

        Date itemStartDate = (Date) attributes.get("itemStartDate");

        if (itemStartDate != null) {
            setItemStartDate(itemStartDate);
        }

        String batchId = (String) attributes.get("batchId");

        if (batchId != null) {
            setBatchId(batchId);
        }

        Integer ifpContractDetailsSid = (Integer) attributes.get(
                "ifpContractDetailsSid");

        if (ifpContractDetailsSid != null) {
            setIfpContractDetailsSid(ifpContractDetailsSid);
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

        String commitmentPeriod = (String) attributes.get("commitmentPeriod");

        if (commitmentPeriod != null) {
            setCommitmentPeriod(commitmentPeriod);
        }
    }

    @Override
    public int getItemStatus() {
        return _itemStatus;
    }

    @Override
    public void setItemStatus(int itemStatus) {
        _itemStatus = itemStatus;

        if (_ifpContractDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _ifpContractDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setItemStatus", int.class);

                method.invoke(_ifpContractDetailsRemoteModel, itemStatus);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getItemMasterSid() {
        return _itemMasterSid;
    }

    @Override
    public void setItemMasterSid(int itemMasterSid) {
        _itemMasterSid = itemMasterSid;

        if (_ifpContractDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _ifpContractDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setItemMasterSid", int.class);

                method.invoke(_ifpContractDetailsRemoteModel, itemMasterSid);
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

        if (_ifpContractDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _ifpContractDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setIfpContractAttachedDate",
                        Date.class);

                method.invoke(_ifpContractDetailsRemoteModel,
                    ifpContractAttachedDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getItemEndDate() {
        return _itemEndDate;
    }

    @Override
    public void setItemEndDate(Date itemEndDate) {
        _itemEndDate = itemEndDate;

        if (_ifpContractDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _ifpContractDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setItemEndDate", Date.class);

                method.invoke(_ifpContractDetailsRemoteModel, itemEndDate);
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

        if (_ifpContractDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _ifpContractDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setTotalVolumeCommitment",
                        String.class);

                method.invoke(_ifpContractDetailsRemoteModel,
                    totalVolumeCommitment);
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

        if (_ifpContractDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _ifpContractDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setTotalDollarCommitment",
                        String.class);

                method.invoke(_ifpContractDetailsRemoteModel,
                    totalDollarCommitment);
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

        if (_ifpContractDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _ifpContractDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setIfpContractAttachedStatus",
                        int.class);

                method.invoke(_ifpContractDetailsRemoteModel,
                    ifpContractAttachedStatus);
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

        if (_ifpContractDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _ifpContractDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedDate", Date.class);

                method.invoke(_ifpContractDetailsRemoteModel, modifiedDate);
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

        if (_ifpContractDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _ifpContractDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setTotalMarketshareCommitment",
                        String.class);

                method.invoke(_ifpContractDetailsRemoteModel,
                    totalMarketshareCommitment);
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

        if (_ifpContractDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _ifpContractDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setRecordLockStatus",
                        boolean.class);

                method.invoke(_ifpContractDetailsRemoteModel, recordLockStatus);
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

        if (_ifpContractDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _ifpContractDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedDate", Date.class);

                method.invoke(_ifpContractDetailsRemoteModel, createdDate);
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

        if (_ifpContractDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _ifpContractDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedBy", int.class);

                method.invoke(_ifpContractDetailsRemoteModel, createdBy);
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

        if (_ifpContractDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _ifpContractDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setSource", String.class);

                method.invoke(_ifpContractDetailsRemoteModel, source);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getItemStartDate() {
        return _itemStartDate;
    }

    @Override
    public void setItemStartDate(Date itemStartDate) {
        _itemStartDate = itemStartDate;

        if (_ifpContractDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _ifpContractDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setItemStartDate", Date.class);

                method.invoke(_ifpContractDetailsRemoteModel, itemStartDate);
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

        if (_ifpContractDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _ifpContractDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setBatchId", String.class);

                method.invoke(_ifpContractDetailsRemoteModel, batchId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getIfpContractDetailsSid() {
        return _ifpContractDetailsSid;
    }

    @Override
    public void setIfpContractDetailsSid(int ifpContractDetailsSid) {
        _ifpContractDetailsSid = ifpContractDetailsSid;

        if (_ifpContractDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _ifpContractDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setIfpContractDetailsSid",
                        int.class);

                method.invoke(_ifpContractDetailsRemoteModel,
                    ifpContractDetailsSid);
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

        if (_ifpContractDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _ifpContractDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedBy", int.class);

                method.invoke(_ifpContractDetailsRemoteModel, modifiedBy);
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

        if (_ifpContractDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _ifpContractDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setInboundStatus", String.class);

                method.invoke(_ifpContractDetailsRemoteModel, inboundStatus);
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

        if (_ifpContractDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _ifpContractDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setIfpContractSid", int.class);

                method.invoke(_ifpContractDetailsRemoteModel, ifpContractSid);
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

        if (_ifpContractDetailsRemoteModel != null) {
            try {
                Class<?> clazz = _ifpContractDetailsRemoteModel.getClass();

                Method method = clazz.getMethod("setCommitmentPeriod",
                        String.class);

                method.invoke(_ifpContractDetailsRemoteModel, commitmentPeriod);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getIfpContractDetailsRemoteModel() {
        return _ifpContractDetailsRemoteModel;
    }

    public void setIfpContractDetailsRemoteModel(
        BaseModel<?> ifpContractDetailsRemoteModel) {
        _ifpContractDetailsRemoteModel = ifpContractDetailsRemoteModel;
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

        Class<?> remoteModelClass = _ifpContractDetailsRemoteModel.getClass();

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

        Object returnValue = method.invoke(_ifpContractDetailsRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            IfpContractDetailsLocalServiceUtil.addIfpContractDetails(this);
        } else {
            IfpContractDetailsLocalServiceUtil.updateIfpContractDetails(this);
        }
    }

    @Override
    public IfpContractDetails toEscapedModel() {
        return (IfpContractDetails) ProxyUtil.newProxyInstance(IfpContractDetails.class.getClassLoader(),
            new Class[] { IfpContractDetails.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        IfpContractDetailsClp clone = new IfpContractDetailsClp();

        clone.setItemStatus(getItemStatus());
        clone.setItemMasterSid(getItemMasterSid());
        clone.setIfpContractAttachedDate(getIfpContractAttachedDate());
        clone.setItemEndDate(getItemEndDate());
        clone.setTotalVolumeCommitment(getTotalVolumeCommitment());
        clone.setTotalDollarCommitment(getTotalDollarCommitment());
        clone.setIfpContractAttachedStatus(getIfpContractAttachedStatus());
        clone.setModifiedDate(getModifiedDate());
        clone.setTotalMarketshareCommitment(getTotalMarketshareCommitment());
        clone.setRecordLockStatus(getRecordLockStatus());
        clone.setCreatedDate(getCreatedDate());
        clone.setCreatedBy(getCreatedBy());
        clone.setSource(getSource());
        clone.setItemStartDate(getItemStartDate());
        clone.setBatchId(getBatchId());
        clone.setIfpContractDetailsSid(getIfpContractDetailsSid());
        clone.setModifiedBy(getModifiedBy());
        clone.setInboundStatus(getInboundStatus());
        clone.setIfpContractSid(getIfpContractSid());
        clone.setCommitmentPeriod(getCommitmentPeriod());

        return clone;
    }

    @Override
    public int compareTo(IfpContractDetails ifpContractDetails) {
        int primaryKey = ifpContractDetails.getPrimaryKey();

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

        if (!(obj instanceof IfpContractDetailsClp)) {
            return false;
        }

        IfpContractDetailsClp ifpContractDetails = (IfpContractDetailsClp) obj;

        int primaryKey = ifpContractDetails.getPrimaryKey();

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
        StringBundler sb = new StringBundler(41);

        sb.append("{itemStatus=");
        sb.append(getItemStatus());
        sb.append(", itemMasterSid=");
        sb.append(getItemMasterSid());
        sb.append(", ifpContractAttachedDate=");
        sb.append(getIfpContractAttachedDate());
        sb.append(", itemEndDate=");
        sb.append(getItemEndDate());
        sb.append(", totalVolumeCommitment=");
        sb.append(getTotalVolumeCommitment());
        sb.append(", totalDollarCommitment=");
        sb.append(getTotalDollarCommitment());
        sb.append(", ifpContractAttachedStatus=");
        sb.append(getIfpContractAttachedStatus());
        sb.append(", modifiedDate=");
        sb.append(getModifiedDate());
        sb.append(", totalMarketshareCommitment=");
        sb.append(getTotalMarketshareCommitment());
        sb.append(", recordLockStatus=");
        sb.append(getRecordLockStatus());
        sb.append(", createdDate=");
        sb.append(getCreatedDate());
        sb.append(", createdBy=");
        sb.append(getCreatedBy());
        sb.append(", source=");
        sb.append(getSource());
        sb.append(", itemStartDate=");
        sb.append(getItemStartDate());
        sb.append(", batchId=");
        sb.append(getBatchId());
        sb.append(", ifpContractDetailsSid=");
        sb.append(getIfpContractDetailsSid());
        sb.append(", modifiedBy=");
        sb.append(getModifiedBy());
        sb.append(", inboundStatus=");
        sb.append(getInboundStatus());
        sb.append(", ifpContractSid=");
        sb.append(getIfpContractSid());
        sb.append(", commitmentPeriod=");
        sb.append(getCommitmentPeriod());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(64);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.IfpContractDetails");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>itemStatus</column-name><column-value><![CDATA[");
        sb.append(getItemStatus());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemMasterSid</column-name><column-value><![CDATA[");
        sb.append(getItemMasterSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>ifpContractAttachedDate</column-name><column-value><![CDATA[");
        sb.append(getIfpContractAttachedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemEndDate</column-name><column-value><![CDATA[");
        sb.append(getItemEndDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>totalVolumeCommitment</column-name><column-value><![CDATA[");
        sb.append(getTotalVolumeCommitment());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>totalDollarCommitment</column-name><column-value><![CDATA[");
        sb.append(getTotalDollarCommitment());
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
            "<column><column-name>totalMarketshareCommitment</column-name><column-value><![CDATA[");
        sb.append(getTotalMarketshareCommitment());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>recordLockStatus</column-name><column-value><![CDATA[");
        sb.append(getRecordLockStatus());
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
            "<column><column-name>itemStartDate</column-name><column-value><![CDATA[");
        sb.append(getItemStartDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>batchId</column-name><column-value><![CDATA[");
        sb.append(getBatchId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>ifpContractDetailsSid</column-name><column-value><![CDATA[");
        sb.append(getIfpContractDetailsSid());
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
            "<column><column-name>commitmentPeriod</column-name><column-value><![CDATA[");
        sb.append(getCommitmentPeriod());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
