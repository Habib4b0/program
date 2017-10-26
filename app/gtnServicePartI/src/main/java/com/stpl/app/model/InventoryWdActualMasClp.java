package com.stpl.app.model;

import com.stpl.app.service.ClpSerializer;
import com.stpl.app.service.InventoryWdActualMasLocalServiceUtil;

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


public class InventoryWdActualMasClp extends BaseModelImpl<InventoryWdActualMas>
    implements InventoryWdActualMas {
    private String _quantityOnOrder;
    private String _week;
    private String _amountOnHand;
    private int _itemMasterSid;
    private int _inventoryWdActualMasSid;
    private String _year;
    private String _itemId;
    private Date _modifiedDate;
    private String _organizationKey;
    private String _source;
    private String _createdBy;
    private Date _createdDate;
    private String _day;
    private String _unitsOnHand;
    private String _amountReceived;
    private String _itemIdentifier;
    private String _modifiedBy;
    private String _inboundStatus;
    private String _month;
    private String _amountWithdrawn;
    private String _quantityReceived;
    private String _unitsWithdrawn;
    private String _country;
    private boolean _recordLockStatus;
    private String _itemIdentifierCodeQualifier;
    private String _batchId;
    private String _amountOnOrder;
    private BaseModel<?> _inventoryWdActualMasRemoteModel;

    public InventoryWdActualMasClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return InventoryWdActualMas.class;
    }

    @Override
    public String getModelClassName() {
        return InventoryWdActualMas.class.getName();
    }

    @Override
    public int getPrimaryKey() {
        return _inventoryWdActualMasSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setInventoryWdActualMasSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _inventoryWdActualMasSid;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Integer) primaryKeyObj).intValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("quantityOnOrder", getQuantityOnOrder());
        attributes.put("week", getWeek());
        attributes.put("amountOnHand", getAmountOnHand());
        attributes.put("itemMasterSid", getItemMasterSid());
        attributes.put("inventoryWdActualMasSid", getInventoryWdActualMasSid());
        attributes.put("year", getYear());
        attributes.put("itemId", getItemId());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("organizationKey", getOrganizationKey());
        attributes.put("source", getSource());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("day", getDay());
        attributes.put("unitsOnHand", getUnitsOnHand());
        attributes.put("amountReceived", getAmountReceived());
        attributes.put("itemIdentifier", getItemIdentifier());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("inboundStatus", getInboundStatus());
        attributes.put("month", getMonth());
        attributes.put("amountWithdrawn", getAmountWithdrawn());
        attributes.put("quantityReceived", getQuantityReceived());
        attributes.put("unitsWithdrawn", getUnitsWithdrawn());
        attributes.put("country", getCountry());
        attributes.put("recordLockStatus", getRecordLockStatus());
        attributes.put("itemIdentifierCodeQualifier",
            getItemIdentifierCodeQualifier());
        attributes.put("batchId", getBatchId());
        attributes.put("amountOnOrder", getAmountOnOrder());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        String quantityOnOrder = (String) attributes.get("quantityOnOrder");

        if (quantityOnOrder != null) {
            setQuantityOnOrder(quantityOnOrder);
        }

        String week = (String) attributes.get("week");

        if (week != null) {
            setWeek(week);
        }

        String amountOnHand = (String) attributes.get("amountOnHand");

        if (amountOnHand != null) {
            setAmountOnHand(amountOnHand);
        }

        Integer itemMasterSid = (Integer) attributes.get("itemMasterSid");

        if (itemMasterSid != null) {
            setItemMasterSid(itemMasterSid);
        }

        Integer inventoryWdActualMasSid = (Integer) attributes.get(
                "inventoryWdActualMasSid");

        if (inventoryWdActualMasSid != null) {
            setInventoryWdActualMasSid(inventoryWdActualMasSid);
        }

        String year = (String) attributes.get("year");

        if (year != null) {
            setYear(year);
        }

        String itemId = (String) attributes.get("itemId");

        if (itemId != null) {
            setItemId(itemId);
        }

        Date modifiedDate = (Date) attributes.get("modifiedDate");

        if (modifiedDate != null) {
            setModifiedDate(modifiedDate);
        }

        String organizationKey = (String) attributes.get("organizationKey");

        if (organizationKey != null) {
            setOrganizationKey(organizationKey);
        }

        String source = (String) attributes.get("source");

        if (source != null) {
            setSource(source);
        }

        String createdBy = (String) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        String day = (String) attributes.get("day");

        if (day != null) {
            setDay(day);
        }

        String unitsOnHand = (String) attributes.get("unitsOnHand");

        if (unitsOnHand != null) {
            setUnitsOnHand(unitsOnHand);
        }

        String amountReceived = (String) attributes.get("amountReceived");

        if (amountReceived != null) {
            setAmountReceived(amountReceived);
        }

        String itemIdentifier = (String) attributes.get("itemIdentifier");

        if (itemIdentifier != null) {
            setItemIdentifier(itemIdentifier);
        }

        String modifiedBy = (String) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        String inboundStatus = (String) attributes.get("inboundStatus");

        if (inboundStatus != null) {
            setInboundStatus(inboundStatus);
        }

        String month = (String) attributes.get("month");

        if (month != null) {
            setMonth(month);
        }

        String amountWithdrawn = (String) attributes.get("amountWithdrawn");

        if (amountWithdrawn != null) {
            setAmountWithdrawn(amountWithdrawn);
        }

        String quantityReceived = (String) attributes.get("quantityReceived");

        if (quantityReceived != null) {
            setQuantityReceived(quantityReceived);
        }

        String unitsWithdrawn = (String) attributes.get("unitsWithdrawn");

        if (unitsWithdrawn != null) {
            setUnitsWithdrawn(unitsWithdrawn);
        }

        String country = (String) attributes.get("country");

        if (country != null) {
            setCountry(country);
        }

        Boolean recordLockStatus = (Boolean) attributes.get("recordLockStatus");

        if (recordLockStatus != null) {
            setRecordLockStatus(recordLockStatus);
        }

        String itemIdentifierCodeQualifier = (String) attributes.get(
                "itemIdentifierCodeQualifier");

        if (itemIdentifierCodeQualifier != null) {
            setItemIdentifierCodeQualifier(itemIdentifierCodeQualifier);
        }

        String batchId = (String) attributes.get("batchId");

        if (batchId != null) {
            setBatchId(batchId);
        }

        String amountOnOrder = (String) attributes.get("amountOnOrder");

        if (amountOnOrder != null) {
            setAmountOnOrder(amountOnOrder);
        }
    }

    @Override
    public String getQuantityOnOrder() {
        return _quantityOnOrder;
    }

    @Override
    public void setQuantityOnOrder(String quantityOnOrder) {
        _quantityOnOrder = quantityOnOrder;

        if (_inventoryWdActualMasRemoteModel != null) {
            try {
                Class<?> clazz = _inventoryWdActualMasRemoteModel.getClass();

                Method method = clazz.getMethod("setQuantityOnOrder",
                        String.class);

                method.invoke(_inventoryWdActualMasRemoteModel, quantityOnOrder);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getWeek() {
        return _week;
    }

    @Override
    public void setWeek(String week) {
        _week = week;

        if (_inventoryWdActualMasRemoteModel != null) {
            try {
                Class<?> clazz = _inventoryWdActualMasRemoteModel.getClass();

                Method method = clazz.getMethod("setWeek", String.class);

                method.invoke(_inventoryWdActualMasRemoteModel, week);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getAmountOnHand() {
        return _amountOnHand;
    }

    @Override
    public void setAmountOnHand(String amountOnHand) {
        _amountOnHand = amountOnHand;

        if (_inventoryWdActualMasRemoteModel != null) {
            try {
                Class<?> clazz = _inventoryWdActualMasRemoteModel.getClass();

                Method method = clazz.getMethod("setAmountOnHand", String.class);

                method.invoke(_inventoryWdActualMasRemoteModel, amountOnHand);
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

        if (_inventoryWdActualMasRemoteModel != null) {
            try {
                Class<?> clazz = _inventoryWdActualMasRemoteModel.getClass();

                Method method = clazz.getMethod("setItemMasterSid", int.class);

                method.invoke(_inventoryWdActualMasRemoteModel, itemMasterSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getInventoryWdActualMasSid() {
        return _inventoryWdActualMasSid;
    }

    @Override
    public void setInventoryWdActualMasSid(int inventoryWdActualMasSid) {
        _inventoryWdActualMasSid = inventoryWdActualMasSid;

        if (_inventoryWdActualMasRemoteModel != null) {
            try {
                Class<?> clazz = _inventoryWdActualMasRemoteModel.getClass();

                Method method = clazz.getMethod("setInventoryWdActualMasSid",
                        int.class);

                method.invoke(_inventoryWdActualMasRemoteModel,
                    inventoryWdActualMasSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getYear() {
        return _year;
    }

    @Override
    public void setYear(String year) {
        _year = year;

        if (_inventoryWdActualMasRemoteModel != null) {
            try {
                Class<?> clazz = _inventoryWdActualMasRemoteModel.getClass();

                Method method = clazz.getMethod("setYear", String.class);

                method.invoke(_inventoryWdActualMasRemoteModel, year);
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

        if (_inventoryWdActualMasRemoteModel != null) {
            try {
                Class<?> clazz = _inventoryWdActualMasRemoteModel.getClass();

                Method method = clazz.getMethod("setItemId", String.class);

                method.invoke(_inventoryWdActualMasRemoteModel, itemId);
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

        if (_inventoryWdActualMasRemoteModel != null) {
            try {
                Class<?> clazz = _inventoryWdActualMasRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedDate", Date.class);

                method.invoke(_inventoryWdActualMasRemoteModel, modifiedDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getOrganizationKey() {
        return _organizationKey;
    }

    @Override
    public void setOrganizationKey(String organizationKey) {
        _organizationKey = organizationKey;

        if (_inventoryWdActualMasRemoteModel != null) {
            try {
                Class<?> clazz = _inventoryWdActualMasRemoteModel.getClass();

                Method method = clazz.getMethod("setOrganizationKey",
                        String.class);

                method.invoke(_inventoryWdActualMasRemoteModel, organizationKey);
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

        if (_inventoryWdActualMasRemoteModel != null) {
            try {
                Class<?> clazz = _inventoryWdActualMasRemoteModel.getClass();

                Method method = clazz.getMethod("setSource", String.class);

                method.invoke(_inventoryWdActualMasRemoteModel, source);
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

        if (_inventoryWdActualMasRemoteModel != null) {
            try {
                Class<?> clazz = _inventoryWdActualMasRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedBy", String.class);

                method.invoke(_inventoryWdActualMasRemoteModel, createdBy);
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

        if (_inventoryWdActualMasRemoteModel != null) {
            try {
                Class<?> clazz = _inventoryWdActualMasRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedDate", Date.class);

                method.invoke(_inventoryWdActualMasRemoteModel, createdDate);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getDay() {
        return _day;
    }

    @Override
    public void setDay(String day) {
        _day = day;

        if (_inventoryWdActualMasRemoteModel != null) {
            try {
                Class<?> clazz = _inventoryWdActualMasRemoteModel.getClass();

                Method method = clazz.getMethod("setDay", String.class);

                method.invoke(_inventoryWdActualMasRemoteModel, day);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getUnitsOnHand() {
        return _unitsOnHand;
    }

    @Override
    public void setUnitsOnHand(String unitsOnHand) {
        _unitsOnHand = unitsOnHand;

        if (_inventoryWdActualMasRemoteModel != null) {
            try {
                Class<?> clazz = _inventoryWdActualMasRemoteModel.getClass();

                Method method = clazz.getMethod("setUnitsOnHand", String.class);

                method.invoke(_inventoryWdActualMasRemoteModel, unitsOnHand);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getAmountReceived() {
        return _amountReceived;
    }

    @Override
    public void setAmountReceived(String amountReceived) {
        _amountReceived = amountReceived;

        if (_inventoryWdActualMasRemoteModel != null) {
            try {
                Class<?> clazz = _inventoryWdActualMasRemoteModel.getClass();

                Method method = clazz.getMethod("setAmountReceived",
                        String.class);

                method.invoke(_inventoryWdActualMasRemoteModel, amountReceived);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getItemIdentifier() {
        return _itemIdentifier;
    }

    @Override
    public void setItemIdentifier(String itemIdentifier) {
        _itemIdentifier = itemIdentifier;

        if (_inventoryWdActualMasRemoteModel != null) {
            try {
                Class<?> clazz = _inventoryWdActualMasRemoteModel.getClass();

                Method method = clazz.getMethod("setItemIdentifier",
                        String.class);

                method.invoke(_inventoryWdActualMasRemoteModel, itemIdentifier);
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

        if (_inventoryWdActualMasRemoteModel != null) {
            try {
                Class<?> clazz = _inventoryWdActualMasRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedBy", String.class);

                method.invoke(_inventoryWdActualMasRemoteModel, modifiedBy);
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

        if (_inventoryWdActualMasRemoteModel != null) {
            try {
                Class<?> clazz = _inventoryWdActualMasRemoteModel.getClass();

                Method method = clazz.getMethod("setInboundStatus", String.class);

                method.invoke(_inventoryWdActualMasRemoteModel, inboundStatus);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getMonth() {
        return _month;
    }

    @Override
    public void setMonth(String month) {
        _month = month;

        if (_inventoryWdActualMasRemoteModel != null) {
            try {
                Class<?> clazz = _inventoryWdActualMasRemoteModel.getClass();

                Method method = clazz.getMethod("setMonth", String.class);

                method.invoke(_inventoryWdActualMasRemoteModel, month);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getAmountWithdrawn() {
        return _amountWithdrawn;
    }

    @Override
    public void setAmountWithdrawn(String amountWithdrawn) {
        _amountWithdrawn = amountWithdrawn;

        if (_inventoryWdActualMasRemoteModel != null) {
            try {
                Class<?> clazz = _inventoryWdActualMasRemoteModel.getClass();

                Method method = clazz.getMethod("setAmountWithdrawn",
                        String.class);

                method.invoke(_inventoryWdActualMasRemoteModel, amountWithdrawn);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getQuantityReceived() {
        return _quantityReceived;
    }

    @Override
    public void setQuantityReceived(String quantityReceived) {
        _quantityReceived = quantityReceived;

        if (_inventoryWdActualMasRemoteModel != null) {
            try {
                Class<?> clazz = _inventoryWdActualMasRemoteModel.getClass();

                Method method = clazz.getMethod("setQuantityReceived",
                        String.class);

                method.invoke(_inventoryWdActualMasRemoteModel, quantityReceived);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getUnitsWithdrawn() {
        return _unitsWithdrawn;
    }

    @Override
    public void setUnitsWithdrawn(String unitsWithdrawn) {
        _unitsWithdrawn = unitsWithdrawn;

        if (_inventoryWdActualMasRemoteModel != null) {
            try {
                Class<?> clazz = _inventoryWdActualMasRemoteModel.getClass();

                Method method = clazz.getMethod("setUnitsWithdrawn",
                        String.class);

                method.invoke(_inventoryWdActualMasRemoteModel, unitsWithdrawn);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getCountry() {
        return _country;
    }

    @Override
    public void setCountry(String country) {
        _country = country;

        if (_inventoryWdActualMasRemoteModel != null) {
            try {
                Class<?> clazz = _inventoryWdActualMasRemoteModel.getClass();

                Method method = clazz.getMethod("setCountry", String.class);

                method.invoke(_inventoryWdActualMasRemoteModel, country);
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

        if (_inventoryWdActualMasRemoteModel != null) {
            try {
                Class<?> clazz = _inventoryWdActualMasRemoteModel.getClass();

                Method method = clazz.getMethod("setRecordLockStatus",
                        boolean.class);

                method.invoke(_inventoryWdActualMasRemoteModel, recordLockStatus);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getItemIdentifierCodeQualifier() {
        return _itemIdentifierCodeQualifier;
    }

    @Override
    public void setItemIdentifierCodeQualifier(
        String itemIdentifierCodeQualifier) {
        _itemIdentifierCodeQualifier = itemIdentifierCodeQualifier;

        if (_inventoryWdActualMasRemoteModel != null) {
            try {
                Class<?> clazz = _inventoryWdActualMasRemoteModel.getClass();

                Method method = clazz.getMethod("setItemIdentifierCodeQualifier",
                        String.class);

                method.invoke(_inventoryWdActualMasRemoteModel,
                    itemIdentifierCodeQualifier);
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

        if (_inventoryWdActualMasRemoteModel != null) {
            try {
                Class<?> clazz = _inventoryWdActualMasRemoteModel.getClass();

                Method method = clazz.getMethod("setBatchId", String.class);

                method.invoke(_inventoryWdActualMasRemoteModel, batchId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getAmountOnOrder() {
        return _amountOnOrder;
    }

    @Override
    public void setAmountOnOrder(String amountOnOrder) {
        _amountOnOrder = amountOnOrder;

        if (_inventoryWdActualMasRemoteModel != null) {
            try {
                Class<?> clazz = _inventoryWdActualMasRemoteModel.getClass();

                Method method = clazz.getMethod("setAmountOnOrder", String.class);

                method.invoke(_inventoryWdActualMasRemoteModel, amountOnOrder);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getInventoryWdActualMasRemoteModel() {
        return _inventoryWdActualMasRemoteModel;
    }

    public void setInventoryWdActualMasRemoteModel(
        BaseModel<?> inventoryWdActualMasRemoteModel) {
        _inventoryWdActualMasRemoteModel = inventoryWdActualMasRemoteModel;
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

        Class<?> remoteModelClass = _inventoryWdActualMasRemoteModel.getClass();

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

        Object returnValue = method.invoke(_inventoryWdActualMasRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            InventoryWdActualMasLocalServiceUtil.addInventoryWdActualMas(this);
        } else {
            InventoryWdActualMasLocalServiceUtil.updateInventoryWdActualMas(this);
        }
    }

    @Override
    public InventoryWdActualMas toEscapedModel() {
        return (InventoryWdActualMas) ProxyUtil.newProxyInstance(InventoryWdActualMas.class.getClassLoader(),
            new Class[] { InventoryWdActualMas.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        InventoryWdActualMasClp clone = new InventoryWdActualMasClp();

        clone.setQuantityOnOrder(getQuantityOnOrder());
        clone.setWeek(getWeek());
        clone.setAmountOnHand(getAmountOnHand());
        clone.setItemMasterSid(getItemMasterSid());
        clone.setInventoryWdActualMasSid(getInventoryWdActualMasSid());
        clone.setYear(getYear());
        clone.setItemId(getItemId());
        clone.setModifiedDate(getModifiedDate());
        clone.setOrganizationKey(getOrganizationKey());
        clone.setSource(getSource());
        clone.setCreatedBy(getCreatedBy());
        clone.setCreatedDate(getCreatedDate());
        clone.setDay(getDay());
        clone.setUnitsOnHand(getUnitsOnHand());
        clone.setAmountReceived(getAmountReceived());
        clone.setItemIdentifier(getItemIdentifier());
        clone.setModifiedBy(getModifiedBy());
        clone.setInboundStatus(getInboundStatus());
        clone.setMonth(getMonth());
        clone.setAmountWithdrawn(getAmountWithdrawn());
        clone.setQuantityReceived(getQuantityReceived());
        clone.setUnitsWithdrawn(getUnitsWithdrawn());
        clone.setCountry(getCountry());
        clone.setRecordLockStatus(getRecordLockStatus());
        clone.setItemIdentifierCodeQualifier(getItemIdentifierCodeQualifier());
        clone.setBatchId(getBatchId());
        clone.setAmountOnOrder(getAmountOnOrder());

        return clone;
    }

    @Override
    public int compareTo(InventoryWdActualMas inventoryWdActualMas) {
        int primaryKey = inventoryWdActualMas.getPrimaryKey();

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

        if (!(obj instanceof InventoryWdActualMasClp)) {
            return false;
        }

        InventoryWdActualMasClp inventoryWdActualMas = (InventoryWdActualMasClp) obj;

        int primaryKey = inventoryWdActualMas.getPrimaryKey();

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
        StringBundler sb = new StringBundler(55);

        sb.append("{quantityOnOrder=");
        sb.append(getQuantityOnOrder());
        sb.append(", week=");
        sb.append(getWeek());
        sb.append(", amountOnHand=");
        sb.append(getAmountOnHand());
        sb.append(", itemMasterSid=");
        sb.append(getItemMasterSid());
        sb.append(", inventoryWdActualMasSid=");
        sb.append(getInventoryWdActualMasSid());
        sb.append(", year=");
        sb.append(getYear());
        sb.append(", itemId=");
        sb.append(getItemId());
        sb.append(", modifiedDate=");
        sb.append(getModifiedDate());
        sb.append(", organizationKey=");
        sb.append(getOrganizationKey());
        sb.append(", source=");
        sb.append(getSource());
        sb.append(", createdBy=");
        sb.append(getCreatedBy());
        sb.append(", createdDate=");
        sb.append(getCreatedDate());
        sb.append(", day=");
        sb.append(getDay());
        sb.append(", unitsOnHand=");
        sb.append(getUnitsOnHand());
        sb.append(", amountReceived=");
        sb.append(getAmountReceived());
        sb.append(", itemIdentifier=");
        sb.append(getItemIdentifier());
        sb.append(", modifiedBy=");
        sb.append(getModifiedBy());
        sb.append(", inboundStatus=");
        sb.append(getInboundStatus());
        sb.append(", month=");
        sb.append(getMonth());
        sb.append(", amountWithdrawn=");
        sb.append(getAmountWithdrawn());
        sb.append(", quantityReceived=");
        sb.append(getQuantityReceived());
        sb.append(", unitsWithdrawn=");
        sb.append(getUnitsWithdrawn());
        sb.append(", country=");
        sb.append(getCountry());
        sb.append(", recordLockStatus=");
        sb.append(getRecordLockStatus());
        sb.append(", itemIdentifierCodeQualifier=");
        sb.append(getItemIdentifierCodeQualifier());
        sb.append(", batchId=");
        sb.append(getBatchId());
        sb.append(", amountOnOrder=");
        sb.append(getAmountOnOrder());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(85);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.InventoryWdActualMas");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>quantityOnOrder</column-name><column-value><![CDATA[");
        sb.append(getQuantityOnOrder());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>week</column-name><column-value><![CDATA[");
        sb.append(getWeek());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>amountOnHand</column-name><column-value><![CDATA[");
        sb.append(getAmountOnHand());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemMasterSid</column-name><column-value><![CDATA[");
        sb.append(getItemMasterSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>inventoryWdActualMasSid</column-name><column-value><![CDATA[");
        sb.append(getInventoryWdActualMasSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>year</column-name><column-value><![CDATA[");
        sb.append(getYear());
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
            "<column><column-name>organizationKey</column-name><column-value><![CDATA[");
        sb.append(getOrganizationKey());
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
            "<column><column-name>createdDate</column-name><column-value><![CDATA[");
        sb.append(getCreatedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>day</column-name><column-value><![CDATA[");
        sb.append(getDay());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>unitsOnHand</column-name><column-value><![CDATA[");
        sb.append(getUnitsOnHand());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>amountReceived</column-name><column-value><![CDATA[");
        sb.append(getAmountReceived());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemIdentifier</column-name><column-value><![CDATA[");
        sb.append(getItemIdentifier());
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
            "<column><column-name>month</column-name><column-value><![CDATA[");
        sb.append(getMonth());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>amountWithdrawn</column-name><column-value><![CDATA[");
        sb.append(getAmountWithdrawn());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>quantityReceived</column-name><column-value><![CDATA[");
        sb.append(getQuantityReceived());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>unitsWithdrawn</column-name><column-value><![CDATA[");
        sb.append(getUnitsWithdrawn());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>country</column-name><column-value><![CDATA[");
        sb.append(getCountry());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>recordLockStatus</column-name><column-value><![CDATA[");
        sb.append(getRecordLockStatus());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemIdentifierCodeQualifier</column-name><column-value><![CDATA[");
        sb.append(getItemIdentifierCodeQualifier());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>batchId</column-name><column-value><![CDATA[");
        sb.append(getBatchId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>amountOnOrder</column-name><column-value><![CDATA[");
        sb.append(getAmountOnOrder());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
