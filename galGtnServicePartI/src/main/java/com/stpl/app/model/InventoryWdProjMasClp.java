package com.stpl.app.model;

import com.stpl.app.service.ClpSerializer;
import com.stpl.app.service.InventoryWdProjMasLocalServiceUtil;

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


public class InventoryWdProjMasClp extends BaseModelImpl<InventoryWdProjMas>
    implements InventoryWdProjMas {
    private String _week;
    private int _itemMasterSid;
    private String _unitsWithdrawn;
    private String _country;
    private String _year;
    private String _itemId;
    private Date _modifiedDate;
    private String _organizationKey;
    private boolean _recordLockStatus;
    private String _itemIdentifierCodeQualifier;
    private String _source;
    private Date _createdDate;
    private String _createdBy;
    private int _inventoryWdProjMasSid;
    private String _day;
    private String _forecastVer;
    private String _batchId;
    private String _itemIdentifier;
    private String _inboundStatus;
    private String _modifiedBy;
    private String _month;
    private String _forecastName;
    private String _amountWithdrawn;
    private BaseModel<?> _inventoryWdProjMasRemoteModel;

    public InventoryWdProjMasClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return InventoryWdProjMas.class;
    }

    @Override
    public String getModelClassName() {
        return InventoryWdProjMas.class.getName();
    }

    @Override
    public int getPrimaryKey() {
        return _inventoryWdProjMasSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setInventoryWdProjMasSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _inventoryWdProjMasSid;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Integer) primaryKeyObj).intValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("week", getWeek());
        attributes.put("itemMasterSid", getItemMasterSid());
        attributes.put("unitsWithdrawn", getUnitsWithdrawn());
        attributes.put("country", getCountry());
        attributes.put("year", getYear());
        attributes.put("itemId", getItemId());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("organizationKey", getOrganizationKey());
        attributes.put("recordLockStatus", getRecordLockStatus());
        attributes.put("itemIdentifierCodeQualifier",
            getItemIdentifierCodeQualifier());
        attributes.put("source", getSource());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("inventoryWdProjMasSid", getInventoryWdProjMasSid());
        attributes.put("day", getDay());
        attributes.put("forecastVer", getForecastVer());
        attributes.put("batchId", getBatchId());
        attributes.put("itemIdentifier", getItemIdentifier());
        attributes.put("inboundStatus", getInboundStatus());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("month", getMonth());
        attributes.put("forecastName", getForecastName());
        attributes.put("amountWithdrawn", getAmountWithdrawn());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        String week = (String) attributes.get("week");

        if (week != null) {
            setWeek(week);
        }

        Integer itemMasterSid = (Integer) attributes.get("itemMasterSid");

        if (itemMasterSid != null) {
            setItemMasterSid(itemMasterSid);
        }

        String unitsWithdrawn = (String) attributes.get("unitsWithdrawn");

        if (unitsWithdrawn != null) {
            setUnitsWithdrawn(unitsWithdrawn);
        }

        String country = (String) attributes.get("country");

        if (country != null) {
            setCountry(country);
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

        Boolean recordLockStatus = (Boolean) attributes.get("recordLockStatus");

        if (recordLockStatus != null) {
            setRecordLockStatus(recordLockStatus);
        }

        String itemIdentifierCodeQualifier = (String) attributes.get(
                "itemIdentifierCodeQualifier");

        if (itemIdentifierCodeQualifier != null) {
            setItemIdentifierCodeQualifier(itemIdentifierCodeQualifier);
        }

        String source = (String) attributes.get("source");

        if (source != null) {
            setSource(source);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        String createdBy = (String) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        Integer inventoryWdProjMasSid = (Integer) attributes.get(
                "inventoryWdProjMasSid");

        if (inventoryWdProjMasSid != null) {
            setInventoryWdProjMasSid(inventoryWdProjMasSid);
        }

        String day = (String) attributes.get("day");

        if (day != null) {
            setDay(day);
        }

        String forecastVer = (String) attributes.get("forecastVer");

        if (forecastVer != null) {
            setForecastVer(forecastVer);
        }

        String batchId = (String) attributes.get("batchId");

        if (batchId != null) {
            setBatchId(batchId);
        }

        String itemIdentifier = (String) attributes.get("itemIdentifier");

        if (itemIdentifier != null) {
            setItemIdentifier(itemIdentifier);
        }

        String inboundStatus = (String) attributes.get("inboundStatus");

        if (inboundStatus != null) {
            setInboundStatus(inboundStatus);
        }

        String modifiedBy = (String) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        String month = (String) attributes.get("month");

        if (month != null) {
            setMonth(month);
        }

        String forecastName = (String) attributes.get("forecastName");

        if (forecastName != null) {
            setForecastName(forecastName);
        }

        String amountWithdrawn = (String) attributes.get("amountWithdrawn");

        if (amountWithdrawn != null) {
            setAmountWithdrawn(amountWithdrawn);
        }
    }

    @Override
    public String getWeek() {
        return _week;
    }

    @Override
    public void setWeek(String week) {
        _week = week;

        if (_inventoryWdProjMasRemoteModel != null) {
            try {
                Class<?> clazz = _inventoryWdProjMasRemoteModel.getClass();

                Method method = clazz.getMethod("setWeek", String.class);

                method.invoke(_inventoryWdProjMasRemoteModel, week);
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

        if (_inventoryWdProjMasRemoteModel != null) {
            try {
                Class<?> clazz = _inventoryWdProjMasRemoteModel.getClass();

                Method method = clazz.getMethod("setItemMasterSid", int.class);

                method.invoke(_inventoryWdProjMasRemoteModel, itemMasterSid);
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

        if (_inventoryWdProjMasRemoteModel != null) {
            try {
                Class<?> clazz = _inventoryWdProjMasRemoteModel.getClass();

                Method method = clazz.getMethod("setUnitsWithdrawn",
                        String.class);

                method.invoke(_inventoryWdProjMasRemoteModel, unitsWithdrawn);
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

        if (_inventoryWdProjMasRemoteModel != null) {
            try {
                Class<?> clazz = _inventoryWdProjMasRemoteModel.getClass();

                Method method = clazz.getMethod("setCountry", String.class);

                method.invoke(_inventoryWdProjMasRemoteModel, country);
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

        if (_inventoryWdProjMasRemoteModel != null) {
            try {
                Class<?> clazz = _inventoryWdProjMasRemoteModel.getClass();

                Method method = clazz.getMethod("setYear", String.class);

                method.invoke(_inventoryWdProjMasRemoteModel, year);
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

        if (_inventoryWdProjMasRemoteModel != null) {
            try {
                Class<?> clazz = _inventoryWdProjMasRemoteModel.getClass();

                Method method = clazz.getMethod("setItemId", String.class);

                method.invoke(_inventoryWdProjMasRemoteModel, itemId);
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

        if (_inventoryWdProjMasRemoteModel != null) {
            try {
                Class<?> clazz = _inventoryWdProjMasRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedDate", Date.class);

                method.invoke(_inventoryWdProjMasRemoteModel, modifiedDate);
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

        if (_inventoryWdProjMasRemoteModel != null) {
            try {
                Class<?> clazz = _inventoryWdProjMasRemoteModel.getClass();

                Method method = clazz.getMethod("setOrganizationKey",
                        String.class);

                method.invoke(_inventoryWdProjMasRemoteModel, organizationKey);
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

        if (_inventoryWdProjMasRemoteModel != null) {
            try {
                Class<?> clazz = _inventoryWdProjMasRemoteModel.getClass();

                Method method = clazz.getMethod("setRecordLockStatus",
                        boolean.class);

                method.invoke(_inventoryWdProjMasRemoteModel, recordLockStatus);
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

        if (_inventoryWdProjMasRemoteModel != null) {
            try {
                Class<?> clazz = _inventoryWdProjMasRemoteModel.getClass();

                Method method = clazz.getMethod("setItemIdentifierCodeQualifier",
                        String.class);

                method.invoke(_inventoryWdProjMasRemoteModel,
                    itemIdentifierCodeQualifier);
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

        if (_inventoryWdProjMasRemoteModel != null) {
            try {
                Class<?> clazz = _inventoryWdProjMasRemoteModel.getClass();

                Method method = clazz.getMethod("setSource", String.class);

                method.invoke(_inventoryWdProjMasRemoteModel, source);
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

        if (_inventoryWdProjMasRemoteModel != null) {
            try {
                Class<?> clazz = _inventoryWdProjMasRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedDate", Date.class);

                method.invoke(_inventoryWdProjMasRemoteModel, createdDate);
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

        if (_inventoryWdProjMasRemoteModel != null) {
            try {
                Class<?> clazz = _inventoryWdProjMasRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedBy", String.class);

                method.invoke(_inventoryWdProjMasRemoteModel, createdBy);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getInventoryWdProjMasSid() {
        return _inventoryWdProjMasSid;
    }

    @Override
    public void setInventoryWdProjMasSid(int inventoryWdProjMasSid) {
        _inventoryWdProjMasSid = inventoryWdProjMasSid;

        if (_inventoryWdProjMasRemoteModel != null) {
            try {
                Class<?> clazz = _inventoryWdProjMasRemoteModel.getClass();

                Method method = clazz.getMethod("setInventoryWdProjMasSid",
                        int.class);

                method.invoke(_inventoryWdProjMasRemoteModel,
                    inventoryWdProjMasSid);
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

        if (_inventoryWdProjMasRemoteModel != null) {
            try {
                Class<?> clazz = _inventoryWdProjMasRemoteModel.getClass();

                Method method = clazz.getMethod("setDay", String.class);

                method.invoke(_inventoryWdProjMasRemoteModel, day);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getForecastVer() {
        return _forecastVer;
    }

    @Override
    public void setForecastVer(String forecastVer) {
        _forecastVer = forecastVer;

        if (_inventoryWdProjMasRemoteModel != null) {
            try {
                Class<?> clazz = _inventoryWdProjMasRemoteModel.getClass();

                Method method = clazz.getMethod("setForecastVer", String.class);

                method.invoke(_inventoryWdProjMasRemoteModel, forecastVer);
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

        if (_inventoryWdProjMasRemoteModel != null) {
            try {
                Class<?> clazz = _inventoryWdProjMasRemoteModel.getClass();

                Method method = clazz.getMethod("setBatchId", String.class);

                method.invoke(_inventoryWdProjMasRemoteModel, batchId);
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

        if (_inventoryWdProjMasRemoteModel != null) {
            try {
                Class<?> clazz = _inventoryWdProjMasRemoteModel.getClass();

                Method method = clazz.getMethod("setItemIdentifier",
                        String.class);

                method.invoke(_inventoryWdProjMasRemoteModel, itemIdentifier);
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

        if (_inventoryWdProjMasRemoteModel != null) {
            try {
                Class<?> clazz = _inventoryWdProjMasRemoteModel.getClass();

                Method method = clazz.getMethod("setInboundStatus", String.class);

                method.invoke(_inventoryWdProjMasRemoteModel, inboundStatus);
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

        if (_inventoryWdProjMasRemoteModel != null) {
            try {
                Class<?> clazz = _inventoryWdProjMasRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedBy", String.class);

                method.invoke(_inventoryWdProjMasRemoteModel, modifiedBy);
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

        if (_inventoryWdProjMasRemoteModel != null) {
            try {
                Class<?> clazz = _inventoryWdProjMasRemoteModel.getClass();

                Method method = clazz.getMethod("setMonth", String.class);

                method.invoke(_inventoryWdProjMasRemoteModel, month);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getForecastName() {
        return _forecastName;
    }

    @Override
    public void setForecastName(String forecastName) {
        _forecastName = forecastName;

        if (_inventoryWdProjMasRemoteModel != null) {
            try {
                Class<?> clazz = _inventoryWdProjMasRemoteModel.getClass();

                Method method = clazz.getMethod("setForecastName", String.class);

                method.invoke(_inventoryWdProjMasRemoteModel, forecastName);
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

        if (_inventoryWdProjMasRemoteModel != null) {
            try {
                Class<?> clazz = _inventoryWdProjMasRemoteModel.getClass();

                Method method = clazz.getMethod("setAmountWithdrawn",
                        String.class);

                method.invoke(_inventoryWdProjMasRemoteModel, amountWithdrawn);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getInventoryWdProjMasRemoteModel() {
        return _inventoryWdProjMasRemoteModel;
    }

    public void setInventoryWdProjMasRemoteModel(
        BaseModel<?> inventoryWdProjMasRemoteModel) {
        _inventoryWdProjMasRemoteModel = inventoryWdProjMasRemoteModel;
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

        Class<?> remoteModelClass = _inventoryWdProjMasRemoteModel.getClass();

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

        Object returnValue = method.invoke(_inventoryWdProjMasRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            InventoryWdProjMasLocalServiceUtil.addInventoryWdProjMas(this);
        } else {
            InventoryWdProjMasLocalServiceUtil.updateInventoryWdProjMas(this);
        }
    }

    @Override
    public InventoryWdProjMas toEscapedModel() {
        return (InventoryWdProjMas) ProxyUtil.newProxyInstance(InventoryWdProjMas.class.getClassLoader(),
            new Class[] { InventoryWdProjMas.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        InventoryWdProjMasClp clone = new InventoryWdProjMasClp();

        clone.setWeek(getWeek());
        clone.setItemMasterSid(getItemMasterSid());
        clone.setUnitsWithdrawn(getUnitsWithdrawn());
        clone.setCountry(getCountry());
        clone.setYear(getYear());
        clone.setItemId(getItemId());
        clone.setModifiedDate(getModifiedDate());
        clone.setOrganizationKey(getOrganizationKey());
        clone.setRecordLockStatus(getRecordLockStatus());
        clone.setItemIdentifierCodeQualifier(getItemIdentifierCodeQualifier());
        clone.setSource(getSource());
        clone.setCreatedDate(getCreatedDate());
        clone.setCreatedBy(getCreatedBy());
        clone.setInventoryWdProjMasSid(getInventoryWdProjMasSid());
        clone.setDay(getDay());
        clone.setForecastVer(getForecastVer());
        clone.setBatchId(getBatchId());
        clone.setItemIdentifier(getItemIdentifier());
        clone.setInboundStatus(getInboundStatus());
        clone.setModifiedBy(getModifiedBy());
        clone.setMonth(getMonth());
        clone.setForecastName(getForecastName());
        clone.setAmountWithdrawn(getAmountWithdrawn());

        return clone;
    }

    @Override
    public int compareTo(InventoryWdProjMas inventoryWdProjMas) {
        int primaryKey = inventoryWdProjMas.getPrimaryKey();

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

        if (!(obj instanceof InventoryWdProjMasClp)) {
            return false;
        }

        InventoryWdProjMasClp inventoryWdProjMas = (InventoryWdProjMasClp) obj;

        int primaryKey = inventoryWdProjMas.getPrimaryKey();

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

        sb.append("{week=");
        sb.append(getWeek());
        sb.append(", itemMasterSid=");
        sb.append(getItemMasterSid());
        sb.append(", unitsWithdrawn=");
        sb.append(getUnitsWithdrawn());
        sb.append(", country=");
        sb.append(getCountry());
        sb.append(", year=");
        sb.append(getYear());
        sb.append(", itemId=");
        sb.append(getItemId());
        sb.append(", modifiedDate=");
        sb.append(getModifiedDate());
        sb.append(", organizationKey=");
        sb.append(getOrganizationKey());
        sb.append(", recordLockStatus=");
        sb.append(getRecordLockStatus());
        sb.append(", itemIdentifierCodeQualifier=");
        sb.append(getItemIdentifierCodeQualifier());
        sb.append(", source=");
        sb.append(getSource());
        sb.append(", createdDate=");
        sb.append(getCreatedDate());
        sb.append(", createdBy=");
        sb.append(getCreatedBy());
        sb.append(", inventoryWdProjMasSid=");
        sb.append(getInventoryWdProjMasSid());
        sb.append(", day=");
        sb.append(getDay());
        sb.append(", forecastVer=");
        sb.append(getForecastVer());
        sb.append(", batchId=");
        sb.append(getBatchId());
        sb.append(", itemIdentifier=");
        sb.append(getItemIdentifier());
        sb.append(", inboundStatus=");
        sb.append(getInboundStatus());
        sb.append(", modifiedBy=");
        sb.append(getModifiedBy());
        sb.append(", month=");
        sb.append(getMonth());
        sb.append(", forecastName=");
        sb.append(getForecastName());
        sb.append(", amountWithdrawn=");
        sb.append(getAmountWithdrawn());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(73);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.InventoryWdProjMas");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>week</column-name><column-value><![CDATA[");
        sb.append(getWeek());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemMasterSid</column-name><column-value><![CDATA[");
        sb.append(getItemMasterSid());
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
            "<column><column-name>recordLockStatus</column-name><column-value><![CDATA[");
        sb.append(getRecordLockStatus());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemIdentifierCodeQualifier</column-name><column-value><![CDATA[");
        sb.append(getItemIdentifierCodeQualifier());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>source</column-name><column-value><![CDATA[");
        sb.append(getSource());
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
            "<column><column-name>inventoryWdProjMasSid</column-name><column-value><![CDATA[");
        sb.append(getInventoryWdProjMasSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>day</column-name><column-value><![CDATA[");
        sb.append(getDay());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>forecastVer</column-name><column-value><![CDATA[");
        sb.append(getForecastVer());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>batchId</column-name><column-value><![CDATA[");
        sb.append(getBatchId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemIdentifier</column-name><column-value><![CDATA[");
        sb.append(getItemIdentifier());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>inboundStatus</column-name><column-value><![CDATA[");
        sb.append(getInboundStatus());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedBy</column-name><column-value><![CDATA[");
        sb.append(getModifiedBy());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>month</column-name><column-value><![CDATA[");
        sb.append(getMonth());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>forecastName</column-name><column-value><![CDATA[");
        sb.append(getForecastName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>amountWithdrawn</column-name><column-value><![CDATA[");
        sb.append(getAmountWithdrawn());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
