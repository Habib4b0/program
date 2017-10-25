package com.stpl.app.model;

import com.stpl.app.service.ClpSerializer;
import com.stpl.app.service.VwIvldInventoryWdActualProjMasLocalServiceUtil;

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


public class VwIvldInventoryWdActualProjMasClp extends BaseModelImpl<VwIvldInventoryWdActualProjMas>
    implements VwIvldInventoryWdActualProjMas {
    private int _ivldInventoryWdActualProjMasSid;
    private String _quantityOnOrder;
    private String _week;
    private double _price;
    private String _amountOnHand;
    private String _isMaster;
    private String _companyName;
    private String _year;
    private String _itemId;
    private Date _modifiedDate;
    private String _organizationKey;
    private String _source;
    private String _createdBy;
    private Date _createdDate;
    private String _day;
    private String _addChgDelIndicator;
    private String _unitsOnHand;
    private String _amountReceived;
    private String _errorCode;
    private Date _intfInsertedDate;
    private String _modifiedBy;
    private String _month;
    private String _reprocessedFlag;
    private String _amountWithdrawn;
    private String _quantityReceived;
    private String _unitsWithdrawn;
    private String _reasonForFailure;
    private String _country;
    private String _companyId;
    private String _isForecast;
    private String _inventoryWdActualProjMasIntfId;
    private String _forecastVer;
    private String _batchId;
    private String _itemName;
    private String _errorField;
    private String _amountOnOrder;
    private String _forecastName;
    private boolean _checkRecord;
    private BaseModel<?> _vwIvldInventoryWdActualProjMasRemoteModel;

    public VwIvldInventoryWdActualProjMasClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return VwIvldInventoryWdActualProjMas.class;
    }

    @Override
    public String getModelClassName() {
        return VwIvldInventoryWdActualProjMas.class.getName();
    }

    @Override
    public int getPrimaryKey() {
        return _ivldInventoryWdActualProjMasSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setIvldInventoryWdActualProjMasSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _ivldInventoryWdActualProjMasSid;
    }

    @Override
    public void setPrimaryKeyObj(Serializable primaryKeyObj) {
        setPrimaryKey(((Integer) primaryKeyObj).intValue());
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("ivldInventoryWdActualProjMasSid",
            getIvldInventoryWdActualProjMasSid());
        attributes.put("quantityOnOrder", getQuantityOnOrder());
        attributes.put("week", getWeek());
        attributes.put("price", getPrice());
        attributes.put("amountOnHand", getAmountOnHand());
        attributes.put("isMaster", getIsMaster());
        attributes.put("companyName", getCompanyName());
        attributes.put("year", getYear());
        attributes.put("itemId", getItemId());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("organizationKey", getOrganizationKey());
        attributes.put("source", getSource());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("day", getDay());
        attributes.put("addChgDelIndicator", getAddChgDelIndicator());
        attributes.put("unitsOnHand", getUnitsOnHand());
        attributes.put("amountReceived", getAmountReceived());
        attributes.put("errorCode", getErrorCode());
        attributes.put("intfInsertedDate", getIntfInsertedDate());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("month", getMonth());
        attributes.put("reprocessedFlag", getReprocessedFlag());
        attributes.put("amountWithdrawn", getAmountWithdrawn());
        attributes.put("quantityReceived", getQuantityReceived());
        attributes.put("unitsWithdrawn", getUnitsWithdrawn());
        attributes.put("reasonForFailure", getReasonForFailure());
        attributes.put("country", getCountry());
        attributes.put("companyId", getCompanyId());
        attributes.put("isForecast", getIsForecast());
        attributes.put("inventoryWdActualProjMasIntfId",
            getInventoryWdActualProjMasIntfId());
        attributes.put("forecastVer", getForecastVer());
        attributes.put("batchId", getBatchId());
        attributes.put("itemName", getItemName());
        attributes.put("errorField", getErrorField());
        attributes.put("amountOnOrder", getAmountOnOrder());
        attributes.put("forecastName", getForecastName());
        attributes.put("checkRecord", getCheckRecord());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Integer ivldInventoryWdActualProjMasSid = (Integer) attributes.get(
                "ivldInventoryWdActualProjMasSid");

        if (ivldInventoryWdActualProjMasSid != null) {
            setIvldInventoryWdActualProjMasSid(ivldInventoryWdActualProjMasSid);
        }

        String quantityOnOrder = (String) attributes.get("quantityOnOrder");

        if (quantityOnOrder != null) {
            setQuantityOnOrder(quantityOnOrder);
        }

        String week = (String) attributes.get("week");

        if (week != null) {
            setWeek(week);
        }

        Double price = (Double) attributes.get("price");

        if (price != null) {
            setPrice(price);
        }

        String amountOnHand = (String) attributes.get("amountOnHand");

        if (amountOnHand != null) {
            setAmountOnHand(amountOnHand);
        }

        String isMaster = (String) attributes.get("isMaster");

        if (isMaster != null) {
            setIsMaster(isMaster);
        }

        String companyName = (String) attributes.get("companyName");

        if (companyName != null) {
            setCompanyName(companyName);
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

        String companyId = (String) attributes.get("companyId");

        if (companyId != null) {
            setCompanyId(companyId);
        }

        String isForecast = (String) attributes.get("isForecast");

        if (isForecast != null) {
            setIsForecast(isForecast);
        }

        String inventoryWdActualProjMasIntfId = (String) attributes.get(
                "inventoryWdActualProjMasIntfId");

        if (inventoryWdActualProjMasIntfId != null) {
            setInventoryWdActualProjMasIntfId(inventoryWdActualProjMasIntfId);
        }

        String forecastVer = (String) attributes.get("forecastVer");

        if (forecastVer != null) {
            setForecastVer(forecastVer);
        }

        String batchId = (String) attributes.get("batchId");

        if (batchId != null) {
            setBatchId(batchId);
        }

        String itemName = (String) attributes.get("itemName");

        if (itemName != null) {
            setItemName(itemName);
        }

        String errorField = (String) attributes.get("errorField");

        if (errorField != null) {
            setErrorField(errorField);
        }

        String amountOnOrder = (String) attributes.get("amountOnOrder");

        if (amountOnOrder != null) {
            setAmountOnOrder(amountOnOrder);
        }

        String forecastName = (String) attributes.get("forecastName");

        if (forecastName != null) {
            setForecastName(forecastName);
        }

        Boolean checkRecord = (Boolean) attributes.get("checkRecord");

        if (checkRecord != null) {
            setCheckRecord(checkRecord);
        }
    }

    @Override
    public int getIvldInventoryWdActualProjMasSid() {
        return _ivldInventoryWdActualProjMasSid;
    }

    @Override
    public void setIvldInventoryWdActualProjMasSid(
        int ivldInventoryWdActualProjMasSid) {
        _ivldInventoryWdActualProjMasSid = ivldInventoryWdActualProjMasSid;

        if (_vwIvldInventoryWdActualProjMasRemoteModel != null) {
            try {
                Class<?> clazz = _vwIvldInventoryWdActualProjMasRemoteModel.getClass();

                Method method = clazz.getMethod("setIvldInventoryWdActualProjMasSid",
                        int.class);

                method.invoke(_vwIvldInventoryWdActualProjMasRemoteModel,
                    ivldInventoryWdActualProjMasSid);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getQuantityOnOrder() {
        return _quantityOnOrder;
    }

    @Override
    public void setQuantityOnOrder(String quantityOnOrder) {
        _quantityOnOrder = quantityOnOrder;

        if (_vwIvldInventoryWdActualProjMasRemoteModel != null) {
            try {
                Class<?> clazz = _vwIvldInventoryWdActualProjMasRemoteModel.getClass();

                Method method = clazz.getMethod("setQuantityOnOrder",
                        String.class);

                method.invoke(_vwIvldInventoryWdActualProjMasRemoteModel,
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

        if (_vwIvldInventoryWdActualProjMasRemoteModel != null) {
            try {
                Class<?> clazz = _vwIvldInventoryWdActualProjMasRemoteModel.getClass();

                Method method = clazz.getMethod("setWeek", String.class);

                method.invoke(_vwIvldInventoryWdActualProjMasRemoteModel, week);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getPrice() {
        return _price;
    }

    @Override
    public void setPrice(double price) {
        _price = price;

        if (_vwIvldInventoryWdActualProjMasRemoteModel != null) {
            try {
                Class<?> clazz = _vwIvldInventoryWdActualProjMasRemoteModel.getClass();

                Method method = clazz.getMethod("setPrice", double.class);

                method.invoke(_vwIvldInventoryWdActualProjMasRemoteModel, price);
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

        if (_vwIvldInventoryWdActualProjMasRemoteModel != null) {
            try {
                Class<?> clazz = _vwIvldInventoryWdActualProjMasRemoteModel.getClass();

                Method method = clazz.getMethod("setAmountOnHand", String.class);

                method.invoke(_vwIvldInventoryWdActualProjMasRemoteModel,
                    amountOnHand);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getIsMaster() {
        return _isMaster;
    }

    @Override
    public void setIsMaster(String isMaster) {
        _isMaster = isMaster;

        if (_vwIvldInventoryWdActualProjMasRemoteModel != null) {
            try {
                Class<?> clazz = _vwIvldInventoryWdActualProjMasRemoteModel.getClass();

                Method method = clazz.getMethod("setIsMaster", String.class);

                method.invoke(_vwIvldInventoryWdActualProjMasRemoteModel,
                    isMaster);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getCompanyName() {
        return _companyName;
    }

    @Override
    public void setCompanyName(String companyName) {
        _companyName = companyName;

        if (_vwIvldInventoryWdActualProjMasRemoteModel != null) {
            try {
                Class<?> clazz = _vwIvldInventoryWdActualProjMasRemoteModel.getClass();

                Method method = clazz.getMethod("setCompanyName", String.class);

                method.invoke(_vwIvldInventoryWdActualProjMasRemoteModel,
                    companyName);
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

        if (_vwIvldInventoryWdActualProjMasRemoteModel != null) {
            try {
                Class<?> clazz = _vwIvldInventoryWdActualProjMasRemoteModel.getClass();

                Method method = clazz.getMethod("setYear", String.class);

                method.invoke(_vwIvldInventoryWdActualProjMasRemoteModel, year);
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

        if (_vwIvldInventoryWdActualProjMasRemoteModel != null) {
            try {
                Class<?> clazz = _vwIvldInventoryWdActualProjMasRemoteModel.getClass();

                Method method = clazz.getMethod("setItemId", String.class);

                method.invoke(_vwIvldInventoryWdActualProjMasRemoteModel, itemId);
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

        if (_vwIvldInventoryWdActualProjMasRemoteModel != null) {
            try {
                Class<?> clazz = _vwIvldInventoryWdActualProjMasRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedDate", Date.class);

                method.invoke(_vwIvldInventoryWdActualProjMasRemoteModel,
                    modifiedDate);
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

        if (_vwIvldInventoryWdActualProjMasRemoteModel != null) {
            try {
                Class<?> clazz = _vwIvldInventoryWdActualProjMasRemoteModel.getClass();

                Method method = clazz.getMethod("setOrganizationKey",
                        String.class);

                method.invoke(_vwIvldInventoryWdActualProjMasRemoteModel,
                    organizationKey);
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

        if (_vwIvldInventoryWdActualProjMasRemoteModel != null) {
            try {
                Class<?> clazz = _vwIvldInventoryWdActualProjMasRemoteModel.getClass();

                Method method = clazz.getMethod("setSource", String.class);

                method.invoke(_vwIvldInventoryWdActualProjMasRemoteModel, source);
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

        if (_vwIvldInventoryWdActualProjMasRemoteModel != null) {
            try {
                Class<?> clazz = _vwIvldInventoryWdActualProjMasRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedBy", String.class);

                method.invoke(_vwIvldInventoryWdActualProjMasRemoteModel,
                    createdBy);
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

        if (_vwIvldInventoryWdActualProjMasRemoteModel != null) {
            try {
                Class<?> clazz = _vwIvldInventoryWdActualProjMasRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedDate", Date.class);

                method.invoke(_vwIvldInventoryWdActualProjMasRemoteModel,
                    createdDate);
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

        if (_vwIvldInventoryWdActualProjMasRemoteModel != null) {
            try {
                Class<?> clazz = _vwIvldInventoryWdActualProjMasRemoteModel.getClass();

                Method method = clazz.getMethod("setDay", String.class);

                method.invoke(_vwIvldInventoryWdActualProjMasRemoteModel, day);
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

        if (_vwIvldInventoryWdActualProjMasRemoteModel != null) {
            try {
                Class<?> clazz = _vwIvldInventoryWdActualProjMasRemoteModel.getClass();

                Method method = clazz.getMethod("setAddChgDelIndicator",
                        String.class);

                method.invoke(_vwIvldInventoryWdActualProjMasRemoteModel,
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

        if (_vwIvldInventoryWdActualProjMasRemoteModel != null) {
            try {
                Class<?> clazz = _vwIvldInventoryWdActualProjMasRemoteModel.getClass();

                Method method = clazz.getMethod("setUnitsOnHand", String.class);

                method.invoke(_vwIvldInventoryWdActualProjMasRemoteModel,
                    unitsOnHand);
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

        if (_vwIvldInventoryWdActualProjMasRemoteModel != null) {
            try {
                Class<?> clazz = _vwIvldInventoryWdActualProjMasRemoteModel.getClass();

                Method method = clazz.getMethod("setAmountReceived",
                        String.class);

                method.invoke(_vwIvldInventoryWdActualProjMasRemoteModel,
                    amountReceived);
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

        if (_vwIvldInventoryWdActualProjMasRemoteModel != null) {
            try {
                Class<?> clazz = _vwIvldInventoryWdActualProjMasRemoteModel.getClass();

                Method method = clazz.getMethod("setErrorCode", String.class);

                method.invoke(_vwIvldInventoryWdActualProjMasRemoteModel,
                    errorCode);
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

        if (_vwIvldInventoryWdActualProjMasRemoteModel != null) {
            try {
                Class<?> clazz = _vwIvldInventoryWdActualProjMasRemoteModel.getClass();

                Method method = clazz.getMethod("setIntfInsertedDate",
                        Date.class);

                method.invoke(_vwIvldInventoryWdActualProjMasRemoteModel,
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

        if (_vwIvldInventoryWdActualProjMasRemoteModel != null) {
            try {
                Class<?> clazz = _vwIvldInventoryWdActualProjMasRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedBy", String.class);

                method.invoke(_vwIvldInventoryWdActualProjMasRemoteModel,
                    modifiedBy);
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

        if (_vwIvldInventoryWdActualProjMasRemoteModel != null) {
            try {
                Class<?> clazz = _vwIvldInventoryWdActualProjMasRemoteModel.getClass();

                Method method = clazz.getMethod("setMonth", String.class);

                method.invoke(_vwIvldInventoryWdActualProjMasRemoteModel, month);
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

        if (_vwIvldInventoryWdActualProjMasRemoteModel != null) {
            try {
                Class<?> clazz = _vwIvldInventoryWdActualProjMasRemoteModel.getClass();

                Method method = clazz.getMethod("setReprocessedFlag",
                        String.class);

                method.invoke(_vwIvldInventoryWdActualProjMasRemoteModel,
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

        if (_vwIvldInventoryWdActualProjMasRemoteModel != null) {
            try {
                Class<?> clazz = _vwIvldInventoryWdActualProjMasRemoteModel.getClass();

                Method method = clazz.getMethod("setAmountWithdrawn",
                        String.class);

                method.invoke(_vwIvldInventoryWdActualProjMasRemoteModel,
                    amountWithdrawn);
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

        if (_vwIvldInventoryWdActualProjMasRemoteModel != null) {
            try {
                Class<?> clazz = _vwIvldInventoryWdActualProjMasRemoteModel.getClass();

                Method method = clazz.getMethod("setQuantityReceived",
                        String.class);

                method.invoke(_vwIvldInventoryWdActualProjMasRemoteModel,
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

        if (_vwIvldInventoryWdActualProjMasRemoteModel != null) {
            try {
                Class<?> clazz = _vwIvldInventoryWdActualProjMasRemoteModel.getClass();

                Method method = clazz.getMethod("setUnitsWithdrawn",
                        String.class);

                method.invoke(_vwIvldInventoryWdActualProjMasRemoteModel,
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

        if (_vwIvldInventoryWdActualProjMasRemoteModel != null) {
            try {
                Class<?> clazz = _vwIvldInventoryWdActualProjMasRemoteModel.getClass();

                Method method = clazz.getMethod("setReasonForFailure",
                        String.class);

                method.invoke(_vwIvldInventoryWdActualProjMasRemoteModel,
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

        if (_vwIvldInventoryWdActualProjMasRemoteModel != null) {
            try {
                Class<?> clazz = _vwIvldInventoryWdActualProjMasRemoteModel.getClass();

                Method method = clazz.getMethod("setCountry", String.class);

                method.invoke(_vwIvldInventoryWdActualProjMasRemoteModel,
                    country);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getCompanyId() {
        return _companyId;
    }

    @Override
    public void setCompanyId(String companyId) {
        _companyId = companyId;

        if (_vwIvldInventoryWdActualProjMasRemoteModel != null) {
            try {
                Class<?> clazz = _vwIvldInventoryWdActualProjMasRemoteModel.getClass();

                Method method = clazz.getMethod("setCompanyId", String.class);

                method.invoke(_vwIvldInventoryWdActualProjMasRemoteModel,
                    companyId);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getIsForecast() {
        return _isForecast;
    }

    @Override
    public void setIsForecast(String isForecast) {
        _isForecast = isForecast;

        if (_vwIvldInventoryWdActualProjMasRemoteModel != null) {
            try {
                Class<?> clazz = _vwIvldInventoryWdActualProjMasRemoteModel.getClass();

                Method method = clazz.getMethod("setIsForecast", String.class);

                method.invoke(_vwIvldInventoryWdActualProjMasRemoteModel,
                    isForecast);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public String getInventoryWdActualProjMasIntfId() {
        return _inventoryWdActualProjMasIntfId;
    }

    @Override
    public void setInventoryWdActualProjMasIntfId(
        String inventoryWdActualProjMasIntfId) {
        _inventoryWdActualProjMasIntfId = inventoryWdActualProjMasIntfId;

        if (_vwIvldInventoryWdActualProjMasRemoteModel != null) {
            try {
                Class<?> clazz = _vwIvldInventoryWdActualProjMasRemoteModel.getClass();

                Method method = clazz.getMethod("setInventoryWdActualProjMasIntfId",
                        String.class);

                method.invoke(_vwIvldInventoryWdActualProjMasRemoteModel,
                    inventoryWdActualProjMasIntfId);
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

        if (_vwIvldInventoryWdActualProjMasRemoteModel != null) {
            try {
                Class<?> clazz = _vwIvldInventoryWdActualProjMasRemoteModel.getClass();

                Method method = clazz.getMethod("setForecastVer", String.class);

                method.invoke(_vwIvldInventoryWdActualProjMasRemoteModel,
                    forecastVer);
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

        if (_vwIvldInventoryWdActualProjMasRemoteModel != null) {
            try {
                Class<?> clazz = _vwIvldInventoryWdActualProjMasRemoteModel.getClass();

                Method method = clazz.getMethod("setBatchId", String.class);

                method.invoke(_vwIvldInventoryWdActualProjMasRemoteModel,
                    batchId);
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

        if (_vwIvldInventoryWdActualProjMasRemoteModel != null) {
            try {
                Class<?> clazz = _vwIvldInventoryWdActualProjMasRemoteModel.getClass();

                Method method = clazz.getMethod("setItemName", String.class);

                method.invoke(_vwIvldInventoryWdActualProjMasRemoteModel,
                    itemName);
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

        if (_vwIvldInventoryWdActualProjMasRemoteModel != null) {
            try {
                Class<?> clazz = _vwIvldInventoryWdActualProjMasRemoteModel.getClass();

                Method method = clazz.getMethod("setErrorField", String.class);

                method.invoke(_vwIvldInventoryWdActualProjMasRemoteModel,
                    errorField);
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

        if (_vwIvldInventoryWdActualProjMasRemoteModel != null) {
            try {
                Class<?> clazz = _vwIvldInventoryWdActualProjMasRemoteModel.getClass();

                Method method = clazz.getMethod("setAmountOnOrder", String.class);

                method.invoke(_vwIvldInventoryWdActualProjMasRemoteModel,
                    amountOnOrder);
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

        if (_vwIvldInventoryWdActualProjMasRemoteModel != null) {
            try {
                Class<?> clazz = _vwIvldInventoryWdActualProjMasRemoteModel.getClass();

                Method method = clazz.getMethod("setForecastName", String.class);

                method.invoke(_vwIvldInventoryWdActualProjMasRemoteModel,
                    forecastName);
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

        if (_vwIvldInventoryWdActualProjMasRemoteModel != null) {
            try {
                Class<?> clazz = _vwIvldInventoryWdActualProjMasRemoteModel.getClass();

                Method method = clazz.getMethod("setCheckRecord", boolean.class);

                method.invoke(_vwIvldInventoryWdActualProjMasRemoteModel,
                    checkRecord);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getVwIvldInventoryWdActualProjMasRemoteModel() {
        return _vwIvldInventoryWdActualProjMasRemoteModel;
    }

    public void setVwIvldInventoryWdActualProjMasRemoteModel(
        BaseModel<?> vwIvldInventoryWdActualProjMasRemoteModel) {
        _vwIvldInventoryWdActualProjMasRemoteModel = vwIvldInventoryWdActualProjMasRemoteModel;
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

        Class<?> remoteModelClass = _vwIvldInventoryWdActualProjMasRemoteModel.getClass();

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

        Object returnValue = method.invoke(_vwIvldInventoryWdActualProjMasRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            VwIvldInventoryWdActualProjMasLocalServiceUtil.addVwIvldInventoryWdActualProjMas(this);
        } else {
            VwIvldInventoryWdActualProjMasLocalServiceUtil.updateVwIvldInventoryWdActualProjMas(this);
        }
    }

    @Override
    public VwIvldInventoryWdActualProjMas toEscapedModel() {
        return (VwIvldInventoryWdActualProjMas) ProxyUtil.newProxyInstance(VwIvldInventoryWdActualProjMas.class.getClassLoader(),
            new Class[] { VwIvldInventoryWdActualProjMas.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        VwIvldInventoryWdActualProjMasClp clone = new VwIvldInventoryWdActualProjMasClp();

        clone.setIvldInventoryWdActualProjMasSid(getIvldInventoryWdActualProjMasSid());
        clone.setQuantityOnOrder(getQuantityOnOrder());
        clone.setWeek(getWeek());
        clone.setPrice(getPrice());
        clone.setAmountOnHand(getAmountOnHand());
        clone.setIsMaster(getIsMaster());
        clone.setCompanyName(getCompanyName());
        clone.setYear(getYear());
        clone.setItemId(getItemId());
        clone.setModifiedDate(getModifiedDate());
        clone.setOrganizationKey(getOrganizationKey());
        clone.setSource(getSource());
        clone.setCreatedBy(getCreatedBy());
        clone.setCreatedDate(getCreatedDate());
        clone.setDay(getDay());
        clone.setAddChgDelIndicator(getAddChgDelIndicator());
        clone.setUnitsOnHand(getUnitsOnHand());
        clone.setAmountReceived(getAmountReceived());
        clone.setErrorCode(getErrorCode());
        clone.setIntfInsertedDate(getIntfInsertedDate());
        clone.setModifiedBy(getModifiedBy());
        clone.setMonth(getMonth());
        clone.setReprocessedFlag(getReprocessedFlag());
        clone.setAmountWithdrawn(getAmountWithdrawn());
        clone.setQuantityReceived(getQuantityReceived());
        clone.setUnitsWithdrawn(getUnitsWithdrawn());
        clone.setReasonForFailure(getReasonForFailure());
        clone.setCountry(getCountry());
        clone.setCompanyId(getCompanyId());
        clone.setIsForecast(getIsForecast());
        clone.setInventoryWdActualProjMasIntfId(getInventoryWdActualProjMasIntfId());
        clone.setForecastVer(getForecastVer());
        clone.setBatchId(getBatchId());
        clone.setItemName(getItemName());
        clone.setErrorField(getErrorField());
        clone.setAmountOnOrder(getAmountOnOrder());
        clone.setForecastName(getForecastName());
        clone.setCheckRecord(getCheckRecord());

        return clone;
    }

    @Override
    public int compareTo(
        VwIvldInventoryWdActualProjMas vwIvldInventoryWdActualProjMas) {
        int primaryKey = vwIvldInventoryWdActualProjMas.getPrimaryKey();

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

        if (!(obj instanceof VwIvldInventoryWdActualProjMasClp)) {
            return false;
        }

        VwIvldInventoryWdActualProjMasClp vwIvldInventoryWdActualProjMas = (VwIvldInventoryWdActualProjMasClp) obj;

        int primaryKey = vwIvldInventoryWdActualProjMas.getPrimaryKey();

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
        StringBundler sb = new StringBundler(77);

        sb.append("{ivldInventoryWdActualProjMasSid=");
        sb.append(getIvldInventoryWdActualProjMasSid());
        sb.append(", quantityOnOrder=");
        sb.append(getQuantityOnOrder());
        sb.append(", week=");
        sb.append(getWeek());
        sb.append(", price=");
        sb.append(getPrice());
        sb.append(", amountOnHand=");
        sb.append(getAmountOnHand());
        sb.append(", isMaster=");
        sb.append(getIsMaster());
        sb.append(", companyName=");
        sb.append(getCompanyName());
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
        sb.append(", addChgDelIndicator=");
        sb.append(getAddChgDelIndicator());
        sb.append(", unitsOnHand=");
        sb.append(getUnitsOnHand());
        sb.append(", amountReceived=");
        sb.append(getAmountReceived());
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
        sb.append(", quantityReceived=");
        sb.append(getQuantityReceived());
        sb.append(", unitsWithdrawn=");
        sb.append(getUnitsWithdrawn());
        sb.append(", reasonForFailure=");
        sb.append(getReasonForFailure());
        sb.append(", country=");
        sb.append(getCountry());
        sb.append(", companyId=");
        sb.append(getCompanyId());
        sb.append(", isForecast=");
        sb.append(getIsForecast());
        sb.append(", inventoryWdActualProjMasIntfId=");
        sb.append(getInventoryWdActualProjMasIntfId());
        sb.append(", forecastVer=");
        sb.append(getForecastVer());
        sb.append(", batchId=");
        sb.append(getBatchId());
        sb.append(", itemName=");
        sb.append(getItemName());
        sb.append(", errorField=");
        sb.append(getErrorField());
        sb.append(", amountOnOrder=");
        sb.append(getAmountOnOrder());
        sb.append(", forecastName=");
        sb.append(getForecastName());
        sb.append(", checkRecord=");
        sb.append(getCheckRecord());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(118);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.VwIvldInventoryWdActualProjMas");
        sb.append("</model-name>");

        sb.append(
            "<column><column-name>ivldInventoryWdActualProjMasSid</column-name><column-value><![CDATA[");
        sb.append(getIvldInventoryWdActualProjMasSid());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>quantityOnOrder</column-name><column-value><![CDATA[");
        sb.append(getQuantityOnOrder());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>week</column-name><column-value><![CDATA[");
        sb.append(getWeek());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>price</column-name><column-value><![CDATA[");
        sb.append(getPrice());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>amountOnHand</column-name><column-value><![CDATA[");
        sb.append(getAmountOnHand());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>isMaster</column-name><column-value><![CDATA[");
        sb.append(getIsMaster());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>companyName</column-name><column-value><![CDATA[");
        sb.append(getCompanyName());
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
            "<column><column-name>companyId</column-name><column-value><![CDATA[");
        sb.append(getCompanyId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>isForecast</column-name><column-value><![CDATA[");
        sb.append(getIsForecast());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>inventoryWdActualProjMasIntfId</column-name><column-value><![CDATA[");
        sb.append(getInventoryWdActualProjMasIntfId());
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
            "<column><column-name>itemName</column-name><column-value><![CDATA[");
        sb.append(getItemName());
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
            "<column><column-name>forecastName</column-name><column-value><![CDATA[");
        sb.append(getForecastName());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>checkRecord</column-name><column-value><![CDATA[");
        sb.append(getCheckRecord());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
