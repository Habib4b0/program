package com.stpl.app.model;

import com.stpl.app.service.ClpSerializer;
import com.stpl.app.service.ItemPricingLocalServiceUtil;

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


public class ItemPricingClp extends BaseModelImpl<ItemPricing>
    implements ItemPricing {
    private int _itemMasterSid;
    private int _itemPricingQualifierSid;
    private double _itemPrice;
    private Date _endDate;
    private Date _modifiedDate;
    private String _entityCode;
    private boolean _recordLockStatus;
    private Date _startDate;
    private Date _createdDate;
    private int _createdBy;
    private String _source;
    private String _batchId;
    private int _itemUom;
    private int _modifiedBy;
    private String _inboundStatus;
    private int _itemPricingSid;
    private int _pricingCodeStatus;
    private int _itemPricePrecision;
    private BaseModel<?> _itemPricingRemoteModel;

    public ItemPricingClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return ItemPricing.class;
    }

    @Override
    public String getModelClassName() {
        return ItemPricing.class.getName();
    }

    @Override
    public int getPrimaryKey() {
        return _itemPricingSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setItemPricingSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _itemPricingSid;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Integer) primaryKeyObj).intValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("itemMasterSid", getItemMasterSid());
        attributes.put("itemPricingQualifierSid", getItemPricingQualifierSid());
        attributes.put("itemPrice", getItemPrice());
        attributes.put("endDate", getEndDate());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("entityCode", getEntityCode());
        attributes.put("recordLockStatus", getRecordLockStatus());
        attributes.put("startDate", getStartDate());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("source", getSource());
        attributes.put("batchId", getBatchId());
        attributes.put("itemUom", getItemUom());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("inboundStatus", getInboundStatus());
        attributes.put("itemPricingSid", getItemPricingSid());
        attributes.put("pricingCodeStatus", getPricingCodeStatus());
        attributes.put("itemPricePrecision", getItemPricePrecision());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Integer itemMasterSid = (Integer) attributes.get("itemMasterSid");

        if (itemMasterSid != null) {
            setItemMasterSid(itemMasterSid);
        }

        Integer itemPricingQualifierSid = (Integer) attributes.get(
                "itemPricingQualifierSid");

        if (itemPricingQualifierSid != null) {
            setItemPricingQualifierSid(itemPricingQualifierSid);
        }

        Double itemPrice = (Double) attributes.get("itemPrice");

        if (itemPrice != null) {
            setItemPrice(itemPrice);
        }

        Date endDate = (Date) attributes.get("endDate");

        if (endDate != null) {
            setEndDate(endDate);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        String entityCode = (String) attributes.get("entityCode");

        if (entityCode != null) {
            setEntityCode(entityCode);
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

        Integer createdBy = (Integer) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        String source = (String) attributes.get("source");

        if (source != null) {
            setSource(source);
        }

        String batchId = (String) attributes.get("batchId");

        if (batchId != null) {
            setBatchId(batchId);
        }

        Integer itemUom = (Integer) attributes.get("itemUom");

        if (itemUom != null) {
            setItemUom(itemUom);
        }

        Integer modifiedBy = (Integer) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        String inboundStatus = (String) attributes.get("inboundStatus");

        if (inboundStatus != null) {
            setInboundStatus(inboundStatus);
        }

        Integer itemPricingSid = (Integer) attributes.get("itemPricingSid");

        if (itemPricingSid != null) {
            setItemPricingSid(itemPricingSid);
        }

        Integer pricingCodeStatus = (Integer) attributes.get(
                "pricingCodeStatus");

        if (pricingCodeStatus != null) {
            setPricingCodeStatus(pricingCodeStatus);
        }

        Integer itemPricePrecision = (Integer) attributes.get(
                "itemPricePrecision");

        if (itemPricePrecision != null) {
            setItemPricePrecision(itemPricePrecision);
        }
    }

    @Override
    public int getItemMasterSid() {
        return _itemMasterSid;
    }

    @Override
    public void setItemMasterSid(int itemMasterSid) {
        _itemMasterSid = itemMasterSid;

        if (_itemPricingRemoteModel != null) {
            try {
                Class<?> clazz = _itemPricingRemoteModel.getClass();

                Method method = clazz.getMethod("setItemMasterSid", int.class);

                method.invoke(_itemPricingRemoteModel, itemMasterSid);
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

        if (_itemPricingRemoteModel != null) {
            try {
                Class<?> clazz = _itemPricingRemoteModel.getClass();

                Method method = clazz.getMethod("setItemPricingQualifierSid",
                        int.class);

                method.invoke(_itemPricingRemoteModel, itemPricingQualifierSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getItemPrice() {
        return _itemPrice;
    }

    @Override
    public void setItemPrice(double itemPrice) {
        _itemPrice = itemPrice;

        if (_itemPricingRemoteModel != null) {
            try {
                Class<?> clazz = _itemPricingRemoteModel.getClass();

                Method method = clazz.getMethod("setItemPrice", double.class);

                method.invoke(_itemPricingRemoteModel, itemPrice);
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

        if (_itemPricingRemoteModel != null) {
            try {
                Class<?> clazz = _itemPricingRemoteModel.getClass();

                Method method = clazz.getMethod("setEndDate", Date.class);

                method.invoke(_itemPricingRemoteModel, endDate);
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

        if (_itemPricingRemoteModel != null) {
            try {
                Class<?> clazz = _itemPricingRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedDate", Date.class);

                method.invoke(_itemPricingRemoteModel, modifiedDate);
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

        if (_itemPricingRemoteModel != null) {
            try {
                Class<?> clazz = _itemPricingRemoteModel.getClass();

                Method method = clazz.getMethod("setEntityCode", String.class);

                method.invoke(_itemPricingRemoteModel, entityCode);
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

        if (_itemPricingRemoteModel != null) {
            try {
                Class<?> clazz = _itemPricingRemoteModel.getClass();

                Method method = clazz.getMethod("setRecordLockStatus",
                        boolean.class);

                method.invoke(_itemPricingRemoteModel, recordLockStatus);
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

        if (_itemPricingRemoteModel != null) {
            try {
                Class<?> clazz = _itemPricingRemoteModel.getClass();

                Method method = clazz.getMethod("setStartDate", Date.class);

                method.invoke(_itemPricingRemoteModel, startDate);
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

        if (_itemPricingRemoteModel != null) {
            try {
                Class<?> clazz = _itemPricingRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedDate", Date.class);

                method.invoke(_itemPricingRemoteModel, createdDate);
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

        if (_itemPricingRemoteModel != null) {
            try {
                Class<?> clazz = _itemPricingRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedBy", int.class);

                method.invoke(_itemPricingRemoteModel, createdBy);
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

        if (_itemPricingRemoteModel != null) {
            try {
                Class<?> clazz = _itemPricingRemoteModel.getClass();

                Method method = clazz.getMethod("setSource", String.class);

                method.invoke(_itemPricingRemoteModel, source);
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

        if (_itemPricingRemoteModel != null) {
            try {
                Class<?> clazz = _itemPricingRemoteModel.getClass();

                Method method = clazz.getMethod("setBatchId", String.class);

                method.invoke(_itemPricingRemoteModel, batchId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getItemUom() {
        return _itemUom;
    }

    @Override
    public void setItemUom(int itemUom) {
        _itemUom = itemUom;

        if (_itemPricingRemoteModel != null) {
            try {
                Class<?> clazz = _itemPricingRemoteModel.getClass();

                Method method = clazz.getMethod("setItemUom", int.class);

                method.invoke(_itemPricingRemoteModel, itemUom);
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

        if (_itemPricingRemoteModel != null) {
            try {
                Class<?> clazz = _itemPricingRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedBy", int.class);

                method.invoke(_itemPricingRemoteModel, modifiedBy);
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

        if (_itemPricingRemoteModel != null) {
            try {
                Class<?> clazz = _itemPricingRemoteModel.getClass();

                Method method = clazz.getMethod("setInboundStatus", String.class);

                method.invoke(_itemPricingRemoteModel, inboundStatus);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getItemPricingSid() {
        return _itemPricingSid;
    }

    @Override
    public void setItemPricingSid(int itemPricingSid) {
        _itemPricingSid = itemPricingSid;

        if (_itemPricingRemoteModel != null) {
            try {
                Class<?> clazz = _itemPricingRemoteModel.getClass();

                Method method = clazz.getMethod("setItemPricingSid", int.class);

                method.invoke(_itemPricingRemoteModel, itemPricingSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getPricingCodeStatus() {
        return _pricingCodeStatus;
    }

    @Override
    public void setPricingCodeStatus(int pricingCodeStatus) {
        _pricingCodeStatus = pricingCodeStatus;

        if (_itemPricingRemoteModel != null) {
            try {
                Class<?> clazz = _itemPricingRemoteModel.getClass();

                Method method = clazz.getMethod("setPricingCodeStatus",
                        int.class);

                method.invoke(_itemPricingRemoteModel, pricingCodeStatus);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getItemPricePrecision() {
        return _itemPricePrecision;
    }

    @Override
    public void setItemPricePrecision(int itemPricePrecision) {
        _itemPricePrecision = itemPricePrecision;

        if (_itemPricingRemoteModel != null) {
            try {
                Class<?> clazz = _itemPricingRemoteModel.getClass();

                Method method = clazz.getMethod("setItemPricePrecision",
                        int.class);

                method.invoke(_itemPricingRemoteModel, itemPricePrecision);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getItemPricingRemoteModel() {
        return _itemPricingRemoteModel;
    }

    public void setItemPricingRemoteModel(BaseModel<?> itemPricingRemoteModel) {
        _itemPricingRemoteModel = itemPricingRemoteModel;
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

        Class<?> remoteModelClass = _itemPricingRemoteModel.getClass();

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

        Object returnValue = method.invoke(_itemPricingRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            ItemPricingLocalServiceUtil.addItemPricing(this);
        } else {
            ItemPricingLocalServiceUtil.updateItemPricing(this);
        }
    }

    @Override
    public ItemPricing toEscapedModel() {
        return (ItemPricing) ProxyUtil.newProxyInstance(ItemPricing.class.getClassLoader(),
            new Class[] { ItemPricing.class }, new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        ItemPricingClp clone = new ItemPricingClp();

        clone.setItemMasterSid(getItemMasterSid());
        clone.setItemPricingQualifierSid(getItemPricingQualifierSid());
        clone.setItemPrice(getItemPrice());
        clone.setEndDate(getEndDate());
        clone.setModifiedDate(getModifiedDate());
        clone.setEntityCode(getEntityCode());
        clone.setRecordLockStatus(getRecordLockStatus());
        clone.setStartDate(getStartDate());
        clone.setCreatedDate(getCreatedDate());
        clone.setCreatedBy(getCreatedBy());
        clone.setSource(getSource());
        clone.setBatchId(getBatchId());
        clone.setItemUom(getItemUom());
        clone.setModifiedBy(getModifiedBy());
        clone.setInboundStatus(getInboundStatus());
        clone.setItemPricingSid(getItemPricingSid());
        clone.setPricingCodeStatus(getPricingCodeStatus());
        clone.setItemPricePrecision(getItemPricePrecision());

        return clone;
    }

    @Override
    public int compareTo(ItemPricing itemPricing) {
        int primaryKey = itemPricing.getPrimaryKey();

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

        if (!(obj instanceof ItemPricingClp)) {
            return false;
        }

        ItemPricingClp itemPricing = (ItemPricingClp) obj;

        int primaryKey = itemPricing.getPrimaryKey();

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
        StringBundler sb = new StringBundler(37);

        sb.append("{itemMasterSid=");
        sb.append(getItemMasterSid());
        sb.append(", itemPricingQualifierSid=");
        sb.append(getItemPricingQualifierSid());
        sb.append(", itemPrice=");
        sb.append(getItemPrice());
        sb.append(", endDate=");
        sb.append(getEndDate());
        sb.append(", modifiedDate=");
        sb.append(getModifiedDate());
        sb.append(", entityCode=");
        sb.append(getEntityCode());
        sb.append(", recordLockStatus=");
        sb.append(getRecordLockStatus());
        sb.append(", startDate=");
        sb.append(getStartDate());
        sb.append(", createdDate=");
        sb.append(getCreatedDate());
        sb.append(", createdBy=");
        sb.append(getCreatedBy());
        sb.append(", source=");
        sb.append(getSource());
        sb.append(", batchId=");
        sb.append(getBatchId());
        sb.append(", itemUom=");
        sb.append(getItemUom());
        sb.append(", modifiedBy=");
        sb.append(getModifiedBy());
        sb.append(", inboundStatus=");
        sb.append(getInboundStatus());
        sb.append(", itemPricingSid=");
        sb.append(getItemPricingSid());
        sb.append(", pricingCodeStatus=");
        sb.append(getPricingCodeStatus());
        sb.append(", itemPricePrecision=");
        sb.append(getItemPricePrecision());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(58);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.ItemPricing");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>itemMasterSid</column-name><column-value><![CDATA[");
        sb.append(getItemMasterSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemPricingQualifierSid</column-name><column-value><![CDATA[");
        sb.append(getItemPricingQualifierSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemPrice</column-name><column-value><![CDATA[");
        sb.append(getItemPrice());
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
            "<column><column-name>entityCode</column-name><column-value><![CDATA[");
        sb.append(getEntityCode());
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
            "<column><column-name>createdBy</column-name><column-value><![CDATA[");
        sb.append(getCreatedBy());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>source</column-name><column-value><![CDATA[");
        sb.append(getSource());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>batchId</column-name><column-value><![CDATA[");
        sb.append(getBatchId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemUom</column-name><column-value><![CDATA[");
        sb.append(getItemUom());
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
            "<column><column-name>itemPricingSid</column-name><column-value><![CDATA[");
        sb.append(getItemPricingSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>pricingCodeStatus</column-name><column-value><![CDATA[");
        sb.append(getPricingCodeStatus());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemPricePrecision</column-name><column-value><![CDATA[");
        sb.append(getItemPricePrecision());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
