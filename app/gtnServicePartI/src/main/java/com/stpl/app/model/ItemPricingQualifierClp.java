package com.stpl.app.model;

import com.stpl.app.service.ClpSerializer;
import com.stpl.app.service.ItemPricingQualifierLocalServiceUtil;

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


public class ItemPricingQualifierClp extends BaseModelImpl<ItemPricingQualifier>
    implements ItemPricingQualifier {
    private int _createdBy;
    private int _itemPricingQualifierSid;
    private String _specificEntityCode;
    private int _modifiedBy;
    private Date _createdDate;
    private String _batchId;
    private Date _modifiedDate;
    private String _effectiveDates;
    private String _notes;
    private boolean _recordLockStatus;
    private String _source;
    private String _pricingQualifier;
    private String _itemPricingQualifierName;
    private String _inboundStatus;
    private BaseModel<?> _itemPricingQualifierRemoteModel;

    public ItemPricingQualifierClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return ItemPricingQualifier.class;
    }

    @Override
    public String getModelClassName() {
        return ItemPricingQualifier.class.getName();
    }

    @Override
    public int getPrimaryKey() {
        return _itemPricingQualifierSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setItemPricingQualifierSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _itemPricingQualifierSid;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Integer) primaryKeyObj).intValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("createdBy", getCreatedBy());
        attributes.put("itemPricingQualifierSid", getItemPricingQualifierSid());
        attributes.put("specificEntityCode", getSpecificEntityCode());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("batchId", getBatchId());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("effectiveDates", getEffectiveDates());
        attributes.put("notes", getNotes());
        attributes.put("recordLockStatus", getRecordLockStatus());
        attributes.put("source", getSource());
        attributes.put("pricingQualifier", getPricingQualifier());
        attributes.put("itemPricingQualifierName", getItemPricingQualifierName());
        attributes.put("inboundStatus", getInboundStatus());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Integer createdBy = (Integer) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        Integer itemPricingQualifierSid = (Integer) attributes.get(
                "itemPricingQualifierSid");

        if (itemPricingQualifierSid != null) {
            setItemPricingQualifierSid(itemPricingQualifierSid);
        }

        String specificEntityCode = (String) attributes.get(
                "specificEntityCode");

        if (specificEntityCode != null) {
            setSpecificEntityCode(specificEntityCode);
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

        Boolean recordLockStatus = (Boolean) attributes.get("recordLockStatus");

        if (recordLockStatus != null) {
            setRecordLockStatus(recordLockStatus);
        }

        String source = (String) attributes.get("source");

        if (source != null) {
            setSource(source);
        }

        String pricingQualifier = (String) attributes.get("pricingQualifier");

        if (pricingQualifier != null) {
            setPricingQualifier(pricingQualifier);
        }

        String itemPricingQualifierName = (String) attributes.get(
                "itemPricingQualifierName");

        if (itemPricingQualifierName != null) {
            setItemPricingQualifierName(itemPricingQualifierName);
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

        if (_itemPricingQualifierRemoteModel != null) {
            try {
                Class<?> clazz = _itemPricingQualifierRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedBy", int.class);

                method.invoke(_itemPricingQualifierRemoteModel, createdBy);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getItemPricingQualifierSid() {
        return _itemPricingQualifierSid;
    }

    @Override
    public void setItemPricingQualifierSid(int itemPricingQualifierSid) {
        _itemPricingQualifierSid = itemPricingQualifierSid;

        if (_itemPricingQualifierRemoteModel != null) {
            try {
                Class<?> clazz = _itemPricingQualifierRemoteModel.getClass();

                Method method = clazz.getMethod("setItemPricingQualifierSid",
                        int.class);

                method.invoke(_itemPricingQualifierRemoteModel,
                    itemPricingQualifierSid);
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

        if (_itemPricingQualifierRemoteModel != null) {
            try {
                Class<?> clazz = _itemPricingQualifierRemoteModel.getClass();

                Method method = clazz.getMethod("setSpecificEntityCode",
                        String.class);

                method.invoke(_itemPricingQualifierRemoteModel,
                    specificEntityCode);
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

        if (_itemPricingQualifierRemoteModel != null) {
            try {
                Class<?> clazz = _itemPricingQualifierRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedBy", int.class);

                method.invoke(_itemPricingQualifierRemoteModel, modifiedBy);
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

        if (_itemPricingQualifierRemoteModel != null) {
            try {
                Class<?> clazz = _itemPricingQualifierRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedDate", Date.class);

                method.invoke(_itemPricingQualifierRemoteModel, createdDate);
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

        if (_itemPricingQualifierRemoteModel != null) {
            try {
                Class<?> clazz = _itemPricingQualifierRemoteModel.getClass();

                Method method = clazz.getMethod("setBatchId", String.class);

                method.invoke(_itemPricingQualifierRemoteModel, batchId);
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

        if (_itemPricingQualifierRemoteModel != null) {
            try {
                Class<?> clazz = _itemPricingQualifierRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedDate", Date.class);

                method.invoke(_itemPricingQualifierRemoteModel, modifiedDate);
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

        if (_itemPricingQualifierRemoteModel != null) {
            try {
                Class<?> clazz = _itemPricingQualifierRemoteModel.getClass();

                Method method = clazz.getMethod("setEffectiveDates",
                        String.class);

                method.invoke(_itemPricingQualifierRemoteModel, effectiveDates);
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

        if (_itemPricingQualifierRemoteModel != null) {
            try {
                Class<?> clazz = _itemPricingQualifierRemoteModel.getClass();

                Method method = clazz.getMethod("setNotes", String.class);

                method.invoke(_itemPricingQualifierRemoteModel, notes);
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

        if (_itemPricingQualifierRemoteModel != null) {
            try {
                Class<?> clazz = _itemPricingQualifierRemoteModel.getClass();

                Method method = clazz.getMethod("setRecordLockStatus",
                        boolean.class);

                method.invoke(_itemPricingQualifierRemoteModel, recordLockStatus);
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

        if (_itemPricingQualifierRemoteModel != null) {
            try {
                Class<?> clazz = _itemPricingQualifierRemoteModel.getClass();

                Method method = clazz.getMethod("setSource", String.class);

                method.invoke(_itemPricingQualifierRemoteModel, source);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getPricingQualifier() {
        return _pricingQualifier;
    }

    @Override
    public void setPricingQualifier(String pricingQualifier) {
        _pricingQualifier = pricingQualifier;

        if (_itemPricingQualifierRemoteModel != null) {
            try {
                Class<?> clazz = _itemPricingQualifierRemoteModel.getClass();

                Method method = clazz.getMethod("setPricingQualifier",
                        String.class);

                method.invoke(_itemPricingQualifierRemoteModel, pricingQualifier);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getItemPricingQualifierName() {
        return _itemPricingQualifierName;
    }

    @Override
    public void setItemPricingQualifierName(String itemPricingQualifierName) {
        _itemPricingQualifierName = itemPricingQualifierName;

        if (_itemPricingQualifierRemoteModel != null) {
            try {
                Class<?> clazz = _itemPricingQualifierRemoteModel.getClass();

                Method method = clazz.getMethod("setItemPricingQualifierName",
                        String.class);

                method.invoke(_itemPricingQualifierRemoteModel,
                    itemPricingQualifierName);
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

        if (_itemPricingQualifierRemoteModel != null) {
            try {
                Class<?> clazz = _itemPricingQualifierRemoteModel.getClass();

                Method method = clazz.getMethod("setInboundStatus", String.class);

                method.invoke(_itemPricingQualifierRemoteModel, inboundStatus);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getItemPricingQualifierRemoteModel() {
        return _itemPricingQualifierRemoteModel;
    }

    public void setItemPricingQualifierRemoteModel(
        BaseModel<?> itemPricingQualifierRemoteModel) {
        _itemPricingQualifierRemoteModel = itemPricingQualifierRemoteModel;
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

        Class<?> remoteModelClass = _itemPricingQualifierRemoteModel.getClass();

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

        Object returnValue = method.invoke(_itemPricingQualifierRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            ItemPricingQualifierLocalServiceUtil.addItemPricingQualifier(this);
        } else {
            ItemPricingQualifierLocalServiceUtil.updateItemPricingQualifier(this);
        }
    }

    @Override
    public ItemPricingQualifier toEscapedModel() {
        return (ItemPricingQualifier) ProxyUtil.newProxyInstance(ItemPricingQualifier.class.getClassLoader(),
            new Class[] { ItemPricingQualifier.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        ItemPricingQualifierClp clone = new ItemPricingQualifierClp();

        clone.setCreatedBy(getCreatedBy());
        clone.setItemPricingQualifierSid(getItemPricingQualifierSid());
        clone.setSpecificEntityCode(getSpecificEntityCode());
        clone.setModifiedBy(getModifiedBy());
        clone.setCreatedDate(getCreatedDate());
        clone.setBatchId(getBatchId());
        clone.setModifiedDate(getModifiedDate());
        clone.setEffectiveDates(getEffectiveDates());
        clone.setNotes(getNotes());
        clone.setRecordLockStatus(getRecordLockStatus());
        clone.setSource(getSource());
        clone.setPricingQualifier(getPricingQualifier());
        clone.setItemPricingQualifierName(getItemPricingQualifierName());
        clone.setInboundStatus(getInboundStatus());

        return clone;
    }

    @Override
    public int compareTo(ItemPricingQualifier itemPricingQualifier) {
        int primaryKey = itemPricingQualifier.getPrimaryKey();

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

        if (!(obj instanceof ItemPricingQualifierClp)) {
            return false;
        }

        ItemPricingQualifierClp itemPricingQualifier = (ItemPricingQualifierClp) obj;

        int primaryKey = itemPricingQualifier.getPrimaryKey();

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
        sb.append(", itemPricingQualifierSid=");
        sb.append(getItemPricingQualifierSid());
        sb.append(", specificEntityCode=");
        sb.append(getSpecificEntityCode());
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
        sb.append(", recordLockStatus=");
        sb.append(getRecordLockStatus());
        sb.append(", source=");
        sb.append(getSource());
        sb.append(", pricingQualifier=");
        sb.append(getPricingQualifier());
        sb.append(", itemPricingQualifierName=");
        sb.append(getItemPricingQualifierName());
        sb.append(", inboundStatus=");
        sb.append(getInboundStatus());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(46);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.ItemPricingQualifier");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>createdBy</column-name><column-value><![CDATA[");
        sb.append(getCreatedBy());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemPricingQualifierSid</column-name><column-value><![CDATA[");
        sb.append(getItemPricingQualifierSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>specificEntityCode</column-name><column-value><![CDATA[");
        sb.append(getSpecificEntityCode());
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
            "<column><column-name>recordLockStatus</column-name><column-value><![CDATA[");
        sb.append(getRecordLockStatus());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>source</column-name><column-value><![CDATA[");
        sb.append(getSource());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>pricingQualifier</column-name><column-value><![CDATA[");
        sb.append(getPricingQualifier());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemPricingQualifierName</column-name><column-value><![CDATA[");
        sb.append(getItemPricingQualifierName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>inboundStatus</column-name><column-value><![CDATA[");
        sb.append(getInboundStatus());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
