package com.stpl.app.model;

import com.stpl.app.service.ClpSerializer;
import com.stpl.app.service.ItemIdentifierLocalServiceUtil;

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


public class ItemIdentifierClp extends BaseModelImpl<ItemIdentifier>
    implements ItemIdentifier {
    private int _itemIdentifierSid;
    private int _itemMasterSid;
    private Date _endDate;
    private Date _modifiedDate;
    private int _identifierStatus;
    private String _entityCode;
    private String _itemIdentifierValue;
    private boolean _recordLockStatus;
    private int _itemQualifierSid;
    private Date _startDate;
    private Date _createdDate;
    private String _source;
    private int _createdBy;
    private String _batchId;
    private int _modifiedBy;
    private String _inboundStatus;
    private BaseModel<?> _itemIdentifierRemoteModel;

    public ItemIdentifierClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return ItemIdentifier.class;
    }

    @Override
    public String getModelClassName() {
        return ItemIdentifier.class.getName();
    }

    @Override
    public int getPrimaryKey() {
        return _itemIdentifierSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setItemIdentifierSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _itemIdentifierSid;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Integer) primaryKeyObj).intValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("itemIdentifierSid", getItemIdentifierSid());
        attributes.put("itemMasterSid", getItemMasterSid());
        attributes.put("endDate", getEndDate());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("identifierStatus", getIdentifierStatus());
        attributes.put("entityCode", getEntityCode());
        attributes.put("itemIdentifierValue", getItemIdentifierValue());
        attributes.put("recordLockStatus", getRecordLockStatus());
        attributes.put("itemQualifierSid", getItemQualifierSid());
        attributes.put("startDate", getStartDate());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("source", getSource());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("batchId", getBatchId());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("inboundStatus", getInboundStatus());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Integer itemIdentifierSid = (Integer) attributes.get(
                "itemIdentifierSid");

        if (itemIdentifierSid != null) {
            setItemIdentifierSid(itemIdentifierSid);
        }

        Integer itemMasterSid = (Integer) attributes.get("itemMasterSid");

        if (itemMasterSid != null) {
            setItemMasterSid(itemMasterSid);
        }

        Date endDate = (Date) attributes.get("endDate");

        if (endDate != null) {
            setEndDate(endDate);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        Integer identifierStatus = (Integer) attributes.get("identifierStatus");

        if (identifierStatus != null) {
            setIdentifierStatus(identifierStatus);
        }

        String entityCode = (String) attributes.get("entityCode");

        if (entityCode != null) {
            setEntityCode(entityCode);
        }

        String itemIdentifierValue = (String) attributes.get(
                "itemIdentifierValue");

        if (itemIdentifierValue != null) {
            setItemIdentifierValue(itemIdentifierValue);
        }

        Boolean recordLockStatus = (Boolean) attributes.get("recordLockStatus");

        if (recordLockStatus != null) {
            setRecordLockStatus(recordLockStatus);
        }

        Integer itemQualifierSid = (Integer) attributes.get("itemQualifierSid");

        if (itemQualifierSid != null) {
            setItemQualifierSid(itemQualifierSid);
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

        String batchId = (String) attributes.get("batchId");

        if (batchId != null) {
            setBatchId(batchId);
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
    public int getItemIdentifierSid() {
        return _itemIdentifierSid;
    }

    @Override
    public void setItemIdentifierSid(int itemIdentifierSid) {
        _itemIdentifierSid = itemIdentifierSid;

        if (_itemIdentifierRemoteModel != null) {
            try {
                Class<?> clazz = _itemIdentifierRemoteModel.getClass();

                Method method = clazz.getMethod("setItemIdentifierSid",
                        int.class);

                method.invoke(_itemIdentifierRemoteModel, itemIdentifierSid);
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

        if (_itemIdentifierRemoteModel != null) {
            try {
                Class<?> clazz = _itemIdentifierRemoteModel.getClass();

                Method method = clazz.getMethod("setItemMasterSid", int.class);

                method.invoke(_itemIdentifierRemoteModel, itemMasterSid);
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

        if (_itemIdentifierRemoteModel != null) {
            try {
                Class<?> clazz = _itemIdentifierRemoteModel.getClass();

                Method method = clazz.getMethod("setEndDate", Date.class);

                method.invoke(_itemIdentifierRemoteModel, endDate);
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

        if (_itemIdentifierRemoteModel != null) {
            try {
                Class<?> clazz = _itemIdentifierRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedDate", Date.class);

                method.invoke(_itemIdentifierRemoteModel, modifiedDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getIdentifierStatus() {
        return _identifierStatus;
    }

    @Override
    public void setIdentifierStatus(int identifierStatus) {
        _identifierStatus = identifierStatus;

        if (_itemIdentifierRemoteModel != null) {
            try {
                Class<?> clazz = _itemIdentifierRemoteModel.getClass();

                Method method = clazz.getMethod("setIdentifierStatus", int.class);

                method.invoke(_itemIdentifierRemoteModel, identifierStatus);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getEntityCode() {
        return _entityCode;
    }

    @Override
    public void setEntityCode(String entityCode) {
        _entityCode = entityCode;

        if (_itemIdentifierRemoteModel != null) {
            try {
                Class<?> clazz = _itemIdentifierRemoteModel.getClass();

                Method method = clazz.getMethod("setEntityCode", String.class);

                method.invoke(_itemIdentifierRemoteModel, entityCode);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getItemIdentifierValue() {
        return _itemIdentifierValue;
    }

    @Override
    public void setItemIdentifierValue(String itemIdentifierValue) {
        _itemIdentifierValue = itemIdentifierValue;

        if (_itemIdentifierRemoteModel != null) {
            try {
                Class<?> clazz = _itemIdentifierRemoteModel.getClass();

                Method method = clazz.getMethod("setItemIdentifierValue",
                        String.class);

                method.invoke(_itemIdentifierRemoteModel, itemIdentifierValue);
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

        if (_itemIdentifierRemoteModel != null) {
            try {
                Class<?> clazz = _itemIdentifierRemoteModel.getClass();

                Method method = clazz.getMethod("setRecordLockStatus",
                        boolean.class);

                method.invoke(_itemIdentifierRemoteModel, recordLockStatus);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getItemQualifierSid() {
        return _itemQualifierSid;
    }

    @Override
    public void setItemQualifierSid(int itemQualifierSid) {
        _itemQualifierSid = itemQualifierSid;

        if (_itemIdentifierRemoteModel != null) {
            try {
                Class<?> clazz = _itemIdentifierRemoteModel.getClass();

                Method method = clazz.getMethod("setItemQualifierSid", int.class);

                method.invoke(_itemIdentifierRemoteModel, itemQualifierSid);
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

        if (_itemIdentifierRemoteModel != null) {
            try {
                Class<?> clazz = _itemIdentifierRemoteModel.getClass();

                Method method = clazz.getMethod("setStartDate", Date.class);

                method.invoke(_itemIdentifierRemoteModel, startDate);
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

        if (_itemIdentifierRemoteModel != null) {
            try {
                Class<?> clazz = _itemIdentifierRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedDate", Date.class);

                method.invoke(_itemIdentifierRemoteModel, createdDate);
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

        if (_itemIdentifierRemoteModel != null) {
            try {
                Class<?> clazz = _itemIdentifierRemoteModel.getClass();

                Method method = clazz.getMethod("setSource", String.class);

                method.invoke(_itemIdentifierRemoteModel, source);
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

        if (_itemIdentifierRemoteModel != null) {
            try {
                Class<?> clazz = _itemIdentifierRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedBy", int.class);

                method.invoke(_itemIdentifierRemoteModel, createdBy);
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

        if (_itemIdentifierRemoteModel != null) {
            try {
                Class<?> clazz = _itemIdentifierRemoteModel.getClass();

                Method method = clazz.getMethod("setBatchId", String.class);

                method.invoke(_itemIdentifierRemoteModel, batchId);
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

        if (_itemIdentifierRemoteModel != null) {
            try {
                Class<?> clazz = _itemIdentifierRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedBy", int.class);

                method.invoke(_itemIdentifierRemoteModel, modifiedBy);
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

        if (_itemIdentifierRemoteModel != null) {
            try {
                Class<?> clazz = _itemIdentifierRemoteModel.getClass();

                Method method = clazz.getMethod("setInboundStatus", String.class);

                method.invoke(_itemIdentifierRemoteModel, inboundStatus);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getItemIdentifierRemoteModel() {
        return _itemIdentifierRemoteModel;
    }

    public void setItemIdentifierRemoteModel(
        BaseModel<?> itemIdentifierRemoteModel) {
        _itemIdentifierRemoteModel = itemIdentifierRemoteModel;
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

        Class<?> remoteModelClass = _itemIdentifierRemoteModel.getClass();

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

        Object returnValue = method.invoke(_itemIdentifierRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            ItemIdentifierLocalServiceUtil.addItemIdentifier(this);
        } else {
            ItemIdentifierLocalServiceUtil.updateItemIdentifier(this);
        }
    }

    @Override
    public ItemIdentifier toEscapedModel() {
        return (ItemIdentifier) ProxyUtil.newProxyInstance(ItemIdentifier.class.getClassLoader(),
            new Class[] { ItemIdentifier.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        ItemIdentifierClp clone = new ItemIdentifierClp();

        clone.setItemIdentifierSid(getItemIdentifierSid());
        clone.setItemMasterSid(getItemMasterSid());
        clone.setEndDate(getEndDate());
        clone.setModifiedDate(getModifiedDate());
        clone.setIdentifierStatus(getIdentifierStatus());
        clone.setEntityCode(getEntityCode());
        clone.setItemIdentifierValue(getItemIdentifierValue());
        clone.setRecordLockStatus(getRecordLockStatus());
        clone.setItemQualifierSid(getItemQualifierSid());
        clone.setStartDate(getStartDate());
        clone.setCreatedDate(getCreatedDate());
        clone.setSource(getSource());
        clone.setCreatedBy(getCreatedBy());
        clone.setBatchId(getBatchId());
        clone.setModifiedBy(getModifiedBy());
        clone.setInboundStatus(getInboundStatus());

        return clone;
    }

    @Override
    public int compareTo(ItemIdentifier itemIdentifier) {
        int primaryKey = itemIdentifier.getPrimaryKey();

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

        if (!(obj instanceof ItemIdentifierClp)) {
            return false;
        }

        ItemIdentifierClp itemIdentifier = (ItemIdentifierClp) obj;

        int primaryKey = itemIdentifier.getPrimaryKey();

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

        sb.append("{itemIdentifierSid=");
        sb.append(getItemIdentifierSid());
        sb.append(", itemMasterSid=");
        sb.append(getItemMasterSid());
        sb.append(", endDate=");
        sb.append(getEndDate());
        sb.append(", modifiedDate=");
        sb.append(getModifiedDate());
        sb.append(", identifierStatus=");
        sb.append(getIdentifierStatus());
        sb.append(", entityCode=");
        sb.append(getEntityCode());
        sb.append(", itemIdentifierValue=");
        sb.append(getItemIdentifierValue());
        sb.append(", recordLockStatus=");
        sb.append(getRecordLockStatus());
        sb.append(", itemQualifierSid=");
        sb.append(getItemQualifierSid());
        sb.append(", startDate=");
        sb.append(getStartDate());
        sb.append(", createdDate=");
        sb.append(getCreatedDate());
        sb.append(", source=");
        sb.append(getSource());
        sb.append(", createdBy=");
        sb.append(getCreatedBy());
        sb.append(", batchId=");
        sb.append(getBatchId());
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
        sb.append("com.stpl.app.model.ItemIdentifier");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>itemIdentifierSid</column-name><column-value><![CDATA[");
        sb.append(getItemIdentifierSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemMasterSid</column-name><column-value><![CDATA[");
        sb.append(getItemMasterSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>endDate</column-name><column-value><![CDATA[");
        sb.append(getEndDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
        sb.append(getModifiedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>identifierStatus</column-name><column-value><![CDATA[");
        sb.append(getIdentifierStatus());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>entityCode</column-name><column-value><![CDATA[");
        sb.append(getEntityCode());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemIdentifierValue</column-name><column-value><![CDATA[");
        sb.append(getItemIdentifierValue());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>recordLockStatus</column-name><column-value><![CDATA[");
        sb.append(getRecordLockStatus());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemQualifierSid</column-name><column-value><![CDATA[");
        sb.append(getItemQualifierSid());
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
            "<column><column-name>batchId</column-name><column-value><![CDATA[");
        sb.append(getBatchId());
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
