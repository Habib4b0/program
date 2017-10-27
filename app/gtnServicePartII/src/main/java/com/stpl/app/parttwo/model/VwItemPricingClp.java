package com.stpl.app.parttwo.model;

import com.stpl.app.parttwo.service.ClpSerializer;
import com.stpl.app.parttwo.service.VwItemPricingLocalServiceUtil;

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


public class VwItemPricingClp extends BaseModelImpl<VwItemPricing>
    implements VwItemPricing {
    private String _pricingCodeQualifier;
    private String _itemPrice;
    private Date _endDate;
    private String _itemId;
    private Date _modifiedDate;
    private String _entityCode;
    private Date _startDate;
    private Date _createdDate;
    private String _createdBy;
    private String _source;
    private String _batchId;
    private String _addChgDelIndicator;
    private String _itemName;
    private String _itemUom;
    private String _modifiedBy;
    private String _itemNo;
    private int _itemPricingSid;
    private String _pricingCodeStatus;
    private String _pricingCodeQualifierName;
    private int _itemPriceprecision;
    private BaseModel<?> _vwItemPricingRemoteModel;

    public VwItemPricingClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return VwItemPricing.class;
    }

    @Override
    public String getModelClassName() {
        return VwItemPricing.class.getName();
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

        attributes.put("pricingCodeQualifier", getPricingCodeQualifier());
        attributes.put("itemPrice", getItemPrice());
        attributes.put("endDate", getEndDate());
        attributes.put("itemId", getItemId());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("entityCode", getEntityCode());
        attributes.put("startDate", getStartDate());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("source", getSource());
        attributes.put("batchId", getBatchId());
        attributes.put("addChgDelIndicator", getAddChgDelIndicator());
        attributes.put("itemName", getItemName());
        attributes.put("itemUom", getItemUom());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("itemNo", getItemNo());
        attributes.put("itemPricingSid", getItemPricingSid());
        attributes.put("pricingCodeStatus", getPricingCodeStatus());
        attributes.put("pricingCodeQualifierName", getPricingCodeQualifierName());
        attributes.put("itemPriceprecision", getItemPriceprecision());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        String pricingCodeQualifier = (String) attributes.get(
                "pricingCodeQualifier");

        if (pricingCodeQualifier != null) {
            setPricingCodeQualifier(pricingCodeQualifier);
        }

        String itemPrice = (String) attributes.get("itemPrice");

        if (itemPrice != null) {
            setItemPrice(itemPrice);
        }

        Date endDate = (Date) attributes.get("endDate");

        if (endDate != null) {
            setEndDate(endDate);
        }

        String itemId = (String) attributes.get("itemId");

        if (itemId != null) {
            setItemId(itemId);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        String entityCode = (String) attributes.get("entityCode");

        if (entityCode != null) {
            setEntityCode(entityCode);
        }

        Date startDate = (Date) attributes.get("startDate");

        if (startDate != null) {
            setStartDate(startDate);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        String createdBy = (String) attributes.get("createdBy");

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

        String addChgDelIndicator = (String) attributes.get(
                "addChgDelIndicator");

        if (addChgDelIndicator != null) {
            setAddChgDelIndicator(addChgDelIndicator);
        }

        String itemName = (String) attributes.get("itemName");

        if (itemName != null) {
            setItemName(itemName);
        }

        String itemUom = (String) attributes.get("itemUom");

        if (itemUom != null) {
            setItemUom(itemUom);
        }

        String modifiedBy = (String) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        String itemNo = (String) attributes.get("itemNo");

        if (itemNo != null) {
            setItemNo(itemNo);
        }

        Integer itemPricingSid = (Integer) attributes.get("itemPricingSid");

        if (itemPricingSid != null) {
            setItemPricingSid(itemPricingSid);
        }

        String pricingCodeStatus = (String) attributes.get("pricingCodeStatus");

        if (pricingCodeStatus != null) {
            setPricingCodeStatus(pricingCodeStatus);
        }

        String pricingCodeQualifierName = (String) attributes.get(
                "pricingCodeQualifierName");

        if (pricingCodeQualifierName != null) {
            setPricingCodeQualifierName(pricingCodeQualifierName);
        }

        Integer itemPriceprecision = (Integer) attributes.get(
                "itemPriceprecision");

        if (itemPriceprecision != null) {
            setItemPriceprecision(itemPriceprecision);
        }
    }

    @Override
    public String getPricingCodeQualifier() {
        return _pricingCodeQualifier;
    }

    @Override
    public void setPricingCodeQualifier(String pricingCodeQualifier) {
        _pricingCodeQualifier = pricingCodeQualifier;

        if (_vwItemPricingRemoteModel != null) {
            try {
                Class<?> clazz = _vwItemPricingRemoteModel.getClass();

                Method method = clazz.getMethod("setPricingCodeQualifier",
                        String.class);

                method.invoke(_vwItemPricingRemoteModel, pricingCodeQualifier);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getItemPrice() {
        return _itemPrice;
    }

    @Override
    public void setItemPrice(String itemPrice) {
        _itemPrice = itemPrice;

        if (_vwItemPricingRemoteModel != null) {
            try {
                Class<?> clazz = _vwItemPricingRemoteModel.getClass();

                Method method = clazz.getMethod("setItemPrice", String.class);

                method.invoke(_vwItemPricingRemoteModel, itemPrice);
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

        if (_vwItemPricingRemoteModel != null) {
            try {
                Class<?> clazz = _vwItemPricingRemoteModel.getClass();

                Method method = clazz.getMethod("setEndDate", Date.class);

                method.invoke(_vwItemPricingRemoteModel, endDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getItemId() {
        return _itemId;
    }

    @Override
    public void setItemId(String itemId) {
        _itemId = itemId;

        if (_vwItemPricingRemoteModel != null) {
            try {
                Class<?> clazz = _vwItemPricingRemoteModel.getClass();

                Method method = clazz.getMethod("setItemId", String.class);

                method.invoke(_vwItemPricingRemoteModel, itemId);
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

        if (_vwItemPricingRemoteModel != null) {
            try {
                Class<?> clazz = _vwItemPricingRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedDate", Date.class);

                method.invoke(_vwItemPricingRemoteModel, modifiedDate);
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

        if (_vwItemPricingRemoteModel != null) {
            try {
                Class<?> clazz = _vwItemPricingRemoteModel.getClass();

                Method method = clazz.getMethod("setEntityCode", String.class);

                method.invoke(_vwItemPricingRemoteModel, entityCode);
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

        if (_vwItemPricingRemoteModel != null) {
            try {
                Class<?> clazz = _vwItemPricingRemoteModel.getClass();

                Method method = clazz.getMethod("setStartDate", Date.class);

                method.invoke(_vwItemPricingRemoteModel, startDate);
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

        if (_vwItemPricingRemoteModel != null) {
            try {
                Class<?> clazz = _vwItemPricingRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedDate", Date.class);

                method.invoke(_vwItemPricingRemoteModel, createdDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getCreatedBy() {
        return _createdBy;
    }

    @Override
    public void setCreatedBy(String createdBy) {
        _createdBy = createdBy;

        if (_vwItemPricingRemoteModel != null) {
            try {
                Class<?> clazz = _vwItemPricingRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedBy", String.class);

                method.invoke(_vwItemPricingRemoteModel, createdBy);
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

        if (_vwItemPricingRemoteModel != null) {
            try {
                Class<?> clazz = _vwItemPricingRemoteModel.getClass();

                Method method = clazz.getMethod("setSource", String.class);

                method.invoke(_vwItemPricingRemoteModel, source);
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

        if (_vwItemPricingRemoteModel != null) {
            try {
                Class<?> clazz = _vwItemPricingRemoteModel.getClass();

                Method method = clazz.getMethod("setBatchId", String.class);

                method.invoke(_vwItemPricingRemoteModel, batchId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getAddChgDelIndicator() {
        return _addChgDelIndicator;
    }

    @Override
    public void setAddChgDelIndicator(String addChgDelIndicator) {
        _addChgDelIndicator = addChgDelIndicator;

        if (_vwItemPricingRemoteModel != null) {
            try {
                Class<?> clazz = _vwItemPricingRemoteModel.getClass();

                Method method = clazz.getMethod("setAddChgDelIndicator",
                        String.class);

                method.invoke(_vwItemPricingRemoteModel, addChgDelIndicator);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getItemName() {
        return _itemName;
    }

    @Override
    public void setItemName(String itemName) {
        _itemName = itemName;

        if (_vwItemPricingRemoteModel != null) {
            try {
                Class<?> clazz = _vwItemPricingRemoteModel.getClass();

                Method method = clazz.getMethod("setItemName", String.class);

                method.invoke(_vwItemPricingRemoteModel, itemName);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getItemUom() {
        return _itemUom;
    }

    @Override
    public void setItemUom(String itemUom) {
        _itemUom = itemUom;

        if (_vwItemPricingRemoteModel != null) {
            try {
                Class<?> clazz = _vwItemPricingRemoteModel.getClass();

                Method method = clazz.getMethod("setItemUom", String.class);

                method.invoke(_vwItemPricingRemoteModel, itemUom);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getModifiedBy() {
        return _modifiedBy;
    }

    @Override
    public void setModifiedBy(String modifiedBy) {
        _modifiedBy = modifiedBy;

        if (_vwItemPricingRemoteModel != null) {
            try {
                Class<?> clazz = _vwItemPricingRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedBy", String.class);

                method.invoke(_vwItemPricingRemoteModel, modifiedBy);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getItemNo() {
        return _itemNo;
    }

    @Override
    public void setItemNo(String itemNo) {
        _itemNo = itemNo;

        if (_vwItemPricingRemoteModel != null) {
            try {
                Class<?> clazz = _vwItemPricingRemoteModel.getClass();

                Method method = clazz.getMethod("setItemNo", String.class);

                method.invoke(_vwItemPricingRemoteModel, itemNo);
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

        if (_vwItemPricingRemoteModel != null) {
            try {
                Class<?> clazz = _vwItemPricingRemoteModel.getClass();

                Method method = clazz.getMethod("setItemPricingSid", int.class);

                method.invoke(_vwItemPricingRemoteModel, itemPricingSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getPricingCodeStatus() {
        return _pricingCodeStatus;
    }

    @Override
    public void setPricingCodeStatus(String pricingCodeStatus) {
        _pricingCodeStatus = pricingCodeStatus;

        if (_vwItemPricingRemoteModel != null) {
            try {
                Class<?> clazz = _vwItemPricingRemoteModel.getClass();

                Method method = clazz.getMethod("setPricingCodeStatus",
                        String.class);

                method.invoke(_vwItemPricingRemoteModel, pricingCodeStatus);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getPricingCodeQualifierName() {
        return _pricingCodeQualifierName;
    }

    @Override
    public void setPricingCodeQualifierName(String pricingCodeQualifierName) {
        _pricingCodeQualifierName = pricingCodeQualifierName;

        if (_vwItemPricingRemoteModel != null) {
            try {
                Class<?> clazz = _vwItemPricingRemoteModel.getClass();

                Method method = clazz.getMethod("setPricingCodeQualifierName",
                        String.class);

                method.invoke(_vwItemPricingRemoteModel,
                    pricingCodeQualifierName);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getItemPriceprecision() {
        return _itemPriceprecision;
    }

    @Override
    public void setItemPriceprecision(int itemPriceprecision) {
        _itemPriceprecision = itemPriceprecision;

        if (_vwItemPricingRemoteModel != null) {
            try {
                Class<?> clazz = _vwItemPricingRemoteModel.getClass();

                Method method = clazz.getMethod("setItemPriceprecision",
                        int.class);

                method.invoke(_vwItemPricingRemoteModel, itemPriceprecision);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getVwItemPricingRemoteModel() {
        return _vwItemPricingRemoteModel;
    }

    public void setVwItemPricingRemoteModel(
        BaseModel<?> vwItemPricingRemoteModel) {
        _vwItemPricingRemoteModel = vwItemPricingRemoteModel;
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

        Class<?> remoteModelClass = _vwItemPricingRemoteModel.getClass();

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

        Object returnValue = method.invoke(_vwItemPricingRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            VwItemPricingLocalServiceUtil.addVwItemPricing(this);
        } else {
            VwItemPricingLocalServiceUtil.updateVwItemPricing(this);
        }
    }

    @Override
    public VwItemPricing toEscapedModel() {
        return (VwItemPricing) ProxyUtil.newProxyInstance(VwItemPricing.class.getClassLoader(),
            new Class[] { VwItemPricing.class }, new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        VwItemPricingClp clone = new VwItemPricingClp();

        clone.setPricingCodeQualifier(getPricingCodeQualifier());
        clone.setItemPrice(getItemPrice());
        clone.setEndDate(getEndDate());
        clone.setItemId(getItemId());
        clone.setModifiedDate(getModifiedDate());
        clone.setEntityCode(getEntityCode());
        clone.setStartDate(getStartDate());
        clone.setCreatedDate(getCreatedDate());
        clone.setCreatedBy(getCreatedBy());
        clone.setSource(getSource());
        clone.setBatchId(getBatchId());
        clone.setAddChgDelIndicator(getAddChgDelIndicator());
        clone.setItemName(getItemName());
        clone.setItemUom(getItemUom());
        clone.setModifiedBy(getModifiedBy());
        clone.setItemNo(getItemNo());
        clone.setItemPricingSid(getItemPricingSid());
        clone.setPricingCodeStatus(getPricingCodeStatus());
        clone.setPricingCodeQualifierName(getPricingCodeQualifierName());
        clone.setItemPriceprecision(getItemPriceprecision());

        return clone;
    }

    @Override
    public int compareTo(VwItemPricing vwItemPricing) {
        int primaryKey = vwItemPricing.getPrimaryKey();

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

        if (!(obj instanceof VwItemPricingClp)) {
            return false;
        }

        VwItemPricingClp vwItemPricing = (VwItemPricingClp) obj;

        int primaryKey = vwItemPricing.getPrimaryKey();

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

        sb.append("{pricingCodeQualifier=");
        sb.append(getPricingCodeQualifier());
        sb.append(", itemPrice=");
        sb.append(getItemPrice());
        sb.append(", endDate=");
        sb.append(getEndDate());
        sb.append(", itemId=");
        sb.append(getItemId());
        sb.append(", modifiedDate=");
        sb.append(getModifiedDate());
        sb.append(", entityCode=");
        sb.append(getEntityCode());
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
        sb.append(", addChgDelIndicator=");
        sb.append(getAddChgDelIndicator());
        sb.append(", itemName=");
        sb.append(getItemName());
        sb.append(", itemUom=");
        sb.append(getItemUom());
        sb.append(", modifiedBy=");
        sb.append(getModifiedBy());
        sb.append(", itemNo=");
        sb.append(getItemNo());
        sb.append(", itemPricingSid=");
        sb.append(getItemPricingSid());
        sb.append(", pricingCodeStatus=");
        sb.append(getPricingCodeStatus());
        sb.append(", pricingCodeQualifierName=");
        sb.append(getPricingCodeQualifierName());
        sb.append(", itemPriceprecision=");
        sb.append(getItemPriceprecision());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(64);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.parttwo.model.VwItemPricing");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>pricingCodeQualifier</column-name><column-value><![CDATA[");
        sb.append(getPricingCodeQualifier());
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
            "<column><column-name>itemId</column-name><column-value><![CDATA[");
        sb.append(getItemId());
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
            "<column><column-name>addChgDelIndicator</column-name><column-value><![CDATA[");
        sb.append(getAddChgDelIndicator());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemName</column-name><column-value><![CDATA[");
        sb.append(getItemName());
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
            "<column><column-name>itemNo</column-name><column-value><![CDATA[");
        sb.append(getItemNo());
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
            "<column><column-name>pricingCodeQualifierName</column-name><column-value><![CDATA[");
        sb.append(getPricingCodeQualifierName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemPriceprecision</column-name><column-value><![CDATA[");
        sb.append(getItemPriceprecision());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
