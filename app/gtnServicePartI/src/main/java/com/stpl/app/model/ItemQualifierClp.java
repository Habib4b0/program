package com.stpl.app.model;

import com.stpl.app.service.ClpSerializer;
import com.stpl.app.service.ItemQualifierLocalServiceUtil;

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


public class ItemQualifierClp extends BaseModelImpl<ItemQualifier>
    implements ItemQualifier {
    private int _createdBy;
    private int _itemQualifierSid;
    private String _specificEntityCode;
    private String _itemQualifierName;
    private int _modifiedBy;
    private Date _createdDate;
    private String _batchId;
    private Date _modifiedDate;
    private String _effectiveDates;
    private String _notes;
    private String _itemQualifierValue;
    private boolean _recordLockStatus;
    private String _source;
    private String _inboundStatus;
    private BaseModel<?> _itemQualifierRemoteModel;

    public ItemQualifierClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return ItemQualifier.class;
    }

    @Override
    public String getModelClassName() {
        return ItemQualifier.class.getName();
    }

    @Override
    public int getPrimaryKey() {
        return _itemQualifierSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setItemQualifierSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _itemQualifierSid;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Integer) primaryKeyObj).intValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("createdBy", getCreatedBy());
        attributes.put("itemQualifierSid", getItemQualifierSid());
        attributes.put("specificEntityCode", getSpecificEntityCode());
        attributes.put("itemQualifierName", getItemQualifierName());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("batchId", getBatchId());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("effectiveDates", getEffectiveDates());
        attributes.put("notes", getNotes());
        attributes.put("itemQualifierValue", getItemQualifierValue());
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

        Integer itemQualifierSid = (Integer) attributes.get("itemQualifierSid");

        if (itemQualifierSid != null) {
            setItemQualifierSid(itemQualifierSid);
        }

        String specificEntityCode = (String) attributes.get(
                "specificEntityCode");

        if (specificEntityCode != null) {
            setSpecificEntityCode(specificEntityCode);
        }

        String itemQualifierName = (String) attributes.get("itemQualifierName");

        if (itemQualifierName != null) {
            setItemQualifierName(itemQualifierName);
        }

        Integer modifiedBy = (Integer) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        String batchId = (String) attributes.get("batchId");

        if (batchId != null) {
            setBatchId(batchId);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        String effectiveDates = (String) attributes.get("effectiveDates");

        if (effectiveDates != null) {
            setEffectiveDates(effectiveDates);
        }

        String notes = (String) attributes.get("notes");

        if (notes != null) {
            setNotes(notes);
        }

        String itemQualifierValue = (String) attributes.get(
                "itemQualifierValue");

        if (itemQualifierValue != null) {
            setItemQualifierValue(itemQualifierValue);
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

        if (_itemQualifierRemoteModel != null) {
            try {
                Class<?> clazz = _itemQualifierRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedBy", int.class);

                method.invoke(_itemQualifierRemoteModel, createdBy);
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

        if (_itemQualifierRemoteModel != null) {
            try {
                Class<?> clazz = _itemQualifierRemoteModel.getClass();

                Method method = clazz.getMethod("setItemQualifierSid", int.class);

                method.invoke(_itemQualifierRemoteModel, itemQualifierSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getSpecificEntityCode() {
        return _specificEntityCode;
    }

    @Override
    public void setSpecificEntityCode(String specificEntityCode) {
        _specificEntityCode = specificEntityCode;

        if (_itemQualifierRemoteModel != null) {
            try {
                Class<?> clazz = _itemQualifierRemoteModel.getClass();

                Method method = clazz.getMethod("setSpecificEntityCode",
                        String.class);

                method.invoke(_itemQualifierRemoteModel, specificEntityCode);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getItemQualifierName() {
        return _itemQualifierName;
    }

    @Override
    public void setItemQualifierName(String itemQualifierName) {
        _itemQualifierName = itemQualifierName;

        if (_itemQualifierRemoteModel != null) {
            try {
                Class<?> clazz = _itemQualifierRemoteModel.getClass();

                Method method = clazz.getMethod("setItemQualifierName",
                        String.class);

                method.invoke(_itemQualifierRemoteModel, itemQualifierName);
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

        if (_itemQualifierRemoteModel != null) {
            try {
                Class<?> clazz = _itemQualifierRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedBy", int.class);

                method.invoke(_itemQualifierRemoteModel, modifiedBy);
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

        if (_itemQualifierRemoteModel != null) {
            try {
                Class<?> clazz = _itemQualifierRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedDate", Date.class);

                method.invoke(_itemQualifierRemoteModel, createdDate);
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

        if (_itemQualifierRemoteModel != null) {
            try {
                Class<?> clazz = _itemQualifierRemoteModel.getClass();

                Method method = clazz.getMethod("setBatchId", String.class);

                method.invoke(_itemQualifierRemoteModel, batchId);
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

        if (_itemQualifierRemoteModel != null) {
            try {
                Class<?> clazz = _itemQualifierRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedDate", Date.class);

                method.invoke(_itemQualifierRemoteModel, modifiedDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getEffectiveDates() {
        return _effectiveDates;
    }

    @Override
    public void setEffectiveDates(String effectiveDates) {
        _effectiveDates = effectiveDates;

        if (_itemQualifierRemoteModel != null) {
            try {
                Class<?> clazz = _itemQualifierRemoteModel.getClass();

                Method method = clazz.getMethod("setEffectiveDates",
                        String.class);

                method.invoke(_itemQualifierRemoteModel, effectiveDates);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getNotes() {
        return _notes;
    }

    @Override
    public void setNotes(String notes) {
        _notes = notes;

        if (_itemQualifierRemoteModel != null) {
            try {
                Class<?> clazz = _itemQualifierRemoteModel.getClass();

                Method method = clazz.getMethod("setNotes", String.class);

                method.invoke(_itemQualifierRemoteModel, notes);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getItemQualifierValue() {
        return _itemQualifierValue;
    }

    @Override
    public void setItemQualifierValue(String itemQualifierValue) {
        _itemQualifierValue = itemQualifierValue;

        if (_itemQualifierRemoteModel != null) {
            try {
                Class<?> clazz = _itemQualifierRemoteModel.getClass();

                Method method = clazz.getMethod("setItemQualifierValue",
                        String.class);

                method.invoke(_itemQualifierRemoteModel, itemQualifierValue);
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

        if (_itemQualifierRemoteModel != null) {
            try {
                Class<?> clazz = _itemQualifierRemoteModel.getClass();

                Method method = clazz.getMethod("setRecordLockStatus",
                        boolean.class);

                method.invoke(_itemQualifierRemoteModel, recordLockStatus);
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

        if (_itemQualifierRemoteModel != null) {
            try {
                Class<?> clazz = _itemQualifierRemoteModel.getClass();

                Method method = clazz.getMethod("setSource", String.class);

                method.invoke(_itemQualifierRemoteModel, source);
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

        if (_itemQualifierRemoteModel != null) {
            try {
                Class<?> clazz = _itemQualifierRemoteModel.getClass();

                Method method = clazz.getMethod("setInboundStatus", String.class);

                method.invoke(_itemQualifierRemoteModel, inboundStatus);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getItemQualifierRemoteModel() {
        return _itemQualifierRemoteModel;
    }

    public void setItemQualifierRemoteModel(
        BaseModel<?> itemQualifierRemoteModel) {
        _itemQualifierRemoteModel = itemQualifierRemoteModel;
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

        Class<?> remoteModelClass = _itemQualifierRemoteModel.getClass();

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

        Object returnValue = method.invoke(_itemQualifierRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            ItemQualifierLocalServiceUtil.addItemQualifier(this);
        } else {
            ItemQualifierLocalServiceUtil.updateItemQualifier(this);
        }
    }

    @Override
    public ItemQualifier toEscapedModel() {
        return (ItemQualifier) ProxyUtil.newProxyInstance(ItemQualifier.class.getClassLoader(),
            new Class[] { ItemQualifier.class }, new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        ItemQualifierClp clone = new ItemQualifierClp();

        clone.setCreatedBy(getCreatedBy());
        clone.setItemQualifierSid(getItemQualifierSid());
        clone.setSpecificEntityCode(getSpecificEntityCode());
        clone.setItemQualifierName(getItemQualifierName());
        clone.setModifiedBy(getModifiedBy());
        clone.setCreatedDate(getCreatedDate());
        clone.setBatchId(getBatchId());
        clone.setModifiedDate(getModifiedDate());
        clone.setEffectiveDates(getEffectiveDates());
        clone.setNotes(getNotes());
        clone.setItemQualifierValue(getItemQualifierValue());
        clone.setRecordLockStatus(getRecordLockStatus());
        clone.setSource(getSource());
        clone.setInboundStatus(getInboundStatus());

        return clone;
    }

    @Override
    public int compareTo(ItemQualifier itemQualifier) {
        int primaryKey = itemQualifier.getPrimaryKey();

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

        if (!(obj instanceof ItemQualifierClp)) {
            return false;
        }

        ItemQualifierClp itemQualifier = (ItemQualifierClp) obj;

        int primaryKey = itemQualifier.getPrimaryKey();

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
        StringBundler sb = new StringBundler(29);

        sb.append("{createdBy=");
        sb.append(getCreatedBy());
        sb.append(", itemQualifierSid=");
        sb.append(getItemQualifierSid());
        sb.append(", specificEntityCode=");
        sb.append(getSpecificEntityCode());
        sb.append(", itemQualifierName=");
        sb.append(getItemQualifierName());
        sb.append(", modifiedBy=");
        sb.append(getModifiedBy());
        sb.append(", createdDate=");
        sb.append(getCreatedDate());
        sb.append(", batchId=");
        sb.append(getBatchId());
        sb.append(", modifiedDate=");
        sb.append(getModifiedDate());
        sb.append(", effectiveDates=");
        sb.append(getEffectiveDates());
        sb.append(", notes=");
        sb.append(getNotes());
        sb.append(", itemQualifierValue=");
        sb.append(getItemQualifierValue());
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
        StringBundler sb = new StringBundler(46);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.ItemQualifier");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>createdBy</column-name><column-value><![CDATA[");
        sb.append(getCreatedBy());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemQualifierSid</column-name><column-value><![CDATA[");
        sb.append(getItemQualifierSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>specificEntityCode</column-name><column-value><![CDATA[");
        sb.append(getSpecificEntityCode());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemQualifierName</column-name><column-value><![CDATA[");
        sb.append(getItemQualifierName());
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
            "<column><column-name>batchId</column-name><column-value><![CDATA[");
        sb.append(getBatchId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
        sb.append(getModifiedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>effectiveDates</column-name><column-value><![CDATA[");
        sb.append(getEffectiveDates());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>notes</column-name><column-value><![CDATA[");
        sb.append(getNotes());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemQualifierValue</column-name><column-value><![CDATA[");
        sb.append(getItemQualifierValue());
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
