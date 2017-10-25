package com.stpl.app.model;

import com.stpl.app.service.ClpSerializer;
import com.stpl.app.service.IvldInventoryWdActualMasLocalServiceUtil;

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


public class IvldInventoryWdActualMasClp extends BaseModelImpl<IvldInventoryWdActualMas>
    implements IvldInventoryWdActualMas {
    private String _quantityOnOrder;
    private String _week;
    private String _amountOnHand;
    private String _year;
    private String _itemId;
    private Date _modifiedDate;
    private String _organizationKey;
    private String _createdBy;
    private Date _createdDate;
    private String _source;
    private int _ivldInventoryWdActualMasSid;
    private String _day;
    private String _addChgDelIndicator;
    private String _unitsOnHand;
    private String _amountReceived;
    private String _itemIdentifier;
    private String _errorCode;
    private Date _intfInsertedDate;
    private String _modifiedBy;
    private String _month;
    private String _reprocessedFlag;
    private String _amountWithdrawn;
    private int _inventoryWdActualMasIntfId;
    private String _quantityReceived;
    private String _unitsWithdrawn;
    private String _reasonForFailure;
    private String _country;
    private String _itemIdentifierCodeQualifier;
    private String _batchId;
    private String _errorField;
    private String _amountOnOrder;
    private boolean _checkRecord;
    private BaseModel<?> _ivldInventoryWdActualMasRemoteModel;

    public IvldInventoryWdActualMasClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return IvldInventoryWdActualMas.class;
    }

    @Override
    public String getModelClassName() {
        return IvldInventoryWdActualMas.class.getName();
    }

    @Override
    public int getPrimaryKey() {
        return _ivldInventoryWdActualMasSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setIvldInventoryWdActualMasSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _ivldInventoryWdActualMasSid;
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
        attributes.put("year", getYear());
        attributes.put("itemId", getItemId());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("organizationKey", getOrganizationKey());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("source", getSource());
        attributes.put("ivldInventoryWdActualMasSid",
            getIvldInventoryWdActualMasSid());
        attributes.put("day", getDay());
        attributes.put("addChgDelIndicator", getAddChgDelIndicator());
        attributes.put("unitsOnHand", getUnitsOnHand());
        attributes.put("amountReceived", getAmountReceived());
        attributes.put("itemIdentifier", getItemIdentifier());
        attributes.put("errorCode", getErrorCode());
        attributes.put("intfInsertedDate", getIntfInsertedDate());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("month", getMonth());
        attributes.put("reprocessedFlag", getReprocessedFlag());
        attributes.put("amountWithdrawn", getAmountWithdrawn());
        attributes.put("inventoryWdActualMasIntfId",
            getInventoryWdActualMasIntfId());
        attributes.put("quantityReceived", getQuantityReceived());
        attributes.put("unitsWithdrawn", getUnitsWithdrawn());
        attributes.put("reasonForFailure", getReasonForFailure());
        attributes.put("country", getCountry());
        attributes.put("itemIdentifierCodeQualifier",
            getItemIdentifierCodeQualifier());
        attributes.put("batchId", getBatchId());
        attributes.put("errorField", getErrorField());
        attributes.put("amountOnOrder", getAmountOnOrder());
        attributes.put("checkRecord", getCheckRecord());

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

        String createdBy = (String) attributes.get("createdBy");

        if (createdBy != null) {
            setCreatedBy(createdBy);
        }

        Date createdDate = (Date) attributes.get("createdDate");

        if (createdDate != null) {
            setCreatedDate(createdDate);
        }

        String source = (String) attributes.get("source");

        if (source != null) {
            setSource(source);
        }

        Integer ivldInventoryWdActualMasSid = (Integer) attributes.get(
                "ivldInventoryWdActualMasSid");

        if (ivldInventoryWdActualMasSid != null) {
            setIvldInventoryWdActualMasSid(ivldInventoryWdActualMasSid);
        }

        String day = (String) attributes.get("day");

        if (day != null) {
            setDay(day);
        }

        String addChgDelIndicator = (String) attributes.get(
                "addChgDelIndicator");

        if (addChgDelIndicator != null) {
            setAddChgDelIndicator(addChgDelIndicator);
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

        String month = (String) attributes.get("month");

        if (month != null) {
            setMonth(month);
        }

        String reprocessedFlag = (String) attributes.get("reprocessedFlag");

        if (reprocessedFlag != null) {
            setReprocessedFlag(reprocessedFlag);
        }

        String amountWithdrawn = (String) attributes.get("amountWithdrawn");

        if (amountWithdrawn != null) {
            setAmountWithdrawn(amountWithdrawn);
        }

        Integer inventoryWdActualMasIntfId = (Integer) attributes.get(
                "inventoryWdActualMasIntfId");

        if (inventoryWdActualMasIntfId != null) {
            setInventoryWdActualMasIntfId(inventoryWdActualMasIntfId);
        }

        String quantityReceived = (String) attributes.get("quantityReceived");

        if (quantityReceived != null) {
            setQuantityReceived(quantityReceived);
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

        String itemIdentifierCodeQualifier = (String) attributes.get(
                "itemIdentifierCodeQualifier");

        if (itemIdentifierCodeQualifier != null) {
            setItemIdentifierCodeQualifier(itemIdentifierCodeQualifier);
        }

        String batchId = (String) attributes.get("batchId");

        if (batchId != null) {
            setBatchId(batchId);
        }

        String errorField = (String) attributes.get("errorField");

        if (errorField != null) {
            setErrorField(errorField);
        }

        String amountOnOrder = (String) attributes.get("amountOnOrder");

        if (amountOnOrder != null) {
            setAmountOnOrder(amountOnOrder);
        }

        Boolean checkRecord = (Boolean) attributes.get("checkRecord");

        if (checkRecord != null) {
            setCheckRecord(checkRecord);
        }
    }

    @Override
    public String getQuantityOnOrder() {
        return _quantityOnOrder;
    }

    @Override
    public void setQuantityOnOrder(String quantityOnOrder) {
        _quantityOnOrder = quantityOnOrder;

        if (_ivldInventoryWdActualMasRemoteModel != null) {
            try {
                Class<?> clazz = _ivldInventoryWdActualMasRemoteModel.getClass();

                Method method = clazz.getMethod("setQuantityOnOrder",
                        String.class);

                method.invoke(_ivldInventoryWdActualMasRemoteModel,
                    quantityOnOrder);
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

        if (_ivldInventoryWdActualMasRemoteModel != null) {
            try {
                Class<?> clazz = _ivldInventoryWdActualMasRemoteModel.getClass();

                Method method = clazz.getMethod("setWeek", String.class);

                method.invoke(_ivldInventoryWdActualMasRemoteModel, week);
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

        if (_ivldInventoryWdActualMasRemoteModel != null) {
            try {
                Class<?> clazz = _ivldInventoryWdActualMasRemoteModel.getClass();

                Method method = clazz.getMethod("setAmountOnHand", String.class);

                method.invoke(_ivldInventoryWdActualMasRemoteModel, amountOnHand);
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

        if (_ivldInventoryWdActualMasRemoteModel != null) {
            try {
                Class<?> clazz = _ivldInventoryWdActualMasRemoteModel.getClass();

                Method method = clazz.getMethod("setYear", String.class);

                method.invoke(_ivldInventoryWdActualMasRemoteModel, year);
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

        if (_ivldInventoryWdActualMasRemoteModel != null) {
            try {
                Class<?> clazz = _ivldInventoryWdActualMasRemoteModel.getClass();

                Method method = clazz.getMethod("setItemId", String.class);

                method.invoke(_ivldInventoryWdActualMasRemoteModel, itemId);
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

        if (_ivldInventoryWdActualMasRemoteModel != null) {
            try {
                Class<?> clazz = _ivldInventoryWdActualMasRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedDate", Date.class);

                method.invoke(_ivldInventoryWdActualMasRemoteModel, modifiedDate);
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

        if (_ivldInventoryWdActualMasRemoteModel != null) {
            try {
                Class<?> clazz = _ivldInventoryWdActualMasRemoteModel.getClass();

                Method method = clazz.getMethod("setOrganizationKey",
                        String.class);

                method.invoke(_ivldInventoryWdActualMasRemoteModel,
                    organizationKey);
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

        if (_ivldInventoryWdActualMasRemoteModel != null) {
            try {
                Class<?> clazz = _ivldInventoryWdActualMasRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedBy", String.class);

                method.invoke(_ivldInventoryWdActualMasRemoteModel, createdBy);
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

        if (_ivldInventoryWdActualMasRemoteModel != null) {
            try {
                Class<?> clazz = _ivldInventoryWdActualMasRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedDate", Date.class);

                method.invoke(_ivldInventoryWdActualMasRemoteModel, createdDate);
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

        if (_ivldInventoryWdActualMasRemoteModel != null) {
            try {
                Class<?> clazz = _ivldInventoryWdActualMasRemoteModel.getClass();

                Method method = clazz.getMethod("setSource", String.class);

                method.invoke(_ivldInventoryWdActualMasRemoteModel, source);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getIvldInventoryWdActualMasSid() {
        return _ivldInventoryWdActualMasSid;
    }

    @Override
    public void setIvldInventoryWdActualMasSid(int ivldInventoryWdActualMasSid) {
        _ivldInventoryWdActualMasSid = ivldInventoryWdActualMasSid;

        if (_ivldInventoryWdActualMasRemoteModel != null) {
            try {
                Class<?> clazz = _ivldInventoryWdActualMasRemoteModel.getClass();

                Method method = clazz.getMethod("setIvldInventoryWdActualMasSid",
                        int.class);

                method.invoke(_ivldInventoryWdActualMasRemoteModel,
                    ivldInventoryWdActualMasSid);
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

        if (_ivldInventoryWdActualMasRemoteModel != null) {
            try {
                Class<?> clazz = _ivldInventoryWdActualMasRemoteModel.getClass();

                Method method = clazz.getMethod("setDay", String.class);

                method.invoke(_ivldInventoryWdActualMasRemoteModel, day);
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

        if (_ivldInventoryWdActualMasRemoteModel != null) {
            try {
                Class<?> clazz = _ivldInventoryWdActualMasRemoteModel.getClass();

                Method method = clazz.getMethod("setAddChgDelIndicator",
                        String.class);

                method.invoke(_ivldInventoryWdActualMasRemoteModel,
                    addChgDelIndicator);
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

        if (_ivldInventoryWdActualMasRemoteModel != null) {
            try {
                Class<?> clazz = _ivldInventoryWdActualMasRemoteModel.getClass();

                Method method = clazz.getMethod("setUnitsOnHand", String.class);

                method.invoke(_ivldInventoryWdActualMasRemoteModel, unitsOnHand);
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

        if (_ivldInventoryWdActualMasRemoteModel != null) {
            try {
                Class<?> clazz = _ivldInventoryWdActualMasRemoteModel.getClass();

                Method method = clazz.getMethod("setAmountReceived",
                        String.class);

                method.invoke(_ivldInventoryWdActualMasRemoteModel,
                    amountReceived);
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

        if (_ivldInventoryWdActualMasRemoteModel != null) {
            try {
                Class<?> clazz = _ivldInventoryWdActualMasRemoteModel.getClass();

                Method method = clazz.getMethod("setItemIdentifier",
                        String.class);

                method.invoke(_ivldInventoryWdActualMasRemoteModel,
                    itemIdentifier);
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

        if (_ivldInventoryWdActualMasRemoteModel != null) {
            try {
                Class<?> clazz = _ivldInventoryWdActualMasRemoteModel.getClass();

                Method method = clazz.getMethod("setErrorCode", String.class);

                method.invoke(_ivldInventoryWdActualMasRemoteModel, errorCode);
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

        if (_ivldInventoryWdActualMasRemoteModel != null) {
            try {
                Class<?> clazz = _ivldInventoryWdActualMasRemoteModel.getClass();

                Method method = clazz.getMethod("setIntfInsertedDate",
                        Date.class);

                method.invoke(_ivldInventoryWdActualMasRemoteModel,
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

        if (_ivldInventoryWdActualMasRemoteModel != null) {
            try {
                Class<?> clazz = _ivldInventoryWdActualMasRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedBy", String.class);

                method.invoke(_ivldInventoryWdActualMasRemoteModel, modifiedBy);
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

        if (_ivldInventoryWdActualMasRemoteModel != null) {
            try {
                Class<?> clazz = _ivldInventoryWdActualMasRemoteModel.getClass();

                Method method = clazz.getMethod("setMonth", String.class);

                method.invoke(_ivldInventoryWdActualMasRemoteModel, month);
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

        if (_ivldInventoryWdActualMasRemoteModel != null) {
            try {
                Class<?> clazz = _ivldInventoryWdActualMasRemoteModel.getClass();

                Method method = clazz.getMethod("setReprocessedFlag",
                        String.class);

                method.invoke(_ivldInventoryWdActualMasRemoteModel,
                    reprocessedFlag);
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

        if (_ivldInventoryWdActualMasRemoteModel != null) {
            try {
                Class<?> clazz = _ivldInventoryWdActualMasRemoteModel.getClass();

                Method method = clazz.getMethod("setAmountWithdrawn",
                        String.class);

                method.invoke(_ivldInventoryWdActualMasRemoteModel,
                    amountWithdrawn);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getInventoryWdActualMasIntfId() {
        return _inventoryWdActualMasIntfId;
    }

    @Override
    public void setInventoryWdActualMasIntfId(int inventoryWdActualMasIntfId) {
        _inventoryWdActualMasIntfId = inventoryWdActualMasIntfId;

        if (_ivldInventoryWdActualMasRemoteModel != null) {
            try {
                Class<?> clazz = _ivldInventoryWdActualMasRemoteModel.getClass();

                Method method = clazz.getMethod("setInventoryWdActualMasIntfId",
                        int.class);

                method.invoke(_ivldInventoryWdActualMasRemoteModel,
                    inventoryWdActualMasIntfId);
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

        if (_ivldInventoryWdActualMasRemoteModel != null) {
            try {
                Class<?> clazz = _ivldInventoryWdActualMasRemoteModel.getClass();

                Method method = clazz.getMethod("setQuantityReceived",
                        String.class);

                method.invoke(_ivldInventoryWdActualMasRemoteModel,
                    quantityReceived);
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

        if (_ivldInventoryWdActualMasRemoteModel != null) {
            try {
                Class<?> clazz = _ivldInventoryWdActualMasRemoteModel.getClass();

                Method method = clazz.getMethod("setUnitsWithdrawn",
                        String.class);

                method.invoke(_ivldInventoryWdActualMasRemoteModel,
                    unitsWithdrawn);
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

        if (_ivldInventoryWdActualMasRemoteModel != null) {
            try {
                Class<?> clazz = _ivldInventoryWdActualMasRemoteModel.getClass();

                Method method = clazz.getMethod("setReasonForFailure",
                        String.class);

                method.invoke(_ivldInventoryWdActualMasRemoteModel,
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

        if (_ivldInventoryWdActualMasRemoteModel != null) {
            try {
                Class<?> clazz = _ivldInventoryWdActualMasRemoteModel.getClass();

                Method method = clazz.getMethod("setCountry", String.class);

                method.invoke(_ivldInventoryWdActualMasRemoteModel, country);
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

        if (_ivldInventoryWdActualMasRemoteModel != null) {
            try {
                Class<?> clazz = _ivldInventoryWdActualMasRemoteModel.getClass();

                Method method = clazz.getMethod("setItemIdentifierCodeQualifier",
                        String.class);

                method.invoke(_ivldInventoryWdActualMasRemoteModel,
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

        if (_ivldInventoryWdActualMasRemoteModel != null) {
            try {
                Class<?> clazz = _ivldInventoryWdActualMasRemoteModel.getClass();

                Method method = clazz.getMethod("setBatchId", String.class);

                method.invoke(_ivldInventoryWdActualMasRemoteModel, batchId);
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

        if (_ivldInventoryWdActualMasRemoteModel != null) {
            try {
                Class<?> clazz = _ivldInventoryWdActualMasRemoteModel.getClass();

                Method method = clazz.getMethod("setErrorField", String.class);

                method.invoke(_ivldInventoryWdActualMasRemoteModel, errorField);
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

        if (_ivldInventoryWdActualMasRemoteModel != null) {
            try {
                Class<?> clazz = _ivldInventoryWdActualMasRemoteModel.getClass();

                Method method = clazz.getMethod("setAmountOnOrder", String.class);

                method.invoke(_ivldInventoryWdActualMasRemoteModel,
                    amountOnOrder);
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

        if (_ivldInventoryWdActualMasRemoteModel != null) {
            try {
                Class<?> clazz = _ivldInventoryWdActualMasRemoteModel.getClass();

                Method method = clazz.getMethod("setCheckRecord", boolean.class);

                method.invoke(_ivldInventoryWdActualMasRemoteModel, checkRecord);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getIvldInventoryWdActualMasRemoteModel() {
        return _ivldInventoryWdActualMasRemoteModel;
    }

    public void setIvldInventoryWdActualMasRemoteModel(
        BaseModel<?> ivldInventoryWdActualMasRemoteModel) {
        _ivldInventoryWdActualMasRemoteModel = ivldInventoryWdActualMasRemoteModel;
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

        Class<?> remoteModelClass = _ivldInventoryWdActualMasRemoteModel.getClass();

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

        Object returnValue = method.invoke(_ivldInventoryWdActualMasRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            IvldInventoryWdActualMasLocalServiceUtil.addIvldInventoryWdActualMas(this);
        } else {
            IvldInventoryWdActualMasLocalServiceUtil.updateIvldInventoryWdActualMas(this);
        }
    }

    @Override
    public IvldInventoryWdActualMas toEscapedModel() {
        return (IvldInventoryWdActualMas) ProxyUtil.newProxyInstance(IvldInventoryWdActualMas.class.getClassLoader(),
            new Class[] { IvldInventoryWdActualMas.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        IvldInventoryWdActualMasClp clone = new IvldInventoryWdActualMasClp();

        clone.setQuantityOnOrder(getQuantityOnOrder());
        clone.setWeek(getWeek());
        clone.setAmountOnHand(getAmountOnHand());
        clone.setYear(getYear());
        clone.setItemId(getItemId());
        clone.setModifiedDate(getModifiedDate());
        clone.setOrganizationKey(getOrganizationKey());
        clone.setCreatedBy(getCreatedBy());
        clone.setCreatedDate(getCreatedDate());
        clone.setSource(getSource());
        clone.setIvldInventoryWdActualMasSid(getIvldInventoryWdActualMasSid());
        clone.setDay(getDay());
        clone.setAddChgDelIndicator(getAddChgDelIndicator());
        clone.setUnitsOnHand(getUnitsOnHand());
        clone.setAmountReceived(getAmountReceived());
        clone.setItemIdentifier(getItemIdentifier());
        clone.setErrorCode(getErrorCode());
        clone.setIntfInsertedDate(getIntfInsertedDate());
        clone.setModifiedBy(getModifiedBy());
        clone.setMonth(getMonth());
        clone.setReprocessedFlag(getReprocessedFlag());
        clone.setAmountWithdrawn(getAmountWithdrawn());
        clone.setInventoryWdActualMasIntfId(getInventoryWdActualMasIntfId());
        clone.setQuantityReceived(getQuantityReceived());
        clone.setUnitsWithdrawn(getUnitsWithdrawn());
        clone.setReasonForFailure(getReasonForFailure());
        clone.setCountry(getCountry());
        clone.setItemIdentifierCodeQualifier(getItemIdentifierCodeQualifier());
        clone.setBatchId(getBatchId());
        clone.setErrorField(getErrorField());
        clone.setAmountOnOrder(getAmountOnOrder());
        clone.setCheckRecord(getCheckRecord());

        return clone;
    }

    @Override
    public int compareTo(IvldInventoryWdActualMas ivldInventoryWdActualMas) {
        int primaryKey = ivldInventoryWdActualMas.getPrimaryKey();

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

        if (!(obj instanceof IvldInventoryWdActualMasClp)) {
            return false;
        }

        IvldInventoryWdActualMasClp ivldInventoryWdActualMas = (IvldInventoryWdActualMasClp) obj;

        int primaryKey = ivldInventoryWdActualMas.getPrimaryKey();

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
        StringBundler sb = new StringBundler(65);

        sb.append("{quantityOnOrder=");
        sb.append(getQuantityOnOrder());
        sb.append(", week=");
        sb.append(getWeek());
        sb.append(", amountOnHand=");
        sb.append(getAmountOnHand());
        sb.append(", year=");
        sb.append(getYear());
        sb.append(", itemId=");
        sb.append(getItemId());
        sb.append(", modifiedDate=");
        sb.append(getModifiedDate());
        sb.append(", organizationKey=");
        sb.append(getOrganizationKey());
        sb.append(", createdBy=");
        sb.append(getCreatedBy());
        sb.append(", createdDate=");
        sb.append(getCreatedDate());
        sb.append(", source=");
        sb.append(getSource());
        sb.append(", ivldInventoryWdActualMasSid=");
        sb.append(getIvldInventoryWdActualMasSid());
        sb.append(", day=");
        sb.append(getDay());
        sb.append(", addChgDelIndicator=");
        sb.append(getAddChgDelIndicator());
        sb.append(", unitsOnHand=");
        sb.append(getUnitsOnHand());
        sb.append(", amountReceived=");
        sb.append(getAmountReceived());
        sb.append(", itemIdentifier=");
        sb.append(getItemIdentifier());
        sb.append(", errorCode=");
        sb.append(getErrorCode());
        sb.append(", intfInsertedDate=");
        sb.append(getIntfInsertedDate());
        sb.append(", modifiedBy=");
        sb.append(getModifiedBy());
        sb.append(", month=");
        sb.append(getMonth());
        sb.append(", reprocessedFlag=");
        sb.append(getReprocessedFlag());
        sb.append(", amountWithdrawn=");
        sb.append(getAmountWithdrawn());
        sb.append(", inventoryWdActualMasIntfId=");
        sb.append(getInventoryWdActualMasIntfId());
        sb.append(", quantityReceived=");
        sb.append(getQuantityReceived());
        sb.append(", unitsWithdrawn=");
        sb.append(getUnitsWithdrawn());
        sb.append(", reasonForFailure=");
        sb.append(getReasonForFailure());
        sb.append(", country=");
        sb.append(getCountry());
        sb.append(", itemIdentifierCodeQualifier=");
        sb.append(getItemIdentifierCodeQualifier());
        sb.append(", batchId=");
        sb.append(getBatchId());
        sb.append(", errorField=");
        sb.append(getErrorField());
        sb.append(", amountOnOrder=");
        sb.append(getAmountOnOrder());
        sb.append(", checkRecord=");
        sb.append(getCheckRecord());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(100);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.IvldInventoryWdActualMas");
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
            "<column><column-name>createdBy</column-name><column-value><![CDATA[");
        sb.append(getCreatedBy());
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
            "<column><column-name>ivldInventoryWdActualMasSid</column-name><column-value><![CDATA[");
        sb.append(getIvldInventoryWdActualMasSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>day</column-name><column-value><![CDATA[");
        sb.append(getDay());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>addChgDelIndicator</column-name><column-value><![CDATA[");
        sb.append(getAddChgDelIndicator());
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
            "<column><column-name>month</column-name><column-value><![CDATA[");
        sb.append(getMonth());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>reprocessedFlag</column-name><column-value><![CDATA[");
        sb.append(getReprocessedFlag());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>amountWithdrawn</column-name><column-value><![CDATA[");
        sb.append(getAmountWithdrawn());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>inventoryWdActualMasIntfId</column-name><column-value><![CDATA[");
        sb.append(getInventoryWdActualMasIntfId());
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
            "<column><column-name>reasonForFailure</column-name><column-value><![CDATA[");
        sb.append(getReasonForFailure());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>country</column-name><column-value><![CDATA[");
        sb.append(getCountry());
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
            "<column><column-name>errorField</column-name><column-value><![CDATA[");
        sb.append(getErrorField());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>amountOnOrder</column-name><column-value><![CDATA[");
        sb.append(getAmountOnOrder());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>checkRecord</column-name><column-value><![CDATA[");
        sb.append(getCheckRecord());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
