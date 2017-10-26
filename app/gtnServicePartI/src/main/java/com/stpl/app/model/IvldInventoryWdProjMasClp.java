package com.stpl.app.model;

import com.stpl.app.service.ClpSerializer;
import com.stpl.app.service.IvldInventoryWdProjMasLocalServiceUtil;

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


public class IvldInventoryWdProjMasClp extends BaseModelImpl<IvldInventoryWdProjMas>
    implements IvldInventoryWdProjMas {
    private int _inventoryWdProjMasIntfId;
    private String _week;
    private String _unitsWithdrawn;
    private String _reasonForFailure;
    private String _country;
    private String _year;
    private String _itemId;
    private Date _modifiedDate;
    private String _organizationKey;
    private String _itemIdentifierCodeQualifier;
    private String _source;
    private Date _createdDate;
    private String _createdBy;
    private String _day;
    private String _forecastVer;
    private String _batchId;
    private String _addChgDelIndicator;
    private String _itemIdentifier;
    private String _errorField;
    private String _errorCode;
    private Date _intfInsertedDate;
    private String _modifiedBy;
    private int _ivldInventoryWdProjMasSid;
    private String _month;
    private String _reprocessedFlag;
    private String _forecastName;
    private String _amountWithdrawn;
    private boolean _checkRecord;
    private BaseModel<?> _ivldInventoryWdProjMasRemoteModel;

    public IvldInventoryWdProjMasClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return IvldInventoryWdProjMas.class;
    }

    @Override
    public String getModelClassName() {
        return IvldInventoryWdProjMas.class.getName();
    }

    @Override
    public int getPrimaryKey() {
        return _ivldInventoryWdProjMasSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setIvldInventoryWdProjMasSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _ivldInventoryWdProjMasSid;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Integer) primaryKeyObj).intValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("inventoryWdProjMasIntfId", getInventoryWdProjMasIntfId());
        attributes.put("week", getWeek());
        attributes.put("unitsWithdrawn", getUnitsWithdrawn());
        attributes.put("reasonForFailure", getReasonForFailure());
        attributes.put("country", getCountry());
        attributes.put("year", getYear());
        attributes.put("itemId", getItemId());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("organizationKey", getOrganizationKey());
        attributes.put("itemIdentifierCodeQualifier",
            getItemIdentifierCodeQualifier());
        attributes.put("source", getSource());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("day", getDay());
        attributes.put("forecastVer", getForecastVer());
        attributes.put("batchId", getBatchId());
        attributes.put("addChgDelIndicator", getAddChgDelIndicator());
        attributes.put("itemIdentifier", getItemIdentifier());
        attributes.put("errorField", getErrorField());
        attributes.put("errorCode", getErrorCode());
        attributes.put("intfInsertedDate", getIntfInsertedDate());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("ivldInventoryWdProjMasSid",
            getIvldInventoryWdProjMasSid());
        attributes.put("month", getMonth());
        attributes.put("reprocessedFlag", getReprocessedFlag());
        attributes.put("forecastName", getForecastName());
        attributes.put("amountWithdrawn", getAmountWithdrawn());
        attributes.put("checkRecord", getCheckRecord());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Integer inventoryWdProjMasIntfId = (Integer) attributes.get(
                "inventoryWdProjMasIntfId");

        if (inventoryWdProjMasIntfId != null) {
            setInventoryWdProjMasIntfId(inventoryWdProjMasIntfId);
        }

        String week = (String) attributes.get("week");

        if (week != null) {
            setWeek(week);
        }

        String unitsWithdrawn = (String) attributes.get("unitsWithdrawn");

        if (unitsWithdrawn != null) {
            setUnitsWithdrawn(unitsWithdrawn);
        }

        String reasonForFailure = (String) attributes.get("reasonForFailure");

        if (reasonForFailure != null) {
            setReasonForFailure(reasonForFailure);
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

        String addChgDelIndicator = (String) attributes.get(
                "addChgDelIndicator");

        if (addChgDelIndicator != null) {
            setAddChgDelIndicator(addChgDelIndicator);
        }

        String itemIdentifier = (String) attributes.get("itemIdentifier");

        if (itemIdentifier != null) {
            setItemIdentifier(itemIdentifier);
        }

        String errorField = (String) attributes.get("errorField");

        if (errorField != null) {
            setErrorField(errorField);
        }

        String errorCode = (String) attributes.get("errorCode");

        if (errorCode != null) {
            setErrorCode(errorCode);
        }

        Date intfInsertedDate = (Date) attributes.get("intfInsertedDate");

        if (intfInsertedDate != null) {
            setIntfInsertedDate(intfInsertedDate);
        }

        String modifiedBy = (String) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        Integer ivldInventoryWdProjMasSid = (Integer) attributes.get(
                "ivldInventoryWdProjMasSid");

        if (ivldInventoryWdProjMasSid != null) {
            setIvldInventoryWdProjMasSid(ivldInventoryWdProjMasSid);
        }

        String month = (String) attributes.get("month");

        if (month != null) {
            setMonth(month);
        }

        String reprocessedFlag = (String) attributes.get("reprocessedFlag");

        if (reprocessedFlag != null) {
            setReprocessedFlag(reprocessedFlag);
        }

        String forecastName = (String) attributes.get("forecastName");

        if (forecastName != null) {
            setForecastName(forecastName);
        }

        String amountWithdrawn = (String) attributes.get("amountWithdrawn");

        if (amountWithdrawn != null) {
            setAmountWithdrawn(amountWithdrawn);
        }

        Boolean checkRecord = (Boolean) attributes.get("checkRecord");

        if (checkRecord != null) {
            setCheckRecord(checkRecord);
        }
    }

    @Override
    public int getInventoryWdProjMasIntfId() {
        return _inventoryWdProjMasIntfId;
    }

    @Override
    public void setInventoryWdProjMasIntfId(int inventoryWdProjMasIntfId) {
        _inventoryWdProjMasIntfId = inventoryWdProjMasIntfId;

        if (_ivldInventoryWdProjMasRemoteModel != null) {
            try {
                Class<?> clazz = _ivldInventoryWdProjMasRemoteModel.getClass();

                Method method = clazz.getMethod("setInventoryWdProjMasIntfId",
                        int.class);

                method.invoke(_ivldInventoryWdProjMasRemoteModel,
                    inventoryWdProjMasIntfId);
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

        if (_ivldInventoryWdProjMasRemoteModel != null) {
            try {
                Class<?> clazz = _ivldInventoryWdProjMasRemoteModel.getClass();

                Method method = clazz.getMethod("setWeek", String.class);

                method.invoke(_ivldInventoryWdProjMasRemoteModel, week);
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

        if (_ivldInventoryWdProjMasRemoteModel != null) {
            try {
                Class<?> clazz = _ivldInventoryWdProjMasRemoteModel.getClass();

                Method method = clazz.getMethod("setUnitsWithdrawn",
                        String.class);

                method.invoke(_ivldInventoryWdProjMasRemoteModel, unitsWithdrawn);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getReasonForFailure() {
        return _reasonForFailure;
    }

    @Override
    public void setReasonForFailure(String reasonForFailure) {
        _reasonForFailure = reasonForFailure;

        if (_ivldInventoryWdProjMasRemoteModel != null) {
            try {
                Class<?> clazz = _ivldInventoryWdProjMasRemoteModel.getClass();

                Method method = clazz.getMethod("setReasonForFailure",
                        String.class);

                method.invoke(_ivldInventoryWdProjMasRemoteModel,
                    reasonForFailure);
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

        if (_ivldInventoryWdProjMasRemoteModel != null) {
            try {
                Class<?> clazz = _ivldInventoryWdProjMasRemoteModel.getClass();

                Method method = clazz.getMethod("setCountry", String.class);

                method.invoke(_ivldInventoryWdProjMasRemoteModel, country);
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

        if (_ivldInventoryWdProjMasRemoteModel != null) {
            try {
                Class<?> clazz = _ivldInventoryWdProjMasRemoteModel.getClass();

                Method method = clazz.getMethod("setYear", String.class);

                method.invoke(_ivldInventoryWdProjMasRemoteModel, year);
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

        if (_ivldInventoryWdProjMasRemoteModel != null) {
            try {
                Class<?> clazz = _ivldInventoryWdProjMasRemoteModel.getClass();

                Method method = clazz.getMethod("setItemId", String.class);

                method.invoke(_ivldInventoryWdProjMasRemoteModel, itemId);
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

        if (_ivldInventoryWdProjMasRemoteModel != null) {
            try {
                Class<?> clazz = _ivldInventoryWdProjMasRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedDate", Date.class);

                method.invoke(_ivldInventoryWdProjMasRemoteModel, modifiedDate);
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

        if (_ivldInventoryWdProjMasRemoteModel != null) {
            try {
                Class<?> clazz = _ivldInventoryWdProjMasRemoteModel.getClass();

                Method method = clazz.getMethod("setOrganizationKey",
                        String.class);

                method.invoke(_ivldInventoryWdProjMasRemoteModel,
                    organizationKey);
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

        if (_ivldInventoryWdProjMasRemoteModel != null) {
            try {
                Class<?> clazz = _ivldInventoryWdProjMasRemoteModel.getClass();

                Method method = clazz.getMethod("setItemIdentifierCodeQualifier",
                        String.class);

                method.invoke(_ivldInventoryWdProjMasRemoteModel,
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

        if (_ivldInventoryWdProjMasRemoteModel != null) {
            try {
                Class<?> clazz = _ivldInventoryWdProjMasRemoteModel.getClass();

                Method method = clazz.getMethod("setSource", String.class);

                method.invoke(_ivldInventoryWdProjMasRemoteModel, source);
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

        if (_ivldInventoryWdProjMasRemoteModel != null) {
            try {
                Class<?> clazz = _ivldInventoryWdProjMasRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedDate", Date.class);

                method.invoke(_ivldInventoryWdProjMasRemoteModel, createdDate);
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

        if (_ivldInventoryWdProjMasRemoteModel != null) {
            try {
                Class<?> clazz = _ivldInventoryWdProjMasRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedBy", String.class);

                method.invoke(_ivldInventoryWdProjMasRemoteModel, createdBy);
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

        if (_ivldInventoryWdProjMasRemoteModel != null) {
            try {
                Class<?> clazz = _ivldInventoryWdProjMasRemoteModel.getClass();

                Method method = clazz.getMethod("setDay", String.class);

                method.invoke(_ivldInventoryWdProjMasRemoteModel, day);
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

        if (_ivldInventoryWdProjMasRemoteModel != null) {
            try {
                Class<?> clazz = _ivldInventoryWdProjMasRemoteModel.getClass();

                Method method = clazz.getMethod("setForecastVer", String.class);

                method.invoke(_ivldInventoryWdProjMasRemoteModel, forecastVer);
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

        if (_ivldInventoryWdProjMasRemoteModel != null) {
            try {
                Class<?> clazz = _ivldInventoryWdProjMasRemoteModel.getClass();

                Method method = clazz.getMethod("setBatchId", String.class);

                method.invoke(_ivldInventoryWdProjMasRemoteModel, batchId);
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

        if (_ivldInventoryWdProjMasRemoteModel != null) {
            try {
                Class<?> clazz = _ivldInventoryWdProjMasRemoteModel.getClass();

                Method method = clazz.getMethod("setAddChgDelIndicator",
                        String.class);

                method.invoke(_ivldInventoryWdProjMasRemoteModel,
                    addChgDelIndicator);
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

        if (_ivldInventoryWdProjMasRemoteModel != null) {
            try {
                Class<?> clazz = _ivldInventoryWdProjMasRemoteModel.getClass();

                Method method = clazz.getMethod("setItemIdentifier",
                        String.class);

                method.invoke(_ivldInventoryWdProjMasRemoteModel, itemIdentifier);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getErrorField() {
        return _errorField;
    }

    @Override
    public void setErrorField(String errorField) {
        _errorField = errorField;

        if (_ivldInventoryWdProjMasRemoteModel != null) {
            try {
                Class<?> clazz = _ivldInventoryWdProjMasRemoteModel.getClass();

                Method method = clazz.getMethod("setErrorField", String.class);

                method.invoke(_ivldInventoryWdProjMasRemoteModel, errorField);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getErrorCode() {
        return _errorCode;
    }

    @Override
    public void setErrorCode(String errorCode) {
        _errorCode = errorCode;

        if (_ivldInventoryWdProjMasRemoteModel != null) {
            try {
                Class<?> clazz = _ivldInventoryWdProjMasRemoteModel.getClass();

                Method method = clazz.getMethod("setErrorCode", String.class);

                method.invoke(_ivldInventoryWdProjMasRemoteModel, errorCode);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public Date getIntfInsertedDate() {
        return _intfInsertedDate;
    }

    @Override
    public void setIntfInsertedDate(Date intfInsertedDate) {
        _intfInsertedDate = intfInsertedDate;

        if (_ivldInventoryWdProjMasRemoteModel != null) {
            try {
                Class<?> clazz = _ivldInventoryWdProjMasRemoteModel.getClass();

                Method method = clazz.getMethod("setIntfInsertedDate",
                        Date.class);

                method.invoke(_ivldInventoryWdProjMasRemoteModel,
                    intfInsertedDate);
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

        if (_ivldInventoryWdProjMasRemoteModel != null) {
            try {
                Class<?> clazz = _ivldInventoryWdProjMasRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedBy", String.class);

                method.invoke(_ivldInventoryWdProjMasRemoteModel, modifiedBy);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getIvldInventoryWdProjMasSid() {
        return _ivldInventoryWdProjMasSid;
    }

    @Override
    public void setIvldInventoryWdProjMasSid(int ivldInventoryWdProjMasSid) {
        _ivldInventoryWdProjMasSid = ivldInventoryWdProjMasSid;

        if (_ivldInventoryWdProjMasRemoteModel != null) {
            try {
                Class<?> clazz = _ivldInventoryWdProjMasRemoteModel.getClass();

                Method method = clazz.getMethod("setIvldInventoryWdProjMasSid",
                        int.class);

                method.invoke(_ivldInventoryWdProjMasRemoteModel,
                    ivldInventoryWdProjMasSid);
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

        if (_ivldInventoryWdProjMasRemoteModel != null) {
            try {
                Class<?> clazz = _ivldInventoryWdProjMasRemoteModel.getClass();

                Method method = clazz.getMethod("setMonth", String.class);

                method.invoke(_ivldInventoryWdProjMasRemoteModel, month);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getReprocessedFlag() {
        return _reprocessedFlag;
    }

    @Override
    public void setReprocessedFlag(String reprocessedFlag) {
        _reprocessedFlag = reprocessedFlag;

        if (_ivldInventoryWdProjMasRemoteModel != null) {
            try {
                Class<?> clazz = _ivldInventoryWdProjMasRemoteModel.getClass();

                Method method = clazz.getMethod("setReprocessedFlag",
                        String.class);

                method.invoke(_ivldInventoryWdProjMasRemoteModel,
                    reprocessedFlag);
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

        if (_ivldInventoryWdProjMasRemoteModel != null) {
            try {
                Class<?> clazz = _ivldInventoryWdProjMasRemoteModel.getClass();

                Method method = clazz.getMethod("setForecastName", String.class);

                method.invoke(_ivldInventoryWdProjMasRemoteModel, forecastName);
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

        if (_ivldInventoryWdProjMasRemoteModel != null) {
            try {
                Class<?> clazz = _ivldInventoryWdProjMasRemoteModel.getClass();

                Method method = clazz.getMethod("setAmountWithdrawn",
                        String.class);

                method.invoke(_ivldInventoryWdProjMasRemoteModel,
                    amountWithdrawn);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public boolean getCheckRecord() {
        return _checkRecord;
    }

    @Override
    public boolean isCheckRecord() {
        return _checkRecord;
    }

    @Override
    public void setCheckRecord(boolean checkRecord) {
        _checkRecord = checkRecord;

        if (_ivldInventoryWdProjMasRemoteModel != null) {
            try {
                Class<?> clazz = _ivldInventoryWdProjMasRemoteModel.getClass();

                Method method = clazz.getMethod("setCheckRecord", boolean.class);

                method.invoke(_ivldInventoryWdProjMasRemoteModel, checkRecord);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getIvldInventoryWdProjMasRemoteModel() {
        return _ivldInventoryWdProjMasRemoteModel;
    }

    public void setIvldInventoryWdProjMasRemoteModel(
        BaseModel<?> ivldInventoryWdProjMasRemoteModel) {
        _ivldInventoryWdProjMasRemoteModel = ivldInventoryWdProjMasRemoteModel;
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

        Class<?> remoteModelClass = _ivldInventoryWdProjMasRemoteModel.getClass();

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

        Object returnValue = method.invoke(_ivldInventoryWdProjMasRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            IvldInventoryWdProjMasLocalServiceUtil.addIvldInventoryWdProjMas(this);
        } else {
            IvldInventoryWdProjMasLocalServiceUtil.updateIvldInventoryWdProjMas(this);
        }
    }

    @Override
    public IvldInventoryWdProjMas toEscapedModel() {
        return (IvldInventoryWdProjMas) ProxyUtil.newProxyInstance(IvldInventoryWdProjMas.class.getClassLoader(),
            new Class[] { IvldInventoryWdProjMas.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        IvldInventoryWdProjMasClp clone = new IvldInventoryWdProjMasClp();

        clone.setInventoryWdProjMasIntfId(getInventoryWdProjMasIntfId());
        clone.setWeek(getWeek());
        clone.setUnitsWithdrawn(getUnitsWithdrawn());
        clone.setReasonForFailure(getReasonForFailure());
        clone.setCountry(getCountry());
        clone.setYear(getYear());
        clone.setItemId(getItemId());
        clone.setModifiedDate(getModifiedDate());
        clone.setOrganizationKey(getOrganizationKey());
        clone.setItemIdentifierCodeQualifier(getItemIdentifierCodeQualifier());
        clone.setSource(getSource());
        clone.setCreatedDate(getCreatedDate());
        clone.setCreatedBy(getCreatedBy());
        clone.setDay(getDay());
        clone.setForecastVer(getForecastVer());
        clone.setBatchId(getBatchId());
        clone.setAddChgDelIndicator(getAddChgDelIndicator());
        clone.setItemIdentifier(getItemIdentifier());
        clone.setErrorField(getErrorField());
        clone.setErrorCode(getErrorCode());
        clone.setIntfInsertedDate(getIntfInsertedDate());
        clone.setModifiedBy(getModifiedBy());
        clone.setIvldInventoryWdProjMasSid(getIvldInventoryWdProjMasSid());
        clone.setMonth(getMonth());
        clone.setReprocessedFlag(getReprocessedFlag());
        clone.setForecastName(getForecastName());
        clone.setAmountWithdrawn(getAmountWithdrawn());
        clone.setCheckRecord(getCheckRecord());

        return clone;
    }

    @Override
    public int compareTo(IvldInventoryWdProjMas ivldInventoryWdProjMas) {
        int primaryKey = ivldInventoryWdProjMas.getPrimaryKey();

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

        if (!(obj instanceof IvldInventoryWdProjMasClp)) {
            return false;
        }

        IvldInventoryWdProjMasClp ivldInventoryWdProjMas = (IvldInventoryWdProjMasClp) obj;

        int primaryKey = ivldInventoryWdProjMas.getPrimaryKey();

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
        StringBundler sb = new StringBundler(57);

        sb.append("{inventoryWdProjMasIntfId=");
        sb.append(getInventoryWdProjMasIntfId());
        sb.append(", week=");
        sb.append(getWeek());
        sb.append(", unitsWithdrawn=");
        sb.append(getUnitsWithdrawn());
        sb.append(", reasonForFailure=");
        sb.append(getReasonForFailure());
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
        sb.append(", itemIdentifierCodeQualifier=");
        sb.append(getItemIdentifierCodeQualifier());
        sb.append(", source=");
        sb.append(getSource());
        sb.append(", createdDate=");
        sb.append(getCreatedDate());
        sb.append(", createdBy=");
        sb.append(getCreatedBy());
        sb.append(", day=");
        sb.append(getDay());
        sb.append(", forecastVer=");
        sb.append(getForecastVer());
        sb.append(", batchId=");
        sb.append(getBatchId());
        sb.append(", addChgDelIndicator=");
        sb.append(getAddChgDelIndicator());
        sb.append(", itemIdentifier=");
        sb.append(getItemIdentifier());
        sb.append(", errorField=");
        sb.append(getErrorField());
        sb.append(", errorCode=");
        sb.append(getErrorCode());
        sb.append(", intfInsertedDate=");
        sb.append(getIntfInsertedDate());
        sb.append(", modifiedBy=");
        sb.append(getModifiedBy());
        sb.append(", ivldInventoryWdProjMasSid=");
        sb.append(getIvldInventoryWdProjMasSid());
        sb.append(", month=");
        sb.append(getMonth());
        sb.append(", reprocessedFlag=");
        sb.append(getReprocessedFlag());
        sb.append(", forecastName=");
        sb.append(getForecastName());
        sb.append(", amountWithdrawn=");
        sb.append(getAmountWithdrawn());
        sb.append(", checkRecord=");
        sb.append(getCheckRecord());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(88);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.IvldInventoryWdProjMas");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>inventoryWdProjMasIntfId</column-name><column-value><![CDATA[");
        sb.append(getInventoryWdProjMasIntfId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>week</column-name><column-value><![CDATA[");
        sb.append(getWeek());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>unitsWithdrawn</column-name><column-value><![CDATA[");
        sb.append(getUnitsWithdrawn());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>reasonForFailure</column-name><column-value><![CDATA[");
        sb.append(getReasonForFailure());
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
            "<column><column-name>addChgDelIndicator</column-name><column-value><![CDATA[");
        sb.append(getAddChgDelIndicator());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>itemIdentifier</column-name><column-value><![CDATA[");
        sb.append(getItemIdentifier());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>errorField</column-name><column-value><![CDATA[");
        sb.append(getErrorField());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>errorCode</column-name><column-value><![CDATA[");
        sb.append(getErrorCode());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>intfInsertedDate</column-name><column-value><![CDATA[");
        sb.append(getIntfInsertedDate());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>modifiedBy</column-name><column-value><![CDATA[");
        sb.append(getModifiedBy());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>ivldInventoryWdProjMasSid</column-name><column-value><![CDATA[");
        sb.append(getIvldInventoryWdProjMasSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>month</column-name><column-value><![CDATA[");
        sb.append(getMonth());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>reprocessedFlag</column-name><column-value><![CDATA[");
        sb.append(getReprocessedFlag());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>forecastName</column-name><column-value><![CDATA[");
        sb.append(getForecastName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>amountWithdrawn</column-name><column-value><![CDATA[");
        sb.append(getAmountWithdrawn());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>checkRecord</column-name><column-value><![CDATA[");
        sb.append(getCheckRecord());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
