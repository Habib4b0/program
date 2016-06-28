package com.stpl.app.model;

import com.stpl.app.service.ClpSerializer;
import com.stpl.app.service.VwInventoryWdActualProjMasLocalServiceUtil;

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


public class VwInventoryWdActualProjMasClp extends BaseModelImpl<VwInventoryWdActualProjMas>
    implements VwInventoryWdActualProjMas {
    private double _quantityOnOrder;
    private String _week;
    private double _price;
    private double _amountOnHand;
    private String _isMaster;
    private String _companyName;
    private String _year;
    private String _itemId;
    private Date _modifiedDate;
    private String _organizationKey;
    private int _inventoryWdActualProjMasSid;
    private String _source;
    private String _createdBy;
    private Date _createdDate;
    private String _day;
    private String _addChgDelIndicator;
    private double _unitsOnHand;
    private double _amountReceived;
    private String _modifiedBy;
    private String _month;
    private double _amountWithdrawn;
    private double _quantityReceived;
    private double _unitsWithdrawn;
    private String _country;
    private String _companyId;
    private String _isForecast;
    private String _forecastVer;
    private String _batchId;
    private String _itemName;
    private double _amountOnOrder;
    private String _forecastName;
    private BaseModel<?> _vwInventoryWdActualProjMasRemoteModel;

    public VwInventoryWdActualProjMasClp() {
    }

    @Override
    public Class<?> getModelClass() {
        return VwInventoryWdActualProjMas.class;
    }

    @Override
    public String getModelClassName() {
        return VwInventoryWdActualProjMas.class.getName();
    }

    @Override
    public int getPrimaryKey() {
        return _inventoryWdActualProjMasSid;
    }

    @Override
    public void setPrimaryKey(int primaryKey) {
        setInventoryWdActualProjMasSid(primaryKey);
    }

    @Override
    public Serializable getPrimaryKeyObj() {
        return _inventoryWdActualProjMasSid;
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
        attributes.put("price", getPrice());
        attributes.put("amountOnHand", getAmountOnHand());
        attributes.put("isMaster", getIsMaster());
        attributes.put("companyName", getCompanyName());
        attributes.put("year", getYear());
        attributes.put("itemId", getItemId());
        attributes.put("modifiedDate", getModifiedDate());
        attributes.put("organizationKey", getOrganizationKey());
        attributes.put("inventoryWdActualProjMasSid",
            getInventoryWdActualProjMasSid());
        attributes.put("source", getSource());
        attributes.put("createdBy", getCreatedBy());
        attributes.put("createdDate", getCreatedDate());
        attributes.put("day", getDay());
        attributes.put("addChgDelIndicator", getAddChgDelIndicator());
        attributes.put("unitsOnHand", getUnitsOnHand());
        attributes.put("amountReceived", getAmountReceived());
        attributes.put("modifiedBy", getModifiedBy());
        attributes.put("month", getMonth());
        attributes.put("amountWithdrawn", getAmountWithdrawn());
        attributes.put("quantityReceived", getQuantityReceived());
        attributes.put("unitsWithdrawn", getUnitsWithdrawn());
        attributes.put("country", getCountry());
        attributes.put("companyId", getCompanyId());
        attributes.put("isForecast", getIsForecast());
        attributes.put("forecastVer", getForecastVer());
        attributes.put("batchId", getBatchId());
        attributes.put("itemName", getItemName());
        attributes.put("amountOnOrder", getAmountOnOrder());
        attributes.put("forecastName", getForecastName());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Double quantityOnOrder = (Double) attributes.get("quantityOnOrder");

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

        Double amountOnHand = (Double) attributes.get("amountOnHand");

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

        Integer inventoryWdActualProjMasSid = (Integer) attributes.get(
                "inventoryWdActualProjMasSid");

        if (inventoryWdActualProjMasSid != null) {
            setInventoryWdActualProjMasSid(inventoryWdActualProjMasSid);
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

        Double unitsOnHand = (Double) attributes.get("unitsOnHand");

        if (unitsOnHand != null) {
            setUnitsOnHand(unitsOnHand);
        }

        Double amountReceived = (Double) attributes.get("amountReceived");

        if (amountReceived != null) {
            setAmountReceived(amountReceived);
        }

        String modifiedBy = (String) attributes.get("modifiedBy");

        if (modifiedBy != null) {
            setModifiedBy(modifiedBy);
        }

        String month = (String) attributes.get("month");

        if (month != null) {
            setMonth(month);
        }

        Double amountWithdrawn = (Double) attributes.get("amountWithdrawn");

        if (amountWithdrawn != null) {
            setAmountWithdrawn(amountWithdrawn);
        }

        Double quantityReceived = (Double) attributes.get("quantityReceived");

        if (quantityReceived != null) {
            setQuantityReceived(quantityReceived);
        }

        Double unitsWithdrawn = (Double) attributes.get("unitsWithdrawn");

        if (unitsWithdrawn != null) {
            setUnitsWithdrawn(unitsWithdrawn);
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

        Double amountOnOrder = (Double) attributes.get("amountOnOrder");

        if (amountOnOrder != null) {
            setAmountOnOrder(amountOnOrder);
        }

        String forecastName = (String) attributes.get("forecastName");

        if (forecastName != null) {
            setForecastName(forecastName);
        }
    }

    @Override
    public double getQuantityOnOrder() {
        return _quantityOnOrder;
    }

    @Override
    public void setQuantityOnOrder(double quantityOnOrder) {
        _quantityOnOrder = quantityOnOrder;

        if (_vwInventoryWdActualProjMasRemoteModel != null) {
            try {
                Class<?> clazz = _vwInventoryWdActualProjMasRemoteModel.getClass();

                Method method = clazz.getMethod("setQuantityOnOrder",
                        double.class);

                method.invoke(_vwInventoryWdActualProjMasRemoteModel,
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

        if (_vwInventoryWdActualProjMasRemoteModel != null) {
            try {
                Class<?> clazz = _vwInventoryWdActualProjMasRemoteModel.getClass();

                Method method = clazz.getMethod("setWeek", String.class);

                method.invoke(_vwInventoryWdActualProjMasRemoteModel, week);
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

        if (_vwInventoryWdActualProjMasRemoteModel != null) {
            try {
                Class<?> clazz = _vwInventoryWdActualProjMasRemoteModel.getClass();

                Method method = clazz.getMethod("setPrice", double.class);

                method.invoke(_vwInventoryWdActualProjMasRemoteModel, price);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getAmountOnHand() {
        return _amountOnHand;
    }

    @Override
    public void setAmountOnHand(double amountOnHand) {
        _amountOnHand = amountOnHand;

        if (_vwInventoryWdActualProjMasRemoteModel != null) {
            try {
                Class<?> clazz = _vwInventoryWdActualProjMasRemoteModel.getClass();

                Method method = clazz.getMethod("setAmountOnHand", double.class);

                method.invoke(_vwInventoryWdActualProjMasRemoteModel,
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

        if (_vwInventoryWdActualProjMasRemoteModel != null) {
            try {
                Class<?> clazz = _vwInventoryWdActualProjMasRemoteModel.getClass();

                Method method = clazz.getMethod("setIsMaster", String.class);

                method.invoke(_vwInventoryWdActualProjMasRemoteModel, isMaster);
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

        if (_vwInventoryWdActualProjMasRemoteModel != null) {
            try {
                Class<?> clazz = _vwInventoryWdActualProjMasRemoteModel.getClass();

                Method method = clazz.getMethod("setCompanyName", String.class);

                method.invoke(_vwInventoryWdActualProjMasRemoteModel,
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

        if (_vwInventoryWdActualProjMasRemoteModel != null) {
            try {
                Class<?> clazz = _vwInventoryWdActualProjMasRemoteModel.getClass();

                Method method = clazz.getMethod("setYear", String.class);

                method.invoke(_vwInventoryWdActualProjMasRemoteModel, year);
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

        if (_vwInventoryWdActualProjMasRemoteModel != null) {
            try {
                Class<?> clazz = _vwInventoryWdActualProjMasRemoteModel.getClass();

                Method method = clazz.getMethod("setItemId", String.class);

                method.invoke(_vwInventoryWdActualProjMasRemoteModel, itemId);
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

        if (_vwInventoryWdActualProjMasRemoteModel != null) {
            try {
                Class<?> clazz = _vwInventoryWdActualProjMasRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedDate", Date.class);

                method.invoke(_vwInventoryWdActualProjMasRemoteModel,
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

        if (_vwInventoryWdActualProjMasRemoteModel != null) {
            try {
                Class<?> clazz = _vwInventoryWdActualProjMasRemoteModel.getClass();

                Method method = clazz.getMethod("setOrganizationKey",
                        String.class);

                method.invoke(_vwInventoryWdActualProjMasRemoteModel,
                    organizationKey);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public int getInventoryWdActualProjMasSid() {
        return _inventoryWdActualProjMasSid;
    }

    @Override
    public void setInventoryWdActualProjMasSid(int inventoryWdActualProjMasSid) {
        _inventoryWdActualProjMasSid = inventoryWdActualProjMasSid;

        if (_vwInventoryWdActualProjMasRemoteModel != null) {
            try {
                Class<?> clazz = _vwInventoryWdActualProjMasRemoteModel.getClass();

                Method method = clazz.getMethod("setInventoryWdActualProjMasSid",
                        int.class);

                method.invoke(_vwInventoryWdActualProjMasRemoteModel,
                    inventoryWdActualProjMasSid);
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

        if (_vwInventoryWdActualProjMasRemoteModel != null) {
            try {
                Class<?> clazz = _vwInventoryWdActualProjMasRemoteModel.getClass();

                Method method = clazz.getMethod("setSource", String.class);

                method.invoke(_vwInventoryWdActualProjMasRemoteModel, source);
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

        if (_vwInventoryWdActualProjMasRemoteModel != null) {
            try {
                Class<?> clazz = _vwInventoryWdActualProjMasRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedBy", String.class);

                method.invoke(_vwInventoryWdActualProjMasRemoteModel, createdBy);
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

        if (_vwInventoryWdActualProjMasRemoteModel != null) {
            try {
                Class<?> clazz = _vwInventoryWdActualProjMasRemoteModel.getClass();

                Method method = clazz.getMethod("setCreatedDate", Date.class);

                method.invoke(_vwInventoryWdActualProjMasRemoteModel,
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

        if (_vwInventoryWdActualProjMasRemoteModel != null) {
            try {
                Class<?> clazz = _vwInventoryWdActualProjMasRemoteModel.getClass();

                Method method = clazz.getMethod("setDay", String.class);

                method.invoke(_vwInventoryWdActualProjMasRemoteModel, day);
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

        if (_vwInventoryWdActualProjMasRemoteModel != null) {
            try {
                Class<?> clazz = _vwInventoryWdActualProjMasRemoteModel.getClass();

                Method method = clazz.getMethod("setAddChgDelIndicator",
                        String.class);

                method.invoke(_vwInventoryWdActualProjMasRemoteModel,
                    addChgDelIndicator);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getUnitsOnHand() {
        return _unitsOnHand;
    }

    @Override
    public void setUnitsOnHand(double unitsOnHand) {
        _unitsOnHand = unitsOnHand;

        if (_vwInventoryWdActualProjMasRemoteModel != null) {
            try {
                Class<?> clazz = _vwInventoryWdActualProjMasRemoteModel.getClass();

                Method method = clazz.getMethod("setUnitsOnHand", double.class);

                method.invoke(_vwInventoryWdActualProjMasRemoteModel,
                    unitsOnHand);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getAmountReceived() {
        return _amountReceived;
    }

    @Override
    public void setAmountReceived(double amountReceived) {
        _amountReceived = amountReceived;

        if (_vwInventoryWdActualProjMasRemoteModel != null) {
            try {
                Class<?> clazz = _vwInventoryWdActualProjMasRemoteModel.getClass();

                Method method = clazz.getMethod("setAmountReceived",
                        double.class);

                method.invoke(_vwInventoryWdActualProjMasRemoteModel,
                    amountReceived);
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

        if (_vwInventoryWdActualProjMasRemoteModel != null) {
            try {
                Class<?> clazz = _vwInventoryWdActualProjMasRemoteModel.getClass();

                Method method = clazz.getMethod("setModifiedBy", String.class);

                method.invoke(_vwInventoryWdActualProjMasRemoteModel, modifiedBy);
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

        if (_vwInventoryWdActualProjMasRemoteModel != null) {
            try {
                Class<?> clazz = _vwInventoryWdActualProjMasRemoteModel.getClass();

                Method method = clazz.getMethod("setMonth", String.class);

                method.invoke(_vwInventoryWdActualProjMasRemoteModel, month);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getAmountWithdrawn() {
        return _amountWithdrawn;
    }

    @Override
    public void setAmountWithdrawn(double amountWithdrawn) {
        _amountWithdrawn = amountWithdrawn;

        if (_vwInventoryWdActualProjMasRemoteModel != null) {
            try {
                Class<?> clazz = _vwInventoryWdActualProjMasRemoteModel.getClass();

                Method method = clazz.getMethod("setAmountWithdrawn",
                        double.class);

                method.invoke(_vwInventoryWdActualProjMasRemoteModel,
                    amountWithdrawn);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getQuantityReceived() {
        return _quantityReceived;
    }

    @Override
    public void setQuantityReceived(double quantityReceived) {
        _quantityReceived = quantityReceived;

        if (_vwInventoryWdActualProjMasRemoteModel != null) {
            try {
                Class<?> clazz = _vwInventoryWdActualProjMasRemoteModel.getClass();

                Method method = clazz.getMethod("setQuantityReceived",
                        double.class);

                method.invoke(_vwInventoryWdActualProjMasRemoteModel,
                    quantityReceived);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getUnitsWithdrawn() {
        return _unitsWithdrawn;
    }

    @Override
    public void setUnitsWithdrawn(double unitsWithdrawn) {
        _unitsWithdrawn = unitsWithdrawn;

        if (_vwInventoryWdActualProjMasRemoteModel != null) {
            try {
                Class<?> clazz = _vwInventoryWdActualProjMasRemoteModel.getClass();

                Method method = clazz.getMethod("setUnitsWithdrawn",
                        double.class);

                method.invoke(_vwInventoryWdActualProjMasRemoteModel,
                    unitsWithdrawn);
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

        if (_vwInventoryWdActualProjMasRemoteModel != null) {
            try {
                Class<?> clazz = _vwInventoryWdActualProjMasRemoteModel.getClass();

                Method method = clazz.getMethod("setCountry", String.class);

                method.invoke(_vwInventoryWdActualProjMasRemoteModel, country);
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

        if (_vwInventoryWdActualProjMasRemoteModel != null) {
            try {
                Class<?> clazz = _vwInventoryWdActualProjMasRemoteModel.getClass();

                Method method = clazz.getMethod("setCompanyId", String.class);

                method.invoke(_vwInventoryWdActualProjMasRemoteModel, companyId);
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

        if (_vwInventoryWdActualProjMasRemoteModel != null) {
            try {
                Class<?> clazz = _vwInventoryWdActualProjMasRemoteModel.getClass();

                Method method = clazz.getMethod("setIsForecast", String.class);

                method.invoke(_vwInventoryWdActualProjMasRemoteModel, isForecast);
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

        if (_vwInventoryWdActualProjMasRemoteModel != null) {
            try {
                Class<?> clazz = _vwInventoryWdActualProjMasRemoteModel.getClass();

                Method method = clazz.getMethod("setForecastVer", String.class);

                method.invoke(_vwInventoryWdActualProjMasRemoteModel,
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

        if (_vwInventoryWdActualProjMasRemoteModel != null) {
            try {
                Class<?> clazz = _vwInventoryWdActualProjMasRemoteModel.getClass();

                Method method = clazz.getMethod("setBatchId", String.class);

                method.invoke(_vwInventoryWdActualProjMasRemoteModel, batchId);
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

        if (_vwInventoryWdActualProjMasRemoteModel != null) {
            try {
                Class<?> clazz = _vwInventoryWdActualProjMasRemoteModel.getClass();

                Method method = clazz.getMethod("setItemName", String.class);

                method.invoke(_vwInventoryWdActualProjMasRemoteModel, itemName);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    @Override
    public double getAmountOnOrder() {
        return _amountOnOrder;
    }

    @Override
    public void setAmountOnOrder(double amountOnOrder) {
        _amountOnOrder = amountOnOrder;

        if (_vwInventoryWdActualProjMasRemoteModel != null) {
            try {
                Class<?> clazz = _vwInventoryWdActualProjMasRemoteModel.getClass();

                Method method = clazz.getMethod("setAmountOnOrder", double.class);

                method.invoke(_vwInventoryWdActualProjMasRemoteModel,
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

        if (_vwInventoryWdActualProjMasRemoteModel != null) {
            try {
                Class<?> clazz = _vwInventoryWdActualProjMasRemoteModel.getClass();

                Method method = clazz.getMethod("setForecastName", String.class);

                method.invoke(_vwInventoryWdActualProjMasRemoteModel,
                    forecastName);
            } catch (Exception e) {
                throw new UnsupportedOperationException(e);
            }
        }
    }

    public BaseModel<?> getVwInventoryWdActualProjMasRemoteModel() {
        return _vwInventoryWdActualProjMasRemoteModel;
    }

    public void setVwInventoryWdActualProjMasRemoteModel(
        BaseModel<?> vwInventoryWdActualProjMasRemoteModel) {
        _vwInventoryWdActualProjMasRemoteModel = vwInventoryWdActualProjMasRemoteModel;
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

        Class<?> remoteModelClass = _vwInventoryWdActualProjMasRemoteModel.getClass();

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

        Object returnValue = method.invoke(_vwInventoryWdActualProjMasRemoteModel,
                remoteParameterValues);

        if (returnValue != null) {
            returnValue = ClpSerializer.translateOutput(returnValue);
        }

        return returnValue;
    }

    @Override
    public void persist() throws SystemException {
        if (this.isNew()) {
            VwInventoryWdActualProjMasLocalServiceUtil.addVwInventoryWdActualProjMas(this);
        } else {
            VwInventoryWdActualProjMasLocalServiceUtil.updateVwInventoryWdActualProjMas(this);
        }
    }

    @Override
    public VwInventoryWdActualProjMas toEscapedModel() {
        return (VwInventoryWdActualProjMas) ProxyUtil.newProxyInstance(VwInventoryWdActualProjMas.class.getClassLoader(),
            new Class[] { VwInventoryWdActualProjMas.class },
            new AutoEscapeBeanHandler(this));
    }

    @Override
    public Object clone() {
        VwInventoryWdActualProjMasClp clone = new VwInventoryWdActualProjMasClp();

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
        clone.setInventoryWdActualProjMasSid(getInventoryWdActualProjMasSid());
        clone.setSource(getSource());
        clone.setCreatedBy(getCreatedBy());
        clone.setCreatedDate(getCreatedDate());
        clone.setDay(getDay());
        clone.setAddChgDelIndicator(getAddChgDelIndicator());
        clone.setUnitsOnHand(getUnitsOnHand());
        clone.setAmountReceived(getAmountReceived());
        clone.setModifiedBy(getModifiedBy());
        clone.setMonth(getMonth());
        clone.setAmountWithdrawn(getAmountWithdrawn());
        clone.setQuantityReceived(getQuantityReceived());
        clone.setUnitsWithdrawn(getUnitsWithdrawn());
        clone.setCountry(getCountry());
        clone.setCompanyId(getCompanyId());
        clone.setIsForecast(getIsForecast());
        clone.setForecastVer(getForecastVer());
        clone.setBatchId(getBatchId());
        clone.setItemName(getItemName());
        clone.setAmountOnOrder(getAmountOnOrder());
        clone.setForecastName(getForecastName());

        return clone;
    }

    @Override
    public int compareTo(VwInventoryWdActualProjMas vwInventoryWdActualProjMas) {
        int primaryKey = vwInventoryWdActualProjMas.getPrimaryKey();

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

        if (!(obj instanceof VwInventoryWdActualProjMasClp)) {
            return false;
        }

        VwInventoryWdActualProjMasClp vwInventoryWdActualProjMas = (VwInventoryWdActualProjMasClp) obj;

        int primaryKey = vwInventoryWdActualProjMas.getPrimaryKey();

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
        StringBundler sb = new StringBundler(63);

        sb.append("{quantityOnOrder=");
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
        sb.append(", inventoryWdActualProjMasSid=");
        sb.append(getInventoryWdActualProjMasSid());
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
        sb.append(", modifiedBy=");
        sb.append(getModifiedBy());
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
        sb.append(", companyId=");
        sb.append(getCompanyId());
        sb.append(", isForecast=");
        sb.append(getIsForecast());
        sb.append(", forecastVer=");
        sb.append(getForecastVer());
        sb.append(", batchId=");
        sb.append(getBatchId());
        sb.append(", itemName=");
        sb.append(getItemName());
        sb.append(", amountOnOrder=");
        sb.append(getAmountOnOrder());
        sb.append(", forecastName=");
        sb.append(getForecastName());
        sb.append("}");

        return sb.toString();
    }

    @Override
    public String toXmlString() {
        StringBundler sb = new StringBundler(97);

        sb.append("<model><model-name>");
        sb.append("com.stpl.app.model.VwInventoryWdActualProjMas");
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
            "<column><column-name>inventoryWdActualProjMasSid</column-name><column-value><![CDATA[");
        sb.append(getInventoryWdActualProjMasSid());
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
            "<column><column-name>modifiedBy</column-name><column-value><![CDATA[");
        sb.append(getModifiedBy());
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
            "<column><column-name>companyId</column-name><column-value><![CDATA[");
        sb.append(getCompanyId());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>isForecast</column-name><column-value><![CDATA[");
        sb.append(getIsForecast());
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
            "<column><column-name>amountOnOrder</column-name><column-value><![CDATA[");
        sb.append(getAmountOnOrder());
        sb.append("]]></column-value></column>");
        sb.append(
            "<column><column-name>forecastName</column-name><column-value><![CDATA[");
        sb.append(getForecastName());
        sb.append("]]></column-value></column>");

        sb.append("</model>");

        return sb.toString();
    }
}
